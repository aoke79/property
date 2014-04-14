<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sinoframe.common.ConstantList;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="main" style="height: 100%; width:100%;">
	<table width="100%" style="background:url(images/zz/mian_jianbian.png) repeat-x">
		<tr>
			<td ><img src="images/zz/houtai_bg.jpg" height="390" id="login" ></td>
		</tr>
	</table>
</div>