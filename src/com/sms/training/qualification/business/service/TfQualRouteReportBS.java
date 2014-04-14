package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualRouteReport;
import com.sms.training.qualification.business.ITfQualRouteReportBS;
@Service("tfQualRouteReportBS")
public class TfQualRouteReportBS extends BaseBS implements ITfQualRouteReportBS{
	
	public TfQualRouteReport getPageByDetailsid( String detailsid){
    String hql=" from TfQualRouteReport tp where tp.tfQualDeclaraPilot.detailsid='"+detailsid+"' ";
    List<TfQualRouteReport> list=this.findPageByQuery(hql);
    if(list!=null&&list.size()!=0){
    	 return list.get(0);    	
    }
   return null;   
	}
}
