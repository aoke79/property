package com.sms.training.qualificationassess.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ModelArgumentTypeZs entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MODEL_ARGUMENT_TYPE_ZS", schema = "SMS")
public class ArgumentType implements java.io.Serializable {

	// Fields

	private String argumenttypeid;
	private String argumenttypename;
	private String argumenttypedesc;
	private String operate;
	private Date createdate;
	private Date modifydate;
	private String status;
	private String other;

	// Constructors

	/** default constructor */
	public ArgumentType() {
	}

	/** minimal constructor */
	public ArgumentType(String argumenttypeid) {
		this.argumenttypeid = argumenttypeid;
	}

	/** full constructor */
	public ArgumentType(String argumenttypeid, String argumenttypename,
			String argumenttypedesc, String operate, Date createdate,
			Date modifydate, String status, String other) {
		this.argumenttypeid = argumenttypeid;
		this.argumenttypename = argumenttypename;
		this.argumenttypedesc = argumenttypedesc;
		this.operate = operate;
		this.createdate = createdate;
		this.modifydate = modifydate;
		this.status = status;
		this.other = other;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
	public String getArgumenttypeid() {
		return this.argumenttypeid;
	}

	public void setArgumenttypeid(String argumenttypeid) {
		this.argumenttypeid = argumenttypeid;
	}

	@Column(name = "ARGUMENTTYPENAME", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getArgumenttypename() {
		return this.argumenttypename;
	}

	public void setArgumenttypename(String argumenttypename) {
		this.argumenttypename = argumenttypename;
	}

	@Column(name = "ARGUMENTTYPEDESC", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getArgumenttypedesc() {
		return this.argumenttypedesc;
	}

	public void setArgumenttypedesc(String argumenttypedesc) {
		this.argumenttypedesc = argumenttypedesc;
	}

	@Column(name = "OPERATE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFYDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OTHER", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}