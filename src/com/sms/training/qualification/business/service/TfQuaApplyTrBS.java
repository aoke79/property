package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQuaApplyTrBS;
@Service("tfQuaApplyTrBS")
public class TfQuaApplyTrBS extends BaseBS implements ITfQuaApplyTrBS{
	/**
	 * 根据查询条件查询，获取符合条件的可申报人员信息
	 * @param pageInfo 分页信息
	 * @param tfQualBaseType 资质类型
	 * @param orgNameStr 机构id串
	 * @param pilotName 查询参数，姓名
	 * @param subGroupId 资质类型分组 小类id
	 * @return
	 */
	public List<Object> getAvailablePilotStayTmp(SysPageInfo pageInfo,TfQualBaseType tfQualBaseType, String orgNameStr, String pilotName, String subGroupId) {
		pageInfo.setMaxCount(this.getCountByHQL(genHqlStr(tfQualBaseType.getTypeid(), orgNameStr, pilotName, subGroupId," count(p.id) ").toString()));
		List<Object> stayList=this.getPilotStayByTypeOrg(tfQualBaseType.getTypeid(),orgNameStr,pilotName,subGroupId,pageInfo);
		return stayList;
	}
	
	private List<Object> getPilotStayByTypeOrg(String typeId,String orgNameStr,String pilotName,String subGroupId,SysPageInfo pageInfo){
		String target="p.id,p.name,p.idcard,r.baseAirplantype.atrdesc,r.ptgradeid,r.ptrfltexptimetotaltotal";
		StringBuffer sql=genHqlStr(typeId, orgNameStr, pilotName, subGroupId,target);
		sql.append(" order by p.name");
		return  this.findPageByQuery(pageInfo, sql.toString());
	}
	
	//hql 语句拼接
	private StringBuffer genHqlStr(String typeId,String orgNameStr,String pilotName,String subGroupId,String target){
		StringBuffer sql=new StringBuffer(" select ").append(target).append(" from CmPeople p ,TfQualPilotTechrecord r ,TfQualPilotLicence l ");
		sql.append("  where p.id=r.cmPeople.id and l.plcseq=r.tfQualPilotLicence.plcseq and l.plcstus='0' and r.ptrcurrent='0'" );
		if(pilotName!=null && !pilotName.equals("")){
			sql.append("and p.name like '%").append(pilotName).append("%' " );
		}
		if(subGroupId.equals("QUAL_TR_JZZJX")||subGroupId.equals("QUAL_TR_JZCY")){//机长差异、机长转机型
			sql.append("and r.ptgradeid in ('M','J','A1','A2','TA','TB','TC') ");
		}else if(subGroupId.equals("QUAL_TR_FJSZJX")||subGroupId.equals("QUAL_TR_FJSCY")){//副驾驶转机型、副驾驶差异
			sql.append("and r.ptgradeid in ('F1','F2','F3','F4','F5') ");
		}else if(subGroupId.equals("QUAL_TR_JYCIQ")){//教员CIQ
			sql.append("and r.ptgradeid in ('TA','TB','TC') ");
		}
		if(orgNameStr!=null && !orgNameStr.equals("")){
			sql.append(" and p.sysOrganization.id in "+orgNameStr);
		}
		sql.append(" and p.id not in ( select st.cmPeople.id from TfQualDeclaraPilotStay st where st.tfQualBaseType.typeid='").append(typeId).append("' and ( st.status='W' or st.status='N' or st.status is null) )");
		return  sql;
	}
	
	/**
	 * 根据查询条件查询，获取符合条件的 已经选择的 待申报人员信息,(机型取主飞机型)
	 * @param sysPageInfo 分页信息
	 * @param typeId 资质类型id
	 * @param orgNameStr 机构id串
	 * @return
	 */
	@Override
	public List<Object> getSelectedPilotStayTmp(SysPageInfo pageInfo, String typeId,String orgNameStr, String pilotName) {
		String hql=" from TfQualPilotTechrecord r,TfQualDeclaraPilotStay st ,CmPeople p, TfQualPilotLicence lc " +
				   " where r.cmPeople.id=p.id and p.id=st.cmPeople.id and lc.plcseq=r.tfQualPilotLicence.plcseq and lc.atrid=r.baseAirplantype.id " +
				   " and r.ptrcurrent='0' and st.tfQualBaseType.typeid='"+typeId+"' and st.status is null and lc.firstflag='1'";
		if(pilotName!=null && !pilotName.equals("")){
			hql+="and p.name like '%"+pilotName+"%' ";
		}
		if(orgNameStr!=null && !orgNameStr.equals("")){
			hql+=" and p.sysOrganization.id in "+orgNameStr;
		}
		List<Object> tmpList=this.findPageByQuery("select r.baseAirplantype.id,p.idcard "+hql+" group by r.baseAirplantype.id,p.idcard");
		pageInfo.setMaxCount(tmpList.size());
		hql=" select distinct p.id,p.name,p.idcard,lc.atrid,r.ptgradeid,r.ptrfltexptimetotaltotal,st.stayid "+ hql +" order by p.name";
		return this.findPageByQuery(pageInfo, hql);
	}
}
