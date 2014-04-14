<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		pagination = "${pageNum}";
		pageSize = "${numPerPage}";
		query = "${strQuery}";
	});

</script>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-org-code-mapping/sys-org-code-mapping!edit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     		<input type="hidden" name="sysOrganization.id" value="${sysOrgCodeMapping.id.orgId}">
				<input type="hidden" name="sysOrgCodeMapping.id.orgId" value="${sysOrgCodeMapping.id.orgId}">
    		    <input type="hidden" value="${sysOrgCodeMapping.id.systemId}" name="sysOrgCodeMapping.id.systemId">
    		    <input type="hidden" name="newSysOrgCodeMapping.id.orgId" value="${sysOrgCodeMapping.id.orgId}">
		 <p>
		 <label>外部系统编号:</label>
			 <input type="text" value="${sysOrgCodeMapping.outerOrgId}" name="newSysOrgCodeMapping.outerOrgId">
   
	     </p>
	     <p>
		 <label>系统编号:</label>
		<input type="text" name="newSysOrgCodeMapping.id.systemId" value="${sysOrgCodeMapping.id.systemId}">
    
	     </p>
	     <p>
		 <label>系统名称:</label>
		 <input type="text" value="${sysOrgCodeMapping.systemName}" name="newSysOrgCodeMapping.systemName">
   
	     </p>
	     
		 </div>
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>
