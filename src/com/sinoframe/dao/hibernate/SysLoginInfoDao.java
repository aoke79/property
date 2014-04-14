package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysLoginInfoDao;

@Repository("sysLoginInfoDao")
@Transactional
public class SysLoginInfoDao extends PublicDao implements
		ISysLoginInfoDao {

}
