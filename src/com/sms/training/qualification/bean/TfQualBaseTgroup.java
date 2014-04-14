package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualBaseTgroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_TGROUP")
public class TfQualBaseTgroup implements java.io.Serializable {

	// Fields
    //小类id
	private String typegroupid;
	//小类名称
	private String typegroupdesc;
	//对应的大类
	private TfQualBaseTypeGroup tfQualBaseTypeGroup;

	// Constructors

	/** default constructor */
	public TfQualBaseTgroup() {
	}

	/** full constructor */
	public TfQualBaseTgroup(String typegroupdesc, TfQualBaseTypeGroup tfQualBaseTypeGroup) {
		this.typegroupdesc = typegroupdesc;
		this.tfQualBaseTypeGroup = tfQualBaseTypeGroup;
	}

	// Property accessors
	@Id
	//@GeneratedValue(generator = "generator")
	//@GenericGenerator(name = "generator", strategy = "uuid")
	@Column(name = "TYPEGROUPID", unique = true, nullable = false, length = 36)
	public String getTypegroupid() {
		return this.typegroupid;
	}

	public void setTypegroupid(String typegroupid) {
		this.typegroupid = typegroupid;
	}

	@Column(name = "TYPEGROUPDESC", length = 60)
	public String getTypegroupdesc() {
		return this.typegroupdesc;
	}

	public void setTypegroupdesc(String typegroupdesc) {
		this.typegroupdesc = typegroupdesc;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "QTGROUPID", unique = false, nullable = true, insertable = true, updatable = true)
	public TfQualBaseTypeGroup getTfQualBaseTypeGroup() {
		return this.tfQualBaseTypeGroup;
	}

	public void setTfQualBaseTypeGroup(TfQualBaseTypeGroup tfQualBaseTypeGroup) {
		this.tfQualBaseTypeGroup = tfQualBaseTypeGroup;
	}
}