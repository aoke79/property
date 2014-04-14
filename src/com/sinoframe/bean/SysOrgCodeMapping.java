package com.sinoframe.bean;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_org_code_mapping")
public class SysOrgCodeMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	// 联合主键
	private SysOrgCodeMappingId id;
	// 机构对象
	private SysOrganization sysOrganization;
	// 外部系统机构编号
	private String outerOrgId;
	// 系统名称
	private String systemName;
	
	/** default constructor */
	public SysOrgCodeMapping() {
	}

	/** minimal constructor */
	public SysOrgCodeMapping(SysOrgCodeMappingId id,
			SysOrganization sysOrganization, String outerOrgId) {
		this.id = id;
		this.sysOrganization = sysOrganization;
		this.outerOrgId = outerOrgId;
	}

	/** full constructor */
	public SysOrgCodeMapping(SysOrgCodeMappingId id,
			SysOrganization sysOrganization, String outerOrgId,
			String systemName) {
		this.id = id;
		this.sysOrganization = sysOrganization;
		this.outerOrgId = outerOrgId;
		this.systemName = systemName;
	}

	@Column(name = "OUTER_ORG_ID", length = 36)
	public String getOuterOrgId() {
		return this.outerOrgId;
	}

	public void setOuterOrgId(String outerOrgId) {
		this.outerOrgId = outerOrgId;
	}

	@Column(name = "SYSTEM_NAME", length = 20)
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "strOrg_Id", column = @Column(name = "ORG_ID", nullable = false, length = 36)),
			@AttributeOverride(name = "strSystem_Id", column = @Column(name = "SYSTEM_ID", nullable = false, length = 36)) })
	public SysOrgCodeMappingId getId() {
		return id;
	}

	public void setId(SysOrgCodeMappingId id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "ORG_ID", nullable = false, insertable = false, updatable = false)
	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

}
