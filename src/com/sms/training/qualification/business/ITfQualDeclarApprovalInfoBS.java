package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;

public interface ITfQualDeclarApprovalInfoBS extends IService{
	public List<TfQualDeclarApprovalinfo> findApprovalInfoByProcessId(String ProcessId);
	public List<TfQualBaseSignature> findSignByUserId(String userId);
	public List<TfQualDeclarApprovalinfo> test(String processId,
			ProcessBase processBase, String taskId,
			List<TfQualDeclarApprovalinfo> infoList,
			TfQualDeclarApprovalinfo info, String fgsZhuanYuanSpYj) ;
	/**
	 * 查询出当前登陆者 所填写的意见记录
	 * @param ProcessId  流程id
	 * @param userId
	 */
	public TfQualDeclarApprovalinfo findApprovalInfoByProcessIdAndUserid(String ProcessId,String userId);
	
}
