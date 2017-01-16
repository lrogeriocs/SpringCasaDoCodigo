<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE >
<html>
<head>

<title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
</head>
<body>
<form:form action="${s:mvcUrl('PC#gravar').build() }" method="post" commandName="produto" enctype="multipart/form-data">
<div>
	<label>Titulo</label>
	<form:input path="titulo"/>
	<form:errors path="titulo"/>
</div>
<div>
	<label>Descricao</label>
	<form:textarea rows="10" cols="20" path="descricao"/>
	
	<form:errors path="descricao"/>
</div>
<div>
	<label>Páginas</label>
	<form:input path = "paginas"/>
	<form:errors path="paginas"/>
</div>
<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label>
                <form:input path="precos[${status.index}].valor"/>
                <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
            </div>
</c:forEach>
<div> 
<label>Date de lançamento</label>
<form:input path = "dataLancamento" />
<form:errors path="dataLancamento"></form:errors>
</div>

<div> 
<label>Sumário</label>
<input name="sumario" type="file"/>
</div>
<button type="submit">Cadastrar</button>
</form:form>

</body>
</html>