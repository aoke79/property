<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-relation-account-role/sys-relation-account-role!rarEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
          <input type="hidden"  name="pageNum" value="${paging.curPage}"/>
         <input type="hidden" name="relationAccountRole.accountRoleId.roleId" value="${relationAccountRole.accountRoleId.roleId}" />
         <input type="hidden" name="relationAccountRole.accountRoleId.userId" value="${relationAccountRole.accountRoleId.userId}" />
         <input type="hidden" name="userId" value="${relationAccountRole.accountRoleId.userId}" />
         
	     <div class="pageFormContent" layoutH="56">
	     <table>
	     <tr>
	     <td>
	      <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
           <p>
		    <label><s:property value="getText('roleName')"/>:</label>
			<input align="left" name="eroleName"  type="text" size="30" 
			       value="${relationAccountRole.sysRole.eroleName}"  readonly="readonly"/>
		    </p>
		 </s:if>
		 <s:else>
		    <p>
		    <label><s:property value="getText('roleName')"/>:</label>
			<input align="left" name="roleName"  type="text" size="30" 
			       value="${relationAccountRole.sysRole.roleName}"  readonly="readonly"/>
		    </p>
		 </s:else>
		 
		 
		 </td>
		 <td>
		 <p>
		    <label><s:property value="getText('userName')"/>:</label>
			<input align="left" name="userName"  type="text" size="30" 
			       value="${relationAccountRole.cmUser.name}"  readonly="readonly" />
		 </p>
		 </td>
		 </tr>
		 <tr>
		 <td>
		 <p>
		 <label><s:property value="getText('description')"/>:</label>
		 <input name="relationAccountRole.description" type="text" size="30"
		        value="${relationAccountRole.description}" />
	     </p>
	     </td>
	     <td>
	     <p>
		 <label><s:property value="getText('validity')"/>:</label>
		 <input type= "radio" name="relationAccountRole.flag" value= "0" <s:if test="relationAccountRole.flag==\"0\""> checked="checked"</s:if> /> <s:property value="getText('valid')"/>
         <input type= "radio" name= "relationAccountRole.flag" value= "1" <s:if test="relationAccountRole.flag==\"1\""> checked="checked"</s:if>/> <s:property value="getText('invalid')"/>        
		        
		        
	     </p>
	     </td>
	     </tr>
	     </table>
		 </div>
		 <div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()"><s:property value="getText('cancel')"/></button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>
