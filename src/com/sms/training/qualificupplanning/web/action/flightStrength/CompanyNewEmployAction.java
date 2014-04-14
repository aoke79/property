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
import com.sms.training.qualificupplanning.bean.TotlePlan;


@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "companyNewEmploy", location = "/sms/training/dt/fjz/companyNewEmployList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class CompanyNewEmployAction extends BaseAction{
	List<CompanyNewEmploy> companyNewEmployList = new ArrayList<CompanyNewEmploy>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	CompanyNewEmploy companyNewEmploy = new CompanyNewEmploy(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="公司新雇员预测";  //不同的页面名称 就修改下
	//EXCEL路径
	public String path = "E:\\e\\公司新雇员预测.xls";
	

	
	public void saveOrOut(){
		if(strc.length()!=0){
			
		}else{
			
		}
		
	}
	
	public  String TotlePlanSave(){
		
		return null;
	}
	/**
	 * 描述： 将EXCLE的数据进行更新
	 * @return
	 */
	public  String flightStrengthOut(){
		try {
		if(companyNewEmploy != null){
			 String[] yea =companyNewEmploy.getYear().split(",");
			// String[] persons =companyNewEmploy.getPersons().split(",");
			 String[] zn = companyNewEmploy.getZuoNuber().split(",");
			 for(int i=0;i<yea.length;i++){
				 
				 CompanyNewEmploy companyNewEmploys = new CompanyNewEmploy(); 
				 companyNewEmploys.setZuoNuber(zn[i].trim());
				 companyNewEmploys.setYear(yea[i].trim());
				// companyNewEmploys.setDw(str[i].trim().trim());
				// TotlePlans.setButtonCoutn(Integer.valueOf(this.buttonCoutn.split(",")[i].trim()));
				// TotlePlans.setJx(jxc[i].trim());
				// TotlePlans.setJz(Integer.valueOf(his.jz.split(",")[i].trim()));
				// TotlePlans.setJxjz(Integer.valueOf(this.jxjz.split(",")[i].trim()));
				// TotlePlans.setZuoNuber((Integer.valueOf(this.ps2013.split(",")[i].trim());
				// companyNewEmploys.setYear(this.year.split(",")[i].trim());
				 companyNewEmploys.setPersons(Float.valueOf(this.persons.split(",")[i].trim())); 
				// int c = 0;
				// c = companyNewEmploys.getPs2013()+TotlePlans.getPx2013();
				// c += companyNewEmploys.getPs2013()+TotlePlans.getPx2014();
				// c += TotlePlans.getPs2015()+TotlePlans.getPx2015();
				// c+= TotlePlans.getJxjz()+TotlePlans.getJz();
				// TotlePlans.setRightCount(Integer.valueOf(this.rightCount.split(",")[i].trim())); 
				// TotlePlans.setRightCount(c); 
			     this.companyNewEmployList.add(companyNewEmploys);
			 }
			
		}
		//for(TotlePlan f : totlePlanList){
		//System.out.print("页面获取后: "+f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn()+" "+f.getRightCount());
		//System.out.println("");
	   
		
				
				Workbook workbook = Workbook.getWorkbook(new File(this.path));
				Sheet sheet = workbook.getSheet(this.sheetName);
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.sheetName,true);
			for(int i=0 ;i<companyNewEmployList.size();i++){
				
				CompanyNewEmploy fs =   companyNewEmployList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				
				jxlUtil.writeExcel(companyNewEmployList.get(i).getYear(),hang,4);
				jxlUtil.writeExcel(companyNewEmployList.get(i).getPersons(),hang,5);
				//jxlUtil.writeExcel(totlePlanList.get(i).getRightCount(),hang,12);
			}
			if(jxlUtil != null){
				jxlUtil.closeJxlOut();
			}

			//flight-strength/flight-strength!TotlePlan.do  "tbuser", "forward"
				this.message = this.getSuccessMessage("保存成功,Excel文件导出成功！", "", "", "");
				//Runtime.getRuntime().exec("D:\\Program Files\\Tencent\\QQ\\Bin\\QQProtect\\Bin\\QQProtect.exe");
		         
		} catch (Exception e) {
			this.message = this.getFailMessage("修改数据只能输入整数!");
			e.printStackTrace();
		}
		
		return "ajax";
	}
	
	
	//输入-现阶段飞行实力 写入
    public String flightStrength(){
    	JxlUtil jxl =  new JxlUtil("E:\\e\\参数.xls","参数");
    	JxlUtil jxl2 =  new JxlUtil(this.path,this.sheetName);
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
			
			Sheet sheet = jxl2.rw.getSheet(this.sheetName);
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
						f.setPersons(Float.valueOf((cc[1+b])).floatValue());
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
					companyNewEmployList.add(f);
					   // List<TotlePlan> listNew = new ArrayList<TotlePlan>();

					   /* if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
				    			for(CompanyNewEmploy fs : companyNewEmployList){
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
					    			
					    			for(TotlePlan fs : totlePlanList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}*/
					    		// totlePlanList = listNew;

					    }

					  
				}
				
			
			
/*			for(TotlePlan f : totlePlanList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<CompanyNewEmploy> nullInfo = new ArrayList<CompanyNewEmploy>();
			// totlePlanList = nullInfo;
		 }	
		} catch (Exception e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "ajax";
		}finally{
			if(jxl != null){
				jxl.closeJxl();
			}
			if(jxl2 != null){
				jxl2.closeJxl();
			}

		}
		//System.out.println("companyNewEmployList.size()="+companyNewEmployList.size());
    	return "companyNewEmploy";
    }
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}

	
    public List<CompanyNewEmploy> getCompanyNewEmployList() {
		return companyNewEmployList;
	}

	public void setCompanyNewEmployList(List<CompanyNewEmploy> companyNewEmployList) {
		this.companyNewEmployList = companyNewEmployList;
	}


	private String year;
    private String persons;
    
   
	public String getStrc() {
		return strc;
	}

	public void setStrc(String strc) {
		this.strc = strc;
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

	public CompanyNewEmploy getCompanyNewEmploy() {
		return companyNewEmploy;
	}

	public void setCompanyNewEmploy(CompanyNewEmploy companyNewEmploy) {
		this.companyNewEmploy = companyNewEmploy;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	
}
