package com.sms.training.reincarnation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 *  描述 :单位
 * @author LUJIE
 *
 */
@Entity
@Table(name="MO_UP_DEPT")
public class CompanyInfo implements java.io.Serializable {
	

	private static final long serialVersionUID = 968108229428498310L;

	//DEPTID
	@Id
	@GeneratedValue(generator="deptid-uuid")
	@GenericGenerator(name="deptid-uuid", strategy = "uuid")
	@Column(name = "DEPTID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String deptid ; //单位ID
	
	//DEPTNAME
	@Column(name="DEPTNAME",length=20)
	private String deptname; //单位名字

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	
	
	

}
