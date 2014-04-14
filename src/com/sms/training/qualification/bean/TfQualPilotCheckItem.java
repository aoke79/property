package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualPilotCheckItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_CHECK_ITEM")
public class TfQualPilotCheckItem implements java.io.Serializable {

    // 检查工作单ID
	private String checkid;
	
	// 飞行员ID
	private String pilotid;
	
	// 申报人员明细
	private String detailsid;
	
	// 运行基地
	private String runbase;
	
	// 类别等级
	private String checkCategory;
	
	// 级别等级
	private String checkLevel;
	
	// 型别等级
	private String checkType;
	
	// 熟练检查类型
	private String checkGrade;
	
	// 熟练检查日期
	private Date checkDate;
	
	// 地点
	private String checkLocation;
	
	// 所用机型
	private String checkAtrid;
	
	// 航空器型号
	private String checkModel;
	
	// CAAC模拟机编号
	private String checkModel1;
	
	// 飞机注册号、级别
	private String registCode;
	
	// 飞机级别
	private String registCode1;
	
	// 评语
	private String ksyComment;
	 
	// 结论时间
	private Date completionDate;
	
	// 2部的要求
	private String claim;
	
	// 熟练检查
	private String checkName;
	
	// 考试员合格编号
	private String ksyNum;
	
	// 签名
	private String ksyCode;
	
	// 日期
	private Date ksyDate;
	
	// 监察员审查意见
	private String jcyYj;
	
	// 监察员签字
	private String jcyCode;
	
	// 监察员签字日期
	private Date jcyDate;
	
	private String checkItem1;
	private String checkItem2;
	private String checkItem3;
	private String checkItem4;
	private String checkItem5;
	private String checkItem6;
	private String checkItem7;
	private String checkItem8;
	private String checkItem9;
	private String checkItem10;
	private String checkItem11;
	private String checkItem12;
	private String checkItem13;
	private String checkItem14;
	private String checkItem15;
	private String checkItem16;
	private String checkItem17;
	private String checkItem18;
	private String checkItem19;
	private String checkItem20;
	private String checkItem21;
	private String checkItem22;
	private String checkItem23;
	private String checkItem24;
	private String checkItem25;
	private String checkItem26;
	private String checkItem27;
	private String checkItem28;
	private String checkItem29;
	private String checkItem30;
	private String checkItem31;
	private String checkItem32;
	private String checkItem33;
	private String checkItem34;
	private String checkItem35;
	private String checkItem36;
	private String checkItem37;
	private String checkItem38;
	private String checkItem39;
	private String checkItem40;
	private String checkItem41;
	private String checkItem42;
	private String checkItem43;
	private String checkItem44;
	private String checkItem45;
	private String checkItem46;
	
	private String checkItemName39;
    private String checkItemName46;
    
	// Constructors

	/** default constructor */
	public TfQualPilotCheckItem() {
	}

	/** minimal constructor */
	public TfQualPilotCheckItem(String checkid) {
		this.checkid = checkid;
	}

