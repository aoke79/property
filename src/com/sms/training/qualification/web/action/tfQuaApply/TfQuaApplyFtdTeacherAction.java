package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQuaApplyFtdBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualPilotDetailBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

/**
 * FTD教员流程
 * @author zhf
 *
 */
@Results({
		@Result(name = "json", type = "json"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "qualPilotListFT", location = "/sms/training/qualification/tfQualFtdTeacher/qualPilotListFT.jsp")
		})
public class TfQuaApplyFtdTeacherAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String state;
	// 消息实体
	private Message message;
	private TfQualDeclarInfo tfQualDeclarInfo;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private static final String NAV_TAB_ID="qualApply_tabId";
	//申报信息ID
	private String declaredinfoid="";
	// 资质申请的流程操作对象
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	private String ids;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private ProcessBase processBase = new ProcessBase();
	private IProcesshistoryBS processhistoryBS;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	private String opinionInfo;
	private Date qianmingDate;
	private String declarInfoTabId;
	// 添加成功表示
	private boolean successFlag = true;
	// 任务传递
	private String taskId;
	// 当前模块名称
	private String moduleName = "TfQuaApplyFtdTeacher";
	private ITfQualPilotDetailBS tfQualPilotDetailBS;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private String detailsid;
	private String subProcessid;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	//资质类型 子类id
	private String subGroupId;
	private String userId;
	//资质类型ID
	private String typeId;
	private String formUrl;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private List<TfQualBaseType> tfQualBaseTypeList;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	//资质类型实体
	private TfQualBaseType tfQualBaseType;
	private ITfQuaApplyFtdBS tfQuaApplyFtdBS;
	//待申报人员列表
	private List<Object> tfQualDeclaraPilotStayList;
	private String typeids;
	
	
	/**
	 * 构造方法
	 */
	public TfQuaApplyFtdTeacherAction() {

	}
	
	/**
	 * to 待申报人员列表页面
	 */
	public String List(){
		try {
			userId=getUser().getUserId();
			//根据资质类型分类及subGroupId获取对应的资质类型列表
			this.tfQualBaseTypeList = this.tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
			if(typeids == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
				tfQualBaseType = tfQualBaseTypeList.get(0);
				typeId=tfQualBaseType.getTypeid();
			}else if(typeids!=null){
				tfQualBaseType=tfQualBaseTypeBS.findById(TfQualBaseType.class, typeids);
			}
			//获取对应资质类型下符合条件的供选择待申报人员列表
			if(tfQualBaseType!=null){
				String orgNameStr=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
					//获取对应资质类型下符合条件的供选择待申报人员列表
				tfQualDeclaraPilotStayList= tfQuaApplyFtdBS.getAvailablePilotStayList(tfQualBaseType, orgNameStr);
				}
			setFormUrl("tf-qua-apply/tf-qua-apply!toSubmit.do");
			
		} catch (Exception e) {
			tfQuaApplyFtdBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		
		return "qualPilotListFT";
	}
	
	/**
	 * 创建申报信息
	 * @return
	 */
	public String toCreateDeclaInfo(){
		try{
			CmUser user = getUser();
			userId=getUser().getUserId();
		    declaredinfoid=this.tfQuaApplyFtdBS.createDeclarInfo(typeids,ids,user);
		}
		catch (RuntimeException e) {
			tfQuaApplyFtdBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 总队开启流程
	 */
	public String quaApplyAdd(){
		try{
			// 登录用户信息
			CmUser user = getUser();
			// 拿到当前机构
			SysOrganization sysOrganization = getUserOrg();
			if(state!=null && !state.equals("")){
				if(state.equals("btg")){
					this.message = this.getSuccessMessage("审核不通过!", NAV_TAB_ID,"closeCurrent","" );
				}else{
					//获取此次申报信息 
					tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
//					 部署资质申报流程
					String	processName=ConstantList.QUALIFICATION_FTD_TEACHER;
					String deploymentProcessId = tfQuaApplyJbpmBS.deploymentProcessDefinition(user, processName);
					 
					// 开启资质申报流程
					Map<String, Object> mapStart = new HashMap<String, Object>();
					mapStart.put("JiDui", sysOrganization.getId());
					String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					//把流程信息保存到申报信息中
					tfQualDeclarInfo.setProcessid(pid);
					tfQualDeclarInfo.setStatus("F");
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
					//根据当前选择下发的人员对原计划人员列表进行删除操作   
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
					if(ids != null && !ids.equals("")){
						for (TfQualDeclaraPilot pilot : tfQualDeclaraPilotList) {
							if(ids.contains(pilot.getDetailsid())){
								pilot.setState("WCXL");//人员状态 改为“完成训练”
								pilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-训练专员");
							}else{
								pilot.setState("REJECT");
							}
							tfQualDeclaraPilotBS.saveOrUpdate(pilot);
						}
					}
					//保存待办需要的机构及人员
					// 将实例id保存到流程信息里
					Map<String, Object> map = new HashMap<String, Object>();
					//保存流程公共信息  转到declar info表中
					map.put("ZlkzZhuanYuan",ConstantList.GHFGBID+"-"+"资质-训练专员");
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					map.put("FromOrgUserID", user.getUserId());
					map.put("task_plan", pid);
					map.put("planInfoId", declaredinfoid);
					map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
					map.put("subGroupId", subGroupId);
					//获取此次申报信息
					String taskIdString = processBase.getTaskIdByPiID(pid);
					// 存储流程历史处理人和历史处理机构
					processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
					//讲流程流转向下
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
					//保存审批意见
					tfQualDeclarApprovalinfo= new TfQualDeclarApprovalinfo();
					tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
					tfQualDeclarApprovalinfo.setPid(pid);
					//保存审批意见
					tfQualDeclarApprovalinfo.setOrgName(sysOrganization.getName());
					tfQualDeclarApprovalinfo.setSigningDate(qianmingDate);
					tfQualDeclarApprovalinfo.setSigningid(user.getUserId());//此处未保存 签名ID 
					tfQualDeclarApprovalinfo.setState(state);
					tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
					setSuccessFlag(true);
					if(declarInfoTabId!=null && declarInfoTabId.equals("declarInfoTab")){
						this.message = this.getSuccessMessage("审核成功!", "declarInfoTab","closeCurrent","");
					}else{
						this.message = this.getSuccessMessage("审核成功!", NAV_TAB_ID,"closeCurrent","");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSuccessFlag(false);
			tfQualifiedDeclareBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "SUCCESS";
	}
	
	/**
	 * 申报阶段  培训部审批下发   下发给 高金川
	 * toJianChaYuanYuanConfirm  对应这个方法  TODO 5月3号，流程修改为申报阶段只留一个节点，故此方法，删除
	 * @return
	 */
//	public String PxbApprovalIssued(){
//			try{
//				//根据当前选择下发的人员对原计划人员列表进行删除操作
//				if(ids != null && !ids.equals("")){
//					if(taskId!=null && !taskId.equals("") ){
//						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
//						if(!declaredinfoid.equals("")){
//							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
//							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
//								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
//									tfQualDeclaraPilotList.get(i).setState("REJECT");
//									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
//									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
//								}
//							}
//						}
//						Map<String, Object> map = new HashMap<String, Object>();
//						map.put("ZlkzZhuanYuan",ConstantList.GHFGBID+"-"+"资质-训练专员"); 
//						//保存待办需要的机构及人员
//						map.put("FromOrgName", getUserOrg().getName());
//						map.put("FromOrgUser", getUser().getName());
//						map.put("subGroupId", subGroupId);
//						//当流程扭转成功后 调整申报人员的状态						
//					    String idsStr = Util.toStringIds(ids);
//						//去掉驳回的人员
//						tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
//						for (int i = 0,n=tfQualDeclaraPilotList.size(); i <n ; i++) {
//							tfQualDeclaraPilotList.get(i).setState("WCXL");//人员状态 改为“完成训练”
//							tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.GHFGBID+"-"+"资质-训练专员");
//							tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
//						}
//						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", " 结束", map);
//						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
//					}
//				}
//			}catch(Exception e){
//				this.message = this.getFailMessage("流程扭转失败！");
//				tfQualifiedDeclareBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//				e.printStackTrace();
//			}
//		return "json";
//	}
	
	/**
	 * 此处 高金川 下发训练计划  toXunLianConfirm
	 * 转给   计划控制室质量控制经理审核报批
	 * @return
	 */
	public String  toZlkzjlApprovalIssued(){
		try{ 
			String orgRoleTemp ="";
			if(!declaredinfoid.equals("")){
				tfQualDeclarInfo = tfQualDeclaraPilotBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				if(tfQualDeclarInfo.getDeclaredinfodesc().contains("天津")){
					orgRoleTemp= ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员";
				}else{
					orgRoleTemp= ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员";
				}
			}
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				// 修改此人的state=ZLKZJL 质量控制经理
				tfQualDeclaraPilot.setState("ZLKZJLSP");
				tfQualDeclaraPilot.setOrgRole(orgRoleTemp);
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID,"", "");  
		}catch(Exception e){
			this.message = this.getFailMessage("下发失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 王楠  分公司级飞管专员    开启后半部分流程   toFgsZyConfirm 
	 * 转给   质量控制室高级经理审核报批
	 * @return
	 */
	public String toZlkzgjjlApprovalIssued(){
		try{
//			 部署资质审核流程
			CmUser user = getUser();
			SysOrganization sysOrganization = getUserOrg();
			String deploymentProcessId = null;
			deploymentProcessId=tfQuaApplyJbpmBS.deploymentProcessDefinition(user, ConstantList.SUB_QUALIFICATION_FTD_TEACHER);
			// 开启资质审核流程
			Map<String, Object> mapStart = new HashMap<String, Object>();
			mapStart.put("ZlkzZhuanYuan", "");
			String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
		    // 将实例id保存到流程信息里
			Map<String, Object> map = new HashMap<String, Object>();
			if(sysOrganization.getParent_Fun().getName().contains("天津")){
				map.put("ZlkzJingLi", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
			}else{
				map.put("ZlkzJingLi", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
			}
			String taskIdString = processBase.getTaskIdByPiID(pid);
			if(taskIdString!=null && !taskIdString.equals("")){
				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "计划控制室质量控制经理审核报批", map);
			}
			taskIdString = processBase.getTaskIdByPiID(pid);
			if(sysOrganization.getParent_Fun().getName().contains("天津")){
				map.put("ZlkzGaoJiJingLi", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任"); 
			}else{
				map.put("ZlkzGaoJiJingLi", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
			}
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUserID", user.getUserId());
			map.put("task_plan", pid);
			map.put("subGroupId", subGroupId);
			if(taskIdString!=null && !taskIdString.equals("")){
				tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "计划控制室高级经理审核报批", map);
			}
			//流程下发成功后 修改此人的state=ZLKZGJJL 质量控制高级经理审批
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
						if(tfQualDeclaraPilot!=null){
							tfQualDeclaraPilot.setState("ZLKZGJJLSP");   
							if(sysOrganization.getParent_Fun().getName().contains("天津")){
								tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
							}else{
								tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
							}
							tfQualDeclaraPilot.setSubProcessid(pid);
							tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
						}
			}
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"");
		}catch (Exception e) {
			e.printStackTrace();
			setSuccessFlag(false);
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "SUCCESS";
	}
	
	/**
	 * 胡玉录     资质-分公司级技术委员会办公室主任     toZongDuiZrBpsConfirm
	 * 转给  培训部领导审核报批     
	 */
	public String toPxbldApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=PXBLD 培训部领导审核报批
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("PXBLDSP");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			
			map.put("PxbLingDao",ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任");
			map.put("subGroupId", subGroupId);
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "培训部领导审核报批", map);
			
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 刘铁祥  下发  给高金川
	 * 培训部领导审批完  转给 质量控制专员申报
	 */
	public String toZlkzzyApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=ZLKZZYSB   质量控制专员申报
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("ZLKZZYSB");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-训练专员");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("subGroupId", subGroupId);
			map.put("ZlkzZhuanYuanSb",ConstantList.GHFGBID+"-"+"资质-训练专员");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "质量控制专员申报", map);
			
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 高金川审核阶段   下发流程
	 * 质量控制专员申报   转给飞管专员申报
	 */
	public String toFgzysbApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=FGZYSB  飞管专员申报
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("FGZYSB");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("subGroupId", subGroupId);
			map.put("GsZhuanYuanSb",ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员申报", map);
			
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 王菲 给肖忆
	 * 飞管专员申报  转给飞管高级经理
	 */
	public String toFggjjlApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=FGGJJLSP  飞管高级经理审核报批
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("FGGJJLSP");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管标准高级经理");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("subGroupId", subGroupId);
			map.put("GsGaoJiJingLi",ConstantList.GHFGBID+"-"+"资质-公司飞管标准高级经理");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管高级经理审核报批", map);
			
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 肖忆 给常厚东
	 * 飞管高级经理   给飞管领导审核
	 */
	public String toFgldApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=FGLDSP  飞管领导 审核报批
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("FGLDSP");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("subGroupId", subGroupId);
			map.put("GsLingDao",ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管领导审核", map);
			
			setTabIndexToReload("0");// 处理子标签页跳转问题
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 常厚东给王菲
	 * 公司领导审批  转给 公司专员发文
	 */
	public String toGszyfwApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=FGZYFW  飞管专员发文
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("FGZYFW");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("subGroupId", subGroupId);
			map.put("GsZhuanYuanFw",ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文", map);
			
			setTabIndexToReload("0");
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 王菲发文  转给 高金川
	 * 公司专员发文   转给质量控制专员资质更新
	 */
	public String toKzzyzzgxApprovalIssued(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			// 调整 训练人员状态  State=KZZYZZGX  控制专员资质更新
			if(detailsid!=null && !detailsid.equals("")){
				tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				tfQualDeclaraPilot.setState("KZZYZZGX");
				tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-训练专员");
				tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
			}
			// 扭转流程
			if(!subProcessid.equals("")){
				taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
			}
			CmUser user = getUser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FromOrgName", sysOrganization.getName());
			map.put("FromOrgUser", user.getName());
			map.put("subGroupId", subGroupId);
			map.put("ZlkzZhuanYuanGx",ConstantList.GHFGBID+"-"+"资质-训练专员");
			tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "质量控制专员资质更新", map);
			
			setTabIndexToReload("0");
			this.message = this.getSuccessMessage("下发成功!", NAV_TAB_ID,"", "");
		}catch(Exception e){
			this.message = this.getFailMessage("流程扭转失败！");
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 处理子标签页跳转问题
	 */
	private void setTabIndexToReload(String index){ 
		this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
		if(index!=null && !index.equals("0")){
			this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
		}
	}
	
	//====================================  set/get方法      ====================================
	@JSON(serialize = false)
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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}

	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
//此处不要加flase;
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}

	@JSON(serialize = false)
	public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
		return tfQuaApplyJbpmBS;
	}

	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
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

	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
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

	public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
		this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
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
	public String getDeclarInfoTabId() {
		return declarInfoTabId;
	}

	public void setDeclarInfoTabId(String declarInfoTabId) {
		this.declarInfoTabId = declarInfoTabId;
	}

	@JSON(serialize = false)
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JSON(serialize = false)
	public ITfQualPilotDetailBS getTfQualPilotDetailBS() {
		return tfQualPilotDetailBS;
	}

	public void setTfQualPilotDetailBS(ITfQualPilotDetailBS tfQualPilotDetailBS) {
		this.tfQualPilotDetailBS = tfQualPilotDetailBS;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
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
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize = false)
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@JSON(serialize = false)
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	@JSON(serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
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
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}
	@JSON(serialize = false)
	public ITfQuaApplyFtdBS getTfQuaApplyFtdBS() {
		return tfQuaApplyFtdBS;
	}

	public void setTfQuaApplyFtdBS(ITfQuaApplyFtdBS tfQuaApplyFtdBS) {
		this.tfQuaApplyFtdBS = tfQuaApplyFtdBS;
	}
	@JSON(serialize = false)
	public List<Object> getTfQualDeclaraPilotStayList() {
		return tfQualDeclaraPilotStayList;
	}

	public void setTfQualDeclaraPilotStayList(List<Object> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}

	@JSON(serialize = false)
	public String getTypeids() {
		return typeids;
	}
	public void setTypeids(String typeids) {
		this.typeids = typeids;
	}

}
