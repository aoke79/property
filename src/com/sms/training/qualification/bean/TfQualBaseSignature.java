package com.sms.training.qualification.bean;
// default package

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
 * TfQualBaseSignature entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_SIGNATURE")
public class TfQualBaseSignature implements java.io.Serializable {

	// Fields
	private String signatureid;
	
	private byte[] imageurl;
	private QuaCmUser cmuser;

	// Constructors

	/** default constructor */
	public TfQualBaseSignature() {
	}

	/** minimal constructor */
	public TfQualBaseSignature(String signatureid) {
		this.signatureid = signatureid;
	}

	/** full constructor */
	public TfQualBaseSignature(String signatureid, QuaCmUser cmuser,
			byte[] imageurl) {
		this.signatureid = signatureid;
		this.cmuser = cmuser;
		this.imageurl = imageurl;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SIGNATUREID", unique = true, nullable = false, length = 36)
	public String getSignatureid() {
		return this.signatureid;
	}

	public void setSignatureid(String signatureid) {
		this.signatureid = signatureid;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "STAFFID")
	public QuaCmUser getCmuser() {
		return cmuser;
	}

	public void setCmuser(QuaCmUser cmuser) {
		this.cmuser = cmuser;
	}
	
	@Column(name = "IMAGEURL", length = 200)
	public byte[] getImageurl() {
		return imageurl;
	}

	public void setImageurl(byte[] imageurl) {
		this.imageurl = imageurl;
	}


}