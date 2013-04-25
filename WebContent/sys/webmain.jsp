<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<base href="<%=basePath%>">

		<title>如意彩渠道推广及用户数据分析系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/default.css">
		<script language="JScript.Encode" src="js/coolbuttons.js"> </script>
		<script LANGUAGE="JavaScript">
<!--
/**
 * 添加连接
 */
function addLink(url) {
	parent.middle.location = url;
}

function logout() {
	parent.location = "#";
}
//-->
			</SCRIPT>
	</HEAD>
	<body leftmargin="0" topmargin="0">
		<table bgcolor="#ff8000" width="100%" height="100%" border="0"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="27" valign="top">
					<table class="coolBar" width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<TD width="1">
								<SPAN class="handle"></SPAN>
							</TD>
							<td width="20%" class="coolButton"
								onclick="javascript:addLink('sys/main.jsp')">
								<div align="center">
									Blog综合管理
								</div>
							</td>
							<td width="20%" class="coolButton">
								<div align="center">
									Blog统计分析
								</div>
							</td>
							<td width="20%" class="coolButton"
								onclick="javascript:addLink('#')">
								<div align="center">
									用户修改密码
								</div>
							</td>
							<td width="20%" class="coolButton"
								onclick="javascript:addLink('#')">
								<div align="center">
									后台配置管理
								</div>
							</td>
							<td width="20%" class="coolButton" onclick="javascript:logout()">
								<div align="center">
									退出系统
								</div>
							</td>
							<TD width="32%"></TD>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<iframe id="mynews" name="middle"
						style="Z-INDEX: 2; VISIBILITY: inherit; WIDTH: 100%; HEIGHT: 100%"
						scrolling="no" frameborder="0" src="sys/main.jsp"></iframe>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</HTML>
