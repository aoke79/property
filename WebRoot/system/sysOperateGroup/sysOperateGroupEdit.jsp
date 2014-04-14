<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：修改操作组
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-operate-group/sys-operate-group!editSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden" name="sysOperateGroup.id" value="${sysOperateGroup.id}">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label>操作组名称:</label>
			<input align="left" name="sysOperateGroup.name" class="required" type="text" size="30" 
			       value="${sysOperateGroup.name}" />
		 </p>
		 <p>
		 <label>操作组描述:</label>
		 <input name="sysOperateGroup.operateDesc" type="text" size="30"
		        value="${sysOperateGroup.operateDesc}" />
	     </p>
	     <p>
		 	<label>有效性:</label>
		 	<!-- <input name="sysOperateGroup.state" type="text" size="30" value="${ sysOperateGroup.state}"/> -->
		 	<s:if test='#attr.sysOperateGroup.state.equals("1")'>
				<input name="sysOperateGroup.state" type="radio" value="1" checked>有效
				<input name="sysOperateGroup.state" type="radio" value="0">无效
			</s:if>
			<s:else>
				<input name="sysOperateGroup.state" type="radio" value="1" >有效
				<input name="sysOperateGroup.state" type="radio" value="0" checked>无效
			</s:else>
	     </p>
	     <p>
		 	<label>备注:</label>
		 	<input name="sysOperateGroup.opegComment" type="text" size="30" value="${sysOperateGroup.opegComment }"/>
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
