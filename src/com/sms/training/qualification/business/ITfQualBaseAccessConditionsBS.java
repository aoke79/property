package com.sms.training.qualification.business;

import java.util.List;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQualBaseAccessConditionsBS extends IService {
	//根据“资质类型对象” 查找到查询条件
	public List<TfQualBaseAccessconditions> queryByBaseType(TfQualBaseType tfQualBaseType);
		
}
