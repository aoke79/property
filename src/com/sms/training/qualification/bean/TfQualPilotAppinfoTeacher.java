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
 * 运输航空公司飞行教员执照和等级申请表
 */
@Entity
@Table(name = "TF_QUAL_PILOT_APPINFO_TEACHER")
public class TfQualPilotAppinfoTeacher implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	//申请表ID
	private String informationid;
	//申报人员明细ID
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//飞行员ID
	private String pilotid;
	//所持有的飞机驾驶员执照与等级
	private String licenserat;
	//航空器型别等级代码
	private String licenseratno;
	//执照编号
	private String licenseno;
	//颁发日期
	private Date licensedate;
	//是否持有CAAC颁发的体检合格证
	private String info1;
	//所持有的飞机驾驶员执照与等级是否单发
	private String licensesingle;
	//所持有的飞机驾驶员等级
	private String licensegradeno;
	//体检合格日期
	private Date info3;
	//体检合格证等级
	private String info2;
	//英语无线电通信实践考试合格
	private String info6;
	//陆空通话考试是否合格
	private String info51;
	//陆空通话考试合格通过时间
	private Date info51date;
	//飞行专业英语考试是否合格
	private String info52;
	//飞行专业英语考试合格通过时间
	private Date info52date;
	//听说读写汉语
	private String info4;
	//单飞地区
	private String info7;
	//单飞航线
	private String info8;
	//申请型别等级飞行教员执照种类
	private String licensetype;
	//型别等级代码
	private String info11;
	//申请人签名
	private String info12;
	//申请日期
	private Date info13;
	//部门推荐执照种类
	private String info14;
	//部门推荐型别等级代码
	private String info15;
	//部门推荐审核人签字
	private String info16;
	//部门推荐审核日期
	private Date info17;
	//部门推荐负责人签字
	private String info18;
	//部门推荐负责人签字日期
	private Date info19;
	//所在地区管理局
	private String info20;
	//理论培训是否通过
	private String info21;
	//理论培训日期
	private Date info21date;
	//模拟机训练是否通过
	private String info22;
	//模拟机训练日期
	private Date info22date;
	//机型飞行培训是否通过
	private String info23;
	//机型飞行培训日期
	private Date info23date;
	//检察员_是否同意签发
	private String info24yn;
	//监察员飞行教员执照类别
	private String info24;
	//监察员型别等级
	private String info25;
	//监察员不同意说明
	private String info26;
	//监察员签字
	private String info27;
	//监察员签字日期
	private Date info28;
	//监察员部门负责人签字
	private String info29;
	//监察员部门负责人签字日期
	private Date info30;
	//监察员制作人签字
	private String info31;
	//监察员制作人签字日期
	private Date info32;
	//民航总局是否同意颁发执照
	private String info33;
	//民航总局问题说明
	private String info34;
	//总局监察员签字
	private String info35;
	//总局审核日期
	private Date info36;
	//总局负责人签字
	private String info37;
	//总局签发日期
	private Date info38;
	//总局制作人签字
	private String info39;
	//总局制作日期
	private Date info40;
	//所附文件
	private String info41;
	//限制级别
	private String infograde42;
	//最后修改人
	private String modifier;
	
	@Id
	@GeneratedValue(generator="type-uuid")
	@GenericGenerator(name="type-uuid",strategy="uuid")
	@Column(name = "INFORMATIONID")
	public String getInformationid() {
		return informationid;
	}
	public void setInformationid(String informationid) {
		this.informationid = informationid;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILSID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	
	@Column(name="PILOTID")
	public String getPilotid() {
		return pilotid;
	}
	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}
	@Column(name="LICENSE_RAT")
	public String getLicenserat() {
		return licenserat;
	}
	public void setLicenserat(String licenserat) {
		this.licenserat = licenserat;
	}
	@Column(name="LICENSE_RAT_NO")
	public String getLicenseratno() {
		return licenseratno;
	}
	public void setLicenseratno(String licenseratno) {
		this.licenseratno = licenseratno;
	}
	@Column(name="LICENSE_NO")
	public String getLicenseno() {
		return licenseno;
	}
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "LICENSE_DATE")
	public Date getLicensedate() {
		return licensedate;
	}
	public void setLicensedate(Date licensedate) {
		this.licensedate = licensedate;
	}
	@Column(name="INFO_1")
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_3")
	public Date getInfo3() {
		return info3;
	}
	public void setInfo3(Date info3) {
		this.info3 = info3;
	}
	@Column(name="INFO_2")
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	@Column(name="INFO_6")
	public String getInfo6() {
		return info6;
	}
	public void setInfo6(String info6) {
		this.info6 = info6;
	}
	@Column(name="INFO_5_1")
	public String getInfo51() {
		return info51;
	}
	public void setInfo51(String info51) {
		this.info51 = info51;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_5_1_DATE")
	public Date getInfo51date() {
		return info51date;
	}
	public void setInfo51date(Date info51date) {
		this.info51date = info51date;
	}
	@Column(name="INFO_5_2")
	public String getInfo52() {
		return info52;
	}
	public void setInfo52(String info52) {
		this.info52 = info52;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_5_2_DATE")
	public Date getInfo52date() {
		return info52date;
	}
	public void setInfo52date(Date info52date) {
		this.info52date = info52date;
	}
	@Column(name="INFO_4")
	public String getInfo4() {
		return info4;
	}
	public void setInfo4(String info4) {
		this.info4 = info4;
	}
	@Column(name="INFO_7")
	public String getInfo7() {
		return info7;
	}
	public void setInfo7(String info7) {
		this.info7 = info7;
	}
	@Column(name="INFO_8")
	public String getInfo8() {
		return info8;
	}
	public void setInfo8(String info8) {
		this.info8 = info8;
	}
	@Column(name="LICENSE_TYPE")
	public String getLicensetype() {
		return licensetype;
	}
	public void setLicensetype(String licensetype) {
		this.licensetype = licensetype;
	}
	@Column(name="INFO_11")
	public String getInfo11() {
		return info11;
	}
	public void setInfo11(String info11) {
		this.info11 = info11;
	}
	@Column(name="INFO_12")
	public String getInfo12() {
		return info12;
	}
	public void setInfo12(String info12) {
		this.info12 = info12;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_13")
	public Date getInfo13() {
		return info13;
	}
	public void setInfo13(Date info13) {
		this.info13 = info13;
	}
	@Column(name="INFO_14")
	public String getInfo14() {
		return info14;
	}
	public void setInfo14(String info14) {
		this.info14 = info14;
	}
	@Column(name="INFO_15")
	public String getInfo15() {
		return info15;
	}
	public void setInfo15(String info15) {
		this.info15 = info15;
	}
	@Column(name="INFO_16")
	public String getInfo16() {
		return info16;
	}
	public void setInfo16(String info16) {
		this.info16 = info16;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_17")
	public Date getInfo17() {
		return info17;
	}
	public void setInfo17(Date info17) {
		this.info17 = info17;
	}
	@Column(name = "INFO_18")
	public String getInfo18() {
		return info18;
	}
	public void setInfo18(String info18) {
		this.info18 = info18;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_19")
	public Date getInfo19() {
		return info19;
	}
	public void setInfo19(Date info19) {
		this.info19 = info19;
	}
	@Column(name = "INFO_20")
	public String getInfo20() {
		return info20;
	}
	public void setInfo20(String info20) {
		this.info20 = info20;
	}
	@Column(name = "INFO_21")
	public String getInfo21() {
		return info21;
	}
	public void setInfo21(String info21) {
		this.info21 = info21;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_21_DATE")
	public Date getInfo21date() {
		return info21date;
	}
	public void setInfo21date(Date info21date) {
		this.info21date = info21date;
	}
	@Column(name = "INFO_22")
	public String getInfo22() {
		return info22;
	}
	public void setInfo22(String info22) {
		this.info22 = info22;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_22_DATE")
	public Date getInfo22date() {
		return info22date;
	}
	public void setInfo22date(Date info22date) {
		this.info22date = info22date;
	}
	@Column(name = "INFO_23")
	public String getInfo23() {
		return info23;
	}
	public void setInfo23(String info23) {
		this.info23 = info23;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_23_DATE")
	public Date getInfo23date() {
		return info23date;
	}
	public void setInfo23date(Date info23date) {
		this.info23date = info23date;
	}
	@Column(name = "INFO_24")
	public String getInfo24() {
		return info24;
	}
	public void setInfo24(String info24) {
		this.info24 = info24;
	}
	@Column(name = "INFO_25")
	public String getInfo25() {
		return info25;
	}
	public void setInfo25(String info25) {
		this.info25 = info25;
	}
	@Column(name = "INFO_26")
	public String getInfo26() {
		return info26;
	}
	public void setInfo26(String info26) {
		this.info26 = info26;
	}
	@Column(name = "INFO_27")
	public String getInfo27() {
		return info27;
	}
	public void setInfo27(String info27) {
		this.info27 = info27;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_28")
	public Date getInfo28() {
		return info28;
	}
	public void setInfo28(Date info28) {
		this.info28 = info28;
	}
	@Column(name = "INFO_29")
	public String getInfo29() {
		return info29;
	}
	public void setInfo29(String info29) {
		this.info29 = info29;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_30")
	public Date getInfo30() {
		return info30;
	}
	public void setInfo30(Date info30) {
		this.info30 = info30;
	}
	@Column(name = "INFO_31")
	public String getInfo31() {
		return info31;
	}
	public void setInfo31(String info31) {
		this.info31 = info31;
	}
	@Column(name = "INFO_32")
	public Date getInfo32() {
		return info32;
	}
	public void setInfo32(Date info32) {
		this.info32 = info32;
	}
	@Column(name = "INFO_33")
	public String getInfo33() {
		return info33;
	}
	public void setInfo33(String info33) {
		this.info33 = info33;
	}
	@Column(name = "INFO_34")
	public String getInfo34() {
		return info34;
	}
	public void setInfo34(String info34) {
		this.info34 = info34;
	}
	@Column(name = "INFO_35")
	public String getInfo35() {
		return info35;
	}
	public void setInfo35(String info35) {
		this.info35 = info35;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_36")
	public Date getInfo36() {
		return info36;
	}
	public void setInfo36(Date info36) {
		this.info36 = info36;
	}
	@Column(name = "INFO_37")
	public String getInfo37() {
		return info37;
	}
	public void setInfo37(String info37) {
		this.info37 = info37;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_38")
	public Date getInfo38() {
		return info38;
	}
	public void setInfo38(Date info38) {
		this.info38 = info38;
	}
	@Column(name = "INFO_39")
	public String getInfo39() {
		return info39;
	}
	public void setInfo39(String info39) {
		this.info39 = info39;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INFO_40")
	public Date getInfo40() {
		return info40;
	}
	public void setInfo40(Date info40) {
		this.info40 = info40;
	}
	@Column(name = "INFO_41")
	public String getInfo41() {
		return info41;
	}
	public void setInfo41(String info41) {
		this.info41 = info41;
	}
	@Column(name = "INFO_GRADE_42")
	public String getInfograde42() {
		return infograde42;
	}
	public void setInfograde42(String infograde42) {
		this.infograde42 = infograde42;
	}
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	@Column(name = "INFO_24_YN")
	public String getInfo24yn() {
		return info24yn;
	}
	public void setInfo24yn(String info24yn) {
		this.info24yn = info24yn;
	}
	@Column(name = "LICENSE_SINGLE")
	public String getLicensesingle() {
		return licensesingle;
	}
	public void setLicensesingle(String licensesingle) {
		this.licensesingle = licensesingle;
	}
	
	@Column(name = "LICENSE_GRADE_NO")
	public String getLicensegradeno() {
		return licensegradeno;
	}
	public void setLicensegradeno(String licensegradeno) {
		this.licensegradeno = licensegradeno;
	}
	
	
}
