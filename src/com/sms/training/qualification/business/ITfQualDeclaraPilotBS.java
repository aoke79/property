package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseLesson;
import com.sms.training.qualification.bean.TfQualBaseLessonCourse;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotCourselist;


public interface ITfQualDeclaraPilotBS extends IService {
	/**
	 * 根据资质类型查找对应的课次
	 * @param typeId 资质类型id
	 * @return 课次列表
	 */
	List<TfQualBaseLesson> findLessons(String typeId);
	/**
	 * 根据课次id查找对应的课程
	 * @param lessonId 课次id
	 * @return 课程列表
	 */
	List<TfQualBaseLessonCourse> findCourses(String lessonId);
    List<TfQualDeclaraPilot> getCountByOrgAndRole(String state,String orgRole,String subGroupId);
    List<TfQualDeclaraPilot> getCountByOrgAndRoleAndPlane(String state,String orgRole,String subGroupId,String planeType);
    List<TfQualDeclaraPilot> getCountByOrgAndRoleAndTypeAndState(String state,String orgRole,String typeSel,String orgSel,String subGroupId);
    
    /**
	 * 查找检查员登录后的代办
	 * @param roleList         角色list
	 * @param subGroupId       资质类型 小类id
	 * @param org              机构
	 * @param user             当前登录者
	 * @param likePilotName    检索的查询条件
	 * @return 
	 */
    public List<TfQualPilotCourselist> getPilotListByC(List<SysRole>  roleList,String subGroupId,SysOrganization org,CmUser user,String likePilotName,String isT);
    List<SysOrganization> getSomeSysOrgs();
    /**
     * 按资质类型搜索
     * @param state
     * @param orgRole
     * @param typeId
     * @return
     */
    List<TfQualDeclaraPilot> getCountByOrgAndRole2(String state,String orgRole,String typeId);
    //教练 检查员 按姓名查询
    List<TfQualDeclaraPilot> getCountByOrgAndRole3(String state,String orgRole, String subGroupId,String pilotName);
    
    public List<TfQualDeclaraPilot> getPilotByIds(String ids) ;

	/**
	 * 根据资质类型及人员id查找对应的课次，适用于模拟机复训
	 * @param typeId 资质类型id
	 * @return 课次列表
	 */
	List<TfQualBaseLesson> findLessons(TfQualBaseType baseType, String pilotId);

	/**
	 * 获取当前登录人的此类别的待办
	 * @param subGroupId
	 * @return
	 */
	List<TfQualDeclaraPilot> getPilotList(String subGroupId,String orgId,List<SysRole>  roleList);
	
	/**
	 * 获取当前登录人的此类别的待办
	 * @param subGroupId
	 * @return
	 */
	List<TfQualDeclaraPilot> getPilotList2(String subGroupId,String orgId,List<SysRole>  roleList);
	
	/**
	 * 获取资质更新阶段的人员待办
	 * @param subGroupId
	 * @return
	 */
	List<TfQualDeclaraPilot> getPilotList3(String subGroupId,String stateSel,String typeSel,String orgSel,List<SysRole>  roleList,String orgId);
	
	/**
	 * 获取审核阶段的人数  
	 * @param subGroupId
	 * @return
	 */
	int getPilotCountOfTraining(String subGroupId, String id, List<SysRole> roleList);
	/**
	 * 获取 待办的总人数（申报阶段）
	 * @param infoIds 申报信息表id拼接串
	 * @deprecated
	 * @return
	 */
	int getPilotCountOfApply(String infoIds);
	/**
	 * 获取 待办的总人数（申报阶段）
	 * @param orgRole "机构id-角色"
	 * @param subGroupId 资质类型子类
	 * @return
	 */
	int getPilotCountOfApply(String orgRole,String subGroupId);
	int getCountOfPilotStay(String orgNameStr, String subGroupId);
	public int getPilotCountOfIssued(String subGroupId, String orgId,List<SysRole> roleList);
		
	/**
	 * 获取  检查员 审核阶段的人数
	 * @param subGroupId
	 * @param user
	 * @return
	 */
	int getPilotCountByC(List<SysRole> roleList, String subGroupId, String userOrgId, CmUser user,String isT);
	
	/**
	 * 判断登录者是教员还是检查员
	 */
	public String checkIsT(List<SysRole> roleList);
	
	/** 计算资质更新阶段的人数
	 * @param subGroupId
	 * @param orgId
	 * @param roleList
	 * @return
	 */
	int getPilotCount4Update(String subGroupId, String orgId, List<SysRole> roleList);
}
