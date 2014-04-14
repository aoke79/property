///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2010 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DownLoadFiles</p>
 * <p><b>Description</b>:  文件压缩共通方法</p>
 * <p><b>DATE</b>: 2010/12/27</p>
 * AUTHOR        : 郝大锋
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.common.util;


import java.util.*;
import java.util.zip.*;
import java.io.*;

public class ZipDown {	


/**
   * 压缩文件 公用接口
   * @param zipFileName 保存的压缩包文件路径
   * @param inputFile 需要压缩的文件夹或者文件路径
   * @throws Exception
   */	
	public static void zip(String zipFileName, String inputFile) throws Exception {
	   zip(zipFileName, new File(inputFile));
	}
	
	/**
	 * 压缩文件实现方法
	 * @param zipFileName
	 * @param inputFile
	 * @throws Exception
	 */
	private static void zip(String zipFileName, File inputFile) throws Exception {
	   ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
	   //递归压缩方法
	   zip(out, inputFile, "");
	   out.close();
	}
	
   /**
    * 递归压缩方法
    * @param out   压缩包输出流
    * @param f     需要压缩的文件
    * @param base 压缩的路径
    * @throws Exception
    */
	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
	   if (f.isDirectory()) {   
		   // 如果是文件夹，则获取下面的所有文件
		   File[] fl = f.listFiles();
		   out.putNextEntry(new ZipEntry(base + "/"));
		   base = base.length() == 0 ? "" : base + "/";
		   for (int i = 0; i < fl.length; i++) {
			   zip(out, fl[i], base + fl[i].getName());
		   }
		   // 如果是文件，则压缩
	   } else {   
		   
		   // 生成下一个压缩节点
		   out.putNextEntry(new ZipEntry(base)); 
		   
		   // 读取文件内容
		   FileInputStream in = new FileInputStream(f);		   
		   int b;
		   while ((b = in.read()) != -1)
			   // 写入到压缩包
			   out.write(b);
			   in.close();
	   }
	}

}
 
