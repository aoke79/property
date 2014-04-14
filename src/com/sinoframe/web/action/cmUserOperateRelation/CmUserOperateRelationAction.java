package com.sinoframe.web.action.cmUserOperateRelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.comm.util.Util;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserOperateRelation;
import com.sinoframe.bean.CmUserOperateRelationId;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysVUserOperate;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.business.ICmUserOperateRelationBS;
import com.sinoframe.business.ISysOperateBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.web.action.BaseAction;

/**
 * 
 * @author niujingwei
 * 
 */
@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "view", location = "/system/tbUserOperateRelation/tbUserOperateRelationAdd.jsp"),
		@Result(name = "list", location = "/system/tbUserOperateRelation/tbUserOperateRelationList.jsp"),
		@Result(name = "listAll", location = "/system/tbUserOperateRelation/sysVUserOperateList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
public class CmUserOperateRelationAction extends BaseAction {

	private static final long serialVersionUID = -4942023052082335487L;

	//当前模块名称
	private static String moduleName = "CmUserOperateRelation";
	
	private ICmUserBS cmUserBS;
	private ISysOperateBS sysOperateBS;
	private ICmUserOperateRelationBS cmUserOperateRelationBS;

	private CmUserOperateRelation cmUserOperateRelation;
	private CmUserOperateRelationId cmUserOperateRelationId;
	private List<CmUserOperateRelation> tbUserOperateRelationList = new ArrayList<CmUserOperateRelation>();
	private List<SysVUserOperate> sysVUserOperateList=new ArrayList<SysVUserOperate>();

	// 分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	// 页码
	private int pageNum = 1;
	// 每页显示条数
	private int numPerPage = 20;

	// 排序字段
	private String orderBlock;
	// 排序方式
	private int orderMethod = 0;
	private String isOrder;
	// 排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();

	// 用于前台权限显示
	private Map<String, String> maps = new HashMap<String, String>();
	// 用于前台用户显示
	private Map<String, String> users = new HashMap<String, String>();
	// 检索传入后台的数据
	private Map<String, String> query = new HashMap<String, String>();
	// 前台显示传入的用户ID
	private String userId;
	// 前台传入的操作IDS
	private String[] operateIds;
	// 前台传入的描述信息
	private String description;
	// 未分配的操作
	private String options;

	// 未分配的操作Id
	// private String noId;
	// 已分配的操作Id
	private String yesId="";
	// 标志
	private CmUser cmUser;
	private String ids;
	// 消息实体
	private Message message;

	private List<SysOperate> sysOperateList = new ArrayList<SysOperate>();
	private SysOperate baseOperate;
	//private String ids0, ids1, ids2, ids3, ids4, ids5, ids6, ids7, ids8, ids9,ids10,ids11,ids12,ids13,ids14,ids15,ids16,ids17;
	
	//private String[] operateIDs;
	
	//当前用户所属的操作ID串：User Operate IDs(UOIDs)
	String UOIDs="";
	//当前用户所属的操作Name串：User Operate Names(UONames)
	String UONames="";

	// 去操作分配页面
	public String goView() throws RollbackableBizException {
		try{
			this.clearCache();
			initOperate();
			cmUser = cmUserBS.findById(userId);
			//根操作，用于遍历所有的操作权限
			baseOperate = sysOperateBS.findById(SysOperate.class, "-1");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	// 根据条件进行检索
	public String search() throws RollbackableBizException {
		HashMap<String, String> query1 = new HashMap<String, String>();
		query1.put("like_sysOperate.operateName", query.get("operateName"));

		// 根据页面传回的信息，构造sysPageInfo对象
		if (this.getPageNum() != 0) {
			this.getSysPageInfo().setPageSize(this.getNumPerPage());
			this.getSysPageInfo().setStartIndex(
					(this.getPageNum() - 1) * this.getNumPerPage());
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
		}
		String string = this.getSysPageInfo().getStartIndex() + "="
				+ this.getSysPageInfo().getStartIndex();

		String hql = "from CmUserOperateRelation r where  " + string
				+ " and  r.id.userId='" + userId + "' ";

		if ("yes".equals(isOrder)) {
			// 设置排序方式，0为正常排序，1为反相排序
			if (orderMethod == 0) {
				sysOrderByInfo.setOrderAsc("asc");
				orderMethod = 1;
			} else {
				sysOrderByInfo.setOrderAsc("desc");
				orderMethod = 0;
			}
		}
		// 获取排序字段
		sysOrderByInfo.setOrderColumn(orderBlock);
		// 获取最大记录数
		String strCountHql = "select count(*) from CmUserOperateRelation r where  2=2 and r.id.userId='"
				+ userId + "' ";
		this.getSysPageInfo()
				.setMaxCount(
						this.cmUserOperateRelationBS.getCountByHQL(strCountHql,
								query1));
		tbUserOperateRelationList = cmUserOperateRelationBS.findPageByQuery(
				this.getSysPageInfo(), hql, query1, sysOrderByInfo);

		return "list";
	}

	// 指向用户角色关系管理页面
	public String operateManage() throws RollbackableBizException {
		
		try {
			cmUser = cmUserOperateRelationBS.findById(CmUser.class, userId);
	    	Set<CmUserOperateRelation> cmUserOperateRelationSet = cmUser.getCmUserOperateRelations();
	    	Iterator<CmUserOperateRelation> iterator = cmUserOperateRelationSet.iterator();
	    	while(iterator.hasNext()){
	    		CmUserOperateRelation next = iterator.next();
	    		SysOperate tempOperate = next.getSysOperate();
	    		sysOperateList.add(tempOperate);
	    		//当前角色所有的操作权限传到前台页面用于判断
	    		UOIDs += tempOperate.getId() + ",";
	    		UONames += tempOperate.getOperateName()+",";
	    	}
	    	UOIDs = UOIDs.length() == 0?"":UOIDs.substring(0,UOIDs.length()-1);
	    	UONames = UONames.length() == 0?"":UONames.substring(0, UONames.length()-1);		
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		// 根据页面传回的信息，构造sysPageInfo对象
		if (this.getPageNum() != 0) {
			this.getSysPageInfo().setPageSize(this.getNumPerPage());
			this.getSysPageInfo().setStartIndex(
					(this.getPageNum() - 1) * this.getNumPerPage());
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
		}
		String string = this.getSysPageInfo().getStartIndex() + "="
				+ this.getSysPageInfo().getStartIndex();

		String hql = "from CmUserOperateRelation r where 1=1 and r.id.userId ='"
				+ userId + "'";
		// 定义排序方式及排序列
		SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
		sysOrderByInfo.setOrderAsc("");
		sysOrderByInfo.setOrderColumn("");

		// 获取最大记录数
		String strCountHql = "select count(*) from CmUserOperateRelation r where "
				+ string + "  and r.id.userId ='" + userId + "'";
		this.getSysPageInfo().setMaxCount(
				this.cmUserOperateRelationBS.getCountByHQL(strCountHql));
		tbUserOperateRelationList = cmUserOperateRelationBS.findPageByQuery(
				this.getSysPageInfo(), hql);

		return "list";
	}
	
	
	// 指向用户角色关系管理页面
	public String searchAllOperate(){
		
		if (this.getPageNum() != 0) {
			this.getSysPageInfo().setPageSize(this.getNumPerPage());
			this.getSysPageInfo().setStartIndex(
					(this.getPageNum() - 1) * this.getNumPerPage());
			this.getSysPageInfo().setCurrentPage(this.getPageNum());
		}
		String string = this.getSysPageInfo().getStartIndex() + "="
		+ this.getSysPageInfo().getStartIndex();
		
	   //通过SysVUserOperate获得操作
		HashMap<String, String> query1 = new HashMap<String, String>();
		query1.put("like_operateName", query.get("operateName"));
		
		 String strCountHql="select count(*) from SysVUserOperate sv where "+string+ "and sv.fid.userId='"+userId+"' ";
		
		this.getSysPageInfo()
		.setMaxCount(
				this.cmUserOperateRelationBS.getCountByHQL(strCountHql,
						query1));
       String hql="from SysVUserOperate sv where sv.fid.userId='"+userId+"' ";
       try {
    	   sysVUserOperateList =cmUserOperateRelationBS.findPageByQuery(
    				this.getSysPageInfo(), hql, query1, sysOrderByInfo);
	   } catch (Exception e) {
		   cmUserOperateRelationBS.getErrorLog().info(e.getMessage()+"#CmUserOperateRelation");
		   e.printStackTrace();
    	}
     
	   return "listAll";
	}
	
	

	// 删除单条记录
	public String delTbUserOperateRelation() {
		try {
			cmUserOperateRelationBS.deleteById(CmUserOperateRelation.class,
					cmUserOperateRelationId);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"tbUserOperateRelationManage", "forward",
					"cm-user-operate-relation/cm-user-operate-relation!operateManage.do?userId="
							+ cmUserOperateRelationId.getUserId() + "&pageNum="
							+ getPageNum());
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("deleteFail"));
		}
		return "SUCCESS";
	}

	// 删除多条记录
	public String multipleDelete() {
		try {

			String[] tempStrs = ids.split(",");
			String userIdString = "";
			userIdString = tempStrs[0].substring(0, tempStrs[0].indexOf("&"));

			cmUserOperateRelationBS.deleteByLianHeIds(this.getIds());
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"roleAccountRelationManage", "forward",
					"cm-user-operate-relation/cm-user-operate-relation!operateManage.do?userId="
							+ userIdString);
		} catch (Exception e) {
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String list() throws RollbackableBizException {

		// 查询记录的hql
		String hql = "from CmUserOperateRelation ";
		// 查询记录数的hql
		String strCountHql = "select count(*) from CmUserOperateRelation";
		// 设置查询的最大记录数
		this.getSysPageInfo().setMaxCount(
				this.cmUserOperateRelationBS.getCountByHQL(strCountHql));
		this.getSysPageInfo().setStartIndex(0);
		tbUserOperateRelationList = cmUserOperateRelationBS.findPageByQuery(
				this.getSysPageInfo(), hql);
		return "list";
	}

	// 给用户分配操作
	public String doFp() {
		Boolean flag = false;
		try {
			this.clearCache();
			cmUser = cmUserOperateRelationBS.findById(CmUser.class, userId);
			//将从添加页面返回的 UOIDs 操作ID串，放入 operateIds String[]数组中
			operateIds = UOIDs.split(",");
			
			// 若在分配前有操作被删除或修改，则分配失败，提示用户重新获取分配页面，再做分配
			// 获取 application 属性范围里所有操作的 ID 串
			String appOperIdsTemp = this.getAppOperIds();
    		for(String tempS : operateIds){
        		if(appOperIdsTemp.indexOf(tempS) == -1 ){
        			flag = true;
        			// 抛出运行时异常，以便捕捉
        			throw new RuntimeException();
        		}
        	}
			
			StringBuilder sbId = new StringBuilder();
			List<SysOperate> list=cmUserOperateRelationBS.findPageByQuery("select t.sysOperate from CmUserOperateRelation t where 1=1 and t.cmUser.userId='"+userId+"'");
			if(list.size()>0){
			for(SysOperate sysOperate:list){
				sbId.append(sysOperate.getId() + ",");
			}
			String tempString = sbId.substring(0, sbId.lastIndexOf(","));
			yesId = tempString;
			}
			String[] delStrings = doDeleteOperate();
			String[] addStrings = doAddOperate();
			// 做添加操作
			if (addStrings != null) {
				ArrayList<CmUserOperateRelation> tbUserOperateRelationList = new ArrayList<CmUserOperateRelation>();
				CmUserOperateRelation cmUserOperateRelation;
				CmUserOperateRelationId cmUserOperateRelationId;
				for (String operateId : addStrings) {
					cmUserOperateRelation = new CmUserOperateRelation();
					cmUserOperateRelationId = new CmUserOperateRelationId();
					cmUserOperateRelationId.setId(operateId.trim());
					cmUserOperateRelationId.setUserId(userId);
					cmUserOperateRelation.setId(cmUserOperateRelationId);
					tbUserOperateRelationList.add(cmUserOperateRelation);
				}
				cmUserOperateRelationBS.saveList(tbUserOperateRelationList);
			}
			this.clearCache();
			// 做删除操作
			if (delStrings != null) {
				CmUserOperateRelationId cmUserOperateRelationId;
				for (String deloperateId : delStrings) {
					cmUserOperateRelationId = new CmUserOperateRelationId();
					cmUserOperateRelationId.setId(deloperateId);
					cmUserOperateRelationId.setUserId(userId);
					cmUserOperateRelationBS.deleteById(
							CmUserOperateRelation.class,
							cmUserOperateRelationId);
				}
			}

			// cmUserOperateRelationBS.getAll(getPaging());
			this.message = this.getSuccessMessage(getText("assignSuccess"),
					"tbUserOperateRelationManage", "",
					"cm-user-operate-relation/cm-user-operate-relation!operateManage.do?userId="
							+ getUserId());
		} catch (Exception e) {
			if(flag){
				this.message = this.getFailMessage("操作权限列表被删除或更新，请重新进入操作权限分配页面，再做分配！");
			} else {
				//设置日志信息
				sysOperateBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
				//设定失败消息
				this.message = this.getFailMessage("分配失败");
				e.printStackTrace();
			}
		}
		return "SUCCESS";
	}

	// 从前台提交的数据中找出数据库中已有的并且准备删除的Operate
	private String[] doDeleteOperate() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {

			if (!"".equals(yesId)) {
				String[] yesIds = yesId.split(",");
				String newString = Util.Array2String(operateIds);
				for (String yesIdsSub : yesIds) {
					if (!newString.contains(yesIdsSub)) {
						sb.append(yesIdsSub + ",");
					}
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}
			}

		}
		return null;
	}

	// 从前台提交的数据中找出数据库中没有并且准备添加的Operate
	private String[] doAddOperate() {
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		if (!"null".equals(yesId)) {
			if (!"".equals(yesId)) {
				// 此时要添加的权限
				for (String roleIdString : operateIds) {
					if (!yesId.contains(roleIdString.trim())) {
						sb.append(roleIdString + ",");
					}
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}
			} else {

				for (String roleIdString : operateIds) {
					sb.append(roleIdString + ",");
				}
				if (sb.length() > 0) {
					tempString = sb.substring(0, sb.lastIndexOf(","));
					return tempString.split(",");
				}

			}
		}

		return null;
	}

	private void initOperate() throws RollbackableBizException {
		sysOperateList=cmUserOperateRelationBS.findPageByQuery("select t.sysOperate from CmUserOperateRelation t where t.cmUser.userId='"+userId+"'");
	}
	//清空缓存
	public void clearCache(){
		cmUserOperateRelationBS.clear(CmUser.class);
		cmUserOperateRelationBS.clear(SysOperate.class);
		cmUserOperateRelationBS.clear(CmUserOperateRelation.class);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	public String[] getOperateIds() {
		return operateIds;
	}

	public void setOperateIds(String[] operateIds) {
		this.operateIds = operateIds;
	}

	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}

	public ICmUserBS getTbUserBS() {
		return cmUserBS;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public void setYesId(String yesId) {
		this.yesId = yesId;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	public ISysOperateBS getSysOperateBS() {
		return sysOperateBS;
	}

	public void setSysOperateBS(ISysOperateBS sysOperateBS) {
		this.sysOperateBS = sysOperateBS;
	}

	public ICmUserOperateRelationBS getCmUserOperateRelationBS() {
		return cmUserOperateRelationBS;
	}

	public void setCmUserOperateRelationBS(
			ICmUserOperateRelationBS cmUserOperateRelationBS) {
		this.cmUserOperateRelationBS = cmUserOperateRelationBS;
	}

	public CmUserOperateRelation getTbUserOperateRelation() {
		return cmUserOperateRelation;
	}

	public void setTbUserOperateRelation(
			CmUserOperateRelation cmUserOperateRelation) {
		this.cmUserOperateRelation = cmUserOperateRelation;
	}

	public CmUserOperateRelationId getCmUserOperateRelationId() {
		return cmUserOperateRelationId;
	}

	public void setCmUserOperateRelationId(
			CmUserOperateRelationId cmUserOperateRelationId) {
		this.cmUserOperateRelationId = cmUserOperateRelationId;
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

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}

	public List<SysOperate> getSysOperateList() {
		return sysOperateList;
	}

	public void setSysOperateList(List<SysOperate> sysOperateList) {
		this.sysOperateList = sysOperateList;
	}

	public SysOperate getBaseOperate() {
		return baseOperate;
	}

	public void setBaseOperate(SysOperate baseOperate) {
		this.baseOperate = baseOperate;
	}

	public CmUser getCmUser() {
		return cmUser;
	}




	public String getYesId() {
		return yesId;
	}

	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public List<CmUserOperateRelation> getTbUserOperateRelationList() {
		return tbUserOperateRelationList;
	}

	public void setTbUserOperateRelationList(
			List<CmUserOperateRelation> tbUserOperateRelationList) {
		this.tbUserOperateRelationList = tbUserOperateRelationList;
	}


	public List<SysVUserOperate> getSysVUserOperateList() {
		return sysVUserOperateList;
	}

	public String getUOIDs() {
		return UOIDs;
	}



	public void setSysVUserOperateList(List<SysVUserOperate> sysVUserOperateList) {
		this.sysVUserOperateList = sysVUserOperateList;
	}

	public void setUOIDs(String ds) {
		UOIDs = ds;
	}


	public String getUONames() {
		return UONames;
	}





	public void setUONames(String names) {
		UONames = names;
	}





}
