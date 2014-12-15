<% 
	if(request.getSession().getAttribute("logged")==null){
		response.sendRedirect("/spring/login");
	}
%>