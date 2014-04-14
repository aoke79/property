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
      <form method="post" action="sys-user-group/sys-user-group!sysUserGroupEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden" name="sysUserGroup.id" value="${sysUserGroup.id}">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label>用户组名称:</label>
			<input align="left" name="sysUserGroup.GName" class="required" type="text" size="30" 
			       value="${sysUserGroup.GName}" />
		 </p>
		 <p>
		 <label>用户组描述:</label>
		 <input name="sysUserGroup.GDesc" type="text" size="30"
		        value="${sysUserGroup.GDesc}" />
	     </p>
	     <p>
	     <label>用户组状态:</label>
	     	<s:if test='sysUserGroup.state.equals("1")'>
				<input type="radio" name="sysUserGroup.state" value="1" checked="checked">有效
				<input type="radio" name="sysUserGroup.state" value="0">无效
			</s:if>
			<s:elseif test='sysUserGroup.state.equals("0")'>
				<input type="radio" name="sysUserGroup.state" value="1" >有效
				<input type="radio" name="sysUserGroup.state" value="0" checked="checked">无效	
			</s:elseif>
			<s:else>
				<input type="radio" name="sysUserGroup.state" value="1" >有效
				<input type="radio" name="sysUserGroup.state" value="0" >无效	
			</s:else>
	     </p>
		 </div>
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick='alertMsg.confirm("您修改的资料未保存，确认要离开吗？",{okCall:function(){navTab.closeCurrentTab();}})'>取消</button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>
