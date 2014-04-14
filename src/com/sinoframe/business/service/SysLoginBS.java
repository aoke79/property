package com.sinoframe.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysPortalData;
import com.sinoframe.business.ISysLoginBS;
import com.sinoframe.common.ConstantList;
import com.sms.security.basicdata.bean.CmAttachment;

@Service("sysLoginBS")
public class SysLoginBS extends BaseBS implements ISysLoginBS {
	
	@Override
	public Map<String, List<CmAttachment>> getFiles() {
		Map<String, List<CmAttachment>> fileMap = new HashMap<String, List<CmAttachment>>();
//		//QAR典型超限事件
//		List<CmAttachment> qarEventList = this.getFilesBySubject(ConstantList.FILE_QAR_TYPICAL_EVENT, 10);
//		if(!qarEventList.isEmpty()) {
//			fileMap.put("QAR_TYPICAL_EVENT", qarEventList);
//		}
		//QAR周报
//		List<CmAttachment> qarWeekList = this.getFilesBySubject(ConstantList.FILE_QAR_WEEK_REPORT, 10);
//		if(!qarWeekList.isEmpty()) {
//			fileMap.put("QAR_WEEK_REPORT", qarWeekList);
//		}
		//QAR月报
//		List<CmAttachment> qarMonthList = this.getFilesBySubject(ConstantList.FILE_QAR_MONTH_REPORT, 10);
//		if(!qarMonthList.isEmpty()) {
//			fileMap.put("QAR_MONTH_REPORT", qarMonthList);
//		}
		//事件报告
//		List<CmAttachment> eventList = this.getFilesBySubject(ConstantList.FILE_EVENT_REPORT, 10);
//		if(!eventList.isEmpty()) {
//			fileMap.put("EVENT_REPORT", eventList);
//		}
		//生产讲评会
//		List<CmAttachment> productionList = this.getFilesBySubject(ConstantList.FILE_PRODUCE_APPRAISE, 10);
//		if(!productionList.isEmpty()){
//			fileMap.put("PRODUCE_APPRAISE", productionList);
//		}
		return fileMap;
	}
	
	/**
	 * 根据数据类型及需要的条目数获取数据集合
	 * @param dataType
	 * @param columns
	 * @return
	 */
	public List<SysPortalData> getDataBySubject(String dataType, int columns) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("from SysPortalData where dataType = '").append(dataType).append("' ");
		String orderInfo = "modifyTime desc ";
		SysPageInfo sysPageInfo = new SysPageInfo();
		sysPageInfo.setPageSize(columns);
		sysPageInfo.setMaxCount(columns);
		sysPageInfo.setStartIndex(0);
		return this.findPageByQuery(sysPageInfo, buffer.toString(), null, orderInfo);
	}
	
	/**
	 * 根据关联的数据类型，表名及需要的条目数获取附件
	 * @param portalType
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public List<CmAttachment> getFilesByPortal(String pId, String tableName) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("from CmAttachment where fromtablename = '").append(tableName).append("' ")
			  .append("and sysPortalData.pid = '").append(pId).append("' ")
			  .append("order by nlssort(attchname,'NLS_SORT=SCHINESE_PINYIN_M') desc ");
		return this.findPageByQuery(buffer.toString());
	}
	
	/**
	 * 获取附件对应的数据信息
	 * @return
	 */
	public Map<String, List<SysPortalData>> getData() {
		Map<String, List<SysPortalData>> dataMap = new HashMap<String, List<SysPortalData>>();
		//日报
		List<SysPortalData> DPList = this.getDataBySubject(ConstantList.PORTAL_DAY_REPORT, 10);
		if(!DPList.isEmpty()){
			dataMap.put("DAY_REPORT", DPList);
		}
		//QAR周报
		List<SysPortalData> QWList = this.getDataBySubject(ConstantList.PORTAL_QAR_WEEK_REPORT, 10);
		if(!QWList.isEmpty()){
			dataMap.put("QAR_WEEK_REPORT", QWList);
		}
		//QAR月报
		List<SysPortalData> QMList = this.getDataBySubject(ConstantList.PORTAL_QAR_MONTH_REPORT, 10);
		if(!QMList.isEmpty()){
			dataMap.put("QAR_MONTH_REPORT", QMList);
		}
		//生产讲评会
		List<SysPortalData> PAList = this.getDataBySubject(ConstantList.PORTAL_PRODUCE_APPRAISE, 10);
		if(!PAList.isEmpty()) {
			dataMap.put("PRODUCE_APPRAISE", PAList);
		}
		//行业信息
		List<SysPortalData> INList = this.getDataBySubject(ConstantList.PORTAL_INDUSTRY_NEWS, 10);
		if(!INList.isEmpty()) {
			dataMap.put("INDUSTRY_NEWS", INList);
		}
		//航空安全
		List<SysPortalData> ASList = this.getDataBySubject(ConstantList.PORTAL_AVIATION_SAFETY, 10);
		if(!ASList.isEmpty()) {
			dataMap.put("AVIATION_SAFETY", ASList);
		}
		//文件与通告
		List<SysPortalData> FNList = this.getDataBySubject(ConstantList.PORTAL_FILES_NOTIFICATION, 10);
		if(!FNList.isEmpty()) {
			dataMap.put("FILES_NOTIFICATION", FNList);
		}
		//安全文化
		List<SysPortalData> SLList = this.getDataBySubject(ConstantList.PORTAL_SAFETY_LECTURE, 10);
		if(!SLList.isEmpty()) {
			dataMap.put("SAFETY_LECTURE", SLList);
		}
		//SMS手册
		List<SysPortalData> SMList = this.getDataBySubject(ConstantList.PORTAL_SMS_MANUAL, 5);
		if(!SMList.isEmpty()) {
			dataMap.put("SMS_MANUAL", SMList);
		}
		return dataMap;
	}
	
}
