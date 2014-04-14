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
import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualPilotDocuments entity. 
 * @author licumn
 */
@Entity
@Table(name = "TF_QUAL_PILOT_DOCUMENTS")
public class TfQualPilotDocuments implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private String docid;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	private String docname;
	private String docurl;
	private Date creationdt;
	private QuaCmUser cmuser;

	// Constructors

	/** default constructor */
	public TfQualPilotDocuments() {
	}

	/** full constructor */
	public TfQualPilotDocuments(TfQualDeclaraPilot tfQualDeclaraPilot,
			String docname, String docurl, Date creationdt, QuaCmUser cmuser) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.docname = docname;
		this.docurl = docurl;
		this.creationdt = creationdt;
		this.cmuser = cmuser;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "DOCID", unique = true, nullable = false, length = 36)
	public String getDocid() {
		return this.docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DECLAREDINFOID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return this.tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@Column(name = "DOCNAME", length = 90)
	public String getDocname() {
		return this.docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	@Column(name = "DOCURL", length = 100)
	public String getDocurl() {
		return this.docurl;
	}

	public void setDocurl(String docurl) {
		this.docurl = docurl;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDT", length = 7)
	public Date getCreationdt() {
		return this.creationdt;
	}

	public void setCreationdt(Date creationdt) {
		this.creationdt = creationdt;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CREATER")
	public QuaCmUser getCmuser() {
		return cmuser;
	}

	public void setCmuser(QuaCmUser cmuser) {
		this.cmuser = cmuser;
	}

}