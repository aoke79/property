package com.sinoframe.business;

import java.util.List;

import com.sinoframe.bean.SysOrgCodeMapping;
import com.sinoframe.bean.SysOrgCodeMappingId;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.common.paging.Paging;

public interface ISysOrgCodeMappingBS extends IService{
	
	public void modify(SysOrgCodeMapping oldSysOrgCodeMapping, SysOrgCodeMapping newSysOrgCodeMapping);
}
