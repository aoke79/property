<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jbpm.api.model.*" %>
<%@page import="java.util.List" %>
<%
	String processInstanceId = (String)request.getAttribute("id");
	ActivityCoordinates ac = (ActivityCoordinates)request.getAttribute("ac");
	ActivityCoordinates acHistory = null;
	List acArr = (List)request.getAttribute("acs");
	List acArr2 = (List)request.getAttribute("acs2");
	Integer activeCount = (Integer)request.getAttribute("activeCount");
	//判断是否有覆盖的层
	boolean flag = false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script>

$( function(){
<%for(int i=0;i<acArr.size()-1;i++){
	acHistory = (ActivityCoordinates)acArr.get(i);
	
		%>
		$("#activeNode").after("<div style='position:absolute;border:2px solid green;left:<%=acHistory.getX()%>px;top:<%=acHistory.getY()%>px;width:<%=acHistory.getWidth()%>px;height:<%=acHistory.getHeight()%>px;'><span style='background-color : #ffe98f;color:#000000'>"+<%=acArr2.get(i)%>+"</span></div>");
		<%
	
}%>
});

</script>
</head>
<body>

<img src="jbpm4/jbpm4-en-exam!drawFlowsheet.do?jbpm4EnExamBean.processInstanceId=<%=processInstanceId %>" style="position:absolute;left:0px;top:0px;" id="imgeee">
 <script type="text/javascript">
                            //  var hh=$("#imgeee").attr("src");
                             // hh=encodeURI(encodeURI(hh));
                             // $("#imgeee").attr("src",hh);
                             </script>   
<div id="activeNode" style="z-index:1;position:absolute;border:2px solid blue;left:<%=ac.getX()%>px;top:<%=ac.getY()%>px;width:<%=ac.getWidth()%>px;height:<%=ac.getHeight()%>px;" ><span style='background-color : #ffe98f;color:#000000'><%=activeCount %></span></div>
</body>
</html>