package com.sms.training.qualification.business.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQuaApplyTeachBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.dao.ITfQuaApplyTeachDAO;

@Service("tfQuaApplyTeachBS")
public class TfQuaApplyTeachBS extends BaseBS implements ITfQuaApplyTeachBS {

	private ITfQuaApplyTeachDAO tfQuaApplyTeachDAO;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;

	@Override
	public void doComputingTeachStaff(String typeId, String orgIds,Date trainDate,String orgName,String subGroupId) {
		TfQualBaseType tfQualBaseType = this.findById(TfQualBaseType.class,typeId.trim());
		List<Object> list =this.tfQuaApplyTeachDAO.findPilotList(tfQualBaseType.getTargetatrid().getId(),orgIds,trainDate,orgName,subGroupId);
		
		tfQualifiedDeclareBS.doSaveGeneralPilot(typeId, list, trainDate);
	}
	
	
	public ITfQuaApplyTeachDAO getTfQuaApplyTeachDAO() {
		return tfQuaApplyTeachDAO;
	}
	@Resource
	public void setTfQuaApplyTeachDAO(ITfQuaApplyTeachDAO tfQuaApplyTeachDAO) {
		this.tfQuaApplyTeachDAO = tfQuaApplyTeachDAO;
	}
	
	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}
	@Resource 
	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}
}
