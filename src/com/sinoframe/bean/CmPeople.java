package com.sinoframe.bean;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

//import com.sms.training.bywork.bean.TcByworkClasspersonGrade;
//import com.sms.training.bywork.bean.TcByworkLicense;
//import com.sms.training.bywork.bean.TcByworkPlanSelected;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;

/**
 * CmPeople
 * @描述  表 "CM_PEOPLE" 的实体类
 * @作者 胡星
 * @版本 1.0
 */
@Entity
@Table(name = "CM_PEOPLE")
public class CmPeople implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3734550135384545166L;
	/** 人员ID */
	private String id;
	/** 机构 */
	private SysOrganization sysOrganization;
	/** 十位编码 */
	private String hrid;
	/**  */
	private String peopleRange;
	/** 姓名 */
	private String name;
	/** 姓名拼写 */
	private String nameSpell;
	/** 性别 */
	private String sex;
	/** 出生日期 */
	private Date birthday;
	/** 国籍 */
	private String nation;
	/** 身份证号 */
	private String idcard;
	/**  */
	private Date workDate;
	/**  */
	private Date systemWorkdate;
	/**  */
	private Date joinCadate;
	/**  */
	private String nationality;
	/**  */
	private String firstName;
	/**  */
	private String midName;
	/**  */
	private String lastName;
	/**  */
	private String professional;
	/**  */
	private String area;
	/**  */
	private String sort;
	/**  */
	private String status;
	/**  */
	private String isStudent;
	/**  */
	private String stusort;
	/**  */
	private String stuyear;
	/**  */
	private String stustate;
	/**  */
	private String stupcstate;
	/**  */
	private String stucolid;
	/**  */
//	private Set<TcByworkClasspersonGrade> tcByworkClasspersonGrades = new HashSet<TcByworkClasspersonGrade>(
//			0);
//	/**  */
//	private Set<TcByworkPlanSelected> tcByorkPlanSelecteds = new HashSet<TcByworkPlanSelected>(
//			0);
//	/**  */
//	private Set<TcByworkLicense> tcByworkLicenses = new HashSet<TcByworkLicense>(0);
	/**  */
