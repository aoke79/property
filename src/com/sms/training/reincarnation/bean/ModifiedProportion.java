package com.sms.training.reincarnation.bean;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * 描述: 改装比例
 * @author LUJIE
 *
 */

@Entity
@Table(name="MO_UP_CHANGE")
public class ModifiedProportion implements java.io.Serializable{
	

	private static final long serialVersionUID = -990503919440365882L;

	@Id
	@GeneratedValue(generator="id-uuid")
	@GenericGenerator(name="id-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	private String id;
	
	@Column(name="PHASE")
	private String phase;    //  PHASE  阶段
	
	@Column(name="RATE_CHANGE")
	private Integer change ;    //  RATE_CHANGE 改装比例

	@Transient
	private String phaseName;
	
	
	
	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

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

	public Integer getChange() {
		return change;
	}

	public void setChange(Integer change) {
		this.change = change;
	}
	@Transient
	private List<String[]> companyInfoList = new ArrayList<String[]>(); 
	
	public List<String[]> getCompanyInfoList() {
		return companyInfoList;
	}

	public void setCompanyInfoList(List<String[]> companyInfoList) {
		this.companyInfoList = companyInfoList;
	}

	public List<String[]> getCompanyInfoListnew() {
		return companyInfoListnew;
	}

	public void setCompanyInfoListnew(List<String[]> companyInfoListnew) {
		this.companyInfoListnew = companyInfoListnew;
	}
	@Transient
	private List<String[]> companyInfoListnew = new ArrayList<String[]>(); 


}
