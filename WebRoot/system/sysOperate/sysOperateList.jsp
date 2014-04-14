<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：操作列表页面，增、删、改、查操作
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page" id="operateList">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-operate/sys-operate!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td class="seTitle">操作名称</td>
					<td class="seBody">
						<input size="20" name="query.like_operateName"
							value="${query.like_operateName}" />
					</td>
					<td class="seTitle">触发的URL串</td>
					<td class="seBody" id="searchType">
						<input size="20" name="query.like_operateAction"
							value="${query.like_operateAction}" />
					</td>
					<td class="seTitle">菜单名称</td>
					<td class="seBody" id="searchType">
						<input size="20" name="names"
							value="<s:property value="#attr.names"/>" />
					</td>
<%-- 	<s:if test="'超级管理员'.equals(#attr.userName)">
					<td class="seTitle">子系统</td>
					<td class="seBody" id="searchType">
						<s:select list="#attr.sysSystemList" name="query.like_subsystemId" listKey="id" listValue="name" headerKey="" headerValue="全部"/>
					</td>
		</s:if>

 --%>	  
					<td class="seZone">
						<div class="settingP">
						<div class="buttonActive rightExpand"><div class="buttonContent"><button type="submit" cape="search">检索</button></div></div>
						<div class="buttonActive rightExpand"><div class="buttonContent"><button type="button" cape="reset">重置</button></div></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!add.do"}.isEmpty()'>
						<a class="add" href="sys-operate/sys-operate!add.do?orderBlock=${orderBlock}&orderMethod=${orderMethod}&numPerPage=${numPerPage}&${strQuery}" rel="adddafe3" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><!-- 添加 --><s:property value="getText('add')"/></span></a>
					</s:if>
				</li>
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!multipleDelete.do"}.isEmpty()'> 
						<a title="<s:property value='getText("batchDeleteTips")'/>" target="removeSelected" href="sys-operate/sys-operate!multipleDelete.do?orderBlock=${orderBlock}&orderMethod=${orderMethod}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" class="delete"><span><!--  批量删除--><s:property value="getText('batchDelete')"/></span></a>
					</s:if>
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="120">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<!-- <th width="70">系统编号</th> -->
					<th width="110" orderField="operateName" class="asc"><!-- 操作名称 --><s:property value="getText('operName')"/></th>
					<th width="80"><!-- 触发的URL串 --><s:property value="getText('urlAction')"/></th>
					<!-- <th width="70">是否是底层菜单</th>-->
					<th width="80"><!-- 父操作名称--><s:property value="getText('parentOperate')"/></th>  
					<th width="80"><!-- 菜单名称 --><s:property value="getText('menuName')"/></th>
					<!--<th width="80"> 子系统<s:property value="getText('subsystem')"/></th> -->
					<!-- <th width="70">等级</th>-->
					<th width="50"><!-- 有效性--><s:property value="getText('validity')"/></th>
					<th width="80"><!-- 操作 --><s:property value="getText('operate')"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${operateList}" var="sysOperate">
				<tr target="sid_user" rel="${sysOperate.id}" >
					<td><input name="ids" value="${sysOperate.id}" type="checkbox"></td>
					<!-- <td><c:out value="${sysOperate.systemId}"></c:out></td> -->
					<td><c:out value="${sysOperate.operateName}"></c:out></td>
					<td><c:out value="${sysOperate.operateAction}"></c:out></td>
					<!-- <td><c:out value="${sysOperate.ifFinallyMenu}"></c:out></td> -->
					<td><c:out value="${sysOperate.parentOperate.operateName}"></c:out></td>
					<td><c:out value="${sysOperate.sysMenu.name}"></c:out></td>
					<!-- <td><c:out value="${sysOperate.opeLevel}"></c:out></td> -->
                 <%--   <td>
	<s:select list="#attr.sysSystemList"  listKey="id"  value="#attr.sysOperate.subsystemId" listValue="name" headerKey="" headerValue="无" disabled="true" id="selectInfo"/>
 					</td>--%>
					<td>
						<!-- <c:out value="${sysOperate.flag}"></c:out> -->
						<s:if test='#attr.sysOperate.flag.equals("1")'>
	  						<!-- 有效 --><s:property value="getText('valid')"/>
	  					</s:if>
	  					<s:elseif test='#attr.sysOperate.flag.equals("0")'>
	  						<!-- 无效 --><s:property value="getText('invalid')"/>
	  					</s:elseif>
					</td>
					<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!delete.do"}.isEmpty()'>
							<a class="btnDel" href="sys-operate/sys-operate!delete.do?sysOperate.id=${sysOperate.id}&orderBlock=${orderBlock}&orderMethod=${orderMethod}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" target="navTabTodo" title="<s:property value="getText('deleteTips')"/>"></a>
						</s:if> 
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!edit.do"}.isEmpty()'>
							<a class="btnEdit" href="sys-operate/sys-operate!edit.do?sysOperate.id=${sysOperate.id}&orderBlock=${orderBlock}&orderMethod=${orderMethod}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" target="navTab" title="<s:property value="getText('modify')"/>" messageHint="<s:property value='getText("editLeaveTips")'/>"></a>
						</s:if> 
						<a class="btnLook" href="sys-operate/sys-operate!viewRole.do?sysOperate.id=${sysOperate.id}" target="navTab" title="查看用户"></a>
					</td>
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
<script>
	$(function(){
		//重置
		$("#operateList").find("button[cape=reset]").click(function(){
			$(".searchContent").find("input").val("");
			$(".searchContent").find("select").val("");
		})
			
	})
</script>