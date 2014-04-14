package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.*;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;
import com.sms.training.qualification.business.service.TfQuaApplyIcaoBS;

@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "qualPilotStayListCT", location = "/sms/training/qualification/quaApplyInspector/qualPilotStayListCT.jsp"),
	@Result(name = "reportPage", location = "/sms/training/qualification/quaApplyInspector/reportPage.jsp"),
	@Result(name = "toZhuanYuanForm", location = "/sms/training/qualification/quaApplyInspector/quaApplyApproval.jsp"),
	@Result(name = "json", type = "json"),
	@Result(name = "success", location = "/standard/ajaxDone.jsp")
})
public class TfQuaApplyInspectorAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//模块名
	private static final String moduleName = "TfQuaApplyInspectorAction";
	//navTabId
	private static final String NAV_TAB_ID="qualApply_tabId";
	//subGroupId  飞行通讯检查员
	private static final String COMM_INSPECTOR = "QUAL_CT_FXTXJCY";
	//subGroupId  公司检查员
	private static final String CORPORATION_INSPECTOR = "QUAL_CT_GSFXJCY";
	//subGroupId  局方委任代表
	private static final String DELEGATE = "QUAL_CT_JFWRDB";
	//subGroupId  熟练检查员
	private static final String SKILLED_INSPECTOR = "QUAL_CT_SLJCY"; 
	//
	private static ProcessBase processBase=new ProcessBase();
	//消息实体
	private Message message;
	//资质类型列表
	private List<TfQualBaseType> tfQualBaseTypeList;
	//资质类型实体
	private TfQualBaseType tfQualBaseType;
	//检查员流程service类
	private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;
	//
	private TfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	//待申报人员列表页面
//	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	//飞行员技术等级 list
	private List<TfQualPilotTechrecord> tfQualPilotTechrecordList=new ArrayList<TfQualPilotTechrecord>();
	//资质类型id
	private String typeId;
	//用来刷新待申报信息页面
	private String declarInfoTabId;
	//申报信息
	private TfQualDeclarInfo tfQualDeclarInfo;
	//计划信息名
	private String planInfoName;
	//申报信息ID
	private String declaredinfoid="";
	//申报人员明细列表
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
	private String userId;
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private String ids;
	private IProcesshistoryBS processhistoryBS;
	private String taskId;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	private String formUrl;
	//资质类型 子类id
	private String subGroupId;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
