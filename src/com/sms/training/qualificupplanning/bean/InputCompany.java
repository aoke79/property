package com.sms.training.qualificupplanning.bean;

/**
 * 描述：飞机总计划  实体类  
 * @author LUJIE
 *
 */
public class InputCompany {
	
	//    年份	调出公司	调出机型	调入公司	调入机型	调整人数

	
	private String year;
	//调出公司
	private String outCompany;
	//调出机型
	private String jx;
	//调入公司
	private String inCompany;
	//调入机型
	private String injx;
	//预测人数
	private String person;
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getOutCompany() {
		return outCompany;
	}
	public void setOutCompany(String outCompany) {
		this.outCompany = outCompany;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public String getInCompany() {
		return inCompany;
	}
	public void setInCompany(String inCompany) {
		this.inCompany = inCompany;
	}
	public String getInjx() {
		return injx;
	}
	public void setInjx(String injx) {
		this.injx = injx;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}

	
}
