package com.sinoframe.common.jbpm4;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.sinoframe.common.ConstantList;
import com.sinoframe.common.util.SpringContextUtil;

public class ProcessBase {

	private RepositoryService repositoryService ;
	private ExecutionService executionService;
	private TaskService taskService;
	private HistoryService historyService;
	private ProcessEngine processEngine;
	public ProcessBase(){
		processEngine = Configuration.getProcessEngine();
		this.repositoryService = processEngine.getRepositoryService();
		this.executionService = processEngine.getExecutionService();
		this.taskService = processEngine.getTaskService();
		this.historyService = processEngine.getHistoryService();
	}
	
	
	/**
	 * 获取已发布的所有流程
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitions(){
		 List <ProcessDefinition> pdList = this.repositoryService.createProcessDefinitionQuery().list();
		 return pdList;
	}
	
	
	/**
	 * 获取唯一一个流程定义
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId){
		ProcessDefinition processDefinition = repositoryService
			.createProcessDefinitionQuery().processDefinitionId(
					processDefinitionId).uniqueResult();
		 return processDefinition;
	}
	
	public Object getVariableByPid(String pid, String key){
		return executionService.getVariable(pid, key);
	}
	
	
	/**
	 * @param map  传入map
	 * @param processDefinitionId 流程id
	 * @return processInstance id
	 */
	public String putOutProcessInstance(String processDefinitionId,Map<String,Object> map){
		ProcessInstance processInstance = executionService.startProcessInstanceById(processDefinitionId,map );
		String piId = processInstance.getId();
		return piId;
	}
	public String putOutProcessInstance(String processDefinitionId){
		ProcessInstance processInstance = executionService.startProcessInstanceById(processDefinitionId);
		String piId = processInstance.getId();
		return piId;
	}
	
	/**
	 * 获取启动的所有流程发布实例
	 * @return
	 */
	public List<ProcessInstance> getProcessInstances(){
		List <ProcessInstance> piList = executionService.createProcessInstanceQuery().list();
		return piList;
	}
	public List<ProcessInstance> getProcessInstances(String processInstanceId){
		List <ProcessInstance> piList = executionService.createProcessInstanceQuery().processInstanceId(processInstanceId).list();
		return piList;
	}
	public ProcessInstance getProcessInstance(String processInstanceId){
		return executionService.findProcessInstanceById(processInstanceId);
	}
	
	public boolean isEnd(String processInstanceId){
		List <ProcessInstance> piList = executionService.createProcessInstanceQuery().processInstanceId(processInstanceId).list();
		if(piList.isEmpty()){
		  return true;	
		}
		return false;
	}
	/**
	 * 
	 * 关闭流程实例
	 * Dec 6, 20113:46:45 PM
	 * @author niujingwei
	 */
	public void endProcessInstance(String processInstanceId){
		executionService.endProcessInstance(processInstanceId, "ended");
	}
	
	
	
	
	/**
	 * 获取用户的待办事宜
	 * @param name
	 * @return
	 */
	public List<Task> getTaskListByName(String name){
//		System.out.println("==============:"+name);
		List<Task> taskList = taskService.findPersonalTasks(name);
		return taskList;
	} 
	
