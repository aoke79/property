package com.sms.training.reincarnation.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 描述：机队实力表(人员)
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_CONDITION_POLIT")
public class FleetInfo implements java.io.Serializable{

	private static final long serialVersionUID = -517414480974358759L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_CONDITION_POLIT_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;
	
	@Column(name="DEPTID")
	private String deptid;   //DEPTID 单位代码

	@Column(name="TYPEID")
	private String  typeid;         //TYPEID 机型代码
	
	@Column(name="DEGREE")
	private String  degree;             // DEGREE 技术等级
	
	@Column(name="NUM_POLIT")
	private String   polits;                //NUM_POLIT 飞行员人数
	
	@Transient
	private String fjnum;
	
	
	public String getFjnum() {
		return fjnum;
	}

	public void setFjnum(String fjnum) {
		this.fjnum = fjnum;
	}
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>();
	@Transient
	private String deptName;  //单位名称
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
	@Transient
	private String typeName;  //机型名称
	@Transient
	private String gradeName; 
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}



	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

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

	public String getPolits() {
		return polits;
	}

	public void setPolits(String polits) {
		this.polits = polits;
	}
	

}
