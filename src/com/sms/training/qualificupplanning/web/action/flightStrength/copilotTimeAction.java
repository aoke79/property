package com.sms.training.qualificupplanning.web.action.flightStrength;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.JxlUtil;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificupplanning.bean.CopilotRatio;
import com.sms.training.qualificupplanning.bean.CopilotTime;


@Results({
	@Result(name="successAjax",location="/standard/ajaxDone.jsp"),
	@Result(name="show",location="/sms/training/dt/fjz/copilotTime.jsp")
})
@ParentPackage("sinoframe-default")
public class copilotTimeAction extends BaseAction{
	private String sheetName = "副驾驶半年累计飞行经历时间平均增加量";
    private Message message;
    private String filePath="E:\\e\\副驾驶半年累计飞行经历时间平均增加量.xls";
    private String dws2;
	private String jxs2;
	private String fowd;
    //引入bean
	private CopilotTime ct;
	//封装的List
	private List<CopilotTime> copilotTimeList = new ArrayList<CopilotTime>();
	
	/**
	 * 将excel 显示到页面的方法 
	 * @return
	 */
	public String showExcel(){
    	JxlUtil jxl =  new JxlUtil("E:\\e\\参数.xls","参数");
    	JxlUtil jxl2 =  new JxlUtil(this.filePath,this.sheetName);
		try{
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
				if(buf.toString().split(",").length==6){ //下个EXCEL要替换 
					 ct = new CopilotTime();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换
						ct.setDepartment(cc[0]);
						ct.setPlaneType(cc[0+b]);
						ct.setF1(cc[1+b]);
						ct.setF2(cc[2+b]);
						ct.setF3(cc[3+b]);
						ct.setF4(cc[4+b]);
						
						
					}
					

					
						this.copilotTimeList.add(ct);
					    List<CopilotTime> listNew = new ArrayList<CopilotTime>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
					    	    for(int ii=0;ii<dwsArray.length;ii++){
					    	    	
					    			for(CopilotTime fs : copilotTimeList){
                                             if(fs.getDepartment().equals(dwsArray[ii]) && fs.getPlaneType().equals(jxsArray[ii])){
                                            	 listNew.add(fs);
                                             }
					    			}
					    	    }
					    	    

					    	    
					    	}
					    		//只选择了单位
					    		if((this.dws2 != null && this.dws2.length() !=0) && (this.jxs2 == null || this.jxs2.length()==0)){
					    			String[] dwsArray = this.dws2.split(",");
					    			
					    			for(CopilotTime fs : copilotTimeList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDepartment().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    		copilotTimeList = listNew;
					    }  
				}
			}
			

		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<CopilotTime> nullInfo = new ArrayList<CopilotTime>();
			
		 }	
			
		}catch(Exception se){
			se.printStackTrace();
			this.message = this.getFailMessage(this.filePath+"文件不存在，或者损坏，无法读取！");
			return "successAjax";
		}finally{
			if(jxl != null){
				jxl.closeJxl();
			}
			if(jxl2 != null){
				jxl2.closeJxl();
			}
		}
		
		return "show";
	}

	public String saveDateToExcel(){
		try{
			if(this.ct != null){
				String[] department = ct.getDepartment().split(",");
				String[] planType = ct.getPlaneType().split(",");
				for(int i=0; i< department.length; i++){
					CopilotTime pp = new CopilotTime();
					pp.setDepartment(department[i].trim());
					pp.setPlaneType(planType[i].trim());
					pp.setF1(ct.getF1().split(",")[i].trim());
				    pp.setF2(ct.getF2().split(",")[i].trim());
				    pp.setF3(ct.getF3().split(",")[i].trim());
				    pp.setF4(ct.getF4().split(",")[i].trim());
				   
					this.copilotTimeList.add(pp);
				}
				if(this.copilotTimeList.size()>0){
					
					JxlUtil jxl = new JxlUtil(this.filePath,this.sheetName,true);
					int hang = 5;
					for(int x=0; x< this.copilotTimeList.size(); x++){
						
						if(hang <= 20){
							jxl.writeExcel(Float.valueOf(copilotTimeList.get(x).getF1()), hang, 6);
							jxl.writeExcel(Float.valueOf(copilotTimeList.get(x).getF2()), hang, 7);
							jxl.writeExcel(Float.valueOf(copilotTimeList.get(x).getF3()), hang, 8);
							jxl.writeExcel(Float.valueOf(copilotTimeList.get(x).getF4()), hang, 9);
													
							
						}
						hang ++;
					}
					if(jxl != null){
						jxl.closeJxlOut();
					}
				}
				
			}
			
		 this.message = this.getSuccessMessage(this.sheetName+"保存成功！", "", "", "");	
		}catch(Exception se){
			se.printStackTrace();
			this.message = this.getFailMessage(this.sheetName+"保存失败！");
		}
		
		return "successAjax";
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

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

	public String getFowd() {
		return fowd;
	}

	public void setFowd(String fowd) {
		this.fowd = fowd;
	}

	public CopilotTime getCt() {
		return ct;
	}

	public void setCt(CopilotTime ct) {
		this.ct = ct;
	}

	public List<CopilotTime> getCopilotTimeList() {
		return copilotTimeList;
	}

	public void setCopilotTimeList(List<CopilotTime> copilotTimeList) {
		this.copilotTimeList = copilotTimeList;
	}

	
	
}
