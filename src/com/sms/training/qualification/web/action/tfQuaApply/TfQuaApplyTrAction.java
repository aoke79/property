package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.*;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;

/**
 * 转机型流程action
 * @author Licumn
 *
 */
@ParentPackage("sinoframe-default")
@Results({
	@Result(name = "qualPilotStayListTR", location = "/sms/training/qualification/quaApplyTr/qualPilotStayListTR.jsp"),
	@Result(name = "json", type = "json"),
	@Result(name = "success", location = "/standard/ajaxDone.jsp")
})
public class TfQuaApplyTrAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	//模块名
	private static final String moduleName="TfQuaApplyTrAction";
	//用户id
	private String userId;
	//资质类型list
	private List<TfQualBaseType> tfQualBaseTypeList;
	//
	private ITfQuaApplyInspectorBS tfQuaApplyInspectorBS;
	//资质类型分类（大类）
	private String qtgroupid;
	//资质类型 子类id
	private String subGroupId;
	//资质类型 实体
	private TfQualBaseType tfQualBaseType;
	//资质类型id
	private String typeId;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	//用于指明要显示的列表是“可选人员”（值=0），还是“已选人员”（值=1）
	private int availableOrSelected = 0;
	//待申报人员列表
	private List<Object> pilotStayTmpList;
	//转机型service
	private ITfQuaApplyTrBS tfQuaApplyTrBS;
	//查询条件，姓名
	private String pilotName;
	//切换页面按钮显示，‘1’：可见，‘0’：不可见。按钮次序：可选择人员、选择申报人员、已选择人员、提交申请
	private String[] btnSwitch={"0","1","1","0"};
	
	/** 构造方法	 */
	public TfQuaApplyTrAction() {
	}
	
	/**
	 * to 待申报人员列表 页面
	 * @return
	 */
	public String list(){
		try {
			userId=getUser().getUserId();
			//根据资质类型分类subGroupId获取对应的资质类型列表
			this.tfQualBaseTypeList = this.tfQuaApplyInspectorBS.findTypeList(subGroupId);
			if(typeId == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
				tfQualBaseType = tfQualBaseTypeList.get(0);
				typeId=tfQualBaseType.getTypeid();
			}else if(typeId!=null){
				tfQualBaseType=tfQuaApplyTrBS.findById(TfQualBaseType.class, typeId);
			}
			if(tfQualBaseType!=null){
				String orgNameStr=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				if(availableOrSelected == 0){
					//获取对应资质类型下符合条件的供选择待申报人员列表
					pilotStayTmpList= tfQuaApplyTrBS.getAvailablePilotStayTmp(this.getSysPageInfo(), tfQualBaseType, orgNameStr,pilotName,subGroupId);
					//切换页面按钮显示
					btnSwitch=new String[]{"0","1","1","0"};
				}else{
					//获取对应资质类型下符合条件的 已选择 待申报人员列表
					pilotStayTmpList= tfQuaApplyTrBS.getSelectedPilotStayTmp(this.getSysPageInfo(), typeId,orgNameStr,pilotName);
					//切换页面按钮显示
					btnSwitch=new String[]{"1","0","0","1"};
				}
			}
		} catch (Exception e) {
			tfQuaApplyTrBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "qualPilotStayListTR";
	}
	
	//==============================    getters and setters ================================

	@JSON(serialize = false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}
	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}

	@JSON(serialize = false)
	public ITfQuaApplyInspectorBS getTfQuaApplyInspectorBS() {
		return tfQuaApplyInspectorBS;
	}
	public void setTfQuaApplyInspectorBS(ITfQuaApplyInspectorBS tfQuaApplyInspectorBS) {
		this.tfQuaApplyInspectorBS = tfQuaApplyInspectorBS;
	}

	@JSON(serialize = false)
	public String getQtgroupid() {
		return qtgroupid;
	}
	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}

	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	
	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@JSON(serialize = false)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}
	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}
	
	@JSON(serialize = false)
	public ITfQuaApplyTrBS getTfQuaApplyTrBS() {
		return tfQuaApplyTrBS;
	}
	public void setTfQuaApplyTrBS(ITfQuaApplyTrBS tfQuaApplyTrBS) {
		this.tfQuaApplyTrBS = tfQuaApplyTrBS;
	}

	@JSON(serialize = false)
	public String getPilotName() {
		return pilotName;
	}
	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	@JSON(serialize = false)
	public List<Object> getPilotStayTmpList() {
		return pilotStayTmpList;
	}
	public void setPilotStayTmpList(List<Object> pilotStayTmpList) {
		this.pilotStayTmpList = pilotStayTmpList;
	}

	@JSON(serialize = false)
	public String[] getBtnSwitch() {
		return btnSwitch;
	}
	public void setBtnSwitch(String[] btnSwitch) {
		this.btnSwitch = btnSwitch;
	}
	@JSON(serialize = false)
	public int getAvailableOrSelected() {
		return availableOrSelected;
	}
	public void setAvailableOrSelected(int availableOrSelected) {
		this.availableOrSelected = availableOrSelected;
	}
}
