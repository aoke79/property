package com.sms.training.qualificupplanning.bean;


/**
 * 飞机引入，退出计划的实体Bean
 * @author sunny
 *
 */
public class PlanOfPlane {
	/**部门*/
    private String department;
    /**机型*/
    private String planType;
    /**一月*/
    private String oneMonth;
    /**二月*/
    private String twoMonth;
    /**三月*/
    private String threeMonth;
    /**四月*/
    private String fourMonth;
    /**五月*/
    private String fiveMonth;
    /**六月*/
    private String sixMonth;
    /**七月*/
    private String seventMonth;
    /**八月*/
    private String eightMonth;
    /**九月*/
    private String nineMonth;
    /**十月*/
    private String tenMonth;
    /**十一月*/
    private String elevenMonth;
    /**十二月*/
    private String twelveMonth;
    
    
    /**
     * empty Constructor
     */
	public PlanOfPlane() {
		super();
		
	}


	/**
	 * full Constructor
	 * @param department
	 * @param planType
	 * @param oneMonth
	 * @param twoMonth
	 * @param threeMonth
	 * @param fourMonth
	 * @param fiveMonth
	 * @param sixMonth
	 * @param seventMonth
	 * @param eightMonth
	 * @param nineMonth
	 * @param tenMonth
	 * @param elevenMonth
	 * @param twelveMonth
	 */
	public PlanOfPlane(String department, String planType, String oneMonth,
			String twoMonth, String threeMonth, String fourMonth,
			String fiveMonth, String sixMonth, String seventMonth,
			String eightMonth, String nineMonth, String tenMonth,
			String elevenMonth, String twelveMonth) {
		super();
		this.department = department;
		this.planType = planType;
		this.oneMonth = oneMonth;
		this.twoMonth = twoMonth;
		this.threeMonth = threeMonth;
		this.fourMonth = fourMonth;
		this.fiveMonth = fiveMonth;
		this.sixMonth = sixMonth;
		this.seventMonth = seventMonth;
		this.eightMonth = eightMonth;
		this.nineMonth = nineMonth;
		this.tenMonth = tenMonth;
		this.elevenMonth = elevenMonth;
		this.twelveMonth = twelveMonth;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPlanType() {
		return planType;
	}


	public void setPlanType(String planType) {
		this.planType = planType;
	}


	public String getOneMonth() {
		return oneMonth;
	}


	public void setOneMonth(String oneMonth) {
		this.oneMonth = oneMonth;
	}


	public String getTwoMonth() {
		return twoMonth;
	}


	public void setTwoMonth(String twoMonth) {
		this.twoMonth = twoMonth;
	}


	public String getThreeMonth() {
		return threeMonth;
	}


	public void setThreeMonth(String threeMonth) {
		this.threeMonth = threeMonth;
	}


	public String getFourMonth() {
		return fourMonth;
	}


	public void setFourMonth(String fourMonth) {
		this.fourMonth = fourMonth;
	}


	public String getFiveMonth() {
		return fiveMonth;
	}


	public void setFiveMonth(String fiveMonth) {
		this.fiveMonth = fiveMonth;
	}


	public String getSixMonth() {
		return sixMonth;
	}


	public void setSixMonth(String sixMonth) {
		this.sixMonth = sixMonth;
	}


	public String getSeventMonth() {
		return seventMonth;
	}


	public void setSeventMonth(String seventMonth) {
		this.seventMonth = seventMonth;
	}


	public String getEightMonth() {
		return eightMonth;
	}


	public void setEightMonth(String eightMonth) {
		this.eightMonth = eightMonth;
	}


	public String getNineMonth() {
		return nineMonth;
	}


	public void setNineMonth(String nineMonth) {
		this.nineMonth = nineMonth;
	}


	public String getTenMonth() {
		return tenMonth;
	}


	public void setTenMonth(String tenMonth) {
		this.tenMonth = tenMonth;
	}


	public String getElevenMonth() {
		return elevenMonth;
	}


	public void setElevenMonth(String elevenMonth) {
		this.elevenMonth = elevenMonth;
	}


	public String getTwelveMonth() {
		return twelveMonth;
	}


	public void setTwelveMonth(String twelveMonth) {
		this.twelveMonth = twelveMonth;
	}
  
	
	
    
    
}
