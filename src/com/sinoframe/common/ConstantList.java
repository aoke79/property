package com.sinoframe.common;

import java.util.HashMap;
import java.util.Map;

public final class ConstantList {
	
	private static ConstantList constantList = null;
	
	public static synchronized ConstantList getInstance() {
		if (constantList == null) {
			constantList = new ConstantList();
		}
		return constantList;
	}
	private ConstantList() {}
	
	
	public final static String ResourcePath = "resource";	
	public final static String GroupSession = "blogGroup";
	public final static String MemberSession = "user";
	public final static String OperatorSession = "operator";
	public final static int LimitedMemberSessionAmount = 500;
	public final static int LimitedOperatorSessionAmount = 500;
	//add by QLL 此处应用于教员 检查员
	public static int countQual=-3;
	public static int MemberSessionAmount=0;
	public static int OperatorSessionAmount=0;
	
	public final static String UrlTransferSession = "transferSession";
	
	public final static String BlogGroupTemplatePath = "css/groupCss";
	public final static String BlogGroupTemplateName = "default.css";
	//add by lijia Begin
	//流程任务对应的业务类型
	//事件报告类型
	public final static String BNEventReport = "EventReport";
	//调查通知书
	public final static String BNResearchNotification = "researchNotification";
	//调查报告
	public final static String BNResearchReport = "researchReport";
	//QAR调查通知书
	public final static String BNQarResearchNotification = "QarresearchNotification";
	//QAR调查报告
	public final static String BNQarResearchReport = "QarresearchReport";
	//安全检查通知书
	public final static String BNCheckNotice = "checkNotice";
	//安全检查报告
	public final static String BNCheckReport = "SecCheckreport";
	//安全审计报告
	public final static String BNAuditReport = "SecAuditreport";
	//安全季度检查
	public final static String BNCheckquarterplan = "secCheckquarterplan";
	//安全教育
	public final static String BNEDUPROGRAM = "secEduProgram";
	//qar周报
	public final static String BNSecQarFlyDa = "SecQarFlyDa";
	//QAR调查报告上报流程
	public final static String BNQarResearchReportReport="QarResearchReportReport";
	//督办通知单
	public final static String BNSecOverlooknotice="SecOverlooknotice";
	//督办考核单
	public final static String BNSecOchecknotice="SecOchecknotice";

	
	// 风险评价报告
	public final static String BNRiskAppraiseReport = "RiskAppraiseReport";
	public final static String BNRiskMeasure = "riskMeasure";
	public final static String BNRiskMeasureResult = "riskMeasureResult";
	// 危险源通知单
	public final static String BNRiskSourceDept = "RiskSourceDept";
	
	//预防纠正通知书
	public final static String BNPreventRectifyNote = "PreventRectifyNote";
	//预防纠正项目报告
	public final static String BNPreventRectifyItem = "PreventRectifyItem";
	//预防纠正措施报告
	public final static String BNPreventRectifyMeasure = "PreventRectifyMeasure";
	//预防纠正复核验证
	public final static String BNPreventRectifyVerification = "PreventRectifyVerification";
	//QAR辅助调查
	public final static String BNSecQarProvideRe = "SecQarProvideRe";
	//月度系统评价报告
	public final static String BNSecMonthlyAppraise = "SecMonthlyAppraise";
	//QAR月报
	public final static String BNSecAttachreport = "SecAttachreport";
	//危险源信息报告单
	public final static String BNSecRmRiskSourceMessage = "SecRmRiskSourceMessage";
	//专项任务流程
	public final static String BNSecOsuperviseTask="SecOsuperviseTask";
	//生产讲评会
	public final static String BNSECSAFTYSPEEKMATERIALRISK = "SecSaftyspeekmaterialRisk";
	//生产讲评会会议纪要
	public final static String BNSECMEETMATERIAL = "SecMeetmaterial";
	//延期
	public final static String BN_EXTENSION_ERN = "ResearchNoteExtension";
	public final static String BN_EXTENSION_PRN = "PreventNoteExtension";
	public final static String BN_EXTENSION_PRR = "PreventResultExtension";
	//转事件
	public final static String BN_QtoE = "QtoE";
	//QAR回复
	public final static String BN_QAR_REPLY = "revert";
	//事件调查报告驳回
	public final static String BN_EREPORT_REJECTION = "EventResearchReportRejection";
	
