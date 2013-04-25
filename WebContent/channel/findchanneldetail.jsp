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
		<script type="text/JavaScript">
           $(document).ready(function(){ 
              $("#btn_back").click(function(){  
              back();     
              }); 
             
              oninit();
           }); 
           
           function oninit(){
              $("#ywid").html('${model.yw.name}'); //selectedValue = 2;              
           }

           function back() {
	           location.replace('listchannel.do');
           }
           
        
         
       </script>
	</head>

	<body>
		<form id="form1" method="post" action="updatechannel.do">
		    <input id="id" name="id" type="hidden" value="${model.id}" />
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
							<span id="ywid" name="ywid"></span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									渠道代码:
								</div>
							</td>
							<td>
								<span>${model.code}</span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									渠道名称:
								</div>
							</td>
							<td>
						<span>${model.name}</span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									渠道创建人:
								</div>
							</td>
							<td>
								<span>${model.user.realname}</span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									渠道创建时间:
								</div>
							</td>
							<td>
						<span><script language="javascript">
								       document.write(OracleData('${model.cjdate}'));													
								    </script></span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									合作模式:
								</div>
							</td>
							<td>
							<% List l=(List)request.getAttribute("cooperat");
							HashMap hs=new HashMap();
									HashMap type=new HashMap();
								 	type.put("CPA","1");
							    	type.put("CPS","2");
							    	type.put("CPC","3");
							    	type.put("CPA","4");
							    	type.put("RIVET","5");
							       	type.put("元/个","type1");
							    	type.put("元/月","type2");
							    	type.put("百分比","type3");
							if(l.size()!=0){
							 hs=(HashMap)l.get(0);
								if(l.size()==2&&l!=null){
									 %>
									<span>CPA+CPS</span>
									 <%
								}else{%>
							<span><%if(hs.get("COOPERAT_TYPE").toString().equals("RIVET")){
								out.print("固定金额");
								
							} else{
								out.print(hs.get("COOPERAT_TYPE"));
								
							}%></span>
								<%}}
							%> 
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									合作成本:
								</div>
							</td>
							<td>
								<%
								if(l.size()!=0&&l!=null){
									for(int i=0;i<l.size();i++){
										hs=(HashMap)l.get(i);
									%>
								<%if(hs.get("COOPERAT_TYPE").toString().equals("RIVET")){
								out.print("固定金额");
								
							} else{
								out.print(hs.get("COOPERAT_TYPE"));
								
							}%>:<span><%=hs.get("COUNT") %> </span><span><%=hs.get("COUNT_TYPE") %></span>
									<%
								 }
								}else{
								}
								%>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									推广地址:
								</div>
							</td>
							<td>
								<span>${model.url}</span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									联系方式:
								</div>
							</td>
							<td>
								<span>${model.tel}</span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_2">
								<div align="center">
									备注:
								</div>
							</td>
							<td>
								<span>${model.bz}</span>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td width="120px" class="left_title_1">
								<div align="center">
									&nbsp;
								</div>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返 回" id="btn_back"	class="btnStyle" />
							</td>
						</tr>
					</table>
				</div>
				
			</div>
		</form>
	</body>
</html>
