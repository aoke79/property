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
import com.sms.training.qualificupplanning.bean.CompanyNewEmploy;
import com.sms.training.qualificupplanning.bean.NewEmployeeTime;
import com.sms.training.qualificupplanning.bean.PlanAdjust;
import com.sms.training.qualificupplanning.bean.TotlePlan;

//5  //2   TotlePlan
@ParentPackage("sinoframe-default")
@Results( {
	@Result(name="ajax",location="/standard/ajaxDone.jsp"),
	@Result(name = "TotlePlan", location = "/sms/training/dt/fjz/totleThreeXlsList.jsp"),
	@Result(name = "json", type = "json"),
	@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
	@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class TotleThreeXlsListAction extends BaseAction{
	List<CompanyNewEmploy> companyNewEmployList = new ArrayList<CompanyNewEmploy>();
	List<NewEmployeeTime> newEmployeeTimeList = new ArrayList<NewEmployeeTime>();
	List<PlanAdjust> planAdjustList = new ArrayList<PlanAdjust>();
	
	private String fowd;

	CompanyNewEmploy companyNewEmploy = new CompanyNewEmploy();
	NewEmployeeTime newEmployeeTime = new NewEmployeeTime();
	PlanAdjust planAdjust = new PlanAdjust();
	String strc = "";
	//SHEET 名称
	public String companyXls="公司新雇员预测";
	public String newXls="新雇员改装训练完成时间分布";
	public String planXls="规划期前调整";
	//EXCEL路径
	public String path = "E:\\input.xls";




	/**
	 * 描述： 将EXCLE的数据进行更新
	 * @return
	 */
	public  String flightStrengthOut(){
		try {
			companyNewEmployList = this.companyNewEmploySave();
			newEmployeeTimeList = this.newEmployeeTimeSave();
			planAdjustList = this.planAdjustSave();
		         
		} catch (Exception e) {
			this.message = this.getFailMessage("修改数据只能输入整数!");
			e.printStackTrace();
		}
		
		return "ajax";
	}
	
	/**
	 * companyNewEmploySave
	 * @return
	 */
	public List<CompanyNewEmploy> companyNewEmploySave(){
		try {
		if(companyNewEmploy != null){
			 String[] yea =companyNewEmploy.getYear().split(",");
			// String[] persons =companyNewEmploy.getPersons().split(",");
			 String[] zn = companyNewEmploy.getZuoNuber().split(",");
			 for(int i=0;i<yea.length;i++){
				 
				 CompanyNewEmploy companyNewEmploys = new CompanyNewEmploy(); 
				 companyNewEmploys.setZuoNuber(zn[i].trim());
				 companyNewEmploys.setYear(yea[i].trim());
				 //companyNewEmploys.setPersons(this.persons.split(",")[i].trim()); 
			     this.companyNewEmployList.add(companyNewEmploys);
			 }
			
		}
				
				Workbook workbook = Workbook.getWorkbook(new File(this.path));
				Sheet sheet = workbook.getSheet(this.companyXls);
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.companyXls);
			for(int i=0 ;i<companyNewEmployList.size();i++){
				
				CompanyNewEmploy fs =   companyNewEmployList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				
				jxlUtil.writeExcel(companyNewEmployList.get(i).getYear(),hang,4);
				jxlUtil.writeExcel(companyNewEmployList.get(i).getPersons(),hang,5);
				//jxlUtil.writeExcel(totlePlanList.get(i).getRightCount(),hang,12);
			}
				jxlUtil.closeJxl();
			//flight-strength/flight-strength!TotlePlan.do  "tbuser", "forward"
				//this.message = this.getSuccessMessage("保存成功,Excel文件导出成功！", "", "", "");
				//Runtime.getRuntime().exec("D:\\Program Files\\Tencent\\QQ\\Bin\\QQProtect\\Bin\\QQProtect.exe");
		         
		} catch (Exception e) {
			//this.message = this.getFailMessage("修改数据只能输入整数!");
			e.printStackTrace();
		}
		
		return companyNewEmployList;
	}

	public List<NewEmployeeTime> newEmployeeTimeSave(){
		try {
		if(newEmployeeTime != null){
			// String[] str =newEmployeeTime.getDw().split(",");
			// String[] jxc =newEmployeeTime.getJx().split(",");
			 String[] yea =newEmployeeTime.getYear().split(",");
			 String[] zn = newEmployeeTime.getZuoNuber().split(",");
			 for(int i=0;i<zn.length;i++){
				 
				 NewEmployeeTime newEmployeeTime = new NewEmployeeTime(); 
				 newEmployeeTime.setZuoNuber(zn[i].trim());
				 newEmployeeTime.setYear(yea[i].trim());
				 //newEmployeeTime.setPercent(this.percent.split(",")[i].trim()); 
			     this.newEmployeeTimeList.add(newEmployeeTime);
			 }
			
		}
				
				Workbook workbook = Workbook.getWorkbook(new File(this.path));
				Sheet sheet = workbook.getSheet(this.newXls);
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.newXls);
			for(int i=0 ;i<newEmployeeTimeList.size();i++){
				
				NewEmployeeTime fs =   newEmployeeTimeList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				jxlUtil.writeExcel(newEmployeeTimeList.get(i).getYear(),hang,4);
				jxlUtil.writeExcel(newEmployeeTimeList.get(i).getPercent(),hang,5);
			}
				jxlUtil.closeJxl();
			//flight-strength/flight-strength!TotlePlan.do  "tbuser", "forward"
				//this.message = this.getSuccessMessage("保存成功,Excel文件导出成功！", "", "", "");
				//Runtime.getRuntime().exec("D:\\Program Files\\Tencent\\QQ\\Bin\\QQProtect\\Bin\\QQProtect.exe");
		         
		} catch (Exception e) {
			//this.message = this.getFailMessage("修改数据只能输入整数!");
			e.printStackTrace();
		}
		
		return newEmployeeTimeList;
	}
	
	
	public List<PlanAdjust> planAdjustSave(){
		try {
		if(planAdjust != null){
			 String[] str =planAdjust.getDw().split(",");
			 String[] jxc =planAdjust.getJx().split(",");
			 String[] zn = planAdjust.getZuoNuber().split(",");
			 for(int i=0;i<str.length;i++){
				 
				 PlanAdjust planAdjust = new PlanAdjust(); 
				 planAdjust.setZuoNuber(zn[i].trim());
				 planAdjust.setDw(str[i].trim().trim());
				// TotlePlans.setButtonCoutn(Integer.valueOf(this.buttonCoutn.split(",")[i].trim()));
				 planAdjust.setJx(jxc[i].trim());
				 planAdjust.setPersons(Integer.valueOf(this.persons.split(",")[i].trim()));
			     this.planAdjustList.add(planAdjust);
			 }
			
		}
				Workbook workbook = Workbook.getWorkbook(new File(this.path));
				Sheet sheet = workbook.getSheet(this.planXls);
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.planXls);
			for(int i=0 ;i<planAdjustList.size();i++){
				
				PlanAdjust fs =   planAdjustList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				jxlUtil.writeExcel(planAdjustList.get(i).getPersons(),hang,6);
				//jxlUtil.writeExcel(PlanAdjustList.get(i).getRightCount(),hang,12);
			}
				jxlUtil.closeJxl();
		         
		} catch (Exception e) {
			//this.message = this.getFailMessage("修改数据只能输入整数!");
			e.printStackTrace();
		}
		
		return planAdjustList;
	}
	
	
	
	
	
	
	
	
	
	//输入-现阶段飞行实力 写入 
	public String flightStrength(){
		//JxlUtil jxl =  new JxlUtil(this.path,"参数");
		try {
			 companyNewEmployList = companyNewEmployList();
			 newEmployeeTimeList = newEmployeeTimeList(); //totle-three-xls-list flight-strength/totle-three-xls-list!flightStrength.do
			 planAdjustList = planAdjustList();
		} catch (Exception e) {
			this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ajax";
		}finally{
			//jxl.closeJxl();
		}
		return "TotlePlan";
	}
	
	
	/**
	 * 公司新雇员预测
	 * @return list
	 */
	public List<CompanyNewEmploy> companyNewEmployList(){
    	JxlUtil jxl =  new JxlUtil(this.path,"参数");
		try {
			
		
			StringBuffer dw = new StringBuffer();
		    StringBuffer jx = new StringBuffer();
			String[] str = jxl.returnParam();
			if(str != null){
                    for(int i=0;i<str.length;i++){
                    	String[] b = str[i].split("-");
                    	if(b.length !=0){
                        	dw.append(b[0]).append(",");
                        	jx.append(b[1]).append(",");
                    	}

                    }
                    if(dw.length() !=0 ){
                    	dw.deleteCharAt(dw.length()-1);
                    	jx.deleteCharAt(jx.length()-1);
                    	this.dws2 = dw.toString();
                    	this.jxs2 = jx.toString();
                    }
			}
			
			Sheet sheet = jxl.rw.getSheet(this.companyXls);
			System.out.println(sheet.getName());
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 5;   //下个EXCEL要替换
			//结束行
			int ove = 9;////下个EXCEL要替换
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
				for(int i=0;i<cell.length ;i++){
					if(cell[i].getContents().trim().length() !=0){
						buf.append(cell[i].getContents()).append(",");
					}
				}
				if(buf.length()!=0)buf.deleteCharAt(buf.length()-1);
				//System.out.println("length==="+buf.toString().split(",").length);
				if(buf.toString().split(",").length==3){ //下个EXCEL要替换 12
					CompanyNewEmploy f = new CompanyNewEmploy();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					//System.out.println("length=="+cc.length);
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换   Integer.valueOf(cc[0+b]).intValue()
						f.setZuoNuber(cc[0]);
						f.setYear(cc[0+b]);
						//f.setPersons(cc[1+b]);
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
					companyNewEmployList.add(f);

					    }
				}
				
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<CompanyNewEmploy> nullInfo = new ArrayList<CompanyNewEmploy>();
			// totlePlanList = nullInfo;
		 }	
		} catch (Exception e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "ajax";
		}finally{
			jxl.closeJxl();
		}
		//System.out.println("companyNewEmployList.size()="+companyNewEmployList.size());
    	return companyNewEmployList;
    }
	
	/**
	 * newXls="新雇员改装训练完成时间分布";
	 */
	public List<NewEmployeeTime> newEmployeeTimeList(){
    	JxlUtil jxl =  new JxlUtil(this.path,"参数");
		try {
			
		
			StringBuffer dw = new StringBuffer();
		    StringBuffer jx = new StringBuffer();
			String[] str = jxl.returnParam();
			if(str != null){
                    for(int i=0;i<str.length;i++){
                    	String[] b = str[i].split("-");
                    	if(b.length !=0){
                        	dw.append(b[0]).append(",");
                        	jx.append(b[1]).append(",");
                    	}

                    }
                    if(dw.length() !=0 ){
                    	dw.deleteCharAt(dw.length()-1);
                    	jx.deleteCharAt(jx.length()-1);
                    	this.dws2 = dw.toString();
                    	this.jxs2 = jx.toString();
                    }
			}
			
			Sheet sheet = jxl.rw.getSheet(this.newXls);
			System.out.println(sheet.getName());
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 5;   //下个EXCEL要替换
			//结束行
			int ove = 7;////下个EXCEL要替换
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
				for(int i=0;i<cell.length ;i++){
					if(cell[i].getContents().trim().length() !=0 && !cell[i].getContents().trim().equals("1减下半年")){
						buf.append(cell[i].getContents()).append(",");
					}
				}
				if(buf.length()!=0)buf.deleteCharAt(buf.length()-1);
				//System.out.println("length==="+buf.toString().split(",").length);
				if(buf.toString().split(",").length==3){ //下个EXCEL要替换 12
					NewEmployeeTime f = new NewEmployeeTime();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换
						f.setZuoNuber(cc[0]);
						//f.setButtonCoutn(Integer.valueOf(cc[2+b]).intValue());
						//f.setJz(Integer.valueOf(cc[3+b]).intValue());
						//f.setJxjz(Integer.valueOf(cc[4+b]).intValue());
						f.setYear(cc[0+b]);
						//f.setPercent(cc[1+b]);
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
						newEmployeeTimeList.add(f);
					    List<NewEmployeeTime> listNew = new ArrayList<NewEmployeeTime>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
				    			for(NewEmployeeTime fs : newEmployeeTimeList){
				    				for(int i=0 ;i<dwsArray.length;i++){
				    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
				    						if(fs.getJx().trim().equals(jxsArray[i].trim())){
				    							listNew.add(fs);
				    						}
				    					}
				    				}
				    			}
					    	    
					    	}
					    		//只选择了单位
					    		if((this.dws2 != null && this.dws2.length() !=0) && (this.jxs2 == null || this.jxs2.length()==0)){
					    			String[] dwsArray = this.dws2.split(",");
					    			
					    			for(NewEmployeeTime fs : newEmployeeTimeList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    }

					  
				}
				
			}
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<NewEmployeeTime> nullInfo = new ArrayList<NewEmployeeTime>();
			// newEmployeeTimeList = nullInfo;
		 }	
		} catch (Exception e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "ajax";
		}finally{
			jxl.closeJxl();
		}
		System.out.println("newEmployeeTimeList.size()="+newEmployeeTimeList.size());
    	return newEmployeeTimeList;
    }
	
	/**
	 * 规划期前调整
	 * @return
	 */
	public List<PlanAdjust> planAdjustList(){
    	JxlUtil jxl =  new JxlUtil(this.path,"参数");
		try {
			
		
			StringBuffer dw = new StringBuffer();
		    StringBuffer jx = new StringBuffer();
			String[] str = jxl.returnParam();
			if(str != null){
                    for(int i=0;i<str.length;i++){
                    	String[] b = str[i].split("-");
                    	if(b.length !=0){
                        	dw.append(b[0]).append(",");
                        	jx.append(b[1]).append(",");
                    	}

                    }
                    if(dw.length() !=0 ){
                    	dw.deleteCharAt(dw.length()-1);
                    	jx.deleteCharAt(jx.length()-1);
                    	this.dws2 = dw.toString();
                    	this.jxs2 = jx.toString();
                    }
			}
			
			//Workbook workbook = Workbook.getWorkbook(new File(this.path));
			//for(int ic=0 ;ic<workbook.getSheets().length;ic++){
			//}
			//System.out.println(workbook.getSheet("输入-现阶段飞行实力").getName());
			//Sheet sheet = workbook.getSheet(0);
			//Sheet sheet = workbook.getSheet(this.sheetName); 
			Sheet sheet = jxl.rw.getSheet(this.planXls);
			System.out.println(sheet.getName());
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 2;   //下个EXCEL要替换
			//结束行
			int ove = 14;////下个EXCEL要替换
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
				for(int i=0;i<cell.length ;i++){
					if(cell[i].getContents().trim().length() !=0){
						buf.append(cell[i].getContents()).append(",");
					}
				}
				if(buf.length()!=0)
					buf.deleteCharAt(buf.length()-1);
				//System.out.println("length==="+buf.toString().split(",").length);
				if(buf.toString().split(",").length==4 || buf.toString().split(",").length==3){ //下个EXCEL要替换 12
					PlanAdjust f = new PlanAdjust();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换  
						f.setZuoNuber(cc[0]);
						f.setDw(cc[0+b]);
						f.setJx(cc[1+b]);
						if(!f.getDw().equals("与规划期初时间间隔"))
							f.setPersons(Integer.valueOf(cc[2+b]).intValue());
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
						planAdjustList.add(f);
					    List<PlanAdjust> listNew = new ArrayList<PlanAdjust>();

					    /*if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
				    			for(PlanAdjust fs : planAdjustList){
				    				for(int i=0 ;i<dwsArray.length;i++){
				    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
				    						if(fs.getJx().trim().equals(jxsArray[i].trim())){
				    							listNew.add(fs);
				    						}
				    					}
				    				}
				    			}
					    	    
					    	}*/
					    		//只选择了单位
					    		/*if((this.dws2 != null && this.dws2.length() !=0) && (this.jxs2 == null || this.jxs2.length()==0)){
					    			String[] dwsArray = this.dws2.split(",");
					    			
					    			for(PlanAdjust fs : planAdjustList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}*/
					    		// PlanAdjustList = listNew;

					    //}

					  
				}
				
			}
			
/*			for(TotlePlan f : PlanAdjustList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<PlanAdjust> nullInfo = new ArrayList<PlanAdjust>();
			// PlanAdjustList = nullInfo;
		 }	
		} catch (Exception e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "ajax";
		}finally{
			jxl.closeJxl();
		}
		System.out.println("planAdjustList.size()="+planAdjustList.size());
    	return planAdjustList;
    }
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}


	//total
	private String ps2013;
	private String px2013;

	private String ps2014;
	private String px2014;

	private String ps2015;
	private String px2015;

	//company
	private String year;
	private String persons;
	//new
	private String percent;
	private String yearX;
	private String percentX;

	//plan
	private String personsPlan;
	public String getStrc() {
		return strc;
	}

	public void setStrc(String strc) {
		this.strc = strc;
	}



	public String getPs2013() {
		return ps2013;
	}

	public void setPs2013(String ps2013) {
		this.ps2013 = ps2013;
	}

	public String getPx2013() {
		return px2013;
	}

	public void setPx2013(String px2013) {
		this.px2013 = px2013;
	}

	public String getPs2014() {
		return ps2014;
	}

	public void setPs2014(String ps2014) {
		this.ps2014 = ps2014;
	}

	public String getPx2014() {
		return px2014;
	}

	public void setPx2014(String px2014) {
		this.px2014 = px2014;
	}

	public String getPs2015() {
		return ps2015;
	}

	public void setPs2015(String ps2015) {
		this.ps2015 = ps2015;
	}

	public String getPx2015() {
		return px2015;
	}

	public void setPx2015(String px2015) {
		this.px2015 = px2015;
	}


	private String rightCount;

	private String buttonCoutn;
	public String getRightCount() {
		return rightCount;
	}

	public void setRightCount(String rightCount) {
		this.rightCount = rightCount;
	}

	public String getButtonCoutn() {
		return buttonCoutn;
	}

	public void setButtonCoutn(String buttonCoutn) {
		this.buttonCoutn = buttonCoutn;
	}

	public String getJz() {
		return jz;
	}

	public void setJz(String jz) {
		this.jz = jz;
	}


	private String jz;

	//见习机长
	private String jxjz;
	public String getJxjz() {
		return jxjz;
	}

	public void setJxjz(String jxjz) {
		this.jxjz = jxjz;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	//页面选择单位
	private String dws;
	//页面选择机型
	private String jxs;
	public String getDws() {
		return dws;
	}

	public void setDws(String dws) {
		this.dws = dws;
	}

	public String getJxs() {
		return jxs;
	}

	public void setJxs(String jxs) {
		this.jxs = jxs;
	}


	private Message message;

	private String dws2;
	private String jxs2;
	public String getDws2() {
		return dws2;
	}

	public void setDws2(String dws2) {
		this.dws2 = dws2;
	}

	public String getJxs2() {
		return jxs2;
	}

	public void setJxs2(String jxs2) {
		this.jxs2 = jxs2;
	}

	public void saveOrOut(){
		if(strc.length()!=0){

		}else{

		}

	}
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}

	public  String TotlePlanSave(){

		return null;
	}


	public CompanyNewEmploy getCompanyNewEmploy() {
		return companyNewEmploy;
	}


	public void setCompanyNewEmploy(CompanyNewEmploy companyNewEmploy) {
		this.companyNewEmploy = companyNewEmploy;
	}


	public NewEmployeeTime getNewEmployeeTime() {
		return newEmployeeTime;
	}


	public void setNewEmployeeTime(NewEmployeeTime newEmployeeTime) {
		this.newEmployeeTime = newEmployeeTime;
	}


	public PlanAdjust getPlanAdjust() {
		return planAdjust;
	}


	public void setPlanAdjust(PlanAdjust planAdjust) {
		this.planAdjust = planAdjust;
	}


	public String getCompanyXls() {
		return companyXls;
	}


	public void setCompanyXls(String companyXls) {
		this.companyXls = companyXls;
	}


	public String getNewXls() {
		return newXls;
	}


	public void setNewXls(String newXls) {
		this.newXls = newXls;
	}


	public String getPlanXls() {
		return planXls;
	}


	public void setPlanXls(String planXls) {
		this.planXls = planXls;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getPersons() {
		return persons;
	}


	public void setPersons(String persons) {
		this.persons = persons;
	}


	public String getPercent() {
		return percent;
	}


	public void setPercent(String percent) {
		this.percent = percent;
	}


	public String getYearX() {
		return yearX;
	}


	public void setYearX(String yearX) {
		this.yearX = yearX;
	}


	public String getPercentX() {
		return percentX;
	}


	public void setPercentX(String percentX) {
		this.percentX = percentX;
	}


	public String getPersonsPlan() {
		return personsPlan;
	}


	public void setPersonsPlan(String personsPlan) {
		this.personsPlan = personsPlan;
	}


	public List<CompanyNewEmploy> getCompanyNewEmployList() {
		return companyNewEmployList;
	}


	public void setCompanyNewEmployList(List<CompanyNewEmploy> companyNewEmployList) {
		this.companyNewEmployList = companyNewEmployList;
	}


	public List<NewEmployeeTime> getNewEmployeeTimeList() {
		return newEmployeeTimeList;
	}


	public void setNewEmployeeTimeList(List<NewEmployeeTime> newEmployeeTimeList) {
		this.newEmployeeTimeList = newEmployeeTimeList;
	}


	public List<PlanAdjust> getPlanAdjustList() {
		return planAdjustList;
	}


	public void setPlanAdjustList(List<PlanAdjust> planAdjustList) {
		this.planAdjustList = planAdjustList;
	}

}
