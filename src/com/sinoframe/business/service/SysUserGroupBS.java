package com.sinoframe.business.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysUserGroup;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysUserGroupBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.dao.ISysRoleDao;
import com.sinoframe.dao.ISysUserGroupDao;
import com.sinoframe.dao.ICmUserDao;

@Service("sysUserGroupBS")
@Transactional
public class SysUserGroupBS extends BaseBS implements ISysUserGroupBS {

	private ISysUserGroupDao sysUserGroupDao;
	private ICmUserDao cmUserDao;
	private ISysRoleDao SysRoleDao;

	/**
	 * 判断相应id值的用户组下是否有用户
	 * 
	 * @param
	 */
	public boolean hasUser(String strId) {
		SysUserGroup sysUserGroup = this.findById(SysUserGroup.class, strId);
		if (null != sysUserGroup && !sysUserGroup.getSetCmUser().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 将用户添加到用户组
	 */
	public void addUserToGroup(String ids, SysUserGroup sysUserGroup) {
		StringBuffer sb = new StringBuffer();
		// 用于存储要添加进用户组的用户
		Set<CmUser> setAddTbUser = new HashSet<CmUser>();
		// 用户存储要从用户组中删除的用户
		Set<CmUser> setMinusTbUser = new HashSet<CmUser>();

		Set<CmUser> listTbUser = sysUserGroup.getSetCmUser();
		// 存储该用户组中原有的用户的id
		List<String> oldIds = new ArrayList<String>();
		// 存储该用户组调整后的用户的id
		List<String> newIds = new ArrayList<String>();
		for (CmUser cmUser : listTbUser) {
			oldIds.add(cmUser.getUserId());
		}
		if (null != ids && !"".equals(ids)) {
			String[] userIds = ids.split(",");
			for (String id : userIds) {
				newIds.add(id.trim());
			}
		}
		// 存储newIds值的集合
		Set<String> tempIds = new HashSet<String>();
		tempIds.addAll(newIds);
		// 筛选出将要添加的用户id
		newIds.removeAll(oldIds);
		// 筛选出将要删除的用户id;
		oldIds.removeAll(tempIds);

		// 将用户添加到当前用户组中
		if (!newIds.isEmpty()) {
			setAddTbUser = getUsers(sb, setAddTbUser, newIds);
		}

		// 将用户从用户组中移除
		if (!oldIds.isEmpty()) {
			setMinusTbUser = getUsers(sb, setMinusTbUser, oldIds);
		}
		sysUserGroup.getSetCmUser().removeAll(setMinusTbUser);
		sysUserGroup.getSetCmUser().addAll(setAddTbUser);
	}

	private Set<CmUser> getUsers(StringBuffer sb, Set<CmUser> setCmUser,
			List<String> newIds) {
		String hql;
		for (String id : newIds) {
			sb.append("'").append(id.trim()).append("'").append(",");
		}
		 // 拼写hql语句
		hql = "from CmUser where userId in ("
				+ sb.toString().substring(0, sb.toString().length() - 1) + ")";
		setCmUser.addAll(this.cmUserDao.findByAnyHql(hql));
		return setCmUser;
	}


/*	public void addUserToGroup(String ids, SysUserGroup sysUserGroup) {
		Set<CmUser> setCmUser = new HashSet<CmUser>();
		if (ids != null && !"".equals(ids)) {
			String strIds = Util.toStringIds(ids);
			String hql = "from CmUser where userId in ("
					+ strIds + ")";
			setCmUser.addAll(cmUserDao.findByAnyHql(hql));
		}
		sysUserGroup.setSetCmUser(setCmUser);
	}*/
	
	/**
	 * 给用户组分配角色
	 * @param ids  要分配的角色的Id；
	 * @param sysUserGroup 当前的用户组
	 */
	@Override
/*	public void addRoleToGroup(String ids, SysUserGroup sysUserGroup) {
		// 存储所有角色的集合
		Set<SysRole> setSysRole = new HashSet<SysRole>();
		// 根据所有角色的id，拼写查询角色的hql语句
		if (ids != null && !"".equals(ids)) {
			String strIds = Util.toStringIds(ids);
			String hql = "from SysRole where roleId in ("
					+ strIds + ")";
			// 根据查询语句查询出所有角色，并保存到集合中
			setSysRole.addAll(cmUserDao.findByAnyHql(hql));
		}
		// 向数据库中插入相关数据 
		sysUserGroup.setSetSysRole(setSysRole);
	}*/
	
	public void addRoleToGroup(String ids, SysUserGroup sysUserGroup) {
		StringBuffer sb = new StringBuffer();
		// 用于存储要给用户组分配的所有角色
		Set<SysRole> setAddSysRole = new HashSet<SysRole>();
		// 用户存储要删除用户组当前拥有的角色
		Set<SysRole> setMinusSysRole = new HashSet<SysRole>();

		Set<SysRole> listSysRole = sysUserGroup.getSetSysRole();
		// 存储该用户组中原有的角色的id
		List<String> oldIds = new ArrayList<String>();
		// 存储该用户组调整后的角色的id
		List<String> newIds = new ArrayList<String>();
		for (SysRole sysRole : listSysRole) {
			oldIds.add(sysRole.getRoleId());
		}
		if (null != ids && !"".equals(ids)) {
			String[] userIds = ids.split(",");
			for (String id : userIds) {
				newIds.add(id.trim());
			}
		}
		// 存储newIds值的集合
		Set<String> tempIds = new HashSet<String>();
		tempIds.addAll(newIds);
		// 筛选出将要添加的角色id
		newIds.removeAll(oldIds);
		// 筛选出将要删除的角色id;
		oldIds.removeAll(tempIds);

		// 将角色分配给当前用户组
		if (!newIds.isEmpty()) {
			setAddSysRole = getRoles(sb, setAddSysRole, newIds);
		}

		// 将当前用户组中移除角色
		if (!oldIds.isEmpty()) {
			setMinusSysRole = getRoles(sb, setMinusSysRole, oldIds);
		}
		sysUserGroup.getSetSysRole().removeAll(setMinusSysRole);
		sysUserGroup.getSetSysRole().addAll(setAddSysRole);
		sysUserGroupDao.clear(SysUserGroup.class);
	}
	
	private Set<SysRole> getRoles(StringBuffer sb, Set<SysRole> setSysRole,
			List<String> newIds) {
		String hql;
		String strIds = Util.toStringIds(newIds.toString().replace("[", "").replace("]", ""));
		 // 拼写hql语句
		hql = "from SysRole where roleId in (" + strIds + ")";
		setSysRole.addAll(this.SysRoleDao.findByAnyHql(hql));
		return setSysRole;
	}

	@Resource
	public void setSysUserGroupDao(ISysUserGroupDao sysUserGroupDao) {
		this.sysUserGroupDao = sysUserGroupDao;
	}

	@Resource
	public void setSysRoleDao(ISysRoleDao sysRoleDao) {
		SysRoleDao = sysRoleDao;
	}

	@Resource
	public void setCmUserDao(ICmUserDao cmUserDao) {
		this.cmUserDao = cmUserDao;
	}
	
}
