<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<meta charset=UTF-8">
<title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do c�digo</title>
</head>
<body>
<form action="/casadocodigo/produtos" method="post">
<div>
	<label>Titulo</label>
	<input type="text" name="titulo">
</div>
<div>
	<label>Descricao</label>
	<textarea rows="10" cols="20" name ="descricao">
	</textarea>
</div>
<div>
	<label>P�ginas</label>
	<input type="text" name = "paginas">
</div>
<button type="submit">Cadastrar</button>
</form>

</body>
</html>