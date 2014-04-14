package com.sms.training.qualification.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualBaseLessonDao;

@Repository("tfQualBaseLessonDao")
@Transactional
public class TfQualBaseLessonDao extends PublicDao implements ITfQualBaseLessonDao{

}
