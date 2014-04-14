package com.sms.training.qualification.business.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualBaseTypeGroup;
import com.sms.training.qualification.business.ITfQualStandardValuesBS;

@Service("tfQualStandardValuesBS")
public class TfQualStandardValuesBS extends BaseBS implements ITfQualStandardValuesBS {

	@Override
	public List<TfQualBaseType> getTfQualBaseTypeList(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo) {
		String hql =" from TfQualBaseType where 1=1 ";
		String countHql ="select count(*) from TfQualBaseType where 1=1";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		return findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	}

	@Override
	public <T> List<T> getSelectList(String entityClass) {
		String hql = "from "+entityClass;
		return this.findPageByQuery(hql);
	}

	
	@Override
	public void saveTfQualBaseAccessconditions(TfQualBaseAccessconditions tfQualBaseAccessconditions) {
		 if(tfQualBaseAccessconditions.getSvprevlevelgrade()!= null){
			 tfQualBaseAccessconditions.setSvprevlevelgrade(tfQualBaseAccessconditions.getSvprevlevelgrade().replace(" ", ""));
		 }else{
			 tfQualBaseAccessconditions.setSvprevlevelgrade("");
		 }
		if(tfQualBaseAccessconditions.getSvconditionsid() != null && !tfQualBaseAccessconditions.getSvconditionsid().equals("")){
//			this.update(tfQualBaseAccessconditions, tfQualBaseAccessconditions.getSvconditionsid());
			this.saveOrUpdate(tfQualBaseAccessconditions);
		}else{
			this.save(tfQualBaseAccessconditions);
			
		}
	}

	@Override
	public List<TfQualBaseAccessconditions> getTfQualBaseAccessconditionsList(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo) {
		String hql =" from TfQualBaseAccessconditions where 1=1 ";
		String countHql ="select count(*) from TfQualBaseAccessconditions where 1=1";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		return findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	}
	

}
