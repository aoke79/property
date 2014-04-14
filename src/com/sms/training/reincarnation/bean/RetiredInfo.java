package com.sms.training.reincarnation.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 退役情况表MO_UP_RETIRE
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_RETIRE")
public class RetiredInfo implements java.io.Serializable{

	private static final long serialVersionUID = 6599776260460806112L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_RETIREID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;   //UP_RETIREID 退休情况表码
	
	@Column(name="DEPTID")
	private String deptid;   //DEPTID 单位代码
	
	
	@Column(name="TYPEID")
	private String typeid;   //DEPTID 单位代码
	
	
	@Transient
	private String deptName;
	public String getTypeid() {
		return typeid;
	}



	public void setTypeid(String typeid) {
		this.typeid = typeid;
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
	private String typeName;
	
	@Column(name="YEAR")
	private String year;   //YEAR 年
	
	
	@Column(name="MONTH")
	private String month;   //MONTH 月
	
	
	@Column(name="DEGREE")
	private String  degree; //DEGREE 技术等级
	
	
	@Column(name="NUM_RETIRE")
	private String  retire; //NUM_RETIRE   退休人员数量

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
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>();

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



	public String getRetire() {
		return retire;
	}



	public void setRetire(String retire) {
		this.retire = retire;
	}

}
