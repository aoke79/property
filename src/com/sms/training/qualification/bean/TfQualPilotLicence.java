package com.sms.training.qualification.bean;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualPilotLicence entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_LICENCE", uniqueConstraints = {})
public class TfQualPilotLicence implements java.io.Serializable {

	// Fields

	private String plcseq;
	private String pilotid;
	private String atrid;
	private String plcno;
	private String plcdesc;
	private String plctype;
	private Date plccheckd;
	private Date plcexpired;
	private String plcrremark;
	private String plcstus;
	private String plclifecycle;
	private String creator;
	private Date createdt;
	private String modifier;
	private Date modifydt;
	private Date plcgetd;
	private String oldplcseq;
	private Long rvrup;
	private Long rvrdown;
	private String firstflag;
	private Date  endorsement; 
	private Set<TfQualPilotTechrecord> tfQualPilotTechrecords = new HashSet<TfQualPilotTechrecord>(
			0);

	// Constructors

	/** default constructor */
	public TfQualPilotLicence() {
	}

	/** minimal constructor */
	public TfQualPilotLicence(String plcseq, String pilotid, String atrid) {
		this.plcseq = plcseq;
		this.pilotid = pilotid;
		this.atrid = atrid;
	}

	/** full constructor */
	public TfQualPilotLicence(String plcseq, String pilotid, String atrid,
			String plcno, String plcdesc, String plctype, Date plccheckd,
			Date plcexpired, String plcrremark, String plcstus,
			String plclifecycle, String creator, Date createdt,
			String modifier, Date modifydt, Date plcgetd, String oldplcseq,
			Long rvrup, Long rvrdown, String firstflag,
			Set<TfQualPilotTechrecord> tfQualPilotTechrecords) {
		this.plcseq = plcseq;
		this.pilotid = pilotid;
		this.atrid = atrid;
		this.plcno = plcno;
		this.plcdesc = plcdesc;
		this.plctype = plctype;
		this.plccheckd = plccheckd;
		this.plcexpired = plcexpired;
		this.plcrremark = plcrremark;
		this.plcstus = plcstus;
		this.plclifecycle = plclifecycle;
		this.creator = creator;
		this.createdt = createdt;
		this.modifier = modifier;
		this.modifydt = modifydt;
		this.plcgetd = plcgetd;
		this.oldplcseq = oldplcseq;
		this.rvrup = rvrup;
		this.rvrdown = rvrdown;
		this.firstflag = firstflag;
		this.tfQualPilotTechrecords = tfQualPilotTechrecords;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "PLCSEQ", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getPlcseq() {
		return this.plcseq;
	}

	public void setPlcseq(String plcseq) {
		this.plcseq = plcseq;
	}

	@Column(name = "PILOTID", unique = false, nullable = false, insertable = true, updatable = true, length = 16)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Column(name = "ATRID", unique = false, nullable = false, insertable = true, updatable = true, length = 16)
	public String getAtrid() {
		return this.atrid;
	}

	public void setAtrid(String atrid) {
		this.atrid = atrid;
	}

	@Column(name = "PLCNO", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getPlcno() {
		return this.plcno;
	}

	public void setPlcno(String plcno) {
		this.plcno = plcno;
	}

	@Column(name = "PLCDESC", unique = false, nullable = true, insertable = true, updatable = true, length = 54)
	public String getPlcdesc() {
		return this.plcdesc;
	}

	public void setPlcdesc(String plcdesc) {
		this.plcdesc = plcdesc;
	}

	@Column(name = "PLCTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getPlctype() {
		return this.plctype;
	}

	public void setPlctype(String plctype) {
		this.plctype = plctype;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLCCHECKD", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPlccheckd() {
		return this.plccheckd;
	}

	public void setPlccheckd(Date plccheckd) {
		this.plccheckd = plccheckd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLCEXPIRED", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPlcexpired() {
		return this.plcexpired;
	}

	public void setPlcexpired(Date plcexpired) {
		this.plcexpired = plcexpired;
	}

	@Column(name = "PLCRREMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getPlcrremark() {
		return this.plcrremark;
	}

	public void setPlcrremark(String plcrremark) {
		this.plcrremark = plcrremark;
	}

	@Column(name = "PLCSTUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getPlcstus() {
		return this.plcstus;
	}

	public void setPlcstus(String plcstus) {
		this.plcstus = plcstus;
	}

	@Column(name = "PLCLIFECYCLE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getPlclifecycle() {
		return this.plclifecycle;
	}

	public void setPlclifecycle(String plclifecycle) {
		this.plclifecycle = plclifecycle;
	}

	@Column(name = "CREATOR", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDT", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCreatedt() {
		return this.createdt;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
	}

	@Column(name = "MODIFIER", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFYDT", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getModifydt() {
		return this.modifydt;
	}

	public void setModifydt(Date modifydt) {
		this.modifydt = modifydt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLCGETD", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPlcgetd() {
		return this.plcgetd;
	}

	public void setPlcgetd(Date plcgetd) {
		this.plcgetd = plcgetd;
	}

	@Column(name = "OLDPLCSEQ", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getOldplcseq() {
		return this.oldplcseq;
	}

	public void setOldplcseq(String oldplcseq) {
		this.oldplcseq = oldplcseq;
	}

	@Column(name = "RVRUP", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getRvrup() {
		return this.rvrup;
	}

	public void setRvrup(Long rvrup) {
		this.rvrup = rvrup;
	}

	@Column(name = "RVRDOWN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getRvrdown() {
		return this.rvrdown;
	}

	public void setRvrdown(Long rvrdown) {
		this.rvrdown = rvrdown;
	}

	@Column(name = "FIRSTFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getFirstflag() {
		return this.firstflag;
	}

	public void setFirstflag(String firstflag) {
		this.firstflag = firstflag;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ENDORSEMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getEndorsement() {
		return endorsement;
	}

	public void setEndorsement(Date endorsement) {
		this.endorsement = endorsement;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tfQualPilotLicence")
	public Set<TfQualPilotTechrecord> getTfQualPilotTechrecords() {
		return this.tfQualPilotTechrecords;
	}

	public void setTfQualPilotTechrecords(
			Set<TfQualPilotTechrecord> tfQualPilotTechrecords) {
		this.tfQualPilotTechrecords = tfQualPilotTechrecords;
	}

}