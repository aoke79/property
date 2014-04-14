<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-menu/sys-user-custom-menu!editSave.do" class="pageForm required-validate"
            onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     <input type="hidden" name="sysUserCustomMenu.id" value="${sysUserCustomMenu.id}">
         <p>
		    <label><s:property value="getText('menuName')"/>:</label>
		    <s:select list='#session.sysMenuList.{?#this.parentMenu.id != "-1"}' listKey="id" listValue="name" name="sysUserCustomMenu.sysMenu.id"></s:select>
		 </p>
		 <p>
		 	<input name="sysUserCustomMenu.cmUser.userId" 
		           type="hidden" size="30" value="${user.userId}" />
	     </p>
	     <p>
		 	<label>状态:</label>
		 	<s:if test="sysUserCustomMenu.state == 1">
		 		<input type="radio" name="sysUserCustomMenu.state" size="30" value="1" checked="checked" /> <s:property value="getText('available')"/>
		 		<input type="radio" name="sysUserCustomMenu.state" size="30" value="0" /> <s:property value="getText('unavailable')"/>
		 	</s:if>
		 	<s:else>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="1" /> <s:property value="getText('available')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="0" checked="checked" /> <s:property value="getText('unavailable')"/>
		 	</s:else>
	     </p>
		 </div>
		 <div class="formBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent">
							<button type="submit"><s:property value="getText('save')"/></button>
						</div></div>
					</li>
					<li>
						<div class="button"><div class="buttonContent">
							<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("editLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'>
									<s:property value="getText('cancel')"/>
							</button>
						</div></div>
					</li>
				</ul>
			</div>
	  </form>
   </div>
</div>