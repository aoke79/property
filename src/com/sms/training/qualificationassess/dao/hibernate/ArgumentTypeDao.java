package com.sms.training.qualificationassess.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualificationassess.dao.IArgumentTypeDao;

@Repository("ArgumentTypeDao")
@Transactional
public class ArgumentTypeDao extends PublicDao implements IArgumentTypeDao {
    
}
