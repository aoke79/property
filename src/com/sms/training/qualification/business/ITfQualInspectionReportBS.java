package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.SysRole;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.InspectionFormData;
import com.sms.training.qualification.bean.TfQualBaseComment;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualInspectionReport;
import com.sms.training.qualification.bean.TfQualInspectionReportD;

public interface ITfQualInspectionReportBS extends IService {

	TfQualInspectionReport findInspectionReport(String detailsid, String reportType);

	List<TfQualInspectionReportD> findResultList(String reportid);

	void saveResults(InspectionFormData inspectionForm, TfQualInspectionReport report) throws Exception;

	List<TfQualBaseComment> getcommentTemplates(List<SysRole> roles,String ctype);
	/**
	 * 根据申报人员明细id和最后修改人  查找对应的 本场训练单
	 * @param detailId 申报人员明细id
	 * @param modifier 最后修改人
	 * @param reportType 单子类型
	 * @return 本场训练单
	 */
	public TfQualInspectionReport findByDetailIdAndModifier(String detailId,String modifier,String reportType);

}
