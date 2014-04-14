package com.sinoframe.business.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOperateGroupRelation;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysRelationRoleOperateId;
import com.sinoframe.bean.CmUserOperateRelation;
import com.sinoframe.bean.CmUserOperateRelationId;
import com.sinoframe.business.ICmUserOperateRelationBS;
import com.sinoframe.dao.IPublicDao;
import com.sinoframe.dao.ISysOperateGroupRelationDao;
import com.sinoframe.dao.ICmUserOperateRelationDao;


@Service("cmUserOperateRelationBS")
public class CmUserOperateRelationBS extends BaseBS implements
		ICmUserOperateRelationBS {

	ICmUserOperateRelationDao cmUserOperateRelationDao;



	/**
	 * doAdd
	 * 
	 * @param entity
	 */
	public void doAdd(CmUserOperateRelation cmUserOperateRelation) {
		// TODO Auto-generated method stub
		this.save(cmUserOperateRelation);
	}

	/**
	 * doDel
	 * 
	 * @param entity
	 */
	public void doDel(CmUserOperateRelation cmUserOperateRelation) {
		// TODO Auto-generated method stub
		//cmUserOperateRelationDao.del(cmUserOperateRelation);
		this.delete(cmUserOperateRelation);
	}

	/**
	 * doUpdate
	 * 
	 * @param entity
	 */
	public void doUpdate(CmUserOperateRelation cmUserOperateRelation) {
		// TODO Auto-generated method stub
		//cmUserOperateRelationDao.update(cmUserOperateRelation);
		this.saveOrUpdate(cmUserOperateRelation);
	}

	/**
	 * getById
	 * 
	 * @param entity
	 */
	public CmUserOperateRelation getById(CmUserOperateRelationId id) {
		// TODO Auto-generated method stub
		return (CmUserOperateRelation) this.findById(
				CmUserOperateRelation.class, id);
	}

	public ICmUserOperateRelationDao getCmUserOperateRelationDao() {
		return cmUserOperateRelationDao;
	}

	@Resource
	public void setCmUserOperateRelationDao(
			ICmUserOperateRelationDao cmUserOperateRelationDao) {
		this.cmUserOperateRelationDao = cmUserOperateRelationDao;
	}

	
	@Override
	public void deleteByLianHeIds(String ids) {
		CmUserOperateRelationId cmUserOperateRelationId;
		String[] tempStrs=ids.split(",");
		for(String s:tempStrs){
			cmUserOperateRelationId=new CmUserOperateRelationId();
			String[] tempStrs1=s.split("&cmUserOperateRelationId.id=");
			System.err.println(tempStrs1[0]);
			System.err.println(tempStrs1[1]);
			cmUserOperateRelationId.setUserId(tempStrs1[0]);
			cmUserOperateRelationId.setId(tempStrs1[1]);
			this.deleteById(CmUserOperateRelation.class, cmUserOperateRelationId);
		}
	}


}
