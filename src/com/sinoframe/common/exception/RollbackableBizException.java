/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 文件名：RollbackableBizException.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2008-12-06
  * 功能描述: 数据库操作异常类
  */
package com.sinoframe.common.exception;

import java.io.Serializable;

public class RollbackableBizException extends BizException implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public RollbackableBizException() {
		super();		
	}
	
	/**
	 * @param message
	 */
	public RollbackableBizException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public RollbackableBizException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	/**
	 * @param cause
	 */
	public RollbackableBizException(Throwable cause) {
		super(cause);		
	}
}
