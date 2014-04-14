package com.sms.training.qualification.business.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQualBaseAccessConditionsBS;

@Service("tfQualBaseAccessConditionBS")
public class TfQualBaseAccessConditionBS extends BaseBS implements ITfQualBaseAccessConditionsBS {
	
	@Override
	public List<TfQualBaseAccessconditions> queryByBaseType(TfQualBaseType tfQualBaseType) {
		String hql="from TfQualBaseAccessconditions t where t.tfQualBaseType.typeid='"+tfQualBaseType.getTypeid()+"' ";

//		String hql="from TfQualBaseAccessconditions t where t.tfQualBaseType.typeid='8a8a113d37e961ee0137e979773a0007' ";
		
		return  this.findPageByQuery(hql);
	}
	

}
