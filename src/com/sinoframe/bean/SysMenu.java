/**
 * Title SysMenu
 * Description the bean entity of table SYS_MENU
 */

package com.sinoframe.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="sys_menu")
public class SysMenu implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	//properties definition
	/** 菜单编号 */
	private String id;
	/** 对应操作编号 */
	private Set<SysOperate> sysOperate = new HashSet<SysOperate>();
	/** 菜单名称 */
	private String name;  
	/** 菜单地址 */
	private String url;   
	/** 状态标识位 */
	private String state;
	/** 对应操作类型 */
	private String operateType;
	/** 操作编号 */
	private String operateId;
	/** 对应用户菜单表 */
	private Set<SysUserCustomMenu> sysUserCustomMenus = new HashSet<SysUserCustomMenu>(0);
	/** 标签信息 */
	private String tabInfo;
	/** 排序信息 */
	private Integer orderColumn;
	/** 上级菜单 */
	private SysMenu parentMenu;
	
	/** 子菜单 */
	private Set<SysMenu> subMenu = new HashSet<SysMenu>();
	/** 菜单英文名称 */
	private String enName;
	//子系统标识
	private String subsystemId;

	
	//constructor
	public SysMenu(){	
	}
	
	public SysMenu(String id, String name, String url,
			String state, String operateType, Set<SysUserCustomMenu> sysUserCustomMenus,String subsystemId) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.state = state;
		this.operateType = operateType;
		this.sysUserCustomMenus = sysUserCustomMenus;
		this.subsystemId = subsystemId;
	}
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
    @Column(name="SUBSYSTEM_ID",length=36)
	public String getSubsystemId() {
		return subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "sysMenu")
	public Set<SysOperate> getSysOperate() {
		return sysOperate;
	}
	public void setSysOperate(Set<SysOperate> sysOperate) {
		this.sysOperate = sysOperate;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="OPERATE_TYPE")
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	@Column(name="OPERATE_ID")
	public String getOperateId() {
		return operateId;
	}
	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	
	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "sysMenu")
	public Set<SysUserCustomMenu> getSysUserCustomMenus() {
		return this.sysUserCustomMenus;
	}

	public void setSysUserCustomMenus(Set<SysUserCustomMenu> sysUserCustomMenus) {
		this.sysUserCustomMenus = sysUserCustomMenus;
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
		if (getClass() != obj.getClass())
			return false;
		final SysMenu other = (SysMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Column(name="TAB_INFO")
	public String getTabInfo() {
		return tabInfo;
	}

	public void setTabInfo(String tabInfo) {
		this.tabInfo = tabInfo;
	}
	
	@Column(name="ORDER_COLUMN")
	public Integer getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(Integer orderColumn) {
		this.orderColumn = orderColumn;
	}

	@ManyToOne(cascade = {})
	@JoinColumn(name = "PARENT_ID")
	@NotFound(action=NotFoundAction.IGNORE)  
	public SysMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SysMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY,mappedBy = "parentMenu")
	public Set<SysMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(Set<SysMenu> subMenu) {
		this.subMenu = subMenu;
	}
	
	@Column(name="EN_NAME")
	public String getEnName() {
		return enName;
	}
	
	public void setEnName(String enName) {
		this.enName = enName;
	}
	
}
