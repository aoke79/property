package com.sms.training.qualification.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualBaseTemplate entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TF_QUAL_BASE_TEMPLATE")
public class TfQualBaseTemplate implements java.io.Serializable {

	// Fields

	private String id;
	private BaseAirPlanType baseAirplantype;
	private String description;

	// Constructors

	/** default constructor */
	public TfQualBaseTemplate() {
	}

	/** minimal constructor */
	public TfQualBaseTemplate(String id) {
		this.id = id;
	}

	/** full constructor */
	public TfQualBaseTemplate(String id, BaseAirPlanType baseAirplantype,
			String description) {
		this.id = id;
		this.baseAirplantype = baseAirplantype;
		this.description = description;
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

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ATR_ID")
	public BaseAirPlanType getBaseAirplantype() {
		return this.baseAirplantype;
	}

	public void setBaseAirplantype(BaseAirPlanType baseAirplantype) {
		this.baseAirplantype = baseAirplantype;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}