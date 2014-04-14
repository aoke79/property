package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseComment;

public interface ITfQualBaseCommentBS extends IService {
	/**
	 * 
	 * @param sysPageInfo  分页信息
	 * @param query 查询条件
	 * @param sysOrderByInfo 排序信息
	 * @return  
	 */
	public List<TfQualBaseComment> findAllByQuery(SysPageInfo sysPageInfo,HashMap<String, String> query ,SysOrderByInfo sysOrderByInfo);
	public void BatchDeleteByIds(TfQualBaseComment tfQualBaseComment,String ids);
	public void saveAndUpdate(TfQualBaseComment tfQualBaseComment);
	
	public long commentListCount(HashMap<String, String> decodeQuery);
	
	public List<TfQualBaseComment> searchCommentList(SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> decodeQuery);
	
	public long roleListCount(HashMap<String, String> decodeQuery);
	
	public List<com.sinoframe.bean.SysRole> searchRoleList(
			SysPageInfo sysPageInfo, SysOrderByInfo sysOrderByInfo,
			HashMap<String, String> decodeQuery);
	/**
	 * 
	 * @param businessId
	 * @param tableName
	 */
	public void deleteFileById(String businessId, String tableName);

}
