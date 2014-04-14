package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import java.util.*;
/**
 * 描述 : 输出新雇员分配情况
 * @author LUJIE
 *
 */


@Entity
@Table(name="MO_UP_NEW_OUT")
public class OutputNewEmployees  implements java.io.Serializable{
	

	private static final long serialVersionUID = -5308261532812154140L;

	@Id
	@GeneratedValue(generator="upnewoutid-uuid")
	@GenericGenerator(name="upnewoutid-uuid", strategy = "uuid")
	@Column(name = "UP_NEW_OUTID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String  upnewoutid; //分配码
	
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>(); 
	
	
	public List<String[]> getCompanyInfoListnew() {
		return companyInfoListnew;
	}


	public void setCompanyInfoListnew(List<String[]> companyInfoListnew) {
		this.companyInfoListnew = companyInfoListnew;
	}


	public List<String[]> getCompanyInfoList() {
		return companyInfoList;
	}


	public void setCompanyInfoList(List<String[]> companyInfoList) {
		this.companyInfoList = companyInfoList;
	}

	@Transient
	private Integer zd;
	@Transient
	private Integer xn;
	@Transient
	private Integer zj;
	@Transient
	private Integer cq;
	@Transient
	private Integer tj;
	@Transient
	private Integer sh;
	@Transient
	private Integer hb;
	
	
	@Transient
	private Integer hj;
	
	
	@Transient
	private Integer yc;
	
	public Integer getHb() {
		return hb;
	}


	public Integer getHj() {
		return hj;
	}





	public void setHj(Integer hj) {
		this.hj = hj;
	}





	public Integer getYc() {
		return yc;
	}





	public void setYc(Integer yc) {
		this.yc = yc;
	}





	public void setHb(Integer hb) {
		this.hb = hb;
	}
	
	@Transient
    private Integer ycs ;



	public Integer getYcs() {
		return ycs;
	}


	public void setYcs(Integer ycs) {
		this.ycs = ycs;
	}

	@Column(name="DEPTID",length=36)
	private String  deptid; //单位代码
	
	@Column(name="YEAR",length=5)
	private Integer  year; //年
	
	@Column(name="NUM_NEW_OUT",length=5)
	private Integer numnewout; //新学员人数 

	@Transient
	private String  deptName; //单位名称
	
	@Transient
	private String yearName ;    //年名称
	
	
	public String getDeptName() {
		return deptName;
	}





	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}





	public String getYearName() {
		return yearName;
	}





	public void setYearName(String yearName) {
		this.yearName = yearName;
	}





	public String getUpnewoutid() {
		return upnewoutid;
	}
	
	
	
	

	public void setUpnewoutid(String upnewoutid) {
		this.upnewoutid = upnewoutid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getNumnewout() {
		return numnewout;
	}

	public void setNumnewout(Integer numnewout) {
		this.numnewout = numnewout;
	}





	public Integer getZd() {
		return zd;
	}





	public void setZd(Integer zd) {
		this.zd = zd;
	}





	public Integer getXn() {
		return xn;
	}





	public void setXn(Integer xn) {
		this.xn = xn;
	}





	public Integer getZj() {
		return zj;
	}





	public void setZj(Integer zj) {
		this.zj = zj;
	}





	public Integer getCq() {
		return cq;
	}





	public void setCq(Integer cq) {
		this.cq = cq;
	}





	public Integer getTj() {
		return tj;
	}





	public void setTj(Integer tj) {
		this.tj = tj;
	}





	public Integer getSh() {
		return sh;
	}





	public void setSh(Integer sh) {
		this.sh = sh;
	}
	
	

}
