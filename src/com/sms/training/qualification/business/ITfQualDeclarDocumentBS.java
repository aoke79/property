package com.sms.training.qualification.business;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualDeclarDocument;
import com.sms.training.qualification.bean.TfQualDeclarSeal;

public interface ITfQualDeclarDocumentBS extends IService{
	public TfQualDeclarSeal getTfQualDeclarSealByOrgId(String orgId);
	public BaseAirPlanType getBaseAirPlanTypeByAtrId(String atrid);
	/**
	 * 根据呈批件类型 及 申报信息id查找对应呈批件
	 * @param planType
	 * @param declaredinfoid
	 * @return
	 */
	public TfQualDeclarDocument findDocument(String planType,String declaredinfoid);
	public String getMaxHeadNum(String constr,String numstr);
	public void addTableInfoToPolit(String type,String ids,String documentId);
	public void delDocIfCreatedForVerify(String type,String ids);
	public void delDocIfCreatedForDeclara(String creater,String plantype,String declareinfoid);
}