//	private String qtgroupid;
	private String orgName;
	//流程进行的阶段标识
	private String stage;
	//待申报人员姓名，用于查询
	private String pilotName;
	//申报人员明细id
	private String detailIds;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS; 
	//定义一个list 用来存放是否已上传文件的标志
	private List<Integer> statusList=new ArrayList<Integer>();
	private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
	//人员id
	private String pilotId;
	//用于指明要显示的列表是“可选人员”（值=0），还是“已选人员”（值=1）
	private int availableOrSelected = 0;
	
	//切换页面按钮显示，‘1’：可见，‘0’：不可见。按钮次序：可选择人员、选择申报人员、已选择人员、提交申请
	private String[] btnSwitch={"0","1","1","0"};
	//待申报人员列表
	private List<Object> pilotStayTmpList;
	//
	private ITfQuaApplyTrBS tfQuaApplyTrBS;
	//TfQualDeclaraPilotStay id ,待申报人员id
	private String stayid;
	//流程实例id
	private String processInstanceId;
	private ITfQuaApplyBS tfQuaApplyBS;
	private TfQualPilotCourselist tfQualPilotCourselist;
	private String courselistid;
	//审批意见
	private String opinionInfo;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	
	public TfQuaApplyInspectorAction() {
	}
	
	/**
	 * to 待申报人员列表 页面
	 * @return
	 */
	public String list(){
		try {
			userId=getUser().getUserId();
			//根据资质类型分类及subGroupId获取对应的资质类型列表
			this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
			if(typeId == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
				tfQualBaseType = tfQualBaseTypeList.get(0);
				typeId=tfQualBaseType.getTypeid();
			}else if(typeId!=null){
				tfQualBaseType=tfQuaApplyInspectorBS.findById(TfQualBaseType.class, typeId);
			}
			//获取对应资质类型下符合条件的供选择待申报人员列表
			if(tfQualBaseType!=null){
				String orgNameStr=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				if(availableOrSelected == 0){
					//获取对应资质类型下符合条件的供选择待申报人员列表
					pilotStayTmpList= tfQuaApplyInspectorBS.getAvailablePilotStayTmp(this.getSysPageInfo(), tfQualBaseType, orgNameStr,pilotName);
					//切换页面按钮显示
					btnSwitch=new String[]{"0","1","1","0"};
				}else{
					//获取对应资质类型下符合条件的 已选择 待申报人员列表
					pilotStayTmpList= tfQuaApplyInspectorBS.getSelectedPilotStayTmp(this.getSysPageInfo(), tfQualBaseType,orgNameStr,pilotName);
					//切换页面按钮显示
					btnSwitch=new String[]{"1","0","0","1"};
				}
			}
		} catch (Exception e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "qualPilotStayListCT";
	}
	
	/**
	 * 保存待申报人信息
	 * @return
	 */
	public String savePilotStays(){
		try {
			String[] pilotIds=StringUtils.split(ids, ",");
			TfQualDeclaraPilotStay stay=null;
			HashSet<String> set=new HashSet<String>();
			for(String id : pilotIds){
				set.add(id);
			}
			for (String id : set) {
				stay= new TfQualDeclaraPilotStay();
				stay.setCmPeople(new CmPeople(id));
				stay.setTfQualBaseType(new TfQualBaseType(typeId));
				this.tfQuaApplyInspectorBS.saveOrUpdate(stay);
			}
		} catch (Exception e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 删除一条已选择的 待申报人信息
	 * @return
	 */
	public String deleteStay(){
		try {
			this.tfQuaApplyInspectorBS.deleteById(TfQualDeclaraPilotStay.class, stayid);
			this.message=getSuccessMessage("删除成功！", "", "", "");
		} catch (Exception e) {
			message=getFailMessage("删除失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 跳转到 分公司专员初审 页面
	 * @return
	 */
	public String  toReportPage(){
		try{
			userId=getUser().getUserId();
			orgName=this.getUserOrg().getName();
			//获取此次申报信息
			String infoId=tfQualDeclarInfo.getDeclaredinfoid();
			if(infoId!=null && !infoId.equals("")){
				tfQualDeclarInfo=tfQuaApplyInspectorBS.findById(TfQualDeclarInfo.class, infoId);
				 //获取此次申报的人员信息
				if(tfQualDeclarInfo!=null){
					planInfoName=tfQualDeclarInfo.getDeclaredinfodesc();
				}
				tfQualDeclaraPilotList=tfQuaApplyInspectorBS.getListByProperty(TfQualDeclaraPilot.class, "tfQualDeclarInfo.declaredinfoid", infoId);
				    //   判断是否已经上传文件
				for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
					int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
					statusList.add(docCount);
				}
			}
		   formUrl="tf-qua-apply/tf-qua-apply-inspector!reportToFgsjlConfirm.do";
		   stage="fgsZhuanYuanChuShen";
		}catch (RuntimeException e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "reportPage";
	}
	
	/**
	 * 分公司飞管标准专员 创建流程，并上报给 分公司高级经理
	 * @return
	 */
	public String reportToFgsjlConfirm(){
		try{
//			 登录用户信息
			CmUser user = getUser();
			// 拿到当前机构
			SysOrganization sysOrganization = getUserOrg();
//			 部署资质申报流程
				String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.QUALIFICATION_APPLY_INSPECTOR);
				 
				// 开启资质申报流程
				Map<String, Object> mapStart = new HashMap<String, Object>();
				mapStart.put("FgsZhuanYuanTuiJian", sysOrganization.getId());
				String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
				
				//把流程信息保存到申报信息中
				//获取此次申报信息 
				String infoId=tfQualDeclarInfo.getDeclaredinfoid();
				tfQualDeclarInfo=tfQuaApplyInspectorBS.findById(TfQualDeclarInfo.class, infoId);
				if(subGroupId==null||"".equals(subGroupId.trim())){
					subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
				}
				//保存流程信息 
				tfQualDeclarInfo.setProcessid(pid);
				tfQualDeclarInfo.setStatus("F");
				tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclarInfo);
				
				tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(infoId);
				if(!ids.equals(""))
				{
					for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)
					{
						if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid()))
						{
							tfQualDeclaraPilotList.get(i).setState("REJECT");
							tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
							this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
						}
					}
				}
				String taskId = processBase.getTaskIdByPiID(pid);
				// 存储流程历史处理人和历史处理机构
				processhistoryBS.saveProcessHistory(taskId, getUser(),getUserOrg());

				Map<String, Object> map = new HashMap<String, Object>();
				//保存待办需要的机构及人员
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("FromOrgUserID", user.getUserId());
				map.put("task_plan", pid);
				map.put("planInfoId", infoId);//申报新Id
				map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
				map.put("subGroupId",subGroupId);
				//保存流程公共信息  
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("FgsZhuanYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
				}else{
					map.put("FgsZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
				}
				//流程流转向下
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管标准专员审核报批", map);
				
				taskId = processBase.getTaskIdByPiID(pid);
				
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("FgsJingLi", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准高级经理");
				}else{
					map.put("FgsJingLi", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准高级经理");
				}
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管标准高级经理审核报批", map);
				if(declarInfoTabId!=null && declarInfoTabId.equals("declarInfoTab")){
					this.message = this.getSuccessMessage("上报成功!", "declarInfoTab","closeCurrent","");
				}else {
					this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent","");
				}
		} catch (Exception e) {
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * to 分公司飞管标准高级经理 任务处理页面
	 * @return
	 */
	public String toFgsJingLiForm(){
		try	{
			userId=getUser().getUserId();
			if(taskId!=null && !taskId.equals("")){
				orgName=this.getUserOrg().getName();
				String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
				planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
				processInstanceId = processBase.getProcessInstanceId(taskId);//通过代办id得到流程实例id
				if(!"".equals(processInstanceId)){
					tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processInstanceId,userId );
				}
				//获取此次申报的人员信息
				if(declaredInfoId!=null && !declaredInfoId.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
				formUrl="tf-qua-apply/tf-qua-apply-inspector!fgsJingLiSpConfirm.do";
			}
		}catch (RuntimeException e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toZhuanYuanForm";
	}
	
	/**
	 * 分公司高级经理审批
	 * @return
	 */
	public String fgsJingLiSpConfirm(){
		try	{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals(""))	{
				if(taskId!=null && !taskId.equals("")){
					String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredInfoId.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
						//tfQualDeclaraPilotList=tfQuaApplyInspectorBS.getListByProperty(TfQualDeclaraPilot.class, "tfQualDeclarInfo.declaredinfoid", declaredInfoId);
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)	{
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid()))
							{
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}
						}
					}
					CmUser user = getUser();
					SysOrganization sysOrganization = getUserOrg();
					Map<String, Object> map = new HashMap<String, Object>();
					String pid = processBase.getProcessInstanceId(taskId);
					//保存审批意见
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
//						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
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
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至 分公司领导 任务处理页面
	 * @return
	 */
	public String toFgsLingDaoForm(){
		try{
			userId = getUser().getUserId();
			if(taskId!=null && !taskId.equals("")){
				String processId=processBase.getProcessInstanceId(taskId);
				processInstanceId = processBase.getProcessInstanceId(taskId);//通过代办id得到流程实例id
				//审批信息
				if(!processId.equals("")){
					tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processInstanceId, userId);
				}
				String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
				planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
				orgName=this.getUserOrg().getName();
				//获取此次申报的人员信息
				if(!declaredInfoId.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
					//tfQualDeclaraPilotList=tfQuaApplyInspectorBS.getListByProperty(TfQualDeclaraPilot.class, "tfQualDeclarInfo.declaredinfoid", declaredInfoId);
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
				formUrl="tf-qua-apply/tf-qua-apply-inspector!fgsLingDaoSpConfirm.do";
			}
		}
		catch (RuntimeException e) { 
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toZhuanYuanForm";
	}
	
	/**
	 * 分公司领导 审批
	 * @return
	 */
	public String fgsLingDaoSpConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredInfoId.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
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
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
//						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
					}
					map.put("GsZhuanYuan", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员审核报批", map);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至 公司飞管专员 任务处理页面(初审)
	 * @return
	 */
	public String toGsZhuanYuanForm(){
		try{
			if(taskId!=null && !taskId.equals("")){
				String processId=processBase.getProcessInstanceId(taskId);
				processInstanceId = processBase.getProcessInstanceId(taskId);//通过代办id得到流程实例id
				//审批信息
				if(!processId.equals("")){
					tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
				}
				String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
				planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
				//获取此次申报的人员信息
				if(!declaredInfoId.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
				formUrl="tf-qua-apply/tf-qua-apply-inspector!gsZhuanYuanApprovalConfirm.do";
				stage="zhuanYuanChuShen";//区分复用jsp的标志
			}
		}catch (RuntimeException e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "reportPage";
	}
	
	/**
	 * 公司飞管专员 初审
	 * @return
	 */
	public String gsZhuanYuanApprovalConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredInfoId.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)	{
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}
						}
					}
					SysOrganization sysOrganization = getUserOrg();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("GsJingLi",ConstantList.GHFGBID+"-"+"资质-公司飞管标准高级经理");
					//保存待办需要的机构及人员
					CmUser user = getUser();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管标准高级经理审核报批", map);
					this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至 公司飞管经理 任务处理页面
	 * @return
	 */
	public String toGsJingLiForm(){
		try {
			userId = getUser().getUserId();
			if (taskId != null && !taskId.equals("")) {
				String processId = processBase.getProcessInstanceId(taskId);
				// 审批信息
				if (!processId.equals("")) {
					tfQualDeclarApprovalinfoList = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, userId);
				}
				String declaredInfoId = processBase.getVariable(taskId, "planInfoId").toString();
				processInstanceId = processId;
				planInfoName = processBase.getVariable(taskId, "planInfoName").toString();
				orgName=this.getUserOrg().getName();
				// 获取此次申报的人员信息
				if (!declaredInfoId.equals("")) {
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
				formUrl = "tf-qua-apply/tf-qua-apply-inspector!gsJingLiConfirm.do";
			}
		} catch (RuntimeException e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toZhuanYuanForm";
	}
	
	/**
	 * 公司飞管经理 审批
	 * @return
	 */
	public String gsJingLiConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredInfoId.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
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
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
//						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
					}
					map.put("GsLingDao",ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管领导审核报批", map);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至 公司飞管领导 任务处理页面
	 * @return
	 */
	public String toGsLingDaoForm(){
		try{
			userId=getUser().getUserId();
			if(taskId!=null && !taskId.equals("")){
				String processId=processBase.getProcessInstanceId(taskId);
				//审批信息
				if(!processId.equals("")){
					tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, userId);
				}
				processInstanceId = processId;
				String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
				planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
				orgName=this.getUserOrg().getName();
				//获取此次申报的人员信息
				if(!declaredInfoId.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
//				ifShow="GsJingLi";//同jingli页面相同
				formUrl="tf-qua-apply/tf-qua-apply-inspector!gsLingDaoConfirm.do";
			}
		}catch (RuntimeException e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toZhuanYuanForm";
	}
	
	/**
	 *  公司飞管领导 批准
	 * @return
	 */
	public String gsLingDaoConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("")){
					String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredInfoId.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
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
					if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
//						tfQualDeclarApprovalinfo.setState(state);
						tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
						tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
					}
					map.put("GsZhuanYuanSb",ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员申报", map);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至 公司飞管专员 任务处理页面(申报)
	 * @return
	 */
	public String toGsZhuanYuanSbForm(){
		try{
			if(taskId!=null && !taskId.equals("")){
				String processId=processBase.getProcessInstanceId(taskId);
				processInstanceId = processId;
				//审批信息
				if(!processId.equals("")){
					tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
				}
				String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
				planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
				//获取此次申报的人员信息
				if(!declaredInfoId.equals("")){
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
				stage="zhuanYuanShenBao";
				if("QUAL_EN_YYPFY".equals(subGroupId)||"QUAL_EN_YYKSY".equals(subGroupId)){
					formUrl="tf-qua-apply/tf-qua-apply-english-ksy!gsZhuanYuanReportConfirm.do";
				}else{
					formUrl="tf-qua-apply/tf-qua-apply-inspector!gsZhuanYuanReportConfirm.do";
				}
			}
		}catch (RuntimeException e) {
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "reportPage";
	}
	
	/**
	 * 公司飞管专员 申报 ,结束申报流程
	 * @return
	 */
	public String gsZhuanYuanReportConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId!=null && !taskId.equals("") ){
					String declaredInfoId= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredInfoId.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredInfoId);
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)	{
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}
						}
					}
					//去掉驳回的人员
					String idsStr = Util.toStringIds(ids);
					tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
					//对于局方委任代表及熟练检查员流程，如果人员未通知培训，则设置状态“已通知培训”，流程不流转
					if((subGroupId.equals(DELEGATE)|| subGroupId.equals(SKILLED_INSPECTOR))&&tfQualDeclaraPilotList.size()!=0 && !"TZPX".equals(tfQualDeclaraPilotList.get(0).getState())&& !"WCPX".equals(tfQualDeclaraPilotList.get(0).getState())){
						for(TfQualDeclaraPilot detail : tfQualDeclaraPilotList){
							detail.setState("TZPX");//已通知培训
							tfQuaApplyInspectorBS.saveOrUpdate(detail);
						}
						this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent", "");
						return SUCCESS;
					}
					//如果人员已通知培训，则上传附件，流程向下流转
					//调整申报人员的状态
					for (int i = 0,n=tfQualDeclaraPilotList.size(); i <n ; i++) {
						if(subGroupId.equals(CORPORATION_INSPECTOR) || subGroupId.equals(COMM_INSPECTOR)){//公司检查员、飞行通讯检查员
							tfQualDeclaraPilotList.get(i).setState("DXL");//人员状态 待训练      
							tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.GHFGBID+"-"+"资质-训练专员");
						}else if(subGroupId.equals(DELEGATE)|| subGroupId.equals(SKILLED_INSPECTOR)){//局方委任代表及熟练检查员流程
							tfQualDeclaraPilotList.get(i).setState("WCPX");//人员状态 完成培训      
							tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
						}
						tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
					}
					Map<String, Object> map = new HashMap<String, Object>();
					if(subGroupId.equals(CORPORATION_INSPECTOR) || subGroupId.equals(COMM_INSPECTOR)){//公司检查员、飞行通讯检查员
						map.put("XlZhuanYuan",ConstantList.GHFGBID+"-"+"资质-训练专员"); 
					}else if(subGroupId.equals(DELEGATE)|| subGroupId.equals(SKILLED_INSPECTOR)){//局方委任代表及熟练检查员流程
						map.put("GsLingDao", ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
					}
					//保存待办需要的机构及人员
					map.put("FromOrgName", getUserOrg().getName());
					map.put("FromOrgUser", getUser().getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
					this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 教员创建新流程（审核阶段流程） 改后版
	 * @return
	 */
	public String addSubQualApplyInspector(){
		try	{
			String detailsid= tfQualDeclaraPilot.getDetailsid();
			if(courselistid!=null && !courselistid.equals("")){
				tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
				//此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
				tfQualPilotCourselist.setState("TOVER");
				tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
				if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")){
					//此处判断教员是否都填写完毕
					if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
			//			 部署资质申报流程
						CmUser user = getUser();
						SysOrganization sysOrganization = getUserOrg();
						String deploymentProcessId=tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.SUB_QUALIFICATION_APPLY_INSPECTOR_GS);
						// 开启资质审核流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						mapStart.put("XlZhuanYuan", "");
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    // 将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("jiaoyuan", ConstantList.TJFXBDSZD+"-"+"资质-教员");
						}else{
							map.put("jiaoyuan", ConstantList.ZDSDD+"-"+"资质-教员");
						}
						String taskIdString = processBase.getTaskIdByPiID(pid);
						if(taskIdString!=null && !taskIdString.equals("")){
							tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "教员填单", map);
						}
						//--------------------------------------------------------------------------------
						taskIdString = processBase.getTaskIdByPiID(pid);
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("JianChaYuan", ConstantList.TJFGB+"-"+"资质-检查员"); 
						}else{
							map.put("JianChaYuan",  ConstantList.ZDSDD+"-"+"资质-检查员");
						}
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						map.put("FromOrgUserID", user.getUserId());
						map.put("task_plan", pid);
						if(taskIdString!=null && !taskIdString.equals("")){
							tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "检查员填单", map);
						}
						
						//流程下发成功后 修改此人的state=DJC 此处获得当前学员的所有排课检查员列表 setorgRole 检查员以此筛选待办
						StringBuffer orgRoleTemp=new StringBuffer("");
						List<SysUserOrgRelation> userOrgRelation =tfQuaApplyBS.getOrgRoleByDetailsId(detailsid); 
						if(userOrgRelation!=null && userOrgRelation.size()!=0){
							for (int i = 0; i < userOrgRelation.size(); i++) {
								orgRoleTemp.append(userOrgRelation.get(i).getSysOrganization().getId()+"-"+userOrgRelation.get(i).getCmUser().getUserId()+",");
							}
						}
						tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid.trim());
						if(tfQualDeclaraPilot!=null){
							tfQualDeclaraPilot.setState("DJC");
							if(orgRoleTemp.length()!=0){
								tfQualDeclaraPilot.setOrgRole(orgRoleTemp.toString());
							}
							tfQualDeclaraPilot.setSubProcessid(pid);
							tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
						}		
					}
					//流程下发成功后 
					setTabIndexToReload("0");// 处理子标签页跳转问题
					this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"");
				}
			}
		}catch (Exception e) {
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 公司飞行检查员流程  教员下发 原始方法
	 * 待教员、检查员节点完全无误后，再删
	 */
//	public String addSubQualApplyInspector(){
//		try	{
////			 部署资质申报流程
//			CmUser user = getUser();
//			SysOrganization sysOrganization = getUserOrg();
//			String deploymentProcessId=tfQuaApplyJbpmBS.deploymentProcessDefinition(user, ConstantList.SUB_QUALIFICATION_APPLY_INSPECTOR_GS);
//			// 开启资质审核流程
//			Map<String, Object> mapStart = new HashMap<String, Object>();
//			mapStart.put("XlZhuanYuan", "");
//			String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
//		    // 将实例id保存到流程信息里
//			Map<String, Object> map = new HashMap<String, Object>();
//			if(sysOrganization.getParent_Fun().getName().contains("天津")){
//				map.put("jiaoyuan", ConstantList.TJFXBDSZD+"-"+"资质-教员");
//			}else{
//				map.put("jiaoyuan", ConstantList.ZDSDD+"-"+"资质-教员");
//			}
//			String taskIdString = processBase.getTaskIdByPiID(pid);
//			if(taskIdString!=null && !taskIdString.equals("")){
//				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "教员填单", map);
//			}
//			//--------------------------------------------------------------------------------
//			taskIdString = processBase.getTaskIdByPiID(pid);
//			if(sysOrganization.getParent_Fun().getName().contains("天津")){
//				map.put("JianChaYuan", ConstantList.TJFGB+"-"+"资质-检查员"); 
//			}else{
//				map.put("JianChaYuan",  ConstantList.ZDSDD+"-"+"资质-检查员");
//			}
//			map.put("FromOrgName", sysOrganization.getName());
//			map.put("FromOrgUser", user.getName());
//			map.put("FromOrgUserID", user.getUserId());
//			map.put("task_plan", pid);
//			if(taskIdString!=null && !taskIdString.equals("")){
//				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "检查员填单", map);
//			}
//			
//			//流程下发成功后 修改此人的state=DJC 及
//			String detailsid= tfQualDeclaraPilot.getDetailsid();
//			if(detailsid!=null && !detailsid.equals("")){
//				tfQualDeclaraPilot=tfQuaApplyInspectorBS.findById(TfQualDeclaraPilot.class, detailsid);
//				if(tfQualDeclaraPilot!=null){
//					tfQualDeclaraPilot.setState("DJC");
//					if(sysOrganization.getParent_Fun().getName().contains("天津")){
//						tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-检查员");
//					}else{
//						tfQualDeclaraPilot.setOrgRole( ConstantList.ZDSDD+"-"+"资质-检查员");
//					}
//					tfQualDeclaraPilot.setSubProcessid(pid);
//					tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilot);
//				}
//			}
//			//流程下发成功后 
//			setTabIndexToReload("0");// 处理子标签页跳转问题
//			this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"");
//		}catch (Exception e) {
//			e.printStackTrace();
////			setSuccessFlag(false);
//			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//		}
//		return "json";
//	}
	
	/**
	 * 检查员 完成填单  下发方法  改后版
	 * @return
	 */
	public String inspectorConfirm(){
		try{
			 String detailsid= tfQualDeclaraPilot.getDetailsid();
			 if(courselistid!=null && !courselistid.equals("")){
				 tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
				 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态 
				 tfQualPilotCourselist.setState("COVER");
				 tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
			 }
			 if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")){
				 if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
					SysOrganization sysOrganization = getUserOrg();
					//调整 训练人员状态
					tfQualDeclaraPilot=tfQuaApplyInspectorBS.findById(TfQualDeclaraPilot.class, detailsid);
					tfQualDeclaraPilot.setState("GSZR");
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任");
					tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilot);
					//扭转流程
					String subProcessid = tfQualDeclaraPilot.getSubProcessid();
					if(subProcessid!=null && !subProcessid.equals("") ){
						taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
					}
					CmUser user = getUser();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					map.put("GsZhuRen", ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任");
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "总飞行师审批", map);
				 }
				 //流程下发成功后 
				 setTabIndexToReload("0");// 处理子标签页跳转问题
				 this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID,"","");
			 }
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 检查员 完成填单  下发  原始方法
	 * 待教员、检查员节点完全无误后，再删
	 */
