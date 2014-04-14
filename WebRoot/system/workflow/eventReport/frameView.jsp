<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String processInstanceId = (String)request.getAttribute("id");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.frameClass {
  width: 100%;
  height: 100%;
}
</style>
<script>
function doNoThing(){}
$(document).ready(function(){
	var id = $("#processInstanceId").val();
	$("#frameId").attr("src","jbpm4/jbpm4-evnet-report!viewFlowsheet.do?jbpm4EventReportBean.processInstanceId="+id);
	//setTimeout("frameId.location.reload()",500);
});
</script>
</head>
<body>
<input type="hidden" id="processInstanceId" value="<%=processInstanceId %>"/>
<Iframe id="frameId" class="frameClass"  scrolling="auto" frameborder="0"></iframe> 
</body>
</html>