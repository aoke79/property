package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualPilotAppinforOne;
import com.sms.training.qualification.bean.TfQualPilotAppinforTwo;
import com.sms.training.qualification.bean.TfQualPilotAppinformation;
import com.sms.training.qualification.bean.TfQualPilotEducation;
import com.sms.training.qualification.bean.TfQualPilotExperience;
/**
 * 执照等级申请表 service
 * @author licumn
 *
 */
public interface ITfQualPilotLicenseRateAppBS extends IService{
	
	/**
	 * 根据飞行员id查找飞行员学习简历
	 * @param id 飞行员id
	 * @return
	 */
	List<TfQualPilotEducation> findEducationList(String id);

	/**
	 * 根据飞行员id查找飞行员工作经历
	 * @param id 飞行员id
	 * @return
	 */
	List<TfQualPilotExperience> findExperienceList(String id);
	
	/**
	 * 根据申报人员明细id查找对应的申请信息
	 * @param detailsid 申报人员明细id
	 * @return 
	 */
	TfQualPilotAppinformation findAppInfo(String detailsid);
	
	/**
	 * 根据申报人员明细id查找对应的驾驶员飞行时间记录 1列表
	 * @param detailsid 申报人员明细id
	 * @return
	 */
	List<TfQualPilotAppinforOne> findPilotTimeList1(String detailsid);
	
	/**
	 * 根据申报人员明细id查找对应的驾驶员飞行时间记录 2列表
	 * @param detailsid
	 * @return
	 */
	List<TfQualPilotAppinforTwo> findPilotTimeList2(String detailsid);
	
	/**
	 * 查找机型列表 
	 * @return
	 */
	List<BaseAirPlanType> findAirplanList();
	
	/**
	 * 根据申报人员明细id和最后修改人  查找对应的 民用航空器驾驶员执照申请表
	 * @param detailId 申报人员明细id
	 * @param modifier 最后修改人
	 * @return 民用航空器驾驶员执照申请表
	 */
	public TfQualPilotAppinformation findByDetailIdAndModifier(String detailId,String modifier);

}
