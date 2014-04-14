
package com.sinoframe.web.action.sysPermission;

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
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOperateGroup;
import com.sinoframe.bean.SysOperateGroupRelation;
import com.sinoframe.bean.SysOperateGroupRelationId;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysPermission;
import com.sinoframe.business.ISysPermissionBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results(
		{
        	@Result(name="list",location="/system/sysPermission/permissionList.jsp"),
        	@Result(name="addPage",location="/system/sysPermission/adjustPer.jsp"),
        	@Result(name="editPage",location="/system/sysPermission/permissionEdit.jsp"),
        	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp")
//        	@Result(name="SUCCESS",location="sys-permission!list", type = "redirectAction")
        }
        )
public class SysPermissionAction extends BaseAction {
    private static final long serialVersionUID = 1780293853128531874L;
    
    // 模块名称
    private static String moduleName = "SysPermission";
    
    /** 机构权限的 BS 实现类 */
    private ISysPermissionBS sysPermissionBS = null;
    /** 机构权限 */
    private SysPermission sysPermission = null;
    /** 机构权限集合 */
	private List<SysPermission> sysPermissionList = null;
	/** 存储未选的操作集合 */
	List<SysOperate> sysOperateList = null;
	//存储已选的操作集合
	List<SysOperate> sysOperateSelectedList = null;
	//存储机构列表
	List<SysOrganization> sysOrganizationList = null;
	private Map sysOperateMap = null;
	private List oprSelectArr = null;
	private List<String> oprSelectedArr = null;
	
	public SysOrganization sysOrganization = null;
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
	//存放ID
	private String ids;
	private String isOrder;
	//排序方式
	private int orderMethod = 0;
	//排序字段
	private String orderBlock;
	//存放机构的ID
	private String sysOrganizationId;
	//根操作，用于遍历所有操作
	private SysOperate baseOperate;
	//当前机构所属的操作ID串：Operate Permission IDs(OPIDs)
	String OPIDs="";
	//当前机构所属的操作Name串：Operate Permission Names(OPNames)
	String OPNames="";
    
	// 跳转至操作分配页面
	public String toAdjustPage(){
		//根操作，用于遍历所有的操作权限
		//baseOperate = sysPermissionBS.findById(SysOperate.class, "-1");
    	//this.sysOperateSelectedList = sysPermissionBS.findPageByQuery("from SysPermission where sysOrganization='"+this.getSysOrganization().getId()+"'");
    	this.sysOperateList = sysPermissionBS.findPageByQuery("from SysOperate");
    	this.setSysOrganization(sysPermissionBS.findById(SysOrganization.class, this.getSysOrganization().getId()));
    	this.sysOrganizationList = sysPermissionBS.findPageByQuery("from SysOrganization");
    	return "addPage";
    }
	
