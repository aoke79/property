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
        $("#aFunOrgIdAdd").click(function(){
        	showProgressBar = true;
        });
        $("#aAdmOrgId").click(function(){
        	showProgressBar = true;
        })
        $("#aFunOrgIdAdd").click(function(){
        	$(this).attr("href","sys-organization/sys-organization!toOrganizationList.do?organizationName=funOrgIdAdd"
        				+"&organizationId=hiddenFunOrgIdAdd&organizationGrade=2&orgFunIds="+$("#hiddenFunOrgIdAdd").val()
		 		 		+"&chooseCheck=0");
        })
      });
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" id="formUser" action="user-info/cm-user!doadd.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
      
		 <div class="pageFormContent" layoutH="56">
           <p>
         <label><s:property value="getText('subsystem')"/></label>
         <s:select list="#attr.sysSystemList" name="cmUser.subsystemId" listKey="id" listValue="name"/>
         </p>
         <p> 
		    <label><s:property value="getText('loginAccount')"/>:</label>
			
			<input id="loginName" 
				align="left" 
				id="loginName" 
				name="cmUser.loginName" 
				class="required alphanumeric" 
				remote="user-info/cm-user!validateLoginName.do" 
				size="30" 
				alt="<s:property value="getText('enterUser')"/>";
				/>
							
			<span id="loginNameError" class="errorInfo"></span>
		 </p>
		 <p>
		 	<label><s:property value="getText('password')"/>:</label>
		 	<input id="accountPassword" name="cmUser.accountPassword" type="password" class="required alphanumeric"  size="30" />
	     </p>
	  
	      <p>
		 	<label><s:property value="getText('functionsOrg')"/>:</label>
		 	<input type="hidden" id="hiddenFunOrgIdAdd" name="orgFunIds"/>
		 	<input id="funOrgIdAdd"  type="text" size="27" readonly="readonly" class="required"/>
		 	<a class="btnLook" 
			 	id="aFunOrgIdAdd" 
		 		target="dialog"  resizable=true drawable=true 
		 		title="<s:property value="getText('chooseOrg')"/>" width="400" height="480" >
		 		选择
		 	</a>
	     </p>

	     <!-- 
	      <p>
		 	<label><s:property value="getText('administrationOrg')"/>:</label>
		 	<input type="hidden" id="hiddenAdmOrgId" name="cmUser.sysOrganizationByAdmOrgCode.id" />
		 	<input id="admOrgId" type="text" size="27" readonly="readonly"/>
		 	<a class="btnLook" id="aAdmOrgId" href="user-info/cm-user!toOrganizationList.do?organizationSign=1"
		 		target="dialog"  resizable=true drawable=true 
		 		title="<s:property value="getText('chooseOrg')"/>" width="400" height="480">
		 		选择
		 	</a>		 	
	     </p>
	      -->
	      <p>
		 	<label><s:property value="getText('name')"/>:</label>
		 	<input name="cmUser.name" type="text" size="30" class="required" alt="<s:property value="getText('enterName')"/>" />
	     </p>
	      <p>
		 	<label><s:property value="getText('userGentle')"/>:</label>
		 	<input name="cmUser.sex" id="male" type="radio" value="1" checked><s:property value="getText('male')"/>
  			<input name="cmUser.sex" id="female" type="radio" value="0"><s:property value="getText('female')"/>
	     </p>
	      <p>
		 	<label><s:property value="getText('birthday')"/>:</label>
		 	<input name="cmUser.birthday" id="birthday" style="float:left;" class="date" yearstart="-100"  readonly="readonly" value="" type="text" size="27" />
		 	<a class="inputDateButton" href="javascript:void(0)">选择</a>
	     </p>
	      <p>
		 	<label><s:property value="getText('identitycard')"/>:</label>
		 	<input id="identityCode" name="cmUser.identityCode" type="text" size="30" /><span id="identityCodeError" class="errorInfo"></span>
	     </p>
	      <p>
		 	<label><s:property value="getText('tel')"/>:</label>
		 	<input id="phone" name="cmUser.phone" type="text" size="30" class="phone"/>
	     </p>
	      <p>
		 	<label><s:property value="getText('email')"/>:</label>
		 	<input id="email" 
		 	name="cmUser.email"
		 	type="text" 
		 	size="30" 
		 	class="email"
		 	remote="user-info/cm-user!validateEmail.do"
		 	/>
	     </p>	      
	   <!--  <p >
	     	<label><s:property value="getText('IPAddress')"/>：</label>	     	
	     	<input type="hidden" id="ipFirst" name="ipFirst" size="30" class="ipFirst" value="0.0.0.0" />
	     </p>-->
	  <!--   <p>
	     	<label><s:property value="getText('toAddress')"/></label>
	     	<input type="hidden" 
	     		id="ipLast" 
	     		name="ipLast" 
	     		size="30" 
	     		class="ipLast" value="255.255.255.255">
	     </p>-->
	     <p>
		 	<label><s:property value="getText('country')"/>:</label>
		 	<s:select list="#attr.listSysCountry" listKey="id" listValue="name"
		 	 name="cmUser.sysCountry.id" headerKey="1" headerValue="中国"></s:select>
	     </p>
	     <p><label><s:property value="getText('postStatus')"/>:</label>
		 	<input name="cmUser.state"  type="radio" value="1" checked><s:property value="getText('atPost')"/>
  			<input name="cmUser.state"  type="radio" value="0"><s:property value="getText('leavePost')"/></p>
	     <p><span style="color:red" id="myError"></span></p>
		 </div>		 
		 <div class="formBar">
				<ul>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<li>					
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="subUser"><s:property value="getText('save')"/></button>
						</div>
					</div>					
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
