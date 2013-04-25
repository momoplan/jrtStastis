<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ruicai.basis.common.RollPage" %>
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
				document.forms[0].page.value = num;
			}
			document.forms[0].submit();
		}
</script>

<table width="100%" height="28" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr bgcolor="" height=28>
		<td height="30" align="center" class="landing_table_tr">
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
			<a href='javascript:formSubmit(0)'><font color="">[首页]</font>
			</a>&nbsp;&nbsp;<a href='javascript:formSubmit(${rollPage.pageCur-1})'><font
				color="">上一页</font>
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
				color="">下一页</font>
			</a>&nbsp;&nbsp;<a href='javascript:formSubmit(${rollPage.pageNum-1})'><font
				color="">[尾页]</font>
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
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="page_in" size="3"
				value='${rollPage.pageCur}' />
			<a href='javascript:formSubmit(document.forms[0].page_in.value)'><font
				color="">GO</font>
			</a>
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

