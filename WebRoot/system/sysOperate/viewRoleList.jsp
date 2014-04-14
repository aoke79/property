<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page" id="viewRoleList">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-operate/sys-operate!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td class="seTitle">角色名称</td>
					<td class="seBody">
						<input size="30" name="query.like_roleName"
							value="${query.like_roleName}" />
					</td>
					<td class="seTitle">角色描述</td>
					<td class="seBody" id="searchType">
						<input size="30" name="query.like_description"
							value="${query.like_description}" />
					</td>
					<td class="seZone">
						<div class="settingP">
						<div class="buttonActive rightExpand"><div class="buttonContent"><button type="submit" cape="search">检索</button></div></div>
						<div class="buttonActive rightExpand"><div class="buttonContent"><button type="button" cape="reset">重置</button></div></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<table class="table" layouth="93">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>角色描述</th>
					<th width="20">有效性</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="relationList">
				<tr>
					<td>${sysRole.roleName}</td>
					<td>${sysRole.description}</td>
					<td>
						<s:if test='sysRole.flag.equals("0")'>可用</s:if>
						<s:elseif test='sysRole.flag.equals("1")'>不可用</s:elseif>
					</td>
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
<script>
	$(function(){
		//重置
		$("#viewRoleList").find("button[cape=reset]").click(function(){
			$(".searchContent").find("input").val("");
			$(".searchContent").find("select").val("");
		})
			
	})
</script>