<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-role/sys-role!roleAdd.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     <table border="0">
	     <tr>
	     <td>
         
            <label><s:property value="getText('roleName')"/>:</label>
			<input align="left" name="role.roleName" class="required" type="text" size="30" value="" alt="请输入角色名称" />
		 
		 </td>
		 <td>
		 
		 	<label><s:property value="getText('roleEnName')"/>:</label>
		 	<input name="role.eroleName" type="text" size="30" />
	     
	     </td>
	    </tr>
	     <tr>
	     <td>
	     
		 	<label><s:property value="getText('roleDescription')"/>:</label>
		 	<input name="role.description" type="text" size="30" />
	     </td>
	     <td>
		 	<label><s:property value="getText('roleEnDescription')"/>:</label>
		 	<input name="role.edescription" type="text" size="30"  />
	     </td>
	     </tr>
	     <tr>
	     <td >
		 	<label><s:property value="getText('validity')"/>:</label>
		 	 <input type= "radio" name="role.flag" value= "0" checked="checked"/> <s:property value="getText('valid')"/>
             <input type= "radio" name= "role.flag" value= "1" /> <s:property value="getText('invalid')"/> 
        
        <div align="left">
 <label><s:property value="getText('subsystem')"/>:</label>
${tableInfo}
</div>
	     </td>
<td>
             
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