    /**
     * add 
     * @return forword
     */
    public String doAdjust() {
    	Boolean flag = false;
    	try {
    		//将从添加页面返回的 OPIDs 操作ID串，放入 IDS List中
    		List<String> IDS = new ArrayList<String>();
    		if(OPIDs != null && !OPIDs.equals("")){
	    		String[] idsTemp = OPIDs.split(",");
	    		for(String idTemp : idsTemp ){
	    			IDS.add(idTemp.trim());
	    		}
	    		// 若在分配前有操作被删除或修改，则分配失败，提示用户重新获取分配页面，再做分配
	    		// 获取 application 属性范围里所有操作的 ID 串
	    		String appOperIdsTemp = this.getAppOperIds();
	    		Iterator<String> iterIDS = IDS.iterator();
	    		while(iterIDS.hasNext()){
	        		String tempS = iterIDS.next();
	        		if(appOperIdsTemp.indexOf(tempS) == -1 ){
	        			flag = true;
	        			// 抛出运行时异常，以便捕捉
	        			throw new RuntimeException();
	        		}
	        	}
    		}
    		
    		this.clearCache();
    		//sysPermissionSet 为从当前机构下得到的权限 Set 集合
        	sysOrganization = sysPermissionBS.findById(SysOrganization.class, sysOrganizationId);
        	Set<SysPermission> sysPermissionSet = null;//sysOrganization.getSysPermissions();
        	
        	//对从添加页面返回的（选中的）操作进行处理，
        	//如果当前机构权限的 Set 集合中含有已选的操作，
        	//1、则将其从 Set 集合中删除（从 Set 集合中删除已选的操作后，剩下的，便于下面从数据库中删除没有选中的操作），
        	//2、也将其从 IDS List 列表中删除（从 IDS List 列表中删除后，剩下的，就是要添加的操作权限）
        	Iterator<String> iterStr =IDS.iterator();
        	while(iterStr.hasNext()){
        		String tempS = iterStr.next();
        		Iterator<SysPermission> iterOP = sysPermissionSet.iterator();
        		inner:while (iterOP.hasNext()) {
					SysPermission tempSP = iterOP.next();
					if(tempSP.getSysOperate().getId().equals(tempS.trim())){
						//从 IDS List 列表中删除 Set 集合中含有已选的操作
						iterStr.remove();
						//从 Set 集合中删除 Set 集合中含有已选的操作
						iterOP.remove();
						break inner;
					}
				}
        	}
        	//从数据库中删除之前有的、但现在没有选中的操作权限
        	//!!!!?此处有一个BUG：当清空一个机构的权限时，最后一条记录删除有问题！！
        	if(sysPermissionSet.size()>0){
	        	SysPermission[] sysPermissions = sysPermissionSet.toArray(new SysPermission[1]);
	        	//清空SysPermission、SysOrganization、SysOperate的缓存
	        	this.clearCache();
	        	for(SysPermission s:sysPermissions){
	        		sysPermissionBS.delete(s);
	        	}
        	}
        	//保存 IDS List 列表中剩下的、要添加的操作权限
    		if(IDS!=null && IDS.size()>0){
	    		List<SysPermission> tempSP = new ArrayList<SysPermission>();
	    		for(String str : IDS){
	    			str = str.trim();
	    			sysPermission = new SysPermission();
	    			SysOperate tempOperate = sysPermissionBS.findById(SysOperate.class, str);
	    			sysPermission.setSysOperate(tempOperate);
	    			sysPermission.setSysOrganization(sysOrganization);
	    			tempSP.add(sysPermission);
	    		}
	    		sysPermissionBS.saveList(tempSP);
    		}
			//设定成功消息
			this.message = this.getSuccessMessage("分配成功", "sysPermission", "", "sys-permission/sys-permission!list.do" + Util.toStrQuery(query));
		} catch (Exception e) {
			if(flag){
				this.message = this.getFailMessage("操作权限列表被删除或更新，请重新进入操作权限分配页面，再做分配！");
			} else {
				//设置日志信息
				sysPermissionBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				//设定失败消息
				this.message = this.getFailMessage("分配失败");
				e.printStackTrace();
			}
		}
        return "SUCCESS";
    }
    
    // 跳转至编辑页面
    public String toEditPage(){
    	this.sysPermission = sysPermissionBS.findById(SysPermission.class,this.getSysPermission().getId());
    	this.sysOrganizationList = sysPermissionBS.findPageByQuery("from SysOrganization");
    	//设置操作列表到页面
    	this.sysOperateList = sysPermissionBS.findPageByQuery("from SysOperate");
    	return "editPage";
    }
    
    /**
     * edit
     * @return forword
     */
    public String doEdit() {
    	try {
    		sysPermissionBS.update(this.getSysPermission(),this.getSysPermission().getId());
    		//设定成功消息
			this.message = this.getSuccessMessage("修改成功", "sysPermission", "closeCurrent", "sys-permission/sys-permission!list.do" );
    	}catch (Exception e) {
    		//设置日志信息
			sysPermissionBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("修改失败");
			e.printStackTrace();
		}
        return "SUCCESS";
    }

