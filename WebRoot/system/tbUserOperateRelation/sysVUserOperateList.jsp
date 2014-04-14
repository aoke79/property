<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="cm-user-operate-relation/cm-user-operate-relation!searchAllOperate.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="" />
		<input type="hidden" name="userId" value="${userId}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<s:property value="getText('operName')"/>
<!--  操作名称：--><input type="text" name="query.operateName" value="${query.operateName}"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/>
<!-- 检索 --></button></div></div>
					</li>
					<li>
						<div class="button">
						   <div class="buttonContent">
						   	<button type="reset"><s:property value="getText('reset')"/>
<!--  重置--></button>
						   </div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<%--<div class="panelBar">
			<ul class="toolBar">
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!add.do"}.isEmpty()'>
						<a class="add" href="sys-operate/sys-operate!add.do" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('add')"/>
<!-- 添加 --></span></a>
					</s:if>
				</li>
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!multipleDelete.do"}.isEmpty()'> 
						<a title="<s:property value="getText('')"/>
<!--  确实要删除这些记录吗?-->" target="removeSelected" href="sys-operate/sys-operate!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/>
<!--  批量删除--></span></a>
					</s:if>
				</li>
				<li class="line">line</li>
				<!-- 
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>
				 -->
			</ul>
		</div>
		--%><table class="table" layouth="114">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<!-- <th width="70">系统编号</th> -->
					<th width="70" orderField="operateName" class="asc"><s:property value="getText('operName')"/>
<!-- 操作名称 --></th>
					<th width="70"><s:property value="getText('urlAction')"/>
<!-- 触发的URL串 --></th>
					<th width="70"><s:property value="getText('navigationInfo')"/>			
					<th width="70"><s:property value="getText('validity')"/>
<!--  有效性--></th>
				
				</tr>
			</thead>
			<tbody>
			
					<s:iterator value="sysVUserOperateList">
					<tr>
					<td><input name="ids" value="${sysOperate.id}" type="checkbox"></td>
					<td>${operateName }</td>
					<td>${operateAction}</td>
					<td>${menuInfo}</td>
					
					<td>
						<s:if test='flag.equals("1")'>
	  						<s:property value="getText('valid')"/>
<!-- 有效 -->
	  					</s:if>
	  					<s:elseif test='flag.equals("0")'>
	  						<s:property value="getText('invalid')"/>
	  					</s:elseif>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')"/><!-- 显示 --></span>
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span><s:property value="getText('column')"/><!-- 条， --><s:property value="getText('total')"/><!-- 共 --><c:out value='${sysPageInfo.maxCount}'/><s:property value="getText('column')"/><!-- 条 --></span>
			</div>
			<div class="pagination" targetType="navTab"
			     totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
			     numPerPage="<c:out value='${numPerPage}'/>"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div>
	</div>
</div>