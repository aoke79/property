package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;

public interface ITfQualPilotLicenceBS extends IService{

	public List<TfQualPilotLicence> getQualPilotLicenceById(String pilotId,String traId);
	public int getCountQualPilotLicenceById(String pilotId,String traId);
	public List<TfQualPilotLicence> getLicencesByIdAndStatus(String pilotId,String traId);
	public List<TfQualPilotTechrecord> getTechrecordsByIdAndStatus(String pilotId,String atrId);
}
