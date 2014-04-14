package com.sms.training.qualification.business.service;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysRole;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.InspectionFormData;
import com.sms.training.qualification.bean.TfQualBaseComment;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualInspectionReport;
import com.sms.training.qualification.bean.TfQualInspectionReportD;
import com.sms.training.qualification.business.ITfQualInspectionReportBS;

@Service("tfQualInspectionReportBS")
public class TfQualInspectionReportBS extends BaseBS implements
		ITfQualInspectionReportBS {
	/**
	 * 根据申报人员明细id查找对应的技术检查报告
	 * @param detailsid 申报人员明细id
	 * @param reportType 报告类型
	 * @return 技术检查报告
	 */
	@Override
	public TfQualInspectionReport findInspectionReport(String detailsid,String reportType) {
//		return this.findUniqueBySingleQuery("TfQualInspectionReport", "tfQualDeclaraPilot.detailsid", detailsid);
		String hql = " from TfQualInspectionReport rp where rp.tfQualDeclaraPilot.detailsid='"+detailsid+"' " +
				" and rp.reporttype='"+reportType+"' ";
		List<TfQualInspectionReport> list=this.findPageByQuery(hql);
		if(list!=null && list.size()!=0){
			return list.get(0);
		}
		return null;
	}
	/**
	 *根据检查报告id查找到对应的 技术检查报告成绩 list
	 *@return 技术检查报告成绩 list
	 */
	@Override
	public List<TfQualInspectionReportD> findResultList(String reportid) {
		String hql="from TfQualInspectionReportD rd where rd.tfQualInspectionReport.reportid='"+reportid+"'";
		List<TfQualInspectionReportD> results=this.findPageByQuery(hql);
		return results;
	}
	/**
	 * 保存或者更新检查报告成绩
	 * @param form 检查报告 表单数据类
	 * @param report 飞行员技术检查报告
	 */
	@Override
	public void saveResults(InspectionFormData form, TfQualInspectionReport report) throws Exception {
		//根据检查报告id查找到对应的 技术检查报告成绩 list
		List<TfQualInspectionReportD> results = this.findResultList(report.getReportid());
		//如果成绩list不空，根据form中的更新数据来更新成绩，使用反射
		if(results!=null && results.size()!=0){
			for(TfQualInspectionReportD rd : results){
				Method method= InspectionFormData.class.getDeclaredMethod("getJcbg"+rd.getBasedataid());
				TfQualInspectionReportD reportD= (TfQualInspectionReportD) method.invoke(form);
				rd.setMark(reportD.getMark());
				rd.setRemarks(reportD.getRemarks());
				this.update(rd, rd.getResultid());
			}
			return;
		}
		//如果成绩list为空，根据form中的更新数据来保存成绩，使用反射
		for (int i = 128; i <= 166; i++) {
			Method method= InspectionFormData.class.getDeclaredMethod("getJcbg"+i);
			TfQualInspectionReportD reportD= (TfQualInspectionReportD) method.invoke(form);
			reportD.setBasedataid(i+"");
			reportD.setTfQualInspectionReport(report);
			this.save(reportD);
		}
	}
	
	@Override
	public List<TfQualBaseComment> getcommentTemplates(List<SysRole> roles,String ctype) {
		StringBuffer ids=new StringBuffer();
		for(SysRole rl : roles){
			if(rl.getRoleId()!=null && !rl.getRoleId().equals("")){
				ids.append("'").append(rl.getRoleId()).append("',");
			}
		}
		if(ids.length()>3){
			ids.deleteCharAt(ids.length()-1);
		}
		String hql=" from TfQualBaseComment c where c.sysRole.roleId in ("+ids.toString()+") and c.ctype='"+ctype+"'";
		return this.findPageByQuery(hql);
	}
	/**
	 * 根据申报人员明细id和最后修改人  查找对应的 本场训练单
	 * @param detailId 申报人员明细id
	 * @param modifier 最后修改人
	 * @param reportType 单子类型
	 * @return 本场训练单
	 */
	@Override
	public TfQualInspectionReport findByDetailIdAndModifier(String detailId,String modifier, String reportType) {
		String hql = "from TfQualInspectionReport c where c.tfQualDeclaraPilot.detailsid='"
			+ detailId + "' and modifier='" + modifier + "'and reporttype='"+ reportType+"'";
	List<TfQualInspectionReport> list = findPageByQuery(hql);
	if (list != null && list.size() != 0) {
		return list.get(0);
	}
		return null;
	}

}
