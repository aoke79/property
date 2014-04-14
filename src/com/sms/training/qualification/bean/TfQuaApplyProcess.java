package com.sms.training.qualification.bean;
/**
 *  .
 * 资质申请
 * @author QLL
 */

public class TfQuaApplyProcess implements java.io.Serializable {

	// Fields
	/**
	 * id
	 */
	private String id;
	/**
	 * 申请人ID
	 */
	private String stuid;
	/**
	 * 流程实例ID
	 */
	private String pid;

	// Constructors

	/** default constructor */
	public TfQuaApplyProcess() {
	}

	/** full constructor */
	public TfQuaApplyProcess(String id, String stuid) {
		this.id = id;
		this.stuid = stuid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStuid() {
		return this.stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public void setpid(String pid) {
		this.pid = pid;
	}
	public String getPid() {
		return this.pid;
	}

}