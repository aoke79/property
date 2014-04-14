package com.sms.training.qualificationassess.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualificationassess.bean.CopilotUpnumber;


public interface ICopilotUpnumberBS extends IService {
	/**
	 * 查询列表
	 */
	public List<CopilotUpnumber> searchCopilotUpnumberList(SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 增加副驾驶升级资格人数
	 */
	public void addCopilotUpnumber(CopilotUpnumber copilotUpnumber,String cmuser);
	
	/**
	 * 批量删除
	 */
	public void multipleDelet(String ids);


}
