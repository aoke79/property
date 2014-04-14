package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述 : 公司内机长调整建议表
 * @author LUJIE
 *
 */
@Entity
@Table(name="MO_UP_CHANGE_IN")
public class CaptainCompany  implements java.io.Serializable{

	private static final long serialVersionUID = 7981567734321983851L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_CHANGE_INID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id; //公司内码
	
	@Column(name="DEPTID",length=36)
	private String deptid; //现单位代码
	
	@Transient
	private String deptNamet; //单位名称
	@Transient
	private String xdeptNamet; //原单位名称
	@Transient
	private String typeNamet; //机型代码
	@Transient
	private String yearNamet;
	@Transient
	private String monthNamet;
	
	public String getDeptNamet() {
		return deptNamet;
	}

	public void setDeptNamet(String deptNamet) {
		this.deptNamet = deptNamet;
	}

	public String getXdeptNamet() {
		return xdeptNamet;
	}

	public void setXdeptNamet(String xdeptNamet) {
		this.xdeptNamet = xdeptNamet;
	}

	public String getTypeNamet() {
		return typeNamet;
	}

	public void setTypeNamet(String typeNamet) {
		this.typeNamet = typeNamet;
	}

	public String getYearNamet() {
		return yearNamet;
	}

	public void setYearNamet(String yearNamet) {
		this.yearNamet = yearNamet;
	}

	public String getMonthNamet() {
		return monthNamet;
	}

	public void setMonthNamet(String monthNamet) {
		this.monthNamet = monthNamet;
	}

	@Column(name="TYPEOUTID",length=36)
	private String typeid; //机型代码
	
	@Column(name="TYPEINID",length=36)
	private String xtypeid; //原单位
	
	@Column(name="YEAR",length=36)
	private String year ; //年
	
	@Column(name="MONTH",length=36)
	private String month;//月
	
	
	@Column(name="NUM_CHANGE",length=5)
	private Integer numchange;//人数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getXtypeid() {
		return xtypeid;
	}

	public void setXtypeid(String xtypeid) {
		this.xtypeid = xtypeid;
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

	public Integer getNumchange() {
		return numchange;
	}

	public void setNumchange(Integer numchange) {
		this.numchange = numchange;
	}
	
	
}
