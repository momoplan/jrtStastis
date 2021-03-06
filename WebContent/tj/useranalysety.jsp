<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户分析</title>
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
          
        </script>
	</head>

	<body>
		<form id="form1" method="post" action="useranalysety.do">
			<div id="container" class="container">				
				<div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="700px" border="0">
						<tr>
							<td width="70" height="22px" align="right">
								起始日期：
							</td>
							<td width="170" align="left">
								<input id="beginTime" name="beginTime" type="text" value="${beginTime}"
									class="input_text_long" onfocus="calendar();" />
							</td>
							<td width="70" align="right">
								截止日期：
							</td>
							<td width="170" align="left">
								<input id="endTime" name="endTime" type="text" value="${endTime}"
									class="input_text_long" onfocus="calendar();" />
							</td>
							<td>
								<input type="button" class="btnStyle" id="delBtn"
									onclick="find();" value="查 询" />
							</td>
						</tr>
					</table>
				</div>
				<!-- 补白 -->
				<div class="blank"></div>

				<!-- 表格body -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;">

						<tr bgColor="#dadfd0" height="23px">
							<td width="100px">
								<div align="center">
									日期
								</div>
							</td>
							<td>
								<div align="center">
									注册用户
								</div>
							</td>
							<td>
								<div align="center">
									沉默用户
								</div>
							</td>
							<td>
								<div align="center">
									活跃用户
								</div>
							</td>
							<td>
								<div align="center">
									VIP用户
								</div>
							</td>
							<td>
								<div align="center">
									逃离用户
								</div>
							</td>

						</tr>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
									<script language="javascript">
								       document.write(OracleData('${list.tjdate}'));
									   //document.write('${list.tjdate}'.substr(0,10));														
								    </script>
								</td>
								<td align="center" class="txtStyle">
									${list.regnum}
								</td>
								<td align="center" class="txtStyle">
									${list.silentnum}
								</td>
								<td align="center" class="txtStyle">
									${list.activenum}
								</td>
								<td align="center" class="txtStyle">
									${list.vipnum}
								</td>
								<td align="center" class="txtStyle">
									${list.escapenum}
								</td>
							</tr>
						</c:forEach>

						<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								合计
							</td>
							<td align="center" class="txtStyle">
								${tj.regnum}
							</td>
							<td align="center" class="txtStyle">
								${tj.silentnum}
							</td>
							<td align="center" class="txtStyle">
								${tj.activenum}
							</td>
							<td align="center" class="txtStyle">
								${tj.vipnum}
							</td>
							<td align="center" class="txtStyle">
								${tj.escapenum}
							</td>
						</tr>

					</table>
				</div>
				
				<div class="blank"></div>
				<div id="tip" class="tip">
					<div id="msg" class="msg">
						<span class="left_bt">数据项说明</span>
						<br />
						<span class="left_txt"> &nbsp; 注册用户：当天新增注册用户数</span>
						<br />
						<span class="left_txt"> &nbsp; 沉默用户：连续1个月无访问记录，并无任何订购关系（账户余额少于3且不为零）的用户数</span>
						<br />
						<span class="left_txt"> &nbsp; 活跃用户：本日有首次充值的用户数</span>
						<br />
						<span class="left_txt"> &nbsp; VIP用户：10天内投注金额大于100元的用户数</span>
						<br />
						<span class="left_txt"> &nbsp; 逃离用户：连续1个月无访问记录，并无任何订购关系（账户余额小于1元）</span>
						<br />											
					</div>
				</div>
				
			</div>
		</form>
	</body>
</html>
