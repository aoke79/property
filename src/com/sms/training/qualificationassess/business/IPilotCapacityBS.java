package com.sms.training.qualificationassess.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualificationassess.bean.PilotCapacity;

public interface IPilotCapacityBS extends IService {
	/**
	 * 查询列表
	 */
	public List<PilotCapacity> searchPilotCapacityList(SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 增加机长及副驾动力
	 */
	public void addPilotCapacity(PilotCapacity pilotCapacity,String cmuser);
	
	/**
	 * 批量删除
	 */
	public void multipleDelet(String ids);

	
	


}
