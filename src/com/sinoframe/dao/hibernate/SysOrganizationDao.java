package com.sinoframe.dao.hibernate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrganization;
import com.sinoframe.common.paging.Paging;
import com.sinoframe.dao.ISysOrganizationDao;

@Repository("sysOrganizationDao")
@Transactional
public class SysOrganizationDao extends PublicDao implements
		ISysOrganizationDao {

	/**
	 * 根据id删除机构，即将相应机构的state设置为0
	 * @param ids 要删除的机构的id
	 */
	public void updateByIds(final String[] ids) {
		
		final String hql = "from SysOrganization so where so.id in (:ids) ";
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// 设置参数
				Query query = session.createQuery(hql).setParameterList("ids", ids);
				List<SysOrganization> listSysOrganization = query.list();
				// 将查询出的机构的state属性设置为"0";
				for(SysOrganization sysOrganzation : listSysOrganization){
					sysOrganzation.setState("0");
				}
				return null;
			}
		});
		
	}
}
