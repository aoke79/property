/**
 * Title: SysMenuAction
 * Description: The Action of table "SYS_MENU"
 */

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
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysSystem;
import com.sinoframe.business.ISysMenuBS;
import com.sinoframe.common.util.StringUtil;
import com.sinoframe.web.action.BaseAction;

@Results(
	{
		@Result(name="add",location="/system/sysMenu/sysMenuAdd.jsp"),
		@Result(name="edit",location="/system/sysMenu/sysMenuEdit.jsp"),
		//@Result(name="list",location="/system/sysMenu/sysMenuList.jsp"),
		@Result(name="listnew",location="/system/sysMenu/sysMenuListNew.jsp"), 
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name="METHOD",location="sys-menu!list",type="redirectAction")
	}
)
@ParentPackage("sinoframe-default")
public class SysMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//当前模块名称
	private static String moduleName = "SysMenu";
	//子系统集合
	private List<SysSystem> sysSystemList = new ArrayList<SysSystem>();
    private String sysMenuName;

	public String getSysMenuName() {
		return sysMenuName;
	}
	public void setSysMenuName(String sysMenuName) {
		this.sysMenuName = sysMenuName;
	}
	//菜单表的service接口
	private ISysMenuBS sysMenuBS; 
	//菜单表实体Bean
	private SysMenu sysMenu;
	//即时消息
	private Message message;
	//存放查询结果的List集
	private List<SysMenu> menuList = new ArrayList<SysMenu>();
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//存放ID
	private String ids;
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private Integer pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	//排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	// 存储树状结构的父节点的id
	private String parentId;
	// 存储列表页面树中需要关闭的节点
	private List<String> parentIds;
	// 存储当前被操作的菜单的id（即用来控制操作后哪个菜单项被选中）
	private String currentTreeNode;
	private List<SysSystem> systemList = new ArrayList<SysSystem>();
	private String info ="";
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	private String sysid;
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public List<SysSystem> getSystemList() {
		return systemList;
	}
	public void setSystemList(List<SysSystem> systemList) {
		this.systemList = systemList;
	}
	public ISysMenuBS getSysMenuBS() {
		return sysMenuBS;
	}
	public void setSysMenuBS(ISysMenuBS sysMenuBS) {
		this.sysMenuBS = sysMenuBS;
	}
	public SysMenu getSysMenu() {
		return sysMenu;
	}
	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public List<SysMenu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<String> getParentIds() {
		return parentIds;
	}
	public void setParentIds(List<String> parentIds) {
		this.parentIds = parentIds;
	}
	public String getCurrentTreeNode() {
		return currentTreeNode;
	}
	public void setCurrentTreeNode(String currentTreeNode) {
		this.currentTreeNode = currentTreeNode;
	}
	public List<SysSystem> getSysSystemList() {
		return sysSystemList;
	}
	public void setSysSystemList(List<SysSystem> sysSystemList) {
		this.sysSystemList = sysSystemList;
	}
	
	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String add(){
		try {//by lujie
			 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
			 ActionContext.getContext().put("userName",cmUser.getName().trim());
             this.sysSystemList = getSystemList(cmUser);
			//获取所有菜单项
			String hql = "from SysMenu where 1=1 ";
			if(!"超级管理员".equals(cmUser.getName().trim())){
				hql += " and subsystemId='"+cmUser.getSubsystemId()+"'";
			}else{
				hql += " and subsystemId is not null ";
			}
			menuList = sysMenuBS.findPageByQuery(hql);
			if(this.sysMenuName!=null && !"-1".equals(this.sysMenuName)){
				ActionContext.getContext().put("sysMenName",this.getSysMenuBS().findById(SysMenu.class,this.getSysMenuName()));
			}else{
			}
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 保存添加信息
	 * @return
	 */
	public String addSave() {
		try {
			//默认设置当前模块名称为navTab的名称
			if(!sysMenu.getUrl().equals("")){
				try{
					sysMenu.setTabInfo(StringUtil.caseModule(sysMenu.getUrl()));
				}catch(StringIndexOutOfBoundsException e){
					info = "菜单地址输入格式不正确！";
					System.out.println(info);
					e.printStackTrace();
				}
				
			}
			//保存菜单并设置currentTreeNode
			currentTreeNode = sysMenuBS.save(sysMenu).getId();
			this.getServletRequest().getSession().setAttribute("currentTreeNode", currentTreeNode);
			// 设定成功消息
			this.message = this.getSuccessMessage(getText("addSuccess"), "menuManage", "closeCurrent", "sys-menu/sys-menu!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail")+"  "+info);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 跳转至编辑页面
	 * @return
	 */
	public String edit() {
		try {//by lujie
			 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
             this.sysSystemList = getSystemList(cmUser);
			sysMenu = sysMenuBS.findById(SysMenu.class, sysMenu.getId());
			//记录parentId状态
			String hql = "from SysMenu where 1=1 ";
			if(sysMenu.getParentMenu() != null){
				parentId = sysMenu.getParentMenu().getId();
			}
			if(!"超级管理员".equals(cmUser.getName().trim())){
				hql += " and subsystemId='"+cmUser.getSubsystemId()+"'";
			}else{
				hql += " and subsystemId is not null ";
			}
			//获取所有菜单项
		
			menuList = sysMenuBS.findPageByQuery(hql);
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "edit";
	}
	
	/**
	 * 保存编辑信息
	 * @return
	 */
	public String editSave() {
		try {
			sysMenuBS.update(sysMenu,sysMenu.getId());
			//设置session中的菜单List
			List<SysMenu> sessionMenuList = new ArrayList<SysMenu>();
			CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
			sessionMenuList = sysMenuBS.getSysMenuByUserId(cmUser.getUserId());
			this.getServletRequest().getSession().setAttribute("sysMenuList",sessionMenuList);
			// 设定成功消息
			this.message = this.getSuccessMessage(getText("updateSuccess"), "menuManage", "closeCurrent", "sys-menu/sys-menu!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除一条记录
	 * @return
	 */
	public String delete() {
		try {
			//判断当前进行删除操作的菜单是否含有子菜单
			sysMenu = sysMenuBS.findById(SysMenu.class, sysMenu.getId());
			if(sysMenuBS.subMenu(sysMenu.getId()) == false){
				sysMenuBS.deleteById(SysMenu.class, sysMenu.getId());
				// 设定成功消息，需要传入parentId及currentTreeNode相关信息
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "", "forward",
						"sys-menu/sys-menu!list.do?parentId=" + sysMenu.getParentMenu().getId());
			} else {
				this.message = this.getFailMessage(getText("deleteFailSub"));
			}
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFailReason"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除多条记录
	 * @return
	 */
	public String multipleDelete() {
		try {
			//判断当前进行删除操作的菜单是否含有子菜单
			if(sysMenuBS.multipleMenu(this.getIds()) == false){
				sysMenuBS.deleteByIds(SysMenu.class, this.getIds());
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "", "forward", "sys-menu/sys-menu!list.do");
			} else {
				this.message = this.getFailMessage(getText("deleteFailSub"));
			}
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFailReason"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 树列表
	 * @return
	 */
	public String list(){
		try {//bu lujie
			//subsystemId 如果 不是 超级管理员的话 就按照当前子系统范围查询
			//查询
			String treeCondition = "from SysMenu where 1=1 ";
			 String subHql = " from SysSystem where 1=1 and state !='0' ";
			CmUser cmUser = (CmUser) ActionContext.getContext().getSession().get("user");
			if(!"超级管理员".equals(cmUser.getName().trim())){
				 treeCondition +=" and   subsystemId='"+cmUser.getSubsystemId()+"' order by orderColumn";
				 subHql += " and id='"+cmUser.getSubsystemId()+"'" ;
			     systemList = this.sysMenuBS.findPageByQuery(subHql);
		            menuList = sysMenuBS.findPageByQuery(treeCondition);
					//根据parentId关闭不需要的结点
					if(parentId != null){
						List<String> closableIds = new ArrayList<String>();
						parentIds = sysMenuBS.closableMenuNodes(menuList, closableIds, parentId);
					}
			}else{
				systemList = this.sysMenuBS.findPageByQuery(subHql);
				menuList = sysMenuBS.findPageByQuery(treeCondition);
			}
			   ActionContext.getContext().put("users",cmUser.getName());
		} catch (Exception e) {
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
	 
		//return "list";
		return "listnew";
	}
	 //判断当前登录的角色  超级管理员 可以选择子系统 
     public  List<SysSystem> getSystemList(CmUser cmUser){
    	      List<SysSystem> sys = null;
			 String hqlsys = "from SysSystem where 1=1 and state!='0' " ;
			 if("超级管理员".equals(cmUser.getName().trim())){
				 return  sys  = sysMenuBS.findPageByQuery(hqlsys);
			 }else{
				 if(!"null".equals(cmUser.getSubsystemId()) && !"".equals(cmUser.getSubsystemId())){
					 hqlsys += " and id='"+cmUser.getSubsystemId()+"'";
					 return sys = sysMenuBS.findPageByQuery(hqlsys);
				 }
			 }
    	  return null;
      }
	
}
