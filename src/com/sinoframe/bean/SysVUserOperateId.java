package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VUserOperateId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysVUserOperateId implements java.io.Serializable {

	// Fields

	private String userId;
	private String id;
	/*
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
	private String standbyThree;*/

	// Constructors

	/** default constructor */
	public SysVUserOperateId() {
	}

	/** full constructor */
	public SysVUserOperateId(String userId, String id, String systemId,
			String operateName, String operateAction, String menuInfo,
			String ifFinallyMenu, String parentId, String menuId,
			String opeLevel, String description, String flag,
			String standbyOne, String standbyTwo, String standbyThree) {
		this.userId = userId;
		this.id = id;
	/*	this.systemId = systemId;
		this.operateName = operateName;
		this.operateAction = operateAction;
		this.menuInfo = menuInfo;
		this.ifFinallyMenu = ifFinallyMenu;
		this.parentId = parentId;
		this.menuId = menuId;
		this.opeLevel = opeLevel;
		this.description = description;
		this.flag = flag;
		this.standbyOne = standbyOne;
		this.standbyTwo = standbyTwo;
		this.standbyThree = standbyThree;*/
	}

	// Property accessors

	@Column(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
/*
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
*/
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysVUserOperateId))
			return false;
		SysVUserOperateId castOther = (SysVUserOperateId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())))
			/*	&& ((this.getSystemId() == castOther.getSystemId()) || (this
						.getSystemId() != null
						&& castOther.getSystemId() != null && this
						.getSystemId().equals(castOther.getSystemId())))
				&& ((this.getOperateName() == castOther.getOperateName()) || (this
						.getOperateName() != null
						&& castOther.getOperateName() != null && this
						.getOperateName().equals(castOther.getOperateName())))
				&& ((this.getOperateAction() == castOther.getOperateAction()) || (this
						.getOperateAction() != null
						&& castOther.getOperateAction() != null && this
						.getOperateAction()
						.equals(castOther.getOperateAction())))
				&& ((this.getMenuInfo() == castOther.getMenuInfo()) || (this
						.getMenuInfo() != null
						&& castOther.getMenuInfo() != null && this
						.getMenuInfo().equals(castOther.getMenuInfo())))
				&& ((this.getIfFinallyMenu() == castOther.getIfFinallyMenu()) || (this
						.getIfFinallyMenu() != null
						&& castOther.getIfFinallyMenu() != null && this
						.getIfFinallyMenu()
						.equals(castOther.getIfFinallyMenu())))
				&& ((this.getParentId() == castOther.getParentId()) || (this
						.getParentId() != null
						&& castOther.getParentId() != null && this
						.getParentId().equals(castOther.getParentId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this
						.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId()
						.equals(castOther.getMenuId())))
				&& ((this.getOpeLevel() == castOther.getOpeLevel()) || (this
						.getOpeLevel() != null
						&& castOther.getOpeLevel() != null && this
						.getOpeLevel().equals(castOther.getOpeLevel())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getStandbyOne() == castOther.getStandbyOne()) || (this
						.getStandbyOne() != null
						&& castOther.getStandbyOne() != null && this
						.getStandbyOne().equals(castOther.getStandbyOne())))
				&& ((this.getStandbyTwo() == castOther.getStandbyTwo()) || (this
						.getStandbyTwo() != null
						&& castOther.getStandbyTwo() != null && this
						.getStandbyTwo().equals(castOther.getStandbyTwo())))
				&& ((this.getStandbyThree() == castOther.getStandbyThree()) || (this
						.getStandbyThree() != null
						&& castOther.getStandbyThree() != null && this
						.getStandbyThree().equals(castOther.getStandbyThree())))*/
				;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		/*result = 37 * result
				+ (getSystemId() == null ? 0 : this.getSystemId().hashCode());
		result = 37
				* result
				+ (getOperateName() == null ? 0 : this.getOperateName()
						.hashCode());
		result = 37
				* result
				+ (getOperateAction() == null ? 0 : this.getOperateAction()
						.hashCode());
		result = 37 * result
				+ (getMenuInfo() == null ? 0 : this.getMenuInfo().hashCode());
		result = 37
				* result
				+ (getIfFinallyMenu() == null ? 0 : this.getIfFinallyMenu()
						.hashCode());
		result = 37 * result
				+ (getParentId() == null ? 0 : this.getParentId().hashCode());
		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result
				+ (getOpeLevel() == null ? 0 : this.getOpeLevel().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37
				* result
				+ (getStandbyOne() == null ? 0 : this.getStandbyOne()
						.hashCode());
		result = 37
				* result
				+ (getStandbyTwo() == null ? 0 : this.getStandbyTwo()
						.hashCode());
		result = 37
				* result
				+ (getStandbyThree() == null ? 0 : this.getStandbyThree()
						.hashCode());*/
		return result;
	}

}