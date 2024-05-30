package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoVideos {
	
	private Connection con = null;
	private static DaoVideos instance = null;
	
	public DaoVideos() throws SQLException {
		
		con = DBConection.getConnection();
	}
	
	
	/**
	 * En este metodo se utiliza el patron Singelton
	 * @return
	 * @throws SQLException
	 */
	public static DaoVideos getInstance() throws SQLException {
		
		if(instance==null) {
			instance = new DaoVideos();
		}
		
		return instance;
		
	}
	
	public void insertar (Video a) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement
				("INSERT video (titulo,director,musica,sinopsis,archivo) VALUES (?,?,?,?,?)");
		ps.setString(1, a.getTitulo());
		ps.setString(2, a.getDirector());
		ps.setString(3, a.getMusica());
		ps.setString(4, a.getSinopsis());
		ps.setString(5, a.getFoto());
		
		int filas = ps.executeUpdate();
		
		ps.close();
	
		
	}
	
	public Video obtenerPorId(int id) throws SQLException {
		
		String sql ="SELECT * FROM video WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Video v = new Video(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		
		return v;
		
	}
	
	
	public Video update(Video a) throws SQLException {

		PreparedStatement ps = null;
		try {
		ps = con.prepareStatement("UPDATE video SET titulo=?,director=?,musica=?,sinopsis=?,foto=?, WHERE id=?");
		ps.setString(1, a.getTitulo());
		ps.setString(2, a.getDirector());
		ps.setString(3, a.getMusica());
		ps.setString(4, a.getSinopsis());
		ps.setString(5, a.getFoto());
		
		int filas = ps.executeUpdate();
		}finally {
		if (ps !=null) ps.close();	
		}
		return a;
	}
	
	public void borrar(int id) throws SQLException {
		
		String sql = "DELETE FROM video WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	

	   public ArrayList<Video> listar() throws SQLException {
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM video");
	        ResultSet rs = ps.executeQuery();

	        ArrayList<Video> result = new ArrayList<>();

	        while (rs.next()) {
	            result.add(new Video(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
	        }

	        rs.close();
	        ps.close();

	        return result;
	    }

	    public String listarJson() throws SQLException {
	        Gson gson = new Gson();
	        return gson.toJson(this.listar());
	    }	
	
	
}
	
/*
	/**
	 * Metodo que nos obtiene los elementos como una Array List
	 * @return
	 * @throws SQLException
	 *
	/*
	public ArrayList<Video> listar() throws SQLException{
		
		PreparedStatement ps =con.prepareStatement("SELECT * FROM video");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Video> result = null;
		
		while(rs.next()) {
			
			if(result == null) {
				result = new ArrayList<>();
			}
			
			result.add(new Video(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			
		}
		
		return result;
	}
	
	/**
	 * Ob
	 *
	public String listarJoson() throws SQLException {
		
		String txtJSON = "";
			
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		
		return txtJSON;
		
			
		
	}

}
*/