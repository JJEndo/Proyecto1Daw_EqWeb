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

public class DaoPersona {

	public static Connection con = null;

	private static DaoPersona instance = null;

	public DaoPersona() throws SQLException {

		this.con = DBConection.getConnection();

	}

	public static synchronized DaoPersona getInstance() throws SQLException {

		if (instance == null) {
			instance = new DaoPersona();
		}

		return instance;
	}

	/**
	 * Metodo de insercción en la BD del objeto persona.
	 * 
	 * @param p
	 * @throws SQLException
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

	/*
	 * public Persona obtenerPorID(int id) throws SQLException {
	 * 
	 * String sql = "SELECT * FROM persona WHERE id=?"; PreparedStatement ps =
	 * con.prepareStatement(sql); ps.setInt(1, id);
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * rs.next();
	 * 
	 * Persona p =new Persona(rs.getInt(1), rs.getString(2), rs.getString(3),
	 * rs.getInt(4));
	 * 
	 * return p; }
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
			throw e; // Re-lanzamos la excepción para que el llamante pueda manejarla
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

	/*
	 * try { ps = con.prepareStatement(sql); ps.setString(1, p.getEmail());
	 * ps.setString(2, pass);
	 * 
	 * rs = ps.executeQuery();
	 * 
	 * if (rs.next()) { aux = new Persona(); aux.setId(rs.getInt("id"));
	 * aux.setNombre(rs.getString("nombre")); aux.setEmail(rs.getString("email"));
	 * aux.setPermiso(rs.getInt("permiso")); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); throw e; // Re-lanzamos la
	 * excepción para que el llamante pueda manejarla } finally { if (rs != null) {
	 * try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } } if (ps
	 * != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace();
	 * } } }
	 * 
	 * return aux; // Retornará null si no se encontró ninguna coincidencia }
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

	public void borrar(int id) throws SQLException {

		String sql = "DELETE FROM persona WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();

	}

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
	/*
	 * PreparedStatement ps = con.prepareStatement(sql);
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * ArrayList<Persona> result = null;
	 * 
	 * while(rs.next()){
	 * 
	 * if(result == null) { result = new ArrayList<Persona>(); } result.add(new
	 * Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
	 * 
	 * }
	 * 
	 * return result;
	 */

	

	/**
	 * Metodo listar que retorna los usuarios con el filtrado de tipo
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

	/*
	 * public String listarJson() throws SQLException {
	 * 
	 * String json = "";
	 * 
	 * Gson gson = new Gson();
	 * 
	 * json = gson.toJson(this.listar());
	 * 
	 * return json;
	 * 
	 * }
	 */
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String listarJson() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(this.listar());
	}
/**
 * 
 * @param tipo
 * @return
 * @throws SQLException
 */
	public String listarJson(int tipo) throws SQLException {

		Gson gson = new Gson();
		ArrayList<Persona> personas = this.listar(tipo);
		return gson.toJson(personas);
	}

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
