package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualPilotDocuments;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;
@Service("tfQualPilotDocumentsBS")
public class TfQualPilotDocumentsBS extends BaseBS implements ITfQualPilotDocumentsBS{

	@Override
	public int findDocumentsByDetailId(String detailsid, String userid) {
		String hql="select count(docid) from TfQualPilotDocuments t where t.tfQualDeclaraPilot.detailsid='"+detailsid+"' and t.cmuser.userId= '" + userid + "' order by t.creationdt";
		return this.getCountByHql(hql);
	}

	@Override
	public List<TfQualPilotDocuments> findDocumentsByDetailId(String detailsid) {
		String hql=" from TfQualPilotDocuments t where t.tfQualDeclaraPilot.detailsid='"+detailsid+"' order by t.creationdt";
		
		return this.findPageByQuery(hql);
	}

}
