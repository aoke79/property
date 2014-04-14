package com.sinoframe.web.action.sysRelationAccountRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.comm.util.Util;
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
		@Result(name = "view", location = "/system/sysRelationAccountRole/sysRelationAccountRoleAdd.jsp"),
		@Result(name = "list", location = "/system/sysRelationAccountRole/sysRelationAccountRoleList.jsp"),
		@Result(name = "addPage", location = "/system/sysRelationAccountRole/sysRelationAccountRoleAdd.jsp"),
		@Result(name = "editPage", location = "/system/sysRelationAccountRole/sysRelationAccountRoleEdit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
public class SysRelationAccountRoleAction extends BaseAction {

	private static final long serialVersionUID = -4942023052082335487L;
	// 服务类
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
	// 前台传入的角色IDS
	private String[] roleIds=new String[0];
	// 前台传入的描述信息
	private String description;
	// 未分配的角色
	private String options;
	// 已分配的角色
	private String yesOptions;
	// 未分配的角色
	private String noOptions;
	// 已分配的角色Id
	private String yesId;

	// 标志
	private String flg;
	private CmUser cmUser;

	private String ids;

	// 消息实体
	private Message message;

	// 去角色分配页面
	public String goView() throws RollbackableBizException {
		try {
			initRole();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cmUser = cmUserBS.findById(userId);
		return "view";
	}

	public String roleManage() throws RollbackableBizException {
		// 根据页面传回的信息，构造sysPageInfo对象
		try {
			if (this.getPageNum() != 0) {
				this.getSysPageInfo().setPageSize(this.getNumPerPage());
				this.getSysPageInfo().setStartIndex(
						(this.getPageNum() - 1) * this.getNumPerPage());
				this.getSysPageInfo().setCurrentPage(this.getPageNum());
			}
			String string = this.getSysPageInfo().getStartIndex() + "="
					+ this.getSysPageInfo().getStartIndex();
			String hql = "from SysRelationAccountRole r where 1=1 and r.accountRoleId.userId ='"
					+ userId + "'";
			// 定义排序方式及排序列
			SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
			sysOrderByInfo.setOrderAsc("");
			sysOrderByInfo.setOrderColumn("");

			// 获取最大记录数
			String strCountHql = "select count(*) from SysRelationAccountRole r where "
					+ string + "  and r.accountRoleId.userId ='" + userId + "'";
			this.getSysPageInfo()
					.setMaxCount(
							this.targetISysRelationAccountRoleBS
									.getCountByHQL(strCountHql));
			sysRelationAccountRoleList = targetISysRelationAccountRoleBS
					.findPageByQuery(this.getSysPageInfo(), hql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}

	// 对用户角色关系表进行修改
	public String rarEdit() {
		try {
			targetISysRelationAccountRoleBS.update(relationAccountRole,
					relationAccountRole.getAccountRoleId());
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"accountRoleRelationManage", "forward",
					"sys-relation-account-role/sys-relation-account-role!roleManage.do?userId="
							+ relationAccountRole.getAccountRoleId()
									.getUserId() + "&pageNum=" + getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 删除指定的某条数据
	public String delSysRelationAccountRole() {
		try {
			targetISysRelationAccountRoleBS.delById(ruser);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"accountRoleRelationManage", "forward",
					"sys-relation-account-role/sys-relation-account-role!roleManage.do?userId="
							+ ruser.getUserId() + "&pageNum=" + getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("deleteFail"));
		}
		return "SUCCESS";
	}

	public String list() {
		// 查询记录的hql
		String hql = "from SysRelationAccountRole ";
		// 查询记录数的hql
		String strCountHql = "select count(*) from SysRelationAccountRole";
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

	// 去编辑页面
	public String toEditPage() {
		try {
			relationAccountRole = targetISysRelationAccountRoleBS
					.searchRelationAccountRoleBySysRAccountRoleId(ruser);
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}
		return "editPage";
	}

	// 根据条件进行检索
	public String search() throws RollbackableBizException {
		HashMap<String, String> query1 = new HashMap<String, String>();
		Set<Entry<String, String>> entrySet = query.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, String> next = iterator.next();
			if("roleName".equals(next.getKey())){
				query1.put("like_roleName", query.get("roleName"));
			}else if("description".equals(next.getKey())){
				query1.put("like_description", query.get("description"));
			}else if ("eroleName".endsWith(next.getKey())) {
				query1.put("like_eroleName", query.get("eroleName"));
			}
		}
		
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
				+ " and r.accountRoleId.userId ='" + userId + "'";

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
		String strCountHql = "select count(*) from SysRelationAccountRole r where  2=2 and r.accountRoleId.userId ='"
				+ userId + "'";
		this.getSysPageInfo().setMaxCount(
				this.targetISysRelationAccountRoleBS.getCountByHQL(strCountHql,
						query1));
		sysRelationAccountRoleList = targetISysRelationAccountRoleBS
				.findPageByQuery(this.getSysPageInfo(), hql, query1,
						sysOrderByInfo);
		return "list";
	}

	// 删除多条记录
	public String multipleDelete() {
		try {
			String[] tempStrs = ids.split(",");
			String userIdString = "";
			userIdString = tempStrs[0].substring(0, tempStrs[0].indexOf("&"));
			targetISysRelationAccountRoleBS.deleteByLianHeIds(this.getIds());
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"accountRoleRelationManage", "forward",
					"sys-relation-account-role/sys-relation-account-role!roleManage.do?userId="
							+ userIdString);
		} catch (Exception e) {
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 给用户分配角色
	public String doFp() {
		try {
			String[] delStrings = doDeleteRole();
			String[] addStrings = doAddRole();
			// 为用户添加角色
			if (addStrings != null) {
				List<SysRelationAccountRole> sysRelationAccountRoleList = new ArrayList<SysRelationAccountRole>();
				SysRelationAccountRole relationAccountRoleTemp;
				SysRAccountRoleId addSysRAccountRoleId;
				for (String roleId : addStrings) {
					relationAccountRoleTemp = new SysRelationAccountRole();
					addSysRAccountRoleId = new SysRAccountRoleId();
					addSysRAccountRoleId.setRoleId(roleId);
					addSysRAccountRoleId.setUserId(userId);
					relationAccountRoleTemp.setDescription(description);
					relationAccountRoleTemp.setFlag("0");
					relationAccountRoleTemp
							.setAccountRoleId(addSysRAccountRoleId);
					sysRelationAccountRoleList.add(relationAccountRoleTemp);
				}
				targetISysRelationAccountRoleBS
						.saveList(sysRelationAccountRoleList);
			}
			// 为用户删除角色
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
					"accountRoleRelationManage", "closeCurrent",
					"sys-relation-account-role/sys-relation-account-role!roleManage.do?userId="
							+ getUserId());

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
				String newString = Util.Array2String(roleIds);
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

	// 从前台传来的数据中找出要添加的角色
	private String[] doAddRole() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {
			if (!"".equals(yesId)) {
				for (String roleIdString : roleIds) {
					if (!yesId.contains(roleIdString)) {
						sb.append(roleIdString + ",");
					}
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}
			} else {

				for (String roleIdString : roleIds) {
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

	private void initRole() throws RollbackableBizException {
		java.util.Locale locale=(Locale) getServletRequest().getSession().getAttribute("WW_TRANS_I18N_LOCALE");
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sbId = new StringBuilder();
		CmUser findById = cmUserBS.findById(userId);
/*		Set<SysRelationAccountRole> sysRelationAccountRoles = findById
				.getSysRelationAccountRoles();*/
		String hql = "from SysRelationAccountRole where cmUser.userId='"+findById.getUserId()+"' ";
		List<SysRelationAccountRole>  sysRelationAccountRoles = this.cmUserBS.findPageByQuery(hql);
		if (sysRelationAccountRoles!=null  &&  sysRelationAccountRoles.size()>0) {
			/*Iterator<SysRelationAccountRole> iterator = sysRelationAccountRoles
					.iterator();
			while (iterator.hasNext()) {
				SysRelationAccountRole next = iterator.next();
				SysRole sysRole = next.getSysRole();
				sb1.append("<option value='" + sysRole.getRoleId() + "'>");
				if(java.util.Locale.US.equals(locale)){
				  sb1.append(sysRole.getEroleName());
				}else{
				  sb1.append(sysRole.getRoleName());
				}
				sb1.append("</option>");
				sbId.append("'" + sysRole.getRoleId() + "',");*/
			//by lujie
					for(SysRelationAccountRole s : sysRelationAccountRoles){
						sb1.append("<option value='" + s.getSysRole().getRoleId() + "'>");
						if(java.util.Locale.US.equals(locale)){
						  sb1.append(s.getSysRole().getEroleName());
						}else{
						  sb1.append(s.getSysRole().getRoleName());
						}
						sb1.append("</option>");
						sbId.append("'" + s.getSysRole().getRoleId() + "',");
					}
		//	}
			
			yesOptions = sb1.toString();
			String tempString = sbId.substring(0, sbId.lastIndexOf(","));
			yesId = tempString;
			List<SysRole> noIdList = targetISysRoleBS
					.findByNotInIds(tempString);
			StringBuilder sb = new StringBuilder();
			StringBuilder nosb = new StringBuilder();
			for (SysRole tsysRole : noIdList) {
				sb.append("<option value='" + tsysRole.getRoleId() + "'>");
				    if(java.util.Locale.US.equals(locale)){
					    sb.append(tsysRole.getEroleName());
					}else{
						sb.append(tsysRole.getRoleName());
					}
				sb.append("</option>");
				nosb.append(tsysRole.getRoleId() + ",");
			}
			noOptions = sb.toString();
		} else {
			List<SysRole> noIdList = targetISysRoleBS.getAll();
			StringBuilder sb = new StringBuilder();
			StringBuilder nosb = new StringBuilder();
			for (SysRole tsysRole : noIdList) {
				sb.append("<option value='" + tsysRole.getRoleId() + "'>");
				if(java.util.Locale.US.equals(locale)){
					    sb.append(tsysRole.getEroleName());
				}else{
						sb.append(tsysRole.getRoleName());
				}
				sb.append("</option>");
				nosb.append(tsysRole.getRoleId() + ",");
			}
			noOptions = sb.toString();

		}

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
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

	public ICmUserBS getTbUserBS() {
		return cmUserBS;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
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

	public SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
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
