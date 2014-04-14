package com.sinoframe.dao;

import java.util.List;
import java.util.Map;

import javax.mail.Flags.Flag;

import com.comm.query.criteria.Criteria;
import com.sinoframe.bean.SysRelationAccountRole;

/**
 *RelationAccountRole 表的基本操作
 * @author niujingwie
 *
 */
public interface ISysRelationAccountRoleDao extends IPublicDao{

    
   /**
    *  删除指定的多条数据
    * @param relationAccountRoles
    */
    public void delSome(final List<SysRelationAccountRole> relationAccountRoles);
    /**
     * 删除全部数据
     */
    public void delAll();
}
