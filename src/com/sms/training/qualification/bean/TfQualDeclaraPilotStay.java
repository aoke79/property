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

import com.sinoframe.bean.CmPeople;

/**
 * TfQualDeclaraPilotStay entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_DECLARA_PILOT_STAY",  uniqueConstraints = {})
public class TfQualDeclaraPilotStay implements java.io.Serializable {

	// Fields

	private String stayid;
	private CmPeople cmPeople;
	private TfQualBaseType tfQualBaseType;
	private String status;
	private Date year;
	
	//应训 YX /必训 BX
	private String trainType;
	
	@Column(name = "TRAIN_TYPE", length = 7)
	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	// Constructors
	@Temporal(TemporalType.DATE)
	@Column(name = "YEAR", length = 7)
	public Date getYear() {
		return year;
	}
	
	public void setYear(Date year) {
		this.year = year;
	}

	/** default constructor */
	public TfQualDeclaraPilotStay() {
	}

	/** minimal constructor */
	public TfQualDeclaraPilotStay(String stayid, CmPeople cmPeople) {
		this.stayid = stayid;
		this.cmPeople = cmPeople;
	}

	/** full constructor */
	public TfQualDeclaraPilotStay(String stayid, CmPeople cmPeople,
			TfQualBaseType tfQualBaseType, String status) {
		this.stayid = stayid;
		this.cmPeople = cmPeople;
		this.tfQualBaseType = tfQualBaseType;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "STAYID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getStayid() {
		return this.stayid;
	}

	public void setStayid(String stayid) {
		this.stayid = stayid;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID", unique = false, nullable = false, insertable = true, updatable = true)
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPEID", unique = false, nullable = true, insertable = true, updatable = true)
	public TfQualBaseType getTfQualBaseType() {
		return this.tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}