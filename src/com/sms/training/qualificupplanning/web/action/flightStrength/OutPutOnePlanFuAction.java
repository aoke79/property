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
import com.sms.training.qualificupplanning.bean.OutPutOnePlanFu;
import com.sms.training.qualificupplanning.bean.OutPutOnePlanJz;


@ParentPackage("sinoframe-default")
@Results( {
	    @Result(name="ajax",location="/standard/ajaxDone.jsp"),
		@Result(name = "OutPutOnePlanFu", location = "/sms/training/dt/fjz/outPutOnePlanFuList.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name="searchPage", location="/sms/training/dt/fjz/searchPage.jsp"),
		@Result(name = "index", location = "/sms/training/qualification/quaJbpm4/index.jsp") })

/** 
 * 描述： 输入-现阶段飞行实力ACTION
 * @author LUJIE		
 */
public class OutPutOnePlanFuAction extends BaseAction{
	List<OutPutOnePlanFu> outPutOnePlanFuList = new ArrayList<OutPutOnePlanFu>();
	private String fowd;
	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}


	OutPutOnePlanFu OutPutOnePlanFu = new OutPutOnePlanFu(); 
	String strc = "";
	//SHEET 名称
	public String sheetName="输出一年度规划平衡表";  //不同的页面名称 就修改下  input-new-employ  flight-strength/out-put-one-plan-fu!flightStrength.do
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
    	JxlUtil jxl =  new JxlUtil(this.path,"输出一年度规划平衡表");
		String[] str = this.getParam();
		String[] dws2 = new String[str.length];
		String[] jxs2 = new String[str.length];
		
		if(str != null){
            for(int i=0;i<str.length;i++){
            	String[] b = str[i].split("-");
            	if(b .length !=0){
                	dws2[i] = b[0].trim();
                	jxs2[i] = b[1].trim();
            	}

            }
		}	Workbook workbook = null;
		try {
			Sheet sheet = jxl.rw.getSheet(this.sheetName);
			System.out.println(sheet.getName());
			//int rowsCount = sheet.getRows();
			//第几行开始
			String orgName = "";
			String years = "";
			int beg = 60;   //下个EXCEL要替换
			//结束行
			int ove = 111;////下个EXCEL要替换 i=4 2013 i=5 zongdui
			for(int x = beg ; x<ove ;x++){
				Cell[] cell= sheet.getRow(x);
			    StringBuffer buf = new StringBuffer();
			    OutPutOnePlanFu f = new OutPutOnePlanFu();//对象下个EXCEL要替换
			    if(!cell[1].getContents().equals("")){
					orgName = cell[1].getContents();
				}
				if(!cell[2].getContents().equals("")){
					years = cell[2].getContents();
				}
				
				if(dws2.length>0 && dws2[0] !=null){
		    		for(int i= 0;i<dws2.length;i++ ){
			    		if(cell[1].getContents() != null && !cell[1].getContents().equals("")){
							orgName = cell[1].getContents();
						}
			    		if(!cell[2].getContents().equals("")){
							years = cell[2].getContents();
						}
			    		if(dws2[i] == null || jxs2[i]== null ){
			    			continue;
			    		}
		    		
			    		if(dws2[i].equals(orgName)&& jxs2[i].equals(cell[3].getContents())){
			    			f.setDw(orgName);
							f.setYear(years);
							f.setJx(cell[3].getContents());
							f.setFeiji(cell[4].getContents());
							f.setNeed(cell[5].getContents());
							f.setStartPeople(cell[6].getContents());
							f.setNewPeople(cell[7].getContents());
							f.setCadd(cell[8].getContents());
							f.setOther1(cell[9].getContents());
							f.setChoudiao(cell[10].getContents());
							f.setTuixiu(cell[11].getContents());
							f.setShengji(cell[12].getContents());
							f.setOther2(cell[13].getContents());
							f.setF1(cell[14].getContents());
							f.setEndPeople(cell[15].getContents());
							f.setEndPow(cell[16].getContents());
							f.setStaticP(cell[17].getContents());
							outPutOnePlanFuList.add(f);
			    		}
		    		}
				}else{
					f.setDw(orgName);
					f.setYear(years);
					f.setJx(cell[3].getContents());
					f.setFeiji(cell[4].getContents());
					f.setNeed(cell[5].getContents());
					f.setStartPeople(cell[6].getContents());
					f.setNewPeople(cell[7].getContents());
					f.setCadd(cell[8].getContents());
					f.setOther1(cell[9].getContents());
					f.setChoudiao(cell[10].getContents());
					f.setTuixiu(cell[11].getContents());
					f.setShengji(cell[12].getContents());
					f.setOther2(cell[13].getContents());
					f.setF1(cell[14].getContents());
					f.setEndPeople(cell[15].getContents());
					f.setEndPow(cell[16].getContents());
					f.setStaticP(cell[17].getContents());
					outPutOnePlanFuList.add(f);
					
				}
				
				
				
				
				/*if(buf.toString().split(",").length==17){ //下个EXCEL要替换 12
					OutPutOnePlanFu f = new OutPutOnePlanFu();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						f.setDw(cc[0]);
						f.setYear(cc[0+b]);
						f.setJx(cc[1+b]);
						f.setFeiji(cc[2+b]);
						f.setNeed(cc[3+b]);
						f.setStartPeople(cc[4+b]);
						f.setNewPeople(cc[5+b]);
						f.setCadd(cc[6+b]);
						f.setOther1(cc[7+b]);
						f.setChoudiao(cc[8+b]);
						f.setTuixiu(cc[9+b]);
						f.setShengji(cc[9+b]);
						f.setOther2(cc[10+b]);
						f.setF1(cc[11+b]);
						f.setEndPeople(cc[13+b]);
						f.setEndPow(cc[14+b]);
						f.setStaticP(cc[12+b]);
						
					}
					outPutOnePlanFuList.add(f);

				}*/
				
			}
			 List<OutPutOnePlanFu> newIn = new ArrayList<OutPutOnePlanFu>();
			   if(dws2 != null &&jxs2!=null ){
				   if(dws2.length>0 && dws2[0] != null){
					   for(OutPutOnePlanFu p : outPutOnePlanFuList){
						    for(int i=0;i<dws2.length;i++){
						    	  if(dws2[i] == null ){
						    		  continue;
						    	  }
						    	  if(dws2[i].equals(p.getDw()) && jxs2[i].equals(p.getJx())){
						    		  newIn.add(p);
						    	  }
						    }
					   }
					   outPutOnePlanFuList = newIn;
				   }
			   }
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<OutPutOnePlanFu> nullInfo = new ArrayList<OutPutOnePlanFu>();
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
    	return "OutPutOnePlanFu";
    }
	/**
	 * 单位机型检索页面
	 * @return
	 */
	public String searchPage(){
		return "searchPage";
	}
  
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


	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<OutPutOnePlanFu> getOutPutOnePlanFuList() {
		return outPutOnePlanFuList;
	}

	public void setOutPutOnePlanFuList(List<OutPutOnePlanFu> outPutOnePlanFuList) {
		this.outPutOnePlanFuList = outPutOnePlanFuList;
	}

	public OutPutOnePlanFu getOutPutOnePlanFu() {
		return OutPutOnePlanFu;
	}

	public void setOutPutOnePlanFu(OutPutOnePlanFu outPutOnePlanFu) {
		OutPutOnePlanFu = outPutOnePlanFu;
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
