
package com.sinoframe.dao.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.impl.SessionImpl;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.dao.IPublicDao;

/**
 * hibernate DAO
 * @author haodafeng
 * @version V1.0
 */
@Repository("publicDAO")
@Transactional
public class PublicDao extends BaseHibernateDao implements IPublicDao{
	//private static Log log = LogFactory.getLog(PublicDao.class);

	/**
	 * save/insert into DB
	 * @param entity
	 * @return 
	 */
	@Transactional(noRollbackFor=RuntimeException.class)
	public <T> T save(T entity) {
		this.getHibernateTemplate().save(entity);
		return entity;
	}
	
	public <T> List<T> saveList(List<T> list) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int i=0;
		for ( T t :list ) {
			i++;
			session.save(t);
			if ( i % 20 == 0 ) {
				session.flush();
				session.clear();
			}else if(i==list.size()){
				session.flush();
				session.clear();
			}
		}
		tx.commit();
		session.close();
		this.clear(this.getHibernateTemplate(), list.get(0).getClass());
		return list;
	}
	
	/**
	 * save or update
	 * @param entity
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> T saveOrUpdate(T entity){
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}
	
	/**
	 * update
	 * @param entity
	 */
	public <T> void update(T entity) {
		this.getHibernateTemplate().update(entity);		
	}
	
	/**
	 * delete by Entity
	 * @param entity
	 */
	@Transactional(rollbackFor=Exception.class)
	public <T> void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		this.clear(this.getHibernateTemplate(), entity.getClass());
	}
	
	/**
	 * get count by hql
	 * @param HQL
	 */
	public long getCountByHQL(String hql)
	{
		return Long.parseLong(this.getHibernateTemplate().find(hql).get(0).toString());
	}
	
	public int getCountByHql(String hql){
		return Integer.parseInt(this.getHibernateTemplate().find(hql).get(0).toString());
	}
	
	/**
	 * find Page By Query
	 * @param sysPageInfo
	 * @param hql
	 * @return Entity List
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> findPageByQuery(final SysPageInfo sysPageInfo,final String hql) {
		
		List<T> result = null;
		result = getHibernateTemplate().executeFind(new HibernateCallback() {
		     public Object doInHibernate(Session session)
		     throws HibernateException, SQLException {
		     Query query = session.createQuery(hql);
		     query.setFirstResult(sysPageInfo.getStartIndex());
		     if(sysPageInfo.getPageSize()!=0){
		    	 query.setMaxResults(sysPageInfo.getPageSize());
		     }
		     
			 List<T> list = query.list();
		     return list;
		     }
		});
		
		//this.getHibernateTemplate().setCacheQueries(true);
		return result;
	}
	
	/**
	 * find Page By Query
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findPageByQuery(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * executeUpdate
	 * @param hql
	 */
	public void executeUpdate(String hql){
		  this.updateStation(hql);
		//this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql).executeUpdate();
	}
	/**
	 * 根据条件删除数据表记录并返回删除条数
	 * 
	 * @param boname			BO名称
	 * @param attributename		参数名称
	 * @param attributevalue	参数值
	 * @return					删除条数
	 */
	public Integer bulkDelete(final String boname,final 
							String attributename,final Object attributevalue){
		Integer ret = (Integer)this.getHibernateTemplate().execute(
				
			//创建匿名内部类
			new HibernateCallback() {
				
			//doInHibernate方法内可以访问Session，该Session对象是绑定到该线程的Session实例
            public Object doInHibernate(Session session) 
            						throws HibernateException {
            	
            	//拼写HQL文
            	String deleteHQL = "delete " + boname + " where " +
            									attributename + "=" + attributevalue;
            	
            	//执行HQL
                Query query = session.createQuery(deleteHQL);
                
                //返回结果
                int r = query.executeUpdate();

                //返回一个integer类
                return new Integer(r) ;
            }
        });
		
		//返回值
		return ret;
	}

	/**
	 * 根据条件更新并返回更新条数
	 * 
	 * @param boname			BO名
	 * @param setattributename	参数名称
	 * @param setattributevalue	参数值
	 * @param conditon			更新条件
	 * @return					发生变化的记录个数
	 */
	public Integer bulkUpdate(final String boname,final String setattributename,
								final Object setattributevalue,final String condition){
		Integer ret = (Integer)this.getHibernateTemplate().execute(
			
			//创建匿名内部类
		    new HibernateCallback() {
		    
		    //方法内可以访问Session，该Session对象是绑定到该线程的Session实例
			public Object doInHibernate(Session session)
                    					throws HibernateException {
				
				//定义HQL
				String updateHQL;
				
				//根据参数生成HQL
				if(condition == null){
					updateHQL = "update " + boname + " set " + setattributename
														+ "=" + setattributevalue;
				}else{
					updateHQL = "update " + boname + " set " + setattributename
										+ "=" + setattributevalue + " where " + condition;
				}
				
				//执行HQL返回执行结果
				Query query = session.createQuery(updateHQL);
				int r = query.executeUpdate();

				return new Integer(r) ;
			}
		});
		return ret;
	}	

	
	
	
	/**
	 * 检索整张BO数据
	 * 
	 * @param boname			BO名字
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List findAll(String boname) {
		
		//生成HQL文
		String hql = "from " + boname;
		
		//根据HQL查询数据库
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 检索有限记录
	 * 
	 * @param boname 			BO名字
	 * @param attributename 	参数名字
	 * @param attributevalue 	参数对象
	 * @param order				排序命令
	 * @return 					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByAny(String boname, String attributename,
								Object attributevalue, String order) {
		
		//根据条件生成HQL
		if(order == null) order = "";
		
		String hql = "from " + boname + " as bo where bo." + attributename
												+ "=" +attributevalue + order;
		
		//返回查询结果
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据参数组检索数据库
	 * 
	 * @param boname			BO名字
	 * @param attributename		参数名数组
	 * @param attributevalue	参数值数组
	 * @param order				排序命令
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByAny(String boname, String[] attributename,
								Object[] attributevalue, String order) {
		
		//根据条件生成HQL
		if(order == null) order = "";
		String hql = "from "+boname+" as bo where ";
		
		//根据参数个数生成HQL条件
		if(attributename.length == attributevalue.length){
			for(int i=0 ; i<attributename.length ; i++){
				if(i == 0){
					hql = hql + "bo." + attributename[i] + "=" + attributevalue[i]+"  ";
				}else{
					hql = hql + " and bo." + attributename[i] + "=" + attributevalue[i];
				}				
			}

			//HQL文
			hql = hql + order;
			
			//检索返回结果
			return this.getHibernateTemplate().find(hql);
		}else{
			return null;
		}
	}

	/**
	 * 根据HQL检索相应的记录
	 * 
	 * @param hql				检索SQL
	 * @return 					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByAnyHql(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据参数检索数据库
	 * 
	 * @param boname 			BO名字
	 * @param attributename 	参数名字
	 * @param attributevalue 	参数值
	 * @param order				排序命令
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByAnyString(String boname, String attributename,
									String attributevalue, String order) {
		
		//根据条件生成HQL
		if(order == null) order = "";
		String hql = "from " + boname + " as bo where bo." + attributename
											+ "='" + attributevalue + "'" + order;
		
		//检索并返回结果
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据参数检索数据库
	 * 
	 * @param boname			BO名字
	 * @param attributename		参数名数组
	 * @param attributevalue	参数值数组
	 * @param order				排序命令
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByAnyString(String boname, String[] attributename,
									String[] attributevalue, String order) {

		//根据条件生成HQL
		if(order == null) order = "";
		String hql = "from " + boname + " as bo where ";
		
		//根据条件生成HQL条件
		if(attributename.length == attributevalue.length){
			for(int i=0 ; i<attributename.length ; i++){
				if(i == 0){
					hql = hql + "bo." + attributename[i] + "='" + attributevalue[i] + "'";
				}else{
					hql = hql+" and bo." + attributename[i] + "='" + attributevalue[i] + "'";
				}				
			}
			
			//生成HQL
			hql = hql + order;

			//检索返回结果
			return this.getHibernateTemplate().find(hql);
		}else{
			return null;
		}
	}
	/**
	 * find Entity by entityClass and  id
	 * @param				entityClass
	 * @param				id 
	 * @return				Entity
	 */
	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> entityClass, Serializable id) {
		return (T)this.getHibernateTemplate().get(entityClass,id);
	}
	
	
	/**
	 * 根据HQL检索数据库
	 * 
	 * @param queryName			HQL名
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName) {
		return this.getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * 根据条件检索数据库
	 * 
	 * @param queryName			HQL名
	 * @param paramName			参数名称
	 * @param value				参数值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByNamedQueryAndNamedParam(String queryName,
										String paramName, Object value) {
		return this.getHibernateTemplate().
						findByNamedQueryAndNamedParam(queryName,paramName,value);
	}

	/**
	 * 根据条件检索数据库
	 * 
	 * @param queryName			HQL名
	 * @param paramNames		参数数组名称
	 * @param value				参数数组值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByNamedQueryAndNamedParam(String queryName,
											String[] paramNames, Object[] values) {
		return this.getHibernateTemplate().
						findByNamedQueryAndNamedParam(queryName,paramNames,values);
	}

	/**
	 * 根据SQL和返回值个数查询数据库
	 * 
	 * @param sql				SQL文
	 * @param length			返回值个数
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQuery(final String sql,final int length) {
	    return this.getHibernateTemplate().executeFind(new HibernateCallback() { 
	        @SuppressWarnings("unchecked")
			public Object doInHibernate(Session s) throws HibernateException,SQLException {
	        	
	        	Session session = ((SessionFactoryImpl)s.getSessionFactory()).openSession();   
	        	PreparedStatement pstmt = ((SessionImpl)session).getBatcher().prepareStatement(sql);   
        	    ResultSet rs = pstmt.executeQuery();
        	    List list = new ArrayList();
        	    while(rs.next()){
        	    	Object [] obj = new Object[length];
        	    	for(int i=0; i< length ; i++){
        	    		obj [i] = rs.getObject(i+1);
        	    	}
        	    	list.add(obj);
        	    }
        	    rs.close();
        	    pstmt.close();        	            	    
        	    session.close();
          
	            return list;	             
    	    } 
	    }); 
	}

	
	/**
	 * 根据SQL映射名称和参数检索数据库
	 * 
	 * @param sqlName			SQL映射名
	 * @param paramName			参数名
	 * @param value				参数值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQueryAndNamedParam(final String sqlName,
										final String paramName, final String value) {
		
	    return this.getHibernateTemplate().executeFind(
	    		
	    	//创建匿名内部类
	    	new HibernateCallback() {
	    		
	        public Object doInHibernate(Session s) throws HibernateException { 
	            Query query = s.getNamedQuery(sqlName);
	            query.setParameter(paramName, value);
	            List list = query.list();
	            return list; 
	        } 
	    }); 
	}

	/**
	 * 根据SQL映射名称和参数数组检索数据库
	 * 
	 * @param sqlName			SQL映射名
	 * @param paramName			参数名
	 * @param value				参数值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQueryAndNamedParam(final String sqlName,
							final String []paramName, final String []value) {
	    return this.getHibernateTemplate().executeFind(new HibernateCallback() { 
	        public Object doInHibernate(Session s) throws HibernateException { 
	            Query query = s.getNamedQuery(sqlName);
	            for(int i=0; i<paramName.length ; i++ ){
	            	query.setParameter(paramName[i], value[i]);
	            }
	            List list = query.list();
	            return list; 
	        } 
	    }); 
	}	

	/**
	 * 根据SQL映射名称检索数据库
	 * 
	 * @param sqlName			SQL映射名
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQueryNamed(final String sqlName) {
	    return this.getHibernateTemplate().executeFind(new HibernateCallback() { 
	        public Object doInHibernate(Session s) throws HibernateException { 
	            Query query = s.getNamedQuery(sqlName);	            
	            List list = query.list();         
	            return list; 
	        } 
	    }); 
	}

	/**
	 * 根据条件检索数据表记录COUNT
	 * 
	 * @param boname			BO名字
	 * @param attributename		参数名称
	 * @param attributevalue	参数值
	 * @return					检索记录个数
	 */
	@SuppressWarnings("rawtypes")
	public long getBOCount(String boname, 
							String attributename,Object attributevalue) {
		String hql = "select count(bo) from "+boname+" as bo where bo."
								+attributename+"="+attributevalue;
		List list = this.getHibernateTemplate().find(hql);
		
		Long ret = (Long)list.get(0);
		return ret;
	}

	/***
	 * 检索数据COUNT
	 * 
	 * @param boname			BO名字
	 * @return					检索记录个数
	 */
	@SuppressWarnings("rawtypes")
	public Integer getBOCount(String boname) {
		String hql = "select count(bo) from "+boname+" as bo";
		List list = this.getHibernateTemplate().find(hql);
		Integer ret = (Integer)list.get(0);
		return ret;
	}

	/**
	 * 初始化数据库持久链接
	 * 
	 * @param object			hibernate实例
	 */
	public void getLazyData(Object object) {
		Hibernate.initialize(object);		
	}

	/**
	 * 根据HQL检索数据库
	 * 
	 * @param hqlName			HQL名称
	 * @return					检索结果
	 */
	public String getQueryStringByName(final String hqlName) {
	    String hql = (String)this.getHibernateTemplate().execute(new HibernateCallback() { 
	        public Object doInHibernate(Session s) throws HibernateException {
	        	return s.getNamedQuery(hqlName).getQueryString();	             
	        } 
	    }); 
	    return hql;
	}

	
	/**
	 * 事务处理SQL
	 * @param lstSQL 事务的SQL文
	 */
	public Object transactionSQL(final List<Object> lstSQL) {
	    return this.getHibernateTemplate().executeFind(new HibernateCallback() { 
	        public Object doInHibernate(Session s) throws HibernateException, SQLException {
	        	
	        	//返回值
	        	boolean bResult = true;
	        	
	        	//取得session
	        	Session session = ((SessionFactoryImpl)s.getSessionFactory()).openSession();
	        	
	        	//事务
	        	Transaction transac = null;
	        	PreparedStatement pstmt = null;
	        	try{
	        		transac = session.beginTransaction();
	        		
		        	//transac
	        		for (int i = 0; i < lstSQL.size(); i++) {
		        		pstmt = ((SessionImpl)session).getBatcher()
		        					.prepareStatement(lstSQL.get(i).toString());
		        		pstmt.executeQuery();
	        		}
	        		
	        		//刷新Session
	        		session.flush();
	        		
	        		//提交事务
	        		transac.commit();
	        	}catch (HibernateException e){
	        		
	        		bResult = false;
	        		if (transac != null ) transac.rollback();
	        	}catch (RuntimeException e) {
	        		bResult = false;	        		
	        		if (transac != null ) transac.rollback();
	        		
	        	}catch (SQLException e){
	        		bResult = false;
	        		if (transac != null ) transac.rollback();
	        		
	        	}finally{
	        		pstmt.close();
	        		session.close();
	        		
	        	}
	            return bResult;	             
    	    } 
	    }); 
	}
	@Override
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void clear(HibernateTemplate hibernateTemplate,  Class c) {
		hibernateTemplate.getSessionFactory().evict(c);
	}
	@Override
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void clear(Class c) {
		this.getHibernateTemplate().getSessionFactory().evict(c);
		
	}
	
	/**
	 * SQL查询方法
	 * @param <T>
	 * @param sql
	 * @return
	 */
	public <T>List<T> findBySQL(String sql) {
		Query query = this.getSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public <T> List<T> updateList(List<T> list) {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int i=0;
		for ( T t :list ) {
			i++;
			session.update(t);
			if ( i % 20 == 0 ) {
				session.flush();
				session.clear();
			}else if(i==list.size()){
				session.flush();
				session.clear();
			}
		}
		tx.commit();
		session.close();
		this.clear(this.getHibernateTemplate(), list.get(0).getClass());
		return list;
	}
	
	@Override
	public <T> List<T> saveOrUpdateList(List<T> list) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int i=0;
		for ( T t :list ) {
			i++;
			session.saveOrUpdate(t);
			if ( i % 20 == 0 ) {
				session.flush();
				session.clear();
			}else if(i==list.size()){
				session.flush();
				session.clear();
			}
		}
		tx.commit();
		session.close();
		this.clear(this.getHibernateTemplate(), list.get(0).getClass());
		return list;
	}

	@Override
	public void executeUpdateSQL(String sql) {
		  this.updateStationSQL(sql);
		
	}
	
}
