package com.sinoframe.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;


public interface IPersonBS extends IService{
	
	/**
	 * 查询人
	 * @param sysPageInfo      分页条件
	 * @param currentTag       当前点击来源
	 * @param query            查询条件
	 * @param sysOrderByInfo   排序条件
	 * @return
	 */
	public List<CmPeople> searchPeople(SysPageInfo sysPageInfo, String currentTag,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 查询人条数
	 * @param sysPageInfo      分页条件
	 * @param currentTag       当前点击来源
	 * @param query            查询条件
	 * @return
	 */
	public long searchPeopleCount(SysPageInfo sysPageInfo, String currentTag,
			HashMap<String, String> query);
	
}
