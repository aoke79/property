package com.sms.data.hr.peopledept.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.web.action.BaseAction;
import com.sms.data.hr.people.SyncPeolpe;
import com.sms.data.hr.people.business.PeopleBS;
import com.sms.data.hr.peopledept.bean.HrSmsinfo;
import com.sms.data.hr.peopledept.business.IHrSmsinfoBS;
@Results( {
	@Result(name = "hrsmsinfoList", location = "/sms/data/hr/peopledept/zsList.jsp"),
	@Result(name = "addHrsmsinfo", location = "/sms/data/hr/peopledept/zsAdd.jsp"),
	@Result(name = "editHrsmsinfo", location = "/sms/data/hr/peopledept/zsEdit.jsp"),
	
	
	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
	@Result(name = "METHOD", location = "sec-bphase!list", type = "redirectAction"),
	@Result(name = "json", type = "json") })
@ParentPackage("sinoframe-default")
public class HrSmsinfoAction extends BaseAction {
	private static final long serialVersionUID = 7352624010000649434L;
	// HR与SMS对应范围
	private HrSmsinfo hrSmsinfo;
	// service
	private IHrSmsinfoBS hrSmsinfoBS;
	// HR与SMS范围集合
	private List<HrSmsinfo> hrSmsinfoList;
	// Message对象
	private Message message;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// ids串
	private String ids;
	// HR与SMS标示
	private String hrDeptid;
	private SyncPeolpe sypeople =new SyncPeolpe();
	private PeopleBS ps;

	/**
	 * 查询列表

	 */
	public String hrsmsinfoList() {
		try {
			// 设置默认排序
			SysOrderByInfo orderByInfo = this.getSysOrderByInfo();
			if (orderByInfo.getOrderColumn() == null
					|| "".equals(orderByInfo.getOrderColumn())) {
				orderByInfo.setOrderColumn("hrDept");
				orderByInfo.setOrderAsc("desc");
				orderByInfo.setIfDate(true);
			}
			this.hrSmsinfoList = this.hrSmsinfoBS
					.searchHrSmsList(this.getSysPageInfo(),
							this.query, orderByInfo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "hrsmsinfoList";
	}
	
	/**
	 * 跳转增加页面
	 * 
	 * @return
	 */
	public String addHrsmsinfo() {
		return "addHrsmsinfo";
	}

	/**
	 * 保存增加
	 * 
	 * @return
	 */
	public String saveaddHrsmsinfo() {
		try {
			this.hrSmsinfoBS.addHrSms(this.hrSmsinfo,
					this.getUser().getName());
			this.message = this.getSuccessMessage(getText("addSuccess"),
					"hrSmsinfo", "closeCurrent",
					"hr-smsinfo!hrsmsinfoList.do");
		} catch (Exception e) {
			this.hrSmsinfoBS.getErrorLog().info(e.getMessage()+"#"+"");
			this.message = this.getFailMessage("保存操作失败！");
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	/**
	 * 同步HR人员数据
	 * @return
	 */
	public String synchroHrdata(){
		try {
			
			sypeople.auto();
		
			    this.message = this.getSuccessMessage("同步数据成功！", "","", "");
		} catch (Exception e) {
			
			this.message=this.getFailMessage("同步数据失败，原因可能是由于部门不对应等造成的");
			
			e.printStackTrace();
		}
		
		return "SUCCESS";
	}
	/**
	 * 单个删除
	 * 
	 * @return
	 */
	public String singleDeleteHrsms() {
		try {
			this.hrSmsinfoBS.deleteById(HrSmsinfo.class, this.hrDeptid);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"hrSmsinfo", "forward",
					"hr-smsinfo!hrsmsinfoList.do");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multipleDeleteHrsms() {
		try {
			this.hrSmsinfoBS.multipleDelet(this.ids);
			this.message = this.getSuccessMessage(getText("deleteSuccess"),
					"hrSmsinfo", "forward",
			"hr-smsinfo!hrsmsinfoList.do");
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
	public String editHrsmsinfo() {
		this.hrSmsinfo = this.hrSmsinfoBS.findById(
				HrSmsinfo.class, this.hrDeptid);
		return "editHrsmsinfo";
	}

	/**
	 * 保存编辑
	 * 
	 * @return
	 */
	public String saveeditHrsmsinfo() {
		try {
			if (this.hrSmsinfo!= null) {
				this.hrSmsinfo.setModifyDate(new Date());
				this.hrSmsinfo.setOperate(this.getUser().getName());
				this.hrSmsinfoBS.update(this.hrSmsinfo, this.hrDeptid);
			}
			this.message = this.getSuccessMessage(getText("updateSuccess"),
					"hrSmsinfo", "closeCurrent",
			         "hr-smsinfo!hrsmsinfoList.do");
			
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
	public String searchHrsmsinfo(){
		this.hrSmsinfo = this.hrSmsinfoBS.findById(HrSmsinfo.class, this.hrDeptid);
		return "searchHrsmsinfo";
	}
	public HrSmsinfo getHrSmsinfo() {
		return hrSmsinfo;
	}
	public void setHrSmsinfo(HrSmsinfo hrSmsinfo) {
		this.hrSmsinfo = hrSmsinfo;
	}
	public IHrSmsinfoBS getHrSmsinfoBS() {
		return hrSmsinfoBS;
	}
	public void setHrSmsinfoBS(IHrSmsinfoBS hrSmsinfoBS) {
		this.hrSmsinfoBS = hrSmsinfoBS;
	}
	public List<HrSmsinfo> getHrSmsinfoList() {
		return hrSmsinfoList;
	}
	public void setHrSmsinfoList(List<HrSmsinfo> hrSmsinfoList) {
		this.hrSmsinfoList = hrSmsinfoList;
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
	public String getHrDeptid() {
		return hrDeptid;
	}
	public void setHrDeptid(String hrDeptid) {
		this.hrDeptid = hrDeptid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SyncPeolpe getSypeople() {
		return sypeople;
	}
	public void setSypeople(SyncPeolpe sypeople) {
		this.sypeople = sypeople;
	}
	public PeopleBS getPs() {
		return ps;
	}
	public void setPs(PeopleBS ps) {
		this.ps = ps;
	}

	
	

}
