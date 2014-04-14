<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
$( "#aRROIdEdit" ).click( function () {
                   $( this ).attr( "href" , "sys-relation-role-operate/sys-relation-role-operate!goView.do?roleId=" 
                   						+$("#roleId").val() + "&RROIDs=" + $( "#hiddenRROIDs" ).val() );
        } )
</script> 
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="sys-relation-role-operate/sys-relation-role-operate!search.do" method="post">
			<input type="hidden" name="pageNum" value="${sysPageInfo.currentPage}"/>	
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		    <input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		    <input type="hidden" id="isOrder" name="isOrder" value="" />
		    <input type="hidden" id="orderAsc" name="sysOrderByInfo.orderAsc" value="${sysOrderByInfo.orderAsc}" />
			<input type="hidden" name="roleId" value="${roleId}" id="roleId">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<s:property value="getText('operName')"/>：
							<input type="text" name="query.operateName" value="${query.operateName}"/>
						</td>
						<td>
							<s:property value="getText('description')"/>：
							<input type="text" name="query.description"  value="${query.description}"/>
						</td>
					</tr>
				</table>
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
						  <div class="button">
						    <div class="buttonContent">
						     <button type="reset"><s:property value="getText('reset')"/></button>
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
					<!-- RROIDs : Relation Role Operate IDs ,存放的是当前角色已经有的操作权限ID -->
					<input type = "hidden" id = "hiddenRROIDs" name = "RROIDs" value = " <s:property value="#attr.RROIDs" /> " />
              		<!-- RRONames : Relation Role Operate Names ,存放的是当前角色已经有的操作权限名字（无作用，调试时用） -->
              		<input id = "funOrgIdEdit"  type = "hidden" readonly = "readonly" size = "30" value = " <s:property value="#attr.RRONames" /> " />
              		<a class="add" id="aRROIdEdit" target="dialog" resizable=true drawable=true title="<s:property value="getText('addRoleOperate')"/>" width="400" height="480"> <span><s:property value="getText('addRoleOperate')"/></span> </a> 
					<!--<a class="add" rel="add" href="sys-relation-role-operate/sys-relation-role-operate!goView.do?roleId=${roleId}" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('addRoleOperate')"/></span>
					</a>
				--></s:if>
				</li>
				<li>
				  <!-- 
					<a class="delete" href="sys-role/sys-relation-account-role!delSysRelationAccountRole.do?ruser.userId={sid_user}" target="navTabTodo" title="确定要删除吗?"><span>批量删除</span></a>
				  -->
				  <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>	
					<a title="<s:property value="getText('batchDeleteTips')"/>" target="removeSelected" href="sys-relation-role-operate/sys-relation-role-operate!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
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
                     <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
					<th width="200">
						<s:property value="getText('roleName')"/>
					</th>
					</s:if>
					<s:else>
					<th width="200">
						<s:property value="getText('roleName')"/>
					</th>
					</s:else>
					<th width="200" orderField="sysOperate.operateName">
						<s:property value="getText('operName')"/>
					</th>
					<th width="400" orderField="description">
						<s:property value="getText('description')"/>
					</th>
					<th width="200" orderField="flag">
					   <s:property value="getText('validity')"/>
					</th>
					<th width="70">
					    <s:property value="getText('operate')"/>
					</th>
				</tr>
			</thead>
			<tbody>
                 <s:if test="sysRelationRoleOperateList.size>0">

				<s:iterator value="sysRelationRoleOperateList">
					<tr target="sid_user" rel="${id.roleId}&sysRelationRoleOperateId.operateId=${id.operateId}" >
					    <td>
					    <input name="ids" value="${id.roleId}&sysRelationRoleOperateId.operateId=${id.operateId}" type="checkbox">
					    </td>
					     <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
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
							${sysOperate.operateName}
						</td>
						<td>
							${description}
						</td>
						<td>
							<s:if test="flag==\"0\""><s:property value="getText('valid')"/></s:if>
							<s:else><s:property value="getText('invalid')"/></s:else>
						</td>
						<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnDel" href="sys-relation-role-operate/sys-relation-role-operate!delSysRelationRoleOperate.do?sysRelationRoleOperateId.roleId=${id.roleId}&sysRelationRoleOperateId.operateId=${id.operateId}&pageNum=${paging.curPage}" target="navTabTodo" title="<s:property value='getText("delete")' />"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnEdit"  rel="edit"  href="sys-relation-role-operate/sys-relation-role-operate!toEditPage.do?sysRelationRoleOperateId.roleId=${id.roleId}&sysRelationRoleOperateId.operateId=${id.operateId}&pageNum=${paging.curPage}" target="navTab" title="<s:property value='getText("modify")' />"  messageHint="<s:property value='getText("editLeaveTips")' />"></a>
					    </s:if>
					    </td>
					</tr>
				</s:iterator>
              </s:if>
              <s:else>
              <tr >
                 <td colspan="4"><s:property value="getText('roleNoOperate')"/></td>
              </tr>
              </s:else>



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
