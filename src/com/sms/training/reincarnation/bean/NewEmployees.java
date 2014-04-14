package com.sms.training.reincarnation.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 公司新雇员预测MO_UP_NEW
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_NEW")
public class NewEmployees implements java.io.Serializable{

	private static final long serialVersionUID = -5465960468860665247L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_NEWID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;   //UP_NEWID
	
	@Column(name="DEPTID")
	private String deptid;   //DEPTID  单位代码
	
	
	@Transient
	private String deptName;
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>();
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>();
	@Column(name="PHASE")
	private String phase;   //PHASE  阶段
	
	@Column(name="NUM_NEW")
	private Integer news;   //NUM_NEW  新学员人数
	
	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public List<String[]> getCompanyInfoListnew() {
		return companyInfoListnew;
	}


	public void setCompanyInfoListnew(List<String[]> companyInfoListnew) {
		this.companyInfoListnew = companyInfoListnew;
	}


	public List<String[]> getCompanyInfoList() {
		return companyInfoList;
	}


	public void setCompanyInfoList(List<String[]> companyInfoList) {
		this.companyInfoList = companyInfoList;
	}


	@Column(name="RATE_OUT")
	private Integer outs;// RATE_OUT  停飞率


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


	public String getPhase() {
		return phase;
	}


	public void setPhase(String phase) {
		this.phase = phase;
	}


	public Integer getNews() {
		return news;
	}


	public void setNews(Integer news) {
		this.news = news;
	}


	public Integer getOuts() {
		return outs;
	}


	public void setOuts(Integer outs) {
		this.outs = outs;
	}
}
