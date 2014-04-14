package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.*;

public interface ITfQuaApplyMjBS extends IService{

	List<TfQualBaseType> queryByQtgroupId(String qtgroupid, String originalgrade);

	List<TfQualDeclaraPilotStay> findAllTfQualDeclaraPilotStay(String typeid);

	List<TfQualDeclaraPilot> getDeclarPilotByInfoId(String infoId);

}
