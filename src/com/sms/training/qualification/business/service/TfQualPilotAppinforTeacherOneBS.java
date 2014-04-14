package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherOne;
import com.sms.training.qualification.business.ITfQualPilotAppinforTeacherOneBS;
@Service("tfQualPilotAppinforTeacherOneBS")
public class TfQualPilotAppinforTeacherOneBS extends BaseBS implements ITfQualPilotAppinforTeacherOneBS{

	@Override
	public List<TfQualPilotAppinforTeacherOne> findPageByDetailId(
			SysPageInfo sysPageInfo,String detailId) {
		String hql = "from TfQualPilotAppinforTeacherOne p where 1=1 and p.tfQualDeclaraPilot.detailsid='"+detailId+"'" ;
		List<TfQualPilotAppinforTeacherOne> TfQualPilotAppinforTeacherOnes = this.findPageByQuery(sysPageInfo, hql);
		if(null != TfQualPilotAppinforTeacherOnes && TfQualPilotAppinforTeacherOnes.size()>0)
			return TfQualPilotAppinforTeacherOnes;
		else
			return null;
	}

	@Override
	public int getCountByDetailId(String detailId) {
		String counthql = "from TfQualPilotAppinforTeacherOne p where 1=1 and p.tfQualDeclaraPilot.detailsid='"+detailId+"'" ;
		//设置最大条数
		return this.getCountByHql(counthql);
	}

	@Override
	public List<BaseAirPlanType> findAirplanList() {
		String hql=" from BaseAirPlanType a where a.atrkind='a' ";
		return this.findPageByQuery(hql);
	}

}