//	public String inspectorConfirm(){
//		try{
//			 SysOrganization sysOrganization = getUserOrg();
//			 //调整 训练人员状态
//			 String detailsid= tfQualDeclaraPilot.getDetailsid();
//			 if(detailsid!=null && !detailsid.equals("")){
//				tfQualDeclaraPilot=tfQuaApplyInspectorBS.findById(TfQualDeclaraPilot.class, detailsid);
//				tfQualDeclaraPilot.setState("GSZR");
//				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任");
//				tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilot);
//			 }
//			//扭转流程
//			String subProcessid = tfQualDeclaraPilot.getSubProcessid();
//			if(subProcessid!=null && !subProcessid.equals("") ){
//				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
//			}
//			CmUser user = getUser();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("FromOrgName", sysOrganization.getName());
//			map.put("FromOrgUser", user.getName());
//			map.put("GsZhuRen", ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任");
//			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "总飞行师审批", map);
//			//流程下发成功后 
//			setTabIndexToReload("0");// 处理子标签页跳转问题
//			this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"","");
//			//扭转流程
//		}catch(Exception e){
//			this.message = this.getFailMessage("流程扭转失败！");
//			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//			e.printStackTrace();
//		}
//		return "json";
//	}
	
	/**
	 * 总飞行师审批 
	 * @return
	 */
	public String zongFeiXingShiConfirm(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			//调整 训练人员状态
			String detailsid=tfQualDeclaraPilot.getDetailsid();
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQuaApplyInspectorBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("GSZY");
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}else{
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}
				tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilot);
				//调整 训练人员状态
				//扭转流程
				String subProcessid=tfQualDeclaraPilot.getSubProcessid();
				if(!subProcessid.equals("")){
					taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
				String taskIdString = processBase.getTaskIdByPiID(subProcessid);
				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
				//流程下发成功后 
				setTabIndexToReload("0");// 处理子标签页跳转问题
				this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID,"", "");
				//扭转流程
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 公司领导审批
	 * @return
	 */
	public String gsLingDaoSpConfirm(){
		try{
			String deploymentProcessId=tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.SUB_QUALIFICATION_APPLY_INSPECTOR_DELEGATE);
			// 开启资质审核流程
			Map<String, Object> mapStart = new HashMap<String, Object>();
			mapStart.put("GsLingDao", "");
			String subProcessid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
			SysOrganization sysOrganization = getUserOrg();
			//调整 训练人员状态
			String detailsid=tfQualDeclaraPilot.getDetailsid();
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQuaApplyInspectorBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("GSZY");
				tfQualDeclaraPilot.setSubProcessid(subProcessid);//
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}else{
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}
				tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilot);
				//调整 训练人员状态
				//扭转流程
