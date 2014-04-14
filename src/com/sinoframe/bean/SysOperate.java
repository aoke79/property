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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * SysOperate
 * @描述  表 "SYS_OPERATE" 的实体类
 * @作者 胡星
 * @版本 1.0
 */
@Entity
@Table(name = "SYS_OPERATE")
public class SysOperate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1940841814595537453L;
	// Fields
	/** 操作ID */ 
	private String id;
	/** 操作名称 */
	private String operateName;
	/** 触发的URL串 */
	private String operateAction;
	/** 导航信息 */
	private String menuInfo;
	/** 是否是底层菜单 */
	private String ifFinallyMenu;
	/** 菜单父编号 */
	private String parentId;
	/** 描述 */
	private String description;
	/** 标志位 */
	private String flag;
	/** 备用一 */
	private String standbyOne;
	/** 备用二 */
	private String standbyTwo;
	/** 备用三 */
	private String standbyThree;
	/** 等级 */
	private String opeLevel;
	/** 子系统 ID */
	private String systemId;
	// 系统编号
	// private SysSystem sysSystem = new SysSystem();
	/** 目录 */
	private SysMenu sysMenu = new SysMenu();
	/** 操作关联操作组关系的 Set 集合 */
	private Set<SysOperateGroupRelation> sysOperateGroupRelations = new HashSet<SysOperateGroupRelation>(
			0);
	/** 操作关联角色操作关系的 Set 集合 */
	private Set<SysRelationRoleOperate> sysRelationRoleOperates = new HashSet<SysRelationRoleOperate>(
			0);
	/** 操作关联机构权限的 Set 集合 */
	private Set<SysPermission> sysPermissions = new HashSet<SysPermission>(0);
	/** 操作关联用户操作关系的 Set 集合 */
	private Set<CmUserOperateRelation> cmUserOperateRelations = new HashSet<CmUserOperateRelation>(
			0);
	/** 上级操作 */
	private SysOperate parentOperate;
	/** 子操作的 Set 集合 */
	private Set<SysOperate> subOperate = new HashSet<SysOperate>();
	//子系统标识
	private String subsystemId;

	// Constructors

	/** default constructor */
	public SysOperate() {
	}

	/** minimal constructor */
	public SysOperate(String id, String ifFinallyMenu, String parentId) {
		this.id = id;
		this.ifFinallyMenu = ifFinallyMenu;
		this.parentId = parentId;
	}

	/** full constructor */
	public SysOperate(String id, String operateName, String operateAction,
			String menuInfo, String ifFinallyMenu, String parentId,
			String description, String flag, String standbyOne,
			String standbyTwo, String standbyThree, String opeLevel,
			String sysSystemId, SysMenu sysMenu,
			Set<SysOperateGroupRelation> sysOperateGroupRelations,
			Set<SysRelationRoleOperate> sysRelationRoleOperates,
			Set<SysPermission> sysPermissions,
			Set<CmUserOperateRelation> cmUserOperateRelations,String subsystemId) {
		this.id = id;
		this.operateName = operateName;
		this.operateAction = operateAction;
		this.menuInfo = menuInfo;
		this.ifFinallyMenu = ifFinallyMenu;
		this.parentId = parentId;
		this.description = description;
		this.flag = flag;
		this.standbyOne = standbyOne;
		this.standbyTwo = standbyTwo;
		this.standbyThree = standbyThree;
		this.opeLevel = opeLevel;
		this.sysOperateGroupRelations = sysOperateGroupRelations;
		this.sysRelationRoleOperates = sysRelationRoleOperates;
		this.sysPermissions = sysPermissions;
		this.cmUserOperateRelations = cmUserOperateRelations;
		/* this.sysSystem = sysSystem; */
		this.systemId = systemId;
		this.sysMenu = sysMenu;
		this.subsystemId = subsystemId;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
    @Column(name="SUBSYSTEM_ID",length=360)
	public String getSubsystemId() {
		return subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
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

	@Column(name = "IF_FINALLY_MENU", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	public String getIfFinallyMenu() {
		return this.ifFinallyMenu;
	}

	public void setIfFinallyMenu(String ifFinallyMenu) {
		this.ifFinallyMenu = ifFinallyMenu;
	}

	@Column(name = "PARENT_ID", unique = false, nullable = false, insertable = false, updatable = false, length = 10)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	@Column(name = "OPE_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getOpeLevel() {
		return this.opeLevel;
	}

	public void setOpeLevel(String opeLevel) {
		this.opeLevel = opeLevel;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sysOperate")
	public Set<SysOperateGroupRelation> getSysOperateGroupRelations() {
		return this.sysOperateGroupRelations;
	}

	public void setSysOperateGroupRelations(
			Set<SysOperateGroupRelation> sysOperateGroupRelations) {
		this.sysOperateGroupRelations = sysOperateGroupRelations;
	}

	@OneToMany(cascade = { }, fetch = FetchType.LAZY, mappedBy = "sysOperate")
	public Set<SysRelationRoleOperate> getSysRelationRoleOperates() {
		return this.sysRelationRoleOperates;
	}

	public void setSysRelationRoleOperates(
			Set<SysRelationRoleOperate> sysRelationRoleOperates) {
		this.sysRelationRoleOperates = sysRelationRoleOperates;
	}

	@OneToMany(cascade = {  }, fetch = FetchType.LAZY, mappedBy = "sysOperate")
	public Set<SysPermission> getSysPermissions() {
		return this.sysPermissions;
	}

	public void setSysPermissions(Set<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "sysOperate")
	public Set<CmUserOperateRelation> getCmUserOperateRelations() {
		return this.cmUserOperateRelations;
	}

	public void setCmUserOperateRelations(
			Set<CmUserOperateRelation> cmUserOperateRelations) {
		this.cmUserOperateRelations = cmUserOperateRelations;
	}

	/*
	 * @ManyToOne(cascade = {}, fetch = FetchType.LAZY) @JoinColumn(name =
	 * "SYSTEM_ID", unique = false, nullable = true, insertable = true,
	 * updatable = true) public SysSystem getSysSystem() { return sysSystem; }
	 * 
	 * public void setSysSystem(SysSystem sysSystem) { this.sysSystem =
	 * sysSystem; }
	 */
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	@Column(name = "SYSTEM_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@ManyToOne(cascade = {})
	@JoinColumn(name = "PARENT_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public SysOperate getParentOperate() {
		return parentOperate;
	}

	public void setParentOperate(SysOperate parentOperate) {
		this.parentOperate = parentOperate;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "parentOperate")
	@OrderBy("id asc")
	public Set<SysOperate> getSubOperate() {
		return subOperate;
	}

	public void setSubOperate(Set<SysOperate> subOperate) {
		this.subOperate = subOperate;
	}
}