<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>业务管理</title>
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
              //oninit();
           }); 
           
           function oninit(){
              $("#status").val('${model.status}'); //selectedValue = 2;              
           }

           function back() {
	           location.replace('listyw.do');
           }
           
           function save(){
              var flag = true;
              var code = $("#code")[0].value;  
              var name = $("#name")[0].value;
                         
              $("#error_code").text("");             
              if(code ==''){
                 $("#error_code").text("业务代码不能为空！");
                 flag = false;
              }
              
              if(name==''){
                 $("#error_name").text("业务名称不能为空！");
                 flag = false;
              }

              if(flag){
                $('#form1')[0].submit();
              }else{
                return false;
              }
           }
       </script>
	</head>

	<body>
		<form id="form1" method="post" action="updateyw.do">
		    <input id="id" name="id" type="hidden" value="${model.id}" />
			<div id="man_zone">
				
				<!-- 表格body -->
				<div class="table_db">
					<table width="98%" border="0" align="center" cellpadding="3"
						cellspacing="1" class="table_style">
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									业务代码:
								</div>
							</td>
							<td>
								<input id="code" name="code" type="text" maxlength="5" value="${model.code}"
									style="float: left; width: 200px" />
								<div id="error_code" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									业务名称:
								</div>
							</td>
							<td>
								<input id="name" name="name" type="text" value="${model.name}"
									style="float: left; width: 200px" />
								<div id="error_name" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>	
						<!--  					
						<tr bgcolor="#ffffff">
							<td width="100">
								<div align="center">
									状态:
								</div>
							</td>
							<td>
								<select id="status" name="status">
									<option value="1">
										开启
									</option>
									<option value="0">
										关闭
									</option>
								</select>
							</td>
						</tr>
						-->
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									备注:
								</div>
							</td>
							<td>
								<input id="bz" name="bz" type="text" value="${model.bz}"
									style="float: left; width: 200px" />
								<div id="error_fid" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
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
				
			</div>
		</form>
	</body>
</html>