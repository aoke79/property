package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQualPilotLicenceBS;
@Service("tfQualPilotLicenceBS")
public class TfQualPilotLicenceBS  extends BaseBS implements ITfQualPilotLicenceBS{

	public List<TfQualPilotLicence> getQualPilotLicenceById(String pilotId,String traId)
	{
		String Hql = "  from TfQualPilotLicence info where info.pilotid ='"
					+ pilotId + "' and info.atrid='"+traId+"'" ;
		return this.findPageByQuery(Hql);
	}
	public int getCountQualPilotLicenceById(String pilotId,String traId)
	{
		String Hql = "  from TfQualPilotLicence info where info.pilotid ='"
					+ pilotId + "' and info.atrid='"+traId+"'" ;
		if( this.findPageByQuery(Hql).size()>0)
		{
			return this.findPageByQuery(Hql).size();
		}else
		{
			return -1 ;
		}
	}
	@Override
	public List<TfQualPilotLicence> getLicencesByIdAndStatus(String pilotId, String traId) {
		String Hql = "  from TfQualPilotLicence info where info.pilotid ='"
				+ pilotId + "' and info.atrid='"+traId+"' and info.plcstus = '0'" ;
		return this.findPageByQuery(Hql);
	}
	@Override
	public List<TfQualPilotTechrecord> getTechrecordsByIdAndStatus(String pilotId, String atrId) {
		String Hql = "  from TfQualPilotTechrecord rcd where rcd.cmPeople.id ='"
				+ pilotId + "' and rcd.baseAirplantype.id='"+atrId+"' and rcd.ptrcurrent = '0'" ;
		return this.findPageByQuery(Hql);
	}
	
}
