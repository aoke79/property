package com.sms.training.qualification.business.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.jbpm4.ProcessBase;
import com.sinoframe.common.util.DateTool;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.business.ITfQuaApplyJbpmBS;
import com.sms.training.qualification.dao.ITfQualApplyJbpmDao;
@Service("tfQuaApplyJbpmBS")
public class TfQuaApplyJbpmBS  extends BaseBS implements ITfQuaApplyJbpmBS {
	ProcessBase processBase = new ProcessBase();
	private ITfQualApplyJbpmDao tfQualApplyJbpmDao;
	public String getTaskIdByPiID(String processInstanceId){
			return processBase.getTaskIdByPiID(processInstanceId);
	}
	public String getTaskNameByPiID(String processInstanceId){
		return processBase.getTaskNameByPiID(processInstanceId);
	}
	
	@Override
	public String getForkTaskId(String processInstanceId,String orgRole){
		return tfQualApplyJbpmDao.getForkTaskIdByIdAndOrgRole(processInstanceId, orgRole);
	}
	@Override
	public String deploymentProcessDefinition(CmUser user, String streamPath) {
		String pdId = processBase.getProcessDefinitionIdByPath(streamPath);
		return pdId;
	}

	@Override
	public String startProcessEnTrainEstimateByUser(CmUser user,
			SysOrganization sysOrganization, String pdId1, String pdId2,
			String year) {
		return null;
	}

	@Override
	public String startProcessByPid(String pdId,Map<String, Object> map) {
		String putOutProcessInstance = processBase.putOutProcessInstance(pdId,
				map);
		return putOutProcessInstance;
	}

	@Override
	public String completeProcessById(String id, String idType,
			String nextName, Map<String, Object> map) {
		String taskId = "";
		if ("instance".equals(idType.toLowerCase())) {
			taskId = this.processBase.getTaskIdByPiID(id);
		} else if ("task".equals(idType.toLowerCase())) {
			taskId = id;
		}
		// 根据user权限获取相关权限
		this.processBase.completeTaskById(taskId, nextName, map);
		return taskId;
	}
	public List<SysRole> getSessionRoleList(CmUser cmUser) {
		String hql="select sr.sysRole from SysRelationAccountRole sr where sr.cmUser.userId='"+ cmUser.getUserId() + "' and sr.sysRole.roleName like '%资质-%'";
		return this.findPageByQuery(hql);
	}
	
