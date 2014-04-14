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
 * Argument entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MODEL_ARGUMENT_ZS", schema = "SMS", uniqueConstraints = {})
public class Argument implements java.io.Serializable {

	// Fields

	private String argumentid;
	private String argumentname;
	private String argumenttype;
	private String argumentdesc;
	private Long argumentvalue;
	private String other;
	private String operate;
	private Date createdate;
	private Date modifydate;
	private String status;

	// Constructors

	/** default constructor */
	public Argument() {
	}

	/** minimal constructor */
	public Argument(String argumentid) {
		this.argumentid = argumentid;
	}

	/** full constructor */
	public Argument(String argumentid, String argumentname,
			String argumenttype, String argumentdesc, Long argumentvalue,
			String other, String operate, Date createdate, Date modifydate,
			String status) {
		this.argumentid = argumentid;
		this.argumentname = argumentname;
		this.argumenttype = argumenttype;
		this.argumentdesc = argumentdesc;
		this.argumentvalue = argumentvalue;
		this.other = other;
		this.operate = operate;
		this.createdate = createdate;
		this.modifydate = modifydate;
		this.status = status;
	}

	// Property accessors
	@Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
	public String getArgumentid() {
		return this.argumentid;
	}

	public void setArgumentid(String argumentid) {
		this.argumentid = argumentid;
	}

	@Column(name = "ARGUMENTNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getArgumentname() {
		return this.argumentname;
	}

	public void setArgumentname(String argumentname) {
		this.argumentname = argumentname;
	}

	@Column(name = "ARGUMENTTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getArgumenttype() {
		return this.argumenttype;
	}

	public void setArgumenttype(String argumenttype) {
		this.argumenttype = argumenttype;
	}

	@Column(name = "ARGUMENTDESC", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getArgumentdesc() {
		return this.argumentdesc;
	}

	public void setArgumentdesc(String argumentdesc) {
		this.argumentdesc = argumentdesc;
	}

	@Column(name = "ARGUMENTVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getArgumentvalue() {
		return this.argumentvalue;
	}

	public void setArgumentvalue(Long argumentvalue) {
		this.argumentvalue = argumentvalue;
	}

	@Column(name = "OTHER", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
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

}