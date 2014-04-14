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
 * 技术检查报告成绩   entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_INSPECTION_REPORT_D")
public class TfQualInspectionReportD implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	//检查报告成绩ID
	private String resultid;
	//检查报告
	private TfQualInspectionReport tfQualInspectionReport;
	//检查报告基础信息ID
	private String basedataid;
	//评分
//	private byte mark;
	private String mark;
	//评注
	private String remarks;

	// Constructors

	/** default constructor */
	public TfQualInspectionReportD() {
	}

	/** full constructor */
	public TfQualInspectionReportD(
			TfQualInspectionReport tfQualInspectionReport, String basedataid,
			String mark, String remarks) {
		this.tfQualInspectionReport = tfQualInspectionReport;
		this.basedataid = basedataid;
		this.mark = mark;
		this.remarks = remarks;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "RESULTID", unique = true, nullable = false, length = 36)
	public String getResultid() {
		return this.resultid;
	}

	public void setResultid(String resultid) {
		this.resultid = resultid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORTID")
	public TfQualInspectionReport getTfQualInspectionReport() {
		return this.tfQualInspectionReport;
	}

	public void setTfQualInspectionReport(
			TfQualInspectionReport tfQualInspectionReport) {
		this.tfQualInspectionReport = tfQualInspectionReport;
	}

	@Column(name = "BASEDATAID", length = 36)
	public String getBasedataid() {
		return this.basedataid;
	}

	public void setBasedataid(String basedataid) {
		this.basedataid = basedataid;
	}

	@Column(name = "MARK", precision = 2, scale = 0)
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Column(name = "REMARKS", length = 50)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}