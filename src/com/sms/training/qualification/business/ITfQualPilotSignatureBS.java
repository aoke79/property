package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotSignature;

public interface ITfQualPilotSignatureBS extends IService{
	
	/**
	 * 获取 飞行员签名list
	 * @param sysPageInfo 
	 * @param query 
	 * @param sysOrderByInfo 
	 * @return 
	 */
	List<TfQualPilotSignature> findPilotSigList(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 获取飞行员list
	 * @param sysPageInfo
	 * @param decodeQuery
	 * @param sysOrderByInfo
	 * @return
	 */
	List<CmPeople> findPilotList(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 获取签名图片url 
	 * @param psignatureId 飞行员签名id
	 * @param modulename 模块名
	 * @return
	 */
	String getImgUrl(String psignatureId, String modulename);
	
	/**
	 * 保存文件并更新飞行员签名信息的签名图片url
	 * @param tfQualPilotSignature 飞行员签名
	 * @param modulename
	 * @param fileKey
	 */
//	void save(TfQualPilotSignature tfQualPilotSignature, String modulename,String fileKey);
	
	/**
	 * 删除附件
	 * @param psignatureId 飞行员签名id
	 * @param modulename 模块名
	 */
	void deleteFileById(String psignatureId, String modulename);
	
	/**
	 * 验证
	 * @param account 账号
	 * @param oldPassword 密码
	 * @return bool
	 * @throws Exception
	 */
	boolean valid(String account, String oldPassword) throws Exception;
}
