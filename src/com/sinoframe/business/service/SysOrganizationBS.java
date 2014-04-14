///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  SysOrganization的service方法</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft jilili
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
 **/
package com.sinoframe.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.ISysOrganizationBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.dao.ISysOrganizationDao;

@Service("sysOrganizationBS")
@Transactional
public class SysOrganizationBS extends BaseBS implements ISysOrganizationBS {

	private ISysOrganizationDao sysOrganizationDao;

	/**
	 * 某个机构对象是否含有子项（子机构、外键关系）
	 * 
	 * @param 机构的id
	 */
	public String hasChild(SysOrganization sysOrganization) {
		// 返回结果
		StringBuffer result = new StringBuffer();
		// 根据strId查找对应的机构
		if (sysOrganization != null) {
			result.append("该机构下有：");
			boolean bOrgAdm=false;
			boolean bOrgFun=false;
			boolean bOrgSupervise=false;
			Set<SysOrganization> orgAdmSet=sysOrganization.getSetChild_Adm();
			for (SysOrganization orgAdm : orgAdmSet) {
				if(orgAdm.getState().equals("1")){
					bOrgAdm=true;
					break;
				}
			}
			Set<SysOrganization> orgFunSet=sysOrganization.getSetChild_Fun();
			for (SysOrganization orgFun : orgFunSet) {
				if(orgFun.getState().equals("1")){
					bOrgFun=true;
					break;
				}
				
			}
			Set<SysOrganization> orgSuperviseSet=sysOrganization.getSetChild_Supervise();
			for (SysOrganization orgSupervise : orgSuperviseSet) {
				if(orgSupervise.getState().equals("1")){
					bOrgSupervise=true;
					break;
				}
			}
			
			
			// 判断此机构是否含有子机构
			if (bOrgAdm|| bOrgFun|| bOrgSupervise ) {
				result.append("子机构");
			}
			// 判断此机构是否含有外部系统映射关系
			if (!sysOrganization.getSetSysOrgCodeMapping().isEmpty()) {
				result.append(",外部系统映射关系");
			}
			// 判断此机构是否含有员工
			if (!sysOrganization.getSysUserOrgRelations().isEmpty()) {
				result.append(",员工");
			}
			result.append(",请确定其不含子项后，再进行删除！");

		} else {
			result.append("该机构不存在！");
		}
		return result.toString();
	}

