package utilidades;

import javax.servlet.http.Cookie;

public class MetodosCookie {
	
	public static boolean verificaCookie(Cookie[] arrayCookie, String usuario) {
		boolean encontrado = false;
		int cont = 0;
		while (!encontrado && cont < arrayCookie.length) {
			if (arrayCookie[cont].getName().equals(usuario)) { //si la encuentra 
				encontrado = true; // encontrado a true y dejamos de iterar arrayCookie
			}
			cont++;
		}
		return encontrado;
	} // fin verificaCookie()
	
	
	public static String getCookieValue(Cookie[] arrayCookie, String usuario) {
		boolean encontrado = false;
		String valorCookie = "";
		int cont = 0;
		while (!encontrado && cont < arrayCookie.length) {
			if (arrayCookie[cont].getName().equals(usuario)) { //si la encuentra 
				encontrado = true; // encontrado a true y dejamos de iterar arrayCookie
				valorCookie = arrayCookie[cont].getValue();
			}
			cont++;
		}
		return valorCookie;
	} // fin getCookieValue()
	
	
}
