<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-relation-role-operate/sys-relation-role-operate!sysRelationRoleOperateEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden"  name="pageNum" value="${paging.curPage}"/>
         <input type="hidden" name="sysRelationRoleOperate.id.roleId" value="${sysRelationRoleOperate.id.roleId}" />
         <input type="hidden" name="sysRelationRoleOperate.id.operateId" value="${sysRelationRoleOperate.id.operateId}" />
	     <div class="pageFormContent" layoutH="56">
	     <table>
	     <tr>
	     <td>
	       <s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
         <p>
		    <label><s:property value="getText('roleName')"/>:</label>
			<input align="left" name="eroleName"  type="text" size="30" 
			       value="${sysRelationRoleOperate.sysRole.eroleName}"  readonly="readonly"/>
		 </p>
		 </s:if>
		 <s:else>
		  <p>
		    <label><s:property value="getText('roleName')"/>:</label>
			<input align="left" name="roleName"  type="text" size="30" 
			       value="${sysRelationRoleOperate.sysRole.roleName}"  readonly="readonly"/>
		 </p>
		 
		 </s:else>
		 </td>
		 <td>
		 <p>
		    <label><s:property value="getText('operName')"/>:</label>
			<input align="left" name="operateName"  type="text" size="30" 
			       value="${sysRelationRoleOperate.sysOperate.operateName}"  readonly="readonly" />
		 </p>
		 </td>
		 </tr>
		 <tr>
		 <td>
		 <p>
		 <label><s:property value="getText('description')"/>:</label>
		 <input name="sysRelationRoleOperate.description" type="text" size="30"
		        value="${sysRelationRoleOperate.description}" />
	     </p>
	     </td>
	     <td>
	     <p>
		 <label><s:property value="getText('validity')"/>:</label>
		 <!-- 
		 <input name="sysRelationRoleOperate.flag" type="text" size="30"
		        value="${sysRelationRoleOperate.flag}" maxlength="1"/>
		  -->       
		 <input type= "radio" name="sysRelationRoleOperate.flag" value= "0" <s:if test="sysRelationRoleOperate.flag==\"0\""> checked="checked"</s:if> /> <s:property value="getText('valid')"/>

         <input type= "radio" name= "sysRelationRoleOperate.flag" value= "1" <s:if test="sysRelationRoleOperate.flag==\"1\""> checked="checked"</s:if>/> <s:property value="getText('invalid')"/>
		  
		        
		        
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
