/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 文件名：BizException.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2008-12-06
  * 功能描述: 异常处理类
  */

package com.sinoframe.common.exception;

import java.io.Serializable;

public class BizException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public BizException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public BizException(String message) {
		super(message);		
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public BizException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param cause
	 */
	public BizException(Throwable cause) {
		super(cause);		
	}
	
}