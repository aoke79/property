package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.AhAdmin;
import com.sms.training.qualification.bean.AhMail;
import com.sms.training.qualification.bean.AhMapping;
import com.sms.training.qualification.bean.AhPeruser;
import com.sms.training.qualification.bean.AhSystem;
import com.sms.training.qualification.bean.AhUser;
import com.sms.training.qualification.bean.Ahdetail;

public interface IAhUserBS extends IService {
	/**
	 * 根据当前登录名，查找到这个人在子系统中的登录名和对应的url
	 * @param loginname
	 * @return
	 */
   public List<Object> findClinteSystemByLoginname(String loginname,String flag);
   
   /**
    * 判断当前ahuser对象是否在mapping表中出现
    */
   public int findCountFromMapping(String ahuserId);
   /**
    * 查询出这个人可以查看几个子系统权限
    * @param useuid
    * @return
    */
   public List<AhMapping> findSystemByUserid(String useuid,String sysid);
   
   /**
    * 查询当前用户有哪些子系统没映射
    */
   public List<AhSystem> findAllSystemByUserid(String useruuid,String adminname);
   
   /**
    * 查询出是否可以查看管理系统
    */
   public boolean ifSeeAdminSystem(String useruuid);
   
   /**
    * 查询出当前用户已有的角色列表
    */
   public List<AhAdmin> findSystemListByUserid(String userid);
   
   /**
    * 查询出当前用户未有的角色列表
    */
   public List<AhSystem> findUnRoleSystemById(String userid);
   
   /**
    * 根据username 查找当前user对象
    */
	public AhUser findUserByLoginname(String loginname);   
   
  /**
	 * 查询所有系统
  */
   public List<AhSystem> findAllSystem();
   
   /**
    * 判断这个人是否是离职人员
    */
   public boolean ifLeave(String hrid,String flag);
   
   /**
    * 根据系统id和userid查找到外围用户的用户名、密码
    */
   public AhPeruser findPuser(String systemid,String useruid);
   
   /**
    * 判断当前登陆者能否看细粒度授权
    */
   public List<AhAdmin> ifCanSee(String username);
   
   /**
    * 获得发送邮件的时间
    */
   public AhMail findAhmail(String userid);
}
