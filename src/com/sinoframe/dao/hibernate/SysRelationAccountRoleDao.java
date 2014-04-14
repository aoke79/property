package com.sinoframe.dao.hibernate;


import java.util.List;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.dao.ISysRelationAccountRoleDao;
/**
 * RelationAccountRole 
 * @author niujingwei
 *
 */
@Repository("SysRelationAccountRoleDao")
@Transactional
public class SysRelationAccountRoleDao extends PublicDao implements ISysRelationAccountRoleDao{
    
	
	public void delAll() {
		this.getHibernateTemplate().bulkUpdate("delete from SysRelationAccountRole");
	}

	public void delSome(final List<SysRelationAccountRole> relationAccountRoles) {
		this.getHibernateTemplate().deleteAll(relationAccountRoles);
	}
}
