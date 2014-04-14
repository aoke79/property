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
 * 民用航空器驾驶员执照和等级申请表 entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_APPINFORMATION")
public class TfQualPilotAppinformation implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	/** ID*/
	private String informationid;
	/** 申报人员明细*/
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	/** 飞行员hrid*/
	private String pilotid;
	/** 驾驶员执照*/
	private String info16;
	/** 执照编号*/
	private String info17;
	/** 颁发日期*/
	private Date info18;
	/** 体检合格证等级*/
	private String info19;
	/** 体检合格日期*/
	private Date info20;
	/** 听说读写汉语*/
	private String info21;
	/** 持有ICAO等级有无*/
	private String info22;
	/** 颁发国*/
	private String infoCountry;
	/** 等级*/
	private String infoLevel;
	/** 申请增加ICAO英语等级*/
	private String info23;
	/** 所申请等级理论考试通过成绩*/
	private String info24a;
	/** 有效期*/
	private Date info24b;
	/** 25A*/
	private String info25a;
	/** 25B*/
	private String info25b;
	/** 26A*/
	private String info26a;
	/** 26B*/
	private String info26b;
	/** 26C*/
	private String info26c;
	/** 26D*/
	private String info26d;
	/** 26E*/
	private String info26e;
	/** 是否增加航空器型别等级*/
	private String info27;
	/** 代码*/
	private String infoCode;
	/** 基于哪一项目申请执照等级*/
	private String infoLicense;
	/** 飞行训练机构_61*/
	private String info28;
	/** 地址_61*/
	private String info29;
	/** 完成课程名称_61*/
	private String info30;
	/** 完成日期_61*/
	private Date info31;
	/** 机构名称_141*/
	private String info32;
	/** 学校合格证编号*/
	private String info33;
	/** 地址_141*/
	private String info34;
	/** 完成课程名称_141*/
	private String info35;
	/** 完成时间_141*/
	private Date info36;
	/** 服役单位_军用*/
	private String info37;
	/** 获得得最高天气标准*/
	private String info38;
	/** 航空器名称*/
	private String info39;
	/** 外国驾驶-国家*/
	private String info40;
	/** 外国驾驶-执照类型*/
	private String info41;
	/** 外国驾驶-执照编号*/
	private String info42;
	/** 外国驾驶-执照所有等级*/
	private String info43;
	/** 航空承运人名称*/
	private String info44;
	/** 完成课程-航空承运人*/
	private String info45;
	/** 完成日期_航空承运人*/
	private Date info46;
	//最后修改人
	private String modifier;
	private String info47;
	

	/** 申请日期*/
	private Date info48;
	/** 飞行教员执照编号*/
	private String info49;
	/**教员签字 */
	private String info50;
	/**期满日期*/
	private Date info51;
	/**考试地点*/
	private String info52;
	/**考试时间地面*/
	private Date info53;
	/**考试时间模拟机*/
	private Date info54;
	/**考试时间飞行*/
	private Date info55;
	/**被考执照或等级*/
	private String info56;
	/**使用的航空器*/
	private String info57;
	/**航空器登记号*/
	private String info58;
	/**考试员合格证编号*/
	private String info59;
	/**委任期满日期*/
	private Date info60;
	/**考试员签字*/
	private String info61;
	/**考试日期*/
	private Date info62;
	/**63*/
	private String info63;
	/**通过不通过*/
	private String infoResult;
	private String info64;
	private String info65;
	private String infoRestrict65;
	private String infoOther65;
	private String infoExplain65;
	private String info66;
	private Date info67;
	private String info68;
	private Date info69;
	private String info70;
	private Date info71;
	private String info72;
	private String infoExplain72;
	private String info73;
	private Date info74;
	private String info75;
	private Date info76;
	private String info77;
	private Date info78;
	private String info79;
	private String otherDoc;
	private String infoGrade65;
	

	

	// Constructors
	/** default constructor */
	public TfQualPilotAppinformation() {
	}

	/** full constructor */
	public TfQualPilotAppinformation(TfQualDeclaraPilot tfQualDeclaraPilot,
			String pilotid, String info16, String info17, Date info18,
			String info19, Date info20, String info21, String info22,
			String infoCountry, String infoLevel, String info23,
			String info24a, Date info24b, String info25a, String info25b,
			String info26a, String info26b, String info26c, String info26d,
			String info26e, String info27, String infoCode, String infoLicense,
			String info28, String info29, String info30, Date info31,
			String info32, String info33, String info34, String info35,
			Date info36, String info37, String info38, String info39,
			String info40, String info41, String info42, String info43,
			String info44, String info45, Date info46) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.pilotid = pilotid;
		this.info16 = info16;
		this.info17 = info17;
		this.info18 = info18;
		this.info19 = info19;
		this.info20 = info20;
		this.info21 = info21;
		this.info22 = info22;
		this.infoCountry = infoCountry;
		this.infoLevel = infoLevel;
		this.info23 = info23;
		this.info24a = info24a;
		this.info24b = info24b;
		this.info25a = info25a;
		this.info25b = info25b;
		this.info26a = info26a;
		this.info26b = info26b;
		this.info26c = info26c;
		this.info26d = info26d;
		this.info26e = info26e;
		this.info27 = info27;
		this.infoCode = infoCode;
		this.infoLicense = infoLicense;
		this.info28 = info28;
		this.info29 = info29;
		this.info30 = info30;
		this.info31 = info31;
		this.info32 = info32;
		this.info33 = info33;
		this.info34 = info34;
		this.info35 = info35;
		this.info36 = info36;
		this.info37 = info37;
		this.info38 = info38;
		this.info39 = info39;
		this.info40 = info40;
		this.info41 = info41;
		this.info42 = info42;
		this.info43 = info43;
		this.info44 = info44;
		this.info45 = info45;
		this.info46 = info46;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "INFORMATIONID", unique = true, nullable = false, length = 36)
	public String getInformationid() {
		return this.informationid;
	}

	public void setInformationid(String informationid) {
		this.informationid = informationid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILSID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return this.tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@Column(name = "PILOTID", length = 36)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Column(name = "INFO_16", length = 1)
	public String getInfo16() {
		return this.info16;
	}

	public void setInfo16(String info16) {
		this.info16 = info16;
	}

	@Column(name = "INFO_17", length = 36)
	public String getInfo17() {
		return this.info17;
	}

	public void setInfo17(String info17) {
		this.info17 = info17;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_18", length = 7)
	public Date getInfo18() {
		return this.info18;
	}

	public void setInfo18(Date info18) {
		this.info18 = info18;
	}

	@Column(name = "INFO_19", length = 1)
	public String getInfo19() {
		return this.info19;
	}

	public void setInfo19(String info19) {
		this.info19 = info19;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_20", length = 7)
	public Date getInfo20() {
		return this.info20;
	}

	public void setInfo20(Date info20) {
		this.info20 = info20;
	}

	@Column(name = "INFO_21", length = 1)
	public String getInfo21() {
		return this.info21;
	}

	public void setInfo21(String info21) {
		this.info21 = info21;
	}

	@Column(name = "INFO_22", length = 1)
	public String getInfo22() {
		return this.info22;
	}

	public void setInfo22(String info22) {
		this.info22 = info22;
	}

	@Column(name = "INFO_COUNTRY", length = 30)
	public String getInfoCountry() {
		return this.infoCountry;
	}

	public void setInfoCountry(String infoCountry) {
		this.infoCountry = infoCountry;
	}

	@Column(name = "INFO_LEVEL", length = 1)
	public String getInfoLevel() {
		return this.infoLevel;
	}

	public void setInfoLevel(String infoLevel) {
		this.infoLevel = infoLevel;
	}

	@Column(name = "INFO_23", length = 1)
	public String getInfo23() {
		return this.info23;
	}

	public void setInfo23(String info23) {
		this.info23 = info23;
	}

	@Column(name = "INFO_24A", length = 30)
	public String getInfo24a() {
		return this.info24a;
	}

	public void setInfo24a(String info24a) {
		this.info24a = info24a;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_24B", length = 7)
	public Date getInfo24b() {
		return this.info24b;
	}

	public void setInfo24b(Date info24b) {
		this.info24b = info24b;
	}

	@Column(name = "INFO_25A", length = 1)
	public String getInfo25a() {
		return this.info25a;
	}

	public void setInfo25a(String info25a) {
		this.info25a = info25a;
	}

	@Column(name = "INFO_25B", length = 1)
	public String getInfo25b() {
		return this.info25b;
	}

	public void setInfo25b(String info25b) {
		this.info25b = info25b;
	}

	@Column(name = "INFO_26A", length = 1)
	public String getInfo26a() {
		return this.info26a;
	}

	public void setInfo26a(String info26a) {
		this.info26a = info26a;
	}

	@Column(name = "INFO_26B", length = 1)
	public String getInfo26b() {
		return this.info26b;
	}

	public void setInfo26b(String info26b) {
		this.info26b = info26b;
	}

	@Column(name = "INFO_26C", length = 1)
	public String getInfo26c() {
		return this.info26c;
	}

	public void setInfo26c(String info26c) {
		this.info26c = info26c;
	}

	@Column(name = "INFO_26D", length = 1)
	public String getInfo26d() {
		return this.info26d;
	}

	public void setInfo26d(String info26d) {
		this.info26d = info26d;
	}

	@Column(name = "INFO_26E", length = 1)
	public String getInfo26e() {
		return this.info26e;
	}

	public void setInfo26e(String info26e) {
		this.info26e = info26e;
	}

	@Column(name = "INFO_27", length = 1)
	public String getInfo27() {
		return this.info27;
	}

	public void setInfo27(String info27) {
		this.info27 = info27;
	}

	@Column(name = "INFO_CODE", length = 36)
	public String getInfoCode() {
		return this.infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	@Column(name = "INFO_LICENSE", length = 1)
	public String getInfoLicense() {
		return this.infoLicense;
	}

	public void setInfoLicense(String infoLicense) {
		this.infoLicense = infoLicense;
	}

	@Column(name = "INFO_28", length = 60)
	public String getInfo28() {
		return this.info28;
	}

	public void setInfo28(String info28) {
		this.info28 = info28;
	}

	@Column(name = "INFO_29", length = 60)
	public String getInfo29() {
		return this.info29;
	}

	public void setInfo29(String info29) {
		this.info29 = info29;
	}

	@Column(name = "INFO_30", length = 60)
	public String getInfo30() {
		return this.info30;
	}

	public void setInfo30(String info30) {
		this.info30 = info30;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_31", length = 7)
	public Date getInfo31() {
		return this.info31;
	}

	public void setInfo31(Date info31) {
		this.info31 = info31;
	}

	@Column(name = "INFO_32", length = 60)
	public String getInfo32() {
		return this.info32;
	}

	public void setInfo32(String info32) {
		this.info32 = info32;
	}

	@Column(name = "INFO_33", length = 36)
	public String getInfo33() {
		return this.info33;
	}

	public void setInfo33(String info33) {
		this.info33 = info33;
	}

	@Column(name = "INFO_34", length = 150)
	public String getInfo34() {
		return this.info34;
	}

	public void setInfo34(String info34) {
		this.info34 = info34;
	}

	@Column(name = "INFO_35", length = 60)
	public String getInfo35() {
		return this.info35;
	}

	public void setInfo35(String info35) {
		this.info35 = info35;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_36", length = 7)
	public Date getInfo36() {
		return this.info36;
	}

	public void setInfo36(Date info36) {
		this.info36 = info36;
	}

	@Column(name = "INFO_37", length = 60)
	public String getInfo37() {
		return this.info37;
	}

	public void setInfo37(String info37) {
		this.info37 = info37;
	}

	@Column(name = "INFO_38", length = 36)
	public String getInfo38() {
		return this.info38;
	}

	public void setInfo38(String info38) {
		this.info38 = info38;
	}

	@Column(name = "INFO_39", length = 60)
	public String getInfo39() {
		return this.info39;
	}

	public void setInfo39(String info39) {
		this.info39 = info39;
	}

	@Column(name = "INFO_40", length = 36)
	public String getInfo40() {
		return this.info40;
	}

	public void setInfo40(String info40) {
		this.info40 = info40;
	}

	@Column(name = "INFO_41", length = 1)
	public String getInfo41() {
		return this.info41;
	}

	public void setInfo41(String info41) {
		this.info41 = info41;
	}

	@Column(name = "INFO_42", length = 36)
	public String getInfo42() {
		return this.info42;
	}

	public void setInfo42(String info42) {
		this.info42 = info42;
	}

	@Column(name = "INFO_43", length = 36)
	public String getInfo43() {
		return this.info43;
	}

	public void setInfo43(String info43) {
		this.info43 = info43;
	}

	@Column(name = "INFO_44", length = 30)
	public String getInfo44() {
		return this.info44;
	}

	public void setInfo44(String info44) {
		this.info44 = info44;
	}

	@Column(name = "INFO_45", length = 1)
	public String getInfo45() {
		return this.info45;
	}

	public void setInfo45(String info45) {
		this.info45 = info45;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_46", length = 7)
	public Date getInfo46() {
		return this.info46;
	}

	public void setInfo46(Date info46) {
		this.info46 = info46;
	}

	@Column(name = "MODIFIER", length = 36)
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Column(name = "INFO_47")
	public String getInfo47() {
		return info47;
	}
	public void setInfo47(String info47) {
		this.info47 = info47;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_48")
	public Date getInfo48() {
		return info48;
	}
	public void setInfo48(Date info48) {
		this.info48 = info48;
	}
	@Column(name = "INFO_49",length = 30)
	public String getInfo49() {
		return info49;
	}

	public void setInfo49(String info49) {
		this.info49 = info49;
	}
	@Column(name = "INFO_50", length = 36)
	public String getInfo50() {
		return info50;
	}

	public void setInfo50(String info50) {
		this.info50 = info50;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_51")
	public Date getInfo51() {
		return info51;
	}

	public void setInfo51(Date info51) {
		this.info51 = info51;
	}
	@Column(name = "INFO_52")
	public String getInfo52() {
		return info52;
	}

	public void setInfo52(String info52) {
		this.info52 = info52;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_53")
	public Date getInfo53() {
		return info53;
	}

	public void setInfo53(Date info53) {
		this.info53 = info53;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_54")
	public Date getInfo54() {
		return info54;
	}

	public void setInfo54(Date info54) {
		this.info54 = info54;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_55")
	public Date getInfo55() {
		return info55;
	}

	public void setInfo55(Date info55) {
		this.info55 = info55;
	}
	@Column(name = "INFO_56",length=36)
	public String getInfo56() {
		return info56;
	}

	public void setInfo56(String info56) {
		this.info56 = info56;
	}
	@Column(name = "INFO_57",length=36)
	public String getInfo57() {
		return info57;
	}

	public void setInfo57(String info57) {
		this.info57 = info57;
	}
	@Column(name = "INFO_58",length=36)
	public String getInfo58() {
		return info58;
	}

	public void setInfo58(String info58) {
		this.info58 = info58;
	}
	@Column(name = "INFO_59",length=36)
	public String getInfo59() {
		return info59;
	}

	public void setInfo59(String info59) {
		this.info59 = info59;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_60")
	public Date getInfo60() {
		return info60;
	}

	public void setInfo60(Date info60) {
		this.info60 = info60;
	}
	@Column(name = "INFO_61",length=30)
	public String getInfo61() {
		return info61;
	}

	public void setInfo61(String info61) {
		this.info61 = info61;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_62")
	public Date getInfo62() {
		return info62;
	}

	public void setInfo62(Date info62) {
		this.info62 = info62;
	}
	@Column(name = "INFO_63",length=2)
	public String getInfo63() {
		return info63;
	}

	public void setInfo63(String info63) {
		this.info63 = info63;
	}
	@Column(name = "INFO_RESULT_63",length=2)
	public String getInfoResult() {
		return infoResult;
	}
	
	public void setInfoResult(String infoResult) {
		this.infoResult = infoResult;
	}
	@Column(name = "INFO_64",length=36)
	public String getInfo64() {
		return info64;
	}
	public void setInfo64(String info64) {
		this.info64 = info64;
	}
	@Column(name = "INFO_65",length=4)
	public String getInfo65() {
		return info65;
	}

	public void setInfo65(String info65) {
		this.info65 = info65;
	}
	@Column(name = "INFO_RESTRICT_65",length=30)
	public String getInfoRestrict65() {
		return infoRestrict65;
	}

	public void setInfoRestrict65(String infoRestrict65) {
		this.infoRestrict65 = infoRestrict65;
	}
	@Column(name = "INFO_OTHER_65",length=300)
	public String getInfoOther65() {
		return infoOther65;
	}

	public void setInfoOther65(String infoOther65) {
		this.infoOther65 = infoOther65;
	}
	@Column(name = "INFO_EXPLAIN_65",length=300)
	public String getInfoExplain65() {
		return infoExplain65;
	}

	public void setInfoExplain65(String infoExplain65) {
		this.infoExplain65 = infoExplain65;
	}
	@Column(name = "INFO_66",length=30)
	public String getInfo66() {
		return info66;
	}

	public void setInfo66(String info66) {
		this.info66 = info66;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_67")
	public Date getInfo67() {
		return info67;
	}

	public void setInfo67(Date info67) {
		this.info67 = info67;
	}
	@Column(name = "INFO_68", length=30)
	public String getInfo68() {
		return info68;
	}

	public void setInfo68(String info68) {
		this.info68 = info68;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_69")
	public Date getInfo69() {
		return info69;
	}

	public void setInfo69(Date info69) {
		this.info69 = info69;
	}
	@Column(name = "INFO_70", length=30)
	public String getInfo70() {
		return info70;
	}

	public void setInfo70(String info70) {
		this.info70 = info70;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_71")
	public Date getInfo71() {
		return info71;
	}

	public void setInfo71(Date info71) {
		this.info71 = info71;
	}
	@Column(name = "INFO_72", length=1)
	public String getInfo72() {
		return info72;
	}

	public void setInfo72(String info72) {
		this.info72 = info72;
	}
	@Column(name = "INFO_EXPLAIN_72", length=300)
	public String getInfoExplain72() {
		return infoExplain72;
	}

	public void setInfoExplain72(String infoExplain72) {
		this.infoExplain72 = infoExplain72;
	}
	@Column(name = "INFO_73", length=30)
	public String getInfo73() {
		return info73;
	}

	public void setInfo73(String info73) {
		this.info73 = info73;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_74")
	public Date getInfo74() {
		return info74;
	}

	public void setInfo74(Date info74) {
		this.info74 = info74;
	}
	@Column(name = "INFO_75", length=30)
	public String getInfo75() {
		return info75;
	}

	public void setInfo75(String info75) {
		this.info75 = info75;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_76")
	public Date getInfo76() {
		return info76;
	}

	public void setInfo76(Date info76) {
		this.info76 = info76;
	}
	@Column(name = "INFO_77", length=30)
	public String getInfo77() {
		return info77;
	}

	public void setInfo77(String info77) {
		this.info77 = info77;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_78")
	public Date getInfo78() {
		return info78;
	}

	public void setInfo78(Date info78) {
		this.info78 = info78;
	}
	@Column(name = "INFO_79", length=30)
	public String getInfo79() {
		return info79;
	}

	public void setInfo79(String info79) {
		this.info79 = info79;
	}
	@Column(name = "OTHER_DOC", length=300)
	public String getOtherDoc() {
		return otherDoc;
	}
	public void setOtherDoc(String otherDoc) {
		this.otherDoc = otherDoc;
	}
	@Column(name = "INFO_GRADE_65", length=30)
	public String getInfoGrade65() {
		return infoGrade65;
	}

	public void setInfoGrade65(String infoGrade65) {
		this.infoGrade65 = infoGrade65;
	}
}