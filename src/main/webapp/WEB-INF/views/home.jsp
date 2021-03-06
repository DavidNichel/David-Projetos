<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Casa do Codigo Livros">

	<section id="index-section" class="container middle">
		<!-- teste if -->
		<c:if test="${not empty message }">
			<h1 class="cdc-call">${message }</h1>
		</c:if>
		<c:if test="${empty message }">
			<h1 class="cdc-call">�ltimos dias com os pre�os promocionais. Aproveite!</h1>
		</c:if>
		
		<ul class="clearfix book-collection">

			<!-- AQUI USE c:forEach -->
			<c:forEach items="${produtos }" var="produto">
				<li>
					<a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}" class="block clearfix">
					<h2 class="product-title">${produto.titulo }</h2> 
					<img width="143" height="202" src="https://cdn.shopify.com/s/files/1/0155/7645/products/java8-featured_large.png?v=1411490181" alt="Java 8 Pr�tico" title="Java 8 Pr�tico" /> 
					<small class="buy-button">Compre</small>
					</a>
				</li>
			</c:forEach>
			<!-- FIM DO c:forEach -->

		</ul>

		<h2 class="cdc-call">Diferenciais da Casa do C�digo</h2>

		<ul id="cdc-diferenciais" class="clearfix">
			<li class="col-left">
				<h3>E-books sem DRM. Leia onde quiser</h3>
				<p>
					<span class="sprite" id="sprite-drm"></span> Nossos e-books n�o possuem DRM, ou seja, voc� pode ler em qualquer computador, tablet e smartphone.
				</p>
			</li>
			<li class="col-right">
				<h3>Autores de renome na comunidade</h3>
				<p>
					<span class="sprite" id="sprite-renome"></span> Autores que participam ativamente na comunidade com Open Source, listas de discuss�o, grupos e
					mais.
				</p>
			</li>
			<li class="col-left">
				<h3>Receba atualiza��es dos e-books</h3>
				<p>
					<span class="sprite" id="sprite-atualizacoes"></span> Quando voc� compra um e-book, automaticamente tem direito �s atualiza��es e corre��es dele.
				</p>
			</li>
			<li class="col-right">
				<h3>Livros com curadoria da Caelum</h3>
				<p>
					<span class="sprite" id="sprite-caelum"></span> Desenvolvedores experientes que avaliam e revisam os livros constantemente.
				</p>
			</li>
		</ul>
	</section>
</tags:pageTemplate>