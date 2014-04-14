package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述：技术等级
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_DEGREE")
public class TechnicalGrade implements java.io.Serializable{

	private static final long serialVersionUID = 7477874510181264719L;

	@Id
	@GeneratedValue(generator="degree-uuid")
	@GenericGenerator(name="degree-uuid", strategy = "uuid")
	@Column(name = "DEGREE", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String  degree;    // 技术等级  DEGREE

	@Column(name="DEGREENAME")
	private String  degreename;          // 飞行员名称 DEGREENAME

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegreename() {
		return degreename;
	}

	public void setDegreename(String degreename) {
		this.degreename = degreename;
	}
}