	//航空安全管理部-事件调查部门
	public final static String ORG_EVENT_ID = "8a8a11fb2f9af9de012f9b0468cc0003";
	//航空安全管理部-安全检查部门
	public final static String ORG_CHECK_ID = "8a8a11ef301b2f3301301befaf7b00ae";
	//航空安全管理部-风险管理部门
	public final static String ORG_RISK_ID = "8a8a11ef301b2f3301301bf1c0c700af";
	//航空安全管理部-QAR部门
	public final static String ORG_QAR_ID = "8a8a11ef301b2f3301301bf1f41c00b0";
	//航空安全管理部-督办考核部门
	public final static String ORG_SUPERVISE_ID = "8a8a11ef301b2f3301301bf2559500b1";
	//飞行管理部
	public final static String ORG_FLIGHTMANAGEMENT_ID = "8a8a11ef301b2f3301301bcd3d8d0066";
	//运行标准部
	public final static String ORG_OPERATIONSTANDARD_ID = "8a8a11ef301b2f3301301bcd3d8d0066";
	//总公司ID
	public final static String ORG_CORPORATION_ID = "4028bd812f9105da012f9119b4d50000";
	//add by lijia End
	
	public static String BasePath="";
	public static String AxisAddress="";
	public static int BROWSEGROUPCOUNT = 10;
	public static int RECENTJOINMEMBERCOUNT = 10;
	public static boolean GROUPMANAGERLIMIT = true;
	public static String BLOGDOMAIN = "blogDate/testBlog.action?login=false";

	public static Integer DEFAUT_BLOG_ID=new Integer(-1);
	public static String Default_Group_ID="-1";
	public static Integer Default_Group_Template = 2;
	public static Integer Default_Blog_template = 1;
    public static String CallerPath="";
    public static String AlbumPath="";
    
    public static String ZZID="8a8a115c38ea2ed20138ea403fbd0018";
    
    public static String YINGYUBANGONGSHIJUESEID="8a8a111130b5534e0130b6cd17aa0004";
    public static String ROLE_GEJIDUI="各机队";
    public static String GUOHANGFEIXINGZONGDUI2="国航飞行总队";
    public static String GUOHANGFEIXINGZONGDUI_ID2="8a8a116a2ffbe0e1012ffbe38e310003";
    
    public static String GUOHANGFEIXINGZONGDUI="飞行技术管理室";
    public static String GUOHANGFEIXINGZONGDUI_ID="8a8a11ef301b2f3301301ba980ee0013";
    
    public static String GHFGB="飞行技术管理部";
    
    public static String XN="西南分公司";
    public static String XNID="8a8a11ef301b2f3301301bc4324e004a";
    
    public static String XINANFENGONGSI="西南分公司飞管部";
    public static String XINANFENGONGSIID="8a8a11ef301b2f3301301bc775050053";
    
    public static String XINANFENGONGSIFXB="西南分公司飞行部";
    public static String XINANFENGONGSIFXBID="8a8a11ef301b2f3301301bc59b34004e";
    //浙江分公司飞管部
    public static String ZHEJIANGFGBID="8a8a11ef301b2f3301301bccdf7f0065";
    
