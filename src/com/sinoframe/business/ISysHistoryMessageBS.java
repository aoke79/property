/**
 * @Title IHistoryMessageBS
 * @Description the service layer interface of "HistoryMessage"
 */

package com.sinoframe.business;

import com.sinoframe.common.exception.RollbackableBizException;


public interface ISysHistoryMessageBS extends IService {
	
	/**
	 * 根据固定时间点进行批量添加
	 * @throws RollbackableBizException
	 */
	public void multipleInsert() throws RollbackableBizException;
}
