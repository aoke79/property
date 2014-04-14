package com.sinoframe.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysUserCustomMenu;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysUserCustomMenuBS;

@Service("sysUserCustomMenuBS")
public class SysUserCustomMenuBS extends BaseBS implements ISysUserCustomMenuBS {
	
	/**
	 * 根据用户查询所有的自定义菜单项
	 * @param user
	 * @return 所有自定义菜单的ID集合
	 */
	public List<SysUserCustomMenu> findByUser(String userId) {
		List<SysUserCustomMenu> customList = new ArrayList<SysUserCustomMenu>();
		//查询条件
		String hql = "from SysUserCustomMenu where cmUser.userId = '" + userId + "'";
		customList = this.findPageByQuery(hql);
		return customList;
	}
	
	/**
	 * 根据用户查询所有的自定义菜单ID
	 * @param user
	 * @return 所有自定义菜单的ID集合
	 */
	public List<String> getAllMenuByUser(CmUser user) {
		List<String> menuItem = new ArrayList<String>();
		List<SysUserCustomMenu> menuList = new ArrayList<SysUserCustomMenu>();
		//查询条件
		String listCondition = " from SysUserCustomMenu where cmUser.userId = '" + user.getUserId() + "'";
		menuList = this.findPageByQuery(listCondition);
		//遍历取出所有自定义菜单的ID并存入list
		for (SysUserCustomMenu sysUserCustomMenu : menuList) {
			menuItem.add(sysUserCustomMenu.getSysMenu().getId());
		}
		return menuItem;
	}
}