    //总队机关 
    public static String ZDJG="8a8a11ef301b2f3301301ba68b6c000d";
    
    
    public static String GEJIDUI="各机队";
    public static String TEMPUSERID="8a8a1111326ffdd4013270110db6000c";
    //国航股份机构ID
    public static String GUOHANGGUFENID="4028bd812f9105da012f9119b4d50000";
    //航空安全管理部
    public static String HAB="8a8a116a2ffce645012ffd57dec70000";
    //运行标准部
    public static String YXBZB="8a8a11ef301b2f3301301bcd83020067";
    //局方
    public static String JF="8a8a11ef31f52c6c0131f5befce500a1";
    
    //飞行技术管理部ID
    public static String GHFGBID="8a8a11ef301b2f3301301bcd3d8d0066";
    
    public static String ROLE_PXBBGS="培训部办公室";
    public static String ROLE_PXBXLDDJL="培训部训练大队经理";
    public static String ROLE_PXBXLDDDZ="培训部训练大队队长";
    //总队三大队
    public static String ZDSDD="8a8a11192ffc6533012ffccfc8860008";
    //天津分公司
    public static String TJFGS="8a8a11ef3183b73d01318456e2a20000"; 
    //天津分公司飞行技术管理部
    public static String TJFGB="8a8a11ef318811170131885a71330011";
    //天津分公司飞行部直属 
    public static String TJFXBZS="8a8a11ef3183b73d01318467c0cf0005";
    //天津分公司飞行部
    public static String TJFXB="8a8a11ef3183b73d0131845f07d60001";
    //天津分公司飞行部三中队
    public static String TJFXBDSZD="8a8a11ef3183b73d013184665a0b0004";
    //国货航飞行运行技术管理部
    public static String GHHFJB="8a8a11ef301b2f3301301bea57af00ac";
    //国货航飞行部
    public static String GHHFXB="8a8a11ef301b2f3301301be5b1ae009d";
    
    
 
    //北京航空飞行部
//    public static String GWJFXB="8a8a11ef31f52c6c0131f5b061f10056";
    public static String GWJFXB="8a8a339935525c9a0135615ffb300053";
    //北京航空飞行技术管理部
//    public static String GWJFGB="8a8a11ef31f52c6c0131f5b4144b0057";
     public static String GWJFGB="8a8a11f1360c038701360f4c2b5403a9";//25
      

 
    
   //add by niujingwei end 
    
    //文件上传路径
    public static String UPLOADPATH="";
    public static String FILEDESC="支持格式:docx/pptx/xlsx/jpg/gif/png/bmp/doc/xls/ppt/html/htm/js/css/txt/sql/chm/swf/pdf/csv/zip/rar/mp3/jar/rm/avi/dat/xml/exe/com/bat/ini/log/bak/DOCX/PPTX/XLSX/JPG/GIF/PNG/BMP/DOC/XLS/PPT/TXT/ZIP/RAR.";
    public static String FILEEXT="*.docx;*.pptx;*.xlsx;*.jpg;*.gif;*.png;*.bmp;*.doc;*.xls;*.ppt;*.html;*.htm;*.js;*.css;*.txt;*.sql;*.chm;*.swf;*.pdf;*.csv;*.zip;*.rar;*.mp3;*.jar;*.rm;*.avi;*.dat;*.xml;*.exe;*.com;*.bat;*.ini;*.log;*.bak;*.DOCX;*.PPTX;*.XLSX;*.JPG;*.GIF;*.PNG;*.BMP;*.DOC;*.XLS;*.PPT;*.TXT;*.ZIP;*.RAR";
    public static String IMAGESET=".jpg.gif.jpeg.png.bmp";
    
    
   //导出excel
    public static String UPLOADPATHEXCEl="fileExcel";
    public static String ENEXAMEXCEL="enExamExcel";
    
    //机构Id与文号对应关系
    public static Map<String, String> ORG_NO = new HashMap<String, String>();
    
