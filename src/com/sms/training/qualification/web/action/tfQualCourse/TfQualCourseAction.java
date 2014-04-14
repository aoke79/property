package com.sms.training.qualification.web.action.tfQualCourse;

import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseLesson;
import com.sms.training.qualification.bean.TfQualBaseLessonCourse;
import com.sms.training.qualification.bean.TfQualBaseTemplate;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQualBaseLessonBS;
import com.sms.training.qualification.business.ITfQualBaseLessonCourseBS;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/sms/training/qualification/tfQualBaseLesson/tfQualBaseLessonList.jsp"),
		@Result(name = "courses", location = "/sms/training/qualification/tfQualBaseLesson/tfQualBaseLessonCourseList.jsp"),
		@Result(name = "courseEdit", location = "/sms/training/qualification/tfQualBaseLesson/tfQualBaseLessonCourseEdit.jsp"),
		@Result(name = "lessonEdit", location = "/sms/training/qualification/tfQualBaseLesson/tfQualBaseLessonEdit.jsp"),
		@Result(name = "addCourse", location = "/sms/training/qualification/tfQualBaseLesson/tfQualBaseLessonCourseAdd.jsp"),
		@Result(name = "addLesson", location = "/sms/training/qualification/tfQualBaseLesson/tfQualBaseLessonAdd.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })

public class TfQualCourseAction extends BaseAction {
	private static String moduleName = "tfQualCourseAction";

	private static final long serialVersionUID = 1L;
	// 课次信息
	private TfQualBaseLesson tfQualBaseLesson;
	// 课程信息
	private TfQualBaseLessonCourse tfQualBaseLessonCourse;
	// 课次信息列表
	private List<TfQualBaseLesson> tfQualBaseLessonList;
	// 课程信息列表
	private List<TfQualBaseLessonCourse> tfQualBaseLessonCourseList;
	// 消息实体
	private Message message;
	// 多个id，用于批量删除
	private String ids = "";
	//资质类型
	private TfQualBaseType tfQualBaseType;
	// 检索传入后台的数据
	private HashMap<String, String> query = new HashMap<String, String>();
	// 用于存储查询条件的字符串形式
	private String strQuery;
	private ITfQualBaseLessonBS tfQualBaseLessonBS;
	private ITfQualBaseLessonCourseBS tfQualBaseLessonCourseBS;
	//模板bean
	private TfQualBaseTemplate tfQualBaseTemplate;

	/**
	 * constructor
	 */
	public TfQualCourseAction() {
	}

	/**
	 *  跳转到课次增加页面
	 * @return
	 */
	public String toLessonAddPage() {
		try {
//			tfQualBaseType=tfQualBaseLessonBS.findById(TfQualBaseType.class,tfQualBaseType.getTypeid());
			tfQualBaseTemplate = this.tfQualBaseLessonBS.findById(TfQualBaseTemplate.class, tfQualBaseTemplate.getId());
			
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "addLesson";
	}
	
	/**
	 * 添加课次(保存)
	 * @return
	 */
	public String addLesson() {
		try {
			tfQualBaseLessonBS.save(tfQualBaseLesson);
			this.message = this.getSuccessMessage(getText("addSuccess"),"tfQualCourse", "closeCurrent","");
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("addFail")));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 * 修改课次
	 * @return
	 */
	public String updateLesson() {
		try {
			tfQualBaseLessonBS.update(tfQualBaseLesson,tfQualBaseLesson.getLessonId());
			this.message =this.getSuccessMessage(getText("updateSuccess"),"tfQualCourse", "closeCurrent","");
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("updateFail")));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 * 跳转到课程增加页面
	 * @return
	 */
	public String toCourseAddPage() {
		try {
			tfQualBaseLesson = tfQualBaseLessonBS.findById(TfQualBaseLesson.class,tfQualBaseLesson.getLessonId());
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "addCourse";
	}

	/**
	 * 添加课程(保存)
	 * @return
	 */
	public String addCourse() {
		try {
			tfQualBaseLesson = tfQualBaseLessonBS.findById(TfQualBaseLesson.class, tfQualBaseLesson.getLessonId());
			tfQualBaseLessonCourseBS.save(tfQualBaseLessonCourse);
			this.message =this.getSuccessMessage(getText("addSuccess"),"courseInfo", "closeCurrent","");
		} catch (Exception e) {
			tfQualBaseLessonCourseBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("addFail")));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 * 修改课程
	 * @return
	 */
	public String updateCourse() {
		try {
			tfQualBaseLesson = tfQualBaseLessonBS.findById(TfQualBaseLesson.class, tfQualBaseLessonCourse.getTfQualBaseLesson().getLessonId());
			tfQualBaseLessonCourseBS.update(tfQualBaseLessonCourse,tfQualBaseLessonCourse.getCourseId());
			this.message =this.getSuccessMessage(getText("updateSuccess"),"courseInfo", "closeCurrent","");
		} catch (Exception e) {
			tfQualBaseLessonCourseBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.setMessage(this.getFailMessage(getText("updateFail")));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 *  跳转到课次修改页面
	 * @return
	 */
	public String toLessonEditPage() {
		try {
			this.tfQualBaseLesson = tfQualBaseLessonBS.findById(TfQualBaseLesson.class, tfQualBaseLesson.getLessonId());
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "lessonEdit";
	}

	/**
	 * 跳转到课程修改页面
	 * @return
	 */
	public String toCourseEditPage() {
		try {
			tfQualBaseLesson = tfQualBaseLessonBS.findById(TfQualBaseLesson.class,tfQualBaseLesson.getLessonId());
			tfQualBaseLessonCourse = tfQualBaseLessonCourseBS.findById(TfQualBaseLessonCourse.class,tfQualBaseLessonCourse.getCourseId());
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "courseEdit";
	}

	/**
	 * 课次的 批量删除
	 * @return
	 */
	public String multipleDeleteLesson() {
		try {
			if( !"".equals(ids)){
				String[] idArr = ids.split(",");
				boolean courseFlag = false;
				TfQualBaseLesson tfQualBaseLesson = null; 
				for (String id : idArr) {
					tfQualBaseLesson= this.tfQualBaseLessonBS.findById(TfQualBaseLesson.class, id);
					if(tfQualBaseLesson != null && tfQualBaseLesson.getTfQualBaseLessonCourses().size()>0 ){
						courseFlag = true;
					}else{
						tfQualBaseLessonBS.deleteByIds(TfQualBaseLesson.class, id);
					}
				}
				this.message = this.getSuccessMessage("批量删除成功！", "tfQualCourse",
						"forward", "tf-qual-course/tf-qual-course!toGetTfQualBaseLessonList.do?tfQualBaseTemplate.id="+tfQualBaseTemplate.getId());
				if(courseFlag){
					if(idArr.length !=1){
						this.message.setStatusCode("300");
						this.message.setMessageInfo("部分课次下有课程,请先删除其下课程");
					}else{
						this.message.setStatusCode("300");
						this.message.setMessageInfo("该课次下有课程,请先删除其下课程");
					}
				}
			}
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 *  课程的 批量删除
	 * @return
	 */
	public String multipleDeleteCourse() {
		try {
			boolean cannotDelete = false;
			if(!"".equals(ids)){
				String[] idArr = ids.split(",");
				for(String id : idArr){
					if(tfQualBaseLessonCourseBS.hasBound(id)){
						cannotDelete = true;
					}else{
						tfQualBaseLessonCourseBS.deleteById(TfQualBaseLessonCourse.class, id);
					}
				}
				this.message = this.getSuccessMessage("批量删除成功！", "tfQualCourse","forward",
						"tf-qual-course/tf-qual-course!listCourses.do?tfQualBaseLesson.lessonId="
								+ tfQualBaseLesson.getLessonId().trim());
				if(cannotDelete){
					if(idArr.length !=1){
						this.message.setStatusCode("300");
						this.message.setMessageInfo("部分科目已被应用，无法执行删除操作");
					}else{
						this.message = this.getFailMessage("该科目已被应用，无法执行删除操作");
					}
				}
			}
		} catch (Exception e) {
			tfQualBaseLessonCourseBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	/**
	 * 显示课次列表 （或根据条件查询）
	 * @return  
	 */
//	public String gettfQualBaseLessonList() {
//		try {
//			if (strQuery != null && !"".equals(strQuery)) {
//				query = Util.toMap(strQuery);
//			}
//			//获取资质类型
//			if(tfQualBaseType!=null){
//				tfQualBaseType=tfQualBaseLessonBS.findById(TfQualBaseType.class, tfQualBaseType.getTypeid());
//			}
//			//添加查询条件
//			if(tfQualBaseType!=null){
//				query.put("like_tfQualBaseType.typeid", tfQualBaseType.getTypeid());
//			}
//			this.getSysPageInfo().setMaxCount(tfQualBaseLessonBS.lessonListCount(Util.decodeQuery(query)));
//			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
//			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
//				sysOrderByInfo.setOrderColumn("lessonOrder");
//			}
//			tfQualBaseLessonList = tfQualBaseLessonBS.searchLessonList(this.getSysPageInfo(), sysOrderByInfo,Util.decodeQuery(query));
//		} catch (Exception e) {
//			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//			e.printStackTrace();
//		}
//		return "list";
//	}
	
	/**
	 * 获取课次列表   
	 */
	public String toGetTfQualBaseLessonList(){
		try {
			String countHql = null;
			if (strQuery != null && !"".equals(strQuery)) {
				query = Util.toMap(strQuery);
			}
			// 查询列表中的条数信息
			if(tfQualBaseTemplate != null ){
				countHql = "select count(*) from TfQualBaseLesson t where t.tfQualBaseTemplate.id = '" + tfQualBaseTemplate.getId()+"'" ;
			}
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(this.tfQualBaseLessonBS.getCountByHQL(countHql, Util.decodeQuery(query)));
			
			// 列表
			StringBuffer buffer = new StringBuffer();
			if(tfQualBaseTemplate!=null){
				buffer.append("from TfQualBaseLesson t where t.tfQualBaseTemplate.id = '" + tfQualBaseTemplate.getId() +"'" );
			}
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderAsc("asc");
				sysOrderByInfo.setOrderColumn("lessonOrder");
			}
			tfQualBaseLessonList = this.tfQualBaseLessonBS.findPageByQuery(this.getSysPageInfo(), buffer.toString(), Util.decodeQuery(query), sysOrderByInfo);
		} catch (Exception e) {
			tfQualBaseLessonBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * 显示课程列表 （或根据条件查询）
	 * @return
	 */
	public String listCourses() {
		try {
			String lessonId=tfQualBaseLesson.getLessonId();
			if(lessonId!=null && !lessonId.equals("")){
				tfQualBaseLesson = tfQualBaseLessonBS.findById(TfQualBaseLesson.class,lessonId);
				query.put("eq_tfQualBaseLesson.lessonId", lessonId);
			}
			
			if (strQuery != null && !"".equals(strQuery)) {
				query = Util.toMap(strQuery);
			}
			this.getSysPageInfo().setMaxCount(tfQualBaseLessonCourseBS.CourseListCount(Util.decodeQuery(query)));
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderColumn("courseCode");
			}
			tfQualBaseLessonCourseList = tfQualBaseLessonCourseBS.searchCourseList(this.getSysPageInfo(), sysOrderByInfo, Util.decodeQuery(query));
		} catch (Exception e) {
			tfQualBaseLessonCourseBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "courses";
	}

//	======================================set/get方法=======================================
	
	@JSON(serialize = false)
	public List<TfQualBaseLesson> getTfQualBaseLessonList() {
		return tfQualBaseLessonList;
	}

	public void setTfQualBaseLessonList(
			List<TfQualBaseLesson> tfQualBaseLessonList) {
		this.tfQualBaseLessonList = tfQualBaseLessonList;
	}

	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	@JSON(serialize = false)
	public ITfQualBaseLessonBS getTfQualBaseLessonBS() {
		return tfQualBaseLessonBS;
	}

	public void setTfQualBaseLessonBS(ITfQualBaseLessonBS tfQualBaseLessonBS) {
		this.tfQualBaseLessonBS = tfQualBaseLessonBS;
	}

	@JSON(serialize = false)
	public ITfQualBaseLessonCourseBS getTfQualBaseLessonCourseBS() {
		return tfQualBaseLessonCourseBS;
	}

	public void setTfQualBaseLessonCourseBS(
			ITfQualBaseLessonCourseBS tfQualBaseLessonCourseBS) {
		this.tfQualBaseLessonCourseBS = tfQualBaseLessonCourseBS;
	}

	@JSON(serialize = false)
	public TfQualBaseLesson getTfQualBaseLesson() {
		return tfQualBaseLesson;
	}

	public void setTfQualBaseLesson(TfQualBaseLesson tfQualBaseLesson) {
		this.tfQualBaseLesson = tfQualBaseLesson;
	}

	@JSON(serialize = false)
	public TfQualBaseLessonCourse getTfQualBaseLessonCourse() {
		return tfQualBaseLessonCourse;
	}

	public void setTfQualBaseLessonCourse(
			TfQualBaseLessonCourse tfQualBaseLessonCourse) {
		this.tfQualBaseLessonCourse = tfQualBaseLessonCourse;
	}

	@JSON(serialize = false)
	public List<TfQualBaseLessonCourse> getTfQualBaseLessonCourseList() {
		return tfQualBaseLessonCourseList;
	}

	public void setTfQualBaseLessonCourseList(
			List<TfQualBaseLessonCourse> tfQualBaseLessonCourseList) {
		this.tfQualBaseLessonCourseList = tfQualBaseLessonCourseList;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@JSON(serialize = false)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@JSON(serialize = false)
	public void setTfQualBaseTemplate(TfQualBaseTemplate tfQualBaseTemplate) {
		this.tfQualBaseTemplate = tfQualBaseTemplate;
	}

	public TfQualBaseTemplate getTfQualBaseTemplate() {
		return tfQualBaseTemplate;
	}

}
