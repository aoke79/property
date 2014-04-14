/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * ��Ŀ���ƣ����˲������
  * �ļ�����RollbackableBizException.java
  * ���ߣ�@�´��
  * ��˾���ƣ�hao.com
  * ����ʱ�䣺2008-12-06
  * ��������: ���ݿ�����쳣��
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
