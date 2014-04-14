package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 描述：机队实力表(飞机)
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_CONDITION_PLANE")
public class FleetInfos implements java.io.Serializable{

	private static final long serialVersionUID = -517414480974358759L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "UP_CONDITION_PLANEID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;
	
	@Column(name="DEPTID")
	private String deptid;   //DEPTID 单位代码

	@Column(name="TYPEID")
	private String  typeid;         //TYPEID 机型代码
	
	
	
	
	@Column(name="NUM_PLANE")
	private String    plane;                //NUM_PLANE 飞行员人数
	@Transient
	private String deptname;  //单位名称
	@Transient
	private String typename;  //机型名称
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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



	public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}



}
