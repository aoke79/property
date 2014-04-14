/**
 * Bean of table "SYS_PORTAL_DATA"
 * create at 10:26 Dec. 6th 2011
 */
package com.sinoframe.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_PORTAL_DATA")
public class SysPortalData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private String pid;
	/** 数据名称 */
	private String dataName;
	/** 数据类型 */
	private String dataType;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String creator;
	/** 修改时间 */
	private Date modifyTime;
	/** 修改人 */
	private String modifier;
	
	//数据类型
	private String pdataType;
	
	@Id
	@GeneratedValue(generator="portal-uuid")
	@GenericGenerator(name="portal-uuid",strategy="uuid")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Column(name = "DATANAME")
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	@Column(name = "DATATYPE")
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYTIME")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Transient
	public String getPdataType() {
		return pdataType;
	}
	public void setPdataType(String pdataType) {
		this.pdataType = pdataType;
	}

}
