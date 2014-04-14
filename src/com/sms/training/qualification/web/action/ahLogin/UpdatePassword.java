package com.sms.training.qualification.web.action.ahLogin;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.business.ICmUserBS;
import com.sinoframe.common.util.Md5;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.AhUser;


@ParentPackage("json-default")
@Results({
		@Result(name = "json", type = "json"),
})
public class UpdatePassword extends BaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * 构造方法
	 */
	public UpdatePassword() {
	}
	//登录名
	private String account;
	//旧密码
	private String oldPassword;
	//新密码
	private String newPassword;
	//ajax - json返回是否成功标志 
	private boolean right;
	ICmUserBS cmUserBS;
	private AhUser ahUser;
	
	@JSON(serialize = false)
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	@JSON(serialize = false)
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	@JSON(serialize = false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@JSON(serialize = false)
	public ICmUserBS getCmUserBS() {
		return cmUserBS;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}
	@JSON(serialize = false)
	public AhUser getAhUser() {
		return ahUser;
	}

	public void setAhUser(AhUser ahUser) {
		this.ahUser = ahUser;
	}

	/**
	 * 修改密码
	 * @return
	 */
	public String modPwd() {
		try {
			List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", account);
			if(ahUserlist!=null && ahUserlist.size()!=0){
				ahUser=ahUserlist.get(0);
			}
			newPassword=Md5.getInstance().EncoderByMd5(newPassword,"");
			ahUser.setPassword(newPassword);
			cmUserBS.saveOrUpdate(ahUser);
			right = true;
		} catch (Exception e) {
			cmUserBS.getErrorLog().info(e.getMessage() + "#" +"");
			e.printStackTrace();
			right = false;
		}
		
		return "json";
	}
	
	/**
	 * 验证旧密码是否输入正确
	 * @return
	 * 先将读取的密码加密，然后再在ahuser这个表中查询是否有这条数据
	 */
	public String valid() {
		try {
			oldPassword=Md5.getInstance().EncoderByMd5(oldPassword,"");
			String attributename[]={"username","password"};
			String attributevalue[]={"'"+account+"'","'"+oldPassword+"'"};
			ahUser=cmUserBS.login("AhUser", attributename, attributevalue, "");
			right =ahUser!=null;
		} catch (Exception e) {
			cmUserBS.getErrorLog().info(e.getMessage() + "#" + "");
			e.printStackTrace();
			right = false;
		}
		return "json";
	}
	
	
}
