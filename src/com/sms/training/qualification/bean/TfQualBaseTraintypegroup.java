package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TfQualBaseTraintypegroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_TRAINTYPEGROUP")
public class TfQualBaseTraintypegroup implements java.io.Serializable {

	private String groupid;
	private String  proupdec;
	
	@Id
	@Column(name = "TTGRID", unique = true, nullable = false, length = 4)
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@Column(name = "TTGRDESC",  nullable = false, length = 90)
	public String getProupdec() {
		return proupdec;
	}
	public void setProupdec(String proupdec) {
		this.proupdec = proupdec;
	}
	

}