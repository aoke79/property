package com.sms.training.qualificationassess.web.action.pilotCapacity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificationassess.bean.PilotCapacity;
import com.sms.training.qualificationassess.bean.RouteTurnnumber;
import com.sms.training.qualificationassess.business.IPilotCapacityBS;
@Results( {
	@Result(name = "capacityList", location = "/sms/training/qualificationassess/pilotCapacity/zsList.jsp"),
	@Result(name = "addPilotCapacity", location = "/sms/training/qualificationassess/pilotCapacity/zsAdd.jsp"),
	@Result(name = "editPilotCapacity", location = "/sms/training/qualificationassess/pilotCapacity/zsEdit.jsp"),
	@Result(name = "searchCapacity", location = "/sms/training/qualificationassess/pilotCapacity/zsInfo.jsp"),

	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
	@Result(name = "METHOD", location = "sec-bphase!list", type = "redirectAction"),
	@Result(name = "json", type = "json") })
@ParentPackage("sinoframe-default")

public class PilotCapacityAction extends BaseAction {
	private static final long serialVersionUID = 7352624010000649434L;
	// 机长与副驾动力范围
	private PilotCapacity pilotCapacity;
	// service
	private IPilotCapacityBS pilotCapacityBS;
	// 机长与副驾范围集合
	private List<PilotCapacity> pilotCapacityList;
	// Message对象
	private Message message;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// ids串
	private String ids;
	// 机长与副驾动力标示
	private String id;
	
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public String capacityList() {
		try {
			// 设置默认排序
			SysOrderByInfo orderByInfo = this.getSysOrderByInfo();
			if (orderByInfo.getOrderColumn() == null
					|| "".equals(orderByInfo.getOrderColumn())) {
				orderByInfo.setOrderColumn("capacityName");
				orderByInfo.setOrderAsc("desc");
				orderByInfo.setIfDate(true);
			}
			this.pilotCapacityList = this.pilotCapacityBS
					.searchPilotCapacityList(this.getSysPageInfo(),this.query, orderByInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "capacityList";
	}
	/**
	 * 跳转增加机长及副驾范围页面
	 * 
	 * @return
	 */
	public String addPilotCapacity() {
		return "addPilotCapacity";
	}

	/**
	 * 保存增加机长及副驾范围
	 * 
	 * @return
	 */
	public String saveaddPilotCapacity() {
		try {
			this.pilotCapacityBS.addPilotCapacity(this.pilotCapacity,
					this.getUser().getName());
			this.message = this.getSuccessMessage(getText("addSuccess"),
					"sms", "closeCurrent",
					"pilot-capacity/pilot-capacity!capacityList.do");
		} catch (Exception e) {
			this.pilotCapacityBS.getErrorLog().info(e.getMessage()+"#"+"");
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
	public String singleDeletePilot() {
		try {
			this.pilotCapacityBS.deleteById(PilotCapacity.class, this.id);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"pilot-capacity/pilot-capacity!capacityList.do");
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
	public String multipleDeletePilot() {
		try {
			this.pilotCapacityBS.multipleDelet(this.ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"pilot-capacity/pilot-capacity!capacityList.do");
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
	public String editPilotCapacity() {
		this.pilotCapacity = this.pilotCapacityBS.findById(
				PilotCapacity.class, this.id);
		return "editPilotCapacity";
	}

	/**
	 * 编辑机长及副驾范围
	 * 
	 * @return
	 */
	public String saveeditPilotCapacity() {
		try {
			if (this.pilotCapacity != null) {
				this.pilotCapacity.setModifyDate(new Date());
				this.pilotCapacity.setOperate(this.getUser().getName());
				this.pilotCapacityBS.update(this.pilotCapacity, this.id);
			}
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"sms", "closeCurrent",
					"pilot-capacity/pilot-capacity!capacityList.do");
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
	public String searchCapacity(){
		this.pilotCapacity = this.pilotCapacityBS.findById(PilotCapacity.class, this.id);
		return "searchCapacity";
	}
	
	public PilotCapacity getPilotCapacity() {
		return pilotCapacity;
	}
	public void setPilotCapacity(PilotCapacity pilotCapacity) {
		this.pilotCapacity = pilotCapacity;
	}
	public IPilotCapacityBS getPilotCapacityBS() {
		return pilotCapacityBS;
	}
	public void setPilotCapacityBS(IPilotCapacityBS pilotCapacityBS) {
		this.pilotCapacityBS = pilotCapacityBS;
	}
	public List<PilotCapacity> getPilotCapacityList() {
		return pilotCapacityList;
	}
	public void setPilotCapacityList(List<PilotCapacity> pilotCapacityList) {
		this.pilotCapacityList = pilotCapacityList;
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
