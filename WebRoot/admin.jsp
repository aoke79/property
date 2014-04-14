<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>安华农业保险</title>
    <style type="text/css">
    body {
	margin:0;
	padding:0;
	}
	.subnav{ 
	border-top:1px #8abb12 solid; 
	border-left:1px #8abb12 solid; 
	border-right:1px #8abb12 solid; 
	background:url("<%=basePath%>/images/bj1.jpg") no-repeat; 
	width:100%; 
	height:100%;
	background-color:#E6F8FC; }
	.subnav span{font-size:32px; color:#FFF; font-weight:bold;float:left; padding:500px 0px 0px 11px;}
    </style>
</head>
<body>
<div class="subnav">
	<span>统一办公平台后台管理</span>
</div>
</body>