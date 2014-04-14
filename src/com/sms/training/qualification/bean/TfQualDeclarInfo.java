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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.sinoframe.bean.CmPeople;

/**
 * TfQualDeclarInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_DECLAR_INFO",  uniqueConstraints = {})
public class TfQualDeclarInfo implements java.io.Serializable {

	// Fields

	private String declaredinfoid;
	private TfQualBaseType tfQualBaseType;
	private String declaredinfodesc;
	private Date createdt;
	private String creator;
	private String status;
	private String processid;
//	private String subProcessid;
	
	private Set<TfQualDeclaraPilot> tfQualDeclaraPilots = new HashSet<TfQualDeclaraPilot>(
			0);

	// Constructors

	/** default constructor */
	public TfQualDeclarInfo() {
	}

	/** minimal constructor */
	public TfQualDeclarInfo(String declaredinfoid, TfQualBaseType tfQualBaseType) {
		this.declaredinfoid = declaredinfoid;
		this.tfQualBaseType = tfQualBaseType;
	}

	/** full constructor */
	public TfQualDeclarInfo(String declaredinfoid,
			TfQualBaseType tfQualBaseType, String declaredinfodesc,
			Date createdt, String creator, String status, String processid,
			Set<TfQualDeclaraPilot> tfQualDeclaraPilots) {
		this.declaredinfoid = declaredinfoid;
		this.tfQualBaseType = tfQualBaseType;
		this.declaredinfodesc = declaredinfodesc;
		this.createdt = createdt;
		this.creator = creator;
		this.status = status;
		this.processid = processid;
		this.tfQualDeclaraPilots = tfQualDeclaraPilots;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "DECLAREDINFOID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getDeclaredinfoid() {
		return this.declaredinfoid;
	}

	public void setDeclaredinfoid(String declaredinfoid) {
		this.declaredinfoid = declaredinfoid;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPEID", unique = false, nullable = false, insertable = true, updatable = true)
	public TfQualBaseType getTfQualBaseType() {
		return this.tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@Column(name = "DECLAREDINFODESC", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDeclaredinfodesc() {
		return this.declaredinfodesc;
	}

	public void setDeclaredinfodesc(String declaredinfodesc) {
		this.declaredinfodesc = declaredinfodesc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDT", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCreatedt() {
		return this.createdt;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
	}

	@Column(name = "CREATOR", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PROCESSID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getProcessid() {
		return this.processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tfQualDeclarInfo")
	public Set<TfQualDeclaraPilot> getTfQualDeclaraPilots() {
		return this.tfQualDeclaraPilots;
	}

	public void setTfQualDeclaraPilots(
			Set<TfQualDeclaraPilot> tfQualDeclaraPilots) {
		this.tfQualDeclaraPilots = tfQualDeclaraPilots;
	}

	
//	@Column(name = "SUB_PROCESSID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
//	public String getSubProcessid() {
//		return subProcessid;
//	}
//
//	public void setSubProcessid(String subProcessid) {
//		this.subProcessid = subProcessid;
//	}
	 
}