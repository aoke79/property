package com.sms.training.qualificupplanning.bean;

/**
 * 描述：飞机总计划  实体类  
 * @author LUJIE
 *
 */
public class PlanAdjust {
	
	//用于接收选择的单位
	private String dws;
	//用于接收选择的机型
	private String jxs;
	//单位
	private String dw;
	//机型
	private String jx;
	//人数
	private int persons;
	//月
	private int mon;
	
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
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	
	
	
}
