/**
 * Title: Message
 * Description: the class to deal with message info. 
 */

package com.sinoframe.bean;

public class Message {
	
	/** 回调方式: closeCurrent关闭当前页面，forward根据forwardUrl进行跳转 */
	private String callbackType; 
	/** 当前链接路径指定的rel属性值 */
	private String navTabId;
	/** 跳转路径  仅当回调方式为forward时起作用 */
	private String forwardUrl;
	/** 提示消息 */
	private String messageInfo;
	/** 状态码：200成功 ，300失败，301超时 */
	private String statusCode;
	
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
}