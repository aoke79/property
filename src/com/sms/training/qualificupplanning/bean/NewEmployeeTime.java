package com.sms.training.qualificupplanning.bean;

/**
 * 描述：飞机总计划  实体类  
 * @author LUJIE
 *
 */
public class NewEmployeeTime {
	
	//用于接收选择的单位
	private String dws;
	//用于接收选择的机型
	private String jxs;
	//单位
	private String dw;
	//机型
	private String jx;
	//2013上
	private String year;
	//百分比
	private float percent;
	//下半年
	private String yearX;
	//下班年百分比
	private float percentX;
	
	
	private String zuoNuber;
	//右边合计
	private int rightCount;
	//下面合计
	private int buttonCoutn;
	
	public String getZuoNuber() {
		return zuoNuber;
	}
	public void setZuoNuber(String zuoNuber) {
		this.zuoNuber = zuoNuber;
	}
	public String getDws() {
		return dws;
	}
	public void setDws(String dws) {
		this.dws = dws;
	}
	public String getJxs() {
		return jxs;
	}
	public void setJxs(String jxs) {
		this.jxs = jxs;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public int getRightCount() {
		return rightCount;
	}
	public void setRightCount(int rightCount) {
		this.rightCount = rightCount;
	}
	public int getButtonCoutn() {
		return buttonCoutn;
	}
	public void setButtonCoutn(int buttonCoutn) {
		this.buttonCoutn = buttonCoutn;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public String getYearX() {
		return yearX;
	}
	public void setYearX(String yearX) {
		this.yearX = yearX;
	}
	public float getPercentX() {
		return percentX;
	}
	public void setPercentX(float percentX) {
		this.percentX = percentX;
	}
	
	
	
}
