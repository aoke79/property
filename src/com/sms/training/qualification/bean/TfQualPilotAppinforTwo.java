package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_APPINFOR_TWO")
public class TfQualPilotAppinforTwo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	//ID
	private String id;
	//申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//航空器类别
	private String aircrafttype;
	//总飞行时间
	private String total;
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
	//机长转场PIC
	private String captainturnPic;
	//机长转场SPIC
	private String captainturnSpic;
	//仪表时间
	private String instrument;
	//夜间带飞时间
	private String instructinNight;
	//夜间起落次数
	private String nightUpdownnum;
	//机长夜间时间PIC
	private String nightPic;
	//机长夜间时SPIC
	private String nightSpic;
	//机长夜间起落次数PIC
	private String nightUpdownnumpic;
	//机长夜间起落次数SPIC
	private String nightUpdownnumspic;
	//飞行次数
	private String flightNo;
	//空中牵引次数
	private String aeroNo;
	//地面牵引次数
	private String groundNo;
	//自行起飞次数
	private String poweredNo;

	// Constructors

	/** default constructor */
	public TfQualPilotAppinforTwo() {
	}

	/** full constructor */
	public TfQualPilotAppinforTwo(TfQualDeclaraPilot tfQualDeclaraPilot,
			String aircrafttype, String total, String instruction, String solo,
			String captainPic, String captainSpic, String turnTime,
			String captainturnPic, String captainturnSpic, String instrument,
			String instructinNight, String nightUpdownnum, String nightPic,
			String nightSpic, String nightUpdownnumpic, String nightUpdownnumspic,
			String flightNo, String aeroNo, String groundNo, String poweredNo) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.aircrafttype = aircrafttype;
		this.total = total;
		this.instruction = instruction;
		this.solo = solo;
		this.captainPic = captainPic;
		this.captainSpic = captainSpic;
		this.turnTime = turnTime;
		this.captainturnPic = captainturnPic;
		this.captainturnSpic = captainturnSpic;
		this.instrument = instrument;
		this.instructinNight = instructinNight;
		this.nightUpdownnum = nightUpdownnum;
		this.nightPic = nightPic;
		this.nightSpic = nightSpic;
		this.nightUpdownnumpic = nightUpdownnumpic;
		this.nightUpdownnumspic = nightUpdownnumspic;
		this.flightNo = flightNo;
		this.aeroNo = aeroNo;
		this.groundNo = groundNo;
		this.poweredNo = poweredNo;
	}

	// Property accessors
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

	@Column(name = "AIRCRAFTTYPE", length = 1)
	public String getAircrafttype() {
		return this.aircrafttype;
	}

	public void setAircrafttype(String aircrafttype) {
		this.aircrafttype = aircrafttype;
	}

	@Column(name = "TOTAL", precision = 10, scale = 0)
	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

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

	@Column(name = "FLIGHT_NO", precision = 10, scale = 0)
	public String getFlightNo() {
		return this.flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	@Column(name = "AERO_NO", precision = 10, scale = 0)
	public String getAeroNo() {
		return this.aeroNo;
	}

	public void setAeroNo(String aeroNo) {
		this.aeroNo = aeroNo;
	}

	@Column(name = "GROUND_NO", precision = 10, scale = 0)
	public String getGroundNo() {
		return this.groundNo;
	}

	public void setGroundNo(String groundNo) {
		this.groundNo = groundNo;
	}

	@Column(name = "POWERED_NO", precision = 10, scale = 0)
	public String getPoweredNo() {
		return this.poweredNo;
	}

	public void setPoweredNo(String poweredNo) {
		this.poweredNo = poweredNo;
	}

}