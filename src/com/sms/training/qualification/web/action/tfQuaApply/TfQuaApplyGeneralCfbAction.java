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
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQuaApplyBS;
import com.sms.training.qualification.business.ITfQuaApplyGeneralBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyInspectorBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualPilotLicenceBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.business.service.TfQualDeclaraPilotBS;

/**
 * 除/防冰训练记录CIO流程和换季学习记录流程的action
 * 
 * @author pxp
 * 
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "qualPilotList", location = "/sms/training/qualification/quaApplyGeneral/qualPilotCfbList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "submit", location = "/sms/training/qualification/quaApplyGeneral/quaGeneralSubmit.jsp"),
		@Result(name = "json", type = "json"),
		})

public class TfQuaApplyGeneralCfbAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final String NAV_TAB_ID = "qualApply_tabId";
	// 添加成功表示
	private boolean successFlag = true;
	// 消息实体
	private Message message;
	private static ProcessBase processBase = new ProcessBase();
	// 当前模块名称
	private String moduleName = "TfQuaGeneralRemoveIce";
	// 计划信息名称
	private String planInfoName;
	// 申报信息ID
	private String declaredinfoid;
	// 资质类型ID
	private String typeids;
	private String userId;
	// 资质类型分组
	private String qtgroupid;
	// 资质员技术标准
	private String originalgrade;
	private String ids;
	private String formUrl;
	private String state;
	// 审批意见，以后可以删
	private String opinionInfo;
	private String detailsid;
	private String subProcessid;
	private String taskId;
	//资质类型 子类id
	private String subGroupId;
	private Date qianmingDate;
	// 用来刷新待申报信息页面
	private String declarInfoTabId;

	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	private TfQualDeclarInfo tfQualDeclarInfo;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;

	private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	// 资质申请的流程操作对象
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private IProcesshistoryBS processhistoryBS;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	// 资质类型list
	private List<TfQualBaseType> tfQualBaseTypeList;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	// 申报人员明细list
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
	private List<TfQualPilotLicence> tfQualPilotLicenceList = new ArrayList<TfQualPilotLicence>();
	private List<TfQualPilotTechrecord> tfQualPilotTechrecordList = new ArrayList<TfQualPilotTechrecord>();
	private ITfQuaApplyGeneralBS tfQuaApplyGeneralBS;
	private String typeId;
	List<String> dateStr;
	private String dateCompute;
	private boolean showSubmitBtn;
	private Date ptrapprovedDate;
	private TfQualBaseType tfQualBaseType;
	private TfQualPilotLicence tfQualPilotLicence;
	private ITfQuaApplyBS tfQuaApplyBS;
	//当前可以资质申报的飞行员数量
	private int pilotCount;

	/**
	 * 构造函数
	 */
	public TfQuaApplyGeneralCfbAction() {
	}

	/**
	 * 申报人员计算页面
	 */
	public String list() {
		try {
			//获得登录用户
			CmUser user = getUser();
			
			//用户id
			userId = user.getUserId();
			dateStr = new ArrayList < String>();
			
			//日期工具
			for (int i = 0; i < 12; i++) {
				Date date = DateTool.addMonths(i + 1);
				dateStr.add(i, DateTool.formatDate(date, DateTool.DEFAULT_DATE_FORMAT).substring(0, 7));
			}
			
			// 判断“制定训练计划”按钮是否显示
			if (dateCompute != null) {
				showSubmitBtn = !dateStr.get(0).equals(dateCompute) && !dateStr.get(1).equals(dateCompute);
			}
			
			//返回类型描述集合
			this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
			
			if (typeids == null && (tfQualBaseTypeList != null && tfQualBaseTypeList.size() > 0)) {
				typeids = tfQualBaseTypeList.get(0).getTypeid();
			}
			if (dateCompute != null && !dateCompute.equals("")) {
				List<TfQualPilotLicence> licences = null;
				List<TfQualPilotTechrecord> techrecords = null;
				
				String orgIds = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findPilotStayList(typeids, orgIds, dateCompute);
				
				//获取上次训练日期和签注日期
				for (TfQualDeclaraPilotStay stay : tfQualDeclaraPilotStayList) {
					licences = tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotLicenceList.add(licences == null || licences.size() < 1 ? null : licences.get(0));
					techrecords = tfQualPilotLicenceBS.getTechrecordsByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotTechrecordList.add(techrecords == null || techrecords.size() < 1 ? null : techrecords.get(0));
				}
			}
			setFormUrl("tf-qua-apply/tf-qua-apply-general-cfb!toSubmit.do");
		} catch (RuntimeException e) {
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "qualPilotList";
	}
	
	/**
	 * 根据资质类型、单位和计算日期查询出的当前可以资质申报人员的数量
	 */
	public String calculatePilotCount() {
		if(dateCompute != null && !dateCompute.equals("")) {
			String orgIds = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
			//申报人员的数量
			pilotCount = this.tfQualifiedDeclareBS.findPilotStayCount(typeids, orgIds, dateCompute);
		}
		return "json";
	}
	
	/**
	 * 查询申报人员
	 */
	public String ComputingStaff() {
		try {
			String orgIdStr = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
			this.tfQuaApplyGeneralBS.doComputingGeneralStaff(typeId, orgIdStr, dateCompute, getUserOrg().getName(),subGroupId);	
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 * 机队申报页面跳转（刘思源）
	 */
	public String toSubmit() {
		try {
			// 获取此次申报信息
			if (declaredinfoid != null && !declaredinfoid.equals("")) {
				tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				
				// 获取此次申报的人员信息
				if (tfQualDeclarInfo != null) {
					planInfoName = tfQualDeclarInfo.getDeclaredinfodesc();
				}
				
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				
				List<TfQualPilotLicence> licences = null;
				
				// 获取上次训练日期和签注日期
				for (TfQualDeclaraPilot stay : tfQualDeclaraPilotList) {
					licences = tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotLicenceList.add(licences == null || licences.size() < 1 ? new TfQualPilotLicence() : licences.get(0));
				}
			}
			formUrl = "tf-qua-apply/tf-qua-apply-general-cfb!quaApplyAdd.do";
		} catch (RuntimeException e) {
			tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "submit";
	}
	
	/**
	 * 执行制定计划操作,开启流程，跳转到教员,结束流程
	 *
	 * 应急，危险品，执照考试，理论复试 流程 执行制定计划操作,开启流程，跳转到资质更新,结束流程
	 * 除防冰，换季学习记录 流程 执行实施培训操作，开启流程，跳转到资质更新，结束流程
	 */
	public String quaApplyAdd() {
		try {
			String tianjinOrgRole = null;
			String guohangOrgRole = null;
			String personState = null;
			//登录用户信息
			CmUser user = getUser();
			//拿到当前机构
			SysOrganization sysOrganization = getUserOrg();
			if(state != null && !state.equals("")) {
				//tg：通过 btg：不通过
				if(state.equals("btg")) {
					this.message = this.getSuccessMessage("下发训练失败!", NAV_TAB_ID,"closeCurrent", "tf-qua-apply/tf-qua-apply-general-cfb!quaApplyAdd.do" );
				} else {
					//获取此次申报信息 
					tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
                    //部署资质申报流程
					String subGrpId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
					if("QUAL_YB_CFBXLJL".equals(subGrpId)|| "QUAL_YB_HJXXJL".equals(subGrpId)){
						//TJFGB：天津飞管部
						tianjinOrgRole = ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员";
						//GUOHANGFEIXINGZONGDUI_ID：国行飞行总队
						guohangOrgRole = ConstantList.GUOHANGFEIXINGZONGDUI_ID + "-" + "资质-分公司级飞管标准专员";
						//DZZGX：待资质更新
						personState = "DZZGX";
					}
					//流程定义
					String deploymentProcessId = tfQuaApplyJbpmBS.deploymentProcessDefinition(user, ConstantList.SUB_QUALIFICATION_APPLY_GENERAL_CFBXLJL);
					
					//开启资质申报流程
					Map<String, Object> mapStart = new HashMap<String, Object>();
					mapStart.put("GsZhuanYuanOver", sysOrganization.getId());
					String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
				    //将实例id保存到流程信息里
					Map<String, Object> map = new HashMap<String, Object>();
					//保存流程公共信息  转到declar info表中
					if(sysOrganization.getName().contains("天津")) {
						map.put("GsZhuanYuanOver", tianjinOrgRole);
					} else {
						map.put("GsZhuanYuanOver", guohangOrgRole);
					}
					
					//保存流程信息 ：N--流程开始之后的状态--流程状态
					tfQualDeclarInfo.setStatus("N");
					tfQualDeclarInfo.setProcessid(pid);
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
					
					//根据当前选择下发的人员对原计划人员列表进行更改状态操作
					tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				
					if(ids != null && !ids.equals("")) {
						for(TfQualDeclaraPilot pilot : tfQualDeclaraPilotList) {
							if(ids.contains(pilot.getDetailsid())) {
								// 当流程扭转成功后 调整申报人员的状态 不同流程的人员状态不同
								pilot.setState(personState);
								if(sysOrganization.getParent_Fun().getName().contains("天津")) {
									pilot.setOrgRole(tianjinOrgRole);
								} else {
									pilot.setOrgRole(guohangOrgRole);
								}	
							} else {
								// 根据当前选择下发的人员对原计划人员列表进行驳回处理
								pilot.setState("REJECT");
							}
							tfQualDeclaraPilotBS.saveOrUpdate(pilot);
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
					//存储流程历史处理人和历史处理机构
					processhistoryBS.saveProcessHistory(taskIdString, getUser(), getUserOrg());
					
					//将流程流转向下
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
					setSuccessFlag(true);
					
					//跳转方式
					if(declarInfoTabId != null && declarInfoTabId.equals("declarInfoTab")) {
						this.message = this.getSuccessMessage("下发训练成功!", "declarInfoTab", "closeCurrent", "");
					} else {
						this.message = this.getSuccessMessage("下发训练成功!", NAV_TAB_ID, "closeCurrent", "");
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
	 * 此方法在这个action中未用到
	 * @return
	 */
//	private void setTabIndexToReload(String index) { //TODO 处理子标签页跳转问题
//		this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
//		if(index != null && !index.equals("0")) {
//			this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
//		}
//	}

	//=======================getters and setters============================
	@JSON(serialize = false)
	public boolean isSuccessFlag() {
		return successFlag;
	}
	
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public static ProcessBase getProcessBase() {
		return processBase;
	}

	public static void setProcessBase(ProcessBase processBase) {
		TfQuaApplyGeneralCfbAction.processBase = processBase;
	}

	@JSON(serialize = false)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@JSON(serialize = false)
	public String getPlanInfoName() {
		return planInfoName;
	}

	public void setPlanInfoName(String planInfoName) {
		this.planInfoName = planInfoName;
	}

	@JSON(serialize = false)
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}

	@JSON(serialize = false)
	public String getTypeids() {
		return typeids;
	}

	public void setTypeids(String typeids) {
		this.typeids = typeids;
	}

	@JSON(serialize = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@JSON(serialize = false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@JSON(serialize = false)
	public String getOpinionInfo() {
		return opinionInfo;
	}

	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
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
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JSON(serialize = false)
	public Date getQianmingDate() {
		return qianmingDate;
	}

	public void setQianmingDate(Date qianmingDate) {
		this.qianmingDate = qianmingDate;
	}
	
	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}

	@JSON(serialize = false)
	public String getDeclarInfoTabId() {
		return declarInfoTabId;
	}

	public void setDeclarInfoTabId(String declarInfoTabId) {
		this.declarInfoTabId = declarInfoTabId;
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
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@JSON(serialize = false)
	public ITfQuaApplyInspectorBS getTfQuaApplyInspectorBS() {
		return tfQuaApplyInspectorBS;
	}

	public void setTfQuaApplyInspectorBS(ITfQuaApplyInspectorBS tfQuaApplyInspectorBS) {
		this.tfQuaApplyInspectorBS = tfQuaApplyInspectorBS;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(TfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}

	@JSON(serialize = false)
	public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
		return tfQuaApplyJbpmBS;
	}

	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
	}

	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}

	@JSON(serialize = false)
	public IProcesshistoryBS getProcesshistoryBS() {
		return processhistoryBS;
	}

	public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
		this.processhistoryBS = processhistoryBS;
	}

	@JSON(serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}

	@JSON(serialize = false)
	public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
		return tfQualDeclarApprovalInfoBS;
	}

	public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
		this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
	}

	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}

	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}

	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}

	@JSON(serialize = false)
	public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStayList() {
		return tfQualDeclaraPilotStayList;
	}

	public void setTfQualDeclaraPilotStayList(List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}

	@JSON(serialize = false)
	public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
		return tfQualDeclaraPilotList;
	}

	public void setTfQualDeclaraPilotList(List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
		this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
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

	@JSON(serialize = false)
	public List<TfQualPilotTechrecord> getTfQualPilotTechrecordList() {
		return tfQualPilotTechrecordList;
	}

	public void setTfQualPilotTechrecordList(List<TfQualPilotTechrecord> tfQualPilotTechrecordList) {
		this.tfQualPilotTechrecordList = tfQualPilotTechrecordList;
	}

	@JSON(serialize = false)
	public ITfQuaApplyGeneralBS getTfQuaApplyGeneralBS() {
		return tfQuaApplyGeneralBS;
	}

	public void setTfQuaApplyGeneralBS(ITfQuaApplyGeneralBS tfQuaApplyGeneralBS) {
		this.tfQuaApplyGeneralBS = tfQuaApplyGeneralBS;
	}

	@JSON(serialize = false)
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@JSON(serialize = false)
	public List<String> getDateStr() {
		return dateStr;
	}

	public void setDateStr(List<String> dateStr) {
		this.dateStr = dateStr;
	}

	@JSON(serialize = false)
	public String getDateCompute() {
		return dateCompute;
	}

	public void setDateCompute(String dateCompute) {
		this.dateCompute = dateCompute;
	}

	@JSON(serialize = false)
	public boolean isShowSubmitBtn() {
		return showSubmitBtn;
	}

	public void setShowSubmitBtn(boolean showSubmitBtn) {
		this.showSubmitBtn = showSubmitBtn;
	}

	@JSON(serialize = false)
	public Date getPtrapprovedDate() {
		return ptrapprovedDate;
	}

	public void setPtrapprovedDate(Date ptrapprovedDate) {
		this.ptrapprovedDate = ptrapprovedDate;
	}

	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@JSON(serialize = false)
	public TfQualPilotLicence getTfQualPilotLicence() {
		return tfQualPilotLicence;
	}

	public void setTfQualPilotLicence(TfQualPilotLicence tfQualPilotLicence) {
		this.tfQualPilotLicence = tfQualPilotLicence;
	}

	@JSON(serialize = false)
	public ITfQuaApplyBS getTfQuaApplyBS() {
		return tfQuaApplyBS;
	}

	public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
		this.tfQuaApplyBS = tfQuaApplyBS;
	}

	@JSON(serialize = false)
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getNavTabId() {
		return NAV_TAB_ID;
	}

	@JSON(serialize = true)
	public int getPilotCount() {
		return pilotCount;
	}

	public void setPilotCount(int pilotCount) {
		this.pilotCount = pilotCount;
	}
	
}

