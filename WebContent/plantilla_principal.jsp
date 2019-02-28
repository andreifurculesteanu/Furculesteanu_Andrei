<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String usuario = (String) request.getAttribute("usuario");
	Integer visitasSesion = (Integer) request.getAttribute("visitasSesion");
	Integer busquedasTotales = (Integer) request.getAttribute("busquedasTotales");
	
%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
</head>
<body>
Conectado como <b><%=usuario %></b> <br/>
Páginas visitadas en esta conexión: <b><%=visitasSesion %></b> <br/>
Consultas realizadas en la ultima conexion: <b><%=busquedasTotales %></b> <br/>
<a href="ServletLogin?desc">Desconectar</a> <br />

<form method="post" action="ServletPrincipal">
<div>
ES <input type="radio" name="idioma" value="ES" checked="checked"/>
EN <input type="radio" name="idioma" value="EN" />
</div>
<div>
<label for="palabra" id="lbl_palabra">Palabra</label> 
<input type="text" name="palabra"> <br />
<input type="submit" name="boton" id="enviar" value="Buscar" /> <br />
</div>
<br />
</form>
<a href="ServletAlta" >Alta de palabras</a>

</body>
</html>
