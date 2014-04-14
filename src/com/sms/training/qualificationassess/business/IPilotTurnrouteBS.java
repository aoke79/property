package com.sms.training.qualificationassess.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualificationassess.bean.PilotTurnroute;

public interface IPilotTurnrouteBS extends IService {
	/**
	 * 查询列表
	 */
	public List<PilotTurnroute> searchTurnrouteList(SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 增加机长转机型路径
	 */
	public void addTurnroute(PilotTurnroute pilotTurnroute,String cmuser);
	
	/**
	 * 批量删除
	 */
	public void multipleDelet(String ids);


}
