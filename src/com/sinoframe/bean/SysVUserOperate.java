package com.sinoframe.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * VUserOperate entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "V_USER_OPERATE")
public class SysVUserOperate implements java.io.Serializable {

	// Fields

	private SysVUserOperateId fid;
	private String id;
	private String systemId;
	private String operateName;
	private String operateAction;
	private String menuInfo;
	private String ifFinallyMenu;
	private String parentId;
	private String menuId;
	private String opeLevel;
	private String description;
	private String flag;
	private String standbyOne;
	private String standbyTwo;
	private String standbyThree;
	//子系统标示
	private String subsystemId;

	// Constructors

	@Column(name="SUBSYSTEM_ID")
	public String getSubsystemId() {
		return subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	/** default constructor */
	public SysVUserOperate() {
	}

	/** full constructor */
	public SysVUserOperate(SysVUserOperateId fid) {
		this.fid = fid;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)),
			@AttributeOverride(name = "id", column = @Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36))
			//,
		//	@AttributeOverride(name = "systemId", column = @Column(name = "SYSTEM_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)),
		//	@AttributeOverride(name = "operateName", column = @Column(name = "OPERATE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)),
		//	@AttributeOverride(name = "operateAction", column = @Column(name = "OPERATE_ACTION", unique = false, nullable = true, insertable = true, updatable = true)),
		//	@AttributeOverride(name = "menuInfo", column = @Column(name = "MENU_INFO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)),
		//	@AttributeOverride(name = "ifFinallyMenu", column = @Column(name = "IF_FINALLY_MENU", unique = false, nullable = true, insertable = true, updatable = true, length = 1)),
		//	@AttributeOverride(name = "parentId", column = @Column(name = "PARENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)),
		//	@AttributeOverride(name = "menuId", column = @Column(name = "MENU_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)),
		//	@AttributeOverride(name = "opeLevel", column = @Column(name = "OPE_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 1)),
		//	@AttributeOverride(name = "description", column = @Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true)),
		//	@AttributeOverride(name = "flag", column = @Column(name = "FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 2)),
		//	@AttributeOverride(name = "standbyOne", column = @Column(name = "STANDBY_ONE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)),
		//	@AttributeOverride(name = "standbyTwo", column = @Column(name = "STANDBY_TWO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)),
		//	@AttributeOverride(name = "standbyThree", column = @Column(name = "STANDBY_THREE", unique = false, nullable = true, insertable = true, updatable = true, length = 200))
			})
	public SysVUserOperateId getFid() {
		return this.fid;
	}

	public void setFid(SysVUserOperateId fid) {
		this.fid = fid;
		this.id = fid.getId();
	}
	@Transient
	public String getId() {
		return this.fid.getId();
	}
	
	/*public void setId(String id) {
		this.id = id;
	}*/
	
	@Column(name = "SYSTEM_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getSystemId() {
		return this.systemId;
	}
	
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@Column(name = "OPERATE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Column(name = "OPERATE_ACTION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getOperateAction() {
		return this.operateAction;
	}

	public void setOperateAction(String operateAction) {
		this.operateAction = operateAction;
	}

	@Column(name = "MENU_INFO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMenuInfo() {
		return this.menuInfo;
	}

	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}

	@Column(name = "IF_FINALLY_MENU", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getIfFinallyMenu() {
		return this.ifFinallyMenu;
	}

	public void setIfFinallyMenu(String ifFinallyMenu) {
		this.ifFinallyMenu = ifFinallyMenu;
	}

	@Column(name = "PARENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "MENU_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "OPE_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getOpeLevel() {
		return this.opeLevel;
	}

	public void setOpeLevel(String opeLevel) {
		this.opeLevel = opeLevel;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "STANDBY_ONE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getStandbyOne() {
		return this.standbyOne;
	}

	public void setStandbyOne(String standbyOne) {
		this.standbyOne = standbyOne;
	}

	@Column(name = "STANDBY_TWO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getStandbyTwo() {
		return this.standbyTwo;
	}

	public void setStandbyTwo(String standbyTwo) {
		this.standbyTwo = standbyTwo;
	}

	@Column(name = "STANDBY_THREE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getStandbyThree() {
		return this.standbyThree;
	}

	public void setStandbyThree(String standbyThree) {
		this.standbyThree = standbyThree;
	}
	

}