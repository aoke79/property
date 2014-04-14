package com.sms.training.qualificationassess.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualificationassess.bean.RouteTurnnumber;
import com.sms.training.qualificationassess.business.IRouteTurnnumberBS;

@Service("routeTurnnumberBS")
@Transactional
public class RouteTurnnumberBS extends BaseBS implements IRouteTurnnumberBS {

	@Override
	public void addRouteTurnnumber(RouteTurnnumber routeTurnnumber,
			String cmuser) {
		if (routeTurnnumber != null) {
			routeTurnnumber.setOperate(cmuser);
			routeTurnnumber.setCreateDate(new Date());
			routeTurnnumber.setModifyDate(new Date());
            routeTurnnumber.setStatus("Y");
			this.save(routeTurnnumber);
		}

	}

	@Override
	public void multipleDelet(String ids) {
		if(ids != null && !"".equals(ids)){
			String tempId[] = ids.split(",");
			for(String id : tempId){
				RouteTurnnumber turn = this.findById(RouteTurnnumber.class, id);
				turn.setStatus("N");
				this.update(turn, id);
			}
		}

	}

	@Override
	public List<RouteTurnnumber> searchRouteTurnnumberList(
			SysPageInfo sysPageInfo, HashMap<String, String> query,
			SysOrderByInfo sysOrderByInfo) {
		// 查询集合条数
		StringBuffer counthql = new StringBuffer();
		counthql
				.append("select count(*) from RouteTurnnumber where 1=1 and status = 'Y'");
		sysPageInfo.setMaxCount(this.getCountByHQL(counthql.toString(), query));
		// 查询集合
		StringBuffer listhql = new StringBuffer();
		listhql.append(" from RouteTurnnumber where 1=1 and status = 'Y'");
		List<RouteTurnnumber> list = this.findPageByQuery(sysPageInfo, listhql
				.toString(), query, sysOrderByInfo);

		if (list != null && list.size() != 0) {
			return list;
		}
		return null;
	}

}
