package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.*;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.*;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;
import com.sms.training.qualification.business.service.*;
/**
 * 制定除防冰教员流程action
 * 
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "qualPilotList", location ="/sms/training/qualification/quaApplyIceTeacher/qualIceTeacherList.jsp"),
		@Result(name = "submit",        location ="/sms/training/qualification/quaApplyIceTeacher/qualIceTeacherSubmit.jsp"),
		@Result(name = "toZhuanYuanForm", location ="/sms/training/qualification/quaApplyIceTeacher/quaIceTeacherSubmitzy.jsp"),
		})
		
public class TfQuaApplyIceTeacherAction  extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final String NAV_TAB_ID="qualApply_tabId";
	private static ProcessBase processBase=new ProcessBase();
	// 添加成功表示
	private boolean successFlag = true;
	// 消息实体
	private Message message;

	private String planInfoName;
	private String declaredinfoid;
	private String taskId;
	private String subGroupId;
	private String processId;
	private String formUrl; 
	private String ifShow;
	private String userId;
	private String detailsid;
	private String subProcessid;
	private String opinionInfo;
	private String typeids;
	private String state;
	private String orgName;
	private String ids;
	private String declarInfoTabId;
	
	private TfQualDeclarInfo tfQualDeclarInfo;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;	
	private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	
	
	// 资质申请的service对象
	private ITfQualPilotDetailBS tfQualPilotDetailBS;
	private IProcesshistoryBS processhistoryBS;
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS;
	
	//定义一个list 用来存放是否已上传文件的标志
	private List<Integer> statusList=new ArrayList<Integer>();
	private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	private List<TfQualBaseType> tfQualBaseTypeList;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();


	
		/**
		 * 构造函数
		 */
		public TfQuaApplyIceTeacherAction(){
			
		}
		
		/**
		 * 待申报人员列表 页面
		 */
		public String list(){
			try {
				CmUser user = getUser();
				userId=user.getUserId();
				this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
				if(typeids == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
					typeids = tfQualBaseTypeList.get(0).getTypeid();
				}
			//暂时先写死，要改	
			//	this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findAllTfQualDeclaraPilotStay(typeids);
				this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findAllTfQualDeclaraPilotStay("8a8a135e3a6394e0013a645630870000");
				setFormUrl("tf-qua-apply/tf-qua-apply-ice-teacher!toSubmit.do");
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			return "qualPilotList";
		}
		
		/**
		 * 机队申报页面跳转到填写意见的页面
		 */
		public String  toSubmit(){
			try
			{ 
				CmUser user = getUser();
				userId=user.getUserId();
				orgName=this.getUserOrg().getName();
				//   获取此次申报信息
				if(declaredinfoid!=null && !declaredinfoid.equals(""))
				{
					tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
			    //  获取此次申报的人员信息
					if(tfQualDeclarInfo!=null)
					{
						planInfoName=tfQualDeclarInfo.getDeclaredinfodesc();
					}
					List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList = tfQualifiedDeclareBS.findApprovalinfoByDeclaredinfoid(declaredinfoid);
					if(tfQualDeclarApprovalinfoList.size()>0){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfoList.get(0);
					}
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				//   判断是否已经上传文件
					for(int t=0,len=tfQualDeclaraPilotList.size();t<len;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
				//  下一步执行的方法
					formUrl="tf-qua-apply/tf-qua-apply-ice-teacher!quaApplyAdd.do";
 			}catch (RuntimeException e) {
				e.printStackTrace();
			}
			return "submit";
		}
		/**
		 * 执行制定计划操作，开启流程
		 */
		public String quaApplyAdd(){
			try{
				// 登录用户信息
				CmUser user = getUser();
				// 拿到当前机构
				SysOrganization sysOrganization = getUserOrg();
				if(state!=null && !state.equals("")){
					if(state.equals("btg")){
						this.message = this.getSuccessMessage("审核不通过!", NAV_TAB_ID,"closeCurrent","tf-qua-apply/tf-qua-apply-ice-teacher!quaApplyAdd.do" );
					}else{
						//获取此次申报信息 
						tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
						if(subGroupId==null||"".equals(subGroupId.trim())){
							subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
						}
                        //部署资质申报流程
						String processName=null;
						processName=ConstantList.QUALIFICATION_APPLY_ICE_TEACHER;
						String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(processName);
						// 开启资质申报流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						mapStart.put("FgsZhuanYuan", sysOrganization.getId());
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    // 将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						//保存流程公共信息  转到declar info表中
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsLingDao", ConstantList.TJFGS+"-"+"资质-分公司级飞管领导");
						}else{
							map.put("FgsLingDao", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管领导");
						}
						//把流程信息保存到申报信息中,保存流程信息 
						tfQualDeclarInfo.setProcessid(pid);
						//what is "F"
						tfQualDeclarInfo.setStatus("F");
						tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
						//根据当前选择下发的人员对原计划人员列表进行删除操作
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						if(!ids.equals("")){
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						map.put("FromOrgUserID", user.getUserId());
						map.put("task_plan", pid);
						//申报新Id
						map.put("planInfoId", declaredinfoid);
						map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
						map.put("subGroupId", subGroupId);
						//获取此次申报信息
						String taskIdString = processBase.getTaskIdByPiID(pid);
						// 存储流程历史处理人和历史处理机构
						processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
						//讲流程流转向下
						tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司级飞管领导审核报批", map);
						//保存审批意见
						if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
							tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
							tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
							tfQualDeclarApprovalinfo.setPid(pid);
							tfQualDeclarApprovalinfo.setState(state);
							tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
						}
						setSuccessFlag(true);
						if(declarInfoTabId!=null && declarInfoTabId.equals("declarInfoTab")){
							this.message = this.getSuccessMessage("审核成功!", "declarInfoTab","closeCurrent","");
						}else{
							this.message = this.getSuccessMessage("审核成功!", NAV_TAB_ID,"closeCurrent","");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				setSuccessFlag(false);
			}
			return "SUCCESS";
		}
		/**
		 * 分公司领导审核页面
		 * @return
		 */
		public String toFgsLingDaoForm(){
			userId=this.getUser().getUserId();
			try{
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					orgName=this.getUserOrg().getName();
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, userId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					formUrl="tf-qua-apply/tf-qua-apply-ice-teacher!toTeacherConfirm.do";
					ifShow="FgsLingDao";
				}
			}catch (RuntimeException e) { 
				e.printStackTrace();
			}
			return "toZhuanYuanForm";
		}
		
		/**
		 * 结束流程，流转到教员（张春雷）
		 * @return
		 */
		
		public String toTeacherConfirm(){
			try{
				SysOrganization sysOrganization = getUserOrg();
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
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
								pilot.setState("ZXL");//人员状态 在训练
								if(sysOrganization.getParent_Fun().getName().contains("天津")){
									pilot.setOrgRole(ConstantList.TJFXBDSZD+"-"+"资质-教员");
								}else {
									pilot.setOrgRole(ConstantList.ZDSDD+"-"+"资质-教员");
								}
								tfQualDeclaraPilotBS.saveOrUpdate(pilot);
							}
						}
					}
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("jiaoyuan", ConstantList.TJFXBDSZD+"-"+"资质-教员");
				}else{
					map.put("jiaoyuan", ConstantList.ZDSDD+"-"+"资质-教员");
				}
				//保存待办需要的机构及人员
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
				this.message = this.getSuccessMessage("上报成功！", NAV_TAB_ID, "closeCurrent", "");
				if(ids!=null && !"".equals(ids)){
					tfQualPilotDetailBS.savePilotDetais(ids);
				}
			}catch(Exception e)	{
				this.message = this.getFailMessage("流程扭转失败！");
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		//=======================getters and setters============================
		

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
		@JSON(serialize = false)
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		@JSON(serialize = false)
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
			TfQuaApplyIceTeacherAction.processBase = processBase;
		}
		public String getDeclaredinfoid() {
			return declaredinfoid;
		}
		public void setDeclaredinfoid(String declaredinfoid) {
			this.declaredinfoid = declaredinfoid;
		}
		@JSON(serialize = false)
		public String getPlanInfoName() {
			return planInfoName;
		}
		public void setPlanInfoName(String planInfoName) {
			this.planInfoName = planInfoName;
		}
		@JSON(serialize = false)
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}

		@JSON(serialize = false)
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
		public void setTfQualDeclaraPilotList(List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
			this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
		}
		
		@JSON(serialize = false)
		public TfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
			return tfQualDeclaraPilotBS;
		}

		public void setTfQualDeclaraPilotBS(TfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
			this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
		}
		@JSON(serialize = false)
		public String getDetailsid() {
			return detailsid;
		}

		public void setDetailsid(String detailsid) {
			this.detailsid = detailsid;
		}
		@JSON(serialize = false)
		public String getSubProcessid() {
			return subProcessid;
		}

		public void setSubProcessid(String subProcessid) {
			this.subProcessid = subProcessid;
		}
		@JSON(serialize = false)
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
		@JSON(serialize = false)
		public String getTypeids() {
			return typeids;
		}

		public void setTypeids(String typeids) {
			this.typeids = typeids;
		}

		@JSON(serialize = false)
		public ITfQuaApplyInspectorBS getTfQuaApplyInspectorBS() {
			return tfQuaApplyInspectorBS;
		}

		public void setTfQuaApplyInspectorBS(ITfQuaApplyInspectorBS tfQuaApplyInspectorBS) {
			this.tfQuaApplyInspectorBS = tfQuaApplyInspectorBS;
		}
		@JSON(serialize = false)
		public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStayList() {
			return tfQualDeclaraPilotStayList;
		}

		public void setTfQualDeclaraPilotStayList(List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
			this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
		}
		@JSON(serialize = false)
		public List<TfQualBaseType> getTfQualBaseTypeList() {
			return tfQualBaseTypeList;
		}

		public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
			this.tfQualBaseTypeList = tfQualBaseTypeList;
		}
		@JSON(serialize = false)
		public String getSubGroupId() {
			return subGroupId;
		}
		public void setSubGroupId(String subGroupId) {
			this.subGroupId = subGroupId;
		}
		@JSON(serialize = false)
		public TfQualDeclarInfo getTfQualDeclarInfo() {
			return tfQualDeclarInfo;
		}

		public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
			this.tfQualDeclarInfo = tfQualDeclarInfo;
		}
		
		public boolean isSuccessFlag() {
			return successFlag;
		}

		public void setSuccessFlag(boolean successFlag) {
			this.successFlag = successFlag;
		}
      
		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		@JSON(serialize = false)
		public String getDeclarInfoTabId() {
			return declarInfoTabId;
		}

		public void setDeclarInfoTabId(String declarInfoTabId) {
			this.declarInfoTabId = declarInfoTabId;
		}
		@JSON(serialize = false)
		public IProcesshistoryBS getProcesshistoryBS() {
			return processhistoryBS;
		}

		public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
			this.processhistoryBS = processhistoryBS;
		}
		@JSON(serialize = false)
		public String getOpinionInfo() {
			return opinionInfo;
		}

		public void setOpinionInfo(String opinionInfo) {
			this.opinionInfo = opinionInfo;
		}

		@JSON(serialize = false)
		public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
			return tfQualDeclarApprovalinfo;
		}

		public void setTfQualDeclarApprovalinfo(TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
			this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
			return tfQualDeclaraPilotStayBS;
		}

		public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
			this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
		}
		@JSON(serialize = false)
		public String getOrgName() {
			return orgName;
		}
		
		public void setOrgName(String orgName) {
			this.orgName = orgName;
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
		public ITfQualPilotDetailBS getTfQualPilotDetailBS() {
			return tfQualPilotDetailBS;
		}

		public void setTfQualPilotDetailBS(ITfQualPilotDetailBS tfQualPilotDetailBS) {
			this.tfQualPilotDetailBS = tfQualPilotDetailBS;
		}
		
		
		
}