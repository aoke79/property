package com.sms.training.qualification.web.action.tfQuaApply;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.business.IProcesshistoryBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQuaApplyInfo;
import com.sms.training.qualification.bean.TfQuaApplyProcess;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.bean.TfQualInspectionReport;
import com.sms.training.qualification.bean.TfQualLessonComment;
import com.sms.training.qualification.bean.TfQualPilotAppinformation;
import com.sms.training.qualification.bean.TfQualPilotConditions;
import com.sms.training.qualification.bean.TfQualPilotCourselist;
import com.sms.training.qualification.bean.TfQualPilotFlyrecordbook;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.bean.TfQualRouteReport;
import com.sms.training.qualification.bean.TfWorkflowDesign;
import com.sms.training.qualification.business.ITfQuaApplyBS;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.business.ITfQualBaseAccessConditionsBS;
import com.sms.training.qualification.business.ITfQualBasePilotInfoBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualBaseWorkOrderBS;
import com.sms.training.qualification.business.ITfQualCheckJobCardBS;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
import com.sms.training.qualification.business.ITfQualDeclarInfoBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
import com.sms.training.qualification.business.ITfQualInspectionReportBS;
import com.sms.training.qualification.business.ITfQualLessonCommentBS;
import com.sms.training.qualification.business.ITfQualPilotConditionsBS;
import com.sms.training.qualification.business.ITfQualPilotDetailBS;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;
import com.sms.training.qualification.business.ITfQualPilotFlyrecordbookBS;
import com.sms.training.qualification.business.ITfQualPilotLicenseRateAppBS;
import com.sms.training.qualification.business.ITfQualRouteReportBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.business.ITfWorkflowDesignBS;
import com.sms.training.qualification.business.service.TfQuaSignatureBS;
import com.sms.training.qualification.business.service.TfQualInspectionTrainBS;
import com.sms.training.qualification.business.service.TfQualPilotLicenceBS;
import com.sms.training.qualification.business.service.TfQualPilotTechrecordBS;

