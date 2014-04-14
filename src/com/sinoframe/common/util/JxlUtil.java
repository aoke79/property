package com.sinoframe.common.util;
import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
public class JxlUtil {
	public String fileName = null;
	public String sheetName = null;
	public jxl.Workbook rw = null;
	public jxl.write.WritableWorkbook wwb = null;
	
	
	
	public JxlUtil(String fileName,String sheetName,Boolean b){
		try{
			this.sheetName = sheetName;
			if(this.rw == null){
				// 创建只读的 Excel 工作薄的对象
				this.rw = Workbook.getWorkbook(new File(fileName));
			}
			if(this.wwb == null){
				// 创建可写入的 Excel 工作薄对象
				this.wwb = Workbook.createWorkbook(new File(fileName), rw);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	
	
	
	public JxlUtil(String fileName,String sheetName){
		try{
			this.sheetName = sheetName;
		
			if(this.rw == null){
				// 创建只读的 Excel 工作薄的对象
				this.rw = Workbook.getWorkbook(new File(fileName));
			}
			if(this.wwb == null){
				// 创建可写入的 Excel 工作薄对象
			//	this.wwb = Workbook.createWorkbook(new File(fileName), rw);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setSheetName(String sheetName){
		this.sheetName = sheetName;
	}
	public void writeExcel(int val,int x,int y){
		 try {    
			 // 读取工作表
			 jxl.write.WritableSheet ws = this.wwb.getSheet(this.sheetName);
			 // 获得第一个单元格对象
			 jxl.write.WritableCell wc = ws.getWritableCell(y, x);
			 // 判断单元格的类型 , 做出相应的转化
			 if(wc.getType() == CellType.NUMBER){
				 jxl.write.Number labelN = (jxl.write.Number)wc;    
				 labelN.setValue(val);
//			 	 Label l = (Label)wc;
//			     l.setString("The value has been modified.");
			 }
		 } catch (Exception e) { 
			 e.printStackTrace();
		 }
	}
	public void writeExcel(Float val,int x,int y){
		 try {    
			 // 读取工作表
			 jxl.write.WritableSheet ws = this.wwb.getSheet(this.sheetName);
			 // 获得第一个单元格对象
			 jxl.write.WritableCell wc = ws.getWritableCell(y, x);
			 // 判断单元格的类型 , 做出相应的转化
			// if(wc.getType() == CellType.NUMBER){
				 jxl.write.Number labelN = (jxl.write.Number)wc;    
				 labelN.setValue(val);
//			 	 Label l = (Label)wc;
//			     l.setString("The value has been modified.");
			// }
		 } catch (Exception e) { 
			 e.printStackTrace();
		 }
	}
	
/*	public void writeExcel(Double val,int x,int y){
		 try {    
			 // 读取工作表
			 jxl.write.WritableSheet ws = this.wwb.getSheet(this.sheetName);
			 // 获得第一个单元格对象
			 jxl.write.WritableCell wc = ws.getWritableCell(y, x);
			 // 判断单元格的类型 , 做出相应的转化
			 if(wc.getType() == CellType.NUMBER){
				 jxl.write.Number labelN = (jxl.write.Number)wc;    
				 labelN.setValue(val);
//			 	 Label l = (Label)wc;
//			     l.setString("The value has been modified.");
			 }
		 } catch (Exception e) { 
			 e.printStackTrace();
		 }
	}*/
	
	public void writeExcelString(String val,int x,int y){
		 try {    
			 // 读取工作表
			 jxl.write.WritableSheet ws = this.wwb.getSheet(this.sheetName);
			 // 获得第一个单元格对象
			 jxl.write.WritableCell wc = ws.getWritableCell(x, y);
			 // 判断单元格的类型 , 做出相应的转化
			 if(wc.getType() == CellType.LABEL){
				 jxl.write.Label labelN = (jxl.write.Label)wc;    
				 labelN.setString(val);
//			 	 Label l = (Label)wc;
//			     l.setString("The value has been modified.");
			 }
		 } catch (Exception e) { 
			 e.printStackTrace();
		 }
	}
	
	
	
	
	public void writeExcel(String val,int x,int y){
		 try {   
			//delete();
			 // 读取工作表
			 jxl.write.WritableSheet ws = this.wwb.getSheet(this.sheetName);
			 
			 // 获得第一个单元格对象
		 jxl.write.WritableCell wc = ws.getWritableCell(y, x);
		 Label labelC = new Label(y, x, val);
		 ws.addCell(labelC);
		 } catch (Exception e) { 
			 e.printStackTrace();
		 }
	}
	public void closeJxl(){
		try{
			 // 关闭只读的 Excel 对象
			if(rw!=null){
				rw.close();
			}
			this.rw = null;
		
			System.out.println("读取已完成");
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	public void closeJxlOut(){
		try{
			// 写入 Excel 对象
			
			// 关闭可写入的 Excel 对象
			if(this.wwb!=null){
				this.wwb.write();
				this.wwb.close();
			}

			this.wwb = null;
			
			System.out.println("写出已完成");
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	/**
	 * 读取 参数
	 * @return
	 */
	public  String[] returnParam(){
		try { 
			StringBuffer buf = new StringBuffer();
			Sheet sheet	= this.rw.getSheet(this.sheetName);
			int beg = 0;
			int ove =  sheet.getRows();
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
				for(int i=0 ;i<cell.length;i++){
					buf.append(cell[i].getContents().trim());
					if(i==0)buf.append("-");
					if(i!=0)buf.append(",");
				}
			}
			if(buf.length() != 0){
				buf.deleteCharAt(buf.length()-1);
				return buf.toString().split(",");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件已经损坏");
		}
		
		return new String[]{};
		
	}
	public void delete(){
	     Sheet sheet = 	this.rw.getSheet(this.sheetName);
	     int ic=0; 
	     int ii = sheet.getRows();
	     for(int i = ic ;i<ii;i++){
	    	 Cell[] cell = sheet.getRow(i);
	    	 for(int x = 0 ;x<cell.length;x++){
	    		// this.writeExcelString("", i,x);
	    		 this.writeExcel("", i, x);
	    	 }
	     }
		
	}

}
