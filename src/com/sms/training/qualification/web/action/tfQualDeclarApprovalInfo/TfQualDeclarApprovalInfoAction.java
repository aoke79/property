package com.sms.training.qualification.web.action.tfQualDeclarApprovalInfo;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclarApprovalinfo;
import com.sms.training.qualification.business.ITfQualDeclarApprovalInfoBS;
/**
 * 审批意见
 * 
 * @author QLL date 2012-07-3
 */
@ParentPackage("sinoframe-default")
@Results({
		@Result(name = "list", location = "/sms/training/englishtrain/tfEnTrainPlan/tfEnTrainMonPlanList.jsp"),
		@Result(name = "loadBrt", location = "/sms/training/englishtrain/tfEnTrainScene/trainSceneReportShow.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "json", type = "json") })
public class TfQualDeclarApprovalInfoAction  extends BaseAction {
	
		private static final long serialVersionUID = 1L;
		// 消息实体
		private Message message;
		// 当前模块名称
		private String moduleName = "TfQuaApply";

		public TfQualDeclarApprovalInfoAction()
		{
			
		}
		private ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS;
		private TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo;
		
		public String SaveQualDeclarApprovalInfo()
		{
			try
			{
				tfQualDeclarApprovalinfo= new TfQualDeclarApprovalinfo();
//				tfQualDeclarApprovalinfo.setOpinionInfo(opinionInfo);
//				tfQualDeclarApprovalinfo.setPid(pid);
//				tfQualDeclarApprovalinfo.setSigningDate(DateTool.getNowDate());
//				tfQualDeclarApprovalinfo.setSigningid(signingid);
//				tfQualDeclarApprovalinfo.setState(state);
				tfQualDeclarApprovalInfoBS.save(tfQualDeclarApprovalinfo);
				this.message = this.getSuccessMessage("保存成功！", "tfQuaApply",
						"closeCurrent", "jbpm4/tf-qual-apply-jbpm!index.do");
			}
			catch(Exception e)
			{
				this.message = this.getFailMessage("流程扭转失败！");
				tfQualDeclarApprovalInfoBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
				e.printStackTrace();
			}
			return "SUCCESS";
		}
		
		
		
		
		
		public ITfQualDeclarApprovalInfoBS getTfQualDeclarApprovalInfoBS() {
			return tfQualDeclarApprovalInfoBS;
		}
		public void setTfQualDeclarApprovalInfoBS(
				ITfQualDeclarApprovalInfoBS tfQualDeclarApprovalInfoBS) {
			this.tfQualDeclarApprovalInfoBS = tfQualDeclarApprovalInfoBS;
		}
		public Message getMessage() {
			return message;
		}
		public void setMessage(Message message) {
			this.message = message;
		}
		public TfQualDeclarApprovalinfo getTfQualDeclarApprovalinfo() {
			return tfQualDeclarApprovalinfo;
		}
		public void setTfQualDeclarApprovalinfo(
				TfQualDeclarApprovalinfo tfQualDeclarApprovalinfo) {
			this.tfQualDeclarApprovalinfo = tfQualDeclarApprovalinfo;
		}
}
