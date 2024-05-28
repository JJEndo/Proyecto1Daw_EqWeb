package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	HttpSession sesion;
       
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
                int opcion = Integer.parseInt(request.getParameter("op"));

                try {
                    switch (opcion) {
                        case 1:
                            String respuestaJSON1 = DaoPersona.getInstance().listarJson();
                            out.print(respuestaJSON1);
                            break;

                        case 2:
                            int id2 = Integer.parseInt(request.getParameter("id"));
                            Persona p2 = new Persona();
                            p2.obtenerPorID(id2);
                            out.print(p2.dameJson());
                            break;

                        case 3:
                            int id3 = Integer.parseInt(request.getParameter("id"));
                            DaoPersona.getInstance().borrar(id3);
                            out.print("{\"success\": \"Persona eliminada\"}");
                            break;

                        case 4:
                            int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
                            String respuestaJSON4 = DaoPersona.getInstance().listarJson(tipo);
                            out.print(respuestaJSON4);
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

/*            ------

            if (idSesion != 0) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                PrintWriter out = response.getWriter();
                int opcion = Integer.parseInt(request.getParameter("op"));

                switch (opcion) {
                    case 1:
                        String respuestaJSON1 = null;
                        try {
                            respuestaJSON1 = DaoPersona.getInstance().listarJson();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        out.print(respuestaJSON1);
                        //response.sendRedirect("listarUsuarios.html");
                        break;

                    case 2:
                        int id2 = Integer.parseInt(request.getParameter("id"));
                        Persona p2 = new Persona();
                        try {
                            p2.obtenerPorID(id2);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        out.print(p2.dameJson());
                        System.out.println(p2.dameJson());
                        break;

                    case 3:
                        int id3 = Integer.parseInt(request.getParameter("id"));
                        try {
                            DaoPersona.getInstance().borrar(id3);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        out.print("{\"success\": \"Persona eliminada\"}");
                        break;

                    case 4:
                        int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
                        String respuestaJSON4 = null;
                        try {
                            respuestaJSON4 = DaoPersona.getInstance().listarJson(tipo);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        out.print(respuestaJSON4);
                        break;

                    default:
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        out.print("{\"error\": \"Opción inválida\"}");
                        break;
                }
            } else {
                response.sendRedirect("cuenta.html");
            }
        } else {
            response.sendRedirect("cuenta.html");
        }
    }		
		
		
		/*
		    // Obtener la sesión
		    HttpSession sesion = request.getSession();

		    // Obtener el atributo "id" de la sesión
		    int idSesion = (int) sesion.getAttribute("id");

		    if (idSesion != 0) {
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");

		        PrintWriter out = response.getWriter();
		        int opcion = Integer.parseInt(request.getParameter("op"));

		        switch (opcion) {
		            case 1:
					String respuestaJSON1 = null;
					try {
						respuestaJSON1 = DaoPersona.getInstance().listarJson();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                out.print(respuestaJSON1);
		                response.sendRedirect("listarUsuarios.html");
		                break;

		            case 2:
		                int id2 = Integer.parseInt(request.getParameter("id"));
		                Persona p2 = new Persona();
					try {
						p2.obtenerPorID(id2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                out.print(p2.dameJson());
		                System.out.println(p2.dameJson());
		                break;

		            case 3:
		                int id3 = Integer.parseInt(request.getParameter("id"));
					try {
						DaoPersona.getInstance().borrar(id3);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                out.print("{\"success\": \"Persona eliminada\"}");
		                break;

		            case 4:
		                int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
					String respuestaJSON4 = null;
					try {
						respuestaJSON4 = DaoPersona.getInstance().listarJson(tipo);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                out.print(respuestaJSON4);
		                break;

		            default:
		                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                out.print("{\"error\": \"Opción inválida\"}");
		                break;
		        }
		    } else {
		        response.sendRedirect("cuenta.html");
		    }
		}		
		*/
		/*		
		sesion = request.getSession();
		
		//int idSesion = Integer.parseInt((String) sesion.getAttribute("id"));
		int idSesion = (int) sesion.getAttribute("id");
		
		if(idSesion !=0) {
		
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");

		    try (PrintWriter out = response.getWriter()) {
		        int opcion = Integer.parseInt(request.getParameter("op"));

		        switch (opcion) {
		            case 1:
		                try {
		                    String respuestaJSON = DaoPersona.getInstance().listarJson();
		                    out.print(respuestaJSON);
		                    response.sendRedirect("listarUsuarios.html");
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		                    out.print("{\"error\": \"Error al listar personas\"}");
		                }
		                break;

		            case 2:
		                try {
		                    int id = Integer.parseInt(request.getParameter("id"));
		                    Persona p = new Persona();
		                    p.obtenerPorID(id);
		                    out.print(p.dameJson());
		                    System.out.println(p.dameJson());
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		                    out.print("{\"error\": \"Error al obtener la persona\"}");
		                } catch (NumberFormatException e) {
		                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                    out.print("{\"error\": \"Parámetro 'id' inválido\"}");
		                }
		                break;

		            case 3:
		                try {
		                    int id = Integer.parseInt(request.getParameter("id"));
		                    DaoPersona.getInstance().borrar(id);
		                    out.print("{\"success\": \"Persona eliminada\"}");
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		                    out.print("{\"error\": \"Error al borrar la persona\"}");
		                } catch (NumberFormatException e) {
		                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                    out.print("{\"error\": \"Parámetro 'id' inválido\"}");
		                }
		                break;
		            case 4:
		            	try {
		                   int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
		                    String respuestaJSON = DaoPersona.getInstance().listarJson(tipo);
		                    out.print(respuestaJSON);
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		                    out.print("{\"error\": \"Error al listar personas por tipo de usuario\"}");
		                } catch (NumberFormatException e) {
		                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                    out.print("{\"error\": \"Parámetro 'tipoUsuario' inválido\"}");
		                }
		            	
		            	break;

		            default:
		                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                out.print("{\"error\": \"Opción inválida\"}");
		                break;
		                
		        }
		    } catch (NumberFormatException e) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        response.getWriter().print("{\"error\": \"Parámetro 'op' inválido\"}");
		    }
		
		}
	}else {
		
		HttpServletResponse response;
		response.sendRedirect("cuenta.html");
	}
		
		
		
		
		/*
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
            } else if (opcion == 1){
                try {
                    String respuestaJSON = DaoPersona.getInstance().listarJson();
                    out.print(respuestaJSON);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print("{\"error\": \"Error al listar personas\"}");
                }
            }else if(opcion==3) {
            	int id = Integer.parseInt(request.getParameter("id"));
            	 DaoPersona.getInstance().borrar(id);
            	
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\": \"Parámetro inválido\"}");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		    int permiso = 0;
		    int id = 0;

		    try {
		        permiso = Integer.parseInt(request.getParameter("permiso"));
		    } catch (NumberFormatException e) {
		        // Manejo de error en caso de que el permiso no sea un entero válido
		        e.printStackTrace();
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Permiso inválido");
		        return;
		    }

		    try {
		        id = Integer.parseInt(request.getParameter("id"));
		    } catch (NumberFormatException e) {
		        // Manejo de error en caso de que el id no sea un entero válido
		        id = 0; // Asignar 0 si el id no es válido para insertar como nuevo registro
		    }

		    Persona p = new Persona(nombre, email, permiso);

		    try {
		        if (id == 0) {
		            p.insertar();
		        } else {
		            p.setId(id);
		            p.actualizar(); // Suponiendo que tienes un método actualizar en la clase Persona
		        }
		    } catch (SQLException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        System.out.println("Error de conexión");
		        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de conexión a la base de datos");
		        return;
		    }

		    System.out.println(p.toString());

		    response.sendRedirect("listarUsuarios.html");
		}
}

		
		
		
		/*
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		int permiso = Integer.parseInt(request.getParameter("permiso"));
		int id Integer.parseInt(request.getParameter("id"));
	
		
		Persona p = new Persona(nombre, email, permiso);
		
		try {
			
			if(id == "") {
			p.insertar();
			}else {
				p.setId(id);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de conexión");
		}
		
		System.out.println(p.toString());
		
		response.sendRedirect("listarUsuarios.html");
	}

}
*/