package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述: 用户记录登录失败用户信息
 * @author LUJIE
 *
 */
@Entity
@Table(name="USER_INFO_ERR")
public class UserInfoErr {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	
	
	//用户的登录名称
	@Column(name="LOGIN_NAME",length=50)
	private String loginName;
	
	//失败的次数   
	@Column(name="ERR_NUMBER",length=5)
	private int errNumber;
	
	//被冻结的时间  yyyy-MM-dd HH:mm:ss   
	@Column(name="TIMES",length=30)
	private String times;

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getErrNumber() {
		return errNumber;
	}

	public void setErrNumber(int errNumber) {
		this.errNumber = errNumber;
	}


	
}
