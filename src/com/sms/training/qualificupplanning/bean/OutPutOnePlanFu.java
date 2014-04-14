package com.sms.training.qualificupplanning.bean;

/**
 * 描述：副驾驶年度平衡表 实体类  
 * @author LUJIE
 *
 */
public class OutPutOnePlanFu {
	//单位   年份	  机型  	飞机 	需求 	期初人数 	升级	改装增加 	其他	  	抽调	退休 	其他	见习机长	期末人数  	平衡状态 	初始阶段平衡状态
	//单位  年份	机型 	飞机 	需求 	期初人数 	新雇员  	改装增加 	 其他	抽调	退休 	升级	其他	其中F1	期末人数 	期末运力	平衡 状态 
	//用于接收选择的单位
	private String dws;
	//用于接收选择的机型
	private String jxs;
	//单位
	private String dw;
	//年份
	private String year;
	//机型
	private String jx;
	//飞机
	private String feiji;
	//机长需求
	private String need;
	//期初人数
	private String startPeople;
	//新雇员
	private String newPeople;
	//改装增加
	private String cadd;
	//其他
	private String other1;
	//抽调
	private String choudiao; 
	//退休
	private String tuixiu;
	//升级
	private String shengji;//升级	其他	其中F1	期末人数 	期末运力	平衡 状态 
	//其他
	private String other2;
	//其中F1
	private String f1;
	//期末人数
	private String endPeople;
	//期末运力
	private String endPow;
	//平衡状态
	private String staticP;
	
	
	
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
	public String getShengji() {
		return shengji;
	}
	public void setShengji(String shengji) {
		this.shengji = shengji;
	}
	public String getCadd() {
		return cadd;
	}
	public void setCadd(String cadd) {
		this.cadd = cadd;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getChoudiao() {
		return choudiao;
	}
	public void setChoudiao(String choudiao) {
		this.choudiao = choudiao;
	}
	public String getFeiji() {
		return feiji;
	}
	public void setFeiji(String feiji) {
		this.feiji = feiji;
	}
	public String getTuixiu() {
		return tuixiu;
	}
	public void setTuixiu(String tuixiu) {
		this.tuixiu = tuixiu;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	public String getStaticP() {
		return staticP;
	}
	public void setStaticP(String staticP) {
		this.staticP = staticP;
	}
	public String getNewPeople() {
		return newPeople;
	}
	public void setNewPeople(String newPeople) {
		this.newPeople = newPeople;
	}
	public String getF1() {
		return f1;
	}
	public void setF1(String f1) {
		this.f1 = f1;
	}
	public String getEndPow() {
		return endPow;
	}
	public void setEndPow(String endPow) {
		this.endPow = endPow;
	}
	
	
	
	
}
