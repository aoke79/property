<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath(); 
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
 td{
		padding-left: 6px;
		padding-top: 6px;
		border: 1px solid #8ABB12;
 
 }


</style>


<div id="updateLogin" >
 <form action="standard!doSaveUserMsg.do"  method="post" onsubmit="return validateCallback(this, dialogAjaxDone)">
 <input type="hidden" value="${ahPeruser.peruuid }" name="ahPeruser.peruuid"  />
	<table  style="border: 1px solid #8ABB12; height: 150px;width: 100%; border-collapse: collapse;">
		<tr>
			<td style="line-height: 20px;">
				用户名:
			</td>
			<td style="line-height: 20px;">
				<s:property value="ahPeruser.name" />
			</td>
		</tr>
		<tr>
			<td style="line-height: 20px;">
				系统名:
			</td>
			<td style="line-height: 20px;">
				<s:property value="ahPeruser.ahSystem.sysname" />
			</td>
		</tr>

		<tr>
			<td >
				登录名:
			</td>
			<td>
				<input name="ahPeruser.login" value="${ahPeruser.login }" />
			</td>
		</tr>

		<tr>
			<td >
				登录密码:
			</td>
			<td>
				<input name="ahPeruser.password" value="${ahPeruser.password }"
					style="width: 250px" />
				<input type="hidden" name="password" value=""  />
			</td>
		</tr>
	</table>
	<div>
	  <input style="line-height: 20px;float: right;margin-top: 10px;margin-right: 20px;" type="submit" value ="保存"/>
	</div>
</form>
</div>
