package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 描述： 机型
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_PLANE")
public class Cmodel implements java.io.Serializable{

	private static final long serialVersionUID = -6982514506819747556L;

	@Id
	@GeneratedValue(generator="typeid-uuid")
	@GenericGenerator(name="typeid-uuid", strategy = "uuid")
	@Column(name = "TYPEID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String  typeid; //机型代码
	
	@Column(name="TYPENAME",length=20)
	private String  typename; //机型名称
	
	@Column(name="FOSRTID",length=20)
	private String  fsort; //机型大类

	@Transient
	private String fsortName;
	
	public String getFsortName() {
		return fsortName;
	}

	public void setFsortName(String fsortName) {
		this.fsortName = fsortName;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getFsort() {
		return fsort;
	}

	public void setFsort(String fsort) {
		this.fsort = fsort;
	}
	

}
