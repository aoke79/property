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

//5  //2   FlightStrength
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "flightStrength", location = "/sms/training/dt/fjz/parameterSettings.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class ParameterSettingsAction extends BaseAction{
	List<FlightStrength> flightStrengthList = new ArrayList<FlightStrength>();
	private String fowd;
	
	private String tableInfo;
	public String getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(String tableInfo) {
		this.tableInfo = tableInfo;
	}

	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	FlightStrength flightStrength = new FlightStrength(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="参数";  //不同的页面名称 就修改下
	//EXCEL路径
	public String path = "E:\\e\\参数.xls";
	public FlightStrength getFlightStrength() {
		return flightStrength;
	}

	public void saveOrOut(){
		if(strc.length()!=0){
			
		}else{
			
		}
		
	}
	
	
	public void setFlightStrength(FlightStrength flightStrength) {
		this.flightStrength = flightStrength;
	}

	public  String flightStrengthSave(){
		
		return null;
	}
	/**
	 * 描述： 将EXCLE的数据进行更新
	 * @return
	 */
	public  String flightStrengthOut(){
		try {
		if(this.dws2 != null){
			 String[] str =this.dws2.split(",");
			 for(int i=0;i<str.length;i++){
				 String [] c = str[i].split("-");
				 String dw = c[1];
				 String jx = c[2];
				 FlightStrength flightStrengths = new FlightStrength(); 
				 flightStrengths.setDw(dw);
				 flightStrengths.setJx(jx);
 
			     this.flightStrengthList.add(flightStrengths);
			 }
			
		}
		for(FlightStrength f : flightStrengthList){
		System.out.print("参数: "+f.getDw()+" "+f.getJx());
		System.out.println("");
	}
		
				
			
			
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.sheetName,true);
				
				   // int beg = 0;
				   // int ove = 15;
				   // jxlUtil.rw.getSheet(this.sheetName);
				
				jxlUtil.delete();
			for(int i=0 ;i<flightStrengthList.size();i++){
				FlightStrength fs =   flightStrengthList.get(i);
				
				jxlUtil.writeExcel(fs.getDw(),i,0);
				jxlUtil.writeExcel(fs.getJx(),i,1);

				//jxlUtil.writeExcel(flightStrengthList.get(i).getRightCount(),hang,12);
			}
			if(jxlUtil != null){
				jxlUtil.closeJxlOut();
			}
				
			//flight-strength/flight-strength!flightStrength.do  "tbuser", "forward"
				this.message = this.getSuccessMessage("保存成功！", "", "", "");
				//Runtime.getRuntime().exec("D:\\Program Files\\Tencent\\QQ\\Bin\\QQProtect\\Bin\\QQProtect.exe");
		         
		} catch (Exception e) {
			this.message = this.getFailMessage("保存失败!");
			e.printStackTrace();
		}
		
		return "ajax";
	}
	
	
	//输入-现阶段飞行实力 写入
    public String parameterSettings(){
    	JxlUtil jxl =  new JxlUtil(this.path,"参数");
		try {
			
		    StringBuffer dw = new StringBuffer();
		    StringBuffer jx = new StringBuffer();
			String[] str = jxl.returnParam();
			if(str != null){
                    for(int i=0;i<str.length;i++){
                    	
                    	dw.append(str[i]).append(",");
                    	
                    }
                    if(dw.length() !=0 ){
                    	dw.deleteCharAt(dw.length()-1);
                    	this.dws2 = dw.toString();
                   
                    }
			}

		} catch (Exception   e) {
			 this.message =  this.getFailMessage(this.path+" 系统出错");
			e.printStackTrace();
			 return "ajax";
		}finally{
			if(jxl != null){
				jxl.closeJxl();
			}
		
		}
		
    	return "flightStrength";
    }
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}

	public List<FlightStrength> getFlightStrengthList() {
		return flightStrengthList;
	}

	public void setFlightStrengthList(List<FlightStrength> flightStrengthList) {
		this.flightStrengthList = flightStrengthList;
	}
	
	
    private String f1;
    private String f2;
    private String f3;
    private String f4;
    private String f5;
	public String getStrc() {
		return strc;
	}

	public void setStrc(String strc) {
		this.strc = strc;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	public String getF5() {
		return f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
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
	
}
