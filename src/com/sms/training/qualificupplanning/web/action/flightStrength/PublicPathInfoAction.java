package com.sms.training.qualificupplanning.web.action.flightStrength;
import java.io.File;
import java.io.IOException;
import java.util.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.JxlUtil;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificupplanning.bean.FlightStrength;
import com.sms.training.qualificupplanning.bean.PublicPathInfo;

//5  //2   FlightStrength
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "flightStrength", location = "/sms/training/dt/fjz/parameterSettings.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="toPersonalInfoManag",type="redirectAction",location="standard.do"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述：用于存放公共的路径
 * @author LUJIE		
 */
public class PublicPathInfoAction extends BaseAction{
	//卢杰本地路径
	private String lingoExePath = "D:\\LINGO11\\Lingo11.exe";
	private String excelExePath = "cmd /c start ";
	public  String excelFilePath = "e:\\input.xls";
	public String  excelFileOut ="e:\\output.xls";

	private Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String returnPath(){

		
	    StringBuffer dw = new StringBuffer();
	   
		try {
	    	JxlUtil jxl =  new JxlUtil("E:\\input.xls","参数");

				String[] str = jxl.returnParam();
				if(str != null){
                    for(int i=0;i<str.length;i++){
                    	String[] b = str[i].split("-");
                    	if(b.length !=0){
                        	dw.append(b[0]).append(",");
                    	}

                    }
                    if(dw.length() !=0)dw.deleteCharAt(dw.length()-1);
                    String[] dws = dw.toString().split(",");
                    String dwsSave = "";
                    String oter = "e:\\其他公司.lg4";
                    String oters = "e:\\其他上.lg4";
                    String oterx = "e:\\其他下.lg4";
               
                    if( dw.toString().length() ==0){
                		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\西南.lg4");
                		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\西南上.lg4");
                		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\西南下.lg4");
                		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\总队.lg4");
                		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\总队上.lg4");
                		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\总队下.lg4");
                		Runtime.getRuntime().exec(this.excelExePath +"  "+oter);
                		Runtime.getRuntime().exec(this.excelExePath +"  "+oters);
                		Runtime.getRuntime().exec(this.excelExePath +"  "+ oterx);
                    }else{
	                    for(int i=0;i<dws.length;i++){
	                    	if(dwsSave.indexOf(dws[i].trim()) == -1){
	                        	if("西南".equals(dws[i].trim())){
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\西南.lg4");
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\西南上.lg4");
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\西南下.lg4");
	                        		dwsSave += dws[i].trim();
	                        	}
	                        	if("总队".equals(dws[i].trim())){
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\总队.lg4");
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\总队上.lg4");
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+"e:\\总队下.lg4");
	                        		dwsSave += dws[i].trim();
	                        	}
	                        	
	                        	if(!"总队".equals(dws[i]) && !"西南".equals(dws[i])){
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+oter);
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+oters);
	                        		Runtime.getRuntime().exec(this.excelExePath +"  "+ oterx);
	                        		dwsSave += dws[i].trim();
	                        		dwsSave += "天津,上海,重庆湖北浙江";
	                        	}

	                    	}
	
	                    }
                    }
			}
			
			
	
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2013年飞机退出计划.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2014年飞机退出计划.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2015年飞机退出计划.xls");
			
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2013年飞机引进计划.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2014年飞机引进计划.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2015年飞机引进计划.xls");
			
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\2013月度退役情况.xls");
			
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\飞机总计划.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\副驾驶半年累计飞行经历时间平均增加量.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\各等级副驾驶升级通过率.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\公司新雇员预测.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\规划期前调整.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\机队实力表.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\机长副驾驶人数比.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\年度退役情况.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\人机比.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\升级资格要求.xls");
			
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\新雇员分配情况.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\新雇员改装训练完成时间分布.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\e\\重型机转入大型机副驾驶.xls");
			Runtime.getRuntime().exec(this.excelExePath+ "  " + "e:\\output.xls");
			
			
		} catch (Exception e) {
			this.message = this.getFailMessage("运行失败，文件不正确，或者路径不正确！");
			
			e.printStackTrace();
		
		}
		System.out.println("lingo文件路径是： "+this.lingoExePath);
		System.out.println("Excel文件路径是： "+this.excelExePath+"  "+this.excelFilePath);
		System.out.println("Excel文件路径是： "+this.excelExePath+"  "+this.excelFileOut);
		return "ajax";
	}
	
	public String getExcelFileOut() {
		return excelFileOut;
	}

	public void setExcelFileOut(String excelFileOut) {
		this.excelFileOut = excelFileOut;
	}

	public String getLingoExePath() {
		return lingoExePath;
	}

	public void setLingoExePath(String lingoExePath) {
		this.lingoExePath = lingoExePath;
	}

	public String getExcelExePath() {
		return excelExePath;
	}

	public void setExcelExePath(String excelExePath) {
		this.excelExePath = excelExePath;
	}

	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	
}
