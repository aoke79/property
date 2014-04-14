package com.sms.training.qualificupplanning.bean;


/**
 * 副驾驶半年累计飞行经历的增加量的实体bean
 * @author sunny
 *
 */
public class CopilotTime {
   private String department;
   private String planeType;
   private String f1;
   private String f2;
   private String f3;
   private String f4;

    /**
     * empty Constructor
     */   
	public CopilotTime() {
		super();

	}

	/**
	 * full Constructor
	 * @param department
	 * @param planeType
	 * @param f1
	 * @param f2
	 * @param f3
	 * @param f4
	 */
	public CopilotTime(String department, String planeType, String f1,
			String f2, String f3, String f4) {
		super();
		this.department = department;
		this.planeType = planeType;
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
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

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}
	
	
	
   
    
}
