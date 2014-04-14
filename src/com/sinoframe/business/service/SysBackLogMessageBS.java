/**
 * @Title BackLogMessageBS
 * @Description the implement of the interface "IBackLogMessageBS"
 */

package com.sinoframe.business.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.CommonMessage;
import com.sinoframe.bean.SysBackLogMessage;
import com.sinoframe.bean.SysHistoryMessage;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.ISysBackLogMessageBS;
import com.sinoframe.common.util.DateTool;

@Service("backLogMessageBS")
public class SysBackLogMessageBS extends BaseBS implements ISysBackLogMessageBS {
	
	/**
	 * 添加系统消息
	 * @param user 从session中获取的用户信息
	 * @param sysBackLogMessage
	 */
	public void insertMessage(CmUser user, SysBackLogMessage sysBackLogMessage, String content) {
		//消息发送者
		String sendUserId = "";
		//存放TbUser的集合
		List<CmUser> userList = new ArrayList<CmUser>();
		//查询用户的条件
		String hql = "";
		//存放消息的集合
		List<SysBackLogMessage> messageList = new ArrayList<SysBackLogMessage>();
		
		if(user != null){
			sendUserId = user.getUserId();
			hql = "from CmUser where userId <> '" + user.getUserId() + "'";
		} else {
			hql = "from CmUser";
		}
		userList = this.findPageByQuery(hql);
		
		//为每个用户发送系统消息
		for(CmUser cmUser : userList){
			SysBackLogMessage backLogMessage = new SysBackLogMessage();
			backLogMessage.setSendUserid(sendUserId);
			backLogMessage.setState("1");
			backLogMessage.setMessageType("S");
			backLogMessage.setSendDate(new Date());
			backLogMessage.setCmUser(cmUser);
			backLogMessage.setTitle(sysBackLogMessage.getTitle());
			backLogMessage.setContent(content.getBytes());
			messageList.add(backLogMessage);
		}
		
		this.saveList(messageList);
	}
	
	/**
	 * 删除一条记录
	 * @param id 从页面获取的ID
	 */
	public void deleteMessage(Serializable id) {
		//先根据传过来的ID在消息表中查询
		SysBackLogMessage backLogMessage = this.findById(SysBackLogMessage.class, id);
		//若在消息表中查询到该条记录，则在消息表中执行删除命令，否则将对历史消息表进行删除该条记录操作
		if(backLogMessage != null){
			this.deleteById(SysBackLogMessage.class, id);
		} else {
			this.deleteById(SysHistoryMessage.class, id);
		}
		
	}
	
	/**
	 * 删除多条记录
	 * @param ids
	 */
	public void deleteMessages(String ids) {
		String[] idArray = ids.split(","); 
		
		String varId;
		for(int i=0;i<idArray.length;i++){
			varId = idArray[i];
			SysBackLogMessage backlogMessage = this.findById(SysBackLogMessage.class, varId);
			if(backlogMessage != null){
				this.deleteById(SysBackLogMessage.class, varId);
			}
			SysHistoryMessage historyMessage = this.findById(SysHistoryMessage.class, varId);
			if(historyMessage != null){
				this.deleteById(SysHistoryMessage.class, varId);
			}
		}
		
	}
	
	/**
	 * 查询消息表中未读消息条数
	 * @param user 从session中获取的当前用户信息
	 * @return lMessageCount
	 */
	public List<SysBackLogMessage> getBackLogMessage(CmUser user) {
		String userId = "";
		List<SysBackLogMessage> backlogList = new ArrayList<SysBackLogMessage>();
		
		if(user != null){
			userId = user.getUserId();
		}
		//消息表查询条件
		String strMessage = "";
		
		if(userId != ""){
			strMessage = "from SysBackLogMessage where state <> '2' and cmUser.userId ='" + userId + "'";
		} else {
			strMessage = "from SysBackLogMessage where state <> '2' ";
		}
		//将查询结果放入List集合
		backlogList = this.findPageByQuery(strMessage);
		return backlogList;
	}
	
