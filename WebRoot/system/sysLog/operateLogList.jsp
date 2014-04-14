<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="operateLogList" class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ah-login/anhua-log!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						用户登录名：<input type="text" name="query.like_username" alt="请输入用户登录名称" value="${query.like_username}" />
					</td>
					<td>
						登录系统名：<input type="text" name="query.like_sysname"  alt="请输入登录系统名称" value="${query.like_sysname}" />
					</td>
					<td>
						开始时间：<input type="text" name="query.dtgteq_optime" class="date" pattern="yyyy-MM-dd"  value="${query.dtgteq_optime}"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/></button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th  orderField="anUser.username">用户登录名</th>
					<th  orderField="anUser.name">用户姓名</th>
					<th  orderField="anUser.unitname">公司名称</th>
					<th  orderField="anUser.deptname">部门名称</th>
					<th  orderField="micip">IP地址</th>
					<th  orderField="ahSystem.sysname">登录系统名称</th>
					<th  orderField="optime">操作时间</th>
					<th  orderField="opname">操作名称</th>
				</tr>
			</thead>
			<tbody>
			
			<s:iterator value="ahLogdateList" >
			  <tr>
			     <td><s:property value="anUser.username"/></td>
					 <td><s:property value="anUser.name"/></td>
					  <td><s:property value="anUser.unitname"/></td>
					   <td><s:property value="anUser.deptname"/></td>
					    <td><s:property value="micip"/></td>
					     <td><s:property value="ahSystem.sysname"/></td>
					      <td><s:property value="optime"/></td>
					       <td><s:property value="opname"/></td>
			  
			  </tr>
			</s:iterator>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')"/></span>
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span><s:property value="getText('column')"/><s:property value="getText('total')"/><c:out value='${sysPageInfo.maxCount}'/><s:property value="getText('column')"/></span>
			</div>
			<div class="pagination" targetType="navTab"
			     totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
			     numPerPage="<c:out value='${numPerPage}'/>"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
	</div>
	
</div>
