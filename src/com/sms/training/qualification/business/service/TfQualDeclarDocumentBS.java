package com.sms.training.qualification.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.Util;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualDeclarDocument;
import com.sms.training.qualification.bean.TfQualDeclarSeal;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQualDeclarDocumentBS;
import com.sms.training.qualification.business.ITfQualDeclaraPilotBS;
@Service("tfQualDeclarDocumentBS")
public class TfQualDeclarDocumentBS extends BaseBS implements ITfQualDeclarDocumentBS{
	private ITfQualDeclaraPilotBS tfQualDeclaraPilotBS;
	public TfQualDeclarSeal getTfQualDeclarSealByOrgId(String orgId)
	{
		StringBuffer bf = new StringBuffer(" from TfQualDeclarSeal t where t.organization.id = '"+orgId+"'");
		List<TfQualDeclarSeal> list = this.findPageByQuery(bf.toString()) ;
		if(list.size()!=0 && list.size()>0)
		{
			return list.get(0);
		}else
		{
			return null;
		}
	}
	
	public BaseAirPlanType getBaseAirPlanTypeByAtrId(String atrid)
	{
		StringBuffer bf = new StringBuffer(" from BaseAirPlanType t where t.atrid = '"+atrid+"'");
		return (BaseAirPlanType) this.findPageByQuery(bf.toString()).get(0);
	}
	
	/**
	 * 根据呈批件类型 及 申报信息id查找对应呈批件
	 * @param planType
	 * @param declaredinfoid
	 * @return
	 */
	@Override
	public TfQualDeclarDocument findDocument(String planType, String declaredinfoid) {
		String hql=" from TfQualDeclarDocument d where d.planType = '"+planType+"' and d.declaredinfoid='"+declaredinfoid+"' ";
		List<TfQualDeclarDocument> list= this.findPageByQuery(hql);
		return list==null || list.size()<=0 ? null : list.get(0);
	}
	/**
	 * 根据不同的文件号内容查找存在的最大文件号+1
	 * @param  
	 * @return  
	 * @author yanxu
	 */
	public String getMaxHeadNum(String constr,String numstr){
		String startPosition="";
		StringBuffer sql =new StringBuffer();
		if(numstr==null||numstr.equals("")){
			startPosition=constr.length()+9+"";
			sql=sql.append("select max(cast(substr(documentId,"+startPosition+",6)as int)) from TfQualDeclarDocument d where d.documentId like '%"+constr+"%' ");
		}else{
			startPosition=constr.length()+9+"";
			String endPosition=Integer.parseInt(startPosition)+1+"";
			sql=sql.append("select max(cast(substr(documentId,"+startPosition+",length(documentId)-"+endPosition+")as int)) from TfQualDeclarDocument d where d.documentId like '%"+constr+"%' and substr(document_id,length(document_id),1) = '"+numstr+"'");
		}
		Object o=this.findPageByQuery(sql.toString()).get(0);
		if(o!=null&&o!=""){
			return (Integer.parseInt(o.toString().trim())+1)+"";
		}else{
			return "1";
		}	
	}
	/**
	 * 审核阶段根据不同的报表往declarpolit表加呈批件id
	 * @param  
	 * @return  
	 * @author yanxu
	 */
	public void addTableInfoToPolit(String type,String ids,String documentId){
		List<TfQualDeclaraPilot> tfQualDeclaraPilotList=new ArrayList<TfQualDeclaraPilot>();
		String idsStr = Util.toStringIds(ids);
		tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
		for (TfQualDeclaraPilot t : tfQualDeclaraPilotList) {
			if(type.equals("gsfgTrainNotice")){
				t.setTranplanid(documentId);
			}else if(type.equals("quaApproveReport")){
				t.setAppreportid(documentId);
			}else if(type.equals("quaApproveNotice")){
				t.setAppnoticeid(documentId);
			}
			tfQualDeclaraPilotBS.save(t);
		}
		
	}
	
	/**
	 * 审核阶段 根据pilot表字段 删除doc
	 * @param  
	 * @return  
	 * @author yanxu
	 */
	public void delDocIfCreatedForVerify(String type,String ids){
		List<TfQualDeclaraPilot> tfQualDeclaraPilotList=new ArrayList<TfQualDeclaraPilot>();
		String idsStr = Util.toStringIds(ids);
		tfQualDeclaraPilotList=tfQualDeclaraPilotBS.getPilotByIds(idsStr);
		for (TfQualDeclaraPilot t : tfQualDeclaraPilotList) {
			if(type.equals("gsfgTrainNotice")){
				if(t.getTranplanid()!=null&&!t.getTranplanid().equals("")){
					String docid=t.getTranplanid();
					TfQualDeclarDocument document=this.findById(TfQualDeclarDocument.class, docid);
					if(document!=null)
					this.delete(document);
					t.setTranplanid("");
				}
			}else if(type.equals("quaApproveReport")){
				if(t.getAppreportid()!=null&&!t.getAppreportid().equals("")){
					String docid=t.getAppreportid();
					TfQualDeclarDocument document=this.findById(TfQualDeclarDocument.class, docid);
					if(document!=null)
					this.delete(document);
					t.setAppreportid("");
				}
			}else if(type.equals("quaApproveNotice")){
				if(t.getAppnoticeid()!=null&&!t.getAppnoticeid().equals("")){
					String docid=t.getAppnoticeid();
					TfQualDeclarDocument document=this.findById(TfQualDeclarDocument.class, docid);
					if(document!=null)
					this.delete(document);
					t.setAppnoticeid("");
				}
			}
			tfQualDeclaraPilotBS.save(t);
		}
	}
	/**
	 * 申报阶段 根据创建者 报表类型 申请id判断是否之前生成过该报表 如果生成过 则删除
	 * @param  
	 * @return  
	 * @author yanxu
	 */
	public void delDocIfCreatedForDeclara(String creater,String plantype,String declareinfoid){
		String hql=" from TfQualDeclarDocument d where d.creater = '"+creater+"' and d.planType = '"+plantype+"' and d.declaredinfoid='"+declareinfoid+"' ";
		List<TfQualDeclarDocument> list=this.findPageByQuery(hql);
		if(list!=null&&list.size()!=0){
			TfQualDeclarDocument doc=list.get(0);
			this.delete(doc);
		}
	}

	public ITfQualDeclaraPilotBS getTfQualDeclaraPilotBS() {
		return tfQualDeclaraPilotBS;
	}
	@Resource
	public void setTfQualDeclaraPilotBS(ITfQualDeclaraPilotBS tfQualDeclaraPilotBS) {
		this.tfQualDeclaraPilotBS = tfQualDeclaraPilotBS;
	}
	
}
