/**
 * @Title HistoryMessage
 * @Description the bean entity of table "HistoryMessage"
 */

package com.sinoframe.bean;

import java.util.Date;

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
@Table(name = "sys_history_message")
public class SysHistoryMessage {
	
	//properties definition
	/** 待处理消息编号 */
	private String id;
	/** 消息标题 */
	private String title;
	/** 发起人编号 */
	private String sendUserid;
	/** 接收人编号 (读取用户表)*/
	private CmUser cmUser;
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
	
	//constructors
	public SysHistoryMessage(){
	}
	
	public SysHistoryMessage(String id, String title, String sendUserid, byte[] content,
			String messageType, Date sendDate, String state, Date readTime) {
		this.id = id;
		this.title = title;
		this.sendUserid = sendUserid;
		this.content = content;
		this.messageType = messageType;
		this.sendDate = sendDate;
		this.state = state;
		this.readTime = readTime;
	}

	//all properties' getters and setters
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	public String getId() {
		return id;
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
	@JoinColumn(name = "RECEIVE_USERID", unique = false, nullable = true, insertable = true, updatable = true)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}
	
	@Column(name = "SEND_USERID", length = 36, nullable = true)
	public String getSendUserid() {
		return sendUserid;
	}
	public void setSendUserid(String sendUserid) {
		this.sendUserid = sendUserid;
	}
	
	@Column(name = "CONTENT", nullable = true)
	@Lob
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Column(name = "MESSAGE_TYPE", length = 1, nullable = true)
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SEND_DATE", nullable = true, columnDefinition = "datetime")
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@Column(name = "STATE", length = 1, nullable = true)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "READ_TIME", nullable = true, columnDefinition = "datetime")
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	
}
