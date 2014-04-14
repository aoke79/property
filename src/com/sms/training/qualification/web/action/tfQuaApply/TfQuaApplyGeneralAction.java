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
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;
import com.sms.training.qualification.business.service.*;
import com.sms.training.qualification.business.ITfQuaApplyBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyInspectorBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;

/**
 * 制定一般资格模拟机复训/检查流程action
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "qualPilotList", location = "/sms/training/qualification/quaApplyGeneral/qualPilotList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "submit", location = "/sms/training/qualification/quaApplyGeneral/quaGeneralSubmit.jsp"),
		@Result(name = "json", type = "json"),
		})
		
public class TfQuaApplyGeneralAction  extends BaseAction {
	
		private static final long serialVersionUID = 1L;
		private static final String NAV_TAB_ID="qualApply_tabId";
		// 添加成功表示
		private boolean successFlag = true;
		// 消息实体
		private Message message;
		private static ProcessBase processBase=new ProcessBase();
		// 当前模块名称
		private String moduleName = "TfQuaGeneral";	
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
		private String originalgrade;
		private String ids;
		private String formUrl; 
		private String state;
		//审批意见，以后可以删
		private String opinionInfo;
		private String detailsid;
		private String subProcessid;
		private String taskId;
		/**资质类型 子类id*/
		private String subGroupId;
		private Date qianmingDate;
		//用来刷新待申报信息页面
		private String declarInfoTabId;
		
		private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
		private TfQualDeclarInfo tfQualDeclarInfo;
		private TfQualDeclaraPilot tfQualDeclaraPilot;
		private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;

		private TfQualDeclaraPilotBS tfQualDeclaraPilotBS;
		//资质申请的流程操作对象
		private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
		private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
		private IProcesshistoryBS processhistoryBS;
		private ITfQualBaseTypeBS tfQualBaseTypeBS;
		private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
		//资质类型list
		private List<TfQualBaseType> tfQualBaseTypeList;
		private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
		//申报人员明细list
		private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
		private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
		private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
		private List<TfQualPilotLicence> tfQualPilotLicenceList =new ArrayList<TfQualPilotLicence>();
		private List<TfQualPilotTechrecord> tfQualPilotTechrecordList=new ArrayList<TfQualPilotTechrecord>();
		private ITfQuaApplyGeneralBS tfQuaApplyGeneralBS;
		private String typeId;
		List<String> dateStr;
		private String dateCompute;
		private boolean showSubmitBtn;
		//
		private Date ptrapprovedDate;
		private TfQualBaseType tfQualBaseType;
		private TfQualPilotLicence tfQualPilotLicence;
		private ITfQuaApplyBS tfQuaApplyBS;
		//排课列表
		private TfQualPilotCourselist tfQualPilotCourselist;
		private String courselistid;
		private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
		//当前可以资质申报的飞行员数量
		private int pilotCount;
		 
		/**
		 * 构造函数
		 */
		public TfQuaApplyGeneralAction() {	
		}
		
		/**
		 * 申报人员计算页面
		 */
		public String list() {
			try {
				CmUser user = getUser();
				userId = user.getUserId();
				dateStr = new ArrayList<String>();
				Date date;
				for (int i = 0; i < 12; i++) {
					date = DateTool.addMonths(i + 1);
					dateStr.add(i,DateTool.formatDate(date, DateTool.DEFAULT_DATE_FORMAT).substring(0, 7));
				}
				//判断“制定训练计划”按钮是否显示
				if(dateCompute != null) {
					showSubmitBtn = !dateStr.get(0).equals(dateCompute) && !dateStr.get(1).equals(dateCompute);
				}else{
					dateCompute = dateStr.get(0);
				}
				
				List<TfQualPilotLicence> licences = null;
				List<TfQualPilotTechrecord> techrecords = null;
				this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
				if(typeids == null && (tfQualBaseTypeList != null && tfQualBaseTypeList.size() > 0 )) {
					typeids = tfQualBaseTypeList.get(0).getTypeid(); 
				}
				if(dateCompute != null && !dateCompute.equals("")) {
					String orgIds=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
					this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findPilotStayList(typeids,orgIds,dateCompute);
					//获取上次训练日期和签注日期,现行技术标准（此处日后须优化）
					for(TfQualDeclaraPilotStay stay :tfQualDeclaraPilotStayList) {
						licences = tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
						tfQualPilotLicenceList.add(licences == null || licences.size() < 1 ? null : licences.get(0));
						techrecords = tfQualPilotLicenceBS.getTechrecordsByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
						tfQualPilotTechrecordList.add(techrecords == null || techrecords.size() < 1 ? null : techrecords.get(0)); 
					}
				}
				setFormUrl("tf-qua-apply/tf-qua-apply-general!toSubmit.do");
			} catch (RuntimeException e) {
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
		
		public String ComputingStaff() {
			try {
				String orgIdStr = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				this.tfQuaApplyGeneralBS.doComputingGeneralStaff(typeId,orgIdStr,dateCompute,getUserOrg().getName(),subGroupId);	
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
				formUrl = "tf-qua-apply/tf-qua-apply-general!quaApplyAdd.do";
 			}
			catch (RuntimeException e) {
				e.printStackTrace();
			}
			return "submit";
		}

		/**
		 * 执行制定计划操作,开启流程，跳转到教员,结束流程
		 *
		 * 应急，危险品，执照考试，理论复试 飞行教员理论课复训 流程 执行制定计划操作,开启流程，跳转到资质更新,结束流程
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
					if(state.equals("btg")) {
						this.message = this.getSuccessMessage("下发训练失败!", NAV_TAB_ID, "closeCurrent", "tf-qua-apply/tf-qua-apply-general!quaApplyAdd.do" );
					} else {
						//获取此次申报信息 
						tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
						if(subGroupId==null||"".equals(subGroupId.trim())){
							subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
						}
                        //部署资质申报流程  ZXL：在训练  DZZGX：待资质更新
						String subGrpId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
						if("QUAL_YB_MNJFXJC".equals(subGrpId)) {
							tianjinOrgRole = ConstantList.TJFXBDSZD + "-" + "资质-教员";
							guohangOrgRole = ConstantList.ZDSDD + "-" + "资质-教员";
							personState = "ZXL";
						}else if ("QUAL_YB_YJHGZ".equals(subGrpId)||"QUAL_YB_WXPHGZ".equals(subGrpId)||"QUAL_YB_LLFX".equals(subGrpId)
								  || "QUAL_YB_NDZZLLKS".equals(subGrpId)||"QUAL_YB_NDZZLLKS".equals(subGrpId)){
							tianjinOrgRole = ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员";
							guohangOrgRole = ConstantList.GUOHANGFEIXINGZONGDUI_ID + "-" + "资质-分公司级飞管标准专员";
							personState = "DZZGX";
						}
						String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.QUALIFICATION_APPLY_GENERAL);		 
						//开启资质申报流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						mapStart.put("JiDui", sysOrganization.getId());
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    //将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						//保存流程公共信息  转到declar info表中
						if(sysOrganization.getParent_Fun().getName().contains("天津")) {
							map.put("jiaoyuan", tianjinOrgRole);
						}else {
							map.put("jiaoyuan", guohangOrgRole);
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
									pilot.setState(personState);
									if(sysOrganization.getParent_Fun().getName().contains("天津")) {
										pilot.setOrgRole(tianjinOrgRole);
									} else {
										pilot.setOrgRole(guohangOrgRole);
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
						map.put("planInfoId", declaredinfoid);//申报新Id
						map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());//申报新Id
						map.put("subGroupId", subGroupId);
						//获取此次申报信息
						String taskIdString = processBase.getTaskIdByPiID(pid);
						//存储流程历史处理人和历史处理机构
						processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
						//将流程流转向下
						tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
						setSuccessFlag(true);
						if(declarInfoTabId != null && declarInfoTabId.equals("declarInfoTab")) {
							this.message = this.getSuccessMessage("下发训练成功!", "declarInfoTab", "closeCurrent", "");
						}else {
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
		 * 检查员 跳转到 刘思远 资质更新    已改版
		 */
		public String toFeiGuanZrBpsConfirm() {
			try {
				 if(courselistid!=null && !courselistid.equals("")){
					 tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
					 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
					 tfQualPilotCourselist.setState("COVER");
					 tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
				 }
				 if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")){
					 if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
						SysOrganization sysOrganization = getUserOrg();
						tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
						CmUser user = getUser();
						Map<String, Object> map = new HashMap<String, Object>();
						if("QUAL_FT_CFBJY".equals(subGroupId)|| "QUAL_FT_FXJYLLJY".equals(subGroupId)){
							tfQualDeclaraPilot.setState("GSZY");
							tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
							map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
						}else{
							tfQualDeclaraPilot.setState("DZZGX");
							if(sysOrganization.getName().contains("天津")) {
								tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员");
								map.put("GsZhuanYuanOver", ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员");
							} else {
								tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID + "-" + "资质-分公司级飞管标准专员");
								map.put("GsZhuanYuanOver", ConstantList.GUOHANGFEIXINGZONGDUI_ID + "-" + "资质-分公司级飞管标准专员");
							}
						}
						tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
						//扭转流程
						if(!subProcessid.equals("")) {
							taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
						}
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "资质更新", map);
						String taskIdString = processBase.getTaskIdByPiID(subProcessid);
						tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
				 }
				 setTabIndexToReload("0");
				 this.message = this.getSuccessMessage("填单成功！", NAV_TAB_ID, "", "");
				 }
			}catch(Exception e) {
				this.message = this.getFailMessage("流程扭转失败！");
				e.printStackTrace();
			}
			return "json";
		}
