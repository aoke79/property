package com.sinoframe.web.action.secSerialnumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SecSerialnumber;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.ISecSerialnumberBS;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results(
	{
		@Result(name="add",location="/system/secSerialnumber/secSerialnumberAdd.jsp"),
		@Result(name="edit",location="/system/secSerialnumber/secSerialnumberEdit.jsp"),
		@Result(name="list",location="/system/secSerialnumber/secSerialnumberList.jsp"),
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name="METHOD",location="sec-serialnumber!list",type="redirectAction")
	}
)
public class SecSerialnumberAction extends BaseAction {
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	//排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//消息实体
	private Message message;
	//实体
	private SecSerialnumber secSerialnumber;
	//Service 接口
	private ISecSerialnumberBS secSerialnumberBS;
	private List<SecSerialnumber> secSerialnumberList = new ArrayList<SecSerialnumber>();
	//存放ID
	private String ids;
	//排序字段
	private String orderBlock;
	//排序方式
	private int orderMethod = 0;
	//跳转至列表页面
	public String list() throws Exception{

		//定义分页的SysPageInfo对象
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		//设置排序方式，0为正常排序，1为反相排序
		if(orderMethod == 0){
			sysOrderByInfo.setOrderAsc("asc");
			orderMethod = 1;
		} else {
			sysOrderByInfo.setOrderAsc("desc");
			orderMethod = 0;
		}
		//获取排序字段
		sysOrderByInfo.setOrderColumn(orderBlock);
		//System.out.println("--orderBlock--"+orderBlock);
		//System.out.println("--orderMethod--"+orderMethod);
		String counthql = "select count(*) from SecSerialnumber where 1=1 ";
		
		sysPageInfo.setMaxCount(this.secSerialnumberBS.getCountByHQL(counthql,query));
		try{
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			secSerialnumberBS.getErrorLog().info(e.getMessage());
			e.printStackTrace();
		}
		String hql = "from SecSerialnumber where 1=1 " ;
		try{
			secSerialnumberList = secSerialnumberBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);	
			//System.out.println("==========" + secSerialnumberList.size());
		}catch (Exception e) {
			secSerialnumberBS.getErrorLog().info(e.getMessage());
			e.printStackTrace();
		}
		
		return "list";
	}
	//跳转至添加页面
	public String add() {
		this.secSerialnumberList = secSerialnumberBS.findPageByQuery("from SecSerialnumber");
		return "add";
	}
	
	//保存添加信息
	public String addSave() {
		try {
//			System.out.print(sysOperate.getIfFinallyMenu());
			secSerialnumberBS.save(secSerialnumber);
			  
			//设定成功消息
			this.message = this.getSuccessMessage("添加成功", "secSerialnumberManage", "closeCurrent", "sec-serialnumber/sec-serialnumber!list.do");
		} catch (Exception e) {
			//设置日志信息
			secSerialnumberBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("添加失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//跳转至编辑页面
	public String edit() {
		this.secSerialnumber = secSerialnumberBS.findById(SecSerialnumber.class, secSerialnumber.getSnid());
		this.secSerialnumberList = secSerialnumberBS.findPageByQuery("from SecSerialnumber");
		return "edit";
	}
	//保存修改信息
	public String editSave() {
		try {
			secSerialnumberBS.update(secSerialnumber,secSerialnumber.getSnid());
			
			//设定成功消息
			this.message = this.getSuccessMessage("修改成功", "secSerialnumberManage", "closeCurrent", "sec-serialnumber/sec-serialnumber!list.do");
		} catch (Exception e) {
			//设置日志信息
			secSerialnumberBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("修改失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//删除一条记录
	public String delete() {
		try {
			secSerialnumberBS.deleteById(SecSerialnumber.class, secSerialnumber.getSnid());
			
			//设定成功消息
			this.message = this.getSuccessMessage("删除成功", "secSerialnumberManage", "forward", "sec-serialnumber/sec-serialnumber!list.do");
		} catch (Exception e) {
			//设置日志信息
			secSerialnumberBS.getErrorLog().info(e.getMessage());
			
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	public String multipleDelete() {
		try {
			secSerialnumberBS.deleteByIds(SecSerialnumber.class, this.getIds());
			this.message = this.getSuccessMessage("删除成功", "secSerialnumberManage", "forward", "sec-serialnumber/sec-serialnumber!list.do");
		} catch (Exception e) {
			//设置日志信息
			secSerialnumberBS.getErrorLog().info(e.getMessage());
			
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
		
		String hql = "from SecSerialnumber where 1=1 ";
		//定义排序方式及排序列
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");
		
		secSerialnumberList = secSerialnumberBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		
		//进行条数查询的语句
		String counthql = "select count(*) from SecSerialnumber where 1=1 ";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.secSerialnumberBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			secSerialnumberBS.getErrorLog().info(e.getMessage());
		}
		
		return "list";
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
	public SecSerialnumber getSecSerialnumber() {
		return secSerialnumber;
	}
	public void setSecSerialnumber(SecSerialnumber secSerialnumber) {
		this.secSerialnumber = secSerialnumber;
	}
	public ISecSerialnumberBS getSecSerialnumberBS() {
		return secSerialnumberBS;
	}
	public void setSecSerialnumberBS(ISecSerialnumberBS secSerialnumberBS) {
		this.secSerialnumberBS = secSerialnumberBS;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<SecSerialnumber> getSecSerialnumberList() {
		return secSerialnumberList;
	}
	public void setSecSerialnumberList(List<SecSerialnumber> secSerialnumberList) {
		this.secSerialnumberList = secSerialnumberList;
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
}
