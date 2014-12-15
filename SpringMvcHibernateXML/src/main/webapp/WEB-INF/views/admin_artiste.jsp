<%@ include file="is_logged.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
    <%@ include file="head.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion Artistes</title>
    </head>
    <body>
    	
        <div class="container">
        	<%@ include file="admin_menu.jsp" %>
	        <div class="well">
	        	<h1 class="header">Gestion des Artistes</h1>
	        	<table class="table table-hover">
	        	<tr>
		        	<th>Id</th>
		        	<th>Nom</th>
		        	<th>Prenom</th>
		        	<th>Action</th>
		        	<th>Delete</th>
		        </tr>
		        	
					<c:forEach var="artiste" items="${artisteList}" varStatus="status">
			        	<tr>
			        		<td>${artiste.id}</td>
			        		<form:form method="post" action="/spring/admin/artiste/modif" modelAttribute="artiste">
								<td>
									<form:input type="hidden" path="id" value="${artiste.id}"/>
									<form:input path="prenom" value="${artiste.prenom}" class="mon_form_control"/>
								</td>
								<td>
									<form:input path="nom" value="${artiste.nom}" class="mon_form_control"/>
								</td>
					     		<td><input type="submit" value="Modifier" class="btn btn-xs btn-warning btn-nomarge"></td>
							</form:form>
							<td><a href="/spring/admin/artiste/delete/${artiste.id}" class="btn btn-flat btn-danger btn-nomarge">X</a></td>
			        	</tr>
					</c:forEach>
					<tr>
						<td>#</td>
						<form:form id="addArtiste" method="post" action="/spring/admin/artiste/add" modelAttribute="artiste">            
				   			<td><form:input path="nom" class="mon_form_control"/></td>
				   			<td><form:input path="prenom" class="mon_form_control"/></td>
				    		<td><input type="submit" value="Ajouter" class="btn btn-xs btn-success btn-nomarge"></td>
						</form:form>
						<td></td>
					</tr>			        	
	        	</table>
	        </div>
        	<c:if test="${not empty param.error}">
				<div class="alert alert-dismissable alert-danger">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<p>Impossible de supprimer cet artiste car il est associé à un album ou une musique</p>
				</div>
			</c:if>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>