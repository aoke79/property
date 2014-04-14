package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualPilotCheckItem;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.business.ITfQualCheckJobCardBS;

@Service("checkJobCardBS")
public class TfQualCheckJobCardBS extends BaseBS  implements ITfQualCheckJobCardBS {
    
	/**
     * 根据人员编号查找熟练检查工作单对应的信息
     * @param pilotId 人员编码 
     * @return 一条熟练检查工作单信息
     */
    @Override
    public TfQualPilotCheckItem findCheckItemById(String detailsId) {
        
        String hql="from TfQualPilotCheckItem ti where ti.detailsid = '"+detailsId+"'";
        List<TfQualPilotCheckItem> list = this.findPageByQuery(hql);
        if (0 != list.size()) {
            TfQualPilotCheckItem item = list.get(0);
            return item;
        } else {
            return null;
        }
    }
    
    /**
     * 根据人员编号查找编号信息
     * @param pilotId 人员编码 
     * @return 一条熟练检查工作单信息
     */
    @Override
    public TfQualPilotLicence findLicence(String pilotId) {
        String hql="from TfQualPilotLicence tl where tl.pilotid='"+pilotId+"'";
        List<TfQualPilotLicence> list = this.findPageByQuery(hql);
        if (0 != list.size()) {
            TfQualPilotLicence licence = list.get(0);
            return licence;
        } else {
            return null;
        }
    }
}
