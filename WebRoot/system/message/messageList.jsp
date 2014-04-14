<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(function(){
		getMessageCount();
	});
</script>
<style>
.trend {
	width: 10%;
	padding-left: 1em;
}
.trendBody {
	width: 25%;
	padding-left: 5px;
}
.elements {
	float: right;
}
.balanceE {
	margin-left:0.5em;
}
</style>
<div class="page">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return navTabSearch(this);" action="sys-message/system-message!list.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td class="trend">
						<span>消息发起时间</span>
					</td>
					<td class="trendBody">
						<input onkeydown="return false;" class="date" name="query.dteq_sendDate"
							style="float:left;width:112px;" yearstart="-40" yearend="50"
							value="${query.dteq_sendDate}" /><a class="inputDateButton"
							href="javascript:void(0)">选择</a>
					</td>
					<td class="trend">
						<span>消息类型</span> 
					</td>
					<td class="trendBody">
						<s:select list='#{"S":"系统消息","B":"业务变更消息","E":"待办事宜消息"}'
								  headerKey="" headerValue="全部消息"
								  name="query.eq_messageType"></s:select>
					</td>
					<td>
						<div class="elements">
						<div class="buttonActive balanceE"><div class="buttonContent"><button type="submit">检索</button></div></div>
						<div class="buttonActive balanceE"><div class="buttonContent"><button type="button" cape="reset">重置</button></div></div>
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
				<!--<li><a class="add" href="sys-message/system-message!add.do" target="navTab" rel="add"><span><s:property value="getText('add')"/></span></a></li>
				--><li>
					<a title="<s:property value='getText("batchDeleteTips")'/>" target="removeSelected" href="sys-message/system-message!multipleDelete.do" class="delete"><span><s:property value="getText('batchDelete')"/></span></a>
				</li>
				<li class="line">line</li>
				<li><a class="icon" href="sys-message/system-message!toSendMessagePage.do" target="navTab" rel="sendMessage" name="permitControl" rel="sendMessage"><span><s:property value="getText('multimessage')"/></span></a></li>

			</ul>
		</div>
		<table class="table" layouth="120">
			<thead>
				<tr>
					<th width="33"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="150"><s:property value="getText('messageTitle')"/></th>
					<th width="90"><s:property value="getText('sendUser')"/></th>
					<th width="120"><s:property value="getText('sendTime')"/></th>
					<th width="120"><s:property value="getText('messageType')"/></th>
					<th width="80"><s:property value="getText('msgStatus')"/></th>
					<th width="120"><s:property value="getText('msgReceiver')"/></th>
					<th width="150"><s:property value="getText('oper')"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${commonList}" var="commonMessage">
				<tr target="sid_user" rel="${commonMessage.messageId}" >
					<td class="contentCenter"><input name="ids" value="${commonMessage.messageId}" type="checkbox"></td>
					<td><c:out value="${commonMessage.messageTitle}"></c:out></td>
					<td><c:out value="${commonMessage.messageLauncher}"></c:out></td>
					<td><c:out value="${commonMessage.messageLaunchTime}"></c:out></td>
					<td>
						<c:choose>
				    		<c:when test="${commonMessage.messageType == 'S'}">
				    			<c:out value="系统消息"></c:out>
				    		</c:when>
				    		<c:when test="${commonMessage.messageType == 'B'}">
				    			<c:out value="业务变更消息"></c:out>
				    		</c:when>
				    		<c:when test="${commonMessage.messageType == 'E'}">
				    			<c:out value="待办事宜消息"></c:out>
				    		</c:when>
				    	</c:choose>
					</td>
					<td class="state">
						<c:choose>
				    		<c:when test="${commonMessage.messageStatus == '2'}">
				    			<c:out value="已读"></c:out>
				    		</c:when>
				    		<c:otherwise>
				    			<c:out value="未读"></c:out>
				    		</c:otherwise>
				    	</c:choose>
					</td>
					<td><c:out value="${commonMessage.messageReceiver}"></c:out></td>
					<td style="text-align: right;">
						<a class="btnDel" href="sys-message/system-message!delete.do?method=remove&${commonMessage.messageSource}.id=${commonMessage.messageId}" target="navTabTodo" title="<s:property value='getText("deleteTips")'/>"></a>
						<a class="btnView" href="sys-message/system-message!view.do?${commonMessage.messageSource}.id=${commonMessage.messageId}" target="navTab" title="<s:property value='getText("messageContent")'/>"></a>
						
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
