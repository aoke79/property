/**
 * Title SysUserCustomMenu
 * Description user custom menu bean entity for table "sys_user_custom_menu"
 */
package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_user_custom_menu")
public class SysUserCustomMenu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	//properties definition
	/** 自定义菜单编号 */
	private String id;
	/** 菜单编号 */
	private SysMenu sysMenu;  
	/** 用户编号 */
	private CmUser cmUser;   
	/** 状态 */
	private String state; 

	//constructors
	public SysUserCustomMenu(){	
	}
	
	public SysUserCustomMenu(String id, SysMenu sysMenu, CmUser cmUser, 
			String state) {
		this.id = id;
		this.sysMenu = sysMenu;
		this.cmUser = cmUser;
		this.state = state;
	}
	
	//Properties accessories
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public SysMenu getSysMenu() {
		return this.sysMenu;
	}
	
	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

}
