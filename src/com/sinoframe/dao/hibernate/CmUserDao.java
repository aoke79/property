package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserPasswordHistory;
import com.sinoframe.dao.ICmUserDao;

@Repository("cmUserDao")
@Transactional
public class CmUserDao extends PublicDao implements ICmUserDao {

	@Override
	@Transactional(noRollbackFor=RuntimeException.class)
	public void saveTbUserPwdAndUpdateTbUser(CmUserPasswordHistory cmUserPasswordHistory, CmUser cmUser) {
		this.save(cmUserPasswordHistory);
		this.update(cmUser);
		
	}

}
