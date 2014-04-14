package com.sinoframe.business;


import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.exception.RollbackableBizException;
/**
 * 2011-4-14
 * @author niujingwei
 *
 */
public interface ISysRoleBS extends IService {
    /**
	 * 根据id查询角色信息
	 * @param id 主键
	 * @return
	 * @throws RollbackableBizException
	 */
	public SysRole searchRoleById(final String id)
			throws RollbackableBizException;
	
	/**
	 * 查询部分数据返回list用于不分页查询
	 * @param ids
	 * @throws RollbackableBizException
	 */
	public List<SysRole> findByIds(final String[] ids)throws RollbackableBizException;
	/**
	 * 删除全部数据
	 * @throws RollbackableBizException
	 */
	//public void delAll() throws RollbackableBizException;
	
	/**
	 * 删除部分数据
	 * @param ids
	 * @throws RollbackableBizException
	 */
	public void delSome(String[] ids) throws RollbackableBizException;

	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 * @throws RollbackableBizException
	 */
	public boolean upateRole(final SysRole role) throws RollbackableBizException;

	/**
	 * 查询所有数据返回list列表，用于不分页查询
	 * @return list
	 */
	public List<SysRole> getAll()throws RollbackableBizException;
	/**
	 * 模糊查询
	 * @param paging
	 * @param roleName
	 * @param roleDescription
	 * @throws RollbackableBizException
	 */
	//public void searchByLike(final Paging paging,final String roleName,final String roleDescription)throws RollbackableBizException;

	public List<SysRole> findByNotInIds(String tempString);
	
}
