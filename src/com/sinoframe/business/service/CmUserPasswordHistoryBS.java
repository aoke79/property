package com.sinoframe.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.ICmUserPasswordHistoryBS;
import com.sinoframe.dao.ICmUserPasswordHistoryDao;

@Service
public class CmUserPasswordHistoryBS extends BaseBS implements ICmUserPasswordHistoryBS {
	
	ICmUserPasswordHistoryDao cmUserPasswordHistoryDao;
	

	@Override
	public <T> List<T> findAll() {
		return this.findPageByQuery("from UserPasswordHistory");
		
	}

	@Override
	public <T> List<T> findByPasswordHistory(int num) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public <T> List<T> findBySQLQueryNamed(String sql) {
		return cmUserPasswordHistoryDao.findBySQLQueryNamed(sql);
	}
	public final void setCmUserPasswordHistoryDao(
			ICmUserPasswordHistoryDao cmUserPasswordHistoryDao) {
		this.cmUserPasswordHistoryDao = cmUserPasswordHistoryDao;
	}
	
}
