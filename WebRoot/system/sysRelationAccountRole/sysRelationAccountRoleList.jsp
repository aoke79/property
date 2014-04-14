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
			action="sys-relation-account-role/sys-relation-account-role!search.do"
			method="post">
			<input type="hidden" name="pageNum"
				value="${sysPageInfo.currentPage}" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock"
				value="${orderBlock}" />
			<input type="hidden" id="orderMethod" name="orderMethod"
				value="${orderMethod}" />
			<input type="hidden" id="isOrder" name="isOrder" value="" />
			<input type="hidden" id="orderAsc" name="sysOrderByInfo.orderAsc"
				value="${sysOrderByInfo.orderAsc}" />
			<input type="hidden" name="userId" value="${userId}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<s:if
							test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
							<td>
								<s:property value="getText('roleName')" />
								：
								<input type="text" name="query.eroleName"
									value="${query.eroleName}" />
							</td>
						</s:if>
						<s:else>
							<td>
								<s:property value="getText('roleName')" />
								：
								<input type="text" name="query.roleName"
									value="${query.roleName}" />
							</td>
						</s:else>
						<td>
							<s:property value="getText('description')" />
							：
							<input type="text" name="query.description"
								value="${query.description}" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">
										<s:property value="getText('search')" />
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="reset">
										<s:property value="getText('reset')" />
									</button>
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
					<s:if
						test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a rel="add" class="add"
							href="sys-relation-account-role/sys-relation-account-role!goView.do?userId=${userId}"
							target="navTab"
							messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property
									value="getText('assignRole')" />
						</span> </a>
					</s:if>
				</li>
				<li>
					<!-- 
					<a class="delete" href="sys-role/sys-relation-account-role!delSysRelationAccountRole.do?ruser.userId={sid_user}" target="navTabTodo" title="确定要删除吗?"><span>批量删除</span></a>
				  -->
					<s:if
						test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a title="<s:property value="getText('batchDeleteTips')"/>"
							target="removeSelected"
							href="sys-relation-account-role/sys-relation-account-role!multipleDelete.do"
							class="delete"><span><s:property
									value="getText('batchDelete')" />
						</span>
						</a>
					</s:if>
				</li>
				<li class="line">
					line
				</li>
				<li>
					<!-- 
					<a class="icon" href="testimport.html" target="dialog" width="300"
						height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span>
					</a>
				 -->
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
						<s:property value="getText('userName')" />
					</th>
					<s:if
						test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
						<th width="200" orderField="sysRole.eroleName">
							<s:property value="getText('roleName')" />
						</th>
					</s:if>
					<s:else>
						<th width="200" orderField="sysRole.roleName">
							<s:property value="getText('roleName')" />
						</th>
					</s:else>
					<th width="400" orderField="description">
						<s:property value="getText('description')" />
					</th>
					<th width="200" orderField="flag">
						<s:property value="getText('validity')" />
					</th>
					<th width="70">
						<s:property value="getText('operate')" />
					</th>
				</tr>
			</thead>
			<tbody>

				<s:if test="sysRelationAccountRoleList.size>0">
					<s:iterator value="sysRelationAccountRoleList">
						<tr target="sid_user"
							rel="${accountRoleId.userId}&ruser.roleId=${accountRoleId.roleId}">
							<td>
								<input name="ids"
									value="${accountRoleId.userId}&ruser.roleId=${accountRoleId.roleId}"
									type="checkbox">
							</td>
							<td>
								${cmUser.name}
							</td>
							<s:if
								test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
								<td>
									${sysRole.eroleName}
								</td>
								
							</s:if>
							<s:else>
								<td>
									${sysRole.roleName}
								</td>
							</s:else>
							<td>
								${description}
							</td>
							<td>
								<s:if test="flag==\"0\"">
									<s:property value="getText('valid')" />
								</s:if>
								<s:else>
									<s:property value="getText('invalid')" />
								</s:else>
							</td>
							<td style="text-align: right;">
								<s:if
									test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
									<a class="btnDel"
										href="sys-relation-account-role/sys-relation-account-role!delSysRelationAccountRole.do?ruser.userId=${accountRoleId.userId}&ruser.roleId=${accountRoleId.roleId}&pageNum=${paging.curPage}"
										target="navTabTodo"
										title="<s:property value="getText('delete')"/>"></a>
								</s:if><%--
								<s:if
									test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
									<a class="btnEdit" rel="edit"
										href="sys-relation-account-role/sys-relation-account-role!toEditPage.do?ruser.userId=${accountRoleId.userId}&ruser.roleId=${accountRoleId.roleId}&pageNum=${paging.curPage}"
										target="navTab"
										title="<s:property value="getText('update')"/>"
										messageHint="<s:property value='getText("editLeaveTips")' />"></a>
								</s:if>
							--%></td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td colspan="4">
							<s:property value="getText('userNoRole')" />
						</td>
					</tr>

				</s:else>
			</tbody>
		</table>

		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')" />
				</span>
				<s:select list='#{"20":"20","10":"10","5":"5"}' name="numPerPage"
					cssClass="paginationStyle"
					onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span><s:property value="getText('column')" />
					<s:property value="getText('total')" />
					<c:out value='${sysPageInfo.maxCount}' />
					<s:property value="getText('column')" />
				</span>
			</div>
			<div class="pagination" targetType="navTab"
				totalCount="${sysPageInfo.maxCount}" numPerPage="${numPerPage}"
				pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
	</div>
</div>
