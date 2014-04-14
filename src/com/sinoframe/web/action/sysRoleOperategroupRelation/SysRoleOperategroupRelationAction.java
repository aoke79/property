
package com.sinoframe.web.action.sysRoleOperategroupRelation;
/**
 * SysRoleOperategroupRelation Action
 * 
 * @author HuXing
 * @version V1.0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOperateGroup;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysRoleOperategroupRelation;
import com.sinoframe.bean.SysRoleOperategroupRelationId;
import com.sinoframe.business.ISysRoleOperategroupRelationBS;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results(
		{
        	@Result(name="list",location="/system/sysRoleOperategroupRelation/sysRoleOperategroupRelationList.jsp"),
        	@Result(name="addPage",location="/system/sysRoleOperategroupRelation/sysRoleOperategroupRelationAdd.jsp"),
        	@Result(name="editPage",location="/system/sysRoleOperategroupRelation/sysRoleOperategroupRelationEdit.jsp"),
        	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
        }
        )
public class SysRoleOperategroupRelationAction extends BaseAction {
    private static final long serialVersionUID = 1780293853128531874L;
    private ISysRoleOperategroupRelationBS sysRoleOperategroupRelationBS = null;
    private String roleId = null;
    private SysRole sysRole = null;
    private String operateGroupId = null;
    //private TestStruct teststruct = new TestStruct();
    private SysRoleOperategroupRelation sysRoleOperategroupRelation = null;
    private SysRoleOperategroupRelationId sysRoleOperategroupRelationId = null; 
	private List sysRoleOperategroupRelationList = null;
	List<SysOperateGroup> sysOperateGroupList = null;
	List<SysOperateGroup> selectedList = new ArrayList<SysOperateGroup>();
	List<SysRole> sysRoleList = null;
	private Map sysOperateGroupMap = null;
	private List oprSelectArr = null;
	private List<String> oprSelectedArr = null;
	//消息实体
	private Message message;
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	//排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	private String ids;

    public String toAddPage(){
    	this.sysOperateGroupList = sysRoleOperategroupRelationBS.findPageByQuery("from SysOperateGroup");
    	sysRole = sysRoleOperategroupRelationBS.findById(SysRole.class, roleId);
    	Set<SysRoleOperategroupRelation> sysRoleOperategroupRelationSet = sysRole.getSysRoleOperategroupRelations();
    	Iterator<SysRoleOperategroupRelation> iterator = sysRoleOperategroupRelationSet.iterator();
    	while(iterator.hasNext()){
    		SysRoleOperategroupRelation next = iterator.next();
    		SysOperateGroup tempOperateGroup = next.getSysOperateGroup();
    		selectedList.add(tempOperateGroup);
    	}
    	sysOperateGroupList.removeAll(selectedList);
    	return "addPage";
    }
    /**
     * add 
     * @return forword
     */
    public String doAdd() {
    	try {
    		sysRole = sysRoleOperategroupRelationBS.findById(SysRole.class, roleId);
    		Set<SysRoleOperategroupRelation> sysRoleOperategroupRelationSet = sysRole.getSysRoleOperategroupRelations();
    		
    		Iterator<String> iterStr = oprSelectedArr.iterator();
    		while(iterStr.hasNext()){
    			String tempS = iterStr.next();
	    		Iterator<SysRoleOperategroupRelation> iterSROR = sysRoleOperategroupRelationSet.iterator();
	    		while(iterSROR.hasNext()){
	    			SysRoleOperategroupRelation tempSROR = iterSROR.next();
	    			if(tempSROR.getSysOperateGroup().getId() == tempS){
	    				iterStr.remove();
	    				iterSROR.remove();
	    				break;
	    			}
	    		}
    		}
    		
    		Iterator<SysRoleOperategroupRelation> iterator = sysRoleOperategroupRelationSet.iterator();
    		while(iterator.hasNext()){
    			SysRoleOperategroupRelation next = iterator.next();
    			SysRoleOperategroupRelationId id = next.getId();
    			iterator.remove();
    			sysRoleOperategroupRelationBS.deleteById(SysRoleOperategroupRelation.class, id);
    			
    		}
    		
    		for(String str : oprSelectedArr){
    			SysOperateGroup sysOper = new SysOperateGroup();
    			sysOper.setId(str);
    			SysRoleOperategroupRelation sysRoleOperategroupRelation = (SysRoleOperategroupRelation)this.getSysRoleOperategroupRelation().clone();
    			sysRoleOperategroupRelationId = this.getSysRoleOperategroupRelationId();
    			sysRoleOperategroupRelationId.setRoleId(sysRoleOperategroupRelation.getId().getRoleId());
    			sysRoleOperategroupRelationId.setOperateGroupid(sysOper.getId());
    			sysRoleOperategroupRelation.setId(sysRoleOperategroupRelationId);
    			sysRoleOperategroupRelationBS.save(sysRoleOperategroupRelation);
    		}
			//设定成功消息
			this.message = this.getSuccessMessage("添加成功", "roleOperategroupRelationManage", "closeCurrent", "sys-role-operategroup-relation/sys-role-operategroup-relation!list.do");
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("添加失败");
			e.printStackTrace();
		}
        return "SUCCESS";
    }
    public String toEditPage(){
    	this.sysRoleOperategroupRelation = sysRoleOperategroupRelationBS.findById(SysRoleOperategroupRelation.class,sysRoleOperategroupRelation.getId());
    	return "editPage";
    }
    /**
     * edit
     * @return forword
     */
    public String doEdit() {
    	try {
    		sysRoleOperategroupRelationBS.saveOrUpdate(this.sysRoleOperategroupRelation);
    		//设定成功消息
			this.message = this.getSuccessMessage("修改成功", "roleOperategroupRelationManage", "closeCurrent", "sys-role-operategroup-relation/sys-role-operategroup-relation!list.do");
    	}catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("修改失败");
			e.printStackTrace();
		}
        return "SUCCESS";
    };

    public String list() throws Exception {System.out.println(sysRoleOperategroupRelationBS);
    	sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysRoleOperategroupRelation r where r.id.roleId='"+roleId+"'";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.sysRoleOperategroupRelationBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysRoleOperategroupRelationBS.getErrorLog().info(e.getMessage());
		}
		
		//列表
		String hql = "from SysRoleOperategroupRelation r where r.id.roleId='"+roleId+"'";
		
		try {
			sysRoleOperategroupRelationList = sysRoleOperategroupRelationBS.findPageByQuery(sysPageInfo, hql);
		} catch (Exception e) {
			//设置日志信息
			sysRoleOperategroupRelationBS.getErrorLog().info(e.getMessage());
			e.printStackTrace();
		}

		return "list";
    	
	}
    public String delPer()throws Exception{
    	try {
    		sysRoleOperategroupRelationBS.deleteById(SysRoleOperategroupRelation.class, sysRoleOperategroupRelation.getId());
			//设定成功消息
			this.message = this.getSuccessMessage("删除成功", "roleOperategroupRelationManage", "forward", "sys-role-operategroup-relation/sys-role-operategroup-relation!list.do");
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
    	return "SUCCESS";
    }
  //删除多条记录
	public String multipleDelete() {
		try {
			System.out.println(this.getIds());
			sysRoleOperategroupRelationBS.deleteByLianHeIds(this.getIds());
			this.message = this.getSuccessMessage("删除成功", "roleOperategroupRelationManage", "forward", "sys-role-operategroup-relation/sys-role-operategroup-relation!list.do");
		} catch (Exception e) {
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
		String hql = "from SysRoleOperategroupRelation where 1=1 ";
		
		//定义排序方式及排序列
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");
		
		sysRoleOperategroupRelationList = sysRoleOperategroupRelationBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysRoleOperategroupRelation where 1=1 ";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.sysRoleOperategroupRelationBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysRoleOperategroupRelationBS.getErrorLog().info(e.getMessage());
		}
		
		return "list";
	}
    
	public SysRoleOperategroupRelation getSysRoleOperategroupRelation() {
		if(sysRoleOperategroupRelation==null)
			sysRoleOperategroupRelation=new SysRoleOperategroupRelation();
	    return sysRoleOperategroupRelation;
	}
	public void setSysRoleOperategroupRelation(SysRoleOperategroupRelation sysRoleOperategroupRelation) {
		this.sysRoleOperategroupRelation = sysRoleOperategroupRelation;
	}
	public ISysRoleOperategroupRelationBS getSysRoleOperategroupRelationBS() {
		return sysRoleOperategroupRelationBS;
	}
	public void setSysRoleOperategroupRelationBS(ISysRoleOperategroupRelationBS sysRoleOperategroupRelationBS) {
		this.sysRoleOperategroupRelationBS = sysRoleOperategroupRelationBS;
	}
	public List getSysRoleOperategroupRelationList() {
		return sysRoleOperategroupRelationList;
	}
	public void setSysRoleOperategroupRelationList(List sysRoleOperategroupRelationList) {
		this.sysRoleOperategroupRelationList = sysRoleOperategroupRelationList;
	}
	public void setOprSelectArr(List oprSelectArr) {
		this.oprSelectArr = oprSelectArr;
	}

	public void setOprSelectedArr(List<String> oprSelectedArr) {
		this.oprSelectedArr = oprSelectedArr;
	}
	public List<SysOperateGroup> getSysOperateGroupList() {
		return sysOperateGroupList;
	}
	public void setSysOperateGroupList(List<SysOperateGroup> sysOperateGroupList) {
		this.sysOperateGroupList = sysOperateGroupList;
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
	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}
	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
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
	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}
	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}
	public SysRoleOperategroupRelationId getSysRoleOperategroupRelationId() {
		if(sysRoleOperategroupRelationId == null){
			this.sysRoleOperategroupRelationId = new SysRoleOperategroupRelationId();
		}
		return sysRoleOperategroupRelationId;
	}
	public void setSysRoleOperategroupRelationId(
			SysRoleOperategroupRelationId sysRoleOperategroupRelationId) {
		this.sysRoleOperategroupRelationId = sysRoleOperategroupRelationId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getOperateGroupId() {
		return operateGroupId;
	}
	public void setOperateGroupId(String operateGroupId) {
		this.operateGroupId = operateGroupId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public SysRole getSysRole() {
		if(sysRole == null)
			sysRole = new SysRole();
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	public List<SysOperateGroup> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<SysOperateGroup> selectedList) {
		this.selectedList = selectedList;
	}
	public List getOprSelectArr() {
		return oprSelectArr;
	}
	public List<String> getOprSelectedArr() {
		return oprSelectedArr;
	}
	
	
}
