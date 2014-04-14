package com.sinoframe.dao;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserPasswordHistory;


public interface ICmUserDao extends IPublicDao{
	public void saveTbUserPwdAndUpdateTbUser(CmUserPasswordHistory cmUserPasswordHistory,CmUser cmUser);
}
