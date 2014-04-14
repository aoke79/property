package com.sms.training.qualification.web.action.tfQualDeclarDocument;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.jbpm.api.task.Task;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarDocument;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclarSeal;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualDeclarDocumentBS;
import com.sms.training.qualification.business.ITfQualDeclarInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "quaDocumnetReportDd", location = "/sms/training/qualification/tfQuaDocument/quaDocumentReportDd.jsp"),
		@Result(name = "toxlShenCha", location = "/sms/training/qualification/qualDeclaration/flightDataRecorder.jsp"),
		@Result(name = "flyTimeReport", location = "/sms/training/qualification/qualDeclaration/flyTimeReport.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") })
public class TfQualDeclarDocumentAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	// 消息实体
	private Message message;
	@Resource
	private ITfQualDeclarDocumentBS tfQualDeclarDocumentBS;	
	private static final char[] UNIT={'亿','十','百','千','万','十','百','千'};
	private static final char[]  CHAINIESFIGURE1={'零','一','二','三','四','五','六','七','八','九'};
	 
	// 当前模块名称
	private String moduleName = "TfQualDeclarDocumentAction";
	private String declaredinfoid;
	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private TfQualDeclarInfo tfQualDeclarInfo;
	private ITfQualDeclarInfoBS tfQualDeclarInfoBS;
	private TfQualBaseType tfQualBaseType;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private BaseAirPlanType baseAirPlanType;
	private String taskId;
	private String documentId;
	private String ID;
	//呈批件标识
	private String approvalSymbol ;
	private TfQualDeclarSeal tfQualDeclarSeal;
	private HashMap<String, String> query  = new HashMap<String, String>();
	//呈批件类型
	private String planType;
	//接收前父页面信息
	private String formUrl;
	/**资质类型 子类id*/
	private String subGroupId;
	//再次生成呈批件方法名
	private String secondCreate;
	private String ids="";
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	private String flyTimeId="";

	public String toQuaDocumnetReportDd(){
		if(!("").equals(ids)){
			String[] idsArr = ids.split(",");
			for(int i = 0, j = idsArr.length; i < j; i++){
				flyTimeId += idsArr[i] + "','";
			}
		}
		return "quaDocumnetReportDd";
	}
	
	/**
	 * 飞行经历时间(针对的是生成呈批件时,报表内飞行时间附件的显示)
	 * @return
	 */
	public String flyTimeReport(){
		return "flyTimeReport";
	}
	
