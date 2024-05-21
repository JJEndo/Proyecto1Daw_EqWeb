package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.DaoPersona;
import modelo.Persona;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class FormUsuarios
 */
public class FormUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int opcion = Integer.parseInt(request.getParameter("op"));

            if (opcion == 2) {
                int id = Integer.parseInt(request.getParameter("id"));
                Persona p = new Persona();
                try {
                    p.obtenerPorID(id);
                    out.print(p.dameJson());
                    System.out.println(p.dameJson());
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print("{\"error\": \"Error al obtener la persona\"}");
                }
            } else {
                try {
                    String respuestaJSON = DaoPersona.getInstance().listarJson();
                    out.print(respuestaJSON);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print("{\"error\": \"Error al listar personas\"}");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\": \"Parámetro inválido\"}");
        }
    }

		
		/*
	
		PrintWriter out = response.getWriter();
		
		
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		if(opcion == 2) {
			int id = Integer.parseInt(request.getParameter("id"));		
			Persona p = new Persona();
			try {
				p.obtenerPorID(id);
				System.out.println(p.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
		}else {
			String respuestaJSON;
			try {
				respuestaJSON = DaoPersona.getInstance().listarJson();
				out.print(respuestaJSON);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		

		
	}
		
		/*PrintWriter out = response.getWriter();
		
		String opcionParam = request.getParameter("op");
        if (opcionParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\":\"El parámetro 'op' es obligatorio\"}");
            return;
        }

        int opcion;
        try {
            opcion = Integer.parseInt(opcionParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\":\"El parámetro 'op' debe ser un número\"}");
            return;
        }

        if (opcion == 2) {
            String idParam = request.getParameter("id");
            if (idParam == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print("{\"error\":\"El parámetro 'id' es obligatorio\"}");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print("{\"error\":\"El parámetro 'id' debe ser un número\"}");
                return;
            }

            Persona p = new Persona();
            try {
                p.obtenerPorID(id);
                System.out.println(p.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().print("{\"error\":\"Error al obtener la persona por ID\"}");
            }

        } else if (opcion == 1) {
            try {
                String respuestaJSON = DaoPersona.getInstance().listarJson();
                DaoPersona usuarios = new DaoPersona();
                System.out.print(respuestaJSON);
                out.print(usuarios.listarJson());
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().print("{\"error\":\"Error al listar usuarios\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\":\"El parámetro 'op' no es válido\"}");
        }
    }

		
		PrintWriter out = response.getWriter();
		
		
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		
		if(opcion==2) {
			int id = Integer.parseInt(request.getParameter("id"));
			Persona p = new Persona();
			try {
				p.obtenerPorID(id);
				System.out.println(p.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(opcion==1){
			
			try {
				String respuestaJSON = DaoPersona.getInstance().listarJson();
				DaoPersona usuarios = new DaoPersona();
				System.out.print(respuestaJSON);
				out.print(usuarios.listarJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Establece el código de estado en caso de error.
		        response.getWriter().print("{\"error\":\"Error al procesar la solicitud\"}"); // Informa del error en formato JSON.
			}
					
		}
		
	}
		*/	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		int permiso = Integer.parseInt(request.getParameter("permiso"));
		
		Persona p = new Persona(nombre, email, permiso);
		
		try {
			p.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de conexión");
		}
		
		System.out.println(p.toString());
		
		response.sendRedirect("listarUsuarios.html");
	}

}
