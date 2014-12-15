<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<%@ include file="head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Musique Cloud</title>
</head>
<body>
	<div class="container">
		<%@ include file="public_menu.jsp" %>
		<div class="row">
			<div class="listes col-md-7 well">
				<h3>Musiques les mieux notées</h3>
				<c:forEach var="musique" items="${populaireList}" varStatus="status">
						<div class="list-line">
							<img src="/spring/images/${musique.album.fichier}" alt="img" style="width:50px;height:50px">
							<a href="/spring/musique/${musique.id}">${musique.titre}</a>
							<span>de <a href="/spring/artiste/${musique.artiste.id}">${musique.artiste}</a> dans <a href="/spring/album/${musique.album.id}">${musique.album}</a></span>
						</div>
				</c:forEach>
				<h3>Nouvelles musiques</h3>
				<c:forEach var="musique" items="${lastMusiques}" varStatus="status">
						<div class="list-line">
							<img src="/spring/images/${musique.album.fichier}" alt="img" style="width:50px;height:50px">
							<a href="/spring/musique/${musique.id}">${musique.titre}</a>
							<span>de <a href="/spring/artiste/${musique.artiste.id}">${musique.artiste}</a> dans <a href="/spring/album/${musique.album.id}">${musique.album}</a></span>
						</div>
				</c:forEach>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-4 well">
				<h3>Publicités</h3>
				<div class="publicite banner">
					<ul>
						<c:forEach var="pub" items="${listPubs}">
							<c:set var="splitLine" value="${fn:split(pub,' ')}"/>
								<li><a href="${splitLine[0]}"><img src="${splitLine[1]}" onError="this.src = 'images/404.png'"></a></li>
						</c:forEach>
					</ul>
				</div>
				<h3>Derniers commentaires</h3>
				<c:forEach var="theComment" items="${listLatestComment}">
					<blockquote>
					    <p>${theComment.commentaire}</p>
					    <input class="mon_rating" type="number" value="${theComment.note}" min=0 max=5 data-size="xs" data-show-clear="false" data-readonly="true">
				    	<c:forEach var="theMusique" items="${listMusiques}">
				    		<c:if test="${theMusique.id eq theComment.musique.id}">
				    			<a href="/spring/musique/${theMusique.id}">${theMusique.titre}</a> de <a href="/spring/artiste/${theMusique.artiste.id}">${theMusique.artiste}</a> 
				    		</c:if>
				    	</c:forEach>
				   </blockquote>
				</c:forEach>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>