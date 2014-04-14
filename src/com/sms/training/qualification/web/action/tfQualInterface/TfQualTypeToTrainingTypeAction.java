package com.sms.training.qualification.web.action.tfQualInterface;

import java.util.HashMap;
import java.util.List;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.data.kettletimer.KettleUtil;
import com.sms.training.qualification.bean.TfQualBaseTrainingtype;
import com.sms.training.qualification.bean.TfQualPilotTechgrade;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseTgroup;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualBaseTypeGroup;
import com.sms.training.qualification.business.ITfQuaManagerBS;
import com.sms.training.qualification.business.ITfQualBaseTypeBS;
import com.sms.training.qualification.bean.TfQualBaseTraintypegroup;
/**
 * 飞行员资质类型与训练类型的对应关系
 * @author 王瑞
 * @date 2012/10/21
 *
 */

@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "addType", location = "/sms/training/qualification/quaApply/quaType/qualiTypeAdd.jsp"),
		@Result(name = "AlterPage", location = "/sms/training/qualification/quaApply/quaType/qualiTypeAlter.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sys-system!list", type = "redirectAction"),
		@Result(name = "list", location = "/sms/training/qualification/tfQualInterface/qualTypeToTrainingTypeList.jsp"),
		@Result(name = "updateTrain", location = "/sms/training/qualification/tfQualInterface/qualTypeToTrainingTypeUpdate.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json")
})
public class TfQualTypeToTrainingTypeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	//模块儿名
	private static String moduleName = "tfQualTypeToTrainingType";
	private TfQualBaseType qualbasetype;
	private TfQualBaseTypeGroup qualbasetypegroup;
	private BaseAirPlanType baseAirPlanType;
	private TfQualPilotTechgrade tfQualPilotTechgrade;	
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 用于存储查询条件的字符串形式
	private String strQuery;
	// 存放ID
	private String ids;
	//删除资质类型id
	private String id;
	private Message message;
	private ITfQuaManagerBS tfQuaManagerBS;
	private ITfQualBaseTypeBS tfQualBaseTypeBS;
	private List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList;
	private List<TfQualBaseTgroup> tfQualBaseTgroupList;
	private List<TfQualBaseType> qualbasetypeList;
	private List<BaseAirPlanType> baseAirPlanTypeList;
	private List<TfQualPilotTechgrade> tfQualPilotTechgradeList;
	private String qtgroupid;
	//训练分组实体
	private TfQualBaseTraintypegroup tfQualBaseTraintypegroup;
	private List<TfQualBaseTraintypegroup> tfQualBaseTraintypegroupList;
	//训练类型实体
	private TfQualBaseTrainingtype tfQualBaseTrainingtype;
	private List<TfQualBaseTrainingtype> tfQualBaseTrainingtypeList;
    private String trainingType;
    //分组类型Id
  	private String groupId;
  	//机型Id
  	private String airTypeId;

	/**
	 * 资质类型和训练类型的对应关系列表
	 * 
	 * @return
	 */
	
	public String list(){
		
		try{
			if (strQuery != null && !"".equals(strQuery)) {
					query = Util.toMap(strQuery);
			}
			// 查询列表中的条数信息
			String countHql = "select count(*) from TfQualBaseType where 1=1 ";
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(this.tfQuaManagerBS.getCountByHQL(countHql, Util.decodeQuery(query)));
			
			// 列表
			String listHql = "from TfQualBaseType  where 1=1 ";

			if (this.getSysOrderByInfo().getOrderAsc().equals("") && this.getSysOrderByInfo().getOrderColumn() == null) {
				this.getSysOrderByInfo().setIfDate(false);
				this.getSysOrderByInfo().setOrderAsc("asc");
				this.getSysOrderByInfo().setOrderColumn("name");
			}
			qualbasetypeList = tfQuaManagerBS.findPageByQuery(this.getSysPageInfo(), listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
			tfQualBaseTypeGroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTypeGroup");
			tfQualBaseTgroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTgroup");
			
			strQuery = Util.toStrQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			this.getSysPageInfo().setCurrentPage(1);
			// 设置日志信息
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}

		return "list";
	}
	
	/**
	 * 同步训练类型数据
	 * @return
	 */
	public String synchrodata(){
		try {
			KettleUtil.kettleExecuteJob("qualification_t_trainingtype"); 
			this.message = this.getSuccessMessage("同步数据成功！", "","", "");
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 同步排课信息数据
	 * @return
	 */
	public String synchroCourseListData(){
		try {
			KettleUtil.kettleExecuteJob("TF_QUAL_PILOT_COURSELIST"); 
			this.message = this.getSuccessMessage("同步数据成功！", "qualApply_tabId","", "");
		} catch (Exception e) {
			tfQuaManagerBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 展示修改训练类型
	 * @return
	 */
	public String toUpdateTrainingType(){
		//查询出来训练类型分组
//		String hql = "from  TfQualBaseTrainingtype t where t.ttypestus = '1'";
//		tfQualBaseTrainingtypeList = this.tfQuaManagerBS.findPageByQuery(hql);
		
		//获得修改前的训练分组类型
		List<String> groupIdList = tfQuaManagerBS.getGroupByBaseType(qualbasetype.getTypeid());
		if(groupIdList != null && groupIdList.size() != 0) {
			groupId = groupIdList.get(0);
		}
		
		//获得修改前的机型
		List<String> airTypeList = tfQuaManagerBS.getAirTypeByBaseType(qualbasetype.getTypeid());
		if(airTypeList != null && airTypeList.size() != 0) {
			airTypeId = airTypeList.get(0);
		}
		
		//获得要修改的对应训练类型列表
		tfQualBaseTrainingtypeList = this.tfQuaManagerBS.getBaseTrainingtypeList(groupId, airTypeId);
		tfQualBaseTraintypegroupList = this.tfQuaManagerBS.findPageByQuery("from  TfQualBaseTraintypegroup where 1=1");
		baseAirPlanTypeList = this.tfQuaManagerBS.findPageByQuery("from BaseAirPlanType b where b.status ='Y' ");
		//查询出当前这条记录
		qualbasetype = this.tfQuaManagerBS.findById(TfQualBaseType.class, qualbasetype.getTypeid());
		
		return "updateTrain";
	}
	
	/**
	 * 添加时，查询
	 * @return
	 */
	public String toQueryeTrainingType(){
		//查询出来训练类型分组
		StringBuffer queryHql = new StringBuffer("from TfQualBaseTrainingtype t where t.ttypestus = '1'");
		if(!"".equals(tfQualBaseTrainingtype.getTtgrid())){
			queryHql.append("and t.ttgrid = "+ "'"+ tfQualBaseTrainingtype.getTtgrid()+"'");
		}
		if(!"".equals(tfQualBaseTrainingtype.getTtypetargetatrid())){
			queryHql.append("and t.ttypetargetatrid = " + "'" + tfQualBaseTrainingtype.getTtypetargetatrid() + "'");
		}
		
		String hql = queryHql.toString();
		
		tfQualBaseTrainingtypeList = this.tfQuaManagerBS.findPageByQuery(hql);
		
		return "json";
	}
	
	/**
	 * 修改训练类型
	 * @return
	 */
	public String updateTrainingType(){
		//查询出来训练类型分组
		try {
			qualbasetype = tfQualBaseTypeBS.findById(TfQualBaseType.class,qualbasetype.getTypeid());
			
			tfQualBaseTrainingtype = tfQuaManagerBS.findUniqueBySingleQuery("TfQualBaseTrainingtype", "ttypeseq", trainingType);
			qualbasetype.setTfQualBaseTrainingtype(tfQualBaseTrainingtype);
			
			this.tfQuaManagerBS.saveOrUpdate(qualbasetype);
			
			
			this.message = this.getSuccessMessage("修改成功！", "strainingType","", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	

	@JSON(serialize = false)
	public ITfQualBaseTypeBS getTfQualBaseTypeBS() {
		return tfQualBaseTypeBS;
	}

	public void setTfQualBaseTypeBS(ITfQualBaseTypeBS tfQualBaseTypeBS) {
		this.tfQualBaseTypeBS = tfQualBaseTypeBS;
	}

	@JSON(serialize = false)
	public List<TfQualBaseTypeGroup> getTfQualBaseTypeGroupList() {
		return tfQualBaseTypeGroupList;
	}

	public void setTfQualBaseTypeGroupList(
			List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList) {
		this.tfQualBaseTypeGroupList = tfQualBaseTypeGroupList;
	}

	public TfQualBaseType getQualbasetype() {
		return qualbasetype;
	}

	public void setQualbasetype(TfQualBaseType qualbasetype) {
		this.qualbasetype = qualbasetype;
	}
	@JSON(serialize = false)
	public TfQualBaseTypeGroup getQualbasetypegroup() {
		return qualbasetypegroup;
	}

	public void setQualbasetypegroup(TfQualBaseTypeGroup qualbasetypegroup) {
		this.qualbasetypegroup = qualbasetypegroup;
	}	
	@JSON(serialize = false)
	public List<TfQualBaseType> getQualbasetypeList() {
		return qualbasetypeList;
	}

	public void setQualbasetypeList(List<TfQualBaseType> qualbasetypeList) {
		this.qualbasetypeList = qualbasetypeList;
	}
	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	@JSON(serialize = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<BaseAirPlanType> getBaseAirPlanTypeList() {
		return baseAirPlanTypeList;
	}

	public void setBaseAirPlanTypeList(List<BaseAirPlanType> baseAirPlanTypeList) {
		this.baseAirPlanTypeList = baseAirPlanTypeList;
	}

	 
	@JSON(serialize = false)
	public BaseAirPlanType getBaseAirPlanType() {
		return baseAirPlanType;
	}

	public void setBaseAirPlanType(BaseAirPlanType baseAirPlanType) {
		this.baseAirPlanType = baseAirPlanType;
	}
 
	public TfQualPilotTechgrade getTfQualPilotTechgrade() {
		return tfQualPilotTechgrade;
	}

	public void setTfQualPilotTechgrade(TfQualPilotTechgrade tfQualPilotTechgrade) {
		this.tfQualPilotTechgrade = tfQualPilotTechgrade;
	}

	public List<TfQualPilotTechgrade> getTfQualPilotTechgradeList() {
		return tfQualPilotTechgradeList;
	}

	public void setTfQualPilotTechgradeList(
			List<TfQualPilotTechgrade> tfQualPilotTechgradeList) {
		this.tfQualPilotTechgradeList = tfQualPilotTechgradeList;
	}
	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public List<TfQualBaseTgroup> getTfQualBaseTgroupList() {
		return tfQualBaseTgroupList;
	}

	public void setTfQualBaseTgroupList(List<TfQualBaseTgroup> tfQualBaseTgroupList) {
		this.tfQualBaseTgroupList = tfQualBaseTgroupList;
	}
	@JSON(serialize = false)
	public ITfQuaManagerBS getTfQuaManagerBS() {
		return tfQuaManagerBS;
	}

	public void setTfQuaManagerBS(ITfQuaManagerBS tfQuaManagerBS) {
		this.tfQuaManagerBS = tfQuaManagerBS;
	}

	@JSON(serialize = false)
	public String getQtgroupid() {
		return qtgroupid;
	}
	public void setQtgroupid(String qtgroupid) {
		this.qtgroupid = qtgroupid;
	}

	
	public TfQualBaseTraintypegroup getTfQualBaseTraintypegroup() {
		return tfQualBaseTraintypegroup;
	}

	public void setTfQualBaseTraintypegroup(
			TfQualBaseTraintypegroup tfQualBaseTraintypegroup) {
		this.tfQualBaseTraintypegroup = tfQualBaseTraintypegroup;
	}

	@JSON(serialize = false)
	public List<TfQualBaseTraintypegroup> getTfQualBaseTraintypegroupList() {
		return tfQualBaseTraintypegroupList;
	}

	public void setTfQualBaseTraintypegroupList(
			List<TfQualBaseTraintypegroup> tfQualBaseTraintypegroupList) {
		this.tfQualBaseTraintypegroupList = tfQualBaseTraintypegroupList;
	}

	//此处不要加false
	public List<TfQualBaseTrainingtype> getTfQualBaseTrainingtypeList() {
		return tfQualBaseTrainingtypeList;
	}

	public void setTfQualBaseTrainingtypeList(
			List<TfQualBaseTrainingtype> tfQualBaseTrainingtypeList) {
		this.tfQualBaseTrainingtypeList = tfQualBaseTrainingtypeList;
	}
	@JSON(serialize = false)
	public TfQualBaseTrainingtype getTfQualBaseTrainingtype() {
		return tfQualBaseTrainingtype;
	}

	public void setTfQualBaseTrainingtype(
			TfQualBaseTrainingtype tfQualBaseTrainingtype) {
		this.tfQualBaseTrainingtype = tfQualBaseTrainingtype;
	}
	@JSON(serialize = false)
	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	@JSON(serialize = false)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@JSON(serialize = false)
	public String getAirTypeId() {
		return airTypeId;
	}

	public void setAirTypeId(String airTypeId) {
		this.airTypeId = airTypeId;
	}

}