	/**
	 * 查询消息表中未读消息条数
	 * @param user 从session中获取的当前用户信息
	 * @return
	 */
	public int getMessage(CmUser user) {
		List<SysBackLogMessage> backlogList = this.getBackLogMessage(user);
		if(backlogList.isEmpty()){
			return 0;
		} else {
			return backlogList.size();
		}
	}
	
	/**
	 * 从历史消息表中查询未读消息
	 * @param user 从session中获取的当前用户信息
	 * @return
	 */
	public List<SysHistoryMessage> getHistoryMessages(CmUser user) {
		String userId = "";
		List<SysHistoryMessage> historyList = new ArrayList<SysHistoryMessage>();
		
		if(user != null) {
			userId = user.getUserId();
		}
		//历史表查询条件
		String strHistory = "";
		if(userId != ""){
			strHistory = "from SysHistoryMessage where state <> '2' and cmUser.userId ='" + userId + "'";
		} else {
			strHistory = "from SysHistoryMessage where state <> '2'";
		}
		//将查询结果放入List集合
		historyList = this.findPageByQuery(strHistory);
		return historyList;
	}
	
	/**
	 * 从历史消息表中查询未读消息
	 * @param user 从session中获取的当前用户信息
	 * @return
	 */
	public int getHistoryMessage(CmUser user) {
		List<SysHistoryMessage> historyList = this.getHistoryMessages(user);
		if(historyList.isEmpty()){
			return 0;
		} else {
			return historyList.size();
		}
	}

	/**
	 * 为单条记录时获取当前记录的对象
	 * @param user
	 * @return
	 */
	public SysBackLogMessage getSingleMessage(CmUser user) {
		SysBackLogMessage backLogMessage = new SysBackLogMessage();
		List<SysBackLogMessage> messageList = this.getBackLogMessage(user);
		if(messageList.size() == 1){
			for (SysBackLogMessage sysBackLogMessage : messageList) {
				backLogMessage.setId(sysBackLogMessage.getId());
				backLogMessage.setTitle(sysBackLogMessage.getTitle());
				backLogMessage.setState(sysBackLogMessage.getState());
				backLogMessage.setSendDate(sysBackLogMessage.getSendDate());
				backLogMessage.setMessageType(sysBackLogMessage.getMessageType());
			}
		}
		return backLogMessage;
	}
	
	/**
	 * 为单条记录时获取当前历史记录的对象
	 * @param user
	 * @return
	 */
	public SysHistoryMessage getSingleHistory(CmUser user) {
		SysHistoryMessage historyMessage = new SysHistoryMessage();
		List<SysHistoryMessage> historyList = this.getHistoryMessages(user);
		if(historyList.size() == 1){
			for (SysHistoryMessage sysHistoryMessage : historyList) {
				historyMessage.setId(sysHistoryMessage.getId());
				historyMessage.setTitle(sysHistoryMessage.getTitle());
				historyMessage.setState(sysHistoryMessage.getState());
				historyMessage.setSendDate(sysHistoryMessage.getSendDate());
				historyMessage.setMessageType(sysHistoryMessage.getMessageType());
			}
		}
		return historyMessage;
	}
	
	/**
	 * 获取当前最新的消息
	 * @param user
	 * @return
	 */
	public List<SysBackLogMessage> getCurrentMessage(CmUser user) {
		String userId = "";
		List<SysBackLogMessage> backlogList = new ArrayList<SysBackLogMessage>();
		
		if(user != null){
			userId = user.getUserId();
		}
		//消息表查询条件
		String strMessage = "";
		if(userId != ""){
			strMessage = "from SysBackLogMessage where state <> '2' and sendDate = to_date('" + DateTool.getDate() + "','yyyy-mm-dd') and cmUser.userId ='" + userId + "'";
		} else {
			strMessage = "from SysBackLogMessage where state <> '2' and sendDate = to_date('" + DateTool.getDate() + "','yyyy-mm-dd') ";
		}
		//将查询结果放入List集合
		backlogList = this.findPageByQuery(strMessage);
		return backlogList;
	}
	
