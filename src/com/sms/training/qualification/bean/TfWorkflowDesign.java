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
 * TfWorkflowDesign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_WORKFLOW_DESIGN")
public class TfWorkflowDesign implements java.io.Serializable {

	// Fields
	private String id;
	private String name;
	//流程发起日期
	private Date createDate;
	//发起机构
	private String orgid;
	//升级类型
	private String type;

	// Constructors

	/** default constructor */
	public TfWorkflowDesign() {
	}

	/** minimal constructor */
	public TfWorkflowDesign(String id) {
		this.id = id;
	}

	/** full constructor */
	public TfWorkflowDesign(String id, String name, Date createDate,
			String orgid, String type) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.orgid = orgid;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "ORGID", length = 36)
	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	@Column(name = "TYPE", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}