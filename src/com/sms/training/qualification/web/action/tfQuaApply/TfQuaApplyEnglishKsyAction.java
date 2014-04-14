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
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

/**
 * 英语评分员和英语考试员流程
 * 
 * @author zhf   date 2013-05-16
 *
 */
@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp")
})
public class TfQuaApplyEnglishKsyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	//申报信息
	private TfQualDeclarInfo tfQualDeclarInfo;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	//申报人员明细列表
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
	private String ids;
	//消息实体
	private Message message;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	private static ProcessBase processBase=new ProcessBase();
	private IProcesshistoryBS processhistoryBS;
	//资质类型 子类id
	private String subGroupId;
	//用来刷新待申报信息页面
	private String declarInfoTabId;
	//navTabId
	private static final String NAV_TAB_ID="qualApply_tabId";
	//模块名
	private static final String moduleName = "TfQuaApplyEnglishKsyAction";
	//申报信息ID
	private String declaredinfoid="";
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private String taskId;
	private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS; 
	//定义一个list 用来存放是否已上传文件的标志
	private List<Integer> statusList=new ArrayList<Integer>();
	
	/**
	 * 构造方法
	 */
	public TfQuaApplyEnglishKsyAction() {
	}

	//分公司飞管标准专员 创建流程，并上报给 分公司高级经理
		public String toFgsjlConfirm(){
			try{
				//登录用户信息
				CmUser user = getUser();
				//拿到当前机构
				SysOrganization sysOrganization = getUserOrg();
				//部署资质申报流程
					String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(ConstantList.QUALIFICATION_APPLY_ENGLISH_KSY);
					 
				// 开启资质申报流程
				Map<String, Object> mapStart = new HashMap<String, Object>();
				mapStart.put("FgsZhuanYuan", sysOrganization.getId());
				String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
				
				//获取此次申报信息 
				String infoId=tfQualDeclarInfo.getDeclaredinfoid();
				if(infoId!=null && !infoId.equals("")){
					tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, infoId);
					tfQualDeclarInfo.setProcessid(pid);
					tfQualDeclarInfo.setStatus("F");
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
					if(subGroupId==null||"".equals(subGroupId.trim())){
						subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
					}
					tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(infoId);
					//判断是否已经上传文件
					for(int t=0,n=tfQualDeclaraPilotList.size();t<n;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
					
				if(!ids.equals("")){
					for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
						if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
							tfQualDeclaraPilotList.get(i).setState("REJECT");
							tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
							this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
						}
					}
				}
				taskId = processBase.getTaskIdByPiID(pid);
				processhistoryBS.saveProcessHistory(taskId, getUser(),getUserOrg());

				Map<String, Object> map = new HashMap<String, Object>();
				//保存待办需要的机构及人员
				map.put("FromOrgName", sysOrganization.getName());
				map.put("FromOrgUser", user.getName());
				map.put("FromOrgUserID", user.getUserId());
				map.put("task_plan", pid);
				map.put("planInfoId", infoId);
				map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
				map.put("subGroupId",subGroupId);
				//流程流转向下
					
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
			return "SUCCESS";
		}
		
		//公司飞管专员 申报 ,结束申报流程
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
						String idsStr = Util.toStringIds(ids);
						tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
						//如果人员未通知培训，则设置状态“已通知培训”，流程不流转
						if(("QUAL_EN_YYPFY".equals(subGroupId)||"QUAL_EN_YYKSY".equals(subGroupId))&&tfQualDeclaraPilotList.size()!=0 && !"TZPX".equals(tfQualDeclaraPilotList.get(0).getState())&& !"WCPX".equals(tfQualDeclaraPilotList.get(0).getState())){
							for(TfQualDeclaraPilot detail : tfQualDeclaraPilotList){
								detail.setState("TZPX");//已通知培训
								tfQualDeclaraPilotBS.saveOrUpdate(detail);
							}
							this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent", "");
							return "SUCCESS";
						}
						//调整申报人员的状态
						for (int i = 0,n=tfQualDeclaraPilotList.size(); i <n ; i++) {
							tfQualDeclaraPilotList.get(i).setState("GSZY");      
							tfQualDeclaraPilotList.get(i).setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
							tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("GsZhuanYuan", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
						//保存待办需要的机构及人员
						map.put("FromOrgName", getUserOrg().getName());
						map.put("FromOrgUser", getUser().getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
						this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		
		
		
		
		@JSON(serialize = false)
		public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
			return tfQuaApplyJbpmBS;
		}

		public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
			this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
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

		public Message getMessage() {
			return message;
		}

		public void setMessage(Message message) {
			this.message = message;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
			return tfQualDeclaraPilotStayBS;
		}

		public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
			this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
		}
		@JSON(serialize = false)
		public static ProcessBase getProcessBase() {
			return processBase;
		}

		public static void setProcessBase(ProcessBase processBase) {
			TfQuaApplyEnglishKsyAction.processBase = processBase;
		}
		@JSON(serialize = false)
		public IProcesshistoryBS getProcesshistoryBS() {
			return processhistoryBS;
		}

		public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
			this.processhistoryBS = processhistoryBS;
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
		@JSON(serialize = false)
		public String getTaskId() {
			return taskId;
		}

		public void setTaskId(String taskId) {
			this.taskId = taskId;
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
