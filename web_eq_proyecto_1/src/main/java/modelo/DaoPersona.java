package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Clase DaoPersona.
 * 
 * Esta clase implementa el patrón Singleton para gestionar el acceso a la base de datos
 * y proporciona métodos para realizar operaciones CRUD en la tabla `persona`.
 */
public class DaoPersona {

	/**
	 * Conexión a la base de datos.
	 * 
	 * Esta es una conexión compartida utilizada por todos los métodos de la clase para
	 * interactuar con la base de datos.
	 * 
	 */
	public static Connection con = null;
	
	/**
	 * Instancia única de DaoPersona.
	 * 
	 * Esta es la instancia única que se utiliza para implementar el patrón Singleton.
	 */
	private static DaoPersona instance = null;

	/**
	 * Constructor de DaoPersona.
	 * 
	 * Este constructor inicializa la conexión a la base de datos utilizando la clase DBConnection.
	 * 
	 * @throws SQLException Si ocurre un error al obtener la conexión a la base de datos.
	 */
	public DaoPersona() throws SQLException {

		this.con = DBConection.getConnection();

	}
	
	/**
	 *  Obtiene la instancia única de DaoPersona.
	 *  
	 *  Este método sigue el patrón Singleton para garantizar que solo exista una instancia de DaoPersona.
	 *  Está sincronizado para ser seguro en un entorno multi-hilo.
	 *  
	 * @return La instancia única de DaoPersona.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos durante la creación de la instancia.
	 */
	public static synchronized DaoPersona getInstance() throws SQLException {

		if (instance == null) {
			instance = new DaoPersona();
		}

		return instance;
	}

