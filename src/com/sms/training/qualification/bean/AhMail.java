package com.sms.training.qualification.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AH_MAIL")
public class AhMail implements java.io.Serializable{
	
	private String mailuuid;
	private Date begintime;
	private AhUser anUser; 
	private String flag;
	
	public AhMail() {
		// TODO Auto-generated constructor stub
	}
	
	public AhMail(String mailuuid) {
		this.mailuuid = mailuuid;
	}
	
	public AhMail(String mailuuid,Date begintime,AhUser anUser, String flag) {
		this.mailuuid = mailuuid;
		this.anUser = anUser;
		this.begintime = begintime;
		this.flag = flag;
	}
	
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "MAILUUID", unique = true, nullable = false, length = 36)
	public String getMailuuid() {
		return mailuuid;
	}
	public void setMailuuid(String mailuuid) {
		this.mailuuid = mailuuid;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGINTIME", length = 7)
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	public AhUser getAnUser() {
		return anUser;
	}
	public void setAnUser(AhUser anUser) {
		this.anUser = anUser;
	}
	
	@Column(name = "FLAG", length = 36)
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}
