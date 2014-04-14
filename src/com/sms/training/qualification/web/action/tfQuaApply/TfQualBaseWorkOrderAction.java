package com.sms.training.qualification.web.action.tfQuaApply;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.Message;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.KSGZDFormData;
import com.sms.training.qualification.bean.QuaCmUser;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualBaseWorkOrderItem;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualInspectionReport;
import com.sms.training.qualification.bean.TfQualPilotAppinformation;
import com.sms.training.qualification.business.ITfQualBaseWorkOrderBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualInspectionReportBS;
import com.sms.training.qualification.business.ITfQualPilotLicenseRateAppBS;

@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "examWorkOrder", location = "/sms/training/qualification/examWorkOrder/examWorkOrder.jsp"),
	@Result(name = "basicInfo", location = "/sms/training/qualification/examWorkOrder/examWorkBasicInfo.jsp"),
	@Result(name = "examItems", location = "/sms/training/qualification/examWorkOrder/examWorkOrderItems.jsp"),
	@Result(name = "assess", location = "/sms/training/qualification/examWorkOrder/examWorkOrderAssess.jsp"),
	@Result(name = "signature", type = "json")
})

/**
 * 航线运输驾驶员执照/型别等级实践考核工作单(飞机)Action
 * @author licumn
 *
 */
public class TfQualBaseWorkOrderAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	//申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//人员基本信息
	private CmPeople cmPeople;
	//考试工作单service
	private ITfQualBaseWorkOrderBS tfQualBaseWorkOrderBS; 
	//考试工作单 entity
	private TfQualBaseWorkOrder tfQualBaseWorkOrder;
	//考试工作单 考试项目
	private TfQualBaseWorkOrderItem orderItem;
	//消息实体
	private Message message;
	//模块名称
	private static final String moduleName="TfQualBaseWorkOrderAction";
	// 驾驶员执照编号 
	private String licenceNo;
	//考试工作单表单数据
	private KSGZDFormData form;
	//考试员签名
	private TfQualBaseSignature examerSign;
	//考试员签名时间
	private String signDate;
	//检查员签名
	private TfQualBaseSignature inspctSign;
	//检查员签名时间
	private String inspctSignDate;
	//签名的参数
	private String hrid;
	// 申报人员明细service
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	//飞行员技术检查报告service
	private ITfQualInspectionReportBS tfQualInspectionReportBS;
	//飞行员技术检查报告（本场训练）
	private  TfQualInspectionReport tfQualInspectionReport;
	//执照等级申请表 service
	private ITfQualPilotLicenseRateAppBS tfQualPilotLicenseRateAppBS; 
	//民用航空器驾驶员执照和等级申请表
	private TfQualPilotAppinformation tfQualPilotAppinformation;
	
	//无参构造函数
	public TfQualBaseWorkOrderAction() {
	}
	
	
	/**
	 * （点击填写）进入考试工作单页面
	 * @return
	 */
	public String toExamWorkOrderPage(){
		try{
			tfQualDeclaraPilot=tfQualBaseWorkOrderBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot != null){
				//获取人员基本信息
				cmPeople=tfQualDeclaraPilot.getCmPeople();
				// 获取驾驶员执照编号 
				licenceNo=tfQualBaseWorkOrderBS.findLicenceNo(tfQualDeclaraPilot);
			}
		} catch (Exception e) {
			tfQualBaseWorkOrderBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "examWorkOrder";
	}
	
	/**
	 * 打开基本信息录入页面（tab_1）
	 * @return
	 */
	
	

	public String basicInfo(){
		try{
			tfQualDeclaraPilot=tfQualBaseWorkOrderBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				tfQualBaseWorkOrder=tfQualBaseWorkOrderBS.findUniqueBySingleQuery("TfQualBaseWorkOrder", "tfQualDeclaraPilot.detailsid", tfQualDeclaraPilot.getDetailsid());
			}
		} catch (Exception e) {
			tfQualBaseWorkOrderBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "basicInfo";
	}
	
	/**
	 * 打开“考试项目”页面（tab_2）
	 * @return
	 */
	public String showItems(){
		try{
			tfQualDeclaraPilot=tfQualBaseWorkOrderBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				tfQualBaseWorkOrder=tfQualBaseWorkOrderBS.findUniqueBySingleQuery("TfQualBaseWorkOrder", "tfQualDeclaraPilot.detailsid", tfQualDeclaraPilot.getDetailsid());
			}
			if(tfQualBaseWorkOrder != null){
				form =new KSGZDFormData();
				//填充对象form,将数据库中数据查询出来，填充进form中，以供页面显示
				tfQualBaseWorkOrderBS.fillForm(tfQualBaseWorkOrder,form);////////////
			}
		} catch (Exception e) {
			tfQualBaseWorkOrderBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "examItems";
	}
	
	/**
	 * 打开“综合评估”页面 （tab_3）
	 * @return
	 */
	public String toAssessPage(){
		try {
			tfQualDeclaraPilot=tfQualBaseWorkOrderBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				tfQualBaseWorkOrder=tfQualBaseWorkOrderBS.findUniqueBySingleQuery("TfQualBaseWorkOrder", "tfQualDeclaraPilot.detailsid", tfQualDeclaraPilot.getDetailsid());
			}
			if(tfQualBaseWorkOrder == null){
				return "assess";
			}
			if(tfQualBaseWorkOrder.getKsyDate()!=null){
				//获取签名
				getSignatureAndDate();
				//获取签名日期
				signDate=DateTool.formatDate(tfQualBaseWorkOrder.getKsyDate(), DateTool.DEFAULT_DATE_FORMAT);
			}
			if(tfQualBaseWorkOrder.getJcyDate()!=null){
				//获取签名
				getInspctSignatureAndDate();
				//获取签名日期
				inspctSignDate=DateTool.formatDate(tfQualBaseWorkOrder.getJcyDate(), DateTool.DEFAULT_DATE_FORMAT);
			}
			form =new KSGZDFormData();
			//填充对象form,将数据库中数据查询出来，填充进form中，以供页面显示
			tfQualBaseWorkOrderBS.fillForm(tfQualBaseWorkOrder,form);////////////
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "assess";
	}
		
	/**
	 * 保存或更新 考试工作单
	 * @return
	 */
	public String saveOrUpdateOrder(){
		String id=tfQualBaseWorkOrder.getId();
		try {
			if(signDate!=null && !signDate.equals("")){
				tfQualBaseWorkOrder.setKsyDate(DateTool.parse(signDate, DateTool.DEFAULT_DATE_FORMAT));
			}
			if(inspctSignDate!=null && !inspctSignDate.equals("")){
				tfQualBaseWorkOrder.setJcyDate(DateTool.parse(inspctSignDate, DateTool.DEFAULT_DATE_FORMAT));
			}
			if(id == null || id.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualBaseWorkOrder.setId(null);
				tfQualBaseWorkOrder.setModifier(getUser().getUserId());
				tfQualBaseWorkOrderBS.save(tfQualBaseWorkOrder);
			}else{
				//如果申请信息id不“空”，则更新
				tfQualBaseWorkOrder.setModifier(getUser().getUserId());
				tfQualBaseWorkOrderBS.update(tfQualBaseWorkOrder, id);
			}
			if(form != null){
				//将对象form中封装的数据保存
				tfQualBaseWorkOrderBS.saveItems(tfQualBaseWorkOrder, form);
			}
			message=getSuccessMessage(id.trim().equals("")?getText("addSuccess"):getText("updateSuccess"), "liusiyuanwrite", "","");
		} catch (Exception e) {
			tfQualBaseWorkOrderBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(id.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return "signature";
	}
	
	/**
	 * 获取系统用户 签名(教员)
	 * 
	 * @return
	 */
	public String getSignatureAndDate() {
		try{
			QuaCmUser user = tfQualBaseWorkOrderBS.findById(QuaCmUser.class, this.getUser().getUserId());
			examerSign = tfQualBaseWorkOrderBS.findUniqueBySingleQuery("TfQualBaseSignature",
							"cmuser.userId", user.getUserId());
			signDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}catch (Exception e) {
			tfQualBaseWorkOrderBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "signature";
	}
	/**
	 * 获取系统用户 签名（检查员）
	 * 
	 * @return
	 */
	public String getInspctSignatureAndDate(){
		try{
			QuaCmUser user = tfQualBaseWorkOrderBS.findById(QuaCmUser.class, this.getUser().getUserId());
			inspctSign = tfQualBaseWorkOrderBS.findUniqueBySingleQuery("TfQualBaseSignature",
							"cmuser.userId", user.getUserId());
			inspctSignDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}catch (Exception e) {
			tfQualBaseWorkOrderBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "signature";
	}
	//=====================  getters and setters ================================

	@JSON(serialize = false)
	public ITfQualBaseWorkOrderBS getTfQualBaseWorkOrderBS() {
		return tfQualBaseWorkOrderBS;
	}
	public void setTfQualBaseWorkOrderBS(
			ITfQualBaseWorkOrderBS tfQualBaseWorkOrderBS) {
		this.tfQualBaseWorkOrderBS = tfQualBaseWorkOrderBS;
	}

	@JSON(serialize = false)
	public TfQualBaseWorkOrder getTfQualBaseWorkOrder() {
		return tfQualBaseWorkOrder;
	}
	public void setTfQualBaseWorkOrder(TfQualBaseWorkOrder tfQualBaseWorkOrder) {
		this.tfQualBaseWorkOrder = tfQualBaseWorkOrder;
	}

	@JSON(serialize = false)
	public TfQualBaseWorkOrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(TfQualBaseWorkOrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@JSON(serialize = false)
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	@JSON(serialize = false)
	public KSGZDFormData getForm() {
		return form;
	}
	public void setForm(KSGZDFormData form) {
		this.form = form;
	}
	
	@JSON(serialize = false)
	public CmPeople getCmPeople() {
		return cmPeople;
	}
	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}
	
	public TfQualBaseSignature getExamerSign() {
		return examerSign;
	}
	public void setExamerSign(TfQualBaseSignature examerSign) {
		this.examerSign = examerSign;
	}

	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	
	public TfQualBaseSignature getInspctSign() {
		return inspctSign;
	}
	public void setInspctSign(TfQualBaseSignature inspctSign) {
		this.inspctSign = inspctSign;
	}

	public String getInspctSignDate() {
		return inspctSignDate;
	}
	public void setInspctSignDate(String inspctSignDate) {
		this.inspctSignDate = inspctSignDate;
	}
	@JSON(serialize = false)
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	@JSON(serialize = false)
	public ITfQualInspectionReportBS getTfQualInspectionReportBS() {
		return tfQualInspectionReportBS;
	}
	public void setTfQualInspectionReportBS(
			ITfQualInspectionReportBS tfQualInspectionReportBS) {
		this.tfQualInspectionReportBS = tfQualInspectionReportBS;
	}
	@JSON(serialize = false)
	public TfQualInspectionReport getTfQualInspectionReport() {
		return tfQualInspectionReport;
	}
	public void setTfQualInspectionReport(
			TfQualInspectionReport tfQualInspectionReport) {
		this.tfQualInspectionReport = tfQualInspectionReport;
	}
	@JSON(serialize = false)
	public ITfQualPilotLicenseRateAppBS getTfQualPilotLicenseRateAppBS() {
		return tfQualPilotLicenseRateAppBS;
	}
	public void setTfQualPilotLicenseRateAppBS(
			ITfQualPilotLicenseRateAppBS tfQualPilotLicenseRateAppBS) {
		this.tfQualPilotLicenseRateAppBS = tfQualPilotLicenseRateAppBS;
	}
	@JSON(serialize = false)
	public TfQualPilotAppinformation getTfQualPilotAppinformation() {
		return tfQualPilotAppinformation;
	}
	public void setTfQualPilotAppinformation(
			TfQualPilotAppinformation tfQualPilotAppinformation) {
		this.tfQualPilotAppinformation = tfQualPilotAppinformation;
	}
	
}
