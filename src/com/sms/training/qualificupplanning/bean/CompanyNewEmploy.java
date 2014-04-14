package com.sms.training.qualificupplanning.bean;

/**
 * 描述：公司新雇员预测  实体类  
 * @author LUJIE
 *
 */
public class CompanyNewEmploy {
	//年份
	private String year;
	//人数
	private float persons;
	//右边合计
	private int rightCount;
	//下面合计
	private int buttonCoutn;
	//列数
	private String zuoNuber;
	
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public float getPersons() {
		return persons;
	}
	public void setPersons(float persons) {
		this.persons = persons;
	}
	public String getZuoNuber() {
		return zuoNuber;
	}
	public void setZuoNuber(String zuoNuber) {
		this.zuoNuber = zuoNuber;
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
	
	
	
}
