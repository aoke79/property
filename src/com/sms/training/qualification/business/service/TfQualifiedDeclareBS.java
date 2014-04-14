package com.sms.training.qualification.business.service; 
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.DateTool;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.bean.TfQualPilotTechgrade;
import com.sms.training.qualification.business.ITfQualifiedDeclareBS;
import com.sms.training.qualification.dao.ITfQualifiedDeclareDao;
@Service("tfQualifiedDeclareBS")
public class TfQualifiedDeclareBS extends BaseBS implements ITfQualifiedDeclareBS{
	private ITfQualifiedDeclareDao tfQualifiedDeclareDao;
	private List<TfQualBaseAccessconditions> tfQualBaseAccessconditionsList;
	private TfQualBaseType tfQualBaseType = new TfQualBaseType();
	private TfQualDeclaraPilotStay tfQualDeclaraPilotStay ;
	private CmPeople cmPeople = new CmPeople();
	private TfQualDeclarInfo tfQualDeclarInfo = new TfQualDeclarInfo();
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	
	@Override
	public List getPilotList(String orgid, String pilottechgrade) {
		return tfQualifiedDeclareDao.getPilotList(orgid, pilottechgrade);
	}
/*	@Override
	public void doComputingStaff(String typeids,String orgid) {
		String [] types = StringUtils.split(typeids==null?"":typeids, ',');
		//遍历所选择要计算的资质类型
		for (int i = 0, len= types.length; i < len; i++) {
			if(this.isExistByTypeid(types[i])){
				this.deletePilotByTypeid(types[i]);
			}
			tfQualBaseType = this.findById(TfQualBaseType.class, types[i]);
			BaseAirPlanType airPlanType = tfQualBaseType.getOriginalatrid();
			TfQualPilotTechgrade techGrade = tfQualBaseType.getOriginalgrade();
			//获得资质类型对应的计算方法
			tfQualBaseAccessconditionsList = this.getTfQualBaseAccessconditionsListBytypeid( types[i]);
			//遍历资质类型对应的计算方法
			for (int j = 0,size = tfQualBaseAccessconditionsList.size(); j < size; j++) {
				//根据条件得到人员列表
				if(airPlanType!=null && techGrade!=null){
					List<String> list =this.getPilotListByOrgid(airPlanType.getId(), techGrade.getPtGradeId(), orgid, tfQualBaseAccessconditionsList.get(j));
					this.doSavePilot(types[i], list);
				}
			}
		}
	}*/
	@Override
	public void doComputingStaff(String typeId,String orgid) {
		if(typeId==null||typeId.trim().equals("")){
			return ;
		}
		if(this.isExistByTypeid(typeId, orgid)){
			this.deletePilotByTypeid(typeId, orgid);
		}
		//获得资质类型
		tfQualBaseType = this.findById(TfQualBaseType.class, typeId);
		if(tfQualBaseType == null){
			return ;
		}
		BaseAirPlanType airPlanType = tfQualBaseType.getOriginalatrid();
		TfQualPilotTechgrade techGrade = tfQualBaseType.getOriginalgrade();
		if(airPlanType!=null && techGrade!=null){
			tfQualBaseAccessconditionsList = this.getTfQualBaseAccessconditionsListBytypeid(typeId);
			for(TfQualBaseAccessconditions condition : tfQualBaseAccessconditionsList){
				//根据条件得到人员列表
				List<String> list =this.tfQualifiedDeclareDao.getPilotListByOrgid(airPlanType.getId(), techGrade.getPtGradeId(), orgid, condition);
				this.doSavePilot(tfQualBaseType, list);
			}
		}
	}
	
