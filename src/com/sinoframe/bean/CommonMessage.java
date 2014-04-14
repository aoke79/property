/**
 * Title CommonMessage
 * Description the common part of SysBackLogMessage and SysHistoryMessage
 */
package com.sinoframe.bean;

import java.util.Date;

public class CommonMessage {
	
	/** 消息ID */
	private String messageId;
	/** 消息标题 */
	private String messageTitle;
	/** 消息发起时间 */
	private Date messageLaunchTime;
	/** 消息发起人 */
	private String messageLauncher;
	/** 读取消息人 */
	private String messageReceiver;
	/** 消息类型 */
	private String messageType;
	/** 消息来源 */
	private String messageSource;
	/** 消息内容 */
	private byte[] messageContent;
	/** 消息状态 */
	private String messageStatus;
	/** 消息读取时间 */
	private Date messageReadTime;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public Date getMessageLaunchTime() {
		return messageLaunchTime;
	}
	public void setMessageLaunchTime(Date messageLaunchTime) {
		this.messageLaunchTime = messageLaunchTime;
	}
	public String getMessageLauncher() {
		return messageLauncher;
	}
	public void setMessageLauncher(String messageLauncher) {
		this.messageLauncher = messageLauncher;
	}
	public String getMessageReceiver() {
		return messageReceiver;
	}
	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(String messageSource) {
		this.messageSource = messageSource;
	}
	public byte[] getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(byte[] messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public Date getMessageReadTime() {
		return messageReadTime;
	}
	public void setMessageReadTime(Date messageReadTime) {
		this.messageReadTime = messageReadTime;
	}
	
	
	
}
