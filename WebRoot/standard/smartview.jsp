<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<style>
body {
	margin: 0;
	padding: 0;
	background: #f8fcff;
}
.contentTitle {
	height: 25px;
	margin: 1px auto;
	padding: 4px;
	border-bottom: 1px solid #1a4d85;
	line-height: 25px;
	font-size: 16px;
	text-align: center;
	font-weight: bold;
}
.contentBody {
	margin-top: 6px;
	font-size: 12px;
}
.contentFile {
	text-align: center;
}
.fileBox {
	margin: 6px;
}
.smartFile {
	color: black;
	text-decoration: none;
}
.smartFile:hover {
	color: blue;
	text-decoration: none;
	border-bottom: 1px solid blue;
}
.smartFile:visited {
	color: black;
	text-decoration: none;
	border-bottom: 1px solid black;
}
</style>
</head>
  
<body>
<div class="contentTitle">
  	${sysPortalData.dataName}
</div>
<div class="contentBody">
	<s:iterator value="fileList">
		<div class="contentFile">
			<div class="fileBox">
			<a href="upload-file.do?fileSrc=<s:property value="attchpath.substring(attchpath.indexOf(@com.sinoframe.common.ConstantList@UPLOADPATH))"/>"
				class="smartFile" id="${attchpath}" width="600" height="400" >${attchname}</a>
			</div>
		</div>
	</s:iterator>
</div>

</body>
</html>
