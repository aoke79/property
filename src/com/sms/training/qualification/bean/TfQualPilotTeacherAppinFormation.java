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
 * 民用航空器飞行教员执照或等级申请表单  实体类
 * @author zhong
 * 2013-05-08
 */
@Entity
@Table(name="TF_QUAL_PILOT_TEACHER_FORM")
public class TfQualPilotTeacherAppinFormation {

	/** ID*/
	private String id;
	/** 申报人员明细*/
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	/** 飞行员hrid*/
	private String pilotid;
	/**驾驶员执照*/
	private String licene;  
	/** 所持有的航空器驾驶员执照与等级*/
	private String info1;
	/** 15a*/
	private String info15a;
	/** 15b*/
	private String info15b;
	/** 15c*/
	private String info15c;
	/** 15d*/
	private String info15d;
	/** 15e*/
	private String info15e;
	/** 型别等级*/
	private String aircraftType;
	/** 执照编号*/
	private String info2;
	/** 颁发日期*/
	private Date info3;
	/** 听说读写汉语*/
	private String info4;
	/** 英语无线电通信资格*/
	private String info5;
	/** 申请*/
	private String applicationItem;
	/** 22a*/
	private String info22a;
	/** 22b*/
	private String info22b;
	/** 22c*/
	private String info22c;
	/** 22d*/
	private String info22d;
	/** A教学原理*/
	private String infoA;
	/** 23a*/
	private String info23a;
	/** 23b*/
	private Date info23b;
	/** 23c*/
	private String info23c;
	/** 24a*/
	private String info24a;
	/** 24b*/
	private Date info24b;
	/** 25a*/
	private String info25a;
	/** 25b*/
	private String info25b;
	/** 25c*/
	private String info25c;
	/** B航空知识理论*/
	private String infoB;
	/** 26a*/
	private String info26a;
	/** 26b*/
	private Date info26b;
	/** 26c*/
	private String info26c;
	/** C教员教学记录*/
	private String infoC;
	/** 27a*/
	private String info27a;
	/** 27b*/
	private String info27b;
	/** 28a*/
	private String info28a;
	/** 28b*/
	private String info28b;
	/** D完成经批准*/
	private String infoD;
	/** 29a*/
	private String info29a;
	/** 29b*/
	private String info29b;
	/** 29c*/
	private String info29c;
	/** 29d*/
	private Date info29d;
	/** 申请人签字*/
	private String info30;
	/** 申请日期*/
	private Date info31;
	/** 飞行教员执照编号*/
	private String info32;
	/** 教员签字*/
	private String info33;
	/** 期满日期*/
	private Date info34;
	/** 教学能力*/
	private String info35;
	/** 工作单结论*/
	private String info36;
	/** 工作单结论说明*/
	private String info36reas;
	/** 考试地点*/
	private String info37;
	/** 考试日期*/
	private Date info38;
	/** 考试时间地面*/
	private Date info39a;
	/** 考试时间模拟机*/
	private Date info39b;
	/** 考试时间飞行*/
	private Date info39c;
	/** 被考执照或等级*/
	private String info40;
	/** 使用的航空器*/
	private String info41;
	/** 航空器登记号*/
	private String info42;
	/** 合格证编号*/
	private String info43;
	/** 委任期满日期*/
	private Date info44;
	/** 考试员签字*/
	private String info45;
	/** 地区管理局*/
	private String info46;
	/** 审查结论*/
	private String info47;
	/** 审查结论说明*/
	private String info47reas;
	/** 监察员签字*/
	private String info48;
	/** 审查日期*/
	private Date info49;
	/** 飞行职能部门负责人签字*/
	private String info50;
	/** 签发日期*/
	private Date info51;
	/** 临时执照制作人签字*/
	private String info52;
	/** 制作日期*/
	private Date info53;
	/** 执照是否颁发执照*/
	private String info54;
	/** 不颁发说明问题*/
	private String info54Reas;
	/** 民航局_监察员签字*/
	private String info55;
	/** 民航局_审查日期*/
	private Date info56;
	/** 民航局_执照管理部门签字*/
	private String info57;
	/** 民航局_签发日期*/
	private Date info58;
	/** 民航局_正式执照制作人签字*/
	private String info59;
	/** 民航局_制作时间*/
	private Date info60;
	/** 所附文件*/
	private String info61;
	/** 其他文件*/
	private String info61Others;
	/** 限制级别*/
	private String infoGrade65;
	/** 最后修改人*/
	private String modifier;
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Column(unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detailsid")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	@Column(name = "pilotid", length = 36)
	public String getPilotid() {
		return pilotid;
	}
	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}
	@Column(name="licene",length=1)
	public String getLicene() {
		return licene;
	}
	public void setLicene(String licene) {
		this.licene = licene;
	}
	@Column(name="info_1",length=1)
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	@Column(name="info_15a",length=1)
	public String getInfo15a() {
		return info15a;
	}
	public void setInfo15a(String info15a) {
		this.info15a = info15a;
	}
	@Column(name="info_15b",length=1)
	public String getInfo15b() {
		return info15b;
	}
	public void setInfo15b(String info15b) {
		this.info15b = info15b;
	}
	@Column(name="info_15c",length=1)
	public String getInfo15c() {
		return info15c;
	}
	public void setInfo15c(String info15c) {
		this.info15c = info15c;
	}
	@Column(name="info_15d",length=1)
	public String getInfo15d() {
		return info15d;
	}
	public void setInfo15d(String info15d) {
		this.info15d = info15d;
	}
	@Column(name="info_15e",length=1)
	public String getInfo15e() {
		return info15e;
	}
	public void setInfo15e(String info15e) {
		this.info15e = info15e;
	}
	@Column(name="Aircraft_Type",length=50)
	public String getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	@Column(name="info_2",length=36)
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_3")
	public Date getInfo3() {
		return info3;
	}
	public void setInfo3(Date info3) {
		this.info3 = info3;
	}
	@Column(name="info_4",length=1)
	public String getInfo4() {
		return info4;
	}
	public void setInfo4(String info4) {
		this.info4 = info4;
	}
	@Column(name="info_5",length=1)
	public String getInfo5() {
		return info5;
	}
	public void setInfo5(String info5) {
		this.info5 = info5;
	}
	@Column(name="application_item",length=1)
	public String getApplicationItem() {
		return applicationItem;
	}
	public void setApplicationItem(String applicationItem) {
		this.applicationItem = applicationItem;
	}
	@Column(name="info_22a",length=1)
	public String getInfo22a() {
		return info22a;
	}
	public void setInfo22a(String info22a) {
		this.info22a = info22a;
	}
	@Column(name="info_22b",length=1)
	public String getInfo22b() {
		return info22b;
	}
	public void setInfo22b(String info22b) {
		this.info22b = info22b;
	}
	@Column(name="info_22c",length=1)
	public String getInfo22c() {
		return info22c;
	}
	public void setInfo22c(String info22c) {
		this.info22c = info22c;
	}
	@Column(name="info_22d",length=1)
	public String getInfo22d() {
		return info22d;
	}
	public void setInfo22d(String info22d) {
		this.info22d = info22d;
	}
	@Column(name="info_A",length=1)
	public String getInfoA() {
		return infoA;
	}
	public void setInfoA(String infoA) {
		this.infoA = infoA;
	}
	@Column(name="info_23a",length=50)
	public String getInfo23a() {
		return info23a;
	}
	public void setInfo23a(String info23a) {
		this.info23a = info23a;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_23b")
	public Date getInfo23b() {
		return info23b;
	}
	public void setInfo23b(Date info23b) {
		this.info23b = info23b;
	}
	@Column(name="info_23c",length=100)
	public String getInfo23c() {
		return info23c;
	}
	public void setInfo23c(String info23c) {
		this.info23c = info23c;
	}
	@Column(name="info_24a",length=1)
	public String getInfo24a() {
		return info24a;
	}
	public void setInfo24a(String info24a) {
		this.info24a = info24a;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_24b")
	public Date getInfo24b() {
		return info24b;
	}
	public void setInfo24b(Date info24b) {
		this.info24b = info24b;
	}
	@Column(name="info_25a",length=100)
	public String getInfo25a() {
		return info25a;
	}
	public void setInfo25a(String info25a) {
		this.info25a = info25a;
	}
	@Column(name="info_25b",length=100)
	public String getInfo25b() {
		return info25b;
	}
	public void setInfo25b(String info25b) {
		this.info25b = info25b;
	}
	@Column(name="info_25c",length=100)
	public String getInfo25c() {
		return info25c;
	}
	public void setInfo25c(String info25c) {
		this.info25c = info25c;
	}
	@Column(name="info_B",length=1)
	public String getInfoB() {
		return infoB;
	}
	public void setInfoB(String infoB) {
		this.infoB = infoB;
	}
	@Column(name="info_26a",length=50)
	public String getInfo26a() {
		return info26a;
	}
	public void setInfo26a(String info26a) {
		this.info26a = info26a;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_26b")
	public Date getInfo26b() {
		return info26b;
	}
	public void setInfo26b(Date info26b) {
		this.info26b = info26b;
	}
	@Column(name="info_26c",length=100)
	public String getInfo26c() {
		return info26c;
	}
	public void setInfo26c(String info26c) {
		this.info26c = info26c;
	}
	@Column(name="info_C",length=1)
	public String getInfoC() {
		return infoC;
	}
	public void setInfoC(String infoC) {
		this.infoC = infoC;
	}
	@Column(name="info_27a",length=1)
	public String getInfo27a() {
		return info27a;
	}
	public void setInfo27a(String info27a) {
		this.info27a = info27a;
	}
	@Column(name="info_27b",length=1)
	public String getInfo27b() {
		return info27b;
	}
	public void setInfo27b(String info27b) {
		this.info27b = info27b;
	}
	@Column(name="info_28a",length=1)
	public String getInfo28a() {
		return info28a;
	}
	public void setInfo28a(String info28a) {
		this.info28a = info28a;
	}
	@Column(name="info_28b",length=1)
	public String getInfo28b() {
		return info28b;
	}
	public void setInfo28b(String info28b) {
		this.info28b = info28b;
	}
	@Column(name="info_D",length=1)
	public String getInfoD() {
		return infoD;
	}
	public void setInfoD(String infoD) {
		this.infoD = infoD;
	}
	@Column(name="info_29a",length=100)
	public String getInfo29a() {
		return info29a;
	}
	public void setInfo29a(String info29a) {
		this.info29a = info29a;
	}
	@Column(name="info_29b",length=100)
	public String getInfo29b() {
		return info29b;
	}
	public void setInfo29b(String info29b) {
		this.info29b = info29b;
	}
	@Column(name="info_29c",length=100)
	public String getInfo29c() {
		return info29c;
	}
	public void setInfo29c(String info29c) {
		this.info29c = info29c;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_29d")
	public Date getInfo29d() {
		return info29d;
	}
	public void setInfo29d(Date info29d) {
		this.info29d = info29d;
	}
	@Column(name="info_30",length=36)
	public String getInfo30() {
		return info30;
	}
	public void setInfo30(String info30) {
		this.info30 = info30;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_31")
	public Date getInfo31() {
		return info31;
	}
	public void setInfo31(Date info31) {
		this.info31 = info31;
	}
	@Column(name="info_32",length=30)
	public String getInfo32() {
		return info32;
	}
	public void setInfo32(String info32) {
		this.info32 = info32;
	}
	@Column(name="info_33",length=36)
	public String getInfo33() {
		return info33;
	}
	public void setInfo33(String info33) {
		this.info33 = info33;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_34")
	public Date getInfo34() {
		return info34;
	}
	public void setInfo34(Date info34) {
		this.info34 = info34;
	}
	@Column(name="info_35",length=1)
	public String getInfo35() {
		return info35;
	}
	public void setInfo35(String info35) {
		this.info35 = info35;
	}
	@Column(name="info_36",length=1)
	public String getInfo36() {
		return info36;
	}
	public void setInfo36(String info36) {
		this.info36 = info36;
	}
	@Column(name="info_36_reas",length=300)
	public String getInfo36reas() {
		return info36reas;
	}
	public void setInfo36reas(String info36reas) {
		this.info36reas = info36reas;
	}
	@Column(name="info_37",length=300)
	public String getInfo37() {
		return info37;
	}
	public void setInfo37(String info37) {
		this.info37 = info37;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_38")
	public Date getInfo38() {
		return info38;
	}
	public void setInfo38(Date info38) {
		this.info38 = info38;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_39a")
	public Date getInfo39a() {
		return info39a;
	}
	public void setInfo39a(Date info39a) {
		this.info39a = info39a;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_39b")
	public Date getInfo39b() {
		return info39b;
	}
	public void setInfo39b(Date info39b) {
		this.info39b = info39b;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_39c")
	public Date getInfo39c() {
		return info39c;
	}
	public void setInfo39c(Date info39c) {
		this.info39c = info39c;
	}
	@Column(name="info_40",length=36)
	public String getInfo40() {
		return info40;
	}
	public void setInfo40(String info40) {
		this.info40 = info40;
	}
	@Column(name="info_41",length=36)
	public String getInfo41() {
		return info41;
	}
	public void setInfo41(String info41) {
		this.info41 = info41;
	}
	@Column(name="info_42",length=36)
	public String getInfo42() {
		return info42;
	}
	public void setInfo42(String info42) {
		this.info42 = info42;
	}
	@Column(name="info_43",length=36)
	public String getInfo43() {
		return info43;
	}
	public void setInfo43(String info43) {
		this.info43 = info43;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_44")
	public Date getInfo44() {
		return info44;
	}
	public void setInfo44(Date info44) {
		this.info44 = info44;
	}
	@Column(name="info_45",length=30)
	public String getInfo45() {
		return info45;
	}
	public void setInfo45(String info45) {
		this.info45 = info45;
	}
	@Column(name="info_46",length=36)
	public String getInfo46() {
		return info46;
	}
	public void setInfo46(String info46) {
		this.info46 = info46;
	}
	@Column(name="info_47",length=1)
	public String getInfo47() {
		return info47;
	}
	public void setInfo47(String info47) {
		this.info47 = info47;
	}
	@Column(name="info_47_reas",length=300)
	public String getInfo47reas() {
		return info47reas;
	}
	public void setInfo47reas(String info47reas) {
		this.info47reas = info47reas;
	}
	@Column(name="info_48",length=30)
	public String getInfo48() {
		return info48;
	}
	public void setInfo48(String info48) {
		this.info48 = info48;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_49")
	public Date getInfo49() {
		return info49;
	}
	public void setInfo49(Date info49) {
		this.info49 = info49;
	}
	@Column(name="info_50",length=30)
	public String getInfo50() {
		return info50;
	}
	public void setInfo50(String info50) {
		this.info50 = info50;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_51")
	public Date getInfo51() {
		return info51;
	}
	public void setInfo51(Date info51) {
		this.info51 = info51;
	}
	@Column(name="info_52",length=30)
	public String getInfo52() {
		return info52;
	}
	public void setInfo52(String info52) {
		this.info52 = info52;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_53")
	public Date getInfo53() {
		return info53;
	}
	public void setInfo53(Date info53) {
		this.info53 = info53;
	}
	@Column(name="info_54",length=1)
	public String getInfo54() {
		return info54;
	}
	public void setInfo54(String info54) {
		this.info54 = info54;
	}
	@Column(name="info_54_Reas",length=30)
	public String getInfo54Reas() {
		return info54Reas;
	}
	public void setInfo54Reas(String info54Reas) {
		this.info54Reas = info54Reas;
	}
	@Column(name="info_55",length=30)
	public String getInfo55() {
		return info55;
	}
	public void setInfo55(String info55) {
		this.info55 = info55;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_56")
	public Date getInfo56() {
		return info56;
	}
	public void setInfo56(Date info56) {
		this.info56 = info56;
	}
	@Column(name="info_57",length=30)
	public String getInfo57() {
		return info57;
	}
	public void setInfo57(String info57) {
		this.info57 = info57;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_58")
	public Date getInfo58() {
		return info58;
	}
	public void setInfo58(Date info58) {
		this.info58 = info58;
	}
	@Column(name="info_59",length=30)
	public String getInfo59() {
		return info59;
	}
	public void setInfo59(String info59) {
		this.info59 = info59;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="info_60")
	public Date getInfo60() {
		return info60;
	}
	public void setInfo60(Date info60) {
		this.info60 = info60;
	}
	@Column(name="info_61",length=30)
	public String getInfo61() {
		return info61;
	}
	public void setInfo61(String info61) {
		this.info61 = info61;
	}
	@Column(name="info_61_others",length=300)
	public String getInfo61Others() {
		return info61Others;
	}
	@Column(name="info_grade_65",length=30)
	public String getInfoGrade65() {
		return infoGrade65;
	}

	public void setInfo61Others(String info61Others) {
		this.info61Others = info61Others;
	}
	public void setInfoGrade65(String infoGrade65) {
		this.infoGrade65 = infoGrade65;
	}
	@Column(name="modifier",length=36)
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
}
