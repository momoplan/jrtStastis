<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration"%>
<%
String path = request.getContextPath();
	try {
		Enumeration sessionNames = request.getSession().getAttributeNames();
		while (sessionNames.hasMoreElements()) {
			String sessionName = (String) sessionNames.nextElement();
			request.getSession().removeAttribute(sessionName);
		}
		//session.removeAttribute("operator");
	} catch (Exception e) {
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>如意彩渠道推广及用户数据分析系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="<%=path%>/css/default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<link rel="stylesheet" href="<%=path%>/css/login.css" type="text/css"></link>
		<script type="text/JavaScript"> 
           $(document).ready(function(){ 
               var msg = "${msg}";
               if("" != msg) {
				alert(msg);
                   }
              $("#btn_ok").click(function(){  
              login();     
              }); 
           }); 
           
           function login(){
              var username = $("#username")[0].value;
              var password = $("#password")[0].value;
              $("#error_tx").text("");
              if(username ==''){
                 $("#error_tx").text("请输入用户名!");
                 return false;
              }
              if(password ==''){
                 $("#error_tx").text("请输入密码!");
                 return false;
              }
           document.getElementById("form1").submit();
           }
           function changepic(){
              var num = new Date().getTime();
              document.getElementById('chkimg').src='<%=path%>/getrandnum.do?num=' + num;   
           }
       </script>
	</head>

	<body style="background: #9CDCF9; text-align:center;">
		<form id="form1" method="post" action="denglu.jsp">
			<table width="681" border="0" align="center" cellpadding="0"
				cellspacing="0" style="margin-top: 120px">
				<tr>
					<td width="353" height="259" align="center" valign="bottom"
						background="<%=path%>/images/login_1_1.GIF">
						<table width="90%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td align="right" valign="bottom" style="color: #05B8E4">
									Power by Copyright 2010
								</td>
							</tr>
						</table>
					</td>
					<td width="212" background="<%=path%>/images/login_2.jpg">
						<table width="215" height="106" border="0" align="center"
							cellpadding="2" cellspacing="0">
							<tr>
								<td height="50" colspan="3" align="left">
									&nbsp;

								</td>
							</tr>
							<tr>
								<td width="60" height="30" align="left">
									登录名称：
								</td>
								<td width="147" colspan="2">
									<input name="username" id="username" type="text"
										style="background: url(<%=path%>/images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; width: 130px; background-color: #FFFFFF;"
										size="16" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td width="60" height="30" align="left">
									登录密码：
								</td>
								<td width="147" colspan="2">
									<input name="password" id="password" type="password"
										style="background: url(<%=path%>/images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; width: 130px; background-color: #FFFFFF;"
										size="16" maxlength="20" />
								</td>
							</tr><!--
							<tr>
								<td height="30">
									验 证 码：
								</td>
								<td>
									<input name="code" type="text" id="code" size="5"
										style="background: url(<%=path%>/images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
										maxlength="4" />
								</td>
								<td>
									<img id="chkimg" name="chkimg" src="getrandnum.do"
										style="width: 60px; height: 20px; border: 0px; text-align: center;"
										alt="load..." />
								</td>
							</tr>
							<tr>
								<td colspan="3" align="right">
									<a href="javascript:changepic();">看不清,换一张</a>
								</td>
							</tr>
							--><tr>

								<td height="40" colspan="3" align="center">
									<div id="error_tx" style="color: #C04000;">${errormsg}</div>
								</td>

							</tr>
							<tr>
								<td colspan="3" align="center">
									<input type="button" id="btn_ok"
										style="background: url(<%=path%>/images/login_5.gif) no-repeat; width: 69px;"
										value=" 登录 " />
									<input type="reset" name="Submit"
										style="background: url(<%=path%>/images/login_5.gif) no-repeat; width: 69px;"
										value=" 取消 " />
								</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
					</td>
					<td width="116" background="<%=path%>/images/login_3.gif">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="161" colspan="3" background="<%=path%>/images/login_4.gif"></td>
				</tr>
			</table>

		</form>
	</body>
</html>
