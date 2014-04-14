<%@ page language="java" import="java.util.*,com.sinoframe.common.jbpm4.ProcessBase;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ProcessBase processBase = new ProcessBase();
%>
<script>
$(function(){
   $(".subBar").hide();
})

function submitForm(roleName){alert(roleName);
	$("input[name=xroleName]").val(roleName);
	//document.pagerForm.submit();
	//navTabSearch($("#pagerForm"));
	//$("#pagerForm").submit();
	 $(".subBar button").click();
	
}





</script>
<div class="page">
	<div class="pageHeader">
	<form id="pagerForm"  onsubmit="return navTabSearch(this);" action="jbpm4/jbpm4-en-exam!index.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<!-- <input type="hidden" name="numPerPage" value="${numPerPage}" /> -->
		<input type="hidden" name="orderField" value="" />
		<input type="hidden" name="xroleName" value=""/>
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
					<select name="roleName" onchange="submitForm(this.value)">
					    <option value="英语办公室" <s:if test="roleId==\"8a8a111130b5534e0130b6cd17aa0004\"">selected</s:if>>英语办公室</option>
				    	<option value="各机队" <s:if test="roleId==\"8a8a111130b5534e0130b6cf65ef0005\"">selected</s:if>>各机队</option>
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
			<s:iterator value="taskList" status="st">
			    
				<tr target="sid_sysPer" rel="${id}" >
					<td><input name="ids" value="${id}" type="checkbox"></td>
					<td>${id}</td>
					<td>
	    				${name}<s:property value="%{isRejectMap[#st.index]}" /><s:property value="%{reasonMap[#st.index]}" />  
					</td>
					<td style="text-align: right;">
					   <s:if test="roleId==\"8a8a111130b5534e0130b6cd17aa0004\" && name!=\"分配考试人数\" && name!=\"制定考试计划\" && name!=\"安排考试时间\" ">
						<a href="${task.formResourceName}?taskId=${id}" target="navTabTodo">${name}</a>
						</s:if>
						<s:elseif test="name==\"分配考试人数\" || name==\"制定考试计划\" ">
						<a href="${task.formResourceName}?taskId=${id}" target="navTab" rel="">${name}</a>
						</s:elseif>
						<s:elseif test="name==\"安排考试时间\" || name==\"登记考生资质\"">
						<a href="${task.formResourceName}?taskId=${id}" target="navTabTodo">${name}</a>
						</s:elseif>
						<s:else>
						 <a href="${task.formResourceName}?taskId=${id}" target="navTab" rel="">${name}</a>
						</s:else>
						
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		
		
</div>

    
</div>