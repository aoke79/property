package com.sinoframe.business;

import java.util.List;
import java.util.Map;

import com.sinoframe.bean.SysPortalData;
import com.sms.security.basicdata.bean.CmAttachment;

public interface ISysLoginBS extends IService {
	
	//获取附件
	public Map<String, List<CmAttachment>> getFiles();
	
	/**
	 * 获取附件对应的数据信息
	 * @return
	 */
	public Map<String, List<SysPortalData>> getData();
}
