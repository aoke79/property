<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%String pagingId = request.getParameter("pagingId");
if(null==pagingId) pagingId="";
%>
<c:if test="${paging.maxPage>1}">
<c:choose
	><c:when test="${paging.curPage}==1"
		><input name="Submit" type="button" class="groupModuleButton" value="第一页" >&nbsp;<input name="Submit" type="button" class="groupModuleButton" value="上一页"></c:when
	><c:otherwise
		><input name="Submit" type="button" class="groupModuleButton" value="第一页" onclick="gotoPage<%=pagingId%>(1)" >&nbsp;<input name="Submit" type="button" class="groupModuleButton" value="上一页" onclick="gotoPage<%=pagingId%>(<c:out value="${paging.priorPage}"/>)"></c:otherwise
></c:choose>
<a href="javascript:gotoPage<%=pagingId%>(<c:out value="${paging.curPage}"/>)" class="text3" ><strong><c:out value="${paging.curPage}"/></strong></a><span class="text3"><strong>/</strong><a href="#" class="text3"><strong></strong></a></span><a href="javascript:gotoPage<%=pagingId%>(<c:out value="${paging.maxPage}"/>)" class="text3" ><strong><c:out value="${paging.maxPage}"/></strong></a>&nbsp;
<c:choose
	><c:when   test="${paging.curPage==paging.maxPage}"
		><input name="Submit" type="button" class="groupModuleButton" value="下一页">&nbsp;<input name="Submit" type="button" class="groupModuleButton" value="最后一页"></c:when
	><c:otherwise
		><input name="Submit" type="button" class="groupModuleButton" value="下一页" onclick="gotoPage<%=pagingId%>(<c:out value="${paging.nextPage}"/>)" >&nbsp;<input name="Submit" type="button" class="groupModuleButton" value="最后一页" onclick="gotoPage<%=pagingId%>(<c:out value="${paging.maxPage}"/>)"></c:otherwise
></c:choose>
&nbsp;<SELECT name="curPage" id="pagingCurPage" onchange="gotoPage<%=pagingId%>(this.value)" >
<c:forEach var="i" begin="1" end="${paging.maxPage}"
	><c:choose
		><c:when test="${i eq paging.curPage}"
			><OPTION selected value="<c:out value="${i}"/>"><c:out value="${i}"/></OPTION></c:when
		><c:otherwise
			><OPTION value=<c:out value="${i}"/>><c:out value="${i}"/></OPTION></c:otherwise
	></c:choose>
</c:forEach>
</select>
</c:if>
