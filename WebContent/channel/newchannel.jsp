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
	           location.replace('listchannel.do');
           }
           
           function save(){
              var flag = true;
              var code = $("#code")[0].value;  
              var name = $("#name")[0].value;
              var url  = $("#url")[0].value;
              var patrn=/^[0-9]+[.][0-9]+$/;
              var patrn1=/^[0-9]+$/;          
              $("#error_code").text("");             
              if(code ==''){
                 $("#error_code").text("渠道代码不能为空！");
                 flag = false;
              }else{
                 $("#error_code").text("");
              }              
              if(name==''){
                 $("#error_name").text("渠道名称不能为空！");
                 flag = false;
              }else{
                 $("#error_name").text("");
              }
              if(url==''){
                 $("#error_url").text("推广地址不能为空！");
                 flag = false;
              }else{
                 $("#error_url").text("");
              }              
              var cooperat_type=document.getElementById("cooperat_type").value;
				if(cooperat_type=="4"){
					var count0=document.getElementById("count0").value;
					var count1=document.getElementById("count1").value;
					if(count0==''||count1==''){
						   $("#error_count").text("合作成本不能为空！");
						     flag = false;
						}
					else{
						   $("#error_count").text("");
						}
					}else{
					var count0=document.getElementById("count0").value;
					if(count0==''){
						   $("#error_count").text("合作成本不能为空！");
						     flag = false;
						}else{
						   $("#error_count").text("");
							}

						}
              var count_type=document.getElementById("count_type0").value;
              if(count_type=='type3'&&cooperat_type=='4'){
            		if(count0>=100||count1>=100){
                		alert("百分比超出范围");
						     flag = false;
                 }
                    }
              else if(cooperat_type=='4'){
            	  var count_type1=document.getElementById("count_type1").value;
            	  if(count_type1=='type3'){
            		if(count1>=100){
                		alert("百分比超出范围");
						     flag = false;
                 }}
                    }
              else if(count_type=='type3'){
                 		if(count0>=100){
                    		alert("百分比超出范围");
    						     flag = false;
                     }
                        }
              if((!patrn.test(count0))&&(!patrn1.test(count0))){
					alert("金额不合法");
				    return false;
			     }else if(cooperat_type=="4"){
			    	  if((!patrn.test(count1))&&(!patrn1.test(count1))){
							alert("金额不合法");
						    return false;
					     }

				     }
              var channelcodelist=document.getElementById("channelcodelist").value;
              var str=channelcodelist.split(",");
              for(var i=0;i<str.length;i++){
				if(str[i]==code){
             	alert("渠道号已存在");
             	  return false;
             	}
                  }
              if(flag){
                $('#form1')[0].submit();
              }else{
                return false;
              }
           }

           function checkingcount(){
	var cooperat_type=document.getElementById("cooperat_type").value;
			if(cooperat_type=="4"){
			document.getElementById("countchange").innerHTML="CPA:  <input id='count0' name='count0' type='text'	style='"+"width: 50px;height: 13px;'"+" />&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'>	<option value='type1'>元/个</option></select>";
			document.getElementById("countchange").innerHTML+="CPS:  <input id='count1' name='count1' type='text'	style='"+"width: 50px;height: 13px;'"+" />&nbsp;&nbsp;&nbsp;<select id='count_type1' name='count_type1'><option value='type3'>百分比 </option></select>";
			
			}else if(cooperat_type=="1"||cooperat_type=="3"){
				document.getElementById("countchange").innerHTML="<input id='count0' name='count0' type='text'	style='"+"width: 50px;height: 13px;'"+" />	&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'><option value='type1'>元/个</option></select>";
			
				}



			else if(cooperat_type=="2"){
				document.getElementById("countchange").innerHTML="<input id='count0' name='count0' type='text'	style='"+"width: 50px;height: 13px;'"+" />	&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'><option value='type3'>百分比 </option></select>";
				}
			

				else if(cooperat_type=="5"){
				document.getElementById("countchange").innerHTML="<input id='count0' name='count0' type='text'	style='"+"width: 50px;height: 13px;'"+" />	&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'><option value='type2'>元/月</option></select>";
				}
			
               }


           
       </script>
	</head>

	<body>
		<form id="form1" method="post" action="savechannel.do">
			<div id="man_zone">				
				
				<!-- 表格body -->
				<div class="table_db">
					<table width="98%" border="0" align="center" cellpadding="3"
						cellspacing="1" class="table_style">
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									业务名称:
								</div>
							</td>
							<td>
								<select id="ywid" name="ywid">
									<c:forEach var="list" items="${list}" varStatus="it">
										<option value="${list.id}">
											${list.name}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									渠道代码:
								</div>
							</td>
							<td>
								<input id="code" name="code" type="text" maxlength="6"
									style="float: left; width: 200px" />
								<div id="error_code" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									渠道名称:
								</div>
							</td>
							<td>
								<input id="name" name="name" type="text"
									style="float: left; width: 200px" />
								<div id="error_name" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									合作模式:
								</div>
							</td>
							<td>
							<select id="cooperat_type" name="cooperat_type" onchange="checkingcount()">
							<option value='1'>CPA</option>
							<option value='2'>CPS</option>
							<option value='3'>CPC</option>
							<option value='4'>CPA+CPS</option>
							<option value='5'>固定金额</option>
							</select>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									合作成本:
								</div>
							</td>
							<td>
								<span id="countchange" >
									<input id="count0" name="count0" type="text"	style='float: left; width: 50px;height: 13px;' />
									&nbsp;&nbsp;&nbsp;
									<select id="count_type0" name="count_type0">
									<option value="type1">元/个</option>
									</select>
									</span>
									<span id="error_count" style="color: #C04000; "></span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									推广地址:
								</div>
							</td>
							<td>
								<input id="url" name="url" type="text" value="e.do"
									style="float: left; width: 200px" />
								<div id="error_url" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									联系方式:
								</div>
							</td>
							<td>
								<input id="tel" name="tel" type="text"
									style="float: left; width: 200px" />
								<div id="error_name" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									备注:
								</div>
							</td>
							<td>
								<input id="bz" name="bz" type="text"
									style="float: left; width: 200px" />
								<div id="error_fid" style="color: #C04000; overflow: left;"></div>
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
				<input type="hidden" id="channelcodelist" value="<%=request.getAttribute("channelcodelist") %>" />
			</div>
		</form>
	</body>
</html>
