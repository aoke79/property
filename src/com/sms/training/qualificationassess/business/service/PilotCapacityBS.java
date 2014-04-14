package com.sms.training.qualificationassess.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualificationassess.bean.PilotCapacity;
import com.sms.training.qualificationassess.business.IPilotCapacityBS;
@Service("pilotCapacityBS")
@Transactional
public class PilotCapacityBS extends BaseBS implements IPilotCapacityBS {

	@Override
	public void addPilotCapacity(PilotCapacity pilotCapacity, String cmuser) {
		if (pilotCapacity != null) {
			pilotCapacity.setOperate(cmuser);
			pilotCapacity.setCreateDate(new Date());
			pilotCapacity.setModifyDate(new Date());
            pilotCapacity.setStatus("Y");
			this.save(pilotCapacity);
		}

	}

	@Override
	public void multipleDelet(String ids) {
		if(ids != null && !"".equals(ids)){
			String tempId[] = ids.split(",");
			for(String id : tempId){
				PilotCapacity pilot = this.findById(PilotCapacity.class, id);
				pilot.setStatus("N");
				this.update(pilot, id);
			}
		}

	}

	@Override
	public List<PilotCapacity> searchPilotCapacityList(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		// 查询集合条数
		StringBuffer counthql = new StringBuffer();
		counthql
				.append("select count(*) from PilotCapacity where 1=1 and status = 'Y'");
		sysPageInfo.setMaxCount(this.getCountByHQL(counthql.toString(), query));
		// 查询集合
		StringBuffer listhql = new StringBuffer();
		listhql.append(" from PilotCapacity where 1=1 and status = 'Y'");
		List<PilotCapacity> list = this.findPageByQuery(sysPageInfo, listhql
				.toString(), query, sysOrderByInfo);

		if (list != null && list.size() != 0) {
			return list;
		}
		return null;
	}

}
