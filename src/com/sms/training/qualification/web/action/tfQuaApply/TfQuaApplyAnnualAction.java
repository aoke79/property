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
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQuaApplyGeneralBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyInspectorBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualPilotLicenceBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.business.service.TfQualDeclaraPilotBS;

/**
 * 制定一般资格年度航线检查action
 * author: yang peng
 * date:2013-03-11
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "qualPilotList", location = "/sms/training/qualification/quaApplyGeneral/qualAnnualPilotList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "submit", location = "/sms/training/qualification/quaApplyGeneral/quaAnnualSubmit.jsp"),
		@Result(name = "json", type = "json"),
		})
		
public class TfQuaApplyAnnualAction  extends BaseAction {
	
		private static final long serialVersionUID = 1L;
		// 当前模块名称
		private String moduleName = "TfQuaApplyAnnual";	
		private static final String NAV_TAB_ID="qualApply_tabId";
		private static ProcessBase processBase=new ProcessBase();
		// 消息实体
		private Message message;
		//用来刷新待申报信息页面
		private String declarInfoTabId;
		private TfQualDeclarInfo tfQualDeclarInfo;
		private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;
		// 添加成功表示
		private boolean successFlag = true;
		//计划信息名称
		private String planInfoName;
	    //申报信息ID
		private String declaredinfoid;
		//资质类型ID
		private String typeids;
		private String userId;
		//资质类型分组
		private String qtgroupid;
		//资质员技术标准
		private String ids;
		private String formUrl; 
		private String state;
		//审批意见
		private String taskId;
		/**资质类型 子类id*/
		private String subGroupId;
		//资质申请的流程操作对象
		private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
		private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
		private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
		private IProcesshistoryBS processhistoryBS;
		//资质类型list
		private List<TfQualBaseType> tfQualBaseTypeList;
		private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
		//申报人员明细list
		private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
		private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
		private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
		private List<TfQualPilotLicence> tfQualPilotLicenceList = new ArrayList<TfQualPilotLicence>();
		private List<TfQualPilotTechrecord> tfQualPilotTechrecordList = null;
		private ITfQuaApplyGeneralBS tfQuaApplyGeneralBS;
		private String typeId;
		private List<String> dateStr;
		private String dateCompute;
		private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
		//当前可以资质申报的飞行员数量
		private int pilotCount;
		 
		/**
		 * 构造函数
		 */
		public TfQuaApplyAnnualAction() {	
		}
		
		/**
		 * 申报人员计算页面
		 */
		public String annual() {
			try {
				//获得登录用户
				CmUser user = getUser();
				//用户id
				userId = user.getUserId();
				dateStr = new ArrayList<String>();
				
				//日期工具
				for (int i = 0; i < 12; i++) {
					Date date = DateTool.addMonths(i + 1);
					dateStr.add(i,DateTool.formatDate(date, DateTool.DEFAULT_DATE_FORMAT).substring(0, 7));
				}
				//返回类型描述集合
				this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
				
				//返回类型id
				if (typeids == null && (tfQualBaseTypeList != null && tfQualBaseTypeList.size() > 0 )) {
					typeids = tfQualBaseTypeList.get(0).getTypeid(); 
				}
				
				//判断的是计算日期
				if (dateCompute != null && !dateCompute.equals("")) {
					
					List<TfQualPilotLicence> licences = null;
					List<TfQualPilotTechrecord> techrecords = null;
					
					//机构名称
					String orgIds=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
					this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findPilotStayList(typeids,orgIds,dateCompute);
					
					if (tfQualPilotTechrecordList == null) {
						tfQualPilotTechrecordList = new ArrayList<TfQualPilotTechrecord>();
					}
					
					//获取上次训练日期和签注日期
					for (TfQualDeclaraPilotStay stay :tfQualDeclaraPilotStayList) {
						licences = tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
						tfQualPilotLicenceList.add(licences == null || licences.size() < 1 ? null : licences.get(0));
						techrecords = tfQualPilotLicenceBS.getTechrecordsByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
						tfQualPilotTechrecordList.add(techrecords == null || techrecords.size() < 1 ? null : techrecords.get(0));
					}
				}
				
				setFormUrl("tf-qua-apply/tf-qua-apply-annual!toSubmit.do");
				
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
		 * @return
		 */
		public String ComputingStaff() {
			try {
				String orgIdStr = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				this.tfQuaApplyGeneralBS.doComputingGeneralStaff(typeId,orgIdStr,dateCompute,getUserOrg().getName(),subGroupId);	
			} catch (Exception e) {
				tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}

		/**
		 * 机队申报页面跳转
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
				
				formUrl = "tf-qua-apply/tf-qua-apply-annual!quaApplyAdd.do";
 			}
			catch (RuntimeException e) {
				tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "submit";
		}

		/**
		 * 执行制定计划操作,开启流程，跳转到教员,结束流程
		 * 年度航线检查 流程 执行实施培训操作，开启流程，跳转到资质更新，结束流程
		 */
		public String quaApplyAdd() {
			try {
				//根据当前选择下发的人员对原计划人员列表进行更改状态操作
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				//获取此次申报信息 
				tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				if(subGroupId==null||"".equals(subGroupId.trim())){
					subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
				}
				
				if (!ids.equals("")) {
					// 对未选中的人员,改变状态
					for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n; i++) {
						if (!ids.contains(tfQualDeclaraPilotList.get(i).getCmPeople().getId())) {
							//--REJECT：驳回---（人员）
							tfQualDeclaraPilotList.get(i).setState("REJECT");
							tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
							this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
						}
					}
				}
				
				//登录用户信息
				CmUser user = getUser();
				//拿到当前机构
				SysOrganization sysOrganization = getUserOrg();
				
				//年度航线检查流程
				String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.QUALIFICATION_APPLY_GENERAL_NDHXJC);		 
				
				//开启资质申报流程
				Map<String, Object> mapStart = new HashMap<String, Object>();
				mapStart.put("JiDui", sysOrganization.getId());
				String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
			    //将实例id保存到流程信息里
				Map<String, Object> map = new HashMap<String, Object>();
				//保存流程信息 ：N--流程开始之后的状态--流程状态
				tfQualDeclarInfo.setStatus("N");
				tfQualDeclarInfo.setProcessid(pid);
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
					
				//保存待办需要的机构及人员
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("FromOrgUserID", user.getUserId());
				map.put("task_plan", pid);
				map.put("planInfoId", declaredinfoid);//申报新Id
				map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());//申报新Id
				map.put("subGroupId", subGroupId);
				// 获取taskId
				String taskIdString = processBase.getTaskIdByPiID(pid);  
				// 存储流程历史处理人和历史处理机构
				processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
				
				// 当流程扭转成功后 调整申报人员的状态						
		    	String idsStr = Util.toStringIds(ids);
				// 去掉未选中的人员
				tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
				
				// 已选中的人员集合---人员流程状态
				for (int i = 0,n=tfQualDeclaraPilotList.size(); i <n ; i++) {
					// -----DZZGX--待资质更新(目前状态还未确定，暂时)--------
					tfQualDeclaraPilotList.get(i).setState("DZZGX");
					tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员");
					tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
				}
				
				// 讲流程流转向下
				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
				setSuccessFlag(true);
				
				// 2种跳转方式
				if (declarInfoTabId != null && declarInfoTabId.equals("declarInfoTab")) {
					this.message = this.getSuccessMessage("实施检查成功!", "declarInfoTab", "closeCurrent", "");
				}else {
					this.message = this.getSuccessMessage("实施检查成功!", NAV_TAB_ID, "closeCurrent", "");
				}
					
			} catch (Exception e) {
				tfQuaApplyJbpmBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
				setSuccessFlag(false);
			}
			return "SUCCESS";
		}
 
		//=======================getters and setters============================
		//表示此属性不被加入json
		@JSON(serialize = false)
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
		@JSON(serialize = false)
		public Message getMessage() {
			return message;
		}
		public void setMessage(Message message) {
			this.message = message;
		}
		@JSON(serialize = false)
		public ProcessBase getProcessBase() {
			return processBase;
		}
		public void setProcessBase(ProcessBase processBase) {
			TfQuaApplyAnnualAction.processBase = processBase;
		}
		@JSON(serialize = false)
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
		@JSON(serialize = false)
		public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
			return tfQualifiedDeclareBS;
		}
		public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
			this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
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
		public TfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
			return tfQualDeclaraPilotBS;
		}
		public void setTfQualDeclaraPilotBS(TfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
			this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
		}
		@JSON(serialize = false)
		public ITfQuaApplyInspectorBS getTfQuaApplyInspectorBS() {
			return tfQuaApplyInspectorBS;
		}
		public void setTfQuaApplyInspectorBS(
				ITfQuaApplyInspectorBS tfQuaApplyInspectorBS) {
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
		public void setTfQualDeclaraPilotStayList(
				List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
			this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
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
		public static String getNavTabId() {
			return NAV_TAB_ID;
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
		@JSON(serialize = false)
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
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
		public void setTfQualPilotLicenceList(
				List<TfQualPilotLicence> tfQualPilotLicenceList) {
			this.tfQualPilotLicenceList = tfQualPilotLicenceList;
		}
		@JSON(serialize = false)
		public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
			return tfQuaApplyIcaoBS;
		}
		public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
			this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
		}
		@JSON(serialize = false)
		public ITfQuaApplyGeneralBS getTfQuaApplyGeneralBS() {
			return tfQuaApplyGeneralBS;
		}
		public void setTfQuaApplyGeneralBS(ITfQuaApplyGeneralBS tfQuaApplyGeneralBS) {
			this.tfQuaApplyGeneralBS = tfQuaApplyGeneralBS;
		}
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
		public List<TfQualPilotTechrecord> getTfQualPilotTechrecordList() {
			return tfQualPilotTechrecordList;
		}
		public void setTfQualPilotTechrecordList(
				List<TfQualPilotTechrecord> tfQualPilotTechrecordList) {
			this.tfQualPilotTechrecordList = tfQualPilotTechrecordList;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
			return tfQualDeclaraPilotStayBS;
		}

		public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
			this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
		}
		@JSON(serialize = true)
		public int getPilotCount() {
			return pilotCount;
		}

		public void setPilotCount(int pilotCount) {
			this.pilotCount = pilotCount;
		}
		
}
