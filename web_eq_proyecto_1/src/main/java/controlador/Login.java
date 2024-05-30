package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Persona;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    String mail = request.getParameter("email");
	    String pass = getMD5(request.getParameter("pass"));

	    if (mail == null || pass == null || mail.isEmpty() || pass.isEmpty()) {
	        response.sendRedirect("error.html"); // Redirige a una página de error si los parámetros son inválidos
	        return;
	    }

	    Persona p = new Persona();
	    p.setEmail(mail);

	    try {
	        if (p.logueo(pass)) {
	            HttpSession sesion = request.getSession();
	            sesion.setAttribute("id", p.getId());
	            sesion.setAttribute("permiso", p.getPermiso());
	            response.sendRedirect("listarUsuarios.html");
	        } else {
	            response.sendRedirect("crearCuenta.html");
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        response.sendRedirect("error.html"); // Redirige a una página de error en caso de excepción
	    }
	}

	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
/*		
		
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		Persona p = new Persona();
		p.setEmail(mail);
		
		//protección
		
		try {
			if(p.logueo(pass)){
				
				sesion = request.getSession();
				
				sesion.setAttribute("id", p.getClass());
				sesion.setAttribute("permiso", p.getPermiso());
				
				response.sendRedirect("playlist.html");
				
			}else {
				response.sendRedirect("crearCuenta.html");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
*/
}
