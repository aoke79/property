package com.sinoframe.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysUserGroup entity.
 * 用户组表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_GROUP")
public class SysUserGroup implements java.io.Serializable {

	private String id;
	//组名称
	private String GName;
	//组描述
	private String GDesc;
	//是否有效
	private String state;
	
	private Set<CmUser> setCmUser = new HashSet<CmUser>(); 
	
	private Set<SysRole> setSysRole = new HashSet<SysRole>();

	// Constructors

	/** default constructor */
	public SysUserGroup() {
	}

	/** minimal constructor */
	public SysUserGroup(String id) {
		this.id = id;
	}

	public SysUserGroup(String id, String gName, String gDesc, String state,
			Set<CmUser> setCmUser) {
		super();
		this.id = id;
		GName = gName;
		GDesc = gDesc;
		this.state = state;
		this.setCmUser = setCmUser;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "G_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getGName() {
		return this.GName;
	}

	public void setGName(String GName) {
		this.GName = GName;
	}

	@Column(name = "G_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 800)
	public String getGDesc() {
		return this.GDesc;
	}

	public void setGDesc(String GDesc) {
		this.GDesc = GDesc;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

/*	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sysUserGroup")
	public Set<SysRoleUsergroupRelation> getSysRoleUsergroupRelations() {
		return this.sysRoleUsergroupRelations;
	}

	public void setSysRoleUsergroupRelations(
			Set<SysRoleUsergroupRelation> sysRoleUsergroupRelations) {
		this.sysRoleUsergroupRelations = sysRoleUsergroupRelations;
	}*/

	@ManyToMany
	@JoinTable(name = "sys_user_group_relation",
			joinColumns = @JoinColumn(name="group_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
	)
	public Set<CmUser> getSetCmUser() {
		return setCmUser;
	}

	public void setSetCmUser(Set<CmUser> setCmUser) {
		this.setCmUser = setCmUser;
	}

	@ManyToMany
	@JoinTable(name="sys_role_usergroup_relation",
			joinColumns=@JoinColumn(name="GROUP_ID"),
			inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	public Set<SysRole> getSetSysRole() {
		return setSysRole;
	}

	public void setSetSysRole(Set<SysRole> setSysRole) {
		this.setSysRole = setSysRole;
	}

}