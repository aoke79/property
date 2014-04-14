package com.sms.training.qualification.business.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualLessonComment;
import com.sms.training.qualification.bean.TfQualLessonCourseResult;
import com.sms.training.qualification.business.ITfQualLessonCourseResultBS;

@Service("tfQualLessonCourseResultBS")
public class TfQualLessonCourseResultBS extends BaseBS implements
		ITfQualLessonCourseResultBS {

	/**
	 * 保存或更新课程成绩
	 * 
	 * @param idsAndStatus
	 *            map类型     key:信息id ,value:对应状态
	 * @param lessoncommentid
	 *            课次评语id
	 */
	public void save(Map<String, String> idsAndStatus,
			TfQualLessonComment comment) {
		TfQualLessonCourseResult result = null;
		if (idsAndStatus == null || idsAndStatus.size() == 0) {
			return;
		}
		List<TfQualLessonCourseResult> results = this
				.findPageByQuery("from TfQualLessonCourseResult r where r.tfQualLessonComment.lessoncommentid='"
						+ comment.getLessoncommentid().trim() + "'");
		for (TfQualLessonCourseResult r : results) {
			this.delete(r);
		}
		// String hql =
		// "delete TfQualLessonCourseResult r where r.tfQualLessonComment.lessoncommentid='"
		// + comment.getLessoncommentid().trim() + "'";
		// this.tfQualBaseLessonCourseDao.executeUpdate(hql);
		for (Entry<String, String> en : idsAndStatus.entrySet()) {
			result = new TfQualLessonCourseResult();
			result.setCourseid(en.getKey());
			result.setResultsstatus(en.getValue());
			result.setTfQualLessonComment(comment);
			this.save(result);
		}
	}

}
