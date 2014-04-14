package com.sms.training.qualification.business.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.business.ITfQualBaseConditionsBS;
import com.sms.training.qualification.dao.ITfQualBaseConditionsDAO;

@Service("tfQualBaseConditionsBS")
public class TfQualBaseConditionsBS extends BaseBS implements ITfQualBaseConditionsBS {
	private ITfQualBaseConditionsDAO tfQualBaseConditionsDAO;

	
	
	@Override
	public List<TfQualBaseConditions> findAllByQuery(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo) {
		String hql =" from TfQualBaseConditions where 1=1 ";
		String countHql ="select count(*) from TfQualBaseConditions where 1=1";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		return findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	}
	
	@Override
	public void saveAndUpdate(TfQualBaseConditions tfQualBaseConditions) {
		String id = tfQualBaseConditions.getConditionid();
		if(id != null && !id.equals("")){
			this.update(tfQualBaseConditions, id);
		}else{
			this.save(tfQualBaseConditions);
		}
	}

	@Override
	public void BatchDeleteByIds(TfQualBaseConditions tfQualBaseConditions,String ids) {
		this.deleteByIds(TfQualBaseConditions.class, ids);
	}

	public ITfQualBaseConditionsDAO getTfQualBaseConditionsDAO() {
		return tfQualBaseConditionsDAO;
	}
	@Resource
	public void setTfQualBaseConditionsDAO(ITfQualBaseConditionsDAO tfQualBaseConditionsDAO) {
		this.tfQualBaseConditionsDAO = tfQualBaseConditionsDAO;
	}

}
