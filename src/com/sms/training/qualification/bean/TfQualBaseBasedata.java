package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 检查课信息和检查报告维护  entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_BASEDATA")
public class TfQualBaseBasedata implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3732212760807462669L;
	// Fields
	//信息ID
	private String nameid;
	//描述
	private String namedesc;
	//类别
	private String category;

	// Constructors

	/** default constructor */
	public TfQualBaseBasedata() {
	}

	/** full constructor */
	public TfQualBaseBasedata(String namedesc, String category) {
		this.namedesc = namedesc;
		this.category = category;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "NAMEID", unique = true, nullable = false, length = 36)
	public String getNameid() {
		return this.nameid;
	}

	public void setNameid(String nameid) {
		this.nameid = nameid;
	}

	@Column(name = "NAMEDESC", length = 150)
	public String getNamedesc() {
		return this.namedesc;
	}

	public void setNamedesc(String namedesc) {
		this.namedesc = namedesc;
	}

	@Column(name = "CATEGORY", length = 150)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}