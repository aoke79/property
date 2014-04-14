
<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年5月
 	 * 最后更新时间：
 	 * 程序用途：序列号列表页面，增、删、改、查序列号
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
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sec-serialnumber/sec-serialnumber!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						单位标识:<input type="text" name="query.like_sdeptid" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/><!-- 检索 --></button></div></div>
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
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!add.do"}.isEmpty()'>
						<a class="add" href="sec-serialnumber/sec-serialnumber!add.do" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('add')"/><!-- 添加 --></span></a>
					</s:if>
				</li>
				<li>
					<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!multipleDelete.do"}.isEmpty()'> 
						<!--  确实要删除这些记录吗?-->
						<a title="<s:property value="getText('')"/>" target="removeSelected" href="sec-serialnumber/sec-serialnumber!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/><!--  批量删除--></span></a>
					</s:if>
				</li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200" orderField="sdeptid">单位标识</th>
					<th width="200" orderField="sespecidlid">SESPECIDLID</th>
					<th width="200" orderField="sdateformat">SDATEFORMAT</th>
					<th width="120" orderField="serial">序号</th>  
					<th width="100" orderField="serialnum">序号的位数</th>
					<th width="100" orderField="sersource">SERSOURCE</th>
					<th width="200">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${secSerialnumberList}" var="secSerialnumber">
				<tr target="sid_user" rel="${secSerialnumber.snid}" >
					<td><input name="ids" value="${secSerialnumber.snid }" type="checkbox"></td>
					<td>${secSerialnumber.sdeptid}</td>
					<td>${secSerialnumber.sespecidlid}</td>
					<td>${secSerialnumber.sdateformat}</td>
					<td>${secSerialnumber.serial}</td>
					<td>${secSerialnumber.serialnum}</td>
					<td>${secSerialnumber.sersource}</td>
					<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!delete.do"}.isEmpty()'>
							<a class="btnDel" href="sec-serialnumber/sec-serialnumber!delete.do?secSerialnumber.snid=${secSerialnumber.snid}" target="navTabTodo" title="<s:property value="getText('')"/><!-- 确定要删除吗? -->"></a>
						</s:if> 
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-operate/sys-operate!edit.do"}.isEmpty()'>
							<a class="btnEdit" href="sec-serialnumber/sec-serialnumber!edit.do?secSerialnumber.snid=${secSerialnumber.snid}" target="navTab" title="<s:property value="getText('modify')"/><!-- 修改 -->" messageHint="<s:property value='getText("editLeaveTips")'/>"></a>
						</s:if> 
					</td>
				</tr>
			</c:forEach>
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
