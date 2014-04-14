<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.jbpm.api.model.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String processInstanceId = (String)request.getAttribute("id");
	ActivityCoordinates ac = (ActivityCoordinates)request.getAttribute("ac");
	ActivityCoordinates acHistory = null;
	ActivityCoordinates acOrg = null;
	List acArr = (List)request.getAttribute("acs");
	List acArr2 = (List)request.getAttribute("acs2");
	Integer activeCount = (Integer)request.getAttribute("activeCount");
	//判断是否有覆盖的层
	boolean flag = false;
	
	Map orgsMap = (Map)request.getAttribute("orgsMap"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.min.js"></script>
<script>

$( function(){
 // window.parent.frames.location.reload();
<%for(int i=0;i<acArr.size()-1;i++){
	acHistory = (ActivityCoordinates)acArr.get(i);
		%>
		$("#activeNode").after("<div style='position:absolute;border:2px solid green;left:<%=acHistory.getX()%>px;top:<%=acHistory.getY()%>px;width:<%=acHistory.getWidth()%>px;height:<%=acHistory.getHeight()%>px;'><span style='background-color : #ffe98f;color:#000000'>"+<%=acArr2.get(i)%>+"</span></div>");
		<%
}%>
//1.种方法 window.status = "完成";
});
</script>
</head>
<body onload="window.status='完成';">
<img src="jbpm4-evnet-report!drawFlowsheet.do?jbpm4EventReportBean.processInstanceId=<%=processInstanceId %>" style="position:absolute;left:0px;top:0px;">
<div id="activeNode" style="z-index:1;position:absolute;border:2px solid blue;left:<%=ac.getX()%>px;top:<%=ac.getY()%>px;width:<%=ac.getWidth()%>px;height:<%=ac.getHeight()%>px;"><span style='background-color : #ffe98f;color:#000000'><%=activeCount %></span></div>
</body>
</html>