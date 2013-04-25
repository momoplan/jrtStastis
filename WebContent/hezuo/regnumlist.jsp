<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>业务推广PV</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<script type="text/javascript" src="../js/comm.js"></script>
		<script language="javascript">
          function add() {
	         //location.replace("yewuadd.html"); onfocus="calendar();"
	         location.href("yewuadd.html");
          }                 
        </script>
	</head>

	<body>
		<form id="form1" method="post" action="pvlist.do">
			<div id="container" class="container">	
				<!-- 补白 -->
				<div class="blank"></div>

				<!-- 表格body -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;">

						<tr bgColor="#dadfd0" height="23px">
							<td width="50">
								<div align="center">
									编号
								</div>
							</td>
							<td width="47%">
								<div align="center">
									手机号
								</div>
							</td>
							<td width="47%">
								<div align="center">
									注册时间
								</div>
							</td>							

						</tr>
						<%
							Integer i = 0;
						%>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">								
								<td align="center" class="txtStyle">
									<script language="javascript">
									  document.write(<%=(++i)%>);														
								    </script>
								</td>
								<td align="center" class="txtStyle">
									${list.mobileid}
								</td>
								<td align="center" class="txtStyle">
									${list.regtime}
								</td>								
							</tr>
						</c:forEach>						

					</table>
				</div>
				<!-- 表格foot -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" align="left">
						<tbody>
							<tr bgcolor="#dadfd0">								
								<td align="right">									
									<input id="back" class="btnStyle" type="button" value="返回"
										onclick="history.go(-1);" />
								</td>								
							</tr>
						</tbody>
					</table>
				</div>
		
				<div class="blank"></div>				
			</div>
		</form>
	</body>
</html>

