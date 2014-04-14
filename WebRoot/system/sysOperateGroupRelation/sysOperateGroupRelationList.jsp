<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：操作组关系列表页面，增、删、改、查操作组关系，为操作组分配操作
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<link href="<%=basePath%>/css/user.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/js/jquery.caret.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.ipaddress.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/user-validate.js"></script>
<script>
$( "#aOOGIdEdit" ).click( function () {
                   $( this ).attr( "href" , "sys-operate-group-relation/sys-operate-group-relation!toAddPage.do?OOGIDs=" 
                   						+$( "#hiddenOOGIDs" ).val() + "&operateGroupId=" + $("input[name=operateGroupId]").val() );
        } )
</script> 
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-operate-group-relation/sys-operate-group-relation!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderField" value="${orderField }" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod }" />
		<input type="hidden" name="operateGroupId" value="${operateGroupId }" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						操作名称：<input type="text" name="query.like_operateName" value="${query.like_operateName }"/>
					</td>
					<!-- 
					<td>
						描述：<input type="text" name="query." />
					</td> 
					-->
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</li>
					<li>
						<div class="button">
						   <div class="buttonContent">
						     <button type="reset">重置</button>
						   </div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<input type = "hidden" id = "hiddenOOGIDs" name = "OOGIDs" value = " <s:property value="#attr.OOGIDs" /> " />
	              	<input id = "funOrgIdEdit"  type = "hidden" readonly = "readonly" size = "30" value = " <s:property value="#attr.OOGNames" /> " />
	              	<a class="add" id="aOOGIdEdit" target="dialog" resizable=true drawable=true title="请选择操作" width="400" height="480"> <span>为操作组分配操作</span> </a>      
				</li>
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group-relation/sys-operate-group-relation!multipleDelete.do"}.isEmpty()'>
						<a title="确实要删除这些记录吗?" target="removeSelected" href="sys-operate-group-relation/sys-operate-group-relation!multipleDelete.do" class="delete"><span>批量删除</span></a>
					</s:if> 
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200">操作组名称</th>
					<th width="200">操作名称</th>
					<th width="200">有效性</th>
					<th width="200">备注</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${sysOperateGroupRelationList}" var="sysOperateGroupRelation" >
				<tr target="sid_sysPer" rel="${sysOperateGroupRelation.id.operateGroupId}&sysRelationRoleOperateId.operateId=${sysOperateGroupRelation.id.operateId}" >
					<td><input name="ids" value="${sysOperateGroupRelation.id.operateGroupId}&sysRelationRoleOperateId.operateId=${sysOperateGroupRelation.id.operateId}" type="checkbox"></td>
					<td><c:out value="${sysOperateGroupRelation.sysOperateGroup.name}"></c:out></td>
					<td><c:out value="${sysOperateGroupRelation.sysOperate.operateName}"></c:out></td>
					<td>
						<s:if test='#attr.sysOperateGroupRelation.state.equals("1")'>
	  						有效
	  					</s:if>
	  					<s:elseif test='#attr.sysOperateGroupRelation.state.equals("0")'>
	  						无效
	  					</s:elseif>
					</td>
					<td><c:out value="${sysOperateGroupRelation.opegrComment}"></c:out></td>
					<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group-relation/sys-operate-group-relation!delPer.do"}.isEmpty()'>
							<a class="btnDel" href="sys-operate-group-relation/sys-operate-group-relation!delPer.do?sysOperateGroupRelationId.operateGroupId=${sysOperateGroupRelation.id.operateGroupId}&sysOperateGroupRelationId.operateId=${sysOperateGroupRelation.id.operateId}" target="navTabTodo" title="确定要删除吗?"></a>
						</s:if> 
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate-group-relation/sys-operate-group-relation!toEditPage.do"}.isEmpty()'>
							<a class="btnEdit" href="sys-operate-group-relation/sys-operate-group-relation!toEditPage.do?sysOperateGroupRelation.id.operateGroupId=${sysOperateGroupRelation.id.operateGroupId}&sysOperateGroupRelation.id.operateId=${sysOperateGroupRelation.id.operateId}" target="navTab" title="修改" messageHint="<s:property value='getText("editLeaveTips")' />"></a>
						</s:if> 
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
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