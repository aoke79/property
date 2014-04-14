/**
 * Title: BasicWorkflowBS
 * Description: the implement of the interface "IBasicWorkflowBS"
 */

package com.sms.security.eventReport.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.CmUser;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.DateTool;
//import com.sms.security.basicworkflow.dao.IBasicWorkflowDao;
//import com.sms.security.eventReport.bean.SecComments;
//import com.sms.security.eventReport.bean.SecEventsecmessage;
import com.sms.security.eventReport.business.IEventReportBS;

@Service("eventReportBS")
public class EventReportBS extends BaseBS implements IEventReportBS {
	
//	private IBasicWorkflowDao basicWorkflowDao;
//	
//	public IBasicWorkflowDao getBasicWorkflowDao() {
//		return basicWorkflowDao;
//	}
//	@Resource
//	public void setBasicWorkflowDao(IBasicWorkflowDao basicWorkflowDao) {
//		this.basicWorkflowDao = basicWorkflowDao;
//	}

	ProcessBase processBase = new ProcessBase();
	
	/**
	 * 开启回复的流程
	 * @param map
	 * @return
	 */
	public String startReplyProcess(Map<String, Object> map) {
		String streamPath = this.replyPath();
		//流程定义Id
		String pdId = processBase.getProcessDefinitionIdByPath(streamPath);
		//根据流程id启动实例
		String processInstanceId = processBase.putOutProcessInstance(pdId,map);
		return processInstanceId;
	}
	
	/**
	 * 回复流程路径
	 * @return
	 */
	public String replyPath() {
		return "/com/sms/security/qar/process/revert.zip";
	}
	
	@Override
	public String startProcessByUser(String proType,Map<String,Object> map) {
		String streamPath = this.getPathByType(proType);
		//流程id
		String pdId = processBase.getProcessDefinitionIdByPath(streamPath);
		
		//根据流程id启动实例
		String processInstanceId = processBase.putOutProcessInstance(pdId,map);
		return processInstanceId;
	}
	
	public String getPathByType(String orgLevel) {
		//System.out.println("根据用户获取相关path路径："+user.getName());
		if(orgLevel.equals("1")){
			return "/com/sms/security/eventReport/process/companyToCompanyEventReport2.zip";
		}
		if(orgLevel.equals("2")){
			return "/com/sms/security/eventReport/process/eventReportOneToCompany3.zip";
		}
		if(orgLevel.equals("3")){
			return "/com/sms/security/eventReport/process/twoToCompanyEventReport4.zip";
		}
		if(orgLevel.equals("4")){
			return "/com/sms/security/eventReport/process/threeToCompanyEventReport4.zip";
		}
		return "";
	}
	@Override
	public String completeProcessById(String id,String idType,String nextName,Map<String,Object> map) {
		String taskId  =  "";
		if("instance".equals(idType.toLowerCase())){
			taskId = this.processBase.getTaskIdByPiID(id);
		}else if("task".equals(idType.toLowerCase())){
			taskId = id;
		}
		String processInstanceId = this.processBase.getProcessInstanceId(taskId);
		if("撤回".equals(nextName)){
			recallReport(processInstanceId,map);
		}else{
			//根据user权限获取相关权限
			this.processBase.completeTaskById(taskId, nextName, map);
		}
		if("instance".equals(idType.toLowerCase())){
			return processInstanceId;
		}else if("task".equals(idType.toLowerCase())){
			return this.processBase.getTaskIdByPiID(processInstanceId);
		}else{
			return "";
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllTasks(CmUser user) {
		List  taskList = this.processBase.getTaskListByName(user.getLoginName());
		return taskList;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List getAllTasks(String currUserIdentity) {
		List  taskList = this.processBase.getTaskListByName(currUserIdentity);
		return taskList;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List getAllTasksByOrgRole(String orgRole) {
		List  taskList = this.processBase.getTaskListByName(orgRole);
		return taskList;
	}
	private void recallReport(String processInstanceId,Map<String,Object> map) {
		this.processBase.recalls(processInstanceId,map);
		
	}
	/**
	 * 根据任务Id得到流程实例Map
	 * @param taskId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getVariablesByTaskId(String taskId) {
		return processBase.getProcessMapById(taskId);
	}
	
	/**
	 * 根据流程实例Id得到流程实例Map
	 * @param processInstanceId
	 * @return
	 */
	public Map<String, Object> getVariablesByInstanceId(String processInstanceId) {
		return processBase.getVariables(processInstanceId);
	}
	/**
	 * 根据流程实例Id删除流程实例
	 * @param processInstanceId
	 * @return
	 */
	public boolean deleteProcessInstanceByPiId(String processInstanceId){
		return processBase.deleteProcessInstanceByPiId(processInstanceId);
	}
	/**
	 * 根据业务编号得到 提交/上报 日期从审批表中
	 * @param bsId 业务编号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCommentsDate(String bsId){
//		String hql = "from SecComments where commfromid='"+bsId+"' order by commentdate desc";
//		List resultList = this.findPageByQuery(hql);
//		Date date = null;
//		if(resultList!=null && resultList.size()>0){
//			date = ((SecComments)resultList.get(0)).getCommentdate();
//			if(date!=null){
//				return DateTool.getDateTime(date, "yyyy-MM-dd");
//			}
//		}
		return null;
	}
	
	/**
	 * 重新开启事件
	 * @param secEventsecmessage
	 */
//	@Transactional
//	public SecEventsecmessage restart(SecEventsecmessage secEventsecmessage) {
//		SecEventsecmessage currentMessage = this.findById(SecEventsecmessage.class, secEventsecmessage.getSefmid());
//		currentMessage.setSevstus(ConstantList.BUSINESSCODE_SAVE);
//		return currentMessage;
//	}
}
