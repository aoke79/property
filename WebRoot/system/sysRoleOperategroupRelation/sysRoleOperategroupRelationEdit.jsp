<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：角色操作组关系修改
 	 * 更新记录：
	 */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="jstl" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-role-operategroup-relation/sys-role-operategroup-relation!doEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
		 <input type="hidden" name="sysRoleOperategroupRelation.id.roleId" value="${sysRoleOperategroupRelation.id.roleId}">
		 <input type="hidden" name="sysRoleOperategroupRelation.id.operateGroupid" value="${sysRoleOperategroupRelation.id.operateGroupid}">
		 <p>
		 <label>角色名称:</label>
		 <input name="sysRoleOperategroupRelation.sysRole.roleName" type="text" size="30"
		        value="${sysRoleOperategroupRelation.sysRole.roleName}" readonly="readonly"/>
	     </p>
	     <p>
		 <label>操作组名称:</label>
		 <input name="sysRoleOperategroupRelation.sysOperateGroup.name" type="text" size="30"
		        value="${sysRoleOperategroupRelation.sysOperateGroup.name}" readonly="readonly"/>
	     </p>	
	     <p>
		 <label>有效性:</label>
		 <input name="sysRoleOperategroupRelation.state" type="text" size="30"
		        value="${sysRoleOperategroupRelation.state}" />
	     </p>
	     <p>
	     <label>备注:</label>
		 <input name="sysRoleOperategroupRelation.rorComment" type="text" size="30"
		        value="${sysRoleOperategroupRelation.rorComment}" />
	     </p>
		 </div>
		 <div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">更新</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>
