<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.*" %>
<%@page import="org.jbpm.api.ProcessDefinition" %>
<%@page import="org.jbpm.api.ProcessInstance" %>
<%@page import="org.jbpm.api.task.Task" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String userName = (String) session.getAttribute("userName");

List<ProcessDefinition> pdList = (List<ProcessDefinition>)request.getAttribute("pdList");
List<ProcessInstance> piList = (List<ProcessInstance>)request.getAttribute("piList");
List<Task> taskList = (List<Task>)request.getAttribute("taskList");
%>
<script>
function submitForm(roleName){alert(roleName);
	$("input[name=pageNum]").val(roleName);
	//document.pagerForm.submit();
	//navTabSearch($("#pagerForm"));
	//$("#pagerForm").submit();
	
}
</script>
<div class="page">
	<div class="pageHeader">
	<form id="pagerForm"  onsubmit="return navTabSearch(this);" action="jbpm4/jbpm4-evnet-report!index.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<!-- <input type="hidden" name="numPerPage" value="${numPerPage}" /> -->
		<input type="hidden" name="orderField" value="" />
		<input type="hidden" name="roleName" value=""/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						
					</td>
				</tr>
			</table>
			<!-- <div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
			</div>-->
		</div>
		</form>
    </div>

<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				
				<!-- <li><a class="edit" href="sys-permission/sys-permission!toEditPage.do?sysPermission.id={sid_sysPer}" target="navTab"><span>修改</span></a></li> 
				<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>-->
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200">实例ID</th>
					<th width="200">活动</th>
					<th width="200">状态</th>
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
			<%
			for (ProcessInstance pi : piList) {
			%>
				<tr target="sid_sysPer" rel="<%=pi.getId() %>" >
					<td><input name="ids" value="<%=pi.getId() %>" type="checkbox"></td>
					<td><c:out value="<%=pi.getId() %>"></c:out></td>
					<td>
						<%=pi.findActiveActivityNames() %>
					</td>
					<td><c:out value="<%=pi.getState() %>"></c:out></td>
					
					
					<td style="text-align: right;">
						<a href="jbpm4/jbpm4-evnet-report!toframeView.do?jbpm4EventReportBean.processInstanceId=<%=pi.getId() %>"  target="navTab" rel="viewpic" title="流程图走势">流程图走势</a>
					</td>
				</tr>
			<%
				}
			%>
			</tbody>
		</table>
		
		
</div>
    
</div>

