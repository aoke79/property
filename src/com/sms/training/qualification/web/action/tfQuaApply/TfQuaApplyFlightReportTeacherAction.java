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
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.bean.TfQualPilotCourselist;
import com.sms.training.qualification.business.ITfQuaApplyBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

/**
 * 英语大流程：飞行通讯教员 流程控制类
 * @author zhongchunping
 * 2013-05-28
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "json", type = "json"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "list", location ="/sms/training/qualification/conditions/qualPilotList.jsp"),
		@Result(name = "submit", location ="/sms/training/qualification/quaApply/quaApplySubmit.jsp"),
		@Result(name = "toWangnForm", location ="/sms/training/qualification/quaApply/quaApplySubmit_liusiyuan.jsp"),
		@Result(name = "toLiuXiForm", location ="/sms/training/qualification/quaApply/quaApplySubmitzy.jsp"),
		@Result(name = "toPilotList", location ="/sms/training/qualification/quaApply/quaApplySuccess_liusiyuan.jsp"),
})
public class TfQuaApplyFlightReportTeacherAction extends BaseAction {
	/** 资质类型分组*/
	private String qtgroupid;
	/** 资质类型 子类id*/
	private String subGroupId;
	/** 用户id*/
	private String userId;
	/** 存放资质类别集合*/
	private List<TfQualBaseType> tfQualBaseTypeList;
	/** 资质类别业务层对象*/
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	/** 资质类型ID*/
	private String typeids;
	/** 存放返回的请求URL*/
	private String formUrl; 
	/** 存放该资质类型下待申报人员集合*/
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	/** 申报人员信息业务层对象*/
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	/** 申报人员ID*/
	private String declaredinfoid;
	/** 申报信息实体对象*/
	private TfQualDeclarInfo tfQualDeclarInfo;
	/** 机构名称*/
	private String orgName;
	/** 申报信息名称*/
	private String planInfoName;
	/** 申报审核信息实体类对象*/
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	/** 申报人员明细集合类*/
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	/** 当前模块名称*/
	private String moduleName = "TfQuaApplyFlightReportTeacherAction";
	/** 消息实体*/
	private Message message;
	/** 大类页面rel*/
	private static final String NAV_TAB_ID="qualApply_tabId";
	/** 状态*/
	private String state;
	/** 资质申请的流程操作对象*/
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	/** 选中的申报人员id*/
	private String ids;
	/** 待申报人员业务成对象*/
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	/** 申报人员明细业务成对象*/
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	/** 流程基础类封装*/
	private ProcessBase processBase = new ProcessBase();
	/** 流程历史业务层对象*/
	private IProcesshistoryBS processhistoryBS;
	/** 申报审核意见信息业务层对象*/
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	/** 评审意见信息*/
	private String opinionInfo;
	/** 用来刷新待申报信息页面*/
	private String declarInfoTabId;
	/** 任务ID*/
	private String taskId;
	/** 流程id*/
	private String processId;
	/** 申报审核信息集合*/
	private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
	private String ifShow;
	/** 添加成功表示*/
	private boolean successFlag = true;
	/** 判断是否是分公司飞管领导*/
	private boolean isfgsfgld;
	/** 资质申请的service对象*/
	private ITfQuaApplyBS tfQuaApplyBS;
	/** 课程id*/
	private String courselistid;
	/** 课程实体对象*/
	private TfQualPilotCourselist tfQualPilotCourselist;
	/** 申报人员明细ID*/
	private String detailsid;
	/** 申报人员明细实体对象*/
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	/**下发流程id*/
	private String subProcessid;
	/***
	 *  跳到算人页面
	 * @return
	 */
	public String list(){
		try {
			CmUser user = getUser();
			userId=user.getUserId();
			this.tfQualBaseTypeList = this.tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
			if(typeids == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
				typeids = tfQualBaseTypeList.get(0).getTypeid();
			}
			//暂时先写死，要改	
			//this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findAllTfQualDeclaraPilotStay(typeids);
			this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findAllTfQualDeclaraPilotStay("8a8a135e3a6394e0013a645630870000");
			setFormUrl("tf-qua-apply/tf-qua-apply-flight-report-teacher!toSubmit.do");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "list";
	}
	/**
	 * 跳到闫钧申报填写意见页面
	 * @return
	 */
	public String toSubmit(){
		try{ 
			CmUser user = getUser();
			userId=user.getUserId();
			orgName=this.getUserOrg().getName();
			//获取此次申报信息
			if(null != declaredinfoid && !declaredinfoid.equals("")){
				tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				 //获取此次申报的人员信息
				if(tfQualDeclarInfo!=null){
					planInfoName=tfQualDeclarInfo.getDeclaredinfodesc();
				}
				//获取领导意见
				List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList = tfQualifiedDeclareBS.findApprovalinfoByDeclaredinfoid(declaredinfoid);
				if(tfQualDeclarApprovalinfoList.size()>0){
					tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfoList.get(0);
				}
				tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
			}
			   formUrl="tf-qua-apply/tf-qua-apply-flight-report-teacher!quaApplyAdd.do";
			}catch (RuntimeException e) {
			tfQualifiedDeclareBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "submit";
	}
	/**
	 * 闫钧审批成功后开启流程
	 * @return
	 */
	public String quaApplyAdd(){
		try{
			// 登录用户信息
			CmUser user = getUser();
			// 拿到当前机构
			SysOrganization sysOrganization = getUserOrg();
			if(state!=null && !state.equals("")){
				if(state.equals("btg")){
					this.message = this.getSuccessMessage("审核不通过!", NAV_TAB_ID,"closeCurrent","tf-qua-apply/tf-qua-apply!quaApplyAdd.do" );
				}else{
					//获取此次申报信息 
					tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
					//发布流程
					String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.QUALIFICATION_APPLY_ENGLISH_FXTXJY);
					// 开启资质申报流程
					Map<String, Object> mapStart = new HashMap<String, Object>();
					mapStart.put("JiDui", sysOrganization.getId());
					String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
				    // 将实例id保存到流程信息里
					Map<String, Object> map = new HashMap<String, Object>();
					//保存流程公共信息  转到declar_info表中
					if(sysOrganization.getParent_Fun().getName().contains("天津")){
						map.put("FgsZhuanYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
					}else{
						map.put("FgsZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
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
					map.put("planInfoId", declaredinfoid);//申报新Id
					map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
					map.put("subGroupId", subGroupId);
					//获取此次申报信息
					String taskIdString = processBase.getTaskIdByPiID(pid);
					// 存储流程历史处理人和历史处理机构
					processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
					//讲流程流转向下
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司级飞管专员审核审批", map);
					//保存审批意见
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalinfo.setPid(pid);
						tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
					}
					this.message = this.getSuccessMessage("审核成功!", NAV_TAB_ID,"closeCurrent","");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tfQualifiedDeclareBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "SUCCESS";
	}
	/**
	 * 分公司专员审核王楠  任务处理页面
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
					/*for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}*/
				}
				formUrl="tf-qua-apply/tf-qua-apply-flight-report-teacher!fgsZhuanYuanHandle.do";
			}
		}catch (RuntimeException e) {
			tfQualifiedDeclareBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toWangnForm";
	}
	/**
	 * 处理  王楠填完审核意见,扭转流程
	 * @return
	 */
	public String fgsZhuanYuanHandle(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(ids!=null && !ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0,n= tfQualDeclaraPilotList.size(); i <n; i++){
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}
						}
					}
					SysOrganization sysOrganization = getUserOrg();
					Map<String, Object> map = new HashMap<String, Object>();
					if(sysOrganization.getParent_Fun().getName().contains("天津")){
						map.put("FgsJingLi", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准高级经理");
					}else{
						map.put("FgsJingLi", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准高级经理");
					}
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", getUser().getName());
					map.put("planInfoId", declaredinfoid);
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管高级经理审核报批", map);
					this.message = this.getSuccessMessage("流程上报成功！" ,NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json"; 
	}
	/**
	 * 分公司经理刘曦  任务处理页面
	 * @return
	 */
	public String toFgsJingLiForm(){
		try{   
//			userId= this.getUser().getUserId();
			if(taskId!=null && !taskId.equals("")){
				processId=processBase.getProcessInstanceId(taskId);
				//单位名称
				orgName=this.getUserOrg().getName();
				if(!processId.equals("")){
					tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, getUser().getUserId());
				}
				declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
				planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
				//获取此次申报的人员信息
				if(declaredinfoid!=null && !declaredinfoid.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
					/*for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}*/
				}
				formUrl="tf-qua-apply/tf-qua-apply-flight-report-teacher!fgsJingliHandle.do";
			}
		}catch (RuntimeException e) {
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toLiuXiForm";
	}
	/**
	 * 处理   分公司经理刘曦审核意见，扭转流程
	 * @return
	 */
	public String fgsJingliHandle(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}
						}
					}
					CmUser user = getUser();
					SysOrganization sysOrganization = getUserOrg();
					Map<String, Object> map = new HashMap<String, Object>();
					//保存审批意见
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
					}
					if(sysOrganization.getParent_Fun().getName().contains("天津")){
						map.put("FgsLingDao", ConstantList.TJFGS+"-"+"资质-分公司级飞管领导");
					}else{
						map.put("FgsLingDao", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管领导");
					}
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管领导审核报批", map);
					setSuccessFlag(true);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,
							"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	/**
	 * 分公司领导审核页面
	 * @return
	 */
	public String toFgsLingDaoForm(){
		userId=this.getUser().getUserId();
		//判断流程身份
		String taskname=processBase.getActivityNameByTaskId(taskId);
		if(taskname.contains("分公司级飞管领导审核报批")){
			isfgsfgld=true;
		}else{
			isfgsfgld=false;
		}
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
					/*for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}*/
				}
				formUrl="tf-qua-apply/tf-qua-apply-flight-report-teacher!fgsLingDaoHandle.do";
				ifShow="FgsLingDao";
			}
		}catch (RuntimeException e) { 
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toLiuXiForm";
	}
	/**
	 * 分公司领导审批通过，流程跳转到公司专员报批
	 * @return
	 */
	public String fgsLingDaoHandle(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
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
					//保存审批意见
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
					}
					//保存流程公共信息  转到declar_info表中
					if(sysOrganization.getName().contains("天津")){
						map.put("FgsZhuanYuanXiaFa", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
					}else{
						map.put("FgsZhuanYuanXiaFa", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
					}
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管标准专员下发训练通知", map);
					setSuccessFlag(true);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	public String toFgsZhuanYuanXiaFaForm(){
		
		try{
			if(subGroupId!=null && !subGroupId.equals("")){
				declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
				if(!declaredinfoid.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				}
				//给页面传去 subGroupId下面的所有资质类型
				tfQualBaseTypeList = tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
				List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
//				tfQualDeclaraPilotList=this.tfQualDeclaraPilotBS.getPilotList2(subGroupId, getUserOrg().getId(), roleList);
				
				//此处是做假的 该根据高津川分组 获取分组ID获取人
//				if( tfQualDeclaraPilotList!=null && tfQualDeclaraPilotList.size()!=0){
//					declaredinfoid=tfQualDeclaraPilotList.get(0).getTfQualDeclarInfo().getDeclaredinfoid();
//				}
				formUrl="tf-qua-apply/tf-qua-apply-flight-report-teacher!xiaFaHandle.do?taskId="+taskId;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "toPilotList";
	}
	public String xiaFaHandle(){
		
		//根据当前选择下发的人员对原计划人员列表进行删除操作
		if(!ids.equals("")){
				if(!declaredinfoid.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
					for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
						if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
							tfQualDeclaraPilotList.get(i).setState("REJECT");
							this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							continue;
						}else{
							tfQualDeclaraPilotList.get(i).setState("DJC");	
						}
						tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
					}
			}
		}
		processId=processBase.getProcessInstanceId(taskId);
		//将实例id保存到流程信息里
		Map<String, Object> map = new HashMap<String, Object>();
		//获取此次申报信息
		String taskIdString = processBase.getTaskIdByPiID(processId);
		//存储流程历史处理人和历史处理机构
		processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
		tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束",map);
		this.message = this.getSuccessMessage("下发训练成功!", NAV_TAB_ID, "closeCurrent", "");
		return "json";
	}
	/**
	 * 检查员 检查完成后开启下半部分下发流程
	 * @return
	 */
	public String toJiaoYuanForm(){
		
		try{
			if(courselistid!=null && !courselistid.equals("")){
				tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
				//此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
				tfQualPilotCourselist.setState("TOVER");
				tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
				if(tfQualPilotCourselist!=null){
					//此处判断教员是否都填写完毕
					//if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
						CmUser user = getUser();
						SysOrganization sysOrganization = getUserOrg();
						//发布流程
						String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.SUB_QUALIFICATION_APPLY_ENGLISH_FXTXJY);
						//开启资质审核流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							mapStart.put("JianChaYuan", ConstantList.TJFXBDSZD+"-"+"资质-检查员");
						}else{
							mapStart.put("JianChaYuan", ConstantList.ZDSDD+"-"+"资质-检查员");
						}
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    // 将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						//得到代办事项id
						String taskIdString = processBase.getTaskIdByPiID(pid);
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsZyUpdate", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员"); 
						}else{
							map.put("FgsZyUpdate",  ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
						}
						map.put("FromOrgName", sysOrganization.getName()); 
						map.put("FromOrgUserID", user.getUserId());
						map.put("task_plan", pid);
						map.put("subGroupId",subGroupId);
						if(taskIdString!=null && !taskIdString.equals("")){
							tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司飞管专员发文资质更新", map);
						}
						//流程下发成功后 修改此人的state=DJC 及
						if(detailsid!=null && !detailsid.equals("")){
							tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid.trim());
							if(tfQualDeclaraPilot!=null){
								tfQualDeclaraPilot.setState("DZZGX");
								if(sysOrganization.getParent_Fun().getName().contains("天津")){
									tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
								}else{
									tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
								}
								
								tfQualDeclaraPilot.setSubProcessid(pid);
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
							 }		
						}
					}
						setSuccessFlag(true);
						this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"");
				}
			
		}catch (Exception e) {
			e.printStackTrace();
			setSuccessFlag(false);
			tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "json";
	}
	/**
	 * 分公司飞管专员 王楠 发文和资质更新
	 * @return
	 */
	public String toQualificationUpdateForm(){
		try{
			//调整 训练人员状态
			CmUser user = getUser();
			SysOrganization sysOrganization = getUserOrg();
			if(detailsid!=null &&  !detailsid.equals("") ){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("FGZYSB");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
				if(!subProcessid.equals("")){
					taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				if("QUAL_EN_FXTXJY".equals(subGroupId)){
					map.put("subGroupId",subGroupId);
				}
				map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员备案", map);
				setTabIndexToReload("1");// 处理子标签页跳转问题
				this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"");
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
		
	}
	/**
	 * 公司飞管专员 王菲 备案 结束流程
	 * @return
	 */
	public String toGsZhuanYuanOverForm(){
		
		
		return "";
	}
	
	private void setTabIndexToReload(String index){ // 处理子标签页跳转问题
		this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
		if(index!=null && !index.equals("0")){
			this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
		}
	}
	@JSON(serialize=false)
	public String getQtgroupid() {
		return qtgroupid;
	}

	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}
	//此处不要加false
	public String getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}

	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}
	@JSON(serialize=false)
	public String getTypeids() {
		return typeids;
	}

	public void setTypeids(String typeids) {
		this.typeids = typeids;
	}
	@JSON(serialize=false)
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	@JSON(serialize=false)
	public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStayList() {
		return tfQualDeclaraPilotStayList;
	}

	public void setTfQualDeclaraPilotStayList(
			List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	@JSON(serialize=false)
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}
	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}
	@JSON(serialize=false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}
	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}
	@JSON(serialize=false)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@JSON(serialize=false)
	public String getPlanInfoName() {
		return planInfoName;
	}
	public void setPlanInfoName(String planInfoName) {
		this.planInfoName = planInfoName;
	}
	@JSON(serialize=false)
	public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
		return tfQualDeclarApprovalinfo;
	}
	public void setTfQualDeclarApprovalinfo(
			TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
		this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
	}
	@JSON(serialize=false)
	public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
		return tfQualDeclaraPilotList;
	}
	public void setTfQualDeclaraPilotList(
			List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
		this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
	}
	@JSON(serialize=false)
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@JSON(serialize=false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	@JSON(serialize=false)
	public String getOpinionInfo() {
		return opinionInfo;
	}
	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}
	@JSON(serialize=false)
	public String getDeclarInfoTabId() {
		return declarInfoTabId;
	}
	public void setDeclarInfoTabId(String declarInfoTabId) {
		this.declarInfoTabId = declarInfoTabId;
	}
	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
	}
	public void setTfQualDeclaraPilotStayBS(
			ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
		this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
	}
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}
	public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
		this.processhistoryBS = processhistoryBS;
	}
	public void setTfQualDeclarApprovalInfoBS(
			ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
		this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
	}
	@JSON(serialize=false)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@JSON(serialize=false)
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	@JSON(serialize=false)
	public List<TfQualDeclarApprovalinfo> getTfQualDeclarApprovalinfoList() {
		return tfQualDeclarApprovalinfoList;
	}
	public void setTfQualDeclarApprovalinfoList(
			List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList) {
		this.tfQualDeclarApprovalinfoList = tfQualDeclarApprovalinfoList;
	}
	@JSON(serialize=false)
	public String getIfShow() {
		return ifShow;
	}
	public void setIfShow(String ifShow) {
		this.ifShow = ifShow;
	}
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
		this.tfQuaApplyBS = tfQuaApplyBS;
	}
	@JSON(serialize=false)
	public String getCourselistid() {
		return courselistid;
	}
	public void setCourselistid(String courselistid) {
		this.courselistid = courselistid;
	}
	@JSON(serialize=false)
	public TfQualPilotCourselist getTfQualPilotCourselist() {
		return tfQualPilotCourselist;
	}
	public void setTfQualPilotCourselist(TfQualPilotCourselist tfQualPilotCourselist) {
		this.tfQualPilotCourselist = tfQualPilotCourselist;
	}
	@JSON(serialize=false)
	public String getDetailsid() {
		return detailsid;
	}
	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}
	@JSON(serialize=false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	@JSON(serialize=false)
	public String getSubProcessid() {
		return subProcessid;
	}
	public void setSubProcessid(String subProcessid) {
		this.subProcessid = subProcessid;
	}
	
	
}
