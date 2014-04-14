<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sec-bphase/sec-bphase!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<s:property value="getText('description')"/>：<input type="text" name="query.like_phdesc" value="${query.like_phdesc}" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/></button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="sec-bphase/sec-bphase!add.do" target="navTab" rel="<s:property value='getText("add")'/>" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('add')"/></span></a></li>
				<li>
					<a title="<s:property value='getText("batchDeleteTips")'/>" target="removeSelected" href="sec-bphase/sec-bphase!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="300" orderField="phdesc"><s:property value="getText('description')"/></th>
					<th width="80"><s:property value="getText('operate')"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${phaseList}" var="bphase">
				<tr target="sid_user" rel="${bphase.id}" >
					<td><input name="ids" value="${bphase.id}" type="checkbox"></td>
					<td>${bphase.phdesc}</td>
					<td style="text-align: right;">
						<a class="btnDel" href="sec-bphase/sec-bphase!delete.do?method=remove&secBphase.id=${bphase.id}" target="navTabTodo" title="<s:property value='getText("deleteTips")'/>"></a>
						<a class="btnEdit" href="sec-bphase/sec-bphase!edit.do?secBphase.id=${bphase.id}" target="navTab" messageHint="<s:property value='getText("editLeaveTips")' />" title="<s:property value='getText("modify")'/>"></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')"/></span>
				<s:select list='#{"15":"15","10":"10","5":"5"}'
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