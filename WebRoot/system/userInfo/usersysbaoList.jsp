<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
<!--
	$(function(){
		$("#subUser").click(function(){
			alert($("#hiddenFunOrgId").val());
		})
		
	})
	
	$(function(){
		$("#userlistJSP").find("button[cape=reset]").click(function(){
			$("#userlistJSP").find("#searchTable").find("input").val("");
			$("#userlistJSP").find("#searchTable").find("select").val("");
		})
	})
//-->
</script>

<div class="page" id ="userlistJSP">
	<div class="pageHeader">
	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="user-info/cm-user!usersyslist.do" method="post">
		
		<input type="hidden" name="pageNum" value="1"/>		
		<input type="hidden" name="numPerPage" value="<s:property value="#attr.numPerPage"/>" />
		<input type="hidden" id="orderField" name="orderBlock" value="" />
		<input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		<div class="searchBar">
	
		<table class="searchContent">
				<tr>
					<td>
					<label>登录名：</label>
						<input name="query.like_loginName" type="text" size="30" value="${query.like_loginName }"/>
					</td>
					<td>
					<label><s:property value="getText('name')"/>：</label>
						<input name="query.like_name" type="text" size="30" value="${query.like_name }"/>
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
								<button onclick='$("#userlistJSP").find(".searchContent").find(":input").val("")' cape=reset>
										<s:property value="getText('reset')" />
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		</s:form>
	</div>
	
	<div class="pageContent">
		<table class="table" layouth="114">
			<thead>
				<tr>
					<th width="5" style="text-align: center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th style="display:none">用户编号</th>
		    		<th width="33" orderField="loginName"><s:property value="getText('loginAccount')"/></th>
		    		<th width="33" orderField="name"><s:property value="getText('name')"/></th>
		    		<th width="17" orderField="">业务系统</th>
					<th width="15"><s:property value="getText('oper')"/></th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#attr.listUser" id="user">
    			<tr target="user_id" rel="<s:property value="#user['userId']"/>" >
    				<td  style="text-align: center"><input name="ids" value="<s:property value="#user['userId']"/>" type="checkbox"></td>
    				<td  style="display:none"><s:property value="#user['userId']"/></td>
    				<td><s:property value="#user['loginName']"></s:property></td>
    				<td><s:property value="#user['name']"/></td>
    				<td>
    					<s:if test='!businessSystemSet.isEmpty()'>
    					<s:iterator value="#user['businessSystemSet']" var="system">${system.asname}&nbsp;</s:iterator>
    					</s:if>
    				</td>
					<td style="text-align: center;">
                      <a target="dialog" title="业务系统" mask="true" width="260" height="400"
							href="sec-baopesystem/sec-baopesystem!userRelation.do?cmUser.userId=<s:property value="#user['userId']"/>">配置</a>
					</td>
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

