package com.sms.security.basicdata.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sinoframe.common.ConstantList;

@Entity
@Table(name = "CM_ATTACHMENT")
public class CmAttachment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 编号 */
	private String attchid;
	/** 来源编号 */
	private String fromid;
	/** 来源表名 */
	private String fromtablename;
	/** 附件名称 */
	private String attchname;
	/** 附件地址 */
	private String attchpath;
	/**文件时候是图片*/
	@SuppressWarnings("unused")
	private Boolean flg;

	public CmAttachment() {
		super();
	}

	public CmAttachment(CmAttachment cmAttachment) {
		this.fromtablename = cmAttachment.getFromtablename();
		this.attchname = cmAttachment.getAttchname();
		this.attchpath = cmAttachment.getAttchpath();
		this.flg = cmAttachment.getFlg();
	}

	@Id
	@GeneratedValue(generator="attachment-uuid")
	@GenericGenerator(name="attachment-uuid",strategy="uuid")
	public String getAttchid() {
		return this.attchid;
	}

	public void setAttchid(String attchid) {
		this.attchid = attchid;
	}

	@Column(name = "FROMID")
	public String getFromid() {
		return this.fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	@Column(name = "FROMTABLENAME")
	public String getFromtablename() {
		return this.fromtablename;
	}

	public void setFromtablename(String fromtablename) {
		this.fromtablename = fromtablename;
	}

	@Column(name = "ATTCHNAME")
	public String getAttchname() {
		return this.attchname;
	}

	public void setAttchname(String attchname) {
		this.attchname = attchname;
	}

	@Column(name = "ATTCHPATH")
	public String getAttchpath() {
		return this.attchpath;
	}

	public void setAttchpath(String attchpath) {
		this.attchpath = attchpath;
	}

	@Transient
	public Boolean getFlg() {
		String ext=attchname.substring(attchname.lastIndexOf(".")+1);
		return ConstantList.IMAGESET.contains(ext.toLowerCase());
	}
	public void setFlg(Boolean flg) {
		this.flg = flg;
	}


}