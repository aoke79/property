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
/**航线飞行检查报告表实体类*/
@Entity
@Table(name = "TF_QUAL_ROUTE_REPORT")
public class TfQualRouteReport implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
private String routeId;
private TfQualDeclaraPilot tfQualDeclaraPilot;
private String purpose;
private String evaluate;
private Date checkDate;
private String route;
private String wheater;
private int flyTime;
private int  numberOfTimes;
/**飞行预先准备*/
private String yxzbScore1;
private String yxzbRemark1;
private String yxzbScore2;
private String yxzbRemark2;
private String yxzbScore3;
private String yxzbRemark3;
private String yxzbScore4;
private String yxzbRemark4;
private String yxzbScore5;
private String yxzbRemark5;
private String yxzbScore6;
private String yxzbRemark6;
private String yxzbScore7;
private String yxzbRemark7;
private String yxzbScore8;
private String yxzbRemark8;
private String yxzbScore9;
private String yxzbRemark9;
/**飞行直接准备阶段*/
private String zjzbScore1;
private String zjzbRemark1;
private String zjzbScore2;
private String zjzbRemark2;
private String zjzbScore3;
private String zjzbRemark3;
private String zjzbScore4;
private String zjzbRemark4;
private String zjzbScore5;
private String zjzbRemark5;
private String zjzbScore6;
private String zjzbRemark6;
private String zjzbScore7;
private String zjzbRemark7;
private String zjzbScore8;
private String zjzbRemark8;
private String zjzbScore9;
private String zjzbRemark9;
private String zjzbScore10;
private String zjzbRemark10;
private String zjzbScore11;
private String zjzbRemark11;
private String zjzbScore12;
private String zjzbRemark12;
private String zjzbScore13;
private String zjzbRemark13;
private String zjzbScore14;
private String zjzbRemark14;
private String zjzbScore15;
private String zjzbRemark15;
/**飞行实施阶段*/
private String ssjdScore1;
private String ssjdRemark1;
private String ssjdScore2;
private String ssjdRemark2;
private String ssjdScore3;
private String ssjdRemark3;
private String ssjdScore4;
private String ssjdRemark4;
private String ssjdScore5;
private String ssjdRemark5;
private String ssjdScore6;
private String ssjdRemark6;
private String ssjdScore7;
private String ssjdRemark7;
private String ssjdScore8;
private String ssjdRemark8;
private String ssjdScore9;
private String ssjdRemark9;
private String ssjdScore10;
private String ssjdRemark10;
private String ssjdScore11;
private String ssjdRemark11;
private String ssjdScore12;
private String ssjdRemark12;
private String ssjdScore13;
private String ssjdRemark13;
private String ssjdScore14;
private String ssjdRemark14;
private String ssjdScore15;
private String ssjdRemark15;
private String ssjdScore16;
private String ssjdRemark16;
private String ssjdScore17;
private String ssjdRemark17;
private String ssjdScore18;
private String ssjdRemark18;
private String ssjdScore19;
private String ssjdRemark19;
private String ssjdScore20;
private String ssjdRemark20;
private String ssjdScore21;
private String ssjdRemark21;
private String ssjdScore22;
private String ssjdRemark22;
/**讲评阶段*/
private String jpjdScore1;
private String jpjdRemark1;
private String jpjdScore2;
private String jpjdRemark2;
private String jpjdScore3;
private String jpjdRemark3;
private String jpjdScore4;
private String jpjdRemark4;
/**其他成绩*/
private String qtScore1;
private String qtRemark1;
private String qtScore2;
private String qtRemark2;
private String qtScore3;
private String qtRemark3;

private String evaluationReport;
private String creater;


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "ROUTEID", unique = true, nullable = false, length = 36)
public String getRouteId() {
	return routeId;
}
public void setRouteId(String routeId) {
	this.routeId = routeId;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "DETAILSID")
