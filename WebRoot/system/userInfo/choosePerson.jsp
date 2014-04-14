<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="page" id="choosePerson">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return dialogSearch(this);" method="post"
			action="person!chooseActivePerson.do?currentTag=${currentTag}&pageTag=${pageTag}&chooseFlag=${chooseFlag}">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
			<table id="searchArea" class="searchContent">
				<tr>
					<td>姓名</td>
					<td><input type="text" name="query.like_name" value="${query.like_name}" /></td>
					<td>职位</td>
					<td><input size="58" name="query.like_professional" value="${query.like_professional}" /></td>
					
				</tr>
				<tr>
					<td>性别</td>
					<td>
						<s:select list="#{'男':'男','女':'女'}" headerKey="" headerValue="请选择"
									name="query.eq_sex"></s:select>
					</td>
					<td>出生日期</td>
					<td><span style="float:left; width: 15px; margin:0 7px; padding-top:5px;">从</span>
					<input type="text" class="date" name="query.dtgteq_birthday" style="float:left;width:112px;"
								yearstart="-70" yearend="20" value="${query.dtgteq_birthday}" /><a class="inputDateButton"
								href="javascript:void(0)">选择</a>
						<span style="float:left; width: 15px; margin:0 7px; padding-top:5px;">到</span>
					<input type="text" class="date" name="query.dtlteq_birthday" style="float:left;width:112px;"
								yearstart="-70" yearend="20" value="${query.dtlteq_birthday}" /><a class="inputDateButton"
								href="javascript:void(0)">选择</a>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="resetForm()">重置</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" layoutH="174">
			<thead>
				<tr>
					<th style="width:5%;">
						<s:if test='!chooseFlag.equals("single")'>
						<input type="checkbox" group="ids" class="checkboxCtrl" />
						</s:if>
					</th>
					<th style="width:15%;">姓名</th>
					<th style="width:5%;">性别</th>
					<th style="width:9%;">民族</th>
					<th style="width:12%;">出生日期</th>
					<th style="width:20%;">所属单位</th>
					<th style="width:36%;">职位</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="peopleList" var="event">
				<tr target="sid_user">
					<td>
						<s:if test='!chooseFlag.equals("single")'>
						<input name="ids" type="checkbox" />			
						</s:if>
						<s:elseif test='chooseFlag.equals("single")'>
						<input name="ids" type="radio" value="${hrid}" content="${name}" />
						</s:elseif>			
					</td>
					<td>${name} </td>
					<td>${sex}</td>
					<td>${nation}</td>
					<td><s:date name="birthday" format="yyyy-MM-dd" /></td>
					<td>
						<s:if test='sysOrganization != null' >${sysOrganization.name}</s:if>
					</td>
					<td>${professional}</td>
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
				          onchange="dialogPageBreak({numPerPage:this.value})"></s:select>
				<span><s:property value="getText('column')"/><s:property value="getText('total')"/><c:out value='${sysPageInfo.maxCount}'/><s:property value="getText('column')"/></span>
			</div>
			<div class="pagination" targetType="dialog"
			     totalCount="<c:out value='${sysPageInfo.maxCount}'/>"
			     numPerPage="<c:out value='${numPerPage}'/>"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
	</div>
	<form class="pageForm required-validate">
		<div class="formBar" >
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="selectPerson">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="Button" onclick="$.pdialog.closeCurrent();">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript">
	function resetForm(){
		$("#choosePerson").find("#searchArea :input").val("");
	}
	
	$(function(){
		var chooseFlag = '${chooseFlag}';
		var divisionId = '${pageTag}';
		var positionTag = '${currentTag}';
		//确定产生的事件
		$("#choosePerson").find("#selectPerson").click(function(){
			$("#"+divisionId+"").find("#"+positionTag+" #selectedId").val("");
			$("#"+divisionId+"").find("#"+positionTag+" #selectedName").val("");
			if(chooseFlag == "single"){
				var choosedVal = $("#choosePerson").find(":checked").val();
				var choosedContent = $("#choosePerson").find(":checked").attr("content");
				
				$("#"+divisionId+"").find("#"+positionTag+" #selectedId").val(choosedVal);
				$("#"+divisionId+"").find("#"+positionTag+" #selectedName").val(choosedContent);
			}
			$.pdialog.closeCurrent();
		})
		
	})
	
</script>