package com.sms.training.qualificationassess.web.action.routeTurnnumber;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificationassess.bean.RouteTurnnumber;
import com.sms.training.qualificationassess.business.IRouteTurnnumberBS;

@Results( {
		@Result(name = "turnnumberList", location = "/sms/training/qualificationassess/routeturnnumber/zsList.jsp"),
		@Result(name = "addRouteTurnnumber", location = "/sms/training/qualificationassess/routeturnnumber/zsAdd.jsp"),
		@Result(name = "editRouteTurnnumber", location = "/sms/training/qualificationassess/routeturnnumber/zsEdit.jsp"),
		@Result(name = "searchTurnnumber", location = "/sms/training/qualificationassess/routeturnnumber/zsInfo.jsp"),

		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sec-bphase!list", type = "redirectAction"),
		@Result(name = "json", type = "json") })
@ParentPackage("sinoframe-default")
public class RouteTurnnumberAction extends BaseAction {
	private static final long serialVersionUID = 7352624010000649434L;
	// 路径转升范围
	private RouteTurnnumber routeTurnnumber;
	// service
	private IRouteTurnnumberBS routeTurnnumberBS;
	// 路径转升范围集合
	private List<RouteTurnnumber> routeTurnnumberList;
	// Message对象
	private Message message;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// ids串
	private String ids;
	// 路径转升标示
	private String seq;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public String turnnumberList() {
		try {
			// 设置默认排序
			SysOrderByInfo orderByInfo = this.getSysOrderByInfo();
			if (orderByInfo.getOrderColumn() == null
					|| "".equals(orderByInfo.getOrderColumn())) {
				orderByInfo.setOrderColumn("routeName");
				orderByInfo.setOrderAsc("desc");
				orderByInfo.setIfDate(true);
			}
			this.routeTurnnumberList = this.routeTurnnumberBS
					.searchRouteTurnnumberList(this.getSysPageInfo(),
							this.query, orderByInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "turnnumberList";
	}

	/**
	 * 跳转增加各路径转升人数预设范围页面
	 * 
	 * @return
	 */
	public String addRouteTurnnumber() {
		return "addRouteTurnnumber";
	}

	/**
	 * 保存增加各路径转升人数预设范围
	 * 
	 * @return
	 */
	public String saveaddRouteTurnnumber() {
		try {
			this.routeTurnnumberBS.addRouteTurnnumber(this.routeTurnnumber,
					this.getUser().getName());
			this.message = this.getSuccessMessage(getText("addSuccess"),
					"sms", "closeCurrent",
					"route-turnnumber/route-turnnumber!turnnumberList.do");
		} catch (Exception e) {
			this.routeTurnnumberBS.getErrorLog().info(e.getMessage()+"#"+"");
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
	public String singleDeleteTurn() {
		try {
			this.routeTurnnumberBS.deleteById(RouteTurnnumber.class, this.seq);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"route-turnnumber/route-turnnumber!turnnumberList.do");
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
	public String multipleDeleteTurn() {
		try {
			this.routeTurnnumberBS.multipleDelet(this.ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"sms", "forward",
					"route-turnnumber/route-turnnumber!turnnumberList.do");
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
	public String editRouteTurnnumber() {
		this.routeTurnnumber = this.routeTurnnumberBS.findById(
				RouteTurnnumber.class, this.seq);
		return "editRouteTurnnumber";
	}

	/**
	 * 编辑各路径转升人数预设范围
	 * 
	 * @return
	 */
	public String saveeditRouteTurnnumber() {
		try {
			if (this.routeTurnnumber != null) {
				this.routeTurnnumber.setModifyDate(new Date());
				this.routeTurnnumber.setOperate(this.getUser().getName());
				this.routeTurnnumberBS.update(this.routeTurnnumber, this.seq);
			}
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"sms", "closeCurrent",
					"route-turnnumber/route-turnnumber!turnnumberList.do");
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
	public String searchTurnnumber(){
		this.routeTurnnumber = this.routeTurnnumberBS.findById(RouteTurnnumber.class, this.seq);
		return "searchTurnnumber";
	}
	

	public RouteTurnnumber getRouteTurnnumber() {
		return routeTurnnumber;
	}

	public void setRouteTurnnumber(RouteTurnnumber routeTurnnumber) {
		this.routeTurnnumber = routeTurnnumber;
	}

	public IRouteTurnnumberBS getRouteTurnnumberBS() {
		return routeTurnnumberBS;
	}

	public void setRouteTurnnumberBS(IRouteTurnnumberBS routeTurnnumberBS) {
		this.routeTurnnumberBS = routeTurnnumberBS;
	}

	public List<RouteTurnnumber> getRouteTurnnumberList() {
		return routeTurnnumberList;
	}

	public void setRouteTurnnumberList(List<RouteTurnnumber> routeTurnnumberList) {
		this.routeTurnnumberList = routeTurnnumberList;
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
