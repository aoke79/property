package com.sinoframe.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysRAccountRoleId;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.ISysRelationAccountRoleBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.dao.IPublicDao;
import com.sinoframe.dao.ISysRelationAccountRoleDao;
/**
 * @author niujingwei
 * 业务操作类
 */
@Service("targetISysRelationAccountRoleBS")
public class SysRelationAccountRoleBS extends BaseBS implements ISysRelationAccountRoleBS{
	private ISysRelationAccountRoleDao sysRelationAccountRoleDao;

	

	public SysRelationAccountRole searchRelationAccountRoleBySysRAccountRoleId(
			final SysRAccountRoleId accountRoleId) throws RollbackableBizException {
		return (SysRelationAccountRole) findById(SysRelationAccountRole.class, accountRoleId);
	}



	
	
	
	
	@Override
	public List<SysRelationAccountRole> getAll()
			throws RollbackableBizException {
		return this.findPageByQuery("from SysRelationAccountRole");
	}
	
	@Override
	public List<SysRole> findByUserId(String userId) {
		return this.findPageByQuery("select r.sysRole from SysRelationAccountRole r where r.accountRoleId.userId="+userId);
	}
	

	@Override
	public void deleteByLianHeIds(String ids) {
		System.out.println(ids);
		String idString=ids;
		SysRAccountRoleId sysRAccountRoleId;
		String[] tempStrs=idString.split(",");
		for(String s:tempStrs){
			sysRAccountRoleId=new SysRAccountRoleId();
			String[] tempStrs1=s.split("&ruser.roleId=");
			System.err.println(tempStrs1[0]);
			System.err.println(tempStrs1[1]);
			sysRAccountRoleId.setUserId(tempStrs1[0]);
			sysRAccountRoleId.setRoleId(tempStrs1[1]);
			this.deleteById(SysRelationAccountRole.class, sysRAccountRoleId);
		}

	}

	@Override
	public void delById(SysRAccountRoleId sysRAccountRoleId) {
		this.deleteById(SysRelationAccountRole.class, sysRAccountRoleId);
	}
	
	@Resource
	public void setSysRelationAccountRoleDao(
			ISysRelationAccountRoleDao sysRelationAccountRoleDao) {
		this.sysRelationAccountRoleDao = sysRelationAccountRoleDao;
	}

	public ISysRelationAccountRoleDao getSysRelationAccountRoleDao() {
		return sysRelationAccountRoleDao;
	}

	

	

	


	

}
