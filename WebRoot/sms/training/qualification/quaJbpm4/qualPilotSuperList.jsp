<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
#qualPilotSuperList{
					width:100%;
					height:100%;
					width:expression(document.body.clientWidth <= 800? "800px": "auto");
                    min-width:800px;
                    height:expression(document.body.clientWidth <= 400? "400px": "auto");
                    min-height:400px;
                    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="<%=basePath%>/images/ahbg.png", sizingMethod='scale')
                    }
</style>
<script type="text/javascript" charset="UTF-8">
//调节首页自适应的js
function fun(){
	$("#navTab").find(".tabsPageContent").css("overflow","auto");
}
$(function(){
	$("#qualPilotSuperList").find("a[rel=qualApply_tabId]").click(function(){
		$("#navTab").find(".tabsPageContent").css("overflow","hidden");
	});
});
</script>

<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/supperlist.css" />
<div  id="qualPilotSuperList" style="background:url(images/ahbg.png) ;height:100%;width:100%;background-color: blue;">
</div>
