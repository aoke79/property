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
 * 飞行员签名  entity. 
 * @author licumn
 */
@Entity
@Table(name = "TF_QUAL_PILOT_SIGNATURE")
public class TfQualPilotSignature implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	//id
	private String psignatureId;
	//登陆账号
	private String ploginId;
	//密码
	private String ppassword;
	//签名图片路径
	private byte[] psignatureUrl;
//	private String psignatureUrl;
	//添加时间
	private Date createDt;
	//添加人
	private String creator;
	//修改时间
	private Date modifiedDt;
	//修改人
	private String modifier;
	//账号状态 ,有效/无效（Y/N）
	private String status;
	//姓名
	private String pilotName;

	// Constructors

	/** default constructor */
	public TfQualPilotSignature() {
	}

	/** full constructor */
	public TfQualPilotSignature(String pLoginId, String pPassword,
			byte[] pSignatureUrl, Date createDt, String creator,
			Date modifiedDt, String modifier, String status, String pilotName) {
		this.ploginId = pLoginId;
		this.ppassword = pPassword;
		this.psignatureUrl = pSignatureUrl;
		this.createDt = createDt;
		this.creator = creator;
		this.modifiedDt = modifiedDt;
		this.modifier = modifier;
		this.status = status;
		this.pilotName = pilotName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PSIGNATUREID", unique = true, nullable = false, length = 36)
	public String getPsignatureId() {
		return this.psignatureId;
	}

	public void setPsignatureId(String psignatureid) {
		this.psignatureId = psignatureid;
	}

	@Column(name = "PLOGINID", length = 10)
	public String getPloginId() {
		return this.ploginId;
	}

	public void setPloginId(String pLoginId) {
		this.ploginId = pLoginId;
	}

	@Column(name = "PPASSWORD", length = 16)
	public String getPpassword() {
		return this.ppassword;
	}

	public void setPpassword(String pPassword) {
		this.ppassword = pPassword;
	}

	@Column(name = "PSIGNATUREURL", length = 100)
//	public String getPsignatureUrl() {
//		return this.psignatureUrl;
//	}
//
//	public void setPsignatureUrl(String pSignatureUrl) {
//		this.psignatureUrl = pSignatureUrl;
//	}
	public byte[] getPsignatureUrl() {
		return psignatureUrl;
	}

	public void setPsignatureUrl(byte[] psignatureUrl) {
		this.psignatureUrl = psignatureUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDT", length = 7)
	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Column(name = "CREATOR", length = 30)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIEDDT", length = 7)
	public Date getModifiedDt() {
		return this.modifiedDt;
	}

	public void setModifiedDt(Date modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	@Column(name = "MODIFIER", length = 30)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PILOTNAME", length = 30)
	public String getPilotName() {
		return this.pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

}