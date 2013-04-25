<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	if(username == null || password == null || !"3gmenhu".equals(username) || !"3gmenhu".equals(password)) {
		
		request.setAttribute("msg", "用户名或密码错误");
		request.getRequestDispatcher("/3g/index.jsp").forward(request,response);
		return;
	} 
	
	request.getSession().setAttribute("info", username);
	
	response.sendRedirect("webmain.jsp");
%>
</body>
</html>