	/**
	 * @param streamPath xml或者zip的路径
	 * @param mark  “xml”为xml加载，“zip”为zip包加载
	 * @return  发布流程id 此id不可直接使用
	 */
	private String deployProcess(String streamPath){
		String deployId = "";
		String mark = streamPath.substring(streamPath.length()-3);
		try{
			if("zip".equals(mark)){
				ZipInputStream zip = new ZipInputStream(this.getClass().getResourceAsStream(streamPath));
			    deployId = repositoryService.createDeployment().addResourcesFromZipInputStream(zip).deploy();
			}else if("xml".equals(mark)){
				deployId = repositoryService.createDeployment().addResourceFromClasspath(streamPath).deploy();
			}else{
				try{
					throw new JbpmException();
				}catch(JbpmException je){
					je.printStackTrace();
				}
			}
		}catch (Exception e) {
		  e.printStackTrace();
		}
		return deployId;
	}
	
	
	/**
	 *  发布流程并获取已发布流程id
	 * @param Path  流程路径
	 * @return  processDefinitionId
	 */
//	public String getProcessDefinitionIdByPath(String path){
//
//		   String processDefinitionId = "";
//		   //获取所有已发布流程定义
//		   List <ProcessDefinition> list = this.getProcessDefinitions();
//		   Boolean processIsReady = false;
//		   String mark = path.substring(path.length()-3);
//		   String processName = "";
//		   
//		   if("xml".equals(mark)){
//		       processName = path.substring(path.lastIndexOf("/")+1,path.length()-9);
//		   }else if("zip".equals(mark)){
//			   processName = path.substring(path.lastIndexOf("/")+1,path.length()-4);
//		   }
//		   
//		   Iterator<ProcessDefinition> i = list.iterator();
//		   while(i.hasNext()){
//			 ProcessDefinition pd = i.next();
//			 String name=pd.getName();
//			 String midStr = name;
//			 if(name.endsWith(".jpdl.xml")){
//				 midStr=name.substring(0,pd.getName().length()-9);
//			 }
//			 if(midStr.equals(processName)){
//				 processIsReady = true;
//				 processDefinitionId = pd.getId();
//				 break;
//			 }
//		   }
//		   if(!processIsReady){
//			   //流程尚未发布
//			   // processDefinitionId = this.deployProcess(path);
//			   //流程部署时候返回的id 是用于查询的,而不能用于启动流程实例,只有查询出来得到的真正的流程实例中的id,才能启动流程实例！
//			   processDefinitionId = this.repositoryService.createProcessDefinitionQuery().deploymentId(this.deployProcess(path)).uniqueResult().getId();
//			 }
//		return processDefinitionId;
//	}
	
	
	/**
	 *  发布流程并获取已发布流程id
	 * @param Path  流程路径
	 * @return  processDefinitionId
	 */
	 public String getProcessDefinitionIdByPath(String path){
//		   ConstantList constantList = new ConstantList();
		   String processDefinitionId = "";
		   //获取所有已发布流程定义
		   List <ProcessDefinition> list = this.getProcessDefinitions();
		   Boolean processIsReady = false;
		   String mark = path.substring(path.length()-3);
		   String processName = "";
		   
		   if("xml".equals(mark)){
		       processName = path.substring(path.lastIndexOf("/")+1,path.length()-9);
		   }else if("zip".equals(mark)){
			   processName = path.substring(path.lastIndexOf("/")+1,path.length()-4);
		   }
		   
		   Iterator<ProcessDefinition> i = list.iterator();
		   String midStr = "";
		   while(i.hasNext()){
			 ProcessDefinition pd = i.next();
//			 if(pd.getName().equals(ConstantList.PROCESSNAME.get(processName))){
			 if(pd.getName().equals(processName)){
				 processIsReady = true;
				 processDefinitionId = pd.getId();
				 break;
			 }
		   }
		   if(!processIsReady){
			   //流程尚未发布
			   // processDefinitionId = this.deployProcess(path);
			   //流程部署时候返回的id 是用于查询的,而不能用于启动流程实例,只有查询出来得到的真正的流程实例中的id,才能启动流程实例！
			   processDefinitionId = this.repositoryService.createProcessDefinitionQuery().deploymentId(this.deployProcess(path)).uniqueResult().getId();
			 }
		return processDefinitionId;
	}
	