//				String subProcessid=tfQualDeclaraPilot.getSubProcessid();
				if(!subProcessid.equals("")){
					taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
				String taskIdString = processBase.getTaskIdByPiID(subProcessid);
				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
				//流程下发成功后 
				setTabIndexToReload("1");// 处理子标签页跳转问题
				this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"", "");
				//扭转流程
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String batchGsLingDaoSpConfirm(){
		try{
			String deploymentProcessId=tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.SUB_QUALIFICATION_APPLY_INSPECTOR_DELEGATE);
			// 开启资质审核流程
			Map<String, Object> mapStart = new HashMap<String, Object>();
			mapStart.put("GsLingDao", "");
			String subProcessid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
			SysOrganization sysOrganization = getUserOrg();
			//调整 训练人员状态
			String[] ids=StringUtils.split(detailIds, ',');
			for(String detailId : ids ){
				tfQualDeclaraPilot=tfQuaApplyInspectorBS.findById(TfQualDeclaraPilot.class, detailId);
				tfQualDeclaraPilot.setState("GSZY");
				tfQualDeclaraPilot.setSubProcessid(subProcessid);//
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}else{
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}
				tfQuaApplyInspectorBS.saveOrUpdate(tfQualDeclaraPilot);
			}
				
			//调整 训练人员状态
			//扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
			String taskIdString = processBase.getTaskIdByPiID(subProcessid);
			tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
			//流程下发成功后 
			setTabIndexToReload("1");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"", "");
			//扭转流程
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyInspectorBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	private void setTabIndexToReload(String index){ // 处理子标签页跳转问题
		this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
		if(index!=null && !index.equals("0")){
			this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
		}
	}
	//=============================   getters and setters  ============================
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}
	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}

	@JSON(serialize = false)
	public ITfQuaApplyInspectorBS getTfQuaApplyInspectorBS() {
		return tfQuaApplyInspectorBS;
	}
	public void setTfQuaApplyInspectorBS(ITfQuaApplyInspectorBS tfQuaApplyInspectorBS) {
		this.tfQuaApplyInspectorBS = tfQuaApplyInspectorBS;
	}

