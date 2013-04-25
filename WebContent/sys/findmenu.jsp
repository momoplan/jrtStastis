<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>菜单管理</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/comm.js"></script>
		<script type="text/JavaScript">
           $(document).ready(function(){ 
           //   $("#btn_back").click(function(){  
           //   back();     
           //   }); 
              $("#btn_ok").click(function(){  
              save();     
              }); 
           });            

           function back() {
	           location.replace('listmenu.do');
           }
           
           function save(){
              var flag = true;
              var name = $("#name")[0].value;  
              var fid = $("#fid")[0].value;  
              var ascid = $("#ascid")[0].value;       
                         
              $("#error_name").text("");             
              if(name ==''){
                 $("#error_name").text("菜单名称不能为空！");
                 flag = false;
              }else{
                 $("#error_name").text("");
              }
              
              if(!checkIsInt(fid)){
                 $("#error_fid").text("请填写整数数字！");
                 flag = false;
              }else{
                 $("#error_fid").text("");
              }
              if(!checkIsInt(ascid)){
                 $("#error_ascid").text("请填写整数数字！");
                 flag = false;
              }else{
                 $("#error_ascid").text("");
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
		<form id="form1" method="post" action="updatemenu.do">
		    <input id="id" name="id" type="hidden" value="${model.id}" />
			<div id="man_zone">				
				<!-- 表格body -->
				<div class="table_db">
					<table width="98%" border="0" align="center" cellpadding="3"
						cellspacing="1" class="table_style">
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									菜单名称:
								</div>
							</td>
							<td>
								<input id="name" name="name" type="text" style="float:left;width:200px" value="${model.name}" />
								<div id="error_name" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									菜单地址:
								</div>
							</td>
							<td>
								<input id="url" name="url" type="text" value="${model.url}" style="float:left;width:200px" />
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									父菜单号:
								</div>
							</td>
							<td>
								<input id="fid" name="fid" type="text" style="float:left;width:200px" value="${model.fid}" />
								<div id="error_fid" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									序号:
								</div>
							</td>
							<td>
								<input id="ascid" name="ascid" type="text" style="float:left;width:200px" value="${model.ascid}" />
								<div id="error_ascid" style="color: #C04000; overflow: left;"></div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									备注:
								</div>
							</td>
							<td>
								<input id="bz" name="bz" type="text" style="float:left;width:200px" value="${model.bz}" />
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
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返 回" id="btn_back"	class="btnStyle" onclick="javascript:window.history.go(-1);"/>
							</td>
						</tr>
					</table>
				</div>
				
			</div>
		</form>
	</body>
</html>
