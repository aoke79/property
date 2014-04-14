package com.sms.training.qualification.web.action.tfQuaApply;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.json.annotations.JSON;


import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.*;

@Results({
		@Result(name = "lessons",  location = "/sms/training/qualification/qualDeclaration/trainingAndInspectionRecords.jsp"),
		@Result(name = "courses", location = "/sms/training/qualification/qualDeclaration/coursePage.jsp"),
		@Result(name = "checkCourse", location = "/sms/training/qualification/qualDeclaration/coursePageCheck.jsp"),
		@Result(name = "signature", type = "json"),
		@Result(name = "flightDataRecorder", location = "/sms/training/qualification/qualDeclaration/flightDataRecorder.jsp"),
		@Result(name = "technicalInspectionReport", location = "/sms/training/qualification/qualDeclaration/technicalInspectionReport.jsp"),
		@Result(name = "localTrainOrder", location = "/sms/training/qualification/localTrainOrder/localTrainOrder.jsp"),
		@Result(name = "toLocalInspectReport", location = "/sms/training/qualification/localTrainOrder/localInspectReport.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "image", params = { "inputName", "imgStream" }, type = "stream"),
		@Result(name = "qualPilotBaseInfo", location = "/sms/training/qualification/quaApply/qualPilotBaseInfo.jsp"),
		@Result(name = "QUAL_TR_flightDataRecorder", location = "/sms/training/qualification/quaApplyTr/flightDataRecorderTR.jsp"),
//		@Result(name = "QUAL_ _flightDataRecorder", location = ""),
		@Result(name = "json", type = "json"),

		@Result(name = "QUAL_TR_inspectionReport", location = "/sms/training/qualification/quaApplyTr/technicalInspectionReportTR.jsp"),
//		@Result(name = "QUAL_TR_inspectionReport", location = ""),
		
		})
@ParentPackage("sinoframe-default")
public class QualifiedDeclarationAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static String moduleName = "QualifiedDeclarationAction";
	
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	// 页码
	private int pageNum = 1;
	// 每页显示条数
	private int numPerPage = 20;
	// 消息实体
	private Message message;
	// 从页面接收到的 信息id
	private String ids;
	// 课次评语
	private TfQualLessonComment tfQualLessonComment;
	// 课次
	private TfQualBaseLesson tfQualBaseLesson;
	// 课次列表
	private List<TfQualBaseLesson> lessonList;
	// 课程列表(左侧)
	private List<TfQualBaseLessonCourse> courseList0;
	// 课程列表(右侧)
	private List<TfQualBaseLessonCourse> courseList1;
	// 资质类型
	private TfQualBaseType tfQualBaseType;
	// 申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	// 申报人员明细service
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	// 课次评论service
	private ITfQualLessonCommentBS tfQualLessonCommentBS;
	// 课程成绩service
	private ITfQualLessonCourseResultBS tfQualLessonCourseResultBS;
	// 签名日期
	private String signDate;
	//签名人（教员/检查员）的 用户id
	private String cmuser;
	//课程成绩列表（在页面table左侧显示）
	private List<MyResult> myResultList_left;
	//课程成绩列表（在页面table右侧显示）
	private List<MyResult> myResultList_right;
	//表单数据对象，用于向页面传递检查课成绩中的状态值（Y/N）
	private JCKFormData form;
	//飞行员技术检查报告
	private  TfQualInspectionReport tfQualInspectionReport;
	//技术检查报告表单数据
	private InspectionFormData inspectionForm;
	//飞行员技术检查报告service
	private ITfQualInspectionReportBS tfQualInspectionReportBS;
	//申报人员id
	private String peopleId;
	//申报信息id
    private String declaredinfoid;
    private String detailsid;
    private String subProcessid;
    
    private TfQualBaseSignature signature;

	private String fmtSigDate;
 	
 	private List<TfQualDeclaraPilot> tfQualDeclaraPilotList = new ArrayList<TfQualDeclaraPilot>();
	private List<TfQualBaseComment> commentTemplates =new ArrayList<TfQualBaseComment>();
	
	private ITfQuaApplyJbpmBS tfQuaApplyJbpmBS;
	private String userid;
	private String hrid;
	
	//定义一个list 用来存放是否保存的标志
	private List<String> statusList=new ArrayList<String>();
	// 用来标志 当前是第几课的index   做跳转时用的
	private int index ;
	
	
	// 用于显示人员照片的字节流
	private ByteArrayInputStream imgStream;
	// 人员 十位编码
	private String hridShowPhoto;
	/**资质类型 子类id*/
	private String subGroupId;
	// 判断驾驶员飞行经历记录薄  是胡玉录、刘铁祥保存还是陈昕、王菲保存
	private String saveFlag; 

	
	public String qualPilotBaseInfo(){
		try{
			tfQualDeclaraPilot=tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "qualPilotBaseInfo";
	}
	
	
	/**
	 * 根据十位编码显示对应人员照片
	 * 
	 * @return
	 */
	public String showPhoto() {
		try {
			CmPeoplePhoto photo = tfQualDeclaraPilotBS.findUniqueBySingleQuery(
					CmPeoplePhoto.class.getSimpleName(), "hrid", hridShowPhoto);
			if (photo != null && photo.getPhoto() != null) {
				imgStream = new ByteArrayInputStream(photo.getPhoto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "image";
	}

	/**
	 * 保存 课次评语 及对应的 课程成绩
	 * 
	 * @return
	 */
	public String saveComment() {
		try {
			List<TfQualBaseLessonCourse> courseList = tfQualDeclaraPilotBS
					.findCourses(tfQualBaseLesson.getLessonId());
			//构造一个map，key： 课程 id，value：状态值Y/N
			Map<String, String> idsAndStatus = new HashMap<String, String>();
			for (TfQualBaseLessonCourse cs : courseList) {
				idsAndStatus.put(cs.getCourseId(), "N");
			}
			if (ids != null && !ids.trim().equals("")) {
				for (String id : StringUtils.split(ids, ",")) {
					idsAndStatus.put(id.trim(), "Y");
				}
			}
			// 保存 课次评语
			tfQualLessonComment.setLessonid(tfQualBaseLesson.getLessonId());
			tfQualLessonComment.setSigningdate(DateTool.parse(signDate, DateTool.DEFAULT_DATETIME_FORMAT));
			if("".equals(tfQualLessonComment.getLessoncommentid())){
				tfQualLessonComment.setLessoncommentid(null);
			}
			tfQualLessonCommentBS.saveOrUpdate(tfQualLessonComment);
			// 保存 课程成绩
			tfQualLessonCourseResultBS.save(idsAndStatus, tfQualLessonComment);
			setMessage(getSuccessMessage(getText("addSuccess"), "haolimingwrite","forward",
					"tf-qua-apply/qualified-declaration!listLessons.do?tfQualDeclaraPilot.detailsid="+tfQualLessonComment.getDetailsid()+"&index="+index));
//			setTabIndexToReload(index);
		} catch (Exception e) {
			tfQualLessonCommentBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("addFail")));
			e.printStackTrace();
		}
		return "SUCCESS";
	}


	/**
	 * 保存 检查课课次评语 及对应的 课程成绩
	 * 
	 * @return
	 */
	public String saveCommentCk() {
		try {
			Map<String, String> idsAndStatus = new HashMap<String, String>();
			for (int i = 62; i <= 127; i++) {
				idsAndStatus.put(i + "", "N");
			}
			if (ids != null && !ids.trim().equals("")) {
				for (String id : StringUtils.split(ids, ",")) {
					idsAndStatus.put(id.trim(), "Y");
				}
			}
			tfQualLessonComment.setLessonid(tfQualBaseLesson.getLessonId());
			tfQualLessonComment.setSigningdate(DateTool.parse(signDate, DateTool.DEFAULT_DATETIME_FORMAT));
			if("".equals(tfQualLessonComment.getLessoncommentid())){
				tfQualLessonComment.setLessoncommentid(null);
			}
			tfQualLessonCommentBS.saveOrUpdate(tfQualLessonComment);
			// 保存 课程成绩
			tfQualLessonCourseResultBS.save(idsAndStatus, tfQualLessonComment);
			setMessage(getSuccessMessage(getText("addSuccess"), "haolimingwrite","forward",
					"tf-qua-apply/qualified-declaration!listLessons.do?tfQualDeclaraPilot.detailsid="+tfQualLessonComment.getDetailsid()+"&index="+index));
		} catch (Exception e) {
			tfQualLessonCommentBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("addFail")));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 * 获取课次列表
	 * 
	 * @return
	 */
	public String listLessons() {
		try {
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
			if (tfQualDeclaraPilot != null){
				tfQualBaseType=tfQualDeclaraPilot.getTfQualBaseType();
				//如果是一般资格流程的模拟机复训
				if("QUAL_YB_MNJFXJC".equals(tfQualBaseType.getTfQualBaseTgroup().getTypegroupid())){
					lessonList = tfQualDeclaraPilotBS.findLessons(tfQualBaseType, tfQualDeclaraPilot.getCmPeople().getId());
				}else{
					lessonList = tfQualDeclaraPilotBS.findLessons(tfQualBaseType.getTypeid());
				}
			}
			for (int i = 0,n=lessonList.size(); i < n; i++) {
				tfQualLessonComment= tfQualLessonCommentBS.findComment(tfQualDeclaraPilot.getDetailsid(),lessonList.get(i).getLessonId());
				statusList.add(tfQualLessonComment==null?"N":"Y");
			}
			//点击查看的时候，就得先查找到评语模板
			commentTemplates=tfQualInspectionReportBS.getcommentTemplates(tfQuaApplyJbpmBS.getSessionRoleList(this.getUser()),"KCPY");		
		} catch (Exception e) {
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "lessons";
	}

	/**
	 * 获取课程列表
	 * 
	 * @return
	 */
	public String listCourses() {
		try {
			userid = this.getUser().getUserId();
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
			tfQualBaseLesson = tfQualDeclaraPilotBS.findById(TfQualBaseLesson.class, tfQualBaseLesson.getLessonId());
			//查找到评语模板  
			commentTemplates=tfQualInspectionReportBS.getcommentTemplates(tfQuaApplyJbpmBS.getSessionRoleList(this.getUser()),"KCPY");
			//查找对应的课次评语
			if(tfQualDeclaraPilot!=null && tfQualBaseLesson!=null){
				tfQualLessonComment= tfQualLessonCommentBS.findComment(tfQualDeclaraPilot.getDetailsid(),tfQualBaseLesson.getLessonId());
			}
			//获取签名imageurl和日期
			if (tfQualLessonComment != null) {
				getSignatureAndDate();
				Date date = tfQualLessonComment.getSigningdate();
				signDate = date == null ? "" : DateTool.formatDate(date,DateTool.DEFAULT_DATETIME_FORMAT);
			}
			//根据课次类型判断，如果是检查课
			if (tfQualBaseLesson.getLessonType().equalsIgnoreCase("c")) {
				if(tfQualLessonComment!=null){
					List<TfQualLessonCourseResult> results=tfQualLessonCommentBS.findResultList(tfQualLessonComment.getLessoncommentid());
					form=new JCKFormData();
					//反射方法调用，给FormData属性赋值
					for(TfQualLessonCourseResult rs:results){
						Method method= JCKFormData.class.getDeclaredMethod("setJck"+rs.getCourseid().trim(),String.class);
						method.invoke(form, rs.getResultsstatus());
					}
				}
				return "checkCourse";
			}
			//非检查课
			if(tfQualLessonComment==null){
				//如果课次评语为null，则只需要将课程list显示在页面
				List<TfQualBaseLessonCourse> courseList = tfQualDeclaraPilotBS.findCourses(tfQualBaseLesson.getLessonId());
				int line = courseList.size() % 2 == 0 ? courseList.size() / 2: courseList.size() / 2 + 1;
				courseList0 = courseList.subList(0, line);
				courseList1 = courseList.subList(line, courseList.size());
			}else{
				//如果课次评语不为null，则根据评语查找到对应的成绩list，将自定义课程成绩list显示在页面
				List<MyResult> myResultList=tfQualLessonCommentBS.findMyResultList(tfQualBaseLesson.getLessonId(),tfQualLessonComment.getLessoncommentid());
				int line = myResultList.size() % 2 == 0 ? myResultList.size() / 2: myResultList.size() / 2 + 1;
				myResultList_left = myResultList.subList(0, line);
				myResultList_right = myResultList.subList(line, myResultList.size());
			}
		} catch (Exception e) {
			tfQualDeclaraPilotBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "courses";
	}

	/**
	 * 获取教员/检查员 签名  (驾驶员升级和检查记录簿)
	 * 
	 * @return
	 */

	@JSON(serialize = false)
	public String getSignatureAndDate(){
		try{
			QuaCmUser user = tfQualDeclaraPilotBS.findById(QuaCmUser.class, this.getUser().getUserId());
			signature = tfQualDeclaraPilotBS.findUniqueBySingleQuery("TfQualBaseSignature","cmuser.userId", user.getUserId());
			if(signature!=null){
				cmuser = user.getUserId();
				signDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			}
		}catch (Exception e) {
			this.message=getFailMessage(e.getMessage());
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "signature";
	}
	
	/**
	 * 获取教员/检查员 签名  (技术检查报告)
	 * 
	 * @return
	 */

	@JSON(serialize = false)
	public String getSignatureAndDate2() {
		try{
			QuaCmUser user = tfQualDeclaraPilotBS.findById(QuaCmUser.class, this.getUser().getUserId());
			signature = tfQualDeclaraPilotBS.findUniqueBySingleQuery("TfQualBaseSignature","cmuser.userId", user.getUserId());
			if(signature!=null){
				signDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				fmtSigDate=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			}
		}catch (Exception e) {
			tfQualDeclaraPilotBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "signature";
	}
	
	/**
	 *  跳至   F4-F5驾驶员飞行记录簿   页面
	 * @return
	 */
	public String flightDataRecorder() {
		try {
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,detailsid);
			//获取当前申报信息所属大类
			String typeGroup=tfQualDeclaraPilot.getTfQualBaseType().getTfQualBaseTypeGroup().getQtgroupid();
			if("QUAL_TR".equals(typeGroup)){
				return "QUAL_TR"+"_flightDataRecorder";
			}
		}catch (Exception e) {
			tfQualInspectionReportBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "flightDataRecorder";
	}

	/**
	 * 跳转到技术检查报告页面
	 * @return
	 */
	public String toInspectionReportPage() {//psignatureUrl
		String returnPage="technicalInspectionReport";//默认返回页面
		try {
			// 根据申报人员明细查找对应的检查报告
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,detailsid);
			//获取当前申报信息所属大类
			String typeGroup=tfQualDeclaraPilot.getTfQualBaseType().getTfQualBaseTypeGroup().getQtgroupid();
			if("QUAL_TR".equals(typeGroup)){
				returnPage= "QUAL_TR"+"_inspectionReport";
			}
			tfQualInspectionReport=tfQualInspectionReportBS.findInspectionReport(tfQualDeclaraPilot.getDetailsid(),"jcbg");

			//获取评语模板list
			commentTemplates=tfQualInspectionReportBS.getcommentTemplates(tfQuaApplyJbpmBS.getSessionRoleList(this.getUser()),"JLBPY");

			// 如果没找到
			if(tfQualInspectionReport==null){
				return returnPage;
			}
			// 如果找到了,获取表单数据，包括飞行员签名
			fetch();
		} catch (Exception e) {
			tfQualInspectionReportBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return returnPage;
	}
	
	private void fetch()throws Exception{
		// 查找对应检查报告成绩list
		List<TfQualInspectionReportD> results=tfQualInspectionReportBS.findResultList(tfQualInspectionReport.getReportid());
		// 反射，给InspectionFormData赋值
		inspectionForm=new InspectionFormData();
		for(TfQualInspectionReportD  rs : results){
			Method method=InspectionFormData.class.getMethod("setJcbg"+rs.getBasedataid(), TfQualInspectionReportD.class);
			method.invoke(inspectionForm, rs);
		}
		//获取 飞行员签名 日期 
		if(tfQualInspectionReport.getPilotdate()!=null){
			signDate = DateTool.formatDate(tfQualInspectionReport.getPilotdate(), "yyyy年MM月dd日");
		}
		String pilot=tfQualInspectionReport.getPilot();
		if(pilot!=null && !pilot.trim().equals("")){
			TfQualPilotSignature ps=tfQualInspectionReportBS.findUniqueBySingleQuery("TfQualPilotSignature", "ploginId", pilot.trim());
			hrid = ps.getPloginId();
		}
	}
	
	/**
	 *  打开  本场训练单 填写 页面
	 * @return
	 */
	public String localTrainOrder() {
		try {
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,
							tfQualDeclaraPilot.getDetailsid());
		}catch (Exception e) {
			tfQualInspectionReportBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "localTrainOrder";
	}
	
	

	/**
	 * 跳至  本场训练单(类似于技术检查报告)页面
	 * @return
	 */
	public String toLocalInspectReport(){
		try {
			 userid = this.getUser().getUserId();
			// 根据申报人员明细查找对应的检查报告
			tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
			tfQualInspectionReport=tfQualInspectionReportBS.findInspectionReport(tfQualDeclaraPilot.getDetailsid(),"bcxl");

			//获取评语模板list
			commentTemplates=tfQualInspectionReportBS.getcommentTemplates(tfQuaApplyJbpmBS.getSessionRoleList(this.getUser()),"JLBPY");
			
			// 如果没找到
			if(tfQualInspectionReport==null){
				return "toLocalInspectReport";
			}
			
			// 如果找到了,获取表单数据，包括飞行员签名
			fetch();
			
		} catch (Exception e) {
			tfQualInspectionReportBS.getErrorLog().info(
					e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toLocalInspectReport";
	}
	
	/**
	 * 保存检查报告评语、成绩
	 * @return
	 */
	
	public String saveInspectionReport(){
		try {
			if("".equals(tfQualInspectionReport.getReportid().trim())){
				tfQualInspectionReport.setReportid(null);
			}
			if( !"".equals(tfQualInspectionReport.getInspector().getCmuser().getUserId())){
				Date t = new Date();
				tfQualInspectionReport.setInspectordate(t);
			}
			tfQualInspectionReport.setModifier(getUser().getUserId());
			
			tfQualInspectionReport.setPilotdate(DateTool.parse(signDate, "yyyy年MM月dd日"));
			TfQualBaseSignature sign= tfQualInspectionReport.getInspector();
			if( !"".equals(sign.getSignatureid())){
				tfQualInspectionReportBS.update(sign, sign.getSignatureid());
			}
			tfQualInspectionReportBS.saveOrUpdate(tfQualInspectionReport);
			// 保存 课程成绩
				tfQualInspectionReportBS.saveResults(inspectionForm,tfQualInspectionReport);
			
			setTabIndexToReload("1");
			if("HYLLTY".equals(saveFlag)){
				setMessage(getSuccessMessage(getText("addSuccess"), "xunlianshencha","",""));
			}else{
				setMessage(getSuccessMessage(getText("addSuccess"), "haolimingwrite","closeCurrent",""));
			}
			//如果是最后节点王菲填写意见 则对改变被批人的状态"已填意见"
//			List<SysRole>  roleList=tfQuaApplyJbpmBS.getSessionRoleList(getUser());
//			for(int i = 0, len = roleList.size(); i < len; i ++){
//				if(roleList.get(i).getRoleName().contains("资质-公司飞管专员") ){
//					detailsid =tfQualInspectionReport.getTfQualDeclaraPilot().getDetailsid();
//					tfQualDeclaraPilot = tfQualDeclaraPilotBS.findById(TfQualDeclaraPilot.class, detailsid);
//					tfQualDeclaraPilot.setState("GSZY_O");
//					tfQualDeclaraPilotBS.saveOrUpdate(tfQualDeclaraPilot);				 
//				}
//			}
			
		} catch (Exception e) {
			tfQualLessonCommentBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("addFail")));
			e.printStackTrace();
		}
		return "SUCCESS";  //张会粉用，需要改动跟我商量
	}
	
	private void setTabIndexToReload(String index){ //TODO 处理子标签页跳转问题
		this.getServletRequest().getSession().setAttribute("tabIndex_toIndexTask", index);
		if(index!=null && !index.equals("0")){
			this.getServletRequest().getSession().setAttribute("flush_toIndexTask", "y");
		}
	}

	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	@JSON(serialize = false)
	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	@JSON(serialize = false)
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@JSON(serialize = false)
	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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
	public ITfQualLessonCourseResultBS getTfQualLessonCourseResultBS() {
		return tfQualLessonCourseResultBS;
	}

	public void setTfQualLessonCourseResultBS(
			ITfQualLessonCourseResultBS tfQualLessonCourseResultBS) {
		this.tfQualLessonCourseResultBS = tfQualLessonCourseResultBS;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public TfQualBaseLesson getTfQualBaseLesson() {
		return tfQualBaseLesson;
	}

	public void setTfQualBaseLesson(TfQualBaseLesson tfQualBaseLesson) {
		this.tfQualBaseLesson = tfQualBaseLesson;
	}

	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@JSON(serialize = false)
	public List<TfQualBaseLesson> getLessonList() {
		return lessonList;
	}

	public void setLessonList(List<TfQualBaseLesson> lessonList) {
		this.lessonList = lessonList;
	}

	@JSON(serialize = false)
	public List<TfQualBaseLessonCourse> getCourseList0() {
		return courseList0;
	}

	public void setCourseList0(List<TfQualBaseLessonCourse> courseList) {
		this.courseList0 = courseList;
	}

	@JSON(serialize = false)
	public List<TfQualBaseLessonCourse> getCourseList1() {
		return courseList1;
	}

	public void setCourseList1(List<TfQualBaseLessonCourse> courseList) {
		this.courseList1 = courseList;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}

	public void setTfQualDeclaraPilotBS(
			ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getCmuser() {
		return cmuser;
	}

	public void setCmuser(String cmuser) {
		this.cmuser = cmuser;
	}
	
	@JSON(serialize = false)
	public List<MyResult> getMyResultList_left() {
		return myResultList_left;
	}

	public void setMyResultList_left(List<MyResult> myResultList_left) {
		this.myResultList_left = myResultList_left;
	}
	
	@JSON(serialize = false)
	public List<MyResult> getMyResultList_right() {
		return myResultList_right;
	}

	public void setMyResultList_right(List<MyResult> myResultList_right) {
		this.myResultList_right = myResultList_right;
	}
	
	@JSON(serialize = false)
	public JCKFormData getForm() {
		return form;
	}

	public void setForm(JCKFormData form) {
		this.form = form;
	}

	@JSON(serialize = false)
	public TfQualInspectionReport getTfQualInspectionReport() {
		return tfQualInspectionReport;
	}

	public void setTfQualInspectionReport(TfQualInspectionReport tfQualInspectionReport) {
		this.tfQualInspectionReport = tfQualInspectionReport;
	}

	@JSON(serialize = false)
	public InspectionFormData getInspectionForm() {
		return inspectionForm;
	}

	public void setInspectionForm(InspectionFormData inspectionForm) {
		this.inspectionForm = inspectionForm;
	}
	@JSON(serialize = false)
	public ITfQualInspectionReportBS getTfQualInspectionReportBS() {
		return tfQualInspectionReportBS;
	}

	public void setTfQualInspectionReportBS(
			ITfQualInspectionReportBS tfQualInspectionReportBS) {
		this.tfQualInspectionReportBS = tfQualInspectionReportBS;
	}

	public String getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
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

	public TfQualBaseSignature getSignature() {
		return signature;
	}
	public void setSignature(TfQualBaseSignature signature) {
		this.signature = signature;
	}

	public String getFmtSigDate() {
		return fmtSigDate;
	}
	public void setFmtSigDate(String fmtSigDate) {
		this.fmtSigDate = fmtSigDate;
	}
	
	@JSON(serialize = false)
	public List<TfQualBaseComment> getCommentTemplates() {
		return commentTemplates;
	}
	public void setCommentTemplates(List<TfQualBaseComment> commentTemplates) {
		this.commentTemplates = commentTemplates;
	}

	@JSON(serialize = false)
	public ITfQuaApplyJbpmBS getTfQuaApplyJbpmBS() {
		return tfQuaApplyJbpmBS;
	}
	public void setTfQuaApplyJbpmBS(ITfQuaApplyJbpmBS tfQuaApplyJbpmBS) {
		this.tfQuaApplyJbpmBS = tfQuaApplyJbpmBS;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	
	@JSON(serialize = false)
	public ByteArrayInputStream getImgStream() {
		return imgStream;
	}

	public void setImgStream(ByteArrayInputStream imgStream) {
		this.imgStream = imgStream;
	}

	@JSON(serialize = false)
	public String getHridShowPhoto() {
		return hridShowPhoto;
	}

	public void setHridShowPhoto(String hridShowPhoto) {
		this.hridShowPhoto = hridShowPhoto;
	}
	
	@JSON(serialize = false)
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@JSON(serialize = false)
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
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
	@JSON(serialize = false)
	public String getSubProcessid() {
		return subProcessid;
	}

	public void setSubProcessid(String subProcessid) {
		this.subProcessid = subProcessid;
	}
	
	@JSON(serialize = false)
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	
	
}
