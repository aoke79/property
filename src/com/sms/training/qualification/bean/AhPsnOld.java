package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AH_PSN_OLD")
public class AhPsnOld implements java.io.Serializable {
	
	private String psncode;
	private String psnname;
	private String id;
	private String psnclscopename;
	private String psnclscope; 
	
	public AhPsnOld() {
		// TODO Auto-generated constructor stub
	}
	
	public AhPsnOld(String psncode) {
		this.psncode=psncode;
	}
	
	public AhPsnOld(String psncode,String psnname,String id,String psnclscopename,String psnclscope) {
		this.psncode=psncode;
		this.psnname=psnname;
		this.psnclscope=psnclscope;
		this.psnclscopename=psnclscopename;
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "PSNCODE", unique = true, nullable = false, length = 50)
	public String getPsncode() {
		return psncode;
	}

	public void setPsncode(String psncode) {
		this.psncode = psncode;
	}

	@Column(name = "PSNNAME", length = 50)
	public String getPsnname() {
		return psnname;
	}

	public void setPsnname(String psnname) {
		this.psnname = psnname;
	}

	@Column(name = "ID", length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PSNCLSCOPENAME", length = 50)
	public String getPsnclscopename() {
		return psnclscopename;
	}

	public void setPsnclscopename(String psnclscopename) {
		this.psnclscopename = psnclscopename;
	}

	@Column(name = "PSNCLSCOPE", length = 50)
	public String getPsnclscope() {
		return psnclscope;
	}

	public void setPsnclscope(String psnclscope) {
		this.psnclscope = psnclscope;
	}
	
	
	
	
	 
}
