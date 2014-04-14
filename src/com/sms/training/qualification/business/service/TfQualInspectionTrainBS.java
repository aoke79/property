package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualInspectionTrain;
import com.sms.training.qualification.business.ITfQualInspectionTrainBS;

@Service("tfQualInspectionTrainBS")
public class TfQualInspectionTrainBS extends BaseBS implements ITfQualInspectionTrainBS {

	@Override
	public List<TfQualInspectionTrain> findTfQualInspectionTrainList(String detailsId) {
		String hql=" from TfQualInspectionTrain t where t.detailsId='"+detailsId+"'";
		return this.findPageByQuery(hql);
	}

	@Override
	public int findTfQualInspectionTrainCount(String detailsId, String userid) {
		String hql="select count(trainid) from TfQualInspectionTrain t where t.detailsId='"+detailsId+"' and t.modifier='"+userid+"'";
		return this.getCountByHql(hql);
	}

}
