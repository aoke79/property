package com.sinoframe.web.action.sysMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CommonMessage;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysBackLogMessage;
import com.sinoframe.bean.SysHistoryMessage;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.ISysBackLogMessageBS;
import com.sinoframe.business.ISysHistoryMessageBS;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;

@Results({
	@Result(name="ulist",location="/system/message/messageUnreadList.jsp"),
	@Result(name="single",location="/system/message/messageSingle.jsp"),
	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
	@Result(name="view",location="/system/message/messageUnreadView.jsp"),
	@Result(name="METHOD",location="unread-message!ulist",type="redirectAction")
})
public class UnreadMessageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//当前模块名称
	private static String moduleName = "SysMessage";

	//即时消息service接口
	private ISysBackLogMessageBS backLogMessageBS;
	//历史消息service接口
	private ISysHistoryMessageBS historyMessageBS;
	//即时消息实体Bean
	private SysBackLogMessage sysBackLogMessage;
	//历史消息实体Bean
	private SysHistoryMessage sysHistoryMessage;
	//公共消息实体Bean
	private CommonMessage commonMessage;
	//提示信息实体
	private Message message;
	//存放查询结果的List集
	private List<SysBackLogMessage> sysMessageList = new ArrayList<SysBackLogMessage>();
	//存放查询结果的历史消息List集
	private List<SysHistoryMessage> sysHistoryList = new ArrayList<SysHistoryMessage>();
	//存放转换好的消息List集
	private List<CommonMessage> commonList = new ArrayList<CommonMessage>();
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//存放ID
	private String ids;
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	//存放处理过后的字符数组的字符串
	private String srtContent;
	//即时消息条数
	private long messageCount;
	
	public String getSrtContent() {
		return srtContent;
	}
	public void setSrtContent(String srtContent) {
		this.srtContent = srtContent;
	}
	public ISysBackLogMessageBS getBackLogMessageBS() {
		return backLogMessageBS;
	}
	public void setBackLogMessageBS(ISysBackLogMessageBS backLogMessageBS) {
		this.backLogMessageBS = backLogMessageBS;
	}

	public ISysHistoryMessageBS getHistoryMessageBS() {
		return historyMessageBS;
	}
	public void setHistoryMessageBS(ISysHistoryMessageBS historyMessageBS) {
		this.historyMessageBS = historyMessageBS;
	}
	
	public SysBackLogMessage getSysBackLogMessage() {
		return sysBackLogMessage;
	}
	public void setSysBackLogMessage(SysBackLogMessage sysBackLogMessage) {
		this.sysBackLogMessage = sysBackLogMessage;
	}
	
	public SysHistoryMessage getSysHistoryMessage() {
		return sysHistoryMessage;
	}
	public void setSysHistoryMessage(SysHistoryMessage sysHistoryMessage) {
		this.sysHistoryMessage = sysHistoryMessage;
	}
	
	public CommonMessage getCommonMessage() {
		return commonMessage;
	}
	public void setCommonMessage(CommonMessage commonMessage) {
		this.commonMessage = commonMessage;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public List<SysBackLogMessage> getSysMessageList() {
		return sysMessageList;
	}
	public void setSysMessageList(List<SysBackLogMessage> sysMessageList) {
		this.sysMessageList = sysMessageList;
	}
	
	public List<SysHistoryMessage> getSysHistoryList() {
		return sysHistoryList;
	}
	public void setSysHistoryList(List<SysHistoryMessage> sysHistoryList) {
		this.sysHistoryList = sysHistoryList;
	}
	
	public List<CommonMessage> getCommonList() {
		return commonList;
	}
	public void setCommonList(List<CommonMessage> commonList) {
		this.commonList = commonList;
	}
	
	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}
	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public long getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(long messageCount) {
		this.messageCount = messageCount;
	}
	
	/**
	 * 未读消息新增多条时查看列表
	 * @return "list"
	 */
	public String handleList() {
		try{
			//从session中获取用户信息
			CmUser user = (CmUser)ActionContext.getContext().getSession().get("user");
			
			//定义分页的SysPageInfo对象
			sysPageInfo.setPageSize(this.getNumPerPage());
			sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
			
			//进行条件及条数查询的语句
			String hql = "";
			String countHql = "";
			if(user != null){
				hql = "from SysBackLogMessage where state <> '2' and sendDate = to_date('" +
				 	  DateTool.getDate() + "','yyyy-mm-dd') and cmUser.userId = '" + user.getUserId() + "'";
				countHql = "select count(*) from SysBackLogMessage where state <> '2' and sendDate = to_date('" +
				 	  DateTool.getDate() + "','yyyy-mm-dd') and cmUser.userId = '" + user.getUserId() + "'";
			} else {
				hql = "from SysBackLogMessage where state <> '2' and sendDate = to_date('" +
			 	  	DateTool.getDate() + "','yyyy-mm-dd') ";
				countHql = "select count(*) from SysBackLogMessage where state <> '2' and sendDate = to_date('" +
			 	  	DateTool.getDate() + "','yyyy-mm-dd') ";
			}
			sysMessageList = backLogMessageBS.findPageByQuery(sysPageInfo, hql, query, this.getSysOrderByInfo());
			sysHistoryList = new ArrayList<SysHistoryMessage>(0);
			commonList = backLogMessageBS.sortList(sysMessageList, sysHistoryList, user);
			long backLogCount = backLogMessageBS.getCountByHQL(countHql, query);
			
			//设置最大条数
			sysPageInfo.setMaxCount(backLogCount);
			//设置当前页
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
		}
		return "ulist";
	}
	
	/**
	 * 查询所有未读消息
	 * @return
	 */
	public String unreadMessage() {
		try{
			//从session中获取用户信息
			CmUser user = (CmUser)ActionContext.getContext().getSession().get("user");
			
			//定义分页的SysPageInfo对象
			sysPageInfo.setPageSize(this.getNumPerPage());
			sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
			
			String messageCondition = "";
			String historyCondition = "";
			String messageCounts = "";
			String historyCounts = "";
			
			if(user != null){
				//条件查询
				messageCondition = "from SysBackLogMessage where state <> '2' and cmUser.userId = '" + user.getUserId() + "'";
				historyCondition = "from SysHistoryMessage where state <> '2' and cmUser.userId = '" + user.getUserId() + "'";
				
				//条数查询
				messageCounts = "select count(*) from SysBackLogMessage where state <> '2' and cmUser.userId = '" + user.getUserId() + "'";
				historyCounts = "select count(*) from SysHistoryMessage where state <> '2' and cmUser.userId = '" + user.getUserId() + "'";
			} else {
				//条件查询
				messageCondition = "from SysBackLogMessage where state <> '2' ";
				historyCondition = "from SysHistoryMessage where state <> '2' ";
				
				//条数查询
				messageCounts = "select count(*) from SysBackLogMessage where state <> '2' ";
				historyCounts = "select count(*) from SysHistoryMessage where state <> '2' ";
			}
			
			sysMessageList = backLogMessageBS.findPageByQuery(sysPageInfo, messageCondition, query, this.getSysOrderByInfo());
			sysHistoryList = backLogMessageBS.findPageByQuery(sysPageInfo, historyCondition, query, this.getSysOrderByInfo());
			commonList = backLogMessageBS.sortList(sysMessageList, sysHistoryList, user);
			
			long backLogCount = backLogMessageBS.getCountByHQL(messageCounts, query);
			long historyCount = backLogMessageBS.getCountByHQL(historyCounts, query);
			
			//设置最大条数
			sysPageInfo.setMaxCount(backLogCount + historyCount);
			//设置当前页
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			historyMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
		}
		return "ulist";
	}
	
	/**
	 * 删除一条信息
	 * @return "SUCCESS"
	 */
	public String delete() {
		try {
			String id = sysBackLogMessage == null ? sysHistoryMessage.getId() : sysBackLogMessage.getId();
			backLogMessageBS.deleteMessage(id);
			//设定成功消息
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "systemMessage", "forward", "sys-message/system-message!handleList.do");
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			historyMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除多条信息
	 * @return
	 */
	public String multipleDelete() {
		
		try {
			backLogMessageBS.deleteMessages(this.getIds());
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "systemMessage", "forward", "sys-message/system-message!handleList.do");
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 查看消息详细内容
	 * @return "view"
	 */
	public String view(){
		try {
			if(sysBackLogMessage != null){
				//查询当前消息详细内容
				sysBackLogMessage = backLogMessageBS.findById(SysBackLogMessage.class, sysBackLogMessage.getId());
				//对字符数组的处理
				srtContent = new String(sysBackLogMessage.getContent());
			} else {
				//查询历史消息详细内容
				sysHistoryMessage = historyMessageBS.findById(SysHistoryMessage.class, sysHistoryMessage.getId());
				//对字符数组的处理
				srtContent = new String(sysHistoryMessage.getContent());
			}
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 查看单条记录时的操作
	 * @return
	 */
	public String viewSingle() {
		try {
			//查询当前消息详细内容
			sysBackLogMessage = backLogMessageBS.findById(SysBackLogMessage.class, sysBackLogMessage.getId());
			//对字符数组的处理
			srtContent = new String(sysBackLogMessage.getContent());
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "single";
	}
	
	/**
	 * 关闭查看时触发状态更新事件
	 * @return
	 */
	public String closeMessage() {
		try {
			if(!sysBackLogMessage.getId().equals("")){
				backLogMessageBS.updateState(sysBackLogMessage.getId(),"2");
			} else {
				backLogMessageBS.updateState(sysHistoryMessage.getId(),"2");
			}
			this.message = this.getSuccessMessage(getText("closeMessage"), "systemMessage", "closeCurrent", "sys-message/system-message!list.do");
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 关闭单条查看时触发状态更新事件
	 * @return
	 */
	public String closeSingle() {
		try {
			backLogMessageBS.updateState(sysBackLogMessage.getId(),"2");
			this.message = this.getSuccessMessage(getText("closeMessage"), "systemMessage", "closeCurrent", "sys-message/system-message!list.do");
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
}
