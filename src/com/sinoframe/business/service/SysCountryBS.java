package com.sinoframe.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.ISysCountryBS;
import com.sinoframe.business.ISysLoginInfoBS;
import com.sinoframe.dao.ISysCountryDao;
import com.sinoframe.dao.ISysLoginInfoDao;

@Service
public class SysCountryBS extends BaseBS implements ISysCountryBS {

	ISysLoginInfoDao sysLoginInfoDao;

	@Override
	public <T> List<T> findAll() {
		return this.findPageByQuery("from SysCountry");
	}

	
}
