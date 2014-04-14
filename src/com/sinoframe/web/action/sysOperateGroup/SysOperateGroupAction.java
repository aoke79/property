/**
 * Title: SysSystemAction
 * Description: use the forward to deal with the code and the page
 */

package com.sinoframe.web.action.sysOperateGroup;
/**
 * SysOperateGroup Action
 *  
 *  @author HuXing
 *  @version V1.0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOperateGroup;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.ISysOperateGroupBS;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results(
	{
		@Result(name="add",location="/system/sysOperateGroup/sysOperateGroupAdd.jsp"),
		@Result(name="edit",location="/system/sysOperateGroup/sysOperateGroupEdit.jsp"),
		@Result(name="list",location="/system/sysOperateGroup/sysOperateGroupList.jsp"),
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name="METHOD",location="sys-operate-group!list",type="redirectAction")
	}
)

public class SysOperateGroupAction extends BaseAction {

private static final long serialVersionUID = 1L;
	
	//系统表的service接口
	private ISysOperateGroupBS sysOperateGroupBS;
	//系统表实体Bean
	private SysOperateGroup sysOperateGroup;
	//消息实体
	private Message message; 
	private List<SysOperate> sysOperateList = new ArrayList<SysOperate>();
	//存放查询结果的List集
	private List<SysOperateGroup> operateGroupList = new ArrayList<SysOperateGroup>();
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
	//排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	
	public ISysOperateGroupBS getSysOperateGroupBS() {
		return sysOperateGroupBS;
	}
	public void setSysOperateGroupBS(ISysOperateGroupBS sysOperateGroupBS) {
		this.sysOperateGroupBS = sysOperateGroupBS;
	}
	
	public SysOperateGroup getSysOperateGroup() {
		if(sysOperateGroup == null){
			sysOperateGroup = new SysOperateGroup();
		}
		return sysOperateGroup;
	}
	public void setSysOperateGroup(SysOperateGroup sysOperateGroup) {
		this.sysOperateGroup = sysOperateGroup;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public List<SysOperateGroup> getOperateGroupList() {
		return operateGroupList;
	}
	public void setOperateGroupList(List<SysOperateGroup> operateGroupList) {
		this.operateGroupList = operateGroupList;
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
	
	public SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}
	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}
	
	//跳转至列表页面
	public String list() {
		
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysOperateGroup ";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.sysOperateGroupBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
		}
		
		//列表
		String hql = "from SysOperateGroup ";
		
		try {
			operateGroupList = sysOperateGroupBS.findPageByQuery(sysPageInfo, hql);
		} catch (Exception e) {
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
			e.printStackTrace();
		}

		return "list";
	}
	
	
	//跳转至添加页面
	public String add() {
		return "add";
	}
	
	//保存添加信息
	public String addSave() {
		try {
			sysOperateGroupBS.save(sysOperateGroup);
			
			//设定成功消息
			this.message = this.getSuccessMessage("添加成功", "operateGroupManager", "closeCurrent", "sys-operate-group/sys-operate-group!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("添加失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//跳转至编辑页面
	public String edit() {
		this.sysOperateGroup = sysOperateGroupBS.findById(SysOperateGroup.class, sysOperateGroup.getId());
		return "edit";
	}
	
	//保存修改信息
	public String editSave() {
		try {
			sysOperateGroupBS.update(sysOperateGroup,sysOperateGroup.getId());
			
			//设定成功消息
			this.message = this.getSuccessMessage("修改成功", "operateGroupManager", "closeCurrent", "sys-operate-group/sys-operate-group!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("修改失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//删除一条记录
	public String delete() {
		try {
			sysOperateGroupBS.deleteById(SysOperateGroup.class, sysOperateGroup.getId());
			
			//设定成功消息
			this.message = this.getSuccessMessage("删除成功", "operateGroupManager", "forward", "sys-operate-group/sys-operate-group!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//删除多条记录
	public String multipleDelete() {
		try {
			sysOperateGroupBS.deleteByIds(SysOperateGroup.class, this.getIds());
			this.message = this.getSuccessMessage("删除成功", "operateGroupManager", "forward", "sys-operate-group/sys-operate-group!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//根据条件进行检索
	public String search(){
		
		//定义分页的SysPageInfo对象
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		//进行条件查询的语句
		String hql = "from SysOperateGroup where 1=1 ";
		
		//定义排序方式及排序列
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");
		
		operateGroupList = sysOperateGroupBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysOperateGroup where 1=1 ";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.sysOperateGroupBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysOperateGroupBS.getErrorLog().info(e.getMessage());
		}
		
		return "list";
	}
	public List<SysOperate> getSysOperateList() {
		return sysOperateList;
	}
	public void setSysOperateList(List<SysOperate> sysOperateList) {
		this.sysOperateList = sysOperateList;
	}
}
