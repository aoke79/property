package com.sms.training.qualification.bean;

import java.util.Date;

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
 * AhEmail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_EMAIL")
public class AhEmail implements java.io.Serializable {

	// Fields

	private String id;
	private AhUser anUser;
	private Date begindate;

	// Constructors

	/** default constructor */
	public AhEmail() {
	}

	/** full constructor */
	public AhEmail(String id) {
		this.id = id;
	}

	public AhEmail(String id,AhUser ahUser,Date begindate){
		this.id = id;
		this.anUser = ahUser;
		this.begindate = begindate;
	}

	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "UUID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade={}, fetch = FetchType.EAGER)
	@JoinColumn(name = "USERUUID")
	public AhUser getAnUser() {
		return anUser;
	}

	public void setAnUser(AhUser anUser) {
		this.anUser = anUser;
	}

	@Column(name = "BEGINDATE", length = 36)
	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	
	

}