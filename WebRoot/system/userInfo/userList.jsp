<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
<!--
	$(function(){
		$("#subUser").click(function(){
			alert($("#hiddenFunOrgId").val());
		})
		
	})
	
	$(function(){
		$("#userlistJSP").find("button[cape=reset]").click(function(){
			$("#userlistJSP").find("#searchTable").find("input").val("");
			$("#userlistJSP").find("#searchTable").find("select").val("");
		})
	})
//-->
</script>

<div class="page" id ="userlistJSP">
	<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="user-info/cm-user!toadd.do" method="post">
		
		<input type="hidden" name="pageNum" value="1"/>		
		<input type="hidden" name="numPerPage" value="<s:property value="#attr.numPerPage"/>" />
		<input type="hidden" id="orderField" name="orderBlock" value="" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
		<table class="searchContent" width="100%" border="0">
				<tr>
					<td>登录名：</td>
					<td>
						<input name="query.like_loginName" type="text" size="20" value="${query.like_loginName }"/>
					</td>
					<td><s:property value="getText('name')"/>：</td>
					<td>
						<input name="query.like_name" type="text" size="20" value="${query.like_name }"/>
					</td>
					
					<td><s:property value="getText('identitycard')"/>：</td>
					<td>
						<input name="query.like_identityCode" type="text" size="20" value="${query.like_identityCode }"/>
					</td>
					
					<td><s:property value="getText('postStatus')"/>：
			<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
						<s:select list="#{'1':'atPost','0':'leavePost'}" listKey="key" listValue="value"  name="query.eq_state" headerKey="" headerValue=""></s:select>
					</s:if>
					<s:else>
						<s:select list="#{'1':'在职','0':'离职'}" listKey="key" listValue="value"  name="query.eq_state" headerKey="" headerValue="请选择"></s:select>
					</s:else>
</td>
					<td>
					</td>
<td></td>
					<td>&nbsp;
					</td>
				</tr>
				<tr>
					<td><s:property value="getText('functionsOrg')"/>:</td>
					<td>
						<input type="hidden" id="hiddenFunOrgIdList" name="funOrgId" value="${search.funOrgId }"/>
					 	<input id="funOrgIdList"  type="text" size="27" name="funOrgName" readonly="readonly" style="float:left" value="${search.funOrgName }"/>
					 	<a class="btnLook" id="aFunOrgIdList" 
					 		href="sys-organization/sys-organization!toOrganizationList.do?organizationName=funOrgIdList&organizationId=hiddenFunOrgIdList&organizationGrade=2&chooseCheck=0"
					 		target="dialog"  resizable=true drawable=true 
					 		title="<s:property value="chooseOrg"/>" width="500" height="580" >
					 	</a>
					</td>
					
					<td><s:property value="getText('country')"/>：</td>
					<td>
						<s:select list="#attr.listSysCountry" listKey="id" listValue="name" name="country"  headerKey="" headerValue="请选择"></s:select>
           			</td>
           			
					<td><s:property value="getText('userGentle')"/>：</td>
					<td>
					<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
						<s:select list="#{'1':'male','0':'female'}" listKey="key" listValue="value"  name="query.eq_sex" headerKey="" headerValue=""></s:select>
					</s:if>
					<s:else>
						<s:select list="#{'1':'男','0':'女'}" listKey="key" listValue="value"  name="query.eq_sex" headerKey="" headerValue="请选择"></s:select>
					</s:else>
  					</td>
			<s:if test="'超级管理员'.equals(#attr.userName)">
					<td>
						<s:property value="getText('subsystem')"/>:
						<s:select list="#attr.sysSystemList" name="query.like_subsystemId" listKey="id" listValue="name" headerKey="" headerValue="全部"/>
					</td>
		   </s:if>
