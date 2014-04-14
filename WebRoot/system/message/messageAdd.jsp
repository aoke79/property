<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-message/system-message!addSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td class="msgTitle"><s:property value="getText('messageTitle')"/></td>
	     			<td colspan="3">
	     				<input type="text" class="required" name="sysBackLogMessage.title" size="80" />
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle areaTitle"><s:property value="getText('messageContent')"/></td>
	     			<td colspan="3">
	     				<textarea class="levarea required" onpropertychange="changeHeight(this)"
	     				          name="content"
		 				          oninput="this.style.height=this.scrollHeight + 'px'"></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>

		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent">
						<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("addLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'>关闭页面</button>
					</div></div>
				</li>
			</ul>
		 </div>
	  </form>
   </div>
</div>
<script type="text/javascript" src="<%=path%>/js/plugin.autowraparea.js"></script>
<script type="text/javascript">
	function changeHeight(hexarea){
		var hexheight = hexarea.style.height.substring(0, hexarea.style.height.length-2);
		if(hexheight < 150){
			hexarea.style.height = hexarea.scrollHeight + 'px';
		}
		
	}
	
	$(function(){
		$("textarea").each(function(){
			autoRemake(this);
		})
	})
</script>