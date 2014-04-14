package com.sinoframe.business;

import java.util.List;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrganization;

public interface ISysOrganizationBS extends IService {

	/**
	 * 判断此机构是否有子机构及是否有外键的引用
	 * @param strId
	 * @return
	 */
	public String hasChild(SysOrganization sysOrganization);
	
	public void deleteOrgs(String[] ids);
	
	public void deleteOrg(String id);
	
	public boolean hasChildren(String strId);
	
	public List findByAny(String boname, String[] attributename,
			Object[] attributevalue, String order);
	/**
	 * 根据机构Id查找上级机构
	 * @param strId 给定的当前的机构id
	 */
	public List<SysOrganization> findByChildId(String strId);

	/**
	 * 递归查处当前机构的所有上级机构
	 * @param parentId
	 * @return
	 */
	public List<String> getFunParentIds(List<SysOrganization> listSysOrganization, List<String> parentIds,String parentId);
	
	
	/**
	 * 选择所属职能机构
	 * 可以得到的级别 0.显示所有单位。1.显示上级单位，本单位的同级单位（包括本单位）和本单位的同级单位（包括本单位）的下级单位 2.显示本单位和下级单位    3.仅显示下级单位 
	 * @param organizationGrade  
	 * @param sysOrganization 
	 * @return
	 */
	public List<SysOrganization> getOrganizationList(int organizationGrade,SysOrganization sysOrganization);
	/**
	 * 得到树的根节点
	 * @param organizationGrade
	 * @param sysOrganization
	 * @return
	 */
	public List<SysOrganization> getOrgParentList(int organizationGrade,SysOrganization sysOrganization);
	
	
	
	/**
	 * 安全模块所需要的机构树   下拉框
	 * 业务需求：
	 * 		1.进来的是国航股份，则将一级单位全部取出
	 * 		2.如果是一级单位取本身、下级及监管单位
	 * 		3.如果是二级单位取本身、下级
	 * 		4.如果是三级单位取本身
	 * @param sysOrganization
	 * @return
	 */
	public List<SysOrganization> securityOrgTree(SysOrganization sysOrganization);
	
	/**
	 * 得到树的根节点
	 * @param sysOrganization
	 * @return
	 */
	public List<SysOrganization> securityOrgParentTree(SysOrganization sysOrganization);
	
	
	
	/**
	 * 根据页面上的部门的id值来求出机构集合
	 * @param orgFunIds text框内所填的机构的id值 如果为null或为""的话则不进行查询
	 * @return
	 */
	public List<SysOrganization> findOrgByFunIds(String orgFunIds) ;

	// ------------ add by jilili start -------------------//
	
	/**
	 * 查找当前单位指定级别的上级单位
	 * @param level
	 * @param sysOrganization
	 * @return
	 */
	public SysOrganization findParentByLevel(int level, SysOrganization sysOrganization);
	
	// ------------ add by jilili end -------------------//
	
}