<td></td>
				</tr>				
			</table>	
	
			<div class="subBar">
				<ul>
				<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit"><s:property value="getText('search')"/></button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button onclick='$("#userlistJSP").find(".searchContent").find(":input").val("")' cape=reset>
										<s:property value="getText('reset')" />
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		</s:form>
	</div>
	
	<div class="pageContent">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li>
				<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
				<a class="add" href="user-info/cm-user!add.do" target="navTab" rel="aaa"><span><s:property value="getText('add')"/></span></a>
				</s:if>
				</li>
				<!-- 
				<li>
					<a class="add" href="sys-relation-account-role/sys-relation-account-role!goView.do?userId={user_id}" target="navTab"><span>为账户分配角色</span>
					</a>
				</li>
				 -->
				<li> 
				<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
				<a title="<s:property value="getText('batchDeleteTips')"/>" target="removeSelected" href="user-info/cm-user!multipleDelete.do"  class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
				</s:if>
				</li><!--
				<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="<s:property value="getText('excel')"/>"><span><s:property value="getText('excel')"/></span></a></li>
			--></ul>
		</div>
		<table class="table" layouth="166">
		
			<thead>
				<tr>
					<th width="5%" style="text-align: center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th style="display:none">用户编号</th>
		    		<th width="13%" orderField="loginName"><s:property value="getText('loginAccount')"/></th>
		    		<th width="" orderField="name"><s:property value="getText('name')"/></th>
		    		<th width="6%" orderField="sex"><s:property value="getText('userGentle')"/></th>
		    		<!-- <th width="7%" orderField="birthday"><s:property value="getText('birthday')"/></th>
		    		<th  width="8%" orderField="identityCode"><s:property value="getText('identitycard')"/></th>
		    		<th width="10%" orderField="phone"><s:property value="getText('tel')"/></th>
		    		<th width="5%" orderField="email"><s:property value="getText('email')"/></th> -->
		    		<th width="" ><s:property value="getText('functionsOrg')"/></th>
		    		<!--<th width="9%" orderField="sysOrganizationByAdmOrgCode.name"><s:property value="getText('administrationOrg')"/></th>
		    		<th width="6%" orderField="sysCountry.name"><s:property value="getText('country')"/></th>-->
		    		<th width="7%" orderField="state"><s:property value="getText('usergroupStatus')"/></th>
<th width="10%" orderField="state"><s:property value="getText('subsystem')"/></th>
					<th width="20%"><s:property value="getText('oper')"/></th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#attr.listUser" id="user">
    			<tr target="user_id" rel="<s:property value="#user['userId']"/>" >
    				<td  style="text-align: center"><input name="ids" value="<s:property value="#user['userId']"/>" type="checkbox"></td>
    				<td  style="display:none"><s:property value="#user['userId']"/></td>
    				<td><s:property value="#user['loginName']"></s:property></td>
    				<td><s:property value="#user['name']"/></td>
    				<td><s:property value="#user['sex']"/></td>
    				<!-- <td><s:property value="#user['birthday']"/></td>
    				<td><s:property value="#user['identityCode']"/></td>
    				<td><s:property value="#user['phone']"/></td>
    				<td><s:property value="#user['email']"/></td>-->
    				<td><s:property value="#user.FunOrgId"/></td>
    				<!--<td><s:property value="#user.sysOrganizationByAdmOrgCode.name"/></td>
    				<td><s:property value="#user.sysCountry.name"/></td>-->
    				<td><s:property value="#user['state']"/></td>
<td><s:select list="#attr.sysSystemList"  listKey="id"  value="#attr.subsystemId" listValue="name" headerKey="" headerValue="无" disabled="true" id="selectInfo"/>

</td>
					<td style="text-align: center;">
                    <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnDel" href="user-info/cm-user!dodeleteById.do?cmUser.userId=<s:property value="#user['userId']"/>&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" target="navTabTodo" title="<s:property value="getText('deleteTips')"/>"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnEdit" href="user-info/cm-user!toupdate.do?cmUser.userId=<s:property value="#user['userId']"/>" target="navTab" title="<s:property value="getText('modify')"/>"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnLook" 
									href="sys-relation-account-role/sys-relation-account-role!roleManage.do?userId=<s:property value="#user['userId']"/>"
									target="navTab" title="<s:property value="getText('assignRole')"/>" rel="accountRoleRelationManage"><span><s:property value="getText('assignRole')"/></span></a>
					 </s:if>
					 <!-- 
					 <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					   <a class="btnLook" rel="tbUserOperateRelationManage"
									href="cm-user-operate-relation/cm-user-operate-relation!operateManage.do?userId=<s:property value="#user['userId']"/>"
									target="navTab" title="<s:property value="getText('userOper')"/>"><span><s:property value="getText('userOper')"/></span></a>
					 </s:if>
					  -->
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnLook" target="navTab" title="<s:property value="getText('searchAllOper')"/>"
							href="cm-user-operate-relation/cm-user-operate-relation!searchAllOperate.do?userId=<s:property value="#user['userId']"/>"
							><span><s:property value="getText('searchAllOper')"/></span></a>
						</s:if>
						<a class="btnStartProcess" target="dialog" title="重置密码" width="800" height="460"
							href="user-info/cm-user!toPwd.do?enter=user&cmUser.userId=<s:property value="#user['userId']"/>"><span>设置</span></a>
					</td>
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
				<span><s:property value="getText('column')"/><s:property value="getText('total')"/><s:property value="#attr.sysPageInfo.maxCount"/><s:property value="getText('column')"/></span>
			</div>
			
			<div class="pagination" targetType="navTab"  
				 totalCount="<s:property value="#attr.sysPageInfo.maxCount"/>" 
				 numPerPage="<s:property value="#attr.numPerPage"/>" 
				 pageNumShown="10" currentPage="<s:property value="#attr.sysPageInfo.currentPage"/>">				 
		</div>
	</div>
</div>
</div>

