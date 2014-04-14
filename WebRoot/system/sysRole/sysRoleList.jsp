<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
$(function(){
		$("#sysrolelistJSP").find("button[cape=reset]").click(function(){
			$("#sysrolelistJSP").find("#searchTable").find("input").val("");
			$("#sysrolelistJSP").find("#searchTable").find("select").val("");
		})
	})
</script>
<div class="page" id ="sysrolelistJSP">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="sys-role/sys-role!search.do" method="post">
			<input type="hidden" name="pageNum" value="${sysPageInfo.currentPage}"/>	
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		    <input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		    <input type="hidden" id="isOrder" name="isOrder" value="" />
		    <input type="hidden" id="orderAsc" name="sysOrderByInfo.orderAsc" value="${sysOrderByInfo.orderAsc}" />
			<div class="searchBar">
			   <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
				  <table class="searchContent">
					<tr>
						<td>
							<s:property value="getText('roleName')"/>:
							<input type="text" name="query.eroleName" value="${query.eroleName}"/>
						</td>
						<td>
							<s:property value="getText('roleDescription')"/>:
							<input type="text" name="query.edescription" value="${query.edescription}" />
						</td>
		<s:if test="'超级管理员'.equals(#attr.userName)">
					<td>
						<s:property value="getText('subsystem')"/>:
						<s:select list="#attr.sysSystemList" name="query.like_subsystemId" listKey="id" listValue="name" headerKey="" headerValue="全部"/>
					
					</td>
		</s:if>
					</tr>
				  </table>
				</s:if>
				<s:else>
				  <table class="searchContent">
					<tr>
						<td>
							<s:property value="getText('roleName')"/>:
							<input type="text" name="query.roleName" value="${query.roleName}"/>
						</td>
						<td>
							<s:property value="getText('roleDescription')"/>:
							<input type="text" name="query.description" value="${query.description}" />
						</td>
		<s:if test="'超级管理员'.equals(#attr.userName)">
					<td>
						<s:property value="getText('subsystem')"/>:
						<s:select list="#attr.sysSystemList" name="query.subsystemId" listKey="id" listValue="name" headerKey="" headerValue="全部"/>
					</td>
		</s:if>
					</tr>
				</table>
				</s:else>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">
										<s:property value="getText('search')"/>
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button onclick='$("#sysrolelistJSP").find(".searchContent").find(":input").val("")' cape=reset>
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
				    <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<a class="add" rel="adddfsaf"   href="sys-role/sys-role!toAddPage.do" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span> <s:property value="getText('add')"/></span>
					</a>
					</s:if>
				</li>
				<li>
					 <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<a title="<s:property value="getText('batchDeleteTips')"/>" target="removeSelected" href="sys-role/sys-role!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
				    </s:if>
				</li>
				<li class="line">
					line
				</li>
				<li>
				</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
                    <th width="40"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                    <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
					  <th width="200" orderField="eroleName" >
						<s:property value="getText('roleName')"/>
					   </th>
					</s:if>
					<s:else>
						<th width="200" orderField="roleName">
						<s:property value="getText('roleName')"/>
					    </th>
					</s:else>
					<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
					<th width="300" orderField="edescription">
						<s:property value="getText('roleDescription')"/>
					</th>
					</s:if>
					<s:else>
					
					<th width="300" orderField="description">
						<s:property value="getText('roleDescription')"/>
					</th>
					</s:else>

					<th width="50" orderField="flag">
						<s:property value="getText('validity')"/>
					</th>
					<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}"><!--
					<th width="80" orderField="edescription">
						<s:property value="getText('subsystem')"/>
					</th>
					--></s:if>
					<s:else><!--
					
					<th width="80" orderField="description">
						<s:property value="getText('subsystem')"/>
					</th>
					--></s:else>
					<th width="250">
					   <s:property value="getText('operate')"/>
					</th>
				</tr>
			</thead>
			<tbody>

				<s:iterator value="sysRoleList">
					<tr target="sid_user" rel="${roleId}" >
					    <td>
					    <input name="ids" value="${roleId}" type="checkbox">
					    </td>
					    <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
						<td>
							${eroleName}
						</td>
						</s:if>
						<s:else>
						<td>
							${roleName}
						</td>
						</s:else>
						<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
						<td>
							${edescription}
						</td>
						</s:if>
						<s:else>
						<td>
							${description}
						</td>
						</s:else>

						<td>
							<s:if test="flag==\"0\""><s:property value="getText('valid')"/></s:if>
							<s:else><s:property value="getText('invalid')"/></s:else>
						</td>
			 <%--			<td>
 <s:select list="#attr.sysSystemList"  listKey="id"  value="#attr.subsystemId" listValue="name" headerKey="" headerValue="无" disabled="true" id="selectInfo"/>
						
						</td>--%>
						<td style="text-align: left;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnDel"  href="sys-role/sys-role!delRole.do?role.roleId=${roleId}&pageNum=${paging.curPage}" target="navTabTodo" title="<s:property value="getText('delete')"/>"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnEdit"  rel="edit" href="sys-role/sys-role!toEditPage.do?role.roleId=${roleId}&pageNum=${paging.curPage}" target="navTab" title="<s:property value="getText('modify')"/>" messageHint="<s:property value='getText("editLeaveTips")' />"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnLook"
									href="sys-relation-role-operate/sys-relation-role-operate!operateManage.do?roleId=${roleId}"
									target="navTab" title="<s:property value="getText('roleOperate')"/>" rel="roleOperateRelationManage"><span><s:property value="getText('roleOperate')"/></span></a>
						</s:if>
						
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-role-operategroup-relation/sys-role-operategroup-relation!list.do"}.isEmpty()'>
						<a class="btnLook" rel="roleOperategroupRelationManage" href="sys-role-operategroup-relation/sys-role-operategroup-relation!list.do?roleId=${roleId}" target="navTab" title="<s:property value="getText('roleOperategroup')"/>"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-role-operategroup-relation/sys-role-operategroup-relation!list.do"}.isEmpty()'>
						<a class="btnLook"
									href="sys-relation-account-role/sys-relation-role-account!searchUsers.do?roleId=${roleId}"
									target="navTab" title="<s:property value="getText('searchUsersByRole')"/>"  rel="roleAccountRelationManage"><span><s:property value="getText('searchUsersByRole')"/></span></a>
						</s:if><!--	
						<a class="btnLook"
									href="upload-file!goView.do"
									target="navTab" title="文件上传"  rel="file"><span>文件上传</span></a>				
					--></td>
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
			     totalCount="${sysPageInfo.maxCount}"
			     numPerPage="${numPerPage}"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
		
		
	</div>
</div>
