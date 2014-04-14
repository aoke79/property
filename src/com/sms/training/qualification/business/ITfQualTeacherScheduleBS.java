package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualTeacherSchedule;

public interface ITfQualTeacherScheduleBS extends IService {
   public List<TfQualTeacherSchedule> findByPilotId(String pilotid) ;
}
