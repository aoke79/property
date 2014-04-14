package com.sinoframe.web.action.userInfo;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserPasswordHistory;
import com.sinoframe.business.ISysMenuBS;
import com.sinoframe.business.ICmUserPasswordHistoryBS;
import com.sinoframe.business.service.CmUserBS;
import com.sinoframe.business.service.CmUserPasswordHistoryBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.web.action.BaseAction;

@Controller
@Results({
	@Result(location="/system/userInfo/userPasswordHistoryList.jsp")
})
public class CmUserPasswordHistoryAction extends BaseAction {
	
	List<CmUserPasswordHistory> listTbUserPasswordHistory;
	
	//当前模块名称
	private static String moduleName = "TbUserPasswordHistoryAction";
	

	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	private HashMap<String, String> search=new HashMap<String, String>();
	
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	
	
	ICmUserPasswordHistoryBS cmUserPasswordHistoryBS;
	// 记录日志错误信息
	ISysMenuBS sysMenuBS;

	public static final String getModuleName() {
		return moduleName;
	}

	public static final void setModuleName(String moduleName) {
		CmUserPasswordHistoryAction.moduleName = moduleName;
	}

	public final void setSysMenuBS(ISysMenuBS sysMenuBS) {
		this.sysMenuBS = sysMenuBS;
	}

	//根据条件进行检索
	public String search(){
		if(ServletActionContext.getRequest().getParameter("name")!=null){
			query.put("like_cmUser.loginName", ServletActionContext.getRequest().getParameter("name"));
			search.put("name", ServletActionContext.getRequest().getParameter("name"));
			
		}
		//定义分页的SysPageInfo对象
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		//进行条件查询的语句
		String hql = "from CmUserPasswordHistory where 1=1 ";
		
		//定义排序方式及排序列
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");
		
		listTbUserPasswordHistory = cmUserPasswordHistoryBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		
		//进行条数查询的语句
		String counthql = "select count(*) from CmUserPasswordHistory where 1=1 ";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.cmUserPasswordHistoryBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设置日志信息
			cmUserPasswordHistoryBS.getErrorLog().info(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	public List<CmUserPasswordHistory> getListTbUserPasswordHistory() {
		return listTbUserPasswordHistory;
	}
	public void setListTbUserPasswordHistory(
			List<CmUserPasswordHistory> listTbUserPasswordHistory) {
		this.listTbUserPasswordHistory = listTbUserPasswordHistory;
	}

	public final void setCmUserPasswordHistoryBS(
			ICmUserPasswordHistoryBS cmUserPasswordHistoryBS) {
		this.cmUserPasswordHistoryBS = cmUserPasswordHistoryBS;
	}

	public final HashMap<String, String> getQuery() {
		return query;
	}

	public final void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	public final SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public final void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public final SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public final void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}

	public final int getPageNum() {
		return pageNum;
	}

	public final void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public final int getNumPerPage() {
		return numPerPage;
	}

	public final void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public final HashMap<String, String> getSearch() {
		return search;
	}

	public final void setSearch(HashMap<String, String> search) {
		this.search = search;
	}
	
	

}