public TfQualDeclaraPilot getTfQualDeclaraPilot() {
	return tfQualDeclaraPilot;
}
public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
	this.tfQualDeclaraPilot = tfQualDeclaraPilot;
}
@Column(name = "PURPOSE", length = 60)
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
@Column(name = "EVALUATE", length = 60)
public String getEvaluate() {
	return evaluate;
}
public void setEvaluate(String evaluate) {
	this.evaluate = evaluate;
}
@Temporal(TemporalType.DATE)
@Column(name = "CHECKDATE", length = 7)
public Date getCheckDate() {
	return checkDate;
}
public void setCheckDate(Date checkDate) {
	this.checkDate = checkDate;
}
@Column(name = "ROUTE", length = 60)
public String getRoute() {
	return route;
}
public void setRoute(String route) {
	this.route = route;
}
@Column(name = "WEATHER", length = 30)
public String getWheater() {
	return wheater;
}
public void setWheater(String wheater) {
	this.wheater = wheater;
}
@Column(name = "FLYTIME")
public int getFlyTime() {
	return flyTime;
}
public void setFlyTime(int flyTime) {
	this.flyTime = flyTime;
}
@Column(name = "NUMBEROFTIMES")
public int getNumberOfTimes() {
	return numberOfTimes;
}
public void setNumberOfTimes(int numberOfTimes) {
	this.numberOfTimes = numberOfTimes;
}
@Column(name = "YXZB_SCORE_1", length = 1)
public String getYxzbScore1() {
	return yxzbScore1;
}
public void setYxzbScore1(String yxzbScore1) {
	this.yxzbScore1 = yxzbScore1;
}
@Column(name = "YXZB_REMARK_1", length = 60)
public String getYxzbRemark1() {
	return yxzbRemark1;
}
public void setYxzbRemark1(String yxzbRemark1) {
	this.yxzbRemark1 = yxzbRemark1;
}
@Column(name = "YXZB_SCORE_2", length = 1)
public String getYxzbScore2() {
	return yxzbScore2;
}
public void setYxzbScore2(String yxzbScore2) {
	this.yxzbScore2 = yxzbScore2;
}
@Column(name = "YXZB_REMARK_2", length = 60)
public String getYxzbRemark2() {
	return yxzbRemark2;
}
public void setYxzbRemark2(String yxzbRemark2) {
	this.yxzbRemark2 = yxzbRemark2;
}
@Column(name = "YXZB_SCORE_3", length = 1)
public String getYxzbScore3() {
	return yxzbScore3;
}
public void setYxzbScore3(String yxzbScore3) {
	this.yxzbScore3 = yxzbScore3;
}
@Column(name = "YXZB_REMARK_3", length = 60)
public String getYxzbRemark3() {
	return yxzbRemark3;
}
public void setYxzbRemark3(String yxzbRemark3) {
	this.yxzbRemark3 = yxzbRemark3;
}
@Column(name = "YXZB_SCORE_4", length = 1)
public String getYxzbScore4() {
	return yxzbScore4;
}
public void setYxzbScore4(String yxzbScore4) {
	this.yxzbScore4 = yxzbScore4;
}
@Column(name = "YXZB_REMARK_4", length = 60)
public String getYxzbRemark4() {
	return yxzbRemark4;
}
public void setYxzbRemark4(String yxzbRemark4) {
	this.yxzbRemark4 = yxzbRemark4;
}
@Column(name = "YXZB_SCORE_5", length = 1)
public String getYxzbScore5() {
	return yxzbScore5;
}
public void setYxzbScore5(String yxzbScore5) {
	this.yxzbScore5 = yxzbScore5;
}
@Column(name = "YXZB_REMARK_5", length = 60)
public String getYxzbRemark5() {
	return yxzbRemark5;
}
public void setYxzbRemark5(String yxzbRemark5) {
	this.yxzbRemark5 = yxzbRemark5;
}
@Column(name = "YXZB_SCORE_6", length = 1)
public String getYxzbScore6() {
	return yxzbScore6;
}
public void setYxzbScore6(String yxzbScore6) {
	this.yxzbScore6 = yxzbScore6;
}
@Column(name = "YXZB_REMARK_6", length = 60)
public String getYxzbRemark6() {
	return yxzbRemark6;
}
public void setYxzbRemark6(String yxzbRemark6) {
	this.yxzbRemark6 = yxzbRemark6;
}
@Column(name = "YXZB_SCORE_7", length = 1)
public String getYxzbScore7() {
	return yxzbScore7;
}
public void setYxzbScore7(String yxzbScore7) {
	this.yxzbScore7 = yxzbScore7;
}
@Column(name = "YXZB_REMARK_7", length = 60)
public String getYxzbRemark7() {
	return yxzbRemark7;
}
public void setYxzbRemark7(String yxzbRemark7) {
	this.yxzbRemark7 = yxzbRemark7;
}
@Column(name = "YXZB_SCORE_8", length = 1)
public String getYxzbScore8() {
	return yxzbScore8;
}
public void setYxzbScore8(String yxzbScore8) {
	this.yxzbScore8 = yxzbScore8;
}
@Column(name = "YXZB_REMARK_8", length = 60)
public String getYxzbRemark8() {
	return yxzbRemark8;
}
public void setYxzbRemark8(String yxzbRemark8) {
	this.yxzbRemark8 = yxzbRemark8;
}
@Column(name = "YXZB_SCORE_9", length = 1)
public String getYxzbScore9() {
	return yxzbScore9;
}
public void setYxzbScore9(String yxzbScore9) {
	this.yxzbScore9 = yxzbScore9;
}
@Column(name = "YXZB_REMARK_9", length = 60)
public String getYxzbRemark9() {
	return yxzbRemark9;
}
public void setYxzbRemark9(String yxzbRemark9) {
	this.yxzbRemark9 = yxzbRemark9;
}
@Column(name = "ZJZB_SCORE_1", length = 1)
public String getZjzbScore1() {
	return zjzbScore1;
}
public void setZjzbScore1(String zjzbScore1) {
	this.zjzbScore1 = zjzbScore1;
}
@Column(name = "ZJZB_REMARK_1", length = 60)
public String getZjzbRemark1() {
	return zjzbRemark1;
}
public void setZjzbRemark1(String zjzbRemark1) {
	this.zjzbRemark1 = zjzbRemark1;
}
@Column(name = "ZJZB_SCORE_2", length = 1)
public String getZjzbScore2() {
	return zjzbScore2;
}
public void setZjzbScore2(String zjzbScore2) {
	this.zjzbScore2 = zjzbScore2;
}
@Column(name = "ZJZB_REMARK_2", length = 60)
public String getZjzbRemark2() {
	return zjzbRemark2;
}
public void setZjzbRemark2(String zjzbRemark2) {
	this.zjzbRemark2 = zjzbRemark2;
}
@Column(name = "ZJZB_SCORE_3", length = 1)
public String getZjzbScore3() {
	return zjzbScore3;
}
public void setZjzbScore3(String zjzbScore3) {
	this.zjzbScore3 = zjzbScore3;
}
@Column(name = "ZJZB_REMARK_3", length = 60)
public String getZjzbRemark3() {
	return zjzbRemark3;
}
public void setZjzbRemark3(String zjzbRemark3) {
	this.zjzbRemark3 = zjzbRemark3;
}
@Column(name = "ZJZB_SCORE_4", length = 1)
public String getZjzbScore4() {
	return zjzbScore4;
}
public void setZjzbScore4(String zjzbScore4) {
	this.zjzbScore4 = zjzbScore4;
}
@Column(name = "ZJZB_REMARK_4", length = 60)
public String getZjzbRemark4() {
	return zjzbRemark4;
}
public void setZjzbRemark4(String zjzbRemark4) {
	this.zjzbRemark4 = zjzbRemark4;
}
@Column(name = "ZJZB_SCORE_5", length = 1)
public String getZjzbScore5() {
	return zjzbScore5;
}
public void setZjzbScore5(String zjzbScore5) {
	this.zjzbScore5 = zjzbScore5;
}
@Column(name = "ZJZB_REMARK_5", length = 60)
public String getZjzbRemark5() {
	return zjzbRemark5;
}
public void setZjzbRemark5(String zjzbRemark5) {
	this.zjzbRemark5 = zjzbRemark5;
}
@Column(name = "ZJZB_SCORE_6", length = 1)
public String getZjzbScore6() {
	return zjzbScore6;
}
public void setZjzbScore6(String zjzbScore6) {
	this.zjzbScore6 = zjzbScore6;
}
@Column(name = "ZJZB_REMARK_6", length = 60)
public String getZjzbRemark6() {
	return zjzbRemark6;
}
public void setZjzbRemark6(String zjzbRemark6) {
	this.zjzbRemark6 = zjzbRemark6;
}
@Column(name = "ZJZB_SCORE_7", length = 1)
public String getZjzbScore7() {
	return zjzbScore7;
}
public void setZjzbScore7(String zjzbScore7) {
	this.zjzbScore7 = zjzbScore7;
}
@Column(name = "ZJZB_REMARK_7", length = 60)
public String getZjzbRemark7() {
	return zjzbRemark7;
}
public void setZjzbRemark7(String zjzbRemark7) {
	this.zjzbRemark7 = zjzbRemark7;
}
@Column(name = "ZJZB_SCORE_8", length = 1)
public String getZjzbScore8() {
	return zjzbScore8;
}
public void setZjzbScore8(String zjzbScore8) {
	this.zjzbScore8 = zjzbScore8;
}
@Column(name = "ZJZB_REMARK_8", length = 60)
public String getZjzbRemark8() {
	return zjzbRemark8;
}
public void setZjzbRemark8(String zjzbRemark8) {
	this.zjzbRemark8 = zjzbRemark8;
}
@Column(name = "ZJZB_SCORE_9", length = 1)
public String getZjzbScore9() {
	return zjzbScore9;
}
public void setZjzbScore9(String zjzbScore9) {
	this.zjzbScore9 = zjzbScore9;
}
@Column(name = "ZJZB_REMARK_9", length = 60)
public String getZjzbRemark9() {
	return zjzbRemark9;
}
public void setZjzbRemark9(String zjzbRemark9) {
	this.zjzbRemark9 = zjzbRemark9;
}
@Column(name = "ZJZB_SCORE_10", length = 1)
public String getZjzbScore10() {
	return zjzbScore10;
}
public void setZjzbScore10(String zjzbScore10) {
	this.zjzbScore10 = zjzbScore10;
}
@Column(name = "ZJZB_REMARK_10", length = 60)
public String getZjzbRemark10() {
	return zjzbRemark10;
}
public void setZjzbRemark10(String zjzbRemark10) {
	this.zjzbRemark10 = zjzbRemark10;
}
@Column(name = "ZJZB_SCORE_11", length = 1)
public String getZjzbScore11() {
	return zjzbScore11;
}
public void setZjzbScore11(String zjzbScore11) {
	this.zjzbScore11 = zjzbScore11;
}
@Column(name = "ZJZB_REMARK_11", length = 60)
public String getZjzbRemark11() {
	return zjzbRemark11;
}
public void setZjzbRemark11(String zjzbRemark11) {
	this.zjzbRemark11 = zjzbRemark11;
}
@Column(name = "ZJZB_SCORE_12", length = 1)
public String getZjzbScore12() {
	return zjzbScore12;
}
public void setZjzbScore12(String zjzbScore12) {
	this.zjzbScore12 = zjzbScore12;
}
@Column(name = "ZJZB_REMARK_12", length = 60)
public String getZjzbRemark12() {
	return zjzbRemark12;
}
public void setZjzbRemark12(String zjzbRemark12) {
	this.zjzbRemark12 = zjzbRemark12;
}
@Column(name = "ZJZB_SCORE_13", length = 1)
public String getZjzbScore13() {
	return zjzbScore13;
}
public void setZjzbScore13(String zjzbScore13) {
	this.zjzbScore13 = zjzbScore13;
}
@Column(name = "ZJZB_REMARK_13", length = 60)
public String getZjzbRemark13() {
	return zjzbRemark13;
}
public void setZjzbRemark13(String zjzbRemark13) {
	this.zjzbRemark13 = zjzbRemark13;
}
@Column(name = "ZJZB_SCORE_14", length = 1)
public String getZjzbScore14() {
	return zjzbScore14;
}
public void setZjzbScore14(String zjzbScore14) {
	this.zjzbScore14 = zjzbScore14;
}
@Column(name = "ZJZB_REMARK_14", length = 60)
public String getZjzbRemark14() {
	return zjzbRemark14;
}
public void setZjzbRemark14(String zjzbRemark14) {
	this.zjzbRemark14 = zjzbRemark14;
}
@Column(name = "ZJZB_SCORE_15", length = 1)
public String getZjzbScore15() {
	return zjzbScore15;
}
public void setZjzbScore15(String zjzbScore15) {
	this.zjzbScore15 = zjzbScore15;
}
@Column(name = "ZJZB_REMARK_15", length = 60)
public String getZjzbRemark15() {
	return zjzbRemark15;
}
public void setZjzbRemark15(String zjzbRemark15) {
	this.zjzbRemark15 = zjzbRemark15;
}
@Column(name = "SSJD_SCORE_1", length = 1)
public String getSsjdScore1() {
	return ssjdScore1;
}
public void setSsjdScore1(String ssjdScore1) {
	this.ssjdScore1 = ssjdScore1;
}
@Column(name = "SSJD_REMARK_1", length = 60)
public String getSsjdRemark1() {
	return ssjdRemark1;
}
public void setSsjdRemark1(String ssjdRemark1) {
	this.ssjdRemark1 = ssjdRemark1;
}
@Column(name = "SSJD_SCORE_2", length = 1)
public String getSsjdScore2() {
	return ssjdScore2;
}
public void setSsjdScore2(String ssjdScore2) {
	this.ssjdScore2 = ssjdScore2;
}
@Column(name = "SSJD_REMARK_2", length = 60)
public String getSsjdRemark2() {
	return ssjdRemark2;
}
public void setSsjdRemark2(String ssjdRemark2) {
	this.ssjdRemark2 = ssjdRemark2;
}
@Column(name = "SSJD_SCORE_3", length = 1)
public String getSsjdScore3() {
	return ssjdScore3;
}
public void setSsjdScore3(String ssjdScore3) {
	this.ssjdScore3 = ssjdScore3;
}
@Column(name = "SSJD_REMARK_3", length = 60)
public String getSsjdRemark3() {
	return ssjdRemark3;
}
public void setSsjdRemark3(String ssjdRemark3) {
	this.ssjdRemark3 = ssjdRemark3;
}
@Column(name = "SSJD_SCORE_4", length = 1)
public String getSsjdScore4() {
	return ssjdScore4;
}
public void setSsjdScore4(String ssjdScore4) {
	this.ssjdScore4 = ssjdScore4;
}
@Column(name = "SSJD_REMARK_4", length = 60)
public String getSsjdRemark4() {
	return ssjdRemark4;
}
public void setSsjdRemark4(String ssjdRemark4) {
	this.ssjdRemark4 = ssjdRemark4;
}
@Column(name = "SSJD_SCORE_5", length = 1)
public String getSsjdScore5() {
	return ssjdScore5;
}
public void setSsjdScore5(String ssjdScore5) {
	this.ssjdScore5 = ssjdScore5;
}
@Column(name = "SSJD_REMARK_5", length = 60)
public String getSsjdRemark5() {
	return ssjdRemark5;
}
public void setSsjdRemark5(String ssjdRemark5) {
	this.ssjdRemark5 = ssjdRemark5;
}
@Column(name = "SSJD_SCORE_6", length = 1)
public String getSsjdScore6() {
	return ssjdScore6;
}
public void setSsjdScore6(String ssjdScore6) {
	this.ssjdScore6 = ssjdScore6;
}
@Column(name = "SSJD_REMARK_6", length = 60)
public String getSsjdRemark6() {
	return ssjdRemark6;
}
public void setSsjdRemark6(String ssjdRemark6) {
	this.ssjdRemark6 = ssjdRemark6;
}
@Column(name = "SSJD_SCORE_7", length = 1)
public String getSsjdScore7() {
	return ssjdScore7;
}
public void setSsjdScore7(String ssjdScore7) {
	this.ssjdScore7 = ssjdScore7;
}
@Column(name = "SSJD_REMARK_7", length = 60)
public String getSsjdRemark7() {
	return ssjdRemark7;
}
public void setSsjdRemark7(String ssjdRemark7) {
	this.ssjdRemark7 = ssjdRemark7;
}
@Column(name = "SSJD_SCORE_8", length = 1)
public String getSsjdScore8() {
	return ssjdScore8;
}
public void setSsjdScore8(String ssjdScore8) {
	this.ssjdScore8 = ssjdScore8;
}
@Column(name = "SSJD_REMARK_8", length = 60)
public String getSsjdRemark8() {
	return ssjdRemark8;
}
public void setSsjdRemark8(String ssjdRemark8) {
	this.ssjdRemark8 = ssjdRemark8;
}
@Column(name = "SSJD_SCORE_9", length = 1)
public String getSsjdScore9() {
	return ssjdScore9;
}
public void setSsjdScore9(String ssjdScore9) {
	this.ssjdScore9 = ssjdScore9;
}
@Column(name = "SSJD_REMARK_9", length = 60)
public String getSsjdRemark9() {
	return ssjdRemark9;
}
public void setSsjdRemark9(String ssjdRemark9) {
	this.ssjdRemark9 = ssjdRemark9;
}
@Column(name = "SSJD_SCORE_10", length = 1)
public String getSsjdScore10() {
	return ssjdScore10;
}
public void setSsjdScore10(String ssjdScore10) {
	this.ssjdScore10 = ssjdScore10;
}
@Column(name = "SSJD_REMARK_10", length = 60)
public String getSsjdRemark10() {
	return ssjdRemark10;
}
public void setSsjdRemark10(String ssjdRemark10) {
	this.ssjdRemark10 = ssjdRemark10;
}
@Column(name = "SSJD_SCORE_11", length = 1)
public String getSsjdScore11() {
	return ssjdScore11;
}
public void setSsjdScore11(String ssjdScore11) {
	this.ssjdScore11 = ssjdScore11;
}
@Column(name = "SSJD_REMARK_11", length = 60)
public String getSsjdRemark11() {
	return ssjdRemark11;
}
public void setSsjdRemark11(String ssjdRemark11) {
	this.ssjdRemark11 = ssjdRemark11;
}
@Column(name = "SSJD_SCORE_12", length = 1)
public String getSsjdScore12() {
	return ssjdScore12;
}
public void setSsjdScore12(String ssjdScore12) {
	this.ssjdScore12 = ssjdScore12;
}
@Column(name = "SSJD_REMARK_12", length = 60)
public String getSsjdRemark12() {
	return ssjdRemark12;
}
public void setSsjdRemark12(String ssjdRemark12) {
	this.ssjdRemark12 = ssjdRemark12;
}
@Column(name = "SSJD_SCORE_13", length = 1)
public String getSsjdScore13() {
	return ssjdScore13;
}
public void setSsjdScore13(String ssjdScore13) {
	this.ssjdScore13 = ssjdScore13;
}
@Column(name = "SSJD_REMARK_13", length = 60)
public String getSsjdRemark13() {
	return ssjdRemark13;
}
public void setSsjdRemark13(String ssjdRemark13) {
	this.ssjdRemark13 = ssjdRemark13;
}
@Column(name = "SSJD_SCORE14", length = 1)
public String getSsjdScore14() {
	return ssjdScore14;
}
public void setSsjdScore14(String ssjdScore14) {
	this.ssjdScore14 = ssjdScore14;
}
@Column(name = "SSJD_REMARK_14", length = 60)
public String getSsjdRemark14() {
	return ssjdRemark14;
}
public void setSsjdRemark14(String ssjdRemark14) {
	this.ssjdRemark14 = ssjdRemark14;
}
@Column(name = "SSJD_SCORE_15", length = 1)
public String getSsjdScore15() {
	return ssjdScore15;
}
public void setSsjdScore15(String ssjdScore15) {
	this.ssjdScore15 = ssjdScore15;
}
@Column(name = "SSJD_REMARK_15", length = 60)
public String getSsjdRemark15() {
	return ssjdRemark15;
}
public void setSsjdRemark15(String ssjdRemark15) {
	this.ssjdRemark15 = ssjdRemark15;
}
@Column(name = "SSJD_SCORE_16", length = 1)
public String getSsjdScore16() {
	return ssjdScore16;
}
public void setSsjdScore16(String ssjdScore16) {
	this.ssjdScore16 = ssjdScore16;
}
@Column(name = "SSJD_REMARK_16", length = 60)
public String getSsjdRemark16() {
	return ssjdRemark16;
}
public void setSsjdRemark16(String ssjdRemark16) {
	this.ssjdRemark16 = ssjdRemark16;
}
@Column(name = "SSJD_SCORE_17", length = 1)
public String getSsjdScore17() {
	return ssjdScore17;
}
public void setSsjdScore17(String ssjdScore17) {
	this.ssjdScore17 = ssjdScore17;
}
@Column(name = "SSJD_REMARK_17", length = 60)
public String getSsjdRemark17() {
	return ssjdRemark17;
}
public void setSsjdRemark17(String ssjdRemark17) {
	this.ssjdRemark17 = ssjdRemark17;
}
@Column(name = "SSJD_SCORE_18", length = 1)
public String getSsjdScore18() {
	return ssjdScore18;
}
public void setSsjdScore18(String ssjdScore18) {
	this.ssjdScore18 = ssjdScore18;
}
@Column(name = "SSJD_REMARK_18", length = 60)
public String getSsjdRemark18() {
	return ssjdRemark18;
}
public void setSsjdRemark18(String ssjdRemark18) {
	this.ssjdRemark18 = ssjdRemark18;
}
@Column(name = "SSJD_SCORE_19", length = 1)
public String getSsjdScore19() {
	return ssjdScore19;
}
public void setSsjdScore19(String ssjdScore19) {
	this.ssjdScore19 = ssjdScore19;
}
@Column(name = "SSJD_REMARK_19", length = 60)
public String getSsjdRemark19() {
	return ssjdRemark19;
}
public void setSsjdRemark19(String ssjdRemark19) {
	this.ssjdRemark19 = ssjdRemark19;
}
@Column(name = "SSJD_SCORE_20", length = 1)
public String getSsjdScore20() {
	return ssjdScore20;
}
public void setSsjdScore20(String ssjdScore20) {
	this.ssjdScore20 = ssjdScore20;
}
@Column(name = "SSJD_REMARK_20", length = 60)
public String getSsjdRemark20() {
	return ssjdRemark20;
}
public void setSsjdRemark20(String ssjdRemark20) {
	this.ssjdRemark20 = ssjdRemark20;
}
@Column(name = "SSJD_SCORE_21", length = 1)
public String getSsjdScore21() {
	return ssjdScore21;
}
public void setSsjdScore21(String ssjdScore21) {
	this.ssjdScore21 = ssjdScore21;
}
@Column(name = "SSJD_REMARK_21", length = 60)
public String getSsjdRemark21() {
	return ssjdRemark21;
}
public void setSsjdRemark21(String ssjdRemark21) {
	this.ssjdRemark21 = ssjdRemark21;
}
@Column(name = "SSJD_SCORE_22", length = 1)
public String getSsjdScore22() {
	return ssjdScore22;
}
public void setSsjdScore22(String ssjdScore22) {
	this.ssjdScore22 = ssjdScore22;
}
@Column(name = "SSJD_REMARK_22", length = 60)
public String getSsjdRemark22() {
	return ssjdRemark22;
}
public void setSsjdRemark22(String ssjdRemark22) {
	this.ssjdRemark22 = ssjdRemark22;
}
@Column(name = "JPJD_SCORE_1", length = 1)
public String getJpjdScore1() {
	return jpjdScore1;
}
public void setJpjdScore1(String jpjdScore1) {
	this.jpjdScore1 = jpjdScore1;
}
@Column(name = "JPJD_REMARK_1", length = 60)
public String getJpjdRemark1() {
	return jpjdRemark1;
}
public void setJpjdRemark1(String jpjdRemark1) {
	this.jpjdRemark1 = jpjdRemark1;
}
@Column(name = "JPJD_SCORE_2", length = 1)
public String getJpjdScore2() {
	return jpjdScore2;
}
public void setJpjdScore2(String jpjdScore2) {
	this.jpjdScore2 = jpjdScore2;
}
@Column(name = "JPJD_REMARK_2", length = 60)
public String getJpjdRemark2() {
	return jpjdRemark2;
}
public void setJpjdRemark2(String jpjdRemark2) {
	this.jpjdRemark2 = jpjdRemark2;
}
@Column(name = "JPJD_SCORE_3", length = 1)
public String getJpjdScore3() {
	return jpjdScore3;
}
public void setJpjdScore3(String jpjdScore3) {
	this.jpjdScore3 = jpjdScore3;
}
@Column(name = "JPJD_REMARK_3", length = 60)
public String getJpjdRemark3() {
	return jpjdRemark3;
}

