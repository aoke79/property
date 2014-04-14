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

@Entity
@Table(name = "MODEL_ROUTE_TURNNUMBER_ZS")
public class RouteTurnnumber implements Serializable {

	// 序号
	private String seq;
	// 转升路径名称
	private String routeName;
	// 人数上限
	private Integer maxNumber;
	// 人数固定值
	private Integer fixedNumber;
	// 人数下限
	private Integer minNumber;
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
	
	

	// Constructors

	/** 无参构造 */
	public RouteTurnnumber() {
	}

	/** 不全参数构造 */
	public RouteTurnnumber(String seq, String routeName, String status,
			String operate, Date createDate, Date modifyDate) {
		this.seq = seq;
		this.routeName = routeName;
		this.status = status;
		this.operate = operate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		//this.deleteflag = deleteflag;
	}

	/** 完全参数构造 */
	public RouteTurnnumber(String seq, String routeName, Integer maxNumber,
			Integer fixedNumber, Integer minNumber, Integer halfRefvalue,
			Integer thisRefvalue, Integer threeRefvalue, Integer fiveRefvalue,
			String status, String operate, Date createDate, Date modifyDate,
			String remark) {
		this.seq = seq;
		this.routeName = routeName;
		this.maxNumber=maxNumber;
		this.fixedNumber=fixedNumber;
		this.minNumber=minNumber;
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
	@Column(name = "ROUTENAME", length = 30)
	public String getRouteName() {
		return routeName;
	}
   
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	@Column(name = "MAXNUMBER")
	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}
	@Column(name = "FIXEDNUMBER")
	public Integer getFixedNumber() {
		return fixedNumber;
	}

	public void setFixedNumber(Integer fixedNumber) {
		this.fixedNumber = fixedNumber;
	}
	@Column(name = "MINNUMBER")
	public Integer getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
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
	
	

}
