package modelo;

import java.sql.SQLException;

public class Persona {

	private int id;
	private String nombre;
	private String email;
	private int permiso;
	
	/**
	 * Constructor para generar un objeto vacio de tipo persona 
	 */
	public Persona () {
		
	}

	/**
	 * Constructor para la creación del objeto desde el formulario.
	 * @param nombre Atributo solo textp
	 * @param email
	 * @param permiso
	 */
	public Persona(String nombre, String email, int permiso) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.permiso = permiso;
	}


	public Persona(int id, String nombre, String email, int permiso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.permiso = permiso;
	}

	/**
	 * Método de inclusión del id en el objeto.
	 * @return retorna el id en tipo entero.
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPermiso() {
		return permiso;
	}


	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	public void insertar() throws SQLException {
		
		DaoPersona.getInstance().insertar(this);	
	}
	
	public void obtenerPorID(int id) throws SQLException {
		
		Persona persona = DaoPersona.getInstance().obtenerPorID(id);
		
		if (persona != null) {
		this.setId(persona.getId());
		this.setNombre(persona.getNombre());
		this.setEmail(persona.getEmail());
		this.setPermiso(persona.getPermiso());
		}
	}
	

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", permiso=" + permiso + "]";
	}

	
	
	
}
