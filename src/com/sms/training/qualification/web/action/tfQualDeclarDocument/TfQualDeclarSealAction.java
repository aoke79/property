package com.sms.training.qualification.web.action.tfQualDeclarDocument;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.ISysOrganizationBS;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclarSeal;
import com.sms.training.qualification.business.ITfQualDeclarSealBS;

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/sms/training/qualification/qualDeclarDocument/qualDeclarSeal/qualDeclarSealList.jsp"),
        @Result(name = "add", location = "/sms/training/qualification/qualDeclarDocument/qualDeclarSeal/qualDeclarSealAdd.jsp"),		
        @Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
    	@Result(name = "updateSuccess", location = "/standard/ajaxUpdateDone.jsp"), 
        @Result(name = "orgList", location = "/sms/training/qualification/qualDeclarDocument/qualDeclarSeal/orgList.jsp"),	
        @Result(name = "updatePage", location = "/sms/training/qualification/qualDeclarDocument/qualDeclarSeal/qualDeclarSealEide.jsp"),	
        @Result(name = "json", type = "json")
       // @Result(name = "SUCCESS", type = "redirectAction" ,location = "tf-qual-declar-document/tf-qual-declar-seal!list.do")	

})

/**
 * 电子印章管理
 * @author zhong
 */
public class TfQualDeclarSealAction extends BaseAction {
	
	private static final long serialVersionUID = -4776000342218258923L;
	// 当前模块名称
	private static final String moduleName = "TfQualDeclarSeal";  //就是菜单<a>连接中的rel值
	
	private ITfQualDeclarSealBS tfQualDeclarSealBS;
	
	private TfQualDeclarSeal tfQualDeclarSeal;
	//组织结构实体对象
	private SysOrganization sysOrganization;
	//组织结构业务层对象
	private ISysOrganizationBS sysOrganizationBS;
	//存放组织机构
	private List<SysOrganization> ListOrganization = new ArrayList<SysOrganization>();
	
	private List<TfQualDeclarSeal> listDeclar = new ArrayList<TfQualDeclarSeal>();
	
	//存储页面查询条件
    private String strQuery;
	
	private String hrid ; 
	//字节流  用来向数据库传输
	private ByteArrayInputStream imgStream;
	//签名文件
	private File myFile;

	//消息实体
	private Message message;
	
	//存放查询对象的HashMap集
    private HashMap<String, String> query  = new HashMap<String, String>();

    //存放批量删除的id
	private String ids;
	
	/**
	 * 获取电子印章list
	 * @return
	 */
	public String list() {
		
		String tempName = "";
		try {
            if (strQuery != null && !"".equals(strQuery)) {
                query = Util.toMap(strQuery);
            }
            
            //查询列表中的条数信息
            String countHql = "select count(*) from TfQualDeclarSeal where 1=1";
            
            if (query.size() > 0) {           	
            	tempName = query.get("like_name");
            	query.clear();
            	query.put("like_organization.name", tempName);
            }          
            //设置最大条数
            this.getSysPageInfo()
                .setMaxCount(this.tfQualDeclarSealBS.getCountByHQL(countHql, Util.decodeQuery(query)));
            
            //列表
            String listHql = "from TfQualDeclarSeal where 1=1";
            
            // 查询结果集
            listDeclar = tfQualDeclarSealBS.findPageByQuery(this.getSysPageInfo(),
                            listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
            query.put("like_name", tempName);//把搜索值置回
        } catch (Exception e) {
            e.printStackTrace();
            this.getSysPageInfo().setCurrentPage(1);
            
            //设置日志信息
            tfQualDeclarSealBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
        }
		
        return "list";
	}
	
	/**
	 * 刷新的方法
	 * @return
	 */
	public String save() {
		
		try {
		    File file = myFile;
		    byte[] ret = this.file2byte(file);
		    tfQualDeclarSeal = new TfQualDeclarSeal();
		    tfQualDeclarSeal.setSeal(ret);
		    tfQualDeclarSeal.setOrganization(sysOrganizationBS.findById(SysOrganization.class, sysOrganization.getId()));
		    tfQualDeclarSealBS.save(tfQualDeclarSeal);
		    
			//设定成功消息
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName,
								"closeCurrent", "tf-qual-declar-document/tf-qual-declar-seal!list.do");
		} catch (Exception e) {
			//设置日志信息
			tfQualDeclarSealBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail"));    
			e.printStackTrace();
		}
		
		return "updateSuccess";
	}
	
	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String toAddPage() {
		return "add";
	}
	
	/**
	 * 按id删除
	 * @return
	 */
	public String delete() {
		
		try {
			this.tfQualDeclarSealBS.deleteById(TfQualDeclarSeal.class, tfQualDeclarSeal.getId());
			
			this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName,
					"forward", "tf-qual-declar-document/tf-qual-declar-seal!list.do" );
		} catch (Exception e) {
			//设置日志信息
			tfQualDeclarSealBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		
		return "SUCCESS";
				
	}
	