	public static String ORG_HANGKONGANQUAN_ID = "8a8a11ef301b2f3301301bf1c0c700af";
	public static void  init(String basePath){
		 BasePath = basePath;
		 AxisAddress = basePath + "/WEB-INF/classes/axis.properties";
		 CallerPath=basePath+"/accessory/caller";
		 AlbumPath=basePath+"/accessory/album";
		
	}
	
//	//角色
	public static String ROLES_LEADER = "领导";
	public static String ROLES_MANAGER = "高级经理";
	public static String ROLES_MANAGER_RESEARCH = "调查部门高级经理";
	public static String ROLES_MANAGER_INSPECT = "检查部门高级经理";
	public static String ROLES_MANAGER_RISK = "风险部门高级经理";
	public static String ROLES_MANAGER_MANAGE = "管理部门高级经理";
	public static String ROLES_MANAGER_SUPERVISER = "督办部门高级经理";   
	public static String ROLES_MANAGER_QAR = "Q部门高级经理";

	//定制被使用的单位名称
	public static String ORG_COMPANY = "总公司";
	public static String ORG_COMPANY_PRI = "一级单位";
	public static String ORG_COMPANY_SEC = "二级单位";
	public static String ORG_COMPANY_TRI = "三级单位";
	
	//按钮文字
	public static String BUTTON_SAVE = "保存";
	public static String BUTTON_SUBMIT = "提交";
	public static String BUTTON_REPORT = "上报";
	public static String BUTTON_POST = "下发";
	public static String BUTTON_REJECT = "驳回";
	public static String BUTTON_REPEAL = "撤回";
	public static String BUTTON_RECALL = "上级单位驳回";
	public static String BUTTON_AGREE = "同意";
	public static String BUTTON_CLOSE = "关闭";
	public static String BUTTON_TRANSPOND = "转发";
	public static String BUTTON_DECIDE = "确认";
	public static String BUTTON_ASSIGN = "指派";
	public static String BUTTON_REPLY = "回复";
	public static String BUTTON_DELAY = "申请延期";
	public static String BUTTON_EXTENTION = "延期";
	public static String BUTTON_REDO = "重新制定措施";
	public static String BUTTON_BACK = "反馈";
	public static String BUTTON_PUBLISH = "发布";
	public static String BUTTON_SIGN = "签收";
	public static String BUTTON_REOPEN = "重新开启";
	public static String BUTTON_COMPLEMENT = "补报";
	public static String BUTTON_PROVIDE = "辅助调查";
	public static String BUTTON_APPROVESUBMIT = "审核提交";
	
	//按钮标识
	public static String BUTTON_TARGET_SAVE = "save";       //保存
	public static String BUTTON_TARGET_SUBMIT = "submit";   //提交
	public static String BUTTON_TARGET_REPORT = "report";   //上报
	public static String BUTTON_TARGET_POST = "post";       //下发
	public static String BUTTON_TARGET_REJECT = "reject";   //驳回
	public static String BUTTON_TARGET_REPEAL = "repeal";   //撤回
	public static String BUTTON_TARGET_AGREE = "agree";     //同意
	public static String BUTTON_TARGET_CLOSE = "close";     //关闭
	public static String BUTTON_TARGET_TRANSPOND = "transpond";   //转发
	public static String BUTTON_TARGET_DECIDE = "decide";   //确认
	public static String BUTTON_TARGET_ASSIGN = "assign";   //指派
	public static String BUTTON_TARGET_REPLY = "reply";     //回复
	public static String BUTTON_TARGET_DELAY = "delay";     //申请延期
	public static String BUTTON_TARGET_EXTENTION = "extention";   //延期
	public static String BUTTON_TARGET_REDO = "redo";       //重新制定措施
	public static String BUTTON_TARGET_BACK = "back";       //反馈
	public static String BUTTON_TARGET_PUBLISH = "publish"; //发布
	public static String BUTTON_TARGET_SIGN = "sign";       //签收
	public static String BUTTON_TARGET_REOPEN = "reopen";       //重新开启
	public static String BUTTON_TARGET_COMPLEMENT = "complement";     //补报
	public static String BUTTON_TARGET_PROVIDE = "provide";  //辅助调查
	public static String BUTTON_TARGET_APPROVESUBMIT = "approveSubmit";  //审核提交
	