public void setJpjdRemark3(String jpjdRemark3) {
	this.jpjdRemark3 = jpjdRemark3;
}
@Column(name = "JPJD_SCORE_4", length = 1)
public String getJpjdScore4() {
	return jpjdScore4;
}
public void setJpjdScore4(String jpjdScore4) {
	this.jpjdScore4 = jpjdScore4;
}
@Column(name = "JPJD_REMARK_4", length = 60)
public String getJpjdRemark4() {
	return jpjdRemark4;
}
public void setJpjdRemark4(String jpjdRemark4) {
	this.jpjdRemark4 = jpjdRemark4;
}
@Column(name = "QT_SCORE_1", length = 1)
public String getQtScore1() {
	return qtScore1;
}
public void setQtScore1(String qtScore1) {
	this.qtScore1 = qtScore1;
}
@Column(name = "QT_REMARK_1", length = 60)
public String getQtRemark1() {
	return qtRemark1;
}
public void setQtRemark1(String qtRemark1) {
	this.qtRemark1 = qtRemark1;
}
@Column(name = "QT_SCORE_2", length = 1)
public String getQtScore2() {
	return qtScore2;
}
public void setQtScore2(String qtScore2) {
	this.qtScore2 = qtScore2;
}
@Column(name = "QT_REMARK_2", length = 60)
public String getQtRemark2() {
	return qtRemark2;
}
public void setQtRemark2(String qtRemark2) {
	this.qtRemark2 = qtRemark2;
}
@Column(name = "QT_SCORE_3", length = 1)
public String getQtScore3() {
	return qtScore3;
}
public void setQtScore3(String qtScore3) {
	this.qtScore3 = qtScore3;
}
@Column(name = "QT_REMARK_3", length = 60)
public String getQtRemark3() {
	return qtRemark3;
}
public void setQtRemark3(String qtRemark3) {
	this.qtRemark3 = qtRemark3;
}
@Column(name = "EVALUATIONREPORT", length = 300)
public String getEvaluationReport() {
	return evaluationReport;
}
public void setEvaluationReport(String evaluationReport) {
	this.evaluationReport = evaluationReport;
}
@Column(name = "CREATOR", length = 36)
public String getCreater() {
	return creater;
}
public void setCreater(String creater) {
	this.creater = creater;
}
}
