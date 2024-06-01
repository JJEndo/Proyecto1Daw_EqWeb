package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.DaoVideos;
import modelo.Video;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class ListarVideos
 */
public class ListarVideos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarVideos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Obtener la sesión
	    HttpSession sesion = request.getSession();

	    // Verificar si el atributo "id" existe en la sesión
	    Object idSesionObj = sesion.getAttribute("id");
	    if (idSesionObj != null) {
	        int idSesion = (int) idSesionObj;
	        if (idSesion != 0) {
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");

	            PrintWriter out = response.getWriter();
	            int opcion;
	            try {
	                opcion = Integer.parseInt(request.getParameter("op"));
	            } catch (NumberFormatException e) {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                out.print("{\"error\": \"Opción inválida\"}");
	                out.close();
	                return;
	            }

	            try {
	                switch (opcion) {
	                    case 1: // Listar
	                        String respuestaJSON1 = DaoVideos.getInstance().listarJson();
	                        out.print(respuestaJSON1);
	                        break;

	                    case 2: // Obtener por ID
	                        int id2 = Integer.parseInt(request.getParameter("id"));
	                        Video video = new Video();
	                        video.obtenerPorID(id2);
	                        out.print(video.dameJson());
	                        break;

	                    case 3: // Borrar
	                        int id3 = Integer.parseInt(request.getParameter("id"));
	                        DaoVideos.getInstance().borrar(id3);
	                        out.print("{\"success\": \"Video eliminado\"}");
	                        break;
	                        
	                    case 4: // Editar
	                        int id4 = Integer.parseInt(request.getParameter("id"));
	                        String titulo = request.getParameter("titulo");
	                        String director = request.getParameter("director");
	                        String musica = request.getParameter("musica");
	                        String sinopsis = request.getParameter("sinopsis");
	                        String foto = request.getParameter("foto");

	                        Video videoEditado = new Video(id4, titulo, director, musica, sinopsis, foto);
	                        DaoVideos.getInstance().update(videoEditado);
	                        out.print("{\"success\": \"Video actualizado\"}");
	                        break;    

	                    default:
	                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                        out.print("{\"error\": \"Opción inválida\"}");
	                        break;
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                out.print("{\"error\": \"Error interno del servidor\"}");
	            } finally {
	                out.close();
	            }
	        } else {
	            response.sendRedirect("cuenta.html");
	        }
	    } else {
	        response.sendRedirect("cuenta.html");
	    }
	}
		
		
		
		
		
		
		
       /* String op = request.getParameter("op");
        if (op != null && op.equals("1")) {
            try {
                String respuestaJSON = DaoVideos.getInstance().listarJoson();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print(respuestaJSON);
                out.flush();
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los videos.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación no válida.");
        }
    }		
		
		*/


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
