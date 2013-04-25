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
		<script type="text/javascript" src="../js/datautil.js"></script>
		
		<script language="javascript">
          function add() {
	         //location.replace("yewuadd.html"); onfocus="calendar();"
	         location.href("yewuadd.html");
          }
          function find() {
             var beginTime = $("#beginTime")[0].value;
             var endTime = $("#endTime")[0].value;
             
             if(CompareDate(beginTime,endTime)){
                form1.submit();
             }else{
                alert('起始日期必须小于截止日期');
             }
          }
          
          var childmenu = new Array(
          ['0','0','0']    	      
	      <c:forEach var="channellist" items="${channellist}" varStatus="it">
			,['${channellist.yw.code}','${channellist.code}','${channellist.name}']				
	      </c:forEach>	      
          );
          
function changeyw()
{
  var slv = document.getElementById("ywid").value;
  document.getElementById("channelid").options.length = 0; 
  
  slct=document.createElement("Option");
  slct.value="";
  slct.text="---全部显示---";
  document.getElementById("channelid").add(slct);
    
  for(i=0;i<childmenu.length;i++)
  {       
    if (childmenu[i][0] == slv)
    {
      //alert(childmenu[i][0] + '  ' + childmenu[i][1] + '  ' + slv);
      slct=document.createElement("Option");
      slct.value=childmenu[i][1];
      slct.text=childmenu[i][2];
      document.getElementById("channelid").add(slct);
    }
 }
}

function init()
{
var ywid = '${ywid}';
var channelid = '${channelid}';
var province = '${province}';

if(ywid != ''){
   document.getElementById("ywid").value = ywid;
   changeyw();
   document.getElementById("channelid").value = channelid;
}
if(province != ''){
   document.getElementById("province").value = province;
}
}
          
        </script>
	</head>

	<body onLoad="init();">
		<form id="form1" method="post" action="pvlist.do">
			<div id="container" class="container">	
				<div class="table_db">
					<table cellSpacing="0" cellPadding="0" width="798" border="0">
						<tr>
							<td width="70" height="24px" align="right">
								起始日期：
							</td>
							<td width="170" align="left">
								<input id="beginTime" name="beginTime" type="text" value="${beginTime}" class="input_text_long"
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

						<tr bgColor="#dadfd0" height="23px">
							<td width="150px">
								<div align="center">
									日期
								</div>
							</td>
							<td>
								<div align="center">
									业务名称
								</div>
							</td>
							<td>
								<div align="center">
									渠道名称
								</div>
							</td>							
							<td>
								<div align="center">
									访问用户数
								</div>
							</td>
							<td>
								<div align="center">
									注册用户数
								</div>
							</td>
							<td>
								<div align="center">
									充值用户数
								</div>
							</td>
							<td>
								<div align="center">
									注册转化率
								</div>
							</td>
							<td>
								<div align="center">
									充值转化率
								</div>
							</td>

						</tr>
						<c:forEach var="list" items="${list}" varStatus="it">
							<tr bgcolor="#FFFFFF" height="23px">
								<td align="center" class="txtStyle">
								    <script language="javascript">
								       document.write(OracleData('${list.tjdate}'));													
								    </script>									
								</td>
								<td align="center" class="txtStyle">
									${list.ywid}
								</td>
								<td align="center" class="txtStyle">
									${list.channelid}
								</td>								
								<td align="center" class="txtStyle">
									${list.visitnum}
								</td>
								<td align="center" class="txtStyle">
									${list.regnum}&nbsp;/&nbsp;<a href = "regnumlist.do?channel=${list.channel}&time=${list.tjdate}">查看</a>
								</td>
								<td align="center" class="txtStyle">
									${list.paynum}
								</td>
								<td align="center" class="txtStyle">
								<script language="javascript">
									var i = 0;
									var a = ${list.visitnum};
									var b = ${list.regnum};		
									if(b!=0&&a!=0){
									  i = (b/a)*100;									  
									}	
												
									document.write(i.toFixed(2));													
								</script>%
								</td>
								<td align="center" class="txtStyle">
								<script language="javascript">
									var i = 0;
									var a = ${list.regnum};
									var b = ${list.paynum};		
									if(b!=0&&a!=0){
									  i = (b/a)*100;									  
									}	
												
									document.write(i.toFixed(2));													
								</script>%
								</td>
							</tr>
						</c:forEach>

						<tr bgcolor="#dadfd0" height="23px">
							<td align="center" class="txtStyle">
								合计
							</td>
							<td align="center" class="txtStyle">
								--
							</td>
							<td align="center" class="txtStyle">
								--
							</td>
							<td align="center" class="txtStyle">
								${tj.visitnum }
							</td>
							<td align="center" class="txtStyle">
								${tj.regnum }
							</td>
							<td align="center" class="txtStyle">
								${tj.paynum }
							</td>
							<td align="center" class="txtStyle">
								<script language="javascript">
									//document.write(${tj.percent}.toFixed(2));
									var i = 0;
									var a = ${tj.visitnum};
									var b = ${tj.regnum};
	
									if(b!=0&&a!=0){
									  i = (b/a)*100;									  
									}												
									document.write(i.toFixed(2));														
								</script>%
							</td>
							<td></td>
						</tr>

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
						<!-- 
						<span class="left_txt"> &nbsp; <img src="../images/ts.gif" width="16" height="16" /> 
						提示： &nbsp;<span class="left_ts">省份</span> 字段由于缺少IP号段表暂时无法查询,等待下期完善！
						</span>	
						 -->					
					</div>
				</div>
			</div>
		</form>
	</body>
</html>

