package com.sms.training.qualification.business;

import java.util.List;

import com.sms.security.basicdata.bean.BaseAirplanType;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;

public interface ITfQualPilotTechrecordBS {

	public List<TfQualPilotTechrecord> getQualPilotTechrecordByptgradeId(String pilotId,String traId,String ptgradeId);
	public int getCountQualPilotTechrecordById(String pilotId,String atrId,String ptgradeId);
	public List<BaseAirplanType> getBaseAirplanTypeByAtrid(String atrid);
	public List<TfQualPilotTechrecord> getQualPilotTechrecordById(String pilotId,String traId);
}
