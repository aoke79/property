/**
 * Title: SysSystemAction
 * Description: use the forward to deal with the code and the page
 */

package com.sinoframe.web.action.sysSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysMenu;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysSystem;
import com.sinoframe.business.ISysSystemBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;

@Results(
	{
		@Result(name="add",location="/system/sysSystem/systemAdd.jsp"),
		@Result(name="edit",location="/system/sysSystem/systemEdit.jsp"),
		@Result(name="list",location="/system/sysSystem/systemList.jsp"),
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name="METHOD",location="sys-system!list",type="redirectAction")
	}
)
@ParentPackage("sinoframe-default")
public class SysSystemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//当前模块名称
	private static String moduleName = "SysSystem";
	
	//系统表的service接口
	private ISysSystemBS sysSystemBS;
	//系统表实体Bean
	private SysSystem sysSystem;
	//消息实体
	private Message message;
	//存放查询结果的List集
	private List<SysSystem> systemList = new ArrayList<SysSystem>();
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//存放ID
	private String ids;
	// 用于存储查询条件的字符串形式
	private String strQuery;
	
	public ISysSystemBS getSysSystemBS() {
		return sysSystemBS;
	}
	public void setSysSystemBS(ISysSystemBS sysSystemBS) {
		this.sysSystemBS = sysSystemBS;
	}
	
	public SysSystem getSysSystem() {
		if(sysSystem == null){
			sysSystem = new SysSystem();
		}
		return sysSystem;
	}
	public void setSysSystem(SysSystem sysSystem) {
		this.sysSystem = sysSystem;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public List<SysSystem> getSystemList() {
		return systemList;
	}
	public void setSystemList(List<SysSystem> systemList) {
		this.systemList = systemList;
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
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	
	/**
	 * 跳转至列表页面
	 * @return
	 */
	public String list() {
		try {
			if (strQuery != null && !"".equals(strQuery)) {
				query = Util.toMap(strQuery);
			}
			//查询列表中的条数信息
			String countHql = "select count(*) from SysSystem where 1=1 ";
			//设置最大条数
			this.getSysPageInfo().setMaxCount(this.sysSystemBS.getCountByHQL(countHql, Util.decodeQuery(query)));
			
			//列表
			String listHql = "from SysSystem where 1=1 ";
			
			if(this.getSysOrderByInfo().getOrderAsc().equals("")
					&& this.getSysOrderByInfo().getOrderColumn() == null){
				this.getSysOrderByInfo().setIfDate(false);
				this.getSysOrderByInfo().setOrderAsc("asc");
				this.getSysOrderByInfo().setOrderColumn("name");
			}
			
			systemList = sysSystemBS.findPageByQuery(this.getSysPageInfo(), listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
			strQuery = Util.toStrQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
			this.getSysPageInfo().setCurrentPage(1);
			//设置日志信息
			sysSystemBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
		}

		return "list";
	}
	
	
	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String add() {
		return "add";
	}
	
	/**
	 * 保存添加信息
	 * @return
	 */
	public String addSave() {
		try {
			sysSystem.setState("1");
			sysSystemBS.save(sysSystem);
			//设定成功消息
			this.message = this.getSuccessMessage(getText("addSuccess"), "systemManage",
								"closeCurrent", "sys-system/sys-system!list.do&" + Util.toStrQuery(query));
		} catch (Exception e) {
			//设置日志信息
			sysSystemBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 跳转至编辑页面
	 * @return
	 */
	public String edit() {
		this.setSysOrderByInfo(this.getSysOrderByInfo());
		this.sysSystem = sysSystemBS.findById(SysSystem.class, sysSystem.getId());
		
		strQuery = Util.toStrQuery(query);
		return "edit";
	}
	
	/**
	 * 保存修改信息
	 * @return
	 */
	public String editSave() {
		try {
			sysSystemBS.update(sysSystem,sysSystem.getId());
			
			//设定成功消息
			this.message = this.getSuccessMessage(getText("updateSuccess"), "systemManage", "closeCurrent", "sys-system/sys-system!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysSystemBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
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
			
			String ment = " from SysMenu where subsystemId like '%"+sysSystem.getId()+"%'";
			List<SysMenu> sysMenuList = this.sysSystemBS.findPageByQuery(ment);
			if(sysMenuList != null && sysMenuList.size() !=0){
				this.message = this.getFailMessage("该子系统已经被菜单引用到，无法删除！");
				return "SUCCESS";
			}	
		
			String sysOperate = " from SysOperate where subsystemId like '%"+sysSystem.getId()+"%'";
			List<SysOperate> sysOperateList = this.sysSystemBS.findPageByQuery(sysOperate);
			if(sysOperateList != null && sysOperateList.size() !=0){
				this.message = this.getFailMessage("该子系统已经被操作引用到，无法删除！");
				return "SUCCESS";
			}	
			
			String cmUser = " from CmUser where subsystemId like '%"+sysSystem.getId()+"%'";
			List<CmUser> cmUserList = this.sysSystemBS.findPageByQuery(cmUser);
			if(cmUserList != null && cmUserList.size() !=0){
				this.message = this.getFailMessage("该子系统已经被用户引用到，无法删除！");
				return "SUCCESS";
			}	
			
			
			String sysRole = " from SysRole where subsystemId like '%"+sysSystem.getId()+"%'";
			List<SysRole> sysRoleList = this.sysSystemBS.findPageByQuery(sysRole);
			if(sysRoleList != null && sysRoleList.size() !=0){
				this.message = this.getFailMessage("该子系统已经被角色引用到，无法删除！");
				return "SUCCESS";
			}	
			
			
			sysSystemBS.deleteById(SysSystem.class, sysSystem.getId());
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "sys-system/sys-system!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "sys-system/sys-system!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			//设置日志信息
			sysSystemBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
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
			sysSystemBS.deleteByIds(SysSystem.class, this.getIds());
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "sys-system/sys-system!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "sys-system/sys-system!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			//设置日志信息
			sysSystemBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
}
