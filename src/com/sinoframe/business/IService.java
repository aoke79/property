
package com.sinoframe.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.security.basicworkflow.bean.BasicWorkflow;

public interface IService {

	/**
	 * save/insert into DB
	 * @param entity
	 * @return 
	 */
	<T extends Object> T save(T entity);
	
	/**
	 * saveList
	 * @param <T>
	 * @param list
	 */
	<T extends Object> List<T> saveList(List<T> list);
	/**
	 * saveList
	 * @param <T>
	 * @param list
	 */
	<T extends Object> List<T> updateList(List<T> list);
	/**
	 * saveList
	 * @param <T>
	 * @param list
	 */
	<T extends Object> List<T> saveOrUpdateList(List<T> list);
	
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
	public <T extends Object> T update(T entity,Serializable id);
	
	/**
	 * copy
	 * @param oldEB
	 * @param newEB
	 * @return
	 * @throws Exception
	 */
	public Object copy(Object oldEB, Object newEB) throws Exception;
	
	/**
	 * delete by Entity
	 * @param entity
	 */
	public <T extends Object> void delete(T entity);
	
	/**
	 * deleteById
	 * @param entityClass
	 * @param id
	 */
	<T extends Object> void deleteById(Class<T> entityClass, Serializable id);
	
	/**
	 * deleteByIds
	 * @param clazz
	 * @param ids
	 */
	public <T extends Object> void deleteByIds(Class<T> entityClass,String ids);
	
	/**
	 * deleteByIds
	 * @param entityClass
	 * @param ids
	 */
	public <T extends Object> void deleteByIds(Class<T> entityClass, Serializable[] ids);
	
	/**
	 * find Entity by entityClass and  id
	 * @param				entityClass
	 * @param				id 
	 * @return				Entity
	 */
	public <T extends Object> T findById(Class<T> entityClass,Serializable id);
	
	/**
	 * get count by HQL
	 * @param HQL
	 */
	public long getCountByHQL(String hql);
	public int getCountByHql(String hql);
	
	/**
	 * get count by HQL and queryMap
	 * @param hql
	 * @param query
	 * @return
	 */
	long getCountByHQL(String hql, HashMap<String, String> query);
	long getCountByHQL(String hql, HashMap<String, String> query,String customHql);
	public int getCountByHql(String hql, HashMap<String,String> query);
	
