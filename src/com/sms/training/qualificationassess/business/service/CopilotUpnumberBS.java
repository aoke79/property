package com.sms.training.qualificationassess.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualificationassess.bean.CopilotUpnumber;
import com.sms.training.qualificationassess.business.ICopilotUpnumberBS;
@Service("copilotUpnumberBS")
@Transactional
public class CopilotUpnumberBS extends BaseBS implements ICopilotUpnumberBS {

	@Override
	public void addCopilotUpnumber(CopilotUpnumber copilotUpnumber,
			String cmuser) {
		if (copilotUpnumber != null) {
			copilotUpnumber.setOperate(cmuser);
			copilotUpnumber.setCreateDate(new Date());
			copilotUpnumber.setModifyDate(new Date());
            copilotUpnumber.setStatus("Y");
			this.save(copilotUpnumber);
		}

	}

	@Override
	public void multipleDelet(String ids) {
		if(ids != null && !"".equals(ids)){
			String tempId[] = ids.split(",");
			for(String id : tempId){
				CopilotUpnumber copilot = this.findById(CopilotUpnumber.class, id);
				copilot.setStatus("N");
				this.update(copilot, id);
			}
		}

	}

	@Override
	public List<CopilotUpnumber> searchCopilotUpnumberList(
			SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo) {
		// 查询集合条数
		StringBuffer counthql = new StringBuffer();
		counthql
				.append("select count(*) from CopilotUpnumber where 1=1 and status = 'Y'");
		sysPageInfo.setMaxCount(this.getCountByHQL(counthql.toString(), query));
		// 查询集合
		StringBuffer listhql = new StringBuffer();
		listhql.append(" from CopilotUpnumber where 1=1 and status = 'Y'");
		List<CopilotUpnumber> list = this.findPageByQuery(sysPageInfo, listhql
				.toString(), query, sysOrderByInfo);

		if (list != null && list.size() != 0) {
			return list;
		}
		return null;
	}

}
