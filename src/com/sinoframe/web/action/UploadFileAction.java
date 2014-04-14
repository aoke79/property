package com.sinoframe.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.FileConfigSystem;
import com.sinoframe.common.util.FileOperate;
import com.sinoframe.common.util.FileUpload;
import com.sms.security.basicdata.bean.CmAttachment;

@ParentPackage("file-default")
@Results( {
		@Result(name = "list", location = "/system/sysRole/FileUpload.jsp"),
		//@Result(type = "json", name = "json"),
		@Result(name = "downfile", type = "stream", params = { "contentType",
				"application/octet-stream;charset=ISO8859-1",
				"contentDisposition",
				"attachment;filename=${downloadFileName}", "bufferSize",
				"4096", "inputName", "downFile" }),
		@Result(name = "showImage", location = "/commonFiles/image.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp") })
public class UploadFileAction extends BaseAction {
	/**
	 * 此类主要负责文件上传
	 */
	private static final long serialVersionUID = 1L;
	private ICmUserBS cmUserBS;
	private FileConfigSystem fileConfigSystem;
	
	//文件
	private File fileupload;
	//文件类型
	private String fileuploadContentType;
	//文件名字
	private String fileuploadFileName;
	//文件保存路径 暂时没有用
	private String savePath = "D:/niu";
	//文件标题
	private String title;
	//文件存储路径
	private String folder;
	//文件对应的数据库记录ID
	private String attchid;

	private String fileKey;
	private Message message;

	private String tips;
	private String fileSrc;
	private String fileSrcy;
	public String getFileSrcy() {
		return fileSrcy;
	}

	public void setFileSrcy(String fileSrcy) {
		this.fileSrcy = fileSrcy;
	}

	private List<CmAttachment> cmAttachmentList=new ArrayList<CmAttachment>();
	HttpSession session ;
	public String goView() {
		
		//cmAttachmentList=cmUserBS.searchFiles("ER0020111009179");
		
		return "list";
	}

	public String input() {
		this.message = this.getFailMessage("上传失败");
		return "SUCCESS";
	}

	@Override
	public String execute() throws Exception {
		 folder= fileConfigSystem.mappingString("security");
		 fileSrcy = fileSrc;
		 //fileSrc=folder+"\\"+fileSrc;
		 fileSrc=folder+"/"+fileSrc;
		return "downfile";
	}
	
	public String upload() throws Exception {
        boolean bflag=true;

        
        folder= fileConfigSystem.mappingString("security");
       
		//保存文件拓展名
		String extName = ""; 
		//保存新的文件名
		String newFileName = ""; 
		//保存当前时间 用于生产一个唯一的文件名
		String nowTimeStr = ""; 
		SimpleDateFormat sDateFormat;
		Random r = new Random();
		//获取随机数
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; 
		HttpServletResponse response = ServletActionContext.getResponse();
		//务必，防止返回文件名是乱码
		response.setCharacterEncoding("utf-8"); 
		//时间格式化的格式
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		//当前时间
		nowTimeStr = sDateFormat.format(new Date()); 
		//获取拓展名
		if (fileuploadFileName.lastIndexOf(".") >= 0) {
			extName = fileuploadFileName.substring(fileuploadFileName
					.lastIndexOf("."));
		}
		//文件重命名后的名字
		newFileName = nowTimeStr + rannum + extName; 
		//newFileName = fileuploadFileName;
		//保存文件
		try {
		
		new FileUpload(fileupload, fileuploadContentType, newFileName, folder,
				title).uploadFile();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(fileuploadFileName);
		if(bflag){
			
			File file2=new File(folder,newFileName);
			
			//向页面端返回结果信息
//			response.getWriter().print(
//					fileuploadFileName + "_-_" + file2.getAbsolutePath());
			
			response.getWriter().print(
			fileuploadFileName + "_-_" + file2.getName());
			
			
		}else{
			//folder=folder.replaceAll("\\\\", "/");
			//System.out.println(folder);
			//判断用户的代理商 用来判断是IE还是其他的浏览器
			String user_Agent=getServletRequest().getHeader("User-Agent");
		    //如果是IE的话要设置字符的编码为gb2312
			if(user_Agent.indexOf("MSIE")!=-1){
			 response.setCharacterEncoding("gb2312");
			}
			//向页面端返回结果信息
			//response.getWriter().print("<script>parent.callback('"+fileuploadFileName + "_-_" + (folder + "/" + newFileName+"')</script>"));//向页面端返回结果信息
			response.getWriter().print("<script>parent.callback('"+fileuploadFileName + "_-_" + (newFileName+"')</script>"));//向页面端返回结果信息
		}
		
		
		return null;
	}
	
	
	public String test() {
		HashMap<String, String> newAndOld = new HashMap<String, String>();
		try {
			System.out.println(fileKey);
			String[] nameStrings = fileKey.split(",");

			for (String s : nameStrings) {
				String[] nameString = s.split("_-_");
				newAndOld.put(nameString[1], nameString[0]);
			}
			Set<Entry<String, String>> entrySet = newAndOld.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				System.out.println(entry.getKey() + "---------"
						+ entry.getValue());
			}

			//把所有的数据存到数据库中。

			//insert into table

			this.message = this.getSuccessMessage("上传成功", "roleManage",
					"forward", "sys-role/sys-role!list.do");
		} catch (Exception e) {

			//若数据存储失败的话 则根据 newAndOld 中的文件名称把之前上传的文件删除

		}
		return "SUCCESS";
	}

