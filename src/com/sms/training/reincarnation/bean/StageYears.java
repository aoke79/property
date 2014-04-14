package com.sms.training.reincarnation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述 :阶段年
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_PHASE_YEAR")
public class StageYears implements java.io.Serializable{

	private static final long serialVersionUID = 2781499857504591334L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;
	
	//YEAR
	@Column(name="YEAR")
	private String year;  //年
	
	@Column(name="PHASE")
	private String phase; //阶段 PHASE

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}
	
}
