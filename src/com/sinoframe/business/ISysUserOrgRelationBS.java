package com.sinoframe.business;

import java.util.List;

import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.CmUser;

public interface ISysUserOrgRelationBS extends IService{

	/**
	 * 求当前登录者所在的部门集合
	 * @param userId 当前登录人员
	 * @return
	 */
	public List<SysOrganization> findOrgByUserId(CmUser cmUser);
	
	public List findByAny(String boname, String attributename,
			Object attributevalue, String order);
}
