<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	$(function(){
		parentId = "";
		parentId = "${sysOrganization.parent_Fun.id}";
	})
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-organization/sys-organization!addOrg.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	    	<input type="hidden" value="${sysOrganization.parent_Fun.id}" name="sysOrganization.parent_Fun.id">
	    	 <input type="hidden" name="sysOrganization.type" value="1"/>
         <p>
		    <label>机构名称:</label>
			<input align="left" name="sysOrganization.name" class="required" type="text" size="30" value="" alt="请输入系统名称" />
		 </p>
		 <p>
		 	<label>机构描述:</label>
		 	<input name="sysOrganization.description" type="text" size="30" />
	     </p>
	     <p>
		 	<label>机构类型:</label>
		 		<input type="radio" name="sysOrganization.orgType" value="1" checked="checked"/>单位
    			<input type="radio" name="sysOrganization.orgType" value="2"/>部门
	     </p>
	     <p>
	     	<label>机构类别</label>
	     	<input type="radio" name="sysOrganization.deptType" value="1" checked="checked"/>国航股份
    		<input type="radio" name="sysOrganization.deptType" value="2"/>国航控股
	     </p>
	     <p>
		 	<label>排列顺序:</label>
		 		<input type="text" name="sysOrganization.orgOrder" value="1">
	     </p>
	     <p>
		 	<label>机构级别:</label>
		 		<s:select list='#{"1":"公司","2":"一级单位","3":"基地","4":"科室"}' name="sysOrganization.orgLevel"></s:select>
		 		<!--<input type="text" name="sysOrganization.orgOrder" value="1">-->
	     </p>
	     <p>
		 	<label>上级监管机构:</label>
		 	<s:select list="listSysOrganization" listKey="#this.id" listValue="#this.name" name="sysOrganization.parent_Supervise.id"
    	headerKey="" headerValue="----------请选择-----------"/>
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
