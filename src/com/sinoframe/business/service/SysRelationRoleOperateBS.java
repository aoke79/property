package com.sinoframe.business.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysRAccountRoleId;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysRelationRoleOperateId;
import com.sinoframe.business.ISysRelationRoleOperateBS;
import com.sinoframe.dao.IPublicDao;
import com.sinoframe.dao.ISysRelationRoleOperateDao;

@Service("sysRelationRoleOperateBS")
public class SysRelationRoleOperateBS extends BaseBS implements
		ISysRelationRoleOperateBS {

	ISysRelationRoleOperateDao sysRelationRoleOperateDao;

	
	/**
	 * getById
	 * 
	 * @param entity
	 */
	public SysRelationRoleOperate getById(SysRelationRoleOperateId id) {
		// TODO Auto-generated method stub
		return this.findById(SysRelationRoleOperate.class, id);
	}
	
	

	public ISysRelationRoleOperateDao getSysRelationRoleOperateDao() {
		return sysRelationRoleOperateDao;
	}

	@Resource
	public void setSysRelationRoleOperateDao(
			ISysRelationRoleOperateDao sysRelationRoleOperateDao) {
		this.sysRelationRoleOperateDao = sysRelationRoleOperateDao;
	}

	@Override
	public void deleteByLianHeIds(String ids) {
		SysRelationRoleOperateId sysRelationRoleOperateId;
		String[] tempStrs=ids.split(",");
		for(String s:tempStrs){
			sysRelationRoleOperateId=new SysRelationRoleOperateId();
			String[] tempStrs1=s.split("&sysRelationRoleOperateId.operateId=");
			System.err.println(tempStrs1[0]);
			System.err.println(tempStrs1[1]);
			sysRelationRoleOperateId.setRoleId(tempStrs1[0]);
			sysRelationRoleOperateId.setOperateId(tempStrs1[1]);
			this.deleteById(SysRelationRoleOperate.class, sysRelationRoleOperateId);
		}
		
	}
}
