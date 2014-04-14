package com.sinoframe.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * RelationAccountRole entity
 * @author niujingwei
 *
 */
@Entity
@Table(name="SYS_RELATION_ACCOUNT_ROLE")
public class SysRelationAccountRole implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2778945165952082375L;
	
	//Fileds
	
	private SysRAccountRoleId accountRoleId;
	//描述
	private String description;
	//标志位
	private String flag;
	
	private SysRole sysRole;
	
	private CmUser cmUser;
	
	public SysRelationAccountRole() {
		
	}
	/**
	 * 联合主键
	 * @return
	 */
	@EmbeddedId
	@AttributeOverrides( {
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)),
		@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)) })
	public SysRAccountRoleId getAccountRoleId() {
		return accountRoleId;
	}
	public void setAccountRoleId(SysRAccountRoleId accountRoleId) {
		this.accountRoleId = accountRoleId;
	}
	

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true)
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
	
	@ManyToOne(fetch = FetchType.EAGER,cascade={})
	@JoinColumn(name="ROLE_ID",insertable=false,updatable=false)
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	@ManyToOne(cascade={}, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", unique = false, nullable = true, insertable = false, updatable = false)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}
	

}
