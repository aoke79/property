package com.sms.training.qualification.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualDeclarSealDAO;

@Repository("tfQualDeclarSealDAO")
@Transactional
public class TfQualDeclarSealDAO extends PublicDao implements ITfQualDeclarSealDAO{

}
