<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-message/system-message!closeMessage.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     	<s:if test="sysBackLogMessage == null">
	     	<input type="hidden" id="id" name="sysHistoryMessage.id" value="${sysHistoryMessage.id}">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td class="msgTitle">消息标题</td>
	     			<td colspan="3">
	     				<span>${sysHistoryMessage.title}</span>
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle">发起消息时间</td>
	     			<td>
	     				<span><s:date name="sysHistoryMessage.sendDate" format="YYYY-mm-dd"/></span>
	     			</td>
	     			<td></td>
	     			<td></td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle areaTitle">消息内容</td>
	     			<td colspan="3">
	     				<textarea class="levarea required" onpropertychange="changeHeight(this)"
	     				          id="sysBackLogMessage.content" name="content" readonly="readonly"
		 				          oninput="this.style.height=this.scrollHeight + 'px'"><s:property value="srtContent" /></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>
	     	</s:if>
	     	<s:else>
	     	<input type="hidden" id="id" name="sysBackLogMessage.id" value="${sysBackLogMessage.id}">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td class="msgTitle">消息标题</td>
	     			<td colspan="3">
	     				<span>${sysBackLogMessage.title}</span>
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle">发起消息时间</td>
	     			<td>
	     				<span><s:date name="sysBackLogMessage.sendDate" format="YYYY-mm-dd"/></span>
	     			</td>
	     			<td></td>
	     			<td></td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle areaTitle">消息内容</td>
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
				<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
				<li>
					<div class="button"><div class="buttonContent"><button type="submit">关闭页面</button></div></div>
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