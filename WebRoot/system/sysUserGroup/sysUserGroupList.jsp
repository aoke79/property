<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<!--form id="pagerForm" method="post" action="sys-org-code-mapping/sys-org-code-mapping!search.do"> 
    <input type="hidden" name="sysOrgCodeMapping.name" value="${sysOrganization.name}" />
    <input type="hidden" name="sysOrganization.description"  value="${sysOrganization.description}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
	<input type="hidden" name="orderField" value="${param.orderField }" />
	<input type="hidden" name="sysOrganization.status" value="active" />
</form-->

<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="sys-user-group/sys-user-group!listUserGroup.do" method="post">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage"
				value="${sysPageInfo.pageSize}" />
			<input type="hidden" id="orderField" name="orderField" value="" />
			<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							用户组名称：
							<input type="text" name="query.like_GName"
								value="${query.like_GName}" />
						</td>
						<td>
							用户组描述：
							<input type="text" name="query.like_GDesc"
								value="${query.like_GDesc}" />
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
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="reset">
										重置
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
						test='!#session.sysOperateList.{?#this.operateAction=="sys-user-group/sys-user-group!toAddPage.do"}.isEmpty()'>
						<a class="add"
							href="sys-user-group/sys-user-group!toAddPage.do?numPerPage=${numPerPage}"
							target="navTab"><span>添加</span> </a>
					</s:if>

				</li>
				<li>
					<s:if
						test='!#session.sysOperateList.{?#this.operateAction=="sys-user-group/sys-user-group!deleteGroups.do"}.isEmpty()'>
						<a title="确实要删除这些记录吗?" target="removeSelected"
							href="sys-user-group/sys-user-group!deleteGroups.do?pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}"
							class="delete"><span>批量删除</span> </a>
					</s:if>
				</li>
				<li class="line">
					line
				</li><!--
				<li>
					<a class="icon" href="testimport.html" target="dialog" width="300"
						height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span>
					</a>
				</li>
			--></ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30">
						<input type="checkbox" group="ids" class="checkboxCtrl">
					</th>
					<th width="150" orderField="GName">
						用户组名
					</th>
					<th width="400" orderField="GDesc">
						描述
					</th>
					<th width="150" orderField="state">
						用户组状态
					</th>
					<th width="130px">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listSysUserGroup" var="org" status="sta">
					<tr target="sid_user" rel="${id}">
						<td>
							<input type="checkbox" name="ids" class="checkboxCtrl"
								value="${id}">
						</td>
						<td>
							${GName}
						</td>
						<td>
							${GDesc}
						</td>
						<td>
							<s:if test='#org.state.equals("1")'>
							有效
						</s:if>
							<s:elseif test='#org.state.equals("0")'>
							无效
						</s:elseif>
						</td>
						<td style="text-align: right;">
							<s:if
								test='!#session.sysOperateList.{?#this.operateAction=="sys-user-group/sys-user-group!delUserGroup.do"}.isEmpty()'>
								<a class="btnDel"
									href="sys-user-group/sys-user-group!delUserGroup.do?method=remove&sysUserGroup.id=${id}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}"
									target="navTabTodo" title="确定要删除吗?"></a>
							</s:if>
							<s:if
								test='!#session.sysOperateList.{?#this.operateAction=="sys-user-group/sys-user-group!toEditPage.do"}.isEmpty()'>
								<a class="btnEdit"
									href="sys-user-group/sys-user-group!toEditPage.do?method=remove&sysUserGroup.id=${id}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}"
									target="navTab" title="修改"></a>
							</s:if>
							<s:if
								test='!#session.sysOperateList.{?#this.operateAction=="sys-user-group/sys-user-group-relation!listByGroup.do"}.isEmpty()'>
								<a class="btnLook"
									href="sys-user-group/sys-user-group-relation!listByGroup.do?sysUserGroup.id=${id}"
									target="navTab" title="查看用户" rel="usersManager"></a>
							</s:if>
							<s:if
								test='!#session.sysOperateList.{?#this.operateAction=="sys-user-group/sys-role-user-group-relation!listByUserGroup.do"}.isEmpty()'>
								<a class="btnLook"
									href="sys-user-group/sys-role-user-group-relation!listByUserGroup.do?sysUserGroup.id=${id}"
									target="navTab" title="查看角色" rel="userRoleManager"></a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<!--<select class="combox" name="numPerPage" change="navTabPageBreak"
					param="numPerPage">
					<option value="20">
						20
					</option>
					<option value="50">
						50
					</option>
					<option value="100">
						100
					</option>
					<option value="200">
						200
					</option>
				</select>-->
				<s:select list='#{"20":"20","5":"5","100":"100","200":"200"}'
					name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})" theme="simple"></s:select>
				<span>条，共<c:out value='${sysPageInfo.maxCount}' />条</span>
			</div>
			<div class="pagination" targetType="navTab"
				totalCount="${sysPageInfo.maxCount}"
				currentPage="${sysPageInfo.currentPage}"
				numPerPage="${sysPageInfo.pageSize}" pageNumShown="10">
			</div>
		</div>
	</div>
</div>
