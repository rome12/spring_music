<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${musique.titre}</title>
</head>
<body>
	<div class="container">
		<%@ include file="public_menu.jsp" %>
		<div class="musique_presentation row well">
			<div class="col-md-2">
				<img src="/spring/images/${musique.album.fichier}" alt="img" style="width:200px;height:200px">
			</div>
			<div class="col-md-10">
				<div><h2>${musique.titre}</h2>
				<span>Musique de <a href="/spring/artiste/${musique.artiste.id}">${musique.artiste}</a> dans <a href="/spring/album/${musique.album.id}">${musique.album}</a></span>
				<input class="mon_rating" type="number" value="${avg}" min=0 max=5 data-size="xs" data-show-clear="false" data-readonly="true">
				</div>
				<br/>
				<div class="musique_player">
					<audio controls>
						<source src="/spring/musiques/${musique.fichier}" type="audio/ogg">
					</audio>
				</div>
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
			<form id="comform" method="post" action="/spring/musique/comment/add/${musique.id}">
				<textarea  maxlength="120" placeholder="Saisissez votre commentaire..." rows="3" cols="50" name="commentaireSaisi"></textarea>
				<br/>
				<input name="noteSaisi" class="mon_rating" type="number" min=0 max=5 data-size="sm" data-show-clear="false" data-step="1">
				<%--<select name="noteSaisi" class="mon_form_control">
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
					<button type="button" class="close" data-dismiss="alert">×</button>
					<c:choose>
						<c:when test="${param.error == 1}">
							<p>Veuillez entrer un commentaire correct</p>
						</c:when>
						<c:when test="${param.error == 2}">
							<p>Impossible d'ajouter plus de 3 commentaires par jour</p>
						</c:when>
						<c:otherwise>
							<p>Erreur</p>
						</c:otherwise>
					</c:choose>
				</div>
		</c:if>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>