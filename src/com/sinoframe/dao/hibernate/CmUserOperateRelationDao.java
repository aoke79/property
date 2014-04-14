package com.sinoframe.dao.hibernate;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.comm.util.BeanUtils;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.CmUserOperateRelation;
import com.sinoframe.dao.ICmUserOperateRelationDao;

@Repository("cmUserOperateRelationDao")
public class CmUserOperateRelationDao extends PublicDao implements
		ICmUserOperateRelationDao {

	/**
	 * del
	 * 
	 * @param
	 * @param
	 */
	public void del(final CmUserOperateRelation cmUserOperateRelation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = getSession().createQuery(
						"delete from CmUserOperateRelation where id=:id");
				query.setParameter("id", cmUserOperateRelation.getId());
				query.executeUpdate();
				return null;
			}
		});
	}

	

	/**
	 * update
	 * 
	 * @param
	 * @param
	 */
	public void update(CmUserOperateRelation cmUserOperateRelation) {
		// TODO Auto-generated method stub
		CmUserOperateRelation tbAccountComHistoryDB = (CmUserOperateRelation) this
				.getHibernateTemplate().load(CmUserOperateRelation.class,
						cmUserOperateRelation.getId());
		BeanUtils.copyProperties(cmUserOperateRelation, tbAccountComHistoryDB);

	}

	/**
	 * save
	 * 
	 * @param
	 * @param
	 */
	public void save(final CmUserOperateRelation cmUserOperateRelation) {
		this.getHibernateTemplate().save(cmUserOperateRelation);
	}

	/**
	 * getById
	 * 
	 * @param
	 * @param
	 */
	public CmUserOperateRelation getById(final String id) {
		return (CmUserOperateRelation) this.getHibernateTemplate().get(
				CmUserOperateRelation.class, id);
	}
}
