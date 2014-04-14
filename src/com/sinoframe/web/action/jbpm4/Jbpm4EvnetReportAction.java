
package com.sinoframe.web.action.jbpm4;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

import com.comm.web.Paging;
import com.sinoframe.bean.Jbpm4EventReportBean;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.CmUser;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results(
		{
        	@Result(name = "processDefinition",location="/system/workflow/eventReport/processDefinition.jsp"),
        	@Result(name = "processMonitor",location="/system/workflow/eventReport/processMonitor.jsp"),
        	@Result(name = "success",location="/standard/ajaxDone.jsp"),
        	@Result(name = "flowsheet",location="/system/workflow/eventReport/view.jsp"),
//        	@Result(name = "drawFlowsheet",location="/system/workflow/eventReport/pic.jsp"),
        	@Result(name = "drawFlowsheet",type="stream" ,params={"inputName", "inputStream"}),
        	@Result(name = "reportPage",location="/system/workflow/eventReport/reportPage.jsp"),
        	@Result(name = "yijiReportPage",location="/system/workflow/eventReport/yijiReportPage.jsp"),
        	@Result(name = "hanganReportPage",location="/system/workflow/eventReport/hanganReportPage.jsp"),
        	@Result(name = "auditPage",location="/system/workflow/eventReport/auditPage.jsp"),
        	@Result(name = "yijiAuditPage",location="/system/workflow/eventReport/yijiAuditPage.jsp"),
        	@Result(name = "hanganAuditPage",location="/system/workflow/eventReport/hanganAuditPage.jsp"),
        	@Result(name = "hanganjingliAuditPage",location="/system/workflow/eventReport/hanganjingliAuditPage.jsp"),
        	@Result(name = "bossPage",location="/system/workflow/eventReport/boss.jsp"),
        	@Result(name = "index",location="/system/workflow/eventReport/index.jsp"),
        	@Result(name = "toframeView",location="/system/workflow/eventReport/frameView.jsp")
        }
        )
public class Jbpm4EvnetReportAction extends BaseAction {
    private static final long serialVersionUID = 1780293853128531874L;
    //消息实体
	private Message message;
	
    private Jbpm4EventReportBean jbpm4EventReportBean; //jbpm对象
    List<ProcessDefinition> pdList; //流程定义列表
	List<ProcessInstance> piList; //流程实例列表
	List<Task> taskList; //待办任务列表
	List<Task> auditTaskList;//待办审批任务列表
	private int flag;
    //分页对象
    private Paging paging=null;
    //分页时每页显示记录数
    private static int pageSize = 4;
    //存储用户角色列表
    List roleNamesArr = new ArrayList();
    //角色名称
    String roleName = null;
    //流程图输入流
    InputStream inputStream = null;
    //登陆页
    public String toLoginPage(){
    	return "login";
    }
//    
//    //登陆方法
//    public String doLogin(){
//    	this.getServletRequest().getSession().setAttribute("userName", this.getJbpm4EventReportBean().getUserName());
//    	System.out.println("--login--------"+this.getJbpm4EventReportBean().getUserName());
//    	return "success";
//    }
    //流程定义部署
    public String processDefinition(){
    	
    	//声明流程引擎
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	//声明流程仓库服务
    	RepositoryService repositoryService = processEngine.getRepositoryService();
    	//声明流程执行服务
    	ExecutionService executionService = processEngine.getExecutionService();
    	//声明任务服务
    	TaskService taskService = processEngine.getTaskService();
    	
    	//获取已部署的流程定义列表
    	this.pdList = repositoryService.createProcessDefinitionQuery().list();
    	//获取当前以创建的流程实例列表
    	this.piList = executionService.createProcessInstanceQuery().list();
    	this.getServletRequest().setAttribute("pdList", pdList);
    	this.getServletRequest().setAttribute("piList", piList);
    	return "processDefinition";
    }

  //流程监控
    public String processMonitor(){
    	//声明流程引擎
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	//声明流程仓库服务
    	RepositoryService repositoryService = processEngine.getRepositoryService();
    	//声明流程执行服务
    	ExecutionService executionService = processEngine.getExecutionService();
    	//声明任务服务
    	TaskService taskService = processEngine.getTaskService();
    	
    	//获取当前以创建的流程实例列表
    	this.piList = executionService.createProcessInstanceQuery().list();
    	this.getServletRequest().setAttribute("pdList", pdList);
    	return "processMonitor";
    }
    
