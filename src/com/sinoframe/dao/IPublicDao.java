
package com.sinoframe.dao;
import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;


import com.sinoframe.bean.SysPageInfo;


/**
 * hibernet DAO interface
 * @author haodafeng
 * @version V1.0
 */
public interface IPublicDao {
	
	/**
	 * save/insert into DB
	 * @param entity
	 * @return 
	 */
	<T extends Object> T save(T entity);
	
	/**
	 * saveList
	 * @param list
	 */
	public <T> List<T> saveList(List<T> list);
	/**
	 * updateList
	 * @param list
	 */
	public <T> List<T> updateList(List<T> list);
	
	/**
	 * saveOrUpdateList
	 * @param list
	 */
	public <T> List<T> saveOrUpdateList(List<T> list);
	
	/**
	 * save or update
	 * @param entity
	 * @return
	 */
	<T extends Object> T saveOrUpdate(T entity);
	/**
	 * update
	 * @param entity
	 */
	public <T extends Object> void update(T entity);
	/**
	 * delete by Entity
	 * @param entity
	 */
	public <T extends Object> void delete(T entity);

	/**
	 * find Entity by entityClass and  id
	 * @param				entityClass
	 * @param				id 
	 * @return				Entity
	 */
	public <T extends Object> T findById(Class<T> entityClass,Serializable id);
	
	
	//<T extends Object> List<T> findPageByQuery(PageInfo pageinfo, String hql, OrderByInfo orderByInfo);
	/**
	 * get count by HQL
	 * @param HQL
	 */
	public long getCountByHQL(String hql);
	public int getCountByHql(String hql);
	/**
	 * find Page By Query
	 * @param sysPageInfo
	 * @param hql
	 * @return Entity List
	 */
	<T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo, String hql);
	
	/**
	 * find Page By Query
	 * @param hql
	 * @return
	 */
	<T extends Object> List<T> findPageByQuery(String hql);

	/**
	 * executeUpdate
	 * @param hql
	 */
	public void executeUpdate(String hql);
	/**
	 * 原生SQL 更新
	 * @param hql
	 */
	public void executeUpdateSQL(String sql);
	
	/**
	 * 根据HQL检索相应的记录
	 * 
	 * @param hql				检索SQL
	 * @return 					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByAnyHql(String hql);
	
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
	public List findByAny(String boname,String attributename,Object attributevalue,String order);
	
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
	public List findByAnyString(String boname,String attributename,String attributevalue,String order);
	
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
	public List findByAny(String boname,String[] attributename,Object[] attributevalue,String order);
	
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
	public List findByAnyString(String boname,String[] attributename,String[] attributevalue,String order);
	
	/**
	 * 检索整张BO数据
	 * 
	 * @param boname			BO名字
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findAll(String boname);
	
	/***
	 * 检索数据COUNT
	 * 
	 * @param boname			BO名字
	 * @return					检索记录个数
	 */
	public Integer getBOCount(String boname);

	/**
	 * 根据条件检索数据表记录COUNT
	 * 
	 * @param boname			BO名字
	 * @param attributename		参数名称
	 * @param attributevalue	参数值
	 * @return					检索记录个数
	 */
	public long getBOCount(String boname,String attributename,Object attributevalue);
	
	/**
	 * 根据HQL检索数据库
	 * 
	 * @param queryName			HQL名
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName);
		
	/**
	 * 根据条件检索数据库
	 * 
	 * @param queryName			HQL名
	 * @param paramName			参数名称
	 * @param value				参数值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value);
	
	/**
	 * 根据条件检索数据库
	 * 
	 * @param queryName			HQL名
	 * @param paramNames		参数数组名称
	 * @param value				参数数组值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values);
	
	/**
	 * 根据条件删除数据表记录并返回删除条数
	 * 
	 * @param boname			BO名称
	 * @param attributename		参数名称
	 * @param attributevalue	参数值
	 * @return					删除条数
	 */
	public Integer bulkDelete(String boname,String attributename,Object attributevalue);
	
	/**
	 * 根据条件更新并返回更新条数
	 * 
	 * @param boname			BO名
	 * @param setattributename	参数名称
	 * @param setattributevalue	参数值
	 * @param conditon			更新条件
	 * @return					发生变化的记录个数
	 */
	public Integer bulkUpdate(String boname,String setattributename,Object setattributevalue,String conditon);
	
	/**
	 * 初始化数据库持久链接
	 * 
	 * @param object			hibernate实例
	 */
	public void getLazyData(Object object);
	
	/**
	 * 根据SQL和返回值个数查询数据库
	 * 
	 * @param sql				SQL文
	 * @param length			返回值个数
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQuery(String sql,int length);
	
	/**
	 * 根据SQL映射名称检索数据库
	 * 
	 * @param sqlName			SQL映射名
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQueryNamed(String sqlName); 
	
	/**
	 * 根据SQL映射名称和参数检索数据库
	 * 
	 * @param sqlName			SQL映射名
	 * @param paramName			参数名
	 * @param value				参数值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQueryAndNamedParam(String sqlName,String paramName,String value);
	
	/**
	 * 根据SQL映射名称和参数数组检索数据库
	 * 
	 * @param sqlName			SQL映射名
	 * @param paramName			参数名
	 * @param value				参数值
	 * @return					检索结果
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQLQueryAndNamedParam(String sqlName,String []paramName, String []value);
	
	/**
	 * 根据HQL检索数据库
	 * 
	 * @param hqlName			HQL名称
	 * @return					检索结果
	 */
	public String getQueryStringByName(String hqlName);
	
	@SuppressWarnings("rawtypes")
	public void clear(HibernateTemplate hibernateTemplate,Class c);
	
	@SuppressWarnings("rawtypes")
	public void clear(Class c);

	public <T>List<T> findBySQL(String sql);

}
