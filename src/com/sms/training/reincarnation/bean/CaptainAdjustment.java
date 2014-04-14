package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 公司间机长调整建议表 MO_UP_CHANGE_OUT
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_CHANGE_OUT")
public class CaptainAdjustment implements java.io.Serializable{

	private static final long serialVersionUID = 7692388122114582748L;


	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_CHANGE_OUTID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;
	
	
	@Column(name="DEPTOUTID")
	private String deptoutid;   //  DEPTOUTID原单位代码
	
	@Transient
	private String deptoutName;
	
	
	@Column(name="TYPEOUTID")
	private String typeoutid;   //  TYPEOUTID 原机型代码
	
	@Transient
	private String typeoutName;
	
	
	@Column(name="YEAR")
	private String year;   //  年 
	
	
	@Column(name="NUM_CHANGE")
	private String change;   // NUM_CHANGE 公司间机长调整人数
	
	
	@Column(name="DEPTINID")
	private String deptinid;   // DEPTINID 现单位代码
	
	
	@Transient
	private String deptinName;
	
	@Column(name="TYPEINID")
	private String typeinid;   // TYPEINID 现机型代码
	
	@Transient
	private String typeinName;


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getDeptoutid() {
		return deptoutid;
	}



	public void setDeptoutid(String deptoutid) {
		this.deptoutid = deptoutid;
	}



	public String getTypeoutid() {
		return typeoutid;
	}



	public void setTypeoutid(String typeoutid) {
		this.typeoutid = typeoutid;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getChange() {
		return change;
	}



	public void setChange(String change) {
		this.change = change;
	}



	public String getDeptinid() {
		return deptinid;
	}



	public void setDeptinid(String deptinid) {
		this.deptinid = deptinid;
	}



	public String getTypeinid() {
		return typeinid;
	}



	public void setTypeinid(String typeinid) {
		this.typeinid = typeinid;
	}



	public String getDeptoutName() {
		return deptoutName;
	}



	public void setDeptoutName(String deptoutName) {
		this.deptoutName = deptoutName;
	}



	public String getTypeoutName() {
		return typeoutName;
	}



	public void setTypeoutName(String typeoutName) {
		this.typeoutName = typeoutName;
	}



	public String getDeptinName() {
		return deptinName;
	}



	public void setDeptinName(String deptinName) {
		this.deptinName = deptinName;
	}



	public String getTypeinName() {
		return typeinName;
	}



	public void setTypeinName(String typeinName) {
		this.typeinName = typeinName;
	}
}
