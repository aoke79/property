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
 * <p><b>Description</b>:  用户常用菜单表的操作类</p>
 * <p><b>DATE</b>: 2011/04/25</p>
 * AUTHOR        : SinoSoft ruanruiwen
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.web.action.sysMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysMenu;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysUserCustomMenu;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysUserCustomMenuBS;
import com.sinoframe.web.action.BaseAction;

@Results(
	{
		@Result(name="add",location="/system/sysMenu/sysUserCustomMenuAdd.jsp"),
		@Result(name="edit",location="/system/sysMenu/sysUserCustomMenuEdit.jsp"),
		@Result(name="list",location="/system/sysMenu/sysUserCustomMenuList.jsp"),
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name="METHOD",location="sys-user-custom-menu!list",type="redirectAction")
	}
)
@ParentPackage("operateInterceptor")
public class SysUserCustomMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//当前模块名称
	private static String moduleName = "SysUserCustomMenu";
	
	//用户菜单的service接口
	private ISysUserCustomMenuBS sysUserCustomMenuBS;
	//用户菜单的实体Bean
	private SysUserCustomMenu sysUserCustomMenu;
	//即时消息实体
	private Message message;
	//存放查询结果的List集
	private List<SysUserCustomMenu> customList = new ArrayList<SysUserCustomMenu>();
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
	//存放菜单
	private List<SysMenu> sysMenuList=new ArrayList<SysMenu>();
	//排序字段
	private String orderBlock;
	//排序方式
	private int orderMethod = 0;
	
	public ISysUserCustomMenuBS getSysUserCustomMenuBS() {
		return sysUserCustomMenuBS;
	}
	public void setSysUserCustomMenuBS(ISysUserCustomMenuBS sysUserCustomMenuBS) {
		this.sysUserCustomMenuBS = sysUserCustomMenuBS;
	}
	
	public SysUserCustomMenu getSysUserCustomMenu() {
		return sysUserCustomMenu;
	}
	public void setSysUserCustomMenu(SysUserCustomMenu sysUserCustomMenu) {
		this.sysUserCustomMenu = sysUserCustomMenu;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public List<SysUserCustomMenu> getCustomList() {
		return customList;
	}
	public void setCustomList(List<SysUserCustomMenu> customList) {
		this.customList = customList;
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
	
	public List<SysMenu> getSysMenuList() {
		return sysMenuList;
	}
	public void setSysMenuList(List<SysMenu> sysMenuList) {
		this.sysMenuList = sysMenuList;
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
	
	/**
	 * 跳转至列表页面
	 * @return "list"
	 */
	public String list() {
		try {
			//从session中获取用户信息
			CmUser user = (CmUser)ActionContext.getContext().getSession().get("user");
			
			//查询列表中的条数信息
			String counthql = "select count(*) from SysUserCustomMenu where 1=1 and cmUser.userId = '" + user.getUserId() + "'";
			//设置最大条数
			sysPageInfo.setMaxCount(this.sysUserCustomMenuBS.getCountByHQL(counthql, query));
			//设置当前页
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
			//设置每页显示条数及起始记录
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
			
			//列表
			String hql = "from SysUserCustomMenu where 1=1 and cmUser.userId = '" + user.getUserId() + "'";
			customList = sysUserCustomMenuBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysUserCustomMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			
		}
		return "list";
	}
	
	/**
	 * 跳转至添加页面
	 * @return "add"
	 */
	public String add() {
		//从session中获取菜单
		List<SysMenu> list= (List<SysMenu>)ActionContext.getContext().getSession().get("sysMenuList");
		//从session中获取用户信息
		CmUser tUser = (CmUser) ActionContext.getContext().getSession().get("user");
		//查询当前用户下所有的菜单
		List<SysMenu> listAll = sysUserCustomMenuBS.findPageByQuery("select t.sysMenu from SysUserCustomMenu t where t.cmUser.userId='"+tUser.getUserId()+"'");
		for(SysMenu s:list){
			if(!"-1".equals(s.getParentMenu().getId())){
				if(!listAll.contains(s)){
					sysMenuList.add(s);
				}
			}
		}
		return "add";
	}
	
	/**
	 * 保存添加信息页面
	 * @return "SUCCESS"
	 */
	public String addSave() {
		try {
			//从session中获取user
			CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
			//查询当前用户下的所有已存在的菜单项
			List<String> menuItem = sysUserCustomMenuBS.getAllMenuByUser(cmUser);
			//当前菜单项不是已经添加过的菜单时执行添加功能
			if(!menuItem.contains(sysUserCustomMenu.getSysMenu().getId())){
				sysUserCustomMenuBS.save(sysUserCustomMenu);
				//设定成功消息
				this.message = this.getSuccessMessage(getText("addSuccess"), "customMenu", "closeCurrent", "sys-menu/sys-user-custom-menu!list.do");
			} else {
				this.message = this.getFailMessage(getText("addFailRepeat"));
			}
		} catch (Exception e) {
			//设置日志信息
			sysUserCustomMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 跳转至编辑页面
	 * @return "list"
	 */
	public String edit() {
		sysUserCustomMenu = sysUserCustomMenuBS.findById(SysUserCustomMenu.class, sysUserCustomMenu.getId());
		return "edit";
	}
	
	/**
	 * 保存编辑信息
	 * @return "SUCCESS"
	 */
	public String editSave() {
		try {
			//从session中获取user
			CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
			//查询当前用户下的所有已存在的菜单项
			List<String> menuItem = sysUserCustomMenuBS.getAllMenuByUser(cmUser);
			//当前菜单项不是已经添加过的菜单时执行添加功能
			if(!menuItem.contains(sysUserCustomMenu.getSysMenu().getId())){
				sysUserCustomMenuBS.update(sysUserCustomMenu,sysUserCustomMenu.getId());
				//设定成功消息
				this.message = this.getSuccessMessage(getText("updateSuccess"), "customMenu", "closeCurrent", "sys-menu/sys-user-custom-menu!list.do");
			} else {
				this.message = this.getFailMessage(getText("updateFailRepeatMenu"));
			}
		} catch (Exception e) {
			//设置日志信息
			sysUserCustomMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除一条数据
	 * @return "SUCCESS"
	 */
	public String delete() {
		try {
			sysUserCustomMenuBS.deleteById(SysUserCustomMenu.class, sysUserCustomMenu.getId());
			//设定成功消息
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "customMenu", "forward", "sys-menu/sys-user-custom-menu!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysUserCustomMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除多条数据
	 * @return
	 */
	public String multipleDelete() {
		try {
			sysUserCustomMenuBS.deleteByIds(SysUserCustomMenu.class, this.getIds());
			//设定成功消息
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "customMenu", "forward", "sys-menu/sys-user-custom-menu!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysUserCustomMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
}
