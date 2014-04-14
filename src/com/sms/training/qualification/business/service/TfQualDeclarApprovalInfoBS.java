package com.sms.training.qualification.business.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;

@Service("tfQualDeclarApprovalInfoBS")
public class TfQualDeclarApprovalInfoBS extends BaseBS  implements ITfQualDeclarApprovalInfoBS {

	public List<TfQualDeclarApprovalinfo> findApprovalInfoByProcessId(
			String ProcessId) {
		String planHql = "  from TfQualDeclarApprovalinfo info where info.pid ='"
				+ ProcessId + "' order by info.signingDate desc " ;
		return this.findPageByQuery(planHql);
	}
	public List<TfQualBaseSignature> findSignByUserId(String userId) {
		String planHql = "  from TfQualBaseSignature info where info.cmuser.userId ='"+userId+"'" ;
		return this.findPageByQuery(planHql);
	}
@Override
	public List<TfQualDeclarApprovalinfo> test(String processId,
			ProcessBase processBase, String taskId,
			List<TfQualDeclarApprovalinfo> infoList,
			TfQualDeclarApprovalinfo info, String fgsZhuanYuanSpYj) {
		processId = processBase.getProcessInstanceId(taskId);
		String fromOrgUserID = processBase.getVariable(taskId, "FromOrgUserID")
				.toString();
		// 审批信息
		if (!fromOrgUserID.equals("") && !processId.equals("")) {
			infoList = this.findApprovalInfoByProcessId(processId);
			if (infoList.size() != 0 && infoList.size() > 0) {
				info = infoList.get(0);
				fgsZhuanYuanSpYj = info.getOpinionInfo();
			}
		}
		// declaredinfoid = processBase.getVariable(taskId, "planInfoId")
		// .toString();
		// planInfoName = processBase.getVariable(taskId, "planInfoName")
		// .toString();
		// 获取此次申报的人员信息
		// if(!declaredinfoid.equals(""))
		// {
		// tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
		// }
		return infoList;
	}
	@Override
	public TfQualDeclarApprovalinfo findApprovalInfoByProcessIdAndUserid(String ProcessId, String userId) {
		String planHql = "  from TfQualDeclarApprovalinfo info where info.pid ='"
				+ ProcessId + "' and info.signingid = '"+userId+"'" ;
		List<TfQualDeclarApprovalinfo> list = findPageByQuery(planHql);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}


}
