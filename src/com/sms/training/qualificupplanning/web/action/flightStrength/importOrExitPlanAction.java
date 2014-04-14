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
import com.sms.training.qualificupplanning.bean.FlightStrength;
import com.sms.training.qualificupplanning.bean.PlanOfPlane;

@Results({
	@Result(name="show",location="/sms/training/dt/fjz/thirtyPlanImport.jsp"),
	@Result(name="successAjax",location="/standard/ajaxDone.jsp")
})
@ParentPackage("sinoframe-default")
public class importOrExitPlanAction extends BaseAction {
    //引入页面显示的Message bean;
    private Message message;
    //要操作的xls的路径
	public String filePath= "";
	public String sheetName="";
	//封装页面的行的List
	private List<PlanOfPlane> planOfPlaneList = new ArrayList<PlanOfPlane>();
	private String dws2;
	private String jxs2;
	private String fowd;
	//导入计划的bean
	private PlanOfPlane p;
	//模块名称
	private String modlePlan;
	/**
	 * 将excel 显示到页面的方法 
	 * @return
	 */
	public String showExcel(){
		ensureModle();
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
				if(buf.toString().split(",").length==14){ //下个EXCEL要替换 
					 p = new PlanOfPlane();//对象下个EXCEL要替换
					String[] cc = buf.toString().split(",");
					System.out.println(buf.toString());
					int b= 1;
					for(int i=0;i<cc.length;i++){
						//下个EXCEL要替换
						p.setDepartment(cc[0]);
						p.setPlanType(cc[0+b]);
						p.setOneMonth(cc[1+b]);
						p.setTwoMonth(cc[2+b]);
						p.setThreeMonth(cc[3+b]);
						p.setFourMonth(cc[4+b]);
						p.setFiveMonth(cc[5+b]);
						p.setSixMonth(cc[6+b]);
						p.setSeventMonth(cc[7+b]);
						p.setEightMonth(cc[8+b]);
						p.setNineMonth(cc[9+b]);
						p.setTenMonth(cc[10+b]);
						p.setElevenMonth(cc[11+b]);
						p.setTwelveMonth(cc[12+b]);
						
					}
					

					
						this.planOfPlaneList.add(p);
					    List<PlanOfPlane> listNew = new ArrayList<PlanOfPlane>();

					    if(this.dws2 != null && this.dws2.length() !=0){
					    	//拍段选择了单位和机型
					    	if(this.dws2 != null && this.dws2.length() !=0 && this.jxs2 != null && this.jxs2.length()!=0){
					    	    String[] dwsArray =  this.dws2.split(",");
					    	    String[] jxsArray =  this.jxs2.split(",");
					    	    
					    	    for(int ii=0;ii<dwsArray.length;ii++){
					    	    	
					    			for(PlanOfPlane fs : planOfPlaneList){
                                             if(fs.getDepartment().equals(dwsArray[ii]) && fs.getPlanType().equals(jxsArray[ii])){
                                            	 listNew.add(fs);
                                             }
					    			}
					    	    }
					    	    

					    	    
					    	}
					    		//只选择了单位
					    		if((this.dws2 != null && this.dws2.length() !=0) && (this.jxs2 == null || this.jxs2.length()==0)){
					    			String[] dwsArray = this.dws2.split(",");
					    			
					    			for(PlanOfPlane fs : planOfPlaneList){
					    				for(int i=0 ;i<dwsArray.length;i++){
					    					if(fs.getDepartment().trim().equals(dwsArray[i].trim())){
					    						listNew.add(fs);
					    					}
					    				}
					    			}
					    			
					    		}
					    		planOfPlaneList = listNew;
					    }  
				}
			}
			

		
   		 if(this.fowd != null && this.fowd.equals("no") ){
			 List<PlanOfPlane> nullInfo = new ArrayList<PlanOfPlane>();
			
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
			if(this.p != null){
				String[] department = p.getDepartment().split(",");
				String[] planType = p.getPlanType().split(",");
				for(int i=0; i< department.length; i++){
					PlanOfPlane pp = new PlanOfPlane();
					pp.setDepartment(department[i].trim());
					pp.setPlanType(planType[i].trim());
					pp.setOneMonth(p.getOneMonth().split(",")[i].trim());
					pp.setTwoMonth(p.getTwoMonth().split(",")[i].trim());
					pp.setThreeMonth(p.getThreeMonth().split(",")[i].trim());
					pp.setFourMonth(p.getFourMonth().split(",")[i].trim());
					pp.setFiveMonth(p.getFiveMonth().split(",")[i].trim());
					pp.setSixMonth(p.getSixMonth().split(",")[i].trim());
					pp.setSeventMonth(p.getSeventMonth().split(",")[i].trim());
					pp.setEightMonth(p.getEightMonth().split(",")[i].trim());
					pp.setNineMonth(p.getNineMonth().split(",")[i].trim());
					pp.setTenMonth(p.getTenMonth().split(",")[i].trim());
					pp.setElevenMonth(p.getElevenMonth().split(",")[i].trim());
					pp.setTwelveMonth(p.getTwelveMonth().split(",")[i].trim());
					this.planOfPlaneList.add(pp);
				}
				if(this.planOfPlaneList.size()>0){
					ensureModle();
					JxlUtil jxl = new JxlUtil(this.filePath,this.sheetName,true);
					int hang = 5;
					for(int x=0; x< this.planOfPlaneList.size(); x++){
						
						if(hang <= 20){
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getOneMonth()), hang, 6);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getTwoMonth()), hang, 7);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getThreeMonth()), hang, 8);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getFourMonth()), hang, 9);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getFiveMonth()), hang, 10);							
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getSixMonth()), hang, 11);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getSeventMonth()), hang, 12);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getEightMonth()), hang, 13);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getNineMonth()), hang, 14);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getTenMonth()), hang, 15);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getElevenMonth()), hang, 16);
							jxl.writeExcel(Integer.valueOf(planOfPlaneList.get(x).getTwelveMonth()), hang,17);
							
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
	
	public void ensureModle(){
		if(this.modlePlan.equals("thirtyImport")){
			this.sheetName="2013年飞机引进计划";
			this.filePath ="E:\\e\\2013年飞机引进计划.xls";
		}else if(this.modlePlan.equals("fortyImport")){
			this.sheetName = "2014年飞机引进计划";
			this.filePath ="E:\\e\\2014年飞机引进计划.xls";
		}else if(this.modlePlan.equals("fiftyImport")){
			this.sheetName = "2015年飞机引进计划";
			this.filePath ="E:\\e\\2015年飞机引进计划.xls";
		}else if(this.modlePlan.equals("thirtyExit")){
			this.sheetName = "2013年飞机退出计划";
			this.filePath ="E:\\e\\2013年飞机退出计划.xls";
		}else if(this.modlePlan.equals("fortyExit")){
			this.sheetName = "2014年飞机退出计划";
			this.filePath ="E:\\e\\2014年飞机退出计划.xls";
		}else if(this.modlePlan.equals("fiftyExit")){
			this.sheetName = "2015年飞机退出计划";
			this.filePath ="E:\\e\\2015年飞机退出计划.xls";
		}
	}
	
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<PlanOfPlane> getPlanOfPlaneList() {
		return planOfPlaneList;
	}

	public void setPlanOfPlaneList(List<PlanOfPlane> planOfPlaneList) {
		this.planOfPlaneList = planOfPlaneList;
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

	public PlanOfPlane getP() {
		return p;
	}

	public void setP(PlanOfPlane p) {
		this.p = p;
	}

	public String getModlePlan() {
		return modlePlan;
	}

	public void setModlePlan(String modlePlan) {
		this.modlePlan = modlePlan;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	

	
}
