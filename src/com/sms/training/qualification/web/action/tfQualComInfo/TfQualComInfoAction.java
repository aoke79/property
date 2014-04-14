package com.sms.training.qualification.web.action.tfQualComInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.training.qualification.bean.TfQualBaseComment;
import com.sms.training.qualification.business.ITfQualBaseCommentBS;
/**
 * 飞行员评语意见信息维护
 * @author 赵少龙
 */

@Results( {
		@Result(name = "comInfoList", location = "/sms/training/qualification/comInfo/comInfoList.jsp"),
		@Result(name = "comInfoAdd", location = "/sms/training/qualification/comInfo/comInfoAdd.jsp"),
		@Result(name = "comInfoEdit", location = "/sms/training/qualification/comInfo/comInfoEdit.jsp"),
    	@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
    	@Result(name="METHOD",location="tf-qual-com-info/tf-qual-com-info!list",type="redirectAction"),
		@Result(name="toSysRoleList",location="/sms/training/qualification/comInfo/sysRoleList.jsp")
		})
@ParentPackage("sinoframe-default")
public class TfQualComInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//当前模块名称
	private static String moduleName = "TfQualComInfo";
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	//系统表的service接口
	private ITfQualBaseCommentBS tfQualBaseCommentBS;
	//系统表实体Bean
	private TfQualBaseComment tfQualBaseComment;
	private SysRole SysRole;
	//存放查询结果的List集
	private List<TfQualBaseComment> tfQualBaseCommentList= new ArrayList<TfQualBaseComment>();
	//角色信息表
	private List<SysRole> sysRoleList = new ArrayList<SysRole>();
	//Message对象,消息实体
	private Message message;
	//存放ID
    private String  ids;
	// 用于存储查询条件的字符串形式
	private String strQuery;		
	private String fileKey;
	private List<CmAttachment> fileList = new ArrayList<CmAttachment>();	
	//标识页面来源
	private String pageFlag;
	//控件选择id
	private String partationId;
	/**
	 * 跳转至列表页面
	 * @return
	 */
	public String list() {
		try {
			if (strQuery != null && !"".equals(strQuery)) {
				query = Util.toMap(strQuery);
			}
			this.getSysPageInfo().setMaxCount(tfQualBaseCommentBS.commentListCount(Util.decodeQuery(query)));
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderColumn("sysRole.roleName");
			}
		    tfQualBaseCommentList = tfQualBaseCommentBS.searchCommentList(this.getSysPageInfo(), sysOrderByInfo,Util.decodeQuery(query));
		} catch (Exception e) {
			tfQualBaseCommentBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "comInfoList";
	}

	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String toAddPage(){
		return "comInfoAdd";
	}
	
	/**
	 * 跳转至编辑页面
	 * @return
	 */
	
	public String toEditPage(){
		try {
			this.tfQualBaseComment = this.tfQualBaseCommentBS.findById(TfQualBaseComment.class, tfQualBaseComment.getCommentid());
		} catch (RuntimeException e) {
			this.tfQualBaseCommentBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "comInfoEdit";
	}
	/**
	 * 跳转到资质类型列表 或是 根据查询条件显示资质类型列表
	 * @return
	 */
		public String toSysRoleList() {
			try {
				if (strQuery != null && !"".equals(strQuery)) {
					query = Util.toMap(strQuery);
				}
				this.getSysPageInfo().setMaxCount(tfQualBaseCommentBS.roleListCount(Util.decodeQuery(query)));
				SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
				sysRoleList = tfQualBaseCommentBS.searchRoleList(this.getSysPageInfo(), sysOrderByInfo,Util.decodeQuery(query));
			} catch (Exception e) {
				tfQualBaseCommentBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "toSysRoleList";
		}
	/**
     * 保存信息
	 * @return
	 */	
	public String doSave(){
		try {
			this.tfQualBaseCommentBS.saveAndUpdate(tfQualBaseComment);
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName, "closeCurrent", 
					"tf-qual-com-info/tf-qual-com-info!list.do?pageNum="
					+this.getPageNum()+"&numPerPage="+this.getNumPerPage()+"&"+Util.toStrQuery(query));
		} catch (RuntimeException e) {
			this.tfQualBaseCommentBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	
	/**
	 * 删除多条数据
	 */
	public String doDelete(){
		try {
			this.tfQualBaseCommentBS.BatchDeleteByIds(tfQualBaseComment,ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward",
					"tf-qual-com-info/tf-qual-com-info!list.do?pageNum="
					+this.getPageNum()+"&numPerPage="+this.getNumPerPage()+"&"+Util.toStrQuery(query)+"&deleteFlag=true");
		} catch (RuntimeException e) {
			this.tfQualBaseCommentBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除一条记录
	 * @return
	*/ 
	public String delete() {		
		
		try {
			
			tfQualBaseCommentBS.deleteFileById(tfQualBaseComment.getCommentid(), moduleName);
			this.tfQualBaseCommentBS.deleteById(TfQualBaseComment.class, tfQualBaseComment.getCommentid());
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "tf-qual-com-info/tf-qual-com-info!list.do" );
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName,
						"forward", "tf-qual-com-info/tf-qual-com-info!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			tfQualBaseCommentBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize = false)
	public ITfQualBaseCommentBS getTfQualBaseCommentBS() {
		return tfQualBaseCommentBS;
	}

	public void setTfQualBaseCommentBS(ITfQualBaseCommentBS tfQualBaseCommentBS) {
		this.tfQualBaseCommentBS = tfQualBaseCommentBS;
	}

	@JSON(serialize = false)
	public List<TfQualBaseComment> getTfQualBaseCommentList() {
		return tfQualBaseCommentList;
	}

	public void setTfQualBaseCommentList(List<TfQualBaseComment> tfQualBaseCommentList) {
		this.tfQualBaseCommentList = tfQualBaseCommentList;
	}
	
	@JSON(serialize = false)
	public TfQualBaseComment getTfQualBaseComment() {
		return tfQualBaseComment;
	}
	public void setTfQualBaseComment(TfQualBaseComment tfQualBaseComment) {
		this.tfQualBaseComment = tfQualBaseComment;
	}
	
	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public static String getModuleName() {
		return moduleName;
	}

	public static void setModuleName(String moduleName) {
		TfQualComInfoAction.moduleName = moduleName;
	}

	@JSON(serialize = false)
	public SysRole getSysRole() {
		return SysRole;
	}

	public void setSysRole(SysRole sysRole) {
		SysRole = sysRole;
	}

	@JSON(serialize = false)
	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}
	
	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	@JSON(serialize = false)
	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	@JSON(serialize = false)
	public List<CmAttachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<CmAttachment> fileList) {
		this.fileList = fileList;
	}

	@JSON(serialize = false)
	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	
	@JSON(serialize = false)
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@JSON(serialize = false)
	public String getPartationId() {
		return partationId;
	}
	public void setPartationId(String partationId) {
		this.partationId = partationId;
	}

}