/**
 *     检查员下发流程方法   初始版
 *     待教员、检查员节点完全无误后，再删
 */
//		public String toFeiGuanZrBpsConfirm() {
//			try {
//				SysOrganization sysOrganization = getUserOrg();
//				//调整 训练人员状态
//				if(detailsid != null && !detailsid.equals("")) {
//					tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
//					tfQualDeclaraPilot.setState("DZZGX");
//					if(sysOrganization.getName().contains("天津")) {
//						tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员");
//					} else {
//						tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID + "-" + "资质-分公司级飞管标准专员");
//					}	
//					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
//					//调整 训练人员状态
//					//扭转流程
//					if(!subProcessid.equals("")) {
//						taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
//					}
//					CmUser user = getUser();
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("FromOrgName", sysOrganization.getName());
//					map.put("FromOrgUser", user.getName());
//					if(sysOrganization.getName().contains("天津")) {
//						map.put("GsZhuanYuanOver", ConstantList.TJFGB + "-" + "资质-分公司级飞管标准专员");
//					} else {
//						map.put("GsZhuanYuanOver", ConstantList.GUOHANGFEIXINGZONGDUI_ID + "-" + "资质-分公司级飞管标准专员");
//					}	
//					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "资质更新", map);
//					String taskIdString = processBase.getTaskIdByPiID(subProcessid);
//					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
//					setTabIndexToReload("0");
//					this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "", "jbpm4/tf-qual-apply-jbpm!toIndexTask.do?typeInfo=" + typeInfo);
//				}
//			}
//			catch(Exception e) {
//				this.message = this.getFailMessage("流程扭转失败！");
//				e.printStackTrace();
//			}
//			return "json";
//		}
		
		/**
		 * 资质更新 
		 */
		public String updatePilotLicence() {
			try {
				if(!ids.equals("")) {
					String idsStr = Util.toStringIds(ids);
					tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
					for (int i = 0, n = tfQualDeclaraPilotList.size(); i < n ; i++) {
						tfQualDeclaraPilot = tfQualDeclaraPilotList.get(i);
						tfQualBaseType = tfQualDeclaraPilot.getTfQualBaseType();
						tfQualPilotLicenceList = tfQualPilotLicenceBS.getLicencesByIdAndStatus(tfQualDeclaraPilot.getCmPeople().getId(), tfQualBaseType.getTargetatrid().getId());
						tfQualPilotLicence = tfQualPilotLicenceList.get(0);
						Date plcdate = tfQualPilotLicence.getPlccheckd();
						Calendar c = Calendar.getInstance();
						c.setTime(plcdate);
						c.add(Calendar.MONTH, 6);
						plcdate = c.getTime();
						tfQualPilotLicence.setPlccheckd(plcdate);
						tfQualPilotLicenceBS.saveOrUpdate(tfQualPilotLicence);
						tfQualDeclaraPilotList.get(i).setState("OVEREND");
						tfQualDeclaraPilotBS.save(tfQualDeclaraPilotList.get(i));
					}				
				}
			}catch(Exception e) {
				setSuccessFlag(false);
				this.message = this.getFailMessage("资质更新失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			this.message = this.getSuccessMessage("资质更新成功！", NAV_TAB_ID, "", "");
			
			return "json";
		}
		
		private void setTabIndexToReload(String index) { //处理子标签页跳转问题
			this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
			if(index != null && !index.equals("0")) {
				this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
			}
		}

		//=======================getters and setters============================
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
			TfQuaApplyGeneralAction.processBase = processBase;
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
		@JSON(serialize = false)
		public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
			return tfQualDeclarApprovalInfoBS;
		}
		public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
			this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
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
		public void setTfQualDeclaraPilotStayList(List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
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
		public String getOriginalgrade() {
			return originalgrade;
		}
		public void setOriginalgrade(String originalgrade) {
			this.originalgrade = originalgrade;
		}
		@JSON(serialize = false)
		public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
			return tfQualBaseTypeBS;
		}
		public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
			this.tfQualBaseTypeBS = tfQualBaseTypeBS;
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
		public IProcesshistoryBS getProcesshistoryBS() {
			return processhistoryBS;
		}
		public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
			this.processhistoryBS = processhistoryBS;
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

		public void setTfQualPilotLicenceList(List<TfQualPilotLicence> tfQualPilotLicenceList) {
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
		public Date getPtrapprovedDate() {
			return ptrapprovedDate;
		}

		public void setPtrapprovedDate(Date ptrapprovedDate) {
			this.ptrapprovedDate = ptrapprovedDate;
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
