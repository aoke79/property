package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.sinoframe.common.paging.Paging;
import com.sinoframe.dao.ISysOrgCodeMappingDao;

@Repository("sysOrgCodeMappingDao")
public class SysOrgCodeMappingDao extends PublicDao implements ISysOrgCodeMappingDao {

	public void getAll(Paging paging) {
		this.HPaging3(paging, "from SysOrgCodeMapping");
	}

}
