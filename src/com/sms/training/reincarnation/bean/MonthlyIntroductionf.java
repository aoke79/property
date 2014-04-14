package com.sms.training.reincarnation.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**\
 * 月度引进飞机计划 MO_UP_IN
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_OUT")
public class MonthlyIntroductionf implements java.io.Serializable{

	private static final long serialVersionUID = 701578263583948363L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_OUTID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;      // UP_INID
	
	@Column(name="DEPTID")
	private String deptid;      // DEPTID  单位代码
	
	@Column(name="TYPEID")
	private String typeid;      // TYPEID  机型代码
	
	@Column(name="YEAR")
	private String year;      //    YEAR
	
	
	@Column(name="MONTH")
	private String month;      //    MONTH
	
	@Transient
	private String deptName;
	
	@Transient
	private String typeName;
	
	public String getDeptName() {
		return deptName;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name="NUM_PLANE_OUT")
	private Integer planein;  // NUM_PLANE_IN 月度引进飞机数量



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



	public Integer getPlanein() {
		return planein;
	}



	public void setPlanein(Integer planein) {
		this.planein = planein;
	}
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
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
	private List<String[]> companyInfoListnew = new ArrayList<String[]>();
}
