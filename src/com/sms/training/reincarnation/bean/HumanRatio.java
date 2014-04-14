package com.sms.training.reincarnation.bean;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 人机比MO_UP_RATE
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_RATE")
public class HumanRatio implements java.io.Serializable{

	private static final long serialVersionUID = -6614562539824139097L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_RATEID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;     //  UP_RATEID  人机比表码
	
	@Column(name="DEPTID")
	private String deptid;     //  DEPTID  单位代码
	
	@Column(name="TYPEID")
	private String typeid;     //  TYPEID 机型代码
	
	@Column(name="PHASE")
	private String phase;     //  PHASE 阶段
	
	
	@Column(name="RATE_PLANE")
	private Integer  plane;  // RATE_PLANE  人机比
	
	@Column(name="RATE_POLIT")
	private Integer   polit;//RATE_POLIT
	
	@Column(name="YEAR")
	private String year ;//YEAR


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


	public String getPhase() {
		return phase;
	}


	public void setPhase(String phase) {
		this.phase = phase;
	}


	public Integer getPlane() {
		return plane;
	}


	public void setPlane(Integer plane) {
		this.plane = plane;
	}


	public Integer getPolit() {
		return polit;
	}


	public void setPolit(Integer polit) {
		this.polit = polit;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}
	
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>(); 
	
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

	@Transient
	private String  deptName; //单位名称
	@Transient
	private String  typeName; //机型
}
