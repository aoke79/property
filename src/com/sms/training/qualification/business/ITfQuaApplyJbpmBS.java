package com.sms.training.qualification.business;

import java.util.List;
import java.util.Map;

import org.jbpm.api.task.Task;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.IService;

public interface ITfQuaApplyJbpmBS extends IService{
	/**
	 * 根据流程ID 获得当前taskID
	 * @param processInstanceId
	 * @return
	 */
	public String getTaskIdByPiID(String processInstanceId);
	/**
	 * 根据流程ID 获得当前task名称
	 * @param processInstanceId
	 * @return
	 */
	public String getTaskNameByPiID(String subProcessid);
	/**
	 * 根据流程ID 和当前角色获得当前分支流程taskID
	 * @param processInstanceId
	 * @param orgRole
	 * @return
	 */
	public String getForkTaskId(String processInstanceId,String orgRole);
	/**
	 * 发布流程
	 * @param user 当前用户
	 * @param streamPath 流程定义的路径
	 * @return
	 */
	public String deploymentProcessDefinition(CmUser user,String streamPath);
	/**
	 * 开启流程
	 * @param user用户
	 * @param sysOrganization机构
	 * @param pdId1
	 * @param pdId2
	 * @param year
	 * @return
	 */
	public String startProcessEnTrainEstimateByUser(CmUser user,SysOrganization sysOrganization, String pdId1,String pdId2, String year);
	/**
	 * 根据流程ＩＤ开启流程实例
	 * @param user
	 * @param pdId
	 * @param sysOrganization
	 * @return
	 */
	public String startProcessByPid(String pdId,Map<String, Object> map) ;
	/**
	 * 将流程向下执行
	 * @param id
	 * @param idType
	 * @param nextName
	 * @param map
	 * @return
	 */
	public String completeProcessById(String id,String idType,String nextName,Map<String,Object> map);
	
	public List<SysRole> getSessionRoleList(CmUser cmUser) ;
	
	public void filter(List<Task> taskList, Map<String, String> taskplanMap,
			Map<String, String> deptsList,SysOrganization sysOrganization,String subGroupId) ;
	public void dealTaskFrom(List<Task> taskList, Map<String, String> namesMap,
			Map<String, String> orgsNameMap, SysOrganization sysOrganization,
			Map<String, String> deptsList, int flg);
	public void isReject(List<Task> taskList, Map<String, String> isRejectMap,
			Map<String, String> reasonMap);
	/**
	 * 根据类型过滤资质待办
	 * @param taskList 待办列表
	 * @param subGroupId 资质类型分类（小类）
	 */
	void filter(List<Task> taskList, String subGroupId);
	
	/**
	 * 发布资质流程
	 * @param streamPath 流程定义的路径
	 * @return
	 */
	public String deployProcessDefinition(String streamPath);
	
	/**
	 * 根据orgRole与资质类型分组子类查询用户待办信息
	 * @param orgRole "机构id-角色名";
	 * @param subGroupId 资质类型子类
	 * @return [ 资质申报信息, 任务上报机构, 任务上报人, 任务上报时间, 任务处理form Url, taskId ]
	 */
	public List<Object> getQualityTaskInfos(String orgRole, String subGroupId);
}
