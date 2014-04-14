package com.sinoframe.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.comm.query.criteria.Criteria;
import com.comm.util.BeanUtils;
import com.comm.util.Util;
import com.sinoframe.bean.SysPermission;
import com.sinoframe.common.paging.Paging;
import com.sinoframe.dao.ISysPermissionDao;

@Repository("SysPermissionDao")
@Transactional
public class SysPermissionDao extends PublicDao implements ISysPermissionDao{
	public void save(final SysPermission sysPermission) {
		this.getHibernateTemplate().save(sysPermission);
	}
	public void update(final SysPermission sysPermission) {
	    	SysPermission tbAccountComHistoryDB=(SysPermission)this.getHibernateTemplate().load(SysPermission.class,sysPermission.getId());
	    	BeanUtils.copyProperties(sysPermission, tbAccountComHistoryDB);
	}
	
	public void del(final SysPermission sysPermission) {
	    getHibernateTemplate().execute( new HibernateCallback() {
		public Object doInHibernate(Session session) throws HibernateException {
        	   Query query=getSession().createQuery("delete from SysPermission where id=:id");
        	   query.setParameter("id", sysPermission.getId());
        	    query.executeUpdate();
        	    return  null;
		}
	    });
	}
	public void delSome(final java.lang.String[] ids) {
	    getHibernateTemplate().execute( new HibernateCallback() {
		public Object doInHibernate(Session session) throws HibernateException {
        	   Query query=getSession().createQuery("delete from SysPermission where id in (:ids)");
        	   query.setParameterList("ids", ids);
        	    query.executeUpdate();
        	    return  null;
		}
	    });
	}
	public void delAll() {
	    this.getHibernateTemplate().bulkUpdate("delete from SysPermission ");
	}
	public List getOrganizationAll(){
		return this.getHibernateTemplate().find("from SysOrganization");
	}
	
	public SysPermission getById(final String id){
	   return (SysPermission)this.getHibernateTemplate().get(SysPermission.class, id);
	}
	public void getByIds(final Paging paging,final java.lang.String[] ids){
	    HPaging3(paging,"from SysPermission where id in ("+Util.Array2String(ids)+") ");
//	    return (List)getHibernateTemplate().execute( new HibernateCallback() {
//		public Object doInHibernate(Session session) throws HibernateException {
//        	   Query query=getSession().createQuery("from SysPermission where id in (:ids)");
//        	   query.setParameterList("ids", ids);
//        	   return query.list();
//		}
//	    });
	}
	
	
	public void getAll(final Paging paging){
	    HPaging3(paging,"from SysPermission");
	}
	public void getByQuery(final Paging paging,final Criteria c){
//	    HPaging3(paging,"from SysPermission sysPermission",c);
	}

}
