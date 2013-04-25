<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<frameset rows="*" cols="215,*" framespacing="6" frameborder="yes" border="5" bordercolor="#FF8000" TOPMARGIN="0"  LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">
  <frame src="menu.html" name=left frameborder="no" scrolling="auto" bordercolor="#FF8000">
  <frame src="listmenu.do" name=main frameborder="no" scrolling="auto" bordercolor="#FF8000">
  </frameset>
<noframes>

</noframes>
</html>
