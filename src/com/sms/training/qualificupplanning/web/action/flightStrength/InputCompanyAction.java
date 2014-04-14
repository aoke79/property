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
import com.sms.training.qualificupplanning.bean.InputCompany;

//5  //2   TotlePlan flight-strength/totle-plane!flightStrength.do
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "InputCompany", location = "/sms/training/dt/fjz/inputCompanyList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class InputCompanyAction extends BaseAction{
	List<InputCompany> inputCompanyList = new ArrayList<InputCompany>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	InputCompany inputCompany = new InputCompany(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="输出公司间机长调整建议表";  //不同的页面名称 就修改下  input-new-employ  flight-strength/input-new-employ!flightStrength.do
	//EXCEL路径
	public String path = "E:\\output.xls";
	

	public void saveOrOut(){
		if(strc.length()!=0){
			
		}else{
			
		}
		
	}
	
	public  String TotlePlanSave(){
		
		return null;
	}
	
	//输入-现阶段飞行实力 写入  输出一年度规划平衡表
    public String flightStrength(){
    	JxlUtil jxl =  new JxlUtil(this.path,"输出规划期全公司机长平衡表");
		String[] str = this.getParam();
		String[] dws2 = new String[str.length];
		String[] jxs2 = new String[str.length];
		try {
			
			Sheet sheet = jxl.rw.getSheet(this.sheetName);
			System.out.println(sheet.getName());
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 5;   //下个EXCEL要替换
			//结束行
			int ove = 107;////下个EXCEL要替换 i=4 2013 i=5 zongdui
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
				for(int i=0;i<cell.length ;i++){
					if(cell[i].getContents().trim().length() !=0){
						buf.append(cell[i].getContents()).append(",");
					}
				}
				if(buf.length()!=0)buf.deleteCharAt(buf.length()-1);
				if(str != null){
		            for(int i=0;i<str.length;i++){
		            	String[] b = str[i].split("-");
		            	if(b.length !=0){
		            	dws2[i] = b[0];
		            	jxs2[i] = b[1];
		            	}
		            }
				}
				//System.out.println("length==="+buf.toString().split(",").length);
				if(buf.toString().split(",").length==6){ //下个EXCEL要替换 12
					

					
					InputCompany f = new InputCompany();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
					
						f.setYear(cc[0]);
						f.setOutCompany(cc[0+b]);
						f.setJx(cc[1+b]);
						f.setInCompany(cc[2+b]);
						f.setInjx(cc[3+b]);
						f.setPerson(cc[4+b]);
						
					}
						inputCompanyList.add(f); 
					   // List<InputEmployFenPei> listNew = new ArrayList<InputEmployFenPei>(); flight-strength/input-company!flightStrength.do

				}
				
			}
			 List<InputCompany> newIn = new ArrayList<InputCompany>();
			   if(dws2 != null &&jxs2!=null ){
				   if(dws2.length>0 && dws2[0] != null){
					   for(InputCompany p : inputCompanyList){
						    for(int i=0;i<dws2.length;i++){
						    	  if(dws2[i] == null ){
						    		  continue;
						    	  }
						    	  if(dws2[i].equals(p.getOutCompany()) && jxs2[i].equals(p.getJx())){
						    		  newIn.add(p);
						    	  }
						    }
					   }
					   inputCompanyList = newIn;
				   }
			   }
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<InputCompany> nullInfo = new ArrayList<InputCompany>();
			// PlanAdjustList = nullInfo;
		 }	
		} catch (Exception e) {
			 this.message =  this.getFailMessage(this.path+" 该文件已经损坏,或者不存在， 无法正常读取");
			
			e.printStackTrace();
			 return "ajax";
		}finally{
			if(jxl != null){
				jxl.closeJxl();
			}

		}
    	return "InputCompany";
    }
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}

    private String persons;
   
   /* private String f3; 
    private String f4;
    private String f5;*/
	public String getStrc() {
		return strc;
	}

	public void setStrc(String strc) {
		this.strc = strc;
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


	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<InputCompany> getInputCompanyList() {
		return inputCompanyList;
	}

	public void setInputCompanyList(List<InputCompany> inputCompanyList) {
		this.inputCompanyList = inputCompanyList;
	}

	public InputCompany getInputCompany() {
		return inputCompany;
	}

	public void setInputCompany(InputCompany inputCompany) {
		this.inputCompany = inputCompany;
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
		}finally{
			jxl.closeJxl();
		}
		return new String[0];
	}
	
}
