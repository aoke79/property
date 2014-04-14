package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotFlyrecordbook;
import com.sms.training.qualification.business.ITfQualPilotFlyrecordbookBS;

@Results({
		@Result(name = "flightExpRecords", location = "/sms/training/qualification/flightExperience/flightExperienceRecords.jsp"),
		@Result(name = "addExpRecords", location = "/sms/training/qualification/flightExperience/flightExperienceRecordsAdd.jsp"),
		@Result(name = "editExpRecords", location = "/sms/training/qualification/flightExperience/flightExperienceRecordsAdd.jsp"),
		@Result(name = "detailExpRecords", location = "/sms/training/qualification/flightExperience/flightExperienceRecordsDetail.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") 
		})
@ParentPackage("sinoframe-default")
public class TfQualFlightExperienceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final String moduleName = "TfFlightExperienceAction";
	// 申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//多个 tfQualPilotFlyrecordbook 的id
	public String ids;
	// 消息实体
	private Message message;
	// 飞行经历记录本 实体
	public TfQualPilotFlyrecordbook tfQualPilotFlyrecordbook;
	// 飞行经历记录list
	public List<TfQualPilotFlyrecordbook> recordList;
	// 飞行经历记录本 service
	public ITfQualPilotFlyrecordbookBS tfQualPilotFlyrecordbookBS;
	// 查询条件
	private HashMap<String, String> query=new HashMap<String, String>();
	//出发时刻
	private String depHour;
	private String depMinute;
	//到达时刻
	private String arrHour;
	private String arrMinute;
	//
	public TfQualFlightExperienceAction() {
	}

	/**
	 * 跳转至飞行经历记录list页面
	 * 
	 * @return
	 */
	public String listRecords() {
		try {
			tfQualDeclaraPilot=tfQualPilotFlyrecordbookBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid() );
			recordList = tfQualPilotFlyrecordbookBS.listRecordsByDetailId(
					tfQualDeclaraPilot.getDetailsid(), this.getSysPageInfo(), this.getSysOrderByInfo(),
					Util.decodeQuery(query));
		} catch (Exception e) {
			tfQualPilotFlyrecordbookBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "flightExpRecords";
	}

	/**
	 * 跳转至飞行经历记录添加页面
	 * 
	 * @return
	 */
	public String toAddPage() {
		tfQualDeclaraPilot=tfQualPilotFlyrecordbookBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid() );
		return "addExpRecords";
	}

	/**
	 * 跳转至飞行经历记录修改页面
	 * 
	 * @return
	 */
	public String toEditPage() {
		tfQualPilotFlyrecordbook = this.tfQualPilotFlyrecordbookBS.findById(
				TfQualPilotFlyrecordbook.class,
				tfQualPilotFlyrecordbook.getRecordId());
		tfQualDeclaraPilot=tfQualPilotFlyrecordbook.getTfQualDeclaraPilot();
		timeToShow();
		return "editExpRecords";
	}
	//时间格式转换为显示格式
	private void timeToShow(){
		String dep=tfQualPilotFlyrecordbook.getDep();
		String arr=tfQualPilotFlyrecordbook.getArr();
		if(dep!=null){
			this.depHour=dep.substring(0, 2);
			this.depMinute=tfQualPilotFlyrecordbook.getDep().substring(3, 5);
		}
		if(arr!=null){
			this.arrHour=arr.substring(0, 2);
			this.arrMinute=arr.substring(3, 5);
		}
	}
	//时间格式转换为存储格式
	private void timeToSave(){
		tfQualPilotFlyrecordbook.setDep(padding(depHour)+":"+padding(depMinute));
		tfQualPilotFlyrecordbook.setArr(padding(arrHour)+":"+padding(arrMinute));
	}
	//时间格式填充
	private String padding(String time) {
		return time.trim().length() < 2 ? "0" + time.trim() : time.trim();
	}
	/**
	 * 跳转至飞行经历记录详细页面
	 * 
	 * @return
	 */
	public String toDetailPage() {
		tfQualPilotFlyrecordbook = this.tfQualPilotFlyrecordbookBS.findById(
				TfQualPilotFlyrecordbook.class,
				tfQualPilotFlyrecordbook.getRecordId());
		return "detailExpRecords";
	}

	/**
	 * 保存或更新飞行记录
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		String rid=tfQualPilotFlyrecordbook.getRecordId();
		try {
			if (rid != null	&& rid.trim().equals("")) {
				tfQualPilotFlyrecordbook.setRecordId(null);
			}
//			this.timeToSave();
			tfQualPilotFlyrecordbook.setCreater(this.getUser().getUserId());
			tfQualPilotFlyrecordbookBS.saveOrUpdate(tfQualPilotFlyrecordbook);
				this.message = this.getSuccessMessage(rid.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),
						moduleName, "",
						"tf-qua-apply/tf-qual-flight-experience!listRecords.do?tfQualDeclaraPilot.detailsid=" + tfQualPilotFlyrecordbook.getTfQualDeclaraPilot().getDetailsid());
		} catch (Exception e) {
			tfQualPilotFlyrecordbookBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(rid.trim().equals("") ? getText("addFail")
					: getText("updateFail")));
			e.printStackTrace();
		}
		return "json";
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDelete() {
		try {
			tfQualPilotFlyrecordbookBS.deleteByIds(TfQualPilotFlyrecordbook.class, ids);
			this.message = this.getSuccessMessage("批量删除成功！", moduleName,"forward",
					"tf-qua-apply/tf-qual-flight-experience!listRecords.do?tfQualDeclaraPilot.detailsid=" + tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualPilotFlyrecordbookBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// ==================================================================

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
	public TfQualPilotFlyrecordbook getTfQualPilotFlyrecordbook() {
		return tfQualPilotFlyrecordbook;
	}

	public void setTfQualPilotFlyrecordbook(
			TfQualPilotFlyrecordbook tfQualPilotFlyrecordbook) {
		this.tfQualPilotFlyrecordbook = tfQualPilotFlyrecordbook;
	}
	@JSON(serialize = false)
	public List<TfQualPilotFlyrecordbook> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<TfQualPilotFlyrecordbook> recordList) {
		this.recordList = recordList;
	}
	@JSON(serialize = false)
	public ITfQualPilotFlyrecordbookBS getTfQualPilotFlyrecordbookBS() {
		return tfQualPilotFlyrecordbookBS;
	}

	public void setTfQualPilotFlyrecordbookBS(
			ITfQualPilotFlyrecordbookBS tfQualPilotFlyrecordbookBS) {
		this.tfQualPilotFlyrecordbookBS = tfQualPilotFlyrecordbookBS;
	}
	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	public String getDepHour() {
		return depHour;
	}

	public void setDepHour(String depHour) {
		this.depHour = depHour;
	}

	public String getDepMinute() {
		return depMinute;
	}

	public void setDepMinute(String depMinute) {
		this.depMinute = depMinute;
	}

	public String getArrHour() {
		return arrHour;
	}

	public void setArrHour(String arrHour) {
		this.arrHour = arrHour;
	}

	public String getArrMinute() {
		return arrMinute;
	}

	public void setArrMinute(String arrMinute) {
		this.arrMinute = arrMinute;
	}
	

}
