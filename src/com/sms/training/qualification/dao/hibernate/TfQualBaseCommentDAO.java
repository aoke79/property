package com.sms.training.qualification.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualBaseCommentDAO;
@Repository("tfQualBaseCommentDAO")
@Transactional
public class TfQualBaseCommentDAO extends PublicDao implements ITfQualBaseCommentDAO {

}