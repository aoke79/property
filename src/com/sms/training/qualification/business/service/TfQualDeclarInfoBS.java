package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.business.ITfQualDeclarInfoBS;

@Service("tfQualDeclarInfoBS")
public class TfQualDeclarInfoBS extends BaseBS implements ITfQualDeclarInfoBS {

	@Override
	public List<TfQualDeclarInfo> getTfQualDeclarInfoByPId(String processid) {
		String hql ="from TfQualDeclarInfo t where t.processid='"+processid+"'" ;
		return this.findPageByQuery(hql);
	}

	@Override
	public List<TfQualDeclarInfo> getTfQualDeclarInfoBySubPId(String processid) {
		String hql ="from TfQualDeclarInfo t where t.subProcessid='"+processid+"'" ;
		return this.findPageByQuery(hql);
	}

	@Override
	public List<TfQualDeclarInfo> getTfQualDeclarInfosByCreator(String userId, String status) {
		String hql ="from TfQualDeclarInfo t where t.creator='"+userId+"' and t.status='"+status+"' order by t.createdt asc " ;
		return this.findPageByQuery(hql);
	}

	@Override
	public void restoreStatus(TfQualDeclarInfo tfQualDeclarInfo) {
		String hql="update TfQualDeclaraPilotStay st set st.status=null " +
				"     where   st.cmPeople.id in ( " +
				"        select dp.cmPeople.id from  TfQualDeclaraPilot dp where dp.tfQualDeclarInfo.declaredinfoid='"+tfQualDeclarInfo.getDeclaredinfoid()+"'" +
				"    ) and st.tfQualBaseType.typeid='"+tfQualDeclarInfo.getTfQualBaseType().getTypeid()+"' and st.status is not null";
		this.executeUpdate(hql);
		hql="delete from TfQualDeclaraPilot t where t.tfQualDeclarInfo.declaredinfoid='"+tfQualDeclarInfo.getDeclaredinfoid()+"' ";
		this.executeUpdate(hql);
	}
}