	public List<TfQualDeclaraPilot> getTfQualDeclarPilotByInfoId(String InfoId)
	{
		String hql ="from TfQualDeclaraPilot t where t.tfQualDeclarInfo.declaredinfoid='"+InfoId+"'  and (t.state  is null or t.state!='REJECT')" ;
		return this.findPageByQuery(hql);
	}
	public List<TfQualDeclaraPilot> getTfQualDeclarPilotByPeopleId(String InfoId,String peopleId)
	{
		//String hql ="from TfQualDeclaraPilot t where t.tfQualDeclarInfo.declaredinfoid='"+InfoId+"' and t.cmPeople.id='"+peopleId+"'"  ;
		StringBuffer bf=new StringBuffer(" from TfQualDeclaraPilot t where ");
		if(!InfoId.equals("") && !peopleId.equals(""))
		{
			bf.append("t.tfQualDeclarInfo.declaredinfoid='"+InfoId+"' and t.cmPeople.id='"+peopleId+"'");
		}else
		if(!InfoId.equals("") && peopleId.equals(""))
		{
			bf.append(" t.tfQualDeclarInfo.declaredinfoid='"+InfoId+"'");
		}else
		{
			bf.append(" t.cmPeople.id='"+peopleId+"'");
		}
		return this.findPageByQuery(bf.toString());
	}
	/**
	 * 通过类型ID查询准入条件
	 * @param typeid
	 * @return
	 */
	@Override
	public List<TfQualDeclarInfo> getTfQualDeclarInfoByUserId(String userId,
			String typeId) {
		String hql =" from TfQualDeclarInfo t where t.creator='"+userId+"'"+" and t.tfQualBaseType.typeid= '"+typeId+"'" ;
		return this.findPageByQuery(hql);
	}
	/**
	 * 通过类型ID查询准入条件
	 * @param typeid
	 * @return
	 */
	private List<TfQualBaseAccessconditions> getTfQualBaseAccessconditionsListBytypeid(String typeid){
		String hql =" from TfQualBaseAccessconditions t where t.tfQualBaseType.typeid='"+typeid+"'"  ;
		return this.findPageByQuery(hql);
	} 
	/**
	 * 保存待资质申报人员
	 * @param typeid
	 * @param list
	 */
	private void doSavePilot(TfQualBaseType tfQualBaseType, List<String> list){
		for (int i = 0,s = list.size(); i < s; i++) {
			tfQualDeclaraPilotStay= new TfQualDeclaraPilotStay();
			cmPeople.setId(list.get(i).trim());
			tfQualDeclaraPilotStay.setCmPeople(cmPeople);
			tfQualDeclaraPilotStay.setTfQualBaseType(tfQualBaseType);
			this.saveOrUpdate(tfQualDeclaraPilotStay);
		}
	}
	public void doSaveGeneralPilot(String typeid,List<Object> list,Date date){
		String isMust=null;
		Object[] objArr=null;
		for (int i = 0,s = list.size(); i < s; i++) {
			tfQualDeclaraPilotStay= new TfQualDeclaraPilotStay();
			objArr=(Object[])list.get(i);
			if(objArr!=null && objArr.length>0){
				cmPeople.setId( objArr[0].toString().trim() );
				tfQualDeclaraPilotStay.setCmPeople(cmPeople);
				tfQualDeclaraPilotStay.setTfQualBaseType(new TfQualBaseType(typeid));
				tfQualDeclaraPilotStay.setYear(date);
				isMust= objArr[1]==null?"":objArr[1].toString();
				tfQualDeclaraPilotStay.setTrainType(isMust);//应训 or 必训
				this.save(tfQualDeclaraPilotStay);
			}
		}
	}
	
	@Override
	public List<String> getPilotListByOrgid(String atrid,String grade,String orgid,TfQualBaseAccessconditions tfQualBaseAccessconditions){
		return this.tfQualifiedDeclareDao.getPilotListByOrgid(atrid, grade, orgid, tfQualBaseAccessconditions);
	}
	/**
	 * 判断该资质类型是否有待上报人员
	 * @param typeid
	 * @param orgIds
	 * @return
	 */
	private boolean isExistByTypeid(String typeid, String orgIds){
		String hql ="select count(*) from TfQualDeclaraPilotStay t " +
					" where t.tfQualBaseType.typeid='"+typeid+"'" +
					" and t.status is null ";
		if(!orgIds.equals("1")){
			hql += " and t.cmPeople.sysOrganization.id in " + orgIds;
		}
		return this.getCountByHql(hql) > 0;
	}
	/**
	 * 通过类别id删除待申报人员
	 * @param typeid
	 * @param orgIds
	 */
	private void deletePilotByTypeid(String typeid, String orgIds){		
		StringBuffer sql=new StringBuffer(300);
		sql.append("delete from TF_QUAL_DECLARA_PILOT_STAY st ").
				append(" where st.TYPEID = '").append(typeid).append("' ").
				append("  and st.STATUS is null ") ;
		if(!orgIds.equals("1")){
			sql.append("  and exists (select p.id ").
					append(" from CM_PEOPLE p ").
					append("  where p.id = st.pilotid ").
					append("  and p.DEPTID in ").append(orgIds).append(")");
		}
		this.tfQualifiedDeclareDao.executeUpdateSQL(sql.toString());
	}

