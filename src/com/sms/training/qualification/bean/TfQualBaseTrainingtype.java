package com.sms.training.qualification.bean;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TfQualBaseTrainingtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_TRAININGTYPE")
public class TfQualBaseTrainingtype implements java.io.Serializable {

	// Fields

	private String ttypeseq;
	private String ttgrid;
	private String lctid;
	private String ttypecode;
	private String ttypeorigingrade;
	private String ttypetargetgrade;
	private String ttypeoriginatrid;
	private String ttypetargetatrid;
	private String ttypeperiod;
	private String ttypestus;
	private String ttypelifecycle;
	private String creator;
	private Date createdt;
	private String modifier;
	private Date modifydt;
	private String ttypedesc;
	private String tpilottype;
	private String tlessonid;
	private String classtypeids;
	private String certfid;
	private String sttrainingtype;

	// Constructors

	/** default constructor */
	public TfQualBaseTrainingtype() {
	}

	/** minimal constructor */
	public TfQualBaseTrainingtype(String ttypeseq) {
		this.ttypeseq = ttypeseq;
	}

	/** full constructor */
	public TfQualBaseTrainingtype(String ttypeseq, String ttgrid,
			String lctid, String ttypecode, String ttypeorigingrade,
			String ttypetargetgrade, String ttypeoriginatrid,
			String ttypetargetatrid, String ttypeperiod, String ttypestus,
			String ttypelifecycle, String creator, Date createdt,
			String modifier, Date modifydt, String ttypedesc,
			String tpilottype, String tlessonid, String classtypeids,
			String certfid, String sttrainingtype) {
		this.ttypeseq = ttypeseq;
		this.ttgrid = ttgrid;
		this.lctid = lctid;
		this.ttypecode = ttypecode;
		this.ttypeorigingrade = ttypeorigingrade;
		this.ttypetargetgrade = ttypetargetgrade;
		this.ttypeoriginatrid = ttypeoriginatrid;
		this.ttypetargetatrid = ttypetargetatrid;
		this.ttypeperiod = ttypeperiod;
		this.ttypestus = ttypestus;
		this.ttypelifecycle = ttypelifecycle;
		this.creator = creator;
		this.createdt = createdt;
		this.modifier = modifier;
		this.modifydt = modifydt;
		this.ttypedesc = ttypedesc;
		this.tpilottype = tpilottype;
		this.tlessonid = tlessonid;
		this.classtypeids = classtypeids;
		this.certfid = certfid;
		this.sttrainingtype = sttrainingtype;
	}

	// Property accessors
	@Id
	@Column(name = "TTYPESEQ", unique = true, nullable = false, precision = 22, scale = 0)
	public String getTtypeseq() {
		return this.ttypeseq;
	}

	public void setTtypeseq(String ttypeseq) {
		this.ttypeseq = ttypeseq;
	}

	@Column(name = "TTGRID", length = 4)
	public String getTtgrid() {
		return this.ttgrid;
	}

	public void setTtgrid(String ttgrid) {
		this.ttgrid = ttgrid;
	}

	@Column(name = "LCTID", precision = 22, scale = 0)
	public String getLctid() {
		return this.lctid;
	}

	public void setLctid(String lctid) {
		this.lctid = lctid;
	}

	@Column(name = "TTYPECODE", length = 76)
	public String getTtypecode() {
		return this.ttypecode;
	}

	public void setTtypecode(String ttypecode) {
		this.ttypecode = ttypecode;
	}

	@Column(name = "TTYPEORIGINGRADE", length = 46)
	public String getTtypeorigingrade() {
		return this.ttypeorigingrade;
	}

	public void setTtypeorigingrade(String ttypeorigingrade) {
		this.ttypeorigingrade = ttypeorigingrade;
	}

	@Column(name = "TTYPETARGETGRADE", length = 4)
	public String getTtypetargetgrade() {
		return this.ttypetargetgrade;
	}

	public void setTtypetargetgrade(String ttypetargetgrade) {
		this.ttypetargetgrade = ttypetargetgrade;
	}

	@Column(name = "TTYPEORIGINATRID", length = 46)
	public String getTtypeoriginatrid() {
		return this.ttypeoriginatrid;
	}

	public void setTtypeoriginatrid(String ttypeoriginatrid) {
		this.ttypeoriginatrid = ttypeoriginatrid;
	}

	@Column(name = "TTYPETARGETATRID", length = 16)
	public String getTtypetargetatrid() {
		return this.ttypetargetatrid;
	}

	public void setTtypetargetatrid(String ttypetargetatrid) {
		this.ttypetargetatrid = ttypetargetatrid;
	}

	@Column(name = "TTYPEPERIOD", precision = 22, scale = 0)
	public String getTtypeperiod() {
		return this.ttypeperiod;
	}

	public void setTtypeperiod(String ttypeperiod) {
		this.ttypeperiod = ttypeperiod;
	}

	@Column(name = "TTYPESTUS", length = 1)
	public String getTtypestus() {
		return this.ttypestus;
	}

	public void setTtypestus(String ttypestus) {
		this.ttypestus = ttypestus;
	}

	@Column(name = "TTYPELIFECYCLE", length = 1)
	public String getTtypelifecycle() {
		return this.ttypelifecycle;
	}

	public void setTtypelifecycle(String ttypelifecycle) {
		this.ttypelifecycle = ttypelifecycle;
	}

	@Column(name = "CREATOR", length = 46)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDT", length = 7)
	public Date getCreatedt() {
		return this.createdt;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
	}

	@Column(name = "MODIFIER", length = 46)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFYDT", length = 7)
	public Date getModifydt() {
		return this.modifydt;
	}

	public void setModifydt(Date modifydt) {
		this.modifydt = modifydt;
	}

	@Column(name = "TTYPEDESC", length = 300)
	public String getTtypedesc() {
		return this.ttypedesc;
	}

	public void setTtypedesc(String ttypedesc) {
		this.ttypedesc = ttypedesc;
	}

	@Column(name = "TPILOTTYPE", length = 1)
	public String getTpilottype() {
		return this.tpilottype;
	}

	public void setTpilottype(String tpilottype) {
		this.tpilottype = tpilottype;
	}

	@Column(name = "TLESSONID", length = 1)
	public String getTlessonid() {
		return this.tlessonid;
	}

	public void setTlessonid(String tlessonid) {
		this.tlessonid = tlessonid;
	}

	@Column(name = "CLASSTYPEIDS", length = 300)
	public String getClasstypeids() {
		return this.classtypeids;
	}

	public void setClasstypeids(String classtypeids) {
		this.classtypeids = classtypeids;
	}

	@Column(name = "CERTFID", length = 10)
	public String getCertfid() {
		return this.certfid;
	}

	public void setCertfid(String certfid) {
		this.certfid = certfid;
	}

	@Column(name = "STTRAININGTYPE", length = 46)
	public String getSttrainingtype() {
		return this.sttrainingtype;
	}

	public void setSttrainingtype(String sttrainingtype) {
		this.sttrainingtype = sttrainingtype;
	}


}