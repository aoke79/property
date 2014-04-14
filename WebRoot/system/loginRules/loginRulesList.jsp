<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
 
$(function(){
var c = '${addFlag}'

if(c != 0){
$("#addPage").text("");
}

   

})

//-->
</script>

<div class="page" id ="userlistJSP">
	<div class="pageHeader">

	<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="" method="post">

		<div class="searchBar">
		<table class="searchContent" width="100%" border="0">
				<tr>
					<td></td>
					<td>
					</td>
					<td>
					</td>					<td>
					</td>

					<td>
					</td>
<td></td>
					<td>&nbsp;
					</td>
				</tr>
				
			</table>	
	
			<div class="subBar"><!--
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
			--></div>
		</div>
		</s:form>
	</div>
	
	<div class="pageContent">
	
		<div class="panelBar">
			<ul class="toolBar">
				<li id="addPage">
				
				<a class="add" href="system/loginRules/loginRulesAdd.jsp" target="navTab"><span><s:property value="getText('add')"/></span></a>
				
				</li>
</ul>
		</div>
		<table class="table" layouth="166">
		
			<thead>
				<tr>
					<th width="20%">限制次数</th>
		    		<th width="20%" >多久</th>
		    		<th width="20%" >时间类型</th>
		    		<th width="20%" >规则是否执行</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="loginRulesList" >
    			<tr target="user_id"  >
    				<td  style="text-align: center">${numbers }</td>
    				<td  style="text-align: center">${times }</td>
    				<td style="text-align: center">${timeT }</td>
    				<td style="text-align: center">${exT }</td>

					<td style="text-align: center;">
	<a class="btnEdit" href="user-info/login-rules!modifyPage.do?loginRules.id=${id}" target="navTab" title="<s:property value="getText('modify')"/>"></a>
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		
		<%--<div class="panelBar">
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
	</div>--%>
</div>
</div>

