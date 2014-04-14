package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysCountryDao;
import com.sinoframe.dao.ISysLoginInfoDao;

@Repository("sysCountryDao")
@Transactional
public class SysCountryDao extends PublicDao implements
		ISysCountryDao {

}
