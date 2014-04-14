<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<link href="<%=basePath%>/css/user.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/js/jquery.caret.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.ipaddress.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/user-validate.js"></script>
 <script type="text/javascript">
      $(function(){
      	showProgressBar = false;
        $('.ipFirst').ipaddressFirst();
        $('.ipLast').ipaddressLast();
        $("#aFunOrgIdEdit").click(function(){
        	showProgressBar = true;
        });
        
        
        $("#aFunOrgIdEdit").click(function(){
        	$(this).attr("href","sys-organization/sys-organization!toOrganizationList.do?"
		 		 		+"organizationName=funOrgIdEdit&organizationId=hiddenFunOrgIdEdit&organizationGrade=2"
		 		 		+"&chooseCheck=0");
        })
        
      });
 </script>
<script type="text/javascript">
<!--
	
    	// 分页赋值
    	$(function(){
			pageSize = "${numPerPage}";
			pagination="${pageNum}";
			query = "${strQuery}";
		});
//-->
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" id="formUser" action="user-info/cm-user!doupdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
      
      <input type="hidden" name="cmUser.userId" id="userId" value="<s:property value="#attr.cmUser.userId"/>"/>
    
	     <div class="pageFormContent" layoutH="56">
           <p>
         <label><s:property value="getText('subsystem')"/></label>
         <s:select list="#attr.sysSystemList" name="cmUser.subsystemId" listKey="id" listValue="name" headerKey="" headerValue="请选择"/>
         </p>
         <p>
		    <label><s:property value="getText('loginAccount')"/>:</label>
			<input  align="left" 
					id="loginName" 
					name="cmUser.loginName" 
					value="<s:property value="#attr.cmUser.loginName"/>" 
					class="required alphanumeric" 
					remote="user-info/cm-user!validateLoginName.do?cmUser.userId=<s:property value="#attr.cmUser.userId"/>" 
					size="30" />
					<span id="loginNameError" class="errorInfo"></span>			
		 </p><!--
		 
		 <p>
