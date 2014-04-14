package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualInspectionTrain entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_INSPECTION_TRAIN")
public class TfQualInspectionTrain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
    // 训练记录ID
	private String trainid;
	//申报人员明细ID
	private String detailsId;
	//日期
	private Date baseDataId;
	//课日课时
	private String mark;
	//地点
	private String remarks;
	//成绩或结论
	private String score;
	//经办人
	private String personHanding;
	//最后修改人
	private String modifier;

	// Constructors

	/** default constructor */
	public TfQualInspectionTrain() {
	}

	/** full constructor */
	public TfQualInspectionTrain(String detailsId, Date baseDataId,
			String mark, String remarks, String score, String personHanding,String modifier) {
		this.detailsId = detailsId;
		this.baseDataId = baseDataId;
		this.mark = mark;
		this.remarks = remarks;
		this.score = score;
		this.personHanding = personHanding;
		this.modifier = modifier;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "TRAINID", unique = true, nullable = false, length = 36)
	public String getTrainid() {
		return this.trainid;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	@Column(name = "DETAILSIDID", length = 36)
	public String getDetailsId() {
		return this.detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BASEDATAID", length = 7)
	public Date getBaseDataId() {
		return this.baseDataId;
	}

	public void setBaseDataId(Date baseDataId) {
		this.baseDataId = baseDataId;
	}

	@Column(name = "MARK", length = 50)
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Column(name = "REMARKS", length = 60)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "SCORE", length = 50)
	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "PERSONHANDLING", length = 36)
	public String getPersonHanding() {
		return this.personHanding;
	}

	public void setPersonHanding(String personHanding) {
		this.personHanding = personHanding;
	}

	@Column(name = "MODIFIER", length = 36)
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}