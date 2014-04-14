package com.sinoframe.dao;

import java.util.List;

import com.sinoframe.bean.SysRole;


/**
 * Role表的基本操作
 * @author niujingwei
 *
 */
public interface ISysRoleDao extends IPublicDao{
	
    // 删除指定的多条数据
    public void delSome(final java.lang.String[] ids);
    // 删除全部数据
    public void delAll();
}
