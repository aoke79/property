<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
    

    <div class="page">
	<div class="pagerForm">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="user-info/sys-login-info!search.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>		
		<input type="hidden" name="numPerPage" value="<s:property value="#attr.numPerPage"/>" />
		<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
		
			<table class="searchContent">
				<tr>
					<td><s:property value="getText('userName')"/>:</td>
					<td>
						<input name="name" type="text" size="30" value="${search.name }"/>
					</td>
					<td><s:property value="getText('loginDate')"/>：</td>
					<td>
						<p><input name="query.dteq_loginDate" type="text" class="date" yearstart="-100" size="27" readonly="readonly" value="${query.dteq_loginDate }" style=" float:left"/>
						<a class="inputDateButton" href="javascript:void(0)">选择</a></p>
					</td>
					</tr>
					<tr>
					<td><s:property value="getText('lastLoginDate')"/>：</td>
					<td>
						<p><input name="query.dteq_lastLogin" readonly="readonly" type="text" class="date" size="27" style=" float:left" value="${query.dteq_lastLogin }"/>
						<a class="inputDateButton" href="javascript:void(0)">选择</a></p>
					</td>
					<td><s:property value="getText('online')"/>：</td>
					<td>
						<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
						<s:select list="#{'1':'at Post','0':'leave Post'}" listKey="key" listValue="value"  name="query.eq_state" headerKey="" headerValue=""></s:select>
					</s:if>
					<s:else>
						<s:select list="#{'1':'在职','0':'离职'}" listKey="key" listValue="value"  name="query.eq_state" headerKey="" headerValue=""></s:select>
					</s:else>
					</td>
				</tr>
			</table>
			
			<div class="subBar">
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
	
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="<s:property value="getText('excel')"/>"><span><s:property value="getText('excel')"/></span></a></li>
			</ul>
		</div>
		<table class="table" layouth="162">
		<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
		<thead>
				<tr>
					<th orderField="cmUser.name"><s:property value="getText('userName')"/></th>
					<th orderField="loginDate"><s:property value="getText('loginDate')"/></th>
					<th orderField="lastLogin"><s:property value="getText('lastLoginDate')"/></th>
					<th orderField="loginCount"><s:property value="getText('loginCounts')"/></th>
					<th orderField="onlineState"><s:property value="getText('online')"/></th>
				</tr>
			</thead>
		</s:if>
		<s:else>
		<thead>
				<tr>
					<th orderField="cmUser.name"><s:property value="getText('userName')"/></th>
					<th orderField="loginDate"><s:property value="getText('loginDate')"/></th>
					<th orderField="lastLogin"><s:property value="getText('lastLoginDate')"/></th>
					<th orderField="loginCount"><s:property value="getText('loginCounts')"/></th>
					<th orderField="onlineState"><s:property value="getText('online')"/></th>
				</tr>
			</thead>
		</s:else>
			
			<tbody>
			<s:iterator value="#attr.listSysLoginInfo" id="login">
    			<tr target="login_id" rel="<s:property value="#login['id']"/>" >
    				<td><s:property value="#login.cmUser.name"/></td>
    				<td><s:property value="#login['loginDate']"/></td>
    				<td><s:property value="#login['lastLogin']"/></td>
    				<td><s:property value="#login['loginCount']"/></td>
    				<td>
    				<s:if test='#login["onlineState"].equals("1")'>在线</s:if>
    				<s:else>离线</s:else></td>
    			</tr>
    		</s:iterator>
			</tbody>
		</table>
		
		<div class="panelBar">
			<div class="pages">
				<span><s:property value="getText('display')"/></span>				
				<s:select list='#{"20":"20","10":"10","5":"5"}'
				          name="numPerPage" cssClass="paginationStyle"
				          onchange="navTabPageBreak({numPerPage:this.value})"></s:select>
				<span><s:property value="getText('column')"/><s:property value="getText('total')"/><s:property value="#attr.sysPageInfo.maxCount"/><s:property value="getText('column')"/></span>
			</div>
			
			<div class="pagination" targetType="navTab"  
				 totalCount="<s:property value="#attr.sysPageInfo.maxCount"/>" 
				 numPerPage="<s:property value="#attr.numPerPage"/>" 
				 pageNumShown="10" currentPage="<s:property value="#attr.sysPageInfo.currentPage"/>">				 
		</div>
	</div>
</div>
</div>
	