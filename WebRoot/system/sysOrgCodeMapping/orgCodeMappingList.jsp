<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>

<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="sys-org-code-mapping/sys-org-code-mapping!list.do?sysOrganization.id=${sysOrganization.id}"
			method="post">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage"
				value="${sysPageInfo.pageSize}" />
			<input type="hidden" name="orderField" value="" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<!--<td>
							内部机构名称：
							<input type="text" name="sysOrgCodeMapping.sysOrganization.name" value="${sysOrgCodeMapping.sysOrganization.name}" />
						</td>
						-->
						<td>
							外部系统编号：
							<input type="text" name="query.like_outerOrgId"
								value="${query.like_outerOrgId}" />
						</td>
						<td>
							系统名称：
							<input type="text" name="query.like_systemName"
								value="${query.like_systemName}" />
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
						test='!#session.sysOperateList.{?#this.operateAction=="sys-org-code-mapping/sys-org-code-mapping!toAdd.do"}.isEmpty()'>
						<a class="add"
							href="sys-org-code-mapping/sys-org-code-mapping!toAdd.do?sysOrganization.id=${sysOrganization.id}&numPerPage=${numPerPage}"
							target="navTab"><span>添加</span> </a>
					</s:if>
				</li>
				<li>
					<s:if
						test='!#session.sysOperateList.{?#this.operateAction=="sys-org-code-mapping/sys-org-code-mapping!deleteMore.do"}.isEmpty()'>
						<a title="确实要删除这些记录吗?" target="removeSelected"
							href="sys-org-code-mapping/sys-org-code-mapping!deleteMore.do?sysOrganization.id=${sysOrganization.id}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}"
							class="delete"><span>批量删除</span>
						</a>
					</s:if>
				</li>
				<li class="line">
					line
				</li>
				<li>
					<a class="icon" href="testimport.html" target="dialog" width="300"
						height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span>
					</a>
				</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30">
						<input type="checkbox" group="ids" class="checkboxCtrl">
					</th>
					<th width="150">
						内部机构名称
					</th>
					<th width="150">
						外部系统机构编号
					</th>
					<th width="150">
						系统编号
					</th>
					<th width="150">
						系统名称
					</th>
					<th width="400">
						备注
					</th>
					<th width="70px">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listSysOrgCodeMapping" var="org" status="sta">
					<tr target="sid_user" rel="<s:property value="id.toString()"/>">
						<td>
							<input type="checkbox" name="ids" class="checkboxCtrl"
								value="<s:property value="id.toString()"/>">
							<input type="hidden" name="ids" class="checkboxCtrl"
								value="<s:property value="id.toString()"/>">
						</td>
						<td>
							${sysOrganization.name}
						</td>
						<td>
							${outerOrgId}
						</td>
						<td>
							${id.systemId}
						</td>
						<td>
							${systemName}
						</td>
						<td></td>
						<td style="text-align: right;">

							<s:if
								test='!#session.sysOperateList.{?#this.operateAction=="sys-org-code-mapping/sys-org-code-mapping!delete.do"}.isEmpty()'>
								<a class="btnDel"
									href="sys-org-code-mapping/sys-org-code-mapping!delete.do?method=remove&sysOrgCodeMapping.id.orgId=${id.orgId}&sysOrgCodeMapping.id.systemId=${id.systemId}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}"
									target="navTabTodo" title="确定要删除吗?"></a>
							</s:if>

							<s:if
								test='!#session.sysOperateList.{?#this.operateAction=="sys-org-code-mapping/sys-org-code-mapping!toEdit.do"}.isEmpty()'>
								<a class="btnEdit"
									href="sys-org-code-mapping/sys-org-code-mapping!toEdit.do?method=remove&sysOrgCodeMapping.id.orgId=${id.orgId}&sysOrgCodeMapping.id.systemId=${id.systemId}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}"
									target="navTab" title="修改"></a>
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
