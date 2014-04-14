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
 * 制定特殊资格申请流程action
 * 
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "qualPilotList", location ="/sms/training/qualification/quaApplySpecial/qualSpecialList.jsp"),
		@Result(name = "submit",        location ="/sms/training/qualification/quaApplySpecial/qualSpecialSubmit.jsp"),
		@Result(name = "toLsyForm",     location ="/sms/training/qualification/quaApplySpecial/qualApplySubmit_Special.jsp"),
		})
		
public class TfQuaApplySpecialAction  extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final String NAV_TAB_ID="qualApply_tabId";
	// 消息实体
	private Message message;
	//BS层
	private String ids;
	//计划信息名称
	private String planInfoName;
	//申报信息ID
	private String declaredinfoid;
	private static ProcessBase processBase=new ProcessBase();
	// 当前模块名称
	private String moduleName = "TfQuaApplySpecial";
	// 添加成功表示
	private boolean successFlag = true;
	// 任务传递
	private String taskId;
	/**资质类型 子类id*/
	private String subGroupId;
	private String processId;
	private String formUrl; 
	private String ifShow;
	private String userId;
	private String detailsid;
	private String subProcessid;
	private String opinionInfo;
	private String typeids;
//	private String qtgroupid;
	private String state;
	private Date qianmingDate;
	//用来刷新待申报信息页面
	private String declarInfoTabId;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private TfQualDeclarInfo tfQualDeclarInfo;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	
	// 资质申请的service对象
	private IProcesshistoryBS processhistoryBS;
	private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
	private ITfQuaApplyBS tfQuaApplyBS;
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	private List<TfQualBaseType> tfQualBaseTypeList;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	private List<TfQualPilotLicence> tfQualPilotLicenceList = new ArrayList<TfQualPilotLicence>();
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;

	
		/**
		 * 构造函数
		 */
		public TfQuaApplySpecialAction(){	
		}
		
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
				this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findAllTfQualDeclaraPilotStay("8a8a11fc392a363601392a5028420000");
				setFormUrl("tf-qua-apply/tf-qua-apply-special!toSubmit.do");
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			return "qualPilotList";
		}
		
		/**
		 * 机队申报页面跳转
		 */

		public String  toSubmit(){
			try{ 
				CmUser user = getUser();
				userId=user.getUserId();
				//没用到，暂时注掉
//				List<TfQualPilotLicence> licences=null;
				//获取此次申报信息
				if(declaredinfoid!=null && !declaredinfoid.equals("")){
					tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
					 //获取此次申报的人员信息
					if(tfQualDeclarInfo!=null){
						planInfoName=tfQualDeclarInfo.getDeclaredinfodesc();
					}
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				}
					formUrl="tf-qua-apply/tf-qua-apply-special!quaApplyAdd.do";
 			}catch (RuntimeException e) {
				e.printStackTrace();
			}
			return "submit";
		}
		
		/**
		 * 执行制定计划操作，开启流程
		 */
		public String quaApplyAdd() {
			try{
				// 登录用户信息
				CmUser user = getUser();
				// 拿到当前机构
				SysOrganization sysOrganization = getUserOrg();
				if(state!=null && !state.equals("")){
					if(state.equals("btg")){
						this.message = this.getSuccessMessage("申请不通过！", NAV_TAB_ID,"closeCurrent","tf-qua-apply/tf-qua-apply-special!quaApplyAdd.do" );
					}else{
						//获取此次申报信息 
						tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
						if(subGroupId==null||"".equals(subGroupId.trim())){
							subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
						}
                        //部署资质申报流程
						String processName=null;
						processName=ConstantList.QUALIFICATION_APPLY_SPECIAL;
						String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(processName);
						// 开启资质申报流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						mapStart.put("JiDui", sysOrganization.getId());
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    // 将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						//保存流程公共信息  转到declar info表中
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsZhuanYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
						}else{
							map.put("FgsZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
						}
						//保存流程信息 
						tfQualDeclarInfo.setProcessid(pid);
						tfQualDeclarInfo.setStatus("F");
						tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
						//根据当前选择下发的人员对原计划人员列表进行删除操作
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						if(!ids.equals("")){
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)	{
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
						map.put("planInfoId", declaredinfoid);//申报新Id
						map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());//申报新Id
						map.put("subGroupId", subGroupId);
						//获取此次申报信息
						String taskIdString = processBase.getTaskIdByPiID(pid);  
						// 存储流程历史处理人和历史处理机构
						processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
						//讲流程流转向下
						tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司级飞管标准专员审核报批", map);
						//保存审批意见
						tfQualDeclarApprovalinfo= new TfQualDeclarApprovalinfo();
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalinfo.setPid(pid);
						//查询 userID 获得机构名称
						tfQualDeclarApprovalinfo.setOrgName(sysOrganization.getName());
						tfQualDeclarApprovalinfo.setSigningDate(qianmingDate);
						tfQualDeclarApprovalinfo.setSigningid(user.getUserId());//此处未保存 签名ID 
						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
						setSuccessFlag(true);
						if(declarInfoTabId!=null && declarInfoTabId.equals("declarInfoTab")){
							this.message = this.getSuccessMessage("申请成功！", "declarInfoTab","closeCurrent","");
						}else{
							this.message = this.getSuccessMessage("申请成功！", NAV_TAB_ID,"closeCurrent","");
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
		 * 经理审批页面
		 * @return
		 */
		public String toJingLiConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)	{
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						// 登录用户信息
						CmUser user = getUser();
						// 拿到当前机构
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						String pid = processBase.getProcessInstanceId(taskId);
						//保存审批意见
						tfQualDeclarApprovalinfo = new TfQualDeclarApprovalinfo();
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
					    tfQualDeclarApprovalinfo.setPid(pid);
					    tfQualDeclarApprovalinfo.setOrgName(sysOrganization.getName());
						tfQualDeclarApprovalinfo.setSigningDate(qianmingDate);
						tfQualDeclarApprovalinfo.setSigningid(user.getUserId());//此处未保存 签名ID 
						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
						if(sysOrganization.getName().contains("天津")){
							map.put("FgsZhuanYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
						}else{
							map.put("FgsZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
						}
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管标准专员申报", map);
						setSuccessFlag(true);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
					}
				}
				
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		
		/**
		 * 公司飞管专员申报
		 * @return
		 */
		public String toGsZhuanYuanSbForm(){
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
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
					}
					ifShow="GsZhuanYuanSb"; 
					formUrl="tf-qua-apply/tf-qua-apply-special!toJianChaYuanYuanConfirm.do";
				}
			}catch (RuntimeException e) {
				e.printStackTrace();
			}
			return "toLsyForm";
		}
		
		public String toJianChaYuanYuanConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("") ){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (TfQualDeclaraPilot pilot : tfQualDeclaraPilotList)	{
								if(ids.contains(pilot.getDetailsid())){
									pilot.setState("DXL");//人员状态 带训练
									pilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-训练专员");
								}else{
									pilot.setState("REJECT");
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(pilot.getCmPeople().getId(),subGroupId);
								}
								tfQualDeclaraPilotBS.saveOrUpdate(pilot);
							}
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("XlZhuanYuan",ConstantList.GHFGBID+"-"+"资质-训练专员"); 
						//保存待办需要的机构及人员
						map.put("FromOrgName", getUserOrg().getName());
						map.put("FromOrgUser", getUser().getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		
		
		//=======================getters and setters============================
		


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
			TfQuaApplySpecialAction.processBase = processBase;
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
//		@JSON(serialize = false)
//		public String getQtgroupid() {
//			return qtgroupid;
//		}
//
//		public void setQtgroupid(String qtgroupid) {
//			this.qtgroupid = qtgroupid;
//		}
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
		@JSON(serialize = false)
		public ITfQualPilotLicenceBS getTfQualPilotLicenceBS() {
			return tfQualPilotLicenceBS;
		}

		public void setTfQualPilotLicenceBS(ITfQualPilotLicenceBS tfQualPilotLicenceBS) {
			this.tfQualPilotLicenceBS = tfQualPilotLicenceBS;
		}
		@JSON(serialize = false)
		public List<TfQualPilotLicence> getTfQualPilotLicenceList() {
			return tfQualPilotLicenceList;
		}

		public void setTfQualPilotLicenceList(List<TfQualPilotLicence> tfQualPilotLicenceList) {
			this.tfQualPilotLicenceList = tfQualPilotLicenceList;
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
		public Date getQianmingDate() {
			return qianmingDate;
		}

		public void setQianmingDate(Date qianmingDate) {
			this.qianmingDate = qianmingDate;
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
		
		
		
}