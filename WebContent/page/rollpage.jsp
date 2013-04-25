<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.ruicai.basis.common.RollPage"%>
<%
	//@ taglib uri="/WEB-INF/c.tld" prefix="c"
%>
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


<table width="800" border="0" cellpadding="2" cellspacing="1"
	bgcolor="#a0ae88">
	<tr bgColor="#dadfd0">
		<td align="right">
			<input type="hidden" name="page" value='${rollPage.pageCur}'>
			第
			<b>${rollPage.pageCur}</b>/
			<b>${rollPage.pageNum}</b>页&nbsp;共
			<b>${rollPage.rowCount}</b>条记录&nbsp;
			<%
				if (rollPage.getPageCur() > 0) {
			%>
			<a href='javascript:formSubmit(0)'><font color="blue">[首页]</font>
			</a><a href='javascript:formSubmit(${rollPage.pageCur-1})'><font
				color="blue">上一页</font> </a>
			<%
				}
			%>
			<%
				if (rollPage.getPageCur() < 1) {
			%>
			[首页] 上一页
			<%
				}
			%>
			<%
				if (rollPage.getPageCur() + 1 < rollPage.getPageNum()) {
			%>
			<a href='javascript:formSubmit(${rollPage.pageCur+1})'><font
				color="blue">下一页</font> </a><a
				href='javascript:formSubmit(${rollPage.pageNum-1})'><font
				color="blue">[尾页]</font> </a>
			<%
				}
			%>
			<%
				if (rollPage.getPageCur() + 1 >= rollPage.getPageNum()) {
			%>
			下一页 [尾页]
			<%
				}
			%>
			&nbsp;&nbsp;&nbsp;&nbsp; 跳转
			<input type="text" name="page_in" size="3"
				value='${rollPage.pageCur}' />
			<a href='javascript:formSubmit(document.forms[0].page_in.value)'
				class="btnStyle"><font color="blue">GO</font> </a> &nbsp; 每页记录数
			<select name="22">
				<option value="50">
					50
				</option>
				<option value="20">
					20
				</option>
				<option value="10">
					10
				</option>
			</select>
			&nbsp;
		</td>
	</tr>
</table>

