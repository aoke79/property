package com.sms.training.qualification.bean;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * CmPeoplePhoto entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CM_PEOPLE_PHOTO")
public class CmPeoplePhoto implements java.io.Serializable {
	// Fields

	private String hrid;
	private byte[] photo;

	// Constructors

	/** default constructor */
	public CmPeoplePhoto() {
	}

	/** full constructor */
	public CmPeoplePhoto(String hrid, byte[] photo) {
		this.hrid = hrid;
		this.photo = photo;
	}

	// Property accessors
	@Id
	@Column(name = "HRID", unique = false, nullable = false, insertable = true, updatable = true, length = 16)
	public String getHrid() {
		return this.hrid;
	}

	public void setHrid(String hrid) {
		this.hrid = hrid;
	}

	@Lob
	@Column(name = "PHOTO",columnDefinition = "BLOB", unique = false, nullable = true, insertable = true, updatable = true)
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}