//	private String returnParamStr(HashMap<String, String> query){
//		String params = "params=ID="+query.get("ID");
//		return params;
//	}
	/**
	 * 根据呈批件类型 及 申报信息id查找对应呈批件
	 * @return
	 */
	public String findDocument(){
		TfQualDeclarDocument document = tfQualDeclarDocumentBS.findDocument(planType,declaredinfoid);
		if(document!=null){
			documentId= document.getId();
		}
		return "json";
	}
	
	/**
	 * 生成呈批件
	 */
	public String toCreateDocument(){
		try{
			String pilots = "";
			String title = "";
			TfQualDeclarDocument document = new TfQualDeclarDocument();
			//此处待驳回完成后追加条件 state
			if(!ids.equals("")){
				String idsStr = Util.toStringIds(ids);
				//要训练人员集合
				tfQualDeclaraPilotList = tfQualDeclaraPilotBS.getPilotByIds(idsStr);
				//已报告:GSZY_R,已更新:GSZY_U,,完成:OVEREND
				//如果是最后一个节点未报告阶段 则将状态改为已报告 如果是已更新则生成报告状态变成OVEREND
				if(approvalSymbol != null && approvalSymbol.equals("quaApproveReport")){
					for(TfQualDeclaraPilot t : tfQualDeclaraPilotList){
						if(t.getState() != null && t.getState().equals("GSZY")){
							t.setState("GSZY_R");
							tfQualDeclaraPilotBS.saveOrUpdate(t);
						}
					}
				}else if(approvalSymbol != null && approvalSymbol.equals("quaApproveNotice")){
					for(TfQualDeclaraPilot t : tfQualDeclaraPilotList) {
						if(t.getState() != null && t.getState().equals("GSZY_U")){
							t.setState("OVEREND");
							tfQualDeclaraPilotBS.saveOrUpdate(t);
						}
					}
				}
			}
			//根据呈批件表示进行判断
			if(approvalSymbol != null && !approvalSymbol.equals("")){
				if(approvalSymbol.equals("quaApproveNotice") || approvalSymbol.equals("quaApproveReport")){
					//抄送单位 
					document.setCopysendUnit("公司飞行技术管理部$_$人力资源部");
				}else if(approvalSymbol.equals("gsfg")){
					//签发人
					document.setIssuer("常厚东");
				}else{
					document.setIssuer(this.getUser().getName());
				}
				//判断是否之前生成过该报表 如果生成过 则删除
				if(approvalSymbol.equals("dd") || approvalSymbol.equals("zd") || approvalSymbol.equals("gsfg")){
					tfQualDeclarDocumentBS.delDocIfCreatedForDeclara(this.getUser().getName(), approvalSymbol, declaredinfoid);
				}else{
					tfQualDeclarDocumentBS.delDocIfCreatedForVerify(approvalSymbol, ids);
				}	
			}
			if(tfQualDeclaraPilotList != null && tfQualDeclaraPilotList.size() != 0){
				for (int i = 0; i < tfQualDeclaraPilotList.size(); i++){
					pilots += tfQualDeclaraPilotList.get(i).getCmPeople().getName();
					if(i + 1 != tfQualDeclaraPilotList.size()){
						pilots += "$_$";
					}
				}
				//添加要的训练人员
				document.setPilots(pilots);
			}
			if(declaredinfoid != null && !declaredinfoid.equals("")){
				//申报信息
				tfQualDeclarInfo = tfQualDeclarInfoBS.findById(TfQualDeclarInfo.class, declaredinfoid);
				if(tfQualDeclarInfo != null){
					//添加申报计划ID
					document.setDeclaredinfoid(tfQualDeclarInfo.getDeclaredinfoid());
					//获得资质类型(小类)
					tfQualBaseType = tfQualDeclarInfo.getTfQualBaseType();
				}
			}else{
				document.setDeclaredinfoid("");
				//此处get0是因为同一批人的资质类型相同
				tfQualBaseType = tfQualDeclaraPilotList.get(0).getTfQualBaseType();
			}
			
			//判断原始级别(只有新雇员没有原始级别)
			if(tfQualBaseType != null && null != tfQualBaseType.getOriginalgrade()){
				//如果是机长
				if(tfQualBaseType.getOriginalgrade().getPtGradeId().contains("F") && !tfQualBaseType.getOriginalgrade().getPtGradeId().equals("5")){
					document.setUpType("副驾驶");//升级类别 副驾驶或机长
				}else if(tfQualBaseType.getOriginalgrade().getPtGradeId().contains("T")){
					document.setUpType("教员"); 
				}else{
					document.setUpType("机长"); 
				}
				if(tfQualBaseType.getTfQualBaseTgroup().getTypegroupid().equals("F4-F5") || tfQualBaseType.getTfQualBaseTgroup().getTypegroupid().equals("A2-TA")){
					document.setPilotGradeType("升级"+tfQualBaseType.getTargetgrade().getPtGradeId());//升级的等级 晋级  升级 F2
				}else{
					document.setPilotGradeType("晋级"+tfQualBaseType.getTargetgrade().getPtGradeId());//升级的等级 晋级  升级 F2
				}
				//机型
				baseAirPlanType = tfQualDeclarDocumentBS.getBaseAirPlanTypeByAtrId(tfQualBaseType.getTargetatrid().getAtrid());
				if(baseAirPlanType != null){
					document.setAtrid(baseAirPlanType.getAtrdesc());
				}
			}
			
			if(taskId != null && !taskId.equals("")){
				ProcessBase base = new ProcessBase();
				String taskName = base.getActivityNameByTaskId(taskId);
				if(taskName.equals("公司飞管专员申报")){
					//局方
					document.setBureau("民航华北地区管理局飞行标准处");
					//技术等级认可日期
					document.setTechStandAppDate(DateTool.getNow());
				}
			}
			
			SysOrganization org = getUserOrg();
			String planType = "";
			Calendar cal = Calendar.getInstance();
			//当前年份
			int year = cal.get(Calendar.YEAR);
			//获得分公司名称
			String orgName = org.getName().substring(0, 2);
			//如果是天津分公司飞行部
			if(getUserOrg().getId().equals(ConstantList.TJFXB)){
				//设置接收单位为飞管
				document.setReceiveOrg(getReceiveOrg(tfQualBaseType.getTypedesc(),getUserOrg()));
				//联系电话
				document.setContectPhone("022-24901519");
			}else{
				//设置接收单位
				document.setReceiveOrg(getReceiveOrg(tfQualBaseType.getTypedesc(),getUserOrg()));
				//联系电话
				document.setContectPhone(this.getUser().getPhone());
			}
			//判断部门
			if(org.getId().equals(ConstantList.TJFXB)){//天津飞行部请示
				//向呈批件中添加 申报计划ID(DECLAREDINFOID)
				String numStr=tfQualDeclarDocumentBS.getMaxHeadNum(""+orgName+"飞呈训练","");
				StringBuffer sb = new StringBuffer();
				sb.append(""+orgName+"飞呈训练 [" + year + "] " + numStr);
				document.setDocumentId(sb.toString());
				//向呈批件中添加 机构类别，印发机构，印章id
				org = tfQualDeclarDocumentBS.findById(SysOrganization.class,ConstantList.TJFXB);
				document.setOrgGrade(org.getName());//机构类别
				document.setPrintOrg(org.getName());//印发机构
				tfQualDeclarSeal = tfQualDeclarDocumentBS.getTfQualDeclarSealByOrgId(org.getId());
				if(tfQualDeclarSeal != null)
				document.setSealId(tfQualDeclarSeal.getId());
			}else if(org.getId().equals(ConstantList.TJFGB) || org.getId().equals(ConstantList.TJFGS)){//天津分公司飞行技术管理部||天津分公司
				//判断呈批件标识,向呈批件中添加 申报计划ID(DECLAREDINFOID)
				if(approvalSymbol.equals("zd")){//总队
					String numStr=tfQualDeclarDocumentBS.getMaxHeadNum("国航股份"+orgName+"飞技发训练","");
					StringBuffer sb = new StringBuffer();
					sb.append("国航股份"+orgName+"飞技发训练 [" + year + "] " + numStr);
					document.setDocumentId(sb.toString());
				}else if(approvalSymbol.equals("quaApproveNotice")){//资质认可通知
					String numStr=tfQualDeclarDocumentBS.getMaxHeadNum("国航股份"+orgName+"飞技发资质","");
					document.setDocumentId("国航股份"+orgName+"飞技发资质 [" + year + "] " + numStr);
				}else if(approvalSymbol.equals("gsfgTrainNotice")){//公司飞管训练通知
					String numStr=tfQualDeclarDocumentBS.getMaxHeadNum("国航股份"+orgName+"飞技发训练","");
					document.setDocumentId("国航股份"+orgName+"飞技发训练 [" + year + "] " + numStr);
				}
				//向呈批件中添加 机构类别，印发机构，印章id
				org = tfQualDeclarDocumentBS.findById(SysOrganization.class,ConstantList.TJFGB);
				document.setOrgGrade(org.getName());//机构类别
				document.setPrintOrg(org.getName());//印发机构
				tfQualDeclarSeal = tfQualDeclarDocumentBS.getTfQualDeclarSealByOrgId(org.getId());
				if(tfQualDeclarSeal != null)
				document.setSealId(tfQualDeclarSeal.getId());
			}else if(org.getId().equals(ConstantList.GHFGBID)){//飞行技术管理部 训练通知
				//判断呈批件标识,向呈批件中添加 申报计划ID(DECLAREDINFOID)
				if(approvalSymbol != null && !approvalSymbol.equals("")){
					if(approvalSymbol.equals("quaApproveReport")){//资质认可报告
						String numStr = tfQualDeclarDocumentBS.getMaxHeadNum("国航股份飞技发资质","1");
						document.setDocumentId("国航股份飞技发资质 [" + year + "] " + numStr+"-1");// 资质认可报告
					}else if(approvalSymbol.equals("quaApproveNotice")){//资质认可通知
						String numStr = tfQualDeclarDocumentBS.getMaxHeadNum("国航股份飞技发资质","2");
						document.setDocumentId("国航股份飞技发资质 [" + year + "] " + numStr+"-2");// 资质认可通知
					}else if(approvalSymbol.equals("gsfgTrainNotice")){//公司飞管训练通知
						String numStr = tfQualDeclarDocumentBS.getMaxHeadNum("国航股份飞技发训练","2");
						document.setDocumentId("国航股份飞技发训练 [" + year + "] " + numStr+"-2");
					}else if(approvalSymbol.equals("gsfg")){//公司飞管
						String numStr = tfQualDeclarDocumentBS.getMaxHeadNum("国航股份飞技发训练","1");
						document.setDocumentId("国航股份飞技发训练 [" + year + "] " + numStr+"-1");
					}
				}
				//向呈批件中添加 机构类别，印发机构，印章id
				org = tfQualDeclarDocumentBS.findById(SysOrganization.class,ConstantList.GHFGBID);
				document.setOrgGrade(org.getName());//机构类别
				document.setPrintOrg(org.getName());//印发机构
				tfQualDeclarSeal = tfQualDeclarDocumentBS.getTfQualDeclarSealByOrgId(org.getId());
				if(tfQualDeclarSeal != null)
				document.setSealId(tfQualDeclarSeal.getId());
			}
			
			planType = approvalSymbol;
			//呈批件类别
			document.setPlanType(planType);
			//联系人
			document.setContectPerson(this.getUser().getName());
			//标题红头
			document.setTitle(title);
			//印发日期
			document.setPrintDate(DateTool.getNow());
			//添加创建时间及创建人
			document.setCreateDate(DateTool.getNow());
			document.setCreater(this.getUser().getName());
			TfQualDeclarDocument documentTemp = tfQualDeclarDocumentBS.save(document);
			documentId = documentTemp.getId();
			//把documentId保存到pilot表
			if(approvalSymbol != null && !approvalSymbol.equals("dd") && !approvalSymbol.equals("zd") && !approvalSymbol.equals("gsfg")){
				//审核阶段
				tfQualDeclarDocumentBS.addTableInfoToPolit(approvalSymbol, ids, documentId);
			}
			this.message = this.getSuccessMessage("呈批件生成成功！", "", "", "");
		}catch (Exception e) {
			this.message = this.getFailMessage("呈批件生成失败！");
			tfQualDeclarDocumentBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	/**
	 * 接收单位
	 * @param subGroupId
	 * @param org
	 * @return
	 */
	public String getReceiveOrg(String subGroupId ,SysOrganization org){
		String orgName = "";
		SysOrganization orgTemp = null;
		//通过某个流程的某个节点 判断接收单位
//		if((subGroupId.contains("F4-F5")||subGroupId.contains("TB-TC")))
//		{
			//if(org.getId().equals(ConstantList.TJFXBZS))
			if(org.getId().equals(ConstantList.TJFXB)){
				orgTemp=tfQualifiedDeclareBS.findById(SysOrganization.class, ConstantList.TJFGB);
				orgName=orgTemp.getName();//如果是天津分公司飞行部直属 下发给天津飞管部
			}else if(getUserOrg().getId().equals(ConstantList.TJFGB)||getUserOrg().getId().equals(ConstantList.TJFGS)){
				orgTemp=tfQualifiedDeclareBS.findById(SysOrganization.class, ConstantList.GHFGBID);
				orgName="国航股份"+orgTemp.getName();//如果是天津分公司飞管部下发给 国航股份飞行技术管理部
			}else if(getUserOrg().getId().equals(ConstantList.GHFGBID)){
//				orgTemp=tfQualifiedDeclareBS.findById(SysOrganization.class, ConstantList.GHFGB);
//				orgName="国航股份"+orgTemp.getName();//如果是天津分公司飞管部下发给 国航股份飞行技术管理部
				if(approvalSymbol==null || approvalSymbol.equals("quaApproveReport")){
					orgName="民航华北地区管理局飞行标准处";
				}else{
					orgName="公司飞行技术管理部$_$人力资源部";
				}
			}
//		}
		return orgName;
	}
	    public String toChineseCharI(String intString)throws NumberFormatException
	    {
	        
	        StringBuffer ChineseCharI=new StringBuffer(intString);
	        String rintString=ChineseCharI.reverse().toString();
	        ChineseCharI.delete(0,ChineseCharI.length());
	        int unitIndex=0;
	        int intStringLen=intString.length();
	        int intStringnumber=0;
	        boolean addZero=false;
	        for(int i=0;i<intStringLen;i++){
	            unitIndex=i%UNIT.length;
	            if(!Character.isDigit(rintString.charAt(i)))    {
	                throw new NumberFormatException("数字中含有非法字符");            
	            }    
	            intStringnumber=Character.digit(rintString.charAt(i),10);
	            if(intStringnumber==0){
	                addZero=true;
	            }else{
	                if(addZero&&ChineseCharI.length()!=0){
	                    ChineseCharI.append(CHAINIESFIGURE1[0]);
	                }
	                if(i>0){
	                    ChineseCharI.append(UNIT[unitIndex]);
	                }
	                ChineseCharI.append(CHAINIESFIGURE1[intStringnumber]);    
	                addZero=false;                
	            }
	        }
	        if(ChineseCharI.length()==0){
	            return "零";
	        }
	        return     ChineseCharI.reverse().toString();
	    }
	    
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize = false)
	public ITfQualDeclarDocumentBS getTfQualDeclarDocumentBS() {
		return tfQualDeclarDocumentBS;
	}

	public void setTfQualDeclarDocumentBS(
			ITfQualDeclarDocumentBS tfQualDeclarDocumentBS) {
		this.tfQualDeclarDocumentBS = tfQualDeclarDocumentBS;
	}
	@JSON(serialize = false)
	public String getDeclaredinfoid() {
		return declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
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
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
	@JSON(serialize = false)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}
	@JSON(serialize = false)
	public ITfQualDeclarInfoBS getTfQualDeclarInfoBS() {
		return tfQualDeclarInfoBS;
	}

	public void setTfQualDeclarInfoBS(ITfQualDeclarInfoBS tfQualDeclarInfoBS) {
		this.tfQualDeclarInfoBS = tfQualDeclarInfoBS;
	}
	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}
	@JSON (serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}
	@JSON (serialize = false)
	public BaseAirPlanType getBaseAirPlanType() {
		return baseAirPlanType;
	}

	public void setBaseAirPlanType(BaseAirPlanType baseAirPlanType) {
		this.baseAirPlanType = baseAirPlanType;
	}
	@JSON (serialize = false)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	//@JSON (serialize = false)不要
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	@JSON (serialize = false)
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	@JSON (serialize = false)
	public TfQualDeclarSeal getTfQualDeclarSeal() {
		return tfQualDeclarSeal;
	}

	public void setTfQualDeclarSeal(TfQualDeclarSeal tfQualDeclarSeal) {
		this.tfQualDeclarSeal = tfQualDeclarSeal;
	}
	@JSON (serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	//@JSON (serialize = false)不要
	public String getApprovalSymbol() {
		return approvalSymbol;
	}
	public void setApprovalSymbol(String approvalSymbol) {
		this.approvalSymbol = approvalSymbol;
	}
	@JSON (serialize = false)
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	@JSON (serialize = false)
	public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
		return tfQuaApplyJbpmBS;
	}

	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
	}
	@JSON (serialize = false)
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
	@JSON (serialize = false)
	public String getSecondCreate() {
		return secondCreate;
	}

	public void setSecondCreate(String secondCreate) {
		this.secondCreate = secondCreate;
	}
	@JSON (serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	@JSON (serialize = false)
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	@JSON (serialize = false)
	public String getFlyTimeId() {
		return flyTimeId;
	}

	public void setFlyTimeId(String flyTimeId) {
		this.flyTimeId = flyTimeId;
	}
	
}
