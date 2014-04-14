package com.sms.training.qualification.web.action.tfQuaApply;
//此类可删
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.web.action.BaseAction;
@Results( {
//    以下页面可删
		@Result(name = "pilotList", location = "/sms/training/qualification/qualifiedDeclare/listview.jsp"),
		@Result(name = "pilotLicense", location = "/sms/training/qualification/qualifiedDeclare/pilotLicense.jsp"),
		@Result(name = "learningResume", location = "/sms/training/qualification/qualifiedDeclare/learningResume.jsp"),
		@Result(name = "workResume", location = "/sms/training/qualification/qualifiedDeclare/workResume.jsp"),
		@Result(name = "requestInfo", location = "/sms/training/qualification/qualifiedDeclare/requestInfo.jsp"),
		@Result(name = "flightDataRecorder", location = "/sms/training/qualification/qualifiedDeclare/flightDataRecorder.jsp"),
		@Result(name = "trainingRecords", location = "/sms/training/qualification/qualifiedDeclare/trainingRecords.jsp"),
		@Result(name = "trainingRecordsAdd", location = "/sms/training/qualification/qualifiedDeclare/trainingRecordsAdd.jsp"),
		@Result(name = "technicalInspectionReport", location = "/sms/training/qualification/qualifiedDeclare/technicalInspectionReport.jsp"),
		@Result(name = "technicalInspectionReportJianCha", location = "/sms/training/qualification/qualifiedDeclare/technicalInspectionReportHasValueJianCha.jsp"),
		@Result(name = "technicalInspectionReportFeiXingYuan", location = "/sms/training/qualification/qualifiedDeclare/technicalInspectionReportHasValueFeiXingYuan.jsp"),
		@Result(name = "technicalInspectionReportDanWei", location = "/sms/training/qualification/qualifiedDeclare/technicalInspectionReportHasValueDanWei.jsp"),
		@Result(name = "technicalInspectionReportFuZeRen", location = "/sms/training/qualification/qualifiedDeclare/technicalInspectionReportHasValueFuZeRen.jsp"),
		@Result(name = "flightExperienceRecords", location = "/sms/training/qualification/qualifiedDeclare/flightExperienceRecords.jsp"),
		@Result(name = "flightExperienceRecordsAdd", location = "/sms/training/qualification/qualifiedDeclare/flightExperienceRecordsAdd.jsp"),
		@Result(name = "listnumber1_feixingyuan", location = "/sms/training/qualification/qualifiedDeclare/listnumber1_feixingyuan.jsp"),
		@Result(name = "listnumber1_jiaoyuan", location = "/sms/training/qualification/qualifiedDeclare/listnumber1_jiaoyuan.jsp"),
		@Result(name = "records", location = "/sms/training/qualification/qualifiedDeclare/trainingAndInspectionRecords.jsp"),
		@Result(name="pilotTimeRecord",location="/sms/training/qualification/qualifiedDeclare/pilotTimeRecord.jsp"),
		@Result(name="applyBase",location="/sms/training/qualification/qualifiedDeclare/applyBase.jsp"),
		@Result(name="examinerReport",location="/sms/training/qualification/qualifiedDeclare/examinerReport.jsp"),
		@Result(name="inspectorReport",location="/sms/training/qualification/qualifiedDeclare/inspectorReport.jsp"),
		@Result(name="reviewOfCACC",location="/sms/training/qualification/qualifiedDeclare/reviewOfCACC.jsp"),
		@Result(name="statement",location="/sms/training/qualification/qualifiedDeclare/statement.jsp"),
		@Result(name="recommendation",location="/sms/training/qualification/qualifiedDeclare/recommendation.jsp")		
		}) 
@ParentPackage("sinoframe-default")
public class QualifiedDeclareAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	private String  numbers = "1";
	private String methodname="learningResume";
	//每页显示条数
	private int numPerPage = 20;
	
	
	private List pilotList = new ArrayList(); 
	/**
	 * 
	 * @return
	 */
	public String list(){
		return "records";
	}
	public String listnumber(){
		return "listnumber"+numbers;
	}
	public String pilotLicense(){
		return "pilotLicense";
	}
	public String flightDataRecorder(){
		return "flightDataRecorder";
	}
	public String trainingRecordsAdd(){
		return "trainingRecordsAdd";
	}
	public String resume(){
		return methodname;
	}
	public String getAccessConditionsForPilots(){
		return "pilotList";
	}
	public String trainingAndInspectionRecords(){
		return "records";
	}
	public String flightExperienceRecords(){
		return "flightExperienceRecords";
	}
	public String flightExperienceRecordsAdd(){
		return "flightExperienceRecordsAdd";
	}
	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	public List getPilotList() {
		return pilotList;
	}
	public void setPilotList(List pilotList) {
		this.pilotList = pilotList;
	}
	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}
	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
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
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	
	
	
}
