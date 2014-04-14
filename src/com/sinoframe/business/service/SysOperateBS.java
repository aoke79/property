package com.sinoframe.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysVUserOperate;
import com.sinoframe.business.ISysOperateBS;
import com.sinoframe.dao.ISysOperateDao;

/**
 * SysOperateBS
 * @描述 操作的业务处理类
 * @作者 胡星
 * @版本 1.0
 */
@Service("sysOperateBS")
public class SysOperateBS extends BaseBS implements ISysOperateBS {
	// 操作的DAO
	ISysOperateDao sysOperateDao;

	public ISysOperateDao getSysOperateDao() {
		return sysOperateDao;
	}

	@Resource
	public void setSysOperateDao(ISysOperateDao sysOperateDao) {
		this.sysOperateDao = sysOperateDao;
	}

	
	/**
	 * 判断操作是否存在
	 * @参数 operId 操作 ID
	 * @返回 boolean true:存在；false:不存在
	 */
	public boolean isExistOper(String operId){
		Boolean flag = true;
		//根据 ID 查找
		SysOperate sysOperate = sysOperateDao.findById(SysOperate.class, operId);
		if(sysOperate == null)
			flag = false;
		return flag;
	}
	/**
	 * find SysOperate List By Not In Ids
	 * 
	 * @param tempString
	 * @return
	 */
	public List<SysOperate> findByNotInIds(String tempString) {
		return this.findPageByQuery("from SysOperate sysOperate where sysOperate.id not in (" + tempString + ")");
	}

	/**
	 * get SysOperate List By UserId
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysOperate> getSysOperateByUser(String userId) {
		// SysVUserOperate 为视图:根据 userId 查询视图结果
		String hqlview = "select distinct a from SysVUserOperate a where 1=1 and a.id.userId = '" + userId + "' ";
		// 查询视图结果集
		List<SysVUserOperate> viewList = this.findPageByQuery(hqlview);
		
		// 将视图结果集中的操作放入操作集合中
		List<SysOperate> sysOperateList = new ArrayList<SysOperate>();
		for (SysVUserOperate vo : viewList) {
			SysOperate newso = new SysOperate();
			try {
				newso = (SysOperate) this.copy(newso, vo);
				// 逐一放入操作集合
				sysOperateList.add(newso);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回操作集合
		return sysOperateList;
	}
	
	@Override
	public List<SysRelationRoleOperate> rolesListByOperate(String operateId, SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> query) {
		String hql = "from SysRelationRoleOperate r where r.sysRole.flag = '0' " +
					 "and r.sysOperate.id = '" + operateId + "' ";
		if(null == sysOrderByInfo.getOrderColumn() || "".equals(sysOrderByInfo.getOrderColumn())){
			sysOrderByInfo.setOrderColumn("sysRole.roleName");
			sysOrderByInfo.setOrderAsc("desc");
		}
		
		List<SysRelationRoleOperate> relationList = this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		return relationList;
	}
	
	@Override
	public int rolesListCount(String operateId, HashMap<String, String> query) {
		String hql = "select count(*) from SysRelationRoleOperate r where r.sysRole.flag = '0' " +
					 "and r.sysOperate.id = '" + operateId + "' ";
		int colCount = this.getCountByHql(hql, query); 
		return colCount;
	}

	
	public List<SysOperate> getSysOperateByUser(String userId, String sid) {
		// SysVUserOperate 为视图:根据 userId 查询视图结果
		String hqlview = "select distinct a from SysVUserOperate a where 1=1 and a.id.userId = '" + userId + "'  ";
		// 查询视图结果集
		if(sid != null && sid.length() != 0 ){
			 hqlview += " and a.subsystemId like '%"+sid+"%'";
		}//select 
		
		List<SysVUserOperate> viewList = this.findPageByQuery(hqlview);
		
		// 将视图结果集中的操作放入操作集合中
		List<SysOperate> sysOperateList = new ArrayList<SysOperate>();
		for (SysVUserOperate vo : viewList) {
			SysOperate newso = new SysOperate();
			try {
				newso = (SysOperate) this.copy(newso, vo);
				// 逐一放入操作集合
				sysOperateList.add(newso);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回操作集合
		return sysOperateList;
	}
}
