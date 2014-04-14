package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotAppinfoTeacher;
import com.sms.training.qualification.bean.TfQualPilotAppinformation;
import com.sms.training.qualification.bean.TfQualPilotEducation;
import com.sms.training.qualification.bean.TfQualPilotExperience;

public interface ITfQualTeacherLicenseAndGradeBS extends IService{
	
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
	TfQualPilotAppinfoTeacher findAppInfo(String detailsid);
}
