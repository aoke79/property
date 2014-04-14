package com.sms.training.qualification.business;

import java.util.List;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQualBaseTypeBS extends IService {
   //根据资质类型id找到这个资质对象
	public List<TfQualBaseType> queryByTypeId(String typeId);
//	public  TfQualBaseType queryByTypeId(String typeId);
	/*
	 * 根据qtgroupid,originalgrade得到basetype对象
	 */
	public List<TfQualBaseType> queryByQtgroupId(String qtgroupid,String originalgrade);
	
	public List<TfQualBaseType> getListByQtgroupid(String qtgroupid );
	
    //通过小类id 去查询所有该小类下面的所有资质类型
	public List<TfQualBaseType> getListBySubGroupId(String subGroupId);
	/**
	 * 根据资质类型的大类分组id查找对应资质类型id
	 * @param groupId 资质类型的大类分组id
	 * @return
	 */
	public List<String> getTypeIdsByGroupId(String groupId);
}
