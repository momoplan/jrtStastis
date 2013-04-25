<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>渠道管理</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/datautil.js"></script>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/calendar.js"></script>
		
		<script language="javascript">
		$(document).ready(function(){ 
          oninit();
        });
         
        function oninit(){
             $("#ywid").val('${ywid}'); //selectedValue = 2;              
        }
		
		function find() {
			var startTime = form1.startTime.value;
			var endTime = form1.endTime.value;
			/**
			验证开始时间与结束时间
			**/
			if(compare_time(startTime,endTime)==false){
				alert("开始时间不能大于截止时间!!!!");
                return ;
			}
	        form1.submit();
        }
		
        function add() {
	      //location.replace("yewuadd.html");
	      location.href("newchannel.do");
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
	        objFrm.form.action = "delchannel.do";
	        objFrm.form.submit();
	      }
        }

       /**
                       比较开始与结束时间的大小
       **/
        function compare_time(a,b) {
        	   var arr=a.split("-");
        	   var starttime=new Date(arr[0],arr[1],arr[2]);
        	   var starttimes=starttime.getTime(); 
        	   var arrs=b.split("-"); 
        	   var endtime=new Date(arrs[0],arrs[1],arrs[2]);
        	   var endtimes=endtime.getTime();
        	   if(starttimes>endtimes)//开始大于结束
        	   {
        	     return false;
        	   } 
        	   else{ 
        	    return true; 
        	   }
        	}

        /**
                        合作方式
        **/
    	function cooperat(code,visitnum,regnum,balance,buymoney,days){
    	   var startTime = form1.startTime.value;
		   var endTime = form1.endTime.value;
           var strUrl = "findCooperatechannel.do?code="+code+"&visitnum="+visitnum+"&regnum="+regnum;
           strUrl+="&balance="+balance+"&buymoney="+buymoney;
           strUrl+="&days="+days;
           location.href = strUrl;
        }
        </script>
	</head>

	<body>
		<form id="form1" name="form1" method="post" action="listchannel.do">
		    <input id="ids" name="ids" type="hidden" value="" />
			<div id="container" class="container">	

                <div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="798" border="0">

						<tr>
							<td width="70" height="24px" align="right">
								业务名称：
							</td>
							<td width="170" align="left">
								<select name="ywid" id="ywid" class="select_text_long">
									<option value="0">
										---全部显示---
									</option>
									<c:forEach var="ywlist" items="${ywlist}" varStatus="it">
										<option value="${ywlist.id}">
											${ywlist.name}
										</option>
									</c:forEach>
								</select>
							</td>
							
							<td width="70" height="24px" align="right">
								开始日期：
							</td>
							<td width="170" align="left">
								<input type="text" name="startTime" onclick="calendar();" readonly="readonly" value="${startTime}"/>
							</td>
							
							<td width="70" height="24px" align="right">
								截止日期：
							</td>
							<td width="170" align="left">
								<input type="text" name="endTime" onclick="calendar();" readonly="readonly" value="${endTime}"/>
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
						<tr bgcolor="#dadfd0" height="25px">
							<td width="44">
								<div align="center">
									编号
								</div>
						  </td>
							<td width="107">
								<div align="center">
									业务名称
								</div>
						  </td>
							<td width="102">
								<div align="center">
									渠道编号
								</div>
						  </td>
							<td width="147">
								<div align="center">
									渠道名称
								</div>
						  </td>
							<td width="102">
								<div align="center">
									总充值金额
								</div>
						  </td>
							<td width="90">
								<div align="center">
									注册用户数
								</div>
						  </td>
							<td width="90">
								<div align="center">
									注册当天充值用户数
								</div>
						  </td>
							<td width="90">
								<div align="center">
									充值用户数
								</div>
						  </td>
							<td width="240">
								<div align="center">
									渠道地址
								</div>
						  </td>
							<td width="96">
								<div align="center">
									购彩金额
								</div>
						  </td>
							<td width="96">
								<div align="center">
									结算金额
								</div>
						  </td>
							<!--
							<td width="50">
								<div align="center">
									状态
								</div>
							</td>
							-->
							<td width="97" align="center">操作</td>							
							<td width="56">
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
									${list.yw.name}
								</td>
								<td align="center" class="txtStyle">
									${list.code}
								</td>
								<td align="center" class="txtStyle">
								<a href="findCooperatechannel.do?code=${list.code}&visitnum=${list.visitnum}&regnum=${list.regnum }&balance=${list.balance}&buymoney=${list.buymoney}&days=${list.days }&endTime=${endTime }&startTime=${startTime }" class="txtStyle">${list.name}</a> 
								  <!-- <a href="#" onclick="cooperat('${list.code}','${list.visitnum}','${list.regnum }','${list.balance}','${list.buymoney}','${list.days }');return false;" class="txtStyle">${list.name}</a> -->
								</td>
								<td align="center" class="txtStyle">
									${list.paymoney}
								</td>
								<td align="center" class="txtStyle">
									${list.regnum}
								</td>
								<td align="center" class="txtStyle">
									${list.regPaynum}
								</td>
								<td align="center" class="txtStyle">
									${list.paynum}
								</td>
								<td align="center" class="txtStyle">
									${list.url}?cn=${list.id}
								</td>
								<td align="center" class="txtStyle">
									${list.buymoney }
								</td>
								<td align="center" class="txtStyle">
									${list.balance}
								</td>
								<!-- 
								<td align="center" class="txtStyle">
									<script language="javascript">
									   if($ {list.status}==1)
								         document.write('开启');
								       else
								       	 document.write('关闭');												
								    </script>
								</td>
								-->
								<td align="center" class="txtStyle">
								    <a href="findchannel.do?id=${list.id}&code=${list.code}" class="txtStyle">编辑</a>
									&nbsp;&nbsp;
									<a href="find1channel.do?id=${list.id}&code=${list.code}" class="txtStyle">详细</a>
								</td>
								<td align="center" class="txtStyle">
									<input type="checkbox" id="${list.id}" name="record" value='测试' />
								</td>
							</tr>
						</c:forEach>

						<tr bgcolor="#dadfd0" height="23px">
							<td align="right" colspan="13">
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
				
			</div>
		</form>
	</body>
</html>