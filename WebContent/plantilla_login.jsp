<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%	
	String mensaje = (String) request.getAttribute("mensaje"); 
%>
    
<!doctype html>
<html>
	<meta charset="utf-8">
	<title> Login </title>
	<style type="text/css">
		.mensaje{
			color: red;
		}
	</style>
<body>
<% if (mensaje != null) { %>
		<div class="mensaje">
			<%= mensaje %>
		</div>
	<% } %>
<form method="post" action="ServletLogin">
<label for="usuario">Usuario</label><input type="text" name="usuario" />
<label for="clave">Clave</label><input type="text" name="clave" />
<br />
<input type="submit" value="Autenticar" />
</form>
</body> 
</html>
