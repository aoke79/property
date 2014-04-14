package com.sms.training.qualification.business.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.*;
import com.sms.training.qualification.business.ITfQuaApplyInspectorBS;

@Service("tfQuaApplyInspectorBS")
public class TfQuaApplyInspectorBS extends BaseBS implements ITfQuaApplyInspectorBS {
	
	@Override
	public List<Object> getAvailablePilotStayTmp(SysPageInfo sysPageInfo, TfQualBaseType type, String orgNameStr, String pilotName) {
		String sql=genHqlStr(" count(*) ", pilotName, orgNameStr, type).toString();
		sysPageInfo.setMaxCount(this.getCountByHQL(sql));
		
		String result=" p.id,p.name,p.idcard,r.baseAirplantype.atrdesc,r.ptgradeid,r.ptrfltexptimetotaltotal ";
		sql=genHqlStr(result, pilotName, orgNameStr, type).append(" order by p.name ").toString();
		return  this.findPageByQuery(sysPageInfo, sql);
	}
	
	private StringBuffer genHqlStr(String result, String pilotName, String orgNameStr, TfQualBaseType type){
		StringBuffer sql=new StringBuffer("   select ").append(result);
		sql.append(" from CmPeople p ,TfQualPilotTechrecord r ,BaseAirplanType a ,TfQualPilotLicence l ");
		sql.append(" where p.id=r.cmPeople.id and  a.id=l.atrid and l.atrid=r.baseAirplantype.id and l.plcseq=r.tfQualPilotLicence.plcseq and l.plcstus='0' and r.ptrcurrent='0' " );
		sql.append(" and r.ptgradeid in ('TA','TB','TC') ");
		String atrDesc=type.getTargetatrid().getAtrdesc();
		if(atrDesc!=null && !atrDesc.equals("")){
			sql.append(" and a.atrdesc='"+atrDesc+"'");
		}
		if(pilotName!=null && !pilotName.equals("")){
			sql.append(" and p.name like '%").append(pilotName).append("%' " );
		}
		if(orgNameStr!=null && !orgNameStr.equals(""))
		{
			sql.append(" and p.sysOrganization.id in "+orgNameStr);
		}
		sql.append(" and p.id not in ( select st.cmPeople.id from TfQualDeclaraPilotStay st where st.tfQualBaseType.typeid='").append(type.getTypeid()).append("' and ( st.status='W' or st.status='N' or st.status is null) )");
		return sql;
	}
	
	/**
	 * 根据查询条件查询，获取符合条件的 已经选择的 待申报人员信息
	 * @param sysPageInfo 分页信息
	 * @param type 资质类型
	 * @param orgNameStr 机构id串
	 * @return
	 */
	@Override
	public List<Object> getSelectedPilotStayTmp(SysPageInfo pageInfo, TfQualBaseType type,String orgNameStr, String pilotName) {
		String hql=" from TfQualPilotTechrecord r,TfQualDeclaraPilotStay st ,CmPeople p " +
				" where r.cmPeople.id=p.id and p.id=st.cmPeople.id and r.ptrcurrent='0' and st.tfQualBaseType.typeid='"+type.getTypeid()+"' and st.status is null";
		hql+=" and  r.baseAirplantype.id='"+type.getTargetatrid().getId()+"' ";
		if(pilotName!=null && !pilotName.equals("")){
			hql+=" and p.name like '%" + pilotName + "%' ";
		}
		if(orgNameStr!=null && !orgNameStr.equals("")){
			hql+=" and p.sysOrganization.id in "+orgNameStr;
		}
		List<Object> tmpList=this.findPageByQuery("select r.baseAirplantype.id,p.idcard "+hql+" group by r.baseAirplantype.id,p.idcard");
		pageInfo.setMaxCount(tmpList.size());
		hql=" select distinct p.id,p.name,p.idcard,r.baseAirplantype.atrdesc,r.ptgradeid,r.ptrfltexptimetotaltotal,st.stayid "+ hql +" order by p.name";
		return this.findPageByQuery(pageInfo, hql);
	}
	
	@Override
	public <T> List<T> getListByProperty(Class<T> cs,String property,Object value){
		StringBuffer hql =new StringBuffer();
		hql.append("from ").append(cs.getSimpleName()).append(" t where 1=1 ");
		if(property!=null && !property.equals("")){
			hql.append(" and t.").append(property).append("='").append(value.toString()).append("'");
		}
		return this.findPageByQuery(hql.toString());
	}

	@Override
	public List<TfQualBaseType> findTypeList(String subGroupId) {
		String hql="from TfQualBaseType t where t.tfQualBaseTgroup.typegroupid='"+subGroupId+"'";
		return this.findPageByQuery(hql);
	}

	
}
