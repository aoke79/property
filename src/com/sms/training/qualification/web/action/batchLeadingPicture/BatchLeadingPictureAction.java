package com.sms.training.qualification.web.action.batchLeadingPicture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualPilotSignature;
import com.sms.training.qualification.business.ITfQualPilotSignatureBS;

@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "list", location = "/sms/training/qualification/batchLeadingPicture/batchLeadingPicture.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
})

/**
 * 批量导入图片
 * @author zhangleilei
 */
public class BatchLeadingPictureAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	// 当前模块名称
	private static final String moduleName = "BatchLeadingPicture";
	//消息
	private Message message;
	//飞行员签名 bean
	private TfQualPilotSignature tfQualPilotSignature;
	//飞行员签名 list
	private List<TfQualPilotSignature> tfQualPilotSignatureList = new ArrayList<TfQualPilotSignature>();
	//飞行员签名 service
	private ITfQualPilotSignatureBS tfQualPilotSignatureBS;
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public TfQualPilotSignature getTfQualPilotSignature() {
		return tfQualPilotSignature;
	}
	public void setTfQualPilotSignature(TfQualPilotSignature tfQualPilotSignature) {
		this.tfQualPilotSignature = tfQualPilotSignature;
	}
	public List<TfQualPilotSignature> getTfQualPilotSignatureList() {
		return tfQualPilotSignatureList;
	}
	public void setTfQualPilotSignatureList(
			List<TfQualPilotSignature> tfQualPilotSignatureList) {
		this.tfQualPilotSignatureList = tfQualPilotSignatureList;
	}
	public ITfQualPilotSignatureBS getTfQualPilotSignatureBS() {
		return tfQualPilotSignatureBS;
	}
	public void setTfQualPilotSignatureBS(
			ITfQualPilotSignatureBS tfQualPilotSignatureBS) {
		this.tfQualPilotSignatureBS = tfQualPilotSignatureBS;
	}
	
	/**
	 * 批量导入图片界面
	 * @return
	 */
	public String list() {
		return "list";
	}
	
	/**
	 * 批量导入图片方法
	 * @return
	 */
	public String leading() {
		try {
			//飞行员签名 list
			tfQualPilotSignatureList = tfQualPilotSignatureBS.findPageByQuery("from TfQualPilotSignature");
			if (!tfQualPilotSignatureList.isEmpty()) {
				for (TfQualPilotSignature tfqPilotSignature : tfQualPilotSignatureList) {
					//飞行员签名十位码
					String ploginId = tfqPilotSignature.getPloginId();
					//路径
					String path = "D:/picture/"+ploginId+".png";
					File file = new File(path);
					byte[] fileArray =  this.fileByte(file);
					if (fileArray != null) {
						//添加创建用户及时间
						tfqPilotSignature.setCreator("sys");
						tfqPilotSignature.setCreateDt(new Date());
						//添加图片
						tfqPilotSignature.setPsignatureUrl(fileArray);
						tfQualPilotSignatureBS.update(tfqPilotSignature, tfqPilotSignature.getPsignatureId());
					}
				}
			}
			this.message = this.getSuccessMessage(getText("导入成功"), "", "", "");
		} catch (Exception ex) {
			tfQualPilotSignatureBS.getErrorLog().info(ex.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage(getText("导入失败"));
			ex.printStackTrace();
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 将文件转化成字节数组
	 * 
	 * @param file
	 * @return
	 */
	public byte[] fileByte(File file) {
		byte[] fileArray = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			if (file == null) {
				return null;
			}
			//判断文件是否存在
			if (!file.exists()) {
				return null;
			}
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream(4096);
			byte[] b = new byte[4096];
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			out.flush();
			fileArray = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileArray;
	}
}