/**
 * 制定资质申请流程action
 * 
 * @author QLL date 2012-05-11
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "toPilotListTeacher", location ="/sms/training/qualification/quaApply/quaApplySuccess_teacher.jsp"),
		@Result(name = "toPilotList", location ="/sms/training/qualification/quaApply/quaApplySuccess_gaojinchuan.jsp"),
		@Result(name = "toPilotList2", location ="/sms/training/qualification/quaApply/quaApplySuccess_liusiyuan.jsp"),
		@Result(name = "toPilotList3", location ="/sms/training/qualification/quaApply/quaApplySuccess_wangfei.jsp"),
		@Result(name = "toPilotList4", location ="/sms/training/qualification/quaApply/quaApplySuccess_wangnan.jsp"),
		@Result(name = "toLsyForm", location ="/sms/training/qualification/quaApply/quaApplySubmit_liusiyuan.jsp"),
		@Result(name = "toCondition22", location ="/sms/training/qualification/quaApply/quaAccessCondition22.jsp"),	
		@Result(name = "toCondition24", location ="/sms/training/qualification/quaApply/quaAccessCondition24.jsp"),	
		@Result(name = "toCondition25", location ="/sms/training/qualification/quaApply/quaAccessCondition25.jsp"),
		@Result(name = "toCondition21", location ="/sms/training/qualification/quaApply/quaAccessCondition21.jsp"),
		@Result(name = "toFeiXingYuanForm", location ="/sms/training/qualification/quaApply/quaAccessCondition2.jsp"),
		@Result(name = "toZhuanYuanForm", location ="/sms/training/qualification/quaApply/quaApplySubmitzy.jsp"),
		@Result(name = "accessCondition", location ="/sms/training/qualification/quaApply/quaAccessCondition.jsp"),
		@Result(name = "accessConditionFirst", location ="/sms/training/qualification/quaApply/quaAccessConditionFirstOne.jsp"),
		@Result(name = "pilotOwnCondition", location ="/sms/training/qualification/conditions/pilotOwnerCondition.jsp"),
		@Result(name = "toShowAccessCondition", location ="/sms/training/qualification/conditions/pilotAndAccessConditionList.jsp"),
    	@Result(name = "submit", location ="/sms/training/qualification/quaApply/quaApplySubmit.jsp"),
	    @Result(name = "toSbGuoCheng", location ="/sms/training/qualification/quaApply/quaThrouthAudit2.jsp"),
		@Result(name = "list", location = "/sms/training/englishtrain/tfEnTrainPlan/tfEnTrainMonPlanList.jsp"),
		@Result(name = "loadBrt", location = "/sms/training/englishtrain/tfEnTrainScene/trainSceneReportShow.jsp"),
		@Result(name = "toxlShenCha", location = "/sms/training/qualification/qualDeclaration/flightDataRecorder.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "QUAL_TR_qualVerifyStage", location = "/sms/training/qualification/quaApplyTr/qualVerifyStageTR.jsp"),
		@Result(name = "QUAL_FT_qualVerifyStage", location = "/sms/training/qualification/tfQualFtdTeacher/qualVerifyStageFtd.jsp"),
		@Result(name = "QUAL_TR_toCondition21", location ="/sms/training/qualification/quaApplyTr/teachersFill.jsp"),
		@Result(name = "QUAL_TR_toCondition22", location ="/sms/training/qualification/quaApplyTr/inspecter_Fill.jsp"),
		@Result(name = "QUAL_TR_toCondition24", location ="/sms/training/qualification/quaApplyTr/branch_Commissioner_Fill.jsp"),
		@Result(name = "QUAL_TR_toCondition25", location ="/sms/training/qualification/quaApplyTr/company_Specialist_Fill.jsp"),
		@Result(name = "reportPage", location = "/sms/training/qualification/quaApplyInspector/reportPage.jsp"),

})
public class TfQuaApplyAction  extends BaseAction {
	
		private static final long serialVersionUID = 1L;
		public TfQuaApplyAction(){

		}
		// 添加成功表示
		private boolean successFlag = true;
		// 资质申请的service对象
		private ITfQuaApplyBS tfQuaApplyBS;
		// 当前模块名称
		private String moduleName = "TfQuaApply";
		// 资质申请的流程操作对象
		private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
		// 资质申请流程
		private TfQuaApplyProcess tfQuaApplyProcess;
		private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
		private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
		private List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList;
		private List<TfQualDeclarInfo> tfQualDeclarInfoList;
		private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
		private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
		private TfQualDeclarInfo tfQualDeclarInfo;
		// 消息实体
		private Message message;
		// 任务传递
		private String taskId;
		private String processId;
		//保存流程信息的公共表
		private TfWorkflowDesign tfWorkflowDesign;
		private ITfWorkflowDesignBS tfWorkflowDesignBS;
		private IProcesshistoryBS processhistoryBS;
		private ProcessBase processBase = new ProcessBase();
		private String opinionInfo;
		private String signingid;
		private String userId;
		private String typeids;//资质类型
		private String state;
		//用来刷新待申报信息页面
		private String declarInfoTabId;
		private TfQuaApplyInfo tfQuaApplyInfo;
		ArrayList<TfQuaApplyInfo> qualificationApplyList = new ArrayList<TfQuaApplyInfo>();
		//申报信息ID
		private String declaredinfoid="";
		private String planInfoName;
		private String fgsZhuanYuanSpYj;
		private TfQuaSignatureBS tfQuaSignatureBS;
		private String dateNow;
		private String imgUrl;
		private String ids="";
		private List<TfQualBaseSignature> tfQualBaseSignatureList;
		private CmPeople cmPeople;
		private Date qianmingDate;
		private String orgName;
		private String peopleId;
		private String formUrl; 
		private String ifShow;
		private String typedesc;
		private String typeId;
		private TfQualDeclaraPilot tfQualDeclaraPilot;
		private TfQualPilotLicence tfQualPilotLicence;
		private List<TfQualPilotLicence> tfQualPilotLicenceList;
		private TfQualPilotTechrecord tfQualPilotTechrecord;
		private List<TfQualPilotTechrecord> tfQualPilotTechrecordList;
		private TfQualBaseType tfQualBaseType;
		private TfQualPilotTechrecordBS tfQualPilotTechrecordBS;
		private Date ptrapprovedDate;
		private BaseAirPlanType baseAirplantype;
		// 分页对象
		private SysPageInfo sysPageInfo = new SysPageInfo();
		private TfQualPilotLicenceBS tfQualPilotLicenceBS;
		private ITfQualDeclarInfoBS tfQualDeclarInfoBS;
		private ITfQualBaseTypeBS tfQualBaseTypeBS;
		private ITfQualBaseAccessConditionsBS tfQualBaseAccessConditionBS;
		private TfQualBaseAccessconditions tfQualBaseAccessConditions;
		private List<TfQualBaseAccessconditions> tfQualBaseAccessConditionList;
		//准入信息
		private List <TfQualPilotConditions> tfQualPilotConditionsList;
		private ITfQualPilotConditionsBS tfQualPilotConditionsBS;
		//准入基本信息
		private  List<TfQualBaseConditions> tfQualBaseConditionsList;
		private ITfQualBasePilotInfoBS tfQualBasePilotInfoBS;
		private TfQualBasePilotInfo tfQualBasePilotInfo;
		private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
		private String subProcessid;
		private ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS;
		private List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStaylist;
		private TfQualDeclaraPilotStay tfQualDeclaraPilotStay;
		//资质类型 子类id
		private String subGroupId;
		private String detailsid;
		//显示签名图片的userid
		private String userid;
		private static final String NAV_TAB_ID="qualApply_tabId";
		private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS; 
		//定义一个list 用来存放是否已上传文件的标志
		private List<Integer> statusList=new ArrayList<Integer>();
		//以下为 所有下发验证  需要用到的
		private  TfQualInspectionReport tfQualInspectionReport;
		private ITfQualInspectionReportBS tfQualInspectionReportBS;
		private ITfQualBaseWorkOrderBS tfQualBaseWorkOrderBS;
		private TfQualPilotAppinformation tfQualPilotAppinformation;
		private ITfQualPilotLicenseRateAppBS tfQualPilotLicenseRateAppBS; 
		private TfQualBaseWorkOrder tfQualBaseWorkOrder;
		private TfQualPilotFlyrecordbook tfQualPilotFlyrecordbook;
		private ITfQualPilotFlyrecordbookBS tfQualPilotFlyrecordbookBS;
		private TfQualLessonComment tfQualLessonComment;
		private ITfQualLessonCommentBS tfQualLessonCommentBS;
		private ITfQualRouteReportBS tfQualRouteReportBS;
		//判断飞行经历记录本是否保存
		private boolean flySave;
		//判断驾驶员飞行记录本 中  检查员   是否填单
		private boolean inspector;
		//判断驾驶员飞行记录本 中  代替检查员填单是否完成
		private boolean inspectorName;
		//判断驾驶员飞行记录本 中  分公司   是否填单
		private boolean branch;
		//判断驾驶员飞行记录本 中  公司技术负责人   是否填单
		private boolean technical; 
		//判断“本场训练单”   是否填单
		private boolean practicebook;
		//判断 “驾驶员飞行记录本”局方是否填写了意见
		private boolean bureaucomm;
		//判断普通课    是否保存
		private boolean lessonSave;
		//判断“民用航空器驾驶员执照和等级申请表” 是否填单
		private boolean pilotApp;
		//判断考试工作单   是否填单
		private boolean exambook;	
		//判断航线飞行检查表   是否填单
		private boolean roote;
		//获取当下如F1-F2下得所有资质类型
		private List<TfQualBaseType> tfQualBaseTypeList;
		//资质小类 业务层对象
		private String likePilotName;
		//大分类id
		private String qtGroupId;
		private ITfQualPilotDetailBS tfQualPilotDetailBS;
		//判断是否是分公司飞管领导
		private boolean isfgsfgld;
		//训练记录业务层对象
		private TfQualInspectionTrainBS tfQualInspectionTrainBS;
		//用来判定张春雷 是否填写了驾驶员训练记录
		private boolean driverFlag; 
		//查询条件 审核状态
		private String stateSel;
		//查询条件 飞机类型
		private String planeSel;
		//飞机类型列表
		private List<BaseAirPlanType> airPlanTypeList;
		//被选中序号的字符串
		private String sels;
		//basetype的id
		private String typeSel;
		//查询条件 所属机构
		private String orgSel;
		//选择机构列表
		private List<SysOrganization> sysOrgList;
		//熟练检查单 业务层对象
		private ITfQualCheckJobCardBS checkJobCardBS;
		//给一个标记判断熟练检查单是否填写了
		private boolean checkJobCardIsSave;
		private List<TfQualPilotCourselist> tfQualPilotCourselistList;
		private TfQualPilotCourselist tfQualPilotCourselist;
		private String courselistid;
		//流程进行的阶段标识  英语评分员、英语考试员流程用到
		private String stage;
		
		
		/**
		 * 根据首页大类获得人员列表
		 * @return
		 */
		public String toDeclarPilotList(){
			String isT = "";
			try{
				userId=getUser().getUserId();
				if(subGroupId!=null && !subGroupId.equals("")){
					List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
					isT = this.tfQualDeclaraPilotBS.checkIsT(roleList);
//	                               此处需要根据不同角色、计划、组进行分类来获取人员列表
					if("T".equals(isT) || "C".equals(isT) || "TC".equals(isT)){
//	伶姐代码					tfQualPilotCourselistList=tfQuaApplyBS.getPiotByUserId(getUser(), typeInfo,likePilotName);
						tfQualPilotCourselistList=this.tfQualDeclaraPilotBS.getPilotListByC(roleList,subGroupId, getUserOrg(),getUser(),likePilotName,isT );
					}else{
						tfQualDeclaraPilotList=this.tfQualDeclaraPilotBS.getPilotList(subGroupId, getUserOrg().getId(), roleList);
						// 判断是否已经上传文件   局方委任代表流程  王菲上传文件页面
						for(int t=0,len=tfQualDeclaraPilotList.size();t<len;t++) {
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}	
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return genReturnPage(qtGroupId,isT);
		}
		
		private String genReturnPage(String qtGroupId,String isT){ 
			String page="";
			if(isT.equals("T")||isT.equals("C") || isT.equals("TC")){
				page="toPilotListTeacher";
			}else{
				page="toPilotList";//默认页面
				if("QUAL_TR".equals(qtGroupId)||"QUAL_FT".equals(qtGroupId)){
					page=qtGroupId+"_qualVerifyStage";
				}
			}
//			1	QUAL_YB	一般资格类
//			2	QUAL_UP	升级类
//			3	QUAL_TR	转机型类
//				----5	QUAL_ZZ	证照类
//			6	QUAL_SQ	特殊资格类
//			7	QUAL_FT	教员资质
//			8	QUAL_CT	飞行检查员
//				----9	QUAL_LC	外部飞行员
//			10	QUAL_EN	ICAO英语签注
//			11	QUAL_NE	新雇员类
//			12	QUAL_QT	其它类
			return page;
		}
		/**
		 * 根据首页大类获得人员列表
		 * @return
		 */
		public String toDeclarPilotList2(){
//			此处需要根据不同角色、计划、组进行分类
			try{
				if(subGroupId!=null && !subGroupId.equals("")){
					//给页面传去 subGroupId下面的所有资质类型
					tfQualBaseTypeList = tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
					List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
					tfQualDeclaraPilotList=this.tfQualDeclaraPilotBS.getPilotList2(subGroupId, getUserOrg().getId(), roleList);
					
					//此处是做假的 该根据高津川分组 获取分组ID获取人
					if( tfQualDeclaraPilotList!=null && tfQualDeclaraPilotList.size()!=0){
						declaredinfoid=tfQualDeclaraPilotList.get(0).getTfQualDeclarInfo().getDeclaredinfoid();
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "toPilotList2";
		} 
		
		/**		 
		 *  获得资质更新的人员列表
		 * @return
		 */
		public String toDeclarPilotList3(){
//			此处需要根据不同角色、计划、组进行分类
			try{
				if(subGroupId!=null && !subGroupId.equals("")){
					List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
					tfQualDeclaraPilotList=this.tfQualDeclaraPilotBS.getPilotList3(subGroupId, stateSel, typeSel, orgSel, roleList, getUserOrg().getId());
					if( tfQualDeclaraPilotList!=null && tfQualDeclaraPilotList.size()!=0){
						for(int t=0,len=tfQualDeclaraPilotList.size(); t<len; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
						declaredinfoid=tfQualDeclaraPilotList.get(0).getTfQualDeclarInfo().getDeclaredinfoid();
					}
				}
				tfQualBaseTypeList = tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
				sysOrgList = tfQualDeclaraPilotBS.getSomeSysOrgs();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "toPilotList3";
		}
		
		/**
		 * 王楠
		 * @return
		 */
		public String toDeclarPilotList4(){
//			此处需要根据不同角色、计划、组进行分类
			try{
				if(subGroupId!=null && !subGroupId.equals("")){
					List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
					tfQualDeclaraPilotList=this.tfQualDeclaraPilotBS.getPilotList3(subGroupId, stateSel, typeSel, orgSel, roleList, getUserOrg().getId());
					if( tfQualDeclaraPilotList!=null && tfQualDeclaraPilotList.size()!=0){
						declaredinfoid=tfQualDeclaraPilotList.get(0).getTfQualDeclarInfo().getDeclaredinfoid();
					}
				}
				tfQualBaseTypeList = tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
				sysOrgList = tfQualDeclaraPilotBS.getSomeSysOrgs();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return "toPilotList4";
		}
		
		
		/**
		 * 页面根据资质类型 查询所有待训练的人员待办
		 * zhong
		 * @return
		 */
		public String sreachPilotList(){
			try{
				if(typeids!=null && !typeids.equals("")){
					tfQualBaseTypeList = tfQualBaseTypeBS.getListBySubGroupId(subGroupId);
					StringBuffer state = new StringBuffer("");
					CmUser user = getUser();
					List<SysRole>  roleList=tfQuaApplyJbpmBS.getSessionRoleList(user);
					StringBuffer orgRole = new StringBuffer("");
					for (int i = 0,n=roleList.size(); i <n ; i++){
						if(i!=roleList.size()-1){
							orgRole.append("'").append(getUserOrg().getId()).append("-").append(roleList.get(i).getRoleName()).append("',");
						}else{
							orgRole.append("'").append(getUserOrg().getId()).append("-").append(roleList.get(i).getRoleName()).append("'");
						}
						if(roleList.get(i).getRoleName().contains("资质-M-j-F1-F2-训练专员")){
							state.append("'DXL',");
						}
					}
					if(state.length()>0){
						state = state.deleteCharAt(state.length()-1);
					}
					//获取待处理人总数
					tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getCountByOrgAndRole2(state.toString(),orgRole.toString(),typeids);
					
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
			return "toPilotList2";
		}
		/**
		 * zhong  教员、检察员 根据name来搜索
		 * @return
		 */
		public String toDeclarPilotList3ByName(){
		  try{
			if(subGroupId!=null && !subGroupId.equals("")){
				String state="";
				CmUser user = getUser();
				List<SysRole>  roleList=tfQuaApplyJbpmBS.getSessionRoleList(user);
				String orgRole="";
				for (int i = 0,n=roleList.size(); i <n ; i++){
					if(i!=roleList.size()-1){
						orgRole+="'"+getUserOrg().getId()+"-"+roleList.get(i).getRoleName()+"',";
					}
					else{
						orgRole+="'"+getUserOrg().getId()+"-"+roleList.get(i).getRoleName()+"'";
					}
					if(roleList.get(i).getRoleName().contains("教员")){
						state+="'ZXL',";
					}
					else if(roleList.get(i).getRoleName().contains("检查员")){
						state+="'DJC',";
					}
				}
				//获取待处理人总数
				if(!state.equals("")){
					if(state.substring(state.length()-1, state.length()).equals(",")){ 
						state=state.substring(0,state.length()-1);
					}
				}
				tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getCountByOrgAndRole3(state,orgRole,subGroupId,likePilotName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
			return "toPilotList";
		}
		
		public String updateQualConfirm(){
			try{
				if(!ids.equals("")){
					String idsStr = Util.toStringIds(ids);
					tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);//得到所要升级人当中的所有执照   
					for (int i=0,len=tfQualDeclaraPilotList.size(); i<len; i++){
							tfQualDeclaraPilot =tfQualDeclaraPilotList.get(i); //得到其中的某个执照
							tfQualBaseType=tfQualDeclaraPilot.getTfQualBaseType(); //根据执照的得到相应的资质类型
//							baseAirplantype= tfQualBaseType.getTargetatrid();
							//如果是升级类 QUAL_UP
							if(tfQualDeclaraPilot.getTfQualBaseType().getTfQualBaseTypeGroup().getQtgroupid().equals("QUAL_UP")){
								//执照 ：取得他得目标执照类型						
								tfQualPilotLicenceList=tfQualPilotLicenceBS.getQualPilotLicenceById(tfQualDeclaraPilot.getCmPeople().getId(), tfQualBaseType.getTargetatrid().getId());
								if(tfQualPilotLicenceList == null || tfQualPilotLicenceList.size()<=0){
									  //得到原始的执照类型，并判断之前的执照是否有等级，   并把原始执照类型置为无效
									List<TfQualPilotLicence> tfQualPilotLicenceList2=tfQualPilotLicenceBS.getQualPilotLicenceById(tfQualDeclaraPilot.getCmPeople().getId(), tfQualBaseType.getOriginalatrid().getId());
									if(tfQualPilotLicenceList2 !=null && tfQualPilotLicenceList2.size()!=0){
										tfQualPilotLicence =tfQualPilotLicenceList.get(0); //得到之前的执照等级
										tfQualPilotLicence.setPlcstus("1");                //设置之前的执照无效										
										tfQualPilotLicenceBS.saveOrUpdate(tfQualPilotLicence);  //更新之前的执照
									}
									tfQualPilotLicence =new TfQualPilotLicence(); //创建新的执照
									tfQualPilotLicence.setAtrid(tfQualBaseType.getTargetatrid().getId().toString());
									tfQualPilotLicence.setPilotid(tfQualDeclaraPilot.getCmPeople().getId());//把新的执照给这个人
									tfQualPilotLicence.setPlclifecycle("1");//设置执照为什么类型 0,null
									tfQualPilotLicence.setPlccheckd(ptrapprovedDate);
//									tfQualPilotLicence.setEndorsement(ptrapprovedDate);
									tfQualPilotLicence.setPlcstus("0"); //设置执照有效
									tfQualPilotLicenceBS.save(tfQualPilotLicence);
								}else{
									tfQualPilotLicence=tfQualPilotLicenceList.get(0);
									tfQualPilotLicence.setEndorsement(ptrapprovedDate);
									tfQualPilotLicenceBS.saveOrUpdate(tfQualPilotLicence);
								}
								//等级：//如果有数据就更新状态 至同机型的为无效 没有新增 //0-现飞，1-曾飞‘
								//根据机型和用户查找是否存在技术等级
								tfQualPilotTechrecordList=tfQualPilotTechrecordBS.getQualPilotTechrecordById(tfQualDeclaraPilot.getCmPeople().getId(), tfQualBaseType.getTargetatrid().getAtrid().toString());
								//如果存在执照等级
								if(tfQualPilotTechrecordList!=null && tfQualPilotTechrecordList.size()>0){
									boolean flag = true;
									for (int j=0,lens=tfQualPilotTechrecordList.size(); j<lens; j++){
										if(tfQualPilotTechrecordList.get(j).getPtgradeid().equals(tfQualBaseType.getTargetgrade().getPtGradeId())){  
											//判断要升级的等级是否存在 ，如果有就更新状态
											tfQualPilotTechrecordList.get(j).setPtrcurrent("0");//现飞
											tfQualPilotTechrecordList.get(j).setPtrapproved(ptrapprovedDate);
											tfQualPilotTechrecordBS.saveOrUpdate(tfQualPilotTechrecordList.get(j));
											flag = false;
										}else{
											tfQualPilotTechrecordList.get(j).setPtrcurrent("1");//设置曾飞
											tfQualPilotTechrecordBS.saveOrUpdate(tfQualPilotTechrecordList.get(j));//更新当前的执照
										}
									}
									if(flag == true){
										this.saveTfQualPilotTechrecordConfirm(tfQualDeclaraPilot, tfQualPilotLicence, ptrapprovedDate);
									}
								}else{
									this.saveTfQualPilotTechrecordConfirm(tfQualDeclaraPilot, tfQualPilotLicence, ptrapprovedDate);
								}
							}
							//修改 pilot 表状态
							tfQualDeclaraPilotList.get(i).setState("GSZY_U");
							tfQualDeclaraPilotBS.save(tfQualDeclaraPilotList.get(i));
							this.message = this.getSuccessMessage("资质更新成功！", NAV_TAB_ID,"", "tf-qua-apply/tf-qua-apply!toDeclarPilotList.do?subGroupId="+subGroupId);
					}
				}
				setSuccessFlag(true);
			}catch(Exception e){
				setSuccessFlag(false);
				this.message = this.getFailMessage("资质更新失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		private void saveTfQualPilotTechrecordConfirm(TfQualDeclaraPilot tfQualDeclaraPilot,TfQualPilotLicence tfQualPilotLicence,Date ptrapprovedDate){
			tfQualPilotTechrecord=new TfQualPilotTechrecord();
			tfQualPilotTechrecord.setCmPeople(tfQualDeclaraPilot.getCmPeople());
			tfQualPilotTechrecord.setPtrcurrent("0");//现飞
			tfQualPilotTechrecord.setBaseAirplantype(tfQualDeclaraPilot.getTfQualBaseType().getTargetatrid());//执照有等级的话，就直接升级
			tfQualPilotTechrecord.setTfQualPilotLicence(tfQualPilotLicence);
			tfQualPilotTechrecord.setPtgradeid(tfQualDeclaraPilot.getTfQualBaseType().getTargetgrade().getPtGradeId());
			tfQualPilotTechrecord.setPtrapproved(ptrapprovedDate);
			tfQualPilotTechrecordBS.save(tfQualPilotTechrecord);
		}
		public String toSbGuoCheng(){
			try{
				//获取此次申报的人员信息
				if(detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				}
				tfQualDeclarInfo=tfQualDeclaraPilot.getTfQualDeclarInfo();
				tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(tfQualDeclarInfo.getProcessid());
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toSbGuoCheng";
		}
		/**
		 * 跳转到训练审查页面
		 * @return
		 */
		public String toxlShenCha(){
			try{
				if( detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					declaredinfoid=tfQualDeclaraPilot.getTfQualDeclarInfo().getDeclaredinfoid();
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toxlShenCha";
		}
		
		/**
		 * 此处 gaojinchuan分组  pilot state改变
		 * @return
		 */
		public String toXunLianConfirm(){
			try{ 
					if(!ids.equals("")){
//						String[] idStr = StringUtil.split(ids, ',');
//						String idsTemp = "";
//						for (int i = 0,n=idStr.length-1; i <n ; i++) {
//							if( i!=(n-1))
//							{
//								idsTemp+="'"+idStr[i]+"',";
//							}else
//							{
//								idsTemp+="'"+idStr[i]+"'";
//							}
//						}
						String idsStr = Util.toStringIds(ids);
						if(!idsStr.equals(""))
						tfQualDeclaraPilotList = tfQualDeclaraPilotBS.getPilotByIds(idsStr);
//						for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++)
//						{
//							tfQualDeclaraPilotBS.deleteById(TfQualDeclaraPilot.class, tfQualDeclaraPilotList.get(i).getDetailsid());
//						}
//						此处该是根据 高津川的分组计划 调整pilot 业务数据 满足教员的需求
						String orgRoleTemp ="";
						if(!declaredinfoid.equals("")){
							tfQualDeclarInfo = tfQualDeclaraPilotBS.findById(TfQualDeclarInfo.class, declaredinfoid);
							if(tfQualDeclarInfo.getDeclaredinfodesc().contains("天津")){
								orgRoleTemp= ConstantList.TJFXBDSZD+"-"+"资质-教员";
							}else{
								orgRoleTemp= ConstantList.ZDSDD+"-"+"资质-教员";
							}
						}
						
						if(tfQualDeclaraPilotList!=null && tfQualDeclaraPilotList.size()!=0){
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								tfQualDeclaraPilotList.get(i).setGroupid("111111");
								tfQualDeclaraPilotList.get(i).setState("ZXL");//人员状态 带训练
								//  根据计划信息 获得 上报机构 根据上报机构 下发任务
								tfQualDeclaraPilotList.get(i).setOrgRole(orgRoleTemp);//机构 总队三大队
								tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
							}
						}
					}
					
				if("QUAL_UP_F4-F5".equals(subGroupId) ||"QUAL_UP_A2-TA".equals(subGroupId)||"QUAL_UP_TA-TB".equals(subGroupId) ||"QUAL_UP_TB-TC".equals(subGroupId)
					||"QUAL_CT_GSFXJCY".equals(subGroupId)||"QUAL_NE_XY".equals(subGroupId)||"QUAL_EN_YYJY".equals(subGroupId) ){
					setTabIndexToReload("0");// 处理子标签页跳转问题
					this.message = this.getSuccessMessage("下达训练计划成功！", NAV_TAB_ID,"", "");  
				}else{
					setTabIndexToReload("1");
					this.message = this.getSuccessMessage("下达训练计划成功！", NAV_TAB_ID,"", "");  
				}  
				if(!ids.equals(""))
				tfQualPilotDetailBS.savePilotDetais(ids);
			}catch(Exception e){
				this.message = this.getFailMessage("下达训练计划失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}

		/**
		 * 公司技术委员会办公室主任
		 * @return
		 */
		public String toFeiGuanZrBpsConfirm(){
			try{
				SysOrganization sysOrganization = getUserOrg();
				//调整 训练人员状态
				if(detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					tfQualDeclaraPilot.setState("GSZY");
					tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
					tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
					if(!subProcessid.equals("")){
						taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
					}
					CmUser user = getUser();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
					tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员发文确认", map);
					String taskIdString = processBase.getTaskIdByPiID(subProcessid);
					tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
					if("QUAL_EN_YYJY".equals(subGroupId)){
						setTabIndexToReload("1");// 处理子标签页跳转问题
						this.message = this.getSuccessMessage("领导审核成功",NAV_TAB_ID, "","");
						return "SUCCESS";
					}else{
						setTabIndexToReload("0");// 处理子标签页跳转问题
						this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID, "" ,"");
					}  
					
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		
			/**
			 * 分公司级技术委员会主任
			 * @return
			 */
			public String toZongDuiZrBpsConfirm(){
				try{
					String state=null;//fgsZhuanYuanOver
					String orgRole=null;
					//调整 训练人员状态
					SysOrganization sysOrganization = getUserOrg();
					if("QUAL_TR_FJSZJX".equals(subGroupId)){//副驾驶转机型流程：to 分公司标准专员
						state="DZZGX";
						if(sysOrganization.getName().contains("天津")){
							orgRole= ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员";
						}else{
							orgRole= ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员";
						}
					}else if("QUAL_EN_YYJY".equals(subGroupId)){//英语教员
						state="WCPX";
						orgRole=ConstantList.GHFGBID+"-"+"资质-公司飞管领导";
					}else{//其他流程：to 公司技术委员会办公室主任
						state="GSZR";
						orgRole=ConstantList.GHFGBID+"-"+"资质-公司级技术委员会办公室主任";
					}
					
					if(detailsid!=null && !detailsid.equals("")){
						tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
						tfQualDeclaraPilot.setState(state);
						tfQualDeclaraPilot.setOrgRole(orgRole);
						tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
					}
					if(!subProcessid.equals("")){
						taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
					}
					CmUser user = getUser();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("FromOrgName", sysOrganization.getName());
					map.put("FromOrgUser", user.getName());
					if("QUAL_TR_FJSZJX".equals(subGroupId)){//副驾驶转机型流程：to 分公司标准专员
						map.put("fgsZhuanYuanOver",orgRole);
					}else if("QUAL_EN_YYJY".equals(subGroupId)){
						map.put("GsLingDao",orgRole);
					}else{
						map.put("GsZhuRen",orgRole);
					}
					if("QUAL_TR_JZZJX".equals(subGroupId)||"QUAL_TR_JYCIQ".equals(subGroupId)||"QUAL_TR_FJSZJX".equals(subGroupId)){// 机长转机型、教员CIQ、副驾驶转机型 流程
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "下发", map);
					}else if("QUAL_EN_YYJY".equals(subGroupId)){
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司领导审核", map);
					}else{//其他流程：to 公司技术委员会办公室主任
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司技术委员会办公室主任审核报批", map);
					}
					if("QUAL_EN_YYJY".equals(subGroupId)){
							setTabIndexToReload("1");// 处理子标签页跳转问题
							this.message = this.getSuccessMessage("经理审核成功",NAV_TAB_ID, "","");
							return "SUCCESS";
						}else{
							setTabIndexToReload("1");// 处理子标签页跳转问题
							this.message = this.getSuccessMessage("审核通过成功!", NAV_TAB_ID,"closeCurrent", "");
						}  
				}catch(Exception e){
					this.message = this.getFailMessage("流程扭转失败！");
					tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
					e.printStackTrace();
				}
				return "json";
			}
			/**
			 * 刘思源/wangn 分公司级飞管专员
			 * @return
			 */
			public String toFgsZyConfirm(){
				try{
					//调整 训练人员状态
					CmUser user = getUser();
					SysOrganization sysOrganization = getUserOrg();
					if(detailsid!=null &&  !detailsid.equals("") ){
						tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
						tfQualDeclaraPilot.setState("FGSZR");
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							tfQualDeclaraPilot.setOrgRole(ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
						}else{
							tfQualDeclaraPilot.setOrgRole(ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
						}
						tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
						if(!subProcessid.equals("")){
							taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						if("QUAL_TR_FJSZJX".equals(subGroupId)){
							map.put("subGroupId",subGroupId);
						}
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsZhuRen", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
						}else{
							map.put("FgsZhuRen", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任");
						}
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级技术委员会办公室主任审核报批", map);
						//如果是 副驾驶差异或者机长差异流程 陈昕下发时，也用的此方法   张会粉加
						if("QUAL_TR_FJSCY".equals(subGroupId) || "QUAL_TR_JZCY".equals(subGroupId)){
							setTabIndexToReload("0");
						}else{
							setTabIndexToReload("1");// 处理子标签页跳转问题
						}
						this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"");
					}
				}catch(Exception e){
					this.message = this.getFailMessage("流程扭转失败！");
					tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
					e.printStackTrace();
				}
				return "json";
			}

		/**
		 * 检查员   下发流程
		 * @return
		 */
			public String toJiShuShenBaoConfirm(){
				try{
					 SysOrganization sysOrganization = getUserOrg();
					 String state="";
					 String orgRole="";
					 Map<String, Object> map = new HashMap<String, Object>();
					 if(courselistid!=null && !courselistid.equals("")) {
						 tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
						 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
						 tfQualPilotCourselist.setState("COVER");
						 tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
					 }
					 if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")){
						 if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
							//判断下发节点
							 if(subProcessid!=null && !subProcessid.equals("") ){
									taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
									if(taskId!=null && !taskId.equals("") ){
										subGroupId= processBase.getVariable(taskId, "subGroupId").toString();
											if(subGroupId.equals("QUAL_UP_A2-TA")||subGroupId.equals("QUAL_UP_TA-TB")){
												state="FGSZR";
												if(sysOrganization.getParent_Fun().getName().contains("天津")){
													 orgRole= ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任";
												}else{
													 orgRole= ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级技术委员会办公室主任";
												}
												map.put("FgsZhuRen",orgRole);
											}else if(subGroupId.equals("QUAL_EN_YYJY")){
												orgRole= ConstantList.GHFGBID+"-"+"资质-公司飞管标准高级经理";
												state="GSJL";
												map.put("GsJingLi",orgRole);
											}else{
												state="DBCXL";
												if(sysOrganization.getParent_Fun().getName().contains("天津")){
													 orgRole= ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员";
												 }else{
													 orgRole= ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员";
												 }
												map.put("FgsZy",orgRole);
											}
										}
								}
							 
							 //调整 训练人员状态
							 if(detailsid!=null && !detailsid.equals("")){
								 tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
								 tfQualDeclaraPilot.setState(state);
								 tfQualDeclaraPilot.setOrgRole(orgRole);
								 tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
							 }
							//扭转流程
							CmUser user = getUser();
							map.put("FromOrgName", sysOrganization.getName());
							map.put("FromOrgUser", user.getName());
							if("QUAL_TR_JZZJX".equals(subGroupId)||"QUAL_TR_JYCIQ".equals(subGroupId)||"QUAL_TR_FJSZJX".equals(subGroupId)||"QUAL_NE_XY".equals(subGroupId)||"QUAL_NE_WJFXY".equals(subGroupId)){//机长转机型、教员CIQ、副驾驶转机型、新雇员 流程
								tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "本场训练", map);
							}else if(subGroupId.equals("QUAL_EN_YYJY")){
								tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司经理审核", map);
							}else{//其他流程
								tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "下发", map);
							}	
						 }	
					 }
					setSuccessFlag(true);
					setTabIndexToReload("0");// 处理子标签页跳转问题
					this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"");
				}catch(Exception e){
					this.message = this.getFailMessage("流程扭转失败！");
					tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
					e.printStackTrace();
				}
				return "json";
			}
			
			/**
			 * 新雇员流程检查员下发流程（分支）
			 * @return
			 */
				public String toJiShuJianchaConfirm(){
					try{
						 Map<String, Object> map = new HashMap<String, Object>();
						 if(courselistid!=null && !courselistid.equals("")){
							 tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
							 //此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
							 tfQualPilotCourselist.setState("COVER");
							 tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
						 }
						 if(tfQualPilotCourselist!=null && detailsid!=null && !detailsid.equals("")) {
							 if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
								 //调整 训练人员状态
								 if(detailsid!=null && !detailsid.equals("")){
									 tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
									 tfQualDeclaraPilot.setState("DBCXL");
									 tfQualDeclaraPilot.setOrgRole(null);
									 tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
								 }
								 //判断下发节点
								 if(subProcessid!=null && !subProcessid.equals("") ){
										map.put("FgsZy",ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
										map.put("FgsZhuRen", ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任");
										taskId=tfQuaApplyJbpmBS.getTaskIdByPiID(subProcessid);
										if(taskId!=null && !taskId.equals("") ){
										//扭转流程
										tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "to fork", map);
										}
									}
							 }	
						 }
						setSuccessFlag(true);
						setTabIndexToReload("0");// 处理子标签页跳转问题
						this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"");
					}catch(Exception e){
						this.message = this.getFailMessage("流程扭转失败！");
						tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
						e.printStackTrace();
					}
					return "json";
				}
				
				
				/**
				 * 新雇员流程本场训练和cp签注下发流程（聚合）
				 * @return
				 */
				public String toGsZyConfirm(){
					try{
						SysOrganization sysOrganization = getUserOrg();
						if(detailsid!=null &&  !detailsid.equals("") ){
							tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
							String orgRole="";
							//判断分支走了哪一路
							if(sysOrganization.getName().contains("飞行技术管理部")){
								orgRole= ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员";
								tfQualDeclaraPilot.setState("FGSZR");
								tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
							}else{
								orgRole= ConstantList.TJFGS+"-"+"资质-分公司级技术委员会办公室主任";
								tfQualDeclaraPilot.setState("DJC");
								tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
							}
							if(!subProcessid.equals("")&& !orgRole.equals("")){
								taskId=tfQuaApplyJbpmBS.getForkTaskId(subProcessid,orgRole);
							}
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("GsZhuanYuanOver", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
							//流程扭转到join
							tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "to join", map);
							//获取当前节点名称
							String taskName=tfQuaApplyJbpmBS.getTaskNameByPiID(subProcessid);
							//判断流程是否到"公司飞管专员发文确认"节点
							if(taskName.equals("公司飞管专员发文确认")){
								tfQualDeclaraPilot.setState("GSZY");
								tfQualDeclaraPilot.setOrgRole(ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
								tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclaraPilot);
								//流程扭转到结束
								String taskIdString = processBase.getTaskIdByPiID(subProcessid);
								tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "结束", map);
							}
							// 处理子标签页跳转问题
						    setTabIndexToReload("0");
							this.message = this.getSuccessMessage("下发成功！", NAV_TAB_ID, "" ,"");
						}
					}catch(Exception e){
						this.message = this.getFailMessage("流程扭转失败！");
						tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
						e.printStackTrace();
					}
					return "json";
				}		
				
		/**
		 * 获取时间
		 * @return
		 */		
		@JSON(serialize = false)
		public String getTimeNow(){
			try{
				userid=this.getUser().getUserId();
				dateNow=DateTool.getDateTime();
				setSuccessFlag(true);
			}
			catch (Exception e) {
				setSuccessFlag(false);
				this.message = this.getFailMessage("获得当前时间失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		
		/**
		 * 保存   签名和意见
		 * @return
		 */
		public String toGetApprovalinfo(){
			try {
				userid=this.getUser().getUserId();
				dateNow=DateTool.getDateTime();
				setSuccessFlag(true);
				Date t2t= DateTool.formatDate(dateNow, "yyyy-MM-dd HH:mm:ss");
				// 拿到当前机构
				SysOrganization sysOrganization = getUserOrg();
				//保存审批意见
				tfQualDeclarApprovalinfo= new TfQualDeclarApprovalinfo();
				//意见
				tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);  
				if(processId !=null && !"".equals(processId)){
					tfQualDeclarApprovalinfo.setPid(processId);
				}else{
					//申报信息
					tfQualDeclarApprovalinfo.setPid(declaredinfoid);    
				}
				//机构
				tfQualDeclarApprovalinfo.setOrgName(sysOrganization.getName());		
				//签名日期
				tfQualDeclarApprovalinfo.setSigningDate(t2t);
				//签名者 
				tfQualDeclarApprovalinfo.setSigningid(userid);			
				tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
				
			} catch (Exception e) {
				this.message = this.getFailMessage("保存意见失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		
		/**
		 * 流程扭转到公司训练专员
		 * @return
		 */
		public String toJianChaYuanYuanConfirm() {
			try {
				if (ids!=null && !ids.equals("")) {
					if (taskId != null && !taskId.equals("")) {
						declaredinfoid = processBase.getVariable(taskId, "planInfoId").toString();
						if (!declaredinfoid.equals("")) {
							tfQualDeclaraPilotList = tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for(TfQualDeclaraPilot pilot : tfQualDeclaraPilotList){
								if (ids.contains(pilot.getDetailsid())) {
									// 当流程扭转成功后 调整申报人员的状态
									pilot.setState("DXL");// 人员状态，待训练
									pilot.setOrgRole(ConstantList.GHFGBID + "-" + "资质-训练专员");
								}else{
									// 根据当前选择下发的人员对原计划人员列表进行驳回处理
									pilot.setState("REJECT");
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(pilot.getCmPeople().getId(),subGroupId);
								}
								tfQualDeclaraPilotBS.saveOrUpdate(pilot);
							}
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("XlZhuanYuan", ConstantList.GHFGBID + "-" + "资质-训练专员");
						// 保存待办需要的机构及人员
						map.put("FromOrgName", getUserOrg().getName());
						map.put("FromOrgUser", getUser().getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "结束", map);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID, "closeCurrent", "");
					}
				}
			} catch (Exception e) {
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}

		/**
		 * 公司飞管专员申报
		 * @return
		 */
		public String toGsZhuanYuanSbForm(){
			try{
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					ifShow="GsZhuanYuanSb"; 
					formUrl="tf-qua-apply/tf-qua-apply!toJianChaYuanYuanConfirm.do";
				}
			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toLsyForm";
		}
		/**
		 * 公司领导审批通过，流程跳转到公司专员
		 * @return
		 */
		public String toJiaoYuanConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						// 登录用户信息
						CmUser user = getUser();
						// 拿到当前机构
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						String pid = processBase.getProcessInstanceId(taskId);
						//保存审批意见
						if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
							tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
							tfQualDeclarApprovalinfo.setPid(pid);
							tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
							tfQualDeclarApprovalinfo.setState(state);
							tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
						}
						map.put("GsZhuanYuanSb",ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员申报", map);
						setSuccessFlag(true);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		/**
		 * 公司领导审批页面
		 * @return
		 */
		public String toGsLingDaoForm(){
			try{
				userId=getUser().getUserId();
				if(taskId!=null && !taskId.equals("")){
					orgName=this.getUserOrg().getName();
					processId=processBase.getProcessInstanceId(taskId);
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, userId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					ifShow="GsJingLi";//同jingli页面相同
					if("QUAL_QT_FPFXY".equals(subGroupId)){ //返聘飞行员
						formUrl="tf-qua-apply/tf-qua-apply-rehelloring!toGsZhuRenSbConfirm.do";
					}else{
						formUrl="tf-qua-apply/tf-qua-apply!toJiaoYuanConfirm.do";
					}
				}
			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toZhuanYuanForm";
		}
		/**
		 * 公司经理审核通过，流程跳转到公司领导
		 * @return
		 */
		public String toFeiXingYuanConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						// 登录用户信息
						CmUser user = getUser();
						// 拿到当前机构
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						String pid = processBase.getProcessInstanceId(taskId);
						//保存审批意见
						if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
							tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
							tfQualDeclarApprovalinfo.setPid(pid);
							tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
							tfQualDeclarApprovalinfo.setState(state);
							tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
						}
						map.put("GsLingDao",ConstantList.GHFGBID+"-"+"资质-公司飞管领导");
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管领导审核报批", map);
						setSuccessFlag(true);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		/**
		 * 公司标准高级经理审核页面
		 * @return
		 */
		public String toGsJingLiForm(){
			try{
				userId=getUser().getUserId();
				if(taskId!=null && !taskId.equals("")){
					orgName=this.getUserOrg().getName();
					processId=processBase.getProcessInstanceId(taskId);
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, userId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}					
					}
					ifShow="GsJingLi";
					formUrl="tf-qua-apply/tf-qua-apply!toFeiXingYuanConfirm.do";
				}
			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toZhuanYuanForm";
		}
		/**
		 * 公司专员申报通过，流程跳转到公司经理
		 * @return
		 */
		public String toShenBaoConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						CmUser user = getUser();
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("GsJingLi",ConstantList.GHFGBID+"-"+"资质-公司飞管标准高级经理");
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管标准高级经理审核报批", map);
						this.message = this.getSuccessMessage("上报成功!", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		/**
		 * 公司专员审核页面
		 * @return
		 */
		public String toGsZhuanYuanForm(){
			try{
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					formUrl="tf-qua-apply/tf-qua-apply!toShenBaoConfirm.do";
					ifShow="GsZhuanYuan";//区分复用jsp的标志
				}
			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toLsyForm";
		}
		
		/**
		 * 分公司领导审批通过，流程跳转到公司专员报批
		 * @return
		 */
		public String toJingLiConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						// 登录用户信息
						CmUser user = getUser();
						// 拿到当前机构
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						//保存审批意见
						if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
							tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
							tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
							tfQualDeclarApprovalinfo.setState(state);
							tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
						}
						map.put("GsZhuanYuan", ConstantList.GHFGBID+"-"+"资质-公司飞管专员");
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "公司飞管专员审核报批", map);
						setSuccessFlag(true);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		/**
		 * 分公司领导审核页面
		 * @return
		 */
		public String toFgsLingDaoForm(){
			userId=this.getUser().getUserId();
			//判断流程身份
			String taskname=processBase.getActivityNameByTaskId(taskId);
			if(taskname.contains("分公司级飞管领导审核报批")){
				isfgsfgld=true;
			}else{
				isfgsfgld=false;
			}
			try{
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					orgName=this.getUserOrg().getName();
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, userId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					if(subGroupId.equals("QUAL_UP_M-J") || subGroupId.equals("QUAL_UP_F3-F4")){
						formUrl="tf-qua-apply/tf-qua-apply-mj!toJingLiConfirm.do";
						ifShow="FgsLingDao";
					}else if(subGroupId.equals("QUAL_UP_F4-F5")||subGroupId.equals("QUAL_UP_A2-TA")||subGroupId.equals("QUAL_UP_TA-TB")||subGroupId.equals("QUAL_UP_TB-TC")
							||subGroupId.equals("QUAL_TR_JZZJX")||subGroupId.equals("QUAL_TR_JYCIQ")||subGroupId.equals("QUAL_TR_FJSZJX")  //机长转机型、教员CIQ、副驾驶转机型 流程
							||subGroupId.equals("QUAL_EN_YYJY")  //英语教员流程
							|| subGroupId.equals("QUAL_QT_FPFXY")){//返聘飞行员 流程
						formUrl="tf-qua-apply/tf-qua-apply!toJingLiConfirm.do";
						ifShow="FgsLingDao";
					}else if(subGroupId.equals("QUAL_SQ_RNP/RNAV")||subGroupId.equals("QUAL_SQ_GYYX")||subGroupId.equals("QUAL_SQ_CATII")||subGroupId.equals("QUAL_SQ_CBKK/AMU")
							||subGroupId.equals("QUAL_SQ_ETOPS")||subGroupId.equals("QUAL_SQ_SFDJZG")||subGroupId.equals("QUAL_SQ_TSJC")){
						formUrl="tf-qua-apply/tf-qua-apply-special!toJingLiConfirm.do";
					}
				}
			}catch (RuntimeException e) { 
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toZhuanYuanForm";
		}
		
		/**
		 * 分公司经理审核通过，流程扭转到分公司领导
		 * @return
		 */
		public String toZhuanYuanConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(!ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						CmUser user = getUser();
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						//保存审批意见
						if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
							tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
							tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
							tfQualDeclarApprovalinfo.setState(state);
							tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
						}
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsLingDao", ConstantList.TJFGS+"-"+"资质-分公司级飞管领导");
						}else{
							map.put("FgsLingDao", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管领导");
						}
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管领导审核报批", map);
						setSuccessFlag(true);
						this.message = this.getSuccessMessage("审批成功！", NAV_TAB_ID,
								"closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		/**
		 * 分公司经理审核页面
		 * @return
		 */
		public String toFgsJingLiForm(){
			try{   
				userId= this.getUser().getUserId();
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					//单位名称
					orgName=this.getUserOrg().getName();
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessIdAndUserid(processId, getUser().getUserId());
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(declaredinfoid!=null && !declaredinfoid.equals("")){
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					formUrl="tf-qua-apply/tf-qua-apply!toZhuanYuanConfirm.do";
				}
			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toZhuanYuanForm";
		}
		/**
		 * 分公司级飞管标准高级经理审批页面
		 * @return
		 */
		public String toZongDuiConfirm(){
			try{
				//根据当前选择下发的人员对原计划人员列表进行删除操作
				if(ids!=null && !ids.equals("")){
					if(taskId!=null && !taskId.equals("")){
						declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
						if(!declaredinfoid.equals("")){
							tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
							for (int i = 0,n= tfQualDeclaraPilotList.size(); i <n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						SysOrganization sysOrganization = getUserOrg();
						Map<String, Object> map = new HashMap<String, Object>();
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsJingLi", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准高级经理");
						}else{
							map.put("FgsJingLi", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准高级经理");
						}
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", getUser().getName());
						tfQuaApplyJbpmBS.completeProcessById(taskId, "task", "分公司级飞管标准高级经理审核报批", map);
						this.message = this.getSuccessMessage("流程上报成功！" ,NAV_TAB_ID, "closeCurrent", "");
					}
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";   //m-j
		}
		/**
		 * 分公司专员审核（王楠或刘思远）
		 * @return
		 */
		public String toFgsZhuanYuanForm(){
			try{
				if(taskId!=null && !taskId.equals("")){
					processId=processBase.getProcessInstanceId(taskId);
					//审批信息
					if(!processId.equals("")){
						tfQualDeclarApprovalinfoList=tfQualDeclarApprovalInfoBS.findApprovalInfoByProcessId(processId);
					}
					declaredinfoid= processBase.getVariable(taskId, "planInfoId").toString();
					planInfoName= processBase.getVariable(taskId, "planInfoName").toString();
					//获取此次申报的人员信息
					if(!declaredinfoid.equals("")){
						ifShow="fGsZhuanYuan"; 
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						for (int t = 0,n=tfQualDeclaraPilotList.size(); t <n ; t++){
							int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
							statusList.add(docCount);
						}
					}
					formUrl="tf-qua-apply/tf-qua-apply!toZongDuiConfirm.do";
				}
			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toLsyForm";
		}
		/**
		 * 教员填单
		 * @return
		 */
		public String quaAccessConditionHLM(){
			String returnUrl = "toCondition21";
			try{
				//获取此次申报的人员信息
				if(detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					if(courselistid!=null && !courselistid.equals("")){
						tfQualPilotCourselist=tfQualDeclaraPilotBS.findById(TfQualPilotCourselist.class, courselistid);
					}
					ifShow=typedesc;
					//F4-F5中  填单的判断
					if("QUAL_UP_F4-F5".equals(subGroupId) || "QUAL_YB_CHZG".equals(subGroupId)){
						commitjiaoyuanIfSave();
						commitLessonIfSave();
                        commitDriverFlyBookIfSaveAdd();
						commitAircraftBookIfSave();
					}else if("QUAL_YB_MNJFXJC".equals(subGroupId) || "QUAL_UP_F1-F2".equals(subGroupId) || "QUAL_UP_F2-F3".equals(subGroupId) ||
							"QUAL_CT_GSFXJCY".equals(subGroupId) || "QUAL_UP_M-J".equals(subGroupId) || "QUAL_UP_F3-F4".equals(subGroupId)){
						commitjiaoyuanIfSave();  
						commitDriverFlyBookIfSaveAdd();   
					}else if("QUAL_UP_A2-TA".equals(subGroupId) ||"QUAL_UP_TA-TB".equals(subGroupId) ){
						commitjiaoyuanIfSave();  
						commitDriverFlyBookIfSaveAdd();
						commitLessonIfSave();
					}else if("QUAL_TR_FJSCY".equals(subGroupId) || "QUAL_TR_JZCY".equals(subGroupId) ||
							"QUAL_TR_FJSZJX".equals(subGroupId) || "QUAL_TR_JZZJX".equals(subGroupId)){
						commitjiaoyuanIfSave();
						commitLessonIfSave();
						commitDriverFlyBookIfSaveAdd();
						commitAircraftBookIfSave();
					}else if("QUAL_NE_XY".equals(subGroupId)){
						commitjiaoyuanIfSave();
						commitOrderIfSave();
						commitDriverFlyBookIfSaveAdd();
					}
				}
				if(subGroupId.contains("QUAL_TR")){
					returnUrl = "QUAL_TR_toCondition21";
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return returnUrl;
		}
		/**
		 * 判断 当前登录者 “飞行经历记录本”是否填单
		 * @return
		 */
		public boolean commitjiaoyuanIfSave(){
			tfQualPilotFlyrecordbook = tfQualPilotFlyrecordbookBS.findByDetailIdAndCreator(tfQualDeclaraPilot.getDetailsid(), this.getUser().getUserId());
			flySave = tfQualPilotFlyrecordbook != null;
			return flySave;
		}
		/**
		 *  判断"驾驶员升级和检查记录薄" 普通课 检查课    是否填单
		 * @return
		 */
		public boolean commitLessonIfSave(){
			List<SysRole> roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
			String isT = this.tfQualDeclaraPilotBS.checkIsT(roleList);
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
			lessonSave= tfQualLessonCommentBS.checkAllCommentIfSave(tfQualDeclaraPilot.getDetailsid(),isT,tfQualDeclaraPilot.getTfQualBaseType().getTypeid());
			return lessonSave;
		}
		
		/**
		 * zhong 判断 张春雷登陆后，飞行员驾驶记录薄是否已经填写了 ，记录薄添加页面为trainingRecordsAdd.jsp
		 */
		private void commitDriverFlyBookIfSaveAdd(){
			int tfQualInspectionTrainsCount =  tfQualInspectionTrainBS.findTfQualInspectionTrainCount(tfQualDeclaraPilot.getDetailsid(),this.getUser().getUserId());
			if(tfQualInspectionTrainsCount >0){//就说明该人员 驾驶员记录薄已经填写
				this.driverFlag = true;
			}else{
				this.driverFlag = false;
			}
		}
		/**
		 * zhong 判断熟练检查单是否填写了 如：F4-F5 chenxin ITfQualCheckJobCardBS
		 */
		private void commitCheckItemIfSave(){
			if(checkJobCardBS.findCheckItemById(detailsid) != null){
				checkJobCardIsSave = true;				
			}else{
				checkJobCardIsSave = false;
			}			
		}
		/**
		 * 判断“驾驶员飞行记录薄” 是否填单
		 * 多人填单 共用这一个方法
		 * @return
		 */
		public String commitOrderIfSave(){
			tfQualInspectionReport=tfQualInspectionReportBS.findInspectionReport(tfQualDeclaraPilot.getDetailsid(),"jcbg");
			if(tfQualInspectionReport!=null && !"".equals(tfQualInspectionReport)){
				//检查员    陈昕是否填单   
				inspector = tfQualInspectionReport.getComments() !=null;
				//分公司    胡玉录是否填单
				branch = tfQualInspectionReport.getSubsidiarycomm() !=null;
				//技术负责人   刘铁祥是否填单
				technical = tfQualInspectionReport.getTechnicaldirectorcomm()!=null;
				//局方填写审批意见 wngf
				bureaucomm = tfQualInspectionReport.getBureaucomm() != null;
				inspectorName = tfQualInspectionReport.getInspectorName()!=null;
			}else{
				inspector=false;
				branch=false;
				technical=false;
				bureaucomm = false;
				inspectorName= false;
			}
			return "json";
		}
		/**
		 * 判断  当前登陆者  “本场训练单” 是否填单
		 * @return
		 */
		public boolean commitPracticeBookIfSave(){
			tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				tfQualInspectionReport = tfQualInspectionReportBS.findByDetailIdAndModifier(tfQualDeclaraPilot.getDetailsid(), getUser().getUserId(), "bcxl");
				practicebook= tfQualInspectionReport!=null;
			}
			return practicebook;
		}
		/**
		 * 判断  当前登录者  “民用航空器驾驶员执照和等级申请表” 是否填单
		 * @return  
		 */
		public boolean commitAircraftBookIfSave(){
			tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				tfQualPilotAppinformation = tfQualPilotLicenseRateAppBS.findByDetailIdAndModifier(tfQualDeclaraPilot.getDetailsid(), getUser().getUserId());
				pilotApp= tfQualPilotAppinformation!=null;
			}
			return pilotApp;
		}
		/**
		 * 判断  当前登陆者  “航线运输驾驶员执照/型别等级实践考核工作单” 是否填单
		 * @return
		 */
		public boolean commitExamBookIfSave(){
			tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				tfQualBaseWorkOrder = tfQualBaseWorkOrderBS.findByDetailIdAndModifier(tfQualDeclaraPilot.getDetailsid(), getUser().getUserId());
				exambook= tfQualBaseWorkOrder!=null;
			}
			return exambook;
		}
		
		/**
		 *  判断  当前登陆者 “航线飞行检查表” 是否填单的方法
		 * @return
		 */
		public boolean rooteIfSave(){
			tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			if(tfQualDeclaraPilot!=null){
				TfQualRouteReport tfQualRouteReport = tfQualRouteReportBS.getPageByDetailsid(detailsid);
				if(tfQualRouteReport != null){
					roote = tfQualRouteReport.getCreater() !=null;
				}else{
					roote = false;
				}
			}
			return roote;
		}
		/**
		 * 检查员填单
		 * @return
		 */
		public String quaAccessConditionWHT(){
			String returnUrl = "toCondition22";
			try{
				if(detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					if(courselistid!=null && !courselistid.equals("")){
						tfQualPilotCourselist=tfQualDeclaraPilotBS.findById(TfQualPilotCourselist.class, courselistid);
					}
					ifShow=typedesc;
					if("QUAL_UP_F4-F5".equals(subGroupId) || "QUAL_YB_CHZG".equals(subGroupId)){
						commitLessonIfSave();
						commitOrderIfSave();
						commitjiaoyuanIfSave();
						commitAircraftBookIfSave();
						commitCheckItemIfSave();
						commitExamBookIfSave();
					}else if("QUAL_UP_A2-TA".equals(subGroupId) || "QUAL_UP_TA-TB".equals(subGroupId)){
						commitLessonIfSave();
						commitOrderIfSave();
						commitjiaoyuanIfSave(); 
					}else if("QUAL_UP_F1-F2".equals(subGroupId) ||"QUAL_UP_F2-F3".equals(subGroupId) ||
							"QUAL_UP_F3-F4".equals(subGroupId) ||"QUAL_UP_M-J".equals(subGroupId) ||
							"QUAL_CT_GSFXJCY".equals(subGroupId)){
						commitOrderIfSave();
						commitjiaoyuanIfSave();     
					}else if("QUAL_YB_MNJFXJC".equals(subGroupId) ){
						commitDriverFlyBookIfSaveAdd();
						commitjiaoyuanIfSave();
						commitCheckItemIfSave();
						//模拟机复训和检查  表单的验证未做
					}else if("QUAL_TR_FJSCY".equals(subGroupId) || "QUAL_TR_JZCY".equals(subGroupId)){
						commitjiaoyuanIfSave();
						commitLessonIfSave();
						commitDriverFlyBookIfSaveAdd(); 
						commitOrderIfSave();
					}else if("QUAL_NE_XY".equals(subGroupId)){
						commitjiaoyuanIfSave();
						commitOrderIfSave();
						commitDriverFlyBookIfSaveAdd();
					}
				}
				if(subGroupId.contains("QUAL_TR")){
					returnUrl = "QUAL_TR_toCondition22";
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return returnUrl;
		}
		
		public String quaAccessConditionLSY(){
			String returnUrl = "toCondition24";
			try{
				if(detailsid!=null && !detailsid.equals("")){
					//申报人员明细
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
					ifShow=typedesc;
					//判断刘思远是否填完单子
					if("QUAL_UP_F5-M".equals(subGroupId) ||"QUAL_UP_J-A1".equals(subGroupId) ||"QUAL_UP_A1-A2".equals(subGroupId)){
						rooteIfSave();     //航线飞行检查表验证
						commitjiaoyuanIfSave();    //J-A1 判断 飞行经历记录本 是否填单
						commitDriverFlyBookIfSaveAdd();   //zhong判断 张春雷 驾驶纪录薄是否填写
						commitOrderIfSave();
					}else if("QUAL_UP_F4-F5".equals(subGroupId)|| "QUAL_UP_TB-TC".equals(subGroupId) || "QUAL_YB_CHZG".equals(subGroupId)){
						commitjiaoyuanIfSave();
						commitPracticeBookIfSave();    //本场训练单
						commitDriverFlyBookIfSaveAdd();
					}else if("QUAL_NE_XY".equals(subGroupId)){
						commitjiaoyuanIfSave();
					}
				}
				if(subGroupId.contains("QUAL_TR")){
					returnUrl = "QUAL_TR_toCondition24";
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return returnUrl;
		}
		
		public String quaAccessConditionWF(){
			String returnUrl = "toCondition25";
			try{
				if(detailsid!=null && !detailsid.equals("")){
					tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
				}
				//判断局方是否填写了“驾驶员飞行记录本”
				commitOrderIfSave();
				if(subGroupId.contains("QUAL_TR")){
					returnUrl = "QUAL_TR_toCondition25";
				}
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return returnUrl;
		}
		 
		/**
		 *   根据用户peopleId拿到人员信息，返回一个列表，在前端页面展示
		 * 因第一个发起人没有taskId,故只能通过peopleid取数据，在TfQualDeclaraPilotStay表中查询
		 */
		public String queryForPilotStay(){
			try{
				tfQualDeclaraPilotStaylist = tfQualDeclaraPilotStayBS.findTfQualDeclaraPilotStayByPeopleId(peopleId);
				if(tfQualDeclaraPilotStaylist!=null&&tfQualDeclaraPilotStaylist.size()!=0){
					tfQualDeclaraPilotStay=tfQualDeclaraPilotStaylist.get(0);
				}
				tabIndexSelection();
			}catch(Exception e){
				this.message = this.getFailMessage("查询失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "accessConditionFirst";
		}
		/**适时的清除session，并确保页面跳转无误*/
		private void tabIndexSelection(){// 处理子标签页跳转问题
			String flush=(String) this.getServletRequest().getSession().getAttribute("flush_queryForPilotStay");
			String index=(String) this.getServletRequest().getSession().getAttribute("tabIndex_queryForPilotStay");
			//适时的清除session，并确保页面跳转无误
			if(flush!=null && !flush.equals("")){
				this.getServletRequest().getSession().removeAttribute("flush_queryForPilotStay");
			}else if(index!=null && !index.equals("")){
				this.getServletRequest().getSession().removeAttribute("tabIndex_queryForPilotStay");
			}
		}
		public String quaAccessCondition(){
			try{
				tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByPeopleId(peopleId);
				if(tfQualDeclaraPilotList!=null&&tfQualDeclaraPilotList.size()!=0){
					tfQualDeclaraPilot=tfQualDeclaraPilotList.get(0);
				}	
			}catch(Exception e){
				this.message = this.getFailMessage("流程扭转失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "accessCondition";
		}
		/**
		 *    跳转到展示个人资质页面  Ajax方式
		 */
		public String toPilotOwnCondition(){
			try{
				this.tfQualPilotConditionsList = tfQualPilotConditionsBS.getQualPilotConditionsByPilotId(cmPeople.getId());
				this.tfQualBaseConditionsList = tfQualPilotConditionsBS.getAllTfQualBaseConditions();
			} catch (Exception e) {
				this.message = this.getFailMessage("查询失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			
			return "pilotOwnCondition";
		}
		/**
		 *    第一个人用的      跳转到展示标准资质页面  Ajax方式
		 */
		public String toShowAccessConditionFirst(){
			try{
				List<TfQualPilotLicence> licences =new ArrayList<TfQualPilotLicence>();
				tfQualDeclaraPilotStaylist = tfQualDeclaraPilotStayBS.findTfQualDeclaraPilotStayByPeopleId(peopleId);
				if(tfQualDeclaraPilotStaylist!=null&&tfQualDeclaraPilotStaylist.size()!=0){
					tfQualDeclaraPilotStay=tfQualDeclaraPilotStaylist.get(0);
					if(tfQualDeclaraPilotStay!=null){
						tfQualBaseType = tfQualDeclaraPilotStay.getTfQualBaseType();
						tfQualBaseAccessConditionList=this.tfQualBaseAccessConditionBS.queryByBaseType(tfQualBaseType);
						tfQualBaseAccessConditions = tfQualBaseAccessConditionList.get(0);
					}
					if(tfQualBaseType!=null && tfQualBaseType.getTfQualBaseTypeGroup().getQtgroupid().equals("QUAL_UP")){
		 				licences = tfQualPilotLicenceBS.getQualPilotLicenceById(peopleId, tfQualBaseType.getTargetatrid().getAtrid());
		 				tfQualPilotLicence = licences.size()>0 ? licences.get(0) : null;
		 			}
		 			if(tfQualPilotLicence != null){
		 				List<TfQualPilotTechrecord> records= tfQualPilotTechrecordBS.getQualPilotTechrecordById(tfQualPilotLicence.getPilotid(), tfQualPilotLicence.getAtrid());
		 				tfQualPilotTechrecord=records.size()>0 ? records.get(0) :null;
		 			}
				}
			}catch(Exception e){
				this.message = this.getFailMessage(" 查询失败！");
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			 tfQualBasePilotInfo = this.tfQualBasePilotInfoBS.getTfQualBasePilotInfo(peopleId);
			return "toShowAccessCondition";
		}
		
		
		/**
		 *     跳转到展示标准资质页面  Ajax方式
		 */
		public String toShowAccessCondition(){
			 //根据“资质对象”找到“标准条件”
			 try{
				List<TfQualPilotLicence> licences =new ArrayList<TfQualPilotLicence>();
			 	if(detailsid!=null && !detailsid.equals("")){
			 		tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
			 		if(tfQualDeclaraPilot!=null){
			 			tfQualDeclarInfo=tfQualDeclaraPilot.getTfQualDeclarInfo();
			 			if(tfQualDeclarInfo!=null){
			 				tfQualBaseType=tfQualDeclarInfo.getTfQualBaseType();
			 				tfQualBaseAccessConditionList=this.tfQualBaseAccessConditionBS.queryByBaseType(tfQualBaseType);
							tfQualBaseAccessConditions = tfQualBaseAccessConditionList==null||tfQualBaseAccessConditionList.size()<1 ? null : tfQualBaseAccessConditionList.get(0);
			 			}
			 			if(tfQualBaseType!=null && tfQualBaseType.getTfQualBaseTypeGroup().getQtgroupid().equals("QUAL_UP")){
			 				licences = tfQualPilotLicenceBS.getQualPilotLicenceById(tfQualDeclaraPilot.getCmPeople().getId(), tfQualDeclaraPilot.getTfQualBaseType().getTargetatrid().getAtrid());
			 				tfQualPilotLicence = licences.size()>0 ? licences.get(0) : null;
			 			}
			 			if(tfQualPilotLicence != null){
			 				List<TfQualPilotTechrecord> records= tfQualPilotTechrecordBS.getQualPilotTechrecordById(tfQualPilotLicence.getPilotid(), tfQualPilotLicence.getAtrid());
			 				tfQualPilotTechrecord=records.size()>0 ? records.get(0) :null;
			 			}
			 		}
				}
				//根据peopleId查找到当前飞行员对象
			 	if(peopleId!=null && !peopleId.equals("")){
			 		tfQualBasePilotInfo = this.tfQualBasePilotInfoBS.getTfQualBasePilotInfo(peopleId);
				}
			 }catch(Exception e){
				 e.printStackTrace(); 
			 }
			return "toShowAccessCondition";
		}
		
		public String toCreateDeclaInfo(){
			try{
				CmUser user = getUser();
				userId=getUser().getUserId();
			    declaredinfoid=this.tfQualifiedDeclareBS.createDeclarInfo(typeids,ids,user);
			}catch (RuntimeException e) {
				declaredinfoid="";
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "json";
		}
		/**
		 * 机队申报页面跳转到填写意见的页面
		 */
		public String  toSubmit(){
			try{ 
				CmUser user = getUser();
				userId=user.getUserId();
				orgName=this.getUserOrg().getName();
				//获取此次申报信息
				if(declaredinfoid!=null && !declaredinfoid.equals("")){
					tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
					 //获取此次申报的人员信息
					if(tfQualDeclarInfo!=null){
						planInfoName=tfQualDeclarInfo.getDeclaredinfodesc();
						if(subGroupId==null||"".equals(subGroupId.trim())){
							subGroupId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
						}
					}
					//获取领导意见
					List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList = tfQualifiedDeclareBS.findApprovalinfoByDeclaredinfoid(declaredinfoid);
					if(tfQualDeclarApprovalinfoList.size()>0){
						tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfoList.get(0);
					}
					tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
					//   判断是否已经上传文件
					for(int t=0,len=tfQualDeclaraPilotList.size();t<len;t++) {
						int docCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilotList.get(t).getDetailsid(),getUser().getUserId());
						statusList.add(docCount);
					}
				}
			   if( subGroupId != null && "QUAL_FT_FTDJY".equals(subGroupId) ){
				   formUrl="tf-qua-apply/tf-qua-apply-ftd-teacher!quaApplyAdd.do";
			   }else if(subGroupId != null && ("QUAL_EN_YYPFY".equals(subGroupId)|| "QUAL_EN_YYKSY".equals(subGroupId))){
				   formUrl="tf-qua-apply/tf-qua-apply-english-ksy!toFgsjlConfirm.do";
				   stage="fgsZhuanYuanChuShen";
				   return "reportPage";
			   }else if(subGroupId != null && "QUAL_FT_FXJXLLJY".equals(subGroupId)) {
				   formUrl="tf-qua-apply/tf-qua-apply-flight-theory-teacher!quaApplyAdd.do";
			   }else{
				   formUrl="tf-qua-apply/tf-qua-apply!quaApplyAdd.do";
			   }
 			}catch (RuntimeException e) {
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "submit";
		}
		
		/**
		 * 执行制定计划操作
		 */
		public String quaApplyAdd(){
			try{
				// 登录用户信息
				CmUser user = getUser();
				// 拿到当前机构
				SysOrganization sysOrganization = getUserOrg();
				if(state!=null && !state.equals("")){
					if(state.equals("btg")){
						this.message = this.getSuccessMessage("审核不通过!", NAV_TAB_ID,"closeCurrent","tf-qua-apply/tf-qua-apply!quaApplyAdd.do" );
					}else{
						//获取此次申报信息 
						tfQualDeclarInfo=tfQualifiedDeclareBS.findById(TfQualDeclarInfo.class, declaredinfoid);
						// 部署资质申报流程
						String processName=null;
						String subGrpId = tfQualDeclarInfo.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
						subGroupId = subGroupId==null||"".equals(subGroupId.trim()) ? subGrpId : subGroupId;
						if("QUAL_UP_F4-F5".equals(subGrpId)|| "QUAL_UP_A2-TA".equals(subGrpId) || "QUAL_UP_TA-TB".equals(subGrpId) || "QUAL_UP_TB-TC".equals(subGrpId)){
							processName=ConstantList.QUALIFICATION_APPLY_F4;
						}else if("QUAL_UP_M-J".equals(subGrpId)||"QUAL_UP_F3-F4".equals(subGrpId)){
							processName=ConstantList.QUALIFICATION_APPLY_MJ;
						}else if( "QUAL_UP_F1-F2".equals(subGrpId)|| "QUAL_UP_F2-F3".equals(subGrpId)){
							processName=ConstantList.QUALIFICATION_APPLY_F1;
						}else if( "QUAL_UP_J-A1".equals(subGrpId)|| "QUAL_UP_A1-A2".equals(subGrpId)|| "QUAL_UP_F5-M".equals(subGrpId)){
							processName=ConstantList.QUALIFICATION_APPLY_J;
						}else if( "QUAL_TR_JZZJX".equals(subGrpId)|| "QUAL_TR_JYCIQ".equals(subGrpId)|| "QUAL_TR_FJSZJX".equals(subGrpId)){//机长转机型、教员CIQ、副驾驶转机型 流程
							processName=ConstantList.QUALIFICATION_APPLY_TR;
						}else if( "QUAL_TR_FJSCY".equals(subGrpId)|| "QUAL_TR_JZCY".equals(subGrpId)){
							processName=ConstantList.QUALIFICATION_APPLY_DIFFERENCE;
						}else if( "QUAL_QT_FPFXY".equals(subGrpId)){
							processName=ConstantList.QUALIFICATION_APPLY_REHELLORING;
						}
						String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(processName);
						// 开启资质申报流程
						Map<String, Object> mapStart = new HashMap<String, Object>();
						mapStart.put("JiDui", sysOrganization.getId());
						String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
					    // 将实例id保存到流程信息里
						Map<String, Object> map = new HashMap<String, Object>();
						//保存流程公共信息  转到declar info表中
						if(sysOrganization.getParent_Fun().getName().contains("天津")){
							map.put("FgsZhuanYuan", ConstantList.TJFGB+"-"+"资质-分公司级飞管标准专员");
						}else{
							map.put("FgsZhuanYuan", ConstantList.GUOHANGFEIXINGZONGDUI_ID+"-"+"资质-分公司级飞管标准专员");
						}
						//把流程信息保存到申报信息中,保存流程信息 
						tfQualDeclarInfo.setProcessid(pid);
						//what is "F"
						tfQualDeclarInfo.setStatus("F");
						tfQualifiedDeclareBS.saveOrUpdate(tfQualDeclarInfo);
						//根据当前选择下发的人员对原计划人员列表进行删除操作
						tfQualDeclaraPilotList=tfQualifiedDeclareBS.getTfQualDeclarPilotByInfoId(declaredinfoid);
						if(!ids.equals("")){
							for (int i = 0,n=tfQualDeclaraPilotList.size(); i < n; i++){
								if(!ids.contains(tfQualDeclaraPilotList.get(i).getDetailsid())){
									tfQualDeclaraPilotList.get(i).setState("REJECT");
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilotList.get(i));
									this.tfQualDeclaraPilotStayBS.updatePilotStayByPilotId(tfQualDeclaraPilotList.get(i).getCmPeople().getId(),subGroupId);
								}
							}
						}
						//保存待办需要的机构及人员
						map.put("FromOrgName", sysOrganization.getName());
						map.put("FromOrgUser", user.getName());
						map.put("FromOrgUserID", user.getUserId());
						map.put("task_plan", pid);
						map.put("planInfoId", declaredinfoid);//申报新Id
						map.put("planInfoName", tfQualDeclarInfo.getDeclaredinfodesc());
						map.put("subGroupId", subGroupId);
						//获取此次申报信息
						String taskIdString = processBase.getTaskIdByPiID(pid);
						// 存储流程历史处理人和历史处理机构
						processhistoryBS.saveProcessHistory(taskIdString, getUser(),getUserOrg());
						//讲流程流转向下
						tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "分公司级飞管标准专员审核报批", map);
						//保存审批意见
						if(tfQualDeclarApprovalinfo !=null && !"".equals(tfQualDeclarApprovalinfo.getId())){
							tfQualDeclarApprovalinfo = tfQualDeclarApprovalInfoBS.findById(TfQualDeclarApprovalinfo.class, tfQualDeclarApprovalinfo.getId());
							tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
							tfQualDeclarApprovalinfo.setState(state);
							tfQualDeclarApprovalinfo.setPid(pid);
							tfQualDeclarApprovalInfoBS.saveOrUpdate(tfQualDeclarApprovalinfo);
						}
						setSuccessFlag(true);
						if(declarInfoTabId!=null && declarInfoTabId.equals("declarInfoTab")){
							this.message = this.getSuccessMessage("审核成功!", declarInfoTabId,"closeCurrent","");
						}else{
							this.message = this.getSuccessMessage("审核成功!", NAV_TAB_ID,"closeCurrent","");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				setSuccessFlag(false);
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			}
			return "SUCCESS";
		}
		/**教员（张春雷） 开启后半部分流程
		 * 
		 * @return
		 */
		public String toAddSubQualApplyF4(){
			try{
				if(courselistid!=null && !courselistid.equals("")){
					tfQualPilotCourselist=tfQuaApplyBS.findById(TfQualPilotCourselist.class, courselistid);
					//此处 不管是否下发流程 都把此课程的状态 设置为 完成 先修改状态
					tfQualPilotCourselist.setState("TOVER");
					tfQuaApplyBS.saveOrUpdate(tfQualPilotCourselist);
					if(tfQualPilotCourselist!=null){
						//此处判断教员是否都填写完毕
						if(tfQuaApplyBS.checkIsAll(detailsid,tfQualPilotCourselist.getTcategory().trim(),tfQualPilotCourselist.getTpplanno().trim())){
							CmUser user = getUser();
							SysOrganization sysOrganization = getUserOrg();
							String processName=null;
							if( "QUAL_UP_F4-F5".equals(subGroupId)|| "QUAL_UP_A2-TA".equals(subGroupId)|| "QUAL_UP_TA-TB".equals(subGroupId)|| "QUAL_UP_TB-TC".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_F4;
							}else if( "QUAL_UP_F3-F4".equals(subGroupId)|| "QUAL_UP_M-J".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_MJ;
							}else if( "QUAL_UP_F1-F2".equals(subGroupId)|| "QUAL_UP_F2-F3".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_F1;
							}else if( "QUAL_YB_MNJFXJC".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_GENERAL;
							}else if( "QUAL_TR_JZZJX".equals(subGroupId)|| "QUAL_TR_JYCIQ".equals(subGroupId)|| "QUAL_TR_FJSZJX".equals(subGroupId)){//机长转机型、教员CIQ、副驾驶转机型 流程
								processName = ConstantList.SUB_QUALIFICATION_APPLY_TR;
							}else if( "QUAL_TR_FJSCY".equals(subGroupId)|| "QUAL_TR_JZCY".equals(subGroupId)){ //副驾驶差异  机长差异  流程
								processName = ConstantList.SUB_QUALIFICATION_APPLY_DIFFERENCE;
							}else if( "QUAL_NE_XY".equals(subGroupId)|| "QUAL_NE_WJFXY".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_NEW_EMPLOYEES;
							}else if( "QUAL_FT_CFBJY".equals(subGroupId)|| "QUAL_FT_FXJYLLJY".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_ICE_TEACHER;
							}else if( "QUAL_EN_YYJY".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_ENGLISH_TEACHER;
							}else if( "QUAL_SQ_RNP/RNAV".equals(subGroupId)|| "QUAL_SQ_GYYX".equals(subGroupId)|| "QUAL_SQ_CATII".equals(subGroupId)|| "QUAL_SQ_CBKK/AMU".equals(subGroupId)
									|| "QUAL_SQ_ETOPS".equals(subGroupId)|| "QUAL_SQ_SFDJZG".equals(subGroupId)|| "QUAL_SQ_TSJC".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_APPLY_SPECIAL;
							}else if( "QUAL_FT_FXJYMNJFX".equals(subGroupId)){
								processName = ConstantList.SUB_QUALIFICATION_FXJYMNJFX_TEACHER;//飞行训练模拟机复训下发流程
							}
							String deploymentProcessId = tfQuaApplyJbpmBS.deployProcessDefinition(processName);
							// 开启资质审核流程
							Map<String, Object> mapStart = new HashMap<String, Object>();
							mapStart.put("XlZhuanYuan", "");
							String pid = tfQuaApplyJbpmBS.startProcessByPid(deploymentProcessId, mapStart);
						    // 将实例id保存到流程信息里
							Map<String, Object> map = new HashMap<String, Object>();
							if(sysOrganization.getParent_Fun().getName().contains("天津")){
								map.put("jiaoyuan", ConstantList.TJFXBDSZD+"-"+"资质-教员");
							}else{
								map.put("jiaoyuan", ConstantList.ZDSDD+"-"+"资质-教员");
							}
							String taskIdString = processBase.getTaskIdByPiID(pid);
							if(taskIdString!=null && !taskIdString.equals("")){
								tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "教员填单", map);
							}
							taskIdString = processBase.getTaskIdByPiID(pid);
							if(sysOrganization.getParent_Fun().getName().contains("天津")){
								map.put("JianChaYuan", ConstantList.TJFGB+"-"+"资质-检查员"); 
							}else{
								map.put("JianChaYuan",  ConstantList.ZDSDD+"-"+"资质-检查员");
							}
							map.put("FromOrgName", sysOrganization.getName()); 
							map.put("FromOrgUserID", user.getUserId());
							map.put("task_plan", pid);
							map.put("subGroupId",subGroupId);
							if(taskIdString!=null && !taskIdString.equals("")){
								tfQuaApplyJbpmBS.completeProcessById(taskIdString, "task", "检查员填单", map);
							}
							//保存流程计划信息
							//流程下发成功后 修改此人的state=DJC 及
							if(detailsid!=null && !detailsid.equals("")){
								//TODO  此处获得当前学员的所有排课检查员列表 setorgRole 检查员以此筛选待办
								StringBuffer orgRoleTemp=new StringBuffer("");
								List<SysUserOrgRelation> userOrgRelation =tfQuaApplyBS.getOrgRoleByDetailsId(detailsid); 
								if(userOrgRelation!=null && userOrgRelation.size()!=0){
									for (int i = 0; i < userOrgRelation.size(); i++) {
										orgRoleTemp.append(userOrgRelation.get(i).getSysOrganization().getId()+"-"+userOrgRelation.get(i).getCmUser().getUserId()+",");
									}
								}
								tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid.trim());
								if(tfQualDeclaraPilot!=null){
									tfQualDeclaraPilot.setState("DJC");
									if(orgRoleTemp.length()!=0){
										tfQualDeclaraPilot.setOrgRole(orgRoleTemp.toString());
									}
									tfQualDeclaraPilot.setSubProcessid(pid);
									tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
								 }		
							}
						}
							setSuccessFlag(true);
							setTabIndexToReload("0");// 处理子标签页跳转问题
							this.message = this.getSuccessMessage("填单完成！", NAV_TAB_ID, "" ,"");
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				setSuccessFlag(false);
				tfQuaApplyBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			}
			return "json";
		}

		public String changeState(){
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
			tfQualDeclaraPilot.setState("GSZY_O");
			tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);
			this.message = this.getSuccessMessage("意见填写成功!", NAV_TAB_ID,"closeCurrent","");
			return "json";
		}
		

		private void setTabIndexToReload(String index){ // 处理子标签页跳转问题
			this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
			if(index!=null && !index.equals("0")){
				this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
			}
		}
		//============================================================================================
		
		@JSON(serialize=false)
		public List<TfQualDeclaraPilot> getTfQualDeclaraPilotList() {
			return tfQualDeclaraPilotList;
		}
		public void setTfQualDeclaraPilotList(List<TfQualDeclaraPilot> tfQualDeclaraPilotList) {
			this.tfQualDeclaraPilotList = tfQualDeclaraPilotList;
		}
		public boolean isSuccessFlag() {
			return successFlag;
		}
		public void setSuccessFlag(boolean successFlag) {
			this.successFlag = successFlag;
		}
		@JSON(serialize = false)
		public ITfQuaApplyBS getTfQuaApplyBS() {
			return tfQuaApplyBS;
		}

		public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
			this.tfQuaApplyBS = tfQuaApplyBS;
		}
		@JSON(serialize = false)
		public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
			return tfQuaApplyJbpmBS;
		}

		public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
			this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
		}
		@JSON(serialize = false)
		public TfWorkflowDesign getTfWorkflowDesign() {
			return tfWorkflowDesign;
		}
		public void setTfWorkflowDesign(TfWorkflowDesign tfWorkflowDesign) {
			this.tfWorkflowDesign = tfWorkflowDesign;
		}
		public void setMessage(Message message) {
			this.message = message;
		}
		public Message getMessage() {
			return message;
		}
		@JSON(serialize = false)
		public String getTaskId() {
			return taskId;
		}

		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		@JSON(serialize = false)
		public TfQuaApplyInfo getTfQuaApplyInfo() {
			return tfQuaApplyInfo;
		}

		public void setTfQuaApplyInfo(TfQuaApplyInfo tfQuaApplyInfo) {
			this.tfQuaApplyInfo = tfQuaApplyInfo;
		}
		@JSON(serialize = false)
		public ArrayList<TfQuaApplyInfo> getQualificationApplyList() {
			return qualificationApplyList;
		}

		public void setQualificationApplyList(ArrayList<TfQuaApplyInfo> qualificationApplyList) {
			this.qualificationApplyList = qualificationApplyList;
		}
		@JSON(serialize = false)
		public ProcessBase getProcessBase() {
			return processBase;
		}
		public void setProcessBase(ProcessBase processBase) {
			this.processBase = processBase;
		}		
		@JSON(serialize = false)
		public ITfWorkflowDesignBS getTfWorkflowDesignBS() {
			return tfWorkflowDesignBS;
		}
		public void setTfWorkflowDesignBS(ITfWorkflowDesignBS tfWorkflowDesignBS) {
			this.tfWorkflowDesignBS = tfWorkflowDesignBS;
		}
		@JSON(serialize = false)
		public TfQuaApplyProcess getTfQuaApplyProcess() {
			return tfQuaApplyProcess;
		}
		public void setTfQuaApplyProcess(TfQuaApplyProcess tfQuaApplyProcess) {
			this.tfQuaApplyProcess = tfQuaApplyProcess;
		}
		@JSON(serialize = false)
		public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
			return tfQualDeclarApprovalInfoBS;
		}
		public void setTfQualDeclarApprovalInfoBS(
				ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
			this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
		}
		//此处不要加json=false
		public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
			return tfQualDeclarApprovalinfo;
		}
		public void setTfQualDeclarApprovalinfo(
				TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
			this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
		}
		@JSON(serialize = false)
		public IProcesshistoryBS getProcesshistoryBS() {
			return processhistoryBS;
		}
		public void setProcesshistoryBS(IProcesshistoryBS processhistoryBS) {
			this.processhistoryBS = processhistoryBS;
		}
		@JSON(serialize = false)
		public String getOpinionInfo() {
			return opinionInfo;
		}
		public void setOpinionInfo(String opinionInfo) {
			this.opinionInfo = opinionInfo;
		}
		@JSON(serialize = false)
		public String getSigningid() {
			return signingid;
		}
		public void setSigningid(String signingid) {
			this.signingid = signingid;
		}
		@JSON(serialize = false)
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		@JSON(serialize = false)
		public String getProcessId() {
			return processId;
		}
		public void setProcessId(String processId) {
			this.processId = processId;
		}
		@JSON(serialize = false)
		public List<TfQualDeclarInfo> getTfQualDeclarInfoList() {
			return tfQualDeclarInfoList;
		}
		public void setTfQualDeclarInfoList(List<TfQualDeclarInfo> tfQualDeclarInfoList) {
			this.tfQualDeclarInfoList = tfQualDeclarInfoList;
		}
		@JSON(serialize = false)
		public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
			return tfQualifiedDeclareBS;
		}
		public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
			this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
		}
		@JSON(serialize = false)
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		@JSON(serialize = false)
		public String getTypeids() {
			return typeids;
		}
		public void setTypeids(String typeids) {
			this.typeids = typeids;
		}		

		public String getDeclaredinfoid() {
			return declaredinfoid;
		}
		public void setDeclaredinfoid(String declaredinfoid) {
			this.declaredinfoid = declaredinfoid;
		}
		@JSON(serialize = false)
		public TfQualDeclarInfo getTfQualDeclarInfo() {
			return tfQualDeclarInfo;
		}
		public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
			this.tfQualDeclarInfo = tfQualDeclarInfo;
		}
		@JSON(serialize = false)
		public String getPlanInfoName() {
			return planInfoName;
		}
		public void setPlanInfoName(String planInfoName) {
			this.planInfoName = planInfoName;
		}
		@JSON(serialize = false)
		public String getFgsZhuanYuanSpYj() {
			return fgsZhuanYuanSpYj;
		}
		public void setFgsZhuanYuanSpYj(String fgsZhuanYuanSpYj) {
			this.fgsZhuanYuanSpYj = fgsZhuanYuanSpYj;
		}
		@JSON(serialize = false)
		public List<TfQualDeclarApprovalinfo> getTfQualDeclarApprovalinfoList() {
			return tfQualDeclarApprovalinfoList;
		}
		public void setTfQualDeclarApprovalinfoList(List<TfQualDeclarApprovalinfo> tfQualDeclarApprovalinfoList) {
			this.tfQualDeclarApprovalinfoList = tfQualDeclarApprovalinfoList;
		}
		@JSON(serialize = false)
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		public String getDateNow() {
			return dateNow;
		}
		public void setDateNow(String dateNow) {
			this.dateNow = dateNow;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		@JSON(serialize = false)
		public List<TfQualBaseSignature> getTfQualBaseSignatureList() {
			return tfQualBaseSignatureList;
		}
		public void setTfQualBaseSignatureList(List<TfQualBaseSignature> tfQualBaseSignatureList) {
			this.tfQualBaseSignatureList = tfQualBaseSignatureList;
		}
		@JSON(serialize = false)
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		@JSON(serialize = false)
		public CmPeople getCmPeople() {
			return cmPeople;
		}
		public void setCmPeople(CmPeople cmPeople) {
			this.cmPeople = cmPeople;
		}
		@JSON(serialize = false)
		public TfQuaSignatureBS getTfQuaSignatureBS() {
			return tfQuaSignatureBS;
		}
		public void setTfQuaSignatureBS(TfQuaSignatureBS tfQuaSignatureBS) {
			this.tfQuaSignatureBS = tfQuaSignatureBS;
		}
		@JSON(serialize = false)
		public String getOrgName() {
			return orgName;
		}
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		@JSON(serialize = false)
		public Date getQianmingDate() {
			return qianmingDate;
		}
		public void setQianmingDate(Date qianmingDate) {
			this.qianmingDate = qianmingDate;
		}
		@JSON(serialize = false)
		public String getPeopleId() {
			return peopleId;
		}
		public void setPeopleId(String peopleId) {
			this.peopleId = peopleId;
		}
		@JSON(serialize = false)
		public String getFormUrl() {
			return formUrl;
		}
		public void setFormUrl(String formUrl) {
			this.formUrl = formUrl;
		}
		@JSON(serialize = false)
		public String getIfShow() {
			return ifShow;
		}
		public void setIfShow(String ifShow) {
			this.ifShow = ifShow;
		}
		@JSON(serialize = false)
		public SysPageInfo getSysPageInfo() {
			return sysPageInfo;
		}
		public void setSysPageInfo(SysPageInfo sysPageInfo) {
			this.sysPageInfo = sysPageInfo;
		}
		@JSON(serialize = false)
		public TfQualDeclaraPilot getTfQualDeclaraPilot() {
			return tfQualDeclaraPilot;
		}
		public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
			this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		}
		@JSON(serialize = false)
		public TfQualPilotLicence getTfQualPilotLicence() {
			return tfQualPilotLicence;
		}
		public void setTfQualPilotLicence(TfQualPilotLicence tfQualPilotLicence) {
			this.tfQualPilotLicence = tfQualPilotLicence;
		}
		@JSON(serialize = false)
		public TfQualBaseType getTfQualBaseType() {
			return tfQualBaseType;
		}
		public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
			this.tfQualBaseType = tfQualBaseType;
		}
		@JSON(serialize = false)
		public TfQualPilotLicenceBS getTfQualPilotLicenceBS() {
			return tfQualPilotLicenceBS;
		}
		public void setTfQualPilotLicenceBS(TfQualPilotLicenceBS tfQualPilotLicenceBS) {
			this.tfQualPilotLicenceBS = tfQualPilotLicenceBS;
		}
		@JSON(serialize = false)
		public List<TfQualPilotLicence> getTfQualPilotLicenceList() {
			return tfQualPilotLicenceList;
		}
		public void setTfQualPilotLicenceList(
				List<TfQualPilotLicence> tfQualPilotLicenceList) {
			this.tfQualPilotLicenceList = tfQualPilotLicenceList;
		}
		@JSON(serialize = false)
		public TfQualPilotTechrecord getTfQualPilotTechrecord() {
			return tfQualPilotTechrecord;
		}
		public void setTfQualPilotTechrecord(TfQualPilotTechrecord tfQualPilotTechrecord) {
			this.tfQualPilotTechrecord = tfQualPilotTechrecord;
		}
		@JSON(serialize = false)
		public List<TfQualPilotTechrecord> getTfQualPilotTechrecordList() {
			return tfQualPilotTechrecordList;
		}
		public void setTfQualPilotTechrecordList(
				List<TfQualPilotTechrecord> tfQualPilotTechrecordList) {
			this.tfQualPilotTechrecordList = tfQualPilotTechrecordList;
		}
		@JSON(serialize = false)
		public TfQualPilotTechrecordBS getTfQualPilotTechrecordBS() {
			return tfQualPilotTechrecordBS;
		}
		public void setTfQualPilotTechrecordBS(TfQualPilotTechrecordBS tfQualPilotTechrecordBS) {
			this.tfQualPilotTechrecordBS = tfQualPilotTechrecordBS;
		}
		@JSON(serialize = false)
		public Date getPtrapprovedDate() {
			return ptrapprovedDate;
		}
		public void setPtrapprovedDate(Date ptrapprovedDate) {
			this.ptrapprovedDate = ptrapprovedDate;
		}
		@JSON(serialize = false)
		public BaseAirPlanType getBaseAirplantype() {
			return baseAirplantype;
		}
		public void setBaseAirplantype(BaseAirPlanType baseAirplantype) {
			this.baseAirplantype = baseAirplantype;
		}
		public void setTfQualPilotConditionsList(List <TfQualPilotConditions> tfQualPilotConditionsList) {
			this.tfQualPilotConditionsList = tfQualPilotConditionsList;
		}
		@JSON(serialize = false)
		public List <TfQualPilotConditions> getTfQualPilotConditionsList() {
			return tfQualPilotConditionsList;
		}

		public void setTfQualBaseConditionsList(List<TfQualBaseConditions> tfQualBaseConditionsList) {
			this.tfQualBaseConditionsList = tfQualBaseConditionsList;
		}
		@JSON(serialize = false)
		public List<TfQualBaseConditions> getTfQualBaseConditionsList() {
			return tfQualBaseConditionsList;
		}
		@JSON(serialize = false)
		public ITfQualPilotConditionsBS getTfQualPilotConditionsBS() {
			return tfQualPilotConditionsBS;
		}
		public void setTfQualPilotConditionsBS(
				ITfQualPilotConditionsBS tfQualPilotConditionsBS) {
			this.tfQualPilotConditionsBS = tfQualPilotConditionsBS;
		}
		@JSON(serialize = false)
		public ITfQualDeclarInfoBS getTfQualDeclarInfoBS() {
			return tfQualDeclarInfoBS;
		}
		public void setTfQualDeclarInfoBS(ITfQualDeclarInfoBS tfQualDeclarInfoBS) {
			this.tfQualDeclarInfoBS = tfQualDeclarInfoBS;
		}
		@JSON(serialize = false)
		public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
			return tfQualBaseTypeBS;
		}
		public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
			this.tfQualBaseTypeBS = tfQualBaseTypeBS;
		}
		@JSON(serialize = false)
		public ITfQualBaseAccessConditionsBS getTfQualBaseAccessConditionBS() {
			return tfQualBaseAccessConditionBS;
		}
		public void setTfQualBaseAccessConditionBS(ITfQualBaseAccessConditionsBS tfQualBaseAccessConditionBS) {
			this.tfQualBaseAccessConditionBS = tfQualBaseAccessConditionBS;
		}
		@JSON(serialize = false)
		public List<TfQualBaseAccessconditions> getTfQualBaseAccessConditionList() {
			return tfQualBaseAccessConditionList;
		}
		public void setTfQualBaseAccessConditionList(List<TfQualBaseAccessconditions> tfQualBaseAccessConditionList) {
			this.tfQualBaseAccessConditionList = tfQualBaseAccessConditionList;
		}
		@JSON(serialize = false)
		public TfQualBaseAccessconditions getTfQualBaseAccessConditions() {
			return tfQualBaseAccessConditions;
		}
		public void setTfQualBaseAccessConditions(TfQualBaseAccessconditions tfQualBaseAccessConditions) {
			this.tfQualBaseAccessConditions = tfQualBaseAccessConditions;
		}
		@JSON(serialize = false)
		public ITfQualBasePilotInfoBS getTfQualBasePilotInfoBS() {
			return tfQualBasePilotInfoBS;
		}
		public void setTfQualBasePilotInfoBS(
				ITfQualBasePilotInfoBS tfQualBasePilotInfoBS) {
			this.tfQualBasePilotInfoBS = tfQualBasePilotInfoBS;
		}
		@JSON(serialize = false)
		public TfQualBasePilotInfo getTfQualBasePilotInfo() {
			return tfQualBasePilotInfo;
		}
		public void setTfQualBasePilotInfo(TfQualBasePilotInfo tfQualBasePilotInfo) {
			this.tfQualBasePilotInfo = tfQualBasePilotInfo;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
			return tfQualDeclaraPilotBS;
		}
		public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
			this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
		}
		@JSON(serialize = false)
		public String getSubProcessid() {
			return subProcessid;
		}
		public void setSubProcessid(String subProcessid) {
			this.subProcessid = subProcessid;
		}
		@JSON(serialize = false)
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
		@JSON(serialize = false)
		public ITfQualDeclaraPilotStayBS getTfQualDeclaraPilotStayBS() {
			return tfQualDeclaraPilotStayBS;
		}
		public void setTfQualDeclaraPilotStayBS(ITfQualDeclaraPilotStayBS tfQualDeclaraPilotStayBS) {
			this.tfQualDeclaraPilotStayBS = tfQualDeclaraPilotStayBS;
		}
		@JSON(serialize = false)
		public List<TfQualDeclaraPilotStay> getTfQualDeclaraPilotStaylist() {
			return tfQualDeclaraPilotStaylist;
		}
		public void setTfQualDeclaraPilotStaylist(
				List<TfQualDeclaraPilotStay> tfQualDeclaraPilotStaylist) {
			this.tfQualDeclaraPilotStaylist = tfQualDeclaraPilotStaylist;
		}
		@JSON(serialize = false)
		public TfQualDeclaraPilotStay getTfQualDeclaraPilotStay() {
			return tfQualDeclaraPilotStay;
		}
		public void setTfQualDeclaraPilotStay(
				TfQualDeclaraPilotStay tfQualDeclaraPilotStay) {
			this.tfQualDeclaraPilotStay = tfQualDeclaraPilotStay;
		}
		@JSON(serialize = false)
		public String getSubGroupId() {
			return subGroupId;
		}
		public void setSubGroupId(String subGroupId) {
			this.subGroupId = subGroupId;
		}
		@JSON(serialize = false)
		public String getDetailsid() {
			return detailsid;
		}
		public void setDetailsid(String detailsid) {
			this.detailsid = detailsid;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getTypedesc() {
			return typedesc;
		}
		public void setTypedesc(String typedesc) {
			this.typedesc = typedesc;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@JSON(serialize = false)
		public TfQualLessonComment getTfQualLessonComment() {
			return tfQualLessonComment;
		}
		public void setTfQualLessonComment(TfQualLessonComment tfQualLessonComment) {
			this.tfQualLessonComment = tfQualLessonComment;
		}
		@JSON(serialize = false)
		public ITfQualLessonCommentBS getTfQualLessonCommentBS() {
			return tfQualLessonCommentBS;
		}
		public void setTfQualLessonCommentBS(
				ITfQualLessonCommentBS tfQualLessonCommentBS) {
			this.tfQualLessonCommentBS = tfQualLessonCommentBS;
		}
		@JSON(serialize = false)
		public TfQualPilotFlyrecordbook getTfQualPilotFlyrecordbook() {
			return tfQualPilotFlyrecordbook;
		}
		public void setTfQualPilotFlyrecordbook(
				TfQualPilotFlyrecordbook tfQualPilotFlyrecordbook) {
			this.tfQualPilotFlyrecordbook = tfQualPilotFlyrecordbook;
		}
		@JSON(serialize = false)
		public ITfQualPilotFlyrecordbookBS getTfQualPilotFlyrecordbookBS() {
			return tfQualPilotFlyrecordbookBS;
		}
		public void setTfQualPilotFlyrecordbookBS(ITfQualPilotFlyrecordbookBS tfQualPilotFlyrecordbookBS) {
			this.tfQualPilotFlyrecordbookBS = tfQualPilotFlyrecordbookBS;
		}
		@JSON(serialize = false)
		public ITfQualBaseWorkOrderBS getTfQualBaseWorkOrderBS() {
			return tfQualBaseWorkOrderBS;
		}
		public void setTfQualBaseWorkOrderBS(
				ITfQualBaseWorkOrderBS tfQualBaseWorkOrderBS) {
			this.tfQualBaseWorkOrderBS = tfQualBaseWorkOrderBS;
		}
		@JSON(serialize = false)
		public TfQualPilotAppinformation getTfQualPilotAppinformation() {
			return tfQualPilotAppinformation;
		}
		public void setTfQualPilotAppinformation(TfQualPilotAppinformation tfQualPilotAppinformation) {
			this.tfQualPilotAppinformation = tfQualPilotAppinformation;
		}
		@JSON(serialize = false)
		public ITfQualPilotLicenseRateAppBS getTfQualPilotLicenseRateAppBS() {
			return tfQualPilotLicenseRateAppBS;
		}
		public void setTfQualPilotLicenseRateAppBS(ITfQualPilotLicenseRateAppBS tfQualPilotLicenseRateAppBS) {
			this.tfQualPilotLicenseRateAppBS = tfQualPilotLicenseRateAppBS;
		}
		@JSON(serialize = false)
		public TfQualBaseWorkOrder getTfQualBaseWorkOrder() {
			return tfQualBaseWorkOrder;
		}
		public void setTfQualBaseWorkOrder(TfQualBaseWorkOrder tfQualBaseWorkOrder) {
			this.tfQualBaseWorkOrder = tfQualBaseWorkOrder;
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
		public ITfQualInspectionReportBS getTfQualInspectionReportBS() {
			return tfQualInspectionReportBS;
		}
		public void setTfQualInspectionReportBS(ITfQualInspectionReportBS tfQualInspectionReportBS) {
			this.tfQualInspectionReportBS = tfQualInspectionReportBS;
		}
		public boolean isInspector() {
			return inspector;
		}
		public boolean isBranch() {
			return branch;
		}
		public boolean isTechnical() {
			return technical;
		}
		public boolean isFlySave() {
			return flySave;
		}
		public boolean isLessonSave() {
			return lessonSave;
		}
		public boolean isPracticebook() {
			return practicebook;
		}
		public boolean isPilotApp() {
			return pilotApp;
		}
		public boolean isExambook() {
			return exambook;
		}
		public boolean isRoote() {
			return roote;
		}
		@JSON(serialize = false)
		public String getDeclarInfoTabId() {
			return declarInfoTabId;
		}
		public void setDeclarInfoTabId(String declarInfoTabId) {
			this.declarInfoTabId = declarInfoTabId;
		}

		@JSON(serialize = false)
		public ITfQualPilotDocumentsBS getTfQualPilotDocumentsBS() {
			return tfQualPilotDocumentsBS;
		}
		public void setTfQualPilotDocumentsBS(ITfQualPilotDocumentsBS tfQualPilotDocumentsBS) {
			this.tfQualPilotDocumentsBS = tfQualPilotDocumentsBS;
		}
		@JSON(serialize = false)
		public List<Integer> getStatusList() {
			return statusList;
		}
		public void setStatusList(List<Integer> statusList) {
			this.statusList = statusList;
		}
		@JSON(serialize = false)
		public ITfQualRouteReportBS getTfQualRouteReportBS() {
			return tfQualRouteReportBS;
		}
		public void setTfQualRouteReportBS(ITfQualRouteReportBS tfQualRouteReportBS) {
			this.tfQualRouteReportBS = tfQualRouteReportBS;
		}

		@JSON(serialize = false)
		public String getQtGroupId() {
			return qtGroupId;
		}
		public void setQtGroupId(String qtGroupId) {
			this.qtGroupId = qtGroupId;
		}

		@JSON(serialize = false)
		public ITfQualPilotDetailBS getTfQualPilotDetailBS() {
			return tfQualPilotDetailBS;
		}
		public void setTfQualPilotDetailBS(ITfQualPilotDetailBS tfQualPilotDetailBS) {
			this.tfQualPilotDetailBS = tfQualPilotDetailBS;
		}
		@JSON(serialize = false)
		public boolean isIsfgsfgld() {
			return isfgsfgld;
		}
		public void setIsfgsfgld(boolean isfgsfgld) {
			this.isfgsfgld = isfgsfgld;
		}
		public List<TfQualBaseType> getTfQualBaseTypeList() {
			return tfQualBaseTypeList;
		}
		public void setTfQualBaseTypeList(List<TfQualBaseType> tfQualBaseTypeList) {
			this.tfQualBaseTypeList = tfQualBaseTypeList;
		}
		public String getLikePilotName() {
			return likePilotName;
		}
		public void setLikePilotName(String likePilotName) {
			this.likePilotName = likePilotName;
		}
		@JSON(serialize=false)
		public TfQualInspectionTrainBS getTfQualInspectionTrainBS() {
			return tfQualInspectionTrainBS;
		}
		@Resource(name="tfQualInspectionTrainBS")
		public void setTfQualInspectionTrainBS(TfQualInspectionTrainBS tfQualInspectionTrainBS) {
			this.tfQualInspectionTrainBS = tfQualInspectionTrainBS;
		}
		public boolean isDriverFlag() {
			return driverFlag;
		}
		public void setDriverFlag(boolean driverFlag) {
			this.driverFlag = driverFlag;
		}
		@JSON(serialize = false)
		public String getStateSel() {
			return stateSel;
		}
		public void setStateSel(String stateSel) {
			this.stateSel = stateSel;
		}
		@JSON(serialize = false)
		public String getPlaneSel() {
			return planeSel;
		}
		public void setPlaneSel(String planeSel) {
			this.planeSel = planeSel;
		}
		@JSON(serialize = false)
		public List<BaseAirPlanType> getAirPlanTypeList() {
			return airPlanTypeList;
		}
		public void setAirPlanTypeList(List<BaseAirPlanType> airPlanTypeList) {
			this.airPlanTypeList = airPlanTypeList;
		}
		@JSON(serialize = false)
		public String getSels() {
			return sels;
		}
		public void setSels(String sels) {
			this.sels = sels;
		}
		@JSON(serialize = false)
		public String getTypeSel() {
			return typeSel;
		}
		public void setTypeSel(String typeSel) {
			this.typeSel = typeSel;
		}
		@JSON(serialize = false)
		public String getOrgSel() {
			return orgSel;
		}
		public void setOrgSel(String orgSel) {
			this.orgSel = orgSel;
		}
		@JSON(serialize = false)
		public List<SysOrganization> getSysOrgList() {
			return sysOrgList;
		}
		public void setSysOrgList(List<SysOrganization> sysOrgList) {
			this.sysOrgList = sysOrgList;
		}
		@JSON(serialize = false)
		public ITfQualCheckJobCardBS getCheckJobCardBS() {
			return checkJobCardBS;
		}
		@Resource(name="checkJobCardBS")
		public void setCheckJobCardBS(ITfQualCheckJobCardBS checkJobCardBS) {
			this.checkJobCardBS = checkJobCardBS;
		}
		public boolean isCheckJobCardIsSave() {
			return checkJobCardIsSave;
		}
		@JSON(serialize = false)
		public List<TfQualPilotCourselist> getTfQualPilotCourselistList() {
			return tfQualPilotCourselistList;
		}
		public void setTfQualPilotCourselistList(List<TfQualPilotCourselist> tfQualPilotCourselistList) {
			this.tfQualPilotCourselistList = tfQualPilotCourselistList;
		}	
		@JSON(serialize = false)
		public TfQualPilotCourselist getTfQualPilotCourselist() {
			return tfQualPilotCourselist;
		}
		public void setTfQualPilotCourselist(TfQualPilotCourselist tfQualPilotCourselist) {
			this.tfQualPilotCourselist = tfQualPilotCourselist;
		}
		@JSON(serialize = false)
		public String getCourselistid() {
			return courselistid;
		}
		public void setCourselistid(String courselistid) {
			this.courselistid = courselistid;
		}
		public boolean isBureaucomm() {
			return bureaucomm;
		}
		public void setBureaucomm(boolean bureaucomm) {
			this.bureaucomm = bureaucomm;
		}

		public boolean isInspectorName() {
			return inspectorName;
		}
		@JSON(serialize = false)
		public String getStage() {
			return stage;
		}
		
		
}
