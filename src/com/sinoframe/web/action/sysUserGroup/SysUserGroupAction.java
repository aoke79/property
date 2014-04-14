package com.sinoframe.web.action.sysUserGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysUserGroup;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysRoleBS;
import com.sinoframe.business.ISysUserGroupBS;
import com.sinoframe.business.ISysUserGroupRelationBS;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/system/sysUserGroup/sysUserGroupList.jsp"),
		@Result(name = "addPage", location = "/system/sysUserGroup/sysUserGroupAdd.jsp"),
		@Result(name = "editPage", location = "/system/sysUserGroup/sysUserGroupEdit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sys-user-group!listUserGroup", type = "redirectAction"),
		@Result(name = "userManager", location = "/system/sysUserGroup/UserGroupManager.jsp"),
		@Result(name = "toRoleManagerPage", location = "/system/sysUserGroup/GroupRoleManager.jsp") })
public class SysUserGroupAction extends BaseAction {

	private static final long serialVersionUID = 1780293853128531874L;
	private ISysUserGroupBS sysUserGroupBS;
	private ISysUserGroupRelationBS sysUserGroupRelationBS;
	// TbUser的服务类
	private ICmUserBS cmUserBS;
	// SysRole的服务类
	private ISysRoleBS targetISysRoleBS;
	// 全部用户的集合
	private List<CmUser> listTbUser;
	private SysUserGroup sysUserGroup;
	// 存储记录的集合
	private List<SysUserGroup> listSysUserGroup;
	// 用于分页对象
	private SysPageInfo sysPageInfo;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 消息实体
	private Message message;
	// 存储要删除的机构的id
	private String ids;
	// 接收当前页面的页号
	private int pageNum = 1;
	// 接收页面显示记录的条数
	private int numPerPage = 20;
	// 存储所有角色的集合
	private List<SysRole> listSysRole = new ArrayList<SysRole>();
	// 用于存储查询条件的字符串形式
	private String strQuery;
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String toAddPage() {
		return "addPage";
	}

	/**
	 * 跳转到管理用户的界面
	 */
	public String toUserManagerPage() {
		// 获得当前用户组
		sysUserGroup = sysUserGroupBS.findById(SysUserGroup.class, this
				.getSysUserGroup().getId());
		// 获得除当前用户组中已有的用户的其他用户
		listTbUser = cmUserBS.findAll();
		listTbUser.removeAll(sysUserGroup.getSetCmUser());

		return "userManager";
	}

	/**
	 * 增加用户组
	 */
	public String sysUserGroupAdd() {
		try {
			sysUserGroupBS.save(sysUserGroup);
			this.message = this.getSuccessMessage("添加成功", "userGroupManager",
					"closeCurrent",
					"sys-user-group/sys-user-group!listUserGroup.do");
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("添加失败！");
		}

		return "SUCCESS";
	}

	/**
	 * 向用户组中添加用户
	 */
	public String addUserToGroup() {

		sysUserGroup = this.sysUserGroupBS.findById(SysUserGroup.class, this
				.getSysUserGroup().getId());

		System.out.println("sysUserGroup  " + sysUserGroup.getGName());

		try {
			sysUserGroupBS.addUserToGroup(ids, sysUserGroup);
			this.message = this.getSuccessMessage("修改成功!", "usersManager",
					"closeCurrent",
					"sys-user-group/sys-user-group-relation!listByGroup.do?sysUserGroup.id="
							+ this.getSysUserGroup().getId());
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("添加失败!");
		}
		return "SUCCESS";
	}