	/**
	 * @deprecated
	 */
	@Override
	public List<TfQualDeclaraPilotStay> findAllTfQualDeclaraPilotStay( String typeid) {
		String hql ="from TfQualDeclaraPilotStay  where status is null and tfQualBaseType.typeid='"+typeid+"' ";
		return this.findPageByQuery(hql);
	}
	
//	@Override
//	public List<TfQualDeclarInfo> findAllTfQualDeclaraInfo(SysPageInfo sysPageInfo, String typeid) {
//		String hql ="from TfQualDeclaraPilotStay  where status is null and tfQualBaseType.typeid='"+typeid+"' ";
//		String countHql ="select count(*) from TfQualDeclaraPilotStay where status is null and tfQualBaseType.typeid='"+typeid+"' ";
//		sysPageInfo.setMaxCount(getCountByHql(countHql));
//		return this.findPageByQuery(sysPageInfo, hql);
//	}
	@Override
	public String createDeclarInfo(String typeid, String ids,CmUser cmUser) {
		return this.saveDeclarInfo(typeid,ids,cmUser);
	}
	//根据id获得info
	public List<TfQualDeclarInfo> getDeclarInfoById(String id) {
		String hql =" from TfQualDeclarInfo t where t.declaredinfoid= '"+id+"')";
		return this.findPageByQuery(hql);
	}
	/**
	 * 保存申报信息
	 * @param typeid
	 */
	private String saveDeclarInfo(String typeid ,String ids ,CmUser cmUser){
		this.tfQualBaseType = new TfQualBaseType(typeid);
		this.tfQualDeclarInfo.setTfQualBaseType(tfQualBaseType);
		this.tfQualDeclarInfo.setCreatedt(DateTool.getNowDate());
		this.tfQualDeclarInfo.setCreator(cmUser.getUserId());
		this.tfQualDeclarInfo.setStatus("W");//Wait 等待申报
		this.tfQualDeclarInfo.setDeclaredinfodesc(this.getDeclaredinfodescFotTypeid(typeid,cmUser));
		this.save(tfQualDeclarInfo);
		String [] pilotids = StringUtils.split(ids,",");
		//往申报人员明细表添加数据e 
		for (int i = 0,j = pilotids.length; i < j; i++) {
			this.tfQualDeclaraPilot = new TfQualDeclaraPilot();
			this.cmPeople.setId(pilotids[i].trim());
			this.tfQualDeclaraPilot.setCmPeople(cmPeople);
			this.tfQualDeclaraPilot.setTfQualDeclarInfo(tfQualDeclarInfo);
			this.tfQualDeclaraPilot.setTfQualBaseType(tfQualBaseType);
			this.save(tfQualDeclaraPilot);
		}
		this.updatePilotStay(typeid,ids);
		return tfQualDeclarInfo.getDeclaredinfoid();
	}
	private void updatePilotStay(String typeid, String ids){
		String pilotids = ids.replace(",", "','");
		String hql ="update TfQualDeclaraPilotStay set status='W' where status is null and tfQualBaseType.typeid='"+typeid+"' and cmPeople.id in ('"+pilotids+"')";
		this.executeUpdate(hql);
	}
	
	/**
	 * 自动生成申报信息名称
	 * @param typeid
	 * @return   FK_DECLARE_TYPEID  TYPEID  TF_QUAL_BASE_TYPE  TYPEID
	 */
	private String getDeclaredinfodescFotTypeid(String typeid,CmUser cmUser){
		StringBuffer sb = new StringBuffer(100);
		sb.append(DateTool.formatDate(DateTool.getNowDate(), "yyyy年MM月dd日"));
		this.tfQualBaseType= this.findById(TfQualBaseType.class, typeid);
		if(this.getOrganizationByUser(cmUser).get(0).getName().contains("天津"))
		{
			sb.append(tfQualBaseType.getTypedesc()).append("(天津分公司)");
		}else
		{
			sb.append(tfQualBaseType.getTypedesc()).append("("+this.getOrganizationByUser(cmUser).get(0).getName()+")");
		}
		return sb.toString();
	}
	
