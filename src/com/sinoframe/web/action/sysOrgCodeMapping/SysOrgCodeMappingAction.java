///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  SysOrgCodeMapping的action方法</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft jilili
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
 **/
package com.sinoframe.web.action.sysOrgCodeMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysOrgCodeMapping;
import com.sinoframe.bean.SysOrgCodeMappingId;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.ISysOrgCodeMappingBS;
import com.sinoframe.business.ISysOrganizationBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "toAdd", location = "/system/sysOrgCodeMapping/orgCodeMappingAdd.jsp"),
		@Result(name = "list", location = "/system/sysOrgCodeMapping/orgCodeMappingList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sys-org-code-mapping!list.do", type = "redirectAction"),
		@Result(name = "toEdit", location = "/system/sysOrgCodeMapping/orgCodeMappingEdit.jsp") })
public class SysOrgCodeMappingAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ISysOrgCodeMappingBS sysOrgCodeMappingBS;

	private ISysOrganizationBS sysOrganizationBS;

	private SysOrgCodeMapping sysOrgCodeMapping;

	private SysOrganization sysOrganization;

	private SysOrgCodeMapping newSysOrgCodeMapping;

	// 所有处于“使用”状态的机构对象的集合
	private List<SysOrganization> listSysOrganziation = new ArrayList<SysOrganization>();
	// 用于存储查询结果的List集合
	private List<SysOrgCodeMapping> listSysOrgCodeMapping = new ArrayList<SysOrgCodeMapping>();
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 消息实体
	private Message message;
	// 分页对象
	private SysPageInfo sysPageInfo;
	private int pageNum = 1;
	// 接收页面显示记录的条数
	private int numPerPage = 20;
	// 接收检索是的排序列
	private String orderField;
	// 用于存储将要删除的机构的id
	private String ids;
	// 用于存储查询条件的字符串形式
	private String strQuery;

	/**
	 * 跳转到保存数据的页面
	 */
	public String toAdd() {
		sysOrganization = sysOrganizationBS.findById(SysOrganization.class,
				this.getSysOrganization().getId());
		return "toAdd";
	}

	/**
	 * 保存数据
	 */
	public String addMapping() {
		try {
			sysOrgCodeMappingBS.save(this.getSysOrgCodeMapping());
			System.out.println(this.getSysOrgCodeMapping().getId().getOrgId());
			this.message = this.getSuccessMessage("保存成功", "orgMappingManager",
					"closeCurrent",
					"sys-org-code-mapping/sys-org-code-mapping!list.do");
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("保存失败！");
		}
		return "SUCCESS";
	}

	/*
	 * 跳转到修改数据的页面
	 */
	public String toEdit() {
		// 获得id的String形式
		String strId = this.getServletRequest().getParameter("sid");
		if (strId != null) {
			// 依据获得的id构建sysOrgCodeMappingId实例
			String[] id = strId.split(";");
			SysOrgCodeMappingId sysOrgCodeMappingId = new SysOrgCodeMappingId(
					id[0], id[1]);
			// 设置SysOrgCodeMapping对象的id
			this.getSysOrgCodeMapping().setId(sysOrgCodeMappingId);
		}
		sysOrgCodeMapping = sysOrgCodeMappingBS.findById(
				SysOrgCodeMapping.class, this.getSysOrgCodeMapping().getId());
		strQuery = Util.toStrQuery(query);
		return "toEdit";
	}

	/*
	 * 修改数据
	 */
	public String edit() {
	System.out.println(sysOrganization.getId() + "============");
		try {
			sysOrgCodeMapping = sysOrgCodeMappingBS.findById(
					SysOrgCodeMapping.class, this.getSysOrgCodeMapping()
							.getId());
			sysOrgCodeMappingBS.modify(sysOrgCodeMapping,
					this.getNewSysOrgCodeMapping());
			this.message = this.getSuccessMessage("保存成功", "orgMappingManager",
					"closeCurrent",
					"sys-org-code-mapping/sys-org-code-mapping!list.do?");
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("更新失败！");
		}
		return "SUCCESS";
	}

	/**
	 * 删除数据
	 */
	public String delete() {
		try {
			sysOrgCodeMappingBS.delete(this.getSysOrgCodeMapping());
			sysPageInfo = this.getSysPageInfo();
			String strCountHql = "select count(*) from SysOrgCodeMapping where sysOrganization.id = '"
					+ this.getSysOrgCodeMapping().getId().getOrgId() + "'";
			// 获得数据的最大条数
			Long maxCount = this.sysOrgCodeMappingBS.getCountByHQL(strCountHql,
					query);
			// 获得最大页数
			Integer maxPageNum = (int) (maxCount % sysPageInfo.getPageSize() == 0 ? maxCount
					/ sysPageInfo.getPageSize()
					: maxCount / sysPageInfo.getPageSize() + 1);
			// 当最大页数比当前页小时，说明当前页已经没有数据，将最大页数设置为当前页
			if (maxPageNum < sysPageInfo.getCurrentPage()) {
				pageNum = maxPageNum;
			}
			this.message = this.getSuccessMessage(
					"删除成功",
					null,
					"forward",
					"sys-org-code-mapping/sys-org-code-mapping!list.do?sysOrganization.id="
							+ this.getSysOrgCodeMapping().getId().getOrgId()
							+ "&pageNum=" + this.getPageNum() + "&numPerPage="
							+ this.getNumPerPage() + "&"
							+ Util.toStrQuery(query));
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("删除失败");
		}
		return "SUCCESS";
	}

	/**
	 * 删除多条数据
	 */
	public String deleteMore() {
		if (ids != null || !"".equals(ids)) {
			// 用于存储将要删除的对象的Id
			List<SysOrgCodeMappingId> listSysOrgCodeMapping = new ArrayList<SysOrgCodeMappingId>();
			// 根据页面传过来的信息，构造对象的id；
			if (!ids.contains(",")) {
				String[] id = ids.split(";");
				SysOrgCodeMappingId sysOrgCodeMappingId = new SysOrgCodeMappingId(
						id[0], id[1]);
				listSysOrgCodeMapping.add(sysOrgCodeMappingId);
			} else {
				String[] sysOrgCodeMappingIds = this.getIds().split(",");
				for (String strId : sysOrgCodeMappingIds) {
					String[] id = strId.split(";");
					SysOrgCodeMappingId sysOrgCodeMappingId = new SysOrgCodeMappingId(
							id[0], id[1]);
					listSysOrgCodeMapping.add(sysOrgCodeMappingId);
				}
			}
			try {
				// 删除以上获得的id相对应的对象
				sysOrgCodeMappingBS.deleteByIds(SysOrgCodeMapping.class,
						listSysOrgCodeMapping
								.toArray(new SysOrgCodeMappingId[0]));
				sysPageInfo = this.getSysPageInfo();
				String strCountHql = "select count(*) from SysOrgCodeMapping where sysOrganization.id = '"
						+ this.getSysOrganization().getId() + "'";
				// 获得数据的最大条数
				Long maxCount = this.sysOrgCodeMappingBS.getCountByHQL(
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
				this.message = this.getSuccessMessage("删除成功",
						null, "forward",
						"sys-org-code-mapping/sys-org-code-mapping!list.do?sysOrganization.id="
								+ this.getSysOrganization().getId()
								+ "&pageNum=" + this.getPageNum()
								+ "&numPerPage=" + this.getNumPerPage() + "&"
								+ Util.toStrQuery(query));
			} catch (Exception e) {
				e.printStackTrace();
				this.message = this.getFailMessage("删除失败!");
			}
		}
		return "SUCCESS";
	}

	/**
	 * 根据条件进行检索
	 */
	public String list() {
		// 设置query的值，如果从url中传值，则以strQuery的形式传，如果strQuery中有值，则将其解析为query
		if (strQuery != null && !"".equals(strQuery)) {
			query = Util.toMap(strQuery);
		}
		sysPageInfo = this.getSysPageInfo();
		String strCountHql = "select count(*) from SysOrgCodeMapping where sysOrganization.id = '"
				+ this.getSysOrganization().getId() + "'";
		sysPageInfo.setMaxCount(this.sysOrgCodeMappingBS.getCountByHQL(
				strCountHql, query));
		String hql = "from SysOrgCodeMapping where sysOrganization.id = '"
				+ this.getSysOrganization().getId() + "'";
		// 定义排序方式及排序列
		SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");
		listSysOrgCodeMapping = sysOrgCodeMappingBS.findPageByQuery(
				sysPageInfo, hql, query, sysOrderByInfo);
		strQuery = Util.toStrQuery(query);
		return "list";
	}

	public ISysOrgCodeMappingBS getSysOrgCodeMappingBS() {
		return sysOrgCodeMappingBS;
	}

	public void setSysOrgCodeMappingBS(ISysOrgCodeMappingBS sysOrgCodeMappingBS) {
		this.sysOrgCodeMappingBS = sysOrgCodeMappingBS;
	}

	public SysOrgCodeMapping getSysOrgCodeMapping() {
		if (sysOrgCodeMapping == null) {
			sysOrgCodeMapping = new SysOrgCodeMapping();
		}
		return sysOrgCodeMapping;
	}

	public void setSysOrgCodeMapping(SysOrgCodeMapping sysOrgCodeMapping) {
		this.sysOrgCodeMapping = sysOrgCodeMapping;
	}

	public ISysOrganizationBS getSysOrganizationBS() {
		return sysOrganizationBS;
	}

	public void setSysOrganizationBS(ISysOrganizationBS sysOrganizationBS) {
		this.sysOrganizationBS = sysOrganizationBS;
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

	public List<SysOrganization> getListSysOrganziation() {
		return listSysOrganziation;
	}

	public void setListSysOrganziatoin(List<SysOrganization> listSysOrganziation) {
		this.listSysOrganziation = listSysOrganziation;
	}

	public List<SysOrgCodeMapping> getListSysOrgCodeMapping() {
		return listSysOrgCodeMapping;
	}

	public void setListSysOrgCodeMapping(
			List<SysOrgCodeMapping> listSysOrgCodeMapping) {
		this.listSysOrgCodeMapping = listSysOrgCodeMapping;
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public void setListSysOrganziation(List<SysOrganization> listSysOrganziation) {
		this.listSysOrganziation = listSysOrganziation;
	}

	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	public SysOrgCodeMapping getNewSysOrgCodeMapping() {
		return newSysOrgCodeMapping;
	}

	public void setNewSysOrgCodeMapping(SysOrgCodeMapping newSysOrgCodeMapping) {
		this.newSysOrgCodeMapping = newSysOrgCodeMapping;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

}
