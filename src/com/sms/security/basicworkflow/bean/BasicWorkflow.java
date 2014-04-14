/**
 * Title  BasicWorkflow
 * TableName  工作流键值
 * Description  bean of table "BASE_WORKFLOW"
 */

package com.sms.security.basicworkflow.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BASE_WORKFLOW")
public class BasicWorkflow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID */
	private String id;
	/** 来源表名 */
	private String bTableName;
	/** 对应相关业务号 */
	private String bTablePk;
	/** 工作流键值 */
	private String wFlowPk;
	
	@Id
	@GeneratedValue(generator="basewflow-uuid")
	@GenericGenerator(name="basewflow-uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="B_TABLENAME")
	public String getbTableName() {
		return bTableName;
	}
	public void setbTableName(String bTableName) {
		this.bTableName = bTableName;
	}
	
	@Column(name="B_TABLEPK")
	public String getbTablePk() {
		return bTablePk;
	}
	public void setbTablePk(String bTablePk) {
		this.bTablePk = bTablePk;
	}
	
	@Column(name="W_FLOWPK")
	public String getwFlowPk() {
		return wFlowPk;
	}
	public void setwFlowPk(String wFlowPk) {
		this.wFlowPk = wFlowPk;
	}
	
}
