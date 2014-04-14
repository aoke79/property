package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;
import com.sms.training.qualification.bean.TfQualPilotConditions;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;

public interface ITfQualPilotConditionsBS extends IService {
	/**
	 * 
	 * @param sysPageInfo  分页信息
	 * @param query 查询条件
	 * @param sysOrderByInfo 排序信息
	 * @return  人员信息
	 */
	public List<CmPeople> getPilotList(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo);
	/**
	 * 通过pilotid查找准入条件
	 * @param pilotid
	 * @return
	 */
	public List <TfQualPilotConditions> getQualPilotConditionsByPilotId(String pilotid);
	/**
	 * 获取所有准入条件记录
	 * @return
	 */
	public  List<TfQualBaseConditions> getAllTfQualBaseConditions();
	/**
	 * 得到飞行员准入信息
	 * @param pilotid
	 * @return
	 */
	public TfQualBasePilotInfo getTfQualBasePilotInfo(String pilotid);
	/**
	 * 得到飞行员技术等级信息
	 * @param pilotid
	 * @return
	 */
	
	public List<Object> getTfQualPilotTechrecord(String pilotid);
	/**
	 * 保存飞行员准入信息
	 * @param ids
	 * @param pilotid
	 */
	public void saveTfQualPilotConditions(String ids,CmPeople cmPeople);
	/**
	 * 获取技术等级页面信息
	 * 
	 * @param pilotid
	 */
	public List<Object> findLibraryGrade(String id);
	/**
	 * 获取资格信息页面信息
	 * 
	 * @param pilotid
	 */
	public List<Object> findLibraryQualifications(String id);
	/**
	 * 获取英语资质页面信息
	 * 
	 * @param pilotid
	 */
	public List<Object> findLibraryEnglish(String id);
	/**
	 * 获取证书类页面信息
	 * 
	 * @param pilotid
	 */
	public List<Object> findLibraryCertificate(String id);
	/**
	 * 获取特殊运行资质页面信息
	 * 
	 * @param pilotid
	 */
	public List<Object> findLibrarySpecial(String id);
	/**
	 * 获取训练记录页面信息
	 * 
	 * @param pilotid
	 */
	public List<Object> findLibraryTrainingRecords(String id);

}
