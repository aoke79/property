package com.sms.training.qualification.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "AH_RBAC")
public class AhRbac implements java.io.Serializable {

	private String rbacuuid;
	private String loginname;
	private String rolename;
	private String username;
	private String sysname;
	private String operationame;
	
	
	public AhRbac() {
		// TODO Auto-generated constructor stub
	}
	
	/** minimal constructor */
	public AhRbac(String rbacuuid) {
		this.rbacuuid = rbacuuid;
	}
	
	public AhRbac(String rbacuuid,String loginname, String rolename, String username,
			String sysname, String operationame) {
		this.rbacuuid = rbacuuid;
		this.loginname = loginname;
		this.operationame = operationame;
		this.rolename = rolename;
		this.username = username;
		this.sysname = sysname;
	}
	
	
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "RBACUUID", unique = true, nullable = false, length = 36)
	public String getRbacuuid() {
		return rbacuuid;
	}
	public void setRbacuuid(String rbacuuid) {
		this.rbacuuid = rbacuuid;
	}
	
	@Column(name = "LOGINNAME", length = 36)
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	@Column(name = "ROLENAME", length = 36)
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Column(name = "USERNAME", length = 36)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "SYSNAME", length = 36)
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	
	@Column(name = "OPERATIONAME", length = 36)
	public String getOperationame() {
		return operationame;
	}
	public void setOperationame(String operationame) {
		this.operationame = operationame;
	}
	
	
}
