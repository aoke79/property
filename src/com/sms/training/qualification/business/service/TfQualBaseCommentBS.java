package com.sms.training.qualification.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.Util;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.training.qualification.bean.TfQualBaseComment;
import com.sms.training.qualification.business.ITfQualBaseCommentBS;
import com.sms.training.qualification.dao.ITfQualBaseCommentDAO;

@Service("tfQualBaseCommentBS")
public class TfQualBaseCommentBS extends BaseBS implements ITfQualBaseCommentBS {
	private ITfQualBaseCommentDAO tfQualBaseCommentDAO;

	@Override
	public List<TfQualBaseComment> findAllByQuery(SysPageInfo sysPageInfo,
			HashMap<String, String> query, SysOrderByInfo sysOrderByInfo) {
		String hql = " from TfQualBaseComment where 1=1 ";
		String countHql = "select count(*) from TfQualBaseComment where 1=1";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		return findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	}

	@Override
	public void saveAndUpdate(TfQualBaseComment tfQualBaseComment) {
		try {
			String id = tfQualBaseComment.getCommentid();
			if (id != null && !id.equals("")) {
				this.update(tfQualBaseComment, id);
			} else {
				String[] comments = tfQualBaseComment.getContents().split("#_#");
				TfQualBaseComment qualBaseComment = null;
				for (String comment : comments) {
					tfQualBaseComment.setContents(comment);
					qualBaseComment = (TfQualBaseComment) this.copy(new TfQualBaseComment(), tfQualBaseComment);
					this.save(qualBaseComment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void BatchDeleteByIds(TfQualBaseComment tfQualBaseComment, String ids) {
		this.deleteByIds(TfQualBaseComment.class, ids);
	}

	@Override
	public long commentListCount(HashMap<String, String> query) {
		long colCount = 0;
		String hql = "select count(*) from TfQualBaseComment where 1=1 ";
		// 显示时需要的字段
		String[] queryShown = { "like_roleName" };
		// 查询时需要的字段
		String[] queryCondition = { "like_sysRole.roleName" };
		this.getQueryCondition(queryShown, queryCondition,Util.decodeQuery(query));
		colCount = this.getCountByHQL(hql, query);
		// 显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		return colCount;
	}

	@Override
	public List<TfQualBaseComment> searchCommentList(SysPageInfo sysPageInfo, SysOrderByInfo sysOrderByInfo, HashMap<String, String> query) {
		List<TfQualBaseComment> commentList = new ArrayList<TfQualBaseComment>();
		String hql = "from TfQualBaseComment where 1=1 ";
		// 显示时需要的字段
		String[] queryShown = { "like_roleName" };
		// 查询时需要的字段
		String[] queryCondition = { "like_sysRole.roleName" };
		this.getQueryCondition(queryShown, queryCondition,Util.decodeQuery(query));
		String countHql ="select count(*) from TfQualBaseComment where 1=1";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		commentList = this.findPageByQuery(sysPageInfo, hql, query,sysOrderByInfo);
		// 显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		return commentList;
	}

	@Override
	public long roleListCount(HashMap<String, String> query) {
		long colCount = 0;
		String hql = "select count(*) from SysRole where 1=1 ";
		colCount = this.getCountByHQL(hql, query);
		return colCount;
	}

	@Override
	public List<SysRole> searchRoleList(SysPageInfo sysPageInfo,SysOrderByInfo sysOrderByInfo, HashMap<String, String> query) {
        List<SysRole> roleList = new ArrayList<SysRole>();	
		String hql = "select r from SysRole r,SysSystem s " +
				" where r.subsystemId=s.id and s.name = '资质子系统'";
		String countHql ="select count(r) from SysRole r,SysSystem s where r.subsystemId=s.id and s.name = '资质子系统'";
		sysPageInfo.setMaxCount(getCountByHql(countHql, query));
		roleList = this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		return roleList;
	}
	
	/**
	 * 
	 * @param businessId
	 * @param tableName
	 */
	public void deleteFileById(String businessId, String tableName) {
		
		StringBuffer buffer = new StringBuffer(400);
		buffer.append("from CmAttachment where fromid = '").append(businessId).append("' ")
			.append(" and fromtablename = '").append(tableName).append("' ");
		List<CmAttachment> fileList = this.findPageByQuery(buffer.toString());
		if(!fileList.isEmpty()){
			for (CmAttachment file : fileList) {
				this.deleteById(CmAttachment.class, file.getAttchid());
			}
		}
	}

	
	public ITfQualBaseCommentDAO getTfQualBaseCommentDAO() {
		return tfQualBaseCommentDAO;
	}

	@Resource
	public void setTfQualBaseCommentDAO(ITfQualBaseCommentDAO tfQualBaseCommentDAO) {
		this.tfQualBaseCommentDAO = tfQualBaseCommentDAO;
	}
}
