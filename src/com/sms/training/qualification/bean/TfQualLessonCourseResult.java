package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * 课程成绩 entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="TF_QUAL_LESSON_COURSE_RESULT" )

public class TfQualLessonCourseResult  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 8390530475501160689L;
	//id
	private String resultid;
	//课次评语
     private TfQualLessonComment tfQualLessonComment;
     //课程信息ID
     private String courseid;
     //是否完成(Y/N)
     private String resultsstatus;


    // Constructors

    /** default constructor */
    public TfQualLessonCourseResult() {
    }

    
    /** full constructor */
    public TfQualLessonCourseResult(TfQualLessonComment tfQualLessonComment, String courseid, String resultsstatus) {
        this.tfQualLessonComment = tfQualLessonComment;
        this.courseid = courseid;
        this.resultsstatus = resultsstatus;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="RESULTID", unique=true, nullable=false, length=36)

    public String getResultid() {
        return this.resultid;
    }
    
    public void setResultid(String resultid) {
        this.resultid = resultid;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="LESSONCOMMENTID")

    public TfQualLessonComment getTfQualLessonComment() {
        return this.tfQualLessonComment;
    }
    
    public void setTfQualLessonComment(TfQualLessonComment tfQualLessonComment) {
        this.tfQualLessonComment = tfQualLessonComment;
    }
    
    @Column(name="COURSEID", length=36)

    public String getCourseid() {
        return this.courseid;
    }
    
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    
    @Column(name="RESULTSSTATUS", length=1)

    public String getResultsstatus() {
        return this.resultsstatus;
    }
    
    public void setResultsstatus(String resultsstatus) {
        this.resultsstatus = resultsstatus;
    }
   








}