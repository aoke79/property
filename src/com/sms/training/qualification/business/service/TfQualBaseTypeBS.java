package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;

@Service("tfQualBaseTypeBS")
public class TfQualBaseTypeBS extends BaseBS implements ITfQualBaseTypeBS {

	@Override
	public List<TfQualBaseType> queryByTypeId(String typeId) {
		String hql ="from TfQualBaseType t where t.typeid='"+typeId+"'" ;
		return this.findPageByQuery(hql);
	}

	@Override
	public List<TfQualBaseType> queryByQtgroupId(String qtgroupid,String originalgrade) {
		String hql="from TfQualBaseType t where t.tfQualBaseTypeGroup.qtgroupid='"+qtgroupid+"' and t.originalgrade.ptGradeId='"+originalgrade+"'";
		return this.findPageByQuery(hql);
	}

	public List<TfQualBaseType> getListByQtgroupid(String qtgroupid )
	{
		String hql=" from TfQualBaseType t where  t.tfQualBaseTypeGroup.qtgroupid='"+qtgroupid+"'";
		return this.findPageByQuery(hql);
	}

	@Override
	public List<String> getTypeIdsByGroupId(String groupId) {
		String hql="select t.typeid from TfQualBaseType t where  t.tfQualBaseTypeGroup.qtgroupid='"+groupId+"'";
		return this.findPageByQuery(hql);
	}
	
	@Override
	public List<TfQualBaseType> getListBySubGroupId(String subGroupId) {
		String hql=" from TfQualBaseType t where  t.tfQualBaseTgroup.typegroupid = '"+subGroupId+"'";
		return this.findPageByQuery(hql);
	}


//	public TfQualBaseType queryByTypeId(String typeId){
//		return this.findById(TfQualBaseType.class, typeId);
//	}
	
}


