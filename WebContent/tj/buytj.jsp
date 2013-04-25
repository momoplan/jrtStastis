<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>购彩方式</title>
		<link rel="stylesheet" href="../css/npage.css" type="text/css"></link>
		<link rel="stylesheet" href="../css/skin.css" type="text/css"></link>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<script type="text/javascript" src="../js/comm.js"></script>
		<script type="text/javascript" src="../js/datautil.js"></script>
		<script type="text/javascript" src="../js/sortshow.js"></script>
		
		<script language="javascript">
          function add() {
	         //location.replace("yewuadd.html"); onfocus="calendar();"
	         location.href("yewuadd.html");
          }
          function find() {
        	  var date=new Date();
        		var d1 = new Date((date.getYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate()).replace(/\-/g, "\/"));
        		var beginTime=document.getElementById("beginTime").value;
        		var endTime=document.getElementById("endTime").value;
        		var d2 = new Date(beginTime.replace(/\-/g, "\/")); 
        		var d3 = new Date(endTime.replace(/\-/g, "\/")); 
        				if(d3<d2){
        				alert('起始日期必须小于截止日期');
            			}else{
            				form1.submit();
                			}
          }
          
           var childmenu = new Array(
          ['0','0','0']    	      
	      <c:forEach var="channellist" items="${channellist}" varStatus="it">
			,['${channellist.yw.id}','${channellist.id}','${channellist.name}']				
	      </c:forEach>	      
          );
          
function changeyw()
{
  var slv = document.getElementById("ywid").value;
  document.getElementById("channelid").options.length = 0; 

  //var slct=document.createElement("Option");
  //slct.value="";
  //slct.text="---全部显示---";
  //document.getElementById("channelid").add(slct);
  $('#channelid').append('<option value="" selected="selected">---全部显示---</option>');
  
  for(i=0;i<childmenu.length;i++)
  {    
    if (childmenu[i][0] == slv)
    {
      //alert(childmenu[i][0] + '  ' + childmenu[i][1] + '  ' + slv);
      slct=document.createElement("Option");
      slct.value=childmenu[i][1];
      slct.text=childmenu[i][2];
      //document.getElementById("channelid").add(slct);
      $('#channelid').append('<option value="'+childmenu[i][1]+'">'+childmenu[i][2]+'</option>');
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

	<body onload="init();">
		<form id="form1" method="post" action="buytj.do">
			<div id="container" class="container">				
				<div class="table_db">
					<table cellspacing="0" cellpadding="0" width="558" border="0">
						<tr>
							<td width="70" height="24px" align="right">
								起始日期:
							</td>
							<td width="170" align="left">
								<input id="beginTime" name="beginTime" type="text" value="${beginTime}"
								class="input_text_long"	onfocus="calendar();" />
							</td>
							<td width="70" align="right">
								截止日期：
							</td>
							<td width="170" align="left">
								<input id="endTime" name="endTime" type="text" value="${endTime}"
									class="input_text_long" onfocus="calendar();" />
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="24px" align="right">
								业务名称：
							</td>
							<td>
								<select name="ywid" id="ywid" class="select_text_long"
									onchange="changeyw()">
									<option value="">
										---全部显示---
									</option>
									<c:forEach var="ywlist" items="${ywlist}" varStatus="it">
										<option value="${ywlist.id}">
											${ywlist.name}
										</option>
									</c:forEach>
								</select>
							</td>
							<td align="right">
								渠道名称：
							</td>
							<td>
								<select name="channelid" id="channelid" class="select_text_long">
									<option value="">
										---全部显示---
									</option>
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
					<table cellspacing="0" cellpadding="0" width="98%" bgcolor="#a0ae88" border="1" style="border-collapse:collapse;" id="idTable">
					<thead>
						<tr bgcolor="#dadfd0" height="23px">
							<td width="150">
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
									购彩方式
								</div>
							</td>
							<td>
								<div align="center">
									购彩用户数<a href="javascript:sortAble('idTable', 4,'int')">(排序)</a>
								</div>
							</td>
							<td>
								<div align="center">
									购彩金额(元)<a href="javascript:sortAble('idTable', 5,'int')">(排序)</a>
								</div>
							</td>
						</tr>
						</thead>
						<tbody>
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
								${list.buytype}
							</td>
							<td align="center" class="txtStyle">
								${list.usernum}
							</td>
							<td align="center" class="txtStyle">
								<script language="javascript">
									document.write(${list.buymoney}.toFixed(2));														
								</script>
							</td>
						</tr>
						</c:forEach>
						</tbody>
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
								--
							</td>
							<td align="center" class="txtStyle">
								${tj.usernum}
							</td>
							<td align="center" class="txtStyle">
								<script language="javascript">
									document.write(${tj.buymoney}.toFixed(2));														
								</script>
							</td>
						</tr>

					</table>
				</div>
				
				<div class="blank"></div>
				<div id="tip" class="tip">
					<div id="msg" class="msg">
						<span class="left_bt">数据项说明</span>
						<br />
						<span class="left_txt"> &nbsp; 购彩方式：指的是具体的彩种</span>
						<br />					
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
