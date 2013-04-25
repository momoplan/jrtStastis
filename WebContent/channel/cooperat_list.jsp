<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title>渠道管理</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/datautil.js"></script>
		<script type="text/javascript" src="../js/jquery.js"></script>
		
		
	</head>

	<body>
		<form id="form1" name="form1" method="post" action="listchannel.do">
		    <input id="ids" name="ids" type="hidden" value="" />
			<div id="container" class="container">	

               
				<!-- 补白 -->
				<div class="blank"></div>
				
				<!-- 表格body -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;">
						<tr bgcolor="#dadfd0" height="25px">
							<td width="510" align="center"><p >合作模式</p></td>
							<td width="510" align="center"><p >运算单位</p></td>
							<td width="690" align="center">对应渠道的结算金额</td>
						</tr>
						<c:forEach var="list" items="${list}" varStatus="cIndex">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
								<c:if test="${list.cooperatType=='RIVET'}">固定金额</c:if>
								<c:if test="${list.cooperatType!='RIVET'}">${list.cooperatType}</c:if>
								</td>
								<td width="510" align="center"><p >${list.countType }</p></td>
							  
								 <td width="510" align="center"><p>
									<c:if test="${list.cooperatType=='CPA'}">${list.cpaCount}</c:if>
									<c:if test="${list.cooperatType=='CPC'}">${list.cpcCount}</c:if>
									<c:if test="${list.cooperatType=='CPS'}">${list.cpsCount}</c:if>
									<c:if test="${list.cooperatType=='RIVET'}">${list.rivetCount}</c:if>
									元
								 </p></td>
							</tr>
							  </c:forEach>
							
							

						<tr bgcolor="#dadfd0" height="23px">
							<td align="right" colspan="11">
								


<script language="javascript">
		function formSubmit(num){

			if(num==-100000){
				document.forms[0].page.value = document.forms[0].selectPage.value;
			}else{
				if(num >0){
					num=0;
					document.forms[0].page.value = num;
				}
				if(num < 0){
					num=0;
					document.forms[0].page.value = num;
				}
				if(!CheckNumber(document.forms[0].page_in,'页码只能是合法的数字')){
					//document.forms[0].page.value = 0;
					document.forms[0].page_in.focus();
					return;
				}
				
				document.forms[0].page.value = num;
			}
			document.forms[0].submit();
		}
		  /**
		    * 检查传入的对象的值是否全是数字
		    *
		    * @param ele 页面传入的需要校验的field对象
		    * @param msg 校验失败时的提示信息
		    * @return boolean 返回校验是否通过
		    */
		
		function CheckNumber(ele,msg) {
			var str=ele.value;
			if(str=='')
			{
			    alert("" + msg);
			    ele.focus();
			    return false;
			}
			//alert('!isNumbers(str)'+!isNumbers(str));
		    if(!isNumbers(str)){
			    	alert("" + msg);
			    	ele.focus();
			    	return false;
			}
			return true;
		}
		  /**
		    * 判断输入的是否全是数字。该函数一般不直接提供外部调用，而是由其它函数调用
		    *
		    * @param str为要校验的对象的值
		    * @return boolean 返回校验的字符串是否全是数字    
		    */
		function isNumbers(str){
			for(var i=0;i<str.length;i++) {
				var ch=str.charCodeAt(i);
				if(ch<48 || ch>57) {
					return false;
				}
			}
			return true;
		}
		function setpageValue(){
			//document.forms[0].page_in2.value = document.forms[0].page_in.value-1;
			document.forms[0].page.value = document.forms[0].page_in.value-1;
		}
</script>

<table width="100%" height="22" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr bgcolor="" height=22>
	<td width="18%" height="22" align="right" class="landing_table_tr">合计：<c:out value="${balance}"/>元</td>
	<td width="37%" height="22" align="right" class="landing_table_tr">&nbsp;<input type="button" value="返回" onclick="javascript:history.go(-1)"/></td>
	<td width="20%" height="22" align="right" class="landing_table_tr">&nbsp;</td>
	<td width="25%" height="22" align="right" class="landing_table_tr">&nbsp;</td>
	</tr>
</table>							</td>
						</tr>
					</table>
				</div>
				
				<!-- 表格foot -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" align="left">
						<tbody>
							<tr bgcolor="#dadfd0">								
								<td align="right">&nbsp;</td>								
							</tr>
						</tbody>
					</table>
				</div>
				
			</div>
		</form>
	</body>
</html>