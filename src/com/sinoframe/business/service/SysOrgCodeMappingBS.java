///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  SysOrgCodeMapping的service方法</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft jilili
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.business.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysOrgCodeMapping;
import com.sinoframe.bean.SysOrgCodeMappingId;
import com.sinoframe.business.ISysOrgCodeMappingBS;
import com.sinoframe.dao.ISysOrgCodeMappingDao;

@Service("sysOrgCodeMappingBS")
@Transactional
public class SysOrgCodeMappingBS extends BaseBS implements ISysOrgCodeMappingBS {

	private ISysOrgCodeMappingDao sysOrgCodeMappingDao;
	
	@Resource
	public void setSysOrgCodeMappingDao(
			ISysOrgCodeMappingDao sysOrgCodeMappingDao) {
		this.sysOrgCodeMappingDao = sysOrgCodeMappingDao;
	}

	@Override
	public void modify(SysOrgCodeMapping oldSysOrgCodeMapping, SysOrgCodeMapping newSysOrgCodeMapping) {
		if(oldSysOrgCodeMapping.getId().equals(newSysOrgCodeMapping.getId())){
			this.update(newSysOrgCodeMapping, newSysOrgCodeMapping.getId());
		}else{
			this.delete(oldSysOrgCodeMapping);
			this.save(newSysOrgCodeMapping);
		}
	}
}
