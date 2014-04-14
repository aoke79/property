package com.sms.training.qualification.dao;

import java.util.List;

import com.sinoframe.dao.IPublicDao;

public interface ITfQualPilotLicenceDAO extends IPublicDao {
	public List<Object> getPilotLicenceList(String pilotid);
	//获取技术等级页面信息
	public List<Object> getLibraryGrade(String pilotId);
	//获取资格信息页面信息
	public List<Object> getLibraryQualifications(String pilotId);
	//获取英语资质页面信息
	public List<Object> getLibraryEnglish(String pilotId);
	//获取证书类页面信息
	public List<Object> getLibraryCertificate(String pilotId);
	//获取特殊运行资质页面信息
	public List<Object> getLibrarySpecial(String pilotId);
	//获取训练记录页面信息
	public List<Object> getLibraryTrainingRecords(String pilotId);
}