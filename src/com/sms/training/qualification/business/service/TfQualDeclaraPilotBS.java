package com.sms.training.qualification.business.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.util.DateTool;
import com.sms.training.qualification.bean.TfQualBaseLesson;
import com.sms.training.qualification.bean.TfQualBaseLessonCourse;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotCourselist;
import com.sms.training.qualification.business.ITfQuaApplyBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
import com.sms.training.qualification.dao.ITfQualApplyJbpmDao;
@Service("tfQualDeclaraPilotBS")
public class TfQualDeclaraPilotBS extends BaseBS implements	ITfQualDeclaraPilotBS {
	private ITfQuaApplyBS tfQuaApplyBS;
	private ITfQualApplyJbpmDao tfQualApplyJbpmDao;
	/**
	 * 根据资质类型查找对应的课次
	 * @param typeId 资质类型id
	 * @return 课次列表
	 */
	@Override
	public List<TfQualBaseLesson> findLessons(String typeId) {
		String hql = "select l from TfQualBaseType t, TfQualBaseLesson l ,TfQualBaseTemplate p  where t.tfQualBaseTemplate.id = p.id " +
					"and p.id = l.tfQualBaseTemplate.id and t.typeid ='"+typeId+"'order by l.lessonOrder";
		return this.findPageByQuery(hql);
	}
	
	/**
	 * 根据资质类型及人员id查找对应的课次，适用于模拟机复训
	 * @param typeId 资质类型id
	 * @return 课次列表
	 */
	@Override
	public List<TfQualBaseLesson> findLessons(TfQualBaseType baseType, String pilotId){
		String hql=" select te.ptgradeid from TfQualBaseType tp ,TfQualPilotTechrecord te ,CmPeople p "+ 
					" where p.id =te.cmPeople.id and tp.originalatrid.atrid = te.baseAirplantype.atrid " +
					" and p.id='"+pilotId+"' and tp.typeid='"+baseType.getTypeid()+"'";
		List<String> techGrades=this.findPageByQuery(hql);
		String grade=techGrades!=null && !techGrades.equals("")? techGrades.get(0):"NULL";
		String trainType=null;
		if("F1,F2,F3,F4,F5".indexOf(grade)>=0){
			trainType="F";//副驾驶
		}else if("M,J,A1,A2".indexOf(grade)>=0){
			trainType="J";//机长
		}
		
		hql = "select l from TfQualBaseType t, TfQualBaseLesson l ,TfQualBaseTemplate p  " +
				"where t.tfQualBaseTemplate.id = p.id and p.id = l.tfQualBaseTemplate.id and t.typeid = '"+baseType.getTypeid()+"'" +
				"and l.lessonTrainType='"+trainType+
				"'order by l.lessonOrder";
		List<TfQualBaseLesson> lessons= this.findPageByQuery(hql);
		boolean flag=new SimpleDateFormat("MMdd").format(new Date()).compareTo("0630")>0;
		TfQualBaseLesson ls=lessons.get(4);
		if(flag){
			lessons=lessons.subList(2, 4);
		}else{
			lessons=lessons.subList(0, 2);
		}
		lessons.add(ls);
		return lessons;
	}
	/**
	 * 根据课次id查找对应的课程
	 * @param lessonId 课次id
	 * @return 课程列表
	 */
	@Override
	public List<TfQualBaseLessonCourse> findCourses(String lessonId) {
		String hql="from TfQualBaseLessonCourse cs where 1=1 and cs.tfQualBaseLesson.lessonId='"+lessonId+"' order by cs.courseCode asc,cs.subject desc";
		return this.findPageByQuery(hql);
	}
	/**
	 * 根据机构角色飞机类型获得待处理人
	 * @param  orgRole 机构角色
	 * @return  
	 */
	@Override
	public List<TfQualDeclaraPilot> getCountByOrgAndRoleAndPlane(String state,String orgRole,String subGroupId,String planeType) {
		if("".equals(planeType)||null==planeType)
			planeType="";
		else
			planeType=" and t.tfQualDeclarInfo.tfQualBaseType.targetatrid.atrdesc='"+planeType+"'";
		StringBuffer sql =new StringBuffer("from TfQualDeclaraPilot t where 1=1  ");
		if(!orgRole.equals("") && state.equals(""))
		{
			sql.append("and t.orgRole in ("+orgRole+")"+planeType);
		}else  if(orgRole.equals("") && !state.equals(""))
		{
			sql.append("and t.state in ("+state+")"+planeType);
		}
		else if(!orgRole.equals("") && !state.equals(""))
		{
			sql.append("and t.state in ("+state+") and t.orgRole in ("+orgRole+")"+planeType);
		}
		if(subGroupId!=null && !subGroupId.equals(""))
		{
			sql.append("and t.tfQualBaseType.tfQualBaseTgroup.typegroupid='").append(subGroupId).append("' "+planeType);
		}
		return this.findPageByQuery(sql.toString());
	}
	/**
	 * 根据机构角色获得待处理人
	 * @param  orgRole 机构角色
	 * @return  
	 */
	@Override
	public List<TfQualDeclaraPilot> getCountByOrgAndRole(String state,String orgRole,String subGroupId) {
		StringBuffer sql=genHqlCountByOrgAndRole(state, orgRole, subGroupId);
		return this.findPageByQuery(sql.toString());
	}
	
