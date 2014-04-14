package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQuaApplyFXJYtheoryBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualPilotDetailBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

/**
 * 飞行教员理论课复训
 * @author sinosoft
 *
 */

@Results({
	@Result(name = "json", type = "json"),
	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
	@Result(name = "list", location = "/sms/training/qualification/tfQualFXJYtheory/FXJYtheory.jsp")
	})
public class TfQuaApplyFxjyTheoryAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	// 当前模块名称
	private String moduleName = "TfQuaApplyFXJYtheory";
	private String state;
	// 消息实体
	private Message message;
	private TfQualDeclarInfo tfQualDeclarInfo;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private static final String NAV_TAB_ID="qualApply_tabId";
	//申报信息ID
	private String declaredinfoid="";
	// 资质申请的流程操作对象
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	private String ids;
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private ProcessBase processBase = new ProcessBase();
	private IProcesshistoryBS processhistoryBS;
	private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
	private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
	private String opinionInfo;
	private Date qianmingDate;
	private String declarInfoTabId;
	// 添加成功表示
	private boolean successFlag = true;
	// 任务传递
	private String taskId;
	private ITfQualPilotDetailBS tfQualPilotDetailBS;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private String detailsid;
	private String subProcessid;
	private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
	private ITfQuaApplyFXJYtheoryBS tfQuaApplyFXJYtheoryBS;
	//资质类型 子类id
	private String subGroupId;
	private String userId;
	//资质类型ID
	private String typeId;
	private String formUrl;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private List<TfQualBaseType> tfQualBaseTypeList;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	//资质类型实体
	private TfQualBaseType tfQualBaseType;
	//待申报人员列表
	private List<Object> tfQualDeclaraPilotStayList;
	private String typeids;
	
	/**
	 * to 待申报人员列表页面
	 */
	public String List(){
		try {
			userId=getUser().getUserId();
			//根据资质类型分类及subGroupId获取对应的资质类型列表
			this.tfQualBaseTypeList = this.tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
			if(typeids == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
				tfQualBaseType = tfQualBaseTypeList.get(0);
				typeId=tfQualBaseType.getTypeid();
			}else if(typeids!=null){
				tfQualBaseType=tfQualBaseTypeBS.findById(TfQualBaseType.class, typeids);
			}
			//获取对应资质类型下符合条件的供选择待申报人员列表
			if(tfQualBaseType!=null){
				String orgNameStr=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
				//获取对应资质类型下符合条件的供选择待申报人员列表
				tfQualDeclaraPilotStayList= tfQuaApplyFXJYtheoryBS.getAvailablePilotStayList(tfQualBaseType, orgNameStr);
				}
			setFormUrl("tf-qua-apply/tf-qua-apply-general!toSubmit.do");
			
		} catch (Exception e) {
			tfQuaApplyFXJYtheoryBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		
		return "list";
	}
	
	@JSON(serialize = false)
	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	@JSON(serialize = false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}

	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
//此处不要加flase;
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}

	@JSON(serialize = false)
	public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
		return tfQuaApplyJbpmBS;
	}

	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
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
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}

	@JSON(serialize = false)
	public ProcessBase getProcessBase() {
		return processBase;
	}

	public void setProcessBase(ProcessBase processBase) {
		this.processBase = processBase;
	}

	@JSON(serialize = false)
	public IProcesshistoryBS getProcesshistoryBS() {
		return processhistoryBS;
	}

	public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
		this.processhistoryBS = processhistoryBS;
	}

	@JSON(serialize = false)
	public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
		return tfQualDeclarApprovalinfo;
	}

	public void setTfQualDeclarApprovalinfo(
			TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
		this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
	}

	@JSON(serialize = false)
	public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
		return tfQualDeclarApprovalInfoBS;
	}

	public void setTfQualDeclarApprovalInfoBS(ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
		this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
	}

	@JSON(serialize = false)
	public String getOpinionInfo() {
		return opinionInfo;
	}

	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}

	@JSON(serialize = false)
	public Date getQianmingDate() {
		return qianmingDate;
	}

	public void setQianmingDate(Date qianmingDate) {
		this.qianmingDate = qianmingDate;
	}

	@JSON(serialize = false)
	public String getDeclarInfoTabId() {
		return declarInfoTabId;
	}

	public void setDeclarInfoTabId(String declarInfoTabId) {
		this.declarInfoTabId = declarInfoTabId;
	}

	@JSON(serialize = false)
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JSON(serialize = false)
	public ITfQualPilotDetailBS getTfQualPilotDetailBS() {
		return tfQualPilotDetailBS;
	}

	public void setTfQualPilotDetailBS(ITfQualPilotDetailBS tfQualPilotDetailBS) {
		this.tfQualPilotDetailBS = tfQualPilotDetailBS;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@JSON(serialize = false)
	public String getDetailsid() {
		return detailsid;
	}

	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}

	@JSON(serialize = false)
	public String getSubProcessid() {
		return subProcessid;
	}

	public void setSubProcessid(String subProcessid) {
		this.subProcessid = subProcessid;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
		return tfQualDeclaraPilotStayBS;
	}

	public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
		this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
	}
	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	@JSON(serialize = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize = false)
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@JSON(serialize = false)
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	@JSON(serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}
	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}

	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}
	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}
	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}
	
	@JSON(serialize = false)
	public List<Object> getTfQualDeclaraPilotStayList() {
		return tfQualDeclaraPilotStayList;
	}

	public void setTfQualDeclaraPilotStayList(
			List<Object> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}

	@JSON(serialize = false)
	public String getTypeids() {
		return typeids;
	}
	public void setTypeids(String typeids) {
		this.typeids = typeids;
	}
	
	@JSON(serialize = false)
	public ITfQuaApplyFXJYtheoryBS getTfQuaApplyFXJYtheoryBS() {
		return tfQuaApplyFXJYtheoryBS;
	}

	public void setTfQuaApplyFXJYtheoryBS(
			ITfQuaApplyFXJYtheoryBS tfQuaApplyFXJYtheoryBS) {
		this.tfQuaApplyFXJYtheoryBS = tfQuaApplyFXJYtheoryBS;
	}
	
}
