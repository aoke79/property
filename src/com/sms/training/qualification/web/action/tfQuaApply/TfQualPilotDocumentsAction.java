package com.sms.training.qualification.web.action.tfQuaApply;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.common.FileConfigSystem;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.QuaCmUser;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotDocuments;
import com.sms.training.qualification.business.ITfQualPilotDocumentsBS;

@Results({ 
	@Result(name = "success", location = "/standard/ajaxDone.jsp"), 
	@Result(name = "updateSuccess", location = "/standard/ajaxUpdateDone.jsp"), 
	@Result(name = "fileList", location = "/sms/training/qualification/quaApply/fileList.jsp"),
	@Result(name="tmp",location="tf-qua-apply/tf-qual-pilot-documents!toFileList.do",params={"tfQualDeclaraPilot.detailsid","${tfQualDeclaraPilot.detailsid}"}, type="redirectAction"),
	@Result(name="json",type="json")
	
})
@ParentPackage("sinoframe-default")
public class TfQualPilotDocumentsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	//申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//上传资料列表
	private List<TfQualPilotDocuments> tfQualPilotDocumentsList;
	//
	private FileConfigSystem fileConfigSystem;
	//消息实体
	private Message message;
	//上传的资料
	private TfQualPilotDocuments tfQualPilotDocument;
	//
	private ITfQualPilotDocumentsBS tfQualPilotDocumentsBS; 
	// 上传文件
	private File myFile;
	// 上传文件类型
	private String contentType;
	// 上传文件名
	private String FileName;
	private String OrganizationName;
	private static final String moduleName = "TfQualPilotDocumentsAction"; 
	private String topDiv;
	private boolean hasUploaded;
	//资质类型分组（小类）
	private String subGroupId;
	
	//定义一个list 用来存放是否有修改操作的标志
	private List<String> statusList=new ArrayList<String>();
	private QuaCmUser cmuser;
	private int curCount;
	public TfQualPilotDocumentsAction() {
	}
	
	/**
	 * 显示已上传文件列表
	 * @return
	 */
	public String toFileList(){
		try{
			SysOrganization sysOrganization = getUserOrg();
			OrganizationName = sysOrganization.getName();
			tfQualDeclaraPilot=tfQualPilotDocumentsBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			subGroupId=tfQualDeclaraPilot.getTfQualBaseType().getTfQualBaseTgroup().getTypegroupid();
			tfQualPilotDocumentsList=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilot.getDetailsid());
			if(tfQualPilotDocumentsList.size() !=0){
				for(int t=0,len=tfQualPilotDocumentsList.size();t<len;t++){
					String creater = tfQualPilotDocumentsList.get(t).getCmuser().getUserId();
					statusList.add(getUser().getUserId().equals(creater)?"Y":"N");
				}
			}			
		}catch (Exception e) {
			tfQualPilotDocumentsBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "fileList";
	}

	/**
	 * 文件上传
	 * @return
	 */
	public String upload(){
		try {
			tfQualDeclaraPilot=tfQualPilotDocumentsBS.findById(TfQualDeclaraPilot.class, tfQualDeclaraPilot.getDetailsid());
			cmuser = tfQualPilotDocumentsBS.findById(QuaCmUser.class, getUser().getUserId());
			String folder= fileConfigSystem.mappingString("qualification");
			String dstName=genDstName(FileName);
			uploadFile( myFile , folder+File.separator+dstName );
			tfQualPilotDocument=new TfQualPilotDocuments(tfQualDeclaraPilot,FileName, dstName, new Date(), cmuser);
			
			tfQualPilotDocumentsBS.save(tfQualPilotDocument);
			this.message=this.getSuccessMessage("上传成功！", "fileList", "", "");////
		} catch (Exception e) {
			this.message=this.getFailMessage("上传失败！");
			tfQualPilotDocumentsBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "updateSuccess";
	}
	
	/**
	 * 文件上传，文件的复制
	 * @param file
	 * @param path
	 * @throws IOException
	 */
	private void uploadFile(File file,String path) throws IOException {
		// 以服务的文件保存路径和原文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(new File(path));
		// 以每个需要上传的文件建立文件文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 将每个需要上传的文件写到服务器对应的文件中
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.flush();
		fos.close();
	}
	
	//生成上传文件名
	private String genDstName(String srcName){
		int index=srcName.lastIndexOf(".");
		return  System.currentTimeMillis() + ( index>0 ? srcName.substring(index) : "");
	}
	
	/**
	 * 删除记录及对应文件
	 * @return
	 */
	public String delete(){
		try{
			tfQualPilotDocument=tfQualPilotDocumentsBS.findById(TfQualPilotDocuments.class, tfQualPilotDocument.getDocid());
			String folder= fileConfigSystem.mappingString("qualification");
			File file= new File(folder, tfQualPilotDocument.getDocurl());
			if(file.exists()){
				file.delete();
			}
			tfQualPilotDocumentsBS.delete(tfQualPilotDocument);
			this.message=this.getSuccessMessage(getText("deleteSuccess"), "fileList", "forward", "tf-qua-apply/tf-qual-pilot-documents!toFileList.do?tfQualDeclaraPilot.detailsid="+tfQualDeclaraPilot.getDetailsid()+"&topDiv="+topDiv+"&subGroupId="+subGroupId);
		}catch (Exception e) {
			tfQualPilotDocumentsBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 文件下载
	 */
	public void fileDownLoad() {
		tfQualPilotDocument = tfQualPilotDocumentsBS.findById(TfQualPilotDocuments.class, tfQualPilotDocument.getDocid());
		String realpath = fileConfigSystem.mappingString("qualification")+ File.separator + tfQualPilotDocument.getDocurl();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fileName = tfQualPilotDocument.getDocname();
		try {
			getServletResponse().setContentType("APPLICATION/OCTET-STREAM");// ///
			getServletResponse().setHeader(	"Content-disposition","attachment;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8"));
			bis = new BufferedInputStream(new FileInputStream(realpath));
			bos = new BufferedOutputStream(getServletResponse().getOutputStream());
			int bytesRead = 0;
			byte[] buffer = new byte[5 * 1024];
			while ((bytesRead = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
			}
			bos.close();
			bis.close();
		} catch (IOException e) {
			getServletResponse().reset();
			tfQualPilotDocumentsBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				System.err.print(e);
			}
		}
	}
	
//	public String hasUploaded(){
//		try{
//			List<TfQualPilotDocuments> documents=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilot.getDetailsid());
//			if(documents!=null && documents.size()>0){
//				hasUploaded=documents.get(0).getCmuser().getUserId().equals(getUser().getUserId()) ;
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "json";
//	}
	
	public String curUploaded(){
		try{
			curCount=tfQualPilotDocumentsBS.findDocumentsByDetailId(tfQualDeclaraPilot.getDetailsid(),getUser().getUserId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}
	//===================getters and setters ==========================
	
	
	
	public boolean isHasUploaded() {
		return hasUploaded;
	}
	
	public void setHasUploaded(boolean hasUploaded) {
		this.hasUploaded = hasUploaded;
	}
	
	public void setMyFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setMyFileFileName(String fileName) {
		this.FileName = fileName;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	@JSON(serialize = false)
	public ITfQualPilotDocumentsBS getTfQualPilotDocumentsBS() {
		return tfQualPilotDocumentsBS;
	}
	public void setTfQualPilotDocumentsBS(ITfQualPilotDocumentsBS tfQualPilotDocumentsBS) {
		this.tfQualPilotDocumentsBS = tfQualPilotDocumentsBS;
	}

	@JSON(serialize = false)
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}
	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@JSON(serialize = false)
	public List<TfQualPilotDocuments> getTfQualPilotDocumentsList() {
		return tfQualPilotDocumentsList;
	}
	public void setTfQualPilotDocumentsList(
			List<TfQualPilotDocuments> tfQualPilotDocumentsList) {
		this.tfQualPilotDocumentsList = tfQualPilotDocumentsList;
	}

	@JSON(serialize = false)
	public FileConfigSystem getFileConfigSystem() {
		return fileConfigSystem;
	}
	public void setFileConfigSystem(FileConfigSystem fileConfigSystem) {
		this.fileConfigSystem = fileConfigSystem;
	}

	@JSON(serialize = false)
	public TfQualPilotDocuments getTfQualPilotDocument() {
		return tfQualPilotDocument;
	}
	public void setTfQualPilotDocument(TfQualPilotDocuments tfQualPilotDocument) {
		this.tfQualPilotDocument = tfQualPilotDocument;
	}

	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize = false)
	public String getOrganizationName() {
		return OrganizationName;
	}
	
	@JSON(serialize = false)
	public String getTopDiv() {
		return topDiv;
	}
	public void setTopDiv(String topDiv) {
		this.topDiv = topDiv;
	}

	@JSON(serialize = false)
	public String getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}
	@JSON(serialize = false)
	public QuaCmUser getCmuser() {
		return cmuser;
	}

	public void setCmuser(QuaCmUser cmuser) {
		this.cmuser = cmuser;
	}
	@JSON(serialize = false)
	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	//此处不要加 flase
	public int getCurCount() {
		return curCount;
	}

	
}
