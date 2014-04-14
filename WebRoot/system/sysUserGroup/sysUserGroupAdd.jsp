<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
      <form method="post" action="sys-user-group/sys-user-group!sysUserGroupAdd.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label>用户组名称:</label>
			<input align="left" name="sysUserGroup.GName" class="required" type="text" size="30" value="" alt="请输入用户组名称" />
		 </p>
		 <p>
		 	<label>用户组描述:</label>
		 	<input name="sysUserGroup.GDesc" type="text" size="30" />
	     </p>
	      <p>
		 	<label>用户组状态:</label>
			 	<input type="radio" name="sysUserGroup.state" value="1">有效
			 	<input type="radio" name="sysUserGroup.state" value="0">无效
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
