package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherTwo;
import com.sms.training.qualification.business.ITfQualPilotAppinforTeacherTwoBS;
@Service("tfQualPilotAppinforTeacherTwoBS")
public class TfQualPilotAppinforTeacherTwoBS extends BaseBS implements ITfQualPilotAppinforTeacherTwoBS{

	@Override
	public List<TfQualPilotAppinforTeacherTwo> findPageByDetailId(String detailId) {
		String hql = "from TfQualPilotAppinforTeacherTwo p where 1=1 and p.tfQualDeclaraPilot.detailsid='"+detailId+"'" ;
		List<TfQualPilotAppinforTeacherTwo> TfQualPilotAppinforTeacherTwos = this.findPageByQuery(hql);
		if(null != TfQualPilotAppinforTeacherTwos && TfQualPilotAppinforTeacherTwos.size()>0)
			return TfQualPilotAppinforTeacherTwos;
		else
			return null;
	}

	@Override
	public List<BaseAirPlanType> findAirplanList() {
		String hql=" from BaseAirPlanType a where a.atrkind='a' ";
		return this.findPageByQuery(hql);
	}

}
