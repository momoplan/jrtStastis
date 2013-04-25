<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'cataloglist.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<script language="JavaScript">
            function add() {
	           location.replace("ConfigAdd.aspx?tid=44");
            }
            
            function selectall (obj) 
            {
               var objFrm=obj;
               
               elements=objFrm.form.elements;
               for (var i = 0; i < elements.length; i++) 
               {
                    var ele = objFrm.form.elements[i];
                    if (ele.type == "checkbox" ) 
                    {
	                    ele.click();
	                }
	           }
            }
		</script>
	    <link rel="stylesheet" href="../css/default.css" type="text/css"></link>
	</head>

	<body text="#000000" bgColor="#f9fbf2">
			<table class="p9" cellSpacing="0" cellPadding="0" width="230"
				border="0">
				<tr height="21px">
					<td width="12px" background="../images/nav1.gif">						
					</td>
					<td class="boardStyle" vAlign="middle" align="center" width="140px" bgColor="#fcca65">
						资源适配管理
					</td>
					<td width="100px" bgColor="#666666">
						<table class="p9" height="19" cellSpacing="0" cellPadding="0"
							width="110" bgColor="#eeeeee" border="0">
							<TR>
								<td align="center" width="100">
									<div align="center">
										<img height="11" src="../images/nav2.gif" width="10" align="absMiddle" />
										&nbsp;适配列表
									</div>
								</td>
							</TR>
						</table>
					</td>
					<td width="17px" background="../images/nav3.gif">
					</td>
				</tr>
			</table>
			<br/>			
			<table cellSpacing="0" cellPadding="0" width="600" border="0">
				<tr>
					<td width="12">
						<img height="33" src="../images/bg1.gif" width="12" border="0">
					</td>
					<td class="p10" noWrap width="576px" background="../images/bg2.gif" height="19px">
						类型选择:						
					</td>
					<td align="right" width="12">
						<img height="33" src="../images/bg3.gif" width="12" border="0">
					</td>
				</tr>
			</table>
			<table cellSpacing="1" cellPadding="2" width="600" bgColor="#a0ae88" border="0">
				<tbody>
					<tr bgColor="#dadfd0">
						<td width="100">
							<div align="center">
								资源类型
							</div>
						</td>
						<td width="100">
							<div align="center">
								资源规格
							</div>
						</td>
						<td width="100">
							<div align="center">
								资源格式
							</div>
						</td>
						<td width="100">
							<div align="center">
								资源长度
							</div>
						</td>
						<td width="100">
							<div align="center">
								缩放比例
							</div>
						</td>
						<td width="50">
							<div align="center">
								编辑
							</div>
						</td>
						<td width="50">
							<div align="center">
								选项
							</div>
						</td>
					</tr>

						<tr bgcolor="#FFFFFF">
							<td align="center" class="txtStyle">
								测试
							</td>
							<td align="center" class="txtStyle">
								测试
							</td>
							<td align="center" class="txtStyle">
								测试
							</td>
							<td align="center" class="txtStyle">
								测试
							</td>
							<td align="center" class="txtStyle">
								测试
							</td>
							<td align="center" class="txtStyle">
								<a
									href="ConfigEdit.aspx?cid=1"
									class="txtStyle">修改</a>
							</td>
							<td align="center" class="txtStyle">
								<input type="checkbox" id="record" name="record"
									value='测试' />
							</td>
						</tr>

					<TR bgColor="#dadfd0">
						<td align="right" colSpan="7">
							
						</td>
					</TR>
				</tbody>
			</table>
			<table class="p9" cellSpacing="0" cellPadding="0" width="600"
				align="left" border="0">
				<tbody>
					<TR>
						<td width="12">
							<img height="33" src="../images/bg4.gif" width="12" border="0">
						</td>
						<td align="right" width="576" background="../images/bg5.gif">
							<input class="btnStyle" onclick="javascript:add();" type="button"
								value="添 加">
							<input class="btnStyle" type="button" value="全 选"
								onClick="selectall(this);">
							<button ID="delBtn" class="btnStyle">删 除</button>
						</td>
						<td align="right" width="12">
							<img height="33" src="../images/bg6.gif" width="12" border="0" />
						</td>
					</tr>
				</tbody>
			</table>
	</body>
</html>
