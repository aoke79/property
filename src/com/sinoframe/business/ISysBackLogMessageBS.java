/**
 * @Title IBackLogMessageBS
 * @Description the service layer interface of "BackLogMessage"
 */

package com.sinoframe.business;

import java.io.Serializable;
import java.util.List;

import com.sinoframe.bean.CommonMessage;
import com.sinoframe.bean.SysBackLogMessage;
import com.sinoframe.bean.SysHistoryMessage;
import com.sinoframe.bean.CmUser;


public interface ISysBackLogMessageBS extends IService {
	/**
	 * 添加系统消息
	 * @param user 从session中获取的用户信息
	 * @param sysBackLogMessage
	 */
	public void insertMessage(CmUser user, SysBackLogMessage sysBackLogMessage, String content);
	/**
	 * 删除一条记录
	 * @param id 从页面获取的ID
	 */
	public void deleteMessage(Serializable id);
	/**
	 * 删除多条记录
	 * @param ids
	 */
	public void deleteMessages(String ids);
	/**
	 * 查询消息表中未读消息的条数
	 * @return 
	 */
	public int getMessage(CmUser user);
	/**
	 * 查询历史消息表中未读消息的条数
	 * @return 
	 */
	public int getHistoryMessage(CmUser user);
	/**
	 * 为单条记录时获取当前记录的对象
	 * @param user
	 * @return
	 */
	public SysBackLogMessage getSingleMessage(CmUser user);
	/**
	 * 为单条记录时获取当前历史记录的对象
	 * @param user
	 * @return
	 */
	public SysHistoryMessage getSingleHistory(CmUser user);
	/**
	 * 获取当前最新消息的条数
	 * @param user
	 * @return
	 */
	public int getNewMessage(CmUser user);
	/**
	 * 获取当前最新单条记录
	 * @param user
	 * @return
	 */
	public SysBackLogMessage getCurrentSingle(CmUser user);
	/**
	 * 更新消息状态
	 * @param user 从session中获取的当前用户信息
	 * @param state "1"为未读，"2"已读
	 */
	public void updateState(Serializable id, String state);
	/**
	 * 更新消息状态为已读
	 * @param id
	 */
	public void updateState(CmUser user, String state);
	/**
	 * 将两个表中获取的List数据存入至一个新的公共List
	 * @param backlogList
	 * @param historyList
	 * @return commonList
	 */
	public List<CommonMessage> sortList(List<SysBackLogMessage> backlogList, List<SysHistoryMessage> historyList, CmUser user);
	/**
	 * 根据用户来获取不同的消息
	 * @param listTbUser
	 * @param messageType
	 * @param context
	 */
	public void saveByUser(List<CmUser> listTbUser, String messageType, String context, String sendUserid);
}