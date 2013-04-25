<%@ page language="java" contentType="text/html; charset=GB18030"	pageEncoding="GB18030"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	String uploadPath = "D:\\temp";
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	if (isMultipart == true) {
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			//�õ����е��ļ�      
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
			    //���δ���ÿ���ļ�     
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				//����ļ���������·��        
				if (fileName != null) {
					File fullFile = new File(item.getName());
					File savedFile = new File(uploadPath, fullFile.getName());
					item.write(savedFile);
				}
			}
			out.print("upload succeed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else {
		out.println("the enctype must be multipart/form-data");
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>File upload</title>
	</head>
	<body>&nbsp;</body>
</html>