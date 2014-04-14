package com.sms.training.qualification.web.action.tfQualPilotConditions;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualBaseTypeGroup;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualStandardValuesBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
@Results( {
		@Result(name = "qualPilotList", location = "/sms/training/qualification/conditions/qualPilotList.jsp"),
		@Result(name = "qualPilotSuperList", location = "/sms/training/qualification/conditions/qualPilotSuperList.jsp"),
//		@Result(name = "qualPilotListTypeList", location = "/sms/training/qualification/conditions/qualPilotListTypeList.jsp"),
		@Result(name = "submit", location ="/sms/training/qualification/quaApply/quaApplySubmit.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name="toSubmit",location="tf-qua-apply!toSubmit.do",type="redirectAction"),
		@Result(name = "json", type = "json") 
		})
@ParentPackage("sinoframe-default")
public class TfQualifiedDeclareAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static String moduleName = "TfQualifiedDeclare";

	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	private String  numbers = "1";
	//每页显示条数
	private int numPerPage = 10;
	private Message message;

	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private ITfQualStandardValuesBS tfQualStandardValuesBS;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private List<TfQualBaseType> tfQualBaseTypeList;
	private List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList ; 
	private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList;
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	private String declaredinfoid;//申报信息ID
	private String formUrl; 
//	private String ifShow;
	//资质类型ID
	private String typeids;
	//资质类型分组
	private String qtgroupid;
	/**资质类型 子类id*/
	private String subGroupId;
	private String ids;
	private String userId;
	private HashMap<String, String> typeCount  = new HashMap<String, String>();
	//当前可以资质申报的飞行员数量
	private int pilotCount;
	
	public String superList(){
		this.typeCount = this.tfQualifiedDeclareBS.getTypeCount(this.getUserOrg().getId());
		return "qualPilotSuperList";
	}
	
	public String list(){
		try {
			CmUser user = getUser();
			userId=user.getUserId();
			this.tfQualBaseTypeList = this.tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
			//英语考试员、英语评分员流程用的，  借用的F4-F5的人；
			if("QUAL_EN_YYPFY".equals(subGroupId)|| "QUAL_EN_YYKSY".equals(subGroupId)){
				typeids="8a8a113d37e986350137e98a26b40002";
			}else{
				if(typeids == null && (tfQualBaseTypeList!= null && tfQualBaseTypeList.size()>0 )){
					typeids = tfQualBaseTypeList.get(0).getTypeid();
				}
			}
			String orgIds=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
			this.tfQualDeclaraPilotStayList = this.tfQualifiedDeclareBS.findPilotStaysByTypeAndOrgs(typeids,orgIds);
			setFormUrl("tf-qua-apply/tf-qua-apply!toSubmit.do");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "qualPilotList";
	}
	
	/**
	 * 根据类型和单位查询出的当前可以资质申报人员的数量
	 */
	public String calculatePilotCount() {
		String orgIds = tfQuaApplyIcaoBS.getOrgName(getUserOrg());
		//申报人员的数量
		pilotCount = this.tfQualifiedDeclareBS.findPilotStaysCountByTypeAndOrgs(typeids, orgIds);
		
		return "json";
	}
	
/*	public String qualPilotListTypeList(){
		try {
			tfQualBaseTypeGroupList = this.tfQualStandardValuesBS.getSelectList("TfQualBaseTypeGroup");
			//显示需要的字段
			String[] queryShown = {"qtgroupid"};
			//查询时需要的字段
			String[] queryCondition = {"eq_tfQualBaseTypeGroup.qtgroupid"};
			//转换特殊条件的查询
			this.tfQualStandardValuesBS.getQueryCondition(queryShown, queryCondition, query);
			tfQualBaseTypeList = this.tfQualStandardValuesBS.getTfQualBaseTypeList(this.getSysPageInfo(), query, this.getSysOrderByInfo());
			//转换显示得值
			this.tfQualStandardValuesBS.getQueryShow(queryShown, queryCondition, query);
		} catch (Exception e) {
			this.tfQualStandardValuesBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "qualPilotListTypeList";
	}*/
	public String ComputingStaff(){
		try {
			String orgIdStr=tfQuaApplyIcaoBS.getOrgName(getUserOrg());
			this.tfQualifiedDeclareBS.doComputingStaff(typeids,orgIdStr);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	public String createDeclarInfo(){
		try {
			CmUser user = getUser();
			userId=user.getUserId();
			 
			declaredinfoid=this.tfQualifiedDeclareBS.createDeclarInfo(typeids,ids,this.getUser());
			//this.message = this.getSuccessMessage("保存成功！", "TfQualifiedDeclare","forward","" );
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
//		return "toSubmit";
//		return "SUCCESS";
		return "json";
	}
	
	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}
	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	@JSON(serialize = false)
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}
	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	@JSON(serialize = false)
	public ITfQualStandardValuesBS getTfQualStandardValuesBS() {
		return tfQualStandardValuesBS;
	}
	public void setTfQualStandardValuesBS(ITfQualStandardValuesBS tfQualStandardValuesBS) {
		this.tfQualStandardValuesBS = tfQualStandardValuesBS;
	}
	@JSON(serialize = false)
	public List<TfQualBaseType> getTfQualBaseTypeList() {
		return tfQualBaseTypeList;
	}
	public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
		this.tfQualBaseTypeList = tfQualBaseTypeList;
	}
	@JSON(serialize = false)
	public List<TfQualBaseTypeGroup> getTfQualBaseTypeGroupList() {
		return tfQualBaseTypeGroupList;
	}
	public void setTfQualBaseTypeGroupList(List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList) {
		this.tfQualBaseTypeGroupList = tfQualBaseTypeGroupList;
	}
	public String getTypeids() {
		return typeids;
	}
	public void setTypeids(String typeids) {
		this.typeids = typeids;
	}
	@JSON(serialize = false)
	public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStayList() {
		return tfQualDeclaraPilotStayList;
	}
	public void setTfQualDeclaraPilotStayList(
			List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStayList) {
		this.tfQualDeclaraPilotStayList = tfQualDeclaraPilotStayList;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	public String getQtgroupid() {
		return qtgroupid;
	}

	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}

	public HashMap<String, String> getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(HashMap<String, String> typeCount) {
		this.typeCount = typeCount;
	}
	@JSON(serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	@JSON(serialize = false)
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	}
	
	@JSON(serialize = true)
	public int getPilotCount() {
		return pilotCount;
	}
	
	public void setPilotCount(int pilotCount) {
		this.pilotCount = pilotCount;
	}
}
