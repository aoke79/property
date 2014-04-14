package com.sinoframe.dao;

import java.util.List;

import com.comm.query.criteria.Criteria;
import com.sinoframe.common.paging.Paging;
import com.sinoframe.bean.SysPermission;

public interface ISysPermissionDao extends IPublicDao{
	public void save(final SysPermission sysPermission) ;
    public void update(final SysPermission sysPermission) ;
    public void del(final SysPermission sysPermission) ;
    public void delSome(final java.lang.String[] ids);
    public void delAll();
    
    public SysPermission getById(final java.lang.String id) ;
    public void getByIds(final Paging paging,final java.lang.String[] ids);
    
    public void getAll(final Paging paging);
    public void getByQuery(final Paging paging, final Criteria c);
    public List getOrganizationAll();
}
