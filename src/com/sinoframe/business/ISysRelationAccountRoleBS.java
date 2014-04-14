package com.sinoframe.business;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.internal.FinalizablePhantomReference;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysRAccountRoleId;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.exception.RollbackableBizException;

/**
 * 
 * @author niujingwei
 *
 */
public interface ISysRelationAccountRoleBS extends IService{
	
		/**
		 * 添加账户角色关系信息
		 * @param relationAccountRole 账户角色关系实例
		 * @return 添加是否成功
		 * @throws RollbackableBizException
		 */
		//public boolean addRelationAccountRole(final SysRelationAccountRole relationAccountRole) throws RollbackableBizException;

	
		
		/**
		 * 根据联合主键查询RelationAccountRole实体
		 * @param rAccountRoleId
		 * @return
		 * @throws RollbackableBizException
		 */
		public SysRelationAccountRole searchRelationAccountRoleBySysRAccountRoleId(final SysRAccountRoleId rAccountRoleId) throws RollbackableBizException;
	
		/**
		 * 查询所有账户角色关系信息 供不分页查询使用
		 * @throws RollbackableBizException
		 */
		public List<SysRelationAccountRole> getAll()throws RollbackableBizException;
		/**
		 * 删除账户角色关系
		 * @param relationAccountRole
		 * @return 
		 * @throws RollbackableBizException
		 */
		//public void doDel(final SysRelationAccountRole relationAccountRole) throws RollbackableBizException;
	
       /**
        * 通过主键删除对象
        * @param sysRAccountRoleId
        */
		public void delById(final SysRAccountRoleId sysRAccountRoleId);


    public void deleteByLianHeIds(String ids);

	public List<SysRole> findByUserId(String userId);

	
		
		
		

}
