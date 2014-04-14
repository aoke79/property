<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script>
//分配权限
$( "#aOPIdEdit" ).click( function () {
                   $( this ).attr( "href" , "sys-permission/sys-permission!toAdjustPage.do?OPIDs=" 
                   						+$( "#hiddenOPIDs" ).val() + "&sysOrganization.id=" + $("input[name=sysOrganizationId]").val() );
        } )
</script> 
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm"  onsubmit="return navTabSearch(this);" action="sys-permission/sys-permission!search.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="" />
		<input type="hidden" name="sysOrganization.id" value="${sysOrganization.id}"/>
		<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		    <input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		    <input type="hidden" id="isOrder" name="isOrder" value="" />
		    <input type="hidden" id="orderAsc" name="sysOrderByInfo.orderAsc" value="${sysOrderByInfo.orderAsc}" />
		<input type="hidden" name="sysOrganizationId" value="${sysOrganization.id}"/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<s:property value="getText('operName')"/>：
						<select name="query.eq_sysOperate">
				    		<option value=""><s:property value="getText('allOper')"/></option>
				    		<c:forEach items="${sysOperateList}" var="sysOperateList" >
				    			<option value=<c:out value="${sysOperateList.id}" />><c:out value="${sysOperateList.operateName}" /></option>
				    		</c:forEach>
			    		</select>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/></button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-permission/sys-permission!toAdjustPage.do"}.isEmpty()'>
						<!-- OPIDs : Operate Permission IDs ,存放的是当前机构已经有的操作权限ID -->
						<input type = "hidden" id = "hiddenOPIDs" name = "OPIDs" value = " <s:property value="#attr.OPIDs" /> " />
						<!-- OPNames : Operate Permission Names ,存放的是当前机构已经有的操作权限名字（无作用，调试时用） -->
						<input type = "hidden" readonly = "readonly" size = "100" value = " <s:property value="#attr.OPNames" /> " />
						<!--  -->
						<a class="add" id="aOPIdEdit" target="dialog" resizable=true drawable=true title="分配权限" width="400" height="480"> <span>分配权限</span> </a> 
						<!--<a class="add" href="sys-permission/sys-permission!toAdjustPage.do?sysOrganization.id=${sysOrganization.id}" target="navTab" rel="adjustPage" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('assignStricts')"/></span></a>
					--></s:if>				
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-permission/sys-permission!delper.do"}.isEmpty()'>
						<a title="<s:property value="getText('batchDeleteTips')"/>" target="removeSelected" href="sys-permission/sys-permission!multipleDelete.do?sysOrganization.id=${sysOrganization.id}" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
					</s:if>
				</li>
				<!-- <li><a class="edit" href="sys-permission/sys-permission!toEditPage.do?sysPermission.id={sid_sysPer}" target="navTab"><span>修改</span></a></li> 
				<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>-->
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200" orderField="sysOrganization.name"><s:property value="getText('reset')"/><s:property value="getText('orgName')"/></th>
					<th width="200" orderField="desiOperateId"><s:property value="getText('orgName')"/>指定可见机构</th>
					<th width="200" orderField="sysOperate.operateName">操作名称</th>
					<th width="200" orderField="scope">权限范围</th>
					
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${sysPermissionList}" var="sysPermission" >
				<tr target="sid_sysPer" rel="${sysPermission.id}" >
					<td><input name="ids" value="${sysPermission.id}" type="checkbox"></td>
					<td><c:out value="${sysPermission.sysOrganization.name}"></c:out></td>
					<td>
						<c:if test="${sysPermission.desiOperateId == -1}">
							本机构
	    				</c:if>
	    				<c:if test="${sysPermission.desiOperateId != -1}">
							<c:forEach items="${sysOrganizationList}" var="sysOrganization" >
		    					<c:if test="${sysPermission.desiOperateId==sysOrganization.id}">
		    						<c:out value="${sysOrganization.name}"/>
		    					</c:if>
		    				</c:forEach>
	    				</c:if>
					</td>
					<td><c:out value="${sysPermission.sysOperate.operateName}"></c:out></td>
					<td>
						<c:if test="${sysPermission.scope==1}">
	    					本机构可见
	    				</c:if>
	    				<c:if test="${sysPermission.scope==2}">
	    					本机构和下属可见
	    				</c:if>
					</td>
					
					<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-permission/sys-permission!delper.do"}.isEmpty()'>
							<a class="btnDel" href="sys-permission/sys-permission!delPer.do?sysPermission.id=${sysPermission.id}&sysOrganization.id=${sysOrganization.id}" target="navTabTodo" title="确定要删除吗?"></a>
						</s:if>
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-permission/sys-permission!toEditPage.do"}.isEmpty()'>
							<a class="btnEdit" href="sys-permission/sys-permission!toEditPage.do?sysPermission.id=${sysPermission.id}" target="navTab" messageHint="<s:property value='getText("editLeaveTips")' />" rel="new" title="修改" ></a>
						</s:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<!--<select class="combox" name="numPerPage" change="navTabPageBreak">
					<option value="20"><a href="">20</a></option>
					<option value="10">10</option>
					<option value="5">5</option>
					<option value="200">200</option>
				</select>-->
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span>条，共<c:out value='${sysPageInfo.maxCount}'/>条</span>
			</div>
			<div class="pagination" targetType="navTab"
			     totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
			     numPerPage="<c:out value='${numPerPage}'/>"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
</div>
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
