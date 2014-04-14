<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(function(){
		parentId = "";
		currentTreeNode = "";
		parentId = "${parentId}";
		currentTreeNode = "${sysMenu.id}";
	});
	
		$(function(){
	
			$("#knowchooseSystemClick").click(function(){
		
				$("#knowchooseSystem").attr("href",
											"sys-operate/sys-operate!menutSystemTreeListAdd.do");
				$("#knowchooseSystem").click();
		
			
		})
	
	})

</script>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-menu/sys-menu!editSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     <input type="hidden" name="sysMenu.id" value="${sysMenu.id}">
         <p>
         <label><s:property value="getText('subsystem')"/></label>
         <s:select list="#attr.sysSystemList" name="sysMenu.subsystemId" listKey="id" listValue="name" headerKey="" headerValue="请选择"/>
         </p>
         <p>
		    <label><s:property value="getText('menuName')"/></label>
			<input align="left" name="sysMenu.name" value="${sysMenu.name}" class="required" type="text" size="30" value="" alt=""/>
		 </p>
		 <p>
		 	<label><s:property value="getText('menuAction')"/></label>
		 	<input align="left" name="sysMenu.url"  value="${sysMenu.url}"  type="text"  size="30" />
	     </p>
	     <p>
		 	<label><s:property value="getText('parentMenu')"/></label>
		 	            		<input name="sysMenu.parentMenu.id" type="hidden" value="${sysMenu.parentMenu.id}"/>
                    <s:if test="#attr.sysMenu.parentMenu.id=='-1'">
                    <input name="systemType"  readonly="readonly"/>
                    </s:if>
                    <s:else>
                    <input name="systemType"  readonly="readonly" value="${sysMenu.parentMenu.name}"/>
                    </s:else>
					
					<a class="btnLook" id="knowchooseSystemClick" 
					 title="请选择菜单"></a>
					<a id="knowchooseSystem" 
					rel="knowchooseSystemDialog" target="dialog" style="display:none"
					width="500" height="600" title="选择菜单"></a>
	     </p>
	     <p>
	     	<label><s:property value="getText('menuEnName')"/></label>
	     	<input align="left" name="sysMenu.enName" type="text" size="30" value="${sysMenu.enName}" />
	     </p>
	     <p>
		 	<label><s:property value="getText('menuOperType')"/></label>
		 	<s:if test="sysMenu.operateType == 1">
		 		<input type="radio" name="sysMenu.operateType" size="30" value="0" /> <s:property value="getText('none')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="1" checked="checked" /> <s:property value="getText('oper')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="2" /> <s:property value="getText('opergroup')"/>
		 	</s:if>
		 	<s:elseif test="sysMenu.operateType == 2">
		 		<input type="radio" name="sysMenu.operateType" size="30" value="0" /> <s:property value="getText('none')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="1" /> <s:property value="getText('oper')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="2" checked="checked" /> <s:property value="getText('opergroup')"/>
		 	</s:elseif>
		 	<s:else>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="0" checked="checked" /> <s:property value="getText('none')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="1" /> <s:property value="getText('oper')"/>
		 		<input type="radio" name="sysMenu.operateType" size="30" value="2" /> <s:property value="getText('opergroup')"/>
		 	</s:else>
	     </p>
	     <p>
	     	<label><s:property value="getText('menuOrder')"/></label>
	     	<input align="left" name="sysMenu.orderColumn" type="text" size="30" class="digits" value="${sysMenu.orderColumn}" />
	     </p>
	     <p>
	     	<label>REL</label>
	     	<input align="left" name="sysMenu.tabInfo" type="text" size="30" value="${sysMenu.tabInfo}">
	     </p>
		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
				<li>
					<div class="buttonActive"><div class="buttonContent">
						<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("editLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'>关闭页面</button>
					</div></div>
				</li>
			</ul>
		 </div>
	  </form>
   </div>
</div>