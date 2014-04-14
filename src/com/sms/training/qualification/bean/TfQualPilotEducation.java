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

import com.sinoframe.bean.CmPeople;

/**
 *  飞行员学习简历 entity. 
 *  @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_EDUCATION")
public class TfQualPilotEducation implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	/**ID*/
	private String educationid;
	/** 飞行员*/
	private CmPeople cmPeople;
	/** 开始年月*/
	private String startdt;
	/** 毕业年月*/
	private String enddt;
	/** 校*/
	private String school;
	/** 院*/
	private String college;
	/** 专业*/
	private String major;
	/** 取得学位*/
	private String degrees;

	// Constructors

	/** default constructor */
	public TfQualPilotEducation() {
	}

	/** full constructor */
	public TfQualPilotEducation(CmPeople cmPeople, String startdt,
			String enddt, String school, String college, String major,
			String degrees) {
		this.cmPeople = cmPeople;
		this.startdt = startdt;
		this.enddt = enddt;
		this.school = school;
		this.college = college;
		this.major = major;
		this.degrees = degrees;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EDUCATIONID", unique = true, nullable = false, length = 36)
	public String getEducationid() {
		return this.educationid;
	}

	public void setEducationid(String educationid) {
		this.educationid = educationid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID")
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	@Column(name = "STARTDT", length = 10)
	public String getStartdt() {
		return this.startdt;
	}

	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	@Column(name = "ENDDT", length = 10)
	public String getEnddt() {
		return this.enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	@Column(name = "SCHOOL", length = 60)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "COLLEGE", length = 60)
	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Column(name = "MAJOR", length = 60)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "DEGREES", length = 30)
	public String getDegrees() {
		return this.degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

}