package com.sms.training.qualificationassess.web.action.copilotUpnumber;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificationassess.bean.CopilotUpnumber;
import com.sms.training.qualificationassess.bean.RouteTurnnumber;
import com.sms.training.qualificationassess.business.ICopilotUpnumberBS;
@Results( {
	@Result(name = "upnumberList", location = "/sms/training/qualificationassess/copilotupnumber/zsList.jsp"),
	@Result(name = "addCopilotUpnumber", location = "/sms/training/qualificationassess/copilotupnumber/zsAdd.jsp"),
	@Result(name = "editCopilotUpnumber", location = "/sms/training/qualificationassess/copilotupnumber/zsEdit.jsp"),
	@Result(name = "searchCopilotUpnumber", location = "/sms/training/qualificationassess/copilotupnumber/zsInfo.jsp"),

	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
	@Result(name = "METHOD", location = "sec-bphase!list", type = "redirectAction"),
	@Result(name = "json", type = "json") })
@ParentPackage("sinoframe-default")
public class CopilotUpnumberAction extends BaseAction {
	private static final long serialVersionUID = 7352624010000649434L;
	// 副驾驶升级资格人数估计
	private CopilotUpnumber copilotUpnumber;
	// service
	private ICopilotUpnumberBS copilotUpnumberBS;
	//副驾驶升级资格人数估计范围集合
	private List<CopilotUpnumber> copilotUpnumberList;
	// Message对象
	private Message message;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// ids串
	private String ids;
	// 副驾驶升级资格人数估计标示
	private String id;
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public String upnumberList() {
		try {
			// 设置默认排序
			SysOrderByInfo orderByInfo = this.getSysOrderByInfo();
			if (orderByInfo.getOrderColumn() == null
					|| "".equals(orderByInfo.getOrderColumn())) {
				orderByInfo.setOrderColumn("type");
				orderByInfo.setOrderAsc("desc");
				orderByInfo.setIfDate(true);
			}
			this.copilotUpnumberList = this.copilotUpnumberBS
					.searchCopilotUpnumberList(this.getSysPageInfo(),this.query, orderByInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "upnumberList";
	}
	/**
	 * 跳转增加副驾驶升级资格人数估计范围页面
	 * 
	 * @return
	 */
	public String addCopilotUpnumber() {
		return "addCopilotUpnumber";
	}

	/**
	 * 保存增加副驾驶升级资格人数估计范围
	 * 
	 * @return
	 */
	public String saveaddCopilotUpnumber() {
		try {
			this.copilotUpnumberBS.addCopilotUpnumber(this.copilotUpnumber,
					this.getUser().getName());
			this.message = this.getSuccessMessage(getText("addSuccess"),
					"sms", "closeCurrent",
					"copilot-upnumber/copilot-upnumber!upnumberList.do");
		} catch (Exception e) {
			this.copilotUpnumberBS.getErrorLog().info(e.getMessage()+"#"+"");
			this.message = this.getFailMessage("保存操作失败！");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	/**
	 * 单个删除
	 * 
	 * @return
	 */
	public String singleDeleteCopilot() {
		try {
			this.copilotUpnumberBS.deleteById(CopilotUpnumber.class, this.id);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"copilot-upnumber/copilot-upnumber!upnumberList.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multipleDeleteCopilot() {
		try {
			this.copilotUpnumberBS.multipleDelet(this.ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"copilot-upnumber/copilot-upnumber!upnumberList.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	/**
	 * 跳转编辑页面
	 * 
	 * @return
	 */
	public String editCopilotUpnumber() {
		this.copilotUpnumber = this.copilotUpnumberBS.findById(
				CopilotUpnumber.class, this.id);
		return "editCopilotUpnumber";
	}

	/**
	 * 编辑副驾驶升级资格人数估计范围
	 * 
	 * @return
	 */
	public String saveeditCopilotUpnumber() {
		try {
			if (this.copilotUpnumber != null) {
				this.copilotUpnumber.setModifyDate(new Date());
				this.copilotUpnumber.setOperate(this.getUser().getName());
				this.copilotUpnumberBS.update(this.copilotUpnumber, this.id);
			}
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"sms", "closeCurrent",
					"copilot-upnumber/copilot-upnumber!upnumberList.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	/**
	 * 查看详细信息
	 * @return
	 */
	public String searchCopilotUpnumber(){
		this.copilotUpnumber = this.copilotUpnumberBS.findById(CopilotUpnumber.class, this.id);
		return "searchCopilotUpnumber";
	}
	
	public CopilotUpnumber getCopilotUpnumber() {
		return copilotUpnumber;
	}
	public void setCopilotUpnumber(CopilotUpnumber copilotUpnumber) {
		this.copilotUpnumber = copilotUpnumber;
	}
	public ICopilotUpnumberBS getCopilotUpnumberBS() {
		return copilotUpnumberBS;
	}
	public void setCopilotUpnumberBS(ICopilotUpnumberBS copilotUpnumberBS) {
		this.copilotUpnumberBS = copilotUpnumberBS;
	}
	public List<CopilotUpnumber> getCopilotUpnumberList() {
		return copilotUpnumberList;
	}
	public void setCopilotUpnumberList(List<CopilotUpnumber> copilotUpnumberList) {
		this.copilotUpnumberList = copilotUpnumberList;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
