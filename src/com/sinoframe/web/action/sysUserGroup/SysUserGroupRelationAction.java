package com.sinoframe.web.action.sysUserGroup;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysUserGroup;
import com.sinoframe.bean.SysUserGroupRelation;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysUserGroupBS;
import com.sinoframe.business.ISysUserGroupRelationBS;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.web.action.BaseAction;

/**
 * SysUserGroupRealtionAction
 * 
 * @author Administrator
 * 
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/system/sysUserGroupRealtion/sysUserGroupRelationList.jsp"),
		@Result(name = "addPage", location = "/system/sysUserGroupRelation/sysUserGroupRelationAdd.jsp"),
		@Result(name = "edit", location = "/system/sysUserGroupRealtion/sysUserGroupRelationEdit.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sys-user-group/sys-user-group-relation!listByGroup.do", type = "redirectAction") })
public class SysUserGroupRelationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	// 实体类
	private SysUserGroupRelation sysUserGroupRelation;
	private SysUserGroup sysUserGroup;
	private CmUser cmUser;
	// 存储数据的list集合
	private List<SysUserGroupRelation> listSysUserGroupRelation;
	// 方法类
	private ISysUserGroupRelationBS sysUserGroupRelationBS;
	private ICmUserBS cmUserBS;
	private ISysUserGroupBS sysUserGroupBSA;

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
	// 接收检索是的排序列
	private String orderField;

	public SysUserGroupRelation getSysUserGroupRelation() {
		if (sysUserGroupRelation == null) {
			sysUserGroupRelation = new SysUserGroupRelation();
		}
		return sysUserGroupRelation;
	}

	public void setSysUserGroupRealtion(
			SysUserGroupRelation sysUserGroupRealtion) {
		this.sysUserGroupRelation = sysUserGroupRealtion;
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

	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	public ICmUserBS getTbUserBS() {
		return cmUserBS;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}

	public ISysUserGroupBS getSysUserGroupBSA() {
		return sysUserGroupBSA;
	}

	public void setSysUserGroupBSA(ISysUserGroupBS sysUserGroupBSA) {
		this.sysUserGroupBSA = sysUserGroupBSA;
	}

	// 列出当前用户组的用户
	public String listByGroup() {
		sysPageInfo = this.getSysPageInfo();
		Date date = new Date();
		String strCountHql = "select count(*) from SysUserGroupRelation where '"
				+ date + "' = '" + date
				+ "' and sysUserGroup.id = '"
				+ this.getSysUserGroup().getId() + "'";
		long l = this.sysUserGroupRelationBS.getCountByHQL(strCountHql);
		sysPageInfo.setMaxCount(l);

		// 构建查询语句
		String strHql = "from SysUserGroupRelation where '" + date + "' = '" + date
				+ "' and sysUserGroup.id = '" + this.getSysUserGroup().getId()
				+ "'";

		// 根据查询语句查询符合条件的数据
		listSysUserGroupRelation = sysUserGroupRelationBS.findPageByQuery(
				sysPageInfo, strHql);
		return "list";
	}
	
	public String serachUser() {
		sysPageInfo = this.getSysPageInfo();
		String strCountHql = "select count(*) from SysUserGroupRelation where 1=1 and sysUserGroup.id = '"
				+ this.getSysUserGroup().getId() + "'";
		long l = this.sysUserGroupRelationBS.getCountByHQL(strCountHql);
		sysPageInfo.setMaxCount(l);

		// 构建查询语句
		String strHql = "from SysUserGroupRelation where 1=1 and sysUserGroup.id = '" + this.getSysUserGroup().getId()
				+ "'";

		// 根据查询语句查询符合条件的数据
		listSysUserGroupRelation = sysUserGroupRelationBS.findPageByQuery(
				sysPageInfo, strHql);
		return "list";
	}

	

	// 更改状态
	public String updateState() {
		// 判断当前关系的状态
		try {
			sysUserGroupRelation = this.sysUserGroupRelationBS.findById(
					SysUserGroupRelation.class, this.getSysUserGroupRelation()
							.getId());
			sysUserGroupRelationBS.updateState(sysUserGroupRelation);
			this.message = this.getSuccessMessage("修改成功", null,
					"forward",
					"sys-user-group/sys-user-group-relation!listByGroup.do?sysUserGroup.id="
							+ this.sysUserGroupRelation.getId().getGroupId()
							+ "&pageNum=" + this.getPageNum() + "&numPerPage="
							+ this.getNumPerPage());
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("修改失败！");
		}
		return "success";
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

	public List<SysUserGroupRelation> getListSysUserGroupRelation() {
		return listSysUserGroupRelation;
	}

	public void setListSysUserGroupRelation(
			List<SysUserGroupRelation> listSysUserGroupRelation) {
		this.listSysUserGroupRelation = listSysUserGroupRelation;
	}

	public void setSysUserGroupRelation(
			SysUserGroupRelation sysUserGroupRelation) {
		this.sysUserGroupRelation = sysUserGroupRelation;
	}

	public void setSysUserGroupRelationBS(
			ISysUserGroupRelationBS sysUserGroupRelationBS) {
		this.sysUserGroupRelationBS = sysUserGroupRelationBS;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		String string = date + "="
		+ date;
		System.out.println(string);
	}
}
