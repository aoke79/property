package com.sms.training.qualification.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualApplyJbpmDao;

@Repository("tfQualApplyJbpmDao")
@Transactional
public class TfQualApplyJbpmDao  extends PublicDao implements ITfQualApplyJbpmDao{

	@Override
	public int countPilotOfApply(String orgRole, String subGroupId) {
		StringBuffer sql = new StringBuffer(300);
		sql.append(
		"select count(detail.declaredinfoid)" +
				" from tf_qual_declara_pilot detail" +
			" where exists (" +
				" select planInfo.string_value_ " +
					"from jbpm4_variable  planInfo," +
						" jbpm4_variable  subGrpId," +
						" jbpm4_task      task" +
				  " where task.execution_ = planInfo.execution_" +
					" and task.execution_ = subGrpId.execution_" +
					" and task.assignee_ ='" ).append(orgRole).append("'").append(
					" and task.execution_id_ like 'qualificationApply%'" +
					" and planInfo.key_ = 'planInfoId'" +
					" and subGrpId.key_ = 'subGroupId'" +
					" and subGrpId.string_value_ = '").append(subGroupId).append("'").append(
					" and planInfo.string_value_ = detail.declaredinfoid" +
			" )" ).append(
			" and detail.state is null");
		List<Object> ls = this.findBySQL(sql.toString());
		BigDecimal dcm = (BigDecimal)ls.get(0);
		int count =dcm.intValue();
		return count;
	}
	
	@Override
	public String  getForkTaskIdByIdAndOrgRole(String processInstanceId,String orgRole) {
		String sql ="select task.dbid_" +
				" from jbpm4_task task" +
				" where task.assignee_ = '" +
				orgRole +
				"' and task.execution_id_ like '" +
				processInstanceId +
				"%'";
		List<Object> taskIdList = this.findBySQL(sql.toString());
		String taskId = null;
		if(taskIdList!=null && taskIdList.size()!=0){
			taskId = taskIdList.get(0).toString();
		}
		return taskId;
	}

	@Override
	public List<Object> findQualityTaskInfos(String orgRole, String subGroupId) {
		StringBuffer sql = new StringBuffer(400);
		sql.append(
			" select planName.string_value_ as pName,"+
			       " orgName.string_value_  as org,"+
			       " userName.string_value_ as rptUser,"+
			       " to_char(task.create_, 'yyyy-mm-dd hh24:mi:ss') as rptDate,"+
			       " task.form_ as formUrl,"+
			       " task.dbid_ as taskId "+
			  " from jbpm4_variable  planName,"+
			       " jbpm4_variable  orgName,"+
			       " jbpm4_variable  userName,"+
			       " jbpm4_variable  subGrpId,"+
			       " jbpm4_task      task"+
			 " where task.execution_ = planName.execution_"+
			   " and task.execution_ = orgName.execution_"+
			   " and task.execution_ = userName.execution_"+
			   " and task.execution_ = subGrpId.execution_"+
			   " and task.assignee_ ='").append(orgRole).append("' ").append(
			   " and task.execution_id_ like 'qualificationApply%'"+
			   " and subGrpId.key_ = 'subGroupId'"+
			   " and subGrpId.string_value_ = '").append(subGroupId).append("'").append(
			   " and planName.Key_ = 'planInfoName'"+
			   " and orgName.key_ = 'FromOrgName'"+
			   " and userName.Key_ = 'FromOrgUser'"+
			 " order by task.create_ desc");
		return this.findBySQL(sql.toString());
	}
	
}
