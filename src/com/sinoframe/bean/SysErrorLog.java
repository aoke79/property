/**
 * Title SysErrorLog
 * Description bean of table "sys_error_log"
 */

package com.sinoframe.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sys_error_log")
public class SysErrorLog {

	/** 日志ID */
	private String id;
	/** 模块名称 */
	private String moduleName;
	/** 用户名称 */
	private String userId;
	/** 产生时间 */
	private Date happenTime;
	/** 产生错误的文件 */
	private String errorFilename;
	/** 错误日志 */
	private String errorMessage;
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
	@Column(name = "MODULE_NAME")
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "HAPPEN_TIME")
	public Date getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}
	@Column(name = "ERROR_FILENAME")
	public String getErrorFilename() {
		return errorFilename;
	}
	public void setErrorFilename(String errorFilename) {
		this.errorFilename = errorFilename;
	}
	@Column(name = "ERROR_MESSAGE")
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Column(name = "SYSTEM_ID")
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
}
