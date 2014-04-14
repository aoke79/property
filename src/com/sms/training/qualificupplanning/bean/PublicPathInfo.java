package com.sms.training.qualificupplanning.bean;

/**
 * 用于路径的存放和修改
 * @author LUJIE
 *
 */
public class PublicPathInfo {
	//LINGO 的EXE路径
	private String lingoPath = "lingo";
	//EXCEL的 EXE路径
	private String excelPath = "excel";
	
	public String excelFilePath = excelPath +"  "+"excleFile-------";
	
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public String getLingoPath() {
		return lingoPath;
	}
	public void setLingoPath(String lingoPath) {
		this.lingoPath = lingoPath;
	}
	public String getExcelPath() {
		return excelPath;
	}
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

}
