<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="org.jbpm.api.*,org.jbpm.api.task.*" %> 
    <%
	ProcessEngine processEngine = Configuration.getProcessEngine();
	TaskService taskService = processEngine.getTaskService();
	String taskId = request.getParameter("id");
	//Task task = taskService.getTask(taskId);
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <fieldset>
    <legend>事件报告</legend>
    <% if (taskService.getVariable(taskId,"reason")!=null){ %>
    驳回理由：<%=taskService.getVariable(taskId,"reason") %><br/>
    <%}%>
    <form action="jbpm4/jbpm4-evnet-report!hanganSubmitReport.do" method="post">
      <input type="hidden" name="jbpm4EventReportBean.taskId" value="${param.id}">
      报告人：<input type="text" name="jbpm4EventReportBean.userName" value="${sessionScope['userName']}"/><br/>
      报告类型：航安事件报告<input name="jbpm4EventReportBean.eventReportType" type="hidden" value="hanganReport"><br/>
    报告内容：<textarea name="jbpm4EventReportBean.centent"></textarea><br/>
    <input type="submit" value="提交事件报告"/>
    </form>
  </fieldset>

</body>
</html>