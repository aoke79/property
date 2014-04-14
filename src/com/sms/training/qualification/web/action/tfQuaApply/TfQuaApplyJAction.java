package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.*;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.*;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;
import com.sms.training.qualification.business.service.*;

/**
 * 制定J-A1资质申请流程action
 * 
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "loadBrt", location = "/sms/training/englishtrain/tfEnTrainScene/trainSceneReportShow.jsp"),
		@Result(name = "toxlShenCha", location = "/sms/training/qualification/qualDeclaration/flightDataRecorder2.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "qualPilotList", location = "/sms/training/qualification/conditions/qualPilotList.jsp"),
		@Result(name = "submit", location ="/sms/training/qualification/quaApply/quaApplySubmit.jsp"),
		@Result(name = "toLsyForm", location ="/sms/training/qualification/quaApply/quaApplySubmit_liusiyuan.jsp"),
		})
		
public class TfQuaApplyJAction  extends BaseAction {
	
		private static final long serialVersionUID = 1L;
		private static final String NAV_TAB_ID="qualApply_tabId";
		// 消息实体
		private Message message;
		//BS层
		private ITfQuaApplyJBS tfQuaApplyJBS;
		private String ids;
		private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
		//计划信息名称
		private String planInfoName;
		//申报信息ID
		private String declaredinfoid;
		private static ProcessBase processBase=new ProcessBase();
		// 添加成功表示
		private boolean successFlag = true;
		// 资质申请的service对象
		private ITfQuaApplyBS tfQuaApplyBS;
		// 当前模块名称
		private String moduleName = "TfQuaApplyJ";
		// 任务传递
		private String taskId;
		private String processId;
		//保存流程信息的公共表
		private String formUrl; 
		private String ifShow;	
		private TfQualDeclaraPilot tfQualDeclaraPilot;
		private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
		private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
		private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
		private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
		private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
		private String detailsid;
		private String subProcessid;
		//定义一个list 用来存放上传文件数量
		private List<Integer> statusList = new ArrayList<Integer>();
		private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS; 
		private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
		//资质类型 子类id
		private String subGroupId;
				

		/**
		 * 构造函数
		 */
		public TfQuaApplyJAction(){	
		}
		

		
		/**
		 * 分公司专员审核 (王楠或刘思远)
		 * @return
		 */
		
		public String toFgsZhuanYuanForm(){
			try{
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						ifShow="fGsZhuanYuan"; 
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
				formUrl="tf-qua-apply/tf-qua-apply-j!toJianChaYuanYuanConfirm.do";
				}
			}catch (RuntimeException e) {
				tfQuaApplyJBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toLsyForm";
		}
		/**
		 * 结束流程，流转到分公司专员
		 * @return
		 */
		
		public String toJianChaYuanYuanConfirm(){
			try{
				SysOrganization sysOrganization = getUserOrg();
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("") ){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							//获取此次申报的人员信息
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (TfQualDeclaraPilot pilot : tfQualDeclaraPilotList){
								//根据当前选择下发的人员对原计划人员列表进行驳回处理
								if(!ids.contains(pilot.getDetailsid())){
									pilot.setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(pilot);
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(pilot.getCmPeople().getId(),subGroupId);
									continue;
								}
								// 当流程扭转成功后 调整申报人员的状态
								pilot.setState("DJC");//人员状态 待检查
								if(sysOrganization.getParent_Fun().getName().contains("天津")){
									pilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
								}else{
									pilot.setOrgRole( ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
								}
								tfQualDeclaraPilotBS.saveOrUpdate(pilot);
							}
						}
					}
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("JianChaYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员"); 
				}else{
					map.put("JianChaYuan",  ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
				}
				//保存待办需要的机构及人员
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
				this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,
						"closeCurrent", "");
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyJBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		
		/**新流程  分公司专员创建流程
		 * 
		 * @return
		 */
		public String toAddSubQualApplyJ(){
			try{
//				 部署资质申报流程
				CmUser user = getUser();
				SysOrganization sysOrganization = getUserOrg();
				String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.SUB_QUALIFICATION_APPLY_J);
				// 开启资质审核流程
				Map<String, Object> mapStart = new HashMap<String, Object>();
				mapStart.put("JianChaYuan", sysOrganization.getId());
				String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
			    // 将实例id保存到流程信息里
				Map<String, Object> map = new HashMap<String, Object>();
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("FgsZhuRen", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
				}else{
					map.put("FgsZhuRen", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
				}
				String taskIdString = processBase.getTaskIdByPiID(pid);
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("FromOrgUserID", user.getUserId());
				map.put("task_plan", pid);
				if(taskIdString!=null && !taskIdString.equals("") )
				{
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司级技术委员会办公室主任审核报批", map);
				}
				if(detailsid!=null && !detailsid.equals("") )
				{
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
							if(tfQualDeclaraPilot!=null)
							{
								tfQualDeclaraPilot.setState("FGSZR");
								if(sysOrganization.getParent_Fun().getName().contains("天津"))
								{
									tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
								}else
								{
									tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
								}
								
								tfQualDeclaraPilot.setSubProcessid(pid);
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
							}
				}
				//流程下发成功后 修改此人的state=DJC 及
				setSuccessFlag(true);
				setTabIndexToReload("1");//处理子标签页跳转问题
				this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"");
			}catch (Exception e) {
				e.printStackTrace();
				setSuccessFlag(false);
				tfQuaApplyJBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			}
			return "json";
		}

		/**
		 * 分公司技术委员会办公室主任 刘思远
		 * @return
		 */
		public String toFeiGuanZrBpsConfirm(){
			try{
				SysOrganization sysOrganization = getUserOrg();
				//调整 训练人员状态
				if(detailsid!=null && !detailsid.equals("") ){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					tfQualDeclaraPilot.setState("DZZGX");
					if(sysOrganization.getName().contains("天津")){
						tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
					}else{
						tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
					}
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
					//调整 训练人员状态
					//扭转流程
					if(!subProcessid.equals("")){
						taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
					}
					CmUser user = getUser();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					if(sysOrganization.getName().contains("天津")){
						map.put("GsZhuanYuanOver", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
					}else{
						map.put("GsZhuanYuanOver", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
					}
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
					String taskIdString = processBase.getTaskIdByPiID(subProcessid);
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
					setTabIndexToReload("0");
					this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID,"", "");
					//扭转流程
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		
		private void setTabIndexToReload(String index){ //处理子标签页跳转问题
			this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
			if(index!=null && !index.equals("0")){
				this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
			}
		}
			
		//=======================getters and setters============================
		
		public boolean isSuccessFlag() {
			return successFlag;
		}
		public void setSuccessFlag(boolean successFlag) {
			this.successFlag = successFlag;
		}
		
		@JSON(serialize = false)
		public ITfQuaApplyJBS getTfQuaApplyJBS() {
			return tfQuaApplyJBS;
		}

		public void setTfQuaApplyJBS(ITfQuaApplyJBS tfQuaApplyJBS) {
			this.tfQuaApplyJBS = tfQuaApplyJBS;
		}
		@JSON(serialize = false)
		public ITfQuaApplyBS getTfQuaApplyBS() {
			return tfQuaApplyBS;
		}
		public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
			this.tfQuaApplyBS = tfQuaApplyBS;
		}
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		@JSON(serialize = false)
		public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
			return tfQuaApplyJbpmBS;
		}
		public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
			this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
		}
		public Message getMessage() {
			return message;
		}
		public void setMessage(Message message) {
			this.message = message;
		}
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		public String getProcessId() {
			return processId;
		}
		public void setProcessId(String processId) {
			this.processId = processId;
		}

		@JSON(serialize = false)
		public ProcessBase getProcessBase() {
			return processBase;
		}
		public void setProcessBase(ProcessBase processBase) {
			TfQuaApplyJAction.processBase = processBase;
		}

		public String getDeclaredinfoid() {
			return declaredinfoid;
		}
		public void setDeclaredinfoid(String declaredinfoid) {
			this.declaredinfoid = declaredinfoid;
		}
		public String getPlanInfoName() {
			return planInfoName;
		}
		public void setPlanInfoName(String planInfoName) {
			this.planInfoName = planInfoName;
		}

		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}

		public String getFormUrl() {
			return formUrl;
		}
		public void setFormUrl(String formUrl) {
			this.formUrl = formUrl;
		}
		public String getIfShow() {
			return ifShow;
		}
		public void setIfShow(String ifShow) {
			this.ifShow = ifShow;
		}

		@JSON(serialize = false)
		public TfQualDeclaraPilot getTfQualDeclaraPilot() {
			return tfQualDeclaraPilot;
		}
		public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
			this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		}

		@JSON(serialize = false)
		public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
			return tfQualDeclarApprovalInfoBS;
		}
		public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
			this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
		}

		@JSON(serialize = false)
		public List<TfQualDeclarApprovalinfo> getTfQualDeclarApprovalinfoList() {
			return tfQualDeclarApprovalinfoList;
		}
		public void setTfQualDeclarApprovalinfoList(List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList) {
			this.tfQualDeclarApprovalinfoList = tfQualDeclarApprovalinfoList;
		}

		@JSON(serialize = false)
		public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
			return tfQualifiedDeclareBS;
		}
		public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
			this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
		}
		
		@JSON(serialize = false)
		public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
			return tfQualDeclaraPilotList;
		}
		public void setTfQualDeclaraPilotList(
				List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
			this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
		}

		@JSON(serialize = false)
		public TfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
			return tfQualDeclaraPilotBS;
		}

		public void setTfQualDeclaraPilotBS(TfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
			this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
		}
		public String getDetailsid() {
			return detailsid;
		}

		public void setDetailsid(String detailsid) {
			this.detailsid = detailsid;
		}

		public String getSubProcessid() {
			return subProcessid;
		}

		public void setSubProcessid(String subProcessid) {
			this.subProcessid = subProcessid;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@JSON(serialize = false)
		public List<Integer> getStatusList() {
			return statusList;
		}
		public void setStatusList(List<Integer> statusList) {
			this.statusList = statusList;
		}
		@JSON(serialize = false)
		public ITfQualPilotDocumentsBS getTfQualPilotDocumentsBS() {
			return tfQualPilotDocumentsBS;
		}
		public void setTfQualPilotDocumentsBS(ITfQualPilotDocumentsBS tfQualPilotDocumentsBS) {
			this.tfQualPilotDocumentsBS = tfQualPilotDocumentsBS;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
			return tfQualDeclaraPilotStayBS;
		}
		public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
			this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
		}
		@JSON(serialize = false)
		public String getSubGroupId() {
			return subGroupId;
		}
		public void setSubGroupId(String subGroupId) {
			this.subGroupId = subGroupId;
		}
 
}
