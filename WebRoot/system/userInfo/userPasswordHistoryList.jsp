<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<div class="page">
	<div class="pagerForm">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="user-info/cm-user-password-history!search.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>		
		<input type="hidden" name="numPerPage" value="<s:property value="#attr.numPerPage"/>" />
		<input type="hidden" name="orderField" value="" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户名：</td>
					<td>
						<input name="name" type="text" size="30" value="${search.name}"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
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
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" layouth="124">
			<thead>
				<tr>
    			<th style="display:none">编号</th>
    			<th>用户名</th>
    			<th>历史密码记录</th>
    			<th>使用日期</th>    			
    		</tr>
				
			</thead>
			<tbody>
			<s:iterator value="#attr.listTbUserPasswordHistory" id="his">
    			<tr>
    				<td style="display:none"><s:property value="#his.id"/></td>
    				<td><s:property value="#his.cmUser.loginName"/></td>
    				<td><s:property value="#his.password"/></td>
    				<td><s:property value="#his.usedate"/></td>
    			</tr>
    	</s:iterator>
			</tbody>
		</table>
		
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>				
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span>条，共<s:property value="#attr.sysPageInfo.maxCount"/>条</span>
			</div>
			
			<div class="pagination" targetType="navTab"  
				 totalCount="<s:property value="#attr.sysPageInfo.maxCount"/>" 
				 numPerPage="<s:property value="#attr.numPerPage"/>" 
				 pageNumShown="10" currentPage="<s:property value="#attr.sysPageInfo.currentPage"/>">				 
		</div>
	</div>
</div>
</div>

