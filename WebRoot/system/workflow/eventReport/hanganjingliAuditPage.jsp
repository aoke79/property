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
    <legend>航安部高级经理审核</legend>
    <form action="jbpm4/jbpm4-evnet-report!dealAudit.do" method="post">
      <input type="hidden" name="jbpm4EventReportBean.taskId" value="${param.id}">
      申请人：<%=taskService.getVariable(taskId, "userName") %><br/>
  报告类型：<%=taskService.getVariable(taskId, "eventReportType") %><br/>
    报告内容：<%=taskService.getVariable(taskId, "content") %><br/>
    <input name="jbpm4EventReportBean.result" type="submit" value="启动调查"/>
    <input name="jbpm4EventReportBean.result" type="submit" value="关闭"/>
    <br/>驳回理由:<textarea name="jbpm4EventReportBean.reason"></textarea><br/>
    <input name="jbpm4EventReportBean.result" type="submit" value="驳回上报"/>
    <input name="jbpm4EventReportBean.result" type="submit" value="驳回提交"/>
    </form>
  </fieldset>

</body>
</html>