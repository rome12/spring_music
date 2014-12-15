<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${artiste}</title>
</head>
<body>
	<div class="container">
	<%@ include file="public_menu.jsp" %>
	<div class="artiste_presentation well">
		<h2>${artiste}</h2>
		<h3>Toutes les musiques</h3>
		<div class="musique_liste">
			<ul>
			<c:forEach var="theMusique" items="${listMusique}">
				<li><a href="/spring/musique/${theMusique.id}">${theMusique.titre}</a> dans <a href="/spring/album/${theMusique.album.id}">${theMusique.album}</a></li>
			</c:forEach>
			</ul>
		</div>
		</div>
	<%@ include file="footer.jsp" %>
	</div>
</body>
</html>