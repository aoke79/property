package com.sms.training.qualification.web.action.tfQualPilotConditions;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.Message;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualPilotTechgrade;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQualStandardValuesBS;

@Results( {
		@Result(name = "qualStandardValuesList", location = "/sms/training/qualification/conditions/qualStandardValuesList.jsp"),
		@Result(name = "toAddPage", location = "/sms/training/qualification/conditions/qualStandardValuesAdd.jsp"),
		@Result(name = "toEditPage", location = "/sms/training/qualification/conditions/qualStandardValuesEdit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
@ParentPackage("sinoframe-default")
public class TfQualStandardValuesAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// Message对象
	private Message message;
	// 当前模块名称
	private static String moduleName = "TfQualStandardValues";
	// 批量删除ids
	private String ids;
	// 页码
	private int pageNum = 1;
	// 每页显示条数
	private int numPerPage = 20;
	private ITfQualStandardValuesBS tfQualStandardValuesBS;
	private TfQualBaseAccessconditions tfQualBaseAccessconditions;
	private List<TfQualBaseAccessconditions> tfQualBaseAccessconditionsList;
	private List<BaseAirPlanType> baseAirPlanTypeList;
	private List<TfQualPilotTechgrade> tfQualPilotTechgradeList;
	//资质类型
	private TfQualBaseType tfQualBaseType;
	//存放资质类型分组ID
	private String qtgroupid;
	private String [] grade ;
	
	/**
	 * 查看 准入标准
	 * @return
	 */
	public String getqualStandardValuesList(){
		try {
			//获取当前资质类型
			tfQualBaseType=tfQualStandardValuesBS.findById(TfQualBaseType.class, tfQualBaseType.getTypeid());
			query.put("like_tfQualBaseType.typeid", tfQualBaseType.getTypeid());
			//查询准入条件list
			this.tfQualBaseAccessconditionsList = this.tfQualStandardValuesBS.getTfQualBaseAccessconditionsList(this.getSysPageInfo(), query, this.getSysOrderByInfo());;
		} catch (RuntimeException e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "qualStandardValuesList";
	}
	
	/**
	 * 跳转到增加页面
	 * @return
	 */
	public String toAddPage(){
		try {
			//获取当前资质类型
			tfQualBaseType=tfQualStandardValuesBS.findById(TfQualBaseType.class, tfQualBaseType.getTypeid());
			
			this.baseAirPlanTypeList = this.tfQualStandardValuesBS.getSelectList("BaseAirPlanType t where t.atrkind='a' order by t.atrid");
			this.tfQualPilotTechgradeList =this.tfQualStandardValuesBS.getSelectList("TfQualPilotTechgrade t where t.status='Y'  order by t.ob");
			String [] g = {"n"};
			this.grade  = g;
		} catch (RuntimeException e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toAddPage";
	}
	
	/**
	 * 保存或更新  准入条件
	 * @return
	 */
	public String doSave(){
		try {
			this.tfQualStandardValuesBS.saveTfQualBaseAccessconditions(tfQualBaseAccessconditions);
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName, "closeCurrent", "");
		} catch (RuntimeException e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("addFail"));
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
			this.baseAirPlanTypeList = this.tfQualStandardValuesBS.getSelectList("BaseAirPlanType t where t.atrkind='a' order by t.atrid");
			this.tfQualPilotTechgradeList =this.tfQualStandardValuesBS.getSelectList("TfQualPilotTechgrade t where t.status='Y'  order by t.ob");
			this.tfQualBaseAccessconditions=this.tfQualStandardValuesBS.findById(TfQualBaseAccessconditions.class, tfQualBaseAccessconditions.getSvconditionsid()) ;
			this.grade = StringUtils.split(tfQualBaseAccessconditions.getSvprevlevelgrade()==null?"":tfQualBaseAccessconditions.getSvprevlevelgrade(), ',');
		} catch (RuntimeException e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "toEditPage";
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String doDelete(){
		try {
			this.tfQualStandardValuesBS.deleteByIds(TfQualBaseAccessconditions.class, ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName, "forword",
					"tf-qual-pilot-conditions/tf-qual-standard-values!getqualStandardValuesList.do?pageNum="+this.pageNum+"&numPerPage="+this.numPerPage
					+"&"+Util.toStrQuery(query));
		} catch (RuntimeException e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 复制   此方法做的有问题   拷贝的增加的方法
	 * @return
	 */
	public String toCopyPage(){
		try {
			this.baseAirPlanTypeList = this.tfQualStandardValuesBS.getSelectList("BaseAirPlanType t where t.atrkind='a' order by t.atrid");
			this.tfQualPilotTechgradeList =this.tfQualStandardValuesBS.getSelectList("TfQualPilotTechgrade t where t.status='Y'  order by t.ob");
			this.tfQualBaseAccessconditions=this.tfQualStandardValuesBS.findById(TfQualBaseAccessconditions.class, tfQualBaseAccessconditions.getSvconditionsid()) ;
			this.tfQualBaseAccessconditions.setSvconditionsid("");
			this.grade = StringUtils.split(tfQualBaseAccessconditions.getSvprevlevelgrade()==null?"":tfQualBaseAccessconditions.getSvprevlevelgrade(), ',');
		} catch (RuntimeException e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "toAddPage";
	}
	
	
//  ===============================set/get方法=========================================================
	
	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
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
	
	@JSON(serialize = false)
	public ITfQualStandardValuesBS getTfQualStandardValuesBS() {
		return tfQualStandardValuesBS;
	}
	public void setTfQualStandardValuesBS(ITfQualStandardValuesBS tfQualStandardValuesBS) {
		this.tfQualStandardValuesBS = tfQualStandardValuesBS;
	}
	
	@JSON(serialize = false)
	public TfQualBaseAccessconditions getTfQualBaseAccessconditions() {
		return tfQualBaseAccessconditions;
	}
	public void setTfQualBaseAccessconditions(
			TfQualBaseAccessconditions tfQualBaseAccessconditions) {
		this.tfQualBaseAccessconditions = tfQualBaseAccessconditions;
	}
	
	@JSON(serialize = false)
	public String getQtgroupid() {
		return qtgroupid;
	}
	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}
	
	@JSON(serialize = false)
	public List<TfQualBaseAccessconditions> getTfQualBaseAccessconditionsList() {
		return tfQualBaseAccessconditionsList;
	}
	public void setTfQualBaseAccessconditionsList(List<TfQualBaseAccessconditions> tfQualBaseAccessconditionsList) {
		this.tfQualBaseAccessconditionsList = tfQualBaseAccessconditionsList;
	}
	
	@JSON(serialize = false)
	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}
	public void setBaseAirPlanTypeList(List<BaseAirPlanType> baseAirPlanTypeList) {
		this.baseAirPlanTypeList = baseAirPlanTypeList;
	}
	 
	@JSON(serialize = false)
	public List<TfQualPilotTechgrade> getTfQualPilotTechgradeList() {
		return tfQualPilotTechgradeList;
	}

	public void setTfQualPilotTechgradeList(
			List<TfQualPilotTechgrade> tfQualPilotTechgradeList) {
		this.tfQualPilotTechgradeList = tfQualPilotTechgradeList;
	}

	@JSON(serialize = false)
	public String[] getGrade() {
		return grade;
	}
	public void setGrade(String[] grade) {
		this.grade = grade;
	}

	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}
	

}
