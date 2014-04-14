package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotDocuments;

public interface ITfQualPilotDocumentsBS extends IService{

	int findDocumentsByDetailId(String detailsid, String userid);
	
	List<TfQualPilotDocuments> findDocumentsByDetailId(String detailsid);

}
