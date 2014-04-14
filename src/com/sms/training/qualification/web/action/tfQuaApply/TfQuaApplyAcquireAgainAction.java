package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.Date;
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
import com.sinoframe.bean.SysUserOrgRelation;
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
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQuaApplyQualificationBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/sms/training/qualification/qualificationAcquire/qualAcquirePilot.jsp"),
		@Result(name = "submit", location = "/sms/training/qualification/qualificationAcquire/qualAcquireSubmit.jsp"),
		@Result(name = "fgsZhuanYuanForm", location = "/sms/training/qualification/qualificationAcquire/fgsZhuanYuanForm.jsp"),
		@Result(name = "zhuanYuanForm", location = "/sms/training/qualification/qualificationAcquire/zhuanYuanForm.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),

})
public class TfQuaApplyAcquireAgainAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static final String moduleName = "TfQuaApplyAcquireAgainAction";
	// 添加成功表示
	private boolean successFlag = true;
	private static final String NAV_TAB_ID = "qualApply_tabId";
	//service
	private ITfQuaApplyQualificationBS tfQuaApplyQualificationBS;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS; 
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	// 资质申请的service对象
	private ITfQuaApplyBS tfQuaApplyBS;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	// 资质申请的流程操作对象
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	//资质类型list
	private List<TfQualBaseType> tfQualBaseTypeList;
	//申报人员资质类型list
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	//申报人员明细list
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	//定义一个list 用来存放是否已上传文件的标志
	private List<Integer> statusList = new ArrayList<Integer>();
	private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
	private TfQualPilotCourselist tfQualPilotCourselist;
	//massage
	private Message message;
	//bean
	private TfQualDeclarInfo tfQualDeclarInfo;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//用户ID
	private String userId;
	private String typeInfo;
	private String formUrl; 
	private String ifShow;
	//资质类型ID
	private String typeids;
	//资质类型分组
	private String qtgroupid;
	//资质类型 子类id
	private String subGroupId;
	//资质员技术标准
	private String originalgrade;
	private String orgName;
	private String declaredinfoid;
	private String planInfoName;
	//审核状态
	private String state;
	private String ids = "";
	//审批意见
	private String opinionInfo;
	private Date qianmingDate;
	//公共表
	private ProcessBase processBase = new ProcessBase();
	private IProcesshistoryBS processhistoryBS;
	private String taskId;
	private String processId;
	//用来刷新待申报信息页面
	private String declarInfoTabId;
	//判断是否是分公司飞管领导
	private boolean isfgsfgld;
	private String courselistid;
	private String detailsid;
	private String subProcessid;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	
	/**
	 * 计算重获资格的人员
	 * @return
	 */
	public String list(){
		try {
			CmUser user = getUser();
			userId = user.getUserId();
			originalgrade = "F5";
			//资质类型
			tfQualBaseTypeList = this.tfQualBaseTypeBS.queryByQtgroupId(qtgroupid, originalgrade);
			//因为现在业务不明确 暂时这样写   
			if("CHZG".equals(typeInfo)){   
				typeids = "8a8a13753a683ecc013a68500ebe0000";
			}
			String orgIds = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
			//待资质申报人员
			this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findPilotStaysByTypeAndOrgs(typeids, orgIds);
			setIfShow(originalgrade); 
			setFormUrl("tf-qua-apply/tf-qua-apply-acquire-again!toSubmit.do");
		}catch (Exception ex) {
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * 获得人员提交申请
	 * @return
	 */
	public String toSubmit(){
		try{
			CmUser user = getUser();
			userId = user.getUserId();
			//单位名称
			orgName = this.getUserOrg().getName();
			//获取此次申报信息
			if(declaredinfoid != null && !declaredinfoid.equals("")){
				tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				 //获取此次申报的人员信息
				if(tfQualDeclarInfo != null){
					planInfoName = tfQualDeclarInfo.getDeclaredinfodesc();
				}
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				//判断是否已经上传文件
				for(int t = 0, p = tfQualDeclaraPilotList.size(); t < p; t++) {
					int docCount = tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(), getUser().getUserId());
					statusList.add(docCount);
				}
			}
			
			formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!quaApplyAdd.do";
			
		}catch(Exception ex){
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "submit";
	}
	
	/**
	 * 执行制定计划操作
	 * @return
	 */
	public String quaApplyAdd(){
		try{
			//登录用户信息
			CmUser user = getUser();
			//当前机构
			SysOrganization sysOrganization = getUserOrg();
			if(null != state && !state.equals("")){
				//判断审核状态
				if(state.equals("btg")){
					this.message = this.getSuccessMessage("审核不通过!", "","forwardUrl","tf-qua-apply/tf-qua-apply-acquire-again!quaApplyAdd.do" );
				}else{
					//获取此次申报信息 
					tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
					//获得申报类型描述    代码中没用到这个变量，暂时注释  zhf
//					String typeDesc = tfQualDeclarInfo.getDeclaredinfodesc();
					//部署资质申报流程
					String processName = ConstantList.QUALIFICATION_APPLY_GENERAL_CHZG;
					//获得流程ID
					String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(processName);
					//开启资质申报流程
					Map<String, Object> mapStart = new HashMap<String, Object>();
					mapStart.put("JiDui", sysOrganization.getId());
					//根据流程ＩＤ开启流程实例 
					String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					//将实例id保存到流程信息里
					Map<String, Object> map = new HashMap<String, Object>();
					//保存流程公共信息  转到declar info表中
					if(sysOrganization.getParent_Fun().getName().contains("天津")){
						map.put("FgsZhuanYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
					}else{
						map.put("FgsZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
					}
					//保存流程信息 
					tfQualDeclarInfo.setProcessid(pid);
					//F意义
					tfQualDeclarInfo.setStatus("F");
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
					if(subGroupId==null||"".equals(subGroupId.trim())){
						subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
					}
					//根据当前选择下发的人员对原计划人员列表进行删除操作
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
					if(!ids.equals("")){
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
					map.put("planInfoId", declaredinfoid);
					map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
					map.put("subGroupId", subGroupId);
					//获取此次申报信息,根据ProcessInstanceID获取 TaskID
					String taskIdString = processBase.getTaskIdByPiID(pid);
					// 存储流程历史处理人和历史处理机构
					processhistoryBS.saveProcessHistory(taskIdString, getUser(), getUserOrg());
					//讲流程流转向下
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司级飞管标准专员审核报批", map);
					//保存审批意见
					tfQualDeclarApprovalinfo = new TfQualDeclarApprovalinfo();
					tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
					tfQualDeclarApprovalinfo.setPid(pid);
					//查询 userID 获得机构名称
					tfQualDeclarApprovalinfo.setOrgName(sysOrganization.getName());
					tfQualDeclarApprovalinfo.setSigningDate(qianmingDate);
					tfQualDeclarApprovalinfo.setSigningid(user.getUserId());
					tfQualDeclarApprovalinfo.setState(state);
					tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
					setSuccessFlag(true);
					if(declarInfoTabId != null && declarInfoTabId.equals("declarInfoTab")){
						this.message = this.getSuccessMessage("审核成功!", "declarInfoTab","closeCurrent", "");
					}else{
						this.message = this.getSuccessMessage("审核成功!", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}
		}catch(Exception ex){
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
			setSuccessFlag(false);
		}
		return "SUCCESS";
	}
	
	/**
	 * 分公司专员(王楠)
	 * @return
	 */
	public String toFgsZhuanYuanForm(){
		this.common();
		ifShow = "fGsZhuanYuan"; 
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toZongDuiConfirm.do";
		return "fgsZhuanYuanForm";
	}
	
	public String toZongDuiConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(ids != null && !ids.equals("")){
				if(taskId != null && !taskId.equals("")){
					declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						//根据申报信息获取申报人员信息
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管标准高级经理审核报批", map);
					this.message = this.getSuccessMessage("流程上报成功！", NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception ex){
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage("流程扭转失败！");
			ex.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 分公司级飞管标准高级经理审核(liuxi)
	 * @return
	 */
	public String toFgsJingLiForm(){
		this.common();
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toZhuanYuanConfirm.do";
		return "zhuanYuanForm";
	}
	
	public String toZhuanYuanConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId != null && !taskId.equals("")){
					declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
					String pid = processBase.getProcessInstanceId(taskId);
					//保存审批意见
					tfQualDeclarApprovalinfo = new TfQualDeclarApprovalinfo();
					tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
				    tfQualDeclarApprovalinfo.setPid(pid);
				    tfQualDeclarApprovalinfo.setOrgName(sysOrganization.getName());
					tfQualDeclarApprovalinfo.setSigningDate(qianmingDate);
					tfQualDeclarApprovalinfo.setSigningid(user.getUserId()); 
					tfQualDeclarApprovalinfo.setState(state);
					tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
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
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 分公司级飞管领导审核(huyulu)
	 * @return
	 */
	public String toFgsLingDaoForm(){
		//判断流程身份
		String taskname = processBase.getActivityNameByTaskId(taskId);
		if(taskname.contains("分公司级飞管领导审核报批")){
			isfgsfgld = true;
		}
		this.common();
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toJingLiConfirm.do";
		return "zhuanYuanForm";
	}
	
	/**
	 * 完成分公司级飞管标准高级经理审核报批
	 * @return
	 */
	public String toJingLiConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId != null && !taskId.equals("")){
					declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
					tfQualDeclarApprovalinfo.setSigningid(user.getUserId());
					tfQualDeclarApprovalinfo.setState(state);
					tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
					map.put("GsZhuanYuan", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员审核报批", map);
					setSuccessFlag(true);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 公司专员
	 * @return
	 */
	public String toGsZhuanYuanForm(){
		this.common();
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toShenBaoConfirm.do";
		//区分复用jsp的标志
		ifShow = "GsZhuanYuan";
		return "fgsZhuanYuanForm";
	}
	
	/**
	 * 公司专员申报页面（王菲）
	 * @return
	 */
	public String toShenBaoConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId != null && !taskId.equals("")){
					declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 公司经理
	 * @return
	 */
	public String toGsJingLiForm(){
		this.common();
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toFeiXingYuanConfirm.do";
		ifShow = "GsJingLi";
		return "zhuanYuanForm";
	}
	
	/**
	 * 公司经理审核页面（肖亿）
	 * @return
	 */
	public String toFeiXingYuanConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId != null && !taskId.equals("")){
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
					tfQualDeclarApprovalinfo.setSigningid(user.getUserId());
					tfQualDeclarApprovalinfo.setState(state);
					tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
					map.put("GsLingDao",ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管领导审核报批", map);
					setSuccessFlag(true);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
				}
			}
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 公司领导
	 * @return
	 */
	public String toGsLingDaoForm(){
		this.common();
		ifShow = "GsJingLi";
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toJiaoYuanConfirm.do";
		return "zhuanYuanForm";
	}
	
	public String toJiaoYuanConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId != null && !taskId.equals("")){
					declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
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
					tfQualDeclarApprovalinfo.setSigningid(user.getUserId());
					tfQualDeclarApprovalinfo.setState(state);
					tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
					map.put("GsZhuanYuanSb",ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
					//保存待办需要的机构及人员
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员申报", map);
					setSuccessFlag(true);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
		}
		return "SUCCESS";
	}
	
	/**
	 * 公司专员申报
	 * @return
	 */
	public String toGsZhuanYuanSbForm(){
		this.common();
		ifShow = "GsZhuanYuanSb"; 
		formUrl = "tf-qua-apply/tf-qua-apply-acquire-again!toJianChaYuanYuanConfirm.do";
		return "fgsZhuanYuanForm";
	}
	
	public String toJianChaYuanYuanConfirm(){
		try{
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			if(!ids.equals("")){
				if(taskId != null && !taskId.equals("") ){
					declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++){
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}
						}
					}
					Map<String, Object> map = new HashMap<String, Object>();
					//保存待办需要的机构及人员
					map.put("FromOrgName", getUserOrg().getName());
					map.put("FromOrgUser", getUser().getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 审批信息,申报的人员信息
	 */
	public void common(){
		try{
			if(null != taskId && !taskId.equals("")){
				processId = processBase.getProcessInstanceId(taskId);
			}
			//审批信息
			if(!processId.equals("")){
				//审批意见集合
				tfQualDeclarApprovalinfoList = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
			}
			declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
			planInfoName = processBase.getVariable(taskId, "planInfoName").toString();
			//获取此次申报的人员信息
			if(!declaredinfoid.equals("")){
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				for(int t = 0, n = tfQualDeclaraPilotList.size(); t < n; t++){
					int docCount = tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(), getUser().getUserId());
					statusList.add(docCount);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
		}
	}
	
	/**
	 * 教员（张春雷） 开启后半部分流程
	 * @return
	 */
	public String toAddSubQualApplyChzg(){
		try{
			if(null != courselistid && !courselistid.equals("")){
				tfQualPilotCourselist = tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
				//此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
				tfQualPilotCourselist.setState("TOVER");
				tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
				if(null != tfQualPilotCourselist){
					//此处判断教员是否都填写完毕
					if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(), tfQualPilotCourselist.getTpplanno().trim())){
						CmUser user = getUser();
						SysOrganization sysOrganization = getUserOrg();
						String deploymentProcessId = null;
						deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.SUB_QUALIFICATION_APPLY_GENERAL_CHZG);
						//开启资质审核流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						mapStart.put("XlZhuanYuan", "");
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    //将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("jiaoyuan", ConstantList.TJFXBDSZD+"-"+"资质-教员");
						}else{
							map.put("jiaoyuan", ConstantList.ZDSDD+"-"+"资质-教员");
						}
						String taskIdString = processBase.getTaskIdByPiID(pid);
						if(taskIdString != null && !taskIdString.equals("")){
							tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "教员填单", map);
						}
						taskIdString = processBase.getTaskIdByPiID(pid);
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("JianChaYuan", ConstantList.TJFGB+"-"+"资质-检查员"); 
						}else{
							map.put("JianChaYuan",  ConstantList.ZDSDD+"-"+"资质-检查员");
						}
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUserID", user.getUserId());
						map.put("task_plan", pid);
						map.put("typeInfo", typeInfo);
						if(taskIdString != null && !taskIdString.equals("")){
							tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "检查员填单", map);
						}
						//保存流程计划信息
						//流程下发成功后 修改此人的 state=DJC 
						if(detailsid != null && !detailsid.equals("")){
							//此处获得当前学员的所有排课检查员列表 setorgRole 检查员以此筛选待办
							StringBuffer orgRoleTemp = new StringBuffer("");
							List<SysUserOrgRelation> userOrgRelation = tfQuaApplyBS.getOrgRoleByDetailsId(detailsid);
							if(userOrgRelation != null && userOrgRelation.size() != 0){
								for (int i = 0; i < userOrgRelation.size(); i++) {
									orgRoleTemp.append(userOrgRelation.get(i).getSysOrganization().getId()+"-"+userOrgRelation.get(i).getCmUser().getUserId()+",");
								}
							}
							tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid.trim());
							if(tfQualDeclaraPilot != null){
								tfQualDeclaraPilot.setState("DJC");
								if(orgRoleTemp.length()!= 0){
									tfQualDeclaraPilot.setOrgRole(orgRoleTemp.toString());
								}
								tfQualDeclaraPilot.setSubProcessid(pid);
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
							 }
						}
					}
					setSuccessFlag(true);
					// 处理子标签页跳转问题
					setTabIndexToReload("0");
					this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"jbpm4/tf-qual-apply-jbpm!toIndexTask.do?typeInfo="+typeInfo);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
		}
		return "json";
	}
	
	/**
	 * 检查员 下发流程
	 * @return
	 */
	public String toJiShuShenBaoConfirm(){
		try{
			 SysOrganization sysOrganization = getUserOrg();
			 String state = null;
			 String orgRole = null;
			 Map<String, Object> map = new HashMap<String, Object>();
			 if(courselistid != null && !courselistid.equals("")){
				 tfQualPilotCourselist = tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
				 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
				 tfQualPilotCourselist.setState("COVER");
				 tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
			 }
			 if(tfQualPilotCourselist != null && detailsid != null && !detailsid.equals("")){
				 if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(), tfQualPilotCourselist.getTpplanno().trim())){
					//判断下发节点
					 if(subProcessid != null && !subProcessid.equals("") ){
						taskId = tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
						if(taskId != null && !taskId.equals("")){
							typeInfo = processBase.getVariable(taskId, "typeInfo").toString();
							state = "DBCXL";
							if(sysOrganization.getParent_Fun().getName().contains("天津")){
								 orgRole = ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员";
							}else{
								 orgRole = ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员";
							}
							map.put("FgsZy",orgRole);
						}
					}
					 
					 //调整 训练人员状态
					 if(detailsid != null && !detailsid.equals("")){
						 tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
						 tfQualDeclaraPilot.setState(state);
						 tfQualDeclaraPilot.setOrgRole(orgRole);
						 tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
					 }
					//扭转流程
					CmUser user = getUser();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "本场训练", map);
				 }	
			 }
			setSuccessFlag(true);
			//处理子标签页跳转问题
			setTabIndexToReload("0");
			this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" , "jbpm4/tf-qual-apply-jbpm!toIndexTask.do?typeInfo="+typeInfo);
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 分公司专员
	 * @return
	 */
	public String toFgsZyConfirm(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			if(detailsid != null &&  !detailsid.equals("") ){
				tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				//调整 训练人员状态
				tfQualDeclaraPilot.setState("FGSZR");
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
				}else{
					tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
				}
				
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
				if(!subProcessid.equals("")){
					taskId = tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				if("FJSZJX".equals(typeInfo)){
					map.put("typeInfo",typeInfo);
				}
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					map.put("FgsZhuRen", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
				}else{
					map.put("FgsZhuRen", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
				}
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级技术委员会办公室主任审核报批", map);
				this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"jbpm4/tf-qual-apply-jbpm!toIndexTask.do?typeInfo="+typeInfo);
			}
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 分公司级技术委员会主任
	 * @return
	 */
	public String toZongDuiZrBpsConfirm(){
		try{
			String state = null;
			String orgRole = null;
			//调整 训练人员状态
			SysOrganization sysOrganization = getUserOrg();
			state = "GSZR";
			orgRole = ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任";
			
			if(detailsid != null && !detailsid.equals("")){
				tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState(state);
				tfQualDeclaraPilot.setOrgRole(orgRole);
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			//扭转流程
			if(!subProcessid.equals("")){
				taskId = tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("GsZhuRen",orgRole);
			
			//to 公司技术委员会办公室主任
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司技术委员会办公室主任审核报批", map);
			//处理子标签页跳转问题
			setTabIndexToReload("1");
			this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID, "closeCurrent", "");
		}catch(Exception ex){
			ex.printStackTrace();
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
		}
		return "json";
	}
	
	/**
	 * 公司技术委员会办公室主任
	 * @return
	 */
	public String toFeiGuanZrBpsConfirm(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			//调整 训练人员状态
			if(detailsid != null && !detailsid.equals("")){
				tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("GSZY");
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}else{
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				}
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
				//调整 训练人员状态
				//扭转流程
				if(!subProcessid.equals("")){
					taskId = tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
				String taskIdString = processBase.getTaskIdByPiID(subProcessid);
				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
				// 处理子标签页跳转问题
				setTabIndexToReload("0");
				this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID, "" ,"jbpm4/tf-qual-apply-jbpm!toIndexTask.do?typeInfo="+typeInfo);
			}
		}catch(Exception ex){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyQualificationBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 处理子标签页跳转问题
	 * @param index
	 */
	private void setTabIndexToReload(String index){  
		this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
		if(index != null && !index.equals("0")){
			this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
		}
	}
	
	@JSON(serialize = false)
	public ITfQuaApplyQualificationBS getTfQuaApplyQualificationBS() {
		return tfQuaApplyQualificationBS;
	}

	public void setTfQuaApplyQualificationBS(
			ITfQuaApplyQualificationBS tfQuaApplyQualificationBS) {
		this.tfQuaApplyQualificationBS = tfQuaApplyQualificationBS;
	}
	@JSON(serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}
	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}

	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}
	
	@JSON(serialize = false)
	public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStayList() {
		return tfQualDeclaraPilotStayList;
	}

	public void setTfQualDeclaraPilotStayList(
			List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	@JSON(serialize = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTypeInfo() {
		return typeInfo;
	}
	
	@JSON(serialize = false)
	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
	}
	
	@JSON(serialize = false)
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	
	@JSON(serialize = false)
	public String getIfShow() {
		return ifShow;
	}

	public void setIfShow(String ifShow) {
		this.ifShow = ifShow;
	}
	
	@JSON(serialize = false)
	public String getTypeids() {
		return typeids;
	}

	public void setTypeids(String typeids) {
		this.typeids = typeids;
	}
	
	@JSON(serialize = false)
	public String getQtgroupid() {
		return qtgroupid;
	}

	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}
	
	@JSON(serialize = false)
	public String getOriginalgrade() {
		return originalgrade;
	}

	public void setOriginalgrade(String originalgrade) {
		this.originalgrade = originalgrade;
	}
	
	@JSON(serialize = false)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@JSON(serialize = false)
	public ITfQualPilotDocumentsBS getTfQualPilotDocumentsBS() {
		return tfQualPilotDocumentsBS;
	}

	public void setTfQualPilotDocumentsBS(
			ITfQualPilotDocumentsBS tfQualPilotDocumentsBS) {
		this.tfQualPilotDocumentsBS = tfQualPilotDocumentsBS;
	}
	
	@JSON(serialize=false)
	public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
		return tfQualDeclaraPilotList;
	}

	public void setTfQualDeclaraPilotList(
			List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
		this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
	}
	
	@JSON(serialize = false)
	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}
	
	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
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
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	
	@JSON(serialize = false)
	public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
		return tfQualDeclarApprovalinfo;
	}

	public void setTfQualDeclarApprovalinfo(
			TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
		this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
	}
	
	@JSON(serialize = false)
	public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
		return tfQualDeclarApprovalInfoBS;
	}

	public void setTfQualDeclarApprovalInfoBS(
			ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
		this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
	}
	
	@JSON(serialize=false)
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
	public ProcessBase getProcessBase() {
		return processBase;
	}

	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}
	
	@JSON(serialize = false)
	public IProcesshistoryBS getProcesshistoryBS() {
		return processhistoryBS;
	}

	public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
		this.processhistoryBS = processhistoryBS;
	}
	
	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}
	
	@JSON(serialize = false)
	public List<TfQualDeclarApprovalinfo> getTfQualDeclarApprovalinfoList() {
		return tfQualDeclarApprovalinfoList;
	}

	public void setTfQualDeclarApprovalinfoList(
			List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList) {
		this.tfQualDeclarApprovalinfoList = tfQualDeclarApprovalinfoList;
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
	
	public String getDeclarInfoTabId() {
		return declarInfoTabId;
	}

	public void setDeclarInfoTabId(String declarInfoTabId) {
		this.declarInfoTabId = declarInfoTabId;
	}

	@JSON(serialize = false)
	public boolean isIsfgsfgld() {
		return isfgsfgld;
	}

	public void setIsfgsfgld(boolean isfgsfgld) {
		this.isfgsfgld = isfgsfgld;
	}

	public static String getModuleName() {
		return moduleName;
	}

	public String getCourselistid() {
		return courselistid;
	}

	public void setCourselistid(String courselistid) {
		this.courselistid = courselistid;
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

	public String getDetailsid() {
		return detailsid;
	}

	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}
	
	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	public String getSubProcessid() {
		return subProcessid;
	}

	public void setSubProcessid(String subProcessid) {
		this.subProcessid = subProcessid;
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
