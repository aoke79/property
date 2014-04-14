<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-message/system-message!editSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     	<input type="hidden" name="sysBackLogMessage.id" value="${sysBackLogMessage.id}">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td>发起消息时间</td>
	     			<td>
	     				<input type="text" class="required date" size="27"
	     				       name="sysBackLogMessage.sendDate" readonly="readonly"
	     				       value="${sysBackLogMessage.sendDate}" />
	     				<a class="inputDateButton" href="javascript:void(0)">选择</a>
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="areaTitle">消息内容</td>
	     			<td colspan="3">
	     				<textarea name="content"><s:property value="srtContent" /></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>

		 </div>
		 <div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">关闭页面</button></div></div>
				</li>
			</ul>
		 </div>
	  </form>
   </div>
</div>
