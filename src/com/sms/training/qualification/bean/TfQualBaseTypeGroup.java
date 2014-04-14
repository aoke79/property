package com.sms.training.qualification.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualBaseTypeGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_TYPE_GROUP")
public class TfQualBaseTypeGroup implements java.io.Serializable {

	// Fields
	private String qtgroupid;
	private String qtgroupdesc;
	private Set<TfQualBaseType> tfQualBaseTypes = new HashSet<TfQualBaseType>(0);

	// Constructors

	/** default constructor */
	public TfQualBaseTypeGroup() {
	}

	/** minimal constructor */
	public TfQualBaseTypeGroup(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}

	/** full constructor */
	public TfQualBaseTypeGroup(String qtgroupid, String qtgroupdesc,
			Set<TfQualBaseType> tfQualBaseTypes) {
		this.qtgroupid = qtgroupid;
		this.qtgroupdesc = qtgroupdesc;
		this.tfQualBaseTypes = tfQualBaseTypes;
	}

	// Property accessors
	@Id
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "QTGROUPID", unique = true, nullable = false, length = 36)
	public String getQtgroupid() {
		return this.qtgroupid;
	}

	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}

	@Column(name = "QTGROUPDESC", length = 100)
	public String getQtgroupdesc() {
		return this.qtgroupdesc;
	}

	public void setQtgroupdesc(String qtgroupdesc) {
		this.qtgroupdesc = qtgroupdesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tfQualBaseTypeGroup")
	public Set<TfQualBaseType> getTfQualBaseTypes() {
		return this.tfQualBaseTypes;
	}

	public void setTfQualBaseTypes(Set<TfQualBaseType> tfQualBaseTypes) {
		this.tfQualBaseTypes = tfQualBaseTypes;
	}

}