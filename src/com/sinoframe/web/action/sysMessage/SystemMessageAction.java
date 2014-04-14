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
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.business.ISysBackLogMessageBS;
import com.sinoframe.business.ISysHistoryMessageBS;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;

@Results({
	@Result(name="list",location="/system/message/messageList.jsp"),
	@Result(name="ulist",location="/system/message/messageUnreadList.jsp"),
	@Result(name="add",location="/system/message/messageAdd.jsp"),
	@Result(name="edit",location="/system/message/messageEdit.jsp"),
	@Result(name="view",location="/system/message/messageView.jsp"),
	@Result(name="single",location="/system/message/messageSingle.jsp"),
	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
	@Result(name="sendMessage", location="/system/message/messageSend.jsp"),
	@Result(name="chooseReceiveUser", location="/system/message/ReceiveUserManage.jsp"),
	@Result(name="METHOD",location="system-message!list",type="redirectAction")
})
public class SystemMessageAction extends BaseAction {

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
	//存储所有用户的集合
	private List<CmUser> listTbUser = new ArrayList<CmUser>();
	//TbUser的service接口
	private ICmUserBS cmUserBS;
	//存储已经选择的用户
	private List<CmUser> hasChooseUsers = new ArrayList<CmUser>();
	
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
	
	public List<CmUser> getListTbUser() {
		return listTbUser;
	}
	public void setListTbUser(List<CmUser> listTbUser) {
		this.listTbUser = listTbUser;
	}
	public ICmUserBS getTbUserBS() {
		return cmUserBS;
	}
	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}
	public List<CmUser> getHasChooseUsers() {
		return hasChooseUsers;
	}
	public void setHasChooseUsers(List<CmUser> hasChooseUsers) {
		this.hasChooseUsers = hasChooseUsers;
	}
	
	/**
	 * 跳转至列表页面
	 * @return "list"
	 */
	public String list() {
		try {
			//从session中获取用户信息
			CmUser user = (CmUser)ActionContext.getContext().getSession().get("user");
			
			sysPageInfo.setPageSize(this.getNumPerPage());
			sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
			
			//进行条数查询的语句
			String countHql = "";
			String countHistory = "";
			
			if(user != null) {
				countHql = "select count(*) from SysBackLogMessage where cmUser.userId = '" + user.getUserId() + "' ";
				countHistory = "select count(*) from SysHistoryMessage where cmUser.userId = '" + user.getUserId() + "'";
			} else {
				countHql = "select count(*) from SysBackLogMessage where 1=1 ";
				countHistory = "select count(*) from SysHistoryMessage where 1=1 ";
			}
			
			long backLogCount = backLogMessageBS.getCountByHQL(countHql, query);
			long historyCount = historyMessageBS.getCountByHQL(countHistory,query);
			
			//设置最大条数
			sysPageInfo.setMaxCount(backLogCount + historyCount);
			//设置当前页
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
			
			//列表
			String hql = "";
			String historyHql = "";
			if(user != null){
				hql = "from SysBackLogMessage where cmUser.userId = '" + user.getUserId() + "' ";
				historyHql = "from SysHistoryMessage where cmUser.userId = '" + user.getUserId() + "' ";
				
			} else {
				hql = "from SysBackLogMessage where 1=1";
				historyHql = "from SysHistoryMessage where 1=1";
			}
			
			sysMessageList = backLogMessageBS.findPageByQuery(sysPageInfo, hql, query, this.getSysOrderByInfo());
			sysHistoryList = backLogMessageBS.findPageByQuery(sysPageInfo, historyHql, query, this.getSysOrderByInfo());
			commonList = backLogMessageBS.sortList(sysMessageList, sysHistoryList, user);
		} catch (Exception e) {
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			historyMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * 跳转至添加页面
	 * @return "add"
	 */
	public String add() {
		return "add";
	}
	
	/**
	 * 保存添加信息
	 * @return "SUCCESS"
	 */
	public String addSave() {
		try {
			//从session中获取用户信息
			CmUser user = (CmUser)ActionContext.getContext().getSession().get("user");
			//从页面获取内容
			String strContent = this.getServletRequest().getParameter("content");
			//执行添加操作
			backLogMessageBS.insertMessage(user,sysBackLogMessage,strContent);
			//设定成功消息
			this.message = this.getSuccessMessage(getText("addSuccess"), "systemMessage", "closeCurrent", "sys-message/system-message!list.do");
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
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
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "systemMessage", "forward", "sys-message/system-message!list.do");
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
			this.message = this.getSuccessMessage(getText("deleteSuccess"), "systemMessage", "forward", "sys-message/system-message!list.do");
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
	 * 关闭查看时触发状态更新事件
	 * @return
	 */
	public String closeSingle() {
		try {
			backLogMessageBS.updateState(sysBackLogMessage.getId(),"2");
			this.message = this.getSuccessMessage(getText("closeMessage"), "main", "closeCurrent", "sys-message/system-message!list.do");
		} catch (Exception e) {
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
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
		} catch(Exception e){
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
		} catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
		}
		return "ulist";
	}
	
	//add by jilili 2011/5/10 start
	public String toSendMessagePage(){
		if(ids != null && !"".equals(ids)){
			String hql = "from CmUser where id in (" + Util.toStringIds(ids)+ ")";
			hasChooseUsers = cmUserBS.findPageByQuery(hql);
		}
		return "sendMessage";
	}
	
	/**
	 * 跳转到选择收件人的页面
	 * @return
	 */
	public String toChooseReceiveUser(){
		if(ids != null && !"".equals(ids)){
			String hql = "from CmUser where id in (" + Util.toStringIds(ids)+ ")";
			hasChooseUsers = cmUserBS.findPageByQuery(hql);
		}
		listTbUser = cmUserBS.findAll();
		return "chooseReceiveUser";
	}
	
	/**
	 * 选择消息的发送对象
	 * @return
	 */
	public String chooseUser(){
		if(ids != null && !"".equals(ids)){
			this.message = this.getSuccessMessage(getText("selected"), "sendMessage", "forward", "sys-message/system-message!toSendMessagePage.do?ids=" + ids);
		}else{
			this.message = this.getFailMessage(getText("noReceiver"));
		}
		return "SUCCESS";
	}
	
	/**
	 * 发送批量消息
	 * @return
	 */
	public String sendMessages(){
		if(ids != null && !"".equals(ids)){
			try{
				String sendUserid = ((CmUser)this.getServletRequest().getSession().getAttribute("user")).getUserId();
				String hql = "from CmUser where id in (" + Util.toStringIds(ids)+ ")";
				listTbUser = cmUserBS.findPageByQuery(hql);
				String strMessageType = this.getServletRequest().getParameter("messageType");
				backLogMessageBS.saveByUser(listTbUser, strMessageType, srtContent,sendUserid);
				this.message = this.getSuccessMessage(getText("sendSuccess"), "systemMessage", "forward", "sys-message/system-message!list.do");
			} catch(Exception e){
				e.printStackTrace();
				this.message = this.getFailMessage(getText("sendFail"));
				backLogMessageBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			}
		}else{
			this.message = this.getFailMessage(getText("noReceiver"));
		}
		return "SUCCESS";
	}
	
	//add by jilili 2011/5/10 end
	
}
