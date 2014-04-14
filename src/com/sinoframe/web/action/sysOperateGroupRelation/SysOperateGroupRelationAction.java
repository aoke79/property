
package com.sinoframe.web.action.sysOperateGroupRelation;
/**
 * SysOperateGroupRelation Action
 * 
 * @author HuXing
 * @version V1.0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.ISysOperateGroupRelationBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
@ParentPackage("sinoframe-default")
@Results(
		{
        	@Result(name="list",location="/system/sysOperateGroupRelation/sysOperateGroupRelationList.jsp"),
        	@Result(name="addPage",location="/system/sysOperateGroupRelation/sysOperateGroupRelationAdd.jsp"),
        	@Result(name="editPage",location="/system/sysOperateGroupRelation/sysOperateGroupRelationEdit.jsp"),
        	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
        }
        )
public class SysOperateGroupRelationAction extends BaseAction {
    private static final long serialVersionUID = 1780293853128531874L;
    private ISysOperateGroupRelationBS sysOperateGroupRelationBS = null;
    private SysOperate baseOperate;
    private SysOperateGroup sysOperateGroup = null;
    private SysOperateGroupRelation sysOperateGroupRelation = null; 
    
    private SysOperateGroupRelationId sysOperateGroupRelationId = null; 
    
	private List sysOperateGroupRelationList = null;
	
	List<SysOperate> sysOperateList = new ArrayList<SysOperate>();
	List<SysOperateGroup> sysOperateGroupList = null;
	
	private List<String> oprSelectedArr = null;
	//消息实体
	private Message message;
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	private String ids;
	private String[] sysOperates ;
	private String operateGroupId = null;
	//当前操作组所属的操作ID串：Operate OperateGroup IDs(OOGIDs)
	String OOGIDs="";
	//当前操作组所属的操作Name串：Operate OperateGroup Names(OOGNames)
	String OOGNames="";
	// 用于存储查询条件的字符串形式
	private String strQuery;

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getOOGIDs() {
		return OOGIDs;
	}

	public void setOOGIDs(String ids) {
		OOGIDs = ids;
	}

	public String getOOGNames() {
		return OOGNames;
	}

	public void setOOGNames(String names) {
		OOGNames = names;
	}

	/**
	 * 跳转到添加页面
	 * @return
	 * @throws Exception
	 */
	public String toAddPage()throws Exception{
		baseOperate = sysOperateGroupRelationBS.findById(SysOperate.class, "-1");
    	return "addPage";
    }
	
    /**
     * add 
     * @return forword
     */
    public String doAdd() {
    	try {
    		List<SysOperateGroupRelation> delSysOperateGroupRelationList=new ArrayList<SysOperateGroupRelation>();
    		List<String> IDS = new ArrayList<String>();
    		if(OOGIDs != null && !OOGIDs.equals("")){
	    		String[] idsTemp = OOGIDs.split(",");
	    		for(String idTemp : idsTemp ){
	    			IDS.add(idTemp.trim());
	    		}
    		}
    		this.clearCache();
        	sysOperateGroup = sysOperateGroupRelationBS.findById(SysOperateGroup.class, operateGroupId);
        	Set<SysOperateGroupRelation> sysOperateGroupRelationSet = sysOperateGroup.getSysOperateGroupRelations();
        	
        	Iterator<String> iterStr =IDS.iterator();
        	while(iterStr.hasNext()){
        		String tempS = iterStr.next();
        		Iterator<SysOperateGroupRelation> iterSOGR = sysOperateGroupRelationSet.iterator();
        		inner:while (iterSOGR.hasNext()) {
					SysOperateGroupRelation tempSOGR = iterSOGR.next();
					if(tempSOGR.getSysOperate().getId().equals(tempS.trim())){
						iterStr.remove();
						iterSOGR.remove();
						break inner;
					}
				}
        	}
        	if(sysOperateGroupRelationSet.size()>0){
	        	SysOperateGroupRelation[] sysOperateGroupRelations=sysOperateGroupRelationSet.toArray(new SysOperateGroupRelation[1]);
	
	        	this.clearCache();
	        	for(SysOperateGroupRelation s:sysOperateGroupRelations){
	        		sysOperateGroupRelationBS.delete(s);
	        	}
        	}
    		if(IDS!=null && IDS.size()>0){
    		List<SysOperateGroupRelation> tempSOGR = new ArrayList<SysOperateGroupRelation>();
    		for(String str : IDS){
    			str = str.trim();
    			sysOperateGroupRelation = new SysOperateGroupRelation();
    			sysOperateGroupRelationId = new SysOperateGroupRelationId();
    			sysOperateGroupRelationId.setOperateId(str);
    			sysOperateGroupRelationId.setOperateGroupId(operateGroupId);
    			sysOperateGroupRelation.setId(sysOperateGroupRelationId);
    			
    			tempSOGR.add(sysOperateGroupRelation);
    		}
    		sysOperateGroupRelationBS.saveList(tempSOGR);
    		}
			//设定成功消息
			this.message = this.getSuccessMessage("添加成功", "operateGroupRelationManage", "", "sys-operate-group-relation/sys-operate-group-relation!list.do" + Util.toStrQuery(query));
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("添加失败");
			e.printStackTrace();
		}
        return "SUCCESS";
    }
    public String toEditPage(){
    	this.setSysOrderByInfo(this.getSysOrderByInfo());
    	this.sysOperateGroupRelation = sysOperateGroupRelationBS.findById(SysOperateGroupRelation.class,sysOperateGroupRelation.getId());
    	strQuery = Util.toStrQuery(query);
    	return "editPage";
    }
    
    /**
     * edit
     * @return forword
     */
    public String doEdit() {
    	try {
    		sysOperateGroupRelationBS.saveOrUpdate(this.sysOperateGroupRelation);
    		//设定成功消息
			this.message = this.getSuccessMessage("修改成功", "operateGroupRelationManage", "closeCurrent", "sys-operate-group-relation/sys-operate-group-relation!list.do");
    	}catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("修改失败");
			e.printStackTrace();
		}
        return "SUCCESS";
    };

    public String list() throws Exception {
    	if (strQuery != null && !"".equals(strQuery)) {
			query = Util.toMap(strQuery);
		}
    	try {
    		this.clearCache();
		sysOperateGroup = sysOperateGroupRelationBS.findById(SysOperateGroup.class, operateGroupId);
    	Set<SysOperateGroupRelation> sysOperateGroupRelationSet = sysOperateGroup.getSysOperateGroupRelations();
    	Iterator<SysOperateGroupRelation> iterator = sysOperateGroupRelationSet.iterator();
    	while(iterator.hasNext()){
    		SysOperateGroupRelation next = iterator.next();
    		SysOperate tempOperate = next.getSysOperate();
    		sysOperateList.add(tempOperate);
    		
    		OOGIDs += tempOperate.getId() + ",";
    		OOGNames += tempOperate.getOperateName()+",";
    	}
    	OOGIDs = OOGIDs.length() == 0?"":OOGIDs.substring(0,OOGIDs.length()-1);
    	OOGNames = OOGNames.length() == 0?"":OOGNames.substring(0, OOGNames.length()-1);
		
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysOperateGroupRelation r where r.id.operateGroupId = '" + operateGroupId + "' ";
		//设置最大条数
		this.getSysPageInfo().setMaxCount(this.sysOperateGroupRelationBS.getCountByHQL(counthql, Util.decodeQuery(query)));
		//设置当前页
		/*try{
			this.getSysPageInfo().setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysOperateGroupRelationBS.getErrorLog().info(e.getMessage());
		}*/
		//列表
		String hql = "from SysOperateGroupRelation r where r.id.operateGroupId = '" + operateGroupId + "' ";
		
		
			sysOperateGroupRelationList = sysOperateGroupRelationBS.findPageByQuery(this.getSysPageInfo(), hql, Util.decodeQuery(query), this.getSysOrderByInfo());
			strQuery = Util.toStrQuery(query);
		} catch (Exception e) {
			this.getSysPageInfo().setCurrentPage(1);
			//设置日志信息
			sysOperateGroupRelationBS.getErrorLog().info(e.getMessage());
			e.printStackTrace();
		}
		return "list";
	}
    public String delPer()throws Exception{
    	try {
    		sysOperateGroupRelationBS.deleteById(SysOperateGroupRelation.class, sysOperateGroupRelationId);
			/*//设定成功消息																							
			this.message = this.getSuccessMessage("删除成功", "operateGroupRelationManage", "forward", "sys-operate-group-relation/sys-operate-group-relation!list.do?operateGroupId="+sysOperateGroupRelationId.getOperateGroupId());
*/		
    		//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "sys-operate-group-relation/sys-operate-group-relation!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "sys-operate-group-relation/sys-operate-group-relation!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
    	
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
			sysOperateGroupRelationBS.deleteByLianHeIds(this.getIds());
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "sys-operate-group-relation/sys-operate-group-relation!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "sys-operate-group-relation/sys-operate-group-relation!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("删除失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	//清空缓存
	public void clearCache(){
		sysOperateGroupRelationBS.clear(SysOperateGroupRelation.class);
		sysOperateGroupRelationBS.clear(SysOperateGroup.class);
		sysOperateGroupRelationBS.clear(SysOperate.class);
	}
	
	public SysOperateGroupRelation getSysOperateGroupRelation() {
		if(sysOperateGroupRelation==null)
			sysOperateGroupRelation=new SysOperateGroupRelation();
	    return sysOperateGroupRelation;
	}
	public void setSysOperateGroupRelation(SysOperateGroupRelation sysOperateGroupRelation) {
		this.sysOperateGroupRelation = sysOperateGroupRelation;
	}
	public ISysOperateGroupRelationBS getSysOperateGroupRelationBS() {
		return sysOperateGroupRelationBS;
	}
	public void setSysOperateGroupRelationBS(ISysOperateGroupRelationBS sysOperateGroupRelationBS) {
		this.sysOperateGroupRelationBS = sysOperateGroupRelationBS;
	}
	public List getSysOperateGroupRelationList() {
		return sysOperateGroupRelationList;
	}
	public void setSysOperateGroupRelationList(List sysOperateGroupRelationList) {
		this.sysOperateGroupRelationList = sysOperateGroupRelationList;
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
	public List<SysOperateGroup> getSysOperateGroupList() {
		return sysOperateGroupList;
	}
	public void setSysOperateGroupList(List<SysOperateGroup> sysOperateGroupList) {
		this.sysOperateGroupList = sysOperateGroupList;
	}
	public SysOperateGroupRelationId getSysOperateGroupRelationId() {
		if(sysOperateGroupRelationId == null)
			this.sysOperateGroupRelationId = new SysOperateGroupRelationId();
		return sysOperateGroupRelationId;
	}
	public void setSysOperateGroupRelationId(
			SysOperateGroupRelationId sysOperateGroupRelationId) {
		this.sysOperateGroupRelationId = sysOperateGroupRelationId;
	}
	public List<String> getOprSelectedArr() {
		return oprSelectedArr;
	}
	public String getOperateGroupId() {
		return operateGroupId;
	}
	public void setOperateGroupId(String operateGroupId) {
		this.operateGroupId = operateGroupId;
	}
	public SysOperateGroup getSysOperateGroup() {
		if(sysOperateGroup == null)
			sysOperateGroup = new SysOperateGroup();
		return sysOperateGroup;
	}
	public void setSysOperateGroup(SysOperateGroup sysOperateGroup) {
		this.sysOperateGroup = sysOperateGroup;
	}
	public SysOperate getBaseOperate() {
		if(baseOperate == null){
			baseOperate = new SysOperate();
		}
		return baseOperate;
	}
	public void setBaseOperate(SysOperate baseOperate) {
		this.baseOperate = baseOperate;
	}
	public String[] getSysOperates() {
		return sysOperates;
	}
	public void setSysOperates(String[] sysOperates) {
		this.sysOperates = sysOperates;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
