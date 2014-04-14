package com.sms.training.qualificationassess.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualificationassess.bean.RouteTurnnumber;

public interface IRouteTurnnumberBS extends IService {
	/**
	 * 查询列表
	 */
	public List<RouteTurnnumber> searchRouteTurnnumberList(SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo);
	
	/**
	 * 增加转升路径
	 */
	public void addRouteTurnnumber(RouteTurnnumber routeTurnnumber,String cmuser);
	
	/**
	 * 批量删除
	 */
	public void multipleDelet(String ids);

}
