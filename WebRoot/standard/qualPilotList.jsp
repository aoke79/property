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
			<input type="hidden" name="pageNum" value="1" />
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
			<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
			<input type="hidden" id="isOrder" name="isOrder" value="" />
			<input type="hidden"  name="qtgroupid" value="${qtgroupid}" />
			<input type="hidden"  name="originalgrade" value="${originalgrade}" />
			<input type="hidden"  name="subGroupId" value="${subGroupId}" />
			<input type="hidden" name="userId"  id="userId" value="${userId}">
			<div class="searchBar"  id="conditionsList">
			 <table class="searchContent">
				  <tr>
						<td>用户登录名称：</td>						
						<td>
						<input type="text" name="username" alt="请输入用户登录名称" value="${username}" />
						</td>
						<td>用户姓名：</td>						
						<td>
						<input type="text" name="name" alt="请输入用户姓名"  value="${name}" />
						</td>
						<td>用户类型：</td>						
						<td>
						   <select name="usertype" id="usertypeId" style="width:100px;">
						   	 <option  value="">请选择</option>
						     <option  value="HR">HR</option>
						     <option  value="WW">WW</option>
						   </select>
						</td>
						<td>系统名：</td>						
						<td>
						   <s:select  name="sysid" list="ahSystemList" headerKey="" headerValue="--请选择--"	listKey="sysid"  listValue="sysname">
   						</s:select>	
						</td>
				</tr>
			</table>
			<div class="subBar"  id="conditionsListSearchPart">
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
						用户HRID
					</th>
					<th>
						用户姓名
					</th>
					<th>
						身份证号
					</th>
					<th>
						公司
					</th>
					<th>
						部门
					</th>
					<th>
						EMAIL
					</th>
					<th>
						用户类型
					</th>
					<th>
						操   作
					</th>
				</tr>
			</thead>
			<tbody>
			 
			<s:iterator value="ahuserList" >
			 		 <tr>		
							<td>									
								<s:property value="username"/>					
							</td>
							<td>									
								<s:property value="hrid"/>				
							</td>
							<td>
								<s:property value="name"/>
							</td>													
							<td >
							<s:property value="idcard"/>
							</td>
							<td >
							<s:property value="unitname"/>
							</td>
							<td >
							<s:property value="deptname"/>
							</td>						
							<td >						
								<s:property value="email"/>
							</td>
							<td>
							  <s:property value="usertype"/>
							</td>
							
							<td >
									<a class="button" href="standard!toAllotAuthority.do?mflag=ALL&ahuser.useruuid=<s:property value='useruuid'/>" target="navTab" rel="allotAuthority" title="访问权限"><span>访问权限</span></a>
									<c:if test="${session.user.usertype=='WW'}"> 
										<a class="button" href="standard!toAllotRole.do?ahuser.useruuid=<s:property value='useruuid'/>" target="navTab" rel="allotRole" title="管理角色"><span>管理角色</span></a>
									</c:if>
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
