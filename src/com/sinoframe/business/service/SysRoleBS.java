package com.sinoframe.business.service;



import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.comm.util.Util;
import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.ISysRoleBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.dao.IPublicDao;
import com.sinoframe.dao.ISysRoleDao;
@Service("targetISysRoleBS")
public class SysRoleBS extends BaseBS implements ISysRoleBS{
	private ISysRoleDao sysRoleDao;
	
	

	public SysRole searchRoleById(String id) throws RollbackableBizException {
	     return (SysRole) this.findById(SysRole.class, id);
	}
	public boolean upateRole(SysRole role) throws RollbackableBizException {
		//sysRoleDao.update(role);
		this.saveOrUpdate(role);
		return true;
	}
	
    public void delSome(String[] ids) throws RollbackableBizException {
    	sysRoleDao.delSome(ids);
	}
	
	@Override
	public List<SysRole> findByIds(String[] ids)
			throws RollbackableBizException {
		return sysRoleDao.findByAnyHql("from SysRole where roleId in ("+Util.Array2String(ids)+") ");
	}

//	@Override
//	public void searchByLike(Paging paging, String roleName,
//			String roleDescription) throws RollbackableBizException {
//		sysRoleDao.getByRoleNameAndRoleDescription(paging, roleName, roleDescription);
//	}

	@Override
	public List<SysRole> getAll() throws RollbackableBizException {
		//by lujie
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String hql = " from SysRole where 1=1 ";
		if(!"超级管理员".equals(cmUser.getName().trim())){
			hql +=" and subsystemId like '%"+cmUser.getSubsystemId()+"%'";
		}
		return findPageByQuery(hql);
	}

	public ISysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}
	@Resource
	public void setSysRoleDao(ISysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	@Override
	public List<SysRole> findByNotInIds(String tempString) {
		//by lujie
		CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
		String hql = "from SysRole sysRole where sysRole.roleId not in ("+tempString+")";
		if(!"超级管理员".equals(cmUser.getName().trim())){
			hql +=" and subsystemId like '%"+cmUser.getSubsystemId()+"%'";
		}
		return this.findPageByQuery(hql);
		
		
	}


}