	@Override
	public void filter(List<Task> taskList, Map<String, String> taskplanMap,
			Map<String, String> deptsList,SysOrganization org,String subGroupId) {
		String subPid = "qualificationApply";//ConstantList.PROCESSNAME.get("qualificationApply");
		Task task=null;
		for (Iterator<Task> it=taskList.iterator();it.hasNext();) {
			task=it.next();
			if (task.getExecutionId().indexOf(subPid) < 0){
				it.remove();
			}else{
				//过滤掉其他类别待办
				// 开启流程时设置变量“小类id”，根据该变量过滤待办
				Object varSubGroupId= processBase.getVariable(task.getId(), "subGroupId");
				if(varSubGroupId==null || !varSubGroupId.toString().equals(subGroupId.trim())){
					it.remove();
				}else{
					taskplanMap.put(task.getId(), processBase.getVariable(task.getId(), "planInfoName").toString());
				}
			}
		}
		// 过滤之后要对列表进行排序
		Collections.sort(taskList, new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o2.getCreateTime().compareTo(o1.getCreateTime());
			}
		});
	}
	/**
	 * 根据类型过滤资质待办
	 * @param taskList 待办列表
	 * @param subGroupId 资质类型分类（小类）
	 */
	@Override
	public void filter(List<Task> taskList,String subGroupId) {
		String subPid = "qualificationApply";//ConstantList.PROCESSNAME.get("qualificationApply");
		Task task=null;
		for(Iterator<Task> it=taskList.iterator();it.hasNext();){
			task=it.next();
			//过滤掉非资质待办
			if(task.getExecutionId().indexOf(subPid) < 0){
				it.remove();
			}else{
				//过滤掉其他类别待办
				// 开启流程时设置变量“小类id”，根据该变量过滤待办
				Object varSubGroupId= processBase.getVariable(task.getId(), "subGroupId");//.toString();
				if(varSubGroupId==null || !varSubGroupId.toString().equals(subGroupId.trim())){
					it.remove();
				}
			}
		}
	}
	
		/**
		 * 
		 * TODO
		 * 
		 * @param pid
		 * @param deptsList
		 * @param flag
		 *            1代表 通过父流程查询 2 代表通过子流程来查
		 * @return Nov 23, 20119:55:15 AM
		 * @author niujingwei
		 */
		private String findNameInfoQualification(String taskID,
				Map<String, String> deptsList, int flag) {
			String infoString = "";
			try {
				StringBuffer sBuffer = new StringBuffer();
				//update by QLL 
				Object variable = processBase.getVariable(taskID, "task_plan");
				sBuffer.append("from TfQualDeclarInfo t where t.processid='").append(
						variable.toString()).append("'");
				List<TfQualDeclarInfo> TfQualDeclarInfoList = this.findPageByQuery(sBuffer.toString());
				if (TfQualDeclarInfoList.size()!=0) {
					TfQualDeclarInfo tfQualDeclarInfo = TfQualDeclarInfoList.get(0);
					// 那一年的任务
					infoString = tfQualDeclarInfo.getDeclaredinfodesc();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return infoString;
		}
		@Override
		public void dealTaskFrom(List<Task> taskList, Map<String, String> namesMap,
				Map<String, String> orgsNameMap, SysOrganization sysOrganization,
				Map<String, String> deptsList, int flg) {
//			ConstantList constantList = new ConstantList();
			String processInstanceId = null;
			String taskID = "";
			String keyReject = "";
			Object orgName ="";
			Object userName ="";
			 if (flg == 3) {
				for (int i = 0, len = taskList.size(); i < len; i++) {
					keyReject = "";
					taskID = taskList.get(i).getId();
					// 获得流程实例，通过实例来判断是父流程是还子流程
					processInstanceId = processBase.getProcessInstanceId(taskList
							.get(i).getId());
					//资质
//					if(processInstanceId != null&&(processInstanceId.contains(constantList.PROCESSNAME.get("qualificationApply"))||processInstanceId.contains(constantList.PROCESSNAME.get("subQualificationApply"))))
					if(processInstanceId != null&&(processInstanceId.contains("qualificationApply")))
					{
							orgName = processBase.getVariable(taskID, "FromOrgName");
							userName = processBase.getVariable(taskID, "FromOrgUser");
							namesMap.put(i + "", userName.toString());
							orgsNameMap.put(i + "", orgName.toString());
					} 
				}
			}

		}
		
		@Override
		public void isReject(List<Task> taskList, Map<String, String> isRejectMap,
				Map<String, String> reasonMap) {
			String taskId;
//			ConstantList constantList = new ConstantList();
			for (int i = 0, len = taskList.size(); i < len; i++) {
				taskId = taskList.get(i).getId();

				Object variable = processBase.getVariable(taskId, "owner");
				String str = variable == null ? "" : variable.toString().substring(
						0, 32);
				String processInstanceId = processBase.getProcessInstanceId(taskId);
				if (processInstanceId.contains("trainSignUp")
						|| processInstanceId.contains("trainSignUp2")
						|| processInstanceId.contains("enExamSign")
						|| processInstanceId.contains("enExamSign2")) {
					str = (String) processBase.getVariable(taskId, "zdorxldd");
					if (str != null) {
						str = str.substring(0, 32);
					}
				}

				Object object = processBase.getVariable(taskId, "isReject" + str);

				if (object != null && object.toString().equals("yes")) {
					isRejectMap.put(i + "", " [驳回] ");

				}

				// Object object1 = processBase.getVariable(taskId, "isReject");
				//
				// if (object1 != null && object1.toString().equals("yes")) {
				// isRejectMap.put(i + "", " [驳回] ");
				//
				// }

				Object reason = processBase.getVariable(taskId, "reason" + str);

				if (reason != null) {
					reasonMap.put(i + "", "驳回理由：" + reason.toString());

				}

				// Object reason1 = processBase.getVariable(taskId, "reason");
				//
				// if (reason1 != null) {
				// reasonMap.put(i + "", "驳回理由：" + reason1.toString());
				// }

			}

		}
		@Override
		public String deployProcessDefinition(String streamPath) {
			String pdId = processBase.findProcessDefinitionIdByPath(streamPath);
			return pdId;
		}
		 
		@Override
		public List<Object> getQualityTaskInfos(String orgRole, String subGroupId){
			return this.tfQualApplyJbpmDao.findQualityTaskInfos(orgRole, subGroupId);
		}
		
		public ITfQualApplyJbpmDao getTfQualApplyJbpmDao() {
			return tfQualApplyJbpmDao;
		}
		@Resource
		public void setTfQualApplyJbpmDao(ITfQualApplyJbpmDao tfQualApplyJbpmDao) {
			this.tfQualApplyJbpmDao = tfQualApplyJbpmDao;
		}
}