//	@JSON(serialize = false)
//	public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStayList() {
//		return tfQualDeclaraPilotStayList;
//	}
//
//	public void setTfQualDeclaraPilotStayList(
//			List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
//		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
//	}
	
	@JSON(serialize = false)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}
	
	@JSON(serialize = false)
	public String getPlanInfoName() {
		return planInfoName;
	}
	public void setPlanInfoName(String planInfoName) {
		this.planInfoName = planInfoName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public IProcesshistoryBS getProcesshistoryBS() {
		return processhistoryBS;
	}

	public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
		this.processhistoryBS = processhistoryBS;
	}

	@JSON(serialize = false)
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JSON(serialize = false)
	public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
		return tfQualDeclarApprovalinfo;
	}

	public void setTfQualDeclarApprovalinfo(TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
		this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
	}

	@JSON(serialize = false)
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
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
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	
//	@JSON(serialize = false)
//	public String getQtgroupid() {
//		return qtgroupid;
//	}
//	public void setQtgroupid(String qtgroupid) {
//		this.qtgroupid = qtgroupid;
//	}
	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}
	@JSON(serialize = false)
	public TfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(TfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}
	
	@JSON(serialize = false)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@JSON(serialize = false)
	public String getDeclarInfoTabId() {
		return declarInfoTabId;
	}
	public void setDeclarInfoTabId(String declarInfoTabId) {
		this.declarInfoTabId = declarInfoTabId;
	}
	
	@JSON(serialize = false)
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@JSON(serialize = false)
	public String getPilotName() {
		return pilotName;
	}
	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}
	
	@JSON(serialize = false)
	public String getDetailIds() {
		return detailIds;
	}
	public void setDetailIds(String detailIds) {
		this.detailIds = detailIds;
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

	@JSON(serialize = false)
	public List<TfQualPilotTechrecord> getTfQualPilotTechrecordList() {
		return tfQualPilotTechrecordList;
	}
	public void setTfQualPilotTechrecordList(List<TfQualPilotTechrecord> tfQualPilotTechrecordList) {
		this.tfQualPilotTechrecordList = tfQualPilotTechrecordList;
	}

	@JSON(serialize = false)
	public ITfQualPilotLicenceBS getTfQualPilotLicenceBS() {
		return tfQualPilotLicenceBS;
	}
	public void setTfQualPilotLicenceBS(ITfQualPilotLicenceBS tfQualPilotLicenceBS) {
		this.tfQualPilotLicenceBS = tfQualPilotLicenceBS;
	}

	@JSON(serialize = false)
	public String getPilotId() {
		return pilotId;
	}
	public void setPilotId(String pilotId) {
		this.pilotId = pilotId;
	}

	@JSON(serialize = false)
	public String[] getBtnSwitch() {
		return btnSwitch;
	}
	public void setBtnSwitch(String[] btnSwitch) {
		this.btnSwitch = btnSwitch;
	}

	@JSON(serialize = false)
	public List<Object> getPilotStayTmpList() {
		return pilotStayTmpList;
	}
	public void setPilotStayTmpList(List<Object> pilotStayTmpList) {
		this.pilotStayTmpList = pilotStayTmpList;
	}
	
	@JSON(serialize = false)
	public ITfQuaApplyTrBS getTfQuaApplyTrBS() {
		return tfQuaApplyTrBS;
	}
	public void setTfQuaApplyTrBS(ITfQuaApplyTrBS tfQuaApplyTrBS) {
		this.tfQuaApplyTrBS = tfQuaApplyTrBS;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}

	@JSON(serialize = false)
	public String getStayid() {
		return stayid;
	}
	public void setStayid(String stayid) {
		this.stayid = stayid;
	}
	@JSON(serialize = false)
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getDeclaredinfoid() {
		return declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}
	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	
	@JSON(serialize = false)
	public ITfQuaApplyBS getTfQuaApplyBS() {
		return tfQuaApplyBS;
	}

	public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
		this.tfQuaApplyBS = tfQuaApplyBS;
	}
	@JSON(serialize = false)
	public TfQualPilotCourselist getTfQualPilotCourselist() {
		return tfQualPilotCourselist;
	}

	public void setTfQualPilotCourselist(TfQualPilotCourselist tfQualPilotCourselist) {
		this.tfQualPilotCourselist = tfQualPilotCourselist;
	}
	@JSON(serialize = false)
	public String getCourselistid() {
		return courselistid;
	}

	public void setCourselistid(String courselistid) {
		this.courselistid = courselistid;
	}
	@JSON(serialize = false)
	public String getOpinionInfo() {
		return opinionInfo;
	}

	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}
	@JSON(serialize = false)
	public int getAvailableOrSelected() {
		return availableOrSelected;
	}
	public void setAvailableOrSelected(int availableOrSelected) {
		this.availableOrSelected = availableOrSelected;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
		return tfQualDeclaraPilotStayBS;
	}

	public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
		this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
	}
	
	
}
