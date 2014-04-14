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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sinoframe.bean.CmPeople;

/**
 * TfQualDeclarApprovalinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_DECLAR_APPROVALINFO")
public class TfQualDeclarApprovalinfo implements java.io.Serializable {

	// Fields

	private String id;
	private String pid;
	private Date signingDate;
	private String opinionInfo;
	private String state;
	private String signingid;
	private String orgName;
//	private String userid;
	
	@Column(name = "ORG_NAME", length = 36)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
	@Column(name = "SIGNINGID", length = 36)
	public String getSigningid() {
		return signingid;
	}

	public void setSigningid(String signingid) {
		this.signingid = signingid;
	}

	/** default constructor */
	public TfQualDeclarApprovalinfo() {
	}

	/** minimal constructor */
	public TfQualDeclarApprovalinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TfQualDeclarApprovalinfo(String id, String signingid, String pid,
			Date signingDate, String opinionInfo, String state) {
		this.id = id;
		
		this.pid = pid;
		this.signingDate = signingDate;
		this.opinionInfo = opinionInfo;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PID", length = 36)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SIGNING_DATE", length = 7)
	public Date getSigningDate() {
		return this.signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	@Column(name = "OPINION_INFO", length = 300)
	public String getOpinionInfo() {
		return this.opinionInfo;
	}

	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}

	@Column(name = "STATE", length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
//	@Transient
//	public String getUserid() {
//		return userid;
//	}
	
}