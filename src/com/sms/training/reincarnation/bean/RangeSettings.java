package com.sms.training.reincarnation.bean;

import  javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述:范围设置
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_SET")
public class RangeSettings  implements java.io.Serializable{

	private static final long serialVersionUID = -8346698455745975788L;

	@Id
	@GeneratedValue(generator="setid-uuid")
	@GenericGenerator(name="setid-uuid", strategy = "uuid")
	@Column(name = "UP_SETID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String  setid;      // UP_SETID  转升规划范围设置码
	
	@Column(name="DEPTID")
	private String  deptid;            // DEPTID  单位代码
	
	@Transient
	private String deptname;  //单位名称
	@Transient
	private String typename;  //机型名称
	
	@Column(name="TYPEID")
	private String typeid;     //   TYPEID 机型代码
	
	@Column(name="USED")
	private String  used;          //USED  是/否

	public String getSetid() {
		return setid;
	}

	public void setSetid(String setid) {
		this.setid = setid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getUsed() {
		return used;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public void setUsed(String used) {
		this.used = used;
	}
	
}
