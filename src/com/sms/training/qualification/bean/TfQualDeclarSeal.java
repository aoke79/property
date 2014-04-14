package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sinoframe.bean.SysOrganization;

@Entity
@Table(name = "TF_QUAL_DECLAR_SEAL")
public class TfQualDeclarSeal implements java.io.Serializable{

	private static final long serialVersionUID = 6130363243517603763L;
	private String id;
	/**
	 * 关联组织结构实体类
	 * 和组织机构是一对一的关系 
	 */
	private SysOrganization  organization; 
	/**
	 * 印章图片字段
	 */
	private byte[] seal;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "SEAL")
	public byte[] getSeal() {
		return seal;
	}

	public void setSeal(byte[] seal) {
		this.seal = seal;
	}

	@ManyToOne
	@JoinColumn(name="ORGID",unique=true)
	public SysOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(SysOrganization organization) {
		this.organization = organization;
	}	
}
