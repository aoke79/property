package com.sinoframe.business;

import java.util.List;

import com.sinoframe.bean.SysMenu;

public interface ISysMenuBS extends IService {
	
	/**
	 * 根据用户获取菜单项
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getSysMenuByUserId(String userId);
	
	/**
	 * 判断某个菜单是否含有子菜单项
	 * @return
	 */
	public boolean subMenu(String id);
	
	/**
	 * 判断多个菜单项时是否含有子菜单项
	 * @param ids
	 * @return
	 */

	public List<SysMenu> getSysMenuByUserId(String userId,String sid);
	/**
	 * 
	 * @param ids
	 * @return
	 */
	public boolean multipleMenu(String ids);
	
	/**
	 * 获取需要被关闭的结点List集 
	 * @param menuList
	 * @param parentIds
	 * @param parentId
	 * @return
	 */
	public List<String> closableMenuNodes(List<SysMenu> menuList, List<String> parentIds, String parentId);
	
}
