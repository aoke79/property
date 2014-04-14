package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualInspectionTrain;
import com.sms.training.qualification.business.ITfQualInspectionTrainBS;
@Results( {
	@Result(name = "trainingRecords", location = "/sms/training/qualification/tfQualInspctTrain/trainingRecords.jsp"),
	@Result(name = "addPage", location = "/sms/training/qualification/tfQualInspctTrain/trainingRecordsAdd.jsp"),
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "json", type = "json")
	})
@ParentPackage("sinoframe-default")
public class TfQualInspectionTrainAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static final String MODULE_NAME="TfQualInspectionTrainAction";
	private TfQualInspectionTrain tfQualInspectionTrain;
	private List<TfQualInspectionTrain> tfQualInspectionTrainList;
	private ITfQualInspectionTrainBS tfQualInspectionTrainBS; 
	private String detailsId;
	private Message message;
	private String ids;
	private String userId;
	
	
	
	public TfQualInspectionTrainAction() {
	}
	
	
	public String showTrainingRecords(){
		try{
			tfQualInspectionTrainList= tfQualInspectionTrainBS.findTfQualInspectionTrainList(detailsId);
		} catch (Exception e) {
			tfQualInspectionTrainBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			e.printStackTrace();
		}
		return "trainingRecords";
	}
	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String toAddPage() {
		userId=this.getUser().getUserId();
		return "addPage";
	}

	/**
	 * 跳转至修改页面
	 * @return
	 */
	public String toEditPage() {
		tfQualInspectionTrain = this.tfQualInspectionTrainBS.findById(TfQualInspectionTrain.class,tfQualInspectionTrain.getTrainid());
		return "addPage";
	}
	
	public String saveOrUpdate(){
		String tid=tfQualInspectionTrain.getTrainid();
		try {
			if (tid != null	&& tid.trim().equals("")) {
				tfQualInspectionTrain.setTrainid(null);
			}
			tfQualInspectionTrainBS.saveOrUpdate(tfQualInspectionTrain);
			this.message = this.getSuccessMessage(tid.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),
					"trainingRecords", "closeCurrent","");
		} catch (Exception e) {
			tfQualInspectionTrainBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			this.setMessage(this.getFailMessage(tid.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return "json";
	}
	
	public String delete(){
		try {
			tfQualInspectionTrainBS.deleteById(TfQualInspectionTrain.class, tfQualInspectionTrain.getTrainid());
			this.message = this.getSuccessMessage("删除成功！", "trainingRecords","","");
		} catch (Exception e) {
			tfQualInspectionTrainBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String multiDelete(){
		try {
			tfQualInspectionTrainBS.deleteByIds(TfQualInspectionTrain.class, ids);
			this.message = this.getSuccessMessage("批量删除成功！", "trainingRecords","","");
		} catch (Exception e) {
			tfQualInspectionTrainBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	//======================== getters and  setters ==========================

	@JSON(serialize = false)
	public List<TfQualInspectionTrain> getTfQualInspectionTrainList() {
		return tfQualInspectionTrainList;
	}
	public void setTfQualInspectionTrainList(List<TfQualInspectionTrain> tfQualInspectionTrainList) {
		this.tfQualInspectionTrainList = tfQualInspectionTrainList;
	}

	@JSON(serialize = false)
	public ITfQualInspectionTrainBS getTfQualInspectionTrainBS() {
		return tfQualInspectionTrainBS;
	}
	public void setTfQualInspectionTrainBS(
			ITfQualInspectionTrainBS tfQualInspectionTrainBS) {
		this.tfQualInspectionTrainBS = tfQualInspectionTrainBS;
	}
	
	@JSON(serialize = false)
	public String getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public TfQualInspectionTrain getTfQualInspectionTrain() {
		return tfQualInspectionTrain;
	}
	public void setTfQualInspectionTrain(TfQualInspectionTrain tfQualInspectionTrain) {
		this.tfQualInspectionTrain = tfQualInspectionTrain;
	}

	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	@JSON(serialize = false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
