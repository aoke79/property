package com.sms.training.reincarnation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述：机长平衡 
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_P")
public class CaptainBalance implements java.io.Serializable {

	private static final long serialVersionUID = 6465612291686237423L;
    
	@Id
	@GeneratedValue(generator="uppid-uuid")
	@GenericGenerator(name="uppid-uuid", strategy = "uuid")
	@Column(name = "UP_PID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String uppid; //机长码
	
	@Column(name="DEPTID",length=36)
	private String deptid;//单位代码
	
	@Transient
	private String deptName;
	
	@Transient
	private String typeName;
	
	@Transient
	private String yearName;
	
	@Transient
	private String monthName;
	
	@Transient
	private String degreeName;
	@Transient
	private String qc;
	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public String getQm() {
		return qm;
	}

	public void setQm(String qm) {
		this.qm = qm;
	}

	@Transient
	private String qm;
	
	
	@Column(name="TYPEID",length=36)
	private String typeid; //机型代码
	
	@Column(name="YEAR",length=36)
	private String year;  //年
	
	@Column(name="MONTH",length=36)
	private String month;  //月
	
	@Column(name="DEGREE",length=36)
	private String degree; //技术等级
	
	@Column(name="NUM_PLANE_BEG",length=5)
	private Integer numplane;//期初飞机数量
	
	@Column(name="NUM_PLANE_END",length=5)
	private Integer numneed;//期末飞机数量
	
	@Column(name="NUM_POLIT_BEG",length=5)
	private Integer numpolitbeg;//期初机长人数
	
	@Column(name="NUM_POLIT_END",length=5)
	private Integer numpolitend;//期末机长人数
	
	@Column(name="NUM_PLANE_AD",length=5)
	private Integer numup;//机长调整前飞机引进数量
	
	@Column(name="NUM_POLIT_UP",length=5)
	private Integer numad; //机长调整前新升级
	
	@Column(name="NUM_RETIRE",length=5)
	private Integer numelsein; //机长调整前退休
	
	@Column(name="NUM_POLIT_CHANGE",length=5)
	private Integer numcross; //机长调整大改小
	
	@Column(name="NUM_POLIT_ELSE",length=5)
	private Integer numretire; //机长调整其他
	
	@Column(name="NUM_PLANE_AFTER",length=5)
	private Integer numelseout; //调整后飞机数量
	
	
	
	
	
	//------------
	
	@Column(name="NUM_POLIT_NEED",length=5)
	private Integer numelseoutneed; //期初机长需求
	
	@Column(name="NUM_POLIT_AFTER",length=5)
	private Integer numelseoutaf; //调整后机长人数
	
	@Column(name="NUM_POLIT_CHANGE_BEG",length=5)
	private Integer numelseoutcb; //机长调整前机长需求变化
	
	@Column(name="NUM_POLIT_NEED_AFTER",length=5)
	private Integer numelseoutaft; //调整后机长需求
	

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getUppid() {
		return uppid;
	}

	public void setUppid(String uppid) {
		this.uppid = uppid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Integer getNumelseoutneed() {
		return numelseoutneed;
	}

	public void setNumelseoutneed(Integer numelseoutneed) {
		this.numelseoutneed = numelseoutneed;
	}

	public Integer getNumelseoutaf() {
		return numelseoutaf;
	}

	public void setNumelseoutaf(Integer numelseoutaf) {
		this.numelseoutaf = numelseoutaf;
	}

	public Integer getNumelseoutcb() {
		return numelseoutcb;
	}

	public void setNumelseoutcb(Integer numelseoutcb) {
		this.numelseoutcb = numelseoutcb;
	}

	public Integer getNumelseoutaft() {
		return numelseoutaft;
	}

	public void setNumelseoutaft(Integer numelseoutaft) {
		this.numelseoutaft = numelseoutaft;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getNumplane() {
		return numplane;
	}

	public void setNumplane(Integer numplane) {
		this.numplane = numplane;
	}

	public Integer getNumneed() {
		return numneed;
	}

	public void setNumneed(Integer numneed) {
		this.numneed = numneed;
	}

	public Integer getNumpolitbeg() {
		return numpolitbeg;
	}

	public void setNumpolitbeg(Integer numpolitbeg) {
		this.numpolitbeg = numpolitbeg;
	}

	public Integer getNumpolitend() {
		return numpolitend;
	}

	public void setNumpolitend(Integer numpolitend) {
		this.numpolitend = numpolitend;
	}

	public Integer getNumup() {
		return numup;
	}

	public void setNumup(Integer numup) {
		this.numup = numup;
	}

	public Integer getNumad() {
		return numad;
	}

	public void setNumad(Integer numad) {
		this.numad = numad;
	}

	public Integer getNumelsein() {
		return numelsein;
	}

	public void setNumelsein(Integer numelsein) {
		this.numelsein = numelsein;
	}

	public Integer getNumcross() {
		return numcross;
	}

	public void setNumcross(Integer numcross) {
		this.numcross = numcross;
	}

	public Integer getNumretire() {
		return numretire;
	}

	public void setNumretire(Integer numretire) {
		this.numretire = numretire;
	}

	public Integer getNumelseout() {
		return numelseout;
	}

	public void setNumelseout(Integer numelseout) {
		this.numelseout = numelseout;
	}

	

}
