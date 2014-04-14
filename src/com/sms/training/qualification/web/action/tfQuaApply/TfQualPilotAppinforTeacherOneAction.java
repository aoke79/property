package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherOne;
import com.sms.training.qualification.business.ITfQualPilotAppinforTeacherOneBS;
@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "list", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeRecordOne.jsp"),
	@Result(name = "toPilotTimeAddPageOne", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeAddOne.jsp"),
	@Result(name = "toPilotTimeDetailPage", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeDetailOne.jsp"),
	@Result(name = "toPilotTimeEditPage", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeAddOne.jsp"),
})
/**
 * 2012-05-20
 *  @author zhongchunping
 *  民用航空器飞行教员执照 驾驶员飞行时间1 控制层对象
 */
public class TfQualPilotAppinforTeacherOneAction extends BaseAction {

	/** 消息实体 */
	private Message message;
	/** 查询记录结果集 */
	private List<TfQualPilotAppinforTeacherOne> TfQualPilotAppinforTeacherOneList= new ArrayList<TfQualPilotAppinforTeacherOne>();
	/** 教员驾驶飞行时间1实体类 */
	private TfQualPilotAppinforTeacherOne tfQualPilotAppinforTeacherOne;
	/** 教员驾驶飞行时间1业务层对象 */
	private ITfQualPilotAppinforTeacherOneBS tfQualPilotAppinforTeacherOneBS;
	/**申报人员明细*/
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	/**机型list*/
	private List<BaseAirPlanType> baseAirPlanTypeList;
	private static final String MODELNAME="TfQualPilotTeacherAppinFormation";
	/**开始时间*/
	private String startDate;
	/**结束时间*/
	private String endDate;
	private String ids;
	/**
	 * 跳到 驾驶飞行时间1 页签
	 * @return
	 */
	public String list(){
		
		try{
			TfQualPilotAppinforTeacherOneList = this.tfQualPilotAppinforTeacherOneBS.findPageByDetailId(this.getSysPageInfo(),this.tfQualDeclaraPilot.getDetailsid());	
		}catch (Exception e) {
			tfQualPilotAppinforTeacherOneBS.getErrorLog().info(e.getMessage() + "#" + "listTfQualPilotAppinforTeacherOne");
			e.printStackTrace();
		}
		return "list";
	}
	/**
	 * 跳到 驾驶飞行时间1 添加页面
	 * @return
	 */
	public String toPilotTimeAddPage(){
		//获取机型列表
		baseAirPlanTypeList=tfQualPilotAppinforTeacherOneBS.findAirplanList();
		return "toPilotTimeAddPageOne";
	}
	/**
	 * 删除多条记录
	 * @return
	 */
	public String multiDelTime(){
		try {
			this.tfQualPilotAppinforTeacherOneBS.deleteByIds(TfQualPilotAppinforTeacherOne.class, ids);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "1");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(getText("deleteSuccess"),"", "forward",
					"tf-qua-apply/tf-qual-license!list.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			this.tfQualPilotAppinforTeacherOneBS.getErrorLog().info(e.getMessage() + "#" + "multipleDeleteTfQualPilotAppinforTeacherOne");
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 保存或是更新记录
	 * @return
	 */
	public String saveOrUpdateTime(){
		
		String id=tfQualPilotAppinforTeacherOne.getId().trim();
		try {
			this.tfQualPilotAppinforTeacherOne.setStartdt(DateTool.formatDate(startDate, "yyyy-MM"));
			this.tfQualPilotAppinforTeacherOne.setEnddt(DateTool.formatDate(endDate, "yyyy-MM"));
			if("".equals(id)){
				tfQualPilotAppinforTeacherOneBS.save(this.tfQualPilotAppinforTeacherOne);
			}else{
				tfQualPilotAppinforTeacherOneBS.update(this.tfQualPilotAppinforTeacherOne,id);
			}
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "1");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage("保存成功",MODELNAME, "closeCurrent","");
		} catch (Exception e) {
			tfQualPilotAppinforTeacherOneBS.getErrorLog().info(e.getMessage() + "#" + MODELNAME);
			this.setMessage(this.getFailMessage("保存失败!"));
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 查询驾驶时间记录具体信息
	 * @return
	 */
	public String toPilotTimeDetailPage(){
		
		this.tfQualPilotAppinforTeacherOne = tfQualPilotAppinforTeacherOneBS.findById(TfQualPilotAppinforTeacherOne.class, this.tfQualPilotAppinforTeacherOne.getId());
		return "toPilotTimeDetailPage";
	}
	/**
	 * 跳转到驾驶员飞行时间1详细页面
	 * @return
	 */
	public String toPilotTimeEditPage(){
		
		this.tfQualPilotAppinforTeacherOne = tfQualPilotAppinforTeacherOneBS.findById(TfQualPilotAppinforTeacherOne.class, this.tfQualPilotAppinforTeacherOne.getId());
		tfQualDeclaraPilot=	tfQualPilotAppinforTeacherOne.getTfQualDeclaraPilot();
		//获取机型列表
		baseAirPlanTypeList=tfQualPilotAppinforTeacherOneBS.findAirplanList();
		startDate = DateTool.formatDate(tfQualPilotAppinforTeacherOne.getStartdt(), "yyyy-MM");
		endDate = DateTool.formatDate(tfQualPilotAppinforTeacherOne.getEnddt(), "yyyy-MM");
		return "toPilotTimeEditPage";
	}
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public List<TfQualPilotAppinforTeacherOne> getTfQualPilotAppinforTeacherOneList() {
		return TfQualPilotAppinforTeacherOneList;
	}

	public void setTfQualPilotAppinforTeacherOneList(
			List<TfQualPilotAppinforTeacherOne> tfQualPilotAppinforTeacherOneList) {
		TfQualPilotAppinforTeacherOneList = tfQualPilotAppinforTeacherOneList;
	}
	@JSON(serialize=false)
	public TfQualPilotAppinforTeacherOne getTfQualPilotAppinforTeacherOne() {
		return tfQualPilotAppinforTeacherOne;
	}

	public void setTfQualPilotAppinforTeacherOne(
			TfQualPilotAppinforTeacherOne tfQualPilotAppinforTeacherOne) {
		this.tfQualPilotAppinforTeacherOne = tfQualPilotAppinforTeacherOne;
	}
	@JSON(serialize=false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	public void setTfQualPilotAppinforTeacherOneBS(
			ITfQualPilotAppinforTeacherOneBS tfQualPilotAppinforTeacherOneBS) {
		this.tfQualPilotAppinforTeacherOneBS = tfQualPilotAppinforTeacherOneBS;
	}
	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}
	public void setBaseAirPlanTypeList(List<BaseAirPlanType> baseAirPlanTypeList) {
		this.baseAirPlanTypeList = baseAirPlanTypeList;
	}
	@JSON(serialize=false)
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@JSON(serialize=false)
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@JSON(serialize=false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
