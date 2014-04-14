package com.sms.training.qualification.business;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.KSGZDFormData;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotFlyrecordbook;

public interface ITfQualBaseWorkOrderBS extends IService{

	/**
	 * 获取驾驶员执照编号
	 * @param detail 申报人员明细
	 * @return
	 */
	String findLicenceNo(TfQualDeclaraPilot detail);
	
	/**
	 * //将对象form中封装的数据保存
	 * @param order 考试工作单
	 * @param form 封装了表单数据“考试项目”
	 * @throws Exception
	 */
	void saveItems(TfQualBaseWorkOrder order, KSGZDFormData form) throws Exception;
	
	/**
	 * 填充对象form,将数据库中数据查询出来，填充进form中，以供页面显示
	 * @param order 考试工作单
	 * @param form 封装了表单数据“考试项目”
	 * @throws Exception
	 */
	void fillForm(TfQualBaseWorkOrder order, KSGZDFormData form) throws Exception;
	
	/**
	 * 根据申报人员明细id和当前登录人  查找对应的 考试工作单
	 * @param detailId 申报人员明细id
	 * @param modifier 最后修改人
	 * @return 考试工作单
	 */
	public TfQualBaseWorkOrder findByDetailIdAndModifier(String detailId,String modifier);
}
