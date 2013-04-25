<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>用户详情</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<script language="javascript">
          function find() {
	         form1.submit();
          }                        
        </script>
	</head>

	<body>
		<form id="form1" method="post" action="finduserdt.do">
			<div id="container" class="container">			
				<div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="310px" border="0">
						<tr>
							<td width="100px" height="24px" align="right">
								注册手机号：
							</td>
							<td width="170" align="left">
								<input name="tel" name="tel" type="text" value="${tel}" maxlength="11" />
							</td>
							<td width="70">
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
					<table width="600" border="0" cellpadding="2" cellspacing="1"
						bgcolor="#a0ae88">
						<tr bgcolor="#ffffff" height="23px">
							<td width="100">
								<div align="center">
									用户手机号:
								</div>
							</td>
							<td width="150">
							    ${model.tel}							
							</td>
							<td width="100">
								<div align="center">
									身份证号:
								</div>
							</td>
							<td width="150">
							    ${model.certid}								
							</td>
						</tr>	
						<tr bgcolor="#ffffff" height="23px">
							<td width="100">
								<div align="center">
									业务号:
								</div>
							</td>
							<td width="150">
							   ${ywname}								
							</td>
							<td width="100">
								<div align="center">
									渠道号:
								</div>
							</td>
							<td width="150">
							   ${channelname}								
							</td>
						</tr>
						<tr bgcolor="#ffffff" height="23px">
							<td width="100">
								<div align="center">
									注册时间:
								</div>
							</td>
							<td width="150">
							   ${model.regtime}								
							</td>
							<td width="100">
								<div align="center">
									最后登陆时间:
								</div>
							</td>
							<td width="150">
							   ${model.modtime}								
							</td>
						</tr>
						<tr bgcolor="#ffffff" height="23px">
							<td width="100">
								<div align="center">
									消费金额:
								</div>
							</td>
							<td width="150">
							   ${model.totalbetamt}/元								
							</td>
							<td width="100">
								<div align="center">
									充值金额:
								</div>
							</td>
							<td width="150">
							   ${model.totalbepositamt}/元							
							</td>
						</tr>
						<tr bgcolor="#ffffff" height="23px">
							<td width="100">
								<div align="center">
									账户余额:
								</div>
							</td>
							<td width="150">
							    ${model.balance}/元								
							</td>
							<td width="100">
								<div align="center">
									
								</div>
							</td>
							<td width="150">								
							</td>
						</tr>								
					</table>
				</div>
				<!-- 补白 -->
				<div class="blank"></div>				
				（最近100条记录）
				<!-- 表格body -->
				<div class="table_db">
					<table cellSpacing="1" cellPadding="2" width="98%"	bgColor="#a0ae88" border="0">

						<tr bgColor="#dadfd0" height="23px" >
							<td>
								<div align="center">
									用户手机号
								</div>
							</td>
							<td>
								<div align="center">
									购彩类型
								</div>
							</td>
							<td>
								<div align="center">
									彩种名称
								</div>
							</td>
							<td>
								<div align="center">
									注数
								</div>
							</td>
							<td>
								<div align="center">
									金额(元)
								</div>
							</td>
							<td>
								<div align="center">
									投注时间
								</div>
							</td>

						</tr>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
									${model.tel}
								</td>
								<td align="center" class="txtStyle">
									${list.acceptno}
								</td>
								<td align="center" class="txtStyle">
									${list.lotno}
								</td>
								<td align="center" class="txtStyle">
									${list.betnum}
								</td>
								<td align="center" class="txtStyle">
									${list.amt}
								</td>
								<td align="center" class="txtStyle">
									${list.plattime}
								</td>
							</tr>
						</c:forEach>					
					</table>
				</div>
				
			</div>
		</form>
	</body>
</html>
