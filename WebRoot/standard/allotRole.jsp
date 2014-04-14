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
<script type="text/javascript">
	var $topDiv=$("#qualPilotList");
	
	//post方式局部刷新子标签页
	function searchPilot(form){
		$topDiv.parent().loadUrl($(form).attr("action"), $(form).serializeArray(), function(){
			$topDiv.parent().find("[layoutH]").layoutH();
		});
		return false;
	};
	
	 $(function(){
	    var usertype="${usertype}";
	    if(usertype=="HR"){
	       document.getElementById("usertypeId").value="HR";
	    }
	    if(usertype=="WW"){
	       document.getElementById("usertypeId").value="WW";
	    }
	});
	
</script>

<div id="allotRoleJsp" class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return searchPilot(this);" action="standard!findUserList.do" method="post">
			<input type="hidden" name="ahuser.useruuid"   value="${ahuser.useruuid}">
			<input type="hidden" name="username" value="${session.user.username}"/>
			<div class="searchBar"  id="conditionsList">
		</div>
		</form>
	</div>

	<div class="pageContent" >
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="standard!toAddRole.do?ahuser.useruuid=${ahuser.useruuid}" rel="" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('add')"/></span></a></li>
			</ul>
		</div>
		<table class="table" 
			layoutH="60"  style="width: 100%">
			<thead>
				<tr >
					<th  style="width: 10%;">
						用户名
					</th>
					<th style="width: 10%;">
						系统名
					</th>
					<th style="width: 15%;">
						操   作
					</th>
				</tr>
			</thead>
			<tbody>
			 
			<s:iterator value="ahAdminList" >
			 		 <tr>		
							<td>									
								<s:property value="ahUser.name"/>					
							</td>
							<td>									
								<s:property value="ahSystem.sysname"/>				
							</td>
							<td >
								<a class="button" title="确定要删除这条记录吗" target="navTabTodo"  
								href="standard!doDeleteRole.do?ahAdmin.uuid=<s:property value="uuid"/>
								&ahAdmin.ahUser.useruuid=<s:property value="ahUser.useruuid"/>
								&ahAdmin.ahSystem.sysid=<s:property value="ahSystem.sysid"/>
								&username=${session.user.username}" >
								<span>删除 </span> 
								</a>
							</td>	
					   </tr>	
			</s:iterator>
			</tbody>
		</table>
	</div>
	
</div>