	//导出excel
	public static String  SECAUDITNOTEEXCEL = "安全审计统计.xls";
	
	public static Integer DEFAULT_TEMPLATESORT_PARENTID=new Integer(-1);
	public static Integer DEFAULT_DELETE_TAG=new Integer(0);
	public static Integer DEFAULT_SHOW_TAG=new Integer(1);
	
	//流程定义文件
	public static String ENGLISH_TRAIN="/com/sms/training/englishtrain/process/trainSignUp.zip";
	public static String ENGLISH_TRAIN2="/com/sms/training/englishtrain/process/trainSignUp2.zip";
	public static String ENGLISH_TRAIN_SIGN="/com/sms/training/englishtrain/process/enTrain.zip";
	public static String SUB_ENGLISH_TRAIN_SIGN="/com/sms/training/englishtrain/process/subEnTrain.zip";
	//update by QLL 
	public static String ENGLISH_TRAIN_SIGN_NEW="/com/sms/training/englishtrain/process/enTrainNew.zip";
	//资质申请流程定义文件
//	public static String QUALIFICATION_APPLY="/com/sms/training/qualification/process/qualificationApply.zip";
//	public static String SUB_QUALIFICATION_APPLY="/com/sms/training/qualification/process/subQualificationApply.zip";
	public static String QUALIFICATION_APPLY_F4="/com/sms/training/qualification/process/qualificationApplyF4.zip";
	public static String SUB_QUALIFICATION_APPLY_F4="/com/sms/training/qualification/process/subQualificationApplyF4.zip";

	public static final String QUALIFICATION_APPLY_MJ = "/com/sms/training/qualification/process/qualificationApplyM.zip";//add by licumn
	public static final String SUB_QUALIFICATION_APPLY_MJ = "/com/sms/training/qualification/process/subQualificationApplyM.zip";//add by licumn

	//检查员流程
	public static final String QUALIFICATION_APPLY_INSPECTOR = "/com/sms/training/qualification/process/qualificationApplyInspector.zip";//add by licumn
	public static final String SUB_QUALIFICATION_APPLY_INSPECTOR_GS = "/com/sms/training/qualification/process/subQualificationApplyInspectorGs.zip";//add by licumn;
	public static final String SUB_QUALIFICATION_APPLY_INSPECTOR_DELEGATE = "/com/sms/training/qualification/process/subQualificationApplyInspectorDelegate.zip";//add by licumn;

	public static final String QUALIFICATION_APPLY_F1 = "/com/sms/training/qualification/process/qualificationApplyF1.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_F1 = "/com/sms/training/qualification/process/subQualificationApplyF1.zip";//add by kid
	
	public static final String QUALIFICATION_APPLY_J = "/com/sms/training/qualification/process/qualificationApplyJ.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_J = "/com/sms/training/qualification/process/subQualificationApplyJ.zip";//add by kid
    
	//一般资格流程定义
	public static final String QUALIFICATION_APPLY_GENERAL = "/com/sms/training/qualification/process/qualificationApplyGeneral.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_GENERAL = "/com/sms/training/qualification/process/subQualificationApplyGeneral.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_GENERAL_CFBXLJL = "/com/sms/training/qualification/process/subQualificationApplyGeneralCFBXLJL.zip";//add by pxp
	public static final String QUALIFICATION_APPLY_GENERAL_NDHXJC = "/com/sms/training/qualification/process/qualificationApplyGeneralNDHXJC.zip";//add by yp
	public static final String QUALIFICATION_APPLY_GENERAL_CHZG = "/com/sms/training/qualification/process/qualificationApplyGeneralChzg.zip";
	public static final String SUB_QUALIFICATION_APPLY_GENERAL_CHZG = "/com/sms/training/qualification/process/subQualificationApplyGeneralChzg.zip";
	
	
	//转机型流程（不含差异流程）
	public static final String QUALIFICATION_APPLY_TR="/com/sms/training/qualification/process/qualificationApplyTr.zip";//add by licumn;
	public static final String SUB_QUALIFICATION_APPLY_TR="/com/sms/training/qualification/process/subQualificationApplyTr.zip";//add by licumn;
	
