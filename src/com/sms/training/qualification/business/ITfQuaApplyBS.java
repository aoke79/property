package com.sms.training.qualification.business;

import com.sinoframe.bean.SysUserOrgRelation;
import java.util.List;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.IService;

public interface ITfQuaApplyBS extends IService{
	/**
	 * 教员获得待办
	 * @param user
	 * @param typeInfo
	 * @return
	 */
//	public List<TfQualPilotCourselist> getPiotByUserId(CmUser user,String typeInfo,String likePilotName );
	/**
	 * 递归遍历所有机构
	 * @param currentOrganizaion
	 * @param list
	 */
	void recursionOrganization(SysOrganization currentOrganizaion,List<SysOrganization> list);
	/**
	 * 判断是否所有人员都已填单
	 * @param detailsId
	 * @param TeacherType
	 * @return
	 */
	public int getCountByDetailsId(String detailsId,String TeacherType,String tpplanno);
	/**
	 * 
	 * @param detailsId
	 * @return
	 */
	public List<SysUserOrgRelation> getOrgRoleByDetailsId(String detailsId);
	/**
	 * 判断是否完成此计划的所有填单 教员、检查员
	 * @param detailsId
	 * @param teacherType
	 * @return
	 */
	public boolean checkIsAll(String detailsId,String teacherType,String tpplanno);
	
	
		
}
