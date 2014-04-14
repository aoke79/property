package com.sinoframe.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.SysPortalData;
import com.sinoframe.business.ISysLoginBS;
import com.sinoframe.common.ConstantList;
import com.sms.security.basicdata.bean.CmAttachment;

@ParentPackage("login")
@Results({
	@Result(name="navigate", location="/standard/login.jsp"),
	@Result(name="view", location="/standard/smartview.jsp"),
	@Result(name="production", location="/sms/security/portalLink/produceAppraise.jsp"),
	@Result(name="dayreport", location="/sms/security/portalLink/dayReport.jsp"),
	@Result(name="qarweek", location="/sms/security/portalLink/qarWeek.jsp"),
	@Result(name="qarmonth", location="/sms/security/portalLink/qarMonth.jsp"),
	@Result(name="aviationsafety", location="/sms/security/portalLink/aviationSafety.jsp"),
	@Result(name="safetylecture", location="/sms/security/portalLink/safetyLecture.jsp"),
	@Result(name="manual", location="/sms/security/portalLink/manual.jsp")
})
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//bean
	SysPortalData sysPortalData;
	//登录页面使用的service
	ISysLoginBS sysLoginBS;
	//日报List
	List<SysPortalData> dayReportList = new ArrayList<SysPortalData>(); 
	//QAR周报List
	List<SysPortalData> qarWeekList = new ArrayList<SysPortalData>();
	//QAR月报List
	List<SysPortalData> qarMonthList = new ArrayList<SysPortalData>();
	//安全文化List
	List<SysPortalData> safetyLectureList = new ArrayList<SysPortalData>();
	//航空安全List
	List<SysPortalData> aviationSafetyList = new ArrayList<SysPortalData>();
	//SMS手册List
	List<SysPortalData> manualList = new ArrayList<SysPortalData>();
	//生产讲评会
	List<SysPortalData> productionList = new ArrayList<SysPortalData>();
	//显示iFrame内容使用的Id
	String pid = "";
	//显示页面使用的类型
	String pageType = "";
	//附件List
	List<CmAttachment> fileList = new ArrayList<CmAttachment>();
	
	/**
	 * 显示登录页面时的方法
	 * @return
	 */
	public String login() {
		try {
			Map<String, List<SysPortalData>> dataMap = sysLoginBS.getData();
			//日报
			dayReportList = dataMap.get("DAY_REPORT");
			//QAR周报
			qarWeekList = dataMap.get("QAR_WEEK_REPORT");
			//QAR月报
			qarMonthList = dataMap.get("QAR_MONTH_REPORT");
			//航空安全
			aviationSafetyList = dataMap.get("AVIATION_SAFETY");
			//安全文化
			safetyLectureList = dataMap.get("EVENT_REPORT");
			//SMS手册
			manualList = dataMap.get("SMS_MANUAL");
			//生产讲评会
			productionList = dataMap.get("PRODUCE_APPRAISE");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "navigate";
	}
	
	/**
	 * 查看附件
	 */
	public String viewInfo() {
		try {
			//当前数据信息
			sysPortalData = sysLoginBS.findById(SysPortalData.class, pid);
			//附件信息
			fileList = sysLoginBS.searchFiles(pid, ConstantList.FILE_PORTAL_DATA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 根据门户数据的类型跳转不同的页面
	 * @return
	 */
	public String toDetailFromPortalType() {
		try {
			if(pid != null && !pid.equals("")){
				fileList = sysLoginBS.searchFiles(pid, ConstantList.FILE_PORTAL_DATA);
			} else {
				//设置每页显示20条及当前页码
				this.setNumPerPage(20);
				this.setPageNum(this.getPageNum());
				//查询条数
				String chql = "select count(*) from SysPortalData where dataType = '" + this.getPageType() +
							  "' order by modifyTime desc ";
				long count = sysLoginBS.getCountByHQL(chql);
				this.getSysPageInfo().setMaxCount(count);
				//查询记录
				String hql = "from SysPortalData where dataType = '" + this.getPageType() +
							 "' order by modifyTime desc ";
				productionList = sysLoginBS.findPageByQuery(this.getSysPageInfo(), hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(this.getPageType().equals(ConstantList.PORTAL_PRODUCE_APPRAISE)){
			//生产讲评会
			return "production"; 
		} else if(this.getPageType().equals(ConstantList.PORTAL_QAR_MONTH_REPORT)){
			//QAR月报
			return "qarmonth";
		} else if(this.getPageType().equals(ConstantList.PORTAL_QAR_WEEK_REPORT)){
			//QAR周报
			return "qarweek";
		} else if(this.getPageType().equals(ConstantList.PORTAL_DAY_REPORT)){
			//日报
			return "dayreport";
		} else if(this.getPageType().equals(ConstantList.PORTAL_AVIATION_SAFETY)){
			//航空安全
			return "aviationsafety";
		} else if(this.getPageType().equals(ConstantList.PORTAL_SAFETY_LECTURE)){
			//安全文化
			return "safetylecture";
		} else if(this.getPageType().equals(ConstantList.PORTAL_SMS_MANUAL)){
			//SMS手册
			return "manual";
		} else {
			//无类型时返回主页
			return "navigate";
		}
	}
	

	public SysPortalData getSysPortalData() {
		return sysPortalData;
	}

	public void setSysPortalData(SysPortalData sysPortalData) {
		this.sysPortalData = sysPortalData;
	}

	public ISysLoginBS getSysLoginBS() {
		return sysLoginBS;
	}

	public void setSysLoginBS(ISysLoginBS sysLoginBS) {
		this.sysLoginBS = sysLoginBS;
	}

	public List<SysPortalData> getDayReportList() {
		return dayReportList;
	}

	public void setDayReportList(List<SysPortalData> dayReportList) {
		this.dayReportList = dayReportList;
	}

	public List<SysPortalData> getQarWeekList() {
		return qarWeekList;
	}

	public void setQarWeekList(List<SysPortalData> qarWeekList) {
		this.qarWeekList = qarWeekList;
	}

	public List<SysPortalData> getQarMonthList() {
		return qarMonthList;
	}

	public void setQarMonthList(List<SysPortalData> qarMonthList) {
		this.qarMonthList = qarMonthList;
	}

	public List<SysPortalData> getSafetyLectureList() {
		return safetyLectureList;
	}

	public void setSafetyLectureList(List<SysPortalData> safetyLectureList) {
		this.safetyLectureList = safetyLectureList;
	}

	public List<SysPortalData> getAviationSafetyList() {
		return aviationSafetyList;
	}

	public void setAviationSafetyList(List<SysPortalData> aviationSafetyList) {
		this.aviationSafetyList = aviationSafetyList;
	}

	public List<SysPortalData> getManualList() {
		return manualList;
	}

	public void setManualList(List<SysPortalData> manualList) {
		this.manualList = manualList;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public List<CmAttachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<CmAttachment> fileList) {
		this.fileList = fileList;
	}

	public List<SysPortalData> getProductionList() {
		return productionList;
	}

	public void setProductionList(List<SysPortalData> productionList) {
		this.productionList = productionList;
	}

}
