package com.sms.data.hr.peopledept.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.data.hr.peopledept.bean.HrSmsinfo;
import com.sms.data.hr.peopledept.business.IHrSmsinfoBS;
@Service("hrSmsinfoBS")
@Transactional
public class HrSmsinfoBS extends BaseBS implements IHrSmsinfoBS {

	@Override
	public void addHrSms(HrSmsinfo hrSmsinfo, String cmuser) {
		if (hrSmsinfo != null) {
			hrSmsinfo.setOperate(cmuser);
			hrSmsinfo.setCreateDate(new Date());
			hrSmsinfo.setModifyDate(new Date());
            hrSmsinfo.setStatus("Y");
			this.save(hrSmsinfo);
		}

	}

	@Override
	public void multipleDelet(String ids) {
		if(ids != null && !"".equals(ids)){
			String tempId[] = ids.split(",");
			for(String id : tempId){
				HrSmsinfo hrsms= this.findById(HrSmsinfo.class, id);
				hrsms.setStatus("N");
				this.update(hrsms, id);
			}
		}
	}

	@Override
	public List<HrSmsinfo> searchHrSmsList(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
	
		// 查询集合条数
		StringBuffer counthql = new StringBuffer();
		counthql
				.append("select count(*) from HrSmsinfo where 1=1 and status = 'Y'");
		sysPageInfo.setMaxCount(this.getCountByHQL(counthql.toString(), query));
		// 查询集合
		StringBuffer listhql = new StringBuffer();
		listhql.append(" from HrSmsinfo where 1=1 and status = 'Y'");
		List<HrSmsinfo> list = this.findPageByQuery(sysPageInfo, listhql
				.toString(), query, sysOrderByInfo);

		if (list != null && list.size() != 0) {
			return list;
		}
		return null;
	}

}
