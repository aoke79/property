package com.sms.training.qualification.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author zhong
 * 飞行教员飞行记录1
 */
@Entity
@Table(name = "TF_QUAL_PILOT_APPINFOR_T_ONE")
public class TfQualPilotAppinforTeacherOne {
	//ID
	private String id;
	//申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//开始时间
	private Date startdt;
	//结束时间
	private Date enddt;
	//总飞行时间
	private String total;
	//所飞机型
	//	private String atrid;
	private BaseAirPlanType baseAirPlanType;
	//带飞时间
	private String instruction;
	//单飞时间
	private String solo;
	//机长时间PIC
	private String captainPic;
	//机长时间SPIC
	private String captainSpic;
	//转场单飞时间
	private String turnTime;
	//机长转场时间PIC
	private String captainturnPic;
	//机长转场时间SPIC
	private String captainturnSpic;
	//仪表时间
	private String instrument;
	//夜间带飞时间
	private String instructinNight;
	//夜间起落次数
	private String nightUpdownnum;
	//机长夜间时间PIC
	private String nightPic;
	//机长夜间时间SPIC
	private String nightSpic;
	//机长夜间起落次数PIC
	private String nightUpdownnumpic;
	//机长夜间起落次数SPIC
	private String nightUpdownnumspic;
	//备注
	private String remarks;
	
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILSID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return this.tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDT", length = 7)
	public Date getStartdt() {
		return this.startdt;
	}

	public void setStartdt(Date startdt) {
		this.startdt = startdt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDT", length = 7)
	public Date getEnddt() {
		return this.enddt;
	}

	public void setEnddt(Date enddt) {
		this.enddt = enddt;
	}

	@Column(name = "TOTAL", precision = 10, scale = 0)
	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

//	@Column(name = "ATRID", length = 36)
//	public String getAtrid() {
//		return this.atrid;
//	}
//
//	public void setAtrid(String atrid) {
//		this.atrid = atrid;
//	}

	@Column(name = "INSTRUCTION", precision = 10, scale = 0)
	public String getInstruction() {
		return this.instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Column(name = "SOLO", precision = 10, scale = 0)
	public String getSolo() {
		return this.solo;
	}

	public void setSolo(String solo) {
		this.solo = solo;
	}

	@Column(name = "CAPTAIN_PIC", precision = 10, scale = 0)
	public String getCaptainPic() {
		return this.captainPic;
	}

	public void setCaptainPic(String captainPic) {
		this.captainPic = captainPic;
	}

	@Column(name = "CAPTAIN_SPIC", precision = 10, scale = 0)
	public String getCaptainSpic() {
		return this.captainSpic;
	}

	public void setCaptainSpic(String captainSpic) {
		this.captainSpic = captainSpic;
	}

	@Column(name = "TURN_TIME", precision = 10, scale = 0)
	public String getTurnTime() {
		return this.turnTime;
	}

	public void setTurnTime(String turnTime) {
		this.turnTime = turnTime;
	}

	@Column(name = "CAPTAINTURN_PIC", precision = 10, scale = 0)
	public String getCaptainturnPic() {
		return this.captainturnPic;
	}

	public void setCaptainturnPic(String captainturnPic) {
		this.captainturnPic = captainturnPic;
	}

	@Column(name = "CAPTAINTURN_SPIC", precision = 10, scale = 0)
	public String getCaptainturnSpic() {
		return this.captainturnSpic;
	}

	public void setCaptainturnSpic(String captainturnSpic) {
		this.captainturnSpic = captainturnSpic;
	}

	@Column(name = "INSTRUMENT", precision = 10, scale = 0)
	public String getInstrument() {
		return this.instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	@Column(name = "INSTRUCTIN_NIGHT", precision = 10, scale = 0)
	public String getInstructinNight() {
		return this.instructinNight;
	}

	public void setInstructinNight(String instructinNight) {
		this.instructinNight = instructinNight;
	}

	@Column(name = "NIGHT_UPDOWNNUM", precision = 10, scale = 0)
	public String getNightUpdownnum() {
		return this.nightUpdownnum;
	}

	public void setNightUpdownnum(String nightUpdownnum) {
		this.nightUpdownnum = nightUpdownnum;
	}

	@Column(name = "NIGHT_PIC", precision = 10, scale = 0)
	public String getNightPic() {
		return this.nightPic;
	}

	public void setNightPic(String nightPic) {
		this.nightPic = nightPic;
	}

	@Column(name = "NIGHT_SPIC", precision = 10, scale = 0)
	public String getNightSpic() {
		return this.nightSpic;
	}

	public void setNightSpic(String nightSpic) {
		this.nightSpic = nightSpic;
	}

	@Column(name = "NIGHT_UPDOWNNUMPIC", precision = 10, scale = 0)
	public String getNightUpdownnumpic() {
		return this.nightUpdownnumpic;
	}

	public void setNightUpdownnumpic(String nightUpdownnumpic) {
		this.nightUpdownnumpic = nightUpdownnumpic;
	}

	@Column(name = "NIGHT_UPDOWNNUMSPIC", precision = 10, scale = 0)
	public String getNightUpdownnumspic() {
		return this.nightUpdownnumspic;
	}

	public void setNightUpdownnumspic(String nightUpdownnumspic) {
		this.nightUpdownnumspic = nightUpdownnumspic;
	}

	@Column(name = "REMARKS", length = 60)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATRID")
	public BaseAirPlanType getBaseAirPlanType() {
		return baseAirPlanType;
	}

	public void setBaseAirPlanType(BaseAirPlanType baseAirPlanType) {
		this.baseAirPlanType = baseAirPlanType;
	}

}
