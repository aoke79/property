<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：角色操作组关系列表页面，增、删、改、查角色操作组关系，为角色分配操作组
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="sys-role-operategroup-relation/sys-role-operategroup-relation!search.do"
			method="post">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" name="orderField" value="" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							操作组名称：
							<input type="text" name="query.eq_sysRole" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">
										检索
									</button>
								</div>
							</div>
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
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-role-operategroup-relation/sys-role-operategroup-relation!toAddPage.do"}.isEmpty()'>
						<a class="add" rel="roleOperategroupAdd"
							href="sys-role-operategroup-relation/sys-role-operategroup-relation!toAddPage.do?roleId=${roleId }"
							target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span>为角色分配操作组</span>
						</a>
					</s:if> 
				</li>
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-role-operategroup-relation/sys-role-operategroup-relation!multipleDelete.do"}.isEmpty()'>
						<a title="确实要删除这些记录吗?" target="removeSelected"
							href="sys-role-operategroup-relation/sys-role-operategroup-relation!multipleDelete.do" class="delete"><span>批量删除</span>
						</a>
					</s:if> 
				</li>
				<li class="line">
					line
				</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30">
						<input type="checkbox" group="ids" class="checkboxCtrl">
					</th>
					<th width="200">
						角色名称
					</th>
					<th width="200">
						操作组名称
					</th>
					<th width="200">
						有效性
					</th>
					<th width="200">
						备注
					</th>
					<th width="70">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sysRoleOperategroupRelationList}" var="sysRoleOperategroupRelation">
					<tr target="sid_sysPer" rel="${sysRoleOperategroupRelation.id.operateGroupid}&sysRelationRoleOperateId.operateId=${sysRoleOperategroupRelation.id.roleId}">
						<td>
							<input name="ids" value="${sysRoleOperategroupRelation.id.operateGroupid}&sysRelationRoleOperateId.operateId=${sysRoleOperategroupRelation.id.roleId}"
								type="checkbox">
						</td>
						<td>
							<c:out value="${sysRoleOperategroupRelation.sysRole.roleName}"></c:out>
						</td>
						<td>
							<c:out value="${sysRoleOperategroupRelation.sysOperateGroup.name}"></c:out>
						</td>
						<td>
							<c:out value="${sysRoleOperategroupRelation.state}"></c:out>
						</td>
						<td>
							<c:out value="${sysRoleOperategroupRelation.rorComment}"></c:out>
						</td>
						<td style="text-align: right;">
							<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-role-operategroup-relation/sys-role-operategroup-relation!delPer.do"}.isEmpty()'>
								<a class="btnDel"
									href="sys-role-operategroup-relation/sys-role-operategroup-relation!delPer.do?sysRoleOperategroupRelation.id.roleId=${sysRoleOperategroupRelation.id.roleId}&sysRoleOperategroupRelation.id.operateGroupid=${sysRoleOperategroupRelation.id.operateGroupid}"
									target="navTabTodo" title="确定要删除吗?"></a>
							</s:if> 
							<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-role-operategroup-relation/sys-role-operategroup-relation!toEditPage.do"}.isEmpty()'>
								<a class="btnEdit"
									href="sys-role-operategroup-relation/sys-role-operategroup-relation!toEditPage.do?sysRoleOperategroupRelation.id.roleId=${sysRoleOperategroupRelation.id.roleId}&sysRoleOperategroupRelation.id.operateGroupid=${sysRoleOperategroupRelation.id.operateGroupid}"
									target="navTab" title="修改" messageHint="<s:property value='getText("editLeaveTips")' />"></a>
							</s:if> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<s:select list='#{"20":"20","10":"10","5":"5"}' name="numPerPage"
					cssClass="paginationStyle"
					onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span>条，共<c:out value='${sysPageInfo.maxCount}' />条</span>
			</div>
			<div class="pagination" targetType="navTab"
				totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
				numPerPage="<c:out value='${numPerPage}'/>" pageNumShown="5"
				currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
	</div>
</div>