/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 文件名：functionTools.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2008-12-27
  * 功能描述: 工具函数类
  * 版   本: V 1.0
  */

package com.sinoframe.common.tools;

import java.security.MessageDigest;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class FunctionTools {
	
    /**
     * 密码加密
     * @param str
     * @return
     */
    public String EncoderByMd5(String str) {
    	
	     try{
	     //此 MessageDigest 类为应用程序提供信息摘要算法的功能，必须用try,catch捕获
	     MessageDigest md5=MessageDigest.getInstance("MD5");
	     
	     //64位编码，归属于sun.misc.BASE64Encoder这个包
	     BASE64Encoder base64encoder=new BASE64Encoder();
	     
	     str=base64encoder.encode(md5.digest(str.getBytes("utf-8")));//转换为MD5码
	     
		 } catch(Exception ex)
		     {
		     ex.printStackTrace();
		     }
	     return str;
    }
    
    /**
     * 替换文本内容
     * 
     * @param strContext	待替换文本
     * @param param			替换参数
     * @return				替换后结果
     */
    public String replaceParam(String strContext,String[] param){

    	//返回值
    	String strResult = strContext;
    	if (param.length < 0) return strResult; 
    	
    	//替换文本
    	for (int i = 0;i < param.length;i++) {
    		strResult = strResult.replace("{" + i + "}", param[i]);
    	}
    	
    	//返回值
    	return strResult;
    }
	
    /**
     * 随机产生定长字符
     * 
     * @param size
     * @return
     */
    public String getRandomString(int size) {// 随机字符串
        char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q',
                'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
                'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        Random random = new Random(); // 初始化随机数产生器
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[Math.abs(random.nextInt()) % c.length]);
        }
        return sb.toString();
    }
}