package com.sinoframe.bean;
// default package


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
 * Processhistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROCESSHISTORY")
public class Processhistory implements java.io.Serializable {

	// Fields
	private String id;
	private SysOrganization sysOrganization;
	private CmUser cmUser;
	private Long taskid;
	//流程实例ID
	private String pid;
	//驳回理由
	private String reason;
	//private String taskName;
	

	// Constructors

	/** default constructor */
	public Processhistory() {
	}

	/** minimal constructor */
	public Processhistory(String id) {
		this.id = id;
	}

	/** full constructor */
	public Processhistory(String id, SysOrganization sysOrganization,
			CmUser cmUser, Long taskid) {
		this.id = id;
		this.sysOrganization = sysOrganization;
		this.cmUser = cmUser;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGID", unique = false, nullable = true, insertable = true, updatable = true)
	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", unique = false, nullable = true, insertable = true, updatable = true)
	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	@Column(name = "PID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	@Column(name = "TASKID", unique = false, nullable = true, insertable = true, updatable = true, scale = 0)
	public Long getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	@Column(name = "REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
//	@Column(name = "TASKNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
//	public String getTaskName() {
//		return taskName;
//	}
//	public void setTaskName(String taskName) {
//		this.taskName = taskName;
//	}
    
}