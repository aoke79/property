package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.*;

public interface ITfQuaApplyJBS extends IService{
	//获取资质类型
	List<TfQualBaseType> queryByQtgroupId(String qtgroupid, String originalgrade);
	//获取待申报人员信息
	List<TfQualDeclaraPilotStay> findAllTfQualDeclaraPilotStay(String typeid);
	//获取申报人员明细
	List<TfQualDeclaraPilot> getDeclarPilotByInfoId(String infoId);

}
