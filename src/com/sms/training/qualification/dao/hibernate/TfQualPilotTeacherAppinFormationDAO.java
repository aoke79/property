package com.sms.training.qualification.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualPilotTeacherAppinFormationDAO;
@Repository("TfQualPilotTeacherAppinFormationDAO")
@Transactional
public class TfQualPilotTeacherAppinFormationDAO extends PublicDao implements ITfQualPilotTeacherAppinFormationDAO{

}
