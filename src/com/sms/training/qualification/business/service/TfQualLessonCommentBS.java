package com.sms.training.qualification.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.MyResult;
import com.sms.training.qualification.bean.TfQualBaseLessonCourse;
import com.sms.training.qualification.bean.TfQualLessonCourseResult;
import com.sms.training.qualification.bean.TfQualLessonComment;
import com.sms.training.qualification.business.ITfQualLessonCommentBS;
import com.sms.training.qualification.dao.ITfQualBaseLessonCourseDao;

@Service("tfQualLessonCommentBS")
public class TfQualLessonCommentBS extends BaseBS implements
		ITfQualLessonCommentBS {
	ITfQualBaseLessonCourseDao tfQualBaseLessonCourseDao;

	public ITfQualBaseLessonCourseDao getTfQualBaseLessonCourseDao() {
		return tfQualBaseLessonCourseDao;
	}

	@Resource
	public void setTfQualBaseLessonCourseDao(
			ITfQualBaseLessonCourseDao tfQualBaseLessonCourseDao) {
		this.tfQualBaseLessonCourseDao = tfQualBaseLessonCourseDao;
	}

	/**
	 * 根据根据 申报人员明细id 和 课次id 查找到对应的课次评语
	 * 
	 * @param detailsid
	 *            申报人员明细id
	 * @param lessonId
	 *            课次id
	 * @return 课次评语
	 */
	@Override
	public TfQualLessonComment findComment(String detailsid, String lessonId) {
		String hql = "from TfQualLessonComment c where c.detailsid='"
				+ detailsid + "' and lessonid='" + lessonId + "'";
		List<TfQualLessonComment> list = findPageByQuery(hql);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据 课次id 查找到对应的 课程成绩(MyResult)list
	 * @param commentId 课次评语id
	 * @param lessonId 课次id
	 * @see MyResult
	 */
	@Override
	public List<MyResult> findMyResultList(String lessonId ,String commentId) {
		List<MyResult> results = new ArrayList<MyResult>();

	/*	String sql = "select cs.courseid,cs.coursecode,cs.subject,r.resultsstatus " +
				" from ( select * from tf_qual_base_lesson_course c " +
				" where c.lessonid='"+lessonId+"') cs " +
				" left outer join tf_qual_lesson_course_result r  " +
				" on cs.courseid=r.courseid " +
				" where r.lessoncommentid='"+commentId +
				"' order by cs.coursecode"; */
		
		String sql="select cs.courseid, cs.coursecode, cs.subject, rst.results " +
				  " from (select * from tf_qual_base_lesson_course c " +
				       " where c.lessonid = '"+lessonId+"') cs " +
			      " left outer join (select r.resultsstatus results, r.courseid courseid " +
				                   "  from tf_qual_lesson_course_result r " +
				                   " where r.lessoncommentid = '"+ commentId +"') rst "+
				  " on cs.courseid = rst.courseid "+
				  " order by cs.coursecode";
		List<Object[]> list = this.tfQualBaseLessonCourseDao.findBySQL(sql);
		TfQualBaseLessonCourse course=null;
		for (Object[] o : list) {
			course=new TfQualBaseLessonCourse(o[0].toString(),
							o[1]==null?0:((BigDecimal)o[1]).intValue(), 
							o[2]==null?"":o[2].toString());
			results.add(new MyResult(course , o[3]==null?"N":o[3].toString()));
		}
		return results;
	}

	/**
	 * 根据课次评语查找到对应的 课程成绩list
	 * 
	 * @param comment
	 *            课次评语
	 */
	@Override
	public List<TfQualLessonCourseResult> findResultList(String commentId) {
		String hql = "from TfQualLessonCourseResult r where r.tfQualLessonComment.lessoncommentid='"
				+ commentId + "'";
		return this.findPageByQuery(hql);
	}

	@Override
	public boolean checkAllCommentIfSave(String detailsid, String isT,String typeId) {
			StringBuffer hql=new StringBuffer("select count(l.lessonId) from TfQualBaseType t, TfQualBaseLesson l ,TfQualBaseTemplate p where t.tfQualBaseTemplate.id = p.id and p.id = l.tfQualBaseTemplate.id ");
			if(typeId !=null && !"".equals(typeId)){
				hql.append(" and t.typeid ='"+typeId+"' ");
			}
			if(isT !=null && !"".equals(isT)){
				hql.append(" and l.lessonType ='"+isT.toLowerCase()+"' ");
			}
			//获取当前登陆者在当前资质类型下，对应的课程数量
			int lessonListLengh = this.getCountByHql(hql.toString());
			
			StringBuffer buff = new StringBuffer("select count(c.lessoncommentid) from TfQualLessonComment c ,TfQualBaseLesson l where c.lessonid = l.lessonId ");
			if(detailsid !=null && !"".equals(detailsid)){
				buff.append(" and c.detailsid= '"+detailsid+"'");
			}
			if(isT !=null && !"".equals(isT)){
				buff.append(" and l.lessonType ='"+isT.toLowerCase()+"' ");
			}
			//获取 当前飞行员 课程的评语数量
			int lessonCommentlistLengh = this.getCountByHql(buff.toString());
			return lessonListLengh == lessonCommentlistLengh;
	}

}
