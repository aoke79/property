<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-message/unread-message!closeSingle.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     	<input type="hidden" name="sysBackLogMessage.id" value="${sysBackLogMessage.id}">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td><s:property value="getText('messageTitle')"/>:</td>
	     			<td colspan="3">
	     				<input type="text" size="100"
	     				       name="sysBackLogMessage.title" readonly="readonly"
	     				       value="${sysBackLogMessage.title}" />
	     			</td>
	     		</tr>
	     		<tr>
	     			<td><s:property value="getText('sendTime')"/>:</td>
	     			<td>
	     				<input type="text" size="30"
	     				       name="sysBackLogMessage.sendDate" readonly="readonly"
	     				       value="${sysBackLogMessage.sendDate}" />
	     			</td>
	     			<td></td>
	     			<td></td>
	     		</tr>
	     		<tr>
	     			<td class="areaTitle"><s:property value="getText('messageContent')"/>:</td>
	     			<td colspan="3">
	     				<textarea id="sysBackLogMessage.content" name="content" readonly="readonly"><s:property value="srtContent" /></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>

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
