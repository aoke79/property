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
		@Result(name = "flightStrength", location = "/sms/training/dt/fjz/zsList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class FlightStrengthAction extends BaseAction{
	List<FlightStrength> flightStrengthList = new ArrayList<FlightStrength>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	FlightStrength flightStrength = new FlightStrength(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="机队实力表";  //不同的页面名称 就修改下
	//EXCEL路径
	public String path = "E:\\e\\机队实力表.xls";
	
	
	
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
		if(flightStrength != null){
			 String[] str =flightStrength.getDw().split(",");
			 String[] jxc =flightStrength.getJx().split(",");
			 String[] zn = flightStrength.getZuoNuber().split(",");
			 for(int i=0;i<str.length;i++){
				 
				 FlightStrength flightStrengths = new FlightStrength(); 
				 flightStrengths.setZuoNuber(zn[i].trim());
				 flightStrengths.setDw(str[i].trim().trim());
				 flightStrengths.setButtonCoutn(Integer.valueOf(this.buttonCoutn.split(",")[i].trim()));
				 flightStrengths.setJx(jxc[i].trim());
				 flightStrengths.setJz(Integer.valueOf(this.jz.split(",")[i].trim()));
				 flightStrengths.setJxjz(Integer.valueOf(this.jxjz.split(",")[i].trim()));
				 flightStrengths.setF1(Integer.valueOf(this.f1.split(",")[i].trim()));
				 flightStrengths.setF2(Integer.valueOf(this.f2.split(",")[i].trim())); 
				 flightStrengths.setF3(Integer.valueOf(this.f3.split(",")[i].trim())); 
				 flightStrengths.setF4(Integer.valueOf(this.f4.split(",")[i].trim())); 
				 flightStrengths.setF5(Integer.valueOf(this.f5.split(",")[i].trim())); 
				 int c = 0;
				 c = flightStrengths.getF1()+flightStrengths.getF2()+flightStrengths.getF3();
				 c += flightStrengths.getF4()+flightStrengths.getF5();
				 c+= flightStrengths.getJxjz()+flightStrengths.getJz();
				// flightStrengths.setRightCount(Integer.valueOf(this.rightCount.split(",")[i].trim())); 
				// flightStrengths.setRightCount(c); 
			     this.flightStrengthList.add(flightStrengths);
			 }
			
		}
		for(FlightStrength f : flightStrengthList){
		System.out.print("页面获取后: "+f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn()+" "+f.getRightCount());
		System.out.println("");
	}
		
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.sheetName,true);
			for(int i=0 ;i<flightStrengthList.size();i++){
			
				FlightStrength fs =   flightStrengthList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				jxlUtil.writeExcel(flightStrengthList.get(i).getButtonCoutn(),hang,6);
				jxlUtil.writeExcel(flightStrengthList.get(i).getJz(),hang,7);
				jxlUtil.writeExcel(flightStrengthList.get(i).getJxjz(),hang,8);
				jxlUtil.writeExcel(flightStrengthList.get(i).getF1(),hang,9);
				jxlUtil.writeExcel(flightStrengthList.get(i).getF2(),hang,10);
				jxlUtil.writeExcel(flightStrengthList.get(i).getF3(),hang,11);
				jxlUtil.writeExcel(flightStrengthList.get(i).getF4(),hang,12);
				jxlUtil.writeExcel(flightStrengthList.get(i).getF5(),hang,13);
				//jxlUtil.writeExcel(flightStrengthList.get(i).getRightCount(),hang,12);
			
			
			}
			if(jxlUtil != null ){
				jxlUtil.closeJxlOut();
			}	
			
			//flight-strength/flight-strength!flightStrength.do  "tbuser", "forward"
				this.message = this.getSuccessMessage("保存成功！", "", "", "");
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
			
			
			
      
			
			//Workbook workbook = Workbook.getWorkbook(new File(this.path));
			//for(int ic=0 ;ic<workbook.getSheets().length;ic++){
			//}
			//System.out.println(workbook.getSheet("输入-现阶段飞行实力").getName());
			//Sheet sheet = workbook.getSheet(0);
			//Sheet sheet = workbook.getSheet(this.sheetName); 
			
			Sheet sheet = jxl2.rw.getSheet(this.sheetName);
			
			System.out.println(sheet.getName());
			//int rowsCount = sheet.getRows();
			//第几行开始
			int beg = 5;   //下个EXCEL要替换
			//结束行
			int ove = 20;////下个EXCEL要替换
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
				for(int i=0;i<cell.length ;i++){
					if(cell[i].getContents().trim().length() !=0){
						buf.append(cell[i].getContents()).append(",");
					}
				}
				if(buf.length()!=0)buf.deleteCharAt(buf.length()-1);
				if(buf.toString().split(",").length==11){ //下个EXCEL要替换 
					FlightStrength f = new FlightStrength();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换
						f.setZuoNuber(cc[0]);
						f.setDw(cc[0+b]);
						f.setJx(cc[1+b]);
						f.setButtonCoutn(Integer.valueOf(cc[2+b]).intValue());
						f.setJz(Integer.valueOf(cc[3+b]).intValue());
						f.setJxjz(Integer.valueOf(cc[4+b]).intValue());
						f.setF1(Integer.valueOf(cc[5+b]).intValue());
						f.setF2(Integer.valueOf(cc[6+b]).intValue());
						f.setF3(Integer.valueOf(cc[7+b]).intValue());
						f.setF4(Integer.valueOf(cc[8+b]).intValue());
						f.setF5(Integer.valueOf(cc[9+b]).intValue());
						//f.setRightCount(Integer.valueOf(cc[10+b]).intValue());
					}
					

					
						flightStrengthList.add(f);
					    List<FlightStrength> listNew = new ArrayList<FlightStrength>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
					    	    for(int ii=0;ii<dwsArray.length;ii++){
					    	    	
					    			for(FlightStrength fs : flightStrengthList){
                                             if(fs.getDw().equals(dwsArray[ii]) && fs.getJx().equals(jxsArray[ii])){
                                            	 listNew.add(fs);
                                             }
					    			}
					    	    }
					    	    

					    	    
					    	}
					    		//只选择了单位
					    		if((this.dws2 != null && this.dws2.length() !=0) && (this.jxs2 == null || this.jxs2.length()==0)){
					    			String[] dwsArray = this.dws2.split(",");
					    			
					    			for(FlightStrength fs : flightStrengthList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    	
					    		 flightStrengthList = listNew;

					    }

					  
				}
				
			}
			
/*			for(FlightStrength f : flightStrengthList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<FlightStrength> nullInfo = new ArrayList<FlightStrength>();
			// flightStrengthList = nullInfo;
		 }	
		} catch (Exception e) {
			 this.message =  this.getFailMessage(this.path+" 系统出错！");
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return "ajax";
		}finally{
			if(jxl !=null ){
				jxl.closeJxl();
				
			}
			if(jxl2 !=null ){
				jxl2.closeJxl();
				
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
