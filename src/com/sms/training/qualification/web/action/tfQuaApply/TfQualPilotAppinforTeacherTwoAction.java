package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherOne;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherTwo;
import com.sms.training.qualification.business.ITfQualPilotAppinforTeacherTwoBS;
@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "success", location = "/standard/ajaxDone.jsp"),
	@Result(name = "list", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeRecordTwo.jsp"),
	@Result(name = "toPilotTimeAddPage", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeAddTwo.jsp"),
	@Result(name = "toPilotTimeEditPage", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeAddTwo.jsp"),
	@Result(name = "toPilotTimeDetailPage", location = "/sms/training/qualification/qualLicenseApplication/pilotTimeDetailTwo.jsp")
})
/**
 * 2012-05-20
 *  @author zhongchunping
 *  民用航空器飞行教员执照 驾驶员飞行时间2 控制层对象
 */
public class TfQualPilotAppinforTeacherTwoAction extends BaseAction {

	/** 消息实体 */
	private Message message;
	/** 查询记录结果集 */
	private List<TfQualPilotAppinforTeacherTwo> TfQualPilotAppinforTeacherTwoList= new ArrayList<TfQualPilotAppinforTeacherTwo>();
	/** 教员驾驶飞行时间2实体类 */
	private TfQualPilotAppinforTeacherTwo tfQualPilotAppinforTeacherTwo;
	/** 教员驾驶飞行时间2业务层对象 */
	private ITfQualPilotAppinforTeacherTwoBS tfQualPilotAppinforTeacherTwoBS;
	/**申报人员明细*/
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	/**机型list*/
	private List<BaseAirPlanType> baseAirPlanTypeList;
	private static final String MODELNAME="TfQualPilotTeacherAppinFormation";
	private String ids;
	/**
	 * 跳到 驾驶飞行时间1 页签
	 * @return
	 */
	public String list(){
		
		try{
			TfQualPilotAppinforTeacherTwoList = this.tfQualPilotAppinforTeacherTwoBS.findPageByDetailId(this.tfQualDeclaraPilot.getDetailsid());	
		}catch (Exception e) {
			tfQualPilotAppinforTeacherTwoBS.getErrorLog().info(e.getMessage() + "#" + "listTfQualPilotAppinforTeacherTwo");
			e.printStackTrace();
		}
		return "list";
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toPilotTimeAddPage(){
		return "toPilotTimeAddPage";
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toPilotTimeEditPage(){
		
		this.tfQualPilotAppinforTeacherTwo = tfQualPilotAppinforTeacherTwoBS.findById(TfQualPilotAppinforTeacherTwo.class, this.tfQualPilotAppinforTeacherTwo.getId());
		tfQualDeclaraPilot=	this.tfQualPilotAppinforTeacherTwo.getTfQualDeclaraPilot();
		return "toPilotTimeEditPage";
	}
	/**
	 * 删除多条记录
	 * @return
	 */
	public String multiDelTime(){
		try {
			this.tfQualPilotAppinforTeacherTwoBS.deleteByIds(TfQualPilotAppinforTeacherTwo.class, ids);
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "2");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage(getText("deleteSuccess"),"", "forward",
					"tf-qua-apply/tf-qual-license!list.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid());
		} catch (Exception e) {
			this.tfQualPilotAppinforTeacherTwoBS.getErrorLog().info(e.getMessage() + "#" + "multipleDeleteTfQualPilotAppinforTeacherTwo");
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 查看驾驶飞行时间记录2详细信息
	 * @return
	 */
	public String toPilotTimeDetailPage(){
		
		this.tfQualPilotAppinforTeacherTwo = tfQualPilotAppinforTeacherTwoBS.findById(TfQualPilotAppinforTeacherTwo.class, this.tfQualPilotAppinforTeacherTwo.getId());
		return "toPilotTimeDetailPage";
	}
	/**
	 * 保存或是修改驾驶员飞行记录2
	 * @return
	 */
	public String saveOrUpdateTime(){
		
		String id=tfQualPilotAppinforTeacherTwo.getId().trim();
		try {
			if(("").equals(id)){
				tfQualPilotAppinforTeacherTwoBS.save(tfQualPilotAppinforTeacherTwo);
			}else{
				tfQualPilotAppinforTeacherTwoBS.update(tfQualPilotAppinforTeacherTwo,id);
			}
			//设置需要打开的选项卡序号
			this.getServletRequest().getSession().setAttribute("tabIndex", "2");
			this.getServletRequest().getSession().setAttribute("flush", "y");
			this.message = this.getSuccessMessage("保存成功!",MODELNAME, "closeCurrent","");
		} catch (Exception e) {
			tfQualPilotAppinforTeacherTwoBS.getErrorLog().info(e.getMessage() + "#" + MODELNAME);
			this.setMessage(this.getFailMessage("保存失败!"));
			e.printStackTrace();
		}
		return "success";
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public List<TfQualPilotAppinforTeacherTwo> getTfQualPilotAppinforTeacherTwoList() {
		return TfQualPilotAppinforTeacherTwoList;
	}
	public void setTfQualPilotAppinforTeacherTwoList(
			List<TfQualPilotAppinforTeacherTwo> tfQualPilotAppinforTeacherTwoList) {
		TfQualPilotAppinforTeacherTwoList = tfQualPilotAppinforTeacherTwoList;
	}
	@JSON(serialize=false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	@JSON(serialize=false)
	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}
	public void setBaseAirPlanTypeList(List<BaseAirPlanType> baseAirPlanTypeList) {
		this.baseAirPlanTypeList = baseAirPlanTypeList;
	}
	@JSON(serialize=false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setTfQualPilotAppinforTeacherTwoBS(
			ITfQualPilotAppinforTeacherTwoBS tfQualPilotAppinforTeacherTwoBS) {
		this.tfQualPilotAppinforTeacherTwoBS = tfQualPilotAppinforTeacherTwoBS;
	}
	@JSON(serialize=false)
	public TfQualPilotAppinforTeacherTwo getTfQualPilotAppinforTeacherTwo() {
		return tfQualPilotAppinforTeacherTwo;
	}
	public void setTfQualPilotAppinforTeacherTwo(
			TfQualPilotAppinforTeacherTwo tfQualPilotAppinforTeacherTwo) {
		this.tfQualPilotAppinforTeacherTwo = tfQualPilotAppinforTeacherTwo;
	}
	
}
