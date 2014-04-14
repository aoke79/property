<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：修改操作
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		orderBy = "${orderBlock}";
		orderCol = "${orderMethod}";
	});
			$("#knowchooseSystemClick").click(function(){
		
				$("#knowchooseSystem").attr("href",
											"sys-operate/sys-operate!menutSystemTreeList.do");
				$("#knowchooseSystem").click();
		
			
		})
		
		
				$("#knowchooseSystemClick2").click(function(){
		
				$("#knowchooseSystem2").attr("href",
											"sys-operate/sys-operate!palSystemTreeList.do");
				$("#knowchooseSystem2").click();
		
			
		})
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-operate/sys-operate!editSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden" name="sysOperate.id" value="${sysOperate.id}">
	     <div class="pageFormContent" layoutH="56">
         <!-- 
         <p>
		 	<label>系统编号:</label>
		 	<input name="sysOperate.systemId" type="text" size="30" value="${sysOperate.systemId }"/>
	     </p>
	      --><%--
           <p>
         <label><s:property value="getText('subsystem')"/></label>
         <s:select list="#attr.sysSystemList" name="sysOperate.subsystemId" listKey="id" listValue="name" headerKey="" headerValue="请选择" class="required"/>
         </p>
         --%><p>
		    <label><!--  操作名称:--><s:property value="getText('operName')"/></label>
			<input align="left" name="sysOperate.operateName" class="required" type="text" size="30" value="${sysOperate.operateName}" />
		 </p>
		 <p>
		 	<label><!-- 触发的URL串: --><s:property value="getText('urlAction')"/></label>
		 	<input name="sysOperate.operateAction" type="text" size="30" value="${sysOperate.operateAction }"/>
	     </p>
	     <p>
		 	<label><!-- 导航信息: --><s:property value="getText('navigationInfo')"/></label>
		 	<input name="sysOperate.menuInfo" type="text" size="30" value="${sysOperate.menuInfo }"/>
	     </p>
	     <!-- 
		 <p>
		 	<label>是否为底层菜单:</label>
		 	<input name="sysOperate.ifFinallyMenu" type="text" size="30" value="${sysOperate.ifFinallyMenu}" class="required"/>
	     </p>
	     -->
					<p>
					<label> 操作父编号: </label>
					<input name="sysOperate.parentOperate.id" type="hidden" size="30" value="-1" class="required" value="${sysOperate.parentOperate.id}"/> 
					<input name="systemType2"  readonly="readonly" value="${sysOperate.parentOperate.operateName}"/>
					<a class="btnLook" id="knowchooseSystemClick2" 
					 title=" 请选择操作父编号"></a>
					<a id="knowchooseSystem2"  resizable=true drawable=true
					rel="knowchooseSystemDialog" target="dialog" style="display:none"
					width="500" height="600" title="选择操作父编号"></a>
				</p>

				<p>
					<label> 菜单名称：</label>
					<input name="sysOperate.sysMenu.id" type="hidden" value="${sysOperate.sysMenu.id}"/>
					<input name="systemType"  readonly="readonly" value="${sysOperate.sysMenu.name}" class="required"/>
					<a class="btnLook" id="knowchooseSystemClick" 
					 title="请选择菜单"></a>
					<a id="knowchooseSystem"  resizable=true drawable=true
					rel="knowchooseSystemDialog" target="dialog" style="display:none"
					width="500" height="600" title="选择菜单"></a>
				</p>
	     <!-- 
	     <p>
		 	<label>等级:</label>
		 	<input name="sysOperate.opeLevel" type="text" size="30" value="${sysOperate.opeLevel }"/>
	     </p>
	     -->
	      <p>
		 	<label><!-- 有效性: --><s:property value="getText('validity')"/></label>
 			<s:if test='#attr.sysOperate.flag.equals("1")'>
				<input name="sysOperate.flag" type="radio" value="1" checked='checked'><!-- 有效 --><s:property value="getText('valid')"/>
				<input name="sysOperate.flag" type="radio" value="0"><!--  无效--><s:property value="getText('invalid')"/>
			</s:if>
			<s:else>
				<input name="sysOperate.flag" type="radio" value="1" ><!-- 有效 --><s:property value="getText('valid')"/>
				<input name="sysOperate.flag" type="radio" value="0" checked='checked'><!--  无效--><s:property value="getText('invalid')"/>
			</s:else>
	     </p>
		 <p>
			<label><!--  描述:--><s:property value="getText('description')"/></label>
		    <textarea name="sysOperate.description"  cols="23" rows="3">${sysOperate.description }</textarea>
	     </p>
           <p>
         <label><s:property value="getText('subsystem')"/></label>
         <s:select list="#attr.sysSystemList" name="sysOperate.subsystemId" listKey="id" listValue="name"/>
         </p>
		 </div>
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><!-- 保存 --><s:property value="getText('save')"/></button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()"><!-- 取消 --><s:property value="getText('cancel')"/></button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset"><!-- 重置 --><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>
