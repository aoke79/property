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

<div id="qualPilotList" class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return searchPilot(this);" action="standard!findUserList.do" method="post">
			<input type="hidden" name="ahuser.useruuid"   value="${ahuser.useruuid}">
			<div class="searchBar"  id="conditionsList">
		</div>
		</form>
	</div>

	<div class="pageContent" >
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="standard!toAddPage.do?ahuser.useruuid=${ahuser.useruuid}&oldcode=${ahuser.oldcode}&username=${session.user.username}" rel="" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('add')"/></span></a></li>
			</ul>
		</div>
		<table class="table" 
			layoutH="105"  style="width: 100%">
			<thead>
				<tr >
					<th  style="width: 10%;">
						用户名
					</th>
					<th style="width: 10%;">
						系统名
					</th>
					<th style="width: 7%;">
						新员工号
					</th>
					<th style="width: 7%;">
						老员工号
					</th>
					<th style="width: 15%;">
						操   作
					</th>
				</tr>
			</thead>
			<tbody>
			 
			<s:iterator value="Ahdetaillist" >
			 		 <tr>		
							<td>									
								<s:property value="username"/>					
							</td>
							<td>									
								<s:property value="sysname"/>				
							</td>
							<td>
								<s:property value="hrid"/>
							</td>													
							<td >
							<s:property value="oldcode"/>
							</td>						
							<td ><s:if test="mflag==1">
								<a class="button wuxiao" 
<%--								onclick="WXlog('${username}','${sysname}');"--%>
									href="standard!toNullity.do?ahuser.useruuid=${ahuser.useruuid}&sysid=${sysid}&mflag=N&username=${session.user.username}"
									target="navTabTodo" rel="" title="确定置为无效?"><span>设置无效</span>
								</a>
							</s:if> <s:elseif test="mflag==0">
							<a class="button"
									href="standard!toNullity.do?ahuser.useruuid=${ahuser.useruuid}&sysid=${sysid}&mflg=Y&username=${session.user.username}"
									target="navTabTodo" rel="" title="确定置为有效?"><span>设置有效</span>
								</a>

							</s:elseif>
							<a class="button"
									href="standard!tomodifyLogin.do?ahuser.useruuid=${ahuser.useruuid}&sysid=${sysid}"
									target="dialog" mask=true width="410" height="250" rel="" title="修改信息"><span>修改信息</span>
								</a>
							</td>	
					   </tr>	
			</s:iterator>
			</tbody>
		</table>
	</div>
	
</div>
