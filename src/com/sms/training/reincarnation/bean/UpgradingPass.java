package com.sms.training.reincarnation.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * 各等级副驾驶升级通过率和飞行时间增加量MO_UP_PASS
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_PASS")
public class UpgradingPass implements java.io.Serializable{

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_PASSID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;   // 各等级副驾驶升级通过率和飞行时间增加量码 UP_PASSID
	
	@Column(name="DEPTID")
	private String  deptid;     // DEPTID 单位代码
	
	@Transient
	private String deptName;
	@Transient
	private String degreeName;
	
	
	
	public String getDegreeName() {
		return degreeName;
	}


	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name="TYPEID")
	private String  typeid;     //TYPEID  机型代码
	
	@Transient
	private String typeName;
	
	
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name="DEGREE")
	private String    degree;             //DEGREE  技术等级
	
	@Column(name="RATE_PASS")
	private Integer   pass;        // RATE_PASS 通过率
	
	@Column(name="NUM_AD")
	private Integer ad;   // NUM_AD 飞行时间增加量


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


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public Integer getPass() {
		return pass;
	}


	public void setPass(Integer pass) {
		this.pass = pass;
	}


	public Integer getAd() {
		return ad;
	}


	public void setAd(Integer ad) {
		this.ad = ad;
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
}
