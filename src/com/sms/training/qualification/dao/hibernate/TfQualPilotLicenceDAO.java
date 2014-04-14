package com.sms.training.qualification.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualPilotLicenceDAO;
@Repository("tfQualPilotLicenceDAO")
@Transactional
public class TfQualPilotLicenceDAO extends PublicDao implements ITfQualPilotLicenceDAO {

	@Override
	public List<Object> getPilotLicenceList(String pilotid) {
		String sql = "select l.atrid ,a.atrdesc, case when l.plcstus ='0' then '有效'  when l.plcstus ='1' then '无效' else '' end  plcstus,to_char(l.plccheckd ,'yyyy-mm-dd') plccheckd , case when l.plclifecycle ='1' then '商用驾驶员执照'  when l.plclifecycle ='2' then '航线运输驾驶员执照' else '' end  plclifecycle,p.ptrils ,p.ptgradeid  , case when p.ptrcurrent ='0' then '现飞'  when p.ptrcurrent ='1' then '曾飞' else '' end  ptrcurrent,p.ptrfltexptimetotaltotal, p.ptrmovemnettotal from tf_qual_pilot_licence l , tf_qual_pilot_techrecord p,base_airplantype a  where l.plcseq = p.plcseq(+) and l.atrid = a.id and l.pilotid='"
				+ pilotid
				+ "' order by l.plcstus,l.plccheckd desc,p.ptrcurrent";
		return this.findBySQL(sql);
	}   
	//获取技术等级页面信息
	@Override
	public List<Object> getLibraryGrade(String pilotId) {
		String sql = "select l.atrid ,a.atrdesc, " +
				"case" +
				" when l.plcstus ='0' then '有效' " +
				" when l.plcstus ='1' then '无效' else '' end " +
				" plcstus,to_char(l.plccheckd ,'yyyy-mm-dd') plccheckd ," +
				" case " +
				" when l.plclifecycle is null  then '商用驾驶员执照' " +
				"when l.plclifecycle ='1'   then '商用驾驶员执照' " +
				" when l.plclifecycle ='2' then '航线运输驾驶员执照' else '' end " +
				" plclifecycle,p.ptrils ,p.ptgradeid  ," +
				" case" +
				" when p.ptrcurrent ='0' then '现飞' " +
				" when p.ptrcurrent ='1' then '曾飞' else '' end " +                //l.ENDORSEMENT
				" ptrcurrent,p.ptrfltexptimetotaltotal, p.ptrmovemnettotal,to_char(p.Ptrapproved ,'yyyy-mm-dd') ENDORSEMENT ,l.RVRUP,l.RVRDOWN" +
				" from tf_qual_pilot_licence l , tf_qual_pilot_techrecord p,base_airplantype a " +
				" where l.plcseq = p.plcseq(+) and l.atrid = a.id and l.pilotid='"
				+ pilotId
				+ "' order by l.plcstus,l.plccheckd desc,p.ptrcurrent";
		return this.findBySQL(sql);
	}
	
	//获取资格信息页面信息
	@Override
	public List<Object> getLibraryQualifications(String pilotId) {
		String sql = "select q.PQUAID,q.atrid ,a.atrdesc,p.PQUADESC," +
				" to_char(q.PQRECAPPROVED ,'yyyy-mm-dd') PQRECAPPROVED ," +
				" to_char(q.PQRECEXPIRED ,'yyyy-mm-dd') PQRECEXPIRED " +
				" from tf_qual_pilot_qualificationrec q ,base_airplantype a ,tf_qual_base_pilotqualify p" +
				" where q.PQUAID = p.PQUAID and q.atrid = a.id and q.pilotid='"
				+ pilotId
				+ "' order by q.atrid";
		return this.findBySQL(sql);
	}
	
 
    //获取英语资质页面信息
	@Override
	public List<Object> getLibraryEnglish(String pilotId) {
		String sql = "select " +
				" to_char(e.QUALIFICATION_DATE ,'yyyy-mm-dd') QUALIFICATION_DATE , " +
				" case " +
				" when e.QUALIFICATION ='4' then  to_char(QUALIFICATION_DATE + interval '3' year,'yyyy-mm-dd') " +
				" when e.QUALIFICATION ='5' then  to_char(QUALIFICATION_DATE + interval '6' year,'yyyy-mm-dd') " +
				" else '' end " +
				" expire ," +
				" case " +
				" when e.QUALIFICATION ='4' then 'ICAO四级' " +
				" when e.QUALIFICATION ='5' then 'ICAO五级' " +
				" when e.QUALIFICATION ='6' then 'ICAO六级' else '' end " +
				" QUALIFICATION " +
				" from tf_en_exam_qualification e " +
				" where e.EXAMINEE_ID='"
				+ pilotId
				+ "' order by e.EXAMINEE_ID";
		return this.findBySQL(sql);
	}

	//获取证书类页面信息
	@Override
	public List<Object> getLibraryCertificate(String pilotId) {
		String sql = "select " +
				" to_char(r.RFIRSTDATE ,'yyyy-mm-dd') RFIRSTDATE ," +
				" to_char(RLASTDATE + interval '1' year,'yyyy-mm-dd') RLASTDATE ," +
				" to_char(r.DGFIRSTDATE ,'yyyy-mm-dd') DGFIRSTDATE," +
				" to_char(DGLASTDATE + interval '2' year,'yyyy-mm-dd') DGLASTDATE " +
				" from tf_qual_base_ritdate r  " +
				" where r.PILOTID='"
				+ pilotId
				+ "' order by r.PILOTID";
		return this.findBySQL(sql);
	}

	//获取特殊运行资质页面信息
	@Override
	public List<Object> getLibrarySpecial(String pilotId) {
		String sql = "select q.PQUAID,q.atrid ,a.atrdesc," +
				" to_char(q.PQRECAPPROVED ,'yyyy-mm-dd') PQRECAPPROVED ," +
				" to_char(q.PQRECEXPIRED ,'yyyy-mm-dd') PQRECEXPIRED " +
				" from tf_qual_pilot_qualificationrec q ,base_airplantype a " +
				" where q.atrid = a.id and q.pilotid='"
				+ pilotId
				+ "' order by q.atrid";
		return this.findBySQL(sql);
	}
	
	//获取训练记录页面信息
	@Override
	public List<Object> getLibraryTrainingRecords(String pilotId) {
		String sql = "select t.TPPLANNO,t.TPPLANNAME ," +
				" to_char(t.TPPLANSTARTDATE ,'yyyy-mm-dd') TPPLANSTARTDATE ," +
				" to_char(t.TPPLANENDDATE ,'yyyy-mm-dd') TPPLANENDDATE, " +
				" case " +
				" when t.P_IDENTITY ='s' then '学员' " +
				" when t.P_IDENTITY ='t' then '教员' " +
				" when t.P_IDENTITY ='c' then '检查员' else '' end " +
				" P_IDENTITY,t.MINUTE" +
				" from tf_qual_pilot_trainrecords t " +
				" where t.PILOTID='"
				+ pilotId
				+ "' order by t.TPPLANNO";
		return this.findBySQL(sql);
	}
	
}