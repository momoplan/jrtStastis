<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>pv count</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

	</head>

	<body>
		<table width="80%" border="1">
			<tr>
				<td>编号</td>
				<td>时间</td>
				<td>类型</td>
				<td>数量</td>
				<td>渠道编号</td>
			</tr>
			<c:forEach var="list" items="${list}" varStatus="it">
				<tr>
					<td>${list.id}</td>
					<td>${list.tsj}</td>
					<td>${list.type}</td>
					<td>${list.num}</td>
					<td>${list.channelid}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
