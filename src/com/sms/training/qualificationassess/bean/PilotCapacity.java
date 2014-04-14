package com.sms.training.qualificationassess.bean;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@SuppressWarnings("serial")
@Entity
@Table(name = "MODEL_PILOT_CAPACITY_ZS")
public class PilotCapacity implements Serializable {
	// 序号
	private String id;
	// 动力名称
	private String capacityName;
	// 半年内人数参考值
	private Integer halfRefvalue;
	// 本年内人数参考值
	private Integer thisRefvalue;
	// 三年内人数参考值
	private Integer threeRefvalue;
	// 五年内人数参考值
	private Integer fiveRefvalue;
	// 操作人员
	private String operate;
	// 创建日期
	private Date createDate;
	// 修改时间
	private Date modifyDate;
	// 标识符 默认为“Y”
	private String status;
	// 备注
	private String remark;
	//包括的技术等级
	private String  industrialGrade;
	//本次规划值
	private Integer thisPlanvalue;
	// Constructors

	/** 无参构造 */
	public PilotCapacity() {
	}

	/** 不全参数构造 */
	public PilotCapacity(String id, String capacityName, String status,
			String operate, Date createDate, Date modifyDate) {
		this.id = id;
		this.capacityName = capacityName;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		
	}

	/** 完全参数构造 */
	public PilotCapacity(String id, String capacityName,  Integer halfRefvalue,
			Integer thisRefvalue, Integer threeRefvalue, Integer fiveRefvalue,
			String status, String operate, Date createDate, Date modifyDate,
			String remark,Integer thisPlanvalue,String industrialGrade) {
		this.id = id;
		this.capacityName = capacityName;
		this.halfRefvalue=halfRefvalue;
		this.thisRefvalue=thisRefvalue;
		this.threeRefvalue=threeRefvalue;
		this.fiveRefvalue=fiveRefvalue;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.remark=remark;
		this.thisPlanvalue=thisPlanvalue;
		this.industrialGrade=industrialGrade;
	}
	
	
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "CAPACITYNAME", length = 30)
	public String getCapacityName() {
		return capacityName;
	}
	public void setCapacityName(String capacityName) {
		this.capacityName = capacityName;
	}
	@Column(name = "HALFREFVALUE")
	public Integer getHalfRefvalue() {
		return halfRefvalue;
	}
	public void setHalfRefvalue(Integer halfRefvalue) {
		this.halfRefvalue = halfRefvalue;
	}
	@Column(name = "THISREFVALUE")
	public Integer getThisRefvalue() {
		return thisRefvalue;
	}
	public void setThisRefvalue(Integer thisRefvalue) {
		this.thisRefvalue = thisRefvalue;
	}
	@Column(name = "THREEREFVALUE")
	public Integer getThreeRefvalue() {
		return threeRefvalue;
	}
	public void setThreeRefvalue(Integer threeRefvalue) {
		this.threeRefvalue = threeRefvalue;
	}
	@Column(name = "FIVEREFVALUE")
	public Integer getFiveRefvalue() {
		return fiveRefvalue;
	}
	public void setFiveRefvalue(Integer fiveRefvalue) {
		this.fiveRefvalue = fiveRefvalue;
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
	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "INDUSTRIALGRADE", length = 30)
	public String getIndustrialGrade() {
		return industrialGrade;
	}
	public void setIndustrialGrade(String industrialGrade) {
		this.industrialGrade = industrialGrade;
	}
	@Column(name = "THISPLANVALUE")
	public Integer getThisPlanvalue() {
		return thisPlanvalue;
	}
	public void setThisPlanvalue(Integer thisPlanvalue) {
		this.thisPlanvalue = thisPlanvalue;
	}
	
	

}
