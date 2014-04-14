
package com.sinoframe.web.action.sysOperate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork.Action;
import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysMenu;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysSystem;
import com.sinoframe.business.ISysOperateBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
/**
 * SysOperateAction
 * @描述 操作的 Action 类
 * @作者 胡星
 * @版本 1.0
 */
@ParentPackage("sinoframe-default")
@Results(
	{
		@Result(name="add",location="/system/sysOperate/sysOperateAdd.jsp"),
		@Result(name="menutSystemTreeList",location="/system/sysOperate/menutSystemTreeList.jsp"),
		@Result(name="menutSystemTreeListAdd",location="/system/sysOperate/menutSystemTreeListAdd.jsp"),
		@Result(name="palSystemTreeList",location="/system/sysOperate/palSystemTreeList.jsp"),
		@Result(name="edit",location="/system/sysOperate/sysOperateEdit.jsp"),
		@Result(name="list",location="/system/sysOperate/sysOperateList.jsp"),
		@Result(name="roleview",location="/system/sysOperate/viewRoleList.jsp"),
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name="METHOD",location="sys-operate!list",type="redirectAction")
	}
)

public class SysOperateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static String moduleName = "SysOperate";
	
	/** 操作业务接口 */
	private ISysOperateBS sysOperateBS;
	/** 操作 */
	private SysOperate sysOperate;
	/** 消息实体 */
	private Message message;
	/** 查询的 List 集合 */
	private List<SysOperate> operateList = new ArrayList<SysOperate>();
	/** 菜单 */
	private List<SysMenu> menuList = new ArrayList<SysMenu>();
	/** 查询对象的 HashMap 集 */
	private HashMap<String, String> query  = new HashMap<String, String>();
	/** 操作 ID 串 */
	private String ids;
	/** 查询条件的字符串 */ 
	private String strQuery;
	//角色操作关系List
	private List<SysRelationRoleOperate> relationList = new ArrayList<SysRelationRoleOperate>();
	
	private String names;
	
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	private SysMenu sysMenu;
	
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	
	
	
	/**
	 * 跳转至列表页面
	 */
	public String list() {
		try{
		if (strQuery != null && !"".equals(strQuery)) {
			query = Util.toMap(strQuery);
		}
		if(query.get("query.like_subsystemId")!=null && query.get("query.like_subsystemId").length()!=0){
			query.put("like_subsystemId", query.get("query.like_subsystemId"));
		}
		 ActionContext.getContext().put("names",this.names);
		 String hqls = "from SysSystem where state !='0'";
		 this.sysSystemList = this.sysOperateBS.findPageByQuery(hqls);
		 
			if(this.names!=null && this.names.length()!=0){
				String hqlsd = "from SysMenu where name like '%"+names+"%'";
				List<SysMenu> sysList = this.sysOperateBS.findPageByQuery(hqlsd);
				StringBuffer buffer = new StringBuffer();
				for(SysMenu s : sysList){
					buffer.append(s.getId()).append(",");
				}
				if(buffer.length()!=0){
					buffer.deleteCharAt(buffer.length()-1);
					query.put("in_sysMenu.id",buffer.toString());
				}else{
					query.put("in_sysMenu.id","xaxx3x");
				}
				
			}
		
		  CmUser cmUser = (CmUser) ActionContext.getContext().getSession().get("user");	
		  ActionContext.getContext().put("userName",cmUser.getName());
		//查询列表中的条数信息
		String counthql = "select count(*) from SysOperate where 1=1 ";
		
		//列表
		String hql = "from SysOperate where 1=1 " ;
		
        if(!"超级管理员".equals(cmUser.getName().trim())){
        	hql += " and subsystemId like '%"+cmUser.getSubsystemId()+"%'";
        	counthql += " and subsystemId like '%"+cmUser.getSubsystemId()+"%'";
        	
        }
      //设置最大条数
		this.getSysPageInfo().setMaxCount(this.sysOperateBS.getCountByHQL(counthql,Util.decodeQuery(query)));
		

			operateList = sysOperateBS.findPageByQuery(this.getSysPageInfo(), hql, Util.decodeQuery(query), this.getSysOrderByInfo());	
			
			
			strQuery = Util.toStrQuery(query);
		}catch (Exception e) {
			this.getSysPageInfo().setCurrentPage(1);
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "list";
	}
	
      	public String menutSystemTreeList(){
      		try {
				CmUser cmUser = (CmUser) ActionContext.getContext()
						.getSession().get("user");
				String treeCondition = "from SysMenu where 1=1 ";
				String subHql = " from SysSystem where 1=1 and state !='0' ";
				if (!"超级管理员".equals(cmUser.getName().trim())) {
					treeCondition += " and   subsystemId='"
							+ cmUser.getSubsystemId()
							+ "' order by orderColumn";
					subHql += " and id='" + cmUser.getSubsystemId() + "'";
					systemList = this.sysOperateBS.findPageByQuery(subHql);
					menuList = sysOperateBS.findPageByQuery(treeCondition);

				} else {
					systemList = this.sysOperateBS.findPageByQuery(subHql);
					menuList = sysOperateBS.findPageByQuery(treeCondition);
				}
				ActionContext.getContext().put("users", cmUser.getName());
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "menutSystemTreeList";
      	}
      	public String palSystemTreeList(){
      		try {
				CmUser cmUser = (CmUser) ActionContext.getContext()
						.getSession().get("user");
				String treeCondition = "from SysOperate where 1=1 ";
				String subHql = " from SysSystem where 1=1 and state !='0' ";
				if (!"超级管理员".equals(cmUser.getName().trim())) {
					treeCondition += " and   subsystemId='"
							+ cmUser.getSubsystemId()
							+ "'";
					subHql += " and id='" + cmUser.getSubsystemId() + "'";
					systemList = this.sysOperateBS.findPageByQuery(subHql);
					operateList = sysOperateBS.findPageByQuery(treeCondition);

				} else {
					systemList = this.sysOperateBS.findPageByQuery(subHql);
					operateList = sysOperateBS.findPageByQuery(treeCondition);
				}
				ActionContext.getContext().put("users", cmUser.getName());
			} catch (Exception e) {
				// TODO: handle exception
			}
      		
      		
      		return "palSystemTreeList";
      	}
      	public String menutSystemTreeListAdd(){
      		try {
				CmUser cmUser = (CmUser) ActionContext.getContext()
						.getSession().get("user");
				String treeCondition = "from SysMenu where 1=1 ";
				String subHql = " from SysSystem where 1=1 and state !='0' ";
				if (!"超级管理员".equals(cmUser.getName().trim())) {
					treeCondition += " and   subsystemId='"
							+ cmUser.getSubsystemId()
							+ "' order by orderColumn";
					subHql += " and id='" + cmUser.getSubsystemId() + "'";
					systemList = this.sysOperateBS.findPageByQuery(subHql);
					menuList = sysOperateBS.findPageByQuery(treeCondition);

				} else {
					systemList = this.sysOperateBS.findPageByQuery(subHql);
					menuList = sysOperateBS.findPageByQuery(treeCondition);
				}
				ActionContext.getContext().put("users", cmUser.getName());
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "menutSystemTreeListAdd";
      	}
	/**
	 * 跳转至添加页面
	 **/
	public String add() {
		try {
 			 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            // this.sysSystemList = getSystemList(cmUser);
    		 String hqls = "from SysSystem where 1=1 ";
    		 this.sysSystemList = getSystemList(cmUser);
    		 //this.sysSystemList = this.sysOperateBS.findPageByQuery(hqls);
            // tableInfo = " <table border='0'>";
            String se = "";
            if(!cmUser.getName().equals("超级管理员")){
            	//se = " disabled='disabled' ";
            	se += " checked='checked'  ";
                for(SysSystem s : sysSystemList){
                	if(cmUser.getSubsystemId().equals(s.getId())){
                   	   tableInfo += "<tr>";
                  	   tableInfo += "<td> "+ "<input type='checkbox' "+se+"name='sid' value='"+s.getId()+"'>"+"</td>";
                  	   tableInfo += "<td> "+s.getName()+"</td>";
                  	   tableInfo += "</tr>";
                	}
              }
            }else{
                for(SysSystem s : sysSystemList){
             	   tableInfo += "<tr>";
             	   tableInfo += "<td> "+ "<input type='checkbox' "+se+"name='sid' value='"+s.getId()+"'>"+"</td>";
             	   tableInfo += "<td> "+s.getName()+"</td>";
             	   tableInfo += "</tr>";
                  
             }
            }
            tableInfo +="</table>" ;
             
			//菜单
			//this.menuList = sysOperateBS.findPageByQuery("from SysMenu where 1=1  order by nlssort(name,'NLS_SORT=SCHINESE_PINYIN_M') ");
			//操作集合
			//this.operateList = sysOperateBS.findPageByQuery("from SysOperate where 1=1 order by nlssort(operateName,'NLS_SORT=SCHINESE_PINYIN_M')");
		} catch (Exception e) {
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 保存添加信息，跳转至列表页面
	 **/
	public String addSave() {
		try {
			//sysOperate.setSubsystemId(this.sid);
			if(sysOperate.getSysMenu().getId() == null || "".equals(sysOperate.getSysMenu().getId().trim())){
				this.message = this.getFailMessage("添加失败 !菜单选择不能选择子系统标识 " );
				return "SUCCESS";
			}
			
			//保存
			sysOperateBS.save(sysOperate);
			//更新Application属性范围里的操作列表
			updateApplicationInitBaseOperate();
			//设定成功消息
			this.message = this.getSuccessMessage("添加成功", "operateManage", "closeCurrent", "sys-operate/sys-operate!list.do" + Util.toStrQuery(query));
		} catch (Exception e) {
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("添加失败 !  " +info);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 跳转至编辑页面
	 **/
	public String edit() {
		try{
 			 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
             this.sysSystemList = getSystemList(cmUser);
			this.setSysOrderByInfo(this.getSysOrderByInfo());
			//菜单
			this.menuList = sysOperateBS.findPageByQuery("from SysMenu where 1=1  order by nlssort(name,'NLS_SORT=SCHINESE_PINYIN_M') ");
			//当前修改的操作
			this.sysOperate = sysOperateBS.findById(SysOperate.class, sysOperate.getId());
			//操作集合
			this.operateList = sysOperateBS.findPageByQuery("from SysOperate where 1=1 order by nlssort(operateName,'NLS_SORT=SCHINESE_PINYIN_M')");
			strQuery = Util.toStrQuery(query);
			
			String[] str = new String[]{};
			if(sysOperate.getSubsystemId() != null && !"null".equals(sysOperate.getSubsystemId())){
				if(sysOperate.getSubsystemId().length()  != 0 ){
					str= sysOperate.getSubsystemId().split(",");
				}
			}
	         tableInfo = " <table border='0'>";
		        String se = "";
		        for(SysSystem s : sysSystemList){
		        	for(int i = 0 ;i<str.length;i++){
		        		if(str[i].trim().equals(s.getId())){
		        			if(!cmUser.getName().equals("超级管理员")){
		        				//se = " disabled='disabled' ";
		        			}
		        			se += " checked='checked'  ";
		        		}
		        	}
		        	   tableInfo += "<tr>";
		        	   tableInfo += "<td> "+ "<input type='checkbox' "+se+"name='sid' value='"+s.getId()+"'>"+"</td>";
		        	   tableInfo += "<td> "+s.getName()+"</td>";
		        	   tableInfo += "</tr>";
		                   se = "";
		        }
		         tableInfo +="</table>" ;
			
		}catch (Exception e) {
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "edit";
	}
	
	/**
	 * 保存修改信息，跳转至列表页面
	 **/
	public String editSave() {
		try {
/*			if(this.sid ==null || this.sid.length()==0){
				sysOperate.setSubsystemId("");
			}else{
				sysOperate.setSubsystemId(this.sid);
			}*/
			
			//更新操作
			sysOperateBS.update(sysOperate,sysOperate.getId());
			
			//更新Application属性范围里的操作列表
			updateApplicationInitBaseOperate();
			
			//设定成功消息
			this.message = this.getSuccessMessage("修改成功", "operateManage", "closeCurrent", "sys-operate/sys-operate!list.do");
		} catch (Exception e) {
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("修改失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	
	/**
	 * 删除一条记录
	 **/
	public String delete() {
		try {
			//检索有没有被关联  BY LUJIE
			String sl = "from SysRelationRoleOperate where sysOperate.id='"+sysOperate.getId()+"'";
			List<SysRelationRoleOperate> sc = this.sysOperateBS.findPageByQuery(sl);
			if(sc != null && sc.size() !=0){
				this.message = this.getFailMessage("该操作已经被角色引用到了，无法删除!");
				return "SUCCESS";
			}
			
			//删除一条记录
			sysOperateBS.deleteById(SysOperate.class, sysOperate.getId());
			
			//更新Application属性范围里的操作列表
			updateApplicationInitBaseOperate();
			
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "sys-operate/sys-operate!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "sys-operate/sys-operate!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("该操作权限在其他功能中已有应用,请核对后,再做删除!");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除多条记录
	 **/
	public String multipleDelete() {
		try {
			//检索有没有被关联 by LU SIR
			
			if(this.getIds() != null && this.getIds().length() !=0){
				String str = "";
				for(int i=0;i<this.getIds().split(",").length;i++){
					str += "'"+this.getIds().split(",")[i].trim()+"',";
				}
				str = str.substring(0,str.length()-1);
		
				String sl = "from SysRelationRoleOperate where sysOperate.id in ("+str+")";
				List<SysRelationRoleOperate> sc = this.sysOperateBS.findPageByQuery(sl);
				if(sc != null && sc.size() !=0){
					String c ="";
					for(SysRelationRoleOperate s : sc){
						if(c.indexOf(s.getSysOperate().getOperateName()) == -1){
							c +=s.getSysOperate().getOperateName()+",";
						}
					}
					this.message = this.getFailMessage(c+"已经被引用，无法删除!");
					return "SUCCESS";
				}
			}
			
			
			//删除选中的多条记录
			sysOperateBS.deleteByIds(SysOperate.class, this.getIds());
			
			//更新Application属性范围里的操作列表
			updateApplicationInitBaseOperate();
			
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "sys-operate/sys-operate!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "sys-operate/sys-operate!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			//设置日志信息
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 从操作查看角色
	 */
	public String viewRole() {
		try {
			//最大条数
			this.getSysPageInfo().setMaxCount(sysOperateBS.rolesListCount(sysOperate.getId(),
					Util.decodeQuery(query)));
			//角色列表
			relationList = sysOperateBS.rolesListByOperate(sysOperate.getId(), this.getSysPageInfo(), this.getSysOrderByInfo(), Util.decodeQuery(query));
		} catch (Exception e) {
			sysOperateBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "roleview";
	}
	
	//更新Application属性范围里的操作列表
	public void updateApplicationInitBaseOperate(){
		try {
			// 先得到根操作
			SysOperate initBaseOperate = sysOperateBS.findById(
					SysOperate.class, "-1");
			// 因为通过SysOperate.getSubOperate()获得其子操作（subOperate）是延迟加载，
			// 所以先递归（recurse）遍历所有操作（加载所有操作），然后再放入 application 属性范围里
			
			recurseOper(initBaseOperate);
			
			// 加载完后，放入 application 里
			this.getServletContext().setAttribute("initBaseOperate",
					initBaseOperate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 递归（recurse）遍历所有操作一次 */
	public void recurseOper(SysOperate tempSysOperate) {
		try {
			// System.out.print(tempSysOperate.getOperateName());
			// 如果当前的操作有子操作，继续向下递归（recurse）
			if (tempSysOperate.getSubOperate().size() > 0) {
				for (SysOperate subOperate : tempSysOperate.getSubOperate())
					// 递归遍历（recurse）操作
					recurseOper(subOperate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<SysMenu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}
	
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public ISysOperateBS getSysOperateBS() {
		return sysOperateBS;
	}
	public void setSysOperateBS(ISysOperateBS sysOperateBS) {
		this.sysOperateBS = sysOperateBS;
	}
	
	public SysOperate getSysOperate() {
		return sysOperate;
	}
	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public List<SysOperate> getOperateList() {
		return operateList;
	}
	public void setOperateList(List<SysOperate> operateList) {
		this.operateList = operateList;
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

	public List<SysRelationRoleOperate> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<SysRelationRoleOperate> relationList) {
		this.relationList = relationList;
	}
  	//子系统集合
	private List<SysSystem> sysSystemList = new ArrayList<SysSystem>();

	public List<SysSystem> getSysSystemList() {
		return sysSystemList;
	}
	public void setSysSystemList(List<SysSystem> sysSystemList) {
		this.sysSystemList = sysSystemList;
	}
    public  List<SysSystem> getSystemList(CmUser cmUser){
	      List<SysSystem> sys = null;
		 String hqlsys = "from SysSystem where 1=1 and state!='0' " ;
		 if("超级管理员".equals(cmUser.getName().trim())){
			 return  sys  = this.sysOperateBS.findPageByQuery(hqlsys);
		 }else{
			 if(!"null".equals(cmUser.getSubsystemId()) && !"".equals(cmUser.getSubsystemId())){
				 hqlsys += " and id='"+cmUser.getSubsystemId()+"'";
				 return sys = sysOperateBS.findPageByQuery(hqlsys);
			 }
		 }
	  return null;
}
    List<SysSystem> systemList = new ArrayList<SysSystem>();

	public List<SysSystem> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<SysSystem> systemList) {
		this.systemList = systemList;
	}
	private String info ;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	private String tableInfo;

	public String getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(String tableInfo) {
		this.tableInfo = tableInfo;
	}
    private String sid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
