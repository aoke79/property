package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 *   描述: 副驾驶平衡表
 *   @author LUJIE
 */

@Entity
@Table(name="MO_UP_F")
public class CoPilotBalance implements java.io.Serializable {

	private static final long serialVersionUID = -8923315372088362281L;

	@Id
	@GeneratedValue(generator="uppid-uuid")
	@GenericGenerator(name="uppid-uuid", strategy = "uuid")
	@Column(name = "UP_FID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String uppid; //副驾驶平衡表码
	
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
	
	@Column(name="NUM_NEED_BEG",length=5)
	private Integer numpolitbeg;//期初副驾驶需求
	
	@Column(name="NUM_NEED_END",length=5)
	private Integer numpolitend;//期末副驾驶需求
	
	@Column(name="NUM_POLIT_BEG",length=5)
	private Integer numup;//期初副驾驶人数
	
	@Column(name="NUM_POLIT_END",length=5)
	private Integer numad; //期末副驾驶人数
	
	@Column(name="NUM_UP",length=5)
	private Integer numelsein; //增加升级
	
	@Column(name="NUM_AD",length=5)
	private Integer numcross; //增加改装增加
	
	@Column(name="NUM_ELSE_IN",length=5)
	private Integer numretire; //增加其他进
	
	@Column(name="NUM_CROSS",length=5)
	private Integer numelseout; //减少抽调

	
	@Column(name="NUM_RETIRE",length=5)
	private Integer numnew; //减少退休
	
	
	//-----------------------------------------
	@Column(name="NUM_UP_OUT",length=5)
	private Integer numnewout; //减少升级
	
	@Column(name="NUM_PLANE_AFTER",length=5)
	private Integer numnewafter; //飞机引进数量
	
	@Column(name="NUM_NEED_AFTER",length=5)
	private Integer numnewneed; //副驾驶需求变化
	
	
	@Column(name="NUM_AD_AFTER",length=5)
	private Integer numnewad; //副驾驶净增人数
	
	@Column(name="NUM_ELSE_OUT",length=5)
	private Integer numnewelse; //减少其他出
	
	@Column(name="NUM_NEW",length=5)
	private Integer numnews; //增加新雇员
	
	public Integer getNumnew() {
		return numnew;
	}

	public void setNumnew(Integer numnew) {
		this.numnew = numnew;
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

	public Integer getNumnewout() {
		return numnewout;
	}

	public void setNumnewout(Integer numnewout) {
		this.numnewout = numnewout;
	}

	public Integer getNumnewafter() {
		return numnewafter;
	}

	public void setNumnewafter(Integer numnewafter) {
		this.numnewafter = numnewafter;
	}

	public Integer getNumnewneed() {
		return numnewneed;
	}

	public void setNumnewneed(Integer numnewneed) {
		this.numnewneed = numnewneed;
	}

	public Integer getNumnewad() {
		return numnewad;
	}

	public void setNumnewad(Integer numnewad) {
		this.numnewad = numnewad;
	}

	public Integer getNumnewelse() {
		return numnewelse;
	}

	public void setNumnewelse(Integer numnewelse) {
		this.numnewelse = numnewelse;
	}

	public Integer getNumnews() {
		return numnews;
	}

	public void setNumnews(Integer numnews) {
		this.numnews = numnews;
	}

	
	

}
