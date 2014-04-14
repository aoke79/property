package com.sms.training.qualification.business.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.business.ITQuaSignatureBS;


@Service("tfQuaSignatureBS")
public class TfQuaSignatureBS extends BaseBS implements ITQuaSignatureBS{
	
	/**
	 * 
	 * @param businessId
	 * @param tableName
	 */
	public List<TfQualBaseSignature> findSignatureByStaffId(String staffId) {
		
		StringBuffer buffer = new StringBuffer(400);
		buffer.append("from TfQualBaseSignature where staffid = '").append(staffId).append("' ");
		List<TfQualBaseSignature> a= this.findPageByQuery(buffer.toString());
		return  this.findPageByQuery(buffer.toString());
		 
	}
	/**
	 * 
	 * @param businessId
	 * @param tableName
	 */
	public void deleteFileById(String businessId, String tableName) {
		
		StringBuffer buffer = new StringBuffer(400);
		buffer.append("from CmAttachment where fromid = '").append(businessId).append("' ")
			.append(" and fromtablename = '").append(tableName).append("' ");
		List<CmAttachment> fileList = this.findPageByQuery(buffer.toString());
		if(!fileList.isEmpty()){
			for (CmAttachment file : fileList) {
				this.deleteById(CmAttachment.class, file.getAttchid());
			}
		}
	}

	/**
	 * 设置签名图片路径    已改需求，现在已无用  勿删
	 * @param signatureid
	 * @param moduleName
	 */
//	public void smashFilePath(String signatureid, String moduleName) {
//		StringBuffer buffer = new StringBuffer(400);
//		buffer.append("from CmAttachment where fromid = '").append(signatureid).append("' ")
//			.append(" and fromtablename = '").append(moduleName).append("' ");
//		List<CmAttachment> fileList = this.findPageByQuery(buffer.toString());
//		CmAttachment file = new CmAttachment();
//		if(!fileList.isEmpty()){
//			file = fileList.get(0);
//		}
////		String filePath = file.getAttchpath() == null ? "" : file.getAttchpath();
//		
//		/*if(filePath.contains(":")){
//			filePath = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.length());
//		}*/
//		
//		TfQualBaseSignature signature = this.findById(TfQualBaseSignature.class, signatureid);
////		signature.setImageurl(filePath);
//		this.update(signature, signature.getSignatureid());
//	}
	
	
}
