package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.DaoVideos;
import modelo.Video;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class GestionVideos
 */
@MultipartConfig
public class GestionVideos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Este atributo de la clase es para indicar la ruta dónde están ubicados los archivos
	 */
	private String pathFiles ="C:\\Users\\jjend\\eclipse-workspace\\web_eq_proyecto1\\src\\main\\webapp\\videosSubidos";
    /**
     * Este objeto es para que Java entienda que es una ruta que vamos a trabajar con archivos
     */
	private File uploads = new File(pathFiles);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionVideos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        try {
            String respuestaJSON = DaoVideos.getInstance().listarJson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(respuestaJSON);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los videos.");
        }
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * Recuperaamos los parámetros de la solicitud HTTP que ha sido enviada por el cliente al servidor.
		 */
		String titulo = request.getParameter("titulo");
		String director = request.getParameter("director");
		String musica = request.getParameter("musica");
		String sinopsis = request.getParameter("sinopsis");
		
		/**
		 * Recuperamos y leemos los datos binarios desde el cliente.
		 */
		Part part =request.getPart("video");
		   if (part == null) {
			   	//Indicamos que algo está mal en la solicitud del cliente y que debe ser corregido.
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        return;
		    }
		
		/**
		 * Gestiono la ruta y obtengo el nombre del archivo original.
		 */
		Path path = Paths.get(part.getSubmittedFileName());
		
		/**
		 * Guardamos en modo texto el nombre del archivo.
		 */
		String fileName = path.getFileName().toString();
		
		/**
		 * Preparamos el camino, el buffer, por donde vamos a hacer la transmición de datos obtenidos con el destino
		 */	
		InputStream input = part.getInputStream();	
		
		/**
		 * Creamos el contenedor donde vamos a guardar los datos
		 */
		File file = new File(uploads, fileName);
		
		/**
		 * Copiamos lo que contenga el proceso de lectura input dentro del contenedor file
		 */
		try {
		Files.copy(input, file.toPath());
		}catch (IOException e) {
			//Indicamos que el servidor ha encontrado una condición inesperada que le impide completar la solicitud del cliente.
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			PrintWriter error = response.getWriter();
			error.print("Se ha producido un error al subir el archivo. Contacte con el administrador.");
			return;
		}
		
		
		Video a = new Video(titulo, director, musica, sinopsis,fileName);
		
		try {
			a.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de conexión");
		}
		
		System.out.println(a.toString());
		
	}

}
