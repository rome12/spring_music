<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${listMusique.get(0).album}</title>
</head>
<body>
<div class="container">
	<%@ include file="public_menu.jsp" %>
	<div class="album_presentation well">
		<div class="row">
			<div class="col-md-2">
				<img src="/spring/images/${listMusique.get(0).album.fichier}" alt="img" style="width:200px;height:200px">
			</div>
			<div class="col-md-10">
				<h2>${listMusique.get(0).album}</h2>
				de <a href="/spring/artiste/${listMusique.get(0).album.artiste.id}">${listMusique.get(0).album.artiste}</a>
			</div>
		</div>
		
		<h3>Toutes les musiques</h3>
		<div class="musique_liste">
			<ul>
			<c:forEach var="theMusique" items="${listMusique}">
				<li><a href="/spring/musique/${theMusique.id}">${theMusique.titre}</a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="row"></div>
	<div class="comments row well">
		<h3>Commentaires</h3>
		<c:forEach var="theComment" items="${listComment}">
			<blockquote>
			    <p>${theComment.commentaire}</p>
			    <input class="mon_rating" type="number" value="${theComment.note}" min=0 max=5 data-size="xs" data-show-clear="false" data-readonly="true">
		    </blockquote>
		</c:forEach>
		
		<form id="comform" method="post" action="/spring/album/comment/add/${listMusique.get(0).album.id}">
			<textarea maxlength="120" placeholder="Saisissez votre commentaire..." rows="3" cols="50" name="commentaireSaisi"></textarea>
			<br/>
			<input name="noteSaisi" class="mon_rating" type="number" min=0 max=5 data-size="sm" data-show-clear="false" data-step="1">
			<%--<select name="noteSaisi">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>--%> 
			<input type="submit" value="Valider" class="btn btn-xs btn-success btn-nomarge"/>
		</form>
	</div>
	
	<c:if test="${not empty param.error}">
		<div class="alert alert-dismissable alert-danger">
					<button type="button" class="close" data-dismiss="alert">×</button><p>Impossible d'ajouter plus de 3 commentaires par jour</p></div>
	</c:if>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>