	/** full constructor */
	public TfQualPilotCheckItem(String checkid, String pilotid,
			String detailsid, String runbase, String checkCategory,
			String checkLevel, String checkType, String checkGrade,
			Date checkDate, String checkLocation, String checkAtrid,
			String checkModel, String registCode, String checkModel1, String registCode1,String ksyComment,
			Date completionDate, String claim, String checkName, String ksyNum,
			String ksyCode, Date ksyDate, String jcyYj, String jcyCode,
			Date jcyDate, String checkItem1, String checkItem2,
			String checkItem3, String checkItem4, String checkItem5,
			String checkItem6, String checkItem7, String checkItem8,
			String checkItem9, String checkItem10, String checkItem11,
			String checkItem12, String checkItem13, String checkItem14,
			String checkItem15, String checkItem16, String checkItem17,
			String checkItem18, String checkItem19, String checkItem20,
			String checkItem21, String checkItem22, String checkItem23,
			String checkItem24, String checkItem25, String checkItem26,
			String checkItem27, String checkItem28, String checkItem29,
			String checkItem30, String checkItem31, String checkItem32,
			String checkItem33, String checkItem34, String checkItem35,
			String checkItem36, String checkItem37, String checkItem38,
			String checkItem39, String checkItem40, String checkItem41,
			String checkItem42, String checkItem43, String checkItem44,
			String checkItem45, String checkItem46,String checkItemName39,String checkItemName46) {
		this.checkid = checkid;
		this.pilotid = pilotid;
		this.detailsid = detailsid;
		this.runbase = runbase;
		this.checkCategory = checkCategory;
		this.checkLevel = checkLevel;
		this.checkType = checkType;
		this.checkGrade = checkGrade;
		this.checkDate = checkDate;
		this.checkLocation = checkLocation;
		this.checkAtrid = checkAtrid;
		this.checkModel = checkModel;
		this.registCode = registCode;
		this.checkModel1 = checkModel;
		this.registCode1 = registCode;
		this.ksyComment = ksyComment;
		this.completionDate = completionDate;
		this.claim = claim;
		this.checkName = checkName;
		this.ksyNum = ksyNum;
		this.ksyCode = ksyCode;
		this.ksyDate = ksyDate;
		this.jcyYj = jcyYj;
		this.jcyCode = jcyCode;
		this.jcyDate = jcyDate;
		this.checkItem1 = checkItem1;
		this.checkItem2 = checkItem2;
		this.checkItem3 = checkItem3;
		this.checkItem4 = checkItem4;
		this.checkItem5 = checkItem5;
		this.checkItem6 = checkItem6;
		this.checkItem7 = checkItem7;
		this.checkItem8 = checkItem8;
		this.checkItem9 = checkItem9;
		this.checkItem10 = checkItem10;
		this.checkItem11 = checkItem11;
		this.checkItem12 = checkItem12;
		this.checkItem13 = checkItem13;
		this.checkItem14 = checkItem14;
		this.checkItem15 = checkItem15;
		this.checkItem16 = checkItem16;
		this.checkItem17 = checkItem17;
		this.checkItem18 = checkItem18;
		this.checkItem19 = checkItem19;
		this.checkItem20 = checkItem20;
		this.checkItem21 = checkItem21;
		this.checkItem22 = checkItem22;
		this.checkItem23 = checkItem23;
		this.checkItem24 = checkItem24;
		this.checkItem25 = checkItem25;
		this.checkItem26 = checkItem26;
		this.checkItem27 = checkItem27;
		this.checkItem28 = checkItem28;
		this.checkItem29 = checkItem29;
		this.checkItem30 = checkItem30;
		this.checkItem31 = checkItem31;
		this.checkItem32 = checkItem32;
		this.checkItem33 = checkItem33;
		this.checkItem34 = checkItem34;
		this.checkItem35 = checkItem35;
		this.checkItem36 = checkItem36;
		this.checkItem37 = checkItem37;
		this.checkItem38 = checkItem38;
		this.checkItem39 = checkItem39;
		this.checkItem40 = checkItem40;
		this.checkItem41 = checkItem41;
		this.checkItem42 = checkItem42;
		this.checkItem43 = checkItem43;
		this.checkItem44 = checkItem44;
		this.checkItem45 = checkItem45;
		this.checkItem46 = checkItem46;
		this.checkItemName39 = checkItemName39;
		this.checkItemName46 = checkItemName46;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "CHECKID", unique = true, nullable = false, length = 36)
	public String getCheckid() {
		return this.checkid;
	}

	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}

	@Column(name = "PILOTID", length = 36)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Column(name = "DETAILSID", length = 36)
	public String getDetailsid() {
		return this.detailsid;
	}

	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}

	@Column(name = "RUNBASE", length = 60)
	public String getRunbase() {
		return this.runbase;
	}

	public void setRunbase(String runbase) {
		this.runbase = runbase;
	}

	@Column(name = "CHECK_CATEGORY", length = 6)
	public String getCheckCategory() {
		return this.checkCategory;
	}

	public void setCheckCategory(String checkCategory) {
		this.checkCategory = checkCategory;
	}

	@Column(name = "CHECK_LEVEL", length = 20)
	public String getCheckLevel() {
		return this.checkLevel;
	}

	public void setCheckLevel(String checkLevel) {
		this.checkLevel = checkLevel;
	}

	@Column(name = "CHECK_TYPE", length = 10)
	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	@Column(name = "CHECK_GRADE", length = 3)
	public String getCheckGrade() {
		return this.checkGrade;
	}

	public void setCheckGrade(String checkGrade) {
		this.checkGrade = checkGrade;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE", length = 7)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "CHECK_LOCATION", length = 60)
	public String getCheckLocation() {
		return this.checkLocation;
	}

	public void setCheckLocation(String checkLocation) {
		this.checkLocation = checkLocation;
	}

	@Column(name = "CHECK_ATRID", length = 3)
	public String getCheckAtrid() {
		return this.checkAtrid;
	}

	public void setCheckAtrid(String checkAtrid) {
		this.checkAtrid = checkAtrid;
	}

	@Column(name = "CHECK_MODEL", length = 20)
	public String getCheckModel() {
	    return this.checkModel;
	}
	
	public void setCheckModel(String checkModel) {
	    this.checkModel = checkModel;
	}
	
	@Column(name = "CHECK_MODEL1", length = 20)
	public String getCheckModel1() {
		return this.checkModel1;
	}

	public void setCheckModel1(String checkModel1) {
		this.checkModel1 = checkModel1;
	}

	@Column(name = "REGIST_CODE", length = 10)
	public String getRegistCode() {
	    return this.registCode;
	}
	
	public void setRegistCode(String registCode) {
	    this.registCode = registCode;
	}
	
	@Column(name = "REGIST_CODE1", length = 10)
	public String getRegistCode1() {
		return this.registCode1;
	}

	public void setRegistCode1(String registCode1) {
		this.registCode1 = registCode1;
	}

	@Column(name = "KSY_COMMENT", length = 150)
	public String getKsyComment() {
		return this.ksyComment;
	}

	public void setKsyComment(String ksyComment) {
		this.ksyComment = ksyComment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COMPLETION_DATE", length = 7)
	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	@Column(name = "CLAIM", length = 60)
	public String getClaim() {
		return this.claim;
	}

	public void setClaim(String claim) {
		this.claim = claim;
	}

	@Column(name = "CHECK_NAME", length = 60)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Column(name = "KSY_NUM", length = 15)
	public String getKsyNum() {
		return this.ksyNum;
	}

	public void setKsyNum(String ksyNum) {
		this.ksyNum = ksyNum;
	}

	@Column(name = "KSY_CODE", length = 32)
	public String getKsyCode() {
		return this.ksyCode;
	}

	public void setKsyCode(String ksyCode) {
		this.ksyCode = ksyCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KSY_DATE", length = 7)
	public Date getKsyDate() {
		return this.ksyDate;
	}

	public void setKsyDate(Date ksyDate) {
		this.ksyDate = ksyDate;
	}

	@Column(name = "JCY_YJ", length = 30)
	public String getJcyYj() {
		return this.jcyYj;
	}

	public void setJcyYj(String jcyYj) {
		this.jcyYj = jcyYj;
	}

	@Column(name = "JCY_CODE", length = 32)
	public String getJcyCode() {
		return this.jcyCode;
	}

	public void setJcyCode(String jcyCode) {
		this.jcyCode = jcyCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JCY_DATE", length = 7)
	public Date getJcyDate() {
		return this.jcyDate;
	}

	public void setJcyDate(Date jcyDate) {
		this.jcyDate = jcyDate;
	}

	@Column(name = "CHECK_ITEM1", length = 1)
	public String getCheckItem1() {
		return this.checkItem1;
	}

	public void setCheckItem1(String checkItem1) {
		this.checkItem1 = checkItem1;
	}

	@Column(name = "CHECK_ITEM2", length = 1)
	public String getCheckItem2() {
		return this.checkItem2;
	}

	public void setCheckItem2(String checkItem2) {
		this.checkItem2 = checkItem2;
	}

	@Column(name = "CHECK_ITEM3", length = 1)
	public String getCheckItem3() {
		return this.checkItem3;
	}

	public void setCheckItem3(String checkItem3) {
		this.checkItem3 = checkItem3;
	}

	@Column(name = "CHECK_ITEM4", length = 1)
	public String getCheckItem4() {
		return this.checkItem4;
	}

	public void setCheckItem4(String checkItem4) {
		this.checkItem4 = checkItem4;
	}

	@Column(name = "CHECK_ITEM5", length = 1)
	public String getCheckItem5() {
		return this.checkItem5;
	}

	public void setCheckItem5(String checkItem5) {
		this.checkItem5 = checkItem5;
	}

	@Column(name = "CHECK_ITEM6", length = 1)
	public String getCheckItem6() {
		return this.checkItem6;
	}

	public void setCheckItem6(String checkItem6) {
		this.checkItem6 = checkItem6;
	}

	@Column(name = "CHECK_ITEM7", length = 1)
	public String getCheckItem7() {
		return this.checkItem7;
	}

	public void setCheckItem7(String checkItem7) {
		this.checkItem7 = checkItem7;
	}

	@Column(name = "CHECK_ITEM8", length = 1)
	public String getCheckItem8() {
		return this.checkItem8;
	}

	public void setCheckItem8(String checkItem8) {
		this.checkItem8 = checkItem8;
	}

	@Column(name = "CHECK_ITEM9", length = 1)
	public String getCheckItem9() {
		return this.checkItem9;
	}

	public void setCheckItem9(String checkItem9) {
		this.checkItem9 = checkItem9;
	}

	@Column(name = "CHECK_ITEM10", length = 1)
	public String getCheckItem10() {
		return this.checkItem10;
	}

	public void setCheckItem10(String checkItem10) {
		this.checkItem10 = checkItem10;
	}

	@Column(name = "CHECK_ITEM11", length = 1)
	public String getCheckItem11() {
		return this.checkItem11;
	}

	public void setCheckItem11(String checkItem11) {
		this.checkItem11 = checkItem11;
	}

	@Column(name = "CHECK_ITEM12", length = 1)
	public String getCheckItem12() {
		return this.checkItem12;
	}

	public void setCheckItem12(String checkItem12) {
		this.checkItem12 = checkItem12;
	}

	@Column(name = "CHECK_ITEM13", length = 1)
	public String getCheckItem13() {
		return this.checkItem13;
	}

	public void setCheckItem13(String checkItem13) {
		this.checkItem13 = checkItem13;
	}

	@Column(name = "CHECK_ITEM14", length = 1)
	public String getCheckItem14() {
		return this.checkItem14;
	}

	public void setCheckItem14(String checkItem14) {
		this.checkItem14 = checkItem14;
	}

	@Column(name = "CHECK_ITEM15", length = 1)
	public String getCheckItem15() {
		return this.checkItem15;
	}

	public void setCheckItem15(String checkItem15) {
		this.checkItem15 = checkItem15;
	}

	@Column(name = "CHECK_ITEM16", length = 1)
	public String getCheckItem16() {
		return this.checkItem16;
	}

	public void setCheckItem16(String checkItem16) {
		this.checkItem16 = checkItem16;
	}

	@Column(name = "CHECK_ITEM17", length = 1)
	public String getCheckItem17() {
		return this.checkItem17;
	}

	public void setCheckItem17(String checkItem17) {
		this.checkItem17 = checkItem17;
	}

	@Column(name = "CHECK_ITEM18", length = 1)
	public String getCheckItem18() {
		return this.checkItem18;
	}

	public void setCheckItem18(String checkItem18) {
		this.checkItem18 = checkItem18;
	}

	@Column(name = "CHECK_ITEM19", length = 1)
	public String getCheckItem19() {
		return this.checkItem19;
	}

	public void setCheckItem19(String checkItem19) {
		this.checkItem19 = checkItem19;
	}

	@Column(name = "CHECK_ITEM20", length = 1)
	public String getCheckItem20() {
		return this.checkItem20;
	}

	public void setCheckItem20(String checkItem20) {
		this.checkItem20 = checkItem20;
	}

	@Column(name = "CHECK_ITEM21", length = 1)
	public String getCheckItem21() {
		return this.checkItem21;
	}

	public void setCheckItem21(String checkItem21) {
		this.checkItem21 = checkItem21;
	}

	@Column(name = "CHECK_ITEM22", length = 1)
	public String getCheckItem22() {
		return this.checkItem22;
	}

	public void setCheckItem22(String checkItem22) {
		this.checkItem22 = checkItem22;
	}

	@Column(name = "CHECK_ITEM23", length = 1)
	public String getCheckItem23() {
		return this.checkItem23;
	}

	public void setCheckItem23(String checkItem23) {
		this.checkItem23 = checkItem23;
	}

	@Column(name = "CHECK_ITEM24", length = 1)
	public String getCheckItem24() {
		return this.checkItem24;
	}

	public void setCheckItem24(String checkItem24) {
		this.checkItem24 = checkItem24;
	}

	@Column(name = "CHECK_ITEM25", length = 1)
	public String getCheckItem25() {
		return this.checkItem25;
	}

	public void setCheckItem25(String checkItem25) {
		this.checkItem25 = checkItem25;
	}

	@Column(name = "CHECK_ITEM26", length = 1)
	public String getCheckItem26() {
		return this.checkItem26;
	}

	public void setCheckItem26(String checkItem26) {
		this.checkItem26 = checkItem26;
	}

	@Column(name = "CHECK_ITEM27", length = 1)
	public String getCheckItem27() {
		return this.checkItem27;
	}

	public void setCheckItem27(String checkItem27) {
		this.checkItem27 = checkItem27;
	}

	@Column(name = "CHECK_ITEM28", length = 1)
	public String getCheckItem28() {
		return this.checkItem28;
	}

	public void setCheckItem28(String checkItem28) {
		this.checkItem28 = checkItem28;
	}

	@Column(name = "CHECK_ITEM29", length = 1)
	public String getCheckItem29() {
		return this.checkItem29;
	}

	public void setCheckItem29(String checkItem29) {
		this.checkItem29 = checkItem29;
	}

	@Column(name = "CHECK_ITEM30", length = 1)
	public String getCheckItem30() {
		return this.checkItem30;
	}

	public void setCheckItem30(String checkItem30) {
		this.checkItem30 = checkItem30;
	}

	@Column(name = "CHECK_ITEM31", length = 1)
	public String getCheckItem31() {
		return this.checkItem31;
	}

	public void setCheckItem31(String checkItem31) {
		this.checkItem31 = checkItem31;
	}

	@Column(name = "CHECK_ITEM32", length = 1)
	public String getCheckItem32() {
		return this.checkItem32;
	}

	public void setCheckItem32(String checkItem32) {
		this.checkItem32 = checkItem32;
	}

	@Column(name = "CHECK_ITEM33", length = 1)
	public String getCheckItem33() {
		return this.checkItem33;
	}

	public void setCheckItem33(String checkItem33) {
		this.checkItem33 = checkItem33;
	}

	@Column(name = "CHECK_ITEM34", length = 1)
	public String getCheckItem34() {
		return this.checkItem34;
	}

	public void setCheckItem34(String checkItem34) {
		this.checkItem34 = checkItem34;
	}

	@Column(name = "CHECK_ITEM35", length = 1)
	public String getCheckItem35() {
		return this.checkItem35;
	}

	public void setCheckItem35(String checkItem35) {
		this.checkItem35 = checkItem35;
	}

	@Column(name = "CHECK_ITEM36", length = 1)
	public String getCheckItem36() {
		return this.checkItem36;
	}

	public void setCheckItem36(String checkItem36) {
		this.checkItem36 = checkItem36;
	}

	@Column(name = "CHECK_ITEM37", length = 1)
	public String getCheckItem37() {
		return this.checkItem37;
	}

	public void setCheckItem37(String checkItem37) {
		this.checkItem37 = checkItem37;
	}

	@Column(name = "CHECK_ITEM38", length = 1)
	public String getCheckItem38() {
		return this.checkItem38;
	}

	public void setCheckItem38(String checkItem38) {
		this.checkItem38 = checkItem38;
	}

	@Column(name = "CHECK_ITEM39", length = 1)
	public String getCheckItem39() {
		return this.checkItem39;
	}

	public void setCheckItem39(String checkItem39) {
		this.checkItem39 = checkItem39;
	}

	@Column(name = "CHECK_ITEM40", length = 1)
	public String getCheckItem40() {
		return this.checkItem40;
	}

	public void setCheckItem40(String checkItem40) {
		this.checkItem40 = checkItem40;
	}

	@Column(name = "CHECK_ITEM41", length = 1)
	public String getCheckItem41() {
		return this.checkItem41;
	}

	public void setCheckItem41(String checkItem41) {
		this.checkItem41 = checkItem41;
	}

	@Column(name = "CHECK_ITEM42", length = 1)
	public String getCheckItem42() {
		return this.checkItem42;
	}

	public void setCheckItem42(String checkItem42) {
		this.checkItem42 = checkItem42;
	}

	@Column(name = "CHECK_ITEM43", length = 1)
	public String getCheckItem43() {
		return this.checkItem43;
	}

	public void setCheckItem43(String checkItem43) {
		this.checkItem43 = checkItem43;
	}

	@Column(name = "CHECK_ITEM44", length = 1)
	public String getCheckItem44() {
		return this.checkItem44;
	}

	public void setCheckItem44(String checkItem44) {
		this.checkItem44 = checkItem44;
	}

	@Column(name = "CHECK_ITEM45", length = 1)
	public String getCheckItem45() {
		return this.checkItem45;
	}

	public void setCheckItem45(String checkItem45) {
		this.checkItem45 = checkItem45;
	}
	                
	@Column(name = "CHECK_ITEM46", length = 1)
	public String getCheckItem46() {
		return this.checkItem46;
	}

	public void setCheckItem46(String checkItem46) {
		this.checkItem46 = checkItem46;
	}
	                
	@Column(name = "CHECK_ITEM_NAME39", length = 60)
    public String getCheckItemName39() {
        return checkItemName39;
    }

    public void setCheckItemName39(String checkItemName39) {
        this.checkItemName39 = checkItemName39;
    }

    @Column(name = "CHECK_ITEM_NAME46", length = 60)
    public String getCheckItemName46() {
        return checkItemName46;
    }

    public void setCheckItemName46(String checkItemName46) {
        this.checkItemName46 = checkItemName46;
    }
}