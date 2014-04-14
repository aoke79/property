package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.*;

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
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;
import com.sms.training.qualification.business.service.TfQualDeclaraPilotBS;
/**
 * 制定一般资格模拟机复训/检查流程action
 * 
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "qualPilotList", location = "/sms/training/qualification/quaApplyTeach/qualPilotListTeach.jsp"),
		@Result(name = "submit", location ="/sms/training/qualification/quaApplyTeach/quaTeacherSubmit.jsp")
		})
		
public class TfQuaApplyTeachAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final String NAV_TAB_ID="qualApply_tabId";
	// 消息实体
	private Message message;
	private static ProcessBase processBase=new ProcessBase();
	// 当前模块名称
	private static final String MODULE_NAME= "TfQuaApplyTeacherAction";
	//计划信息名称
	private String planInfoName;
	//资质类型分组
//	private String qtgroupid;
	//资质类型 子类id
	private String subGroupId;
	private String formUrl; 
//	private String state;
	//用来刷新待申报信息页面
	private String declarInfoTabId;
	private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	//资质类型list
	private List<TfQualBaseType> tfQualBaseTypeList;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	//申报人员明细list
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
	private List<TfQualPilotLicence> tfQualPilotLicenceList =new ArrayList<TfQualPilotLicence>();
	private List<TfQualPilotTechrecord> tfQualPilotTechrecordList=new ArrayList<TfQualPilotTechrecord>();
	private String typeId;
	private String dateCompute;
	private ITfQuaApplyTeachBS tfQuaApplyTeachBS;
	private String userId; 
	List<String> dateStr;
	private boolean showSubmitBtn;
    //申报信息ID
	private String declaredinfoid;
	private TfQualDeclarInfo tfQualDeclarInfo;
	//申报人员明细list
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	//资质申请的流程操作对象
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private String ids;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private IProcesshistoryBS processhistoryBS;
	//课程id
	private String courselistid;
	// 课程实体类
	private TfQualPilotCourselist tfQualPilotCourselist;
	private String detailsid;
	//子流程id
	private String subProcessid;
	//代办事项id
	private String taskId;
	// 申报人员实体类
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	
	public TfQuaApplyTeachAction() {
	}
	/**
	 * 申报人员计算页面
	 */
	public String list(){
		try {
			userId=getUser().getUserId();
			List<TfQualPilotLicence> licences=null;
			List<TfQualPilotTechrecord> techrecords=null;
			dateStr=new ArrayList<String>();
			Date date;
			for (int i = 0; i < 12; i++) {
				date = DateTool.addMonths(i+1);
				dateStr.add(i,DateTool.formatDate(date, DateTool.DEFAULT_DATE_FORMAT).substring(0, 7));
			}
			if(dateCompute!=null){
				showSubmitBtn=!dateStr.get(0).equals(dateCompute) && !dateStr.get(1).equals(dateCompute);
			}
			
			this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
			if(typeId == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
				typeId = tfQualBaseTypeList.get(0).getTypeid(); 
			}
			if(dateCompute!=null && !dateCompute.equals("")){
				String orgIds=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findPilotStayList(typeId,orgIds,dateCompute);
				//获取上次训练日期和签注日期,现行技术标准（此处日后须优化）
				for(TfQualDeclaraPilotStay stay :tfQualDeclaraPilotStayList){
					licences=tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotLicenceList.add(licences == null || licences.size()<1 ? new TfQualPilotLicence(): licences.get(0));
					techrecords=tfQualPilotLicenceBS.getTechrecordsByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotTechrecordList.add(techrecords==null||techrecords.size()<1 ? null : techrecords.get(0)); 
				}
			}
			setFormUrl("tf-qua-apply/tf-qua-apply-teach!toSubmit.do");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "qualPilotList";
	}
	public String ComputingStaff(){
		try {
			String orgIdStr=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
			this.tfQuaApplyTeachBS.doComputingTeachStaff(typeId,orgIdStr,DateTool.formatDate(dateCompute, "yyyy-MM"),getUserOrg().getName(),subGroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 机队申报页面跳转（刘思源）
	 */
	public String toSubmit() {
		try { 
			CmUser user = getUser();
			userId = user.getUserId();
			List<TfQualPilotLicence> licences = null;
			//获取此次申报信息
			if(declaredinfoid != null && !declaredinfoid.equals("")) {
				tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				 //获取此次申报的人员信息
				if(tfQualDeclarInfo != null) {
					planInfoName = tfQualDeclarInfo.getDeclaredinfodesc();
				}
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				//获取上次训练日期和签注日期
				for(TfQualDeclaraPilot stay : tfQualDeclaraPilotList) {
					licences = tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotLicenceList.add(licences == null || licences.size() < 1 ? new TfQualPilotLicence() : licences.get(0));
				}
			}
			formUrl = "tf-qua-apply/tf-qua-apply-teach!quaApplyAdd.do";
			}catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "submit";
	}
	/**
	 * 执行制定计划操作,开启流程，跳转到教员,结束流程
	 * 飞行教员模拟机复训 流程 执行制定计划操作,开启流程，跳转到资质更新,结束流程
	 * 
	 */
	public String quaApplyAdd() {
		try {
			//登录用户信息
			CmUser user = getUser();
			//拿到当前机构
			SysOrganization sysOrganization = getUserOrg();
			//获取此次申报信息 
			tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
			if(subGroupId==null||"".equals(subGroupId.trim())){
				subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
			}
            //部署资质申报流程  ZXL：在训练  DZZGX：待资质更新
			String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.QUALIFICATION_FXJYMNJFX_TEACHER);		 
			//开启资质申报流程
			Map<String, Object> mapStart = new HashMap<String, Object>();
			mapStart.put("FgsZhuanYuanSb", sysOrganization.getId());
			String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
			//将实例id保存到流程信息里
			Map<String, Object> map = new HashMap<String, Object>();
			//保存流程公共信息  转到declar info表中
			if(sysOrganization.getParent_Fun().getName().contains("天津")) {
				map.put("jiaoyuan", ConstantList.TJFXBDSZD + "-" + "资质-教员");
			}else {
				map.put("jiaoyuan", ConstantList.ZDSDD + "-" + "资质-教员");
			}
					
			//保存流程信息 
			tfQualDeclarInfo.setStatus("F");
			tfQualDeclarInfo.setProcessid(pid);
			tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
			//根据当前选择下发的人员对原计划人员列表进行删除操作
			tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
			if(ids != null && !ids.equals("")) {
				for(TfQualDeclaraPilot pilot : tfQualDeclaraPilotList) {
				if(ids.contains(pilot.getDetailsid())) {
				// 当流程扭转成功后 调整申报人员的状态 不同流程的人员状态不同
						pilot.setState("ZXL");
						if(sysOrganization.getParent_Fun().getName().contains("天津")) {
							pilot.setOrgRole(ConstantList.TJFXBDSZD + "-" + "资质-教员");
						} else {
							pilot.setOrgRole(ConstantList.ZDSDD + "-" + "资质-教员");
						}	
				 } else {
				// 根据当前选择下发的人员对原计划人员列表进行驳回处理
					pilot.setState("REJECT");
					this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(pilot.getCmPeople().getId(),subGroupId);
				   }
				tfQualDeclaraPilotBS.saveOrUpdate(pilot);
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
			//获取此次申报信息
			String taskIdString = processBase.getTaskIdByPiID(pid);
			//存储流程历史处理人和历史处理机构
			processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
			//将流程流转向下
			tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
				this.message = this.getSuccessMessage("下发训练成功!", NAV_TAB_ID, "closeCurrent", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 检查员   下发流程
	 * @return
	 */
	public String toQualConfirm(){
			try{
				 SysOrganization sysOrganization = getUserOrg();
				 String state="";
				 String orgRole="";
				 Map<String, Object> map = new HashMap<String, Object>();
				 if(courselistid!=null && !courselistid.equals("")) {
					 tfQualPilotCourselist=tfQuaApplyTeachBS.findById(TfQualPilotCourselist.class, courselistid);
					 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
					 tfQualPilotCourselist.setState("COVER");
					 tfQuaApplyTeachBS.saveOrUpdate(tfQualPilotCourselist);
				 }
				 if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")){
					 //if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
						//判断下发节点
						 if(subProcessid!=null && !subProcessid.equals("") ){
								taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
								if(taskId!=null && !taskId.equals("") ){
									subGroupId= processBase.getVariable(taskId, "subGroupId").toString();
										state="DZZGX";
										if(sysOrganization.getParent_Fun().getName().contains("天津")){
											 orgRole= ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员";
										 }else{
											 orgRole= ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员";
										 }
										map.put("GsZhuanYuanOver",orgRole);
									}
							}
						 
						 //调整 训练人员状态
						 if(detailsid!=null && !detailsid.equals("")){
							 tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
							 tfQualDeclaraPilot.setState(state);
							 tfQualDeclaraPilot.setOrgRole(orgRole);
							 tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
						 }
						//扭转流程
						CmUser user = getUser();
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "资质更新", map);
					// }	
				 }
				//setTabIndexToReload("0");// 处理子标签页跳转问题
				this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"");
				//扭转流程
			}
			catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyTeachBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
				e.printStackTrace();
			}
			return "json";
		}
	//=======================getters and setters============================
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize = false)
	public String getPlanInfoName() {
		return planInfoName;
	}
	public void setPlanInfoName(String planInfoName) {
		this.planInfoName = planInfoName;
	}
	@JSON(serialize = false)
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}
	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	@JSON(serialize = false)
	public ITfQuaApplyInspectorBS getTfQuaApplyInspectorBS() {
		return tfQuaApplyInspectorBS;
	}

	public void setTfQuaApplyInspectorBS(ITfQuaApplyInspectorBS tfQuaApplyInspectorBS) {
		this.tfQuaApplyInspectorBS = tfQuaApplyInspectorBS;
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
	public void setTfQualDeclaraPilotStayList(List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
//	@JSON(serialize = false)
//	public String getQtgroupid() {
//		return qtgroupid;
//	}
//	public void setQtgroupid(String qtgroupid) {
//		this.qtgroupid = qtgroupid;
//	}
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
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}

	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@JSON(serialize = false)
	public ITfQuaApplyTeachBS getTfQuaApplyTeachBS() {
		return tfQuaApplyTeachBS;
	}
	public void setTfQuaApplyTeachBS(ITfQuaApplyTeachBS tfQuaApplyTeachBS) {
		this.tfQuaApplyTeachBS = tfQuaApplyTeachBS;
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
	
	public boolean isShowSubmitBtn() {
		return showSubmitBtn;
	}
	public void setShowSubmitBtn(boolean showSubmitBtn) {
		this.showSubmitBtn = showSubmitBtn;
	}

	@JSON(serialize = false)
	public List<TfQualPilotTechrecord> getTfQualPilotTechrecordList() {
		return tfQualPilotTechrecordList;
	}
	public void setTfQualPilotTechrecordList(List<TfQualPilotTechrecord> tfQualPilotTechrecordList) {
		this.tfQualPilotTechrecordList = tfQualPilotTechrecordList;
	}
	@JSON(serialize = false)
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}
	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}
	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}
	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}
	@JSON(serialize = false)
	public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
		return tfQualDeclaraPilotList;
	}
	public void setTfQualDeclaraPilotList(List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
		this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
	}
	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
		this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
	}
	public void setTfQualDeclaraPilotBS(TfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
		this.processhistoryBS = processhistoryBS;
	}
	@JSON(serialize = false)
	public static ProcessBase getProcessBase() {
		return processBase;
	}
	public static void setProcessBase(ProcessBase processBase) {
		TfQuaApplyTeachAction.processBase = processBase;
	}
	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
	}
	@JSON(serialize = false)
	public String getCourselistid() {
		return courselistid;
	}
	public void setCourselistid(String courselistid) {
		this.courselistid = courselistid;
	}
	@JSON(serialize = false)
	public TfQualPilotCourselist getTfQualPilotCourselist() {
		return tfQualPilotCourselist;
	}
	public void setTfQualPilotCourselist(TfQualPilotCourselist tfQualPilotCourselist) {
		this.tfQualPilotCourselist = tfQualPilotCourselist;
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
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	
}
