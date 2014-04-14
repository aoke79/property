package com.sms.training.qualificationassess.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualificationassess.dao.ICopilotTurnDao;

@Repository("CopilotTurnDao")
@Transactional
public class CopilotTurnDao extends PublicDao implements ICopilotTurnDao {
    
}
