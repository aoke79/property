<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/simpleTable.css" />
<style>
tables { border-collapse:separate; border-spacing:0;background-color:red;}
table tr {text-align:center;}
.contentCenter {text-align:center;}
caption, tr, td { text-align:left; font-weight:normal;}
</style>
<div id="addAllotRoleJsp" class="page">
	<div class="pageContent">
		<form method="post" action="standard!doSaveRole.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		 <input type="hidden" name="ahuser.useruuid" value="${ahuser.useruuid}"/>
		 <input type="hidden" name="username" value="${session.user.username}"/>	
			<div class="pageFormContent" layoutH="56">
				<table class="tables" cellpadding="0" cellspacing="0" style="width: 98%">
					<tr >
					<s:iterator value="ahSystemList" status="st">
					      <s:if test="#st.getIndex()%4==0">   
					   <tr>
					   </s:if>
						<td><input name="sysid" value="${sysid}" type="checkbox"/>${sysname }</td>
						<s:if test="#st.getIndex()%4==3">
						</tr>
						</s:if>
					</s:iterator>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" onclick="navTab.closeCurrentTab()"><s:property value="getText('close')"/></button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="reset"><s:property value="getText('reset')"/></button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>

</div>

