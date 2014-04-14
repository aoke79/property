package com.sinoframe.common.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mockejb.jndi.java.javaURLContextFactory;

import com.sinoframe.bean.CmPeople;
//import com.sms.training.english.bean.EnExamQualification;

import jxl.*;

public class DaoExcel {
	static String createTableSql = "";// 创建数据库的sql
	static String colType = "TEXT";// 字段类型
	static String key = "id";// 主键
	static String charSet = "utf8";// 表格字符类型
	static String ENGINE = "InnoDB";// 表格类型
	static String tableName = "tempExcelToMysql";// 表名称
	static String colName = "col";// 默认字段名
	static Connection conn = null;
   public  static List list1=new ArrayList();
   public  static List list2=new ArrayList();
	public static void copyToObject(){
		
		try {
		    CmPeople cmPeople;
//		    EnExamQualification enExamQualification;
//            
		    
			System.out.println("start load file-------------------------");
			InputStream is = new FileInputStream("D:/bb.xls");// 创建输入

			jxl.Workbook rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(1); // 读取第一个sheet
			int colNum = rs.getColumns();// 列数
			int rowNum = rs.getRows();// 行数
		    java.text.SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		    java.text.SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-d");
			System.out.println(colNum);
			System.out.println(rowNum);
            String strValue1 = "";
            String strValue2 = "";
			for (int i = 1; i < rowNum; i++) {
				cmPeople=new CmPeople();
//				enExamQualification=new EnExamQualification();
					Cell c1 = rs.getCell(0, i);
					Cell c2 = rs.getCell(1, i);
					strValue1 = c1.getContents();
					strValue2 = c2.getContents();
					list1.add(strValue1);
					list2.add(strValue2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		copyToObject();
		for(int i=0;i<list1.size();i++){
			System.out.println(list1.get(i)+"          "+list2.get(i));
		}
	}
	
	
	
}