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
import com.sms.training.qualificupplanning.bean.PlanOfPlane;

@Results({
	@Result(name="successAjax",location="/standard/ajaxDone.jsp"),
	@Result(name="show",location="/sms/training/dt/fjz/copilotRatio.jsp")
})
@ParentPackage("sinoframe-default")
public class copilotRatioAction extends BaseAction {
    private String sheetName = "机长副驾驶人数比";
    private Message message;
    private String filePath="E:\\e\\机长副驾驶人数比.xls";
    private String dws2;
	private String jxs2;
	private String fowd;
	//导入机长副驾驶人数比的bean
	private CopilotRatio cp;
	private List<CopilotRatio> CopilotRatioList = new ArrayList<CopilotRatio>();
	
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
				if(buf.toString().split(",").length==8){ //下个EXCEL要替换 
					 cp = new CopilotRatio();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换
						cp.setDepartment(cc[0]);
						cp.setPlaneType(cc[0+b]);
						cp.setThirtyFirstHalfYear(cc[1+b]);
						cp.setThirtySecondHalf(cc[2+b]);
						cp.setFortyFirstHalfYear(cc[3+b]);
						cp.setFortySecondHalf(cc[4+b]);
						cp.setFiftyFirstHalfYear(cc[5+b]);
						cp.setFiftySecondHalf(cc[6+b]);
						
					}
					

					
						this.CopilotRatioList.add(cp);
					    List<CopilotRatio> listNew = new ArrayList<CopilotRatio>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
					    	    for(int ii=0;ii<dwsArray.length;ii++){
					    	    	
					    			for(CopilotRatio fs : CopilotRatioList){
                                             if(fs.getDepartment().equals(dwsArray[ii]) && fs.getPlaneType().equals(jxsArray[ii])){
                                            	 listNew.add(fs);
                                             }
					    			}
					    	    }
					    	    

					    	    
					    	}
					    		//只选择了单位
					    		if((this.dws2 != null && this.dws2.length() !=0) && (this.jxs2 == null || this.jxs2.length()==0)){
					    			String[] dwsArray = this.dws2.split(",");
					    			
					    			for(CopilotRatio fs : CopilotRatioList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDepartment().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    		CopilotRatioList = listNew;
					    }  
				}
			}
			

		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<CopilotRatio> nullInfo = new ArrayList<CopilotRatio>();
			
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
			if(this.cp != null){
				String[] department = cp.getDepartment().split(",");
				String[] planType = cp.getPlaneType().split(",");
				for(int i=0; i< department.length; i++){
					CopilotRatio pp = new CopilotRatio();
					pp.setDepartment(department[i].trim());
					pp.setPlaneType(planType[i].trim());
					pp.setThirtyFirstHalfYear(cp.getThirtyFirstHalfYear().split(",")[i].trim());
				    pp.setThirtySecondHalf(cp.getThirtySecondHalf().split(",")[i].trim());
				    pp.setFortyFirstHalfYear(cp.getFortyFirstHalfYear().split(",")[i].trim());
				    pp.setFortySecondHalf(cp.getFortySecondHalf().split(",")[i].trim());
				    pp.setFiftyFirstHalfYear(cp.getFiftyFirstHalfYear().split(",")[i].trim());
				    pp.setFiftySecondHalf(cp.getFiftySecondHalf().split(",")[i].trim());
					this.CopilotRatioList.add(pp);
				}
				if(this.CopilotRatioList.size()>0){
					
					JxlUtil jxl = new JxlUtil(this.filePath,this.sheetName,true);
					int hang = 5;
					for(int x=0; x< this.CopilotRatioList.size(); x++){
						
						if(hang <= 20){
							jxl.writeExcel(Float.valueOf(CopilotRatioList.get(x).getThirtyFirstHalfYear()), hang, 6);
							jxl.writeExcel(Float.valueOf(CopilotRatioList.get(x).getThirtySecondHalf()), hang, 7);
							jxl.writeExcel(Float.valueOf(CopilotRatioList.get(x).getFortyFirstHalfYear()), hang, 8);
							jxl.writeExcel(Float.valueOf(CopilotRatioList.get(x).getFortySecondHalf()), hang, 9);
							jxl.writeExcel(Float.valueOf(CopilotRatioList.get(x).getFiftyFirstHalfYear()), hang, 10);							
							jxl.writeExcel(Float.valueOf(CopilotRatioList.get(x).getFiftySecondHalf()), hang, 11);
							
							
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public CopilotRatio getCp() {
		return cp;
	}

	public void setCp(CopilotRatio cp) {
		this.cp = cp;
	}

	public List<CopilotRatio> getCopilotRatioList() {
		return CopilotRatioList;
	}

	public void setCopilotRatioList(List<CopilotRatio> copilotRatioList) {
		CopilotRatioList = copilotRatioList;
	}
	
	
	
}
