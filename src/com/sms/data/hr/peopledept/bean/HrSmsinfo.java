package com.sms.data.hr.peopledept.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sms.data.hr.people.SyncPeolpe;
@SuppressWarnings("serial")
@Entity
@Table(name = "INTF_HR_SMS_DEPT")
public class HrSmsinfo implements Serializable {
	// HR部门ID
	private String hrDeptid;
	// HR部门名称
	private String hrDept;
	// SMS系统部门ID
	private String smsDeptid;
	// SMS系统部门名称
	private String smsDept;
	// 部门地点
	private String area;
	// 操作人员
	private String operate;
	// 创建日期
	private Date createDate;
	// 修改时间
	private Date modifyDate;
	// 标识符 默认为“Y”
	private String status;
	
	// Constructors

	/** 无参构造 */
	public HrSmsinfo() {
	}

	/** 全参数构造 */
	public HrSmsinfo(String hrDeptid, String hrDept, String smsDeptid,
			String smsDept,String area) {
		this.hrDeptid=hrDeptid;
		this.hrDept=hrDept;
		this.smsDeptid=smsDeptid;
		this.smsDept=smsDept;
		this.area=area;
	}
	@Id
	@Column(name = "HRDEPTID", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	public String getHrDeptid() {
		return hrDeptid;
	}
    
	public void setHrDeptid(String hrDeptid) {
		this.hrDeptid = hrDeptid;
	}
	@Column(name = "HRDEPT", length = 200)
	public String getHrDept() {
		return hrDept;
	}
	
	public void setHrDept(String hrDept) {
		this.hrDept = hrDept;
	}
	@Column(name = "SMSDEPTID", length = 100)
	public String getSmsDeptid() {
		return smsDeptid;
	}

	public void setSmsDeptid(String smsDeptid) {
		this.smsDeptid = smsDeptid;
	}
	@Column(name = "SMSDEPT", length = 200)
	public String getSmsDept() {
		return smsDept;
	}

	public void setSmsDept(String smsDept) {
		this.smsDept = smsDept;
	}
	@Column(name = "AREA", length = 6)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	@Column(name = "OPERATE", length = 30)
	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", nullable = false, length = 7)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYDATE", nullable = false, length = 7)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
}
