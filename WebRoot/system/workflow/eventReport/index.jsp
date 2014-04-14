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
$(function(){
   $(".subBar").hide();
})

function submitForm(roleName){alert(roleName);
	$("input[name=roleName]").val(roleName);
	//document.pagerForm.submit();
	//navTabSearch($("#pagerForm"));
	//$("#pagerForm").submit();
	 $(".subBar button").click();
	
}
</script>
<div class="page">
	<div class="pageHeader">
	<form id="pagerForm"  onsubmit="return navTabSearch(this);" action="jbpm4/jbpm4-evnet-report!index.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<!-- <input type="hidden" name="numPerPage" value="${numPerPage}" /> -->
		<input type="hidden" name="orderField" value="" />
		<input type="hidden" name="roleName" value=""/>
		<input type="hidden" name="flag" value="1"/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
				   
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li> 
					
				</ul>
			</div>
		</div>
		</form>
    </div>

<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">	
				<li>当前角色:&nbsp;
					<select name="query.eq_sysOperate" onchange="submitForm(this.value)">
				    	<c:forEach items="${roleNamesArr}" var="name" >
				    		<c:if test="${name==roleName}">
					    		<option value=<c:out value="${name}" /> selected>
					    			<c:out value="${name}" />
					    		</option>
				    		</c:if>
				    		<c:if test="${name!=roleName}">
				    			<option value=<c:out value="${name}" />>
				    				<c:out value="${name}" />
				    			</option>
				    		</c:if>
				    	</c:forEach>
			    	</select>
				</li>
				<!-- <li><a class="edit" href="sys-permission/sys-permission!toEditPage.do?sysPermission.id={sid_sysPer}" target="navTab"><span>修改</span></a></li> 
				<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>-->
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200">任务id</th>
					<th width="200">名称</th>
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${taskList}" var="task" >
				<tr target="sid_sysPer" rel="${task.id}" >
					<td><input name="ids" value="${task.id}" type="checkbox"></td>
					<td><c:out value="${task.id}"></c:out></td>
					<td>
	    				<c:out value="${task.name}"></c:out>
					</td>
					
					
					<td style="text-align: right;">
						<a href="${task.formResourceName}?id=${task.id}" target="navTab" rel="deal" title="处理">处理</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		
</div>

    
</div>