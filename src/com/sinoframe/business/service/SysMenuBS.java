package com.sinoframe.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysMenu;
import com.sinoframe.business.ISysMenuBS;
import com.sinoframe.dao.ISysMenuDao;

@Service("sysMenuBS")
@Transactional
public class SysMenuBS extends BaseBS implements ISysMenuBS {
	
	private ISysMenuDao SysMenuDao;
	
	@Resource
	public void setSysMenuDao(ISysMenuDao sysMenuDao) {
		SysMenuDao = sysMenuDao;
	}
	public ISysMenuDao getSysMenuDao(){
		return SysMenuDao;
	}
	
	/**
	 * 根据用户获取菜单项
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getSysMenuByUserId(String userId){
		String hql = "select distinct a from SysMenu a where a.id <> '-1' and a.id in (select distinct b.menuId from SysVUserOperate b where 1=1 and b.id.userId = '"+userId+"' ) order by a.orderColumn";
		List<SysMenu> sysMenuList = this.findPageByQuery(hql);
		return sysMenuList;
	}
	
	/**
	 * 判断某个菜单是否含有子菜单项
	 * @return
	 */
	public boolean subMenu(String id) {
		SysMenu sysMenu = this.findById(SysMenu.class, id);
		if(!sysMenu.getSubMenu().isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断多个菜单项时是否含有子菜单项
	 * @param ids
	 * @return
	 */
	public boolean multipleMenu(String ids) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			if(this.subMenu(idArray[i]) == true){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 使用递归手段获取当前菜单的父菜单ID集合
	 * @param parentMenuIds
	 * @param parentId
	 * @return
	 */
	private List<String> parentMenuNode(List<String> parentMenuIds, String parentId) {
		//根据父结点ID查询父菜单信息
		SysMenu sysMenu = this.findById(SysMenu.class, parentId);
		//当有父结点时，将数据送进ID的List集合
		if(sysMenu != null){
			parentMenuIds.add(sysMenu.getId());
			//向上递归，找到当前菜单的完整菜单树划分
			if(sysMenu.getParentMenu() != null){
				parentMenuNode(parentMenuIds,sysMenu.getParentMenu().getId());
			}
		}
		return parentMenuIds;
	}
	
	/**
	 * 获取需要被关闭的结点List集 
	 * @param menuList
	 * @param parentIds
	 * @param parentId
	 * @return
	 */
	public List<String> closableMenuNodes(List<SysMenu> menuList, List<String> parentIds, String parentId) {
		List<String> menuIds = new ArrayList<String>();
		//获取所有结点
		for (SysMenu sysMenu : menuList) {
			if(!sysMenu.getSubMenu().isEmpty()){
				menuIds.add(sysMenu.getId());
			}
		}
		//从所有结点中去除当前进行操作的结点
		menuIds.removeAll(this.parentMenuNode(parentIds, parentId));
		return menuIds;
	}
   
	public List<SysMenu> getSysMenuByUserId(String userId, String sid) {
		String se = "";
		if(sid != null && sid.length() != 0 ){
			 se += "   and b.subsystemId like '%"+sid+"%' ";
		}
		String hql = "select distinct a from SysMenu a where a.id <> '-1' and a.id in (select distinct b.menuId from SysVUserOperate b where 1=1 and b.id.userId = '"+userId+"'"+se+" ) order by a.orderColumn";

		
		List<SysMenu> sysMenuList = this.findPageByQuery(hql);
		return sysMenuList;
	}
	
}
