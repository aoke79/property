<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
   

})
</script>
<div class="page">
   <div class="pageContent">
      
      <form method="post" action="sys-role/sys-role!roleEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden"  name="pageNum" value="${paging.curPage}"/>
         <input type="hidden" name="role.roleId" value="${role.roleId}">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label><s:property value="getText('roleName')"/>:</label>
			<input align="left" name="role.roleName" class="required" type="text" size="30" 
			       value="${role.roleName}" />
		 </p>
		 <p>
		    <label><s:property value="getText('roleEnName')"/>:</label>
			<input align="left" name="role.eroleName"  type="text" size="30" 
			       value="${role.eroleName}" />
		 </p>
		 <p>
		 <label><s:property value="getText('roleDescription')"/>:</label>
		 <input name="role.description" type="text" size="30"
		        value="${role.description}" />
	     </p>
	      <p>
		 <label><s:property value="getText('roleEnDescription')"/>:</label>
		 <input name="role.edescription" type="text" size="30"
		        value="${role.edescription}" />
	     </p>
	     <p>
	      <label><s:property value="getText('validity')"/>:</label>
		 <input type= "radio" name="role.flag" value= "0" <s:if test="role.flag==\"0\""> checked="checked"</s:if> /> <s:property value="getText('valid')"/>
         <input type= "radio" name= "role.flag" value= "1" <s:if test="role.flag==\"1\""> checked="checked"</s:if>/> <s:property value="getText('invalid')"/>       
        <div align="left">
 <label><s:property value="getText('subsystem')"/>:</label>
  ${tableInfo}
</div>	     
</p>

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


