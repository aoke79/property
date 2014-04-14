package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualPilotAppinfoTeacher;
import com.sms.training.qualification.bean.TfQualPilotEducation;
import com.sms.training.qualification.bean.TfQualPilotExperience;
import com.sms.training.qualification.business.ITfQualTeacherLicenseAndGradeBS;

@Service("tfQualTeacherLicenseAndGradeBS")
public class TfQualTeacherLicenseAndGradeBS extends BaseBS implements ITfQualTeacherLicenseAndGradeBS {
	
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
	public TfQualPilotAppinfoTeacher findAppInfo(String detailsid) {
		return this.findUniqueBySingleQuery("TfQualPilotAppinfoTeacher", "tfQualDeclaraPilot.detailsid", detailsid);
	}
}
