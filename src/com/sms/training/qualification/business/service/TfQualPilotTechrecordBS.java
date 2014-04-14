package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.security.basicdata.bean.BaseAirplanType;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQualPilotTechrecordBS;
@Service("tfQualPilotTechrecordBS")
public class TfQualPilotTechrecordBS extends BaseBS implements ITfQualPilotTechrecordBS{

	public List<TfQualPilotTechrecord> getQualPilotTechrecordByptgradeId(String pilotId,String traId,String ptgradeId)
	{
		String Hql = "  from TfQualPilotTechrecord info where info.cmPeople.id ='"
					+ pilotId + "' and info.baseAirplantype.atrid='"+traId+"' and info.ptgradeid='"+ptgradeId+"'" ;
		return this.findPageByQuery(Hql);
	}
	public List<TfQualPilotTechrecord> getQualPilotTechrecordById(String pilotId,String traId)
	{
		String Hql = "  from TfQualPilotTechrecord info where info.cmPeople.id ='"
					+ pilotId + "' and info.baseAirplantype.atrid='"+traId+"'" ;
		return this.findPageByQuery(Hql);
	}
	public int getCountQualPilotTechrecordById(String pilotId,String atrId,String ptgradeId)
	{
		String Hql = "  from TfQualPilotTechrecord info where info.cmPeople.id ='"
					+ pilotId + "' and info.baseAirplantype.atrid='"+atrId+"' and info.ptgradeid='"+ptgradeId+"'" ;
		if( this.findPageByQuery(Hql).size()>0 )
		{
			return this.findPageByQuery(Hql).size();
		}else
		{
			return -1 ;
		}
	}
	
	public List<BaseAirplanType> getBaseAirplanTypeByAtrid(String atrid)
	{
		String Hql = "  from BaseAirplanType info where info.atrid ='"
				+ atrid+  "'" ;
		return this.findPageByQuery(Hql);
	}
}
