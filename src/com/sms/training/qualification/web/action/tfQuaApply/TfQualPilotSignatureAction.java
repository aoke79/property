package com.sms.training.qualification.web.action.tfQuaApply;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.Message;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Md5;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;

import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotSignature;
import com.sms.training.qualification.business.ITfQualPilotSignatureBS;

@ParentPackage("json-default")
@Results({
		@Result(name = "add", location = "/sms/training/qualification/quaApply/qualPilotSignature/qualPilotSignatureAdd.jsp"),
		@Result(name = "edit", location = "/sms/training/qualification/quaApply/qualPilotSignature/qualPilotSignatureEdit.jsp"),
		@Result(name = "list", location = "/sms/training/qualification/quaApply/qualPilotSignature/qualPilotSignatureList.jsp"),
		@Result(name = "pilotList", location = "/sms/training/qualification/quaApply/qualPilotSignature/pilotList.jsp"),
		@Result(name = "success", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json"),
		@Result(name = "updateSuccess", location = "/standard/ajaxUpdateDone.jsp"),
		@Result(name = "image", params = {"inputName", "imgStream"}, type = "stream")
})

/**
 * 飞行员签名维护
 * @author licumn
 */
public class TfQualPilotSignatureAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	// 当前模块名称
	private static final String moduleName = "TfQualPilotSignature";
	// 消息实体
	private Message message;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 存放ID
	private String ids;
	//飞行员签名   实体
	private TfQualPilotSignature tfQualPilotSignature;
	//飞行员签名list
	private List<TfQualPilotSignature> pilotSignatureList;
	//飞行员list
	private List<CmPeople> pilotList;
	//飞行员签名 service
	private ITfQualPilotSignatureBS tfQualPilotSignatureBS;
	//文件上传要用的
	private String fileKey;
	//附件list
	private List<CmAttachment> fileList = new ArrayList<CmAttachment>();
	//=======================================================
	//飞行员账号 ploginId(即hrid)
	private String account;
	//旧密码
	private String oldPassword;
	//新密码
	private String newPassword;
	//ajax - json返回是否成功标志 
	private boolean right;
	//申报人员明细id
	private String detailId;
	//飞行员签字日期
	private String pilotSignDate;
	//签名文件
//	private String fileurl;
	private File myFile;
	//字节流  用来向数据库传输
	private ByteArrayInputStream imgStream;
	private String hrid;
	
	public TfQualPilotSignatureAction() {
	}
	
	/**
	 * 获取飞行员签名list
	 * @return
	 */
	public String list() {
		try {
			pilotSignatureList = tfQualPilotSignatureBS.findPilotSigList(this.getSysPageInfo(), Util.decodeQuery(query),this.getSysOrderByInfo());
		} catch (Exception e) {
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		
		return "list";
	}
	
	/**
	 * 飞行员签名认证 与 修改密码验证
	 * @return
	 */
	public String valid() {
		try {
			if(detailId != null && !detailId.trim().equals("")) {
				//当detailId不空时，根据detailId查找到对应的飞行员，验证签名账号是否与申请人信息一致
				TfQualDeclaraPilot detail = tfQualPilotSignatureBS.findById(TfQualDeclaraPilot.class, detailId);
				String hrid = detail.getCmPeople().getHrid();
				if(hrid == null || hrid.equals("") || !hrid.equals(account)) {
					right = false;
					return "json";
				}
			}
			//根据账号、密码进行验证
			right = tfQualPilotSignatureBS.valid(account,oldPassword);
		} catch (Exception e) {
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			right = false;
		}
		
		return "json";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modPwd() {
		try {
			tfQualPilotSignature = tfQualPilotSignatureBS.findUniqueBySingleQuery("TfQualPilotSignature", "ploginId", account);
			tfQualPilotSignature.setPpassword(Md5.getInstance().EncoderByMd5(newPassword, ""));
			this.tfQualPilotSignatureBS.update(this.tfQualPilotSignature, tfQualPilotSignature.getPsignatureId());
			right = true;
		} catch (Exception e) {
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			right = false;
		}
		
		return "json";
	}
	
	/**
	 * 获取飞行员签名信息
	 * @return
	 */
	public String getPilotSign() {
		tfQualPilotSignature = tfQualPilotSignatureBS.findUniqueBySingleQuery("TfQualPilotSignature", "ploginId", account);
		pilotSignDate = DateTool.formatDate(new Date(), "yyyy年MM月dd日");
		
		return "json";
	}
	
	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String toAddPage() {
		return "add";
	}
	
	/**
	 * 添加飞行员签名信息时，判断是否已经存在
	 * @return
	 */
	public String exist() {
		List<TfQualPilotSignature> list = tfQualPilotSignatureBS.findPageByQuery("from TfQualPilotSignature ps where ps.ploginId ='"+tfQualPilotSignature.getPloginId().trim()+"'");
		right = (list.size() == 0);
		
		return "json";
	}
	
	/**
	 * 保存新添加的签名信息   张会粉
	 * @return
	 */
	public String save() {
		try{
		    File file = myFile;
		    byte[] ret = this.file2byte(file);
            tfQualPilotSignature.setPsignatureUrl(ret);
			String pwd = Md5.getInstance().EncoderByMd5(tfQualPilotSignature.getPloginId().substring(5), "");
			tfQualPilotSignature.setPpassword(pwd);
			tfQualPilotSignature.setCreateDt(new Date());
			tfQualPilotSignature.setCreator(getUser().getName());
			tfQualPilotSignatureBS.save(tfQualPilotSignature);
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName,
					"closeCurrent", "tf-qua-apply/tf-qua-signature!list.do&" + Util.toStrQuery(query));
		} catch (Exception e) {
			//设置日志信息
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
		
		return "updateSuccess";
	}
	
	/**
	 * 获取飞行员列表
	 * @return
	 */
	public String pilotList() {
		try {
			pilotList = tfQualPilotSignatureBS.findPilotList(
					this.getSysPageInfo(), Util.decodeQuery(query),
					this.getSysOrderByInfo());
		} catch (Exception e) {
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		
		return "pilotList";
	}
	
	/**
	 * 跳转到编辑页
	 * @return
	 */
	public String toEditPage() {
		tfQualPilotSignature = tfQualPilotSignatureBS.findById(
				TfQualPilotSignature.class, tfQualPilotSignature.getPsignatureId());
		fileList = tfQualPilotSignatureBS.searchFiles(tfQualPilotSignature.getPsignatureId(), moduleName);	
		
		return "edit";
	}

	/**
	 * 更新签名信息   张会粉
	 * @return
	 */
	public String update() {
		try {
			tfQualPilotSignature = tfQualPilotSignatureBS.findById(tfQualPilotSignature.getClass(), tfQualPilotSignature.getPsignatureId());
			File file = myFile;
		    byte[] ret = this.file2byte(file);
            tfQualPilotSignature.setPsignatureUrl(ret);
			tfQualPilotSignature.setModifiedDt(new Date());
			tfQualPilotSignature.setModifier(getUser().getName());
			tfQualPilotSignatureBS.saveOrUpdate(tfQualPilotSignature);
			this.message = this.getSuccessMessage(getText("updateSuccess"), moduleName,"closeCurrent", "");
			
		} catch (Exception e) {
			//设置日志信息
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("updateFail"));    
			e.printStackTrace();
		}
		
		return "updateSuccess";
	}

	/**
	 * 单条删除
	 * @return
	 */
	public String delete() {
		try {
			tfQualPilotSignatureBS.deleteFileById(tfQualPilotSignature.getPsignatureId(), moduleName);
			
			this.tfQualPilotSignatureBS.delete(tfQualPilotSignature);
			
			this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName,
					"forward", "tf-qua-apply/tf-qual-pilot-signature!list.do" );
		} catch (Exception e) {
			//设置日志信息
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDelete() {
		try {
			if(ids != null && !ids.trim().equals("")) {
				tfQualPilotSignatureBS.deleteByIds(TfQualPilotSignature.class, ids);
				String[] signids = ids.split(",");			
				for(String id : signids) {
					tfQualPilotSignatureBS.deleteFileById(id, moduleName);				
				}
			}
			this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName,
					"forward", "tf-qua-apply/tf-qual-pilot-signature!list.do");
		} catch (Exception e) {
			//设置日志信息
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 删除数据库签名样式
	 */
//	public String deleteImage() {
//		try {
//			fileList = tfQualPilotSignatureBS.searchFiles(tfQualPilotSignature.getPsignatureId(), moduleName);	
//			String imgUrl = fileList.isEmpty() ? "" : ( fileList.get(0).getAttchpath() == null ? "" : fileList.get(0).getAttchpath());
//			tfQualPilotSignature.setPsignatureUrl(imgUrl);
//			tfQualPilotSignatureBS.update(tfQualPilotSignature, tfQualPilotSignature.getPsignatureId());
//			this.message = this.getSuccessMessage(getText("deleteSuccess"), "",	"", "");
//		} catch (Exception e) {
//			//设置日志信息
//			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//			//设定失败消息
//			this.message = this.getFailMessage(getText("deleteFail"));
//			e.printStackTrace();
//		}
//		return SUCCESS;
//	}
	
	// ========================================================================================

	/**
	 * 读取飞行员签名照片方法   张会粉
	 * * @param hrid
	 * @return
	 */
	public String showSignature() {
		try {
			TfQualPilotSignature image = tfQualPilotSignatureBS.findUniqueBySingleQuery(TfQualPilotSignature.class.getSimpleName(), "ploginId", hrid);
			if(image != null && image.getPsignatureUrl() != null){
				imgStream = new ByteArrayInputStream(image.getPsignatureUrl());
			}
		} catch (Exception e) {
			tfQualPilotSignatureBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage(getText("showFail"));
			e.printStackTrace();
		}
		
		return "image";
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TfQualPilotSignature getTfQualPilotSignature() {
		return tfQualPilotSignature;
	}

	public void setTfQualPilotSignature(
			TfQualPilotSignature tfQualPilotSignature) {
		this.tfQualPilotSignature = tfQualPilotSignature;
	}
	@JSON(serialize = false)
	public ITfQualPilotSignatureBS getTfQualPilotSignatureBS() {
		return tfQualPilotSignatureBS;
	}

	public void setTfQualPilotSignatureBS(
			ITfQualPilotSignatureBS tfQualPilotSignatureBS) {
		this.tfQualPilotSignatureBS = tfQualPilotSignatureBS;
	}
	@JSON(serialize = false)
	public List<TfQualPilotSignature> getPilotSignatureList() {
		return pilotSignatureList;
	}

	public void setPilotSignatureList(
			List<TfQualPilotSignature> pilotSignatureList) {
		this.pilotSignatureList = pilotSignatureList;
	}
	@JSON(serialize = false)
	public List<CmPeople> getPilotList() {
		return pilotList;
	}

	public void setPilotList(List<CmPeople> pilotList) {
		this.pilotList = pilotList;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public List<CmAttachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<CmAttachment> fileList) {
		this.fileList = fileList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getPilotSignDate() {
		return pilotSignDate;
	}

	public void setPilotSignDate(String pilotSignDate) {
		this.pilotSignDate = pilotSignDate;
	}
//	@JSON(serialize = false)
//	public String getFileurl() {
//		return fileurl;
//	}
//	public void setFileurl(String fileurl) {
//		this.fileurl = fileurl;
//	}
	@JSON(serialize = false)
	public ByteArrayInputStream getImgStream() {
		return imgStream;
	}
	public void setImgStream(ByteArrayInputStream imgStream) {
		this.imgStream = imgStream;
	}
	@JSON(serialize = false)
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	@JSON(serialize = false)
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
}
