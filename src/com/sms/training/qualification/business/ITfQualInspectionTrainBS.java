package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualInspectionTrain;

public interface ITfQualInspectionTrainBS extends IService {

	List<TfQualInspectionTrain> findTfQualInspectionTrainList(String detailsId);
	/**
	 * 判断当前登陆者是否填写过记录     驾驶员飞行记录薄中的航空理论学习/复习与模拟机训练、熟练检查情况记录表单
	 * @param detailsId
	 * @param userid
	 * @return
	 */
	public int findTfQualInspectionTrainCount(String detailsId,String userid);

}
