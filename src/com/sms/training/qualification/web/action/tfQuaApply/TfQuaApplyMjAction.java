package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.*;

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
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;

@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "json", type = "json"),
	
	@Result(name = "success", location = "/standard/ajaxDone.jsp")
})
public class TfQuaApplyMjAction  extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static final String moduleName = "TfQuaApplyMjAction";
	private Message message;
	private ITfQuaApplyMjBS tfQuaApplyMjBS;
	private String ids;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private String taskId;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	//资质类型 子类id
	private String subGroupId;
	private String declaredinfoid;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private String detailsid;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private String subProcessid;
	private Date qianmingDate;
	private String opinionInfo;
	private String state;
	private static final String NAV_TAB_ID="qualApply_tabId";
	private static ProcessBase processBase=new ProcessBase();
	private TfQualPilotCourselist tfQualPilotCourselist;
	private String courselistid;
	private ITfQuaApplyBS tfQuaApplyBS;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;

	public TfQuaApplyMjAction() {
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
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
							if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
								tfQualDeclaraPilotList.get(i).setState("REJECT");
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
								this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
							}else{
								tfQualDeclaraPilotList.get(i).setState("DXL");//人员状态 待训练
								if(getUserOrg().getName().contains("天津")){
									tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.TJFGB+"-"+"资质-M-j-F1-F2-训练专员");
								}else{
									tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-M-j-F1-F2-训练专员"); 
								}
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
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
				
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
				this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "closeCurrent", "");
				}
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程流转失败！");
			tfQuaApplyMjBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 检查员  下发流程方法  改后版
	 * @return
	 */
	public String toJiShuShenBaoConfirm(){
		try	{
			 if(courselistid!=null && !courselistid.equals("")){
				 tfQualPilotCourselist=tfQualifiedDeclareBS.findById(TfQualPilotCourselist.class, courselistid);
				 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态 
				 tfQualPilotCourselist.setState("COVER");
				 tfQualifiedDeclareBS.saveOrUpdate(tfQualPilotCourselist);
			 }
			 if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")){
				 if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
					SysOrganization sysOrganization = getUserOrg();
					 //调整 训练人员状态
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					tfQualDeclaraPilot.setState("FGSZR");
					if(sysOrganization.getParent_Fun().getName().contains("天津")){
						tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
					}else {
						tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
					}
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
					//扭转流程
					subProcessid = tfQualDeclaraPilot.getSubProcessid();
					if(subProcessid!=null && !subProcessid.equals("")){
						taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
					}
					CmUser user = getUser();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					if(sysOrganization.getParent_Fun().getName().contains("天津")){
						map.put("FgsZhuRen", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
					}else{
						map.put("FgsZhuRen", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
					}
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级技术委员会办公室主任审核报批", map);// 
				 }
				setTabIndexToReload("0");
				this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID,"","");
			 }
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyMjBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	/**
	 * 检查员下发流程  原始方法
	 * 待教员、检查员节点完全无误后，再删
	 */
//	public String toJiShuShenBaoConfirm(){
//		try	{
//			 SysOrganization sysOrganization = getUserOrg();
//			 //调整 训练人员状态
//			 if(detailsid!=null && !detailsid.equals("")){
//				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
//				tfQualDeclaraPilot.setState("FGSZR");
//				if(sysOrganization.getParent_Fun().getName().contains("天津")){
//					tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
//				}else {
//					tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
//				}
//				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
//			 }
//			//扭转流程
//			 subProcessid = tfQualDeclaraPilot.getSubProcessid();
//			if(subProcessid!=null && !subProcessid.equals("")){
//				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
//			}
//			CmUser user = getUser();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("FromOrgName", sysOrganization.getName());
//			map.put("FromOrgUser", user.getName());
//			if(sysOrganization.getParent_Fun().getName().contains("天津")){
//				map.put("FgsZhuRen", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
//			}else{
//				map.put("FgsZhuRen", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
//			}
//			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级技术委员会办公室主任审核报批", map);// 
//			setTabIndexToReload("1");// 处理子标签页跳转问题
//			this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"","jbpm4/tf-qual-apply-jbpm!toIndexTask.do?typeInfo="+typeInfo);
//			//扭转流程
//		}catch(Exception e){
//			this.message = this.getFailMessage("流程扭转失败！");
//			tfQuaApplyMjBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//			e.printStackTrace();
//		}
//		return "json";
//	}
	
	/**
	 * 分公司级技术委员会主任
	 * @return
	 */
	public String toFeiGuanZrBpsConfirm() {
		try {
			//调整 训练人员状态
			SysOrganization sysOrganization = getUserOrg();
			if( detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("DZZGX");
				if(sysOrganization.getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
				}else {
					tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
				}
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			//调整 训练人员状态
			//扭转流程
			subProcessid=tfQualDeclaraPilot.getSubProcessid();
			if(subProcessid!=null && !subProcessid.equals("")){
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
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID,"", "");
			//扭转流程
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyMjBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 刘思源 分公司级飞管专员
	 * @return
	 */
	public String toFgsZyConfirm(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			//调整 训练人员状态
			if(!detailsid.equals("") && detailsid!=null){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				if(sysOrganization.getParent_Fun().getName().contains("天津")){
					tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
				}else {
					tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
				}
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
				//调整 训练人员状态
				//扭转流程
				subProcessid=tfQualDeclaraPilot.getSubProcessid();
				if(!subProcessid.equals("")){
					taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
				}
				CmUser user = getUser();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
//				map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"");////////////
//				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
//				String taskId = processBase.getTaskIdByPiID(subProcessid);
				tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
				this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
			}
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQuaApplyMjBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
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
	//=======================getters and setters============================
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public ITfQuaApplyMjBS getTfQuaApplyMjBS() {
		return tfQuaApplyMjBS;
	}
	public void setTfQuaApplyMjBS(ITfQuaApplyMjBS tfQuaApplyMjBS) {
		this.tfQuaApplyMjBS = tfQuaApplyMjBS;
	}

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
	public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
		return tfQualDeclarApprovalinfo;
	}
	public void setTfQualDeclarApprovalinfo(TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
		this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
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

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JSON(serialize = false)
	public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
		return tfQualDeclarApprovalInfoBS;
	}
	public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
		this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
	}
	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}
	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}

	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
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
	public Date getQianmingDate() {
		return qianmingDate;
	}
	public void setQianmingDate(Date qianmingDate) {
		this.qianmingDate = qianmingDate;
	}

	public String getOpinionInfo() {
		return opinionInfo;
	}
	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@JSON(serialize = false)
	public static ProcessBase getProcessBase() {
		return processBase;
	}
	public static void setProcessBase(ProcessBase processBase) {
		TfQuaApplyMjAction.processBase = processBase;
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
	public ITfQuaApplyBS getTfQuaApplyBS() {
		return tfQuaApplyBS;
	}

	public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
		this.tfQuaApplyBS = tfQuaApplyBS;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
		return tfQualDeclaraPilotStayBS;
	}
	public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
		this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
	}
	
	
}
