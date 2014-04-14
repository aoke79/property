package com.sms.training.reincarnation.bean;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述：阶段
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_PHASE")
public class PhaseInfo implements java.io.Serializable{

	private static final long serialVersionUID = -8550644770683521005L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;
	
	@Column(name="PHASE")
	private String phase;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}
	

}
