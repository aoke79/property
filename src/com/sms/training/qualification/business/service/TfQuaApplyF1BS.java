package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.ITfQuaApplyF1BS;

@Service("tfQuaApplyF1BS")
public class TfQuaApplyF1BS extends BaseBS implements ITfQuaApplyF1BS{
//获取资质类型
	@Override
	public List<TfQualBaseType> queryByQtgroupId(String qtgroupid,String originalgrade) {
		String hql="from TfQualBaseType t where t.tfQualBaseTypeGroup.qtgroupid='"+qtgroupid+"' and t.originalgrade.ptGradeId='"+originalgrade+"'";
		return this.findPageByQuery(hql);
	}
//获取待申报人员信息
	@Override
	public List<TfQualDeclaraPilotStay> findAllTfQualDeclaraPilotStay( String typeid) {
		String hql ="from TfQualDeclaraPilotStay  where status is null and tfQualBaseType.typeid='"+typeid+"' ";
		return this.findPageByQuery(hql);
	}
//获取申报人员明细	
	@Override
	public List<TfQualDeclaraPilot> getDeclarPilotByInfoId(String infoId) {
		String hql ="from TfQualDeclaraPilot t where t.tfQualDeclarInfo.declaredinfoid='"+infoId+"'" ;
		return this.findPageByQuery(hql);
	}

}
