package com.sms.training.qualification.web.action.tfQuaApply;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotTeacherAppinFormation;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualPilotTeacherAppinFormationBS;
@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "qualLicenseMain", location = "/sms/training/qualification/qualLicenseApplication/qualLicenseMain.jsp"),
	@Result(name = "requestInfo", location = "/sms/training/qualification/qualLicenseApplication/requestInfo.jsp"),
	@Result(name = "statement", location = "/sms/training/qualification/qualLicenseApplication/statement.jsp"),
	@Result(name = "examinerReport", location = "/sms/training/qualification/qualLicenseApplication/examinerReport.jsp"),
	@Result(name = "inspectorReport", location = "/sms/training/qualification/qualLicenseApplication/inspectorReport.jsp"),
	@Result(name = "caacExamine", location = "/sms/training/qualification/qualLicenseApplication/caacExamine.jsp"),
	@Result(name = "statement", location = "/sms/training/qualification/qualLicenseApplication/statement.jsp"),
})
/**
 * 2012-05-07
 *  @author zhongchunping
 *  民用航空器飞行教员执照或等级申请表控制层
 */
public class TfQualLicenseAction extends BaseAction{
	/**jsp页面名称*/
	private String pageName;
	/**申报人员明细 */
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	/**民用航空器飞行教员执照或等级申请表业务层对象*/
	private ITfQualPilotTeacherAppinFormationBS tfQualPilotTeacherAppinFormationBS;
	/**民用航空器飞行教员执照或等级申请表实体对象*/
	private TfQualPilotTeacherAppinFormation tfQualPilotTeacherAppinFormation;
	/**消息实体*/
	private Message message;
	private String modelName="TfQualPilotTeacherAppinFormation";
	@Autowired
	public void setTfQualPilotTeacherAppinFormationBS(
			ITfQualPilotTeacherAppinFormationBS tfQualPilotTeacherAppinFormationBS) {
		this.tfQualPilotTeacherAppinFormationBS = tfQualPilotTeacherAppinFormationBS;
	}
	@Autowired
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}

	public String list(){
		
		String flush = (String)this.getServletRequest().getSession().getAttribute("flush");
		String index = (String)this.getServletRequest().getSession().getAttribute("tabIndex");
		//适时的清除session，并确保页面跳转无误
		if(flush != null && !flush.equals("")){
			this.getServletRequest().getSession().removeAttribute("flush");
		}else if(index != null && !index.equals("")){
			this.getServletRequest().getSession().removeAttribute("tabIndex");
		}
		this.tfQualDeclaraPilot = this.tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, "8a8a46cc3e10e846013e112f0461000e");
		return "qualLicenseMain";
	}
	/**
	 * 根据pageName打开相应的选项卡，进入对应页面
	 * @return
	 */
	public String gotoTab(){
		this.tfQualPilotTeacherAppinFormation = this.tfQualPilotTeacherAppinFormationBS.findByDetailsid(tfQualDeclaraPilot.getDetailsid());
		if(null == this.tfQualPilotTeacherAppinFormation){
			this.tfQualPilotTeacherAppinFormation = new TfQualPilotTeacherAppinFormation();
		}
		return pageName;
	}
	/**
	 * 申请信息标签页数据保存
	 * @return
	 */
	public String saveOrUpdateAppInfo(){
		String id = comment();
		//设置需要打开的选项卡序号
		this.getServletRequest().getSession().setAttribute("tabIndex", "0");
		return "success";
	}
	/**
	 * 申请页签    数据保存
	 * @return
	 */
	public String saveOrUpdateStatement(){
		String id = comment();
		//设置需要打开的选项卡序号
		this.getServletRequest().getSession().setAttribute("tabIndex", "3");
		return "success";
	}
	public String saveOrUpadateExaminerReport(){
		
		String id = comment();
		//设置需要打开的选项卡序号
		this.getServletRequest().getSession().setAttribute("tabIndex", "4");
		return "success";
	}
	public String saveOrUpadateInspectorReport(){
		
		String id = comment();
		this.getServletRequest().getSession().setAttribute("tabIndex", "5");
		return "success";
	}
	private String comment() {
		String id = this.tfQualPilotTeacherAppinFormation.getId();
		if("".equals(id)){
			tfQualPilotTeacherAppinFormationBS.save(this.tfQualPilotTeacherAppinFormation);
		}else{
			tfQualPilotTeacherAppinFormationBS.update(tfQualPilotTeacherAppinFormation,this.tfQualPilotTeacherAppinFormation.getId());
		}
		this.getServletRequest().getSession().setAttribute("flush", "y");
		this.message = this.getSuccessMessage(id.equals("")?getText("addSuccess"):getText("updateSuccess"), "","forward",
				"tf-qua-apply/tf-qual-license!list.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		return id;
	}
	/**
	 * 名航总局飞行标准审核页签    数据保存
	 * @return
	 */
	public String saveOrUpadateCaccExamine(){
		
		String id = comment();
		//设置需要打开的选项卡序号
		this.getServletRequest().getSession().setAttribute("tabIndex", "6");
		this.getServletRequest().getSession().setAttribute("flush", "y");
		this.message = this.getSuccessMessage(id.equals("")?getText("addSuccess"):getText("updateSuccess"), "","forward",
				"tf-qua-apply/tf-qual-license!list.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		return "success";
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
	public TfQualPilotTeacherAppinFormation getTfQualPilotTeacherAppinFormation() {
		return tfQualPilotTeacherAppinFormation;
	}
	public void setTfQualPilotTeacherAppinFormation(
			TfQualPilotTeacherAppinFormation tfQualPilotTeacherAppinFormation) {
		this.tfQualPilotTeacherAppinFormation = tfQualPilotTeacherAppinFormation;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
}
