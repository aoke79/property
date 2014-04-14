<%
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("jbpm4-evnet-report!toLoginPage.do");
	}
%>