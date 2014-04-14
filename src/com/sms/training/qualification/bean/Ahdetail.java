package com.sms.training.qualification.bean;

public class Ahdetail implements java.io.Serializable{
	//系统名
	private String sysname;
	//子系统对应url
	private String sysurl;
	//子系统登录名
	private String login;
	//子系统密码
	private String plaincode;
	//子系统密码
	private String password;
	//用户名字
	private String username;
	//新工号
	private String hrid;
	//老工号
	private String oldcode;
	//系统id
	private String sysid;
	//mapping标志位
	private String mflag;
	
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getSysurl() {
		return sysurl;
	}
	public void setSysurl(String sysurl) {
		this.sysurl = sysurl;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPlaincode() {
		return plaincode;
	}
	public void setPlaincode(String plaincode) {
		this.plaincode = plaincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	public String getOldcode() {
		return oldcode;
	}
	public void setOldcode(String oldcode) {
		this.oldcode = oldcode;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getMflag() {
		return mflag;
	}
	public void setMflag(String mflag) {
		this.mflag = mflag;
	}
	
	
}
