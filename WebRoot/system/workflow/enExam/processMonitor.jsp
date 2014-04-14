<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script>
function submitForm(roleName){alert(roleName);
	$("input[name=pageNum]").val(roleName);
	//document.pagerForm.submit();
	//navTabSearch($("#pagerForm"));
	//$("#pagerForm").submit();
	
}
</script>
<div class="page">
    <!--  
	<div class="pageHeader">
	<form id="pagerForm"  onsubmit="return navTabSearch(this);" action="jbpm4/jbpm4-en-exam!index.do" method="post">
		<input type="hidden" name="pageNum" value="1"/>
		<input type="hidden" name="orderField" value="" />
		<input type="hidden" name="roleName" value=""/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						
					</td>
				</tr>
			</table>
			<div class="subBar">
				
			</div>
		</div>
		</form>
    </div>
   -->
<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				
				<!-- <li><a class="edit" href="sys-permission/sys-permission!toEditPage.do?sysPermission.id={sid_sysPer}" target="navTab"><span>修改</span></a></li> 
				<li class="line">line</li>
				<li><a class="icon" href="testimport.html" target="dialog" width="300" height="200" mask="true" title="导入EXCEL"><span>导入EXCEL</span></a></li>-->
			</ul>
		</div>
		<table class="table" layouth="75">
			<thead>
				<tr>
				    <!-- 
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					 -->
					<th width="200">实例ID</th>
					<th width="200">活动</th>
					
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
		       <s:iterator value="piList" status="st">
				<tr target="sid_sysPer" rel="${id }" >
				    <!--  
					<td><input name="ids" value="${id}" type="checkbox"></td>
					-->
					<td><c:out value="${id}"></c:out></td>
					<td>
						<s:property value="%{orgsNameMap[#st.index]}" /><s:property value="findActiveActivityNames()"/>
					</td>
					
					<td style="text-align: right;">
					 	<a href="jbpm4/jbpm4-en-exam!viewFlowsheet.do?jbpm4EnExamBean.processInstanceId=${id}" id="kanliutu${st.index}" target="navTab" rel="viewpic" title="流程图走势">流程图走势</a>
					      <script type="text/javascript">
                              var hh=$("#kanliutu${st.index}").attr("href");
                              hh=encodeURI(encodeURI(hh));
                              $("#kanliutu${st.index}").attr("href",hh);
                             </script>   
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		
		
</div>
    
</div>

