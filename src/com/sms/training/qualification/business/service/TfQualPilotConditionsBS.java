package com.sms.training.qualification.business.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;
import com.sms.training.qualification.bean.TfQualPilotConditions;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQualPilotConditionsBS;
import com.sms.training.qualification.dao.ITfQualPilotLicenceDAO;

@Service("tfQualPilotConditionsBS")
public class TfQualPilotConditionsBS extends BaseBS implements ITfQualPilotConditionsBS {
	private ITfQualPilotLicenceDAO tfQualPilotLicenceDAO;

	@Override
	public List<CmPeople> getPilotList(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo) {
		String hql = "from CmPeople c where c.sort='P'";
		String countHql ="select count(*) from CmPeople c where c.sort='P' ";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		return this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	}

	@Override
	public List<TfQualPilotConditions> getQualPilotConditionsByPilotId(String pilotid) {
		String hql = "from TfQualPilotConditions t where t.cmPeople.id='"+pilotid+"'";
		return this.findPageByQuery(hql);
	}

	@Override
	public  List<TfQualBaseConditions> getAllTfQualBaseConditions() {
		String hql = "from TfQualBaseConditions";
		return this.findPageByQuery(hql);
	}

	@Override
	public TfQualBasePilotInfo getTfQualBasePilotInfo(String pilotid) {
		String hql = "from TfQualBasePilotInfo t where t.cmPeople.id='"+pilotid+"'";
		List<TfQualBasePilotInfo>TfQualBasePilotInfoList =  this.findPageByQuery(hql);
		TfQualBasePilotInfo tfQualBasePilotInfo = new TfQualBasePilotInfo();
		if(TfQualBasePilotInfoList != null && TfQualBasePilotInfoList.size()>0){
			tfQualBasePilotInfo = TfQualBasePilotInfoList.get(0);
		} 
		return tfQualBasePilotInfo;
	}

	@Override
	public List<Object> getTfQualPilotTechrecord(String pilotid) {
		return this.tfQualPilotLicenceDAO.getPilotLicenceList(pilotid);
	}

	@Override
	public void saveTfQualPilotConditions(String ids, CmPeople cmPeople) {
		String[] conditionsArray = StringUtils.split(ids==null?"":ids, ',');
		this.deleteTfQualPilotConditions(cmPeople.getId());
		for (int i = 0, j = conditionsArray.length; i < j; i++) {
			String[] conditionAndValue = StringUtils.split(conditionsArray[i],"#_#");
			String conditionId = conditionAndValue[0];
			String value = conditionAndValue[1];
			if (conditionId != null && !conditionId.equals("")) {
				TfQualPilotConditions tfQualPilotConditions = new TfQualPilotConditions();
				TfQualBaseConditions tfQualBaseConditions = new TfQualBaseConditions();
				tfQualBaseConditions.setConditionid(conditionId.trim());
				tfQualPilotConditions.setCmPeople(cmPeople);
				tfQualPilotConditions.setTfQualBaseConditions(tfQualBaseConditions);
				tfQualPilotConditions.setStatus(value);
				this.save(tfQualPilotConditions);
			}
		}
	}
	private void deleteTfQualPilotConditions(String pilotid){
		try {
			String hql = "delete from TfQualPilotConditions t where t.cmPeople.id='"+pilotid+"'";
			this.executeUpdate(hql);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public ITfQualPilotLicenceDAO getTfQualPilotLicenceDAO() {
		return tfQualPilotLicenceDAO;
	}

	@Resource 
	public void setTfQualPilotLicenceDAO(ITfQualPilotLicenceDAO tfQualPilotLicenceDAO) {
		this.tfQualPilotLicenceDAO = tfQualPilotLicenceDAO;
	}
    //获取技术等级页面信息
	@Override
	public List<Object> findLibraryGrade(String pilotId) {
		return this.tfQualPilotLicenceDAO.getLibraryGrade(pilotId);
		
	}
	//获取资格信息页面信息
	@Override
	public List<Object> findLibraryQualifications(String pilotId) {
		return this.tfQualPilotLicenceDAO.getLibraryQualifications(pilotId);
		
	}
	//获取英语资质页面信息
	@Override
	public List<Object> findLibraryEnglish(String pilotId) {
		return this.tfQualPilotLicenceDAO.getLibraryEnglish(pilotId);
	}
	//获取证书类页面信息
	@Override
	public List<Object> findLibraryCertificate(String pilotId) {
		return this.tfQualPilotLicenceDAO.getLibraryCertificate(pilotId);
	}
	//获取特殊运行资质页面信息
	@Override
	public List<Object> findLibrarySpecial(String pilotId) {
		return this.tfQualPilotLicenceDAO.getLibrarySpecial(pilotId);
	}
	//获取训练记录页面信息
	@Override
	public List<Object> findLibraryTrainingRecords(String pilotId) {
		return this.tfQualPilotLicenceDAO.getLibraryTrainingRecords(pilotId);
	}

}
