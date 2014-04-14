package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 描述： 机型大类
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_FSORT")
public class CmodelBig implements java.io.Serializable{

	private static final long serialVersionUID = -6982514506819747556L;

	@Id
	@GeneratedValue(generator="typeid-uuid")
	@GenericGenerator(name="typeid-uuid", strategy = "uuid")
	@Column(name = "FSORT", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String  typeid; //机型代码
	
	@Column(name="FSORTNAME",length=20)
	private String  typename; //机型名称
	


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


}
