package com.sinoframe.web.action.sysLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysUserOperateInfo;
import com.sinoframe.business.ISysUserOperateInfoBS;
import com.sinoframe.web.action.BaseAction;

@Results ({
	
	@Result(name="list",location="/system/sysLog/operateLogList.jsp"),
	@Result(name="METHOD",location="operate-log!list",type="redirectAction")
})

@ParentPackage("sinoframe-default")
public class OperateLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//模块名称
	private static String moduleName = "SysLog";
	//实体Bean
	private SysUserOperateInfo sysUserOperateInfo;
	//Service
	private ISysUserOperateInfoBS sysUserOperateInfoBS;
	//List
	private List<SysUserOperateInfo> operateInfoList = new ArrayList<SysUserOperateInfo>();
	//即时消息
	private Message message;
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//存放ID
	private String ids;
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	
	public static String getModuleName() {
		return moduleName;
	}
	public static void setModuleName(String moduleName) {
		OperateLogAction.moduleName = moduleName;
	}

	public SysUserOperateInfo getSysUserOperateInfo() {
		return sysUserOperateInfo;
	}
	public void setSysUserOperateInfo(SysUserOperateInfo sysUserOperateInfo) {
		this.sysUserOperateInfo = sysUserOperateInfo;
	}

	public ISysUserOperateInfoBS getSysUserOperateInfoBS() {
		return sysUserOperateInfoBS;
	}
	public void setSysUserOperateInfoBS(ISysUserOperateInfoBS sysUserOperateInfoBS) {
		this.sysUserOperateInfoBS = sysUserOperateInfoBS;
	}

	public List<SysUserOperateInfo> getOperateInfoList() {
		return operateInfoList;
	}
	public void setOperateInfoList(List<SysUserOperateInfo> operateInfoList) {
		this.operateInfoList = operateInfoList;
	}

	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}
	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
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

	/**
	 * 列表
	 * @return
	 */
	public String list() {
		try {
			//查询列表中的条数信息
			String counthql = "select count(*) from SysUserOperateInfo where 1=1 ";
			//设置最大条数
			sysPageInfo.setMaxCount(this.sysUserOperateInfoBS.getCountByHQL(counthql, query));
			//设置每页显示条数及起始记录
			sysPageInfo.setPageSize(this.getNumPerPage());
			sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
			//设置当前页
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
			
			//列表
			String hql = "from SysUserOperateInfo where 1=1 ";
			operateInfoList = sysUserOperateInfoBS.findPageByQuery(sysPageInfo, hql, query, this.getSysOrderByInfo());
			
		} catch (Exception e) {
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysUserOperateInfoBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "list";
	}
	
}
