package com.sms.training.qualification.business.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.business.ITfQuaApplyBS;


@Service("tfQuaApplyBS")
public class TfQuaApplyBS extends BaseBS implements ITfQuaApplyBS {
	
	public void recursionOrganization(SysOrganization currentOrganizaion,List<SysOrganization> list) {
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
	
	/**
	 * 教员过滤代办
	 * @param typeInfo
	 * @param user
	 * @param likePilotName
	 * @return    张会粉  暂时先注释掉，伶姐写的代码    
	 */
//	public List<TfQualPilotCourselist> getPiotByUserId(CmUser user,String typeInfo,String likePilotName )
//	{
//		StringBuffer hqlStr = new StringBuffer( " select t from TfQualPilotCourselist t, TfQualDeclaraPilot st ,TfQualBaseType type where " +
//				" t.tfQualDeclaraPilot.detailsid=st.detailsid and st.tfQualBaseType.typeid=type.typeid and type.typedesc like '%"+ConstantList.TYPEINFO.get(typeInfo)+"%' " +
//				" and t.tracherPilotid='"+user.getLoginName().trim()+"' ");
//		hqlStr.append(" and t.trealstartdt between  to_date('"+DateTool.formatDate(DateTool.addDays(ConstantList.countQual))+"', 'YYYY-MM-DD') and ");
//		hqlStr.append(" to_date('"+DateTool.formatDate(DateTool.addDays(1))+"', 'YYYY-MM-DD') and t.tcategory='T' and t.state is null ");
//		if( likePilotName!=null && !"".equals(likePilotName)){
//			hqlStr.append(" and t.tfQualDeclaraPilot.cmPeople.name like '%"+likePilotName+"%'");
//		}
//		return this.findPageByQuery(hqlStr.toString());
//	}
	
	/**
	 * 判断是否完成 此计划的所有填单 ———— 教员、 检查员
	 * @param detailsId
	 * @param teacherType
	 * @return
	 */
	public int getCountByDetailsId(String detailsId,String TeacherType,String tpplanno)
	{
		StringBuffer hqlStr = new StringBuffer(" select count(*) from TfQualPilotCourselist t");
		hqlStr.append(" where t.tpplanno ='"+tpplanno+"'");
		hqlStr.append(" and t.tfQualDeclaraPilot.detailsid='"+detailsId+"' ");
		hqlStr.append(" and t.tcategory='"+TeacherType.trim()+"' ");
		hqlStr.append(" and t.state is null");
//		hqlStr.append(" and t.trealstartdt between  to_date('"+DateTool.formatDate(DateTool.addDays(ConstantList.countQual))+"', 'YYYY-MM-DD') and ");
//		hqlStr.append(" to_date('"+DateTool.formatDate(DateTool.addDays(1))+"', 'YYYY-MM-DD') and t.state is null ");
		return this.getCountByHql(hqlStr.toString());
	}
	public List<SysUserOrgRelation> getOrgRoleByDetailsId(String detailsId)
	{
		StringBuffer hql = new StringBuffer(" select orgRelation from TfQualPilotCourselist t,CmUser cu,SysUserOrgRelation orgRelation");
		hql.append("  where t.tracherPilotid=cu.loginName and cu.userId=orgRelation.cmUser.userId ");
		hql.append(" and t.tfQualDeclaraPilot.detailsid='"+detailsId+"' and t.tcategory='C'");
		return this.findPageByQuery(hql.toString());
	}

	@Override
	public boolean checkIsAll(String detailsId, String teacherType,String tpplanno) {
		if(this.getCountByDetailsId(detailsId,teacherType,tpplanno)>0){
			return false;
		}else{
			return true;
		}
	}
	
}

