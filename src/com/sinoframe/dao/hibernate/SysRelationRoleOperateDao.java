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
import org.springframework.transaction.annotation.Transactional;

import com.comm.util.BeanUtils;
import com.sinoframe.bean.SysOperateGroupRelation;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.dao.ISysRelationRoleOperateDao;

@Repository("sysRelationRoleOperateDao")
@Transactional
public class SysRelationRoleOperateDao extends PublicDao implements
		ISysRelationRoleOperateDao {
	/**
	 * del
	 * 
	 * @param
	 * @param
	 */
	public void del(final SysRelationRoleOperate sysRelationRoleOperate) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = getSession().createQuery(
						"delete from SysRelationRoleOperate where id=:id");
				query.setParameter("id", sysRelationRoleOperate.getId());
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
	public void update(SysRelationRoleOperate sysRelationRoleOperate) {
		SysRelationRoleOperate tbAccountComHistoryDB = (SysRelationRoleOperate) this
				.getHibernateTemplate().load(SysRelationRoleOperate.class,
						sysRelationRoleOperate.getId());
		BeanUtils.copyProperties(sysRelationRoleOperate, tbAccountComHistoryDB);
	}

	/**
	 * save
	 * 
	 * @param
	 * @param
	 */
	public void save(final SysRelationRoleOperate sysRelationRoleOperate) {
		this.getHibernateTemplate().save(sysRelationRoleOperate);
	}

	/**
	 * getById
	 * 
	 * @param
	 * @param
	 */
	public SysRelationRoleOperate getById(final String id) {
		return (SysRelationRoleOperate) this.getHibernateTemplate().get(
				SysRelationRoleOperate.class, id);
	}


}
