package com.sinoframe.business.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysBackLogMessage;
import com.sinoframe.bean.SysHistoryMessage;
import com.sinoframe.business.ISysHistoryMessageBS;
import com.sinoframe.common.exception.RollbackableBizException;
import com.sinoframe.dao.ISysHistoryMessageDao;

@Service("historyMessageBS")
public class SysHistoryMessageBS extends BaseBS implements ISysHistoryMessageBS {
	
	private ISysHistoryMessageDao historyMessageDao;
	
	public ISysHistoryMessageDao getHistoryMessageDao() {
		return historyMessageDao;
	}

	@Resource
	public void setHistoryMessageDao(ISysHistoryMessageDao historyMessageDao) {
		this.historyMessageDao = historyMessageDao;
	}

	/**
	 * 根据固定时间点进行批量添加
	 * @throws RollbackableBizException
	 */
	public void multipleInsert() throws RollbackableBizException {
		
		//获取当前时间
		Date nowDate = new Date();
		//设置转换格式
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		//拼接HQL语句的字符串
		StringBuffer hqlBuffer = new StringBuffer();
		
		hqlBuffer.append(" from SysBackLogMessage log where log.send_date = ");
		hqlBuffer.append(" toDate('").append(formatDate.format(nowDate)).append("'");
		
		//根据条件检索出对应的数据并置于一个List中
		List<SysBackLogMessage> messageList = historyMessageDao.findPageByQuery(hqlBuffer.toString());
		
		List<SysHistoryMessage> historyList = new ArrayList<SysHistoryMessage>();
		
		//数据转换
		for(SysBackLogMessage sysBackLogMessage : messageList){
			SysHistoryMessage sysHistoryMessage = new SysHistoryMessage();
			sysHistoryMessage.setId(sysBackLogMessage.getId());
			sysHistoryMessage.setTitle(sysBackLogMessage.getTitle());
			sysHistoryMessage.setContent(sysBackLogMessage.getContent());
			sysHistoryMessage.setMessageType(sysBackLogMessage.getMessageType());
			sysHistoryMessage.setCmUser(sysBackLogMessage.getCmUser());
			sysHistoryMessage.setSendDate(sysBackLogMessage.getSendDate());
			sysHistoryMessage.setReadTime(sysBackLogMessage.getReadTime());
			sysHistoryMessage.setState(sysBackLogMessage.getState());
			historyList.add(sysHistoryMessage);
		}
		
		historyMessageDao.saveList(historyList);
	}
	
}
