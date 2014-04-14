<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(function(){

	
		$("a[mark=edit]").click(function(){
			alert();
		})
	})
</script>
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-system/sys-system!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar" id="userlistJSP">
			<table class="searchContent">
				<tr>
					<td>
						<s:property value="getText('systemName')"/>
					</td>
					<td>
						<input type="text" name="query.like_name" value="${query.like_name}" id="a"/>
					</td>
					<td>
						<s:property value="getText('systemDescription')"/>
					</td>
					<td>
						<input type="text" name="query.like_description" value="${query.like_description}" id="b"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('search')"/></button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="resetId" onclick='$("#userlistJSP").find(".searchContent").find(":input").val("")' cape=reset><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="sys-system/sys-system!add.do?orderBlock=${orderBlock}&orderMethod=${orderMethod}&numPerPage=${numPerPage}&${strQuery}" rel="add" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('add')"/></span></a></li>
				<li>
					<a title="<s:property value='getText("batchDeleteTips")'/>" target="removeSelected" href="sys-system/sys-system!multipleDelete.do?orderBlock=${orderBlock}&orderMethod=${orderMethod}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
				</li>
				<!--<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>-->
			</ul>
		</div>
		<table class="table" layouth="143">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200" orderField="name"
						<s:if test='orderBlock=="name" && orderMethod==1'>class="desc"</s:if><s:elseif test='orderBlock=="name" && orderMethod==0'>class="asc"</s:elseif>>
						<s:property value="getText('systemName')"/>
					</th>
					<th width="200" orderField="description"><s:property value="getText('systemDescription')"/></th>
					<th width="100"><s:property value="getText('operate')"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${systemList}" var="sysSystem">
				<tr target="sid_user" rel="${sysSystem.id}" >
					<td class="contentCenter"><input name="ids" value="${sysSystem.id}" type="checkbox"></td>
					<td><c:out value="${sysSystem.name}"></c:out></td>
					<td><c:out value="${sysSystem.description}"></c:out></td>
					<td style="text-align: right;">
						<a class="btnDel" href="sys-system/sys-system!delete.do?method=remove&sysSystem.id=${sysSystem.id}&orderBlock=${orderBlock}&orderMethod=${orderMethod}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" target="navTabTodo" title="<s:property value='getText("deleteTips")'/>"></a>
						<a class="btnEdit" mark="edit" href="sys-system/sys-system!edit.do?sysSystem.id=${sysSystem.id}&orderBlock=${orderBlock}&orderMethod=${orderMethod}&pageNum=${pageNum}&numPerPage=${numPerPage}&${strQuery}" target="navTab" rel="edit" messageHint="<s:property value='getText("editLeaveTips")' />" title="<s:property value='getText("modify")'/>"></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')"/></span>
				<!--<select class="combox" name="numPerPage" change="navTabPageBreak">
					<option value="20"><a href="">20</a></option>
					<option value="10">10</option>
					<option value="5">5</option>
					<option value="200">200</option>
				</select>-->
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