	private StringBuffer genHqlCountByOrgAndRole(String state,String orgRole,String subGroupId){
		StringBuffer sql =new StringBuffer("from TfQualDeclaraPilot t where 1=1  ");
		if(subGroupId!=null && !subGroupId.equals("")){
			sql.append(" and t.tfQualBaseType.tfQualBaseTgroup.typegroupid='").append(subGroupId).append("' ");
		}
		if(state!=null && !"".equals(state)){
			sql.append("and t.state in ("+state+")" );
		}
		if(orgRole!=null && !orgRole.equals("")){
			sql.append(" and t.orgRole in ("+orgRole+") ");
		}
		if(orgRole.equals("")){
			sql.append(" and t.orgRole is null ");
		}
		return sql;
	}
	/**
	 * 根据资质全名和机构角色和类型和查询人所在机构获得待处理人
	 * @param  orgRole 机构角色
	 * @return  
	 * @author yanxu
	 */
	public List<TfQualDeclaraPilot> getCountByOrgAndRoleAndTypeAndState(String state,String orgRole,String typeDesc,String sysOrg,String subGroupId) {
		StringBuffer sql=getHqlCountByOrgAndRoleAndTypeAndState( state, orgRole, typeDesc, sysOrg,subGroupId);
		return this.findPageByQuery(sql.toString());
	}
	private StringBuffer getHqlCountByOrgAndRoleAndTypeAndState(String state,String orgRole,String typeSel,String orgSel,String subGroupId){
		StringBuffer sql =new StringBuffer("from TfQualDeclaraPilot t where 1=1 ");
		if(typeSel!=null && !typeSel.equals("")&& !typeSel.equals("ALL")){
			sql.append(" and t.tfQualBaseType.typeid = '").append(typeSel).append("' ");
		}else{
			sql.append(" and t.tfQualBaseType.tfQualBaseTgroup.typegroupid='").append(subGroupId).append("' ");
		}
		if(state!=null && !"".equals(state)){
			sql.append("and t.state in ("+state+")" );
		}
		if(orgRole!=null && !orgRole.equals("")){
			sql.append(" and t.orgRole in ("+orgRole+") ");
		}
		if(orgSel!=null && !orgSel.equals("")&& !orgSel.equals("ALL")){
			//sql.append(" and t.cmPeople.sysOrganization.id = '"+orgSel+"' ");
			sql.append(" and t.cmPeople.sysOrganization.id in ("+orgsIdList2Str(orgSel)+") ");
		}else{
			
		}
		return sql;
	}
	public List<SysOrganization> getSomeSysOrgs(){
		StringBuffer sql =new StringBuffer("from SysOrganization t where 1=1 ");
		sql.append(" and t.name in('天津分公司')");//,'湖北分公司','重庆分公司','上海分公司','浙江分公司','西南分公司')");
		return this.findPageByQuery(sql.toString());
	}
	/**
	 * 根据一个机构获得所有其子机构 拼接成sql用的字符串
	 * @return  
	 * @author yanxu
	 */
	public String orgsIdList2Str(String orgId){
		if(orgId.equals(ConstantList.TJFGB))
		{
			orgId=ConstantList.TJFGS;
		}
		StringBuffer sql =new StringBuffer("from SysOrganization t where 1=1 ");
		sql.append(" and t.id='"+orgId+"'");
		SysOrganization org=(SysOrganization)this.findPageByQuery(sql.toString()).get(0);
		
		List<SysOrganization> list=new ArrayList<SysOrganization>();
		tfQuaApplyBS.recursionOrganization(org, list);
		
		StringBuffer sb=new StringBuffer("");
		for(SysOrganization o:list){
			sb=sb.append("'"+o.getId()+"',");
		}
		sb.append("'"+orgId+"'");
		//sb.substring(0, sb.length()-1)
		return sb.toString();
	} 
	
	
	@Override
	public List<TfQualDeclaraPilot> getPilotByIds(String ids) {
		String hql="from TfQualDeclaraPilot t where t.detailsid in ("+ids+")";
		return this.findPageByQuery(hql);
	}

