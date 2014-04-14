package com.sms.training.qualification.business.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualPilotFlyrecordbook;
import com.sms.training.qualification.business.ITfQualPilotFlyrecordbookBS;

@Service("tfQualPilotFlyrecordbookBS")
public class TfQualPilotFlyrecordbookBS extends BaseBS implements ITfQualPilotFlyrecordbookBS{

	/**
	 * 根据申报人员明细id 查找对应的 飞行经历记录list
	 * @param detailId 申报人员明细id
	 * @param sysPageInfo 分页信息
	 * @param orderByInfo 排序信息
	 * @param query 查询条件
	 * 
	 * @return 飞行经历记录list
	 */
	@Override
	public List<TfQualPilotFlyrecordbook> listRecordsByDetailId(String detailId, SysPageInfo sysPageInfo, SysOrderByInfo orderByInfo, HashMap<String, String> query) {
		String hql="from TfQualPilotFlyrecordbook rb where rb.tfQualDeclaraPilot.detailsid='"+detailId+"' ";
		sysPageInfo.setMaxCount(listSize(detailId));
		orderByInfo.setOrderColumn("recordDate");
		return this.findPageByQuery(sysPageInfo, hql, query, orderByInfo);
	}
	
	/**
	 * 查找相关记录的最大条数
	 * @param detailId
	 * @return sysPageInfo.maxCount
	 */
	public long listSize(String detailId){
		String hql="select count(*) from TfQualPilotFlyrecordbook rb where rb.tfQualDeclaraPilot.detailsid='"+detailId+"' ";
		return this.getCountByHQL(hql);
	}
	/**
	 * 根据detailId,creator查找当前飞行记录
	 * @param detailId
	 * * @param creator
	 * @return TfQualPilotFlyrecordbook
	 */
	@Override
	public TfQualPilotFlyrecordbook findByDetailIdAndCreator(String detailId,String creator) {
		String hql = "from TfQualPilotFlyrecordbook c where c.tfQualDeclaraPilot.detailsid='"
			+ detailId + "' and creater='" + creator + "'";
	List<TfQualPilotFlyrecordbook> list = findPageByQuery(hql);
	if (list != null && list.size() != 0) {
		return list.get(0);
	}
	return null;	
	}
}
