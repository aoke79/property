package com.sms.training.qualification.business;

import java.util.List;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseSignature;

public interface ITQuaSignatureBS extends IService{  
	   
	public List<TfQualBaseSignature> findSignatureByStaffId(String staffId) ;
	/**
	 * 
	 * @param businessId
	 * @param tableName
	 */
	public void deleteFileById(String businessId, String tableName);
	
//	/**
//	 * 原始的设置签名图片路径方法   现在已取消
//	 * @param signatureid
//	 * @param moduleName
//	 */
//	public void smashFilePath(String signatureid, String moduleName);
	
	
}
