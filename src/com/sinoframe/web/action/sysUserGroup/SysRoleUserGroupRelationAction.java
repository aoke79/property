package com.sinoframe.web.action.sysUserGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRoleUserGroupRelation;
import com.sinoframe.bean.SysUserGroup;
import com.sinoframe.business.ISysRoleUserGroupRelationBS;
import com.sinoframe.business.ISysUserGroupBS;
import com.sinoframe.web.action.BaseAction;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/system/sysRoleUserGroupRelation/sysRoleUserGroupRelationList.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sys-user-group/sys-user-group-relation!listByGroup.do", type = "redirectAction") })
public class SysRoleUserGroupRelationAction extends BaseAction {

	private SysUserGroup sysUserGroup;
	private List<SysRoleUserGroupRelation> listSysRoleUserGroupRelation = new ArrayList<SysRoleUserGroupRelation>();
	private ISysUserGroupBS sysUserGroupBS;
	private ISysRoleUserGroupRelationBS roleUserGroupRelationBS;
	private SysRoleUserGroupRelation sysRoleUserGroupRelation;

	// 用于分页对象
	private SysPageInfo sysPageInfo;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 消息实体
	private Message message;
	// 存储要删除的机构的id
	private String ids;
	// 接收检索是的排序列
	private String orderField;

	/**
	 * 列出当前用户组的所有角色
	 * 
	 * @return
	 */
	public String listByUserGroup() {

		sysPageInfo = this.getSysPageInfo();
		Date date = new Date();
		// 获取最大记录数
		String strCountHql = "select count(*) from SysRoleUserGroupRelation where '"
				+ date
				+ "' = '"
				+ date
				+ "' and roleUsergroupId.groupId = '"
				+ this.getSysUserGroup().getId() + "'";

		sysPageInfo.setMaxCount(this.roleUserGroupRelationBS
				.getCountByHQL(strCountHql));

		// 通过Id查找相应的用户组对象
		sysUserGroup = sysUserGroupBS.findById(SysUserGroup.class, this
				.getSysUserGroup().getId());

		// 拼写hql查询语句
		String hql = "from SysRoleUserGroupRelation where '" + date + "' = '"
				+ date + "' and roleUsergroupId.groupId = '"
				+ this.getSysUserGroup().getId() + "'";
		listSysRoleUserGroupRelation = roleUserGroupRelationBS.findPageByQuery(
				sysPageInfo, hql);

		return "list";
	}

	public String serachRole() {

		sysPageInfo = this.getSysPageInfo();
		// 获取最大记录数
		String strCountHql = "select count(*) from SysRoleUserGroupRelation where 1=1 and roleUsergroupId.groupId = '"
				+ this.getSysUserGroup().getId() + "'";

		sysPageInfo.setMaxCount(this.roleUserGroupRelationBS
				.getCountByHQL(strCountHql));

		// 通过Id查找相应的用户组对象
		sysUserGroup = sysUserGroupBS.findById(SysUserGroup.class, this
				.getSysUserGroup().getId());
		// 拼写hql查询语句
		String hql = "from SysRoleUserGroupRelation where 1=1 and roleUsergroupId.groupId = '"
				+ this.getSysUserGroup().getId() + "'";
		listSysRoleUserGroupRelation = roleUserGroupRelationBS.findPageByQuery(
				sysPageInfo, hql);

		return "list";
	}

	/**
	 * 更改用户组角色关系的状态
	 * 
	 * @return
	 */
	public String updateState() {
		try {
			// 在查找到相应的对象
			sysRoleUserGroupRelation = this.roleUserGroupRelationBS
					.findById(SysRoleUserGroupRelation.class, this
							.getSysRoleUserGroupRelation().getRoleUsergroupId());
			// 改变其现有状态
			roleUserGroupRelationBS.updateState(sysRoleUserGroupRelation);
			this.message = this
					.getSuccessMessage(
							"修改成功",
							null,
							"forward",
							"sys-user-group/sys-role-user-group-relation!listByUserGroup.do?sysUserGroup.id="
									+ this.getSysRoleUserGroupRelation()
											.getRoleUsergroupId().getGroupId()
									+ "&pageNum="
									+ this.getPageNum()
									+ "&numPerPage=" + this.getNumPerPage());
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("修改失败！");
		}
		return "success";
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

	public List<SysRoleUserGroupRelation> getListSysRoleUserGroupRelation() {
		return listSysRoleUserGroupRelation;
	}

	public void setListSysRoleUserGroupRelation(
			List<SysRoleUserGroupRelation> listSysRoleUserGroupRelation) {
		this.listSysRoleUserGroupRelation = listSysRoleUserGroupRelation;
	}

	public void setSysUserGroupBS(ISysUserGroupBS sysUserGroupBS) {
		this.sysUserGroupBS = sysUserGroupBS;
	}

	public void setRoleUserGroupRelationBS(
			ISysRoleUserGroupRelationBS roleUserGroupRelationBS) {
		this.roleUserGroupRelationBS = roleUserGroupRelationBS;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public SysRoleUserGroupRelation getSysRoleUserGroupRelation() {
		if (this.sysRoleUserGroupRelation == null) {
			sysRoleUserGroupRelation = new SysRoleUserGroupRelation();
		}
		return sysRoleUserGroupRelation;
	}

	public void setSysRoleUserGroupRelation(
			SysRoleUserGroupRelation sysRoleUserGroupRelation) {
		this.sysRoleUserGroupRelation = sysRoleUserGroupRelation;
	}

}
