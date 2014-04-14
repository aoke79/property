<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		pageSize = "${numPerPage}";
		orderBy = "${orderBlock}";
		orderCol = "${orderMethod}";
	});

</script>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-system/sys-system!addSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label><s:property value="getText('systemName')"/>:</label>
			<input align="left" name="sysSystem.name" class="required" type="text" size="30" value="" alt="请输入系统名称" />
		 </p>
		 <p>
		 	<label><s:property value="getText('systemDescription')"/>:</label>
		 	<input name="sysSystem.description" type="text" size="30" />
	     </p>
		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
				<li>
					<div class="buttonActive"><div class="buttonContent">
						<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("addLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'>关闭页面</button>
					</div></div>
				</li>
			</ul>
		</div>
	  </form>
   </div>
</div>
