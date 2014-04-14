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

import com.sms.training.qualification.bean.TfQualPilotTechgrade;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;

/**
 * TfQualBaseAccessconditions entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_ACCESSCONDITIONS")
public class TfQualBaseAccessconditions implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private String svconditionsid;
	private BaseAirPlanType baseAirplantype;
	private TfQualBaseType tfQualBaseType;
	private TfQualPilotTechgrade svgrade;
	private String svlicensetype;
	private Long svallflytimemin;
	private String svallflytimemax;
	private Long svcapflytime;
	private Long svflytimeyear2;
	private Long svflytimemonth12;
	private Long svflytimemonth6;
	private Long svallupdown;
	private Long svupdownmin;
	private Long svupdownmax;
	private Long svupdownmonth12;
	private Long svupdownday90;
	private String svatpl;
	private String svmultiplecertificate;
	private String svicao;
	private String svprevlevelgrade;
	private Long svcurrentflytime;
	private Long svcurrentupdown;
	private String svconditionsname ;

	// Constructors

	/** default constructor */
	public TfQualBaseAccessconditions() {
	}

	/** minimal constructor */
	public TfQualBaseAccessconditions(String svconditionsid,
			BaseAirPlanType baseAirplantype, TfQualBaseType tfQualBaseType,
			TfQualPilotTechgrade svgrade) {
		this.svconditionsid = svconditionsid;
		this.baseAirplantype = baseAirplantype;
		this.tfQualBaseType = tfQualBaseType;
		this.svgrade = svgrade;
	}

	/** full constructor */
	public TfQualBaseAccessconditions(String svconditionsid,
			BaseAirPlanType baseAirplantype, TfQualBaseType tfQualBaseType,
			TfQualPilotTechgrade svgrade, String svlicensetype, Long svallflytimemin,
			String svallflytimemax, Long svcapflytime, Long svflytimeyear2,
			Long svflytimemonth12, Long svflytimemonth6, Long svallupdown,
			Long svupdownmin, Long svupdownmax, Long svupdownmonth12,
			Long svupdownday90, String svatpl, String svmultiplecertificate,
			String svicao, String svprevlevelgrade,
			Long svcurrentflytime, Long svcurrentupdown,String svconditionsname) {
		this.svconditionsid = svconditionsid;
		this.baseAirplantype = baseAirplantype;
		this.tfQualBaseType = tfQualBaseType;
		this.svgrade = svgrade;
		this.svlicensetype = svlicensetype;
		this.svallflytimemin = svallflytimemin;
		this.svallflytimemax = svallflytimemax;
		this.svcapflytime = svcapflytime;
		this.svflytimeyear2 = svflytimeyear2;
		this.svflytimemonth12 = svflytimemonth12;
		this.svflytimemonth6 = svflytimemonth6;
		this.svallupdown = svallupdown;
		this.svupdownmin = svupdownmin;
		this.svupdownmax = svupdownmax;
		this.svupdownmonth12 = svupdownmonth12;
		this.svupdownday90 = svupdownday90;
		this.svatpl = svatpl;
		this.svmultiplecertificate = svmultiplecertificate;
		this.svicao = svicao;
		this.svprevlevelgrade = svprevlevelgrade;
		this.svcurrentflytime = svcurrentflytime;
		this.svcurrentupdown = svcurrentupdown;
		this.svconditionsname = svconditionsname;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SVCONDITIONSID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getSvconditionsid() {
		return this.svconditionsid;
	}

	public void setSvconditionsid(String svconditionsid) {
		this.svconditionsid = svconditionsid;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "SVSTRID", unique = false, nullable = true, insertable = true, updatable = true)
	public BaseAirPlanType getBaseAirplantype() {
		return this.baseAirplantype;
	}

	public void setBaseAirplantype(BaseAirPlanType baseAirplantype) {
		this.baseAirplantype = baseAirplantype;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "SVTYPEID", unique = false, nullable = true, insertable = true, updatable = true)
	public TfQualBaseType getTfQualBaseType() {
		return this.tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "SVGRADE", unique = false, nullable = true, insertable = true, updatable = true)
	public TfQualPilotTechgrade getSvgrade() {
		return this.svgrade;
	}

	public void setSvgrade(TfQualPilotTechgrade svgrade) {
		this.svgrade = svgrade;
	}

	@Column(name = "SVLICENSETYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getSvlicensetype() {
		return this.svlicensetype;
	}

	public void setSvlicensetype(String svlicensetype) {
		this.svlicensetype = svlicensetype;
	}

	@Column(name = "SVALLFLYTIMEMIN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvallflytimemin() {
		return this.svallflytimemin;
	}

	public void setSvallflytimemin(Long svallflytimemin) {
		this.svallflytimemin = svallflytimemin;
	}
	@Column(name = "SVALLFLYTIMEMAX", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getSvallflytimemax() {
		return this.svallflytimemax;
	}

	public void setSvallflytimemax(String svallflytimemax) {
		this.svallflytimemax = svallflytimemax;
	}

	@Column(name = "SVCAPFLYTIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvcapflytime() {
		return this.svcapflytime;
	}

	public void setSvcapflytime(Long svcapflytime) {
		this.svcapflytime = svcapflytime;
	}

	@Column(name = "SVFLYTIMEYEAR2", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvflytimeyear2() {
		return this.svflytimeyear2;
	}

	public void setSvflytimeyear2(Long svflytimeyear2) {
		this.svflytimeyear2 = svflytimeyear2;
	}

	@Column(name = "SVFLYTIMEMONTH12", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvflytimemonth12() {
		return this.svflytimemonth12;
	}

	public void setSvflytimemonth12(Long svflytimemonth12) {
		this.svflytimemonth12 = svflytimemonth12;
	}

	@Column(name = "SVFLYTIMEMONTH6", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvflytimemonth6() {
		return this.svflytimemonth6;
	}

	public void setSvflytimemonth6(Long svflytimemonth6) {
		this.svflytimemonth6 = svflytimemonth6;
	}

	@Column(name = "SVALLUPDOWN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvallupdown() {
		return this.svallupdown;
	}

	public void setSvallupdown(Long svallupdown) {
		this.svallupdown = svallupdown;
	}

	@Column(name = "SVUPDOWNMIN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvupdownmin() {
		return this.svupdownmin;
	}

	public void setSvupdownmin(Long svupdownmin) {
		this.svupdownmin = svupdownmin;
	}

	@Column(name = "SVUPDOWNMAX", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvupdownmax() {
		return this.svupdownmax;
	}

	public void setSvupdownmax(Long svupdownmax) {
		this.svupdownmax = svupdownmax;
	}

	@Column(name = "SVUPDOWNMONTH12", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvupdownmonth12() {
		return this.svupdownmonth12;
	}

	public void setSvupdownmonth12(Long svupdownmonth12) {
		this.svupdownmonth12 = svupdownmonth12;
	}

	@Column(name = "SVUPDOWNDAY90", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvupdownday90() {
		return this.svupdownday90;
	}

	public void setSvupdownday90(Long svupdownday90) {
		this.svupdownday90 = svupdownday90;
	}

	@Column(name = "SVATPL", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getSvatpl() {
		return this.svatpl;
	}

	public void setSvatpl(String svatpl) {
		this.svatpl = svatpl;
	}

	@Column(name = "SVMULTIPLECERTIFICATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getSvmultiplecertificate() {
		return this.svmultiplecertificate;
	}

	public void setSvmultiplecertificate(String svmultiplecertificate) {
		this.svmultiplecertificate = svmultiplecertificate;
	}

	@Column(name = "SVICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getSvicao() {
		return this.svicao;
	}

	public void setSvicao(String svicao) {
		this.svicao = svicao;
	}

	@Column(name = "SVPREVLEVELGRADE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getSvprevlevelgrade() {
		return this.svprevlevelgrade;
	}

	public void setSvprevlevelgrade(String svprevlevelgrade) {
		this.svprevlevelgrade = svprevlevelgrade;
	}

	@Column(name = "SVCURRENTFLYTIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvcurrentflytime() {
		return this.svcurrentflytime;
	}

	public void setSvcurrentflytime(Long svcurrentflytime) {
		this.svcurrentflytime = svcurrentflytime;
	}

	@Column(name = "SVCURRENTUPDOWN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSvcurrentupdown() {
		return this.svcurrentupdown;
	}

	public void setSvcurrentupdown(Long svcurrentupdown) {
		this.svcurrentupdown = svcurrentupdown;
	}

	@Column(name = "SVCONDITIONSNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	public String getSvconditionsname() {
		return svconditionsname;
	}

	public void setSvconditionsname(String svconditionsname) {
		this.svconditionsname = svconditionsname;
	}
	

}