//	private Set<EnExamQualification> enExamQualifications = new HashSet<EnExamQualification>(
//			0);
	
	private Set<TfQualBasePilotInfo> tfQualBasePilotInfos=new HashSet<TfQualBasePilotInfo>(0);
	// Constructors


	/** default constructor */
	public CmPeople() {
	}

	/** minimal constructor */
	public CmPeople(String id) {
		this.id = id;
	}

	/** full constructor */
	public CmPeople(String id, SysOrganization sysOrganization, String hrid,
			String peopleRange, String name, String nameSpell, String sex,
			Date birthday, String nation, String idcard, Date workDate,
			Date systemWorkdate, Date joinCadate, String nationality,
			String firstName, String midName, String lastName,
			String professional, String area, String sort, String status,
			String isStudent, String stusort, String stuyear, String stustate,
			String stupcstate, String stucolid) {
		this.id = id;
		this.sysOrganization = sysOrganization;
		this.hrid = hrid;
		this.peopleRange = peopleRange;
		this.name = name;
		this.nameSpell = nameSpell;
		this.sex = sex;
		this.birthday = birthday;
		this.nation = nation;
		this.idcard = idcard;
		this.workDate = workDate;
		this.systemWorkdate = systemWorkdate;
		this.joinCadate = joinCadate;
		this.nationality = nationality;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.professional = professional;
		this.area = area;
		this.sort = sort;
		this.status = status;
		this.isStudent = isStudent;
		this.stusort = stusort;
		this.stuyear = stuyear;
		this.stustate = stustate;
		this.stupcstate = stupcstate;
		this.stucolid = stucolid;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID", unique = false, nullable = true, insertable = true, updatable = true)
	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmPeople")
//	public Set<TcByworkClasspersonGrade> getTcByworkClasspersonGrades() {
//		return this.tcByworkClasspersonGrades;
//	}
//
//	public void setTcByworkClasspersonGrades(
//			Set<TcByworkClasspersonGrade> tcByworkClasspersonGrades) {
//		this.tcByworkClasspersonGrades = tcByworkClasspersonGrades;
//	}
//	
//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmPeople")
//	public Set<TcByworkPlanSelected> getTcByorkPlanSelecteds() {
//		return tcByorkPlanSelecteds;
//	}
//
//	public void setTcByorkPlanSelecteds(
//			Set<TcByworkPlanSelected> tcByorkPlanSelecteds) {
//		this.tcByorkPlanSelecteds = tcByorkPlanSelecteds;
//	}
//	
//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmPeople")
//	public Set<TcByworkLicense> getTcByworkLicenses() {
//		return tcByworkLicenses;
//	}
//
//	public void setTcByworkLicenses(Set<TcByworkLicense> tcByworkLicenses) {
//		this.tcByworkLicenses = tcByworkLicenses;
//	}

	@Column(name = "HRID", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getHrid() {
		return this.hrid;
	}

	public void setHrid(String hrid) {
		this.hrid = hrid;
	}

	@Column(name = "PEOPLE_RANGE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getPeopleRange() {
		return this.peopleRange;
	}

	public void setPeopleRange(String peopleRange) {
		this.peopleRange = peopleRange;
	}

	@Column(name = "NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 66)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_SPELL", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getNameSpell() {
		return this.nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	@Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "NATION", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "IDCARD", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WORK_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SYSTEM_WORKDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getSystemWorkdate() {
		return this.systemWorkdate;
	}

	public void setSystemWorkdate(Date systemWorkdate) {
		this.systemWorkdate = systemWorkdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JOIN_CADATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getJoinCadate() {
		return this.joinCadate;
	}

	public void setJoinCadate(Date joinCadate) {
		this.joinCadate = joinCadate;
	}

	@Column(name = "NATIONALITY", unique = false, nullable = true, insertable = true, updatable = true, length = 66)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "FIRST_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 66)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MID_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 66)
	public String getMidName() {
		return this.midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	@Column(name = "LAST_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 66)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "PROFESSIONAL", unique = false, nullable = true, insertable = true, updatable = true, length = 66)
	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	@Column(name = "AREA", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "SORT", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IS_STUDENT", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getIsStudent() {
		return this.isStudent;
	}

	public void setIsStudent(String isStudent) {
		this.isStudent = isStudent;
	}

	@Column(name = "STUSORT", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStusort() {
		return this.stusort;
	}

	public void setStusort(String stusort) {
		this.stusort = stusort;
	}

	@Column(name = "STUYEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public String getStuyear() {
		return this.stuyear;
	}

	public void setStuyear(String stuyear) {
		this.stuyear = stuyear;
	}

	@Column(name = "STUSTATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStustate() {
		return this.stustate;
	}

	public void setStustate(String stustate) {
		this.stustate = stustate;
	}

	@Column(name = "STUPCSTATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStupcstate() {
		return this.stupcstate;
	}

	public void setStupcstate(String stupcstate) {
		this.stupcstate = stupcstate;
	}

	@Column(name = "STUCOLID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getStucolid() {
		return this.stucolid;
	}

	public void setStucolid(String stucolid) {
		this.stucolid = stucolid;
	}
//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmPeople")
//	public Set<EnExamQualification> getEnExamQualifications() {
//		return this.enExamQualifications;
//	}
//
//	public void setEnExamQualifications(
//			Set<EnExamQualification> enExamQualifications) {
//		this.enExamQualifications = enExamQualifications;
//	}
	
	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmPeople")
	public Set<TfQualBasePilotInfo> getTfQualBasePilotInfos() {
		return tfQualBasePilotInfos;
	}

	public void setTfQualBasePilotInfos(
			Set<TfQualBasePilotInfo> tfQualBasePilotInfos) {
		this.tfQualBasePilotInfos = tfQualBasePilotInfos;
	}
}