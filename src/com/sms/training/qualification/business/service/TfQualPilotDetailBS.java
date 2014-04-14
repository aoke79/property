package com.sms.training.qualification.business.service;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.data.jdbc.JdbcTemplateEx;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.business.ITfQualPilotDetailBS;
import com.sms.training.qualification.dao.hibernate.TfQualPilotDetailDao;
@Service("tfQualPilotDetailBS")
public class TfQualPilotDetailBS extends BaseBS implements ITfQualPilotDetailBS {
	//待申报得飞行员信息BS
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	//待申报人员实体
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	@Override
	public void savePilotDetais(String ids) {
		try {
			//判断传过来的ids是否为空，不为空则往飞安系统插入训练人员数据
			if (ids != null && !ids.equals("")) {
				TfQualPilotDetailDao tfQualPilotDetailDao = new TfQualPilotDetailDao();
				String[] idArr = ids.split(",");
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0, size = idArr.length; i < size; i++) {
					tfQualDeclaraPilot = findById(TfQualDeclaraPilot.class,idArr[i]);
					map.put("ID", UUID.randomUUID().toString());
					map.put("DETAILSID", idArr[i].trim());
					map.put("CMPEOPLEID", tfQualDeclaraPilot.getCmPeople().getId());
					map.put("TRAINTYPE", tfQualDeclaraPilot.getTfQualBaseType().getTfQualBaseTrainingtype().getTtypeseq());
					map.put("QUAL_TYPE", tfQualDeclaraPilot.getTfQualBaseType().getTypeid());
					map.put("DECLAREDINFOID", tfQualDeclaraPilot.getTfQualDeclarInfo().getDeclaredinfoid());
					map.put("STATE", "D");
					map.put("PILOTID", tfQualDeclaraPilot.getCmPeople().getHrid());
					tfQualPilotDetailDao.insertPilotDetail(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	@JSON(serialize = false)
	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	 
}

