package com.sms.training.qualificupplanning.bean;


/***
 * 机长副驾驶人数比的实体bean
 * @author sunny
 *
 */
public class CopilotRatio {
  /**部门*/
  private String department;
  /**机型*/
  private String planeType;
  /**2013上*/
  private String thirtyFirstHalfYear;
  /**2013下*/
  private String thirtySecondHalf;
  /**2014上*/
  private String fortyFirstHalfYear;
  /**2014下*/
  private String fortySecondHalf;
  /**2015上*/
  private String fiftyFirstHalfYear;
  /**2015下*/
  private String fiftySecondHalf;
  
  
    /**
     * empty Constructor
     */
	public CopilotRatio() {
		super();
		
	}

    /**
     * full Constructor
     * @param department
     * @param planeType
     * @param thirtyFirstHalfYear
     * @param thirtySecondHalf
     * @param fortyFirstHalfYear
     * @param fortySecondHalf
     * @param fiftyFirstHalfYear
     * @param fiftySecondHalf
     */
	public CopilotRatio(String department, String planeType,
			String thirtyFirstHalfYear, String thirtySecondHalf,
			String fortyFirstHalfYear, String fortySecondHalf,
			String fiftyFirstHalfYear, String fiftySecondHalf) {
		super();
		this.department = department;
		this.planeType = planeType;
		this.thirtyFirstHalfYear = thirtyFirstHalfYear;
		this.thirtySecondHalf = thirtySecondHalf;
		this.fortyFirstHalfYear = fortyFirstHalfYear;
		this.fortySecondHalf = fortySecondHalf;
		this.fiftyFirstHalfYear = fiftyFirstHalfYear;
		this.fiftySecondHalf = fiftySecondHalf;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public String getThirtyFirstHalfYear() {
		return thirtyFirstHalfYear;
	}

	public void setThirtyFirstHalfYear(String thirtyFirstHalfYear) {
		this.thirtyFirstHalfYear = thirtyFirstHalfYear;
	}

	public String getThirtySecondHalf() {
		return thirtySecondHalf;
	}

	public void setThirtySecondHalf(String thirtySecondHalf) {
		this.thirtySecondHalf = thirtySecondHalf;
	}

	public String getFortyFirstHalfYear() {
		return fortyFirstHalfYear;
	}

	public void setFortyFirstHalfYear(String fortyFirstHalfYear) {
		this.fortyFirstHalfYear = fortyFirstHalfYear;
	}

	public String getFortySecondHalf() {
		return fortySecondHalf;
	}

	public void setFortySecondHalf(String fortySecondHalf) {
		this.fortySecondHalf = fortySecondHalf;
	}

	public String getFiftyFirstHalfYear() {
		return fiftyFirstHalfYear;
	}

	public void setFiftyFirstHalfYear(String fiftyFirstHalfYear) {
		this.fiftyFirstHalfYear = fiftyFirstHalfYear;
	}

	public String getFiftySecondHalf() {
		return fiftySecondHalf;
	}

	public void setFiftySecondHalf(String fiftySecondHalf) {
		this.fiftySecondHalf = fiftySecondHalf;
	}
    
	
	

}
