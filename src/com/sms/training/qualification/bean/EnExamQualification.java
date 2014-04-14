package com.sms.training.qualification.bean;
// default package




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sinoframe.bean.CmPeople;

/**
 * EnExamQualification entity.
 * 考生资质表
 * @author 牛静伟
 */
@Entity
@Table(name = "TF_EN_EXAM_QUALIFICATION")
public class EnExamQualification implements java.io.Serializable,Cloneable {

	// Fields
	//考生ID
	private String examineeId;
	//最后考试时间
	private Date lastDate;
	//获取资质时间
	private Date qualificationDate;
	//英语资质	
	private Integer qualification;
	//考试类别
	private String examSort;
	//偏离
	private Integer diverge;
	//考试次数
	private Integer currentResitTotal;
	//飞行员
	private CmPeople cmPeople;
	//报考是否通过标记
	private String resitsign;
	// Constructors
	//存储一个临时的变量，用于判断当前资质到期还有一个月、两个月或者三个月，在显示时给颜色标注
	private String colorFlag;
	//存储一个临时的变量。英语培训或者英语考试颜色标注颜色,exam代表考试,train代表培训
	private String englishFlag;
	//存储一个临时的变量。考试报名时资质到期提醒列表中的人员如果培训天数大于40天，给一个背影颜色
	private boolean trainOver;
	private String allowModify;
	/** default constructor */
	public EnExamQualification() {
	}
	/** minimal constructor */
	public EnExamQualification(String examineeId) {
		this.examineeId = examineeId;
	}

	/** full constructor */
	public EnExamQualification(String examineeId, Date lastDate,
			Date qualificationDate, Integer qualification, String examSort,
			Integer diverge, Integer currentResitTotal,CmPeople cmPeople) {
		this.examineeId = examineeId;
		this.lastDate = lastDate;
		this.qualificationDate = qualificationDate;
		this.qualification = qualification;
		this.examSort = examSort;
		this.diverge = diverge;
		this.currentResitTotal = currentResitTotal;
		this.cmPeople=cmPeople;
	}

	// Property accessors
	@Id 
	@Column(name = "EXAMINEE_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getExamineeId() {
		return this.examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "QUALIFICATION_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getQualificationDate() {
		return this.qualificationDate;
	}

	public void setQualificationDate(Date qualificationDate) {
		this.qualificationDate = qualificationDate;
	}

	@Column(name = "QUALIFICATION", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Integer getQualification() {
		return this.qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

	@Column(name = "EXAM_SORT", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getExamSort() {
		return this.examSort;
	}

	public void setExamSort(String examSort) {
		this.examSort = examSort;
	}

	@Column(name = "DIVERGE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Integer getDiverge() {
		return this.diverge;
	}

	public void setDiverge(Integer diverge) {
		this.diverge = diverge;
	}

	@Column(name = "CURRENT_RESIT_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Integer getCurrentResitTotal() {
		return this.currentResitTotal;
	}

	public void setCurrentResitTotal(Integer currentResitTotal) {
		this.currentResitTotal = currentResitTotal;
	}
//	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
//	@JoinColumn(name = "EXAMINEE_ID", unique = false, nullable = true, insertable = false, updatable = false)
//	public EnUser getEnUser() {
//		return this.enUser;
//	}
//
//	public void setEnUser(EnUser enUser) {
//		this.enUser = enUser;
//	}
	
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "EXAMINEE_ID", unique = true, nullable = false, insertable = false, updatable = false)
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return lastDate+"  "+qualificationDate+"  "+qualification+"  "+examSort+"  "+diverge+"  "+currentResitTotal;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Column(name = "RESITSIGN", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getResitsign() {
		return this.resitsign;
	}

	
	
	
	public void setResitsign(String resitsign) {
		this.resitsign = resitsign;
	}
	
	@Transient
	public String getColorFlag() {
		return colorFlag;
	}
	public void setColorFlag(String colorFlag) {
		this.colorFlag = colorFlag;
	}
	
	@Transient
	public String getEnglishFlag() {
		return englishFlag;
	}
	public void setEnglishFlag(String englishFlag) {
		this.englishFlag = englishFlag;
	}
	@Transient
	public boolean isTrainOver() {
		return trainOver;
	}
	public void setTrainOver(boolean trainOver) {
		this.trainOver = trainOver;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((examineeId == null) ? 0 : examineeId.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		final EnExamQualification other = (EnExamQualification) obj;
//		if (examineeId == null) {
//			if (other.examineeId != null)
//				return false;
//		} else if (!examineeId.equals(other.examineeId))
//			return false;
//		return true;
//	}
	@Transient
	public String getAllowModify() {
		return allowModify;
	}
	public void setAllowModify(String allowModify) {
		this.allowModify = allowModify;
	}
	
	
	
	
	
	
}