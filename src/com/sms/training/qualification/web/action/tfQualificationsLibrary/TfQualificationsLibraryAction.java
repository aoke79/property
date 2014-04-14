package com.sms.training.qualification.web.action.tfQualificationsLibrary;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseConditions;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;
import com.sms.training.qualification.bean.TfQualPilotConditions;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.bean.TfQualPilotTechrecord;
import com.sms.training.qualification.business.ITfQualPilotConditionsBS;

@Results( {
	@Result(name = "toQualLibraryGrade", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryGrade.jsp"),
	@Result(name = "qualifications", location = "/sms/training/qualification/qualificationsLibrary/qualifications.jsp"),
	@Result(name = "qualificationsLibraryAssess", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryAssess.jsp"),
	@Result(name = "qualificationsLibraryCertificate", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryCertificate.jsp"),
	@Result(name = "qualificationsLibraryEnglish", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryEnglish.jsp"),
	@Result(name = "qualificationsLibraryGrade", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryGrade.jsp"),
	@Result(name = "qualificationsLibraryOther", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryOther.jsp"),
	@Result(name = "qualificationsLibraryRisk", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryRisk.jsp"),
	@Result(name = "qualificationsLibrarySpecial", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibrarySpecial.jsp"),
	@Result(name = "qualificationsLibraryLicense", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryLicense.jsp"),
	@Result(name = "qualificationsLibraryTrainingRecords", location = "/sms/training/qualification/qualificationsLibrary/qualificationsLibraryTrainingRecords.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
@ParentPackage("sinoframe-default")
public class TfQualificationsLibraryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ITfQualPilotConditionsBS tfQualPilotConditionsBS;
	//人员信息
	private CmPeople cmPeople;
	//准入信息
	private List <TfQualPilotConditions> tfQualPilotConditionsList;
	//准入基本信息
	private  List<TfQualBaseConditions> tfQualBaseConditionsList;
	//飞行时间准入信息
	private TfQualBasePilotInfo tfQualBasePilotInfo ;
	//飞行员执照机型技术等级信息
	private List<Object> tfQualPilotLicenceList;
	
	//获取技术等级页面信息
	public String toQualLibraryGrade() {
		tfQualPilotLicenceList=tfQualPilotConditionsBS.findLibraryGrade(cmPeople.getId());	
		return "toQualLibraryGrade";
	}
    //获取资格信息页面信息
	public String toQualifications() {
	    tfQualPilotLicenceList=tfQualPilotConditionsBS.findLibraryQualifications(cmPeople.getId());
		return "qualifications";
	}

	public String toQualificationsLibraryAssess() {
		return "qualificationsLibraryAssess";
	}
    //获取证书类页面信息
	public String toQualificationsLibraryCertificate() {
		tfQualPilotLicenceList=tfQualPilotConditionsBS.findLibraryCertificate(cmPeople.getId());
		return "qualificationsLibraryCertificate";
	}
    //获取英语资质页面信息
	public String toQualificationsLibraryEnglish() {
		tfQualPilotLicenceList=tfQualPilotConditionsBS.findLibraryEnglish(cmPeople.getId());
		return "qualificationsLibraryEnglish";
	}
	//获取训练记录页面信息
	public String toQualificationsLibraryTrainingRecords() {
		tfQualPilotLicenceList=tfQualPilotConditionsBS.findLibraryTrainingRecords(cmPeople.getId());
		return "qualificationsLibraryTrainingRecords";
	}

	public String toQualificationsLibraryLicense() {
		return "qualificationsLibraryLicense";
	}
	public String toQualificationsLibraryRisk() {
		return "qualificationsLibraryRisk";
	}
	 //获取特殊运行资质页面信息
	public String toQualificationsLibrarySpecial() {
		tfQualPilotLicenceList=tfQualPilotConditionsBS.findLibrarySpecial(cmPeople.getId());
		return "qualificationsLibrarySpecial";
	}

	public String toQualificationsLibraryOther() {
		try {
			this.cmPeople = tfQualPilotConditionsBS.findById(CmPeople.class,cmPeople.getId());
			this.tfQualPilotConditionsList = tfQualPilotConditionsBS.getQualPilotConditionsByPilotId(cmPeople.getId());
			this.tfQualBaseConditionsList = tfQualPilotConditionsBS.getAllTfQualBaseConditions();
			this.tfQualBasePilotInfo = tfQualPilotConditionsBS.getTfQualBasePilotInfo(cmPeople.getId());
			this.tfQualPilotLicenceList = tfQualPilotConditionsBS.getTfQualPilotTechrecord(cmPeople.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qualificationsLibraryOther";
	}

	public ITfQualPilotConditionsBS getTfQualPilotConditionsBS() {
		return tfQualPilotConditionsBS;
	}

	public void setTfQualPilotConditionsBS(
			ITfQualPilotConditionsBS tfQualPilotConditionsBS) {
		this.tfQualPilotConditionsBS = tfQualPilotConditionsBS;
	}

	public CmPeople getCmPeople() {
		return cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	public List<TfQualPilotConditions> getTfQualPilotConditionsList() {
		return tfQualPilotConditionsList;
	}

	public void setTfQualPilotConditionsList(
			List<TfQualPilotConditions> tfQualPilotConditionsList) {
		this.tfQualPilotConditionsList = tfQualPilotConditionsList;
	}

	public List<TfQualBaseConditions> getTfQualBaseConditionsList() {
		return tfQualBaseConditionsList;
	}

	public void setTfQualBaseConditionsList(
			List<TfQualBaseConditions> tfQualBaseConditionsList) {
		this.tfQualBaseConditionsList = tfQualBaseConditionsList;
	}

	public TfQualBasePilotInfo getTfQualBasePilotInfo() {
		return tfQualBasePilotInfo;
	}

	public void setTfQualBasePilotInfo(TfQualBasePilotInfo tfQualBasePilotInfo) {
		this.tfQualBasePilotInfo = tfQualBasePilotInfo;
	}

	public List<Object> getTfQualPilotLicenceList() {
		return tfQualPilotLicenceList;
	}

	public void setTfQualPilotLicenceList(List<Object> tfQualPilotLicenceList) {
		this.tfQualPilotLicenceList = tfQualPilotLicenceList;
	}
	
}

