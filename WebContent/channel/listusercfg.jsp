<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>业务推广PV</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<script type="text/javascript" src="../js/comm.js"></script>
		<script language="javascript">
          $(document).ready(function(){ 
              oninit();
           }); 
          
          function add() {
	         //location.replace("yewuadd.html"); onfocus="calendar();"
	         location.href("newusercfg.do");
          }       

          function oninit(){
             $("#userid").val('${userid}'); //selectedValue = 2;              
          }
          
          function find() {
	         form1.submit();
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
	           //alert(ele.id);
	        }
	      }
        }
        function del(obj) 
        {
          var objFrm=obj;
          var ids = "";
             
          elements=objFrm.form.elements;
          for (var i = 0; i < elements.length; i++) 
          {
            var ele = objFrm.form.elements[i];
            if (ele.type == "checkbox" ) 
            {
               if(ele.checked){
	             ids += ele.id + ",";
	           }
	        }
	      }
	      if(ids == '') {
	         alert('您没有选取要删除的数据！');
	         return;
	      }
	      if(!confirm('确认删除吗?')) return;
	      
	      objFrm.form.ids.value = ids;
	      //alert(objFrm.form.ids.value);
	      if(objFrm.form.ids.value!=""){
	        objFrm.form.action = "delusercfg.do";
	        objFrm.form.submit();
	      }
        }
        </script>
	</head>

	<body>
		<form id="form1" method="post" action="listusercfg.do">
			<input id="ids" name="ids" type="hidden" value="" />
			<div id="container" class="container">
				<div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="798" border="0">

						<tr>
							<td width="70" height="24px" align="right">
								登录名：
							</td>
							<td width="170" align="left">
								<select name="userid" id="userid" class="select_text_long">
									<option value="0">
										---全部显示---
									</option>
									<c:forEach var="userlist" items="${userlist}" varStatus="it">
										<option value="${userlist.id}">
											${userlist.name}
										</option>
									</c:forEach>
								</select>
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
					<table cellspacing="0" cellpadding="0" width="98%"
						bgcolor="#a0ae88" border="1" style="border-collapse: collapse;">

						<tr bgColor="#dadfd0" height="23px">
							<td width="50">
								<div align="center">
									编号
								</div>
							</td>
							<td>
								<div align="center">
									登录名
								</div>
							</td>
							<td>
								<div align="center">
									用户名
								</div>
							</td>
							<td>
								<div align="center">
									渠道名称
								</div>
							</td>
							<td width="50px">
								<div align="center">
									编辑
								</div>
							</td>
							<td width="50px">
								<div align="center">
									选项
								</div>
							</td>
						</tr>
						<%
							Integer i = 0;
						%>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
									<script language="javascript">
									  document.write(${rollPage.pageCur*rollPage.pagePer} + <%=(++i)%>);														
								    </script>
								</td>
								<td align="center" class="txtStyle">
									${list.user.name}
								</td>
								<td align="center" class="txtStyle">
									${list.user.realname}
								</td>
								<td align="center" class="txtStyle">
									${list.channel.name}
								</td>
								<td align="center">
									<a href="findusercfg.do?id=${list.id}" class="txtStyle">修改</a>
								</td>
								<td align="center">
									<input type="checkbox" id="${list.id}" name="record" value='测试' />
								</td>
							</tr>
						</c:forEach>

						<tr bgColor="#dadfd0" height="23px">
							<td align="right" colSpan="6">
                                <c:import url="/page/rollpage_o.jsp" />
							</td>
						</tr>

					</table>
				</div>
				<!-- 表格foot -->
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="98%" align="left">
						<tbody>
							<tr bgcolor="#dadfd0">								
								<td align="right">
									<input class="btnStyle" onclick="javascript:add();"
										type="button" value="添 加" />
									<input class="btnStyle" type="button" value="全 选"
										onclick="selectall(this);" />
									<input id="delBtn" class="btnStyle" type="button" value="删 除"
										onclick="del(this);" />
								</td>								
							</tr>
						</tbody>
					</table>
				</div>

				<div class="blank"></div>
				<div id="tip" class="tip">
					<div id="msg" class="msg">
						<span class="left_bt">数据项说明</span>
						<br />
						<span class="left_txt"> &nbsp; 访问用户数：由推广渠道引导过来的访问推广页面的用户数</span>
						<br />
						<span class="left_txt"> &nbsp; 注册用户数：由推广渠道引导过来的注册用户数</span>
						<br />
						<span class="left_txt"> &nbsp; 充值用户数：有过充值行为的用户数</span>
						<br />
						<span class="left_txt"> &nbsp; <img src="../images/ts.gif"
								width="16" height="16" /> 提示： &nbsp;<span class="left_ts">省份</span>
							字段由于缺少IP号段表暂时无法查询,等待下期完善！ </span>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>

