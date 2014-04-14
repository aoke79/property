package com.sinoframe.business;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Processhistory;
import com.sinoframe.bean.SysOrganization;

public interface IProcesshistoryBS extends IService{

	Processhistory findProcesshistoryByTaskId(String taskId);
    /**
     * 存储流程历史记录
     * @param taskId 任务ID
     * @param user   当前用户
     * @param sysOrganization 当前机构
     */
	void saveProcessHistory(String taskId, CmUser user,
			SysOrganization sysOrganization);
	
	  /**
     * 存储流程历史记录
     * @param taskId 任务ID
     * @param user   当前用户
     * @param sysOrganization 当前机构
     * @param reason 驳回理由 
     */
	void saveProcessHistory(String taskId, CmUser user,
			SysOrganization sysOrganization,String reason);
	
  	
}