	/**
	 * 获取当前最新消息的条数
	 * @param user
	 * @return
	 */
	public int getNewMessage(CmUser user) {
		List<SysBackLogMessage> backlogList = this.getCurrentMessage(user);
		
		if(backlogList.isEmpty()){
			return 0;
		} else {
			return backlogList.size();
		}
	}
	
	/**
	 * 获取当前最新单条记录
	 * @param user
	 * @return
	 */
	public SysBackLogMessage getCurrentSingle(CmUser user) {
		SysBackLogMessage sysMessage = new SysBackLogMessage();
		List<SysBackLogMessage> backlogList = this.getCurrentMessage(user);
		if(this.getNewMessage(user) == 1){
			for (SysBackLogMessage sysBackLogMessage : backlogList) {
				sysMessage.setId(sysBackLogMessage.getId());
				sysMessage.setTitle(sysBackLogMessage.getTitle());
				sysMessage.setMessageType(sysBackLogMessage.getMessageType());
			}
		}
		return sysMessage;
	}
	
	/**
	 * 更新消息状态
	 * @param id 当前消息的id
	 * @param state "1"为未读，"2"已读
	 */
	public void updateState(Serializable id, String state) {
		//根据当前id查询对应详细信息
		SysBackLogMessage backLogMessage = this.findById(SysBackLogMessage.class, id);
			//若在消息表中查询到该条记录，则在消息表中执行查看命令，否则将在历史消息表进行查询并查看该条记录操作
		if(backLogMessage != null){
				backLogMessage.setState(state);
				this.update(backLogMessage, id);
		} else {
			SysHistoryMessage historyMessage = this.findById(SysHistoryMessage.class, id);
			historyMessage.setState(state);
			this.update(historyMessage, id);
		}
		
		
	}
	
	/**
	 * 更新消息状态
	 * @param user 从session中获取的当前用户信息
	 * @param state "1"为未读，"2"已读
	 */
	public void updateState(CmUser user, String state) {

		//从消息表中获取未读消息
		List<SysBackLogMessage> messageList = this.getBackLogMessage(user);
		//从历史表中获取未读消息
		List<SysHistoryMessage> historyList = this.getHistoryMessages(user);
		
		//对消息表中的消息状态进行处理
		if(messageList != null){
			for (SysBackLogMessage backLogMessage : messageList) {
				backLogMessage.setState(state);
				this.update(backLogMessage, backLogMessage.getId());
			}
		}
		
		//对历史表中的消息状态进行处理
		if(historyList != null){
			for (SysHistoryMessage historyMessage : historyList) {
				historyMessage.setState(state);
				this.update(historyMessage, historyMessage.getId());
			}
		}
		
	}
	
