package com.sms.data.hr.peopledept.business;
import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.data.hr.peopledept.bean.HrSmsinfo;

public interface IHrSmsinfoBS extends IService {
	/**
	 * 查询列表
	 */
	public List<HrSmsinfo> searchHrSmsList(SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 增加HR和SMS部门信息
	 */
	public void addHrSms(HrSmsinfo hrSmsinfo,String cmuser);
	
	/**
	 * 批量删除
	 */
	public void multipleDelet(String ids);

}
