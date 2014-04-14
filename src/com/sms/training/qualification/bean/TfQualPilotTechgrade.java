package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TfQualPilotTechgrade 主操作技术标准. 
 * 按ptGradeLevel 顺序排序
 * @author chenleilei 
 */
@Entity
@Table(name = "TF_QUAL_PILOT_TECHGRADE")
public class TfQualPilotTechgrade implements java.io.Serializable {

	// Fields
	private String ptGradeId;	// 飞行员技术标准代码
	private String rnkgrpId;	//	技术标准分组代码
	private String ptGradeDesc;	//	飞行员技术标准描述
	private String ptGradePrior;//	前一等级
	private String ptGradeNext;	//	后一等级
	private Byte ptGradeLevel;	//	技术标准级别	
	private String creator;		//	创建人
	private Date createdt;		//	创建时间
	private String modifier;	//	最后修改人
	private Date modifydt;		//	最后修改时间
	private String itypeId;		//
	private String status;		//
	private Long ob ;
	// Constructors

	/** default constructor */
	public TfQualPilotTechgrade() {
	}

	/** minimal constructor */
	public TfQualPilotTechgrade(String ptGradeId) {
		this.ptGradeId = ptGradeId;
	}

	/** full constructor */
	public TfQualPilotTechgrade(String ptGradeId, String rnkgrpId,
			String ptGradeDesc, String ptGradePrior, String ptGradeNext,
			Byte ptGradeLevel, String creator, Date createdt, String modifier,
			Date modifydt, String itypeId,String status) {
		this.ptGradeId = ptGradeId;
		this.rnkgrpId = rnkgrpId;
		this.ptGradeDesc = ptGradeDesc;
		this.ptGradePrior = ptGradePrior;
		this.ptGradeNext = ptGradeNext;
		this.ptGradeLevel = ptGradeLevel;
		this.creator = creator;
		this.createdt = createdt;
		this.modifier = modifier;
		this.modifydt = modifydt;
		this.itypeId = itypeId;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "PTGRADEID", unique = true, nullable = false, length = 6)
	public String getPtGradeId() {
		return this.ptGradeId;
	}

	public void setPtGradeId(String ptGradeId) {
		this.ptGradeId = ptGradeId;
	}

	@Column(name = "RNKGRPID", length = 4)
	public String getRnkgrpId() {
		return this.rnkgrpId;
	}

	public void setRnkgrpId(String rnkgrpId) {
		this.rnkgrpId = rnkgrpId;
	}

	@Column(name = "PTGRADEDESC", length = 90)
	public String getPtGradeDesc() {
		return this.ptGradeDesc;
	}

	public void setPtGradeDesc(String ptGradeDesc) {
		this.ptGradeDesc = ptGradeDesc;
	}

	@Column(name = "PTGRADEPRIOR", length = 6)
	public String getPtGradePrior() {
		return this.ptGradePrior;
	}

	public void setPtGradePrior(String ptGradePrior) {
		this.ptGradePrior = ptGradePrior;
	}

	@Column(name = "PTGRADENEXT", length = 6)
	public String getPtGradeNext() {
		return this.ptGradeNext;
	}

	public void setPtGradeNext(String ptGradeNext) {
		this.ptGradeNext = ptGradeNext;
	}

	@Column(name = "PTGRADELEVEL", precision = 2, scale = 0)
	public Byte getPtGradeLevel() {
		return this.ptGradeLevel;
	}

	public void setPtGradeLevel(Byte ptGradeLevel) {
		this.ptGradeLevel = ptGradeLevel;
	}

	@Column(name = "CREATOR", length = 16)
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

	@Column(name = "MODIFIER", length = 16)
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

	@Column(name = "ITYPEID", length = 16)
	public String getItypeId() {
		return this.itypeId;
	}

	public void setItypeId(String itypeId) {
		this.itypeId = itypeId;
	}
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OB", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getOb() {
		return ob;
	}

	public void setOb(Long ob) {
		this.ob = ob;
	}
	

}