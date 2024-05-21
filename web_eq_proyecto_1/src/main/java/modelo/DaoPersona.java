package modelo;

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
			
			if(instance==null) {
				instance = new DaoPersona();
			}
			
			return instance;
	}
		
	/** Metodo de insercción en la BD del objeto persona.
	 * 
	 * @param p
	 * @throws SQLException
	 */
	public void insertar (Persona p) throws SQLException {
		
		PreparedStatement ps = null;
		try {
		ps = con.prepareStatement("INSERT INTO persona (nombre,email,permiso) VALUES (?,?,?)");
		ps.setString(1, p.getNombre());
		ps.setString(2, p.getEmail());
		ps.setInt(3, p.getPermiso());
		
		int filas = ps.executeUpdate();
		}finally {		
		if (ps !=null) ps.close();
		}
	}
	
	
	public Persona obtenerPorID(int id) throws SQLException {
		
		String sql = "SELECT * FROM persona WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Persona p =new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		
		return p;
	}
	
	
	
	public ArrayList<Persona> listar() throws SQLException{
		
		String sql = "SELECT * FROM persona";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Persona> result = null;
		
		while(rs.next()){
			
			if(result == null) {
				result = new ArrayList<Persona>();				
			}
		result.add(new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			
		}
		
		return result;
		
	}
	
	public String listarJson() throws SQLException {
		
		String json = "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar());
		
		return json;
		
	}
	
	
	
	
	

}
