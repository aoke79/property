package com.sms.training.qualification.business.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Util;
import com.sms.training.qualification.bean.EnExamQualification;
import com.sms.training.qualification.business.ITfQuaApplyIcaoBS;
@Service("tfQuaApplyIcaoBS")
public class TfQuaApplyIcaoBS extends BaseBS  implements ITfQuaApplyIcaoBS {

	@Override
	public List<EnExamQualification> findFuKaoList(String type,String orgNameString)
	{
		// 四级五级资质到期时间
		String monthString = "-33";
		if ("5".equals(type)) {
			monthString = "-69";
		}
		String examDate=DateTool.getDateTime(DateTool.getNow() ,"yyyy-MM");
		String hqlFkString = "from EnExamQualification t where t.qualification="
			+ type
			+ " and t.qualificationDate is not null and MONTHS_BETWEEN(add_months(to_date('"+examDate+"','yyyy-mm'),"
			+ monthString
			+ "),to_date(to_char(t.qualificationDate,'yyyy-mm'),'yyyy-mm'))>=0  and t.cmPeople.sysOrganization.id in "
			+ orgNameString + "";

		hqlFkString+=" order by t.qualificationDate asc";
		
		List<EnExamQualification>  quaIcaoFuKaoList = this.findPageByQuery(hqlFkString);

		return quaIcaoFuKaoList;
	}
	
	/**
	 * 递归遍历所有机构
	 * 
	 * @param currentOrganizaion
	 * @param list
	 */
	public void recursionOrganization(SysOrganization currentOrganizaion,List<SysOrganization> list)
	{

		String hql="from SysOrganization where parent_Fun.id='"+currentOrganizaion.getId()+"'";
		
		List<SysOrganization> setChild_Fun =this.findPageByQuery(hql);

		// 有子操作时继续向下递归
		if (!setChild_Fun.isEmpty()) {
			for (SysOrganization sysOrganization : setChild_Fun) {
				list.add(sysOrganization);
				recursionOrganization(sysOrganization, list);
			}
		}
	}
	public List<SysOrganization> getOrgList(String orgId)
	{
		String hql="from SysOrganization where parent_Fun.id='"+orgId+"'";
		
		List<SysOrganization> setChild_Fun =this.findPageByQuery(hql);
		
		return setChild_Fun;
	}
	public String getOrgName(SysOrganization org){
		String orgNameStr="";
		List<String> orgStrList=new ArrayList<String>();
		String orgNameStrTemp="";
		if(org.getId().equals(ConstantList.TJFGB)||org.getId().equals(ConstantList.TJFXB)){
			orgNameStrTemp=ConstantList.TJFGS;//英语 lisha//阎君
		}else{
			orgNameStrTemp=org.getId();
		}
		if(orgNameStrTemp!=null && !orgNameStrTemp.equals("")){
			SysOrganization sysOrganization = this.findById(SysOrganization.class,orgNameStrTemp);
			List<SysOrganization> orgListTemp=new ArrayList<SysOrganization>();
			orgListTemp.add(sysOrganization);
			this.recursionOrganization(sysOrganization, orgListTemp);
			if(orgListTemp!=null && orgListTemp.size()!=0){
				for (int i = 0; i < orgListTemp.size(); i++){
					orgStrList.add(orgListTemp.get(i).getId());
				}
			}
			orgNameStr = Util.toStringList(orgStrList, true);
		}
		return orgNameStr;
	}
}