	/**
	 * find Page By Query
	 * @param hql
	 * @return Entity List
	 */
	public <T extends Object> List<T> findPageByQuery(String hql);
	/**
	 * find Page By Query
	 * @param pageinfo
	 * @param hql
	 * @param orderByInfo
	 * @return Entity List
	 */
	<T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo, String hql);
	
	/**
	 * 
	 * @param <T>
	 * @param sysOrderByInfo
	 * @param hql
	 * @return
	 */
	<T extends Object> List<T> findPageByQuery(SysOrderByInfo sysOrderByInfo, String hql);
	
	/**
	 * find page by query
	 * @param <T>
	 * @param pageinfo
	 * @param hql
	 * @param query
	 * @param orderByInfo
	 * @return Entity List
	 */
	<T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo, String hql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);

	/**
	 * find page by query
	 * @param <T>
	 * @param pageinfo
	 * @param hql
	 * @param query
	 * @param orderByInfo
	 * @return Entity List
	 */
	<T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo, String hql,String customHql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 自定义排序字段的排序方法
	 * @param <T>
	 * @param sysPageInfo
	 * @param hql
	 * @param query
	 * @param orderByInfo
	 * @return
	 */
	public <T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo,
			String hql, HashMap<String, String> query, String orderByInfo);
	
	/**
	 * 自定义排序字段的排序方法(带自定义SQL）
	 * @param <T>
	 * @param sysPageInfo
	 * @param hql
	 * @param customHql
	 * @param query
	 * @param orderByInfo
	 * @return
	 */
	public <T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo,
			String hql, String customHql, HashMap<String, String> query, String orderByInfo);
	
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
	 * 处理缓存问题
	 * @param c
	 */
	@SuppressWarnings("rawtypes")
	public void clear(Class c);
	/**
	 * save/insert into DB
	 * @param entity
	 * @return 
	 */
	<T extends Object> T saveNoLog(T entity);
	
	/**
	 * saveList
	 * @param <T>
	 * @param list
	 */
	<T extends Object> void saveListNoLog(List<T> list);
	
	/**
	 * save or update
	 * @param entity
	 * @return
	 */
	<T extends Object> T saveOrUpdateNoLog(T entity);
	
	/**
	 * update
	 * @param entity
	 */
	public <T extends Object> void updateNoLog(T entity,Serializable id);
	
	/**
	 * delete by Entity
	 * @param entity
	 */
	public <T extends Object> void deleteNoLog(T entity);
	
	/**
	 * deleteById
	 * @param entityClass
	 * @param id
	 */
	<T extends Object> void deleteByIdNoLog(Class<T> entityClass, Serializable id);
	
	/**
	 * deleteByIds
	 * @param clazz
	 * @param ids
	 */
	public <T extends Object> void deleteByIdsNoLog(Class<T> entityClass,String ids);
	
	/**
	 * deleteByIds
	 * @param entityClass
	 * @param ids
	 */
	public <T extends Object> void deleteByIdsNoLog(Class<T> entityClass, Serializable[] ids);
	
	/**
	 * find Page By Query
	 * @param hql
	 * @return Entity List
	 */
	public <T extends Object> List<T> findPageByQueryNoLog(String hql);
	/**
	 * find Page By Query
	 * @param pageinfo
	 * @param hql
	 * @param orderByInfo
	 * @return Entity List
	 */
	<T extends Object> List<T> findPageByQueryNoLog(SysPageInfo sysPageInfo, String hql);
	
	/**
	 * find page by query
	 * @param <T>
	 * @param pageinfo
	 * @param hql
	 * @param query
	 * @param orderByInfo
	 * @return Entity List
	 */
	<T extends Object> List<T> findPageByQueryNoLog(SysPageInfo sysPageInfo, String hql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * executeUpdate
	 * @param hql
	 */
	public void executeUpdateNoLog(String hql);
	
	/**
	 * save operateId , userId , time and operateDescribe
	 * @param id
	 */
	public void operateSave(String operateid,String describe,String userId);
	
	/**
	 * 根据URL相关信息记录操作
	 * @param url
	 */
	public void operateLogger(String url);
	
	public Log getSelectLog();

	public Log getUpdateLog();

	public Log getInsertLog();

	public Log getErrorLog();

	public Log getDeleteLog();

	public Log getExecuteLog();
	
	/**
     * 根据当前用户获取机构信息
     * @param user
     * @return
     */
    public List<SysOrganization> getOrganizationByUser(CmUser user);
    /**
     * 根据当前用户获取角色信息
     * @param user
     * @return
     */
    public List<SysRole> getRoleByUser(CmUser user);
    
    /**
     * 获取查询时需要的条件，并转换成查询时需要的格式
     * @param queryShow  查询时显示的部分
     * @param queryCondition  查询时需要的条件
     * @param query  查询条件
     * @return query  已修改成正确查询格式的查询条件
     */
    public HashMap<String, String> getQueryCondition(String[] queryShow, String[] queryCondition, 
    		HashMap<String, String> query);
    
    /**
     * 根据条件获取查询后显示需要的值
     * @param queryShow  查询时显示的部分
     * @param queryCondition  查询时需要的条件
     * @param query  查询条件
     * @return query  已修改成显示格式的查询条件
     */
    public HashMap<String, String> getQueryShow(String[] queryShow, String[] queryCondition, 
    		HashMap<String, String> query);
    
    /**
     * 根据机构和角色名称获取用户集合
     * @param organizationId
     * @param roleName
     * @return
     */
    public List<CmUser> getReferanceRole(String organizationId, String roleName);
    
    /**
	 * 根据deleteFlag进行删除
	 * @param tableName         对应实体名称
	 * @param tableId           实体ID标识
	 * @param tableIdVal        实体ID值
	 */
	public void deleteFlag(String tableName, String tableId, String tableIdVal);
	
	
	
	/**
     * 
     *	检索分页
     *  @param sysPageInfo 分页对象
     *  @param boname 实体bean
     *  @return 返回分页对象
     *  @author   LengGaoFeng
     */
    public SysPageInfo searchPageInfoByQuery(SysPageInfo sysPageInfo,HashMap<String,String> query,String boname);
    
    /**
     * 
     *	检索数据 如果query不为空的时候不根据条件检索，否则根据query携带条件检索数据。
     *  @param <T>  返回所查询的集合
     *  @param sysPageInfo分页对象
     *  @param query 查询条件
     *  @param sysOrderByInfo 排序条件。
     *  @param boname 实体bean
     *  @return 返回的集合
     *  @author   LengGaoFeng
     */
    public <T extends Object> List<T> searchQuery(SysPageInfo sysPageInfo, 
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo,String boname);
    
    
  /**
   * 检索数据，如果有多个条件的时候则使用此方法。适用于后台向前台传递数据时提前将顺序排列好。 
   * @param <T> 
   * @param sysPageInfo 分页对象
   * @param query 查询条件
   * @param order 排序条件
   * @param boname
   * @return
   */
    public <T extends Object> List<T> searchQuery(SysPageInfo sysPageInfo, 
			HashMap<String, String> query, String order,String boname);


      /**
     * 自定义查询 检索分页 主要扩展排序条件
     * @author chenleilei 
     * @param <T>
     * @param sysPageInfo 分页
     * @param hql 查询语句
     * @param query 条件
     * @param order 自定义排序，可以排多个
     * @return
     */
	public <T extends Object> List<T> findPageByQueryNoLog(SysPageInfo sysPageInfo,
			String hql, HashMap<String, String> query,
			String order);
	
	/**
	 * 根据单条件查询唯一结果
	 * @param <T>
	 * @param beanName
	 * @param propertyName
	 * @param propertyVal
	 * @return
	 */
	public <T extends Object> T findUniqueBySingleQuery(String beanName, String propertyName, String propertyVal);
	
	/**
	 * 根据模块资源生成序列号
	 * @param moduleResource
	 * @return generatedSN
	 */
	public String getGeneratedSerialNumber(String moduleResource);
	
	/**
	 * 保存附件
	 * @param businessId      对应业务表的ID (一般为业务主表的Id)
	 * @param tableName       对应业务表的表名转化得来的业务模块名称
	 * @param filePath        指定格式的附件字符串组合
	 */
	public void saveFiles(String businessId, String tableName, String filePath);
	/**
	 * 
	 * 查询fromid模块的所有附件
	 * @param fromid
	 * @return
	 * Oct 10, 20116:42:26 PM
	 * @author niujingwei
	 */
	public List<CmAttachment> searchFiles(String fromid);
	/**
	 * 
	 * 通过业务Id删除附件
	 * @param fromid
	 * Oct 17, 20115:14:14 PM
	 * @author niujingwei
	 */
	public void deleteFileByBusinessId(String fromid);
	
	/**
	 * 获取机构名称
	 * @param orgId    机构ID
	 * @return    机构名称
	 */
	public String getOrganizationNameById(String orgId);
	
	/**
	 * 获取用户名称
	 * @param userId   用户Id
	 */
	public String getUserNameById(String userId);
	
	/**
	 * 删除附件信息  根据传进来的fromids
	 * @param fromids	
	 * @param pattern fromids分隔标识
	 * @return
	 */
	public List<CmAttachment> deleteFiles(String fromids, String pattern);
	
	/**
	 * 删除流程和业务关系表
	 * @param businessId
	 * Oct 25, 20111:34:24 AM
	 * @author niujingwei
	 */
	public void deleteBasicWorkflowByBusinessId(String businessId);
	/**
	 * 
	 * 添加
	 * @param businessId
	 * @param processInatanceId
	 * Oct 25, 20111:35:32 AM
	 * @author niujingwei
	 */
	public void insertBasicWorkflow(String businessId,String processInatanceId);
	
	/**
	 * 
	 * 添加
	 * @param businessId
	 * @param processInatanceId
	 * Oct 25, 20111:35:32 AM
	 * @author niujingwei
	 */
	public void insertBasicWorkflow(String businessId,String processInatanceId,String tableName);
	
	/**
	 * 
	 * 查看业务流程关系表
	 * @param businessId
	 * @return
	 * Oct 27, 20119:53:11 AM
	 * @author niujingwei
	 */
	public BasicWorkflow  findBasicWorkflow(String businessId);
	
	//===========add by jilili 2011-11-1 start========================//
	/**
	 * 根据来源，与表名查找相应的附件
	 * @param sectItemid
	 * @param string
	 * @return
	 */
	List<CmAttachment> searchFiles(String fromid, String fromtablename);
	
	/**
	 * 根据来源表名与需要的条数查找附件
	 * @param fromTableName
	 * @param columns
	 * @return
	 */
	public List<CmAttachment> getFilesBySubject(String fromTableName, int columns);
	//===========add by jilili 2011-11-1 end========================//
	
	/**
	 * 查询可用的人
	 * @param sysPageInfo       分页条件
	 * @param customHql         自定义的查询部分
	 * @param query             查询条件
	 * @param sysOrderByInfo    排序条件
	 */
	public List<CmPeople> activePerson(SysPageInfo sysPageInfo, String customHql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 查询可用的人的条数
	 * @param sysPageInfo       分页条件
	 * @param customHql         自定义的查询部分
	 * @param query             查询条件
	 * @return
	 */
	public long activePersonCount(SysPageInfo sysPageInfo, String customHql,
			HashMap<String, String> query);
	
	/**
	 * 根据单条件查询集合结果
	 * @param boname 表名
	 * @param attributename 条件属性名
	 * @param attributevalue 条件属性值
	 * @param order 排序字符串
	 * @return List集合
	 */
	public <T extends Object> List<T> findBySingleQuery(String beanName, String propertyName, String propertyVal);
	
	/**
	 * 查询可用的用户
	 * @param sysPageInfo
	 * @param customHql
	 * @param query
	 * @param sysOrderByInfo
	 * @return
	 */
	public List<SysUserOrgRelation> activeUser(SysPageInfo sysPageInfo, String customHql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 查询可用的用户条数
	 * @param sysPageInfo
	 * @param customHql
	 * @param query
	 * @return
	 */
	public long activeUserCount(String customHql, HashMap<String, String> query);
}
