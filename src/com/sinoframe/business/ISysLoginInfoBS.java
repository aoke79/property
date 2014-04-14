package com.sinoframe.business;

import java.io.Serializable;
import java.util.List;

public interface ISysLoginInfoBS extends IService{
public <T> List<T> findAll();

	public List findByAny( String attributename,Object attributevalue, String order);
	
	public <T> void update(T entity);
	public List findBySQLQuery(final String sql,final int length);
	
}
	

