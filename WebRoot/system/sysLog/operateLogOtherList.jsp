<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
 $(function(){
	    var usertype="${sysname}";
	    if(usertype=="日志系统"){
	       document.getElementById("sysnameIds").value="日志系统";
	    }
	    if(usertype=="办公系统"){
	       document.getElementById("sysnameIds").value="办公系统";
	    }
	    if(usertype=="费控系统"){
	       document.getElementById("sysnameIds").value="费控系统";
	    }
	    if(usertype=="HR系统"){
	       document.getElementById("sysnameIds").value="HR系统";
	    }
	});
</script>
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ah-login/anhua-log!otherList.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						姓名：<input type="text" name="query.like_username" alt="请输入用户姓名" value="${query.like_username}" />
					</td>
					<td>登录系统名：</td>						
						<td>
						   <select name="sysname" id="sysnameIds" style="width:100px;">
						   	 <option  value="">请选择</option>
						     <option  value="日志系统">日志系统</option>
						     <option  value="办公系统">办公系统</option>
						     <option  value="费控系统">费控系统</option>
						     <option  value="HR系统">HR系统</option>
						   </select>
						</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/></button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<table class="table" layouth="114">
			<thead>
				<tr><!--
					<th   style="width: 18%;">用户登录名</th>
					<th   style="width: 10%;">用户姓名</th>
					<th   style="width: 13%;">IP地址</th>
					<th   style="width: 10%;">登录系统名称</th>
					<th   style="width: 13%;">操作时间</th>
					<th   style="width: 36%;">操作名称</th>
					
					--><th >用户登录名</th>
					<th >用户姓名</th>
					<th >IP地址</th>
					<th >登录系统名称</th>
					<th >操作时间</th>
					<th >操作名称</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${ahTuLogdateList}" var="logInfo">
				<tr target="sid_user" rel="${logInfo.loguuid}" >
					<td><c:out value="${logInfo.useruuid}"></c:out></td>
					<td><c:out value="${logInfo.username}"></c:out></td>
					<td><c:out value="${logInfo.micip}"></c:out></td>
					<td><c:out value="${logInfo.sysname}"></c:out></td>
					<td><c:out value="${logInfo.optime}"></c:out></td>
					<td><c:out value="${logInfo.opname}"></c:out></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
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
	
</div>
