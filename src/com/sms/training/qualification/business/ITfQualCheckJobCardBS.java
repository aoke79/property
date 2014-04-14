package com.sms.training.qualification.business;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotCheckItem;
import com.sms.training.qualification.bean.TfQualPilotLicence;

public interface ITfQualCheckJobCardBS extends IService{
    
    /**
     * 根据人员编号查找熟练检查工作单对应的信息
     * @param pilotId 人员编码 
     * @return 一条熟练检查工作单信息
     */
    public TfQualPilotCheckItem findCheckItemById(String pilotId);
    
    /**
     * 根据人员编号查找编号信息
     * @param pilotId 人员编码 
     * @return 一条熟练检查工作单信息
     */
    public TfQualPilotLicence findLicence(String pilotId);
    
}
