package com.sms.training.qualification.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 课次信息    entity. 
 * @author litongmeng
 * 
 */
@Entity
@Table(name = "TF_QUAL_BASE_LESSON")
public class TfQualBaseLesson implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -2249138448188090133L;
	//
	private String lessonId;
	//模板id
	private TfQualBaseTemplate tfQualBaseTemplate;
	//课次号
	private String lessonDesc;
	//课次类型,训练课：t(train)、检查课:c(check)
	private String lessonType;
	//排序,从1开始计数，如1,2,3,4……
	private int lessonOrder;
//	private BigDecimal lessonOrder;
	//人员类型：F:副驾驶，J:机长
	private String lessonTrainType;
	private Set<TfQualBaseLessonCourse> tfQualBaseLessonCourses = new HashSet<TfQualBaseLessonCourse>(0);

	// Constructors

	/** default constructor */
	public TfQualBaseLesson() {
	}

	/** full constructor */
	public TfQualBaseLesson( String lessondesc,TfQualBaseTemplate tfQualBaseTemplate,
			String lessontype, int lessonOrder, Set<TfQualBaseLessonCourse> tfQualBaseLessonCourses) {
		this.tfQualBaseTemplate = tfQualBaseTemplate;
		this.lessonDesc = lessondesc;
		this.lessonType = lessontype;
		this.lessonOrder = lessonOrder;
		this.tfQualBaseLessonCourses = tfQualBaseLessonCourses;
	}

	// Property accessors

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LESSONID", unique = true, nullable = false, length = 36)
	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	@Column(name = "LESSONDESC", length = 15)
	public String getLessonDesc() {
		return lessonDesc;
	}

	public void setLessonDesc(String lessonDesc) {
		this.lessonDesc = lessonDesc;
	}

	@Column(name = "LESSONTYPE", length = 1)
	public String getLessonType() {
		return lessonType;
	}

	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}

	@Column(name = "LESSONORDER", precision = 2, scale = 0)
	public int getLessonOrder() {
		return lessonOrder;
	}

	public void setLessonOrder(int lessonOrder) {
		this.lessonOrder = lessonOrder;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfQualBaseLesson")
	public Set<TfQualBaseLessonCourse> getTfQualBaseLessonCourses() {
		return this.tfQualBaseLessonCourses;
	}

	public void setTfQualBaseLessonCourses(Set<TfQualBaseLessonCourse> tfQualBaseLessonCourses) {
		this.tfQualBaseLessonCourses = tfQualBaseLessonCourses;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID")
	public TfQualBaseTemplate getTfQualBaseTemplate() {
		return tfQualBaseTemplate;
	}

	public void setTfQualBaseTemplate(TfQualBaseTemplate tfQualBaseTemplate) {
		this.tfQualBaseTemplate = tfQualBaseTemplate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(name = "LESSONTRAINTYPE", length = 1)
	public String getLessonTrainType() {
		return lessonTrainType;
	}
	
	public void setLessonTrainType(String lessonTrainType) {
		this.lessonTrainType = lessonTrainType;
	}

}