    // 跳转至列表页面
    public String list(){
    	try{
    	//设置机构列表到页面
    	this.sysOrganizationList = sysPermissionBS.findPageByQuery("from SysOrganization");
    	//设置操作列表到页面
    	this.sysOperateList = sysPermissionBS.findPageByQuery("from SysOperate");
    	
    	this.clearCache();
    	sysOrganization = sysPermissionBS.findById(SysOrganization.class, this.getSysOrganization().getId());
    	// 根据关系集合遍历，拼接操作 ID 串
    	Set<SysPermission> sysPermissionSet = null;//sysOrganization.getSysPermissions();
    	Iterator<SysPermission> iterator = sysPermissionSet.iterator();
    	while(iterator.hasNext()){
    		SysPermission next = iterator.next();
    		SysOperate tempOperate = next.getSysOperate();
    		//sysOperateList.add(tempOperate);
    		//当前机构所有的操作权限传到前台页面用于判断
    		OPIDs += tempOperate.getId() + ",";
    		OPNames += tempOperate.getOperateName()+",";
    	}
    	OPIDs = OPIDs.length() == 0?"":OPIDs.substring(0,OPIDs.length()-1);
    	OPNames = OPNames.length() == 0?"":OPNames.substring(0, OPNames.length()-1);
    	
    	sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysPermission where sysOrganization='"+this.getSysOrganization().getId()+"'";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.sysPermissionBS.getCountByHQL(counthql, query));
		
		//设置当前页
		
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysPermissionBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		
		//列表
		String hql = "from SysPermission where sysOrganization='"+this.getSysOrganization().getId()+"'";
		try {
			sysPermissionList = sysPermissionBS.findPageByQuery(sysPageInfo, hql);
		} catch (Exception e) {
			//设置日志信息
			sysPermissionBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}

		return "list";
    	
	}
    
    // 删除单条记录
    public String delPer()throws Exception{
    	try {
    		sysPermissionBS.deleteById(SysPermission.class, sysPermission.getId());
			//设定成功消息
			this.message = this.getSuccessMessage("删除成功", "sysPermission", "forward", "sys-permission/sys-permission!list.do?sysOrganization.id="+this.getSysOrganization().getId());
		} catch (Exception e) {
			//设置日志信息
			sysPermissionBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
    	return "SUCCESS";
    }
    
    //删除多条记录
	public String multipleDelete() {
		try {
			sysPermissionBS.deleteByIds(SysPermission.class, this.getIds());
			this.message = this.getSuccessMessage("删除成功", "systemManage", "forward", "sys-permission/sys-permission!list.do?sysOrganization.id="+this.getSysOrganization().getId());
		} catch (Exception e) {
			//设置日志信息
			sysPermissionBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	//清空缓存
	public void clearCache(){
		sysPermissionBS.clear(SysPermission.class);
    	sysPermissionBS.clear(SysOrganization.class);
    	sysPermissionBS.clear(SysOperate.class);
	}
	
	public SysOrganization getSysOrganization() {
		if(sysOrganization==null)
			sysOrganization=new SysOrganization();
	    return sysOrganization;
	}
	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}
	public SysPermission getSysPermission() {
		if(sysPermission==null)
    		sysPermission=new SysPermission();
	    return sysPermission;
	}
	public void setSysPermission(SysPermission sysPermission) {
		this.sysPermission = sysPermission;
	}
	public ISysPermissionBS getSysPermissionBS() {
		return sysPermissionBS;
	}
	public void setSysPermissionBS(ISysPermissionBS sysPermissionBS) {
		this.sysPermissionBS = sysPermissionBS;
	}
	public List<SysPermission> getSysPermissionList() {
		return sysPermissionList;
	}
	public void setSysPermissionList(List<SysPermission> sysPermissionList) {
		this.sysPermissionList = sysPermissionList;
	}
	public void setOprSelectArr(List oprSelectArr) {
		this.oprSelectArr = oprSelectArr;
	}

	public void setOprSelectedArr(List<String> oprSelectedArr) {
		this.oprSelectedArr = oprSelectedArr;
	}
	public List<SysOperate> getSysOperateList() {
		return sysOperateList;
	}
	public void setSysOperateList(List<SysOperate> sysOperateList) {
		this.sysOperateList = sysOperateList;
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
	public List<SysOrganization> getSysOrganizationList() {
		return sysOrganizationList;
	}
	public void setSysOrganizationList(List<SysOrganization> sysOrganizationList) {
		this.sysOrganizationList = sysOrganizationList;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<SysOperate> getSysOperateSelectedList() {
		return sysOperateSelectedList;
	}
	public void setSysOperateSelectedList(List<SysOperate> sysOperateSelectedList) {
		this.sysOperateSelectedList = sysOperateSelectedList;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public int getOrderMethod() {
		return orderMethod;
	}
	public void setOrderMethod(int orderMethod) {
		this.orderMethod = orderMethod;
	}
	public String getOrderBlock() {
		return orderBlock;
	}
	public void setOrderBlock(String orderBlock) {
		this.orderBlock = orderBlock;
	}
	public String getOPNames() {
		return OPNames;
	}
	public void setOPNames(String names) {
		OPNames = names;
	}
	public SysOperate getBaseOperate() {
		return baseOperate;
	}
	public void setBaseOperate(SysOperate baseOperate) {
		this.baseOperate = baseOperate;
	}
	public String getSysOrganizationId() {
		return sysOrganizationId;
	}
	public void setSysOrganizationId(String sysOrganizationId) {
		this.sysOrganizationId = sysOrganizationId;
	}
	public String getOPIDs() {
		return OPIDs;
	}
	public void setOPIDs(String ds) {
		OPIDs = ds;
	}
	
}
