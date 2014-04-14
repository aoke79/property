package com.sinoframe.web.action.sysRelationAccountRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.comm.util.Util;
import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRAccountRoleId;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysRelationAccountRoleBS;
import com.sinoframe.business.ISysRoleBS;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.web.action.BaseAction;

@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "view", location = "/system/sysRelationAccountRole/sysRelationRoleAccountAdd.jsp"),
		@Result(name = "list", location = "/system/sysRelationAccountRole/sysRelationAccountRoleList.jsp"),
		@Result(name = "listUsers", location = "/system/sysRelationAccountRole/sysRelationRoleAccountList.jsp"),
		@Result(name = "addPage", location = "/system/sysRelationAccountRole/sysRelationAccountRoleAdd.jsp"),
		@Result(name = "editPageRoleAccount", location = "/system/sysRelationAccountRole/sysRelationRoleAccountEdit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
public class SysRelationRoleAccountAction extends BaseAction {

	private static final long serialVersionUID = -4942023052082335487L;
	// 服务对象
	private ISysRoleBS targetISysRoleBS;
	private ICmUserBS cmUserBS;
	private ISysRelationAccountRoleBS targetISysRelationAccountRoleBS;
	// 分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	// 页码
	private int pageNum = 1;
	// 每页显示条数
	private int numPerPage = 20;

	// 排序字段
	private String orderBlock;
	// 排序方式
	private int orderMethod = 0;
	private String isOrder;
	// 排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();

	private SysRAccountRoleId ruser;
	private SysRelationAccountRole relationAccountRole;
	private SysRole role;

	private List<SysRelationAccountRole> sysRelationAccountRoleList = new ArrayList<SysRelationAccountRole>();
	// 检索传入后台的数据
	private Map<String, String> query = new HashMap<String, String>();
	// 前台显示传入的用户ID
	private String userId;
	private String roleId;
	// 前台传入的用户IDS
	private String[] userIds=new String[0];
	// 未分配的用户
	private String options;
	// 已分配的用户
	private String yesOptions;
	// 未分配的用户
	private String noOptions;
	// 已分配的用户Id
	private String yesId;
	// 标志
	private String flg;
	private SysRole sysRole;
	private String ids;

	// 消息实体
	private Message message;

	// 去为角色分配用户页面
	public String goRoleUser() throws RollbackableBizException {
		initRole();
		role = targetISysRoleBS.findById(SysRole.class, roleId);
		return "view";
	}

	// 通过角色查找所有用户 适用页面sysRelationRoleAccountList.jsp
	public String searchUsers() throws RollbackableBizException {
		// 根据页面传回的信息，构造sysPageInfo对象
		if (this.getPageNum() != 0) {
			this.getSysPageInfo().setPageSize(this.getNumPerPage());
			this.getSysPageInfo().setStartIndex(
					(this.getPageNum() - 1) * this.getNumPerPage());
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
		}
		String string = this.getSysPageInfo().getStartIndex() + "="
				+ this.getSysPageInfo().getStartIndex();
		String hql = "from SysRelationAccountRole r where 1=1 and r.accountRoleId.roleId ='"
				+ roleId + "'";
		// 定义排序方式及排序列
		SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");

		// 获取最大记录数
		String strCountHql = "select count(*) from SysRelationAccountRole r where "
				+ string + "  and r.accountRoleId.roleId ='" + roleId + "'";
		this.getSysPageInfo()
				.setMaxCount(
						this.targetISysRelationAccountRoleBS
								.getCountByHQL(strCountHql));
		sysRelationAccountRoleList = targetISysRelationAccountRoleBS
				.findPageByQuery(this.getSysPageInfo(), hql);
		return "listUsers";
	}

	// 对用户角色关系表进行修改 适用页面sysRelationRoleAccountEdit.jsp
	public String roleUserEdit() {

		try {
			targetISysRelationAccountRoleBS.update(relationAccountRole,
					relationAccountRole.getAccountRoleId());

			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"roleAccountRelationManage", "forward",
					"sys-relation-account-role/sys-relation-role-account!searchUsers.do?roleId="
							+ relationAccountRole.getAccountRoleId()
									.getRoleId() + "&pageNum=" + getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 删除指定的某条数据 适用页面sysRelationRoleAccountList.jsp
	public String delSysRelationRoleAccount() {
		try {
			targetISysRelationAccountRoleBS.delById(ruser);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"roleAccountRelationManage", "forward",
					"sys-relation-account-role/sys-relation-role-account!searchUsers.do?roleId="
							+ ruser.getRoleId() + "&pageNum=" + getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("deleteFail"));
		}
		return "SUCCESS";
	}

	public String list() {
		// by lujie
		// 查询记录的hql
		String hql = "from SysRelationAccountRole where 1=1 ";
		// 查询记录数的hql
		String strCountHql = "select count(*) from SysRelationAccountRole where 1=1 ";
/*		 CmUser cmUser = (CmUser) ActionContext.getContext().getSession().get("user");	
        if(!"超级管理员".equals(cmUser.getName().trim())){
        	hql += " and subsystemId ='"+cmUser.getSubsystemId()+"'";
        	strCountHql += " and subsystemId ='"+cmUser.getSubsystemId()+"'";
        	
        }*/
		// 设置查询的最大记录数
		this.getSysPageInfo()
				.setMaxCount(
						this.targetISysRelationAccountRoleBS
								.getCountByHQL(strCountHql));
		this.getSysPageInfo().setStartIndex(0);
		this.getSysPageInfo().setCurrentPage(this.getPageNum());
		sysRelationAccountRoleList = targetISysRelationAccountRoleBS
				.findPageByQuery(this.getSysPageInfo(), hql);
		return "list";
	}

	// 去编辑适用页面sysRelationRoleAccountList.jsp
	public String toEditPageRoleAccount() {
		try {
			relationAccountRole = targetISysRelationAccountRoleBS
					.searchRelationAccountRoleBySysRAccountRoleId(ruser);
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}
		return "editPageRoleAccount";
	}

	// 根据条件进行检索适用页面sysRelationRoleAccountList.jsp
	public String search() throws RollbackableBizException {
		HashMap<String, String> query1 = new HashMap<String, String>();
		query1.put("like_cmUser.loginName", query.get("loginName"));
		query1.put("like_description", query.get("description"));
		// 根据页面传回的信息，构造sysPageInfo对象
		if (this.getPageNum() != 0) {
			this.getSysPageInfo().setPageSize(this.getNumPerPage());
			this.getSysPageInfo().setStartIndex(
					(this.getPageNum() - 1) * this.getNumPerPage());
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
		}
		String string = this.getSysPageInfo().getStartIndex() + "="
				+ this.getSysPageInfo().getStartIndex();

		String hql = "from SysRelationAccountRole r where  " + string
				+ " and r.accountRoleId.roleId ='" + roleId + "'";

		if ("yes".equals(isOrder)) {
			// 设置排序方式，0为正常排序，1为反相排序
			if (orderMethod == 0) {
				sysOrderByInfo.setOrderAsc("asc");
				orderMethod = 1;
			} else {
				sysOrderByInfo.setOrderAsc("desc");
				orderMethod = 0;
			}
		}
		// 获取排序字段
		sysOrderByInfo.setOrderColumn(orderBlock);
		// 获取最大记录数
		String strCountHql = "select count(*) from SysRelationAccountRole r where  2=2 and r.accountRoleId.roleId ='"
				+ roleId + "'";
		this.getSysPageInfo().setMaxCount(
				this.targetISysRelationAccountRoleBS.getCountByHQL(strCountHql,
						query1));
		sysRelationAccountRoleList = targetISysRelationAccountRoleBS
				.findPageByQuery(this.getSysPageInfo(), hql, query1,
						sysOrderByInfo);
		return "listUsers";
	}

	// 删除多条记录
	public String multipleDelete() {
		try {
			String[] tempStrs = ids.split(",");
			String userIdString = "";
			userIdString = tempStrs[0].substring(tempStrs[0]
					.indexOf("&ruser.roleId=")
					+ "&ruser.roleId=".length());
			targetISysRelationAccountRoleBS.deleteByLianHeIds(this.getIds());
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"roleAccountRelationManage", "forward",
					"sys-relation-account-role/sys-relation-role-account!searchUsers.do?roleId="
							+ userIdString);
		} catch (Exception e) {
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 给角色分配用户
	public String doFp() {
		try {
			String[] delStrings = doDeleteRole();
			String[] addStrings = doAddRole();
			// 为角色添加用户
			if (addStrings != null) {
				List<SysRelationAccountRole> sysRelationAccountRoleList = new ArrayList<SysRelationAccountRole>();
				SysRelationAccountRole relationAccountRoleTemp;
				SysRAccountRoleId addSysRAccountRoleId;
				for (String userId : addStrings) {
					relationAccountRoleTemp = new SysRelationAccountRole();
					addSysRAccountRoleId = new SysRAccountRoleId();
					addSysRAccountRoleId.setRoleId(roleId);
					addSysRAccountRoleId.setUserId(userId);
					relationAccountRoleTemp
							.setAccountRoleId(addSysRAccountRoleId);
					relationAccountRoleTemp.setFlag("0");
					sysRelationAccountRoleList.add(relationAccountRoleTemp);
				}
				targetISysRelationAccountRoleBS
						.saveList(sysRelationAccountRoleList);
			}
			// 为角色删除用户
			if (delStrings != null) {
				SysRAccountRoleId delSysRAccountRoleId;
				for (String delroleId : delStrings) {
					delSysRAccountRoleId = new SysRAccountRoleId();
					delSysRAccountRoleId.setRoleId(delroleId);
					delSysRAccountRoleId.setUserId(userId);
					targetISysRelationAccountRoleBS
							.delById(delSysRAccountRoleId);
				}
			}
			// targetISysRelationAccountRoleBS.getAll(getPaging());
			this.message = this.getSuccessMessage(getText("assignSuccess"),
					"roleAccountRelationManage", "closeCurrent",
					"sys-relation-account-role/sys-relation-role-account!searchUsers.do?roleId="
							+ getRoleId());

		} catch (Exception e) {
			this.message = this.getFailMessage(getText("assignFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 从前台传来的数据中找出要删除的角色
	private String[] doDeleteRole() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {
			if (!"".equals(yesId)) {
				String[] yesIds = yesId.split(",");
				String newString = Util.Array2String(userIds);
				for (String yesIdsSub : yesIds) {
					String subString = yesIdsSub.substring(1, yesIdsSub
							.length() - 1);
					if (!newString.contains(subString)) {
						sb.append(subString + ",");
					}
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}
			}

		}
		return null;
	}

	// 从前台传来的数据中找出要添加的
	private String[] doAddRole() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {
			if (!"".equals(yesId)) {
				for (String roleIdString : userIds) {
					if (!yesId.contains(roleIdString)) {
						sb.append(roleIdString + ",");
					}
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}
			} else {

				for (String roleIdString : userIds) {
					sb.append(roleIdString + ",");
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}

			}
		}

		return null;
	}

	private void initRole() {
		try {
			
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sbId = new StringBuilder();

		//List<SysRelationAccountRole> sysRelationAccountRoles=targetISysRoleBS.findPageByQuery("from SysRelationAccountRole sr where sr.sysRole.roleId='"+roleId+"'");
		SysRole findById = targetISysRoleBS.findById(SysRole.class, roleId);
		Set<SysRelationAccountRole> sysRelationAccountRoles = findById
				.getSysRelationAccountRoles();
		if (sysRelationAccountRoles.size() > 0) {
			for(SysRelationAccountRole sysRelationAccountRole:sysRelationAccountRoles){
				CmUser cmUser = sysRelationAccountRole.getCmUser();
				sb1.append("<option value='" + cmUser.getUserId() + "'>");
				sb1.append(cmUser.getName());
				sb1.append("</option>");
				sbId.append("'" + cmUser.getUserId() + "',");
			}
			yesOptions = sb1.toString();
			String tempString = sbId.substring(0, sbId.lastIndexOf(","));
			yesId = tempString;

			List<CmUser> noIdList = cmUserBS.findByNotInIds(tempString);

			StringBuilder sb = new StringBuilder();
			StringBuilder nosb = new StringBuilder();
			for (CmUser tTbUser : noIdList) {
				sb.append("<option value='" + tTbUser.getUserId() + "'>");
				sb.append(tTbUser.getName());
				sb.append("</option>");
				nosb.append(tTbUser.getUserId() + ",");
			}
			noOptions = sb.toString();
		} else {
			List<CmUser> noIdList = cmUserBS.findAll();
			StringBuilder sb = new StringBuilder();
			StringBuilder nosb = new StringBuilder();
			for (CmUser tTbUser : noIdList) {
				sb.append("<option value='" + tTbUser.getUserId() + "'>");
				sb.append(tTbUser.getName());
				sb.append("</option>");
				nosb.append(tTbUser.getUserId() + ",");
			}
			noOptions = sb.toString();

		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public SysRAccountRoleId getRuser() {
		return ruser;
	}

	public void setRuser(SysRAccountRoleId ruser) {
		this.ruser = ruser;
	}

	public SysRelationAccountRole getRelationAccountRole() {
		return relationAccountRole;
	}

	public void setRelationAccountRole(
			SysRelationAccountRole relationAccountRole) {
		this.relationAccountRole = relationAccountRole;
	}

	public ISysRelationAccountRoleBS getTargetISysRelationAccountRoleBS() {
		return targetISysRelationAccountRoleBS;
	}

	public void setTargetISysRelationAccountRoleBS(
			ISysRelationAccountRoleBS targetISysRelationAccountRoleBS) {
		this.targetISysRelationAccountRoleBS = targetISysRelationAccountRoleBS;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public ISysRoleBS getTargetISysRoleBS() {
		return targetISysRoleBS;
	}

	public void setTargetISysRoleBS(ISysRoleBS targetISysRoleBS) {
		this.targetISysRoleBS = targetISysRoleBS;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getYesOptions() {
		return yesOptions;
	}

	public void setYesOptions(String yesOptions) {
		this.yesOptions = yesOptions;
	}

	public String getYesId() {
		return yesId;
	}

	public void setYesId(String yesId) {
		this.yesId = yesId;
	}

	public String getNoOptions() {
		return noOptions;
	}

	public void setNoOptions(String noOptions) {
		this.noOptions = noOptions;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrderBlock() {
		return orderBlock;
	}

	public void setOrderBlock(String orderBlock) {
		this.orderBlock = orderBlock;
	}

	public int getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(int orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}

	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public List<SysRelationAccountRole> getSysRelationAccountRoleList() {
		return sysRelationAccountRoleList;
	}

	public void setSysRelationAccountRoleList(
			List<SysRelationAccountRole> sysRelationAccountRoleList) {
		this.sysRelationAccountRoleList = sysRelationAccountRoleList;
	}

}
