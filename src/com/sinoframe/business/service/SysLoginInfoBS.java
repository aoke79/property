package com.sinoframe.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.ISysLoginInfoBS;
import com.sinoframe.dao.ISysLoginInfoDao;

@Service("sysLoginInfoBS")
public class SysLoginInfoBS extends BaseBS implements ISysLoginInfoBS {

	ISysLoginInfoDao sysLoginInfoDao;
	
	@Resource
	public final void setSysLoginInfoDao(ISysLoginInfoDao sysLoginInfoDao) {
		this.sysLoginInfoDao = sysLoginInfoDao;
	}

	@Override
	public <T> List<T> findAll() {
		return this.findPageByQuery("from SysLoginInfo");
	}

	@Override
	public List findByAny(String attributename, Object attributevalue,
			String order) {
		return sysLoginInfoDao.findByAny("SysLoginInfo", attributename, attributevalue, order);
	}

	@Override
	public <T> void update(T entity) {
		sysLoginInfoDao.update(entity);
	}

	@Override
	public List findBySQLQuery(String sql, int length) {
		// TODO Auto-generated method stub
		return sysLoginInfoDao.findBySQLQuery(sql, length);
	}
	
}
