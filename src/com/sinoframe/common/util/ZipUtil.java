package com.sinoframe.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.tools.zip.ZipOutputStream;

import com.sinoframe.common.ConstantList;

public class ZipUtil {
	public String createZip(String path,String fileName,String xxdm){

		  String zipFileName = xxdm + ".zip";

		   byte[] buf = new byte[1024];

		   try {

		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path + zipFileName));

		    FileInputStream in = new FileInputStream(path + fileName);

		    out.putNextEntry(new org.apache.tools.zip.ZipEntry(fileName));

		    int len;

		    while ((len = in.read(buf)) > 0) 

		    {

		     out.write(buf, 0, len);

		    }

		    out.closeEntry();

		    in.close();

		    out.close();

		     }catch (IOException e) 

		     {

		      e.printStackTrace();

		     }  

		  return zipFileName;

		 } 
	public void zip(String inputFileName,String path) throws Exception {
//        String zipFileName = path+"\\"+ConstantList.PHOTOPATHZIP; //打包后文件名字
//        System.out.println(zipFileName);
//        zip(zipFileName, new File(inputFileName));
    }

    private void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        
        zip(out, inputFile, "");
        System.out.println("zip done");
        out.close();
    }

    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
           File[] fl = f.listFiles();
           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
           base = base.length() == 0 ? "" : base + "/";
           for (int i = 0; i < fl.length; i++) {
           zip(out, fl[i], base + fl[i].getName());
         }
        }else {
           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
           FileInputStream in = new FileInputStream(f);
           int b;
           System.out.println(base);
           while ( (b = in.read()) != -1) {
            out.write(b);
         }
         in.close();
       }
    }

    public static void main(String [] temp){
//    	ZipUtil book = new ZipUtil();
//        try {
//           book.zip("D:\\考生照片");//你要压缩的文件夹
//        }catch (Exception ex) {
//           ex.printStackTrace();
//       }
    }
}
