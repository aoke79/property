package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualPilotAppinforOne;
import com.sms.training.qualification.bean.TfQualPilotAppinforTwo;
import com.sms.training.qualification.bean.TfQualPilotAppinformation;
import com.sms.training.qualification.bean.TfQualPilotEducation;
import com.sms.training.qualification.bean.TfQualPilotExperience;
import com.sms.training.qualification.business.ITfQualPilotLicenseRateAppBS;
@Service("tfQualPilotLicenseRateAppBS")
public class TfQualPilotLicenseRateAppBS extends BaseBS implements ITfQualPilotLicenseRateAppBS{
	
	/**
	 * 根据飞行员id查找飞行员学习简历
	 * @param id 飞行员id
	 * @return
	 */
	@Override
	public List<TfQualPilotEducation> findEducationList(String id) {
		String hql=" from TfQualPilotEducation edu where edu.cmPeople.id='"+ id +"' order by edu.startdt ";
		return this.findPageByQuery(hql);
	}

	/**
	 * 根据飞行员id查找飞行员工作经历
	 * @param id 飞行员id
	 * @return
	 */
	@Override
	public List<TfQualPilotExperience> findExperienceList(String id) {
		String hql=" from TfQualPilotExperience ex where ex.cmPeople.id='"+ id +"' order by ex.startdt ";
		return this.findPageByQuery(hql);
	}

	/**
	 * 根据申报人员明细id查找对应的申请信息
	 * @param detailsid 申报人员明细id
	 * @return
	 */
	@Override
	public TfQualPilotAppinformation findAppInfo(String detailsid) {
		return this.findUniqueBySingleQuery("TfQualPilotAppinformation", "tfQualDeclaraPilot.detailsid", detailsid);
	}

	/**
	 * 根据申报人员明细id查找对应的驾驶员飞行时间记录 1列表
	 * @param detailsid 申报人员明细id
	 * @return
	 */
	@Override
	public List<TfQualPilotAppinforOne> findPilotTimeList1(String detailsid) {
		String hql=" from TfQualPilotAppinforOne info1 where info1.tfQualDeclaraPilot.detailsid='"+ detailsid +"' order by info1.startdt ";
		return this.findPageByQuery(hql);
	}

	/**
	 * 根据申报人员明细id查找对应的驾驶员飞行时间记录 2列表
	 * @param detailsid
	 * @return
	 */
	@Override
	public List<TfQualPilotAppinforTwo> findPilotTimeList2(String detailsid) {
		String hql=" from TfQualPilotAppinforTwo info2 where info2.tfQualDeclaraPilot.detailsid='"+ detailsid +"' ";
		return this.findPageByQuery(hql);
	}

	/**
	 * 查找机型列表 
	 * @return
	 */
	@Override
	public List<BaseAirPlanType> findAirplanList() {//select * from base_airplantype a where a.atrkind='a'
		String hql=" from BaseAirPlanType a where a.atrkind='a' ";
		return this.findPageByQuery(hql);
	}

	/**
	 * 根据申报人员明细id和最后修改人  查找对应的 民用航空器驾驶员执照申请表
	 * @param detailId 申报人员明细id
	 * @param modifier 最后修改人
	 * @return 民用航空器驾驶员执照申请表
	 */
	@Override
	public TfQualPilotAppinformation findByDetailIdAndModifier(String detailId,String modifier) {
		String hql = "from TfQualPilotAppinformation c where c.tfQualDeclaraPilot.detailsid='"
			+ detailId + "' and modifier='" + modifier + "'";
	List<TfQualPilotAppinformation> list = findPageByQuery(hql);
	if (list != null && list.size() != 0) {
		return list.get(0);
	}
		return null;
	}

}
