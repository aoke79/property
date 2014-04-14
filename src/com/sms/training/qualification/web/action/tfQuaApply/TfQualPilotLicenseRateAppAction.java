package com.sms.training.qualification.web.action.tfQuaApply;


import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.ITfQualPilotLicenseRateAppBS;

@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "pilotLicense", location = "/sms/training/qualification/pilotLicenseRateApp/pilotLicense.jsp"),
	
	@Result(name = "learningResume", location = "/sms/training/qualification/pilotLicenseRateApp/learningResume.jsp"),
	@Result(name = "workResume", location = "/sms/training/qualification/pilotLicenseRateApp/workResume.jsp"),
	@Result(name = "requestInfo", location = "/sms/training/qualification/pilotLicenseRateApp/requestInfo.jsp"),

	@Result(name = "pilotTimeRecord1", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeRecord.jsp"),
	@Result(name = "toPilotTimeAddPage1", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeAdd.jsp"),
	@Result(name = "toPilotTimeEditPage1", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeAdd.jsp"),
	@Result(name = "toPilotTimeDetailPage1", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeDetail.jsp"),

	@Result(name = "pilotTimeRecord2", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeRecord2.jsp"),
	@Result(name = "toPilotTimeAddPage2", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeAdd2.jsp"),
	@Result(name = "toPilotTimeEditPage2", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeAdd2.jsp"),
	@Result(name = "toPilotTimeDetailPage2", location = "/sms/training/qualification/pilotLicenseRateApp/pilotTimeDetail2.jsp"),
	
	@Result(name = "applyBase", location = "/sms/training/qualification/pilotLicenseRateApp/applyBase.jsp"),
	@Result(name = "statement", location = "/sms/training/qualification/pilotLicenseRateApp/statement.jsp"),
	@Result(name = "recommendation", location = "/sms/training/qualification/pilotLicenseRateApp/recommendation.jsp"),
	@Result(name = "examinerReport", location = "/sms/training/qualification/pilotLicenseRateApp/examinerReport.jsp"),
	@Result(name = "inspectorReport", location = "/sms/training/qualification/pilotLicenseRateApp/inspectorReport.jsp"),
	@Result(name = "reviewOfCACC", location = "/sms/training/qualification/pilotLicenseRateApp/reviewOfCACC.jsp"),
	
	@Result(name = "educationAdd", location = "/sms/training/qualification/pilotLicenseRateApp/eduRecordAdd.jsp"),
	@Result(name = "educationEdit", location = "/sms/training/qualification/pilotLicenseRateApp/eduRecordAdd.jsp"),
	@Result(name = "expAdd", location = "/sms/training/qualification/pilotLicenseRateApp/experienceAdd.jsp"),
	@Result(name = "json", type="json"),
	@Result(name = "expEdit", location = "/sms/training/qualification/pilotLicenseRateApp/experienceAdd.jsp")

})
/**
 * 飞行员执照、等级申请表 Action
 * @author licumn
 *
 */
public class TfQualPilotLicenseRateAppAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//模块名称
	private static String moduleName = "TfQualPilotLicenseRateApp";
	// 消息实体
	private Message message;
	// 检索传入后台的数据
	private HashMap<String, String> query = new HashMap<String, String>();
	// 多个id，用于批量删除
	private String ids;
	//jsp页面名称
	private String pageName;
	//申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//民用航空器驾驶员执照和等级申请表
	private TfQualPilotAppinformation tfQualPilotAppinformation;
	//飞行员学习简历 
	private TfQualPilotEducation tfQualPilotEducation ;
	//飞行员学习简历   list
	private List<TfQualPilotEducation> educationList;
	//飞行员工作简历
	private TfQualPilotExperience tfQualPilotExperience;
	//飞行员工作简历   list
	private List<TfQualPilotExperience> experienceList;
	//飞行时间记录 1
	private TfQualPilotAppinforOne tfQualPilotAppinforOne;
	//飞行时间记录 1 list
	private List<TfQualPilotAppinforOne> tfQualPilotAppinforOneList;
	//飞行时间记录 2
	private TfQualPilotAppinforTwo tfQualPilotAppinforTwo;
	//飞行时间记录 2 list
	private List<TfQualPilotAppinforTwo> tfQualPilotAppinforTwoList;
	//执照等级申请表 service
	private ITfQualPilotLicenseRateAppBS tfQualPilotLicenseRateAppBS; 
	//机型list
	private List<BaseAirPlanType> baseAirPlanTypeList;
	private String[] info;
	//开始时间
	private String startDate;
	//结束时间
	private String endDate;
	
	/** 无参构造函数 */
	public TfQualPilotLicenseRateAppAction() {
	}

	/**
	 * 执照和等级申请表 主页
	 * @return
	 */
	public String pilotLicense(){
		String flush=(String) this.getServletRequest().getSession().getAttribute("flush");
		String index=(String) this.getServletRequest().getSession().getAttribute("tabIndex");
		//适时的清除session，并确保页面跳转无误
		if(flush!=null && !flush.equals("")){
			this.getServletRequest().getSession().removeAttribute("flush");
		}else if(index!=null && !index.equals("")){
			this.getServletRequest().getSession().removeAttribute("tabIndex");
		}
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		return "pilotLicense";
	}
	
	/**
	 * 根据pageName打开相应的选项卡，进入对应页面
	 * @return
	 */
	public String gotoTab(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		if(tfQualDeclaraPilot==null){
			this.message=this.getFailMessage("此申报人员明细记录不存在！");
			throw new RuntimeException("tfQualDeclaraPilot.detailsid is null !");
		}
		if("learningResume".equals(pageName)){
			//学习简历
			educationList=tfQualPilotLicenseRateAppBS.findEducationList(tfQualDeclaraPilot.getCmPeople().getId());
		} else if ("workResume".equals(pageName)) {
			//工作经历
			experienceList=tfQualPilotLicenseRateAppBS.findExperienceList(tfQualDeclaraPilot.getCmPeople().getId());
		} else if("pilotTimeRecord1".equals(pageName)){
			//驾驶员飞行时间记录 1
			tfQualPilotAppinforOneList=tfQualPilotLicenseRateAppBS.findPilotTimeList1(tfQualDeclaraPilot.getDetailsid());
		} else if("pilotTimeRecord2".equals(pageName)){
			//驾驶员飞行时间记录 2
			tfQualPilotAppinforTwoList=tfQualPilotLicenseRateAppBS.findPilotTimeList2(tfQualDeclaraPilot.getDetailsid());
		} else /*TODO if("requestInfo".equals(pageName)||"applyBase".equals(pageName)||"examinerReport".equals(pageName))*/{
			//其他页面
			tfQualPilotAppinformation=tfQualPilotLicenseRateAppBS.findAppInfo(tfQualDeclaraPilot.getDetailsid());

		}
		return pageName;
	}

	//------------------------驾驶员飞行时间记录 -01 -------------------------------------
	
	public String[] getInfo() {
		return info;
	}

	public void setInfo(String[] info) {
		this.info = info;
	}

	public String toPilotTimeAddPage1(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		//获取机型列表
		baseAirPlanTypeList=tfQualPilotLicenseRateAppBS.findAirplanList();
		return "toPilotTimeAddPage1";
	}
	
	public String toPilotTimeEditPage1(){
		tfQualPilotAppinforOne=tfQualPilotLicenseRateAppBS.findById(TfQualPilotAppinforOne.class, tfQualPilotAppinforOne.getId());
		tfQualDeclaraPilot=	tfQualPilotAppinforOne.getTfQualDeclaraPilot();
		//获取机型列表
		baseAirPlanTypeList=tfQualPilotLicenseRateAppBS.findAirplanList();
		startDate = DateTool.formatDate(tfQualPilotAppinforOne.getStartdt(), "yyyy-MM");
		endDate = DateTool.formatDate(tfQualPilotAppinforOne.getEnddt(), "yyyy-MM");
		return "toPilotTimeEditPage1";
	}

	public String toPilotTimeDetailPage1(){
		tfQualPilotAppinforOne=tfQualPilotLicenseRateAppBS.findById(TfQualPilotAppinforOne.class, tfQualPilotAppinforOne.getId());
		return "toPilotTimeDetailPage1";
	}
	
	public String saveOrUpdateTime1(){
		String id=tfQualPilotAppinforOne.getId().trim();
		try {
			if(id.equals("")){
				tfQualPilotAppinforOne.setId(null);
			}
			tfQualPilotAppinforOne.setStartdt(DateTool.formatDate(startDate, "yyyy-MM"));
			tfQualPilotAppinforOne.setEnddt(DateTool.formatDate(endDate, "yyyy-MM"));
			tfQualPilotLicenseRateAppBS.saveOrUpdate(tfQualPilotAppinforOne);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "3");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(id.equals("")?getText("addSuccess"):getText("updateSuccess"),moduleName, "closeCurrent","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(id.equals("")?getText("addFail"):getText("updateFail")));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String multiDelTime1(){
		try {
			tfQualPilotLicenseRateAppBS.deleteByIds(TfQualPilotAppinforOne.class, ids);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "3");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "","forward",
					"tf-qua-apply/tf-qual-pilot-license-rate-app!pilotLicense.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(	e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//------------------------驾驶员飞行时间记录 -02 -------------------------------------
	
	public String toPilotTimeAddPage2(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		return "toPilotTimeAddPage2";
	}

	public String toPilotTimeEditPage2(){
		tfQualPilotAppinforTwo=tfQualPilotLicenseRateAppBS.findById(TfQualPilotAppinforTwo.class, tfQualPilotAppinforTwo.getId());
		tfQualDeclaraPilot=	tfQualPilotAppinforTwo.getTfQualDeclaraPilot();
		return "toPilotTimeEditPage2";
	}
	
	public String toPilotTimeDetailPage2(){
		tfQualPilotAppinforTwo=tfQualPilotLicenseRateAppBS.findById(TfQualPilotAppinforTwo.class, tfQualPilotAppinforTwo.getId());
		return "toPilotTimeDetailPage2";
	}
	
	public String saveOrUpdateTime2(){
		String id=tfQualPilotAppinforTwo.getId().trim();
		try {
			if(id.equals("")){
				tfQualPilotAppinforTwo.setId(null);
			}
			tfQualPilotLicenseRateAppBS.saveOrUpdate(tfQualPilotAppinforTwo);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "4");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(id.equals("")?getText("addSuccess"):getText("updateSuccess"),moduleName, "closeCurrent","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(id.equals("")?getText("addFail"):getText("updateFail")));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String multiDelTime2(){
		try {
			tfQualPilotLicenseRateAppBS.deleteByIds(TfQualPilotAppinforTwo.class, ids);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "4");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "","forward",
					"tf-qua-apply/tf-qual-pilot-license-rate-app!pilotLicense.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(	e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//------------------------学	   习	 简	 历-------------------------------------
	/**
	 * 跳至学习经历添加页面
	 * @return
	 */
	public String toEduAddPage(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotEducation=new TfQualPilotEducation();
		tfQualPilotEducation.setCmPeople(tfQualDeclaraPilot.getCmPeople());
		return "educationAdd";
	}
	
	/**
	 * 跳至学习经历修改页面
	 * @return
	 */
	public String toEduEditPage(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotEducation=tfQualPilotLicenseRateAppBS.findById(TfQualPilotEducation.class, tfQualPilotEducation.getEducationid());
		return "educationEdit";
	}
	
	/**
	 * 保存或更新学习经历
	 * @return
	 */
	public String saveOrUpdateEdu(){
		String eduId=tfQualPilotEducation.getEducationid();
		try {
			if(eduId !=null && eduId.trim().equals("")){
				tfQualPilotEducation.setEducationid(null);
			}
			tfQualPilotLicenseRateAppBS.saveOrUpdate(tfQualPilotEducation);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "0");
			this.message = this.getSuccessMessage(eduId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),
					moduleName, "closeCurrent","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(eduId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除一条学习经历
	 * @return
	 */
	public String deleteEdu(){
		try{
			tfQualPilotLicenseRateAppBS.delete(tfQualPilotEducation);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "0");
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "","forward",
					"tf-qua-apply/tf-qual-pilot-license-rate-app!pilotLicense.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//-----------------------------工 	作	 简	 历--------------------------------------------
	
	/**
	 * 跳至工作经历添加页面
	 * @return
	 */
	public String toExpAddPage(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotExperience=new TfQualPilotExperience();
		tfQualPilotExperience.setCmPeople(tfQualDeclaraPilot.getCmPeople());
		return "expAdd";
	}
	

	/**
	 * 跳至工作经历修改页面
	 * @return
	 */
	public String toExpEditPage(){
		tfQualDeclaraPilot=tfQualPilotLicenseRateAppBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotExperience=tfQualPilotLicenseRateAppBS.findById(TfQualPilotExperience.class, tfQualPilotExperience.getExperienceid());
		return "expEdit";
	}
	
	/**
	 * 保存或更新工作经历
	 * @return
	 */
	public String saveOrUpdateExp(){
		String expId=tfQualPilotExperience.getExperienceid();
		try {
			if(expId !=null && expId.trim().equals("")){
				tfQualPilotExperience.setExperienceid(null);
			}
			tfQualPilotLicenseRateAppBS.saveOrUpdate(tfQualPilotExperience);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "1");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(expId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),
					moduleName, "closeCurrent","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(expId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除一条工作经历
	 * @return
	 */
	public String deleteExp(){
		try{
			tfQualPilotLicenseRateAppBS.delete(tfQualPilotExperience);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "1");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "","forward",
					"tf-qua-apply/tf-qual-pilot-license-rate-app!pilotLicense.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//--------------------------执照 和 等级 申请表-------------------------------------------
	/**
	 * 保存或更新 申请信息
	 * @return
	 */
	public String saveOrUpdateAppInfo(){
		String infoId=tfQualPilotAppinformation.getInformationid();
		try {
			if(infoId == null || infoId.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualPilotAppinformation.setInformationid(null);
				tfQualPilotAppinformation.setModifier(getUser().getUserId());
				tfQualPilotLicenseRateAppBS.save(tfQualPilotAppinformation);
			}else{
				//如果申请信息id不“空”，则更新
				tfQualPilotAppinformation.setInfo26c(tfQualPilotAppinformation.getInfo26c()==null?"":tfQualPilotAppinformation.getInfo26c());
				tfQualPilotAppinformation.setInfo27(tfQualPilotAppinformation.getInfo27()==null?"":tfQualPilotAppinformation.getInfo27());
				tfQualPilotAppinformation.setInfo45(tfQualPilotAppinformation.getInfo45()==null ? "" :tfQualPilotAppinformation.getInfo45());
				tfQualPilotAppinformation.setModifier(getUser().getUserId());
				tfQualPilotLicenseRateAppBS.update(tfQualPilotAppinformation, infoId);
			}
			//设置需要打开的选项卡序号
			if("requestInfo".equals(pageName)){
				this.getServletRequest().getSession().setAttribute("tabIndex", "2");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}else{
				this.getServletRequest().getSession().setAttribute("tabIndex", "5");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}
		message=getSuccessMessage(infoId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"), moduleName, "","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return "json";
	}
	
	/**添加声明和授权教员推荐信息*/
	public String saveOrUpadateRecommendation(){
		String infoId=tfQualPilotAppinformation.getInformationid();
		try {
			if(infoId == null || infoId.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualPilotAppinformation.setInformationid(null);
				tfQualPilotLicenseRateAppBS.save(tfQualPilotAppinformation);
			}else{
				//如果申请信息id不“空”，则更新
				tfQualPilotLicenseRateAppBS.update(tfQualPilotAppinformation, infoId);					
			}
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "6");
			this.getServletRequest().getSession().setAttribute("flush", "y");
		message=getSuccessMessage(infoId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"), moduleName, "","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	/**添加考试员报告*/
	public String saveOrUpadateExaminerReport(){
		String infoId=tfQualPilotAppinformation.getInformationid();
		try {
			if(infoId == null || infoId.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualPilotAppinformation.setInformationid(null);
				tfQualPilotLicenseRateAppBS.save(tfQualPilotAppinformation);
			}else{
				//如果申请信息id不“空”，则更新
						
				tfQualPilotLicenseRateAppBS.update(tfQualPilotAppinformation, infoId);			
			}
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "7");
			this.getServletRequest().getSession().setAttribute("flush", "y");
		message=getSuccessMessage(infoId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"), moduleName, "","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**添加监察员报告*/
	public String saveOrUpadateInspectorReport(){
		String infoId=tfQualPilotAppinformation.getInformationid();
		try {
			if(infoId == null || infoId.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualPilotAppinformation.setInformationid(null);
				tfQualPilotLicenseRateAppBS.save(tfQualPilotAppinformation);
			}else{
				//如果申请信息id不“空”，则更新
				
				tfQualPilotAppinformation.setInfoRestrict65(tfQualPilotAppinformation.getInfoRestrict65()==null?"":tfQualPilotAppinformation.getInfoRestrict65().replace(",","").replace(" ", ""));
				
					tfQualPilotLicenseRateAppBS.update(tfQualPilotAppinformation, infoId);					
				}
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "8");
			this.getServletRequest().getSession().setAttribute("flush", "y");
		message=getSuccessMessage(infoId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"), moduleName, "","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}		
		return SUCCESS;
	}
	/**民航局飞行标准司审核*/
	public String saveOrUpadatereviewOfCACC(){
		String infoId=tfQualPilotAppinformation.getInformationid();
		try {
			if(infoId == null || infoId.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualPilotAppinformation.setInformationid(null);
				tfQualPilotLicenseRateAppBS.save(tfQualPilotAppinformation);
			}else{
				//如果申请信息id不“空”，则更新
							
				tfQualPilotAppinformation.setInfo79(tfQualPilotAppinformation.getInfo79()==null?"":tfQualPilotAppinformation.getInfo79().replace(",", "").replace(" ", ""));								
				tfQualPilotLicenseRateAppBS.update(tfQualPilotAppinformation, infoId);			
			}
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "9");
			this.getServletRequest().getSession().setAttribute("flush", "y");
		message=getSuccessMessage(infoId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"), moduleName, "","");
		} catch (Exception e) {
			tfQualPilotLicenseRateAppBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//----------------------getters and setters ---------------------------------------------
	@JSON(serialize = false)
	public TfQualPilotAppinformation getTfQualPilotAppinformation() {
		return tfQualPilotAppinformation;
	}
	public void setTfQualPilotAppinformation(
			TfQualPilotAppinformation tfQualPilotAppinformation) {
		this.tfQualPilotAppinformation = tfQualPilotAppinformation;
	}
	@JSON(serialize = false)
	public TfQualPilotEducation getTfQualPilotEducation() {
		return tfQualPilotEducation;
	}
	public void setTfQualPilotEducation(TfQualPilotEducation tfQualPilotEducation) {
		this.tfQualPilotEducation = tfQualPilotEducation;
	}
	@JSON(serialize = false)
	public TfQualPilotExperience getTfQualPilotExperience() {
		return tfQualPilotExperience;
	}
	public void setTfQualPilotExperience(TfQualPilotExperience tfQualPilotExperience) {
		this.tfQualPilotExperience = tfQualPilotExperience;
	}
	@JSON(serialize = false)
	public ITfQualPilotLicenseRateAppBS getTfQualPilotLicenseRateAppBS() {
		return tfQualPilotLicenseRateAppBS;
	}
	public void setTfQualPilotLicenseRateAppBS(
			ITfQualPilotLicenseRateAppBS tfQualPilotLicenseRateAppBS) {
		this.tfQualPilotLicenseRateAppBS = tfQualPilotLicenseRateAppBS;
	}

	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}
	
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	@JSON(serialize = false)
	public List<TfQualPilotEducation> getEducationList() {
		return educationList;
	}
	public void setEducationList(List<TfQualPilotEducation> educationList) {
		this.educationList = educationList;
	}
	@JSON(serialize = false)
	public List<TfQualPilotExperience> getExperienceList() {
		return experienceList;
	}
	public void setExperienceList(List<TfQualPilotExperience> experienceList) {
		this.experienceList = experienceList;
	}
	@JSON(serialize = false)
	public TfQualPilotAppinforOne getTfQualPilotAppinforOne() {
		return tfQualPilotAppinforOne;
	}

	public void setTfQualPilotAppinforOne(
			TfQualPilotAppinforOne tfQualPilotAppinforOne) {
		this.tfQualPilotAppinforOne = tfQualPilotAppinforOne;
	}
	@JSON(serialize = false)
	public List<TfQualPilotAppinforOne> getTfQualPilotAppinforOneList() {
		return tfQualPilotAppinforOneList;
	}

	public void setTfQualPilotAppinforOneList(
			List<TfQualPilotAppinforOne> tfQualPilotAppinforOneList) {
		this.tfQualPilotAppinforOneList = tfQualPilotAppinforOneList;
	}
	@JSON(serialize = false)
	public TfQualPilotAppinforTwo getTfQualPilotAppinforTwo() {
		return tfQualPilotAppinforTwo;
	}

	public void setTfQualPilotAppinforTwo(
			TfQualPilotAppinforTwo tfQualPilotAppinforTwo) {
		this.tfQualPilotAppinforTwo = tfQualPilotAppinforTwo;
	}
	@JSON(serialize = false)
	public List<TfQualPilotAppinforTwo> getTfQualPilotAppinforTwoList() {
		return tfQualPilotAppinforTwoList;
	}

	public void setTfQualPilotAppinforTwoList(
			List<TfQualPilotAppinforTwo> tfQualPilotAppinforTwoList) {
		this.tfQualPilotAppinforTwoList = tfQualPilotAppinforTwoList;
	}
	@JSON(serialize = false)
	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}

	public void setBaseAirPlanTypeList(List<BaseAirPlanType> baseAirPlanTypeList) {
		this.baseAirPlanTypeList = baseAirPlanTypeList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
