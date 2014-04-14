package com.sinoframe.bean;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Role entity
 * @author niujingwei
 */
@Entity
@Table(name="SYS_ROLE")
public class SysRole implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5824653422198238608L;
	
	// Fields
	//角色ID
	private String roleId;
	//角色名称
	private String roleName;
	//角色名称
	private String eroleName;
	//角色描述
	private String edescription;
	//角色描述
	private String description;
	//标志位
	private String flag;
	//子系统标识
	private String subsystemId;

	
	private Set<SysRelationAccountRole> sysRelationAccountRoles=new HashSet<SysRelationAccountRole>();
	private Set<SysRelationRoleOperate> sysRelationRoleOperates = new HashSet<SysRelationRoleOperate>();
	//private Set<SysRoleUsergroupRelation> sysRoleUsergroupRelations = new HashSet<SysRoleUsergroupRelation>();
	private Set<SysRoleOperategroupRelation> sysRoleOperategroupRelations = new HashSet<SysRoleOperategroupRelation>();
	private Set<SysUserGroup> sysUserGroups = new HashSet<SysUserGroup>(); 
	
	public SysRole() {
	}
	
	/** full constructor */
	public SysRole(String id, String roleName, String ERoleName,
			String EDescription, String description, String flag,
			Set<SysRelationRoleOperate> sysRelationRoleOperates,
			Set<SysRelationAccountRole> sysRelationAccountRoles,
			Set<SysUserGroup> sysUserGroups,
			//Set<SysRoleUsergroupRelation> sysRoleUsergroupRelations,
			Set<SysRoleOperategroupRelation> sysRoleOperategroupRelations,String subsystemId) {
		this.roleId = id;
		this.roleName = roleName;
		this.eroleName = ERoleName;
		this.edescription = EDescription;
		this.description = description;
		this.flag = flag;
		this.sysRelationRoleOperates = sysRelationRoleOperates;
		this.sysRelationAccountRoles = sysRelationAccountRoles;
		//this.sysRoleUsergroupRelations = sysRoleUsergroupRelations;
		this.sysUserGroups=sysUserGroups;
		this.sysRoleOperategroupRelations = sysRoleOperategroupRelations;
		this.subsystemId = subsystemId;
	}
	

	
	
	@Id
	@GeneratedValue(generator="role-uuid")
	@GenericGenerator(name="role-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 3)
    public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    @Column(name="SUBSYSTEM_ID",length=360)
	public String getSubsystemId() {
		return subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}
	@Column(name = "ROLE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "E_ROLE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getEroleName() {
		return eroleName;
	}

	public void setEroleName(String roleName) {
		eroleName = roleName;
	}
	@Column(name = "E_DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getEdescription() {
		return edescription;
	}

	public void setEdescription(String description) {
		edescription = description;
	}
	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@OneToMany(cascade = {},fetch=FetchType.EAGER,mappedBy="sysRole")
	@NotFound(action=NotFoundAction.IGNORE) 
	public Set<SysRelationAccountRole> getSysRelationAccountRoles() {
		return sysRelationAccountRoles;
	}


	public void setSysRelationAccountRoles(
			Set<SysRelationAccountRole> sysRelationAccountRoles) {
		this.sysRelationAccountRoles = sysRelationAccountRoles;
	}
	@OneToMany(cascade = {}, fetch = FetchType.EAGER, mappedBy = "sysRole")
	public Set<SysRelationRoleOperate> getSysRelationRoleOperates() {
		return sysRelationRoleOperates;
	}


	public void setSysRelationRoleOperates(
			Set<SysRelationRoleOperate> sysRelationRoleOperates) {
		this.sysRelationRoleOperates = sysRelationRoleOperates;
	}

	@ManyToMany(mappedBy = "setSysRole")
	public Set<SysUserGroup> getSysUserGroups() {
		return sysUserGroups;
	}

	public void setSysUserGroups(Set<SysUserGroup> sysUserGroups) {
		this.sysUserGroups = sysUserGroups;
	}

//	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sysRole")
//	public Set<SysRoleUsergroupRelation> getSysRoleUsergroupRelations() {
//		return this.sysRoleUsergroupRelations;
//	}
//
//	public void setSysRoleUsergroupRelations(
//			Set<SysRoleUsergroupRelation> sysRoleUsergroupRelations) {
//		this.sysRoleUsergroupRelations = sysRoleUsergroupRelations;
//	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRoleOperategroupRelation> getSysRoleOperategroupRelations() {
		return this.sysRoleOperategroupRelations;
	}

	public void setSysRoleOperategroupRelations(
			Set<SysRoleOperategroupRelation> sysRoleOperategroupRelations) {
		this.sysRoleOperategroupRelations = sysRoleOperategroupRelations;
	}
	
	
	
	
	
	

}
