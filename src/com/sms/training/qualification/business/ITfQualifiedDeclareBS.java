package com.sms.training.qualification.business;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;

public interface ITfQualifiedDeclareBS extends IService{
	
	public List<TfQualDeclaraPilot> getTfQualDeclarPilotByPeopleId(String peopleId);
	/**
	 * 根据peopleId获得TfQualDeclaraPilot对象
	 * @param peopleId
	 * @return
	 */
	public void doSaveGeneralPilot(String typeid,List<Object> list,Date date);
	
	public List<TfQualDeclaraPilot> getTfQualDeclarPilotByPeopleId(String InfoId,String peopleId);
	/**
	 * 根据id获得info
	 * @param id
	 * @return
	 */
	public List<TfQualDeclarInfo> getDeclarInfoById(String id) ;
	/**
	 * 根据申报信息获取申报人员信息
	 * @param InfoId
	 * @return
	 */
	public List<TfQualDeclaraPilot> getTfQualDeclarPilotByInfoId(String InfoId);
	/**
	 * QLL 根据申请人和类别 获取此次申报详细信息
	 * @param userId
	 * @param typeId
	 * @return
	 */
	public List<TfQualDeclarInfo> getTfQualDeclarInfoByUserId(String userId,String typeId);
	
	public List getPilotList(String orgid, String pilottechgrade);
	/**
	 * 计算满足条件得人员
	 * @param typeids
	 * @param orgid
	 */
	public void doComputingStaff(String typeids,String orgid);
	/**
	 * 查询满足条件得飞行员
	 * @param atrid
	 * @param grade
	 * @param orgid
	 * @param tfQualBaseAccessconditions
	 * @return
	 */
	public List<String> getPilotListByOrgid(String atrid,String grade,String orgid,TfQualBaseAccessconditions tfQualBaseAccessconditions);
	/**
	 * 查询待上报人员
	 * @deprecated 由于此方法不能依据单位进行过滤，故已过时，请改用其他相关方法
	 * @param typeid 资质类型id
	 * @see findPilotStaysByTypeAndOrgs(String typeId, String orgIds)
	 * @return
	 */
	public List<TfQualDeclaraPilotStay> findAllTfQualDeclaraPilotStay(String typeid);
	/**
	 * 创建申报信息
	 * @param typeid资质类型
	 * @param ids人员ID
	 */
	public String createDeclarInfo(String typeid,String ids,CmUser cmUser);		
	/**
	 * 获得每个资质类型分组的人数
	 * @return
	 */
	public HashMap<String, String> getTypeCount(String orgid);
	/**
	 * 根据资质类型、申报人员单位和人员计算日期 查询待申报人员列表
	 * @param typeids
	 * @param dateCompute
	 * @param orgIds
	 * @return
	 */
	public List<TfQualDeclaraPilotStay> findPilotStayList(String typeids, String orgIds, String dateCompute);
	
	/**
	 * 根据资质类型、申报专员所在单位和人员计算日期查询申报人员的数量
	 * @param typeids
	 * @param orgIds
	 * @param dateCompute
	 * @return
	 */
	public int findPilotStayCount(String typeids, String orgIds, String dateCompute);
	
	/**
	 * 按照资质类型和申报专员所在单位进行查询待申报人员
	 * @param typeId 资质类型id
	 * @param orgIds 单位id串，格式为: ('ss','df','sdd')
	 * @return
	 */
	public List<TfQualDeclaraPilotStay> findPilotStaysByTypeAndOrgs(String typeId, String orgIds);
	
	/**
	 * 按照资质类型和申报专员所在单位进行查询申报人员的数量
	 * @param typeId 资质类型id
	 * @param orgIds 单位id串，格式为: ('ss','df','sdd')
	 * @return
	 */
	public int findPilotStaysCountByTypeAndOrgs(String typeId, String orgIds);
	
	/**
	 * 根据申报信息id获取意见记录，适用于 开启申报流程的人
	 * @param declaredinfoid
	 * @return
	 */
	public List<TfQualDeclarApprovalinfo> findApprovalinfoByDeclaredinfoid(String declaredinfoid);
	
	
}
