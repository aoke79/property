package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseTrainingtype;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQuaManagerBS;


@Service("tfQuaManagerBS")
public class TfQuaManagerServiceBS extends BaseBS implements ITfQuaManagerBS{

	@Override
	public List<TfQualBaseType> qualityManager(String hql) {
	
		return this.findPageByQuery(hql);
	}

	/**
	 * 通过资质类型id查找训练分组类型列表
	 */
	@Override
	public List<String> getGroupByBaseType(String qualBaseTypeId) {
		String hql = " select grp.groupid               " +
					 " from TfQualBaseTrainingtype tra, " +
					 " TfQualBaseTraintypegroup grp,    " +
					 " TfQualBaseType tp                " +
					 " where tra.ttgrid = grp.groupid   " +
					 " and tp.tfQualBaseTrainingtype.ttypeseq = tra.ttypeseq " +
					 " and tp.typeid = '" + qualBaseTypeId + "'              ";
		
		return this.findPageByQuery(hql);
	}
	
	/**
	 * 通过资质类型id查找机型列表
	 */
	@Override
	public List<String> getAirTypeByBaseType(String qualBaseTypeId) {
		String hql = " select ba.atrid                  " +
					 " from TfQualBaseTrainingtype tra, " +
					 " BaseAirplanType ba,              " +
					 " TfQualBaseType tp                " +
					 " where tra.ttypetargetatrid = ba.atrid                 " +
					 " and tp.tfQualBaseTrainingtype.ttypeseq = tra.ttypeseq " +
					 " and tp.typeid = '" + qualBaseTypeId + "'              ";
		
		return this.findPageByQuery(hql);
	}
	
	/**
	 * 通过训练分组类型id和机型id查找对应训练类型列表
	 */
	public List<TfQualBaseTrainingtype> getBaseTrainingtypeList(String groupId, String airTypeId) {
		String hql = "";
		
		if(groupId != null && !"".equals(groupId) && airTypeId != null  && !"".equals(airTypeId)) {
			//ttypestus = '1' 表示有效的资质类型
			hql = " select distinct tra                 " +
				  " from TfQualBaseTrainingtype tra,    " +
				  " TfQualBaseTraintypegroup grp,       " +
				  " TfQualBaseType tp                   " +
				  " where tra.ttgrid = grp.groupid      " +
				  " and tp.tfQualBaseTrainingtype.ttypeseq = tra.ttypeseq    " +
				  " and tra.ttypestus = '1'                                  " +
				  " and grp.groupid = '" + groupId + "'                      " +
				  " and tra.ttypetargetatrid = '" + airTypeId + "'           ";
		} else if(groupId != null && !"".equals(groupId)) {
			hql = " select distinct tra                 " +
				  " from TfQualBaseTrainingtype tra,    " +
				  " TfQualBaseTraintypegroup grp        " +
				  " where tra.ttgrid = grp.groupid      " +
				  " and tra.ttypestus = '1'             " +
				  " and grp.groupid = '" + groupId + "' ";
		} else if(airTypeId != null && !"".equals(airTypeId)) {
			hql = "select distinct tra                  " +
				  " from TfQualBaseTrainingtype tra     " +
				  " where tra.ttypestus = '1'           " +
				  " and tra.ttypetargetatrid = '" + airTypeId + "'           ";
		} else {
			hql = "from TfQualBaseTrainingtype t where t.ttypestus = '1'";
		}
		
		return this.findPageByQuery(hql);
	}
}
