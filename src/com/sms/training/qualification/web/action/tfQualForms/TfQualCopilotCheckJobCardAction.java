package com.sms.training.qualification.web.action.tfQualForms;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualCopilotChecklist;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQualCopilotCheckJobCardBS;

@Results({
	@Result(name = "toCopilotCheckJobCard",  location = "/sms/training/qualification/qualityForms/copilotQualificationCheckJobCard.jsp"),
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "json", type = "json")
	})
public class TfQualCopilotCheckJobCardAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static String moduleName = "TfQualCopilotCheckJobCard";
	//副驾驶资格检查工作单实体类
	private TfQualCopilotChecklist tfQualCopilotChecklist;
	//副驾驶资格检查工作单业务接口
	private ITfQualCopilotCheckJobCardBS tfQualCopilotCheckJobCardBS;
	//申报人员信息
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//消息实体
	private Message message;
	//执照编号
	private String plcno;
	//ICAO英语等级
	private String qualification;
	
	public String toCopilotCheckJobCardPage() {
		try {
			tfQualDeclaraPilot = tfQualCopilotCheckJobCardBS.findById(TfQualDeclaraPilot.class,
					tfQualDeclaraPilot.getDetailsid());
			
			tfQualCopilotChecklist = tfQualCopilotCheckJobCardBS.getPageByDetailsid(tfQualDeclaraPilot.getDetailsid());
			String pilotId = tfQualDeclaraPilot.getCmPeople().getId();
			
			plcno = tfQualCopilotCheckJobCardBS.getPlcno(pilotId);
			
			qualification = tfQualCopilotCheckJobCardBS.getQualification(pilotId);
			
		} catch (Exception ex) {
			tfQualCopilotCheckJobCardBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			ex.printStackTrace();
		}
		
		return "toCopilotCheckJobCard";
	}
	
	public String saveOrUpdate() {
		String id = tfQualCopilotChecklist.getId();
		try {
			if(id == null || id.equals("")) {
				//如果id为空则保存
				tfQualCopilotChecklist.setId(null);
				tfQualCopilotCheckJobCardBS.save(tfQualCopilotChecklist);
			} else {
				//如果id不为空则更新
				tfQualCopilotCheckJobCardBS.update(tfQualCopilotChecklist, id);
			}
			this.message = getSuccessMessage(getText("addSuccess"), moduleName,"","");
//			message = this.getSuccessMessage(id.equals("") ? getText("addSuccess") : getText("updateSuccess"), moduleName, "", "");
		} catch (Exception ex) {
			tfQualCopilotCheckJobCardBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(id.trim().equals("") ? getText("addFail"): getText("updateFail")));
			ex.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//----------------------getters and setters ---------------------------------------------

	public TfQualCopilotChecklist getTfQualCopilotChecklist() {
		return tfQualCopilotChecklist;
	}

	public void setTfQualCopilotChecklist(
			TfQualCopilotChecklist tfQualCopilotChecklist) {
		this.tfQualCopilotChecklist = tfQualCopilotChecklist;
	}

	public ITfQualCopilotCheckJobCardBS getTfQualCopilotCheckJobCardBS() {
		return tfQualCopilotCheckJobCardBS;
	}

	public void setTfQualCopilotCheckJobCardBS(
			ITfQualCopilotCheckJobCardBS tfQualCopilotCheckJobCardBS) {
		this.tfQualCopilotCheckJobCardBS = tfQualCopilotCheckJobCardBS;
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

	public String getPlcno() {
		return plcno;
	}

	public void setPlcno(String plcno) {
		this.plcno = plcno;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
}
