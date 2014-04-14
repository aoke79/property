package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.business.ITfQualDeclarInfoBS;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;
import com.sms.training.qualification.business.ITfQualPilotLicenceBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "tfQualDeclarInfos", location = "/sms/training/qualification/quaApply/tfQualDeclarInfoList.jsp"),
		@Result(name = "uploadAccessory", location = "/sms/training/qualification/quaApply/quaUploadAccessor/tfQualUploadAccessory.jsp"),
		@Result(name = "toUpload1", location ="/sms/training/qualification/quaApply/quaUploadAccessor/quaApplyUpload.jsp"),
		@Result(name = "toUpload2", location = "/sms/training/qualification/quaApply/quaUploadAccessor/quaApplyGeneralUpload.jsp"),
//		@Result(name = "opinionPage_QUAL_UP", location ="/sms/training/qualification/quaApply/quaApplySubmit.jsp"),
//		@Result(name = "opinionPage_QUAL_CT", location = "/sms/training/qualification/quaApplyInspector/reportPage.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") })
public class TfQualDeclarInfoAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	//
	private List<TfQualDeclarInfo> tfQualDeclarInfoList;
	//
	private ITfQualDeclarInfoBS tfQualDeclarInfoBS;
	//
	private TfQualDeclarInfo tfQualDeclarInfo;
	//
	private Message message;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
	private String planInfoName;
	private String orgName;
	private String userId;
	private String formUrl;
	//
	private static final String MODULE_NAME="TfQualDeclarInfoAction";
	
	//申报信息ID
	private String declaredinfoid="";
	private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS;
	//定义一个list 用来存放是否已上传文件的标志
	private List<Integer> statusList=new ArrayList<Integer>();
	private ITfQualPilotLicenceBS tfQualPilotLicenceBS;
	private List<TfQualPilotLicence> tfQualPilotLicenceList =new ArrayList<TfQualPilotLicence>();
	
	public TfQualDeclarInfoAction() {
	}
	
	/**
	 * 显示申报信息列表
	 * @return
	 */
	public String showTfQualDeclarInfos(){
		tfQualDeclarInfoList = tfQualDeclarInfoBS.getTfQualDeclarInfosByCreator(getUser().getUserId(),"W");
		return "tfQualDeclarInfos";
	}
	
	/**
	 * 显示待上传附件页面
	 * @return
	 */
	public String showUploadAccessoryPage() {
		tfQualDeclarInfoList = tfQualDeclarInfoBS.getTfQualDeclarInfosByCreator(getUser().getUserId(),"W");
		return "uploadAccessory";
	}
	
	/**
	 * 上传升级流程、转机型流程附件的方法	
	 * @return
	 */
	public String  toUpload1() {
		try { 
			CmUser user = getUser();
			userId = user.getUserId();
			orgName = this.getUserOrg().getName();
			//获取此次申报信息
			if(declaredinfoid != null && !declaredinfoid.equals("")) {
				tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				//获取此次申报的人员信息
				if(tfQualDeclarInfo != null) {
					planInfoName = tfQualDeclarInfo.getDeclaredinfodesc();
				}
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				//判断是否已经上传文件
				for(int t = 0, len = tfQualDeclaraPilotList.size(); t < len; t++) {
					int docCount = tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
					statusList.add(docCount);
				}
			}
		}
		catch (RuntimeException e) {
			tfQualDeclarInfoBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			e.printStackTrace();
		}
		return "toUpload1";
	}
	
	/**
	 * 上传一般资格流程附件的方法
	 * @return
	 */
	public String toUpload2() {
		try { 
			CmUser user = getUser();
			userId = user.getUserId();
			List<TfQualPilotLicence> licences = null;
			//获取此次申报信息
			if(declaredinfoid != null && !declaredinfoid.equals("")) {
				tfQualDeclarInfo = tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				//获取此次申报的人员信息
				if(tfQualDeclarInfo != null) {
					planInfoName = tfQualDeclarInfo.getDeclaredinfodesc();
				}
				tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
				//获取上次训练日期和签注日期
				for(TfQualDeclaraPilot stay : tfQualDeclaraPilotList) {
					licences = tfQualPilotLicenceBS.getLicencesByIdAndStatus(stay.getCmPeople().getId(), stay.getTfQualBaseType().getTargetatrid().getId().trim());
					tfQualPilotLicenceList.add(licences == null || licences.size() < 1 ? new TfQualPilotLicence() : licences.get(0));
				}
			}
			formUrl = "tf-qua-apply/tf-qua-apply-general!quaApplyAdd.do";
		}
		catch (RuntimeException e) {
			tfQualDeclarInfoBS.getErrorLog().info(e.getMessage() + "#" + MODULE_NAME);
			e.printStackTrace();
		}
		return "toUpload2";
	}
	
	/**
	 * 单条删除
	 * @return
	 */
	public String delete(){
		try {
//			tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(tfQualDeclarInfo.getDeclaredinfoid());
//			for(TfQualDeclaraPilot detail : tfQualDeclaraPilotList){
//				tfQualDeclarInfoBS.deleteById(TfQualDeclaraPilot.class, detail.getDetailsid());
//			}
			tfQualDeclarInfo=tfQualDeclarInfoBS.findById(TfQualDeclarInfo.class, tfQualDeclarInfo.getDeclaredinfoid());
			//删除 当前申报信息及对应 申报人员明细 ，并修改 待申报人员状态
			tfQualDeclarInfoBS.restoreStatus(tfQualDeclarInfo);
			tfQualDeclarInfoBS.deleteById(TfQualDeclarInfo.class, tfQualDeclarInfo.getDeclaredinfoid());
			this.message = this.getSuccessMessage(getText("deleteSuccess"), MODULE_NAME,
					"forward", "tf-qua-apply/tf-qual-declar-info!showTfQualDeclarInfos.do" );
		} catch (Exception e) {
			//设置日志信息
			tfQualDeclarInfoBS.getErrorLog().info(e.getMessage()+"#"+MODULE_NAME);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//=====================  getters and setters ========================================
	@JSON(serialize = false)
	public List<TfQualDeclarInfo> getTfQualDeclarInfoList() {
		return tfQualDeclarInfoList;
	}
	public void setTfQualDeclarInfoList(List<TfQualDeclarInfo> tfQualDeclarInfoList) {
		this.tfQualDeclarInfoList = tfQualDeclarInfoList;
	}
	@JSON(serialize = false)
	public ITfQualDeclarInfoBS getTfQualDeclarInfoBS() {
		return tfQualDeclarInfoBS;
	}
	public void setTfQualDeclarInfoBS(ITfQualDeclarInfoBS tfQualDeclarInfoBS) {
		this.tfQualDeclarInfoBS = tfQualDeclarInfoBS;
	}
	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}
	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}
	@JSON(serialize = false)
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}
	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	@JSON(serialize = false)
	public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
		return tfQualDeclaraPilotList;
	}
	public void setTfQualDeclaraPilotList(
			List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
		this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
	}
	@JSON(serialize = false)
	public String getPlanInfoName() {
		return planInfoName;
	}
	public void setPlanInfoName(String planInfoName) {
		this.planInfoName = planInfoName;
	}
	@JSON(serialize = false)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@JSON(serialize = false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize = false)
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	@JSON(serialize = false)
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}
	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}
	@JSON(serialize = false)
	public ITfQualPilotDocumentsBS getTfQualPilotDocumentsBS() {
		return tfQualPilotDocumentsBS;
	}
	public void setTfQualPilotDocumentsBS(
			ITfQualPilotDocumentsBS tfQualPilotDocumentsBS) {
		this.tfQualPilotDocumentsBS = tfQualPilotDocumentsBS;
	}
	@JSON(serialize = false)
	public List<Integer> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}
	@JSON(serialize = false)
	public ITfQualPilotLicenceBS getTfQualPilotLicenceBS() {
		return tfQualPilotLicenceBS;
	}
	public void setTfQualPilotLicenceBS(ITfQualPilotLicenceBS tfQualPilotLicenceBS) {
		this.tfQualPilotLicenceBS = tfQualPilotLicenceBS;
	}
	@JSON(serialize = false)
	public List<TfQualPilotLicence> getTfQualPilotLicenceList() {
		return tfQualPilotLicenceList;
	}
	public void setTfQualPilotLicenceList(
			List<TfQualPilotLicence> tfQualPilotLicenceList) {
		this.tfQualPilotLicenceList = tfQualPilotLicenceList;
	}
	
}
