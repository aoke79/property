<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script>
	$(function(){
		parentId = "";
		parentId = "${sysMenu.parentMenu.id}";
	})
	
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
      <form method="post" action="sys-menu/sys-menu!addSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
         <p>
         <label><s:property value="getText('subsystem')"/></label>
<s:if test="!'超级管理员'.equals(#attr.userName)">
   <s:select  list="#attr.sysSystemList" name="sysMenu.subsystemId" listKey="id" listValue="name"/>
</s:if>
<s:else>
   <s:select  list="#attr.sysSystemList" name="sysMenu.subsystemId" listKey="id" listValue="name" headerKey="" headerValue="请选择"/>
</s:else>
      
         </p>
         <p>
		    <label><s:property value="getText('menuName')"/></label>
			<input align="left" name="sysMenu.name" class="required" type="text" size="30" value="" />
		 </p>
		 <p>
		 	<label><s:property value="getText('menuAction')"/></label>
		 	<input align="left" name="sysMenu.url" type="text" size="30" value="" />
	     </p>
	     <p>
		 	<label><s:property value="getText('parentMenu')"/></label>
                   <s:if test="#attr.sysMenName.id!=null && !''.equals(#attr.sysMenName.id)">
                   <input name="sysMenu.parentMenu.id" type="hidden" value='<s:property value="#attr.sysMenName.id"/>'/>
                   </s:if>
<s:else>
<input name="sysMenu.parentMenu.id" type="hidden" value="-1"/>
</s:else>
            		
					<input name="systemType"  readonly="readonly" value='<s:property value="#attr.sysMenName.name"/>'/>
					<a class="btnLook" id="knowchooseSystemClick" 
					 title="请选择菜单"></a>
					<a id="knowchooseSystem" 
					rel="knowchooseSystemDialog" target="dialog" style="display:none"
					width="500" height="600" title="选择菜单"></a>
		 	<!--<s:select list='menuList' listKey="id" listValue="name" name="sysMenu.parentMenu.id" headerKey="-1" headerValue="-1"></s:select>
	     --></p>
	     <p>
	     	<label><s:property value="getText('menuEnName')"/></label>
	     	<input align="left" name="sysMenu.enName" type="text" size="30" />
	     </p>
	     <p>
		 	<label><s:property value="getText('menuOperType')"/></label>
		 	<input type="radio" name="sysMenu.operateType" size="30" value="0" checked="checked" /> <s:property value="getText('none')"/>
		 	<input type="radio" name="sysMenu.operateType" size="30" value="1" /> <s:property value="getText('oper')"/>
		 	<input type="radio" name="sysMenu.operateType" size="30" value="2" /> <s:property value="getText('opergroup')"/>
	     </p>
	     <p>
	     	<label><s:property value="getText('menuOrder')"/></label>
	     	<input align="left" name="sysMenu.orderColumn" type="text" size="30" class="digits" alt="<s:property value='getText("customMenuTips")' />" />
	     </p>
	     <p>
	     	<label>REL</label>
	     	<input align="left" name="sysMenu.tabInfo" type="text" size="30"/>
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