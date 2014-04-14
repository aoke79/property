package com.sinoframe.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sys_query_log")
public class SysQueryLog {

	/** 日志ID */
	private String id;
	/** 模块名称 */
	private String moduleName;
	/**  */
	private double visitCount;
	/**  */
	private double visitUserCount;
	/**  */
	private Date visitDate;
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
	@Column(name = "VISIT_COUNT")
	public double getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(double visitCount) {
		this.visitCount = visitCount;
	}
	@Column(name = "VISIT_USER_COUNT")
	public double getVisitUserCount() {
		return visitUserCount;
	}
	public void setVisitUserCount(double visitUserCount) {
		this.visitUserCount = visitUserCount;
	}
	@Column(name = "VISIT_DATE")
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	@Column(name = "SYSTEM_ID")
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
}
