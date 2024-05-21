package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {
	
	public static Connection con = null;
	private static DaoUsuario instance = null;
	
	public DaoUsuario() throws SQLException {
		
		con = DBConection.getConnection();
		
	}
		
		
	public static DaoUsuario getInstance() throws SQLException {
			
			if(instance==null) {
				instance = new DaoUsuario();
			}
			
			return instance;
	}
		
	
	public void insertar (Persona p) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("Insert persona (nombre,email,permiso) VALUES (?,?,?)");
		ps.setString(1, p.getNombre());
		ps.setString(2, p.getEmail());
		ps.setInt(3, p.getPermiso());
		
		int filas = ps.executeUpdate();
		
		ps.close();
		
	}
	
	public Persona update(int id) throws SQLException {
		
		String sql = "SELECT * FROM persona WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Persona u = new Persona(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));
		
		return u;
	}
	
	

}
