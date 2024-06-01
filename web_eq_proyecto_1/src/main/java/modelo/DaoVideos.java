package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Clase DaoVideo
 * 
 * Esta clase implementa el patrón Singleton para gestionar el acceso a la base de datos
 * y proporciona métodos para realizar operaciones CRUD en la tabla `video`.
 * 
 */
public class DaoVideos {

	/**
	 * Conexión a la base de datos.
	 * 
	 * Esta es una conexión compartida utilizada por todos los métodos de la clase
	 * para interactuar con la base de datos.
	 * 
	 */
	private Connection con = null;

	/**
	 * Instancia única de DaoVideos.
	 * 
	 * Esta es la instancia única que se utiliza para implementar el patrón
	 * Singleton.
	 */
	private static DaoVideos instance = null;

	/**
	 * Constructor de DaoVideos.
	 * 
	 * Este constructor inicializa la conexión a la base de datos utilizando la
	 * clase DBConnection.
	 * 
	 * @throws SQLException Si ocurre un error al obtener la conexión a la base de
	 *                      datos.
	 */
	public DaoVideos() throws SQLException {

		con = DBConection.getConnection();
	}

	/**
	 * Obtiene la instancia única de DaoVideos.
	 * 
	 * Este método sigue el patrón Singleton para garantizar que solo exista una
	 * instancia de DaoVideos. Está sincronizado para ser seguro en un entorno
	 * multi-hilo.
	 * 
	 * @return La instancia única de DaoVideos.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos durante
	 *                      la creación de la instancia
	 */
	public static DaoVideos getInstance() throws SQLException {

		if (instance == null) {
			instance = new DaoVideos();
		}

		return instance;

	}

	/**
	 * Inserta un nuevo video en la base de datos.
	 * 
	 * @param a El objeto Video que contiene los datos a insertar.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public void insertar(Video a) throws SQLException {

		PreparedStatement ps = con
				.prepareStatement("INSERT video (titulo,director,musica,sinopsis,foto) VALUES (?,?,?,?,?)");
		ps.setString(1, a.getTitulo());
		ps.setString(2, a.getDirector());
		ps.setString(3, a.getMusica());
		ps.setString(4, a.getSinopsis());
		ps.setString(5, a.getFoto());

		int filas = ps.executeUpdate();

		ps.close();

	}

	/**
	 * Obtiene un video por su ID.
	 * 
	 * @param id El identificador del video.
	 * @return El objeto Video correspondiente al ID proporcionado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Video obtenerPorId(int id) throws SQLException {

		String sql = "SELECT * FROM video WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Video v = new Video(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

		return v;

	}

	/**
	 * Actualiza los datos de un video existente en la base de datos. 
	 * 
	 * @param a El objeto Video que contiene los datos actualizados.
	 * @return  El objeto Video con los datos actualizados.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Video update(Video a) throws SQLException {

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE video SET titulo=?,director=?,musica=?,sinopsis=?,foto=?, WHERE id=?");
			ps.setString(1, a.getTitulo());
			ps.setString(2, a.getDirector());
			ps.setString(3, a.getMusica());
			ps.setString(4, a.getSinopsis());
			ps.setString(5, a.getFoto());
			ps.setInt(6, a.getId());
			int filas = ps.executeUpdate();
		} finally {
			if (ps != null)
				ps.close();
		}
		return a;
	}

	/**
	 * Borra un video de la base de datos por su ID.
	 * 
	 * @param id El identificador del video a borrar.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public void borrar(int id) throws SQLException {

		String sql = "DELETE FROM video WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();

	}

	/**
	 * Lista todos los videos de la base de datos.
	 * 
	 * @return Una lista de objetos Video.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public ArrayList<Video> listar() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM video");
		ResultSet rs = ps.executeQuery();

		ArrayList<Video> result = new ArrayList<>();

		while (rs.next()) {
			result.add(new Video(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6)));
		}

		rs.close();
		ps.close();

		return result;
	}

	/**
	 * Retorna una lista de videos en formato JSON.
	 * 
	 * te método obtiene una lista de todos los videos en la base de datos y la convierte en una cadena JSON.
	 * 
	 * @return Una cadena JSON que representa la lista de videos.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public String listarJson() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(this.listar());
	}

}