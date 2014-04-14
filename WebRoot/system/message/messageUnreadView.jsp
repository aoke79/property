<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-message/unread-message!closeMessage.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     	<input type="hidden" id="id" name="sysBackLogMessage.id" value="${sysBackLogMessage.id}">
	     	<s:if test="sysBackLogMessage == null">
	     	<input type="hidden" id="id" name="sysHistoryMessage.id" value="${sysHistoryMessage.id}">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td class="msgTitle"><s:property value="getText('messageTitle')"/>:</td>
	     			<td colspan="3">
	     				<input type="text" size="80"
	     				       name="sysHistoryMessage.title" readonly="readonly"
	     				       value="${sysHistoryMessage.title}" />
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle"><s:property value="getText('sendTime')"/>:</td>
	     			<td>
	     				<input type="text" size="15"
	     				       name="sysHistoryMessage.sendDate" readonly="readonly"
	     				       value='<s:date name="sysHistoryMessage.sendDate" format="YYYY-mm-dd"/>'/>
	     			</td>
	     			<td></td>
	     			<td></td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle areaTitle"><s:property value="getText('messageContent')"/>:</td>
	     			<td colspan="3">
	     				<textarea class="levarea required" onpropertychange="changeHeight(this)"
	     				          id="sysBackLogMessage.content" name="content" readonly="readonly"
		 				          oninput="this.style.height=this.scrollHeight + 'px'"><s:property value="srtContent" /></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>
	     	</s:if>
	     	<s:else>
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td class="msgTitle"><s:property value="getText('messageTitle')"/></td>
	     			<td colspan="3">
	     				<input type="text" size="80"
	     				       name="sysBackLogMessage.title" readonly="readonly"
	     				       value="${sysBackLogMessage.title}" />
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle"><s:property value="getText('sendTime')"/>:</td>
	     			<td>
	     				<input type="text" size="15"
	     				       name="sysBackLogMessage.sendDate" readonly="readonly"
	     				       value="${sysBackLogMessage.sendDate}" />
	     			</td>
	     			<td></td>
	     			<td></td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle areaTitle"><s:property value="getText('messageContent')"/>:</td>
	     			<td colspan="3">
	     				<textarea class="levarea required" onpropertychange="changeHeight(this)"
	     				          id="sysBackLogMessage.content" name="content" readonly="readonly"
		 				          oninput="this.style.height=this.scrollHeight + 'px'"><s:property value="srtContent" /></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>
			</s:else>
		 </div>
		 <div class="formBar">
				<ul>
					<li>
						<div class="button"><div class="buttonContent"><button type="submit"><s:property value="getText('close')"/></button></div></div>
					</li>
				</ul>
			</div>
	  </form>
   </div>
</div>
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