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
@Table(name = "MODEL_COPILOT_UPNUMBER_ZS")
public class CopilotUpnumber implements Serializable {
	//序号
	private String id;
	//机型
	private String type;
    //技术等级名称
	private String gradeName;
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
	//飞行经历时间符合要求人数
	private Integer fitNumber;
	//升级资格占比
	private Integer upqualificationRate;
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
	
	
	/** 无参构造 */
	public CopilotUpnumber() {
	}

	/** 不全参数构造 */
	public CopilotUpnumber(String id, String type, String status,
			String operate, Date createDate, Date modifyDate) {
		this.id = id;
		this.type = type;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		
	}

	/** 完全参数构造 */
	public CopilotUpnumber(String id, String type,Integer halfRefvalue,String gradeName,Integer fitNumber,Integer upqualificationRate,
			Integer thisRefvalue, Integer threeRefvalue, Integer fiveRefvalue,Integer thisPlanvalue,String priority,
			String status, String operate, Date createDate, Date modifyDate,
			String remark) {
		this.id = id;
		this.type = type;
		this.thisPlanvalue=thisPlanvalue;
		this.gradeName=gradeName;
		this.halfRefvalue=halfRefvalue;
		this.thisRefvalue=thisRefvalue;
		this.threeRefvalue=threeRefvalue;
		this.fiveRefvalue=fiveRefvalue;
		this.fitNumber=fitNumber;
		this.upqualificationRate=upqualificationRate;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.remark=remark;
		
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
	@Column(name = "TYPE", length = 30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "GRADENAME", length = 30)
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
	@Column(name = "THISPLANVALUE")
	public Integer getThisPlanvalue() {
		return thisPlanvalue;
	}

	public void setThisPlanvalue(Integer thisPlanvalue) {
		this.thisPlanvalue = thisPlanvalue;
	}
	@Column(name = "FITNUMBER")
	public Integer getFitNumber() {
		return fitNumber;
	}

	public void setFitNumber(Integer fitNumber) {
		this.fitNumber = fitNumber;
	}
	@Column(name = "UPQUALIFICATIONRATE")
	public Integer getUpqualificationRate() {
		return upqualificationRate;
	}

	public void setUpqualificationRate(Integer upqualificationRate) {
		this.upqualificationRate = upqualificationRate;
	}

	

}
