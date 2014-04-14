package com.sinoframe.web.action.sysRelationRoleOperate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.comm.util.Util;
import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysRelationRoleOperateId;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.ISysOperateBS;
import com.sinoframe.business.ISysRelationRoleOperateBS;
import com.sinoframe.business.ISysRoleBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.web.action.BaseAction;

@ParentPackage("sinoframe-default")
@Results( {
	   // @Result(name = "listnew", type="redirectAction", location = "/sys-relation-role-operate/sys-relation-role-operate!search.do"),
		@Result(name = "list", location = "/system/sysRelationRoleOperate/sysRelationRoleOperateList.jsp"),
		@Result(name = "view", location = "/system/sysRelationRoleOperate/sysRelationRoleOperateAdd.jsp"),
		@Result(name = "viewnew", location = "/system/sysRelationRoleOperate/sysRelationRoleOperateAdd_new.jsp"),
		@Result(name = "editPage", location = "/system/sysRelationRoleOperate/sysRelationRoleOperateEdit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
public class SysRelationRoleOperateAction extends BaseAction {

	private static final long serialVersionUID = -4942023052082335487L;

	private ISysRoleBS targetISysRoleBS;
	private ISysOperateBS sysOperateBS;
	private ISysRelationRoleOperateBS sysRelationRoleOperateBS;

	// 分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	// 排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	// 排序字段
	private String orderBlock;
	// 排序方式
	private int orderMethod = 0;
	private SysRelationRoleOperate sysRelationRoleOperate;
	private SysRelationRoleOperateId sysRelationRoleOperateId;
	private SysRole role;
	private List<SysRelationRoleOperate> sysRelationRoleOperateList = new ArrayList<SysRelationRoleOperate>();
	// 用于前台用户显示
	private Map<String, String> roles = new HashMap<String, String>();
	// 检索传入后台的数据
	private Map<String, String> query = new HashMap<String, String>();
	// 前台显示传入的角色ID
	private String roleId;
	// 前台传入的操作IDS
	private String[] operateIds;
	// 前台传入的描述信息
	private String description;
	private String options;
	// 已分配的操作
	private String yesOptions;
	// 未分配的操作
	private String noOptions;
	// 已分配的操作Id
	private String yesId="";

	private String ids;

	private String flg;

	private List<SysOperate> sysOperateList = new ArrayList<SysOperate>();
	private SysOperate baseOperate;

	// 消息实体
	private Message message;
	
	//当前角色所属的操作ID串：Relation Role Operate IDs(RROIDs)
	String RROIDs="";
	//当前角色所属的操作Name串：Relation Role Operate Names(RRONames)
	String RRONames="";

	// 去角色分配页面
	public String goView() throws RollbackableBizException {
		try{
			this.clearCache();
		role = targetISysRoleBS.searchRoleById(roleId);
		initOPerate();
		//根操作，用于遍历所有的操作权限
		baseOperate = sysOperateBS.findById(SysOperate.class, "-1");
		CmUser cmUser = (CmUser) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().put("userName",cmUser.getSubsystemId());
		ActionContext.getContext().put("userInfo",cmUser.getName());
		if(cmUser.getName().trim().equals("超级管理员"))return "viewnew";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
		//return "viewnew";
	}

	// 根据条件进行检索
	public String search() throws RollbackableBizException {
		HashMap<String, String> query1 = new HashMap<String, String>();
		query1.put("like_sysOperate.operateName", query.get("operateName"));
		query1.put("like_description", query.get("description"));

		// by lujie
		role = sysRelationRoleOperateBS.findById(SysRole.class, roleId);
		String hqls = "from SysRelationRoleOperate where id.roleId='"+roleId+"'";
    	List<SysRelationRoleOperate> sysRelationRoleOperateSet = this.sysOperateBS.findPageByQuery(hqls); //role.getSysRelationRoleOperates();
	      for( SysRelationRoleOperate  s : sysRelationRoleOperateSet){
	    	  RROIDs += s.getSysOperate().getId() + ",";
	    		RRONames += s.getSysOperate().getOperateName()+",";
	      }
	      RROIDs = RROIDs.length() == 0?"":RROIDs.substring(0,RROIDs.length()-1);
	      RRONames = RRONames.length() == 0?"":RRONames.substring(0, RRONames.length()-1);
		// 根据页面传回的信息，构造sysPageInfo对象
		if (this.getPageNum() != 0) {
			this.getSysPageInfo().setPageSize(this.getNumPerPage());
			this.getSysPageInfo().setStartIndex(
					(this.getPageNum() - 1) * this.getNumPerPage());
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
		}
		String string = this.getSysPageInfo().getStartIndex() + "="
				+ this.getSysPageInfo().getStartIndex();

		String hql = "from SysRelationRoleOperate r where  " + string
				+ " and r.id.roleId='" + roleId + "' ";
		// 设置排序方式，0为正常排序，1为反相排序
		if (orderMethod == 0) {
			sysOrderByInfo.setOrderAsc("asc");
			orderMethod = 1;
		} else {
			sysOrderByInfo.setOrderAsc("desc");
			orderMethod = 0;
		}
		// 获取排序字段
		sysOrderByInfo.setOrderColumn(orderBlock);

		// 获取最大记录数
		String strCountHql = "select count(*) from SysRelationRoleOperate r where  2=2 and r.id.roleId='"
				+ roleId + "'";
		this.getSysPageInfo().setMaxCount(
				this.sysRelationRoleOperateBS
						.getCountByHQL(strCountHql, query1));
		sysRelationRoleOperateList = sysRelationRoleOperateBS.findPageByQuery(
				this.getSysPageInfo(), hql, query1, sysOrderByInfo);

		return "list";
	}

	// 进入角色操作关系页面
	public String operateManage() {
		try {
			
		role = sysRelationRoleOperateBS.findById(SysRole.class, roleId);
    	Set<SysRelationRoleOperate> sysRelationRoleOperateSet = role.getSysRelationRoleOperates();
    	// 根据关系集合遍历，拼接操作 ID 串
  /*  	Iterator<SysRelationRoleOperate> iterator = sysRelationRoleOperateSet.iterator();
    	while(iterator.hasNext()){
    		SysRelationRoleOperate next = iterator.next();
    		SysOperate tempOperate = next.getSysOperate();
    		sysOperateList.add(tempOperate);
    		//当前角色所有的操作权限传到前台页面用于判断
    		RROIDs += tempOperate.getId() + ",";
    		RRONames += tempOperate.getOperateName()+",";
    	}
    	RROIDs = RROIDs.length() == 0?"":RROIDs.substring(0,RROIDs.length()-1);
    	RRONames = RRONames.length() == 0?"":RRONames.substring(0, RRONames.length()-1);*/
    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 根据页面传回的信息，构造sysPageInfo对象
			if (this.getPageNum() != 0) {
				this.getSysPageInfo().setPageSize(this.getNumPerPage());
				this.getSysPageInfo().setStartIndex(
						(this.getPageNum() - 1) * this.getNumPerPage());
				this.getSysPageInfo().setCurrentPage(this.getPageNum());
			}
			String string = this.getSysPageInfo().getStartIndex() + "="
					+ this.getSysPageInfo().getStartIndex();
			String hql = "from SysRelationRoleOperate r where  1=1 and r.id.roleId='"
					+ roleId + "' ";
			// 定义排序方式及排序列
			SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
			sysOrderByInfo.setOrderAsc("");
			sysOrderByInfo.setOrderColumn("");
			// 获取最大记录数
			String strCountHql = "select count(*) from SysRelationRoleOperate r where "
					+ string + "  and r.id.roleId='" + roleId + "'";
			this.getSysPageInfo().setMaxCount(
					this.sysRelationRoleOperateBS.getCountByHQL(strCountHql));
			sysRelationRoleOperateList = sysRelationRoleOperateBS
					.findPageByQuery(this.getSysPageInfo(), hql);
			sysRelationRoleOperateListc = sysRelationRoleOperateBS
					.findPageByQuery(hql);
			for (SysRelationRoleOperate s : sysRelationRoleOperateListc) {
				RROIDs += s.getSysOperate().getId() + ",";
				sysOperateList.add(s.getSysOperate());
				RRONames += s.getSysOperate().getOperateName() + ",";
			}
			RROIDs = RROIDs.length() == 0 ? "" : RROIDs.substring(0, RROIDs
					.length() - 1);
			RRONames = RRONames.length() == 0 ? "" : RRONames.substring(0,
					RRONames.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除指定的某条数据
	public String delSysRelationRoleOperate() {
		try {
			sysRelationRoleOperateBS.deleteById(SysRelationRoleOperate.class,
					sysRelationRoleOperateId);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"roleOperateRelationManage", "forward",
					"sys-relation-role-operate/sys-relation-role-operate!operateManage.do?roleId="
							+ sysRelationRoleOperateId.getRoleId()
							+ "&pageNum=" + getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("deleteFail"));
		}
		return "SUCCESS";
	}

	// 删除多条记录
	public String multipleDelete() {
		try {

			String[] tempStrs = ids.split(",");
			String roleIdString = "";
			roleIdString = tempStrs[0].substring(0, tempStrs[0].indexOf("&"));
			sysRelationRoleOperateBS.deleteByLianHeIds(this.getIds());
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"roleAccountRelationManage", "forward",
					"sys-relation-role-operate/sys-relation-role-operate!operateManage.do?roleId="
							+ roleIdString);
		} catch (Exception e) {
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 去编辑页面
	public String toEditPage() {
		try {
			sysRelationRoleOperate = sysRelationRoleOperateBS
					.getById(sysRelationRoleOperateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editPage";
	}

	// 对用户角色关系表进行修改
	public String sysRelationRoleOperateEdit() {
		try {
			sysRelationRoleOperateBS.update(sysRelationRoleOperate,
					sysRelationRoleOperate.getId());
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"roleOperateRelationManage", "forward",
					"sys-relation-role-operate/sys-relation-role-operate!operateManage.do?roleId="
							+ sysRelationRoleOperate.getId().getRoleId()
							+ "&pageNum=" + getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 给用户分配权限
	public String doFp() {
		try {
			this.clearCache();
			role = sysRelationRoleOperateBS.findById(SysRole.class, roleId);
			//将从添加页面返回的 RROIDs 操作ID串，放入 operateIds String[]数组中
			operateIds = RROIDs.split(",");
			StringBuilder sbId = new StringBuilder();
			List<SysOperate> list=sysRelationRoleOperateBS.findPageByQuery("select r.sysOperate from SysRelationRoleOperate r where r.sysRole.roleId='"+roleId+"'");
			if(list.size()>0){
			for(SysOperate sysOperate:list){
				sbId.append(sysOperate.getId() + ",");
			}
			
			String tempString = sbId.substring(0, sbId.lastIndexOf(","));
			yesId = tempString;
			}
			// 处理删除的权限
			String[] delStrings = doDeleteOperate();
			// 处理新增的权限
			String[] addStrings = doAddOperate();
			// 为当前角色添加操作
			if (addStrings != null) {
				List<SysRelationRoleOperate> sysRelationRoleOperateList = new ArrayList<SysRelationRoleOperate>();
				SysRelationRoleOperate sysRelationRoleOperateTemp;
				SysRelationRoleOperateId addSysRelationRoleOperateId;
				for (String operateId : addStrings) {
					sysRelationRoleOperateTemp = new SysRelationRoleOperate();
					addSysRelationRoleOperateId = new SysRelationRoleOperateId();
					addSysRelationRoleOperateId.setRoleId(roleId);
					addSysRelationRoleOperateId.setOperateId(operateId.trim());
					sysRelationRoleOperateTemp
							.setId(addSysRelationRoleOperateId);
					sysRelationRoleOperateTemp.setFlag("0");
					sysRelationRoleOperateList.add(sysRelationRoleOperateTemp);
				}
				sysRelationRoleOperateBS.saveList(sysRelationRoleOperateList);
			}
			this.clearCache();
			// 为当前角色删除操作
			if (delStrings != null) {
				SysRelationRoleOperateId delSysRelationRoleOperateId;
				for (String deloperateId : delStrings) {
					delSysRelationRoleOperateId = new SysRelationRoleOperateId();
					delSysRelationRoleOperateId.setRoleId(roleId);
					delSysRelationRoleOperateId.setOperateId(deloperateId);
					sysRelationRoleOperateBS.deleteById(
							SysRelationRoleOperate.class,
							delSysRelationRoleOperateId);
				}
			}
			this.message = this.getSuccessMessage("分配成功",
					"roleOperateRelationManage", "",
					"sys-relation-role-operate/sys-relation-role-operate!operateManage.do?roleId="
							+ roleId);
		} catch (Exception e) {
			this.message = this.getFailMessage("分配失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 回显全部数据
	public String list() throws Exception {
		// 查询记录的hql
		String hql = "from SysRelationRoleOperate ";
		// 查询记录数的hql
		String strCountHql = "select count(*) from SysRelationRoleOperate";
		// 设置查询的最大记录数
		this.getSysPageInfo().setMaxCount(
				this.sysRelationRoleOperateBS.getCountByHQL(strCountHql));
		this.getSysPageInfo().setStartIndex(0);
		this.getSysPageInfo().setCurrentPage(this.getPageNum());
		sysRelationRoleOperateList = sysRelationRoleOperateBS.findPageByQuery(
				this.getSysPageInfo(), hql);
		return "list";
	}

	// 从前台传来的数据中找出要删除的操作
	private String[] doDeleteOperate() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {
			if (!"".equals(yesId)) {
				String[] yesIds = yesId.split(",");
				String newString = Util.Array2String(operateIds);
				for (String yesIdsSub : yesIds) {
					if (!newString.contains(yesIdsSub)) {
						sb.append(yesIdsSub + ",");
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

	// 从前台传来的数据中找出要添加的操作
	private String[] doAddOperate() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {
			if (!"".equals(yesId)) {
				// 此时要添加的操作
				for (String operateIdString : operateIds) {
					if (!yesId.contains(operateIdString.trim())) {
						sb.append(operateIdString + ",");
					}
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}
			} else {

				for (String operateIdString : operateIds) {
					sb.append(operateIdString + ",");
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}

			}
		}

		return null;
	}
	// 进入角色操作管理页面时显示当前角色的也分配操作和未分配操作
	private void initOPerate() {
		sysOperateList=sysRelationRoleOperateBS.findPageByQuery("select r.sysOperate from SysRelationRoleOperate r where r.sysRole.roleId='"+roleId+"'");
	}
	//清空缓存
	public void clearCache(){
		sysRelationRoleOperateBS.clear(SysOperate.class);
		sysRelationRoleOperateBS.clear(SysRole.class);
		sysRelationRoleOperateBS.clear(SysRelationRoleOperate.class);
	}
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String[] getOperateIds() {
		return operateIds;
	}

	public void setOperateIds(String[] operateIds) {
		this.operateIds = operateIds;
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

	public Map<String, String> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
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

	public ISysOperateBS getSysOperateBS() {
		return sysOperateBS;
	}

	public void setSysOperateBS(ISysOperateBS sysOperateBS) {
		this.sysOperateBS = sysOperateBS;
	}

	public ISysRelationRoleOperateBS getSysRelationRoleOperateBS() {
		return sysRelationRoleOperateBS;
	}

	public void setSysRelationRoleOperateBS(
			ISysRelationRoleOperateBS sysRelationRoleOperateBS) {
		this.sysRelationRoleOperateBS = sysRelationRoleOperateBS;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public SysRelationRoleOperate getSysRelationRoleOperate() {
		return sysRelationRoleOperate;
	}

	public void setSysRelationRoleOperate(
			SysRelationRoleOperate sysRelationRoleOperate) {
		this.sysRelationRoleOperate = sysRelationRoleOperate;
	}

	public SysRelationRoleOperateId getSysRelationRoleOperateId() {
		return sysRelationRoleOperateId;
	}

	public void setSysRelationRoleOperateId(
			SysRelationRoleOperateId sysRelationRoleOperateId) {
		this.sysRelationRoleOperateId = sysRelationRoleOperateId;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}

	public List<SysRelationRoleOperate> getSysRelationRoleOperateList() {
		return sysRelationRoleOperateList;
	}

	public void setSysRelationRoleOperateList(
			List<SysRelationRoleOperate> sysRelationRoleOperateList) {
		this.sysRelationRoleOperateList = sysRelationRoleOperateList;
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

	public List<SysOperate> getSysOperateList() {
		return sysOperateList;
	}

	public void setSysOperateList(List<SysOperate> sysOperateList) {
		this.sysOperateList = sysOperateList;
	}

	public SysOperate getBaseOperate() {
		return baseOperate;
	}

	public void setBaseOperate(SysOperate baseOperate) {
		this.baseOperate = baseOperate;
	}
	
	public String getRROIDs() {
		return RROIDs;
	}

	public void setRROIDs(String ds) {
		RROIDs = ds;
	}

	public String getRRONames() {
		return RRONames;
	}

	public void setRRONames(String names) {
		RRONames = names;
	}
	private List<SysRelationRoleOperate> sysRelationRoleOperateListc = new ArrayList<SysRelationRoleOperate>();

	public List<SysRelationRoleOperate> getSysRelationRoleOperateListc() {
		return sysRelationRoleOperateListc;
	}

	public void setSysRelationRoleOperateListc(
			List<SysRelationRoleOperate> sysRelationRoleOperateListc) {
		this.sysRelationRoleOperateListc = sysRelationRoleOperateListc;
	}
	
}