	/**
	 *  发布流程并获取已发布流程id
	 * @param Path  流程路径
	 * @return  
	 */
	public String findProcessDefinitionIdByPath(String path) {
		String processName = "";
		if(path.endsWith("xml")){
			processName = path.substring(path.lastIndexOf("/")+1,path.length()-9);
		}else if(path.endsWith("zip")){
			processName = path.substring(path.lastIndexOf("/")+1,path.length()-4);
		}
		ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionName(processName).uniqueResult();
		if(pd != null){
			return pd.getId();
		}
		String deploymentId =  this.deployProcess(path);
		return this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).uniqueResult().getId();
	}

	public void completeTaskById(String taskId,String nextName,Map <String,Object> map){
		if(map==null){
			map = new HashMap<String,Object> ()  ;
		}
		if(!"".equals(taskId)){
			if(!"".equals(nextName)&&!map.isEmpty()    ){
				this.taskService.completeTask(taskId,nextName,map);
			}else if(!"".equals(nextName)&&map.isEmpty()){
				this.taskService.completeTask(taskId,nextName);
			}else if("".equals(nextName)&&!map.isEmpty()){
				this.taskService.completeTask(taskId,map);
			}else{
				this.taskService.completeTask(taskId);
			}
		}
	}
	
	/** 
	 * 根据ProcessInstanceID获取ProcessDefinitionID
	 * 根据taskID获取processInstranceID
	 * 根据taskID获取ProcessDefinitionID
	 */
	/**
	 * 根据ProcessInstanceID获取ProcessDefinitionID
	 */
	public String getProcessDefinitionID(String processInstanceId){
		
		return this.executionService.createProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult().getProcessDefinitionId();
	}
	/**
	 * 根据ProcessInstanceID获取 TaskID
	 */
	public String getTaskIdByPiID(String processInstanceId){
		String taskId = "";
		if(this.taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult()==null){
			return null;
		}
		taskId = this.taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult().getId();
		return taskId;
	}
	
	/**
	 * 根据ProcessInstanceID获取 Taskname
	 */
	public String getTaskNameByPiID(String processInstanceId){
		String taskName = "";
		if(this.taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult()==null){
			return null;
		}
		taskName = this.taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult().getName();
		return taskName;
	}
	
	
	/**
	 * 根据ProcessInstanceID获取 TaskID
	 */
	public List<Task> getTaskByPiID(String processInstanceId){
		TaskQuery processInstanceId2 = this.taskService.createTaskQuery().processInstanceId(processInstanceId);
		if(processInstanceId2!=null){
			List<Task> list = processInstanceId2.list();
			return list;
		}
		return null;
	}
	
	
	
	
	
	
	
	/**  
	 * 获取流程实例  
	 * @param taskId 任务id  
	 * @return  
	 */   
	public String getTaskProcessInstanceID(String taskId) {   
	    Task task = taskService.getTask(taskId);    
	    Execution execution = executionService.findExecutionById(task.getExecutionId());    
	    Execution processInstance = execution.getProcessInstance();
	    return processInstance.getId();   
	} 
	
	
	public String getParentProcessInstanceIDByTaskId(String taskId){
		     Task task = taskService.getTask(taskId);    
	         Execution execution = executionService.findExecutionById(task.getExecutionId());    
	         Execution processInstance = execution.getProcessInstance();    
	         ExecutionImpl superProcessExecution = ((ExecutionImpl)processInstance).getSuperProcessExecution(); 
	         String id = superProcessExecution.getId();
		     return id;
	}
	
	
	
