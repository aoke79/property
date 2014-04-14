package com.sinoframe.web.action.sysRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
/**
 * 
 * @author niujingwei
 *
 */

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysMenu;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysSystem;
import com.sinoframe.business.ISysRoleBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "list", location = "/system/sysRole/sysRoleList.jsp"),
		@Result(name = "addPage", location = "/system/sysRole/sysRoleAdd.jsp"),
		@Result(name = "editPage", location = "/system/sysRole/sysRoleEdit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name="json",type="json")})
public class SysRoleAction extends BaseAction {

	
	
	private static final long serialVersionUID = 1780293853128531874L;

	private ISysRoleBS targetISysRoleBS;
	// 数据载体
	private SysRole role;
	private String ids;
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	//排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	//排序字段
	private String orderBlock;
	//排序方式
	private int orderMethod = 0;
	// 消息实体
	private Message message;
	//子系统的集合ID
	private String sid;
	
	// 检索传入后台的数据
	private SysMenu sysMenu;
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}
	private Map<String, String> query = new HashMap<String, String>();
	private String isOrder;
	private List<SysRole> sysRoleList=new ArrayList<SysRole>();
	public String toAddPage() {
		 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
         this.sysSystemList = getSystemList(cmUser);
		
         tableInfo = " <table border='0'>";
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
		return "addPage";
	}
	
	public String lang(){
		return "json";
	}

	//添加一个角色对象
	public String roleAdd() {
		try {
			System.out.println(this.sid);
			this.role.setSubsystemId(sid);
			targetISysRoleBS.save(role);
			this.message = this.getSuccessMessage(getText("addSuccess"), "roleManage",
					"closeCurrent", "sys-role/sys-role!list.do");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	//删除一条数据
	public String delRole() {
		try {

			if (role == null || role.getRoleId() == null){
				return "idRequired";	
				
			}
			
			//角色校验是否删除   by lujie
			String hql = " from SysRelationAccountRole where  sysRole.roleId='"+role.getRoleId()+"'";
			List<SysRelationAccountRole>  list = this.targetISysRoleBS.findPageByQuery(hql);
			if(list != null && list.size() !=0){
				this.message = this.getFailMessage("该角色已经被用户引用了，无法删除!");
				return "SUCCESS";	
			}
			
			
			
			targetISysRoleBS.delete(role);
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "roleManage",
					"forward", "sys-role/sys-role!list.do?pageNum="+getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("deleteFailReason"));
		}
		return "SUCCESS";
	}
	
	
	//回显全部数据
	public String list() throws Exception {
		try {
			//targetISysRoleBS.getAll(getPaging());
			// 查询记录的hql
			String hql = "from SysRole where 1=1";
			// 查询记录数的hql
			String strCountHql = "select count(*) from SysRole where 1=1";
			//by lujie
			CmUser cmUser = (CmUser) ActionContext.getContext().getSession()
					.get("user");
			if (!"超级管理员".equals(cmUser.getName().trim())) {
				hql += " and subsystemId like  '%" + cmUser.getSubsystemId() + "%' ";
				strCountHql += " and subsystemId like  '%" + cmUser.getSubsystemId()
						+ "%' ";

			}
			   ActionContext.getContext().put("userName",cmUser.getName());
				 String hqls = "from SysSystem where state !='0'";
				 this.sysSystemList = this.targetISysRoleBS.findPageByQuery(hqls);
			// 设置查询的最大记录数
			this.getSysPageInfo().setMaxCount(
					this.targetISysRoleBS.getCountByHQL(strCountHql));
			this.getSysPageInfo().setStartIndex(0);
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
			sysRoleList = targetISysRoleBS.findPageByQuery(this
					.getSysPageInfo(), hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//去编辑页面
	public String toEditPage() {
		
		try {
			 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
             this.sysSystemList = getSystemList(cmUser);
			 role = targetISysRoleBS.searchRoleById(role.getRoleId());
			
			String[] str = new String[]{};
			if(role.getSubsystemId() != null && !"null".equals(role.getSubsystemId())){
				if(role.getSubsystemId().length()  != 0 ){
					str= role.getSubsystemId().split(",");
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
			
			
		} catch (RollbackableBizException e) {
			e.printStackTrace();
		}
		return "editPage";
	}

	//执行编辑操作
	public String roleEdit() {
		try {
			role.setSubsystemId(this.sid);
			targetISysRoleBS.upateRole(role);
			this.message = this.getSuccessMessage(getText("updateSuccess"), "roleManage",
					"forward", "sys-role/sys-role!list.do?pageNum="+getPageNum());
		} catch (RollbackableBizException e) {
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
			
		}
		return "SUCCESS";
	}
	//删除多条记录
	public String multipleDelete() {
		try {
			// by LU SIR
			if(this.getIds() != null && this.getIds().length() !=0){
				String str = "";
				for(int i=0;i<this.getIds().split(",").length;i++){
					str += "'"+this.getIds().split(",")[i].trim()+"',";
				}
				str = str.substring(0,str.length()-1);
				
				String sl = "from SysRelationAccountRole where sysRole.roleId in ("+str+")";
				List<SysRelationAccountRole> sc = this.targetISysRoleBS.findPageByQuery(sl);
				if(sc != null && sc.size() !=0){
					String c ="";
					for(SysRelationAccountRole s : sc){
						if(c.indexOf(s.getSysRole().getRoleName()) == -1){
							c +=s.getSysRole().getRoleName()+",";
						}
					}
					this.message = this.getFailMessage(c+"已经被引用，无法删除!");
					return "SUCCESS";
				}
			}
			
			
			targetISysRoleBS.deleteByIds(SysRole.class, this.getIds());
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "roleManage", "forward", "sys-role/sys-role!list.do");
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	// 根据条件进行检索
	public String search() throws RollbackableBizException {
		try {
			CmUser cmUser =  (CmUser) ActionContext.getContext().getSession().get("user");
			 ActionContext.getContext().put("userName",cmUser.getName());
				 String hqls = "from SysSystem where state !='0'";
				 this.sysSystemList = this.targetISysRoleBS.findPageByQuery(hqls);
			HashMap<String, String> query1 = new HashMap<String, String>();
			Set<Entry<String, String>> entrySet = query.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while(iterator.hasNext()){
				Entry<String, String> next = iterator.next();
				if("roleName".equals(next.getKey())){
					query1.put("like_roleName", query.get("roleName"));
				}else if("description".equals(next.getKey())){
					query1.put("like_description", query.get("description"));
				}else if ("eroleName".endsWith(next.getKey())) {
					query1.put("like_eroleName", query.get("eroleName"));
				}else if ("edescription".endsWith(next.getKey())) {
					query1.put("like_edescription", query.get("edescription"));
				}else if("subsystemId".endsWith(next.getKey())){
					query1.put("like_subsystemId", query.get("subsystemId"));
				}
			}
			// 根据页面传回的信息，构造sysPageInfo对象
			if(this.getPageNum() != 0){
				this.getSysPageInfo().setPageSize(this.getNumPerPage());
				this.getSysPageInfo().setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
				this.getSysPageInfo().setCurrentPage(this.getPageNum());
			}
			String string=this.getSysPageInfo().getStartIndex()+"="+this.getSysPageInfo().getStartIndex();

			
			String hql = "from SysRole where " + string;
			//by lujie
			if (!"超级管理员".equals(cmUser.getName().trim())) {
				hql += " and subsystemId like '%" + cmUser.getSubsystemId() + "%' ";
			}
			
			if("yes".equals(isOrder)){
				  //设置排序方式，0为正常排序，1为反相排序
				   if(orderMethod == 0){
					sysOrderByInfo.setOrderAsc("asc");
					orderMethod = 1;
				   } else {
					sysOrderByInfo.setOrderAsc("desc");
					orderMethod = 0;
				   }
				}
				//获取排序字段
				sysOrderByInfo.setOrderColumn(orderBlock);
			// 获取最大记录数
			String strCountHql = "select count(*) from SysRole r where  2=2 ";
			//by lujie
			if (!"超级管理员".equals(cmUser.getName().trim())) {
				strCountHql += " and subsystemId like '%" + cmUser.getSubsystemId() + "%' ";
			}
			this.getSysPageInfo().setMaxCount(this.targetISysRoleBS.getCountByHQL(strCountHql, query1));
			sysRoleList = targetISysRoleBS.findPageByQuery(
					this.getSysPageInfo(), hql, query1, sysOrderByInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}

    public  List<SysSystem> getSystemList(CmUser cmUser){
	      List<SysSystem> sys = null;
		 String hqlsys = "from SysSystem where 1=1 and state!='0' " ;
		 if("超级管理员".equals(cmUser.getName().trim())){
			 return  sys  = targetISysRoleBS.findPageByQuery(hqlsys);
		 }else{
			 if(!"null".equals(cmUser.getSubsystemId()) && !"".equals(cmUser.getSubsystemId())){
				 hqlsys += " and id='"+cmUser.getSubsystemId()+"'";
				 return sys = targetISysRoleBS.findPageByQuery(hqlsys);
			 }
		 }
	  return null;
}
	public ISysRoleBS getTargetISysRoleBS() {
		return targetISysRoleBS;
	}

	public void setTargetISysRoleBS(ISysRoleBS targetISysRoleBS) {
		this.targetISysRoleBS = targetISysRoleBS;
	}

	public SysRole getRole() {
		if (role == null)
			role = new SysRole();
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
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

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
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

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}
  	//子系统集合
	private List<SysSystem> sysSystemList = new ArrayList<SysSystem>();

	public List<SysSystem> getSysSystemList() {
		return sysSystemList;
	}
	public void setSysSystemList(List<SysSystem> sysSystemList) {
		this.sysSystemList = sysSystemList;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	private String tableInfo;
	public String getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(String tableInfo) {
		this.tableInfo = tableInfo;
	}
}
