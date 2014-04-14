package com.sms.training.qualificationassess.web.action.pilotTurnroute;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificationassess.bean.PilotTurnroute;
import com.sms.training.qualificationassess.bean.RouteTurnnumber;
import com.sms.training.qualificationassess.business.IPilotTurnrouteBS;


@Results( {
	@Result(name = "pilotTurnrouteList", location = "/sms/training/qualificationassess/pilotturnroute/zsList.jsp"),
	@Result(name = "addModelPilotTurnroute", location = "/sms/training/qualificationassess/pilotturnroute/zsAdd.jsp"),
	@Result(name = "editModelPilotTurnroute", location = "/sms/training/qualificationassess/pilotturnroute/zsEdit.jsp"),
	@Result(name = "searchModelPilotTurnroute", location = "/sms/training/qualificationassess/pilotturnroute/zsInfo.jsp"),

	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
	@Result(name = "METHOD", location = "sec-bphase!list", type = "redirectAction"),
	@Result(name = "json", type = "json") })
@ParentPackage("sinoframe-default")

public class PilotTurnrouteAction extends BaseAction {
	private static final long serialVersionUID = 7352624010000649434L;
	// 机长转机型路径范围
	private PilotTurnroute pilotTurnroute;
	// service
	private IPilotTurnrouteBS pilotTurnrouteBS;
	// 机长转机型路径范围集合
	private List<PilotTurnroute> pilotTurnrouteList;
	// Message对象
	private Message message;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// ids串
	private String ids;
	//机长转机型路径标示
	private String seq;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public String pilotTurnrouteList() {
		try {
			// 设置默认排序
			SysOrderByInfo orderByInfo = this.getSysOrderByInfo();
			if (orderByInfo.getOrderColumn() == null
					|| "".equals(orderByInfo.getOrderColumn())) {
				orderByInfo.setOrderColumn("transformMode");
				orderByInfo.setOrderAsc("desc");
				orderByInfo.setIfDate(true);
			}
			this.pilotTurnrouteList= this.pilotTurnrouteBS
					.searchTurnrouteList(this.getSysPageInfo(),this.query, orderByInfo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "pilotTurnrouteList";
	}
	/**
	 * 跳转增加机长转机型路径页面
	 * 
	 * @return
	 */
	public String addModelPilotTurnroute() {
		return "addModelPilotTurnroute";
	}

	/**
	 * 保存增加机长转机型路径
	 * 
	 * @return
	 */
	public String saveaddModelPilotTurnroute() {
		try {
			this.pilotTurnrouteBS.addTurnroute(this.pilotTurnroute,
					this.getUser().getName());
			this.message = this.getSuccessMessage(getText("addSuccess"),
					"sms", "closeCurrent",
					"pilot-turnroute/pilot-turnroute!pilotTurnrouteList.do");
		} catch (Exception e) {
			this.pilotTurnrouteBS.getErrorLog().info(e.getMessage()+"#"+"");
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
	public String singleDeleteTurnroute() {
		try {
			this.pilotTurnrouteBS.deleteById(PilotTurnroute.class, this.seq);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"pilot-turnroute/pilot-turnroute!pilotTurnrouteList.do");
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
	public String multipleDeleteTurnroute() {
		try {
			this.pilotTurnrouteBS.multipleDelet(this.ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
			"pilot-turnroute/pilot-turnroute!pilotTurnrouteList.do");
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
	public String editModelPilotTurnroute() {
		this.pilotTurnroute = this.pilotTurnrouteBS.findById(
				PilotTurnroute.class, this.seq);
		return "editModelPilotTurnroute";
	}

	/**
	 * 编辑增加机长转机型路径
	 * 
	 * @return
	 */
	public String saveeditModelPilotTurnroute() {
		try {
			if (this.pilotTurnroute!= null) {
				this.pilotTurnroute.setModifyDate(new Date());
				this.pilotTurnroute.setOperate(this.getUser().getName());
				this.pilotTurnrouteBS.update(this.pilotTurnroute, this.seq);
			}
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"sms", "closeCurrent",
			   "pilot-turnroute/pilot-turnroute!pilotTurnrouteList.do");
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
	public String searchModelPilotTurnroute(){
		this.pilotTurnroute = this.pilotTurnrouteBS.findById(PilotTurnroute.class, this.seq);
		return "searchModelPilotTurnroute";
	}
	public PilotTurnroute getPilotTurnroute() {
		return pilotTurnroute;
	}
	public void setPilotTurnroute(PilotTurnroute pilotTurnroute) {
		this.pilotTurnroute = pilotTurnroute;
	}
	public IPilotTurnrouteBS getPilotTurnrouteBS() {
		return pilotTurnrouteBS;
	}
	public void setPilotTurnrouteBS(IPilotTurnrouteBS pilotTurnrouteBS) {
		this.pilotTurnrouteBS = pilotTurnrouteBS;
	}
	public List<PilotTurnroute> getPilotTurnrouteList() {
		return pilotTurnrouteList;
	}
	public void setPilotTurnrouteList(List<PilotTurnroute> pilotTurnrouteList) {
		this.pilotTurnrouteList = pilotTurnrouteList;
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
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
