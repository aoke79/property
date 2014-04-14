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
import com.sms.training.qualificupplanning.bean.InputNewEmploy;

//5  //2   TotlePlan flight-strength/totle-plane!flightStrength.do
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "InputNewEmploy", location = "/sms/training/dt/fjz/inputNewEmployList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class InputNewEmployAction extends BaseAction{
	List<InputNewEmploy> inputNewEmployList = new ArrayList<InputNewEmploy>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	InputNewEmploy inputNewEmploy = new InputNewEmploy(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="输出新雇员分配情况表";  //不同的页面名称 就修改下  input-new-employ  flight-strength/input-new-employ!flightStrength.do
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
    	JxlUtil jxl =  new JxlUtil(this.path,"参数");
		try {
			//StringBuffer dw = new StringBuffer();
		   // StringBuffer jx = new StringBuffer();
			/*String[] str = jxl.returnParam();
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
			}*/
			Sheet sheet = jxl.rw.getSheet(this.sheetName);
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
				if(buf.toString().split(",").length==11){ //下个EXCEL要替换 12
					InputNewEmploy f = new InputNewEmploy();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换   重庆	天津	上海	湖北	需求合计	预测-需求

						//f.setZuoNuber(cc[0]);
						f.setYear(cc[0]);
						f.setPerson(cc[0+b]);
						f.setZongdui(cc[1+b]);
						f.setXinnan(cc[2+b]);
						f.setZhejiang(cc[3+b]);
						f.setChongqing(cc[4+b]);
						f.setTianjjin(cc[5+b]);
						f.setShanghai(cc[6+b]);
						f.setHubei(cc[7+b]);
						f.setHeji(cc[8+b]);
						f.setYuceheji(cc[9+b]);
						
						//重庆	天津	上海	湖北	需求合计	预测-需求
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
						inputNewEmployList.add(f);
					    List<InputNewEmploy> listNew = new ArrayList<InputNewEmploy>();

				}
				
			}
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<InputNewEmploy> nullInfo = new ArrayList<InputNewEmploy>();
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
    	return "InputNewEmploy";
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

	public List<InputNewEmploy> getInputNewEmployList() {
		return inputNewEmployList;
	}

	public void setInputNewEmployList(List<InputNewEmploy> inputNewEmployList) {
		this.inputNewEmployList = inputNewEmployList;
	}

	public InputNewEmploy getInputNewEmploy() {
		return inputNewEmploy;
	}

	public void setInputNewEmploy(InputNewEmploy inputNewEmploy) {
		this.inputNewEmploy = inputNewEmploy;
	}

	
	
}
