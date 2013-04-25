<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%//@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%java.lang.String path = request.getContextPath();%>
<script language="javascript">
		function formSubmit(num){
			if(num==-100000){
				document.forms[0].page.value = document.forms[0].selectPage.value;
			}else{
				if(num ><c:out value="${rollPage.pageNum-1}"/>){
					num=<c:out value="${rollPage.pageNum-1}"/>;
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

<table width="100%" height="28" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr bgcolor="" height=28>
		<td height="30" align="right">
			<input type="hidden" name="page" value='<c:out value="${rollPage.pageCur}" />'>
			共<font color="red"><b><c:out value="${rollPage.rowCount}" /></b></font>条记录&nbsp;分<FONT color="red"><B><c:out value="${rollPage.pageNum}" /></B></FONT>页显示&nbsp;当前为第<FONT color="red"><B><c:out value="${rollPage.pageCur+1}" /></B></FONT>页 &nbsp;
			<c:if test="${rollPage.pageCur>0}">
				<img height="13" src="<%=path%>/images/jian1.gif" width="13" />
				<a href='javascript:formSubmit(0)'><font color="blue">[首页]</font></a>
				<img height="13" src="<%=path%>/images/jian1.gif" width="13" />
				<a href='javascript:formSubmit(<c:out value="${rollPage.pageCur-1}"/>)'><font color="blue">上一页</font></a>
			</c:if>
			<c:if test="${rollPage.pageCur<1}">
				<img height="13" src="<%=path%>/images/jian1.gif" width="13" />
							[首页]
							<img height="13" src="<%=path%>/images/jian1.gif" width="13" />
							上一页
						</c:if>
			<c:if test="${rollPage.pageCur+1<rollPage.pageNum}">
				<img height=13 src="<%=path%>/images/jian2.gif" width="13" />
				<a href='javascript:formSubmit(<c:out value="${rollPage.pageCur+1}"/>)'><font color="blue">下一页</font></a>
				<img height=13 src="<%=path%>/images/jian2.gif" width="13" />
				<a href='javascript:formSubmit(<c:out value="${rollPage.pageNum-1}"/>)'><font color="blue">[尾页]</font></a>
			</c:if>
			<c:if test="${rollPage.pageCur+1>=rollPage.pageNum}">
				<img height=13 src="<%=path%>/images/jian2.gif" width="13" />
							下一页
							<img height=13 src="<%=path%>/images/jian2.gif" width="13" />
							[尾页]
						</c:if>
			<input type="text" name="page_in" size="3" value='<c:out value="${rollPage.pageCur}" />' />
			&nbsp&nbsp<a href='javascript:formSubmit(document.forms[0].page_in.value)'>页&nbsp<font color="blue">跳转</font></a>
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

