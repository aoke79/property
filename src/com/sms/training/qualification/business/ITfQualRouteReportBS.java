package com.sms.training.qualification.business;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualRouteReport;

public interface ITfQualRouteReportBS extends IService{
	
	TfQualRouteReport getPageByDetailsid(String detailsid);
}
