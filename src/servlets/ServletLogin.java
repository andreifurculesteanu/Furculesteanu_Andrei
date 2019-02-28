package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilidades.MetodosCookie;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletLogin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("desc") != null) {
			// Recuperamos la sesión
			HttpSession laSesion = request.getSession(false);
			String usuario = (String) laSesion.getAttribute("usuario");
			String mensaje = "Adios, " + usuario;
			
			int busquedasTotalesAhora = (int) laSesion.getAttribute("busquedasTotalesAhora");
			
			// Creamos cookie
			Cookie galleta = new Cookie(usuario, Integer.toString(busquedasTotalesAhora));
			galleta.setMaxAge(60*60*24*365*10); // dura 10 años
			response.addCookie(galleta);
			
			// Invalidamos sesión
			request.getSession().invalidate();
			
			request.setAttribute("mensaje", mensaje);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/plantilla_login.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario, clave, mensaje = "", valorCookie;
		int cont = 0, busquedasTotales = 0, visitasSesion = 0, busquedasTotalesAhora = 0;
		
		Map<String, String> mapaUsuarios = new HashMap<String, String>();
		mapaUsuarios.put("alumno", "alumno");
		mapaUsuarios.put("alfa", "beta");
		mapaUsuarios.put("gamma", "delta");
		mapaUsuarios.put("usuario", "claveusuario");
		mapaUsuarios.put("andrei", "andrei");
		
		usuario = request.getParameter("usuario");
		clave = request.getParameter("clave");
		
		if(usuario != null) { //usario tiene cosas integradas
			if(clave != null) { //la clave tambien tiene cosas dentro
				// vemos si el usuario existe
				for (Entry<String, String> key : mapaUsuarios.entrySet()) {
					if (key.getKey().equals(usuario) && key.getValue().equals(clave)) {
						cont++;
					}
				}
				
				if(cont != 0) {// el usuario existe en el mapa
					Cookie arrayCookies[] = request.getCookies(); // Recojo las cookies que hay en el navegador
					if (arrayCookies != null) { // Si hay cookies en el navegador
						if (MetodosCookie.verificaCookie(arrayCookies, usuario)) { 
							/* 
							 * Existe la cookie con el nombre del usuario sacamos el valor de la cookie y los añadimos a la sesion
							 * Cookie value:busquedasTotales
							 */
							valorCookie = MetodosCookie.getCookieValue(arrayCookies, usuario);
							busquedasTotales = Integer.parseInt(valorCookie);
						} 
					} 
						
					// Creamos sesion:
					HttpSession laSesion = request.getSession(true);
					laSesion.setAttribute("usuario", usuario);
					laSesion.setAttribute("visitasSesion", visitasSesion);
					laSesion.setAttribute("busquedasTotales", busquedasTotales);
					laSesion.setAttribute("busquedasTotalesAhora", busquedasTotalesAhora);
						
					//redirijo al ServletPrincipal
					//alli es donde recojo otra vez la sesion y se la paso al jsp
					response.sendRedirect("ServletPrincipal"); 
						
				} else { //el usuario no existe en el mapa
					mensaje = "Usuario y/o contrasenia no validos";
					request.setAttribute("mensaje", mensaje);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/plantilla_login.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				mensaje = "La contrasenia no puede estar vacia"; 
				request.setAttribute("mensaje", mensaje);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/plantilla_login.jsp");
				dispatcher.forward(request, response);
			} //fin else clave != null
		} else {
			mensaje = "El usuario no puede estar vacio";
			request.setAttribute("mensaje", mensaje);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/plantilla_login.jsp");
			dispatcher.forward(request, response);
		} //fin else 	usuario != null	
	} // fin doPost

}//fin clase
