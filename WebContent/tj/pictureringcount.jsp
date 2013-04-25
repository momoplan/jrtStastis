<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户行为</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<script type="text/javascript" src="../js/comm.js"></script>
		<script type="text/javascript" src="../js/datautil.js"></script>

		<script language="javascript">
          function add() {
	         //location.replace("yewuadd.html"); onfocus="calendar();"
	         location.href("yewuadd.html");
          }
          function find() {
        	  var date=new Date();
        		var d1 = new Date((date.getYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate()).replace(/\-/g, "\/"));
        		var beginTime=document.getElementById("beginTime").value;
        		var endTime=document.getElementById("endTime").value;
        		var d2 = new Date(beginTime.replace(/\-/g, "\/")); 
        		var d3 = new Date(endTime.replace(/\-/g, "\/")); 
        				if(d3<d2){
        				alert('起始日期必须小于截止日期');
            			}else{
            				form1.submit();
                			}
          }
          function find1(diag) {
        	  var date=new Date();
        		var d1 = new Date((date.getYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate()).replace(/\-/g, "\/"));
        		var beginTime=document.getElementById("beginTime").value;
        		var endTime=document.getElementById("endTime").value;
        		var d2 = new Date(beginTime.replace(/\-/g, "\/")); 
        		var d3 = new Date(endTime.replace(/\-/g, "\/")); 
        				if(d3<d2){
        				alert('起始日期必须小于截止日期');
            			}else{

                			document.getElementById('diag').value=diag;
            				form1.submit();
                			}
          }
        </script>
	</head>

	<body>
		<form id="form1" method="post" action="pictureringcount.do">
			<div id="container" class="container">				
				<div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="700px" border="0">
						<tr>
							<td width="100" height="22px" align="right">
								起始日期：
							</td>
							<td width="170" align="left">
								<input id="beginTime" name="beginTime" type="text" value="${beginTime}"
									class="input_text_long" onfocus="calendar();" />
							</td>
							<td width="100" align="right">
								截止日期：
							</td>
							<td width="170" align="left">
								<input id="endTime" name="endTime" type="text" value="${endTime}"
									class="input_text_long" onfocus="calendar();" />
							</td>
							<td>
								<%if(request.getAttribute("diag").toString().equals("1")) {%>
					<input type="submit" class="btnStyle" 
									onclick="find1('2');" value="图铃页面访问数" />
					<%}else{ %>
					<input type="submit" class="btnStyle" 
									onclick="find1('1');" value="图铃下载数" />
					
					<%} %>
							</td>
						</tr>
						<tr>
						<%if(request.getAttribute("diag").toString().equals("1")){ %>
							<td width="100" height="22px" align="right">
								下载类型：
							</td>
							<td width="170" align="left">
							<select id="type" name="type">
							<option value=""  <%=request.getAttribute("type").equals("")?"selected":""%>>全部</option>
							<option value="1" <%=request.getAttribute("type").equals("1")?"selected":""%>>图片</option>
							<option value="2" <%=request.getAttribute("type").equals("2")?"selected":""%>>铃声</option>
							</select>
							</td><%} else{%>
							<td></td>
							<td></td>
							<%} %>
							<td width="70" align="right">
								<input type="submit" class="btnStyle" id="delBtn" 
									onclick="find();" value="查 询" />
							</td>
							<td width="170" align="left" >
							
							</td>
							<td>
							</td>
						</tr>
					</table>
				
				</div>
				<!-- 补白 -->
				<div class="blank"></div>
				
				<!-- 表格body -->
				<div class="table_db">
				<%if(request.getAttribute("diag").toString().equals("1")){ %>
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;">

						<tr bgColor="#dadfd0" height="23px" >
							<td width="100px">
								<div align="center">
									日期
								</div>
							</td>
							<td>
								<div align="center">
									名称
								</div>
							</td>
							<td>
								<div align="center">
									类型
								</div>
							</td>
							<td>
								<div align="center">
									下载次数
								</div>
							</td>
						</tr>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
								   ${list.DATES}
								</td>
								<td align="center" class="txtStyle">
									${list.CONTENT}
								</td>
								<td align="center" class="txtStyle">
									${list.TYPE}
								</td>
								<td align="center" class="txtStyle">
									${list.DOWNCOUNT}
								</td>
							
				
								
							</tr>
						</c:forEach>
							<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								合计
							</td>
					
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
								${count}
							</td>
							</tr>
					</table>
					<%}else{ %>
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;">

						<tr bgColor="#dadfd0" height="23px" >
							<td width="100px">
								<div align="center">
									日期
								</div>
							</td>
							<td>
								<div align="center">
									图铃首页访问数
								</div>
							</td>
							<td>
								<div align="center">
									图铃下载页访问数
								</div>
							</td>
						</tr>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
								   ${list.DATES}
								</td>
								<td align="center" class="txtStyle">
									${list.INDEXCOUNT}
								</td>
								<td align="center" class="txtStyle">
									${list.DOWNCOUNT}
								</td>
							
				
								
							</tr>
						</c:forEach>
							<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								合计
							</td>
					
							
							<td align="center" class="txtStyle">
								${indexcount}
							</td>
							<td align="center" class="txtStyle">
								${downcount}
							</td>
							</tr>
					</table>
					<%} %>
				</div>
				
				<div class="blank"></div>
				<div id="tip" class="tip">
					<div id="msg" class="msg">
					</div>
				</div>
				
			</div>
			<input type="hidden" id="diag" name="diag" value="<%=request.getAttribute("diag") %>"/>
		</form>
	</body>
</html>