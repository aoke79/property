package com.sms.training.qualification.web.action.jbpm4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.jbpm.api.task.Task;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
import com.sms.security.eventReport.business.IEventReportBS;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "indexTask_QUAL_UP",location = "/sms/training/qualification/quaJbpm4/indexTaskUP.jsp"),
		@Result(name = "indexTask_QUAL_YB",location = "/sms/training/qualification/quaJbpm4/indexTaskYB.jsp"),
		@Result(name = "indexTask_QUAL_TR",location = "/sms/training/qualification/quaJbpm4/indexTaskTR.jsp"),
		@Result(name = "indexTask_QUAL_SQ",location = "/sms/training/qualification/quaJbpm4/indexTaskSQ.jsp"),
		@Result(name = "indexTask_QUAL_FT",location = "/sms/training/qualification/quaJbpm4/indexTaskFT.jsp"),
		@Result(name = "indexTask_QUAL_CT",location = "/sms/training/qualification/quaJbpm4/indexTaskCT.jsp"),
		@Result(name = "indexTask_QUAL_EN",location = "/sms/training/qualification/quaJbpm4/indexTaskEN.jsp"),
		@Result(name = "indexTask_QUAL_NE",location = "/sms/training/qualification/quaJbpm4/indexTaskNE.jsp"),
		@Result(name = "indexTask_QUAL_QT",location = "/sms/training/qualification/quaJbpm4/indexTaskQT.jsp"),
		@Result(name = "dlIndex", location = "/sms/training/qualification/quaJbpm4/qualPilotSuperListTask.jsp"),
		@Result(name = "processDefinition", location = "/sms/training/english/workflow/enExam/processDefinition.jsp"),
		@Result(name = "processMonitor", location = "/sms/training/english/workflow/enExam/processMonitor.jsp"),
		@Result(name = "searchUnDealProcessOrg", location = "/sms/training/enExamTrain/searchUnDealProcessOrg.jsp"),
		@Result(name = "searchHistoryProcess", location = "/sms/training/enExamTrain/historyTask.jsp"),
		@Result(name = "searchSignUpHistoryProcess", location = "/sms/training/enExamTrain/historySignUpTask.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "flowsheet", location = "/sms/training/english/workflow/enExam/view.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "drawFlowsheet", type = "stream", params = {"inputName", "inputStream" }),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp"),
})
public class TfQualApplyJbpmAction extends BaseAction {
	private static final long serialVersionUID = 1780293853128531874L;
	// 部门的id和name的Map
	private Map<String, String> deptsList = new HashMap<String, String>();
	//获得当前机构ID
	private String curOrgID;
	private Message message;
	private String moduleName = "TfQualApplyJbpm";
	// 角色名称
	private String roleName;
	private SysRole sysRole;
	List<Task> taskList=new ArrayList<Task>(); // 待办任务列表
	ProcessBase processBase = new ProcessBase();
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	//任务和计划相关信息
	private Map<String, String> taskplanMap=new HashMap<String, String>();	
	private IEventReportBS eventReportBS;
	// 用于存放任务来自哪个机构
	private Map<String, String> orgsNameMap = new HashMap<String, String>();
	//用于存储任务是哪个人做的
	private Map<String, String> namesMap = new HashMap<String, String>();
	// 是否驳回
	private Map<String, String> isRejectMap = new HashMap<String, String>();
	// 驳回理由
	private Map<String, String> reasonMap = new HashMap<String, String>();
	private int count;
	private int countSB;
	private int countXl;
	private int countUpdate;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private String declarInfoId;
	/**资质类型 大类id*/
	private String qtGroupId;
	/**资质类型 子类id*/
	private String subGroupId;
	private String originalGrade;
	private List<Object> taskInfos;
	
