package com.sinoframe.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysUserOrgRelationBS;
import com.sinoframe.dao.ISysUserOrgRelationDao;

@Service("sysUserOrgRelationBS")
public class SysUserOrgRelationBS extends BaseBS implements
		ISysUserOrgRelationBS {
	public ISysUserOrgRelationDao sysUserOrgRelationDao;
	
	@Resource
	public final void setSysUserOrgRelationDao(
			ISysUserOrgRelationDao sysUserOrgRelationDao) {
		this.sysUserOrgRelationDao = sysUserOrgRelationDao;
	}
	
	@Override
	public List findByAny(String boname, String attributename,
			Object attributevalue, String order) {
		// TODO Auto-generated method stub
		
		return this.sysUserOrgRelationDao.findByAny(boname, attributename, attributevalue, order);
	}
	
	@Override
	public List<SysOrganization> findOrgByUserId(CmUser cmUser) {
		List<SysOrganization> sysOrganizationList=new ArrayList<SysOrganization>();
		String hql="from SysUserOrgRelation where cmUser.userId='"+cmUser.getUserId()+"'";
		List<SysUserOrgRelation> sysUserOrgRelationList=sysUserOrgRelationDao.findPageByQuery(hql);
		
		for (int i = 0; i < sysUserOrgRelationList.size(); i++) {
			sysOrganizationList.add(sysUserOrgRelationList.get(i).getSysOrganization());
		}
		return sysOrganizationList;
	}


}
