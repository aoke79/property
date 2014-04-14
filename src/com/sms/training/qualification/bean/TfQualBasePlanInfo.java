package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualBasePlanInfoId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_PLAN_INFO")
public class TfQualBasePlanInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String teacherid;
	private String checkid;
	private String processid;
	private Date teachdate;
	private Date checkdate;

	// Constructors

	/** default constructor */
	public TfQualBasePlanInfo() {
	}

	/** full constructor */
	public TfQualBasePlanInfo(String id, String teacherid, String checkid,
			String processid, Date teachdate, Date checkdate) {
		this.id = id;
		this.teacherid = teacherid;
		this.checkid = checkid;
		this.processid = processid;
		this.teachdate = teachdate;
		this.checkdate = checkdate;
	}

	// Property accessors

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TEACHERID", length = 36)
	public String getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	@Column(name = "CHECKID", length = 36)
	public String getCheckid() {
		return this.checkid;
	}

	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}

	@Column(name = "PROCESSID", length = 36)
	public String getProcessid() {
		return this.processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TEACHDATE", length = 7)
	public Date getTeachdate() {
		return this.teachdate;
	}

	public void setTeachdate(Date teachdate) {
		this.teachdate = teachdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECKDATE", length = 7)
	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TfQualBasePlanInfo))
			return false;
		TfQualBasePlanInfo castOther = (TfQualBasePlanInfo) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTeacherid() == castOther.getTeacherid()) || (this
						.getTeacherid() != null
						&& castOther.getTeacherid() != null && this
						.getTeacherid().equals(castOther.getTeacherid())))
				&& ((this.getCheckid() == castOther.getCheckid()) || (this
						.getCheckid() != null && castOther.getCheckid() != null && this
						.getCheckid().equals(castOther.getCheckid())))
				&& ((this.getProcessid() == castOther.getProcessid()) || (this
						.getProcessid() != null
						&& castOther.getProcessid() != null && this
						.getProcessid().equals(castOther.getProcessid())))
				&& ((this.getTeachdate() == castOther.getTeachdate()) || (this
						.getTeachdate() != null
						&& castOther.getTeachdate() != null && this
						.getTeachdate().equals(castOther.getTeachdate())))
				&& ((this.getCheckdate() == castOther.getCheckdate()) || (this
						.getCheckdate() != null
						&& castOther.getCheckdate() != null && this
						.getCheckdate().equals(castOther.getCheckdate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTeacherid() == null ? 0 : this.getTeacherid().hashCode());
		result = 37 * result
				+ (getCheckid() == null ? 0 : this.getCheckid().hashCode());
		result = 37 * result
				+ (getProcessid() == null ? 0 : this.getProcessid().hashCode());
		result = 37 * result
				+ (getTeachdate() == null ? 0 : this.getTeachdate().hashCode());
		result = 37 * result
				+ (getCheckdate() == null ? 0 : this.getCheckdate().hashCode());
		return result;
	}

}