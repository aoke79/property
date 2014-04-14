package com.sms.training.qualification.business.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.Md5;
import com.sinoframe.common.util.Util;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.training.qualification.bean.TfQualPilotSignature;
import com.sms.training.qualification.business.ITfQualPilotSignatureBS;

@Service("tfQualPilotSignatureBS")
public class TfQualPilotSignatureBS extends BaseBS implements
		ITfQualPilotSignatureBS {
	/**
	 * 获取 飞行员签名list
	 * @param sysPageInfo 
	 * @param query 
	 * @param sysOrderByInfo 
	 * @return 
	 */
	@Override
	public List<TfQualPilotSignature> findPilotSigList(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		sysPageInfo.setMaxCount(listCount(query,"TfQualPilotSignature"));
		sysOrderByInfo.setOrderColumn("pilotName");
		String hql=" from TfQualPilotSignature where 1=1 ";
		return  this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	}
	
	/**
	 * count(*),查询某表中符合查询条件的所有记录数
	 * @param query
	 * @param entity
	 * @return
	 */
	private long listCount(HashMap<String, String> query, String entity) {
		String hql="select count(*) from "+entity+" where 1=1 ";
		return this.getCountByHQL(hql, query);
	}

	/**
	 * 获取飞行员list
	 * @param sysPageInfo
	 * @param decodeQuery
	 * @param sysOrderByInfo
	 * @return
	 */
	@Override
	public List<CmPeople> findPilotList(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		query.put("eq_sort","P" );
		query.put("eq_status","1" );
		String hql=" from CmPeople where 1=1 ";
		// 显示时需要的字段
		String[] queryShown = { "like_orgName" };
		// 查询时需要的字段
		String[] queryCondition = { "like_sysOrganization.name" };
		this.getQueryCondition(queryShown, queryCondition, Util.decodeQuery(query));
		sysPageInfo.setMaxCount(listCount(query,"CmPeople"));
		sysOrderByInfo.setOrderColumn("sysOrganization.name");
		List<CmPeople> list = this.findPageByQuery(sysPageInfo, hql, query,	sysOrderByInfo);
		// 显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		return list;
	}

	/**
	 * 获取签名图片url 
	 * @param psignatureId 飞行员签名id
	 * @param modulename 模块名
	 * @return
	 */
	@Override
	public String getImgUrl(String signatureId, String moduleName) {
		StringBuffer buf = new StringBuffer(150);
		buf.append("from CmAttachment where fromid = '").append(signatureId).append("' and fromtablename = '").append(moduleName).append("' ");
		List<CmAttachment> fileList = this.findPageByQuery(buf.toString());
		CmAttachment file = new CmAttachment();
		if(!fileList.isEmpty()){
			file = fileList.get(0);
		}
		return file.getAttchpath() == null ? "" : file.getAttchpath();
	}

	/**
	 * 保存文件并更新飞行员签名信息的签名图片url
	 * @param tfQualPilotSignature 飞行员签名
	 * @param modulename
	 * @param fileKey
	 */
//	@Override
//	public void save(TfQualPilotSignature tfQualPilotSignature,
//			String moduleName,String fileKey) {
//		this.save(tfQualPilotSignature);
//		if(!fileKey.equals("")){
//			this.saveFiles(tfQualPilotSignature.getPsignatureId(), moduleName, fileKey);
//		}
//		String imgUrl= this.getImgUrl(tfQualPilotSignature.getPsignatureId(), moduleName);
//		tfQualPilotSignature.setPsignatureUrl(imgUrl);
//		this.update(tfQualPilotSignature,tfQualPilotSignature.getPsignatureId());
//	}

	/**
	 * 删除附件
	 * @param psignatureId 飞行员签名id
	 * @param modulename 模块名
	 */
	@Override
	public void deleteFileById(String psignatureId, String modulename) {
		StringBuffer buf = new StringBuffer(200);
		buf.append("from CmAttachment where fromid = '").append(psignatureId).append("' and fromtablename = '").append(modulename).append("' ");
		List<CmAttachment> fileList = this.findPageByQuery(buf.toString());
		if(!fileList.isEmpty()){
			for (CmAttachment file : fileList) {
				this.deleteById(CmAttachment.class, file.getAttchid());
			}
		}
	}

	/**
	 * 验证
	 * @param account 账号
	 * @param oldPassword 密码
	 * @return bool
	 * @throws Exception
	 */
	@Override
	public boolean valid(String account, String oldPassword) throws Exception {
		String hql=" from TfQualPilotSignature s where s.ploginId='"+account+"'" +
				" and  s.ppassword='"+Md5.getInstance().EncoderByMd5(oldPassword, "")+"' ";
		List<TfQualPilotSignature> list= this.findPageByQuery(hql);
		return !list.isEmpty();
	}

	

}
