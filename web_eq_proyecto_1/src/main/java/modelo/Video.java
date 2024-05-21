package modelo;

import java.sql.SQLException;

public class Video {
	
	private int id;
	private String titulo;
	private String director;
	private String musica;
	private String sinopsis;
	private String foto;
	
	public Video() {
		
	}
	
	
	public Video(int id, String titulo, String director, String musica, String sinopsis, String foto) {
	
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.musica = musica;
		this.sinopsis = sinopsis;
		this.foto = foto;
	}


	public Video(String titulo, String director, String musica, String sinopsis, String foto) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.musica = musica;
		this.sinopsis = sinopsis;
		this.foto = foto;
	}


	public Video(String titulo, String director, String musica, String sinopsis) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.musica = musica;
		this.sinopsis = sinopsis;
	}


	public Video(int id, String titulo, String director, String musica, String sinopsis) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.musica = musica;
		this.sinopsis = sinopsis;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getMusica() {
		return musica;
	}


	public void setMusica(String musica) {
		this.musica = musica;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public void insertar() throws SQLException {		
		/*
		DaoVideos dao = new DaoVideos();
		dao.insertar(this);
		*/	
		DaoVideos.getInstance().insertar(this);		
	}
	
	public void update() throws SQLException {
		DaoVideos.getInstance().update(this);
	}
	

	@Override
	public String toString() {
		return "Video [id=" + id + ", titulo=" + titulo + ", director=" + director + ", musica=" + musica
				+ ", sinopsis=" + sinopsis + ", foto=" + foto + "]";
	}
	
	
	

}
