package com.sinoframe.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sys_insert_log")
public class SysAddLog {

	/** 日志ID */
	private String id;
	/** 模块名称 */
	private String moduleName;
	/** 用户名称 */
	private String userId;
	/** 插入时间 */
	private Date addTime;
	/** 插入的SQL语句 */
	private String addSql;
	/** 系统编号 */
	private String systemId;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "MODULE_NAME")
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "ADD_SQL")
	public String getAddSql() {
		return addSql;
	}
	public void setAddSql(String addSql) {
		this.addSql = addSql;
	}
	@Column(name = "SYSTEM_ID")
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}
