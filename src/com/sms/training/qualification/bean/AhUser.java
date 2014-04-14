package com.sms.training.qualification.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;



/**
 * AhUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_USER")
public class AhUser implements java.io.Serializable {

	// Fields

	private String useruuid;
	private String username;
	private String password;
	private String hrid;
	private String name;
	private String idcard;
	private String unitname;
	private String deptname;
	//HR：人力资源系统正式员工 WW：外围系统临时员工
	private String usertype;
	private String email;
	private String oldcode;
	private String mobile;
//	private Date begintime;
//	private Set<AhMapping> ahMappings = new HashSet<AhMapping>(0);

	// Constructors

	/** default constructor */
	public AhUser() {
	}

	/** minimal constructor */
	public AhUser(String useruuid) {
		this.useruuid = useruuid;
	}

	/** full constructor */
	public AhUser(String useruuid, String username, String password,String mobile,
			String hrid, String name, String idcard, String unitname,Date begintime,
			String deptname, String usertype, String email, Set<AhMapping> ahMappings) {
		this.useruuid = useruuid;
		this.username = username;
		this.password = password;
		this.hrid = hrid;
		this.name = name;
		this.idcard = idcard;
		this.unitname = unitname;
		this.deptname = deptname;
		this.usertype = usertype;
		this.email = email;
		this.mobile = mobile;
//		this.begintime = begintime;
//		this.ahMappings = ahMappings;
	}

	// Property accessors

	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "USERUUID", unique = true, nullable = false, length = 36)
	public String getUseruuid() {
		return this.useruuid;
	}

	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}

	@Column(name = "USERNAME", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "HRID", length = 8)
	public String getHrid() {
		return this.hrid;
	}

	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "IDCARD", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "UNITNAME", length = 100)
	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	@Column(name = "DEPTNAME", length = 100)
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Column(name = "USERTYPE", length = 2)
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "OLDCODE", length = 10)
	public String getOldcode() {
		return oldcode;
	}

	public void setOldcode(String oldcode) {
		this.oldcode = oldcode;
	}

	@Column(name = "MOBILE", length = 100)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
//	@Column(name = "BEGINTIME")
//	public Date getBegintime() {
//		return begintime;
//	}
//
//	public void setBegintime(Date begintime) {
//		this.begintime = begintime;
//	}
	
	
//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "ahUser")
//	public Set<AhMapping> getAhMappings() {
//		return ahMappings;
//	}
//
//	public void setAhMappings(Set<AhMapping> ahMappings) {
//		this.ahMappings = ahMappings;
//	}

	

}
