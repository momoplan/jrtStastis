<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看装机量</title>
<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/calendar.js"></script>
<script type="text/javascript" src="../js/comm.js"></script>
<script type="text/javascript" src="../js/datautil.js"></script>
<script type="text/javascript" src="../js/sortshow.js"></script>

<script language="javascript">
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
            				document.getElementById("form1").submit();
                			}
          }
          String.prototype.UrlEncode = function() 
          { 
          var str = this; 
          str = str.replace(/./g,function(sHex) 
          { 
          window.EnCodeStr = ""; 
          window.sHex = sHex; 
          window.execScript('window.EnCodeStr=Hex(Asc(window.sHex))',"vbscript"); 
          return window.EnCodeStr.replace(/../g,"%$&"); 
          }); 
          return str; 
          } 

        </script>
</head>

<body>
	<div id="container" class="container">
		<form id="form1" method="post"
			action="<%=request.getContextPath()%>/tj/zjltj.do">
			<div class="table_db">
			
				<table cellSpacing="0" cellPadding="0" width="798" border="0">
					<tr>
						<td width="90" height="24px" align="right">起始日期：</td>
						<td width="140" align="left"><input id="beginTime"
							name="beginTime" type="text" value="${beginTime}"
							class="input_text_long" onfocus="calendar();" /></td>
						<td width="90" align="right">截止日期：</td>
						<td width="140" align="left"><input id="endTime"
							name="endTime" type="text" value="${endTime}"
							class="input_text_long" onfocus="calendar();" /></td>
						<td><input type="submit" class="btnStyle" id="delBtn"
							onclick="find();" value="查 询" /></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 补白 -->
		<div class="blank"></div>

		<!-- 表格body -->
		<div class="table_db">
		    <c:set var="zongU" value="0"></c:set>
			总合计: ${tjZjl}
		  <c:forEach var="channelsListEntry" items="${channelsListMap}">
			<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88"
				border="1" style="border-collapse: collapse;" id="idTable">
				<thead>
					<tr bgColor="#dadfd0" height="23px">
						<td width="150px">
							<div align="center">${channelsListEntry.key}</div>
						</td>
						<td width="300px">
							<div align="center"></div>
						</td>
						<td>
							<div align="center"></div>
						</td>
					</tr>
					<tr bgColor="#dadfd0" height="23px">
						<td width="150px">
							<div align="center">日期</div>
						</td>
						<td width="300px">
							<div align="center">渠道名称</div>
						</td>
						<td>
							<div align="center">
								装机量<a href="javascript:sortAble('idTable', 2,'int')">(排序)</a>
							</div>
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${channelsListEntry.value}" varStatus="it">
						<tr bgcolor="#FFFFFF" height="23px">
							<td align="center" class="txtStyle">
						       ${list.statdate}													
						    </td>
							<td align="center" class="txtStyle">${list.coopid}</td>
							<td align="center" class="txtStyle">${list.zjl}</td>
						</tr>
						<c:set var="zongU" value="${zongU+list.zjl}"></c:set>
						<c:set var="totalU" value="${totalU+list.zjl}"></c:set>
					</c:forEach>
				</tbody>
				<tr bgcolor="#dadfd0" height="23px">
					<td align="center" class="txtStyle">合计</td>
					<td align="center" class="txtStyle">--</td>
					<td align="center" class="txtStyle">${totalU}</td>
				</tr>
			</table>
				<c:set var="totalU" value="0"></c:set>
		  </c:forEach>
		  
		</div>

		<div class="blank"></div>
		<div id="tip" class="tip">
			<div id="msg" class="msg">
				<span class="left_bt">数据项说明</span> <br /> <span class="left_txt">
					&nbsp; 安装客户端成功的用户数：当日成功安装客户端的用户数</span> <br />
			</div>
		</div>

	</div>
</body>
</html>
