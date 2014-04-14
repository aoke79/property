package com.sinoframe.business;

import java.util.List;

import com.sinoframe.bean.SysUserCustomMenu;
import com.sinoframe.bean.CmUser;


public interface ISysUserCustomMenuBS extends IService {
	
	/**
	 * 根据用户查询所有的自定义菜单项
	 * @param user
	 * @return 所有自定义菜单的ID集合
	 */
	public List<SysUserCustomMenu> findByUser(String userId);
	
	/**
	 * 根据用户查询所有的自定义菜单ID
	 * @param user
	 * @return 所有自定义菜单的ID集合
	 */
	public List<String> getAllMenuByUser(CmUser user);
	
}