    //主管理界面
    public String index(){
    	System.out.println("roleName==22="+this.getRoleName());
    	//声明流程引擎
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	//声明流程仓库服务
    	RepositoryService repositoryService = processEngine.getRepositoryService();
    	//声明流程执行服务
    	ExecutionService executionService = processEngine.getExecutionService();
    	//声明任务服务
    	TaskService taskService = processEngine.getTaskService();
    	
    	//获取已部署的流程定义列表
    	this.pdList = repositoryService.createProcessDefinitionQuery().list();
    	//获取当前以创建的流程实例列表
    	this.piList = executionService.createProcessInstanceQuery().list();
    	if(flag==0){
        	getSessionRoleName();
        }
    	
    	if("基层员工".equals(this.getRoleName())){
    		//获取人员组的任务列表
    		this.taskList = taskService.findGroupTasks(this.getRoleName());
    	}else if("基层领导".equals(this.getRoleName())){
    		this.taskList = taskService.findPersonalTasks(this.getRoleName());
    		this.auditTaskList = taskService.findGroupTasks(this.getRoleName());
    		taskList.addAll(this.auditTaskList);
    	}else{
    		this.taskList = taskService.findPersonalTasks(this.getRoleName());
    	}
    	this.getServletRequest().setAttribute("pdList", pdList);
    	this.getServletRequest().setAttribute("piList", piList);
    	this.getServletRequest().setAttribute("taskList", taskList);
    	getSessionRoleNames();
    	
    	return "index";
    }
    
    //部署流程定义
    public String deploymentProcessDefinition(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	RepositoryService repositoryService = processEngine.getRepositoryService();

    	//repositoryService.createDeployment().addResourceFromClasspath("/leave.jpdl.xml").deploy();
    	try{
    		ZipInputStream zis = new ZipInputStream(this.getClass().getResourceAsStream("/eventReport.zip"));
        	repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
        	this.message = this.getSuccessMessage("发布成功", "workflowIndex", "forward", "jbpm4/jbpm4-evnet-report!processDefinition.do");
    	}catch(Exception e){
    		this.message = this.getFailMessage("发布失败");
			e.printStackTrace();
    	}
    	return "success";
    }
    //生成并启动一个流程定义的实例
    public String startInstence(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	ExecutionService executionService = processEngine
    			.getExecutionService();
    	Map map = new HashMap();
    	map.put("userName", this.getSessionRoleName());
//    	System.out.println("启动=="+this.getSessionRoleName());
//    	map.put("userName", "jiceng");
    	try {
    		executionService.startProcessInstanceById(this.getJbpm4EventReportBean().getProcessDefinitionId(), map);
			//设定成功消息
			this.message = this.getSuccessMessage("启动成功", "workflowIndex", "forward", "jbpm4/jbpm4-evnet-report!processDefinition.do");
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("启动失败");
			e.printStackTrace();
		}
    	return "success";
    }
    //以级联的方式删除流程定义
    public String removeDeployment(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	RepositoryService repositoryService = processEngine.getRepositoryService();
//    	repositoryService.deleteDeploymentCascade(this.getJbpm4EventReportBean().getDeploymentId());
    	
    	try {
    		repositoryService.deleteDeploymentCascade(this.getJbpm4EventReportBean().getDeploymentId());
			//设定成功消息
			this.message = this.getSuccessMessage("删除成功", "workflowIndex", "forward", "jbpm4/jbpm4-evnet-report!processDefinition.do");
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
    	return "success";
    }
   
    //查看流程图
    public String viewFlowsheet(){
    	String id = this.getJbpm4EventReportBean().getProcessInstanceId();
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	RepositoryService repositoryService = processEngine.getRepositoryService();
    	ExecutionService executionService = processEngine.getExecutionService();
    	ProcessInstance processInstance = executionService.findProcessInstanceById(id);
    	Set<String> activityNames = processInstance.findActiveActivityNames();
//    	System.out.println("activityNames==="+activityNames.iterator().next());
    	ActivityCoordinates ac = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityNames.iterator().next());
    	
    	this.getServletRequest().setAttribute("id", id);
    	this.getServletRequest().setAttribute("ac", ac);
    	
//    	String executionId = processInstance
//        .findActiveExecutionIn("调查")
//        .getId();

        
    	HistoryService historyService = processEngine.getHistoryService();
    	List<HistoryActivityInstance> histActInsts = historyService.createHistoryActivityInstanceQuery().processInstanceId(id).orderAsc("dbid").list(); 
    	
    	HistoryActivityInstance h =null;
    	ActivityCoordinates ac2 = null;
    	List acArr = new ArrayList();
    	List acArr2 = new ArrayList();
    	int activeCount = 0;
    	for(int i=0 ;i<histActInsts.size();i++){
    		h=(HistoryActivityInstance)histActInsts.get(i);
//    		System.out.println(activeCount+"==name====="+h.getActivityName());
//    		System.out.println("Transition====="+h.getTransitionNames());
    		
    		//获取历史节点坐标
    		ac2 = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),h.getActivityName());
    		acArr.add(ac2);
    		acArr2.add(i+1);
    		activeCount++;
    	}
    	//获取所有单位名用于流程图显示
    	ProcessBase processBase = new ProcessBase();
    	String v0 = (String)processBase.getVariableBykey(id, "v0");
    	String v1 = (String)processBase.getVariableBykey(id, "v1");
    	String v2 = (String)processBase.getVariableBykey(id, "v2");
    	String v3 = (String)processBase.getVariableBykey(id, "v3");
