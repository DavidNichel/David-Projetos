<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de usuarios">
	<section id="index-section" class="container middle"
	
	>
	<!-- BOOTSTRAP -->
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

		<h1>Cadastro de Usuários</h1>
		
		<h2 class="color-red">${message }</h2>
	 <form:form action="${s:mvcUrl('UC#gravar').build() }" method="POST"
			commandName="usuario" enctype="multipart/form-data">
			<div class="form-group">
				<label>Nome:</label>
				<form:input size="25" path="nome" class="form-control" />
				<form:errors path="nome" class="color-red" />
			</div>
			<div class="form-group">
				<label>Email:</label>
				<form:input path="email" class="form-control" />
				<form:errors path="email" class="color-red" />
			</div>
			<div class="form-group">
				<label>Senha:</label>
				<form:password path="senha" class="form-control" />
				<form:errors path="senha" class="color-red" />
			</div>
			<div class="form-group">
				<label>Confirma senha:</label>
				<form:password path="senhaRepetida" class="form-control" />
				<form:errors path="senhaRepetida" class="color-red" />
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
</section>
</tags:pageTemplate>