	public String deleteFile() {
		new FileOperate().delFile(fileSrc);
		tips = "1";
		return "json";
	}
	
	public String showImage(){
		return "showImage";
	}

	public String deleteFileSrc() {
		System.out.println(fileSrc);
		 folder= fileConfigSystem.mappingString("security");
		try {
			if(attchid!=null && !"".equals(attchid)){
			  cmUserBS.deleteById(CmAttachment.class, attchid);
			}
			
			//new FileOperate().delFile(folder+"\\"+fileSrc);
			new FileOperate().delFile(folder+"/"+fileSrc);
		} catch (Exception e) {
			
			message = getFailMessage("失败");
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public InputStream getDownFile() {
		
		InputStream inputStream2=null;
		try {
			inputStream2= new FileInputStream (fileSrc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream2;
	}

	/** 提供转换编码后的供下载用的文件名 */

	public String getDownloadFileName() {

			List<CmAttachment> cmAttachments = this.cmUserBS.findPageByQuery(" from  CmAttachment where attchpath='"+this.fileSrcy+"'");
			String fileNameInfo = "";
			if(cmAttachments != null && cmAttachments.size() != 0){
				fileNameInfo = cmAttachments.get(0).getAttchname();
			}
		    String downFileName = fileSrcy;
		try {
			if(fileNameInfo.length() !=0){
				//fileNameInfo = new String(downFileName.replaceAll(fileSrcy, fileNameInfo).getBytes(), "ISO8859-1");
				fileNameInfo = URLEncoder.encode(fileNameInfo, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return (fileNameInfo.length()!=0?fileNameInfo:this.fileSrcy);  
	
	}

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public String getFileuploadContentType() {
		return fileuploadContentType;
	}

	public void setFileuploadContentType(String fileuploadContentType) {
		this.fileuploadContentType = fileuploadContentType;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public static void main(String[] args) {
		
		
		String attchpath="D:\\NIUJINGWEI\\2011112611182212593968.docx";
		
		int s1=attchpath.indexOf(ConstantList.UPLOADPATH);
		System.out.println(s1);
		String substring = attchpath.substring(s1);
		
		System.out.println(substring);
		
		
		
		
	}

//	@JSON(serialize = true)
//	public String getTips() {
//		return tips;
//	}
//
//	public void setTips(String tips) {
//		this.tips = tips;
//	}

	public String getFileSrc() {
		return fileSrc;
	}

	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<CmAttachment> getCmAttachmentList() {
		return cmAttachmentList;
	}

	public void setCmAttachmentList(List<CmAttachment> cmAttachmentList) {
		this.cmAttachmentList = cmAttachmentList;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}

	public String getAttchid() {
		return attchid;
	}

	public void setAttchid(String attchid) {
		this.attchid = attchid;
	}

	public FileConfigSystem getFileConfigSystem() {
		return fileConfigSystem;
	}

	public void setFileConfigSystem(FileConfigSystem fileConfigSystem) {
		this.fileConfigSystem = fileConfigSystem;
	}

}