	/**
	 * Método de inserción en la base de datos del objeto Persona.
	 * 
	 * Este método inserta una nueva fila en la tabla `persona` con los valores del objeto `Persona` proporcionado.
	 * 
	 * @param p El objeto Persona que contiene los datos a insertar en la base de datos.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public void insertar(Persona p) throws SQLException {

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO persona (nombre,email,permiso) VALUES (?,?,?)");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getEmail());
			ps.setInt(3, p.getPermiso());

			int filas = ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}
	}


	/**
	 * Obtiene una instancia de Persona desde la base de datos usando su identificador único.
	 * 
	 * @param id El identificador único de la persona.
	 * @return Un objeto Persona si se encuentra una fila con el ID proporcionado, o null si no se encuentra.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Persona obtenerPorID(int id) throws SQLException {
		String sql = "SELECT * FROM persona WHERE id=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				} else {
					return null;
				}
			}
		}
	}

	/**
	 * Autentica a un usuario con el correo electrónico y la contraseña proporcionados.
	 * 
	 * Este método verifica si existe un usuario en la base de datos con el correo electrónico y la contraseña proporcionados.
	 * Si se encuentra un usuario, retorna un objeto `Persona` con la información del usuario. De lo contrario, retorna null.
	 * 
	 * @param p El objeto Persona que contiene el correo electrónico a verificar.
	 * @param pass La contraseña del usuario a verificar.
	 * @return Un objeto `Persona` si se encuentra una coincidencia en la base de datos, o null si no se encuentra ninguna coincidencia.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Persona logueando(Persona p, String pass) throws SQLException {
		String sql = "SELECT * FROM persona WHERE email=? AND pass=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Persona aux = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getEmail());
			ps.setString(2, pass);

			System.out.println("Executing query: " + ps.toString());

			rs = ps.executeQuery();

			if (rs.next()) {
				aux = new Persona();
				aux.setId(rs.getInt("id"));
				aux.setNombre(rs.getString("nombre"));
				aux.setEmail(rs.getString("email"));
				aux.setPermiso(rs.getInt("permiso"));
				System.out.println("User found: " + aux.getEmail());
			} else {
				System.out.println("No user found with email: " + p.getEmail());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e; // Re-lanzamos la excepción para pdoer visualizarla.
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return aux; // Retornará null si no se encontró ninguna coincidencia
	}

	/**
	 * Registra un nuevo usuario en la base de datos.
	 * 
	 * Este método inserta una nueva fila en la tabla `persona` con el email, la contraseña y el nombre proporcionados.
	 * 
	 * @param email El correo electrónico del usuario que se va a registrar.
	 * @param password La contraseña del usuario que se va a registrar.
	 * @param nombre El nombre del usuario que se va a registrar.
	 * @return
	 * @throws SQLException
	 */
	public boolean registrarUsuario(String email, String password, String nombre) throws SQLException {
		PreparedStatement ps = null;
		boolean registrado = false;

		try {
			String sql = "INSERT INTO persona (email, pass, nombre) VALUES (?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, nombre);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				registrado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return registrado;
	}

	/**
	 * Actualiza los datos de una persona existente en la base de datos.
	 * 
	 * Este método toma un objeto `Persona` que contiene los nuevos datos de una persona
	 * y actualiza la fila correspondiente en la base de datos utilizando el ID de la persona.
	 * 
	 * @param p El objeto Persona que contiene los datos actualizados.
	 * @return El objeto Persona con los datos actualizados.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Persona actualizar(Persona p) throws SQLException {

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE persona SET nombre=?,email=?,permiso=? WHERE id=?");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getEmail());
			ps.setInt(3, p.getPermiso());
			ps.setInt(4, p.getId());

			int filas = ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}
		return p;

	}

	/**
	 * Borra una persona de la base de datos por su ID.
	 * 
	 * Este método elimina la fila de la base de datos que corresponde al ID proporcionado.
	 * 
	 * @param id El identificador de la persona a borrar.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public void borrar(int id) throws SQLException {

		String sql = "DELETE FROM persona WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();

	}

	/**
	 * Lista todas las personas de la base de datos.
	 * 
	 * Este método recupera todas las filas de la tabla `persona` de la base de datos y
	 * las convierte en una lista de objetos `Persona`.
	 * 
	 * @return Una lista de objetos Persona que contiene todas las filas de la tabla `persona`.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public ArrayList<Persona> listar() throws SQLException {

		String sql = "SELECT * FROM persona";
		ArrayList<Persona> result = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql); 
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				result.add(new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		}
		return result;
	}
	

	/**
	 * Metodo listar que retorna los usuarios con el filtrado de tipo.
	 * 
	 * Este método obtiene una lista de objetos `Persona` desde la base de datos cuyo campo `permiso`
	 * coincide con el tipo proporcionado.
	 * 
	 * @param tipo
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Persona> listar(int tipo) throws SQLException {

		String sql = "SELECT * FROM persona WHERE permiso = ?";
		ArrayList<Persona> result = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, tipo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					result.add(new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				}
			}
		}

		return result;
	}


	/**
	 * Retorna una lista de usuarios en formato JSON.
	 * 
	 * Este método obtiene una lista de todos los usuarios en la base de datos y la convierte en una cadena JSON.
	 * 
	 * @return Una cadena JSON que representa la lista de todos los usuarios.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public String listarJson() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(this.listar());
	}

	/**
	 * Retorna una lista de usuarios filtrados por tipo en formato JSON.
	 * 
	 * Este método obtiene una lista de usuarios de la base de datos cuyo campo `permiso` coincide con el tipo proporcionado
	 * y la convierte en una cadena JSON.
	 * 
	 * @param tipo El tipo de permiso para filtrar los usuarios
	 * @return Una cadena JSON que representa la lista de usuarios filtrados por tipo.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public String listarJson(int tipo) throws SQLException {

		Gson gson = new Gson();
		ArrayList<Persona> personas = this.listar(tipo);
		return gson.toJson(personas);
	}

	/**
	 * Calcula el hash MD5 de una cadena de texto.
	 * 
	 * Este método toma una cadena de texto como entrada y devuelve su hash MD5 correspondiente.
	 * 
	 * @param input La cadena de texto a la que se le calculará el hash MD5.
	 * @return Una cadena que representa el hash MD5 de la entrada.
	 */
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
