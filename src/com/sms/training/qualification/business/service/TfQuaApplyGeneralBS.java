package com.sms.training.qualification.business.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQuaApplyGeneralBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.dao.ITfQuaApplyGeneralDAO;
@Service("tfQuaApplyGeneralBS")
public class TfQuaApplyGeneralBS extends BaseBS implements ITfQuaApplyGeneralBS {
	
	private TfQualBaseType tfQualBaseType;
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	private ITfQuaApplyGeneralDAO tfQuaApplyGeneralDAO;
	

	public void doComputingGeneralStaff(String typeid,String orgid,String trainDate,String orgName,String subGroupId) {
		try{
			tfQualBaseType = this.findById(TfQualBaseType.class,typeid.trim());
			List<Object> list =this.getPilotListByOrgid(tfQualBaseType.getTargetatrid().getId(),orgid,trainDate,orgName,subGroupId);
//			Date date = DateTool.getNow();
//			date.setYear(Integer.valueOf(trainDate.substring(0, 4)));
//			date.setMonth(Integer.valueOf(trainDate.substring(4, 6)));
			tfQualifiedDeclareBS.doSaveGeneralPilot(typeid, list, new SimpleDateFormat("yyyy-MM").parse(trainDate));
		}catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	@Override
	public List<Object> getPilotListByOrgid(String atrid,String orgid,String trainDate,String orgName,String subGroupId){
		return this.tfQuaApplyGeneralDAO.getPilotListByOrgid(atrid, orgid, trainDate,orgName, subGroupId);
	}
 
	

	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}
	@Resource 
	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}

	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	public ITfQuaApplyGeneralDAO getTfQuaApplyGeneralDAO() {
		return tfQuaApplyGeneralDAO;
	}

	@Resource 
	public void setTfQuaApplyGeneralDAO(ITfQuaApplyGeneralDAO tfQuaApplyGeneralDAO) {
		this.tfQuaApplyGeneralDAO = tfQuaApplyGeneralDAO;
	}


	 
}
