package com.sinoframe.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * CmmUsernameTest entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_PERMISSION")
public class SysPermission implements java.io.Serializable,Cloneable  {
    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String scope;
    private String desiOperateId;
    private String conditionSql;
    private String perComment;
    
    private SysOrganization sysOrganization;
    private SysOperate sysOperate;
    // Constructors

    /** default constructor */
    public SysPermission() {
    }

    /** minimal constructor */
    public SysPermission(String id) {
        this.id = id;
    }
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public SysOperate getSysOperate() {
		return this.sysOperate;
	}

	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}
	
	@Column(name = "SCOPE",length=1)
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Column(name = "DESI_OPERATE_ID",length=36)
	public String getDesiOperateId() {
		return desiOperateId;
	}

	public void setDesiOperateId(String desiOperateId) {
		this.desiOperateId = desiOperateId;
	}

	@Column(name = "CONDITION_SQL",length=500)
	public String getConditionSql() {
		return conditionSql;
	}

	public void setConditionSql(String conditionSql) {
		this.conditionSql = conditionSql;
	}
	
	@Column(name = "PER_COMMENT",length=500)
	public String getPerComment() {
		return perComment;
	}

	public void setPerComment(String perComment) {
		this.perComment = perComment;
	}

	 public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }



	
	
	




    

}