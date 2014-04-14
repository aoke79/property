package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualDeclarInfo;

public interface ITfQualDeclarInfoBS extends IService {
	public List<TfQualDeclarInfo> getTfQualDeclarInfoBySubPId(String processid)  ;
		
	/**
	 * 通过流程id即：processId 查找资质分类id
	 * @param taskId
	 * @return
	 */
	public List <TfQualDeclarInfo> getTfQualDeclarInfoByPId(String processid);

	public List<TfQualDeclarInfo> getTfQualDeclarInfosByCreator(String userId, String status);

	public void restoreStatus(TfQualDeclarInfo tfQualDeclarInfo);
}
