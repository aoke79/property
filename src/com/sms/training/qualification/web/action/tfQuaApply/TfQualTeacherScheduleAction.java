package com.sms.training.qualification.web.action.tfQuaApply;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.QuaCmUser;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualTeacherSchedule;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualTeacherScheduleBS;
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "teacherSchedulePage", location = "/sms/training/qualification/qualityForms/airlineTransport.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") })
public class TfQualTeacherScheduleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final String MODULE_NAME="TfQualTeacherScheduleAction";
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private Message message;
	private TfQualTeacherSchedule tfQualTeacherSchedule;
	private ITfQualTeacherScheduleBS tfQualTeacherScheduleBS;
	// 申报人员明细service
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private TfQualBaseSignature signature;   //签名图片
	
	public String toTeacherSchedulePage(){
		try{
			String detailsId=tfQualDeclaraPilot.getDetailsid();
			if( detailsId!=null && !detailsId.equals("")){
				tfQualDeclaraPilot=tfQualTeacherScheduleBS.findById(TfQualDeclaraPilot.class, detailsId);
				if(tfQualTeacherScheduleBS.findByPilotId(tfQualDeclaraPilot.getDetailsid()).size() >0){
					tfQualTeacherSchedule = tfQualTeacherScheduleBS.findByPilotId(tfQualDeclaraPilot.getDetailsid()).get(0);
				}else{
					tfQualTeacherSchedule=null;
				}
			}
		}catch(Exception e){
			tfQualTeacherScheduleBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			e.printStackTrace();
		}
		return "teacherSchedulePage";
	}
	
	public String saveOrUpdate(){
		String id = tfQualTeacherSchedule.getScheduleid();
		try {
			if(id!=null && id.equals("")){
				tfQualTeacherSchedule.setScheduleid(null);	
			}
			tfQualTeacherScheduleBS.saveOrUpdate(tfQualTeacherSchedule);
			this.message=getSuccessMessage(getText("addSuccess"), "scheduleReport","","");
		} catch (Exception e) {
			this.message=getFailMessage(getText("addFail"));
			tfQualTeacherScheduleBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获取教员/检查员 签名  (技术检查报告)
	 * 
	 * @return
	 */

	public String getSignatureAndDate2() {
		try{
			QuaCmUser user = tfQualDeclaraPilotBS.findById(QuaCmUser.class, this.getUser().getUserId());
			signature = tfQualDeclaraPilotBS.findUniqueBySingleQuery("TfQualBaseSignature","cmuser.userId", user.getUserId());
		}catch (Exception e) {
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
		}
		return "json";
	}
	
	
	
	//================================  getters and setters ===================================
	
	@JSON(serialize = false)
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

	@JSON(serialize = false)
	public TfQualTeacherSchedule getTfQualTeacherSchedule() {
		return tfQualTeacherSchedule;
	}
	public void setTfQualTeacherSchedule(TfQualTeacherSchedule tfQualTeacherSchedule) {
		this.tfQualTeacherSchedule = tfQualTeacherSchedule;
	}
	
	@JSON(serialize = false)
	public ITfQualTeacherScheduleBS getTfQualTeacherScheduleBS() {
		return tfQualTeacherScheduleBS;
	}
	public void setTfQualTeacherScheduleBS(
			ITfQualTeacherScheduleBS tfQualTeacherScheduleBS) {
		this.tfQualTeacherScheduleBS = tfQualTeacherScheduleBS;
	}

}
