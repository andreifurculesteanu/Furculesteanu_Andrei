package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletPrincipal
 */
@WebServlet("/ServletPrincipal")
public class ServletPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletPrincipal() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario;
		int visitasSesion, busquedasTotales;
		
		//recupero sesion:
		HttpSession laSesion = request.getSession(false);
		
		//recojo parametros de la sesion
		usuario = (String) laSesion.getAttribute("usuario");
		visitasSesion = (int) laSesion.getAttribute("visitasSesion");
		busquedasTotales = (int) laSesion.getAttribute("busquedasTotales");
		visitasSesion++;
		laSesion.setAttribute("visitasSesion", visitasSesion);
		//paso parametros al jsp
		request.setAttribute("usuario", usuario);
		request.setAttribute("visitasSesion", visitasSesion);
		request.setAttribute("busquedasTotales", busquedasTotales);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/plantilla_principal.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("boton") != null) { //vengo de buscar del jsp: plantilla_principal
			int busquedasTotales;
			
			//recupero sesion:
			HttpSession laSesion = request.getSession(false);
			busquedasTotales = (int) laSesion.getAttribute("busquedasTotales");
			busquedasTotales++; //aumento las veces que el usuario haga click en el boton de buscar
			
			//vuelvo a meter en la sesion 
			laSesion.setAttribute("busquedasTotales", busquedasTotales);
		}
		
		String usuario;
		int visitasSesion, busquedasTotales;
		
		//recupero sesion:
		HttpSession laSesion = request.getSession(false);
		
		//recojo parametros de la sesion
		usuario = (String) laSesion.getAttribute("usuario");
		visitasSesion = (int) laSesion.getAttribute("visitasSesion");
		busquedasTotales = (int) laSesion.getAttribute("busquedasTotales");
		visitasSesion++;
		laSesion.setAttribute("visitasSesion", visitasSesion);
		//paso parametros al jsp
		request.setAttribute("usuario", usuario);
		request.setAttribute("visitasSesion", visitasSesion);
		request.setAttribute("busquedasTotales", busquedasTotales);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/plantilla_principal.jsp");
		dispatcher.forward(request, response);
	}

}
