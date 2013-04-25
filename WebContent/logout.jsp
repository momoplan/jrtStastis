<%@ page contentType="text/html; charset=gb2312" language="java" errorPage=""%>
<%@ page import="java.util.Enumeration"%>
<%
	try {
		Enumeration sessionNames = request.getSession().getAttributeNames();
		while (sessionNames.hasMoreElements()) {
			String sessionName = (String) sessionNames.nextElement();
			request.getSession().removeAttribute(sessionName);
		}
		//session.removeAttribute("operator");
	} catch (Exception e) {
	}
	response.sendRedirect("index.jsp");
%>

