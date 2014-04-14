package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.jbpm.api.task.Task;

import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
//import com.sms.security.eventReport.business.IEventReportBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQuaManagerBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") })
public class TfQuaPilotCalculateAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private List<String> typeGroupList;
	private String groupId;
	private Map<String, Integer> countMap = new HashMap<String, Integer>();
	private ITfQuaManagerBS tfQuaManagerBS;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private final String JSON="json";
	private ProcessBase processBase = new ProcessBase();
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
//	private IEventReportBS eventReportBS;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	private int countOfUpdate = 0;
	private String subGroupId = null;
/*	public String findAllTypeGroups(){
		String hql="select g.qtgroupid from TfQualBaseTypeGroup g";
		typeGroupList=this.tfQuaManagerBS.findPageByQuery(hql);
		return JSON;
	}*/
	
	public String countByGroupId(){
		String hql="select g.typegroupid from TfQualBaseTgroup g where g.tfQualBaseTypeGroup.qtgroupid='"+groupId+"'";
		List<String> subGroupIds=this.tfQuaManagerBS.findPageByQuery(hql);
		SysOrganization userOrg=getUserOrg();
		String orgIds=null;
		int reportRight=isAbleToReport();
		if(reportRight!=0){
			orgIds=tfQuaApplyIcaoBS.getOrgName(userOrg);
		}
		//获取当前用户的所有角色
		List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
		int count = 0;
		for(String subGroupId : subGroupIds){
			count = getCountBySubGroupId(subGroupId, groupId, reportRight, userOrg.getId(), orgIds, roleList);
			countMap.put(subGroupId, count);
		}
		return JSON;
	}
	
	private int getCountBySubGroupId(String subGroupId,String typeGroup, int reportRight, String userOrgId,String orgIds,List<SysRole> roleList){
		int countSum = 0;
		String isT = "";
		try {
			//获取定时器算人的待申报人员，要取对应小类下、本单位的待申报人员
			if( isTheProcessToReport(reportRight,typeGroup) ){
				countSum += this.tfQualDeclaraPilotBS.getCountOfPilotStay(orgIds,subGroupId);
			}
			
			//获取 待办的总人数（申报阶段）
			String orgRole=null;
			for(SysRole role : roleList){
				orgRole = userOrgId+"-"+role.getRoleName();
				countSum += this.tfQualDeclaraPilotBS.getPilotCountOfApply(orgRole, subGroupId);
			}
			
			//下发训练计划阶段  
			countSum += this.tfQualDeclaraPilotBS.getPilotCountOfIssued(subGroupId, userOrgId, roleList);
			
			//训练阶段(审核阶段)的人数
			isT = this.tfQualDeclaraPilotBS.checkIsT(roleList);
			if("T".equals(isT) || "C".equals(isT) || "TC".equals(isT)){
				countSum += this.tfQualDeclaraPilotBS.getPilotCountByC(roleList,subGroupId, userOrgId,getUser(),isT);
			}else{
				countSum += this.tfQualDeclaraPilotBS.getPilotCountOfTraining(subGroupId, userOrgId, roleList);
			}
			
			// 资质更新阶段的人数
			if(hasRightOfUpdate()){//判断有木有资质更新权限，有则统计
				countSum += this.tfQualDeclaraPilotBS.getPilotCount4Update(subGroupId, userOrgId, roleList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSum;
	}
	public String countByStage(){
		//获取当前用户的所有角色
		List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
		// 资质更新阶段的人数
		if(hasRightOfUpdate()){//判断有木有资质更新权限，有则统计
			countOfUpdate = this.tfQualDeclaraPilotBS.getPilotCount4Update(subGroupId, getUserOrg().getId(), roleList);
		}
		return JSON;
	}

	private int isAbleToReport(){
		@SuppressWarnings("unchecked")
		List<SysOperate> sysOperateList=(List<SysOperate>) ServletActionContext.getRequest().getSession().getAttribute("sysOperateList");
		String optAction=null;
		for(SysOperate opt : sysOperateList){
			optAction = opt.getOperateAction();
			if("Tab-QualityDeclare".equals(optAction)){
				return 1;//公司申报，返回 1
			}else if("Tab-FilialeQualityDeclare".equals(optAction)){
				return 2;//分公司申报，返回 2
			}
		}
		//如果当前用户没有申报权限，返回0
		return 0;
	}
	private boolean isTheProcessToReport(int reportRight,String typeGroup){
		//由公司发起的流程：升级、转机型、……
		String gsStartProcess="QUAL_UP,QUAL_TR";
		//由分公司发起的流程：新雇员、检查员、一般资格、特殊资格……
		String fgsStartProcess="QUAL_NE,QUAL_CT,QUAL_YB,QUAL_SQ";
		if(reportRight==1 && gsStartProcess.contains(typeGroup)){//gs
			return true;
		}else if(reportRight==2 && fgsStartProcess.contains(typeGroup)){//fgs
			return true;
		}
		return false;
	}
	private boolean hasRightOfUpdate(){
		@SuppressWarnings("unchecked")
		List<SysOperate> sysOperateList=(List<SysOperate>) ServletActionContext.getRequest().getSession().getAttribute("sysOperateList");
		//公司飞管专员资质更新权限特有操作
		String gsOpt="Tab-CompanyCommissionerRenewal";
		//分公司飞管专员资质更新权限特有操作
		String fgsOpt="Tab-FilialeCommissionerRenewal";
		for(SysOperate opt : sysOperateList){
			if(gsOpt.equals(opt.getOperateAction()) || fgsOpt.equals(opt.getOperateAction())){
				return true;
			}
		}
		return false;
	}

/*	private  List<Task> getAllTask(String subGroupId,List<SysRole>  roleList, String userOrgId){
		List<Task> taskList = new ArrayList<Task>();
		try {
			//根据机构及角色名称获得待办
			for(int n=0,len=roleList.size();n<len;n++){
				taskList.addAll(processBase.getTaskListByName(userOrgId+"-"+roleList.get(n).getRoleName()));
			}
			//过滤待办，由于此处获取待办只为计算人数，故不需要获取待办信息、不需排序等等，现在用此重载方法替换旧方法，如有新需求日后可以完善。
			tfQuaApplyJbpmBS.filter(taskList, subGroupId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return taskList==null ? new ArrayList<Task>() : taskList;
	}*/
/*	//根据用户ID得到任务
	private List<Task> getTasksByUserId() {
		List<Task> tempList = null;
		String currUserIdentity = this.getUserOrg().getId() + "-" + this.getUser().getUserId();
		try {
			tempList = this.eventReportBS.getAllTasks(currUserIdentity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempList == null){
			tempList = new ArrayList<Task>();
		}
		return tempList;
	}	*/
//=========================================================================================
	@JSON(serialize = true)////must be true !!!
	public List<String> getTypeGroupList() {
		return typeGroupList;
	}
	public void setTypeGroupList(List<String> typeGroupList) {
		this.typeGroupList = typeGroupList;
	}
	@JSON(serialize = true)////must be true !!!
	public Map<String, Integer> getCountMap() {
		return countMap;
	}
	public void setCountMap(Map<String, Integer> countMap) {
		this.countMap = countMap;
	}
	
	@JSON(serialize = false)
	public ITfQuaManagerBS getTfQuaManagerBS() {
		return tfQuaManagerBS;
	}
	public void setTfQuaManagerBS(ITfQuaManagerBS tfQuaManagerBS) {
		this.tfQuaManagerBS = tfQuaManagerBS;
	}
	@JSON(serialize = false)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	@JSON(serialize = false)
	public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
		return tfQuaApplyJbpmBS;
	}
	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
	}
//	@JSON(serialize = false)
//	public IEventReportBS getEventReportBS() {
//		return eventReportBS;
//	}
//	public void setEventReportBS(IEventReportBS eventReportBS) {
//		this.eventReportBS = eventReportBS;
//	}
	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}
	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}

	public int getCountOfUpdate() {
		return countOfUpdate;
	}

	public void setCountOfUpdate(int countOfUpdate) {
		this.countOfUpdate = countOfUpdate;
	}
	
	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	
}