//	public String getSubProcessInstanceIDByTaskId(String pid){
//		
////		ProcessInstance findProcessInstanceById = executionService.findProcessInstanceById(pid);
////		findProcessInstanceById.gete
//		
//	    Task task = taskService.getTask(taskId);    
//        Execution execution = executionService.findExecutionById(task.getExecutionId());    
//        Execution processInstance = execution.getProcessInstance();    
//        ExecutionImpl superProcessExecution = ((ExecutionImpl)processInstance).getSuperProcessExecution(); 
//        String id = superProcessExecution.getProcessInstance().getId();
//	     return id;
//}
	


	/**
	 * 根据taskID获取processInstranceID
	 */
	public String getProcessInstanceId(String taskId) {   
	    Task task = taskService.getTask(taskId);    
	    Execution execution = executionService.findExecutionById(task.getExecutionId());    
	    Execution processInstance = execution.getProcessInstance();        
	    return  processInstance.getId();   
	} 
	
	/**
	 * 根据taskID获取processInstranceID
	 */
	public String getProcessInstanceId2(String taskId) {
		ProcessInstance processInstance = processEngine.getExecutionService().findProcessInstanceById(taskService.getTask(taskId).getExecutionId());
		if(processInstance!=null){
			return processInstance.getId();
		}else{
			return null;
		}
	} 
	
	/**
	 * 根据实例id获取先流程任务的上一个任务名称
	 * @param processInstanceId
	 * @return 上一个历史任务的名称
	 */
	@SuppressWarnings("rawtypes")
	private String getHistoryTask(String processInstanceId,Map map){
		List<HistoryActivityInstance> historyActivity =  historyService.createHistoryActivityInstanceQuery().processInstanceId(processInstanceId).orderAsc("dbid").list();
		int count=2;
		if(map.get("backStep")!=null){
			count = Integer.parseInt(map.get("backStep").toString());
		}
		int i = historyActivity.size()-count ;
		String historyTaskName = historyActivity.get(i).getActivityName();
		while(historyTaskName.contains("exclusive")){
			i = historyActivity.size()-(++count) ;
			historyTaskName = historyActivity.get(i).getActivityName();
		}
		return historyTaskName;
	}
	
	
	
	/**
	 * 撤回上一个任务节点
	 * @param processInstanceId
	 * @param map 执行的map
	 */
	 public void recalls(String processInstanceId,Map<String,Object> map){
		 
		 String pdId = getProcessDefinitionID(processInstanceId);
		 ProcessDefinition  pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
		 Task task = this.taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult();
		 String toTaskName = getHistoryTask(processInstanceId,map);
		 ProcessDefinitionImpl pdi = (ProcessDefinitionImpl)pd;   
	     TaskImpl wetask = (TaskImpl)task;   
		 addOutTransition(pdi,wetask,toTaskName,map);
	 }
	 /**
		 * 转到任意节点
		 * @param processInstanceId
		 * @param map 执行的map
		 */
		 public void goTaskNoteByNoteName(String processInstanceId,String noteName){
			 Map<String,Object> map = getVariables(processInstanceId);
			 String pdId = getProcessDefinitionID(processInstanceId);
			 ProcessDefinition  pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
			 Task task = this.taskService.createTaskQuery().processInstanceId(processInstanceId).uniqueResult();
			 String toTaskName = noteName;//航安部信息员提交
			 ProcessDefinitionImpl pdi = (ProcessDefinitionImpl)pd;   
		     TaskImpl wetask = (TaskImpl)task;   
			 addOutTransition(pdi,wetask,toTaskName,map);
		 }
	 public void addOutTransition(Task task,String to,Map<String,Object> map){
		 String processInstanceId=getProcessInstanceId(task.getId());
		 String pdId = getProcessDefinitionID(processInstanceId);
		 ProcessDefinition  pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
		 ProcessDefinitionImpl pdi = (ProcessDefinitionImpl)pd;   
		 TaskImpl wetask = (TaskImpl)task;   
		 addOutTransition(pdi,wetask,to,map);
	 }
	 
	 
	 
	 
	 
		/**
		 * 根据实例id得到所有任务节点
		 * @param processInstanceId
		 * @param map 执行的map
		 */
		 @SuppressWarnings("rawtypes")
		public Map getAllNodes(String processInstanceId){
			 Map<String, ActivityCoordinates> map = new HashMap<String, ActivityCoordinates>();
			 List<? extends Activity> activityList = null;
			 String pdId="";
				try {
					pdId = getProcessDefinitionID(processInstanceId);
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 ProcessDefinition  pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
			 ProcessDefinitionImpl pdi = (ProcessDefinitionImpl)pd;
			 //得到实例中所有节点对象
			 activityList = pdi.getActivities();
			 
			 ActivityCoordinates ac = null;
			 for(Activity aname:activityList){
				 ac = repositoryService.getActivityCoordinates(pdId,aname.getName());
//				 System.out.println(pdId+"--"+aname.getType()+"--"+aname.getName()+"--x="+ac.getX()+"--y="+ac.getY());//暂时只是打印出来
				 if(aname.getType().equals("task")){
					 map.put(aname.getName(), ac);
				 }
			 }
			 return map;
//		     TaskImpl wetask = (TaskImpl)task;   
		 }
	 
	
	 
	 
	  /**   
     * 动态创建连接当前任务节点至名称为destName的节点的Transition   
     * @param pd 流程定义   
     * @param sourceTask 源节点Task  
     * @param destName  目标节点名称   
     * @param map 执行map
     */   
    public void addOutTransition(ProcessDefinitionImpl pd,TaskImpl sourceTask,String destName,Map<String,Object> map){  
    	
         //取得当前流程的活动定定义   
         ActivityImpl sourceActivity = pd.findActivity(sourceTask.getActivityName());   
         //取得目标的活动定义   
         ActivityImpl destActivity=pd.findActivity(destName);   
         //为两个节点创建连接   
         TransitionImpl transition = sourceActivity.createOutgoingTransition();   
         transition.setName("to" + destName);   
         transition.setDestination(destActivity);   
         sourceActivity.addOutgoingTransition(transition);  
         this.completeTaskById(sourceTask.getId(), transition.getName(), map);
  }   
	
    /**
     * 根据传入task名称判定是否与当前task的上一个节点名称一致
     * @param taskName
     * @param processInstanceId
     * @return 一致返回true 否则 false
     */
    
//	public boolean contrastTaskName(String taskName , String processInstanceId){
//		boolean returnMark = false;
//		String thisTaskHistoryName = this.getHistoryTask(processInstanceId);
//		if(taskName.equals(thisTaskHistoryName)){
//			returnMark = true;
//		}
//		return returnMark ;
//	}
	/**
	 * 以级联的方式删除流程定义
	 * @param id
	 */
	public void deleteDeploymentCascade(String id){
		repositoryService.deleteDeploymentCascade(id);
	}
	
   
	public InputStream drawFlowsheet(String deploymentId,
			String imageResourceName) {
		return repositoryService.getResourceAsStream(deploymentId, imageResourceName);
	}


	public ProcessInstance getProcessInstanceById(String id) {
		return executionService.findProcessInstanceById(id);
	}
	
	
	
	public ProcessInstance getSubProcessInstance(String taskId){
		ExecutionImpl execution = (ExecutionImpl) executionService.findExecutionById("qualificationApply.44570001");
		 
//        ActivityImpl activitytemp =(ActivityImpl)execution.getActivity();
//        List<Transition> transitions = (List<Transition>) activitytemp.getOutgoingTransitions();
//        for(Transition transition1 : transitions){
//            System.out.println("transition name"+ transition1.getName());
//        }
		return  execution.getSubProcessInstance();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void viewFlowsheet(String id,HttpServletRequest request){
    	ProcessInstance processInstance =getProcessInstanceById(id);
    	Set<String> activityNames = processInstance.findActiveActivityNames();
    	System.out.println("activityNames==="+activityNames.iterator().next());
    	ActivityCoordinates ac = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityNames.iterator().next());
    	request.setAttribute("id", id);
    	request.setAttribute("ac", ac);
    	HistoryService historyService = processEngine.getHistoryService();
    	List<HistoryActivityInstance> histActInsts = historyService.createHistoryActivityInstanceQuery().processInstanceId(id).orderAsc("dbid").list(); 
    	
    	HistoryActivityInstance h =null;
    	ActivityCoordinates ac2 = null;
    	List acArr = new ArrayList();
    	List acArr2 = new ArrayList();
    	int activeCount = 0;
    	for(int i=0 ;i<histActInsts.size();i++){
    		h=(HistoryActivityInstance)histActInsts.get(i);
    		System.out.println(activeCount+"==name====="+h.getActivityName());
    		System.out.println("Transition====="+h.getTransitionNames());
    		
    		//获取历史节点坐标
    		ac2 = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),h.getActivityName());
    		acArr.add(ac2);
    		acArr2.add(i+1);
    		activeCount++;
    	}
    	request.setAttribute("activeCount",activeCount);
    	request.setAttribute("acs",acArr);
    	request.setAttribute("acs2",acArr2);
	}
	public Object getVariable(String taskId,String key){
		return taskService.getVariable(taskId, key);
	}
	public void setVariable(String taskId,String key,String value){
		Set<String> variableNames = taskService.getVariableNames(taskId);
		Map<String, Object> variables = taskService.getVariables(taskId, variableNames);
		variables.put(key, value);
		taskService.setVariables(taskId, variables);
		
	}
	/**
	 * 根据InstanceId设置map
	 * @param instanceId
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("rawtypes")
	public void setVariableByInstanceId(String instanceId,String key,Map map){
		executionService.setVariable(instanceId, key,map);
	}
	

	/**
	 * 根据任务Id得到流程实例Map
	 * @param taskId
	 * @return
	 */
	public Map<String, Object> getProcessMapById(String taskId) {
		Set<String> variableNames = taskService.getVariableNames(taskId);
		Map<String, Object> variables = taskService.getVariables(taskId, variableNames);
		return variables;
	}
	
	/**
	 * 根据流程实例Id得到流程实例Map
	 * @param processInstanceId
	 * @return
	 */
	public Map<String, Object> getVariables(String processInstanceId) {
		Set<String> variableNames = executionService.getVariableNames(processInstanceId);
		return executionService.getVariables(processInstanceId, variableNames);
	}
	/**
	 * 根据流程实例Id得到流程实例Map中指定的变量
	 * @param processInstanceId
	 * @return
	 */
	public Object getVariableBykey(String processInstanceId,String key) {
		return executionService.getVariable(processInstanceId, key);
	}
	/**
	 * 根据流程实例Id取得当前节点名称
	 * @param processInstanceId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCurrentNodeName(String processInstanceId) {
//		Iterator it = this.getProcessInstanceById(processInstanceId).findActiveActivityNames().iterator();
		String str =""; 
		for(Iterator it = this.getProcessInstanceById(processInstanceId).findActiveActivityNames().iterator(); it.hasNext();){
			str = (String)it.next();
			break;
		}
		return str;
	}
	/**
	 * 根据流程实例id结束流程实例
	 * @param processInstanceId
	 * @return
	 */
	public boolean deleteProcessInstanceByPiId(String processInstanceId) {
		try {
			this.processEngine.getExecutionService().deleteProcessInstanceCascade(processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String getActivityNameByTaskId(String taskId){
		Task task = taskService.getTask(taskId);    
	    task.getActivityName();
//	    System.out.println("name=="+task.getName());
//	    System.out.println("Aname"+task.getActivityName());
	    return task.getActivityName();
	}
	
	
	public String getActivityNameByProcessInstanceId(String processInstanceId){
		String taskId=getTaskIdByPiID(processInstanceId);
		Task task = taskService.getTask(taskId);    
	    task.getActivityName();
//	    System.out.println("name=="+task.getName());
//	    System.out.println("Aname"+task.getActivityName());
	    return task.getActivityName();
	}
	
	
	
	
	/**
	 * 返回当前活动节点任务执行人的单位和角色
	 * @param taskId
	 * @return
	 */
	public String getActivityiAssigneeByTaskId(String PiId){
		String taskId = "";
		//得到实例当前的任务id
		taskId = getTaskIdByPiID(PiId);
		if(taskId==null){
			return "";
		}
		//根据任务id得到
		Task task = taskService.getTask(taskId);
		String result = "";
	    if(task == null){
	    	return "";
	    }else{
	    	result = task.getAssignee();
	    }
	    return result;
	}
	
	
	public static void main(String[] args) {
		ProcessBase processBase=new ProcessBase();
		processBase.deleteProcessInstanceByPiId("enExam.31330221");
		
	}
	
	
	
	
	

}

