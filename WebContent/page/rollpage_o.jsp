<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ruicai.basis.common.RollPage"%>
<%
	String path = request.getContextPath();
	RollPage rollPage = (RollPage) request.getAttribute("rollPage");
%>
<script language="javascript">
		function formSubmit(num){

			if(num==-100000){
				document.forms[0].page.value = document.forms[0].selectPage.value;
			}else{
				if(num >${rollPage.pageNum-1}){
					num=${rollPage.pageNum-1};
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
		<td height="22" align="right" class="landing_table_tr">
			<input type="hidden" name="page" value='${rollPage.pageCur}'>
			共
			<font color="red"><b>${rollPage.rowCount}</b>
			</font>条记录&nbsp;分
			<FONT color="red"><B>${rollPage.pageNum}</B>
			</FONT>页显示&nbsp;当前为第
			<FONT color="red"><B>${rollPage.pageCur+1}</B>
			</FONT>页&nbsp;
			<%
				if (rollPage.getPageCur() > 0) {
			%>
			<a href='javascript:formSubmit(0)'><font color="#FF6633">[首页]</font>
			</a>&nbsp;&nbsp;<a href='javascript:formSubmit(${rollPage.pageCur-1})'><font
				color="#FF6633">上一页</font>
			</a>
			<%
				}
			%>
			<%
				if (rollPage.getPageCur() < 1) {
			%>
			[首页]&nbsp;&nbsp;上一页
			<%
				}
			%>
			<%
				if (rollPage.getPageCur() + 1 < rollPage.getPageNum()) {
			%>
			<a href='javascript:formSubmit(${rollPage.pageCur+1})'><font
				color="#FF6633">下一页</font>
			</a>&nbsp;&nbsp;<a href='javascript:formSubmit(${rollPage.pageNum-1})'><font
				color="#FF6633">[尾页]</font>
			</a>
			<%
				}
			%>
			<%
				if (rollPage.getPageCur() + 1 >= rollPage.getPageNum()) {
			%>
			下一页&nbsp;&nbsp;[尾页]
			<%
				}
			%>
			&nbsp;&nbsp;
			<input type="text" name="page_in" style= "height:12px;width:20px " value='${rollPage.pageCur+1}'  onBlur="setpageValue()"/>
			<a href='javascript:formSubmit(document.forms[0].page.value)'><font
				color="">GO</font>
			</a>
			&nbsp;&nbsp;
			<!-- 
			<select name="selectPage" onChange='formSubmit(-1)'>
				<c:forEach var="item" begin="1" end="${rollPage.pageNum}">
					<option value="<c:out value="${item-1}"/>" <c:if test="${rollPage.pageCur==item-1}">selected="selected"</c:if>>
						<c:out value="${item}" />
					</option>
				</c:forEach>
			</select>
			 -->
		</td>
	</tr>
</table>

