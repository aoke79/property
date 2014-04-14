package com.sms.training.qualification.web.action.tfQuaApply;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualRouteReport;
import com.sms.training.qualification.business.ITfQualRouteReportBS;

@Results({
	@Result(name = "toTfQualRouteReport",  location = "/sms/training/qualification/qualDeclaration/toTfQualRouteReport.jsp"),	
	@Result(name = "routeReportPage",  location = "/sms/training/qualification/qualDeclaration/tfQualRouteReport.jsp"),	
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "json", type = "json") 
	})
public class TfQualRouteReportAction extends BaseAction{
	
	
	private static final long serialVersionUID = 1L;
	private static String moduleName = "TfQualRouteReport";
	private TfQualRouteReport tfQualRouteReport;
	private ITfQualRouteReportBS tfQualRouteReportBS;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private String pageName;
	private Message message;
	private int sec;
	private int house;

public String toRouteReportPage(){			
	try {
		tfQualDeclaraPilot = tfQualRouteReportBS.findById(TfQualDeclaraPilot.class,
						tfQualDeclaraPilot.getDetailsid());
		tfQualRouteReport=tfQualRouteReportBS.getPageByDetailsid(tfQualDeclaraPilot.getDetailsid());
		if(tfQualRouteReport!=null){
			house=tfQualRouteReport.getFlyTime()/60;
			sec=tfQualRouteReport.getFlyTime()%60;
		}
	}catch (Exception e) {
		tfQualRouteReportBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		e.printStackTrace();
	}
	return "toTfQualRouteReport";
}


public String routeReportPage(){
	tfQualDeclaraPilot=tfQualRouteReportBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
	if(tfQualDeclaraPilot==null){		
		this.message=this.getFailMessage("此申报人员明细记录不存在！");
		throw new RuntimeException("tfQualDeclaraPilot.detailsid is null !");
	}
	pageName="routeReportPage";

	return pageName;
	
}


public String saveOrUpdateRouteReport(){
	String infoId=tfQualRouteReport.getRouteId();
	try{
		if(infoId==null || infoId.trim().equals("")){
			/**如果id为空则保存*/
			tfQualRouteReport.setRouteId(null);
			tfQualRouteReportBS.save(tfQualRouteReport);			
		}else{		
			if(sec<60){  //测试时再定
			tfQualRouteReport.setFlyTime(house*60+sec);	
			}
			/**如果id不为空则更新*/
			tfQualRouteReportBS.update(tfQualRouteReport, infoId);
		}
		message=this.getSuccessMessage(infoId.equals("")?getText("addSuccess"):getText("updateSuccess"), "haolimingwrite", "closeCurrent","");
	}catch(Exception e){
		tfQualRouteReportBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
		e.printStackTrace();		
	}	
	return SUCCESS;  //张会粉用，需要改动的跟我商量
}



//----------------------getters and setters ---------------------------------------------
public String getPageName() {
	return pageName;
}
public ITfQualRouteReportBS getTfQualRouteReportBS() {
	return tfQualRouteReportBS;
}
public void setTfQualRouteReportBS(ITfQualRouteReportBS tfQualRouteReportBS) {
	this.tfQualRouteReportBS = tfQualRouteReportBS;
}
public void setPageName(String pageName) {
	this.pageName = pageName;
}
public TfQualRouteReport getTfQualRouteReport() {
	return tfQualRouteReport;
}

public void setTfQualRouteReport(TfQualRouteReport tfQualRouteReport) {
	this.tfQualRouteReport = tfQualRouteReport;
}

public TfQualDeclaraPilot getTfQualDeclaraPilot() {
	return tfQualDeclaraPilot;
}

public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
	this.tfQualDeclaraPilot = tfQualDeclaraPilot;
}
public Message getMessage() {
	return message;
}
public void setMessage(Message message) {
	this.message = message;
}

public int getSec() {
	return sec;
}
public void setSec(int sec) {
	this.sec = sec;
}
public int getHouse() {
	return house;
}


public void setHouse(int house) {
	this.house = house;
}
}
