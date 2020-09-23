<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Usuarios">

	<section style="padding: 1em" id="index-section"
		class="container middle">
		<h2>${message }</h2>
		<a href="${s:mvcUrl('UC#form').build() }" rel="nofollow">Novo
			usuário</a>

		

			<table class="table table-bordered table-striped table-hover">
				<thead>
				<tr>
					<th>Nome</th>
					<th>E-mail</th>
					<th>Roles</th>
				</tr>
					</thead>
				<c:forEach items="${user}" var="user">
					<tr>
						<td>${user.nome }</td>
						<td>${user.email }</td>
						<td>${user.roles.toString() }</td>
						<td style="text-align: center">
						<!--   ${contador.count} -->
						<form action="${s:mvcUrl('UC#alterarRoles').build() }"
							method="POST">
							<input type="hidden" name="email" value="${user.email }">
							<%-- <form:hidden path="email"/> --%>
							<button type="submit">
								<img
									src="http://www.108talkradio.com/wp-content/uploads/2018/04/1232/lets-take-more-than-seven-seconds-with-corey-champagne.png">
							</button>
						</form>
					</tr>
				</c:forEach>
			</table>
		
	</section>

</tags:pageTemplate>