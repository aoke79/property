/**
 * Title  MessageAction
 * Description  deal with the in-time message
 */

package com.sinoframe.web.action;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.SysBackLogMessage;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysBackLogMessageBS;

@ParentPackage("json-default")
@Result(name = "success",type="json")

public class MessageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//获取JSON格式数据成功时的跳转格式
	private String success;
	//获取的未读消息的条数
	private String messageColumn;
	//获取新消息的条数
	private String messageNew;

	//单条记录时的ID
	private String singleId;
	//单条记录的类型
	private String singleType;
	//即时消息service接口
	private ISysBackLogMessageBS backLogMessageBS;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessageColumn() {
		return messageColumn;
	}
	public void setMessageColumn(String messageColumn) {
		this.messageColumn = messageColumn;
	}
	
	public String getMessageNew() {
		return messageNew;
	}
	public void setMessageNew(String messageNew) {
		this.messageNew = messageNew;
	}
	
	public String getSingleId() {
		return singleId;
	}
	public void setSingleId(String singleId) {
		this.singleId = singleId;
	}
	
	public String getSingleType() {
		return singleType;
	}
	public void setSingleType(String singleType) {
		this.singleType = singleType;
	}
	
	@JSON(serialize=false)
	public ISysBackLogMessageBS getBackLogMessageBS() {
		return backLogMessageBS;
	}
	public void setBackLogMessageBS(ISysBackLogMessageBS backLogMessageBS) {
		this.backLogMessageBS = backLogMessageBS;
	}
	
	/**
	 * 获取未读消息
	 * @return SUCCESS
	 */
	public String countMessage() {
		
		//从session中获取用户信息
		CmUser user = (CmUser)ActionContext.getContext().getSession().get("user");
		//根据用户信息查询当前未读消息
		try {
			int messageSize = backLogMessageBS.getMessage(user);
			int historySize = backLogMessageBS.getHistoryMessage(user);
			//获取未读消息总条数
			messageColumn = String.valueOf(messageSize + historySize);
			
			//获取最新消息
			int newColumn = backLogMessageBS.getNewMessage(user);
			
			if(newColumn == 1){
				SysBackLogMessage newMessage = backLogMessageBS.getCurrentSingle(user);
				this.setSingleId(newMessage.getId());
				this.setSingleType(newMessage.getMessageType());
			}
			this.setMessageNew(String.valueOf(newColumn));
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage());
			e.printStackTrace();
		}
		
		this.setSuccess("true");
		this.setMessageColumn(messageColumn);
		
		return SUCCESS;
	}
	
}