	//转机型   差异Difference
	public static final String QUALIFICATION_APPLY_DIFFERENCE = "/com/sms/training/qualification/process/qualificationApplyDifference.zip";//add by zhanghuifen
	public static final String SUB_QUALIFICATION_APPLY_DIFFERENCE = "/com/sms/training/qualification/process/subQualificationApplyDifference.zip";//add by zhanghuifen
	
	//返聘飞行员流程
	public static final String QUALIFICATION_APPLY_REHELLORING = "/com/sms/training/qualification/process/qualificationApplyRehelloring.zip";//add by zhanghuifen
	public static final String SUB_QUALIFICATION_APPLY_REHELLORING = "/com/sms/training/qualification/process/subQualificationApplyRehelloring.zip";//add by zhanghuifen
	
	//FTD教员流程
	public static final String QUALIFICATION_FTD_TEACHER = "/com/sms/training/qualification/process/qualificationApplyFtdTeacher.zip";//add by zhanghuifen
	public static final String SUB_QUALIFICATION_FTD_TEACHER = "/com/sms/training/qualification/process/subQualificationApplyFtdTeacher.zip";//add by zhanghuifen
	public static final String QUALIFICATION_FLIGHT_THEORY_TEACHER = "/com/sms/training/qualification/process/qualificationApplyFlightTheoryTeacher.zip";//add by pxp
	public static final String SUB_QUALIFICATION_FLIGHT_THEORY_TEACHER = "/com/sms/training/qualification/process/subQualificationApplyFlightTheoryTeacher.zip";//add by pxp
	
	//教员/考试员：飞行教员模拟机复训
	public static final String QUALIFICATION_FXJYMNJFX_TEACHER="/com/sms/training/qualification/process/qualificationApplyTeacherFXJYMNJFX.zip";//zhongchunping
	public static final String SUB_QUALIFICATION_FXJYMNJFX_TEACHER="/com/sms/training/qualification/process/subQualificationApplyTeacherFXJYMNJFX.zip";//zhongchunping
	
	
	//英语考试流程定义
	public static String ENGLISH_EXAM_SIGNUP="/com/sms/training/english/process/enExamSign.zip";
	public static String ENGLISH_EXAM_SIGNUP2="/com/sms/training/english/process/enExamSign2.zip";
	public static String ENGLISH_EXAM="/com/sms/training/english/process/enExam.zip";

	public static final String QUALIFICATION_APPLY_FXTXJCY = "/com/sms/training/qualification/process/qualificationApplyFXTXJCY.zip";//add by zll
	public static final String SUB_QUALIFICATION_APPLY_FXTXJCY = "/com/sms/training/qualification/process/subQualificationApplyFXTXJCY.zip";//add by zll

	public static final String QUALIFICATION_APPLY_ENGLISH_KSY = "/com/sms/training/qualification/process/qualificationApplyEnglishKSY.zip";//add by zhanghuifen
	//英语教员流程定义
	public static final String QUALIFICATION_APPLY_ENGLISH_TEACHER = "/com/sms/training/qualification/process/qualificationApplyEnglishTeacher.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_ENGLISH_TEACHER = "/com/sms/training/qualification/process/subQualificationApplyEnglishTeacher.zip";//add by kid
	
	//英语/飞行通信教员流程定义
	public static final String QUALIFICATION_APPLY_ENGLISH_FXTXJY = "/com/sms/training/qualification/process/qualificationApplyEnglishFXTXJY.zip";//zhongchunping
	public static final String SUB_QUALIFICATION_APPLY_ENGLISH_FXTXJY = "/com/sms/training/qualification/process/subQualificationApplyEnglishFXTXJY.zip";//zhongchunping
	