	/**
	 * 判断某个机构是否含有子项（子机构、外键关系）
	 */
	public boolean hasChildren(String strId) {
		SysOrganization sysOrganization = this.findById(SysOrganization.class,
				strId);
		if (!sysOrganization.getSetChild_Adm().isEmpty()
				|| !sysOrganization.getSetChild_Fun().isEmpty()
				|| !sysOrganization.getSetChild_Supervise().isEmpty()
				|| !sysOrganization.getSetSysOrgCodeMapping().isEmpty()
				|| !sysOrganization.getSetCmUser().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 删除多个机构
	 * 
	 * @param 将要删除的机构的id
	 */
	public void deleteOrgs(String[] ids) {
		// sysOrganizationDao.updateByIds(ids);
		String strIds = Util.toStringIds(Arrays.toString(ids).replace("[", "").replace("]", ""));
		String hql = "update SysOrganization set state = '0' where id in ( " + strIds + " )";
		this.sysOrganizationDao.executeUpdate(hql);
	/*	this.sysOrganizationDao.bulkUpdate("SysOrganization", "state", "'0'", "id in ("
				+ strIds + ")");*/
	}

	/**
	 * 根据Id删除某个数据-- 将该记录的state字段的值改变为"0"
	 */
	public void deleteOrg(String id) {
		SysOrganization sysOrganization = this.findById(SysOrganization.class,
				id);
		sysOrganization.setState("0");
		this.update(sysOrganization, id);
	}

	public ISysOrganizationDao getSysOrganizationDao() {
		return sysOrganizationDao;
	}

	@Resource
	public void setSysOrganizationDao(ISysOrganizationDao sysOrganizationDao) {
		this.sysOrganizationDao = sysOrganizationDao;
	}
	
	@Override
	public List findByAny(String boname, String[] attributename,
			Object[] attributevalue, String order) {
		
		return this.sysOrganizationDao.findByAny(boname, attributename, attributevalue, order);
	}
	
	/**
	 * 根据机构Id查找上级机构
	 * @param strId 给定的当前的机构id
	 */
	public List<SysOrganization> findByChildId(String strId){
		List<SysOrganization> listSysOrganization = new ArrayList<SysOrganization>();
		SysOrganization sysOrganiztion = this.findById(SysOrganization.class, strId);
		return listSysOrganization;
	}

	private List<String> getFunParentId(List<String> parentIds, String parentId) {
		SysOrganization sysOrganization = this.findById(SysOrganization.class, parentId);
		if(sysOrganization != null){
			parentIds.add(sysOrganization.getId());
			if(sysOrganization.getParent_Fun() != null){
				getFunParentId(parentIds,sysOrganization.getParent_Fun().getId());
			}
		}
		return parentIds;
	}
	

	
	@Override
	public List<String> getFunParentIds(List<SysOrganization> listSysOrganization, List<String> parentIds, String parentId) {
		List<String> allIds = new ArrayList<String>();
		for(SysOrganization sysOrganization : listSysOrganization){
			if(!sysOrganization.getSetChild_Fun().isEmpty()){
				allIds.add(sysOrganization.getId());
			}
		}
		allIds.removeAll(this.getFunParentId(parentIds, parentId));
		return allIds;
	}
	
	/**
	 * 查找机构集合的递归。
	 * @param sysOrgList 所存的递归的集合
	 * @param sysOrganization 传进来的根据机构
	 * @return
	 * @author chenleilei
	 */
	public Set<SysOrganization> getOrgList(Set<SysOrganization> sysOrgList, SysOrganization sysOrganization){
		// 首先将第一个机构实体加入到集合当中 
		sysOrgList.add(sysOrganization);
		// 求出所有的当前机构的子集合
		Set<SysOrganization> sysOrgSet = sysOrganization.getSetChild_Fun();
		// 循环整个的set集合
		for(SysOrganization organization : sysOrgSet){
			if(organization.getState().equals("1")){
				// 将符合条件的加入到set集合中
				sysOrgList.add(organization);
				// 执行递归操作
				getOrgList(sysOrgList, organization);
				
			}
		}
		return sysOrgList;
	}

	/**
	 * 选择所属职能机构
	 * 可以得到的级别 
	 * 0.显示所有单位。
	 * 1.显示上级单位，本单位的同级单位（包括本单位）和本单位下级
	 * 2.显示本单位和下级单位    
	 * 3.仅显示下级单位 
	 * @param organizationGrade  
	 * @param sysOrganization 
	 * @return
	 */
	@Override
	public List<SysOrganization> getOrganizationList(int organizationGrade,
			SysOrganization sysOrganization) {
		String hql="from SysOrganization so where so.state = '1' ";
		List<SysOrganization> sysOrganizationList = new ArrayList<SysOrganization>();
		Set<SysOrganization> sysOrgaSet = new HashSet<SysOrganization>();
		Set<SysOrganization> set=new HashSet<SysOrganization>();
		try {
			// 判断传进来的级别 根据级别求出机构的实体
			if(organizationGrade==0){
				hql+=" and parent_Fun is null";		
				sysOrganization=(SysOrganization)sysOrganizationDao.findPageByQuery(hql).get(0);
			}
			// 显示上级单位，本单位的同级单位（包括本单位）和本单位下级
			if (organizationGrade==1) {				
				if(sysOrganization.getParent_Fun()!=null){
					// 将父类中的所有子类都添加到set集合中
					set=sysOrganization.getParent_Fun().getSetChild_Fun();
					// 将父类添入到list集合当中 
					set.add(sysOrganization.getParent_Fun());
					// 移除本身
					boolean b=set.remove(sysOrganization);
					// 将set集合添加list中
					sysOrganizationList.addAll(set);
				}
				for (SysOrganization sysOrg : set) {
					if(!sysOrg.getState().equals("1")){
						sysOrganizationList.remove(sysOrg);
					}
				}
				// 使用递归得出本身，并将其子类得到
				sysOrgaSet=getOrgList(sysOrgaSet, sysOrganization);
				// 将本身及子类加到list集合当中 
				sysOrganizationList.addAll(sysOrgaSet);
				
			} else {				
				sysOrgaSet = getOrgList(sysOrgaSet,sysOrganization);
				sysOrganizationList.addAll(sysOrgaSet);
			}
			if (organizationGrade==3) {
				sysOrganizationList.remove(sysOrganization);
			}
			// 对拿到的集合进行排序。
			Collections.sort(sysOrganizationList, new Comparator<SysOrganization>() {

				@Override
				public int compare(SysOrganization o1, SysOrganization o2) {
					
						if(o1.getOrgType()!=null&& o2.getOrgType()!=null && o1.getOrgType() < o2.getOrgType()){
							return 1;
						}else if(o1.getOrgType()!=null && o2.getOrgType()!=null && o1.getOrgType() == o2.getOrgType()){
							
								if(o1.getOrgOrder()!=null && o2.getOrgOrder()!=null && o1.getOrgOrder() < o2.getOrgOrder()){
									return -1;
								}else if(o1.getOrgOrder()!=null && o2.getOrgOrder()!=null && o1.getOrgOrder() == o2.getOrgOrder()){
									if(o1.getName()!=null&&o2.getName()!=null){
										if(o1.getName().compareTo(o2.getName())==0){
											return 0;
										}else {
											return o1.getName().compareTo(o2.getName());
										}
									}else {
										if(o1.getName()==null&&o2.getName()!=null){
											return -1;
										}
										return 1;
									}
								}else{
									if(o1.getOrgOrder()==null&&o2.getOrgOrder()!=null){
										return -1;
									}
									return 1;
								}
							
						}else {
							if(o1.getOrgType()==null&&o2.getOrgType()!=null){
								return 1;
							}	return -1;
							
						}
					
				}
				
				
			});
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return sysOrganizationList;
		
	}

	/**
	 * 选择所属职能机构
	 * 可以得到的级别 
	 * 0.返回最大的单位
	 * 1.返回本单位的上一级单位
	 * 2.返回本单位  
	 * 3.返回下级单位 
	 * @param organizationGrade  
	 * @param sysOrganization 
	 * @return
	 */
	@Override
	public List<SysOrganization> getOrgParentList(int organizationGrade,
			SysOrganization sysOrganization) {
		
		List<SysOrganization> sysOrganizationList = new ArrayList<SysOrganization>();
		String hql="from SysOrganization so where so.state = '1' ";
		if(organizationGrade==0){
			hql+=" and parent_Fun is null ";			
			sysOrganizationList=sysOrganizationDao.findPageByQuery(hql);	
		}else if (organizationGrade==1) {
			sysOrganizationList.add(sysOrganization.getParent_Fun());
		}else if (organizationGrade==2) {
			sysOrganizationList.add(sysOrganization);
			
		}else if (organizationGrade==3) {
			sysOrganizationList.addAll(sysOrganization.getSetChild_Fun()); 
		}
		
		return sysOrganizationList;
	}



	@Override
	public List<SysOrganization> findOrgByFunIds(String orgFunIds) {	
		List<SysOrganization> sysOrganizationList=new ArrayList<SysOrganization>();
		orgFunIds=com.sinoframe.common.util.Util.toStringIds(orgFunIds);
		sysOrganizationList=sysOrganizationDao.findPageByQuery("from SysOrganization where id in ("+orgFunIds+") ");
		return sysOrganizationList;
	}

	/**
	 * 安全模块所需要的机构树
	 * 业务需求：
	 * 		1.进来的是国航股份，则将一级单位全部取出
	 * 		2.如果是一级单位取本身、下级及监管单位
	 * 		3.如果是二级单位取本身、下级
	 * 		4.如果是三级单位取本身
	 * @param sysOrganization
	 * @return
	 */
	@Override
	public List<SysOrganization> securityOrgTree(SysOrganization sysOrganization) {
		// TODO Auto-generated method stub

		List<SysOrganization> orgList=new ArrayList<SysOrganization>();
		/*String hql=" from SysOrganization where id = '"+sysOrganization.getId()+"'";
		sysOrganization=(SysOrganization)sysOrganizationDao.findByAnyHql(hql).get(0);*/
		
		
		if(sysOrganization.getOrgLevel().equals("1")){	
			String hql=" from SysOrganization where orgLevel in ('1','2') and type='1' and state = '1' and parent_Fun.id <> null order by orgOrder";
			orgList=sysOrganizationDao.findByAnyHql(hql);
		}else if (sysOrganization.getOrgLevel().equals("2")) {
			orgList.add(sysOrganization);
			orgList.addAll(sysOrganization.getSetChild_Fun());
			String orgIds="";
			for (SysOrganization sysOrg : orgList) {
				orgIds += "'" + sysOrg.getId() + "',";
			}
			if(orgIds.length()!=0){
				orgIds=orgIds.substring(0, orgIds.length()-1);
			}
				
			String hql=" from SysOrganization where parent_Supervise.id in ("+orgIds+") ";
			orgList.addAll(sysOrganizationDao.findByAnyHql(hql));		
				
		}else if(sysOrganization.getOrgLevel().equals("3")) {
			orgList.add(sysOrganization);
			orgList.addAll(sysOrganization.getSetChild_Fun());
				
		}else if (sysOrganization.getOrgLevel().equals("4")){
			orgList.add(sysOrganization);
		}
		
		List<SysOrganization> organizationList=new ArrayList<SysOrganization>();
		if(("2").equals(sysOrganization.getOrgLevel())){
			for(SysOrganization organization:orgList){
				boolean idFlag = false; 
				for(int i = 0; i < organizationList.size(); i++){
					if(organization.getId().equals(organizationList.get(i).getId())){
						idFlag = true;
						continue;
					}
				}
				if(idFlag) continue;
				if(organization.getType().equals("1") && organization.getState().equals("1") && organization.getParent_Fun() !=null){
					organizationList.add(organization);
				}
			}
		}else{
			for(SysOrganization organization:orgList){
				if(organization.getType().equals("1") && organization.getState().equals("1") && organization.getParent_Fun() !=null){
					organizationList.add(organization);
				}
			}
		}
			
		return organizationList;
	}

	@Override
	public List<SysOrganization> securityOrgParentTree(
			SysOrganization sysOrganization) {
		// TODO Auto-generated method stub
		List<SysOrganization> orgList=new ArrayList<SysOrganization>();
		
		/*String hql=" from SysOrganization where id = '"+sysOrganization.getId()+"'";
		sysOrganization=(SysOrganization)sysOrganizationDao.findByAnyHql(hql).get(0);*/
		
		if(sysOrganization.getOrgLevel().equals("1")){			
			String hql=" from SysOrganization where orgLevel in ('1','2') and type='1' and state = '1' and parent_Fun.id <> null order by orgLevel ,orgOrder";
			orgList=sysOrganizationDao.findByAnyHql(hql);
		}else {
			orgList.add(sysOrganization);		
		}
		
		List<SysOrganization> organizationList=new ArrayList<SysOrganization>();
		for(SysOrganization organization:orgList){
			if(organization.getType().equals("1") && organization.getState().equals("1") && organization.getParent_Fun() !=null){
				organizationList.add(organization);
			}
			System.out.println(organization.getName()+"-------");
		}
			
		return organizationList;
	}

	/**
	 * 递归，判断是第几层机构
	 * @param sysOrganization 当前机构
	 * @param num 
	 * @return
	 */
	public int recursionSecurityByOrgNum(SysOrganization sysOrganization,int num){
		
		if(sysOrganization.getParent_Fun()!=null){
			num+=1;
			recursionSecurityByOrgNum(sysOrganization.getParent_Fun(), num);
		}
		return num;
	}

	
	// ------------ add by jilili start -------------------//
	/**
	 * 查找当前单位指定级别的上级单位
	 * @param level
	 * @param sysOrganization
	 * @return
	 */
	public SysOrganization findParentByLevel(int level, SysOrganization sysOrganization){
		sysOrganization = sysOrganization.getParent_Fun(); 
		if(sysOrganization != null && !sysOrganization.getOrgLevel().equals(String.valueOf(level))){
			sysOrganization = findParentByLevel(level, sysOrganization);
		}
		return sysOrganization;
	}
	// ------------ add by jilili end -------------------//
}
