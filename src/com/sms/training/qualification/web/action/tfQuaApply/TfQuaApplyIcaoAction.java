package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.EnExamQualification;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "qualPilotIcaoList", location = "/sms/training/qualification/quaApplyIcao/qualPilotIcaoList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") })
public class TfQuaApplyIcaoAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	public TfQuaApplyIcaoAction()
	{
		
	}
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	private String moduleName = "tfQuaApplyIcao";
	private List<EnExamQualification> qualificationFkList; 
	private List<EnExamQualification> qualificationFkListTemp;
	private List<TfQualBaseType> tfQualBaseTypelist;
	
	// 消息实体
	private Message message;
	
	public String toDeclareQuaIcaoList()
	{
		try
		{
			SysOrganization org=getUserOrg();
			String orgNameString=tfQuaApplyIcaoBS.getOrgName(org);
			qualificationFkList = tfQuaApplyIcaoBS.findFuKaoList("4", orgNameString);
			qualificationFkListTemp = tfQuaApplyIcaoBS.findFuKaoList("5", orgNameString);
			for (int i = 0,n = qualificationFkList.size(); i < n; i++)
			{
				qualificationFkList.get(i).setLastDate(this.get(qualificationFkList.get(i).getQualificationDate(),3));
			}
			if( qualificationFkListTemp!=null && qualificationFkListTemp.size()!=0 )
			{
				for (int i = 0,n = qualificationFkListTemp.size(); i < n; i++) {
					//只是用考试时间加载显示到期时间
					qualificationFkListTemp.get(i).setLastDate(this.get(qualificationFkListTemp.get(i).getQualificationDate(), 6));
					qualificationFkList.add(qualificationFkListTemp.get(i));
				}
			}
		}
		catch (Exception e)
		{
			this.message = this.getFailMessage("计算失败！");
			tfQuaApplyIcaoBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "qualPilotIcaoList";
	}
	
	private Date get(Date date, int n)
	{
		Calendar calender = Calendar.getInstance();
		if (date == null) {
			return null;
		}
		calender.setTime(date);
		calender.add(Calendar.YEAR, n);
		return calender.getTime();
	}
	
	
	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}
	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}
	@JSON(serialize = false)
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
 
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize = false)
	public List<EnExamQualification> getQualificationFkList() {
		return qualificationFkList;
	}

	public void setQualificationFkList(List<EnExamQualification> qualificationFkList) {
		this.qualificationFkList = qualificationFkList;
	}
	@JSON(serialize = false)
	public List<EnExamQualification> getQualificationFkListTemp() {
		return qualificationFkListTemp;
	}

	public void setQualificationFkListTemp(
			List<EnExamQualification> qualificationFkListTemp) {
		this.qualificationFkListTemp = qualificationFkListTemp;
	}
	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypelist() {
		return tfQualBaseTypelist;
	}

	public void setTfQualBaseTypelist(List<TfQualBaseType> tfQualBaseTypelist) {
		this.tfQualBaseTypelist = tfQualBaseTypelist;
	}
}