	//除防冰教员，飞行教员理论教员流程定义
	public static final String QUALIFICATION_APPLY_ICE_TEACHER = "/com/sms/training/qualification/process/qualificationApplyIceTeacher.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_ICE_TEACHER = "/com/sms/training/qualification/process/subQualificationApplyIceTeacher.zip";//add by kid
	
	//新雇员流程定义
	public static final String QUALIFICATION_APPLY_NEW_EMPLOYEES = "/com/sms/training/qualification/process/qualificationApplyNewEmployees.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_NEW_EMPLOYEES = "/com/sms/training/qualification/process/subQualificationApplyNewEmployees.zip";//add by kid
	
	//特殊资格流程定义
	public static final String QUALIFICATION_APPLY_SPECIAL = "/com/sms/training/qualification/process/qualificationApplySpecial.zip";//add by kid
	public static final String SUB_QUALIFICATION_APPLY_SPECIAL = "/com/sms/training/qualification/process/subQualificationApplySpecial.zip";//add by kid
	
//	//督办考核单位
	public static String ORG_SECOVERLOOK="8a8a11ef301b2f3301301bf2559500b1";
	public static String FILE_PORTAL_DATA = "portalData";                         //门户信息
//
	//门户文件类型编码
	public static String PORTAL_DAY_REPORT = "DP";            //日报
	public static String PORTAL_QAR_WEEK_REPORT = "QW";       //QAR周报
	public static String PORTAL_QAR_MONTH_REPORT = "QM";      //QAR月报
	public static String PORTAL_PRODUCE_APPRAISE = "PA";      //生产讲评会
	public static String PORTAL_INDUSTRY_NEWS = "IN";         //行业信息
	public static String PORTAL_AVIATION_SAFETY = "AS";       //航空安全
	public static String PORTAL_FILES_NOTIFICATION = "FN";    //文件与通告
	public static String PORTAL_SAFETY_LECTURE = "SL";        //安全文化
	public static String PORTAL_SMS_MANUAL = "SM";            //SMS手册
	
	//门户文件类型名称
	public static String PORTALNAME_DAY_REPORT = "日报";
	public static String PORTALNAME_QAR_WEEK_REPORT = "QAR周报";
	public static String PORTALNAME_QAR_MONTH_REPORT = "QAR月报";
	public static String PORTALNAME_PRODUCE_APPRAISE = "生产讲评会";
	public static String PORTALNAME_INDUSTRY_NEWS = "行业信息";
	public static String PORTALNAME_AVIATION_SAFETY = "航空安全";
	public static String PORTALNAME_FILES_NOTIFICATION = "文件与通告";
	public static String PORTALNAME_SAFETY_LECTURE = "安全文化";
	public static String PORTALNAME_SMS_MANUAL = "SMS手册";
	
	//预警颜色
	public static String WARNING_RED = "8a8a119232ec53c80132ec7009e10003";
	public static String WARNING_YELLOW = "8a8a119232ec53c80132ec7059830004";
	public static String WARNING_ORANGE = "8a8a119232ec53c80132ec70a8910005";
	public static String WARNING_GREEN = "8a8a119232ec53c80132ec70d8410006";
	public static String WARNING_WHITE = "8a8a119232ec53c80132ec7112db0007";
	
	// 检查类型为安全自查的Id
	public static String SEC_SELFCHECK_KIND_ID = "02";
	
	public static String  getValueByParameter(String value){
		if(null==value||"null".equals(value))
			value = "";
		return value ;
	}
	
	public static int getValueStrTransInt(String value){
		int iReturn=0;
		if(null==value||"null".equals(value)||"".equals(value)){
			return iReturn;
		}else{
			
			iReturn =  Integer.parseInt(value);
			return iReturn;
		}
		
	}
	
}
