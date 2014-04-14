package com.sinoframe.business;

import java.io.Serializable;
import java.util.List;

import com.sinoframe.bean.SysLoginInfo;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserPasswordHistory;
import com.sms.training.qualification.bean.AhUser;

public interface ICmUserBS extends IService{
	
	public <T> List<T> findAll();
	
	public <T> T findById(Serializable id);

	public List findByAny( String attributename,Object attributevalue, String order);
	
	public <T> void update(T entity);
	
	public AhUser login(String boname, String[] attributename,
			String[] attributevalue, String order);
	
	public Integer bulkUpdate(final String boname,final String setattributename,
			final Object setattributevalue,final String condition);
	
	public void saveTbUserPwdAndUpdateTbUser(CmUserPasswordHistory cmUserPasswordHistory,CmUser cmUser);

	public List<CmUser> findByNotInIds(String tempString);
	
	public void updateUserAndSysLoginInfo(CmUser cmUser,SysLoginInfo sysLoginInfo,String[] orgFunId);
	
	public void saveListAndUserOrg(List<Object> list , List<SysUserOrgRelation> userOrgList);
	
	
	
}
