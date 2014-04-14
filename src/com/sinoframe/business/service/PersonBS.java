package com.sinoframe.business.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IPersonBS;

@Service("personBS")
public class PersonBS extends BaseBS implements IPersonBS {

	@Override
	public List<CmPeople> searchPeople(SysPageInfo sysPageInfo, String currentTag,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		String extraHql = "";
		//选择飞行员
		if(currentTag.contains("Captain")){
			extraHql = "and sort = 'P' and sysOrganization.id <> '8a8a11ef301b2f3301301bff5e7f00cc' ";
		}
		return this.activePerson(sysPageInfo, extraHql, query, sysOrderByInfo);
	}

	@Override
	public long searchPeopleCount(SysPageInfo sysPageInfo, String currentTag,
			HashMap<String, String> query) {
		String extraHql = "";
		//选择飞行员
		if(currentTag.contains("Captain")){
			extraHql = "and sort = 'P' and sysOrganization.id <> '8a8a11ef301b2f3301301bff5e7f00cc' ";
		}
		return this.activePersonCount(sysPageInfo, extraHql, query);
	}
	
}
