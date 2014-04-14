package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualBaseConditions entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_CONDITIONS", uniqueConstraints = {})
public class TfQualBaseConditions implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String conditionid;
	private String conditiondesc;
	private String remarks;

	/** default constructor */
	public TfQualBaseConditions() {
	}

	/** minimal constructor */
	public TfQualBaseConditions(String conditionid) {
		this.conditionid = conditionid;
	}

	/** full constructor */
	public TfQualBaseConditions(String conditionid, String conditiondesc) {
		this.conditionid = conditionid;
		this.conditiondesc = conditiondesc;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "CONDITIONID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getConditionid() {
		return this.conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	@Column(name = "CONDITIONDESC", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getConditiondesc() {
		return this.conditiondesc;
	}

	public void setConditiondesc(String conditiondesc) {
		this.conditiondesc = conditiondesc;
	}
	@Column(name = "REMARKS", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}