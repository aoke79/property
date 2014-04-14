<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
   <div class="pageContent">
     
      <input type="hidden" name="cmUser.userId" id="userId" value="<s:property value="#attr.cmUser.userId"/>"/>
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label><s:property value="getText('loginAccount')"/>:</label><s:property value="#attr.cmUser.loginName"/>
		 </p>
		 <p>
		 	<label><s:property value="getText('name')"/>:</label><s:property value="#attr.cmUser.name"/>
	     </p>
	     <p>
		 	<label><s:property value="getText('functionsOrg')"/>:</label><s:property value="#attr.cmUser.funOrgId"/>
		 </p>
	     <!--<p>
		 	<label><s:property value="getText('administrationOrg')"/>:</label><s:property value="#attr.cmUser.sysOrganizationByAdmOrgCode.name"/>
		 </p>	     
	     --><p>
		 	<label><s:property value="getText('userGentle')"/>:</label>
				<s:if test='#attr.cmUser.sex.equals("0")'><s:property value="getText('female')"/>
  				</s:if>
  				<s:else><s:property value="getText('male')"/>
  				</s:else>
	     </p>
	      <p>
		 	<label><s:property value="getText('birthday')"/>:</label><s:property value="#attr.cmUser.birthday"/>
	     </p>
	      
	      <p>
		 	<label><s:property value="getText('tel')"/>:</label><s:property value="#attr.cmUser.phone"/>
	     </p>
	      
	     <p>
	     	<label><s:property value="getText('IPAddress')"/>：</label><s:property value="#attr.ipFirst"/>
	     </p>
	     <p>
	     	<label><s:property value="getText('toAddress')"/></label><s:property value="#attr.ipLast"/>
	     </p>
	     <p>
		 	<label><s:property value="getText('identitycard')"/>:</label><s:property value="#attr.cmUser.identityCode"/>
	     </p>
	     <p>
		 	<label><s:property value="getText('email')"/>:</label><s:property value="#attr.cmUser.email"/>
	     </p>
	      <p>
		 	<label><s:property value="getText('country')"/>:</label>
		 	<s:property value="#attr.cmUser.sysCountry.name"/>
	     </p>
	     
	     <p><label><s:property value="getText('postStatus')"/>:</label>    	     
	     	<s:if test='#attr.cmUser.state.equals("1")'><s:property value="getText('atPost')"/></s:if>
  			<s:else><s:property value="getText('leavePost')"/></s:else>
	     <p>
	     
	 <div>
   </div>
</div>
