package com.sms.training.qualification.web.action.tfQualCourse;

import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseTemplate;
import com.sms.training.qualification.business.ITfQuaManagerBS;

/**
 * 课程模板维护模块儿
 * @author zhanghuifen
 *
 */

@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "toTemeplateList", location = "/sms/training/qualification/qualTemplate/qualApplyTemplateList.jsp"),
		@Result(name = "toAddPage", location = "/sms/training/qualification/qualTemplate/qualApplyTemplateAdd.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp")
	
		})
public class TfQualTemplateAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//模块儿名
	private static String moduleName = "tfQualTemplate";
	private List<TfQualBaseTemplate> tfQualBaseTemplateList;
	private ITfQuaManagerBS tfQuaManagerBS;
	//机型列表
	private List<BaseAirPlanType> baseAirPlanTypeList;
	//模板bean
	private TfQualBaseTemplate tfQualBaseTemplate;
	private Message message;
	//单个记录id
	private String id;
	//批量删除ids
	private String ids = "";
	// 用于存储查询条件的字符串形式
	private String strQuery;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();

	/**
	 * constructor
	 */
	public TfQualTemplateAction() {
	}
	
	/**
	 * 跳转到模板列表页面
	 */
	
	public String toTemeplateList(){
		try {
			String hql = "select count(*) from TfQualBaseTemplate where 1=1 ";
			// 显示时需要的字段
			String[] queryShown = { "like_atrdesc" };
			// 查询时需要的字段
			String[] queryCondition = { "like_baseAirplantype.atrdesc" };
			this.tfQuaManagerBS.getQueryCondition(queryShown, queryCondition,Util.decodeQuery(query));
			long colCount = this.tfQuaManagerBS.getCountByHQL(hql, query);
			// 显示特殊查询 条件的数据
			this.tfQuaManagerBS.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(colCount);
			
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderColumn("baseAirplantype.atrdesc");
			}
			
			String sql = "from TfQualBaseTemplate where 1=1";
			tfQualBaseTemplateList = this.tfQuaManagerBS.findPageByQuery(getSysPageInfo(), sql, query,sysOrderByInfo);
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
		return "toTemeplateList";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toAddPage(){
    	try {
    		if( "".equals(id) || id == null){
    			baseAirPlanTypeList = this.tfQuaManagerBS.findPageByQuery("from BaseAirPlanType b where b.status ='Y' order by b.id asc");
    		}else{
    			tfQualBaseTemplate = this.tfQuaManagerBS.findById(TfQualBaseTemplate.class, id);
    			baseAirPlanTypeList = this.tfQuaManagerBS.findPageByQuery("from BaseAirPlanType b where b.status ='Y' order by b.id asc");
    		}
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
		}
    	return "toAddPage";
    }
    
    /**
     * 根据id删除记录
     */
	public String deleteById(){
		try {
			if(id != null && !"".equals(id)){
				String countHql = "select count(*) from TfQualBaseLesson t where t.tfQualBaseTemplate.id = '" + id+"'" ;
				int colCount = this.tfQuaManagerBS.getCountByHql(countHql);
				if(colCount!=0){
					this.message = this.getFailMessage("请先删除该模板下的课次信息");
				}else{
					this.tfQuaManagerBS.deleteById(TfQualBaseTemplate.class, id);
					this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward","tf-qual-course/tf-qual-template!toTemeplateList.do");
				}
			}
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
    
	/**
	 * 批量根据ids删除记录
	 */
	public String toDeleteBatch() {
		try {
			if( !"".equals(ids)){
				String[] idArr = ids.split(",");
				boolean lessonFlag = false;
				for(String id : idArr){
					String countHql = "select count(*) from TfQualBaseLesson t where t.tfQualBaseTemplate.id ='" +id+"'" ;
					int colCount = this.tfQuaManagerBS.getCountByHql(countHql);
					if(colCount != 0){
						lessonFlag = true;
					}else{
						tfQuaManagerBS.deleteByIds(TfQualBaseTemplate.class, id);
					}
				}
				this.message = this.getSuccessMessage("批量删除成功！", moduleName,
						"forward", "tf-qual-course/tf-qual-template!toTemeplateList.do");
				if(lessonFlag ){
					if(idArr.length !=1){
						this.message.setMessageInfo("部分模板下有课程，请先删除其下课程");
						this.message.setStatusCode("300");
					}else{
						this.message = this.getFailMessage("请先删除该模板下的课次信息");
					}
				}
			}
		} catch (Exception e) {
			// 设置日志信息
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
    
    /**
     * 保存
     * @return
     */
    public String toSavePage(){
    	try {
			String id = tfQualBaseTemplate.getId();
			if(id!=null && id.equals("")){
				tfQualBaseTemplate.setId(null);
			}
			this.tfQuaManagerBS.saveOrUpdate(tfQualBaseTemplate);
			this.message = this.getSuccessMessage(id.trim().equals("")?getText("addSuccess"):getText("updateSuccess"),moduleName, "forword",	"tf-qual-course/tf-qual-template!toTemeplateList.do");
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
		}
    	return "SUCCESS";
    }
	
	
//        ===========================     set/get方法       ===================================
  
    //不需要set方法
    @JSON(serialize = false)
	public List<TfQualBaseTemplate> getTfQualBaseTemplateList() {
		return tfQualBaseTemplateList;
	}


	@JSON(serialize = false)
	public ITfQuaManagerBS getTfQuaManagerBS() {
		return tfQuaManagerBS;
	}

	public void setTfQuaManagerBS(ITfQuaManagerBS tfQuaManagerBS) {
		this.tfQuaManagerBS = tfQuaManagerBS;
	}

	//不需要set方法
	@JSON(serialize = false)
	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}

	@JSON(serialize = false)
	public TfQualBaseTemplate getTfQualBaseTemplate() {
		return tfQualBaseTemplate;
	}

	public void setTfQualBaseTemplate(TfQualBaseTemplate tfQualBaseTemplate) {
		this.tfQualBaseTemplate = tfQualBaseTemplate;
	}

	//此处不要加json=false
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	@JSON(serialize = false)
	public void setId(String id) {
		this.id = id;
	}

	@JSON(serialize = false)
	public void setIds(String ids) {
		this.ids = ids;
	}

	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	
}
