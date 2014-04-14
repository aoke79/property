<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <fieldset>
    <legend>事件报告</legend>
    <form id="pagerForm"  onsubmit="return navTabSearch(this);" action="jbpm4/jbpm4-evnet-report!jicengSubmitReport.do" method="post">
      <input type="hidden" name="jbpm4EventReportBean.taskId" value="${param.id}">
      报告人：<input type="text" name="jbpm4EventReportBean.userName" value="${sessionScope['userName']}"/><br/>
      报告类型：<select name="jbpm4EventReportBean.eventReportType">
      			<option value="yijiReport">一级事件报告</option>
      			<option value="hanganReport">航安事件报告</option>
      		 </select><br/>
     报告内容：<textarea name="jbpm4EventReportBean.centent"></textarea><br/>
    <input type="submit" value="提交事件报告"/>
    </form>
  </fieldset>

</body>
</html>