	/**
	 * 给用户组分配角色
	 * 
	 * @return
	 */
	public String addRoleToGroup() {
		// 通过id查询当前组
		sysUserGroup = this.sysUserGroupBS.findById(SysUserGroup.class, this
				.getSysUserGroup().getId());
		try {
			// 给组分配角色
			sysUserGroupBS.addRoleToGroup(ids, sysUserGroup);
			this.message = this
					.getSuccessMessage(
							"添加成功！",
							"userRoleManager",
							"closeCurrent",
							"sys-user-group/sys-role-user-group-relation!listByUserGroup.do?sysUserGroup.id="
									+ this.getSysUserGroup().getId());
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("添加失败！");
		}
		return "SUCCESS";
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delUserGroup() throws Exception {
		if (sysUserGroupBS.hasUser(this.getSysUserGroup().getId())) {
			this.message = this.getFailMessage("该用户组下有用户,请在删除用户后,再进行删除!");
		} else {
			try {
				sysUserGroupBS.deleteById(SysUserGroup.class, this
						.getSysUserGroup().getId());
				// 判断删除后的最后一页是否有数据
				sysPageInfo = this.getSysPageInfo();
				String strCountHql = "select count(*) from SysUserGroup  where 1=1";
				// 获得数据的最大条数
				Long maxCount = this.sysUserGroupBS.getCountByHQL(strCountHql,
						query);
				// 获得最大页数
				Integer maxPageNum = (int) (maxCount
						% sysPageInfo.getPageSize() == 0 ? maxCount
						/ sysPageInfo.getPageSize() : maxCount
						/ sysPageInfo.getPageSize() + 1);
				// 当最大页数比当前页小时，说明当前页已经没有数据，将最大页数设置为当前页
				if (maxPageNum < sysPageInfo.getCurrentPage()) {
					pageNum = maxPageNum;
				}
				this.message = this.getSuccessMessage(
						"删除成功！",
						null,
						"forward",
						"sys-user-group/sys-user-group!listUserGroup.do?pageNum="
								+ this.getPageNum() + "&numPerPage="
								+ this.getNumPerPage() + "&"
								+ Util.toStrQuery(query));
			} catch (Exception e) {
				e.printStackTrace();
				this.message = this.getFailMessage("删除失败！");
			}
		}
		return "SUCCESS";
	}

	/**
	 * 批量删除数据
	 */
	public String deleteGroups() {
		try {
			if (ids != null) {
				// 表示将要删除的用户组下是否有用户
				boolean exists = false;
				// 用于存放有用户组的组名
				List<String> listName = new ArrayList<String>();
				// 获得要删除的id
				String[] id = this.getIds().split(",");
				for (String strId : id) {
					// 判断每个机构对象是否含有子对象
					if (sysUserGroupBS.hasUser(strId)) {
						// 如果有子机构，存放到list集合中
						listName.add(sysUserGroupBS.findById(
								SysUserGroup.class, strId).getGName());
						exists = true;
					}
				}
				// 设置将要删除的机构有子机构的情况的消息
				if (exists) {
					this.message = this.getFailMessage(listName.toString()
							.replace("[", "").replace("]", " ")
							+ listName.size() + "个用户组有用户, 不能被删除,请重新选择!");
				} else {
					this.sysUserGroupBS.deleteByIds(SysUserGroup.class, ids);
					// 判断删除后的最后一页是否有数据
					sysPageInfo = this.getSysPageInfo();
					String strCountHql = "select count(*) from SysUserGroup  where 1=1";
					// 获得数据的最大条数
					Long maxCount = this.sysUserGroupBS.getCountByHQL(
							strCountHql, query);
					// 获得最大页数
					Integer maxPageNum = (int) (maxCount
							% sysPageInfo.getPageSize() == 0 ? maxCount
							/ sysPageInfo.getPageSize() : maxCount
							/ sysPageInfo.getPageSize() + 1);
					// 当最大页数比当前页小时，说明当前页已经没有数据，将最大页数设置为当前页
					if (maxPageNum < sysPageInfo.getCurrentPage()) {
						pageNum = maxPageNum;
					}
					this.message = this.getSuccessMessage(
							"删除成功！",
							null,
							"forward",
							"sys-user-group/sys-user-group!listUserGroup.do?pageNum="
							+ this.getPageNum() + "&numPerPage="
							+ this.getNumPerPage() + "&"
							+ Util.toStrQuery(query));
				}
			} else {
				this.message = this.getFailMessage("没有选中任何数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("删除失败");
		}
		return "SUCCESS";
	}

	/**
	 * 跳转到编辑页面
	 */
	public String toEditPage() {
		sysUserGroup = sysUserGroupBS.findById(SysUserGroup.class,
				sysUserGroup.getId());
		strQuery = Util.toStrQuery(query);
		return "editPage";
	}

	/**
	 * 跳转到用户组角色分配页面
	 */
	public String toRoleManagerPage() {
		try {
			// 查询出所有角色
			listSysRole = targetISysRoleBS.getAll();
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}
		// 查出当前用户组
		sysUserGroup = sysUserGroupBS.findById(SysUserGroup.class, this
				.getSysUserGroup().getId());
		listSysRole.removeAll(sysUserGroup.getSetSysRole());
		return "toRoleManagerPage";
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String sysUserGroupEdit() {
		try {
			sysUserGroupBS.update(sysUserGroup, sysUserGroup.getId());
			this.message = this.getSuccessMessage("修改成功！", "userGroupManager",
					"closeCurrent",
					"sys-user-group/sys-user-group!listUserGroup.do");
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("修改失败！");
		}
		return "SUCCESS";
	}

	/**
	 * 根据条件查询
	 * 
	 * @return
	 */
	public String listUserGroup() {
		if (strQuery != null && !"".equals(strQuery)) {
			query = Util.toMap(strQuery);
		}
		sysPageInfo = this.getSysPageInfo();
		String strCountHql = "select count(*) from SysUserGroup  where 1=1";
		// 设置查询结果的最大条数
		Long maxCount = this.sysUserGroupBS.getCountByHQL(strCountHql, query);
		sysPageInfo.setMaxCount(maxCount);
		String hql = "from SysUserGroup where 1=1";
		
		listSysUserGroup = sysUserGroupBS.findPageByQuery(sysPageInfo, hql,
				query, this.getSysOrderByInfo());
		strQuery = Util.toStrQuery(query);
		return "list";
	}

	public SysUserGroup getUserGroup() {
		if (sysUserGroup == null)
			sysUserGroup = new SysUserGroup();
		return sysUserGroup;
	}

	public void setUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	public SysUserGroup getSysUserGroup() {
		if (sysUserGroup == null) {
			sysUserGroup = new SysUserGroup();
		}
		return sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	public ISysUserGroupBS getSysUserGroupBS() {
		return sysUserGroupBS;
	}

	public void setSysUserGroupBS(ISysUserGroupBS sysUserGroupBS) {
		this.sysUserGroupBS = sysUserGroupBS;
	}

	public SysPageInfo getSysPageInfo() {
		if (sysPageInfo == null) {
			sysPageInfo = new SysPageInfo();
		}
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum() - 1)
				* this.getNumPerPage());
		sysPageInfo.setCurrentPage(this.getPageNum());
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<SysUserGroup> getListSysUserGroup() {
		return listSysUserGroup;
	}

	public void setListSysUserGroup(List<SysUserGroup> listSysUserGroup) {
		this.listSysUserGroup = listSysUserGroup;
	}

	public List<CmUser> getListTbUser() {
		return listTbUser;
	}

	public void setListTbUser(List<CmUser> listTbUser) {
		this.listTbUser = listTbUser;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}

	public List<SysRole> getListSysRole() {
		return listSysRole;
	}

	public void setListSysRole(List<SysRole> listSysRole) {
		this.listSysRole = listSysRole;
	}

	public void setTargetISysRoleBS(ISysRoleBS targetISysRoleBS) {
		this.targetISysRoleBS = targetISysRoleBS;
	}

	public ISysUserGroupRelationBS getSysUserGroupRelationBS() {
		return sysUserGroupRelationBS;
	}

	public void setSysUserGroupRelationBS(
			ISysUserGroupRelationBS sysUserGroupRelationBS) {
		this.sysUserGroupRelationBS = sysUserGroupRelationBS;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	

}
