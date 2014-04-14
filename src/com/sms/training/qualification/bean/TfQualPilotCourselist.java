package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TfQualPilotCourselist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_COURSELIST")
public class TfQualPilotCourselist implements java.io.Serializable {

	// Fields

	private String courselistid;
//	private String detailsid;
	private String pilotid;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	
	

	private String tpplanno;
	private String sgrid;
	private String tcpno;
	private Date trealstartdt;
	private Date trealenddt;
	private String tracherPilotid;
	private String tcategory;
	private String state;

	// Constructors

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/** default constructor */
	public TfQualPilotCourselist() {
	}

	/** minimal constructor */
	public TfQualPilotCourselist(String courselistid) {
		this.courselistid = courselistid;
	}

	/** full constructor */
	public TfQualPilotCourselist(String courselistid, String pilotid,
			TfQualDeclaraPilot tfQualDeclaraPilot, String tpplanno, String sgrid, String tcpno,
			Date trealstartdt, Date trealenddt, String tracherPilotid,
			String tcategory) {
		this.courselistid = courselistid;
		this.pilotid = pilotid;
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.tpplanno = tpplanno;
		this.sgrid = sgrid;
		this.tcpno = tcpno;
		this.trealstartdt = trealstartdt;
		this.trealenddt = trealenddt;
		this.tracherPilotid = tracherPilotid;
		this.tcategory = tcategory;
	}

	// Property accessors
	@Id
	@Column(name = "COURSELISTID", unique = true, nullable = false, length = 36)
	public String getCourselistid() {
		return this.courselistid;
	}

	public void setCourselistid(String courselistid) {
		this.courselistid = courselistid;
	}

//	@Column(name = "DETAILSID", length = 36)
//	public String getDetailsid() {
//		return this.detailsid;
//	}
//
//	public void setDetailsid(String detailsid) {
//		this.detailsid = detailsid;
//	}

	@Column(name = "PILOTID", length = 36)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Column(name = "TPPLANNO", length = 36)
	public String getTpplanno() {
		return this.tpplanno;
	}

	public void setTpplanno(String tpplanno) {
		this.tpplanno = tpplanno;
	}

	@Column(name = "SGRID", length = 14)
	public String getSgrid() {
		return this.sgrid;
	}

	public void setSgrid(String sgrid) {
		this.sgrid = sgrid;
	}

	@Column(name = "TCPNO", length = 18)
	public String getTcpno() {
		return this.tcpno;
	}

	public void setTcpno(String tcpno) {
		this.tcpno = tcpno;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TREALSTARTDT", length = 7)
	public Date getTrealstartdt() {
		return this.trealstartdt;
	}

	public void setTrealstartdt(Date trealstartdt) {
		this.trealstartdt = trealstartdt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TREALENDDT", length = 7)
	public Date getTrealenddt() {
		return this.trealenddt;
	}

	public void setTrealenddt(Date trealenddt) {
		this.trealenddt = trealenddt;
	}

	@Column(name = "TRACHER_PILOTID", length = 36)
	public String getTracherPilotid() {
		return this.tracherPilotid;
	}

	public void setTracherPilotid(String tracherPilotid) {
		this.tracherPilotid = tracherPilotid;
	}

	@Column(name = "TCATEGORY", length = 1)
	public String getTcategory() {
		return this.tcategory;
	}

	public void setTcategory(String tcategory) {
		this.tcategory = tcategory;
	}
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILSID", unique = false, nullable = false, insertable = true, updatable = true)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
}