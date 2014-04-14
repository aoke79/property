package com.sms.training.qualificationassess.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualificationassess.bean.PilotTurnroute;
import com.sms.training.qualificationassess.business.IPilotTurnrouteBS;

@Service("pilotTurnrouteBS")
@Transactional
public class PilotTurnrouteBS extends BaseBS implements
		IPilotTurnrouteBS {

	@Override
	public void addTurnroute(PilotTurnroute pilotTurnroute,
			String cmuser) {
		if (pilotTurnroute != null) {
			pilotTurnroute.setOperate(cmuser);
			pilotTurnroute.setCreateDate(new Date());
			pilotTurnroute.setModifyDate(new Date());
			pilotTurnroute.setStatus("Y");
			this.save(pilotTurnroute);
		}

	}

	@Override
	public void multipleDelet(String ids) {
		if (ids != null && !"".equals(ids)) {
			String tempId[] = ids.split(",");
			for (String id : tempId) {
				PilotTurnroute turnroute = this.findById(
						PilotTurnroute.class, id);
				turnroute.setStatus("N");
				this.update(turnroute, id);
			}
		}

	}

	@Override
	public List<PilotTurnroute> searchTurnrouteList(
			SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo) {
		// 查询集合条数
		StringBuffer counthql = new StringBuffer();
		counthql
				.append("select count(*) from PilotTurnroute where 1=1 and status = 'Y'");
		sysPageInfo.setMaxCount(this.getCountByHQL(counthql.toString(), query));
		// 查询集合
		StringBuffer listhql = new StringBuffer();
		listhql.append(" from PilotTurnroute where 1=1 and status = 'Y'");
		List<PilotTurnroute> list = this.findPageByQuery(sysPageInfo,
				listhql.toString(), query, sysOrderByInfo);

		if (list != null && list.size() != 0) {
			return list;
		}
		return null;
	}

}
