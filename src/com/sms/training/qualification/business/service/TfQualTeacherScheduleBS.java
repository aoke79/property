package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualTeacherSchedule;
import com.sms.training.qualification.business.ITfQualTeacherScheduleBS;
@Service("tfQualTeacherScheduleBS")
public class TfQualTeacherScheduleBS extends BaseBS implements ITfQualTeacherScheduleBS {

	@Override
	public List<TfQualTeacherSchedule> findByPilotId(String pilotid) {
		
		String hql="from TfQualTeacherSchedule t where 1=1 and t.pilotid='"+pilotid+"'";
		return this.findPageByQuery(hql);
	}
   
}