	/**
	 * 将两个表中获取的List数据存入至一个新的公共List
	 * @param backlogList
	 * @param historyList
	 * @return commonList
	 */
	public List<CommonMessage> sortList(List<SysBackLogMessage> backlogList, List<SysHistoryMessage> historyList, CmUser user) {
		List<CommonMessage> commonList = new ArrayList<CommonMessage>();
		//当消息List不为空时，将其添加进入公共的List中去
		if(!backlogList.isEmpty()){
			for (SysBackLogMessage sysBackLogMessage : backlogList) {
				CommonMessage commonMessage = new CommonMessage();
				commonMessage.setMessageId(sysBackLogMessage.getId());
				commonMessage.setMessageTitle(sysBackLogMessage.getTitle());
				commonMessage.setMessageLaunchTime(sysBackLogMessage.getSendDate());
				commonMessage.setMessageType(sysBackLogMessage.getMessageType());
				commonMessage.setMessageContent(sysBackLogMessage.getContent());
				//对发送人的处理
				if(sysBackLogMessage.getSendUserid() != null){
					commonMessage.setMessageLauncher(this.findById(CmUser.class, sysBackLogMessage.getSendUserid()).getName());
				}
				commonMessage.setMessageStatus(sysBackLogMessage.getState());
				if(sysBackLogMessage.getCmUser() != null){
					commonMessage.setMessageReceiver(sysBackLogMessage.getCmUser().getName());
				}
				commonMessage.setMessageReadTime(sysBackLogMessage.getReadTime());
				commonMessage.setMessageSource("sysBackLogMessage");
				commonList.add(commonMessage);
			}
		}
		//当历史消息List不为空时，将其添加进入公共的List中去
		if(!historyList.isEmpty()){
			for (SysHistoryMessage sysHistoryMessage : historyList) {
				CommonMessage commonMessage = new CommonMessage();
				commonMessage.setMessageId(sysHistoryMessage.getId());
				commonMessage.setMessageTitle(sysHistoryMessage.getTitle());
				commonMessage.setMessageLaunchTime(sysHistoryMessage.getSendDate());
				commonMessage.setMessageType(sysHistoryMessage.getMessageType());
				commonMessage.setMessageContent(sysHistoryMessage.getContent());
				//处理发送人
				if(sysHistoryMessage.getSendUserid() != null){
					commonMessage.setMessageLauncher(this.findById(CmUser.class, sysHistoryMessage.getSendUserid()).getName());
				}
				commonMessage.setMessageStatus(sysHistoryMessage.getState());
				//对接收人的处理
				if(sysHistoryMessage.getCmUser() != null){
					commonMessage.setMessageReceiver(sysHistoryMessage.getCmUser().getName());
				}
				commonMessage.setMessageReadTime(sysHistoryMessage.getReadTime());
				commonMessage.setMessageSource("sysHistoryMessage");
				commonList.add(commonMessage);
			}
		}
		
		//对公共的List进行排序
		Collections.sort(commonList,new Comparator<CommonMessage>(){
            public int compare(CommonMessage orgMessage, CommonMessage newMessage) {
            	//消息状态排序条件
            	int statusFlag = orgMessage.getMessageStatus().compareTo(newMessage.getMessageStatus());
            	int timeFlag = newMessage.getMessageLaunchTime().compareTo(orgMessage.getMessageLaunchTime());
            	int idFlag = newMessage.getMessageId().compareTo(orgMessage.getMessageId());
            	if(statusFlag == 0){
            		return timeFlag;
            	} else if(timeFlag == 0){
            		return idFlag;
            	}
            	return statusFlag;
            }
        });
		
		return commonList;
	}
	
	/**
	 * 根据用户来获取不同的消息
	 * @param listTbUser
	 * @param messageType
	 * @param context
	 */
	public void saveByUser(List<CmUser> listTbUser, String messageType, String context, String sendUserid) {
		List<SysBackLogMessage> listSysBackLogMessage = new ArrayList<SysBackLogMessage>();
		for(CmUser cmUser : listTbUser){
			SysBackLogMessage sysBackLogMessage = new SysBackLogMessage();
			sysBackLogMessage.setCmUser(cmUser);
			sysBackLogMessage.setSendUserid(sendUserid);
			sysBackLogMessage.setSendDate(new Date());
			sysBackLogMessage.setMessageType(messageType);
			sysBackLogMessage.setContent(context.getBytes());
			sysBackLogMessage.setState("1");
			sysBackLogMessage.setTitle("B".equals(messageType) ? "业务变更消息" : "待办事宜消息");
			listSysBackLogMessage.add(sysBackLogMessage);
		}
		
		this.saveList(listSysBackLogMessage);
	}
	
}
