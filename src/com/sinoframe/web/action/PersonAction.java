package com.sinoframe.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.business.IPersonBS;
import com.sinoframe.common.util.Util;

@Result(name="personlist", location="/system/userInfo/choosePerson.jsp")
public class PersonAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//people bean
	CmPeople cmPeople;
	//user service
	IPersonBS personBS;
	//当前页面标识
	private String pageTag;
	//当前标记标识（是由哪个控件点击产生的）
	private String currentTag;
	//当前标记名称显示标识
	private String currentNameTag;
	//选择标识
	private String chooseFlag;
	//记录Id的字符串
	private String recordIds;
	//记录名称的字符串
	private String recordNames;
	//人List
	private List<CmPeople> peopleList = new ArrayList<CmPeople>();
	//查询条件
	private HashMap<String, String> query  = new HashMap<String, String>();
	
	/**
	 * 选人的列表
	 * @return
	 */
	public String chooseActivePerson() {
		try {
			//最大条数
			this.getSysPageInfo().setMaxCount(personBS.searchPeopleCount(this.getSysPageInfo(),
					currentTag, Util.decodeQuery(query)));
			//查询列表
			peopleList = personBS.searchPeople(this.getSysPageInfo(), currentTag,
					Util.decodeQuery(query), this.getSysOrderByInfo());
		} catch (Exception e) {
			this.getSysPageInfo().setCurrentPage(1);
			e.printStackTrace();
		}
		return "personlist";
	}
	
	public CmPeople getCmPeople() {
		return cmPeople;
	}
	
	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}
	
	public void setPersonBS(IPersonBS personBS) {
		this.personBS = personBS;
	}

	public String getPageTag() {
		return pageTag;
	}

	public void setPageTag(String pageTag) {
		this.pageTag = pageTag;
	}

	public String getCurrentTag() {
		return currentTag;
	}

	public void setCurrentTag(String currentTag) {
		this.currentTag = currentTag;
	}

	public String getCurrentNameTag() {
		return currentNameTag;
	}

	public void setCurrentNameTag(String currentNameTag) {
		this.currentNameTag = currentNameTag;
	}

	public String getChooseFlag() {
		return chooseFlag;
	}

	public void setChooseFlag(String chooseFlag) {
		this.chooseFlag = chooseFlag;
	}

	public String getRecordIds() {
		return recordIds;
	}

	public void setRecordIds(String recordIds) {
		this.recordIds = recordIds;
	}

	public String getRecordNames() {
		return recordNames;
	}

	public void setRecordNames(String recordNames) {
		this.recordNames = recordNames;
	}

	@JSON(serialize=false)
	public List<CmPeople> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<CmPeople> peopleList) {
		this.peopleList = peopleList;
	}

	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	
}
