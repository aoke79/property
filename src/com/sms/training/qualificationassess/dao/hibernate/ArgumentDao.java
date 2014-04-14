package com.sms.training.qualificationassess.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualificationassess.dao.IArgumentDao;

@Repository("ArgumentDao")
@Transactional
public class ArgumentDao extends PublicDao implements IArgumentDao {
    
}
