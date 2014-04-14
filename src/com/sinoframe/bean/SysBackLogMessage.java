package com.sinoframe.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_BACKLOG_MESSAGE")
public class SysBackLogMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	 
	// properties definition
	/** 待处理消息编号 */
	private String id;
	/** 消息标题 */
	private String title;
	/** 用户编号(读取用户表) */
	private CmUser cmUser;
	/** 发起人编号 */
	private String sendUserid;
	/** 消息内容 */
	private byte[] content;
	/** 消息类型 */
	private String messageType;
	/** 发起消息日期 */
	private Date sendDate;
	/** 消息状态 */
	private String state;
	/** 消息读取时间 */
	private Date readTime;

	// Constructors
	/** default constructor */
	public SysBackLogMessage() {
	}

	/** full constructor */
	public SysBackLogMessage(String id, String title, CmUser cmUser, String sendUserid,
			byte[] content, String messageType, Date sendDate, String state,
			Date readTime) {
		this.id = id;
		this.title = title;
		this.cmUser = cmUser;
		this.sendUserid = sendUserid;
		this.content = content;
		this.messageType = messageType;
		this.sendDate = sendDate;
		this.state = state;
		this.readTime = readTime;
	}

	// Property accessories
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	@Column(name = "SEND_USERID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getSendUserid() {
		return this.sendUserid;
	}

	public void setSendUserid(String sendUserid) {
		this.sendUserid = sendUserid;
	}

	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true)
	@Lob
	@Basic(fetch=FetchType.LAZY)
	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Column(name = "MESSAGE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SEND_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "READ_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

}