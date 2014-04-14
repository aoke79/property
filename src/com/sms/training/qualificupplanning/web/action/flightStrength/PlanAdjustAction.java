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
import com.sms.training.qualificupplanning.bean.PlanAdjust;
import com.sms.training.qualificupplanning.bean.TotlePlan;

//5  //2   TotlePlan flight-strength/totle-plane!flightStrength.do
@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "planAdjust", location = "/sms/training/dt/fjz/planAdjustList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class PlanAdjustAction extends BaseAction{
	List<PlanAdjust> PlanAdjustList = new ArrayList<PlanAdjust>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	PlanAdjust planAdjust = new PlanAdjust(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="规划期前调整";  //不同的页面名称 就修改下 flight-strength/plan-adjust!flightStrength.do
	//EXCEL路径
	public String path = "E:\\e\\规划期前调整.xls";
	

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
		if(planAdjust != null){
			 String[] str =planAdjust.getDw().split(",");
			 String[] jxc =planAdjust.getJx().split(",");
			 String[] zn = planAdjust.getZuoNuber().split(",");
			 int person;
			 for(int i=0;i<str.length;i++){
				 
				 PlanAdjust planAdjust = new PlanAdjust(); 
				 planAdjust.setZuoNuber(zn[i].trim());
				 planAdjust.setDw(str[i].trim().trim());
				// TotlePlans.setButtonCoutn(Integer.valueOf(this.buttonCoutn.split(",")[i].trim()));
				// planAdjust.setDw(dw)
				 //机型
				 
				// TotlePlans.setJz(Integer.valueOf(his.jz.split(",")[i].trim()));
				// TotlePlans.setJxjz(Integer.valueOf(this.jxjz.split(",")[i].trim()));
				// planAdjust.setZuoNuber((Integer.valueOf(this.ps2013.split(",")[i].trim());
				// if(i!=9)
				 if(i==0){
					 planAdjust.setMon(Integer.valueOf(jxc[i].trim()));
					// planAdjust.setJx(jxc[i].trim());
				 }
				 else
					 planAdjust.setJx(jxc[i].trim());
				 if(i!=0){
					 planAdjust.setPersons(Integer.valueOf(this.persons.split(",")[i-1].trim()));
					 //person=Integer.valueOf(this.persons.split(",")[i].trim());
				 }
				// int c = 0;
				// c = TotlePlans.getPs2013()+TotlePlans.getPx2013();
				// c += TotlePlans.getPs2013()+TotlePlans.getPx2014();
				// c += TotlePlans.getPs2015()+TotlePlans.getPx2015();
				// c+= TotlePlans.getJxjz()+TotlePlans.getJz();
				// TotlePlans.setRightCount(Integer.valueOf(this.rightCount.split(",")[i].trim())); 
				// TotlePlans.setRightCount(c); 
			     this.PlanAdjustList.add(planAdjust);
			 }
			
		}
		//for(TotlePlan f : PlanAdjustList){
		//System.out.print("页面获取后: "+f.getDw()+" "+f.getJx()+" "+f.getButtonCoutn()+" "+f.getRightCount());
		//System.out.println("");
	   //}
		
				
				Workbook workbook = Workbook.getWorkbook(new File(this.path));
				Sheet sheet = workbook.getSheet(this.sheetName);
				
				JxlUtil jxlUtil = new JxlUtil(this.path, this.sheetName,true);
			for(int i=0 ;i<PlanAdjustList.size();i++){
				
				PlanAdjust fs =   PlanAdjustList.get(i);
				int hang = Integer.valueOf(fs.getZuoNuber());
				/*int hang = Integer.valueOf(fs.getZuoNuber());
				jxlUtil.writeExcel(PlanAdjustList.get(i).getButtonCoutn(),hang,4);
				jxlUtil.writeExcel(PlanAdjustList.get(i).getJz(),hang,5);
				jxlUtil.writeExcel(PlanAdjustList.get(i).getJxjz(),hang,6);*/
				//jxlUtil.writeExcel(PlanAdjustList.get(i).getButtonCoutn(),hang,4);
				if(PlanAdjustList.get(i).getDw().equals("与规划期初时间间隔")){
					jxlUtil.writeExcel(PlanAdjustList.get(i).getMon(),hang,5);
				}
				else
					jxlUtil.writeExcel(PlanAdjustList.get(i).getJx(),hang,5);
				jxlUtil.writeExcel(PlanAdjustList.get(i).getPersons(),hang,6);
				//jxlUtil.writeExcel(PlanAdjustList.get(i).getRightCount(),hang,12);
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
				if(buf.length()!=0)buf.deleteCharAt(buf.length()-1);
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
						//f.setButtonCoutn(Integer.valueOf(cc[2+b]).intValue());
						//f.setJz(Integer.valueOf(cc[3+b]).intValue());
						//f.setJxjz(Integer.valueOf(cc[4+b]).intValue());
						if(f.getDw().equals("与规划期初时间间隔"))
							f.setMon(Integer.valueOf(cc[1+b]).intValue());
						if(!f.getDw().equals("与规划期初时间间隔"))
						f.setPersons(Integer.valueOf(cc[2+b]).intValue());
						//f.setRightCount(Integer.valueOf(cc[9+b]).intValue());
					}
						PlanAdjustList.add(f);
					    List<PlanAdjust> listNew = new ArrayList<PlanAdjust>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
				    			for(PlanAdjust fs : PlanAdjustList){
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
					    			
					    			for(PlanAdjust fs : PlanAdjustList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDw().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    		// PlanAdjustList = listNew;

					    }

					  
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
			 return "ajax";
		}finally{
			if(jxl != null){
				jxl.closeJxl();
			}
			if(jxl2 != null){
				jxl2.closeJxl();
			}
		}
		System.out.println("PlanAdjustList.size()="+PlanAdjustList.size());
    	return "planAdjust";
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

	public List<PlanAdjust> getPlanAdjustList() {
		return PlanAdjustList;
	}

	public void setPlanAdjustList(List<PlanAdjust> planAdjustList) {
		PlanAdjustList = planAdjustList;
	}

	public PlanAdjust getPlanAdjust() {
		return planAdjust;
	}

	public void setPlanAdjust(PlanAdjust planAdjust) {
		this.planAdjust = planAdjust;
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

	
	
}
