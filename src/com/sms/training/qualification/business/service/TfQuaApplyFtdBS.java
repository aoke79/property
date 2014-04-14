package com.sms.training.qualification.business.service;

import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.CmUser;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.DateTool;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualDeclarInfo;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.business.ITfQuaApplyFtdBS;
@Service("tfQuaApplyFtdBS")
public class TfQuaApplyFtdBS extends BaseBS implements ITfQuaApplyFtdBS {
	private TfQualBaseType tfQualBaseType = new TfQualBaseType();
	private CmPeople cmPeople = new CmPeople();
	private TfQualDeclarInfo tfQualDeclarInfo = new TfQualDeclarInfo();
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	@Override
	public List<Object> getAvailablePilotStayList(TfQualBaseType type, String orgNameStr) {
		String result=" p.id,p.name,p.idcard,r.baseAirplantype.atrdesc,r.ptgradeid,r.ptrfltexptimetotaltotal ";
		String sql=genHqlStr(result,orgNameStr, type).append(" order by p.name ").toString();
		return  this.findPageByQuery(sql);
	}

	private StringBuffer genHqlStr(String result, String orgNameStr, TfQualBaseType type){
		StringBuffer sql=new StringBuffer("select ").append(result);
		sql.append(" from CmPeople p ,TfQualPilotTechrecord r ,BaseAirplanType a ,TfQualPilotLicence l ");
		sql.append(" where p.id=r.cmPeople.id and  a.id=l.atrid and l.atrid=r.baseAirplantype.id and l.plcseq=r.tfQualPilotLicence.plcseq and l.plcstus='0' and r.ptrcurrent='0' " );
		sql.append(" and r.ptgradeid in ('TA','TB','TC') ");
		String atrDesc=type.getTargetatrid().getAtrdesc();
		if(atrDesc!=null && !atrDesc.equals("")){
			sql.append(" and a.atrdesc='"+atrDesc+"'");
		}
		if(orgNameStr!=null && !orgNameStr.equals(""))
		{
			sql.append(" and p.sysOrganization.id in "+orgNameStr);
		}
		sql.append(" and p.id not in ( select st.cmPeople.id from TfQualDeclaraPilotStay st where st.tfQualBaseType.typeid='").append(type.getTypeid()).append("' and ( st.status='W' or st.status='N' or st.status is null) )");
		return sql;
	}

	@Override
	public String createDeclarInfo(String typeid, String ids, CmUser cmUser) {
		return this.saveDeclarInfo(typeid,ids,cmUser);
	}
	/**
	 * 保存申报信息
	 * @param typeid
	 * @param ids
	 * @param cmUser
	 * @return
	 */
	private String saveDeclarInfo(String typeid ,String ids ,CmUser cmUser){
		try {
			this.savePilotStays(typeid ,ids ,cmUser);
			
			this.tfQualBaseType = new TfQualBaseType(typeid);
			this.tfQualDeclarInfo.setTfQualBaseType(tfQualBaseType);
			this.tfQualDeclarInfo.setCreatedt(DateTool.getNowDate());
			this.tfQualDeclarInfo.setCreator(cmUser.getUserId());
			this.tfQualDeclarInfo.setStatus("W");//Wait 等待申报
			this.tfQualDeclarInfo.setDeclaredinfodesc(this.getDeclaredinfodescFotTypeid(typeid,cmUser));
			this.save(tfQualDeclarInfo);
			String [] pilotids = StringUtils.split(ids,",");
			//往申报人员明细表添加数据e 
			for (int i = 0,j = pilotids.length; i < j; i++) {
				this.tfQualDeclaraPilot = new TfQualDeclaraPilot();
				this.cmPeople.setId(pilotids[i].trim());
				this.tfQualDeclaraPilot.setCmPeople(cmPeople);
				this.tfQualDeclaraPilot.setTfQualDeclarInfo(tfQualDeclarInfo);
				this.tfQualDeclaraPilot.setTfQualBaseType(tfQualBaseType);
				this.save(tfQualDeclaraPilot);
			}
			this.updatePilotStay(typeid,ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tfQualDeclarInfo.getDeclaredinfoid();
	}
	
	@Override
	public void savePilotStays(String typeid, String ids, CmUser cmUser){
		try {
			String[] pilotIds=StringUtils.split(ids, ",");
			TfQualDeclaraPilotStay stay=null;
			HashSet<String> set=new HashSet<String>();
			for(String id : pilotIds){
				set.add(id);
			}
			for (String id : set) {
				stay= new TfQualDeclaraPilotStay();
				stay.setCmPeople(new CmPeople(id));
				stay.setTfQualBaseType(new TfQualBaseType(typeid));
				this.saveOrUpdate(stay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新stay表中的statues字段
	 * @param typeid
	 * @param ids
	 */
	private void updatePilotStay(String typeid, String ids){
		String pilotids = ids.replace(",", "','");
		String hql ="update TfQualDeclaraPilotStay set status='W' where status is null and tfQualBaseType.typeid='"+typeid+"' and cmPeople.id in ('"+pilotids+"')";
		this.executeUpdate(hql);
	}
	/**
	 * 自动生成申报信息名称
	 * @param typeid
	 * @return   FK_DECLARE_TYPEID  TYPEID  TF_QUAL_BASE_TYPE  TYPEID
	 */
	private String getDeclaredinfodescFotTypeid(String typeid,CmUser cmUser){
		StringBuffer sb = new StringBuffer(100);
		sb.append(DateTool.formatDate(DateTool.getNowDate(), "yyyy年MM月dd日"));
		this.tfQualBaseType= this.findById(TfQualBaseType.class, typeid);
		if(this.getOrganizationByUser(cmUser).get(0).getName().contains("天津"))
		{
			sb.append(tfQualBaseType.getTypedesc()).append("(天津分公司)");
		}else
		{
			sb.append(tfQualBaseType.getTypedesc()).append("("+this.getOrganizationByUser(cmUser).get(0).getName()+")");
		}
		return sb.toString();
	}
	
	
}
