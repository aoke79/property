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
@Table(name = "MODEL_PILOT_TURNROUTE_ZS")
public class PilotTurnroute implements Serializable {
	// 序号
	private String seq;
	// 机型转换方式
	private String transformMode;
	// 半年内人数参考值
	private Integer halfRefvalue;
	// 本年内人数参考值
	private Integer thisRefvalue;
	// 三年内人数参考值
	private Integer threeRefvalue;
	// 五年内人数参考值
	private Integer fiveRefvalue;
	//本次规划值
	private Integer thisPlanvalue;
	//优先级
	private String priority;
	// 操作人员
	private String operate;
	// 创建日期
	private Date createDate;
	// 修改日期
	private Date modifyDate;
	// 标识符 默认为“Y”
	private String status;
	// 备注
	private String remark;
	
	

	// Constructors

	/** 无参构造 */
	public PilotTurnroute() {
	}

	/** 不全参数构造 */
	public PilotTurnroute(String seq, String transformMode, String status,
			String operate, Date createDate, Date modifyDate) {
		this.seq = seq;
		this.transformMode = transformMode;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		
	}

	/** 完全参数构造 */
	public PilotTurnroute(String seq, String transformMode,Integer halfRefvalue,
			Integer thisRefvalue, Integer threeRefvalue, Integer fiveRefvalue,Integer thisPlanvalue,String priority,
			String status, String operate, Date createDate, Date modifyDate,
			String remark) {
		this.seq = seq;
		this.transformMode = transformMode;
		this.thisPlanvalue=thisPlanvalue;
		this.priority=priority;
		this.halfRefvalue=halfRefvalue;
		this.thisRefvalue=thisRefvalue;
		this.threeRefvalue=threeRefvalue;
		this.fiveRefvalue=fiveRefvalue;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.remark=remark;
		
	}
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SEQ", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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
	
	@Column(name = "TRANSFORMMODE" ,length=30)
	public String getTransformMode() {
		return transformMode;
	}

	public void setTransformMode(String transformMode) {
		this.transformMode = transformMode;
	}
	@Column(name = "THISPLANVALUE")
	public Integer getThisPlanvalue() {
		return thisPlanvalue;
	}

	public void setThisPlanvalue(Integer thisPlanvalue) {
		this.thisPlanvalue = thisPlanvalue;
	}
	@Column(name = "PRIORITY" ,length=30)
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	

}

