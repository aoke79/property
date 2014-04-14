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
 * CopilotTurn entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MODEL_COPILOT_TURN_ZS", schema = "SMS")
public class CopilotTurn implements java.io.Serializable {

	// Fields

	private String id;
	private String upqualificationname;
	private Long halfrefvalue;
	private Long thisrefvalue;
	private Long threerefvalue;
	private Long fiverefvalue;
	private Long thisplanvalue;
	private Long heavyrequirevalue;
	private Long safetypronumber;
	private String operate;
	private Date createdate;
	private Date modifydate;
	private String status;
	private String remark;

	// Constructors

	/** default constructor */
	public CopilotTurn() {
	}

	/** minimal constructor */
	public CopilotTurn(String id) {
		this.id = id;
	}

	/** full constructor */
	public CopilotTurn(String id, String upqualificationname,
			Long halfrefvalue, Long thisrefvalue, Long threerefvalue,
			Long fiverefvalue, Long thisplanvalue, Long heavyrequirevalue,
			Long safetypronumber, String operate, Date createdate,
			Date modifydate, String status, String remark) {
		this.id = id;
		this.upqualificationname = upqualificationname;
		this.halfrefvalue = halfrefvalue;
		this.thisrefvalue = thisrefvalue;
		this.threerefvalue = threerefvalue;
		this.fiverefvalue = fiverefvalue;
		this.thisplanvalue = thisplanvalue;
		this.heavyrequirevalue = heavyrequirevalue;
		this.safetypronumber = safetypronumber;
		this.operate = operate;
		this.createdate = createdate;
		this.modifydate = modifydate;
		this.status = status;
		this.remark = remark;
	}

	// Property accessors
	
	@Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "UPQUALIFICATIONNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getUpqualificationname() {
		return this.upqualificationname;
	}

	public void setUpqualificationname(String upqualificationname) {
		this.upqualificationname = upqualificationname;
	}

	@Column(name = "HALFREFVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getHalfrefvalue() {
		return this.halfrefvalue;
	}

	public void setHalfrefvalue(Long halfrefvalue) {
		this.halfrefvalue = halfrefvalue;
	}

	@Column(name = "THISREFVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getThisrefvalue() {
		return this.thisrefvalue;
	}

	public void setThisrefvalue(Long thisrefvalue) {
		this.thisrefvalue = thisrefvalue;
	}

	@Column(name = "THREEREFVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getThreerefvalue() {
		return this.threerefvalue;
	}

	public void setThreerefvalue(Long threerefvalue) {
		this.threerefvalue = threerefvalue;
	}

	@Column(name = "FIVEREFVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getFiverefvalue() {
		return this.fiverefvalue;
	}

	public void setFiverefvalue(Long fiverefvalue) {
		this.fiverefvalue = fiverefvalue;
	}

	@Column(name = "THISPLANVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getThisplanvalue() {
		return this.thisplanvalue;
	}

	public void setThisplanvalue(Long thisplanvalue) {
		this.thisplanvalue = thisplanvalue;
	}

	@Column(name = "HEAVYREQUIREVALUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getHeavyrequirevalue() {
		return this.heavyrequirevalue;
	}

	public void setHeavyrequirevalue(Long heavyrequirevalue) {
		this.heavyrequirevalue = heavyrequirevalue;
	}

	@Column(name = "SAFETYPRONUMBER", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getSafetypronumber() {
		return this.safetypronumber;
	}

	public void setSafetypronumber(Long safetypronumber) {
		this.safetypronumber = safetypronumber;
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

	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}