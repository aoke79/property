package com.sinoframe.dao.hibernate;

import org.drools.lang.DRLParser.and_constr_return;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.comm.util.Util;
import com.sinoframe.dao.ISysRoleDao;

/**
 * Role表操作实现类
 * @author niujingwei
 *
 */


@Repository("SysRoleDao")
@Transactional
public class SysRoleDao extends PublicDao implements ISysRoleDao {
 
	public void delAll() {
		this.getHibernateTemplate().bulkUpdate("delete from SysRole ");
	}

	public void delSome(final String[] ids) {
		 getHibernateTemplate().execute( new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
		        	   Query query=getSession().createQuery("delete from SysRole where roleId in (:ids)");
		        	   query.setParameterList("ids", ids);
		        	    query.executeUpdate();
		        	    return  null;
				}
			    });
	}
}
