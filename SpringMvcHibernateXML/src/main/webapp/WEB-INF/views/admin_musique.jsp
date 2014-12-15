<%@ include file="is_logged.jsp"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Musiques</title>
</head>
<body>

	<div class="container">
		<%@ include file="admin_menu.jsp"%>
		<div class="well">
			<h1>Gestion des Musiques</h1>
			<table class="table table-hover">
				<tr>
					<th>Id</th>
					<th>Titre</th>
					<th>Album</th>
					<th>Artiste</th>
					<th>Genre</th>
					<th>Fichier</th>
					<th>Action</th>
					<th>Supprimer</th>
					<th>Lire</th>
				</tr>


				<c:forEach var="musique" items="${musiqueList}" varStatus="status">
					<tr>
						<td>${musique.id}</td>
						<form:form method="post" action="/spring/admin/musique/modif"
							enctype="multipart/form-data" modelAttribute="musique">
							<td><form:input type="hidden" path="id"
									value="${musique.id}" /> <form:input path="titre" class="mon_form_control"
									value="${musique.titre}" /></td>
							<td><form:select path="album.id" class="mon_form_control">
									<c:forEach var="theAlbum" items="${albumList}">
										<c:choose>
											<c:when test="${theAlbum.id == musique.album.id}">
												<form:option value="${theAlbum.id}" selected="selected">
													<c:out value="${theAlbum.titre}" />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${theAlbum.id}">
													<c:out value="${theAlbum.titre}" />
												</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select></td>
							<td><form:select path="artiste.id" class="mon_form_control">
									<c:forEach var="theArtist" items="${artisteList}">
										<c:choose>
											<c:when test="${theArtist.id == musique.artiste.id}">
												<form:option value="${theArtist.id}" selected="selected">
													<c:out value="${theArtist.prenom} ${theArtist.nom}" />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${theArtist.id}">
													<c:out value="${theArtist.prenom} ${theArtist.nom}" />
												</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select></td>
							<td><form:select path="genre.id" class="mon_form_control">
									<c:forEach var="theGenre" items="${genreList}">
										<c:choose>
											<c:when test="${theGenre.id == musique.genre.id}">
												<form:option value="${theGenre.id}" selected="selected">
													<c:out value="${theGenre.nom}" />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${theGenre.id}">
													<c:out value="${theGenre.nom}" />
												</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select></td>
							<c:set var="fileSplits" value="${fn:split(musique.fichier,':')}"/>
							<c:choose>
								<c:when test="${not empty fileSplits[1]}">
									<td><input type="file" name="file" />Actuel : ${fileSplits[1]}</td>
								</c:when>
								<c:otherwise>
									<td><input type="file" name="file" />Actuel : ${fileSplits[0]}</td>
								</c:otherwise>
							</c:choose>
							<td><input type="submit" value="Modifier" class="btn btn-xs btn-warning btn-nomarge"></td>
						</form:form>
						<td><a href="/spring/admin/musique/delete/${musique.id}" class="btn btn-flat btn-danger btn-nomarge">X</a></td>
						<c:set var="splitFile" value="${fn:split(musique.fichier,'.')}"/>
						
						<c:choose>
							<c:when test="${splitFile[fn:length(splitFile)-1] == 'mp3' }">
								<td><audio controls> <source
										src="/spring/musiques/${musique.fichier}" type="audio/mpeg"></audio>
								</td>
							</c:when>
							<c:when test="${splitFile[fn:length(splitFile)-1] == 'ogg' }">
								<td><audio controls> <source
										src="/spring/musiques/${musique.fichier}" type="audio/ogg"></audio>
								</td>
							</c:when>
							<c:otherwise>
								<td>Fichier incorrect</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				<tr>
					<td>#</td>
					<form:form id="addMusique" method="post"
						enctype="multipart/form-data" action="/spring/admin/musique/add"
						modelAttribute="musique">
						<td><form:input path="titre" class="mon_form_control"/></td>
						<td><form:select path="album.id" class="mon_form_control">
								<c:forEach var="theAlbum" items="${albumList}">
									<form:option value="${theAlbum.id}">
										<c:out value="${theAlbum.titre}" />
									</form:option>
								</c:forEach>
							</form:select></td>
						<td><form:select path="artiste.id" class="mon_form_control">
								<c:forEach var="theArtist" items="${artisteList}">
									<form:option value="${theArtist.id}">
										<c:out value="${theArtist.prenom} ${theArtist.nom}" />
									</form:option>
								</c:forEach>
							</form:select></td>
						<td><form:select path="genre.id" class="mon_form_control">
								<c:forEach var="theGenre" items="${genreList}">
									<form:option value="${theGenre.id}">
										<c:out value="${theGenre.nom}" />
									</form:option>
								</c:forEach>
							</form:select></td>
						<td><input type="file" name="file" /></td>
						<td><input type="submit" value="Ajouter" class="btn btn-xs btn-success btn-nomarge"></td>
					</form:form>
					<td></td>
					<td></td>
				</tr>
			</table>
			<c:if test="${not empty param.error}">
				<div class="error">
					<p>Impossible de supprimer cet album car il contient des
						musiques</p>
				</div>
			</c:if>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>