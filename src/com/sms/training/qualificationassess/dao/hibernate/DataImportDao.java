package com.sms.training.qualificationassess.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualificationassess.dao.IDataImportDao;

@Repository("DataImportDao")
@Transactional
public class DataImportDao extends PublicDao implements IDataImportDao {
    
}
