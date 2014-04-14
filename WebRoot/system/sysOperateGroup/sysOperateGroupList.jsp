<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：操作组列表页面，增、删、改、查操作组
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-operate-group/sys-operate-group!search.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						操作组名称：<input type="text" name="query.like_name" value="${query.like_name}" />
					</td>
					<td>
						操作组描述：<input type="text" name="query.like_operateDesc" value="${query.like_operateDesc}"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group/sys-operate-group!search.do"}.isEmpty()'> </s:if>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						
					</li>
					<li>
						  <div class="button">
						    <div class="buttonContent">
						     <button type="reset">重置</button>
						    </div>
						  </div>
						</li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<!-- <form action="sys-system/sys-system!multipleDelete.do" method="post" id="delForm"> -->
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group/sys-operate-group!add.do"}.isEmpty()'> 
						<a class="add" href="sys-operate-group/sys-operate-group!add.do" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span>添加</span></a>
					</s:if>
				</li>
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group/sys-operate-group!multipleDelete.do"}.isEmpty()'>
						<a title="确实要删除这些记录吗?" target="removeSelected" href="sys-operate-group/sys-operate-group!multipleDelete.do" class="delete"><span>批量删除</span></a>
					</s:if> 
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200" orderField="name" class="asc">操作组名称</th>
					<th width="200">有效性</th>
					<th width="200">操作组描述</th>
					<th width="200">备注</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${operateGroupList}" var="sysOperateGroup">
				<tr target="sid_user" rel="${sysOperateGroup.id}" >
					<td><input name="ids" value="${sysOperateGroup.id}" type="checkbox"></td>
					<td><c:out value="${sysOperateGroup.name}"></c:out></td>
					<td>
						<!-- <c:out value="${sysOperateGroup.state}"></c:out> -->
						<s:if test='#attr.sysOperateGroup.state.equals("1")'>
	  						有效
	  					</s:if>
	  					<s:elseif test='#attr.sysOperateGroup.state.equals("0")'>
	  						无效
	  					</s:elseif>
					</td>
					<td><c:out value="${sysOperateGroup.operateDesc}"></c:out></td>
					<td><c:out value="${sysOperateGroup.opegComment}"></c:out></td>
					<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group/sys-operate-group!delete.do"}.isEmpty()'>
							<a class="btnDel" href="sys-operate-group/sys-operate-group!delete.do?sysOperateGroup.id=${sysOperateGroup.id}" target="navTabTodo" title="确定要删除吗?"></a>
						</s:if> 
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group/sys-operate-group!edit.do"}.isEmpty()'>
							<a class="btnEdit" href="sys-operate-group/sys-operate-group!edit.do?sysOperateGroup.id=${sysOperateGroup.id}" target="navTab" title="修改" messageHint="<s:property value='getText("editLeaveTips")' />"></a>
						</s:if> 
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group-relation/sys-operate-group-relation!list.do"}.isEmpty()'>
							<a class="btnLook" rel="operateGroupRelationManage" href="sys-operate-group-relation/sys-operate-group-relation!list.do?operateGroupId=${sysOperateGroup.id }" target="navTab" title="操作组关系管理"></a>
						</s:if> 
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span>条，共<c:out value='${sysPageInfo.maxCount}'/>条</span>
			</div>
			<div class="pagination" targetType="navTab"
			     totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
			     numPerPage="<c:out value='${numPerPage}'/>"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
		<!-- </form> -->
	</div>
</div>