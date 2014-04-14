package com.sms.training.qualification.bean;

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
 * 考试工作单考试项目  entity. 
 * @author licumn
 */
@Entity
@Table(name = "TF_QUAL_BASE_WORK_ORDER_ITEM")
public class TfQualBaseWorkOrderItem implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	//ID
	private String id;
	//考试工作单
	private TfQualBaseWorkOrder tfQualBaseWorkOrder;
	//考试项目ID
	private String examid;
	//考试项目是否通过
	private String examResult;
	//关于此项的备注
	private String examComment;
	//考试项目描述
	private String itemDesc;
	// Constructors

	/** default constructor */
	public TfQualBaseWorkOrderItem() {
	}

	/** full constructor */
	public TfQualBaseWorkOrderItem(TfQualBaseWorkOrder tfQualBaseWorkOrder,
			String examid, String examResult, String examComment,String itemDesc) {
		this.tfQualBaseWorkOrder = tfQualBaseWorkOrder;
		this.examid = examid;
		this.examResult = examResult;
		this.examComment = examComment;
		this.itemDesc=itemDesc;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERID")
	public TfQualBaseWorkOrder getTfQualBaseWorkOrder() {
		return this.tfQualBaseWorkOrder;
	}

	public void setTfQualBaseWorkOrder(TfQualBaseWorkOrder tfQualBaseWorkOrder) {
		this.tfQualBaseWorkOrder = tfQualBaseWorkOrder;
	}

	@Column(name = "EXAMID", length = 36)
	public String getExamid() {
		return this.examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	@Column(name = "EXAM_RESULT", length = 36)
	public String getExamResult() {
		return this.examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	@Column(name = "EXAM_COMMENT", length = 100)
	public String getExamComment() {
		return this.examComment;
	}

	public void setExamComment(String examComment) {
		this.examComment = examComment;
	}
	
	@Column(name = "ITEMDESC", length = 90)
	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

}