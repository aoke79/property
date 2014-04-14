package com.sinoframe.business.service;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SecSerialnumber;
import com.sinoframe.bean.SysErrorLog;
import com.sinoframe.bean.SysInsertLog;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysQueryLog;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysUpdateLog;
import com.sinoframe.bean.SysUserOperateInfo;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.business.IService;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.FileOperate;
import com.sinoframe.common.util.Util;
import com.sinoframe.dao.IPublicDao;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.security.basicworkflow.bean.BasicWorkflow;
import com.sms.training.qualification.bean.AhUser;

@Service("BaseBS")
public class BaseBS implements IService {
	
	private IPublicDao publicDAO;
	@Resource
	public void setPublicDAO(IPublicDao publicDAO) {
		this.publicDAO = publicDAO;
	}
	
	@Override
	public <T> T save(T entity) {
		entity = this.publicDAO.save(entity);
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(cmUser != null) {
			userId = cmUser.getUserId();
		}
		this.getInsertLog().info(this.readObject(entity) + "#" + userId);
		return entity;
	}
	@Override
	public <T> List<T> saveList(List<T> list) {
		List<T> returnList = this.publicDAO.saveList(list);
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(cmUser != null) {
			userId = cmUser.getUserId();
		}
		for(T t :returnList){
			this.getInsertLog().info(this.readObject(t) + "#" + userId);
		}
		return returnList;
	}
	@Override
	public <T> T saveOrUpdate(T entity) {
		return this.publicDAO.saveOrUpdate(entity); 
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T update(T entity,Serializable id) {
		Object oldt = null;
	 	try {
	 		oldt =(T) this.findById(entity.getClass(), id);
			oldt = this.copy(oldt, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.publicDAO.update((T)oldt);
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(cmUser != null) {
			userId = cmUser.getUserId();
		}
		this.getUpdateLog().info(this.readObject(entity) + "#" + userId);
		return (T)oldt;
	}
	
	/**
	 * copy  new entity to old entity return old entity
	 * @param oldEB
	 * @param newEB
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object copy(Object oldEB, Object newEB) throws Exception {
		Field[] oldEBFields = oldEB.getClass().getDeclaredFields();
		Field[] newEBFields = newEB.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(oldEBFields, true);
		AccessibleObject.setAccessible(newEBFields, true);
		Object value = null;
		String name = null;
		String returnType = null;
		for (int i = 0; i < newEBFields.length; i++) {
			name = newEBFields[i].getName();
			returnType = newEBFields[i].getType().getName();
			for (int j = 0; j < oldEBFields.length; j++) {
				if (name.equals(oldEBFields[j].getName())
						&& returnType
								.equals(oldEBFields[j].getType().getName())) {
					String modifier = Modifier.toString(oldEBFields[j]
							.getModifiers());
					if (modifier != null && modifier.indexOf("final") > -1)
						break;
					else {
						value = newEBFields[i].get(newEB);
						if (value != null) {
							if (value instanceof Collection) {
								if (((Collection) value).size() != 0) {
									oldEBFields[j].set(oldEB, value);
								}
							} else {
								oldEBFields[j].set(oldEB, value);
							}
						}
						break;
					}
				}
			}
		}
		return oldEB;
	}
	
	/**
	 * readObject 读取EntityBean 中的数据
	 * @param object
	 * @return String entityBean Name +#+fieldName+：+fieldvalue 
	 */
	public String readObject(Object object) {
		//Field[] Fields = object.getClass().getFields();//获取所有公有属性其中包括继承来的和自己的
		Field[] Fields = object.getClass().getDeclaredFields();//获取本类所有属性公有私有都能获取。但是不能获取继承的。
		StringBuffer sb = new StringBuffer();
		sb.append(object.getClass().getName()+"#");
		for(Field field :Fields){
			//String fieldType = field.getType().getSimpleName();
			String fieldName = field.getName();
			try{
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
				Method fieldGetMet = pd.getReadMethod();//获得读方法
				if(field.getType()==java.lang.String.class
					||field.getType()==java.lang.Integer.class
					||field.getType()==java.lang.Boolean.class
					||field.getType()==java.lang.Byte.class
					||field.getType()==java.lang.Long.class
					||field.getType()==java.lang.Double.class
					||field.getType()==java.lang.Short.class
					||field.getType()==java.lang.Float.class)
				{
					sb.append(fieldName+":"+fieldGetMet.invoke(object).toString()+",");
				}else if (field.getType()==java.util.Date.class) {
					try {
						sb.append(fieldName+":"+DateTool.parse(fieldGetMet.invoke(object).toString(), DateTool.DEFAULT_DATETIME_FORMAT)+"," );
					} catch (Exception e0) {
						sb.append(fieldName+":"+DateTool.parse(fieldGetMet.invoke(object).toString(), DateTool.DEFAULT_DATE_FORMAT)+"," );
					}
						sb.append(fieldName+":"+fieldGetMet.invoke(object)+",");
				}else if(field.getType().getSimpleName().equals(object.getClass().getSimpleName()+"Id")){
					Object idObject = fieldGetMet.invoke(object);
					//Field[] Fields = object.getClass().getFields();//获取所有公有属性其中包括继承来的和自己的
					Field[] idFields = idObject.getClass().getDeclaredFields();//获取本类所有属性公有私有都能获取。但是不能获取继承的。
					sb.append(fieldName+"_"+idObject.getClass().getName()+":");
					sb.append("{");
					for(Field idfield :idFields){
						String idfieldName = idfield.getName();
						try{
							PropertyDescriptor idpd = new PropertyDescriptor(idfieldName, idObject.getClass());
							Method idfieldGetMet = idpd.getReadMethod();//获得读方法
							if(idfield.getType()==java.lang.String.class
								||idfield.getType()==java.lang.Integer.class
								||idfield.getType()==java.lang.Boolean.class
								||idfield.getType()==java.lang.Byte.class
								||idfield.getType()==java.lang.Long.class
								||idfield.getType()==java.lang.Double.class
								||idfield.getType()==java.lang.Short.class
								||idfield.getType()==java.lang.Float.class)
							{
								sb.append(idfieldName+":"+idfieldGetMet.invoke(idObject).toString()+",");
							}
						}catch(Exception e){
							continue;
						}
					}
					sb.delete(sb.length()-1,sb.length());
					sb.append("},");
				}
			}catch(Exception e){
				continue;
			}
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	
	@Override
	public <T> void delete(T entity) {
		this.publicDAO.delete(entity);
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(cmUser != null) {
			userId = cmUser.getUserId();
		}
		this.getDeleteLog().info(this.readObject(entity) + "#" + userId);
	}
	@Override
	public <T> void deleteById(Class<T> entityClass, Serializable id) {
		T t = this.findById(entityClass, id);
		this.publicDAO.delete(t);
		AhUser ahUser = (AhUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(ahUser != null) {
			userId = ahUser.getUseruuid();
		}
		this.getDeleteLog().info(this.readObject(t) + "#" + userId);
	}
	@Override
	public <T> void deleteByIds(Class<T> entityClass,String ids) {
		String[] idArr = ids.split(",");
		this.deleteByIds(entityClass, idArr);
	}
	@Override
	public <T> void deleteByIds(Class<T> entityClass,Serializable ids[]) {
		for (Serializable id : ids) {
			if (null != id && !"".equals(id)) {
				this.deleteById(entityClass, id);
			}
		}
	}
	@Override
	public <T> T findById(Class<T> entityClass, Serializable id) {
		return this.publicDAO.findById(entityClass, id);
	}
	@Override
	public long getCountByHQL(String hql) {
		return this.publicDAO.getCountByHQL(hql);
	}
	public int getCountByHql(String hql) {
		return this.publicDAO.getCountByHql(hql);
	}
	public int getCountByHql(String hql, HashMap<String,String> query) {
		hql += this.getHqlbyMap(query);
		return this.getCountByHql(hql);
	}
	@Override
	public long getCountByHQL(String hql, HashMap<String,String> query) {
		hql+=this.getHqlbyMap(query);
		return this.getCountByHQL(hql);
	}
	
	@Override
	public <T> List<T> findPageByQuery(String hql) {
		this.getSelectLog().info(hql);
		return  this.publicDAO.findPageByQuery(hql);
	}
	@Override
	public <T> List<T> findPageByQuery(SysPageInfo sysPageInfo, String hql) {
		this.getSelectLog().info(hql);
		return this.publicDAO.findPageByQuery(sysPageInfo, hql);
	}
	@Override
	public <T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo,
			String hql, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo) {
		hql += this.getHqlbyMap(query);
		if(sysOrderByInfo!=null){
			if (sysOrderByInfo.getOrderColumn() != null
					&& !sysOrderByInfo.getOrderColumn().equals("")) {
				
				if(sysOrderByInfo.getLeftJoinColumn() != null && !"".equals(sysOrderByInfo.getLeftJoinColumn())){
					String hql1 = hql.substring(0, hql.indexOf("where"));
					String hql2 = hql.substring(hql.indexOf("where"));
					hql = "select bo " + hql1 + " left join bo." + sysOrderByInfo.getLeftJoinColumn() + " ss " + hql2;
					if(sysOrderByInfo.isIfDate()){
						hql += " ORDER BY ss." + sysOrderByInfo.getOrderColumn() + " " +
						sysOrderByInfo.getOrderAsc();
					}else{
						hql += " ORDER BY nlssort(ss." + sysOrderByInfo.getOrderColumn()
								+ ",'NLS_SORT=SCHINESE_PINYIN_M') "
								+ sysOrderByInfo.getOrderAsc(); 
					}
				}else{
					//判断是否存在
					if(sysOrderByInfo.getOrderColumn().contains(",")
							&& sysOrderByInfo.getOrderAsc().contains(",")){
						String[] orderCol = sysOrderByInfo.getOrderColumn().split(",");
						String[] orderType = sysOrderByInfo.getOrderAsc().split(",");
						String orderField = "";
						for (int i = 0; i < orderType.length; i++) {
							orderField += orderCol[i] + " " + orderType[i] + ",";
						}
						if(orderField.endsWith(",")){
							orderField = orderField.substring(0, orderField.lastIndexOf(","));
						}
						if(sysOrderByInfo.isIfDate()){
							hql += " ORDER BY " + orderField;
						} else {
							hql += " ORDER BY nlssort(" + orderField + ", 'NLS_SORT=SCHINESE_PINYIN_M')";
						}
					} else {
						if(sysOrderByInfo.isIfDate()){
							hql += " ORDER BY " + sysOrderByInfo.getOrderColumn() + " " +
							sysOrderByInfo.getOrderAsc();
						}else{
							hql += " ORDER BY nlssort(" + sysOrderByInfo.getOrderColumn()
									+ ",'NLS_SORT=SCHINESE_PINYIN_M') "
									+ sysOrderByInfo.getOrderAsc(); 
						}
					}
					
				}
			}
		}
		this.getSelectLog().info(hql);
		return this.findPageByQuery(sysPageInfo, hql);
	}

	@Override
	public <T extends Object> List<T> findPageByQuery(SysPageInfo sysPageInfo,
			String hql, String customHql, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo) {
		hql += this.getHqlbyMap(query) + customHql;
		if(sysOrderByInfo!=null){
			if (sysOrderByInfo.getOrderColumn() != null
					&& !sysOrderByInfo.getOrderColumn().equals("")) {
				if(sysOrderByInfo.isIfDate()){
					 hql += " ORDER BY " + sysOrderByInfo.getOrderColumn() + " " +
						sysOrderByInfo.getOrderAsc();
				}else{
					/*hql += " ORDER BY nlssort(" + sysOrderByInfo.getOrderColumn()
							+ ",'NLS_SORT=SCHINESE_PINYIN_M') "
							+ sysOrderByInfo.getOrderAsc(); */
					
					 hql += " ORDER BY " + sysOrderByInfo.getOrderColumn() + " " +
						sysOrderByInfo.getOrderAsc();
				}
			}
		}
		this.getSelectLog().info(hql);
		return this.findPageByQuery(sysPageInfo, hql);
	}
	
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
			String hql, HashMap<String, String> query, String orderByInfo) {
		StringBuilder hqlString = new StringBuilder();
		hqlString.append(hql);
		hqlString.append(this.getHqlbyMap(query));
		if(!orderByInfo.equals("")){
			hqlString.append(" ORDER BY ");
			hqlString.append(orderByInfo);
		}
		return this.findPageByQuery(sysPageInfo, hqlString.toString());
	}
	
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
			String hql, String customHql, HashMap<String, String> query, String orderByInfo) {
		StringBuilder hqlString = new StringBuilder();
		hqlString.append(hql);
		hqlString.append(customHql);
		hqlString.append(this.getHqlbyMap(query));
		if(!orderByInfo.equals("")){
			hqlString.append("ORDER BY ");
			hqlString.append(orderByInfo);
		}
		return this.findPageByQuery(sysPageInfo, hqlString.toString());
	}
	
	@Override
	public void executeUpdate(String hql){
		this.publicDAO.executeUpdate(hql);
		this.getExecuteLog().info(hql);
	}
	@Override
	public <T> T saveNoLog(T entity) {
		entity = this.publicDAO.save(entity);
		return entity;
	}
	@Override
	@SuppressWarnings("unused")
	public <T> void saveListNoLog(List<T> list) {
		List<T> returnList = this.publicDAO.saveList(list);
	}
	@Override
	public <T> T saveOrUpdateNoLog(T entity) {
		return this.publicDAO.saveOrUpdate(entity); 
	}
	@Override
	@SuppressWarnings("unchecked")
	public <T> void updateNoLog(T entity,Serializable id) {
		Object oldt =(T) this.findById(entity.getClass(), id);
		try {
			oldt = this.copy(oldt, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.publicDAO.update((T)oldt);
	}
	
	@Override
	public <T> void deleteNoLog(T entity) {
		this.publicDAO.delete(entity);
	}
	@Override
	public <T> void deleteByIdNoLog(Class<T> entityClass, Serializable id) {
		T t = this.findById(entityClass, id);
		this.publicDAO.delete(t);
	}
	@Override
	public <T> void deleteByIdsNoLog(Class<T> entityClass,String ids) {
		String[] idArr = ids.split(",");
		this.deleteByIdsNoLog(entityClass, idArr);
	}
	@Override
	public <T> void deleteByIdsNoLog(Class<T> entityClass,Serializable ids[]) {
		for (Serializable id : ids) {
			if (null != id && !"".equals(id)) {
				this.deleteByIdNoLog(entityClass, id);
			}
		}
	}
	
	@Override
	public <T> List<T> findPageByQueryNoLog(String hql) {
		return  this.publicDAO.findPageByQuery(hql);
	}
	@Override
	public <T> List<T> findPageByQueryNoLog(SysPageInfo sysPageInfo, String hql) {
		return this.publicDAO.findPageByQuery(sysPageInfo, hql);
	}
	@Override
	public <T extends Object> List<T> findPageByQueryNoLog(SysPageInfo sysPageInfo, String hql, HashMap<String,String> query,
			SysOrderByInfo sysOrderByInfo) {
		hql+= this.getHqlbyMap(query);
		if(sysOrderByInfo.getOrderColumn() != null && !sysOrderByInfo.getOrderColumn().equals("")) {
			hql  += " ORDER BY nlssort(" + sysOrderByInfo.getOrderColumn() + ",'NLS_SORT=SCHINESE_PINYIN_M') " + sysOrderByInfo.getOrderAsc(); //orecle
			// hql  += " ORDER BY " + sysOrderByInfo.getOrderColumn() + " " + sysOrderByInfo.getOrderAsc();//mySql
		}
		return this.findPageByQueryNoLog(sysPageInfo, hql);
	}
	
	@Override
	public void executeUpdateNoLog(String hql){
		this.publicDAO.executeUpdate(hql);
	}

	/**
	 * 对带有操作的项进行记录
	 */
	public void operateSave(String operateid,String describe, String userId){
		SysUserOperateInfo sysUserOperateInfo = new SysUserOperateInfo();
		sysUserOperateInfo.setOperateDate(DateTool.getDateTime());
		sysUserOperateInfo.setOperateid(operateid);
		sysUserOperateInfo.setDescribe(describe);
		if(userId != null){
			sysUserOperateInfo.setUserid(userId);
		}
		this.save(sysUserOperateInfo);
	}
	
	/**
	 * 根据URL相关信息记录操作
	 * @param url
	 */
	public void operateLogger(String url) {
		//根据URL串查询对应操作相关内容
		String hql = "from SysOperate where operateAction = '" + url + "'";
		List<SysOperate> entityList = this.findPageByQuery(hql);
		String operateId = "";
		String operateName = "";
		for (SysOperate sysOperate : entityList) {
			operateId = sysOperate.getId();
			operateName = sysOperate.getOperateName();
		}
		
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String description = cmUser.getName() + "执行了" + operateName + "操作";
		this.operateSave(operateId, description, cmUser.getUserId());
	}
	
	@Override
	public Log getSelectLog() {
		return LogFactory.getLog("SELECTLOG");
	}
	@Override
	public Log getUpdateLog() {
		return LogFactory.getLog("UPDATELOG");
	}
	@Override
	public Log getInsertLog() {
		return LogFactory.getLog("INSERTLOG");
	}
	@Override
	public Log getErrorLog() {
		return LogFactory.getLog("ERRORLOG");
	}
	@Override
	public Log getDeleteLog() {
		return LogFactory.getLog("DELETELOG");
	}
	@Override
	public Log getExecuteLog() {
		return LogFactory.getLog("EXECUTELOG");
	}
	

	/**
	 * readErrorFileByLines
	 * @param fileName
	 * @throws ParseException
	 */
    public void readErrorFileByLines(String fileName) throws ParseException {
    	File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
           
            while ((tempString = reader.readLine()) != null) {
            	SysErrorLog sysErrorLog = new SysErrorLog();
            	Pattern p = Pattern.compile("#");
            	String[] tempLog = p.split(tempString);
            	//时间
            	sysErrorLog.setHappenTime(DateTool.parse(tempLog[0], DateTool.DEFAULT_DATETIME_FORMAT));
            	//产生错误的类文件
            	sysErrorLog.setErrorFilename(tempLog[2]);
            	//错误内容
            	sysErrorLog.setErrorMessage(tempLog[5] == null ? "" : tempLog[5]);
            	//错误模块
            	sysErrorLog.setModuleName(tempLog[6]);
            	this.saveNoLog(sysErrorLog);
            	
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    /**
     * readInsertFileByLines
     * @param fileName
     * @throws ParseException
     */
    public void readInsertFileByLines(String fileName) throws ParseException {
    	File file = new File(fileName);
    	BufferedReader reader = null;
    	try {
    		reader = new BufferedReader(new FileReader(file));
    		String tempString = null;
    		while ((tempString = reader.readLine()) != null) {
    			SysInsertLog sysInsertLog = new SysInsertLog();
    			Pattern p = Pattern.compile("#");
    			String[] tempLog = p.split(tempString);
    			//添加时间
    			sysInsertLog.setAddTime(DateTool.parse(tempLog[0], DateTool.DEFAULT_DATETIME_FORMAT));
    			//用户
    			sysInsertLog.setUserId(tempLog[5]);
    			//模块名称
            	String moduleName = "";
            	//不能直接使用"."进行分隔，容易产生不必要的错误，需要进行转换后再分隔
            	String[] modArray = tempLog[2].replace(".", "&").split("&");
            	moduleName = modArray[modArray.length-1];
            	sysInsertLog.setModuleName(moduleName);
            	//SQL
            	String insertSQL = this.smatchInsertSQL(moduleName, tempLog[4]);
            	sysInsertLog.setAddSql(insertSQL);
    			this.saveNoLog(sysInsertLog);
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (reader != null) { 
    			try {
    				reader.close();
    			} catch (IOException e1) {
    			}
    		}
    	}
    }
    
    /**
     * readUpdateFileByLines
     * @param fileName
     * @throws ParseException
     */
    public void readUpdateFileByLines(String fileName) throws ParseException {
    	File file = new File(fileName);
    	BufferedReader reader = null;
    	try {
    		reader = new BufferedReader(new FileReader(file));
    		String tempString = null;
    		while ((tempString = reader.readLine()) != null) {
    			SysUpdateLog sysUpdateLog = new SysUpdateLog();
    			Pattern p = Pattern.compile("#");
    			String[] tempLog = p.split(tempString);
    			//时间
    			sysUpdateLog.setUpdateTime(DateTool.parse(tempLog[0], DateTool.DEFAULT_DATETIME_FORMAT));
    			//用户
    			sysUpdateLog.setUserId(tempLog[4]);
    			//模块名称
            	String moduleName = "";
            	//不能直接使用"."进行分隔，容易产生不必要的错误，需要进行转换后再分隔
            	String[] modArray = tempLog[2].replace(".", "&").split("&");
            	moduleName = modArray[modArray.length-1];
            	sysUpdateLog.setModuleName(moduleName);
            	//SQL
            	String updateSQL = this.smatchUpdateSQL(moduleName, tempLog[3]);
            	sysUpdateLog.setUpdateSql(updateSQL);
    			this.saveNoLog(sysUpdateLog);
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (reader != null) {
    			try {
    				reader.close();
    			} catch (IOException e1) {
    			}
    		}
    	}
    }
    
    /**
     * readDeleteFileByLines
     * @param fileName
     * @throws ParseException
     */
    public void readDeleteFileByLines(String fileName) throws ParseException {
    	File file = new File(fileName);
    	BufferedReader reader = null;
    	try {
    		reader = new BufferedReader(new FileReader(file));
    		String tempString = null;
    		while ((tempString = reader.readLine()) != null) {
    			SysUpdateLog sysUpdateLog = new SysUpdateLog();
    			Pattern p = Pattern.compile("#");
    			String[] tempLog = p.split(tempString);
    			//时间
    			sysUpdateLog.setUpdateTime(DateTool.parse(tempLog[0], DateTool.DEFAULT_DATETIME_FORMAT));
    			//用户
    			sysUpdateLog.setUserId(tempLog[4]);
    			//模块名称
    			String moduleName = "";
            	//不能直接使用"."进行分隔，容易产生不必要的错误，需要进行转换后再分隔
            	String[] modArray = tempLog[2].replace(".", "&").split("&");
            	moduleName = modArray[modArray.length-1];
            	sysUpdateLog.setModuleName(moduleName);
            	//SQL
            	String deleteSQL = this.smatchDeleteSQL(moduleName, tempLog[3]);
            	sysUpdateLog.setUpdateSql(deleteSQL);
    			this.saveNoLog(sysUpdateLog);
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (reader != null) {
    			try {
    				reader.close();
    			} catch (IOException e1) {
    			}
    		}
    	}
    }
    
    /**
     * readSelectFileByLines
     * @param fileName
     * @throws ParseException
     */
    public void readSelectFileByLines(String fileName) throws ParseException {
    	File file = new File(fileName);
    	BufferedReader reader = null;
    	try {
    		reader = new BufferedReader(new FileReader(file));
    		String tempString = null;
    		Map<String, Integer> capsule = new HashMap<String, Integer>();
    		while ((tempString = reader.readLine()) != null) {
    			SysQueryLog sysQueryLog = new SysQueryLog();
    			Pattern p = Pattern.compile("#");
    			String[] tempLog = p.split(tempString);
    			//时间
    			sysQueryLog.setVisitDate(DateTool.parse(DateTool.getYesterday(), DateTool.DEFAULT_DATE_FORMAT));
    			//模块名称
    			String logSQL = tempLog[4];
            	String moduleName = "";
            	//获取模块名称
            	if(logSQL.startsWith("select")){
            		logSQL = logSQL.substring(logSQL.indexOf("from")).replace("from", "").trim();
            		moduleName = logSQL.substring(0,logSQL.indexOf(" ", 1));
            		
            	} else {
            		logSQL = logSQL.replace("from", "").trim();
            		moduleName = logSQL.substring(0,logSQL.indexOf(" ", 1));
            	}
            	//根据模块名称统计访问次数
            	if(capsule.containsKey(moduleName)){
            		capsule.put(moduleName, capsule.get(moduleName)+1);
            	} else {
            		capsule.put(moduleName, 1);
            	}
            	//模块名称及对应的访问次数
            	for(String key : capsule.keySet()){
            		sysQueryLog.setModuleName(key);
            		sysQueryLog.setVisitCount(capsule.get(key));
            	}
    			this.saveNoLog(sysQueryLog);
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (reader != null) {
    			try {
    				reader.close();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    		}
    	}
    }
    /**
     * getHqlbyMap
     * @param query
     * @return hql String
     */
    public String getHqlbyMap(HashMap<String,String> query){
    	StringBuffer sb = new StringBuffer();
		if(query!=null&&query.size()>0){
			String keyName = "";
			for(Object key :query.keySet()){
				if(null!=query.get(key)&&query.get(key).toString().length()>0){
					keyName = key.toString().replaceAll("[*]", ".");
					String value = query.get(key).replaceAll("'", "''");
					sb.append(" and " + keyName.substring(keyName.lastIndexOf("_")+1) + " ");
					if("eq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" = '"+value+"' " );
					}else if("noteq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" <> '"+value+"' " );
					}
					else if("gt".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" > '"+value+"' " );
					}
					else if("gteq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" >= '"+value+"' " );
					}
					else if("lt".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" < '"+value+"' " );
					}
					else if("lteq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" <= '"+value+"' " );
					}
					else if("like".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" like '%"+value+"%' " );
					}
					else if("notin".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" not in ('"+value.toString().replaceAll(",", "','")+"')");
					}
					else if("in".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" in ('"+value.toString().replaceAll(",", "','")+"') " );
					}
					else if("null".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" is null" );
					}
					else if("notnull".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						sb.append(" is not null" );
					}
					else if("dtgt".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						if(value.toString().length()==19){
							sb.append(" > to_date( '"+value+"','yyyy-mm-dd HH24:mi:ss') " );
						}else if(value.toString().length()==10){
							sb.append(" > to_date('"+value+"','yyyy-mm-dd') " );
						}
					}
					else if("dtgteq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						if(value.toString().length()==19){
							sb.append(" >= to_date( '"+value+"','yyyy-mm-dd HH24:mi:ss') " );
						}else if(value.toString().length()==10){
							sb.append(" >= to_date('"+value+"','yyyy-mm-dd') " );
						}
					}
					else if("dtlt".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						if(value.toString().length()==19){
							sb.append(" < to_date( '"+value+"','yyyy-mm-dd HH24:mi:ss') " );
						}else if(value.toString().length()==10){
							sb.append(" < to_date('"+value+"','yyyy-mm-dd') " );
						}
					}
					else if("dtlteq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						if(value.toString().length()==19){
							sb.append(" <= to_date( '"+value+"','yyyy-mm-dd HH24:mi:ss') " );
						}else if(value.toString().length()==10){
							sb.append(" <= to_date('"+value+"','yyyy-mm-dd') " );
						}
					}
					else if("dteq".equals(keyName.substring(0,keyName.lastIndexOf("_")))){
						if(value.toString().length()==19){
							sb.append(" = to_date( '"+value+"','yyyy-mm-dd HH24:mi:ss') " );
						}else if(value.toString().length()==10){
							sb.append(" = to_date('"+value+"','yyyy-mm-dd') " );
						}
					}
				}
			}
		}
		return sb.toString();
    }
    @Override
    @SuppressWarnings("rawtypes")
	public void clear(Class c) {
		publicDAO.clear(c);
	}
    
    /**
     * 根据条件拼接一条插入语句
     * @param entityName
     * @param fixedValue 指定为固定格式的值语句
     * @return 语句
     */
    private String smatchInsertSQL(String entityName, String fixedValue) {
    	String insertPart = "insert into " + entityName;
    	//属性部分
    	String propertyPart = "";
    	//值部分
    	String valuePart = "";
    	
    	String[] entityArray = fixedValue.split(",");
    	for (int i = 0; i < entityArray.length; i++) {
    		String[] propertyArray = entityArray[i].split(":");
			
    		if(propertyArray.length == 2){
    			if(i == 0){
    				propertyPart += "(" + propertyArray[0] + ",";
    				valuePart += "('" + propertyArray[1] + "',";
    			} else if (i != entityArray.length -1){
    				propertyPart += propertyArray[0] + ",";
    				valuePart += "'" + propertyArray[1] + "',";
    			} else {
    				propertyPart += propertyArray[0] + ")";
    				valuePart += "'" + propertyArray[1] + "')";
    			}
    		} else {
    			if(i == 0){
    				propertyPart += "(" + propertyArray[0] + ",";
    				valuePart += "('',";
    			} else if (i != entityArray.length -1){
    				propertyPart += propertyArray[0] + ",";
    				valuePart += "'',";
    			} else {
    				propertyPart += propertyArray[0] + ")";
    				valuePart += "'')";
    			}
    		}
		}
    	return insertPart + propertyPart + " values" + valuePart;
    }
    
    /**
     * 根据条件拼接一条更新语句
     * @param entityName
     * @param fixedValue 指定为固定格式的值语句
     * @return SQL语句
     */
    private String smatchUpdateSQL(String entityName, String fixedValue) {
    	String updateSQL = "update " + entityName;
    	
    	String[] entityArray = fixedValue.split(",");
    	for (int i = 0; i < entityArray.length; i++) {
    		String[] propertyArray = entityArray[i].split(":");
			if(propertyArray.length == 2){
				if(i != entityArray.length -1) {
					updateSQL += propertyArray[0] + "='" + propertyArray[1] + "', ";
				} else {
					updateSQL += propertyArray[0] + "='" + propertyArray[1] + "' ";
				}
			} else {
				if(i != entityArray.length -1) {
					updateSQL += propertyArray[0] + "='', ";
				} else {
					updateSQL += propertyArray[0] + "='' ";
				}
			}
		}
    	//获取对应实体的ID及其对应的value
    	String entityId = entityArray[0].split(":")[0];
    	String entityIdVal = entityArray[0].split(":")[1];
    	updateSQL = updateSQL + "where " + entityId + "='" + entityIdVal + "'";
    	return updateSQL;
    }
    
    /**
     * 根据条件拼接一条删除语句
     * @param entityName
     * @param fixedValue 指定为固定格式的值语句
     * @return SQL语句
     */
    private String smatchDeleteSQL(String entityName, String fixedValue) {
    	String deleteSQL = "delete from " + entityName;
    	//以固定格式的值语句格式判断是否是以"id_"开始，若是则为联合主键
    	//联合主键标识
    	boolean combine = false;
    	if(fixedValue.startsWith("id_")){
    		combine = true;
    		fixedValue = fixedValue.substring(fixedValue.indexOf("{")+1, fixedValue.length()-1);
    		
    	}
    	
    	String[] entityArray = fixedValue.split(",");
    	deleteSQL = deleteSQL + " where ";
		if(combine){
			for (int i = 0; i < entityArray.length; i++) {
				String[] propertyArray = entityArray[i].split(":");
				if(propertyArray.length == 2){
					if(i != entityArray.length -1){
						deleteSQL += propertyArray[0] + "='" + propertyArray[1] + "' and ";
	        		} else {
						deleteSQL += propertyArray[0] + "='" + propertyArray[1] + "'";
					}
				} else {
					if(i != entityArray.length -1){
						deleteSQL += propertyArray[0] + "='' and ";
	        		} else {
						deleteSQL += propertyArray[0] + "=''";
					}
				}
			}
		} else {
			deleteSQL += entityArray[0].split(":")[0] + "='" + entityArray[0].split(":")[1] + "'";
		}
    	return deleteSQL;
    }
    
    /**
     * save all the system logs to database
     * @throws ParseException
     */
    public void saveAllLogs() throws ParseException {
    	String insertFilePath = "d:/systemlog/insertlog.log";
    	String deleteFilePath = "d:/systemlog/deletelog.log";
    	String updateFilePath = "d:/systemlog/updatelog.log";
    	String queryFilePath = "d:/systemlog/selectlog.log";
    	String errorFilePath = "d:/systemlog/errorlog.log";
    	
    	this.readInsertFileByLines(insertFilePath);
    	this.readDeleteFileByLines(deleteFilePath);
    	this.readUpdateFileByLines(updateFilePath);
    	this.readSelectFileByLines(queryFilePath);
    	this.readErrorFileByLines(errorFilePath);
    }
	@Override
	public <T> List<T> findPageByQuery(SysOrderByInfo sysOrderByInfo, String hql) {
		
		
		if(sysOrderByInfo.isIfDate()){
			 hql += " ORDER BY " + sysOrderByInfo.getOrderColumn() + " " +
				sysOrderByInfo.getOrderAsc();
		}else{
			
			 hql += " ORDER BY " + sysOrderByInfo.getOrderColumn() + " " +
				sysOrderByInfo.getOrderAsc();
		}
		return findPageByQuery(hql);
	}
    
    /**
     * 根据当前用户获取机构信息
     * @param user
     * @return
     */
    public List<SysOrganization> getOrganizationByUser(CmUser user) {
    	List<SysUserOrgRelation> relationList = new ArrayList<SysUserOrgRelation>();
    	List<SysOrganization> orgList = new ArrayList<SysOrganization>();
    	String hql = "";
    	if(user != null && !user.equals("")){ 
    		hql = "from SysUserOrgRelation where cmUser.userId = '" + user.getUserId() + "'";
			relationList = this.findPageByQuery(hql);
    		for (SysUserOrgRelation sysUserOrgRelation:relationList) {
				orgList.add(sysUserOrgRelation.getSysOrganization());
			}
    	}
    	return orgList;
    	
    }
    
    /**
     * 根据当前用户获取角色信息
     * @param user
     * @return
     */
    public List<SysRole> getRoleByUser(CmUser user) {
    	List<SysRelationAccountRole> relationList = new ArrayList<SysRelationAccountRole>();
    	List<SysRole> roleList = new ArrayList<SysRole>();
    	String hql = "";
    	if(user != null && !user.equals("")){
    		hql = "from SysRelationAccountRole where cmUser.userId = '" + user.getUserId() + "'";
    		relationList = this.findPageByQuery(hql);
    		for (SysRelationAccountRole sysRelationAccountRole : relationList) {
				roleList.add(sysRelationAccountRole.getSysRole());
			}
    	}
    	return roleList;
    }
    
    /**
     * 获取查询时需要的条件，并转换成查询时需要的格式
     * @param queryShow  查询时显示的部分
     * @param queryCondition  查询时需要的条件
     * @param query  查询条件
     * @return query  已修改成正确查询格式的查询条件
     */
    public HashMap<String, String> getQueryCondition(String[] queryShow, String[] queryCondition, 
    		HashMap<String, String> query) {
    	
    	for (int i = 0; i < queryCondition.length; i++) {
        	String conditionValue = query.get(queryShow[i]);
        	if(conditionValue != null && !conditionValue.equals("")){
    			query.put(queryCondition[i], conditionValue);
    			//移除显示需要的部分，避免产生查询了错误字段的错误
    			query.remove(queryShow[i]);
    		}
        }
    	return query;
    }
    
    /**
     * 根据条件获取查询后显示需要的值
     * @param queryShow  查询时显示的部分
     * @param queryCondition  查询时需要的条件
     * @param query  查询条件
     * @return query  已修改成显示格式的查询条件
     */
    public HashMap<String, String> getQueryShow(String[] queryShow, String[] queryCondition, 
    		HashMap<String, String> query) {
    	for (int i = 0; i < queryCondition.length; i++) {
        	String conditionValue = query.get(queryCondition[i]);
        	if(conditionValue != null && !conditionValue.equals("")){
    			query.put(queryShow[i], conditionValue);
    			//移除查询时需要的部分
    			query.remove(queryCondition[i]);
    		}
        }
    	return query;
    }
    
    /**
     * 根据机构和角色名称获取用户集合
     * @param organizationId
     * @param roleName
     * @return
     */
    public List<CmUser> getReferanceRole(String organizationId, String roleName) {
    	List<CmUser> referanceList = new ArrayList<CmUser>();
    	//查询当前机构下所有用户
    	String hql = "from SysUserOrgRelation where sysOrganization.id = '" + organizationId +
    				 "' and sysOrganization.state = '1' and cmUser.state = '1' ";
    	List<SysUserOrgRelation> allUserList = this.findPageByQuery(hql);
    	if(!allUserList.isEmpty()){
    		
    		for (SysUserOrgRelation sysUserOrgRelation : allUserList) {
    			//判断是否指定角色
    			if(roleName.equals("")){
    				if(!referanceList.contains(sysUserOrgRelation.getCmUser())){
    					referanceList.add(sysUserOrgRelation.getCmUser());
    				}
    			} else {
    				//指定角色时只获取对应角色的用户
    				Set<SysRelationAccountRole> userRoleSet = sysUserOrgRelation.getCmUser().getSysRelationAccountRoles();
    				if(!userRoleSet.isEmpty()){
    					for (SysRelationAccountRole sysRelationAccountRole : userRoleSet) {
    						if(sysRelationAccountRole.getSysRole().getRoleName().contains(roleName)){
    							referanceList.add(sysUserOrgRelation.getCmUser());
    						}
    					}
    				}
    			}
    			
    		}
    	}
    	return referanceList;
    }
    
    /**
	 * 根据deleteFlag进行删除
	 * @param tableName         对应实体名称
	 * @param tableId           实体ID标识
	 * @param tableIdVal        实体ID值
	 */
	public void deleteFlag(String tableName, String tableId, String tableIdVal) {
		String hql = "update " + tableName + " t set t.deleteFlag = '1' where t." + tableId + "= '" + tableIdVal +"' ";
		this.executeUpdate(hql);
	}
	
	
	/**
	 * 检索分页
	 * @param sysPageInfo 分页对象 
	 * @param beanName 实体bean名称
	 * @return 返回分页对象 
	 * @author LengGaoFeng
	 */
	public SysPageInfo searchPageInfoByQuery(SysPageInfo sysPageInfo,
			HashMap<String, String> query, String beanName) {
		String hql = "select count(*) from " + beanName + " where 1=1";
		sysPageInfo.setMaxCount(this.getCountByHQL(hql, query));
		return sysPageInfo;
	}
	
	/**
	 * 根据条件检索数据
	 * @param <T>
	 * @param sysPageInfo 分页对象 
	 * @param query 检索条件
	 * @param sysOrderByInfo 排序条件
	 * @param beanName
	 * @return 返回检索的集合
	 * @author LengGaoFeng
	 */
	public <T extends Object> List<T> searchQuery(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo,
			String beanName) {
		List<T> list = new ArrayList<T>();
		String hql = "from " + beanName + " where 1=1";
		list = this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		return list;
	}
	
	/**
	 * 
	 * 根据条件检索数据
	 * 
	 * @param <T>
	 * @param sysPageInfo 分页条件
	 * @param query 条件
	 * @param order 排序字段及顺序
	 * @param beanName 实体名
	 * @return
	 * @author chenleilei
	 * @date 2011-5-24 下午09:07:17
	 */
	@Override
	public <T> List<T> searchQuery(SysPageInfo sysPageInfo,
			HashMap<String, String> query, String order, String beanName) {
		List<T> list = new ArrayList<T>();
		String hql = "from " + beanName + " where 1=1";
		list = this.findPageByQueryNoLog(sysPageInfo, hql, query, order);
		return list;
	}


	/**
	 * 根据自定义的排序进行查询
	 * @param sysPageInfo
	 * @param hql
	 * @param query
	 * @param order
	 */
	@Override
	public <T extends Object> List<T> findPageByQueryNoLog(SysPageInfo sysPageInfo,
			String hql, HashMap<String, String> query, String order) {
		hql += this.getHqlbyMap(query);
		if (order != null && !order.equals("")) {
			hql += " ORDER BY " +order;
		}
		this.getSelectLog().info(hql);
		return this.findPageByQuery(sysPageInfo, hql);
	}
	
	/**
	 * 根据单条件查询唯一结果
	 * @param <T>
	 * @param beanName
	 * @param propertyName
	 * @param propertyVal
	 * @return
	 */
	public <T extends Object> T findUniqueBySingleQuery(String beanName, String propertyName, String propertyVal) {
		String hql = "from " + beanName + " b where b." + propertyName + " = '" + propertyVal + "' ";
		List<T> resultList = this.findPageByQuery(hql);
		if(resultList.size() == 1) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	/**
	 * 根据单条件查询集合结果
	 * @param <T>
	 * @param beanName
	 * @param propertyName
	 * @param propertyVal
	 * @return
	 */
	public <T extends Object> List<T> findBySingleQuery(String beanName, String propertyName, String propertyVal) {
		String hql = "from " + beanName + " b where b." + propertyName + " = '" + propertyVal + "' ";
		List<T> resultList = this.findPageByQuery(hql);
		if(resultList.size() > 0 ) {
			return resultList;
		} else {
			return null;
		}
	}
	/**
	 * 根据模块资源生成序列号
	 * @param moduleResource
	 * @return generatedSN
	 */
	public String getGeneratedSerialNumber(String moduleResource){
		SecSerialnumber serialResource = this.findUniqueBySingleQuery("SecSerialnumber", "sersource", moduleResource);
		String generatedSN = "";
		if(serialResource != null) {
			//获取   XX{类型1}{类型2}{类型3}  格式的显示方式
			String formatModule = serialResource.getSespecidlid();
			//取第一个"{"之前的部分作为序列号标识
			generatedSN += formatModule.substring(0, formatModule.indexOf("{"));
			String moduleModel = formatModule.substring(formatModule.indexOf("{")).replace("}", ",").replace("{", "");
			//替换时间部分
			if(moduleModel.contains("时间")){
				String currentTimeString = DateTool.getDateTime(serialResource.getSdateformat());
				moduleModel = moduleModel.replace("时间", currentTimeString == null ? "" : currentTimeString);
			}
			//替换单位部分
			if(moduleModel.contains("单位")){
				moduleModel = moduleModel.replace("单位", serialResource.getSdeptid() == null ? "" : serialResource.getSdeptid());
			}
			//替换序号部分
			if(moduleModel.contains("序号")){
				//按序号的位数补"0"
				StringBuffer serialBuffer = new StringBuffer();
				for (int i = 0; i < serialResource.getSerialnum()-1; i++) {
					serialBuffer.append("0");
				}
				serialBuffer.append(serialResource.getSerial());
				//根据当前的计数去掉多余的部分
				if(serialBuffer.length() > serialResource.getSerialnum()){
					serialBuffer.replace(0, serialBuffer.length()-serialResource.getSerialnum(), "");
				}
				moduleModel = moduleModel.replace("序号", serialBuffer.toString());
			}
			generatedSN += moduleModel.replace(",", "");
			//每获取一次使序号加1
			serialResource.setSerial(serialResource.getSerial()+1);
			System.out.println(generatedSN);
		}
		return generatedSN;
	}
	
	/**
	 * 保存附件
	 * @param businessId      对应业务表的ID (一般为业务主表的Id)
	 * @param tableName       对应业务表的表名转化得来的业务模块名称
	 * @param filePath        指定格式的附件字符串组合
	 */
	public void saveFiles(String businessId, String tableName, String filePath) {

		//判断相同的文件名字是否存在
		String hql = "";
		//处理附件字符串组合并进行保存
		String[] fileInfo = filePath.split(",");
		for (int i = 0; i < fileInfo.length; i++) {
			CmAttachment backupFile = new CmAttachment();
			//来源信息
			backupFile.setFromid(businessId);
			backupFile.setFromtablename(tableName);
			//文件相关信息
			String[] names = fileInfo[i].split("_-_");
			backupFile.setAttchname(names[0]);
			backupFile.setAttchpath(names[1]);
			hql="from CmAttachment where fromid = '" + businessId + "' and attchname='"+names[0]+"'";
			List<CmAttachment> fileList = this.findPageByQuery(hql);
			if(fileList.isEmpty()){
				this.save(backupFile);
			}
		}
	}
	
	/**
	 * 
	 * 查询fromid模块的所有附件
	 * @param fromid
	 * @return
	 * Oct 10, 20116:41:19 PM
	 * @author niujingwei
	 */
	@Override
	public List<CmAttachment> searchFiles(String fromid) {
		String hql="from CmAttachment where fromid = '"+fromid+"'";
		return findPageByQuery(hql);
	}

	@Override
	public void deleteFileByBusinessId(String searchFiles) {
		List<CmAttachment> searchFiles2 = searchFiles(searchFiles);
		for(CmAttachment cmAttachment:searchFiles2){
			String attchpath = cmAttachment.getAttchpath();
			File file=new File(attchpath);
			file.deleteOnExit();
		}
		
	}
	
	/**
	 * 获取机构名称
	 * @param orgId    机构ID
	 * @return    机构名称
	 */
	public String getOrganizationNameById(String orgId) {
		String name = "";
		if(orgId != null && !orgId.equals("")){
			SysOrganization organization = this.findById(SysOrganization.class, orgId);
			if(organization!=null){
			name = organization.getName();
			}
		}
		return name;
	}
	
	/**
	 * 获取用户名称
	 * @param userId   用户Id
	 */
	public String getUserNameById(String userId) {
		String username = "";
		if(userId != null && !userId.equals("")){
			CmUser user = this.findById(CmUser.class, userId);
			username = user.getName();
		}
		return username;
	}
	
	/**
	 * 删除附件的方法
	 * @param fromids
	 * @param pattern
	 */
	@Override
	public List<CmAttachment> deleteFiles(String fromids, String pattern) {
		
		String hql="from CmAttachment where fromid in ("+Util.toStringIds(fromids,pattern)+")";
		List<CmAttachment> cmAttachments = new ArrayList<CmAttachment>();
		cmAttachments=findPageByQuery(hql);
		for(int i = 0; i < cmAttachments.size(); i++ ){
			new FileOperate().delFile(cmAttachments.get(i).getAttchpath());
			this.deleteById(CmAttachment.class, cmAttachments.get(i).getAttchid());
		}
		return findPageByQuery(hql);
	}

	/**
	 * 批量更新
	 * @param list
	 */
	@Override
	public <T> List<T> updateList(List<T> list) {
		List<T> returnList = this.publicDAO.updateList(list);
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(cmUser != null) {
			userId = cmUser.getUserId();
		}
		for(T t :returnList){
			this.getInsertLog().info(this.readObject(t) + "#" + userId);
		}
		return returnList;
	}

	/**
	 * 批量保存/更新
	 * @param list
	 */
	@Override
	public <T> List<T> saveOrUpdateList(List<T> list) {
		List<T> returnList = this.publicDAO.saveOrUpdateList(list);
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String userId = "";
		if(cmUser != null) {
			userId = cmUser.getUserId();
		}
		for(T t :returnList){
			this.getInsertLog().info(this.readObject(t) + "#" + userId);
		}
		return returnList;
	}

	
	@Override
	public void deleteBasicWorkflowByBusinessId(String businessId) {
		String hql = "from BasicWorkflow where bTablePk='" + businessId + "'";
		List<BasicWorkflow> basicWorkflowList = this.findPageByQuery(hql);
		for (BasicWorkflow basicWorkflow : basicWorkflowList) { 
			ProcessBase processBase= new ProcessBase();
			if(!processBase.isEnd(basicWorkflow.getwFlowPk())){
				processBase.deleteProcessInstanceByPiId(basicWorkflow.getwFlowPk());
			}
			this.delete(basicWorkflow);
		}
	}

	@Override
	public void insertBasicWorkflow(String businessId, String processInatanceId,String tableName) {
		// 首先用业务ID来查询BasicWorkflow如果存在 则不插入 如果不存在则插入
		String hql = "from BasicWorkflow where bTablePk='" + businessId + "'";
		List<BasicWorkflow> basicWorkflowList = this.findPageByQuery(hql);
		BasicWorkflow basicWorkflow;
		if (basicWorkflowList.isEmpty()) {
			// 存储流程和相关业务
			basicWorkflow = new BasicWorkflow();

		} else {
			basicWorkflow = basicWorkflowList.get(0);
		}
		basicWorkflow.setbTableName(tableName);
		basicWorkflow.setbTablePk(businessId);
		basicWorkflow.setwFlowPk(processInatanceId);
		this.saveOrUpdate(basicWorkflow);
	}

	@Override
	public BasicWorkflow findBasicWorkflow(String businessId) {
		String hql = "from BasicWorkflow where bTablePk='" + businessId + "'";
		List<BasicWorkflow> basicWorkflowList = this.findPageByQuery(hql);
		if (!basicWorkflowList.isEmpty()) {
			// 存储流程和相关业务
			return basicWorkflowList.get(0);

		} 
		return null;
	}

	
	//===========add by jilili 2011-11-1 start========================//
	
	@Override
	public List<CmAttachment> searchFiles(String fromid, String fromtablename) {
		String hql="from CmAttachment where fromid = '"+fromid+"' and fromtablename = '" + fromtablename + "'";
		return findPageByQuery(hql);
	}
	
	public List<CmAttachment> getFilesBySubject(String fromTableName, int columns) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("from CmAttachment where fromtablename = '").append(fromTableName).append("' ");
		String orderByInfo = "nlssort(attchname,'NLS_SORT=SCHINESE_PINYIN_M') desc ";
		SysPageInfo sysPageInfo = new SysPageInfo();
		sysPageInfo.setPageSize(columns);
		sysPageInfo.setMaxCount(columns);
		sysPageInfo.setStartIndex(0);
		//sysPageInfo.setCurrentPage(1);
		return this.findPageByQuery(sysPageInfo, buffer.toString(), "", null, orderByInfo);
	}

	@Override
	public void insertBasicWorkflow(String businessId, String processInatanceId) {
		// 首先用业务ID来查询BasicWorkflow如果存在 则不插入 如果不存在则插入
		String hql = "from BasicWorkflow where bTablePk='" + businessId + "'";
		List<BasicWorkflow> basicWorkflowList = this.findPageByQuery(hql);
		BasicWorkflow basicWorkflow;
		if (basicWorkflowList.isEmpty()) {
			// 存储流程和相关业务
			basicWorkflow = new BasicWorkflow();

		} else {
			basicWorkflow = basicWorkflowList.get(0);
		}
		basicWorkflow.setbTableName("SecQarFlyDa");
		basicWorkflow.setbTablePk(businessId);
		basicWorkflow.setwFlowPk(processInatanceId);
		this.saveOrUpdate(basicWorkflow);
	}
	
	//===========add by jilili 2011-11-1 end========================//

	@Override
	public long getCountByHQL(String hql, HashMap<String, String> query,
			String customHql) {
		hql+=this.getHqlbyMap(query)+" "+customHql;
		return this.getCountByHQL(hql);
	}

	@Override
	public List<CmPeople> activePerson(SysPageInfo sysPageInfo, String customHql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("from CmPeople where NVL(status,'1') = '1' ");
		if(!customHql.equals("")){
			buffer.append(customHql);
		}
		List<CmPeople> peopleList = this.findPageByQuery(sysPageInfo,
				buffer.toString(), query, sysOrderByInfo);
		if(peopleList.isEmpty()){
			return Collections.<CmPeople>emptyList();
		}
		return peopleList;
	}
	
	@Override
	public long activePersonCount(SysPageInfo sysPageInfo, String customHql,
			HashMap<String, String> query) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(*) from CmPeople where NVL(status,'1') = '1' ");
		if(!customHql.equals("")){
			buffer.append(customHql);
		}
		return this.getCountByHQL(buffer.toString(), query);
	}
	@Override
	public List<SysUserOrgRelation> activeUser(SysPageInfo sysPageInfo, String customHql,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("from SysUserOrgRelation where NVL(cmUser.state,'1') = '1' ");
		if(!customHql.equals("")){
			buffer.append(customHql);
		}
		List<SysUserOrgRelation> userList = this.findPageByQuery(sysPageInfo,
				buffer.toString(), query, sysOrderByInfo);
		if(userList.isEmpty()){
			return Collections.<SysUserOrgRelation>emptyList();
		}
		return userList;
	}
	
	@Override
	public long activeUserCount(String customHql, HashMap<String, String> query) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(*) from SysUserOrgRelation where NVL(cmUser.state,'1') = '1' ");
		if(!customHql.equals("")){
			buffer.append(customHql);
		}
		return this.getCountByHQL(buffer.toString(), query);
	}

	@Override
	public void executeUpdateSQL(String sql) {
		this.publicDAO.executeUpdateSQL(sql);
		this.getExecuteLog().info(sql);
		
	}
}
