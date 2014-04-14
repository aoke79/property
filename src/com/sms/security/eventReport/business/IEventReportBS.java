package com.sms.security.eventReport.business;
import java.util.List;
import java.util.Map;

import com.sinoframe.bean.CmUser;
import com.sinoframe.business.IService;
//import com.sms.security.eventReport.bean.SecEventsecmessage;


public interface IEventReportBS extends IService {
	public String startProcessByUser(String proType,Map<String,Object> map);
	public String completeProcessById(String id,String idType,String nextName,Map<String,Object> map);
	@SuppressWarnings("rawtypes")
	public List getAllTasks(CmUser user);
	@SuppressWarnings("rawtypes")
	public List getAllTasks(String currUserIdentity);
	@SuppressWarnings("rawtypes")
	public List getAllTasksByOrgRole(String orgRole);
	/**
	 * 根据任务Id得到流程实例Map
	 * @param taskId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getVariablesByTaskId(String taskId);
	
	/**
	 * 根据流程实例Id得到流程实例Map
	 * @param processInstanceId
	 * @return
	 */
	public Map<String, Object> getVariablesByInstanceId(String processInstanceId);
	/**
	 * 根据流程实例Id删除流程实例
	 * @param processInstanceId
	 * @return
	 */
	public boolean deleteProcessInstanceByPiId(String processInstanceId);
	/**
	 * 根据业务编号得到 提交/上报 日期从审批表中
	 * @param bsId 业务编号
	 * @return
	 */
	public String getCommentsDate(String bsId);
	
	/**
	 * 开启回复的流程
	 * @param map
	 * @return
	 */
	public String startReplyProcess(Map<String, Object> map);
	
	/**
	 * 重新开启事件
	 * @param secEventsecmessage
	 */
//	public SecEventsecmessage restart(SecEventsecmessage secEventsecmessage);
}
