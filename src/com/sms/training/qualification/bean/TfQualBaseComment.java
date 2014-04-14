package com.sms.training.qualification.bean;
//default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//自动增长id
import org.hibernate.annotations.GenericGenerator;

import com.sinoframe.bean.SysRole;

/**
 * TfQualBaseComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_COMMENT")
public class TfQualBaseComment implements java.io.Serializable {

	// Fields

	private String commentid;
	private SysRole sysRole;
	private String contents;
	private String ctype;

	// Constructors构造函数

	/** default constructor */
	public TfQualBaseComment() {
	}

	/** minimal constructor */
	public TfQualBaseComment(String commentid) {
		this.commentid = commentid;
	}

	/** full constructor */
	public TfQualBaseComment(String commentid, SysRole sysRole, String contents,String ctype) {
		this.commentid = commentid;
		this.sysRole = sysRole;
		this.contents = contents;
		this.ctype = ctype;
	}

	// Property accessors属性访问器
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "COMMENTID", unique = true, nullable = false, length = 36)
	public String getCommentid() {
		return this.commentid;
	}
	
	

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	@ManyToOne(cascade={})
	@JoinColumn(name = "ROLEID")
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@Column(name = "CONTENTS", length = 500)
	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Column(name = "CTYPE", length = 5)
	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	
	

}