<label><s:property value="getText('password')"/>:</label>
		 	<input id="accountPassword" name="cmUser.accountPassword" type="hidden" value="<s:property value="#attr.cmUser.accountPassword"/>"/>
	     </p>
	  
	      --><p>
 	<input id="accountPassword" name="cmUser.accountPassword" type="hidden" value="<s:property value="#attr.cmUser.accountPassword"/>"/>
		 	<label><s:property value="getText('functionsOrg')"/>:</label>
		 	
		 	<input type="hidden" id="hiddenFunOrgIdEdit" name="orgFunIds" value="<s:property value="#attr.orgFunIds"/>" />
		 	
		 	<input id="funOrgIdEdit"  type="text" readonly="readonly" size="27" value="<s:property value="#attr.orgFunNames"/>"/>
		 	
		 	<a class="btnLook" id="aFunOrgIdEdit" target="dialog"  resizable=true drawable=true 
		 		title="<s:property value="getText('chooseOrg')"/>" width="400" height="480">选择</a>		 	
	     </p>
	     
	    <!-- 
	      <p>
		 	<label><s:property value="getText('administrationOrg')"/>:</label>
		 	<input type="hidden" id="hiddenAdmOrgId" name="cmUser.sysOrganizationByAdmOrgCode.id" value="<s:property value="#attr.cmUser.sysOrganizationByAdmOrgCode.id"/>"/>
		 	<input id="admOrgId" type="text" readonly="readonly" size="27" value="<s:property value="#attr.cmUser.sysOrganizationByAdmOrgCode.name"/>"/>
		 	<a class="btnLook" id="aAdmOrgId" href="user-info/cm-user!toOrganizationList.do?organizationSign=1"
		 		target="dialog"  resizable=true drawable=true 
		 		title="<s:property value="getText('chooseOrg')"/>" width="400" height="480">
		 		选择
		 	</a>
	     </p>
	    -->
	      <p>
		 	<label><s:property value="getText('name')"/>:</label>
		 	<input name="cmUser.name" value="<s:property value="#attr.cmUser.name"/>" type="text" size="30" class="required" alt="请输入姓名" />
	     </p>
	      <p>
		 	<label><s:property value="getText('userGentle')"/>:</label>
				<s:if test='#attr.cmUser.sex.equals("0")'>
  					<input name="cmUser.sex" id="male" type="radio" value="1" ><s:property value="getText('male')"/>
  					<input name="cmUser.sex"   id="female" type="radio" value="0" checked><s:property value="getText('female')"/>
  				</s:if>
  				<s:else>
  					<input name="cmUser.sex" id="male" type="radio" value="1" checked><s:property value="getText('male')"/>
  					<input name="cmUser.sex"  id="female" type="radio" value="0"><s:property value="getText('female')"/>
  				</s:else>
	     </p>
	      <p>
		 	<label><s:property value="getText('birthday')"/>:</label>
		 	<input name="cmUser.birthday" id="birthday" style="float:left;" yearstart="-100" class="date" value="<s:property value="#attr.cmUser.birthday"/>" type="text" size="27" readonly="readonly"/>
		 	<a class="inputDateButton" href="javascript:void(0)">选择</a>
	     </p>
	     
	      <p>
		 	<label><s:property value="getText('tel')"/>:</label>
		 	<input name="cmUser.phone" value="<s:property value="#attr.cmUser.phone"/>" type="text" size="30" class="phone" />
	     </p>
	      <p>
		 	<label><s:property value="getText('email')"/>:</label>
		 	<input id="email" 
		 	name="cmUser.email" 
		 	value="<s:property value="#attr.cmUser.email"/>" 
		 	type="text" 
		 	size="30" 
		 	class="email"
		 	remote="user-info/cm-user!validateEmail.do?cmUser.userId=${cmUser.userId }" 
		 	/>
		 	
	     </p><!--
	     <p>
	     	<label><s:property value="getText('IPAddress')"/>：</label><input type="text" id="ipFirst" name="ipFirst" size="30" class="ipFirst"  value="<s:property value="#attr.ipFirst"/>">

	     </p>
	     <p>
	     	<label><s:property value="getText('toAddress')"/></label><input type="text" id="ipLast" name="ipLast" size="30" class="ipLast"  value="<s:property value="#attr.ipLast"/>" size="30">
	     	<span id="ipError" class="errorInfo"></span>
	     </p>
	      --><p>
		 	<label><s:property value="getText('identitycard')"/>:</label>
		 	<input id="identityCode" name="cmUser.identityCode" type="text" value="<s:property value="#attr.cmUser.identityCode"/>" size="30" />
		 	<span id="identityCodeError" class="errorInfo"></span>
	     </p>
	      <p>
		 	<label><s:property value="getText('country')"/>:</label>
		 	<s:select list="#attr.listSysCountry" listKey="id" listValue="name" name="cmUser.sysCountry.id"></s:select>
	     </p>
	     
	     <p><label><s:property value="getText('postStatus')"/>:</label>    
	     
	     	<s:if test='#attr.cmUser.state.equals("0")'>
  				<input name="cmUser.state" type="radio" value="1" ><s:property value="getText('userOnlie')"/>
  				<input name="cmUser.state" type="radio" value="0" checked><s:property value="getText('leavePost')"/>
  			</s:if>
  			<s:else>
  				<input name="cmUser.state"  type="radio" value="1" checked><s:property value="getText('userOnlie')"/>
  				<input name="cmUser.state"  type="radio" value="0"><s:property value="getText('leavePost')"/>
  			</s:else>
	     <p>
	     
	      <p><span style="color:red" id="myError"></span></p>
		 </div>
		
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<li>					
					<div class="buttonActive"><div class="buttonContent"><button type="submit" id="subUser"><s:property value="getText('save')"/></button></div></div>					
					</li>
					</s:if>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()"><s:property value="getText('cancel')"/></button></div></div>
					</li>
					</s:if>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<li><div class="button"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
					</s:if>
				</ul>
			</div>
	  </form>
   </div>
</div>
