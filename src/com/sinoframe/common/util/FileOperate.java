///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
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
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  文件操作类</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft ruanruiwen
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileOperate {
    
    /**
     * 判断文件是否存在
     * 
     * @param strFile  文件名
     * @return 存在返回True,不存在返回False
     */
    public static boolean checkFile(String strFile) {

        // 文件名为null或空时返回false
        if (strFile == null || strFile.length() == 0) {
            return false;
        }
        
        // 构造文件
        File fileCheck = new File(strFile);
        
        // 判断文件是否存在
        if (!fileCheck.exists() 
                || !fileCheck.isFile() 
                || !fileCheck.canRead()
                || fileCheck.length() == 0) {
            return false;
        }
        
        // 文件存在返回
        return true;
    }

    /**
     * 删除指定文件夹下所有文件
     * @param path 文件夹完整绝对路径
     * @return 删除成果返回TRUE 否则返回FALSE
     */
    public boolean delAllFile(String path) {
     boolean bea = false;
        File file = new File(path);
        if (!file.exists()) {
            return bea;
        }
        if (!file.isDirectory()) {
            return bea;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            }else{
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path+"/"+ tempList[i]); //先删除文件夹里面的文件
                delFolder(path+"/"+ tempList[i]);  //再删除空文件夹
                bea = true;
            }
        }
        return bea;
    }
    
    /**
     * 删除指定的某个文件
     * @param path
     * @return
     */
    public boolean delFile(String path) {
        boolean bea = false;
           File file = new File(path);
           if (!file.exists()) {
               return bea;
           }
           bea=file.delete();
           return bea;
       }
    
    
    
    /**
     * 删除文件夹
     * 
     * @param folderPath 文件夹完整绝对路径
     */
    public void delFolder(String folderPath) {
        try {
            //删除完里面所有内容
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            
            //删除空文件夹
            myFilePath.delete(); 
        }
        catch (Exception e) {
            e.printStackTrace();
           // message = ("删除文件夹操作出错");
        }
    }
    
    /**
     * 取得文件名称
     * 
     * @param strSelSrc 文件全路径
     * @return 文件名
     */
    public String getFileName(String strSelSrc) {

        //字符集的长度
        int len;
        
        //存放截取的字符集
        String[] strUrl;
        
        //经过处理的地址
        String strSelUrl;
        
        //根据"/"符号截取
        strUrl = strSelSrc.split("/");        
        len = strUrl.length;        
        strSelUrl = strUrl[len-1];
        
        //返回文件名
        return strSelUrl;
    }
    
    /**
     * 检查目录并是否存在不存在就新建
     * 
     * @param folderPath 目录地址
     * @param 文件路径
     */
    public String checkAndCreateFolder(String folderPath)  {
        String folder = folderPath;
        try {
            File myFilePath = new File(folder);
            folder = folderPath;
            
            //如果文件夹不存在md
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folder;
    }

    /**
     * 取得文件后缀
     * 
     * @param strFileName 文件名称
     * @return 文件后缀
     */
    public String getExtension(String strFileName) {
        // 返回值
        String strResult = "";
        
        // 判断文件名是否为空
        if ((strFileName != null) && (strFileName.length() > 0)) { 
            int i = strFileName.lastIndexOf('.'); 
            
            // 取得文件后缀
            if ((i >-1) && (i < (strFileName.length() - 1))) { 
                strResult = strFileName.substring(i + 1); 
            } 
        }
        
        // 返回文件后缀
        return strResult; 
    }

    public static void copyFile(String oldFile,String newFile){
    	try {
			// 取得需要上传的文件数组
			File file = new File(oldFile);
			// 以服务的文件保存路径和原文件名建立上传文件输出流
			FileOutputStream fos = new FileOutputStream(newFile);
			// 以每个需要上传的文件建立文件文件输入流
			FileInputStream fis = new FileInputStream(file);
			// 将每个需要上传的文件写到服务器对应的文件中
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		FileOperate fileOperate=new FileOperate();
		System.out.println(UUID.randomUUID());
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}