package com.sinoframe.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sys_update_log")
public class SysUpdateLog {

	/** 日志ID */
	private String id;
	/** 模块名称 */
	private String moduleName;
	/** 用户名称 */
	private String userId;
	/** 产生时间 */
	private Date updateTime;
	/** 更新SQL */
	private String updateSql;
	/**  */
	private String beforeData;
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
	@Column(name ="update_time" )
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "UPDATE_SQL")
	public String getUpdateSql() {
		return updateSql;
	}
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}
	@Column(name = "BEFORE_DATA")
	public String getBeforeData() {
		return beforeData;
	}
	public void setBeforeData(String beforeData) {
		this.beforeData = beforeData;
	}
	@Column(name = "SYSTEM_ID")
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}
