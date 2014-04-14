<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page">
	<div class="pageHeader">		
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-menu/sys-user-custom-menu!list.do" method="post">
			<input type="hidden" name="pageNum" value="1"/>
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="" />
			<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<s:property value="getText('menuName')"/>：<input type="text" name="menuIds" value="${query.like_sysMenu.name}" />
						</td>
						<td>
							<s:property value="getText('userName')"/>：<input type="text" name="userIds" value="${query.like_cmUser.name}" />
						</td>
						<!-- <td>
							状态：<input type="text" name="query.like_state" value="${query.eq_state}"
							            alt="只能输入1或0" />
						</td> -->
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
				<li><a class="add" href="sys-menu/sys-user-custom-menu!add.do" rel="add" messageHint="<s:property value='getText("addLeaveTips")' />" target="navTab"><span><s:property value="getText('add')"/></span></a></li>
				<li>
					<a title="<s:property value='getText("batchDeleteTips")'/>" target="removeSelected" href="sys-menu/sys-user-custom-menu!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
				</li>
				<!--<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>-->
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="150" orderField="sysMenu.name"><s:property value="getText('menuName')"/></th>
					<th width="150" orderField="cmUser.name"><s:property value="getText('userName')"/></th>
					<th width="150" orderField="state"><s:property value="getText('status')"/></th>
					<th width="150"><s:property value="getText('oper')"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${customList}" var="sysUserCustomMenu">
				<tr target="sid_user" rel="${sysUserCustomMenu.id}">
					<td><input name="ids" value="${sysUserCustomMenu.id}" type="checkbox"></td>
					<td><c:out value="${sysUserCustomMenu.sysMenu.name}"></c:out></td>
					<td><c:out value="${sysUserCustomMenu.cmUser.name}"></c:out></td>
					<td>
						<c:choose>
				    		<c:when test="${sysUserCustomMenu.state == 0}">
				    			<s:property value="getText('unavailable')"/>
				    		</c:when>
				    		<c:when test="${sysUserCustomMenu.state == 1}">
				    			<s:property value="getText('available')"/>
				    		</c:when>
				    	</c:choose>
					</td>
					<td style="text-align: right;">
						<a class="btnDel" href="sys-menu/sys-user-custom-menu!delete.do?method=remove&sysUserCustomMenu.id=${sysUserCustomMenu.id}" target="navTabTodo" title="<s:property value="getText('deleteTips')"/>"></a>
						<a class="btnEdit" target="navTab" title="<s:property value='getText("modify")' />" rel="edit" messageHint="<s:property value='getText("editLeaveTips")' />" href="sys-menu/sys-user-custom-menu!edit.do?sysUserCustomMenu.id=${sysUserCustomMenu.id}"></a>
					</td>
				</tr>
			</c:forEach>
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