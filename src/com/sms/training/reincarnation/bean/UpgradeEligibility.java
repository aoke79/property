package com.sms.training.reincarnation.bean;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 升级资格要求MO_UP_NEED
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_NEED")
public class UpgradeEligibility implements java.io.Serializable{

	private static final long serialVersionUID = -8522476155636161616L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_NEEDID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;  //UP_NEEDID 升级资格要求码
	
	@Column(name="TYPEID")
	private String typeid;  //TYPEID 机型代码
	
	@Column(name="DEGREE")
	private String degree;  //DEGREE 目标技术等级
	
	@Column(name="NEEDTIME")
	private String needtime;  //NEEDTIME 要求时间
	@Transient
  private String degreeName;

	@Transient
	  private String typeNameBig;
	@Transient
	  private String typeNameBigs;

	public String getTypeNameBigs() {
		return typeNameBigs;
	}


	public void setTypeNameBigs(String typeNameBigs) {
		this.typeNameBigs = typeNameBigs;
	}


	public String getTypeNameBig() {
		return typeNameBig;
	}


	public void setTypeNameBig(String typeNameBig) {
		this.typeNameBig = typeNameBig;
	}


	public String getDegreeName() {
		return degreeName;
	}


	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getNeedtime() {
		return needtime;
	}


	public void setNeedtime(String needtime) {
		this.needtime = needtime;
	}
	@Transient
	private String typeName;
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>();
}
