package com.sinoframe.dao.hibernate;


import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;


import com.comm.query.Hibernater3Parse;
import com.comm.query.criteria.Criteria;
import com.sinoframe.common.paging.HPagination3;
import com.sinoframe.common.paging.Paging;

/**
 * Convenient super class for Hibernate data access objects.
 *
 * <p>Requires a SessionFactory to be set, providing a HibernateTemplate
 * based on it to subclasses. Can alternatively be initialized directly with
 * a HibernateTemplate, to reuse the latter's settings such as the SessionFactory,
 * exception translator, flush mode, etc.
 *
 * <p>This base class is mainly intended for HibernateTemplate usage
 * but can also be used when working with SessionFactoryUtils directly,
 * for example in combination with HibernateInterceptor-managed Sessions.
 * Convenience <code>getSession</code> and <code>releaseSession</code>
 * methods are provided for that usage style.
 *
 * <p>This class will create its own HibernateTemplate if only a SessionFactory
 * is passed in. The "allowCreate" flag on that HibernateTemplate will be "true"
 * by default. A custom HibernateTemplate instance can be used through overriding
 * <code>createHibernateTemplate</code>.
 *
 * @author Juergen Hoeller
 * @since 1.2
 * @see #setSessionFactory
 * @see #setHibernateTemplate
 * @see #createHibernateTemplate
 * @see #getSession
 * @see #releaseSession
 * @see org.springframework.orm.hibernate3.HibernateTemplate
 * @see org.springframework.orm.hibernate3.HibernateInterceptor
 */
@Repository
public abstract class BaseHibernateDao extends DaoSupport {

	
	//jdbcTemplate
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name = "dataSource")
	public final void setDataSource(final DataSource datasource){
		jdbcTemplate=new JdbcTemplate(datasource);
	}
	public final JdbcTemplate getJdbcTemplate() {
	  return jdbcTemplate;
	}
	
	//hibernateTemplate
	private HibernateTemplate hibernateTemplate;
	
	@Resource
	public final void setSessionFactory(final SessionFactory sessionFactory) {
//		logger.info("ioc set sessionFactory");
	  hibernateTemplate = createHibernateTemplate(sessionFactory);
	}
	
	//add by niujingwei start
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	//add by niujingwei end
	
	
	protected HibernateTemplate createHibernateTemplate(final SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}
	public final HibernateTemplate getHibernateTemplate() {
	  return hibernateTemplate;
	}
	
	//hbiernate:other
	public final SessionFactory getSessionFactory() {
		return (hibernateTemplate != null ? hibernateTemplate.getSessionFactory() : null);
	}
	protected final void checkDaoConfig() {
//		logger.info("InitializingBean--checkDaoConfig");
		if (hibernateTemplate == null) {
			throw new IllegalArgumentException("sessionFactory or hibernateTemplate is required");
		}
	}
	protected void initDao() throws Exception {
//		logger.info("InitializingBean--initDao");
	}

	
	protected final Session getSession()
	    throws DataAccessResourceFailureException, IllegalStateException {

		return getSession(hibernateTemplate.isAllowCreate());
	}

	
	protected final Session getSession(boolean allowCreate)
	    throws DataAccessResourceFailureException, IllegalStateException {

		return (!allowCreate ?
		    SessionFactoryUtils.getSession(getSessionFactory(), false) :
				SessionFactoryUtils.getSession(
						getSessionFactory(), 
						hibernateTemplate.getEntityInterceptor(),
						hibernateTemplate.getJdbcExceptionTranslator()));
	}

	protected final DataAccessException convertHibernateAccessException(final HibernateException ex) {
		return hibernateTemplate.convertHibernateAccessException(ex);
	}

	
	protected final void releaseSession(final Session session) {
		SessionFactoryUtils.releaseSession(session, getSessionFactory());
	}
	
	//
	protected final void HPaging3(final Paging paging,final String sql){
	    hibernateTemplate.execute( new HibernateCallback() {
		public Object doInHibernate(Session session) throws HibernateException {
		    HPagination3.getInstance(paging).query(sql, session);
		    return null;
		}
	    });
	}
	protected final void HPaging3(final Paging paging,final String sql,final Object para, final Type type){
	    hibernateTemplate.execute( new HibernateCallback() {
		public Object doInHibernate(Session session) throws HibernateException {
		    HPagination3.getInstance(paging).query(sql, para,type,session);
		    return null;
		}
	    });
	}
	protected final void HPaging3(final Paging paging,final String sql,final Object[] para, final Type[] type){
	    hibernateTemplate.execute( new HibernateCallback() {
		public Object doInHibernate(Session session) throws HibernateException {
		    HPagination3.getInstance(paging).query(sql,para,type, session);
		    return null;
		}
	    });
	}
//	protected final void HPaging3(final Paging paging,final String sql,final Criteria c){
//	    hibernateTemplate.execute( new HibernateCallback() {
//		public Object doInHibernate(Session session) throws HibernateException {
//		    Hibernater3Parse parse=new Hibernater3Parse(c,session);
//		    parse.parse(sql);
//		    HPagination3.getInstance(paging).query(parse.getSqlStr(),parse.getValues(),parse.getTypes(),session);
//		    return null;
//		}
//	    });
//	}

	protected final List find(final String sql,final Object[] objArray) throws DataAccessException{
		  
		   if(null==objArray){
			 
			   return this.getHibernateTemplate().find(sql);
		   }
		   return this.getHibernateTemplate().find(sql, objArray);
	   }
	
	protected final Object getObject(final String sql,final Object[] objArray) throws DataAccessException{
		   List list= null;
		   if(null==objArray){
			 
			   list =this.getHibernateTemplate().find(sql);
		   }else{
			   list = this.getHibernateTemplate().find(sql, objArray);
		   }
		   if(null==list||list.isEmpty()){
			   return null; 
		   }else{
			   return list.get(0);
		   }
	   }
	//execute sql in database
	protected final boolean execInData(String sql){
		Session session =  getSessionFactory().getCurrentSession(); 
		Query query = session.createQuery(sql);
		
		int ret  = query.executeUpdate();
		
		if(ret>0){
			return true;
		}else{
			return false;
		}
	}
	
	public final boolean updateStationSQL(String sql) {
		// TODO Auto-generated method stub
		return execInDataSQL(sql);
	}
	
	
	protected final boolean execInDataSQL(String sql){
		Session session =  getSessionFactory().getCurrentSession(); 
		Query query = session.createSQLQuery(sql);
		
		int ret  = query.executeUpdate();
		
		if(ret>0){
			return true;
		}else{
			return false;
		}
	}
	
	public final boolean updateStation(String sql) {
		// TODO Auto-generated method stub
		return execInData(sql);
	}
	
	public final boolean updateStation(String sql,Object[] obj ,Type[] type) {
		// TODO Auto-generated method stub
		Session session =  getSessionFactory().getCurrentSession(); 
		Query query = session.createQuery(sql);
		query.setParameters(obj, type);
		int ret  = query.executeUpdate();
		
		if(ret>0){
			return true;
		}else{
			return false;
		}
	}
	
	protected final List findCount(final String sql,int count) {
		Session session =  getSessionFactory().getCurrentSession(); 
		Query query = session.createQuery(sql);
		query.setFirstResult(1); 
		query.setMaxResults(count); 
		
	    return query.list();
		  
	   }
}
