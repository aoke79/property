package com.sms.training.qualification.web.action.tfQualConditions;

import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.Message;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.business.ITfQualBaseConditionsBS;

@Results( {
		@Result(name = "conditionsList", location = "/sms/training/qualification/conditions/conditionsList.jsp"),
		@Result(name = "conditionsAdd", location = "/sms/training/qualification/conditions/conditionsAdd.jsp"),
		@Result(name = "json", type = "json"),
    	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp")
		})
@ParentPackage("sinoframe-default")
public class TfQualConditionsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	private ITfQualBaseConditionsBS tfQualBaseConditionsBS;
	private TfQualBaseConditions tfQualBaseConditions;
    private List<TfQualBaseConditions> tfQualBaseConditionsList;
	//Message对象
	private Message message;
	//当前模块名称
	private static String moduleName = "tfQualConditions";

    private String  ids;
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;

	/**
	 * 展示list
	 * @return
	 */
	public String list() {
		this.tfQualBaseConditionsList = tfQualBaseConditionsBS.findAllByQuery(this.getSysPageInfo(),query,this.getSysOrderByInfo());
		return "conditionsList";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toAddPage(){
		return "conditionsAdd";
	}
	
	/**
	 * 保存、修改
	 * @return
	 */
	public String doSave(){
		try {
			this.tfQualBaseConditionsBS.saveAndUpdate(tfQualBaseConditions);
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName, "closeCurrent", "tf-qual-conditions/tf-qual-conditions!list.do?");
		} catch (RuntimeException e) {
			this.tfQualBaseConditionsBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
		return "json";
	}
	/**
	 * 删除多条数据
	 */
	public String doDelete(){
		try {
			this.tfQualBaseConditionsBS.BatchDeleteByIds(tfQualBaseConditions,ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName, "forward", "tf-qual-conditions/tf-qual-conditions!list.do?pageNum="+this.pageNum+"&numPerPage="+this.numPerPage
					+"&"+Util.toStrQuery(query)+"&deleteFlag=true");
		} catch (RuntimeException e) {
			this.tfQualBaseConditionsBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String toEditPage(){
		try {
			this.tfQualBaseConditions = this.tfQualBaseConditionsBS.findById(TfQualBaseConditions.class, tfQualBaseConditions.getConditionid());
		} catch (RuntimeException e) {
			this.tfQualBaseConditionsBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "conditionsAdd";
	}
	
	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	@JSON(serialize = false)
	public ITfQualBaseConditionsBS getTfQualBaseConditionsBS() {
		return tfQualBaseConditionsBS;
	}

	public void setTfQualBaseConditionsBS(ITfQualBaseConditionsBS tfQualBaseConditionsBS) {
		this.tfQualBaseConditionsBS = tfQualBaseConditionsBS;
	}

	@JSON(serialize = false)
	public List<TfQualBaseConditions> getTfQualBaseConditionsList() {
		return tfQualBaseConditionsList;
	}

	public void setTfQualBaseConditionsList(List<TfQualBaseConditions> tfQualBaseConditionsList) {
		this.tfQualBaseConditionsList = tfQualBaseConditionsList;
	}
	
	@JSON(serialize = false)
	public TfQualBaseConditions getTfQualBaseConditions() {
		return tfQualBaseConditions;
	}
	public void setTfQualBaseConditions(TfQualBaseConditions tfQualBaseConditions) {
		this.tfQualBaseConditions = tfQualBaseConditions;
	}
	
	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	@JSON(serialize = false)
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	@JSON(serialize = false)
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	

}
