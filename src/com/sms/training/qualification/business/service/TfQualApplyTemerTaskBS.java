package com.sms.training.qualification.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
import com.sms.training.qualification.business.ITfQualApplyTemerTaskBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
@Service("tfQualApplyTemerTaskBS")
public class TfQualApplyTemerTaskBS extends BaseBS implements ITfQualApplyTemerTaskBS  {
	@Resource
	private ITfQualifiedDeclareBS tfQualifiedDeclareBS;
	@Resource
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	@Resource
	private ITfQuaApplyIcaoBS tfQuaApplyIcaoBS;
	
	public void autoExecuteTask(){
		try{
			//升级
			List<String> typeIds = tfQualBaseTypeBS.getTypeIdsByGroupId("QUAL_UP");
			for(String typeId : typeIds){
				 tfQualifiedDeclareBS.doComputingStaff(typeId,"1");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ITfQualifiedDeclareBS getTfQualifiedDeclareBS() {
		return tfQualifiedDeclareBS;
	}

	public void setTfQualifiedDeclareBS(ITfQualifiedDeclareBS tfQualifiedDeclareBS) {
		this.tfQualifiedDeclareBS = tfQualifiedDeclareBS;
	}

	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}
	public ITfQuaApplyIcaoBS getTfQuaApplyIcaoBS() {
		return tfQuaApplyIcaoBS;
	}

	public void setTfQuaApplyIcaoBS(ITfQuaApplyIcaoBS tfQuaApplyIcaoBS) {
		this.tfQuaApplyIcaoBS = tfQuaApplyIcaoBS;
	} 
	 
}
