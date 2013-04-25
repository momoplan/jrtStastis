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
          
        </script>
	</head>

	<body>
		<form id="form1" method="post" action="useractiontj.do">
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
								<input type="submit" class="btnStyle" id="delBtn" 
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

						<tr bgColor="#dadfd0" height="23px" >
							<td width="100px">
								<div align="center">
									日期
								</div>
							</td>
							<td>
								<div align="center">
									首页访问用户数
								</div>
							</td>
							<td>
								<div align="center">
									推广用户访问数
								</div>
							</td>
							<td>
								<div align="center">
									充值成功笔数
								</div>
							</td>
							<td>
								<div align="center">
									充值行为笔数
								</div>
							</td>
							<td>
								<div align="center">
									充值总金额(元)
								</div>
							</td>
							<td>
								<div align="center">
									实际充值总金额(元)
								</div>
							</td>
							<td>
								<div align="center">
									客户端下载数
								</div>
							</td>
							<td>
								<div align="center">
									登录用户数
								</div>
							</td>
							<td>
								<div align="center">
									提现用户数
								</div>
							</td>
							<td>
								<div align="center">
									投注用户数
								</div>
							</td>		
						</tr>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
									<script language="javascript">
								       document.write(OracleData('${list.dates}'));
									   //document.write('${list.tjdate}'.substr(0,10));														
								    </script>
								</td>
								<td align="center" class="txtStyle">
									${list.indexvn}
								</td>
								<td align="center" class="txtStyle">
									${list.popvn}
								</td>
								<td align="center" class="txtStyle">
									${list.payvn}
								</td>
								<td align="center" class="txtStyle">
									${list.payn}
								</td>
								<td align="center" class="txtStyle">
									<script language="javascript">
									document.write(parseFloat(${list.paymoney}).toFixed(2));														
									</script>
								</td>
								<td align="center" class="txtStyle">
									<script language="javascript">
									document.write(parseFloat(${list.realPaymoney}).toFixed(2));														
									</script>
								</td>
								<td align="center" class="txtStyle">
									${list.psoftn}
								</td>
								<td align="center" class="txtStyle">
									${list.loginn}
								</td>
								<td align="center" class="txtStyle">
									${list.getmoneyn}
								</td>
								<td align="center" class="txtStyle">
									${list.buyn}
								</td>
								
							</tr>
						</c:forEach>
						<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								合计
							</td>
							<td align="center" class="txtStyle">
								${tj.indexvn}
							</td>
							<td align="center" class="txtStyle">
								${tj.popvn}
							</td>
							<td align="center" class="txtStyle">
								${tj.payvn}
							</td>
							<td align="center" class="txtStyle">
								${tj.payn}
							</td>
							<td align="center" class="txtStyle">
								<script language="javascript">
									document.write(${tj.paymoney}.toFixed(2));														
								</script>
							</td>
							<td align="center" class="txtStyle">
								<script language="javascript">
									document.write(${tj.realPaymoney}.toFixed(2));														
								</script>
							</td>
							<td align="center" class="txtStyle">
								${tj.psoftn}
							</td>
							<td align="center" class="txtStyle">
								${tj.loginn}
							</td>
							<td align="center" class="txtStyle">
								${tj.getmoneyn}
							</td>
							<td align="center" class="txtStyle">
								${tj.buyn}
							</td>
							
						</tr>
					</table>
				</div>
				
				<div class="blank"></div>
				<div id="tip" class="tip">
					<div id="msg" class="msg">
						<span class="left_bt">数据项说明</span>
						<br />
						<span class="left_txt"> &nbsp; 首页访问用户数：WAP首页访问用户数</span>
						<br />
						<span class="left_txt"> &nbsp; 推广用户访问数：WAP推广用户访问数（不包括未通过渠道引导来的用户数）</span>
						<br />
						<span class="left_txt"> &nbsp; 充值成功笔数：充值成功的订单总数（不区分WAP和WEB）</span>
						<br />
						<span class="left_txt"> &nbsp; 充值行为笔数：充值的订单总数（不区分WAP和WEB）</span>
						<br />
						<span class="left_txt"> &nbsp; 充值总金额：充值总金额（不区分WAP和WEB）</span>
						<br />
						<span class="left_txt"> &nbsp; 客户端下载数：客户端下载数（包括WAP和WEB）</span>
						<br />
						
						<span class="left_txt"> &nbsp; 登录用户数：登录用户数（包括WAP和WEB及客户端）</span>
						<br />
						<span class="left_txt"> &nbsp; 提现用户数：当前有提现操作的用户数（包括WAP和WEB及客户端）</span>
						<br />
						<span class="left_txt"> &nbsp; 投注用户数：投注用户数（包括WAP和WEB及客户端）</span>
						<br />									
					</div>
				</div>
				
			</div>
		</form>
	</body>
</html>