<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-menu/sys-user-custom-menu!addSave.do" class="pageForm required-validate"
            onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label><s:property value="getText('menuName')"/>:</label>
			<s:select list='#session.sysMenuList.{?#this.parentMenu.id != "-1"}' listKey="id" listValue="name" name="sysUserCustomMenu.sysMenu.id"></s:select>
		 </p>
		 <p>
		 	<input name="sysUserCustomMenu.cmUser.userId" 
		           type="hidden" size="30" value="${user.userId}" />
	     </p>
	     <p>
	     	<label><s:property value="getText('status')"/>:</label>
	     	<input type="radio" name="sysUserCustomMenu.state" size="30" value="1" checked="checked" /> <s:property value="getText('available')"/>
		 	<input type="radio" name="sysUserCustomMenu.state" size="30" value="0" /> <s:property value="getText('unavailable')"/>
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
							<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("addLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'><s:property value="getText('cancel')"/></button>
						</div></div>
					</li>
					<li>
						<div class="button"><div class="buttonContent">
							<button type="reset"><s:property value="getText('reset')"/></button>
						</div></div>
					</li>
				</ul>
			</div>
	  </form>
   </div>
</div>