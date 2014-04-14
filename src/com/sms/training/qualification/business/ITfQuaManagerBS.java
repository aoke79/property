package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseTrainingtype;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQuaManagerBS extends IService{
	
   public List<TfQualBaseType> qualityManager(String hql); 

   /**
    * 通过资质类型id查找训练分组类型列表
    * @param qualBaseTypeId
    * @return
    */
   public List<String> getGroupByBaseType(String qualBaseTypeId);
   
   /**
    * 通过资质类型id查找机型列表
    * @param qualBaseTypeId
    * @return
    */
   public List<String> getAirTypeByBaseType(String qualBaseTypeId);
   
   /**
    * 通过训练分组类型id和机型id查找对应训练类型列表
    * @param groupId
    * @param airTypeId
    * @return
    */
   public List<TfQualBaseTrainingtype> getBaseTrainingtypeList(String groupId, String airTypeId);
}
