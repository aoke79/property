package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.AhAdmin;
import com.sms.training.qualification.bean.AhMail;
import com.sms.training.qualification.bean.AhMapping;
import com.sms.training.qualification.bean.AhPeruser;
import com.sms.training.qualification.bean.AhPsnOld;
import com.sms.training.qualification.bean.AhSystem;
import com.sms.training.qualification.bean.AhUser;
import com.sms.training.qualification.bean.Ahdetail;
import com.sms.training.qualification.business.IAhUserBS;

@Service("ahUserBS")
public class AhUserBS extends BaseBS implements IAhUserBS {
    public AhUser ahUser;
	@Override
	public List<Object> findClinteSystemByLoginname(String loginname,String flag) {
		StringBuffer buf = new StringBuffer("select distinct s.sysname,s.sysurl,pu.login,pu.plaincode,pu.password,p.ahUser.name,p.ahUser.hrid,p.ahUser.oldcode,s.sysid ,p.mflag from AhMapping p, AhSystem s,AhPeruser pu " +
				"where p.ahSystem.sysid = s.sysid and p.ahPeruser.peruuid = pu.peruuid ");
		if( !"ALL".equals(flag)){
			buf.append("and p.mflag = '1'");
		}
		buf.append(" and p.ahUser.username = '"+loginname +"'");
		
		return this.findPageByQuery(buf.toString());
	}

	@Override
	public int findCountFromMapping(String ahuserId) {
		String sql  = "";
		if(ahuserId != null && !"".equals(ahuserId)){
			sql = "select count(uuid) from AhMapping p where p.ahUser.useruuid ='" + ahuserId+"'";
		}
		return this.getCountByHql(sql);
	}

	@Override
	public List<AhMapping> findSystemByUserid(String useruid,String sysid) {
		String hql = "select p from AhMapping p where p.ahUser.useruuid = '"+useruid+"' and p.ahSystem.sysid='"+sysid+"'";
		return this.findPageByQuery(hql);
	}

	@Override
	public List<AhSystem> findAllSystemByUserid(String useruuid,String adminname) {
		String hql = "select s from AhSystem s where s.sysid not in (select p.ahSystem.sysid from AhMapping p where p.ahUser.useruuid = '"+useruuid+"') and s.sysid in (select a.ahSystem.sysid from AhAdmin a where a.ahUser.username = '"+adminname+"')";
		return this.findPageByQuery(hql);
	}

	@Override
	public boolean ifSeeAdminSystem(String useruuid) {
		int count = 0;
		boolean seeAdminSystem =false;
		String hql = "select count(*) from AhAdmin am where 1=1 and am.ahUser.useruuid = '"+useruuid.trim()+"'";
		count = this.getCountByHql(hql);
		seeAdminSystem=count!=0;
		return seeAdminSystem;
	}

	@Override
	public List<AhAdmin> findSystemListByUserid(String userid) {
		String hql = "select ad from AhAdmin ad where 1=1 and ad.ahUser.useruuid = '"+userid.trim()+"'";

		return this.findPageByQuery(hql);
	}

	@Override
	public List<AhSystem> findUnRoleSystemById(String userid) {
		String hql = "select s from AhSystem s where s.sysid not in (select ad.ahSystem.sysid from AhAdmin ad where ad.ahUser.useruuid = '"+userid.trim()+"')";
		return this.findPageByQuery(hql);
	}

	@Override
	public AhUser findUserByLoginname(String loginname) {
		String hql = "select u from AhUser u where u.username = '"+loginname+"'";
		if(this.findPageByQuery(hql).size()>0){
			ahUser = (AhUser)this.findPageByQuery(hql).get(0);
		}else{
			return null;
		}
		return ahUser;
	}

	@Override
	public List<AhSystem> findAllSystem() {
		String hql = "select s from AhSystem s where 1=1";
		return this.findPageByQuery(hql);
	}
	
	boolean flag = false;
	@Override
	public boolean ifLeave(String hrid, String flags) {
		String hql;
		if(!"".equals(flags) && flags != null && "change".equals(flags)){
			hql ="select t from AhPsnOld t where t.psncode = '"+hrid+"' and t.psnclscope ='4'";
		}else{
			hql ="select t from AhPsnOld t where t.psncode = '"+hrid+"' and t.psnclscope !='4'";
		}
		List<AhPsnOld> ahPsnOldList = this.findPageByQuery(hql);
		flag = ahPsnOldList.size() > 0?true:false;
		return flag;
	}

	public AhPeruser ahPeruser;
	@Override
	public AhPeruser findPuser(String systemid, String useruid) {
		String hql = "select pu from AhMapping p,AhPeruser pu where p.ahPeruser.peruuid = pu.peruuid and p.ahSystem.sysid = '"+systemid+"' and p.ahUser.useruuid = '"+useruid+"'";
		if(this.findPageByQuery(hql).size()>0){
			ahPeruser = (AhPeruser)this.findPageByQuery(hql).get(0);
		}else{
			return null;
		}
		return ahPeruser;
	}

	@Override
	public List<AhAdmin> ifCanSee(String username) {
		String hql ="select a from AhAdmin a where a.ahUser.useruuid='"+username+"'";
		return this.findPageByQuery(hql);
	}

	private AhMail ahmail;
	@Override
	public AhMail findAhmail(String userid) {
		String hql ="select a from AhMail a where a.anUser.useruuid='"+userid+"'";
		if(this.findPageByQuery(hql).size()>0){
			ahmail = (AhMail)this.findPageByQuery(hql).get(0);
		}else{
			return null;
		}
		return ahmail;
	}

}