	/**
	  * 待办及人员列表页面
	  * @return
	  */
	public String toIndexTask()
	{
		try {
			SysOrganization org = getUserOrg();
			String isT = "";
			List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
			
			//审核阶段的人数
			isT = this.tfQualDeclaraPilotBS.checkIsT(roleList);
			if("T".equals(isT) || "C".equals(isT) || "TC".equals(isT)){
				countXl=this.tfQualDeclaraPilotBS.getPilotCountByC(roleList,subGroupId, org.getId(),getUser(),isT);
			}else{
				countXl=this.tfQualDeclaraPilotBS.getPilotCountOfTraining(subGroupId, org.getId(), roleList);
			}
			
			//下发训练计划阶段  
			count=this.tfQualDeclaraPilotBS.getPilotCountOfIssued(subGroupId, org.getId(), roleList);
			
			//获取 待办的总人数
			String orgRole=null;
			for(SysRole role : roleList){
				orgRole = org.getId()+"-"+role.getRoleName();
				countSB += this.tfQualDeclaraPilotBS.getPilotCountOfApply(orgRole, subGroupId);
			}

			// 资质更新阶段的人数
			countUpdate=this.tfQualDeclaraPilotBS.getPilotCount4Update(subGroupId, org.getId(), roleList);
			
			// 处理子标签页跳转问题
			tabIndexSelection();
		} catch (Exception e) {
			this.message = this.getFailMessage("资质更新失败！");
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "indexTask_"+qtGroupId;
	}
	
	/**适时的清除session，并确保页面跳转无误*/
	private void tabIndexSelection(){// 处理子标签页跳转问题,暂时这么写
		String flush=(String) this.getServletRequest().getSession().getAttribute("flush_toIndexTask");
		String index=(String) this.getServletRequest().getSession().getAttribute("tabIndex_toIndexTask");
		//适时的清除session，并确保页面跳转无误
		if(flush!=null && !flush.equals("")){
			this.getServletRequest().getSession().removeAttribute("flush_toIndexTask");
		}else if(index!=null && !index.equals("")){
			this.getServletRequest().getSession().removeAttribute("tabIndex_toIndexTask");
		}
	}
	
	/**
	 * 申报阶段待办任务
	 * @return
	 */
	public String toIndex(){
		try{
			List<SysRole>  roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
			String orgRole = null;
			String orgId = this.getUserOrg().getId();
			taskInfos = new ArrayList<Object>();
			for(SysRole role : roleList){
				orgRole = orgId+"-"+role.getRoleName();
				taskInfos.addAll(this.tfQuaApplyJbpmBS.getQualityTaskInfos(orgRole, subGroupId));
			}
		}catch (Exception e) {
			this.message = this.getFailMessage("获取待办列表失败！");
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "index";
	}

/*	private  List<Task> getAllTask(String subGroupId,List<SysRole>  roleList)
	{
		try
		{
			orgMap();
			SysOrganization sysOrganization = getUserOrg();
			 curOrgID=sysOrganization.getId();
			//根据用户id得到任务
//			this.taskList.addAll(this.getTasksByUserId());

			//根据机构及角色名称获得待办
			for(int n=0,len=roleList.size();n<len;n++)
			{
				this.taskList.addAll(processBase.getTaskListByName(sysOrganization.getId()+"-"+roleList.get(n).getRoleName()));
			}
			// 过滤
			tfQuaApplyJbpmBS.filter(taskList,taskplanMap,deptsList,sysOrganization,subGroupId);
			// 用于处理任务来自哪个机构和由什么人处理
			tfQuaApplyJbpmBS.dealTaskFrom(taskList, namesMap, orgsNameMap,sysOrganization, deptsList, 3);
			// 判断每个任务是否是驳回的任务，如果是则做相应的处理
//			tfQuaApplyJbpmBS.isReject(taskList, isRejectMap, reasonMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return taskList;
	}*/
	public int getAllPilotCountByDeclarInfoId(String declarInfoId)
	{
		  
		tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByPeopleId(declarInfoId,"");
		if(tfQualDeclaraPilotList!=null && tfQualDeclaraPilotList.size()!=0)
		{
			return tfQualDeclaraPilotList.size();
		}
		else
		{
			return 0;
		}
	}

	public IEventReportBS getEventReportBS() {
		return eventReportBS;
	}

	public void setEventReportBS(IEventReportBS eventReportBS) {
		this.eventReportBS = eventReportBS;
	}

	/*	private void orgMap() {
			List<SysOrganization> sysOrganizationList = (List<SysOrganization>) getServletContext()
					.getAttribute("initBaseSysOrganizationList");
			for (SysOrganization sysOrganization : sysOrganizationList) {
				deptsList.put(sysOrganization.getId(), sysOrganization.getName());
			}

		}*/
		
		//根据用户ID得到任务
		@SuppressWarnings("unchecked")
		private List<Task> getTasksByUserId() {
			List<Task> tempList = null;
			String currUserIdentity = this.getUserOrg().getId() + "-" + this.getUser().getUserId();
			try {
				tempList = this.eventReportBS.getAllTasks(currUserIdentity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(tempList == null){
				tempList = new ArrayList<Task>();
			}
			return tempList;
		}
		@JSON(serialize = false)
		public Map<String, String> getDeptsList() {
			return deptsList;
		}

		public void setDeptsList(Map<String, String> deptsList) {
			this.deptsList = deptsList;
		}
		@JSON(serialize = false)
		public String getCurOrgID() {
			return curOrgID;
		}

		public void setCurOrgID(String curOrgID) {
			this.curOrgID = curOrgID;
		}
		@JSON(serialize = false)
		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		@JSON(serialize = false)
		public SysRole getSysRole() {
			return sysRole;
		}

		public void setSysRole(SysRole sysRole) {
			this.sysRole = sysRole;
		}
		@JSON(serialize = false)
		public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
			return tfQuaApplyJbpmBS;
		}

		public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
			this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
		}
		@JSON(serialize = false)
		public List<Task> getTaskList() {
			return taskList;
		}

		public void setTaskList(List<Task> taskList) {
			this.taskList = taskList;
		}
		@JSON(serialize = false)
		public ProcessBase getProcessBase() {
			return processBase;
		}

		public void setProcessBase(ProcessBase processBase) {
			this.processBase = processBase;
		}
		@JSON(serialize = false)
		public Map<String, String> getTaskplanMap() {
			return taskplanMap;
		}

		public void setTaskplanMap(Map<String, String> taskplanMap) {
			this.taskplanMap = taskplanMap;
		}
		@JSON(serialize = false)
		public Map<String, String> getOrgsNameMap() {
			return orgsNameMap;
		}

		public void setOrgsNameMap(Map<String, String> orgsNameMap) {
			this.orgsNameMap = orgsNameMap;
		}
		@JSON(serialize = false)
		public Map<String, String> getNamesMap() {
			return namesMap;
		}

		public void setNamesMap(Map<String, String> namesMap) {
			this.namesMap = namesMap;
		}
		@JSON(serialize = false)
		public Map<String, String> getIsRejectMap() {
			return isRejectMap;
		}

		public void setIsRejectMap(Map<String, String> isRejectMap) {
			this.isRejectMap = isRejectMap;
		}
		@JSON(serialize = false)
		public Map<String, String> getReasonMap() {
			return reasonMap;
		}

		public void setReasonMap(Map<String, String> reasonMap) {
			this.reasonMap = reasonMap;
		}
		@JSON(serialize = false)
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
			return tfQualDeclaraPilotBS;
		}

		public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
			this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
		}
		@JSON(serialize = false)
		public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
			return tfQualDeclaraPilotList;
		}

		public void setTfQualDeclaraPilotList(
				List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
			this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
		}
		public Message getMessage() {
			return message;
		}
		public void setMessage(Message message) {
			this.message = message;
		}
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		public String getDeclarInfoId() {
			return declarInfoId;
		}
		public void setDeclarInfoId(String declarInfoId) {
			this.declarInfoId = declarInfoId;
		}
		public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
			return tfQualifiedDeclareBS;
		}
		public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
			this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
		}
		public int getCountXl() {
			return countXl;
		}
		public void setCountXl(int countXl) {
			this.countXl = countXl;
		}

		public int getCountSB() {
			return countSB;
		}

		public void setCountSB(int countSB) {
			this.countSB = countSB;
		}

		public String getQtGroupId() {
			return qtGroupId;
		}
		public void setQtGroupId(String qtGroupId) {
			this.qtGroupId = qtGroupId;
		}

		@JSON(serialize = false)
		public int getCountUpdate() {
			return countUpdate;
		}
		public void setCountUpdate(int countUpdate) {
			this.countUpdate = countUpdate;
		}
		
		@JSON(serialize = false)
		public String getOriginalGrade() {
			return originalGrade;
		}
		public void setOriginalGrade(String originalGrade) {
			this.originalGrade = originalGrade;
		}
		@JSON(serialize = false)
		public String getSubGroupId() {
			return subGroupId;
		}
		public void setSubGroupId(String subGroupId) {
			this.subGroupId = subGroupId;
		}
		
		@JSON(serialize = false)
		public List<Object> getTaskInfos() {
			return taskInfos;
		}
		public void setTaskInfos(List<Object> taskInfos) {
			this.taskInfos = taskInfos;
		}
		
}
