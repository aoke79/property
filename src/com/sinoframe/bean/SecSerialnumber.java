package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SecSerialnumber
 * @描述  表 "SEC_SERIALNUMBER" 的实体类
 * @作者 胡星
 * @版本 1.0
 */
@Entity
@Table(name = "SEC_SERIALNUMBER")
public class SecSerialnumber implements java.io.Serializable {

	private static final long serialVersionUID = 1671052274896948856L;
	/** 编号 ID */
	private String snid;
	/** 单位标识 */
	private String sdeptid;
	/** 例如: FD{单位}{时间}{序号} */
	private String sespecidlid;
	/** 例如: yyyy-MM-dd */
	private String sdateformat;
	/** 序号 */
	private Integer serial;
	/** 序号的位数 */
	private Integer serialnum;
	/** 通过这个字段作为依据获取不同业务的序号 */
	private String sersource;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SNID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getSnid() {
		return this.snid;
	}

	public void setSnid(String snid) {
		this.snid = snid;
	}

	@Column(name = "SDEPTID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSdeptid() {
		return this.sdeptid;
	}

	public void setSdeptid(String sdeptid) {
		this.sdeptid = sdeptid;
	}

	@Column(name = "SESPECIDLID", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getSespecidlid() {
		return this.sespecidlid;
	}

	public void setSespecidlid(String sespecidlid) {
		this.sespecidlid = sespecidlid;
	}

	@Column(name = "SDATEFORMAT", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getSdateformat() {
		return this.sdateformat;
	}

	public void setSdateformat(String sdateformat) {
		this.sdateformat = sdateformat;
	}

	@Column(name = "SERIAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 5, scale = 0)
	public Integer getSerial() {
		return this.serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	@Column(name = "SERIALNUM", unique = false, nullable = true, insertable = true, updatable = true, precision = 5, scale = 0)
	public Integer getSerialnum() {
		return this.serialnum;
	}

	public void setSerialnum(Integer serialnum) {
		this.serialnum = serialnum;
	}

	@Column(name = "SERSOURCE", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public String getSersource() {
		return this.sersource;
	}

	public void setSersource(String sersource) {
		this.sersource = sersource;
	}

}