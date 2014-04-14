package com.sinoframe.bean;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

//import com.sms.security.basicdata.bean.SecQaruser;
//import com.sms.security.riskManage.bean.SecRmcircuit;


@Entity
@Table(name = "sys_organization")
public class SysOrganization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 机构编号
	private String id;
	// 机构名称
	private String name;
	// 机构类型
	private String type;
	// 机构描述
	private String description;
	// 状态  1 有效
	private String state;

	// 上级行政机构
	private SysOrganization parent_Adm;

	// 上级职能机构
	private SysOrganization parent_Fun;

	// 上级监督机构
	private SysOrganization parent_Supervise;

	// 行政子机构
	private Set<SysOrganization> setChild_Adm = new HashSet<SysOrganization>();

	// 职能子机构
	private Set<SysOrganization> setChild_Fun = new HashSet<SysOrganization>();

	// 监督子机构
	private Set<SysOrganization> setChild_Supervise = new HashSet<SysOrganization>();

	// 外部系统编号映射对象
	private Set<SysOrgCodeMapping> setSysOrgCodeMapping = new HashSet<SysOrgCodeMapping>();
	
	// 用户机构对照表
	private Set<SysUserOrgRelation> sysUserOrgRelations = new HashSet<SysUserOrgRelation>(0);
	
	private Set<CmUser> setCmUser = new HashSet<CmUser>();
	private Set<CmPeople> cmPeoples = new HashSet<CmPeople>(0);
	/*private Set<SysPermission> sysPermissions = new HashSet<SysPermission>(0);*/
	//流程历史
	//private Set<Processhistory> processhistories = new HashSet<Processhistory>(0);
	// 机构级别 1.国航股份    2.一级单位    3.二级单位    4.三级单位
	private String orgLevel;
	// 机构类型（1.单位   2.部门）
	private Character orgType;
	// 机构排序字段
	private Integer orgOrder;
	// 机构属性（1-国航；2-外航）
	private String deptType;
	
	//关联QAR用户表
	/*private SecQaruser secQaruser;*/
	// 机构文号
	private String orgNo;
	// 二级流程的集合
//	private Set<SecRmcircuit> secRmcircuits;
	
	@Id
	@Column(name = "ID", length = 36)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 200, nullable = true)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", length = 1, nullable = true)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "DESCRIPTION", length = 200, nullable = true)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATE", length = 1, nullable = true)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@OneToMany(mappedBy = "parent_Adm")
	public Set<SysOrganization> getSetChild_Adm() {
		return setChild_Adm;
	}

	public void setSetChild_Adm(Set<SysOrganization> setChild_Adm) {
		this.setChild_Adm = setChild_Adm;
	}

	@OneToMany(mappedBy = "parent_Fun")
	@OrderBy("orgType desc ,orgOrder,name ")
	public Set<SysOrganization> getSetChild_Fun() {
		return setChild_Fun;
	}

	public void setSetChild_Fun(Set<SysOrganization> setChild_Fun) {
		this.setChild_Fun = setChild_Fun;
	}

	@OneToMany(mappedBy = "parent_Supervise")
	public Set<SysOrganization> getSetChild_Supervise() {
		return setChild_Supervise;
	}
	public void setSetChild_Supervise(Set<SysOrganization> setChild_Supervise) {
		this.setChild_Supervise = setChild_Supervise;
	}

	@OneToMany(mappedBy = "sysOrganization")
	public Set<SysOrgCodeMapping> getSetSysOrgCodeMapping() {
		return setSysOrgCodeMapping;
	}

	public void setSetSysOrgCodeMapping(
			Set<SysOrgCodeMapping> setSysOrgCodeMapping) {
		this.setSysOrgCodeMapping = setSysOrgCodeMapping;
	}
	
	@OneToMany(cascade = { }, fetch = FetchType.LAZY, mappedBy = "sysOrganization")
	public Set<CmPeople> getCmPeoples() {
		return this.cmPeoples;
	}

	public void setCmPeoples(Set<CmPeople> cmPeoples) {
		this.cmPeoples = cmPeoples;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ADM_ID" , nullable=true)
	public SysOrganization getParent_Adm() {
		return parent_Adm;
	}

	public void setParent_Adm(SysOrganization parent_Adm) {
		this.parent_Adm = parent_Adm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_FUN_ID", nullable=true)
	public SysOrganization getParent_Fun() {
		return parent_Fun;
	}

	public void setParent_Fun(SysOrganization parent_Fun) {
		this.parent_Fun = parent_Fun;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_SUPERVISE_ID" , nullable=true)
	public SysOrganization getParent_Supervise() {
		return parent_Supervise;
	}

	public void setParent_Supervise(SysOrganization parent_Supervise) {
		this.parent_Supervise = parent_Supervise;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final SysOrganization other = (SysOrganization) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sysOrganizationByParentFunId")
	public Set<CmUser> getSetCmUser() {
		return setCmUser;
	}

	public void setSetCmUser(Set<CmUser> setCmUser) {
		this.setCmUser = setCmUser;
	}

	@Column(name="ORG_LEVEL")
	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	@Column(name="ORG_TYPE")
	public Character getOrgType() {
		return orgType;
	}

	public void setOrgType(Character orgType) {
		this.orgType = orgType;
	}

	@Column(name="ORG_ORDER")
	@org.hibernate.annotations.OrderBy(clause="orgOrder asc")
	public Integer getOrgOrder() {
		return orgOrder;
	}

	public void setOrgOrder(Integer orgOrder) {
		this.orgOrder = orgOrder;
	}

	@Column(name="DEPT_TYPE")
	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysOrganization")
	public final Set<SysUserOrgRelation> getSysUserOrgRelations() {
		return sysUserOrgRelations;
	}

	public final void setSysUserOrgRelations(
			Set<SysUserOrgRelation> sysUserOrgRelations) {
		this.sysUserOrgRelations = sysUserOrgRelations;
	}
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "sysOrganization")
	@OrderBy("id asc")
	public Set<SysPermission> getSysPermissions() {
		return sysPermissions;
	}

	public void setSysPermissions(Set<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}*/

	/*@OneToOne(mappedBy="sysOrganization", fetch=FetchType.LAZY)
	public SecQaruser getSecQaruser() {
		return secQaruser;
	}

	public void setSecQaruser(SecQaruser secQaruser) {
		this.secQaruser = secQaruser;
	}*/

	@Transient
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

//	@ManyToMany
//	@JoinTable(name="SEC_ORG_CIRCUIT_RELATION", joinColumns=@JoinColumn(name="DEPTID"), inverseJoinColumns=@JoinColumn(name="CIRCUITID"))
//	public Set<SecRmcircuit> getSecRmcircuits() {
//		return secRmcircuits;
//	}
//
//	public void setSecRmcircuits(Set<SecRmcircuit> secRmcircuits) {
//		this.secRmcircuits = secRmcircuits;
//	}
	
	/**
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sysOrganization")
	public Set<Processhistory> getProcesshistories() {
		return this.processhistories;
	}

	public void setProcesshistories(Set<Processhistory> processhistories) {
		this.processhistories = processhistories;
	}
	*/
}
