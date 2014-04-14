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
	var $topDiv=$("#ahrbacList");
	
	//post方式局部刷新子标签页
	function searchPilot(form){
		$topDiv.parent().loadUrl($(form).attr("action"), $(form).serializeArray(), function(){
			$topDiv.parent().find("[layoutH]").layoutH();
		});
		return false;
	};
	
	 $(function(){
	    var usertype="${sysname}";
	    if(usertype=="日志系统"){
	       document.getElementById("sysnameId").value="日志系统";
	    }
	    if(usertype=="核心系统"){
	       document.getElementById("sysnameId").value="核心系统";
	    }
	});
	
</script>

<div id="ahrbacList" class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="standard!findRizhiJilu.do" method="post">
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
			<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
			<input type="hidden" id="isOrder" name="isOrder" value="" />
			<input type="hidden"  name="qtgroupid" value="${qtgroupid}" />
			<input type="hidden"  name="originalgrade" value="${originalgrade}" />
			<input type="hidden"  name="subGroupId" value="${subGroupId}" />
			<input type="hidden" name="userId"  id="userId" value="${userId}">
			<div class="searchBar"  id="ahrbac">
			 <table class="searchContent">
				  <tr>
						<td>用户登录名称：</td>						
						<td>
						<input type="text" name="query.eq_loginname" alt="请输入用户登录名称" value="${query.eq_loginname}" />
						</td>
						<td>用户姓名：</td>						
						<td>
						<input type="text" name="query.like_username" alt="请输入用户姓名"  value="${query.like_username}" />
						</td>
						<td>系统名称：</td>						
						<td>
						   <select name="sysname" id="sysnameId" style="width:100px;">
						   	 <option  value="">请选择</option>
						     <option  value="日志系统">日志系统</option>
						     <option  value="核心系统">核心系统</option>
						   </select>
						</td>
				</tr>
			</table>
			<div class="subBar"  id="ahrbacListSearchPart">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit"><s:property value="getText('search')"/></button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="reset"><s:property value="getText('reset')"/></button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		</form>
	</div>

	<div class="pageContent" >
		<table class="table" 
			layoutH="115"  style="width: 100%">
			<thead>
				<tr >
					<th>
						用户登陆名称
					</th>
					<th>
						用户姓名
					</th>
					<th>
						角色-分组
					</th>
					<th>
						操作
					</th>
					<th>
						系统
					</th>
					
				</tr>
			</thead>
			<tbody>
			 
			<s:iterator value="ahrbacList" >
			 		 <tr>		
							<td>									
								<s:property value="loginname"/>					
							</td>
							<td>									
								<s:property value="username"/>				
							</td>
							<td>
								<s:property value="rolename"/>
							</td>
							<td>
							  <s:property value="operationame"/>
							</td>													
							<td >
							<s:property value="sysname"/>
							</td>
					   </tr>	
			</s:iterator>
			</tbody>
		</table>
	</div>
		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')"/></span>
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span><s:property value="getText('column')"/><s:property value="getText('total')"/><c:out value='${sysPageInfo.maxCount}'/><s:property value="getText('column')"/></span>
			</div>
			<div class="pagination" targetType="navTab"
			     totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
			     numPerPage="<c:out value='${numPerPage}'/>"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
	
</div>
