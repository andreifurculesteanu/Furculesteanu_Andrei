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
<form method="post" action="ServletAlta">
<div>
<label for="palabra_es" id="lbl_palabra_es">Palabra</label> 
<input type="text" name="palabra_es"> 
<label for="word_en" id="lbl_word_en">Word</label> 
<input type="text" name="word_en"> <br />
<input type="submit" name="insertar" id="enviar" value="Insertar" /> <br />
</div>
<br />
</form>
<a href="ServletPrincipal" >Volver a principal</a>


</body>
</html>
