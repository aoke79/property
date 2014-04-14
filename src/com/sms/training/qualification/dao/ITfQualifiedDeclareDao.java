package com.sms.training.qualification.dao;

import java.util.List;

import com.sinoframe.dao.IPublicDao;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;

public interface ITfQualifiedDeclareDao extends IPublicDao{
	public List getPilotList(String orgid, String pilottechgrade);
	public List<String> getPilotListByOrgid(String atrid,String grade,String orgid,TfQualBaseAccessconditions tfQualBaseAccessconditions);
	/**
	 * 通过类别id删除待申报人员
	 * @param typeid
	 */
	public void deletePilotByTypeid(String typeid);
	/**
	 * 通过登陆人部门ID查找类型分类人员数量
	 * @param orgid
	 * @return
	 */
	public List<Object> getTypeCount(String orgid);
}
