package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotAppinfoTeacher;
import com.sms.training.qualification.bean.TfQualPilotEducation;
import com.sms.training.qualification.bean.TfQualPilotExperience;
import com.sms.training.qualification.business.ITfQualTeacherLicenseAndGradeBS;

@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "teacherLicense", location = "/sms/training/qualification/teacherLicenseAndGrade/teacherLicense.jsp"),
	
	@Result(name = "tlearningResume", location = "/sms/training/qualification/teacherLicenseAndGrade/tlearningResume.jsp"),
	@Result(name = "eduAddPage", location = "/sms/training/qualification/teacherLicenseAndGrade/eduAddPage.jsp"),
	@Result(name = "educationEdit", location = "/sms/training/qualification/teacherLicenseAndGrade/educationEdit.jsp"),
	
	@Result(name = "tworkResume", location = "/sms/training/qualification/teacherLicenseAndGrade/tworkResume.jsp"),
	@Result(name = "expAdd", location = "/sms/training/qualification/teacherLicenseAndGrade/expAdd.jsp"),
	@Result(name = "expEdit", location = "/sms/training/qualification/teacherLicenseAndGrade/expAdd.jsp"),
	
	@Result(name = "requestInfo", location = "/sms/training/qualification/teacherLicenseAndGrade/requestInfo.jsp"),
	@Result(name = "reportInfo", location = "/sms/training/qualification/teacherLicenseAndGrade/reportInfo.jsp"),
	@Result(name = "recommendation", location = "/sms/training/qualification/teacherLicenseAndGrade/recommendation.jsp"),
	@Result(name = "inspectorReport", location = "/sms/training/qualification/teacherLicenseAndGrade/inspectorReport.jsp"),
	@Result(name = "reviewOfCACC", location = "/sms/training/qualification/teacherLicenseAndGrade/reviewOfCACC.jsp"),
	
	@Result(name = "json", type="json"),
})
public class TfQualTeacherLicenseAndGradeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	//模块名称
	private static String moduleName = "TfQualTeacherLicenseAndGrade";
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
	//运输航空公司飞行教员执照和等级申请表  
	private TfQualPilotAppinfoTeacher tfQualPilotAppinfoTeacher;
	//飞行员学习简历 
	private TfQualPilotEducation tfQualPilotEducation ;
	//飞行员学习简历   list
	private List<TfQualPilotEducation> educationList;
	//飞行员工作简历
	private TfQualPilotExperience tfQualPilotExperience;
	//飞行员工作简历   list
	private List<TfQualPilotExperience> experienceList;
	//机型list
	private List<BaseAirPlanType> baseAirPlanTypeList;
	//service
	private ITfQualTeacherLicenseAndGradeBS tfQualTeacherLicenseAndGradeBS;
	
	/**
	 * 执照和等级申请表 主页
	 * @return
	 */
	public String teacherLicense(){
		String flush = (String)this.getServletRequest().getSession().getAttribute("flush");
		String index = (String)this.getServletRequest().getSession().getAttribute("tabIndex");
		//适时的清除session，并确保页面跳转无误
		if(flush != null && !flush.equals("")){
			this.getServletRequest().getSession().removeAttribute("flush");
		}else if(index != null && !index.equals("")){
			this.getServletRequest().getSession().removeAttribute("tabIndex");
		}
		if(null == tfQualDeclaraPilot){
			tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,"ff8080813e1b1c22013e1b5a8acc0008");
		}else{
			tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		}
		return "teacherLicense";
	}
	
	/**
	 * 根据pageName打开相应的选项卡，进入对应页面
	 * @return
	 */
	public String gotoTab(){
		tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		
		if("tlearningResume".equals(pageName)){
			//学习简历
			educationList = tfQualTeacherLicenseAndGradeBS.findEducationList(tfQualDeclaraPilot.getCmPeople().getId());
		}else if ("tworkResume".equals(pageName)) {
			//工作经历
			experienceList = tfQualTeacherLicenseAndGradeBS.findExperienceList(tfQualDeclaraPilot.getCmPeople().getId());
		}else{
			tfQualPilotAppinfoTeacher = tfQualTeacherLicenseAndGradeBS.findAppInfo(tfQualDeclaraPilot.getDetailsid());
		}
		
		return pageName;
	}
	
	/**
	 * 学习简历 添加方法
	 * @return
	 */
	public String eduAddPage(){
		tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotEducation = new TfQualPilotEducation();
		tfQualPilotEducation.setCmPeople(tfQualDeclaraPilot.getCmPeople());
		return "eduAddPage";
	}
	
	/**
	 * 学习经历修改页面
	 * @return
	 */
	public String toEduEditPage(){
		tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotEducation=tfQualTeacherLicenseAndGradeBS.findById(TfQualPilotEducation.class, tfQualPilotEducation.getEducationid());
		return "educationEdit";
	}
	
	/**
	 * 保存或更新学习经历
	 * @return
	 */
	public String saveOrUpdateEdu(){
		String eduId = tfQualPilotEducation.getEducationid();
		try {
			if(eduId != null && eduId.trim().equals("")){
				tfQualPilotEducation.setEducationid(null);
			}
			tfQualTeacherLicenseAndGradeBS.saveOrUpdate(tfQualPilotEducation);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "0");
			this.message = this.getSuccessMessage(eduId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),
					moduleName, "closeCurrent","");
		} catch (Exception ex) {
			tfQualTeacherLicenseAndGradeBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(eduId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除一条学习经历
	 * @return
	 */
	public String deleteEdu(){
		try{
			tfQualTeacherLicenseAndGradeBS.delete(tfQualPilotEducation);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "0");
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "","forward",
					"tf-qua-apply/tf-qual-teacher-license-and-grade!teacherLicense.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualTeacherLicenseAndGradeBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳至工作经历添加页面
	 * @return
	 */
	public String toExpAddPage(){
		tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotExperience = new TfQualPilotExperience();
		tfQualPilotExperience.setCmPeople(tfQualDeclaraPilot.getCmPeople());
		return "expAdd";
	}
	

	/**
	 * 跳至工作经历修改页面
	 * @return
	 */
	public String toExpEditPage(){
		tfQualDeclaraPilot = tfQualTeacherLicenseAndGradeBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
		tfQualPilotExperience = tfQualTeacherLicenseAndGradeBS.findById(TfQualPilotExperience.class, tfQualPilotExperience.getExperienceid());
		return "expEdit";
	}
	
	/**
	 * 保存或更新工作简历
	 * @return
	 */
	public String saveOrUpdateExp(){
		String expId = tfQualPilotExperience.getExperienceid();
		try {
			if(expId !=null && expId.trim().equals("")){
				tfQualPilotExperience.setExperienceid(null);
			}
			tfQualTeacherLicenseAndGradeBS.saveOrUpdate(tfQualPilotExperience);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "1");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(expId.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),
					moduleName, "closeCurrent","");
		} catch (Exception e) {
			tfQualTeacherLicenseAndGradeBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
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
			tfQualTeacherLicenseAndGradeBS.delete(tfQualPilotExperience);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "1");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "","forward",
					"tf-qua-apply/tf-qual-teacher-license-and-grade!teacherLicense.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			tfQualTeacherLicenseAndGradeBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 保存或更新
	 * @return
	 */
	public String saveOrUpadateTAppinfo(){
		String infoId = tfQualPilotAppinfoTeacher.getInformationid();
		try {
			if(infoId == null || infoId.trim().equals("")){
				//如果申请信息id为“空”，则保存
				tfQualPilotAppinfoTeacher.setInformationid(null);
				tfQualPilotAppinfoTeacher.setModifier(getUser().getUserId());
				tfQualTeacherLicenseAndGradeBS.save(tfQualPilotAppinfoTeacher);
			}else{
				//如果申请信息id不“空”，则更新
				tfQualPilotAppinfoTeacher.setModifier(getUser().getUserId());
				tfQualTeacherLicenseAndGradeBS.update(tfQualPilotAppinfoTeacher, infoId);
			}
			//设置需要打开的选项卡序号
			if("requestInfo".equals(pageName)){//基本信息
				this.getServletRequest().getSession().setAttribute("tabIndex", "2");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}else if("reportInfo".equals(pageName)){//申请信息和声明
				this.getServletRequest().getSession().setAttribute("tabIndex", "3");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}
			if("recommendation".equals(pageName)){//运输航空公司推荐
				this.getServletRequest().getSession().setAttribute("tabIndex", "4");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}
			if("inspectorReport".equals(pageName)){//监察员报告
				this.getServletRequest().getSession().setAttribute("tabIndex", "5");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}
			if("reviewOfCACC".equals(pageName)){//民航局飞行标准司审核
				this.getServletRequest().getSession().setAttribute("tabIndex", "6");
				this.getServletRequest().getSession().setAttribute("flush", "y");
			}
		message=getSuccessMessage(infoId.trim().equals("")?getText("addSuccess"):getText("保存成功"), moduleName, "","");
		} catch (Exception e) {
			tfQualTeacherLicenseAndGradeBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(infoId.trim().equals("") ? getText("addFail"): getText("updateFail")));
			e.printStackTrace();
		}
		return "json";
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

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

	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	public TfQualPilotEducation getTfQualPilotEducation() {
		return tfQualPilotEducation;
	}

	public void setTfQualPilotEducation(TfQualPilotEducation tfQualPilotEducation) {
		this.tfQualPilotEducation = tfQualPilotEducation;
	}

	public List<TfQualPilotEducation> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<TfQualPilotEducation> educationList) {
		this.educationList = educationList;
	}

	public TfQualPilotExperience getTfQualPilotExperience() {
		return tfQualPilotExperience;
	}

	public void setTfQualPilotExperience(TfQualPilotExperience tfQualPilotExperience) {
		this.tfQualPilotExperience = tfQualPilotExperience;
	}

	public List<TfQualPilotExperience> getExperienceList() {
		return experienceList;
	}

	public void setExperienceList(List<TfQualPilotExperience> experienceList) {
		this.experienceList = experienceList;
	}

	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}

	public void setBaseAirPlanTypeList(List<BaseAirPlanType> baseAirPlanTypeList) {
		this.baseAirPlanTypeList = baseAirPlanTypeList;
	}

	public ITfQualTeacherLicenseAndGradeBS getTfQualTeacherLicenseAndGradeBS() {
		return tfQualTeacherLicenseAndGradeBS;
	}

	public void setTfQualTeacherLicenseAndGradeBS(
			ITfQualTeacherLicenseAndGradeBS tfQualTeacherLicenseAndGradeBS) {
		this.tfQualTeacherLicenseAndGradeBS = tfQualTeacherLicenseAndGradeBS;
	}

	public TfQualPilotAppinfoTeacher getTfQualPilotAppinfoTeacher() {
		return tfQualPilotAppinfoTeacher;
	}

	public void setTfQualPilotAppinfoTeacher(
			TfQualPilotAppinfoTeacher tfQualPilotAppinfoTeacher) {
		this.tfQualPilotAppinfoTeacher = tfQualPilotAppinfoTeacher;
	}
	
	
}
