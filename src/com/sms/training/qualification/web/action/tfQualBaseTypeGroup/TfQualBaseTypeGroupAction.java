package com.sms.training.qualification.web.action.tfQualBaseTypeGroup;

import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseTgroup;
import com.sms.training.qualification.bean.TfQualBaseTypeGroup;
import com.sms.training.qualification.business.ITfQuaManagerBS;


@ParentPackage("sinoframe-default")
@Results({
	    @Result(name = "toQualBaseTypeAddPage", location = "/sms/training/qualification/quaBaseTypeGroup/qualBaseTypeAdd.jsp"),
	    @Result(name = "toQualBaseTypeList", location = "/sms/training/qualification/quaBaseTypeGroup/qualBaseTypeList.jsp"),
	    @Result(name = "toBaseTypeGroupList", location = "/sms/training/qualification/quaBaseTypeGroup/qualBaseTypeGroupList.jsp"),
		@Result(name = "toBaseTypeGroupAdd", location = "/sms/training/qualification/quaBaseTypeGroup/qualBaseTypeGroupAdd.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp")
	
		})
public class TfQualBaseTypeGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//模块儿名
	private static String moduleName = "toBaseTypeGroup";
	//大类别分组类表
	private List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList;
	//小类型分组类表
	private List<TfQualBaseTgroup> tfQualBaseTgroupList;
	//tfQuaManagerBS
	private ITfQuaManagerBS tfQuaManagerBS;
	//bean
	private TfQualBaseTypeGroup tfQualBaseTypeGroup;
	private TfQualBaseTgroup tfQualBaseTgroup;
	private Message message;
	//单个记录id
	private String id;
	private String Qtgroupid;
	//批量删除ids
	private String ids="";
	// 用于存储查询条件的字符串形式
	private String strQuery;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();

	/**
	 * constructor
	 */
	public TfQualBaseTypeGroupAction() {
	}
	
	/**
	 * 跳转到大类别分组列表页面
	 */
	
	public String toBaseTypeGroupList(){
		try {
			String hql = "select count(*) from TfQualBaseTypeGroup where 1=1 ";
			// 显示时需要的字段
			String[] queryShown = { "like_qtgroupdesc" };
			// 查询时需要的字段
			String[] queryCondition = { "like_qtgroupdesc" };
			this.tfQuaManagerBS.getQueryCondition(queryShown, queryCondition,Util.decodeQuery(query));
			long colCount = this.tfQuaManagerBS.getCountByHQL(hql, query);
			// 显示特殊查询 条件的数据
			this.tfQuaManagerBS.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(colCount);
			
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderColumn("qtgroupdesc");
			}
			
			String sql = "from TfQualBaseTypeGroup where 1=1";
			tfQualBaseTypeGroupList = this.tfQuaManagerBS.findPageByQuery(getSysPageInfo(), sql, query,sysOrderByInfo);
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
	
		return "toBaseTypeGroupList";
	}
	
	/**
	 * 跳转到大类别分组添加页面
	 * @return
	 */
	public String toAddPage(){
		try {
    		if( id != null){
    			tfQualBaseTypeGroup = this.tfQuaManagerBS.findById(TfQualBaseTypeGroup.class, id);
    		}
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
    	return "toBaseTypeGroupAdd";
    }
    
    /**
     * 根据id删除大类记录
     */
	public String deleteById(){
		try {
			String countHql = null;
			countHql = "select count(*) from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid = '" + id+"'" ;
			long colCount = this.tfQuaManagerBS.getCountByHQL(countHql, query);
			if( colCount!=0){
				this.message = this.getFailMessage("请先删除类别下子类型");
    		}else{
			this.tfQuaManagerBS.deleteById(TfQualBaseTypeGroup.class, id);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward",
					"tf-qual-base-type-group/tf-qual-base-type-group!toBaseTypeGroupList.do");
    		}
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
    
	/**
	 * 批量根据ids删除记录
	 */
	public String toDeleteBatch() {
		try {
			if( !"".equals(ids)){
				String[] idArr = ids.split(",");
				boolean baseTypeFlag = false;
				for (String id : idArr) {
					String countHql = "select count(*) from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid = '" + id+"'" ;
					int colCount= this.tfQuaManagerBS.getCountByHql(countHql);
					if(colCount !=0 ){
						baseTypeFlag = true;
					}else{
						tfQuaManagerBS.deleteByIds(TfQualBaseTypeGroup.class, id);
					}
				}
					this.message = this.getSuccessMessage("批量删除成功！", moduleName,
							"forward","tf-qual-base-type-group/tf-qual-base-type-group!toBaseTypeGroupList.do");
					if(baseTypeFlag ){
						if(idArr.length !=1){
							this.message.setMessageInfo("部分类型分组下有数据，请先删除其下数据");
							this.message.setStatusCode("300");
						}else{
							this.message = this.getFailMessage(getText("deleteFail"));
						}
					}
			}
		} catch (Exception e) {
			// 设置日志信息
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
    
    /**
     * 保存大类别分组
     * @return
     */
    public String toSavePage(){
    	try {
    		String countHql = null;
			countHql = "select count(*) from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid = '" + id+"'" ;
			long colCount = this.tfQuaManagerBS.getCountByHQL(countHql, query);
    		if( "".equals(id) || id == null){
    			tfQualBaseTypeGroup.setQtgroupid(tfQualBaseTypeGroup.getQtgroupid().trim());
    			this.tfQuaManagerBS.save(tfQualBaseTypeGroup);
    		}else if(!tfQualBaseTypeGroup.getQtgroupid().equals(id) && colCount!=0){
    			this.message = this.getFailMessage("请先删除类别下子类型");
    			return "SUCCESS";
    		}else{
    			String sql="update tf_qual_base_type_group grp " +
    					" set grp.qtgroupid='"+tfQualBaseTypeGroup.getQtgroupid().trim()+"'," +
    					"grp.qtgroupdesc='"+tfQualBaseTypeGroup.getQtgroupdesc()+"'" +
    					" where grp.qtgroupid ='"+id+"'"; 
    			this.tfQuaManagerBS.executeUpdateSQL(sql);
    		}
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName, "forward",
					"tf-qual-base-type-group/tf-qual-base-type-group!toBaseTypeGroupList.do");
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText(ERROR));
			e.printStackTrace();
		}
    	return "SUCCESS";
    }
    
   /**
	 * 跳转到子类型分组列表页面   
	 */
	public String toQualBaseTypeList(){
		try {
			String countHql = null;
			// 查询列表中的条数信息
			if(tfQualBaseTypeGroup != null ){
				countHql = "select count(*) from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid = '" + tfQualBaseTypeGroup.getQtgroupid()+"'" ;
			}
			long colCount = this.tfQuaManagerBS.getCountByHQL(countHql, query);
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(colCount);
			// 列表
			StringBuffer buffer = new StringBuffer();
			if(tfQualBaseTypeGroup!=null){
				buffer.append("from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid = '" + tfQualBaseTypeGroup.getQtgroupid() +"'" );
			}
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderColumn("typegroupdesc");
			}
			tfQualBaseTgroupList = this.tfQuaManagerBS.findPageByQuery(this.getSysPageInfo(),
					buffer.toString(), Util.decodeQuery(query), sysOrderByInfo);
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toQualBaseTypeList";
	}
    
	/**
	 *   跳转到子类型分组增加页面   
	 * @return
	 */
	public String toQualBaseTypeAddPage() {
		try { 
			if( "".equals(id) || id == null){
				tfQualBaseTypeGroup = this.tfQuaManagerBS.findById(TfQualBaseTypeGroup.class, tfQualBaseTypeGroup.getQtgroupid());
    		}else{
    			tfQualBaseTgroup = this.tfQuaManagerBS.findById(TfQualBaseTgroup.class, id);
    			tfQualBaseTypeGroup = this.tfQuaManagerBS.findById(TfQualBaseTypeGroup.class, tfQualBaseTypeGroup.getQtgroupid());
    		}
			
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toQualBaseTypeAddPage";
	}
	
	 /**
     * 保存子类型分组
     * @return
     */
    public String toQualBaseTypeSavePage(){
    	try {
    		if( "".equals(id) || id == null){
    			tfQualBaseTgroup.setTypegroupid(tfQualBaseTgroup.getTypegroupid().trim());
    			this.tfQuaManagerBS.save(tfQualBaseTgroup);
    		}else{
    			String sql="update tf_qual_base_tgroup grp " +
    					" set grp.typegroupid='"+tfQualBaseTgroup.getTypegroupid().trim()+"'," +
    					"grp.typegroupdesc='"+tfQualBaseTgroup.getTypegroupdesc()+"'," +
    					"grp.qtgroupid='"+tfQualBaseTgroup.getTfQualBaseTypeGroup().getQtgroupid().trim()+"'" +
    					" where grp.typegroupid ='"+id+"'"; 
    			this.tfQuaManagerBS.executeUpdateSQL(sql);
    		}
			Qtgroupid=tfQualBaseTgroup.getTfQualBaseTypeGroup().getQtgroupid();
			this.message = this.getSuccessMessage(getText("addSuccess"), "qualBaseTypeList", "forward",
					"tf-qual-base-type-group/tf-qual-base-type-group!toQualBaseTypeList.do?tfQualBaseTypeGroup.qtgroupid="+Qtgroupid);
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
    	return "SUCCESS";
    }
    
    /**
     * 根据id删除小类分组
     */
	public String deleteBaseTypeById(){
		try {
			String countHql = null;
			countHql = "select count(*) from TfQualBaseType t where t.tfQualBaseTgroup.typegroupid = '" + id+"'" ;
			long colCount = this.tfQuaManagerBS.getCountByHQL(countHql, query);
			if( colCount!=0){
				this.message = this.getFailMessage("请先删除对应的资质类型");
    		}else{
				this.tfQuaManagerBS.deleteById(TfQualBaseTgroup.class, id);
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "qualBaseTypeList", "forward",
						"tf-qual-base-type-group/tf-qual-base-type-group!toQualBaseTypeList.do?tfQualBaseTypeGroup.qtgroupid="+Qtgroupid);
    		}
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 批量根据ids删除小类分组
	 */
	public String toDeleteBaseTypeBatch() {
		try {
			this.tfQuaManagerBS.deleteByIds(TfQualBaseTgroup.class, ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward",
					"tf-qual-base-type-group/tf-qual-base-type-group!toQualBaseTypeList.do?tfQualBaseTypeGroup.qtgroupid="+Qtgroupid);

		} catch (Exception e) {
			// 设置日志信息
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
    
	
	
//        ===========================     set/get方法       ===================================
  
	@JSON(serialize = false)
	public ITfQuaManagerBS getTfQuaManagerBS() {
		return tfQuaManagerBS;
	}

	public void setTfQuaManagerBS(ITfQuaManagerBS tfQuaManagerBS) {
		this.tfQuaManagerBS = tfQuaManagerBS;
	}


	//此处不要加json=false
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	@JSON(serialize = false)
	public void setId(String id) {
		this.id = id;
	}

	@JSON(serialize = false)
	public void setIds(String ids) {
		this.ids = ids;
	}

	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	@JSON(serialize = false)
	public List<TfQualBaseTypeGroup> getTfQualBaseTypeGroupList() {
		return tfQualBaseTypeGroupList;
	}

	public void setTfQualBaseTypeGroupList(List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList) {
		this.tfQualBaseTypeGroupList = tfQualBaseTypeGroupList;
	}

	@JSON(serialize = false)
	public TfQualBaseTypeGroup getTfQualBaseTypeGroup() {
		return tfQualBaseTypeGroup;
	}

	public void setTfQualBaseTypeGroup(TfQualBaseTypeGroup tfQualBaseTypeGroup) {
		this.tfQualBaseTypeGroup = tfQualBaseTypeGroup;
	}

	@JSON(serialize = false)
	public List<TfQualBaseTgroup> getTfQualBaseTgroupList() {
		return tfQualBaseTgroupList;
	}

	public void setTfQualBaseTgroupList(List<TfQualBaseTgroup> tfQualBaseTgroupList) {
		this.tfQualBaseTgroupList = tfQualBaseTgroupList;
	}

	@JSON(serialize = false)
	public TfQualBaseTgroup getTfQualBaseTgroup() {
		return tfQualBaseTgroup;
	}

	public void setTfQualBaseTgroup(TfQualBaseTgroup tfQualBaseTgroup) {
		this.tfQualBaseTgroup = tfQualBaseTgroup;
	}

	public String getQtgroupid() {
		return Qtgroupid;
	}

	public void setQtgroupid(String qtgroupid) {
		Qtgroupid = qtgroupid;
	}
	public String getId() {
		return id;
	}
	
	
}
