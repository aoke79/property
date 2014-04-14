package com.sinoframe.business;

import java.util.List;

public interface ICmUserPasswordHistoryBS extends IService {
	
	public <T> List<T> findAll();
	
	// 查询最后更改的密码前num次
	public <T> List<T> findByPasswordHistory(int num);
	
}
