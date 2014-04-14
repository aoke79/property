<%@ page language="java" import="java.util.*,org.jbpm.api.ProcessDefinition;" pageEncoding="UTF-8"%>
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

<%
  List<ProcessDefinition> pdList=(List<ProcessDefinition>)request.getAttribute("pdList");
  String pdid="";
%>

<div class="page">
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

<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
				    <s:if
						test='!#session.sysOperateList.{?#this.operateAction=="jbpm4/jbpm4-en-exam!deploymentProcessDefinition.do"}.isEmpty()'>
					<a class="add" href="jbpm4/jbpm4-en-exam!deploymentProcessDefinition.do" target="navTabTodo" rel="adjustPage"><span>发布新流程(开启预估流程)</span></a>
				    </s:if>
				</li>
				<li class="line">
					line
				</li>
				<!-- 
				<li>
				 <s:if
						test='!#session.sysOperateList.{?#this.operateAction=="jbpm4/jbpm4-en-exam!deploymentProcessDefinition.do"}.isEmpty()'>
				   <a class="add" href="jbpm4/jbpm4-en-exam!deploymentProcessDefinitionEnExam.do" target="navTabTodo" rel="adjustPage"><span>发布新流程(英语考试流程)</span></a>
				 </s:if>
				</li>
				 -->
						
			</ul>
		</div>
		<table class="table" layouth="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="200">流程id</th>
					<th width="200">名称</th>
				
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
			<%
			ProcessDefinition processDefinition;
			 for(int i=0;i<pdList.size();i++){ 
				 processDefinition=pdList.get(i);
			
			%>
			    
				<tr target="sid_sysPer" rel="<%=processDefinition.getName()%>" >
					<td><input name="ids" value="<%=processDefinition.getId()%>" type="checkbox"></td>
					<td><%=processDefinition.getId()%></td>
					<td>
	    				<%=processDefinition.getName()%>
					</td>
					
					<td style="text-align: right;">
					      <% pdid=processDefinition.getId();%>
					      
					        <s:if
						test='!#session.sysOperateList.{?#this.operateAction=="jbpm4/jbpm4-en-exam!deploymentProcessDefinition.do"}.isEmpty()'>
							<a class="btnDel" href="jbpm4/jbpm4-en-exam!removeDeployment.do?jbpm4EnExamBean.deploymentId=<%=processDefinition.getDeploymentId()%>" target="navTabTodo" title="确定要删除吗?"></a>
						    </s:if>
						        <s:if
						test='!#session.sysOperateList.{?#this.operateAction=="jbpm4/jbpm4-en-exam!deploymentProcessDefinition.do"}.isEmpty()'>
						    <% if("各机队预估人数流程".equals(processDefinition.getName())){ %>
						    
							<a class="btnAdd" href="jbpm4/jbpm4-en-exam!startProcessEnExamSignUpByUser.do?jbpm4EnExamBean.processDefinitionId=<%=pdid%>"  id="encodeURIhref<%=i %>" target="navTabTodo" rel="enProcessDefinition"></a>
							
						    <%}else{ %>
						  
						     	<a class="btnAdd" href="jbpm4/jbpm4-en-exam!startProcessEnExamByUser.do?jbpm4EnExamBean.processDefinitionId=<%=pdid%>" id="encodeURIhref<%=i %>" target="navTabTodo" rel="enProcessDefinition"></a>
						     <%} %>
						     </s:if>
						      <script type="text/javascript">
                              var hh=$("#encodeURIhref<%=i %>").attr("href");
                              hh=encodeURI(encodeURI(hh));
                              $("#encodeURIhref<%=i %>").attr("href",hh);
                             </script>
	 				</td>
				</tr>
			<%} %>
			</tbody>
		</table>
		
		
</div>
   
</div>
