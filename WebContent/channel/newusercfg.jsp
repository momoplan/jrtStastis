<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>渠道分配</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/JavaScript">
           $(document).ready(function(){ 
              $("#btn_back").click(function(){  
              back();     
              }); 
              $("#btn_ok").click(function(){  
              save();     
              }); 
           }); 

           function back() {
	           location.replace('listusercfg.do');
           }
           
           function save(){
               $('#form1')[0].submit();
           }
       </script>
	</head>

	<body>
		<form id="form1" method="post" action="saveusercfg.do">
			<div id="man_zone">				
				
				<!-- 表格body -->
				<div class="table_db">
					<table width="98%" border="0" align="center" cellpadding="3"
						cellspacing="1" class="table_style">
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									登录名:
								</div>
							</td>
							<td>
								<select id="userid" name="userid">
									<c:forEach var="userlist" items="${userlist}" varStatus="it">
										<option value="${userlist.id}">
											${userlist.name}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									渠道名称:
								</div>
							</td>
							<td>
								<select id="channelid" name="channelid">
									<c:forEach var="channellist" items="${channellist}" varStatus="it">
										<option value="${channellist.id}">
											${channellist.name}
										</option>
									</c:forEach>
								</select>								
							</td>
						</tr>
						
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									&nbsp;
								</div>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="提 交" id="btn_ok" class="btnStyle" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返 回" id="btn_back"	class="btnStyle" />
							</td>
						</tr>
					</table>
				</div>
				
			</div>
		</form>
	</body>
</html>
