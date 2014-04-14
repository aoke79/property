package com.sms.training.reincarnation.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述:重型机转入大型机副驾驶MO_UP_CROSS
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_CROSS")
public class HeavyMachine implements java.io.Serializable{
	

	private static final long serialVersionUID = -1076805064145909434L;
	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_CROSSID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id ; //UP_CROSSID 转机型码
	
	@Column(name="DEPTID")
	private String  deptid;    //DEPTID  单位代码	
	
	@Column(name="TYPEID")
	private String   typeid;    //TYPEID 原机型代码
	
	@Column(name="TYPE1ID")
	private String  type1id;  //TYPE1ID  现机型代码
	
	@Column(name="PHASE")
	private String   phase;         //PHASE 阶段
	
	@Column(name="NUM_CROSS")
	private Integer    cross;             //NUM_CROSS 转机型人员数量

	
	@Transient
	private String s2008;
	@Transient
	private String x2008;
	@Transient
	private String s2009;
	@Transient
	private String x2009;
	@Transient
	private String s2010;
	@Transient
	private String x2010;
	
	@Transient
	private String s2011;
	@Transient
	private String x2011;
	@Transient
	private String s2012;
	@Transient
	private String x2012;
	
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

	public String getType1id() {
		return type1id;
	}

	public void setType1id(String type1id) {
		this.type1id = type1id;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Integer getCross() {
		return cross;
	}

	public void setCross(Integer cross) {
		this.cross = cross;
	}

	public String getS2008() {
		return s2008;
	}

	public void setS2008(String s2008) {
		this.s2008 = s2008;
	}

	public String getX2008() {
		return x2008;
	}

	public void setX2008(String x2008) {
		this.x2008 = x2008;
	}

	public String getS2009() {
		return s2009;
	}

	public void setS2009(String s2009) {
		this.s2009 = s2009;
	}

	public String getX2009() {
		return x2009;
	}

	public void setX2009(String x2009) {
		this.x2009 = x2009;
	}

	public String getS2010() {
		return s2010;
	}

	public void setS2010(String s2010) {
		this.s2010 = s2010;
	}

	public String getX2010() {
		return x2010;
	}

	public void setX2010(String x2010) {
		this.x2010 = x2010;
	}

	public String getS2011() {
		return s2011;
	}

	public void setS2011(String s2011) {
		this.s2011 = s2011;
	}

	public String getX2011() {
		return x2011;
	}

	public void setX2011(String x2011) {
		this.x2011 = x2011;
	}

	public String getS2012() {
		return s2012;
	}

	public void setS2012(String s2012) {
		this.s2012 = s2012;
	}

	public String getX2012() {
		return x2012;
	}

	public void setX2012(String x2012) {
		this.x2012 = x2012;
	}
	
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>(); 
	
	@Transient
	private String  deptName; //单位名称
	@Transient
	private String  typeName; //机型
	@Transient
	private String  type1Name; //机型
	public String getType1Name() {
		return type1Name;
	}

	public void setType1Name(String type1Name) {
		this.type1Name = type1Name;
	}

	public List<String[]> getCompanyInfoList() {
		return companyInfoList;
	}

	public void setCompanyInfoList(List<String[]> companyInfoList) {
		this.companyInfoList = companyInfoList;
	}

	public List<String[]> getCompanyInfoListnew() {
		return companyInfoListnew;
	}

	public void setCompanyInfoListnew(List<String[]> companyInfoListnew) {
		this.companyInfoListnew = companyInfoListnew;
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
}
