/**
 * Title: SysSystem
 * Description: The Entity Bean of table "SYS_SYSTEM"
 */

package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_system")
public class SysSystem {
	
	//properties definition
	/** 系统编号 */
	private String id;
	/** 系统名称 */
	private String name;
	/** 系统描述 */
	private String description;
	/** 是否可用 */
	private String state;

	
	//constructor
	public SysSystem(){
	}
	
	public SysSystem(String id, String name, String description, String state) {
		this.id = id;
		this.name = name;
		this.description = description;
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
	
	@Column(name="NAME",nullable=true,length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DESCRIPTION",nullable=true,length=200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="STATE",nullable=true,length=1)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
