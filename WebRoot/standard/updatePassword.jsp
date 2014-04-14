<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="css/zzstyle.css" rel="stylesheet" type="text/css" />
<link href="css/loading.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/quality/js/dwz.ajax.js"></script>

<script>
	$(function(){
		$("input[name='reNewPasswords']").blur(function(){alert(11);
			var pwd=$("input[name='newPasswords']").val();
			var rePwd=$("input[name='reNewPasswords']").val();
		
			if(pwd != rePwd){
				$("#messagePwd").text("重复输入密码不一致，请重新输入");
			}
		});
	});


</script>




<body>
<div  id="blockDIVsss" class="white_content" >
 	<div class="">
 	<table>
 		<thead>
 			<tr>
 				<th align="center" colspan="2">修改密码</th>
 			</tr>
 		</thead>
 		<tbody>
 			<tr>
 				<td><span class="span_" >用户名</span></td><td><input name="username" value="${session.user.username }" disabled="disabled"  maxlength="10" minlength="10" size="28" /></td>
 			</tr>
 			<tr>
 				<td><span class="span_" >输入旧密码</span></td><td><input name="oldPassword" value="${session.user.password }" disabled="disabled" type="password" maxlength="30" minlength="5" size="30"/></td>
 			</tr>
 			<tr>
 				<td><span class="span_" >输入新密码</span></td><td><input name="newPasswords" value="" type="password" maxlength="30" minlength="6" size="30"/></td>
 			</tr>
 			<tr>
 				<td><span class="span_" >确认新密码</span></td><td><input name="reNewPasswords" value="" type="password" size="30"/></td>
 			</tr>
 			<tr>
 				<td colspan="2"><span id="messagePwd" style="color: red;" ></span></td>
 			</tr>
 			<tr>
 				<td align="right" colspan="2"><button type="submit" id="subPwd">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;<button id="btnClose" >关闭</button></td>
 			</tr>
 		</tbody>
 	</table>
 	</div>
</div>
</body>
