package com.sms.training.qualification.web.action.tfQuaApply;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.Message;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualBaseTemplate;
import com.sms.training.qualification.bean.TfQualBaseTrainingtype;
import com.sms.training.qualification.bean.TfQualBaseTraintypegroup;
import com.sms.training.qualification.bean.TfQualPilotTechgrade;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualBaseTgroup;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualBaseTypeGroup;
import com.sms.training.qualification.business.ITfQuaManagerBS;

/**
 * 飞行员资质类型信息维护
 * @author 杨书州
 * @date 2012/06/13
 *
 */

@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "addType", location = "/sms/training/qualification/quaApply/quaType/qualiTypeAdd.jsp"),
		@Result(name = "AlterPage", location = "/sms/training/qualification/quaApply/quaType/qualiTypeAlter.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "METHOD", location = "sys-system!list", type = "redirectAction"),
		@Result(name = "list", location = "/sms/training/qualification/quaApply/quaType/quaTypeList.jsp"),
		@Result(name = "json", type = "json")
})
public class TfQuaTypeManagerAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	//模块儿名
	private static String moduleName = "tfQuaTypeManager";
	private TfQualBaseType qualbasetype;
	private TfQualBaseTypeGroup qualbasetypegroup;
	private BaseAirPlanType baseAirPlanType;
	private TfQualPilotTechgrade tfQualPilotTechgrade;
	private TfQualBaseTrainingtype tfQualBaseTrainingtype;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 用于存储查询条件的字符串形式
	private String strQuery;
	// 存放ID
	private String ids;
	//删除资质类型id
	private String id;
	private Message message;
	@Resource
	private ITfQuaManagerBS tfQuaManagerBS;
	private List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList;
	private List<TfQualBaseTgroup> tfQualBaseTgroupList;
	private List<TfQualBaseType> qualbasetypeList;
	private List<BaseAirPlanType> baseAirPlanTypeList;
	private List<TfQualBaseTrainingtype> tfQualBaseTrainingtypeList;
	private List<TfQualBaseTraintypegroup> tfQualBaseTraintypegroupList;
	private List<TfQualPilotTechgrade> tfQualPilotTechgradeList;
	private String qtgroupid;
	private String [] grade ;  //级别
	private String [] gradeAirType ;  //机型 
	//模板
	private List<TfQualBaseTemplate> tfQualBaseTemplateList;
	//分组类型Id
	private String groupId;
	//机型Id
	private String airTypeId;

	/**
	 * 资质类型信息显示
	 * 
	 * @return
	 */
	public String qualityManager() {
		
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
			String orderByInfo = "tfQualBaseTypeGroup.qtgroupdesc,tfQualBaseTgroup.typegroupdesc,typedesc";
			qualbasetypeList = tfQuaManagerBS.findPageByQuery(this.getSysPageInfo(), listHql, Util.decodeQuery(query), orderByInfo);
			//------------------------------------
//         此处暂时先不删除
//			for(TfQualBaseType type : qualbasetypeList){
//				  if(type.getOriginalatrid()!=null && type.getOriginalatrid().getId()!=null && !"".equals(type.getOriginalatrid().getId())){
//					  atrIds=StringUtils.split(type.getOriginalatrid().getId(), ",");
//					  for(String id : atrIds){
//							BaseAirPlanType plane=tfQuaManagerBS.findById(BaseAirPlanType.class, id);
//							type.getOriginalAtrs().add(plane==null ? "" : plane.getAtrdesc());
//						}
//				  }
//				  if(type.getOriginalgrade()!=null && type.getOriginalgrade().getPtGradeId()!=null && !"".equals(type.getOriginalgrade().getPtGradeId())){
//					  gradeIds=StringUtils.split(type.getOriginalgrade().getPtGradeId(), ",");
//					  for(String id : gradeIds){
//							TfQualPilotTechgrade grade=tfQuaManagerBS.findById(TfQualPilotTechgrade.class, id);
//							type.getOriginalGrades().add(grade==null ? "" : grade.getPtGradeDesc());
//						}
//				  }
//			}
			
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
	 * 根据大类查找小类项
	 * @return
	 */
	public String quaryType() {
		try {
			tfQualBaseTgroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid='"+qtgroupid+"'");
			for(TfQualBaseTgroup tgroup : tfQualBaseTgroupList){
				tgroup.setTfQualBaseTypeGroup(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		
		return "json";
	}
	
	/**
	 * 添加资质类型显示
	 * 
	 * @return
	 */
	public String toAddPage() {
		try {
			String hql = "from  TfQualBaseTrainingtype t where t.ttypestus = '1'";
			tfQualBaseTrainingtypeList = this.tfQuaManagerBS.findPageByQuery(hql);
			tfQualBaseTraintypegroupList = this.tfQuaManagerBS.findPageByQuery("from  TfQualBaseTraintypegroup where 1=1");
			
			tfQualBaseTypeGroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTypeGroup");
			tfQualBaseTgroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTgroup");
			baseAirPlanTypeList = this.tfQuaManagerBS.findPageByQuery("from BaseAirPlanType b where b.status ='Y' order by b.id asc");
			tfQualPilotTechgradeList = this.tfQuaManagerBS.findPageByQuery("from TfQualPilotTechgrade g where g.status ='Y'");
			String [] g = {"n"};
			this.grade  = g;
			
			//查询出所有模板列表
			String hql2 = "from TfQualBaseTemplate where 1=1";
			tfQualBaseTemplateList = this.tfQuaManagerBS.findPageByQuery(hql2);
			
		} catch (Exception e) {
			e.printStackTrace();
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "addType";		
	}

	/**
	 * 保存资质类型
	 * 
	 * @return
	 */
	public String toSavePage() {
		try {
			if(qualbasetype.getOriginalatrid() != null && qualbasetype.getOriginalgrade() != null){
				qualbasetype.getOriginalatrid().setId(qualbasetype.getOriginalatrid().getId().replace(" ", "").replace("n", ""));
				qualbasetype.getOriginalgrade().setPtGradeId(qualbasetype.getOriginalgrade().getPtGradeId().replace(" ", "").replace("n", ""));
			}
			this.tfQuaManagerBS.save(qualbasetype);
			this.message = this.getSuccessMessage(getText("addSuccess"),moduleName, "closeCurrent",	"");
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}

		return "SUCCESS";
	}	

	/**
	 * 修改资质类型页面
	 * @author pxp
	 * */
	public String toAlterPage() {
		try {
			//资质类型
			qualbasetype = tfQuaManagerBS.findById(TfQualBaseType.class, id);
			
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
			
			//训练类型分组列表
			tfQualBaseTraintypegroupList = this.tfQuaManagerBS.findPageByQuery("from  TfQualBaseTraintypegroup where 1=1");
			//资质类型分组列表
			tfQualBaseTypeGroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTypeGroup");
			//机型列表
			baseAirPlanTypeList = this.tfQuaManagerBS.findPageByQuery("from BaseAirPlanType b where b.status ='Y' order by b.id asc");
			//级别列表
			tfQualPilotTechgradeList = this.tfQuaManagerBS.findPageByQuery("from TfQualPilotTechgrade g where g.status ='Y'");
			//资质小类列表
			tfQualBaseTgroupList = this.tfQuaManagerBS.findPageByQuery("from TfQualBaseTgroup t where t.tfQualBaseTypeGroup.qtgroupid= '"+qualbasetype.getTfQualBaseTypeGroup().getQtgroupid()+"'");
			//原始机型
			if(qualbasetype.getOriginalatrid()!=null){
				this.gradeAirType = StringUtils.split(qualbasetype.getOriginalatrid().getId()==null?"":qualbasetype.getOriginalatrid().getId(), ',');
			}else{
				this.gradeAirType = new String[]{"n"};
			}
			//原始级别
			if(qualbasetype.getOriginalgrade() != null){
				this.grade = StringUtils.split(qualbasetype.getOriginalgrade().getPtGradeId()==null?"":qualbasetype.getOriginalgrade().getPtGradeId(), ',');
			}else{
				this.grade = new String[]{"n"};
			}
			String hql = "from TfQualBaseTemplate where 1=1";
			//模版描述列表
			tfQualBaseTemplateList = this.tfQuaManagerBS.findPageByQuery(hql);
			
		} catch (Exception e) {
			e.printStackTrace();
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "AlterPage";
	}
	
	/**
	 * 更新资质类型
	 * 
	 * @return
	 */
	public String toUpdatePage() {
		try {
			if(qualbasetype.getOriginalatrid() != null && qualbasetype.getOriginalgrade() != null){
				qualbasetype.getOriginalatrid().setId(qualbasetype.getOriginalatrid().getId().replace(" ", "").replace("n", ""));
				qualbasetype.getOriginalgrade().setPtGradeId(qualbasetype.getOriginalgrade().getPtGradeId().replace(" ", "").replace("n", ""));
			}
			this.tfQuaManagerBS.saveOrUpdate(qualbasetype);
			this.message = this.getSuccessMessage(getText("updateSuccess"),moduleName, "closeCurrent","tf-qua-apply/tf-qua-type-manager!qualityManager.do");
		} catch (Exception e) {
			this.message = this.getFailMessage(getText("addFail"));
			e.printStackTrace();
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
		}
		return "SUCCESS";
	}

	/**
	 * 根据资质类型IDS批量删除
	 * 
	 * @return
	 */
	public String toDeletePage() {
		try {
//			this.tfQuaManagerBS.deleteByIds(TfQualBaseType.class, ids);
//			this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward","tf-qua-apply/tf-qua-type-manager!qualityManager.do");
			
			if( !"".equals(ids)){
				String[] idArr = ids.split(",");
				boolean typeFlag = false;
				for(String id : idArr){
					String countHql = "select count(*) from TfQualBaseAccessconditions t where t.tfQualBaseType.typeid = '" + id+"'" ;
					int colCount = this.tfQuaManagerBS.getCountByHql(countHql);
					if(colCount != 0){
						typeFlag = true;
					}else{
						tfQuaManagerBS.deleteById(TfQualBaseType.class, id);
					}
				}
				this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward","tf-qua-apply/tf-qua-type-manager!qualityManager.do");
				if(typeFlag){
					if(idArr.length !=1){
						this.message.setMessageInfo("部分资质类型下有准入标准信息，请先删除其下信息");
						this.message.setStatusCode("300");
					}else{
						this.message = this.getFailMessage("请先删除该资质类型下的准入标准信息");
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
	 *  根据资质类型id删除一条记录
	 * @return
	 */
	public String deleteById() {
		try {
			if(id != null && !"".equals(id)){
				String countHql = "select count(*) from TfQualBaseAccessconditions t where t.tfQualBaseType.typeid = '" + id+"'" ;
				int colCount = this.tfQuaManagerBS.getCountByHql(countHql);
				if(colCount!=0){
					this.message = this.getFailMessage("请先删除该资质类型下的准入标准信息");
				}else{
					tfQuaManagerBS.deleteById(TfQualBaseType.class, id);
					// 设定成功消息，判断是否记录了排序需要的条件
					if (this.getSysOrderByInfo().getOrderColumn() != null
							&& !this.getSysOrderByInfo().getOrderColumn().equals("")) {
						this.message = this.getSuccessMessage(getText("deleteSuccess"),"", "forward",
								"tf-qua-apply/tf-qua-type-manager!qualityManager.do?orderBlock="
										+ this.getSysOrderByInfo().getOrderColumn()
										+ "&orderMethod="
										+ this.getSysOrderByInfo().getOrderAsc()
										+ "&pageNum=" + this.getPageNum()
										+ "&numPerPage=" + this.getNumPerPage() + "&"
										+ Util.toStrQuery(query));
					} else {
						this.message = this.getSuccessMessage(getText("deleteSuccess"),moduleName, "forward",
								"tf-qua-apply/tf-qua-type-manager!qualityManager.do?pageNum="
										+ this.getPageNum() + "&numPerPage="
										+ this.getNumPerPage() + "&"+ Util.toStrQuery(query));
					}
				}
			}
		} catch (Exception e) {
			// 设置日志信息
			tfQuaManagerBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			// 设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	@JSON(serialize = false)
	public List<TfQualBaseTypeGroup> getTfQualBaseTypeGroupList() {
		return tfQualBaseTypeGroupList;
	}

	public void setTfQualBaseTypeGroupList(
			List<TfQualBaseTypeGroup> tfQualBaseTypeGroupList) {
		this.tfQualBaseTypeGroupList = tfQualBaseTypeGroupList;
	}

	@JSON(serialize = false)
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

	@JSON(serialize = false)
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
 
	@JSON(serialize = false)
	public TfQualPilotTechgrade getTfQualPilotTechgrade() {
		return tfQualPilotTechgrade;
	}
	public void setTfQualPilotTechgrade(TfQualPilotTechgrade tfQualPilotTechgrade) {
		this.tfQualPilotTechgrade = tfQualPilotTechgrade;
	}

	@JSON(serialize = false)
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

	//此处不要加json = false
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
	
	@JSON(serialize = false)
	public String[] getGradeAirType() {
		return gradeAirType;
	}
	public void setGradeAirType(String[] gradeAirType) {
		this.gradeAirType = gradeAirType;
	}
	
	@JSON(serialize = false)
	public String[] getGrade() {
		return grade;
	}
	public void setGrade(String[] grade) {
		this.grade = grade;
	}

	@JSON(serialize = false)
	public TfQualBaseTrainingtype getTfQualBaseTrainingtype() {
		return tfQualBaseTrainingtype;
	}
	public void setTfQualBaseTrainingtype(TfQualBaseTrainingtype tfQualBaseTrainingtype) {
		this.tfQualBaseTrainingtype = tfQualBaseTrainingtype;
	}

	@JSON(serialize = false)
	public List<TfQualBaseTrainingtype> getTfQualBaseTrainingtypeList() {
		return tfQualBaseTrainingtypeList;
	}
	public void setTfQualBaseTrainingtypeList(List<TfQualBaseTrainingtype> tfQualBaseTrainingtypeList) {
		this.tfQualBaseTrainingtypeList = tfQualBaseTrainingtypeList;
	}

	@JSON(serialize = false)
	public List<TfQualBaseTraintypegroup> getTfQualBaseTraintypegroupList() {
		return tfQualBaseTraintypegroupList;
	}
	public void setTfQualBaseTraintypegroupList(List<TfQualBaseTraintypegroup> tfQualBaseTraintypegroupList) {
		this.tfQualBaseTraintypegroupList = tfQualBaseTraintypegroupList;
	}
	
	@JSON(serialize = false)
	public List<TfQualBaseTemplate> getTfQualBaseTemplateList() {
		return tfQualBaseTemplateList;
	}

	public void setTfQualBaseTemplateList(
			List<TfQualBaseTemplate> tfQualBaseTemplateList) {
		this.tfQualBaseTemplateList = tfQualBaseTemplateList;
	}

	@JSON(serialize = false)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupDesc) {
		this.groupId = groupDesc;
	}
	
	@JSON(serialize = false)
	public String getAirTypeId() {
		return airTypeId;
	}
	public void setAirTypeId(String airTypeId) {
		this.airTypeId = airTypeId;
	}
}
