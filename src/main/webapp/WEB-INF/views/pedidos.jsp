<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


	<tags:pageTemplate titulo="Lista de Pedidos Casa do Código">
	<section id="index-section" class="container middle">
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data do Pedido</th>
				<th>Titulos</th>
			</tr>
			<c:forEach items="${pedidos}" var="pedidos">
				<tr>
					<td>${pedidos.id}</td>
					<td>${pedidos.valor}</td>
					<td>
					<span><fmt:formatDate value="${pedidos.data.time}"	pattern="dd/MM/yyyy" /></span>
					</td>
						<td>"${pedidos.produtos}"</td>
				</tr>
			</c:forEach>
		</table>
		</section>
	</tags:pageTemplate>
	