	/**
	 * 跳转至修改页面
	 * @return
	 */
	public String toEditPage() {
		
		tfQualDeclarSeal = tfQualDeclarSealBS.findById(TfQualDeclarSeal.class, tfQualDeclarSeal.getId());
			
		return "updatePage";
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String update() {
		
		try {
			File file = myFile;
			byte[] rett =  this.file2byte(file);
			
			TfQualDeclarSeal  tfQualDeclarSealUpdate = tfQualDeclarSealBS.findById(TfQualDeclarSeal.class, tfQualDeclarSeal.getId());
			tfQualDeclarSealUpdate.setSeal(rett);
			  
			tfQualDeclarSealBS.saveOrUpdate(tfQualDeclarSealUpdate);
			this.message = this.getSuccessMessage(getText("updateSuccess"), moduleName,
					"closeCurrent", "tf-qual-declar-document/tf-qual-declar-seal!list.do" );
		} catch(Exception e) {
			//设置日志信息
			tfQualDeclarSealBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		
		return "updateSuccess";
	}
	
    /**
     * 批量删除
     * @return
     */
	public String multiDelete() {
		
		try {
			tfQualDeclarSealBS.deleteByIds(TfQualDeclarSeal.class, ids);
			
			this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName,
					"forward", "tf-qual-declar-document/tf-qual-declar-seal!list.do");
		} catch (Exception e) {
			//设置日志信息
			tfQualDeclarSealBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 根据id取得图片
	 * @return
	 */
	public String showSignature() {
		try {
			TfQualDeclarSeal image= tfQualDeclarSealBS.findUniqueBySingleQuery(TfQualDeclarSeal.class.getSimpleName(), "id", this.getHrid());
			if (image != null && image.getSeal() != null) {
				imgStream = new ByteArrayInputStream(image.getSeal());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "image";
	}
	
	/**
	 * 添加时首先判断是是否已经存在这个组织机构的印章否已经存在
	 * @return
	 */
	public String exist() {
			
		tfQualDeclarSeal = tfQualDeclarSealBS.findUniqueBySingleQuery("TfQualDeclarSeal", "organization.id", this.getSysOrganization().getId());
		this.message = new Message();
		if (tfQualDeclarSeal != null) {
			//数据库已经存在
		    this.message = this.getSuccessMessage("fail", "checkJob","closeCurrent", "tf-qual-declar-document/tf-qual-declar-seal!list.do");
		} else {
			//没有查到
			message.setMessageInfo("success");
		}
		
		return "json";
	}
	
	/**
	 * 查询所有的机构
	 * 
	 */
	public String orgList() {
					
		try {
           if (strQuery != null && !"".equals(strQuery)) {
                query = Util.toMap(strQuery);
           }
            
           //查询列表中的条数信息
           String countHql = "select count(*) from SysOrganization where 1=1";         
            
           //设置最大条数
           this.getSysPageInfo()
                .setMaxCount(this.sysOrganizationBS.getCountByHQL(countHql, Util.decodeQuery(query)));
            
           //列表
           String listHql = "from SysOrganization where 1=1";
                                
           this.setOrderBlock("orgOrder");
           this.setFieldType(true);
           // this.setOrderMethod(1);
           // 查询结果集
           ListOrganization = sysOrganizationBS.findPageByQuery(this.getSysPageInfo(), listHql,
            		                     Util.decodeQuery(query), this.getSysOrderByInfo());
                           
        } catch (Exception e) {
           e.printStackTrace();
           this.getSysPageInfo().setCurrentPage(1);
            
           //设置日志信息
           sysOrganizationBS.getErrorLog().info(e.getMessage() + "#" + "sysOrganizationBS");
        }
		
		return "orgList";
	}
	
	@JSON(serialize = false)
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	@JSON(serialize = false)
	public ByteArrayInputStream getImgStream() {
		return imgStream;
	}
	public void setImgStream(ByteArrayInputStream imgStream) {
		this.imgStream = imgStream;
	}
	@JSON(serialize = false)
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public ISysOrganizationBS getSysOrganizationBS() {
		return sysOrganizationBS;
	}
	@Resource(name="sysOrganizationBS")
	public void setSysOrganizationBS(ISysOrganizationBS sysOrganizationBS) {
		this.sysOrganizationBS = sysOrganizationBS;
	}
	@JSON(serialize=false)
	public ITfQualDeclarSealBS getTfQualDeclarSealBS() {
		return tfQualDeclarSealBS;
	}
	@Resource(name="tfQualDeclarSealBS")
	public void setTfQualDeclarSealBS(ITfQualDeclarSealBS tfQualDeclarSealBS) {
		this.tfQualDeclarSealBS = tfQualDeclarSealBS;
	}
	@JSON(serialize=false)
	public TfQualDeclarSeal getTfQualDeclarSeal() {
		return tfQualDeclarSeal;
	}
	public void setTfQualDeclarSeal(TfQualDeclarSeal tfQualDeclarSeal) {
		this.tfQualDeclarSeal = tfQualDeclarSeal;
	}
	@JSON(serialize=false)
	public String getStrQuery() {
		return strQuery;
	}
	@JSON(serialize=false)
	public List<SysOrganization> getListOrganization() {
		return ListOrganization;
	}
	public void setListOrganization(List<SysOrganization> listOrganization) {
		ListOrganization = listOrganization;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	@JSON(serialize=false)
	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize=false)
	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}
	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}
	@JSON(serialize=false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	@JSON(serialize=false)
	public List<TfQualDeclarSeal> getListDeclar() {
		return listDeclar;
	}
	public void setListDeclar(List<TfQualDeclarSeal> listDeclar) {
		this.listDeclar = listDeclar;
	}
}
