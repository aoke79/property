<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"%><%
// 导出word  application/msword    导出Excel   application/vnd.ms-excel 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imageID=request.getParameter("imageID");
String exportFormat=request.getParameter("exportFormat");
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
   <center><br><img src="<%=basePath%>/bioscenter/fcexporter/imageShow.jsp?imageID=<%=imageID%>&exportFormat=<%=exportFormat%>"></center>
  </body>
</html>
