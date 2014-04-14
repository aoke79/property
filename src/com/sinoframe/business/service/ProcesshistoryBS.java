package com.sinoframe.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Processhistory;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.jbpm4.ProcessBase;


@Service("processhistoryBS")
public class ProcesshistoryBS extends BaseBS implements IProcesshistoryBS {

	@Override
	public Processhistory findProcesshistoryByTaskId(String taskId) {
          List<Processhistory> processhistoryList = this.findPageByQuery("from Processhistory where taskid="+taskId);
          if(processhistoryList!=null && processhistoryList.size()>0){
        	  return processhistoryList.get(0);
          }
          return null;
	}

	@Override
	public void saveProcessHistory(String taskId, CmUser user,
			SysOrganization sysOrganization) {
		ProcessBase processBase=new ProcessBase();
		Processhistory processhistory=this.findProcesshistoryByTaskId(taskId);
		if (processhistory==null) {
			processhistory=new Processhistory();
		}
		//存储任务ID
		processhistory.setTaskid(Long.parseLong(taskId));
	    //存储处理人
		processhistory.setCmUser(user);
		//存储处理机构
		processhistory.setSysOrganization(sysOrganization);
		
		//存储实例ID
		processhistory.setPid(processBase.getProcessInstanceId(taskId));
		
		//processhistory.setTaskName(taskName);
		
		this.saveOrUpdate(processhistory);
		
		
	}

	@Override
	public void saveProcessHistory(String taskId, CmUser user,
			SysOrganization sysOrganization, String reason) {
		ProcessBase processBase=new ProcessBase();
		Processhistory processhistory=this.findProcesshistoryByTaskId(taskId);
		if (processhistory==null) {
			processhistory=new Processhistory();
		}
		//存储任务ID
		processhistory.setTaskid(Long.parseLong(taskId));
	    //存储处理人
		processhistory.setCmUser(user);
		//存储处理机构
		processhistory.setSysOrganization(sysOrganization);
		//存储实例ID
		processhistory.setPid(processBase.getProcessInstanceId(taskId));
		processhistory.setReason(reason);
		//processhistory.setTaskName(taskName);
		this.saveOrUpdate(processhistory);
		
	}
	
	
	
}