	@Override
	public HashMap<String, String> getTypeCount(String orgid) {
		List<Object> list = this.tfQualifiedDeclareDao.getTypeCount(orgid);
		HashMap<String, String> tempCount  = new HashMap<String, String>();
		for (int i = 0,size = list.size(); i < size; i++) {
			Object[] object = (Object[])list.get(i);
			String key = (String)object[0];
			String value = (String)object[1];
			tempCount.put(key, value);
		}
		return tempCount;
	}
	public ITfQualifiedDeclareDao getTfQualifiedDeclareDao() {
		return tfQualifiedDeclareDao;
	}
	@Resource 
	public void setTfQualifiedDeclareDao(ITfQualifiedDeclareDao tfQualifiedDeclareDao) {
		this.tfQualifiedDeclareDao = tfQualifiedDeclareDao;
	}
	@Override
	public List<TfQualDeclaraPilot> getTfQualDeclarPilotByPeopleId(String peopleId) {
		String hql =" from TfQualDeclaraPilot t where t.cmPeople.id= '"+peopleId+"'";
		return this.findPageByQuery(hql);
	}
	/**
	 * 根据资质类型 和 人员计算日期 查询待申报人员列表
	 * @param typeids
	 * @param dateCompute
	 * @return
	 */
	@Override
	public List<TfQualDeclaraPilotStay> findPilotStayList(String typeids, String orgIds, String dateCompute) {
		String hql ="from TfQualDeclaraPilotStay st where st.status is null and st.tfQualBaseType.typeid='"+typeids+"' " +
				" and to_char( st.year,'YYYY-MM') = '"+dateCompute+"'" +
				" and st.cmPeople.sysOrganization.id in "+orgIds+
				" order by st.trainType";
		return this.findPageByQuery(hql);
	}
	
	/**
	 * 根据资质类型、申报专员所在单位和人员计算日期查询申报人员的数量
	 * @param typeids
	 * @param orgIds
	 * @param dateCompute
	 * @return
	 */
	public int findPilotStayCount(String typeids, String orgIds, String dateCompute) {
		String hql ="select count(*) from TfQualDeclaraPilotStay st where st.status is null and st.tfQualBaseType.typeid='"+typeids+"' " +
				" and to_char( st.year,'YYYY-MM') = '"+dateCompute+"'" +
				" and st.cmPeople.sysOrganization.id in "+orgIds+
				" order by st.trainType";
		return this.getCountByHql(hql);
	}
	
	/**
	 * 按照资质类型和申报专员所在单位进行查询待申报人员
	 * @param typeId 资质类型id
	 * @param orgIds 单位id串，格式为: ('ss','df','sdd')
	 * @return
	 */
	@Override
	public List<TfQualDeclaraPilotStay> findPilotStaysByTypeAndOrgs(String typeId, String orgIds) {
		String hql ="from TfQualDeclaraPilotStay st where st.status is null " +
				" and st.tfQualBaseType.typeid='"+typeId+"' " +
				" and st.cmPeople.sysOrganization.id in "+orgIds;
		return this.findPageByQuery(hql);
	}
	
	/**
	 * 按照资质类型和申报专员所在单位进行查询申报人员的数量
	 * @param typeId 资质类型id
	 * @param orgIds 单位id串，格式为: ('ss','df','sdd')
	 * @return
	 */
	public int findPilotStaysCountByTypeAndOrgs(String typeId, String orgIds) {
		String hql = "select count(*) from TfQualDeclaraPilotStay st where st.status is null " +
				" and st.tfQualBaseType.typeid='"+typeId+"' " +
				" and st.cmPeople.sysOrganization.id in "+orgIds;;
		return this.getCountByHql(hql);
	}
	
	@Override
	public List<TfQualDeclarApprovalinfo> findApprovalinfoByDeclaredinfoid(String declaredinfoid) {
		String planHql = "from TfQualDeclarApprovalinfo info where info.pid ='"+ declaredinfoid+"'";
		return this.findPageByQuery(planHql);
	}
	
}
