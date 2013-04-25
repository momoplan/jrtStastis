<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@page import="com.ruicai.basis.entity.Ttransaction"%>
<%@page import="com.ruicai.basis.util.ChargeType"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>用户详情</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/calendar.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="javascript">
          function find() {
	         form1.submit();
          }                 
          $(document).ready(function() {
      		var msg = "${errormsg}";
      		if(msg!=''&&msg!=null){
      			alert(msg);
      		}
      		});
        </script>
	</head>
	<body>
	<form id="form1" method="post" action="findsnuserdt.do">
			<div id="container" class="container">			
				<div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="70%" border="0">
						<tr>
							<td width="100px" height="24px" align="right">
								用户imei号：
							</td>
							<td width="170" align="left">
								<input name="imei" name="imei" type="text" value="${param.imei}" />
							</td>
							<td width="70" height="24px" align="right">
								起始日期:
							</td>
							<td width="170" align="left">
								<input id="starttime" name="starttime" type="text" value="${param.starttime}"
								class="input_text_long"	onfocus="calendar();" />
							</td>
							<td width="70" align="right">
								截止日期：
							</td>
							<td width="170" align="left">
								<input id="endtime" name="endtime" type="text" value="${param.endtime}"
									class="input_text_long" onfocus="calendar();" />
							</td>
							<td>
								&nbsp;
							</td>
							<td width="70">
								<input type="button" class="btnStyle" id="delBtn"
									onclick="find();" value="查 询" />
							</td>
						</tr>						
					</table>
				</div>
			</div>
		
		<!-- 补白 -->
				<div class="blank"></div>
				
				<!-- 表格body -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;" id="idTable">
					<thead>
						<tr bgcolor="#dadfd0" height="23px">
						<td></td>
							<td>
								<div align="center">
									交易ID
								</div>
							</td>
							<td>
								<div align="center">
									用户编号
								</div>
							</td>
							<td>
								<div align="center">
									充值方式
								</div>
							</td>
							<td>
								<div align="center">
									充值时间
								</div>
							</td>
							<td>
								<div align="center">
									金额
								</div>
							</td>
							<td>
								<div align="center">
									手续费
								</div>
							</td>
							<td>
								<div align="center">
									状态
								</div>
							</td>
							<td>
								<div align="center">
									备注
								</div>
							</td>
						</tr>
						</thead>
						<tbody>
						<%int sumamt = 0; %>
                        <c:forEach var="transaction" items="${list}" varStatus="num">
                       	<% 
									Ttransaction ttransaction = (Ttransaction)pageContext.getAttribute("transaction");
									String bank = ChargeType.getMemo(ttransaction.getBankid());
									String state = "";
									if(ttransaction.getState( ) == 0){
										state ="未完成";
									}else if(ttransaction.getState() == 1){
										state = "成功";
									}else if(ttransaction.getState() == 2){
										state = "失败";
									}else if(ttransaction.getState() == 3){
										state = "处理中";
									}
									sumamt += ttransaction.getAmt();
								%>
						<tr bgcolor="#FFFFFF" height="23px">
							<td title="${num.count}" align="center">${num.count}</td>
							<td title="${transaction.id}" align="center">${transaction.id}"</td>
							<td title="${transaction.userno}" align="center">${transaction.userno}</td>
							<td title="${transaction.bankid}" align="center"><%=bank %></td>
							<td title="${transaction.plattime}" align="center"><fmt:formatDate value="${transaction.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td title="${transaction.amt}" align="center">${transaction.amt/100}</td>
							<td title="${transaction.fee}" align="center">${transaction.fee/100}</td>
							<td title="${transaction.state}" align="center"><%=state %></td>
							<td title="${transaction.memo}" align="center">${transaction.memo}</td>
						</tr>
						</c:forEach>
						</tbody>
						<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								本页合计
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
								<%=sumamt/100 %>
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
						</tr>
						<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								总合计
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
								${sumAmt/100 }
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
							<td align="center" class="txtStyle">
							</td>
						</tr>
						<tr bgcolor="#dadfd0" height="23px">
							<td align="right" colspan="9">
								<c:import url="/page/rollpage_o.jsp" /> 
							</td>
						</tr>
					</table>
				</div>
				</form>
		</body>
		
</html>
