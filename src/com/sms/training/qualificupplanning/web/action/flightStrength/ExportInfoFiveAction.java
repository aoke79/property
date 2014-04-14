package com.sms.training.qualificupplanning.web.action.flightStrength;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.sms.training.qualificupplanning.bean.ExportInfo;
//5  //2   FlightStrength
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "exportInfo", location = "/sms/training/dt/fjz/yearBalanceList2015.jsp"),
		@Result(name = "exportInfo2", location = "/sms/training/dt/fjz/yearBalanceList2015f.jsp"),
		@Result(name = "aviatorDemandYearTable", location = "/sms/training/dt/fjz/acAdjust.jsp"),
		@Result(name = "acAdjust", location = "/sms/training/dt/fjz/aviatorDemandYearTable.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class ExportInfoFiveAction extends BaseAction{
	List<ExportInfo> exportInfoList = new ArrayList<ExportInfo>();
	ExportInfo exportInfo = new ExportInfo();
	
	//SHEET 名称
	String sheetName="机队实力表";  //不同的页面名称 就修改下
	//EXCEL路径
	String path = "E:\\output.xls";
	String inputPath = "E:\\input.xls";
	String pageFlag = "year";
	public ExportInfo getExportInfo() {
		return exportInfo;
	}

	public void setExportInfo(ExportInfo exportInfo) {
		this.exportInfo = exportInfo;
	}

	public List<ExportInfo> getExportInfoList() {
		return exportInfoList;
	}

	public void setExportInfoList(List<ExportInfo> exportInfoList) {
		this.exportInfoList = exportInfoList;
	}

	/**
	 * 描述： 机长年计划平衡表
	 * @return
	 */
	public  String exportYearBalanceTable(){
		//得到参数条件============Being
		String[] str = this.getParam();
		String[] dws2 = new String[str.length];
		String[] jxs2 = new String[str.length];
		if(str != null){
            for(int i=0;i<str.length;i++){
            	String[] b = str[i].split("-");
            	if(b.length !=0){
            	dws2[i] = b[0];
            	jxs2[i] = b[1];
            	}
            }
		}
		//得到参数条件============End
		try {
			//this.sheetName = "输出三年计划平衡表";
			this.sheetName="输出2014年计划平衡表";
			Workbook workbook = Workbook.getWorkbook(new File(this.path));
			Sheet sheet = workbook.getSheet(this.sheetName); 
			ExportInfo exportInfo = null;
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 5;   //下个EXCEL要替换
			//结束行
			int ove = 185;////下个EXCEL要替换
			String orgName = "";
			String month = "";
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
			    exportInfo = new ExportInfo();
					if(!cell[1].getContents().equals("")){
						orgName = cell[1].getContents();
					}
					if(!cell[2].getContents().equals("")){
						month = cell[2].getContents();
					}
					if(dws2.length>0 && dws2[0] != null){
			    		for(int i= 0;i<dws2.length;i++ ){
				    		if(cell[1].getContents() != null && !cell[1].getContents().equals("")){
								orgName = cell[1].getContents();
							}
				    		if(!cell[2].getContents().equals("")){
								month = cell[2].getContents();
							}
				    		if(dws2[i] == null || jxs2[i]== null ){
				    			continue;
				    		}
				    		if(dws2[i].equals(orgName)&& jxs2[i].equals(cell[3].getContents())){
								exportInfo.setField0(orgName);
								exportInfo.setField1(month);
								exportInfo.setField2(cell[3].getContents());
								exportInfo.setField3(cell[4].getContents());
								exportInfo.setField4(cell[5].getContents());
								exportInfo.setField5(cell[6].getContents());
								exportInfo.setField6(cell[7].getContents());
								exportInfo.setField7(cell[8].getContents());
								exportInfo.setField8(cell[9].getContents());
								exportInfo.setField9(cell[10].getContents());
								exportInfo.setField10(cell[11].getContents());
								exportInfo.setField11(cell[12].getContents());
								exportInfo.setField12(cell[13].getContents());
								exportInfo.setField13(cell[14].getContents());
								exportInfo.setField14(cell[15].getContents());
								exportInfo.setField15(cell[16].getContents());
								exportInfoList.add(exportInfo);
				    		}
			    		}
					}else{
						exportInfo.setField0(orgName);
						exportInfo.setField1(month);
						exportInfo.setField2(cell[3].getContents());
						exportInfo.setField3(cell[4].getContents());
						exportInfo.setField4(cell[5].getContents());
						exportInfo.setField5(cell[6].getContents());
						exportInfo.setField6(cell[7].getContents());
						exportInfo.setField7(cell[8].getContents());
						exportInfo.setField8(cell[9].getContents());
						exportInfo.setField9(cell[10].getContents());
						exportInfo.setField10(cell[11].getContents());
						exportInfo.setField11(cell[12].getContents());
						exportInfo.setField12(cell[13].getContents());
						exportInfo.setField13(cell[14].getContents());
						exportInfo.setField14(cell[15].getContents());
						exportInfo.setField15(cell[16].getContents());
						exportInfoList.add(exportInfo);
					}
			}
			
/*			for(FlightStrength f : flightStrengthList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
			
		} catch (BiffException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏，或者不存在， 无法正常读取");
			
			 e.printStackTrace();
			 return "ajax";
			// TODO Auto-generated catch block
			
		}catch (IOException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "ajax";
		}
		
		return "exportInfo";
	}
	/**
	 * 描述： 副驾驶年计划平衡表
	 * @return
	 */
	public  String copilotYearBalanceTable(){
		//得到参数条件============Being
		String[] str = this.getParam();
		String[] dws2 = new String[str.length];
		String[] jxs2 = new String[str.length];
		if(str != null){
            for(int i=0;i<str.length;i++){
            	String[] b = str[i].split("-");
            	if(b.length !=0){
            	dws2[i] = b[0];
            	jxs2[i] = b[1];
            	}
            }
		}
		//得到参数条件============End
		
		try {
		//	this.sheetName = "输出三年计划平衡表";
			this.sheetName="输出2014年计划平衡表";
			Workbook workbook = Workbook.getWorkbook(new File(this.path));
			Sheet sheet = workbook.getSheet(this.sheetName); 
			ExportInfo exportInfo = null;
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 190;   //下个EXCEL要替换
			//结束行
			int ove = 370;////下个EXCEL要替换
			String orgName = "";
			String month = "";
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
			    exportInfo = new ExportInfo();
					if(!cell[1].getContents().equals("")){
						orgName = cell[1].getContents();
					}
					if(!cell[2].getContents().equals("")){
						month = cell[2].getContents();
					}
					
					if(dws2.length>0 && dws2[0] != null){
			    		for(int i= 0;i<dws2.length;i++ ){
				    		if(cell[1].getContents() != null && !cell[1].getContents().equals("")){
								orgName = cell[1].getContents();
							}
				    		if(!cell[2].getContents().equals("")){
								month = cell[2].getContents();
							}
				    		if(dws2[i] == null || jxs2[i]== null ){
				    			continue;
				    		}
				    		if(dws2[i].equals(orgName)&& jxs2[i].equals(cell[3].getContents())){
								exportInfo.setField0(orgName);
								exportInfo.setField1(month);
								exportInfo.setField2(cell[3].getContents());
								exportInfo.setField3(cell[4].getContents());
								exportInfo.setField4(cell[5].getContents());
								exportInfo.setField5(cell[6].getContents());
								exportInfo.setField6(cell[7].getContents());
								exportInfo.setField7(cell[8].getContents());
								exportInfo.setField8(cell[9].getContents());
								exportInfo.setField9(cell[10].getContents());
								exportInfo.setField10(cell[11].getContents());
								exportInfo.setField11(cell[12].getContents());
								exportInfo.setField12(cell[13].getContents());
								exportInfo.setField13(cell[14].getContents());
								exportInfo.setField14(cell[15].getContents());
								exportInfo.setField15(cell[16].getContents());
								exportInfoList.add(exportInfo);
				    		}
			    		}
					}else{
						exportInfo.setField0(orgName);
						exportInfo.setField1(month);
						exportInfo.setField2(cell[3].getContents());
						exportInfo.setField3(cell[4].getContents());
						exportInfo.setField4(cell[5].getContents());
						exportInfo.setField5(cell[6].getContents());
						exportInfo.setField6(cell[7].getContents());
						exportInfo.setField7(cell[8].getContents());
						exportInfo.setField8(cell[9].getContents());
						exportInfo.setField9(cell[10].getContents());
						exportInfo.setField10(cell[11].getContents());
						exportInfo.setField11(cell[12].getContents());
						exportInfo.setField12(cell[13].getContents());
						exportInfo.setField13(cell[14].getContents());
						exportInfo.setField14(cell[15].getContents());
						exportInfo.setField15(cell[16].getContents());
						exportInfoList.add(exportInfo);
					}
			}
			
/*			for(FlightStrength f : flightStrengthList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
			
		} catch (BiffException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏，或者不存在， 无法正常读取");
			
			 e.printStackTrace();
			 return "ajax";
			// TODO Auto-generated catch block
			
		}catch (IOException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "ajax";
		}
		
		return "exportInfo2";
	}
	public String[] getParam(){
		StringBuffer buf = new StringBuffer();
		JxlUtil jxl =  new JxlUtil("E:\\e\\参数.xls","参数");
		try{
		
			Sheet sheet = jxl.rw.getSheet("参数"); 
			int beg = 0;
			int ove =  sheet.getRows();
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
				for(int i=0 ;i<cell.length;i++){
					buf.append(cell[i].getContents());
					if(i==0)buf.append("-");
					if(i!=0)buf.append(",");
				}
			}
			if(buf.length() != 0){
				buf.deleteCharAt(buf.length()-1);
				return buf.toString().split(",");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new String[0];
	}
	/**
	 * 描述： 将EXCLE的数据进行更新
	 * @return
	 */
	public  String acAdjust(){
		//得到参数条件============Being
		String[] str = this.getParam();
		String[] dws2 = new String[str.length];
		String[] jxs2 = new String[str.length];
		if(str != null){
            for(int i=0;i<str.length;i++){
            	String[] b = str[i].split("-");
            	dws2[i] = b[0];
            	jxs2[i] = b[1];
            }
		}
		//得到参数条件============End
		try {
			this.sheetName = "输出二机长调整建议表";
			Workbook workbook = Workbook.getWorkbook(new File(this.path));
			Sheet sheet = workbook.getSheet(this.sheetName); 
			ExportInfo exportInfo = null;
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 4;   //下个EXCEL要替换
			//结束行
			int ove = 125;////下个EXCEL要替换
			String orgName = "";
			String month = "";
			int count = 0;
			HashMap map = new HashMap();
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
			    exportInfo = new ExportInfo();
			    if(cell.length > 0){
			    	if(dws2.length>0){
			    		for(int i= 0;i<dws2.length;i++ ){
				    		if(cell[1].getContents() != null && !cell[1].getContents().equals("")){
								orgName = cell[1].getContents();
							}
				    		if(dws2[i].equals(orgName)&& jxs2[i].equals(cell[3].getContents())){
				    			
								exportInfo.setField0(orgName);
								exportInfo.setField1(cell[2].getContents());
								exportInfo.setField2(cell[3].getContents());
								exportInfo.setField3(cell[4].getContents());
								exportInfo.setField4(cell[5].getContents());
								exportInfo.setField5(cell[6].getContents());
								exportInfoList.add(exportInfo);
								break;
				    		}
				    	}
			    	}else{
			    		if(cell[1].getContents() != null && !cell[1].getContents().equals("")){
							orgName = cell[1].getContents();
						}
			    		exportInfo.setField0(orgName);
						exportInfo.setField1(cell[2].getContents());
						exportInfo.setField2(cell[3].getContents());
						exportInfo.setField3(cell[4].getContents());
						exportInfo.setField4(cell[5].getContents());
						exportInfo.setField5(cell[6].getContents());
						exportInfoList.add(exportInfo);
			    	}
			    	
					
			    }
			}
			
/*			for(FlightStrength f : flightStrengthList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
			
		} catch (BiffException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏，或者不存在， 无法正常读取");
			
			 e.printStackTrace();
			 return "ajax";
			// TODO Auto-generated catch block
			
		}catch (IOException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "ajax";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "acAdjust";
	}
	
	
	/**
	 * 描述： 飞行员需求年度月度表
	 * @return
	 */
	public  String aviatorDemandYearMonthTable(){
		//得到参数条件============Being
		String[] str = this.getParam();
		String[] dws2 = new String[str.length];
		String[] jxs2 = new String[str.length];
		if(str != null){
            for(int i=0;i<str.length;i++){
            	String[] b = str[i].split("-");
            	dws2[i] = b[0];
            	jxs2[i] = b[1];
            }
		}
		//得到参数条件============End
		
		try {
			//第几行开始
			int beg = 3;   //下个EXCEL要替换
			//结束行
			int ove = 54;////下个EXCEL要替换
			this.sheetName = "输出飞行员需求年度表";
			if(pageFlag.equals("month")){
				beg = 3;
				ove = 207;
				this.sheetName = "输出飞行员需求月度表";
			}
			Workbook workbook = Workbook.getWorkbook(new File(this.path));
			Sheet sheet = workbook.getSheet(this.sheetName); 
			ExportInfo exportInfo = null;
			//int rowsCount = sheet.getRows();
			
			String orgName = "";
			String month = "";
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
			    exportInfo = new ExportInfo();
					if(!cell[1].getContents().equals("")){
						orgName = cell[1].getContents();
					}
					if(!cell[2].getContents().equals("")){
						month = cell[2].getContents();
					}
					
					if(dws2.length>0){
			    		for(int i= 0;i<dws2.length;i++ ){
				    		if(cell[1].getContents() != null && !cell[1].getContents().equals("")){
								orgName = cell[1].getContents();
							}
				    		if(dws2[i].equals(orgName)&& jxs2[i].equals(cell[3].getContents())){
								exportInfo.setField0(orgName);
								exportInfo.setField1(cell[2].getContents());
								exportInfo.setField2(cell[3].getContents());
								exportInfo.setField3(cell[4].getContents());
								exportInfo.setField4(cell[5].getContents());
								exportInfo.setField5(cell[6].getContents());
								exportInfo.setField6(cell[7].getContents());
								exportInfo.setField7(cell[8].getContents());
								exportInfoList.add(exportInfo);
				    		}
			    		}
					}else{
						exportInfo.setField0(orgName);
						exportInfo.setField1(month);
						exportInfo.setField2(cell[3].getContents());
						exportInfo.setField3(cell[4].getContents());
						exportInfo.setField4(cell[5].getContents());
						exportInfo.setField5(cell[6].getContents());
						exportInfo.setField6(cell[7].getContents());
						exportInfo.setField7(cell[8].getContents());
						exportInfoList.add(exportInfo);
					}
			}
			
/*			for(FlightStrength f : flightStrengthList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
			
		} catch (BiffException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏，或者不存在， 无法正常读取");
			
			 e.printStackTrace();
			 return "ajax";
			// TODO Auto-generated catch block
			
		}catch (IOException e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "ajax";
		}
		
		return "aviatorDemandYearTable";
	}
	
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	private Message message;
	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	
}
