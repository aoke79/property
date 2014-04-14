package com.sms.training.qualificupplanning.bean;

/**
 * 描述：飞机总计划  实体类  
 * @author LUJIE
 *
 */
public class InputEmployFenPei {
	
	private String year;
	//用于接收选择的单位
	private String dws;
	//用于接收选择的机型
	private String jxs;
	//单位
	private String dw;
	//机型
	private String jx;
	//飞机数量
	private String flyNum;
	//机长需求
	private String need;
	//期初人数
	private String startPeople;
	//期末人数
	private String endPeople;
	//平衡状态
	private String beginstatic;
	//调整后状态
	private String lastStatic;
	
	
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
	public String getFlyNum() {
		return flyNum;
	}
	public void setFlyNum(String flyNum) {
		this.flyNum = flyNum;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public String getStartPeople() {
		return startPeople;
	}
	public void setStartPeople(String startPeople) {
		this.startPeople = startPeople;
	}
	public String getEndPeople() {
		return endPeople;
	}
	public void setEndPeople(String endPeople) {
		this.endPeople = endPeople;
	}
	public String getBeginstatic() {
		return beginstatic;
	}
	public void setBeginstatic(String beginstatic) {
		this.beginstatic = beginstatic;
	}
	public String getLastStatic() {
		return lastStatic;
	}
	public void setLastStatic(String lastStatic) {
		this.lastStatic = lastStatic;
	}
	
	
	
}
