<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Musique Cloud</title>
</head>
<body>
	<div class="container">
		<%@ include file="public_menu.jsp"%>
		<div class="well">
			<h1>Authentification</h1>
				<form method="post" action="/spring/login-check">
						<input class="mon_form_control" type="text" name="loginSaisi" placeholder="Identifiant"/>
						<input class="mon_form_control" type="password" name="passSaisi"  placeholder="Mot de passe"/>
						<input value="Valider" type="submit" class="btn btn-xs btn-success btn-nomarge"/>
					</tr>
				</form>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>