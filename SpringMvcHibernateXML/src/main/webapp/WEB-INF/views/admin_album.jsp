<%@ include file="is_logged.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Albums</title>
</head>
<body>

	<div class="container">
		<%@ include file="admin_menu.jsp"%>
		<div class="well">
			<h1>Gestion des Albums</h1>
			<table class="table table-hover">
				<tr>
					<th>Id</th>
					<th>Titre</th>
					<th>Artiste</th>
					<th>Genre</th>
					<th>Fichier</th>
					<th>Action</th>
					<th>Delete</th>
					<th>Pochette</th>
				</tr>


				<c:forEach var="album" items="${albumList}" varStatus="status">
					<tr>
						<td>${album.id}</td>
						<form:form method="post" enctype="multipart/form-data"
							action="/spring/admin/album/modif" modelAttribute="album">
							<td><form:input type="hidden" path="id" value="${album.id}" />
								<form:input path="titre" value="${album.titre}" class="mon_form_control"/></td>
							<td><form:select path="artiste.id" class="mon_form_control">
									<c:forEach var="theArtist" items="${artisteList}">
										<c:choose>
											<c:when test="${theArtist.id == album.artiste.id}">
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
											<c:when test="${theGenre.id == album.genre.id}">
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
							<td><input type="file" name="file" title="${album.fichier}" />Actuel : ${album.fichier}</td>
							<td><input type="submit" value="Modifier" class="btn btn-xs btn-warning btn-nomarge"></td>
						</form:form>
						<td><a href="/spring/admin/album/delete/${album.id}" class="btn btn-flat btn-danger btn-nomarge">X</a></td>

						<td><img src="/spring/images/${album.fichier}" alt="img"
							style="width: 40px; height: 40px"></td>

					</tr>
				</c:forEach>
				<tr>
					<td>#</td>
					<form:form id="addAlbum" method="post"
						enctype="multipart/form-data" action="/spring/admin/album/add"
						modelAttribute="album">
						<td><form:input path="titre" class="mon_form_control"/></td>
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
						<td><input type="file" name="file"/></td>
						<td><input type="submit" value="Ajouter" class="btn btn-xs btn-success btn-nomarge"></td>
					</form:form>
					<td></td>
					<td></td>
				</tr>

			</table>
			<c:if test="${not empty param.error}">
				<div class="alert alert-dismissable alert-danger">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<p>Impossible de supprimer cet album car il contient des
						musiques</p>
				</div>
			</c:if>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>