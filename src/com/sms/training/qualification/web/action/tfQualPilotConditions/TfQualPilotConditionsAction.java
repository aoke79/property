package com.sms.training.qualification.web.action.tfQualPilotConditions;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.Message;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;
import com.sms.training.qualification.bean.TfQualPilotConditions;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQualPilotConditionsBS;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "pilotConditionsList", location = "/sms/training/qualification/conditions/pilotConditionsList.jsp"),
		@Result(name = "toEditPage", location = "/sms/training/qualification/conditions/pilotConditionsEdit.jsp"),
    	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp")
})

/**
 * 飞行员资格库维护
 * @author
 */
public class TfQualPilotConditionsAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	private ITfQualPilotConditionsBS tfQualPilotConditionsBS;
    private List<CmPeople> cmPeopleList;
	//Message对象
	private Message message;
	//当前模块名称
	private static String moduleName = "TfQualPilotConditions";

    private String  ids;
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	//人员信息
	private CmPeople cmPeople;
	//准入信息
	private List <TfQualPilotConditions> tfQualPilotConditionsList;
	//准入基本信息
	private  List<TfQualBaseConditions> tfQualBaseConditionsList;
	//飞行时间准入信息
	private TfQualBasePilotInfo tfQualBasePilotInfo;
	//飞行员执照机型技术等级信息
	private List<Object> tfQualPilotLicenceList;

	/**
	 * 获取飞行员资格list
	 * @return
	 */
	public String list() {
		//根据分页信息和排序信息获得人员信息
		this.cmPeopleList = tfQualPilotConditionsBS.getPilotList(this.getSysPageInfo(), query, this.getSysOrderByInfo());
		
		return "pilotConditionsList";
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String toEditPage() {
		this.cmPeople = tfQualPilotConditionsBS.findById(CmPeople.class, cmPeople.getId());
		this.tfQualPilotConditionsList = tfQualPilotConditionsBS.getQualPilotConditionsByPilotId(cmPeople.getId());
		this.tfQualBaseConditionsList = tfQualPilotConditionsBS.getAllTfQualBaseConditions();
		this.tfQualBasePilotInfo = tfQualPilotConditionsBS.getTfQualBasePilotInfo(cmPeople.getId());
		this.tfQualPilotLicenceList = tfQualPilotConditionsBS.getTfQualPilotTechrecord(cmPeople.getId());
			
		return "toEditPage";
	}
	
	/**
	 * 保存的方法
	 * @return
	 */
	public String doSave() {
		try {
			this.tfQualPilotConditionsBS.saveTfQualPilotConditions(ids, cmPeople);
			setTabIndexToReload("1");
			this.message = getSuccessMessage(getText("addSuccess"), "queryForPilotStay", "", "");
		} catch (RuntimeException e) {
			tfQualPilotConditionsBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		
		return "SUCCESS";
	} 
	
	/**
	 * 处理子标签页跳转问题
	 * @param index
	 */
	private void setTabIndexToReload(String index) {
		this.getServletRequest().getSession().setAttribute("tabIndex_queryForPilotStay", index);
		if(index != null && !index.equals("0")) {
			this.getServletRequest().getSession().setAttribute("flush_queryForPilotStay", "y");
		}
	}
	
	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	public ITfQualPilotConditionsBS getTfQualPilotConditionsBS() {
		return tfQualPilotConditionsBS;
	}
	public void setTfQualPilotConditionsBS(
			ITfQualPilotConditionsBS tfQualPilotConditionsBS) {
		this.tfQualPilotConditionsBS = tfQualPilotConditionsBS;
	}
	public List<CmPeople> getCmPeopleList() {
		return cmPeopleList;
	}
	public void setCmPeopleList(List<CmPeople> cmPeopleList) {
		this.cmPeopleList = cmPeopleList;
	}
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
	public CmPeople getCmPeople() {
		return cmPeople;
	}
	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}
	public List<TfQualPilotConditions> getTfQualPilotConditionsList() {
		return tfQualPilotConditionsList;
	}
	public void setTfQualPilotConditionsList(List<TfQualPilotConditions> tfQualPilotConditionsList) {
		this.tfQualPilotConditionsList = tfQualPilotConditionsList;
	}
	public TfQualBasePilotInfo getTfQualBasePilotInfo() {
		return tfQualBasePilotInfo;
	}
	public void setTfQualBasePilotInfo(TfQualBasePilotInfo tfQualBasePilotInfo) {
		this.tfQualBasePilotInfo = tfQualBasePilotInfo;
	}
	public List<TfQualBaseConditions> getTfQualBaseConditionsList() {
		return tfQualBaseConditionsList;
	}
	public void setTfQualBaseConditionsList(
			List<TfQualBaseConditions> tfQualBaseConditionsList) {
		this.tfQualBaseConditionsList = tfQualBaseConditionsList;
	}
	public List<Object> getTfQualPilotLicenceList() {
		return tfQualPilotLicenceList;
	}
	public void setTfQualPilotLicenceList(List<Object> tfQualPilotLicenceList) {
		this.tfQualPilotLicenceList = tfQualPilotLicenceList;
	}
}