	@Override
	public List<TfQualDeclaraPilot> getCountByOrgAndRole2(String state,	String orgRole, String typeId) {
		StringBuffer sql = comment(state, orgRole);
		if(typeId!=null && !typeId.equals(""))
		{
			sql.append("and t.tfQualBaseType.typeid='"+typeId+"'");
		}
		return this.findPageByQuery(sql.toString());

	}

	private StringBuffer comment(String state, String orgRole) {
		StringBuffer sql =new StringBuffer("from TfQualDeclaraPilot t where 1=1  ");
		if(!orgRole.equals("") && state.equals(""))
		{
			sql.append("and t.orgRole in ("+orgRole+")");
		}else  if(orgRole.equals("") && !state.equals(""))
		{
			sql.append("and t.state in ("+state+")" );
		}
		else if(!orgRole.equals("") && !state.equals(""))
		{
			sql.append("and t.state in ("+state+") and t.orgRole in ("+orgRole+")" );
		}
		return sql;
	}

	@Override
	public List<TfQualDeclaraPilot> getCountByOrgAndRole3(String state,
			String orgRole, String subGroupId, String pilotName) {
		
		StringBuffer sql = comment(state, orgRole);
		if(subGroupId!=null && !subGroupId.equals(""))
		{
			sql.append("and t.tfQualBaseType.tfQualBaseTgroup.typegroupid = '").append(subGroupId).append("'");
			if(pilotName != null && !"".equals(pilotName.trim())){
			sql.append("and t.cmPeople.name like '%").append(pilotName.trim()).append("%'");
			}
		}
		return this.findPageByQuery(sql.toString());
	}
	//=============================================================================================
	/**
	 * 检查员过滤代办    
	 * @param subGroupId
	 * @param orgId
	 * @param roleList
	 * @return
	 */
	public List<TfQualPilotCourselist> getPilotListByC(List<SysRole>  roleList,String subGroupId,SysOrganization org,CmUser user,String likePilotName,String isT){
		StringBuffer sql=new StringBuffer("");
		try	{
			String state ="";
			StringBuffer orgRole = new StringBuffer("");
			state=jointState(orgRole, roleList, org.getId().trim());
			sql=getPilotByC(state, org.getId().trim(),subGroupId,user,likePilotName,isT);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return this.findPageByQuery(sql.toString());
	}
	private StringBuffer getPilotByC(String state,String orgSel,String subGroupId,CmUser user,String likePilotName,String isT){
		StringBuffer sql =new StringBuffer("select cl from TfQualDeclaraPilot t,TfQualPilotCourselist cl where 1=1 and t.detailsid=cl.tfQualDeclaraPilot.detailsid ");//还需要连接corelist 判断时间及state状态是不是null
		if(subGroupId!=null && !subGroupId.equals("")){
			sql.append("and t.tfQualBaseType.tfQualBaseTgroup.typegroupid = '").append(subGroupId).append("' ");
		}
		if("C".equals(isT)){    //如果是检查员需要拼接这个机构角色
			if(orgSel!=null && !orgSel.equals("")){
				sql.append(" and t.orgRole like '%"+orgSel.trim()+"-"+user.getUserId().trim()+"%' ");
			}
		}
		if("TC".equals(isT)){
			sql.append(" and cl.tcategory in ('T','C') ");
		}else{
			if(isT!=null && !"".equals(isT)){
				sql.append(" and cl.tcategory='"+isT+"' ");
			}
		}
		if(state!=null && !"".equals(state)){
			sql.append(" and t.state in ("+state+")" );
		}
		if(user !=null && user.getLoginName() !=null && !"".equals(user.getLoginName()) ){
			sql.append(" and cl.tracherPilotid='"+user.getLoginName().trim()+"' " );
		}
		sql.append(" and cl.state is null" );
//		sql.append(" and to_char(cl.trealstartdt,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd') " );
		sql.append(" and cl.trealstartdt between  to_date('"+DateTool.formatDate(DateTool.addDays(ConstantList.countQual))+" 00:00:00', 'YYYY-MM-DD hh24:mi:ss') and ");
		sql.append(" to_date('"+DateTool.formatDate(DateTool.getNow())+" 23:59:59', 'YYYY-MM-DD hh24:mi:ss') ");
		if(likePilotName!=null && !likePilotName.equals("")){
			sql.append(" and cl.tfQualDeclaraPilot.cmPeople.name like '%"+likePilotName+"%'");
		}
		return sql;
	}
	
	/**
	 * 获取教员、检查员登录后， 审核阶段的人数
	 * @param subGroupId
	 * @param orgId
	 * @param roleList
	 * @return  zhanghuifen
	 */
	@Override
	public int getPilotCountByC(List<SysRole> roleList, String subGroupId,String userOrgId, CmUser user,String isT) {
		StringBuffer sql=new StringBuffer("");
		try	{
			String state ="";
			StringBuffer orgRole = new StringBuffer("");
			state=jointState(orgRole, roleList, userOrgId.trim());
			if( "".equals(state.trim()) ){
				return 0;
			}
			sql=getPilotCountByC(state, userOrgId.trim(),subGroupId,user,isT);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return this.getCountByHql(sql.toString());
	}
	private StringBuffer getPilotCountByC(String state,String orgSel,String subGroupId,CmUser user,String isT){
		StringBuffer sql =new StringBuffer("select count(cl.courselistid) from TfQualDeclaraPilot t,TfQualPilotCourselist cl where 1=1 and t.detailsid=cl.tfQualDeclaraPilot.detailsid ");//还需要连接corelist 判断时间及state状态是不是null
		if(subGroupId!=null && !subGroupId.equals("")){
			sql.append("and t.tfQualBaseType.tfQualBaseTgroup.typegroupid='").append(subGroupId).append("' ");
		}
		if("C".equals(isT)){    //如果是检查员需要拼接这个机构角色
			if(orgSel!=null && !orgSel.equals("")){
				sql.append(" and t.orgRole like '%"+orgSel.trim()+"-"+user.getUserId().trim()+"%' ");
			}
		}
		if("TC".equals(isT)){
			sql.append(" and cl.tcategory in ('T','C')");
		}else{
			if(isT!=null && !"".equals(isT)){
				sql.append(" and cl.tcategory='"+isT+"' ");
			}
		}
		if(state!=null && !"".equals(state)){
			sql.append(" and t.state in ("+state+")" );
		}
		if(user !=null && user.getLoginName() !=null && !"".equals(user.getLoginName()) ){
			sql.append(" and cl.tracherPilotid='"+user.getLoginName().trim()+"' " );
		}
		sql.append(" and cl.state is null" );
		sql.append(" and cl.trealstartdt between  to_date('"+DateTool.formatDate(DateTool.addDays(ConstantList.countQual))+" 00:00:00', 'YYYY-MM-DD hh24:mi:ss') and ");
		sql.append(" to_date('"+DateTool.formatDate(DateTool.getNow())+" 23:59:59', 'YYYY-MM-DD hh24:mi:ss') ");
		return sql;
	}
	
	//获取训练阶段（即审核阶段）的人员列表     除教员、检查员外
	@Override
	public List<TfQualDeclaraPilot> getPilotList(String subGroupId,String orgId,List<SysRole>  roleList) {
		List<TfQualDeclaraPilot> tfQualDeclaraPilotList=null;
		try	{
			String state="";
			StringBuffer orgRole=new StringBuffer();
			state=jointState(orgRole, roleList, orgId);
			if("".equals(state.trim())){
				return new ArrayList<TfQualDeclaraPilot>();
			}
			//判断是否为新雇员流程分支节点
			if(subGroupId.equals("QUAL_NE_XY")&&(orgId.equals("8a8a11ef3183b73d01318456e2a20000")||orgId.equals("8a8a11ef318811170131885a71330011"))){
				orgRole=new StringBuffer();
			}
			tfQualDeclaraPilotList=this.getCountByOrgAndRole(state,orgRole.toString(),subGroupId);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return tfQualDeclaraPilotList;
	}
	
	//获取训练阶段（即审核阶段）的人数     除教员、检查员外
	public int getPilotCountOfTraining(String subGroupId, String orgId,List<SysRole> roleList) {
		StringBuffer orgRole=new StringBuffer();
		String state=jointState(orgRole, roleList, orgId);
		if( "".equals(state.trim()) ){
			return 0;
		}
		StringBuffer hql= genHqlCountByOrgAndRole(state, orgRole.toString(), subGroupId);
		hql.insert(0, "select count(detailsid) ");
		return this.getCountByHql(hql.toString());
	}
	
	//拼接state和orgRole，用作查询审核阶段人数的条件
	private String jointState(StringBuffer orgRole,List<SysRole> roleList,String orgId){
		StringBuffer state = new StringBuffer("");
		String roleName="";
		for (SysRole role : roleList) {
			roleName=role.getRoleName();
			orgRole.append("'").append(orgId).append("-").append(roleName).append("',");
			if(roleName.contains("教员")){
				state.append("'ZXL',");
			}
			if(roleName.contains("检查员")){
				state.append("'DJC',");
			}
			if(roleName.contains("资质-分公司级飞管标准专员")){
				state.append("'DJC','DBCXL','ZLKZJLSP',");
			}
			if(roleName.equals("资质-公司级技术委员会办公室主任")){
				state.append("'GSZR','PXBLDSP','GSJSWYHSP',");
			}
			if(roleName.equals("资质-分公司级技术委员会办公室主任")){
				state.append("'FGSZR','DBCXL','ZLKZGJJLSP',");
			}
			if(roleName.equals("资质-训练专员")){  //TODO 张会粉 做FTD教员，使用 
				state.append("'ZLKZZYSB','KZZYZZGX','WCXL',");
			}
			if(roleName.equals("资质-公司飞管领导")){  //TODO 张会粉 做FTD教员，使用
				state.append("'WCPX','FGLDSP',");
			}
			if(roleName.equals("资质-公司飞管专员")){  //TODO 张会粉 做FTD教员，使用
				state.append("'FGZYSB','FGZYFW',");
			}
			if(roleName.equals("资质-公司飞管标准高级经理")){  //TODO 张会粉 做FTD教员，使用
				state.append("'FGGJJLSP','GSJL',");
			}
		}
	//下面注释的代码暂时先不要删除   张会粉	
//		String state="";
//		String roleName="";
//		for (SysRole role : roleList){
//			roleName=role.getRoleName();
//			orgRole.append("'").append(orgId).append("-").append(roleName).append("',");
//			if(roleName.contains("教员")){
//				state+="'ZXL',";
//			}
//			if(roleName.contains("检查员")){
//				state+="'DJC',";
//			}
//			if(roleName.contains("资质-分公司级飞管标准专员")){
////				state+="'DJC','DBCXL',";   TODO 张会粉 做FTD教员，暂时注释
//				state+="'DJC','DBCXL','ZLKZJLSP'";
//			}
////			if(roleName.contains("公司飞管专员")){
////				state+="'GSZY',";
////			}
//			if(roleName.equals("资质-公司级技术委员会办公室主任")){
////				state+="'GSZR',";        TODO 张会粉 做FTD教员，暂时注释
//				state+="'GSZR','PXBLDSP','GSJSWYHSP',";
//			}
//			if(roleName.equals("资质-分公司级技术委员会办公室主任")){
////				state+="'FGSZR',";       TODO 张会粉 做FTD教员，暂时注释，'DBCXL'是新雇员流程加的
//				state+="'FGSZR','DBCXL','ZLKZGJJLSP',";
//			}
//			if(roleName.equals("资质-公司飞管领导")){//检查员流程使用
//				state+="'WCPX',";//完成培训
//			}
//			if(roleName.equals("资质-训练专员")){  //TODO 张会粉 做FTD教员，使用 
//				state+="'ZLKZZYSB','KZZYZZGX','WCXL',";
//			}
//			if(roleName.equals("资质-公司飞管领导")){  //TODO 张会粉 做FTD教员，使用
//				state+="'FGLDSP',";
//			}
//			if(roleName.equals("资质-公司飞管专员")){  //TODO 张会粉 做FTD教员，使用
//				state+="'FGZYSB','FGZYFW'";
//			}
//			if(roleName.equals("资质-公司飞管标准高级经理")){  //TODO 张会粉 做FTD教员，使用
//				state+="'FGGJJLSP','GSJL'";
//			}
//		}
		if(state.length()>0){
			state =state.deleteCharAt(state.length()-1);
		}
		if(orgRole.length()>0){
			orgRole.deleteCharAt(orgRole.length()-1);
		}
		return state.toString();
	}
		
	@Override
	public List<TfQualDeclaraPilot> getPilotList2(String subGroupId, String orgId, List<SysRole> roleList) {
		List<TfQualDeclaraPilot> tfQualDeclaraPilotList=null;
		try {
			String state="";
			StringBuffer orgRole=new StringBuffer();
			state=jointState2(orgRole, roleList, orgId);
			if("".equals(state.trim())){
				return null;
			}
			tfQualDeclaraPilotList=this.getCountByOrgAndRole(state,orgRole.toString(),subGroupId);
		}catch (Exception e) {
				e.printStackTrace();
		}
		return tfQualDeclaraPilotList;
	}
	
	/**
	 * 获取资质更新的人员待办
	 * @param subGroupId
	 * @return
	 */
	@Override
	public List<TfQualDeclaraPilot> getPilotList3(String subGroupId,String stateSel,String typeSel,String orgSel,List<SysRole> roleList,String orgId){
		List<TfQualDeclaraPilot> tfQualDeclaraPilotList=null;
		try	{
			StringBuffer state = new StringBuffer("");
			StringBuffer orgRole = new StringBuffer("");
			for (int i = 0,n=roleList.size(); i <n ; i++){
				if(i!=roleList.size()-1){
					orgRole.append("'").append(orgId).append("-").append(roleList.get(i).getRoleName()).append("',");
				} else {
					orgRole.append("'").append(orgId).append("-").append(roleList.get(i).getRoleName()).append("'");
				}
				if(roleList.get(i).getRoleName().contains("资质-分公司级飞管标准专员")){
					state.append("'DZZGX',");
				} else if(roleList.get(i).getRoleName().contains("公司飞管专员")){
			    	if(stateSel.equals("ALL")||stateSel.equals("")||stateSel==null){
			    		state.append("'GSZY','GSZY_R','GSZY_U','GSZY_O',");
			    	}
			    	if(stateSel.equals("GSZY_R")){
						state.append("'GSZY_R',");
			    	}
					if(stateSel.equals("GSZY_U")){
						state.append("'GSZY_U',");
					}
					if(stateSel.equals("GSZY_O")){
						state.append("'GSZY_O',");
					}
					if(stateSel.equals("GSZY")){
						state.append("'GSZY',");
					}
				}
			}
			//获取待处理人总数
			if(state.length()>0){
				state=state.deleteCharAt(state.length()-1);
			}else{
				return null;
			}
			tfQualDeclaraPilotList=this.getCountByOrgAndRoleAndTypeAndState(state.toString(), orgRole.toString(), typeSel, orgSel,subGroupId);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return tfQualDeclaraPilotList;
	}
	
	//获取“下发训练”阶段的人数
	public int getPilotCountOfIssued(String subGroupId, String orgId,List<SysRole> roleList) {
		StringBuffer orgRole=new StringBuffer();
		String state=jointState2(orgRole, roleList, orgId);
		if( "".equals(state.trim()) ){
			return 0;
		}
		StringBuffer hql= genHqlCountByOrgAndRole(state, orgRole.toString(), subGroupId);
		hql.insert(0, "select count(detailsid) ");
		return this.getCountByHql(hql.toString());
	}
		
	//拼接state和orgRole
	private String jointState2(StringBuffer orgRole,List<SysRole> roleList,String orgId){
		StringBuffer state = new StringBuffer("");
		for (SysRole role : roleList){
			orgRole.append("'").append(orgId).append("-").append(role.getRoleName()).append("',");
			if(role.getRoleName().contains("资质-M-j-F1-F2-训练专员") || role.getRoleName().contains("资质-训练专员")){
				state.append("'DXL',");
			}
		}
		
		if(state.length()>0){
			state=state.deleteCharAt(state.length()-1);
		}
		if(orgRole.length()>0){
			orgRole.deleteCharAt(orgRole.length()-1);
		}
		return state.toString();
	}
	@Override
	public int getPilotCountOfApply(String infoIds){
		StringBuffer hql=new StringBuffer();
		hql.append("select count(detailsid) from TfQualDeclaraPilot t where t.tfQualDeclarInfo.declaredinfoid in (").append(infoIds).append(")");
		hql.append(" and t.state is null");
		return this.getCountByHql(hql.toString());
	}

	@Override
	public int getPilotCountOfApply(String orgRole, String subGroupId){
		return tfQualApplyJbpmDao.countPilotOfApply(orgRole, subGroupId);
	}

	@Override
	public int getCountOfPilotStay(String orgIds, String subGroupId) {
		StringBuffer hql=new StringBuffer();
		hql.append("select count(stayid) from TfQualDeclaraPilotStay st where st.status is null ");
		if(subGroupId!=null && !subGroupId.equals("")){
			hql.append(" and st.tfQualBaseType.tfQualBaseTgroup.typegroupid = '").append(subGroupId).append("' ");
		}
		if(orgIds!=null && !orgIds.equals("")){
			hql.append(" and st.cmPeople.sysOrganization.id in ").append(orgIds);
		}
		return this.getCountByHql(hql.toString());
	}

	public ITfQuaApplyBS getTfQuaApplyBS() {
		return tfQuaApplyBS;
	}
	@Resource
	public void setTfQuaApplyBS(ITfQuaApplyBS tfQuaApplyBS) {
		this.tfQuaApplyBS = tfQuaApplyBS;
	}

	@Override
	public String checkIsT(List<SysRole> roleList) {
		String teacherType="";
	    boolean teacher = false;
	    boolean cheker = false;
		if(roleList!=null && roleList.size()!=0){
			for (int i = 0; i < roleList.size(); i++) {
				if(roleList.get(i).getRoleName().contains("资质-教员")){
					teacher = true;
				}else if(roleList.get(i).getRoleName().contains("资质-检查员")){
					cheker = true;
				}
				if(teacher && cheker){
					break;
				}
			}
			if(teacher && !cheker){
				//T表示教员
				teacherType = "T";
			}else if(!teacher && cheker){
				//C表示检查员
				teacherType = "C";
			}else if(teacher && cheker){
				//TC表示即是教员又是检查员
				teacherType = "TC";
			}
		}
		return teacherType;
	}
	
	/** 计算资质更新阶段的人数
	 * @param subGroupId
	 * @param orgId
	 * @param roleList
	 * @return
	 */
	@Override
	public int getPilotCount4Update(String subGroupId, String orgId,List<SysRole> roleList) {
		List<TfQualDeclaraPilot> ls = this.getPilotList3(subGroupId, "", "", "", roleList, orgId);
		return ls!=null ? ls.size() : 0;
	}
	
	public ITfQualApplyJbpmDao getTfQualApplyJbpmDao() {
		return tfQualApplyJbpmDao;
	}
	@Resource
	public void setTfQualApplyJbpmDao(ITfQualApplyJbpmDao tfQualApplyJbpmDao) {
		this.tfQualApplyJbpmDao = tfQualApplyJbpmDao;
	}
}