//    	System.out.println("v0=="+v0);
//    	System.out.println("v1=="+v1);
//    	System.out.println("v2=="+v2);
//    	System.out.println("v3=="+v3);
    	
//    	processBase.deleteProcessInstanceByPiId("secCheckReport.30812342");
    	//processBase.goTaskNoteByNoteName(id,"航安部信息员提交");
    	
    	Map orgsMap = (Map)processBase.getVariableBykey(id, "orgsMap");
    	this.getServletRequest().setAttribute("orgsMap", orgsMap);
    	
    	this.getServletRequest().setAttribute("activeCount",activeCount);
    	this.getServletRequest().setAttribute("acs",acArr);
    	this.getServletRequest().setAttribute("acs2",acArr2);
    	System.out.println("activeCount==1==="+acArr.size());
    	return "flowsheet";
    }
    //绘制流程图
    public String drawFlowsheet(){
    	//this.getServletRequest().setAttribute("id", this.getJbpm4EventReportBean().getProcessInstanceId());
    	
    	
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	RepositoryService repositoryService = processEngine
    			.getRepositoryService();
    	ExecutionService executionService = processEngine
    			.getExecutionService();
    	String processInstanceId = (String)this.getJbpm4EventReportBean().getProcessInstanceId();
    	ProcessInstance processInstance = executionService
    			.findProcessInstanceById(processInstanceId);
    	String processDefinitionId = processInstance
    			.getProcessDefinitionId();
    	ProcessDefinition processDefinition = repositoryService
    			.createProcessDefinitionQuery().processDefinitionId(
    					processDefinitionId).uniqueResult();
    	inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getImageResourceName());
    	
    	return "drawFlowsheet";
    }
    
    
    //一级单位报告添加页面
    public String toYijiReportPage(){
    	return "yijiReportPage";
    }
    //一级单位提交事件报告
    public String yijiSubmitReport(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	TaskService taskService = processEngine.getTaskService();

    	String taskId = this.getJbpm4EventReportBean().getTaskId();
    	String userName = this.getJbpm4EventReportBean().getUserName();
    	String content = this.getJbpm4EventReportBean().getCentent();
    	String eventReportType = this.getJbpm4EventReportBean().getEventReportType();
    	Map map = new HashMap();
    	map.put("userName", userName);
    	map.put("content", content);
    	map.put("eventReportType", eventReportType);
    	try {
		
    	taskService.completeTask(taskId,"上报", map);
    	    this.message = this.getSuccessMessage("一级单位安提交事件成功", "index", "forward", "jbpm4/jbpm4-evnet-report!index.do");
    	} catch (Exception e) {
    		this.message=this.getFailMessage("一级单位提交事件失败");
		}	
    	return "success";
    }
    
    //航安报告添加页面
    public String toHanganReportPage(){
    	return "hanganReportPage";
    }
    //航安提交事件报告
    public String hanganSubmitReport(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	TaskService taskService = processEngine.getTaskService();

    	String taskId = this.getJbpm4EventReportBean().getTaskId();
    	String userName = this.getJbpm4EventReportBean().getUserName();
    	String content = this.getJbpm4EventReportBean().getCentent();
    	String eventReportType = this.getJbpm4EventReportBean().getEventReportType();
    	Map map = new HashMap();
    	map.put("userName", userName);
    	map.put("content", content);
    	map.put("eventReportType", eventReportType);
    	try {
    	 taskService.completeTask(taskId,"提交", map);
    	  this.message = this.getSuccessMessage("航安提交事件成功", "index", "forward", "jbpm4/jbpm4-evnet-report!index.do");
    	} catch (Exception e) {
			this.message=this.getFailMessage("航安提交事件失败");
		}	
    	return "success";
    }
    
    //基层报告添加页面
    public String toReportPage(){
    	return "reportPage";
    }
    //基层提交事件报告
    public String jicengSubmitReport(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	TaskService taskService = processEngine.getTaskService();

    	String taskId = this.getJbpm4EventReportBean().getTaskId();
    	String userName = this.getJbpm4EventReportBean().getUserName();
    	String content = this.getJbpm4EventReportBean().getCentent();
    	String eventReportType = this.getJbpm4EventReportBean().getEventReportType();
    	Map map = new HashMap();
    	map.put("userName", userName);
    	map.put("content", content);
    	if(userName.equals("jicenglingdao")){
    		map.put("isMgr", "1");
    	}else{
    		map.put("isMgr", "0");
    	}
    	map.put("eventReportType", eventReportType);
    		
    	taskService.completeTask(taskId,"提交", map);
    	
    	return "success";
    }
    
    //基层领导审核页面
    public String toAuditPage(){
    	return "auditPage";
    }
    //一级单位审核页面
    public String toYijiAuditPage(){
    	return "yijiAuditPage";
    }
    //航安部审核页面
    public String toHanganAuditPage(){
    	return "hanganAuditPage";
    }
    //航安部经理审核页面
    public String toHanganjingliAuditPage(){
    	return "hanganjingliAuditPage";
    }
    //处理审核
    public String dealAudit(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	TaskService taskService = processEngine.getTaskService();

    	String taskId = this.getJbpm4EventReportBean().getTaskId();
    	String result = this.getJbpm4EventReportBean().getResult();
    	String reason = this.getJbpm4EventReportBean().getReason();
    	Map map = new HashMap();
    	map.put("reason", reason);
    	taskService.completeTask(taskId, result,map);
    	
    	return "success";
    }
    
    //老板审核页面
    public String bossPage(){
    	return "bossPage";
    }
    //老板处理审核
    public String bossDealAudit(){
    	ProcessEngine processEngine = Configuration.getProcessEngine();
    	TaskService taskService = processEngine.getTaskService();

    	String taskId = this.getJbpm4EventReportBean().getTaskId();
    	taskService.completeTask(taskId);
    	return "success";
    }
    
    //获取登陆用户名
    private List getSessionRoleNames(){
    	
    	CmUser cmUser = (CmUser)this.getServletRequest().getSession().getAttribute("user");
    	
    	Set<SysRelationAccountRole> roleSet = cmUser.getSysRelationAccountRoles();
    	Iterator<SysRelationAccountRole> it = roleSet.iterator();
    	for(;it.hasNext();){
    		SysRelationAccountRole sysRA = it.next();
    		String roleName=sysRA.getSysRole().getRoleName();
    		if(!"一级单位调查员".equals(roleName)){
    		roleNamesArr.add(roleName);
    		}
    	}
    	return roleNamesArr;
//    	return (String) this.getServletRequest().getSession().getAttribute("userName");
    }
    //获取登陆用户名
    private String getSessionRoleName(){
//    	CmUser cmUser = (CmUser)this.getServletRequest().getSession().getAttribute("user");
//    	Set<SysRelationAccountRole> roleSet = cmUser.getSysRelationAccountRoles();
//    	Iterator<SysRelationAccountRole> it = roleSet.iterator();
//    	for(;it.hasNext();){
//    		SysRelationAccountRole sysRA = it.next();
//    		this.roleName = sysRA.getSysRole().getRoleName();
//    		break;
//    	}
    	this.roleName="基层员工";
    	this.getServletRequest().getSession().setAttribute("roleName", roleName);
    	return roleName;
    }
    //航安部经理审核页面
    public String toframeView(){
    	String id = this.getJbpm4EventReportBean().getProcessInstanceId();
    	ProcessBase pBase = new ProcessBase();
    	pBase.getAllNodes(id);
    	this.getServletRequest().setAttribute("id", id);
    	return "toframeView";
    }
    
	public Paging getPaging() {
	    if(paging==null){
	    	paging=new Paging();
	    	paging.setPageSize(this.pageSize);
	    }
	    return paging;
	}
	public Jbpm4EventReportBean getJbpm4EventReportBean() {
		if(jbpm4EventReportBean == null){
			jbpm4EventReportBean = new Jbpm4EventReportBean();
		}
		return jbpm4EventReportBean;
	}
	public void setJbpm4EventReportBean(Jbpm4EventReportBean jbpm4EventReportBean) {
		this.jbpm4EventReportBean = jbpm4EventReportBean;
	}
	public List<ProcessDefinition> getPdList() {
		return pdList;
	}
	public void setPdList(List<ProcessDefinition> pdList) {
		this.pdList = pdList;
	}
	public List<ProcessInstance> getPiList() {
		return piList;
	}
	public void setPiList(List<ProcessInstance> piList) {
		this.piList = piList;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public List<Task> getAuditTaskList() {
		return auditTaskList;
	}

	public void setAuditTaskList(List<Task> auditTaskList) {
		this.auditTaskList = auditTaskList;
	}

	//获取用户角色列表
	public List getRoleNamesArr() {
		return roleNamesArr;
	}
	public void setRoleNamesArr(List roleNamesArr) {
		this.roleNamesArr = roleNamesArr;
	}
	//获取第一个角色名称
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}




}
