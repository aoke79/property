<%@page import="org.jbpm.api.*,java.io.*"%>
<%
	ProcessEngine processEngine = Configuration.getProcessEngine();
	RepositoryService repositoryService = processEngine
			.getRepositoryService();
	ExecutionService executionService = processEngine
			.getExecutionService();
	String processInstanceId = (String)request.getAttribute("id");
	ProcessInstance processInstance = executionService
			.findProcessInstanceById(processInstanceId);
	String processDefinitionId = processInstance
			.getProcessDefinitionId();
	ProcessDefinition processDefinition = repositoryService
			.createProcessDefinitionQuery().processDefinitionId(
					processDefinitionId).uniqueResult();
	InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),"eventReport.png");
	byte[] b = new byte[1024];
	int len = -1;
	while ((len = inputStream.read(b, 0, 1024)) != -1) {
		response.getOutputStream().write(b, 0, len);
	}
	out.clear(); 
	out = pageContext.pushBody(); 
%>