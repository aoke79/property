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
import com.sms.training.qualificupplanning.bean.TotlePlan;

//5  //2   TotlePlan
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "TotlePlan", location = "/sms/training/dt/fjz/totlePlaneList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class TotlePlaneAction extends BaseAction{
	List<TotlePlan> totlePlanList = new ArrayList<TotlePlan>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	TotlePlan TotlePlan = new TotlePlan(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="飞机总计划";  //不同的页面名称 就修改下
	//EXCEL路径
	public String path = "E:\\e\\飞机总计划.xls";
	public TotlePlan getTotlePlan() {
		return TotlePlan;
	}

	public void saveOrOut(){
		if(strc.length()!=0){
			
		}else{
			
		}
		
	}
	
	
	public void setTotlePlan(TotlePlan TotlePlan) {
		this.TotlePlan = TotlePlan;
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
		if(TotlePlan != null){
			 String[] str =TotlePlan.getDw().split(",");
			 String[] jxc =TotlePlan.getJx().split(",");
			 String[] zn = TotlePlan.getZuoNuber().split(",");
			 for(int i=0;i<str.length;i++){
				 
				 TotlePlan TotlePlans = new TotlePlan(); 
				 TotlePlans.setZuoNuber(zn[i].trim());
				 TotlePlans.setDw(str[i].trim().trim());
				// TotlePlans.setButtonCoutn(Integer.valueOf(this.buttonCoutn.split(",")[i].trim()));
				 TotlePlans.setJx(jxc[i].trim());
				// TotlePlans.setJz(Integer.valueOf(his.jz.split(",")[i].trim()));
				// TotlePlans.setJxjz(Integer.valueOf(this.jxjz.split(",")[i].trim()));
				// TotlePlans.setZuoNuber((Integer.valueOf(this.ps2013.split(",")[i].trim());
				 TotlePlans.setPs2013(Integer.valueOf(this.ps2013.split(",")[i].trim()));
				 TotlePlans.setPx2013(Integer.valueOf(this.px2013.split(",")[i].trim())); 
				 TotlePlans.setPs2014(Integer.valueOf(this.ps2014.split(",")[i].trim())); 
				 TotlePlans.setPx2014(Integer.valueOf(this.px2014.split(",")[i].trim())); 
				 TotlePlans.setPs2015(Integer.valueOf(this.ps2015.split(",")[i].trim())); 
				 TotlePlans.setPx2015(Integer.valueOf(this.px2015.split(",")[i].trim())); 
				 int c = 0;
				 c = TotlePlans.getPs2013()+TotlePlans.getPx2013();
				 c += TotlePlans.getPs2013()+TotlePlans.getPx2014();
				 c += TotlePlans.getPs2015()+TotlePlans.getPx2015();
				// c+= TotlePlans.getJxjz()+TotlePlans.getJz();
				// TotlePlans.setRightCount(Integer.valueOf(this.rightCount.split(",")[i].trim())); 
				// TotlePlans.setRightCount(c); 
			     this.totlePlanList.add(TotlePlans);
			 }
			
		}
		//for(TotlePlan f : totlePlanList){
		//System.out.print("页面获取后: "+f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn()+" "+f.getRightCount());
		//System.out.println("");
	   //}
		
				
				Workbook workbook = Workbook.getWorkbook(new File(this.path));
				Sheet sheet = workbook.getSheet(this.sheetName);
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.sheetName,true);
			for(int i=0 ;i<totlePlanList.size();i++){
				
				TotlePlan fs =   totlePlanList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				/*int hang = Integer.valueOf(fs.getZuoNuber());
				jxlUtil.writeExcel(totlePlanList.get(i).getButtonCoutn(),hang,4);
				jxlUtil.writeExcel(totlePlanList.get(i).getJz(),hang,5);
				jxlUtil.writeExcel(totlePlanList.get(i).getJxjz(),hang,6);*/
				//jxlUtil.writeExcel(totlePlanList.get(i).getButtonCoutn(),hang,4);
				jxlUtil.writeExcel(totlePlanList.get(i).getPs2013(),hang,6);
				jxlUtil.writeExcel(totlePlanList.get(i).getPx2013(),hang,7);
				jxlUtil.writeExcel(totlePlanList.get(i).getPs2014(),hang,8);
				jxlUtil.writeExcel(totlePlanList.get(i).getPx2014(),hang,9);
				jxlUtil.writeExcel(totlePlanList.get(i).getPs2015(),hang,10);
				jxlUtil.writeExcel(totlePlanList.get(i).getPx2015(),hang,11);
				jxlUtil.writeExcel(totlePlanList.get(i).getRightCount(),hang,12);
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
				//System.out.println("length==="+buf.toString().split(",").length);
				if(buf.toString().split(",").length==9){ //下个EXCEL要替换 12
					TotlePlan f = new TotlePlan();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换  
						f.setZuoNuber(cc[0]);
						f.setDw(cc[0+b]);
						f.setJx(cc[1+b]);
						//f.setButtonCoutn(Integer.valueOf(cc[2+b]).intValue());
						//f.setJz(Integer.valueOf(cc[3+b]).intValue());
						//f.setJxjz(Integer.valueOf(cc[4+b]).intValue());
						f.setPs2013(Integer.valueOf(cc[2+b]).intValue());
						f.setPx2013(Integer.valueOf(cc[3+b]).intValue());
						f.setPs2014(Integer.valueOf(cc[4+b]).intValue());
						f.setPx2014(Integer.valueOf(cc[5+b]).intValue());
						f.setPs2015(Integer.valueOf(cc[6+b]).intValue());
						f.setPx2015(Integer.valueOf(cc[7+b]).intValue());
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
						totlePlanList.add(f);
					    List<TotlePlan> listNew = new ArrayList<TotlePlan>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
				    			for(TotlePlan fs : totlePlanList){
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
					    			
					    			for(TotlePlan fs : totlePlanList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    		// totlePlanList = listNew;

					    }

					  
				}
				
			}
			
/*			for(TotlePlan f : totlePlanList){
				
				//System.out.print(f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn());
				//System.out.println("");
			}*/
		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<TotlePlan> nullInfo = new ArrayList<TotlePlan>();
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
		//System.out.println("totlePlanList.size()="+totlePlanList.size());
    	return "TotlePlan";
    }
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}

	public List<TotlePlan> gettotlePlanList() {
		return totlePlanList;
	}

	public void settotlePlanList(List<TotlePlan> totlePlanList) {
		this.totlePlanList = totlePlanList;
	}
	
	
    private String ps2013;
    private String px2013;
    
    private String ps2014;
    private String px2014;
    
    private String ps2015;
    private String px2015;
   /* private String f3;
    private String f4;
    private String f5;*/
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

	public List<TotlePlan> getTotlePlanList() {
		return totlePlanList;
	}

	public void setTotlePlanList(List<TotlePlan> totlePlanList) {
		this.totlePlanList = totlePlanList;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
}
