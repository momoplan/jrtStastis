<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<title>管理区域</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/JavaScript"> 
           $(document).ready(function(){ 
              $("#btn_ok").click(function(){  
              login();     
              }
              ); 
           }); 
           
           function login(){
              location.href = "findpass.do";
           }
       </script>
	</head>

	<body>	    
	    <form id="form1" method="post" action="updatepass.do">
		<div id="man_zone">
			<table width="98%" border="0" align="center" cellpadding="3"
				cellspacing="1" class="table_style">
				<tr>
					<td width="120px" class="left_title_1">
						用户登录名:
					</td>
					<td>
						${user.name}
					</td>
				</tr>
				<tr>
					<td class="left_title_2">
						真实姓名:
					</td>
					<td>
						${user.realname}
					</td>
				</tr>
				<tr>
					<td width="120px" class="left_title_1">
						联系方式:
					</td>
					<td>
						${user.tel}
					</td>
				</tr>			
				<tr>
					<td class="left_title_2">
						&nbsp;
					</td>
					<td>					    
						<input type="button" class="btnStyle" id="btn_ok" value="更改密码" />&nbsp;${msg}
					</td>
				</tr>
			</table>
		</div>
		</form>
	</body>
</html>

