package com.sinoframe.business.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserPasswordHistory;
import com.sinoframe.bean.SysLoginInfo;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.bean.SysUserOrgRelationId;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.dao.ICmUserDao;
import com.sinoframe.dao.ISysLoginInfoDao;
import com.sms.training.qualification.bean.AhUser;

@Service("cmUserBS")
public class CmUserBS extends BaseBS implements ICmUserBS {
	
	ICmUserDao cmUserDao;
	ISysLoginInfoDao sysLoginInfoDao;
	@Resource
	public final void setSysLoginInfoDao(ISysLoginInfoDao sysLoginInfoDao) {
		this.sysLoginInfoDao = sysLoginInfoDao;
	}

	@Resource
	public final void setCmUserDao(ICmUserDao cmUserDao) {
		this.cmUserDao = cmUserDao;
	}
	
	@Override
	public <T> List<T> findAll() {
		return cmUserDao.findAll("CmUser");
	}
	
	@Override
	public <T> T findById( Serializable id){
		return (T) cmUserDao.findById(AhUser.class,id);
	}
	
	@Override
	public List findByAny( String attributename,Object attributevalue, String order){
		return cmUserDao.findByAny("CmUser", attributename, attributevalue, order);
	}
	
	@Override
	public <T> void update(T entity) {
		cmUserDao.update(entity);		
	}

	@Override
	public AhUser login(String boname, String[] attributename,
			String[] attributevalue, String order) {
		List<AhUser> list=this.cmUserDao.findByAny(boname, attributename, attributevalue, "");
		AhUser ahUser=null;
		if(list.size()==1) ahUser=list.get(0);
		return ahUser;
	}

	@Override
	public Integer bulkUpdate(String boname, String setattributename,
			Object setattributevalue, String condition) {
		// TODO Auto-generated method stub
		return this.cmUserDao.bulkUpdate(boname, setattributename, setattributevalue, condition);
	}

	@Override
	public void saveTbUserPwdAndUpdateTbUser(
			CmUserPasswordHistory cmUserPasswordHistory, CmUser cmUser) {
		this.cmUserDao.saveTbUserPwdAndUpdateTbUser(cmUserPasswordHistory, cmUser);
	}

	@Override
	public List<CmUser> findByNotInIds(String tempString) {
		return this.findPageByQuery("from CmUser t where t.state = '1' and t.userId not in ("+tempString+") order by nlssort(t.name, 'NLS_SORT=SCHINESE_PINYIN_M')");
	}

	@Override
	@Transactional
	public void updateUserAndSysLoginInfo(CmUser cmUser,
			SysLoginInfo sysLoginInfo,String[] orgFunId) {
		// TODO Auto-generated method stub
		SysUserOrgRelation sysUserOrgRelation=null;
		SysOrganization sysOrganization=null;
		this.update(cmUser);
		
		// 根据用户id删除已经存在的用户机构表的数据
		List<SysUserOrgRelation>  sysUserOrgRelationList=this.cmUserDao.findByAny("SysUserOrgRelation", "cmUser.userId", "'"+cmUser.getUserId()+"'", "");
		for (int i = 0; i < sysUserOrgRelationList.size(); i++) {
			this.delete(sysUserOrgRelationList.get(i));
		}
		for (int i = 0; i < orgFunId.length; i++) {
			sysOrganization=new SysOrganization();
			sysOrganization=this.findById(SysOrganization.class, orgFunId[i]);
			SysUserOrgRelationId sysUserOrgRelationId=new SysUserOrgRelationId(cmUser.getUserId(),sysOrganization.getId());
			sysUserOrgRelation=new SysUserOrgRelation(sysUserOrgRelationId,cmUser,sysOrganization);					
			this.save(sysUserOrgRelation);
		}		
		
		this.sysLoginInfoDao.update(sysLoginInfo);
		
	}

	@Override
	@Transactional
	public void saveListAndUserOrg(List<Object> list, List<SysUserOrgRelation> userOrgList) {
		this.cmUserDao.saveList(list);
		CmUser cmUser=(CmUser)list.get(0);
		for (int i = 0; i < userOrgList.size(); i++) {
			((SysUserOrgRelation)userOrgList.get(i)).getId().setUserId(cmUser.getUserId());
		}
		this.cmUserDao.saveList(userOrgList);
	}

	@Override
	public boolean ifLeave(String hrid) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
