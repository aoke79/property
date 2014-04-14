<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		pageSize = "${numPerPage}";
	});
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-org-code-mapping/sys-org-code-mapping!addMapping.do?sysOrgCodeMapping.id.orgId=${sysOrganization.id}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
		 <p>
		 	<label>外部系统机构编号:</label>
		 	<input type="text" name="sysOrgCodeMapping.outerOrgId">
	     </p>
	     <p>
		 	<label>系统编号:</label>
		 		<input type="text" name="sysOrgCodeMapping.id.systemId">
	     </p>
	     <p>
		 	<label>系统名称</label>
		 	<input type="text" name="sysOrgCodeMapping.systemName">
	     </p>
	     <p>
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
