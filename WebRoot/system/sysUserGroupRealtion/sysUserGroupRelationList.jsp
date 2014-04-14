<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="sys-user-group/sys-user-group-relation!serachUser.do"
			method="post">
			<input type="hidden" name="pageNum" value="1" />
     		<input type="hidden" name="numPerPage" value="${sysPageInfo.pageSize}" />
     		<input type="hidden" name="orderField" value="" />
			<input type="hidden" name="sysUserGroup.id" value="${sysUserGroup.id}">
		</form>

	<div class="pageContent" layoutH="15">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<a class="add"
						href="sys-user-group/sys-user-group!toUserManagerPage.do?sysUserGroup.id=${sysUserGroup.id}"
						target="navTab"><span>管理组</span>
					</a>
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
		<table class="table" layoutH="77">
			<thead>
				<tr>
					<th width="150">
						用户组名
					</th>
					<th width="400">
						用户名
					</th>
					<th width="150">
						用户组状态
					</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listSysUserGroupRelation" var="org" status="sta" >
					<tr target="sid_user" rel='<s:property value="id.toString()"/>'>
						<td>
							${sysUserGroup.GName}
						</td>
						<td>
							${cmUser.name}
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
							<a class="btnEdit"
									href="sys-user-group/sys-user-group-relation!updateState.do?sysUserGroupRelation.id.groupId=${id.groupId}&sysUserGroupRelation.id.userId=${id.userId}&pageNum=${pageNum}&numPerPage=${numPerPage}"
									target="navTabTodo" title="更改状态"></a>
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
				<s:select list='#{"20":"20","5":"5","100":"100","200":"200"}' name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" theme="simple" ></s:select>
				<span>条，共<c:out value='${sysPageInfo.maxCount}' />条</span>
			</div>
			<div class="pagination" targetType="navTab"
			totalCount="${sysPageInfo.maxCount}"
			currentPage="${sysPageInfo.currentPage}"
			numPerPage="${sysPageInfo.pageSize}" pageNumShown="10">
		</div>
		</div>
	</div>
