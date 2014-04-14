package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualPilotDetailBS;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.business.service.TfQualDeclaraPilotBS;


/**
 * * 制定 “转机型差异” 资质申请流程action
 * @author 张会粉
 * @date 2012/11/7
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
	public class TfQuaApplyDifferenceAction extends BaseAction{
		private static final long serialVersionUID = 1L;
		private static final String NAV_TAB_ID="qualApply_tabId";
		// 消息实体
		private Message message;
		// 资质申请流程
		private static ProcessBase processBase=new ProcessBase();
		// 任务传递
		private String taskId;
		private String processId;
		private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
		private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
		//计划信息名称
		private String planInfoName;
		//申报信息ID
		private String declaredinfoid;
		// 资质申请的流程操作对象
		private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
		private String formUrl; 
		private String ifShow;
		
		private TfQualDeclaraPilot tfQualDeclaraPilot;
		private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
		private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
		private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
		// 当前模块名称
		private String moduleName = "TfQuaApplyDifference";
		private String detailsid;
		private String subProcessid;
		//资质类型 子类id
		private String subGroupId;
		private String ids;
		private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
		private ITfQualPilotDetailBS tfQualPilotDetailBS;
		//上传资料列表
		private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS;
		//定义一个list 用来存放上传文件数量
		private List<Integer> statusList = new ArrayList<Integer>();
		
		/**
		 * 构造函数
		 */
		public TfQuaApplyDifferenceAction(){	
		}
		
		/**
		 * 分公司专员审核  liusiyuan
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
				formUrl="tf-qua-apply/tf-qua-apply-difference!toJianChaYuanYuanConfirm.do";
				}
			}catch (RuntimeException e) {
				tfQualifiedDeclareBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toLsyForm";
		}
		
		/**
		 * 结束流程，流转到刘思远
		 * @return
		 */
		public String toJianChaYuanYuanConfirm(){
			try {
				SysOrganization sysOrganization = getUserOrg();
				if(!ids.equals(""))	{
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (TfQualDeclaraPilot pilot : tfQualDeclaraPilotList){
								//根据当前选择下发的人员对原计划人员列表进行驳回处理
								if(!ids.contains(pilot.getDetailsid())){
									pilot.setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(pilot);
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(pilot.getCmPeople().getId(),subGroupId);
									continue;
								}
								//当流程扭转成功后 调整申报人员的状态
								pilot.setState("DXL");//人员状态 待训练
								if(sysOrganization.getParent_Fun().getName().contains("天津")){
									pilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-M-j-F1-F2-训练专员");
								}else {
									pilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-M-j-F1-F2-训练专员");
								}
								tfQualDeclaraPilotBS.saveOrUpdate(pilot);
							}
						}
					}
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("XlZhuanYuan", ConstantList.TJFGB+"-"+"资质-M-j-F1-F2-训练专员");
				}else{
					map.put("XlZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-M-j-F1-F2-训练专员");
				}
				//保存待办需要的机构及人员
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
				this.message = this.getSuccessMessage("流程下发成功！", NAV_TAB_ID, "closeCurrent", "");
				if(ids!=null && !"".equals(ids)){
					tfQualPilotDetailBS.savePilotDetais(ids);
				}
			}catch(Exception e)	{
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
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
				if(detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					tfQualDeclaraPilot.setState("DZZGX");
					if(sysOrganization.getName().contains("天津")){
						tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
					}else{
						tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
					}	
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
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
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司飞管专员发文确认", map);
					String taskIdString = processBase.getTaskIdByPiID(subProcessid);
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
					this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID,"", "");
					//扭转流程
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		//=======================getters and setters============================
	
		@JSON(serialize = false)
		public String getSubProcessid() {
			return subProcessid;
		}

		public void setSubProcessid(String subProcessid) {
			this.subProcessid = subProcessid;
		}

		@JSON(serialize = false)
		public String getDetailsid() {
			return detailsid;
		}

		public void setDetailsid(String detailsid) {
			this.detailsid = detailsid;
		}

		@JSON(serialize = false)
		public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
			return tfQuaApplyJbpmBS;
		}

		public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
			this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
		}

		@JSON(serialize = false)
		public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
		}

		@JSON(serialize = false)
		public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
			return tfQualDeclaraPilotList;
		}

		public void setTfQualDeclaraPilotList(List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
			this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
		}

		@JSON(serialize = false)		
		public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
			return tfQualifiedDeclareBS;
		}
	
		public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
			this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
		}
	
		@JSON(serialize = false)	
		public TfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
			return tfQualDeclaraPilotBS;
		}
	
		public void setTfQualDeclaraPilotBS(TfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
			this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
		}
	
		@JSON(serialize = false)	
		public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
			return tfQualDeclarApprovalInfoBS;
		}
	
		public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
			this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
		}
	
		@JSON(serialize = false)	
		public String getTaskId() {
			return taskId;
		}
	
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
	
		@JSON(serialize = false)	
		public List<TfQualDeclarApprovalinfo> getTfQualDeclarApprovalinfoList() {
			return tfQualDeclarApprovalinfoList;
		}
	
		public void setTfQualDeclarApprovalinfoList(List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList) {
			this.tfQualDeclarApprovalinfoList = tfQualDeclarApprovalinfoList;
		}
	
		@JSON(serialize = false)	
		public String getPlanInfoName() {
			return planInfoName;
		}
	
		public void setPlanInfoName(String planInfoName) {
			this.planInfoName = planInfoName;
		}
	
		@JSON(serialize = false)	
		public String getModuleName() {
			return moduleName;
		}
	
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
	
		@JSON(serialize = false)	
		public String getIfShow() {
			return ifShow;
		}
	
		public void setIfShow(String ifShow) {
			this.ifShow = ifShow;
		}
	
		@JSON(serialize = false)	
		public String getFormUrl() {
			return formUrl;
		}
	
		public void setFormUrl(String formUrl) {
			this.formUrl = formUrl;
		}
	
		public Message getMessage() {
			return message;
		}
		public void setMessage(Message message) {
			this.message = message;
		}	
		public String getProcessId() {
			return processId;
		}

		public void setProcessId(String processId) {
			this.processId = processId;
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
		@JSON(serialize = false)
		public ITfQualPilotDetailBS getTfQualPilotDetailBS() {
			return tfQualPilotDetailBS;
		}

		public void setTfQualPilotDetailBS(ITfQualPilotDetailBS tfQualPilotDetailBS) {
			this.tfQualPilotDetailBS = tfQualPilotDetailBS;
		}
		@JSON(serialize = false)
		public ITfQualPilotDocumentsBS getTfQualPilotDocumentsBS() {
			return tfQualPilotDocumentsBS;
		}

		public void setTfQualPilotDocumentsBS(ITfQualPilotDocumentsBS tfQualPilotDocumentsBS) {
			this.tfQualPilotDocumentsBS = tfQualPilotDocumentsBS;
		}
		@JSON(serialize = false)
		public List<Integer> getStatusList() {
			return statusList;
		}

		public void setStatusList(List<Integer> statusList) {
			this.statusList = statusList;
		}	
		
}
