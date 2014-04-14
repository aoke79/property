<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
$( "#aUOIdEdit" ).click( function () {
                   $( this ).attr( "href" , "cm-user-operate-relation/cm-user-operate-relation!goView.do?UOIDs=" 
                   						+$( "#hiddenUOIDs" ).val() + "&userId=" + $("input[name=userId]").val() );
        } )
</script> 
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);"
			action="cm-user-operate-relation/cm-user-operate-relation!search.do" method="post">
			<input type="hidden" name="pageNum" value="${sysPageInfo.currentPage}"/>	
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		    <input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		    <input type="hidden" id="isOrder" name="isOrder" value="" />
		    <input type="hidden" id="orderAsc" name="sysOrderByInfo.orderAsc" value="${sysOrderByInfo.orderAsc}" />
			<input type="hidden" name="userId" value="${userId}">
			
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<s:property value="getText('operName')"/>：
							<input type="text" name="query.operateName" value="${query.operateName}"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">
										<s:property value="getText('search')"/>
									</button>
								</div>
							</div>
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
			        <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<!-- UOIDs : User Operate IDs ,存放的是当前用户已经有的操作权限ID -->
					<input type = "hidden" id = "hiddenUOIDs" name = "UOIDs" value = " <s:property value="#attr.UOIDs" /> " />
              		<!-- UONames : User Operate Names ,存放的是当前用户已经有的操作权限名字（无作用，调试时用） -->
              		<input id = "funOrgIdEdit"  type = "hidden" readonly = "readonly" size = "30" value = " <s:property value="#attr.UONames" /> " />
              		<a class="add" id="aUOIdEdit" target="dialog" resizable=true drawable=true title="<s:property value="getText('assignOper')"/>" width="400" height="480"> <span><s:property value="getText('assignOper')"/></span> </a> 
					<!--<a class="add" rel="add"  href="cm-user-operate-relation/cm-user-operate-relation!goView.do?userId=${userId}" target="navTab" messageHint="<s:property value='getText("addLeaveTips")' />"><span><s:property value="getText('assignOper')"/></span>
					</a>
					--></s:if>
				</li>
				<li>
				    <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
					<a title="<s:property value="getText('batchDeleteTips')"/>" target="removeSelected" href="cm-user-operate-relation/cm-user-operate-relation!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
					</s:if>
				</li>
				<li class="line">
					line
				</li>
				<li>
				   <!-- 
					<a class="icon" href="testimport.html" target="dialog" width="300"
						height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span>
					</a>
					 -->
				</li>
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
                    <th width="30">
                    <input type="checkbox" group="ids" class="checkboxCtrl">
                    </th>
					<th width="200">
						<s:property value="getText('userName')"/>
					</th>
					<th width="200" orderField="sysOperate.operateName">
						<s:property value="getText('operName')"/>
					</th>
					<th width="70">
					   <s:property value="getText('operate')"/>
					</th>
				</tr>
			</thead>
			<tbody>

               <s:if test="tbUserOperateRelationList.size>0">
				<s:iterator value="tbUserOperateRelationList">
					<tr target="sid_user" rel="${id.userId}&cmUserOperateRelationId.id=${id.id}" >
					    <td>
					    <input name="ids" value="${id.userId}&cmUserOperateRelationId.id=${id.id}" type="checkbox">
					    </td>
						<td>
							${cmUser.name}
						</td>
						<td>
							${sysOperate.operateName}
						</td>
						<td style="text-align: right;">
						<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
						<a class="btnDel" href="cm-user-operate-relation/cm-user-operate-relation!delTbUserOperateRelation.do?cmUserOperateRelationId.userId=${id.userId}&cmUserOperateRelationId.id=${id.id}&pageNum=${paging.curPage}" target="navTabTodo" title="删除"></a>
						</s:if>
						<!-- 
						<a class="btnEdit" href="cm-user-operate-relation/cm-user-operate-relation!toEditPage.do?cmUserOperateRelationId.userId={sid_user}" target="navTab" title="修改"></a>
						 -->
					    </td>
					</tr>
				</s:iterator>
              </s:if>
              <s:else>
              <tr target="sid_user" rel="${id.userId}&cmUserOperateRelationId.id=${id.id}" >
						<td colspan="4">
						   <s:property value="getText('userNoOperate')"/>
						</td>
					</tr>
              </s:else>
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
			     totalCount="${sysPageInfo.maxCount}"
			     numPerPage="${numPerPage}"
			     pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div> 
	</div>
</div>
