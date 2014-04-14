<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script>
$(function(){
	var ids = "";
	$(".hiddenIds").each(function(i, n){
		ids += $(this).val() + ",";
	})

	$(".button").click(function(){
		$(this).attr("href","sys-message/system-message!toChooseReceiveUser.do?ids=" + ids);
		return false;
	});
});
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-message/system-message!sendMessages.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
	     	<table class="cusTable" cellpadding="0" cellspacing="0">
	     		<tr>
	     			<td colspan="2">
	     				<a class="button" href="#" target="dialog" title="选择收件人" width="520" height="350"><span>选择收件人</span></a>
					</td>
				</tr>
	     		<tr>
	     			<td class="msgTitle">收件人</td>
	     			<td class="msgBody">
		     			<s:iterator value="hasChooseUsers" status="stat">
		     				<input type="hidden" value="${userId}" name="ids" class="hiddenIds">
		     				<s:if test="#stat.count == hasChooseUsers.size()">
		     					${name}
		     				</s:if>
		     				<s:else>
		     					${name};
		     				</s:else>
		     			</s:iterator>
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle">消息类型</td>
	     			<td class="msgBody">
	     				<select name="messageType">
		     				<option value="">请选择</option>
		     				<option value="E">代办事宜</option>
		     				<option value="B">业务变更</option>
	     				</select>
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="msgTitle areaTitle">消息内容</td>
	     			<td class="msgBody">
	     				<textarea class="required" onpropertychange="changeHeight(this)"
	     						  id="sysBackLogMessage.content" name="srtContent"
		 				          oninput="this.style.height=this.scrollHeight + 'px'"></textarea>
	     			</td>
	     		</tr>
	     		
	     	</table>

		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">关闭页面</button></div></div>
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
		if(hexheight < 200){
			hexarea.style.height = hexarea.scrollHeight + 'px';
		}
		
	}
</script>