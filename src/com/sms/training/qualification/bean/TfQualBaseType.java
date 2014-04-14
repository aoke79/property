package com.sms.training.qualification.bean;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Transient;
import com.sms.training.qualification.bean.TfQualPilotTechgrade;
/**
 * TfQualBaseType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_TYPE")
public class TfQualBaseType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeid;
	private TfQualBaseTypeGroup tfQualBaseTypeGroup;
	//原始技术级别many to one
	private TfQualPilotTechgrade originalgrade ;
	//目标级别many to one
	private TfQualPilotTechgrade targetgrade;
	//临时变量
	private List<String> originalAtrs=new ArrayList<String>();
	private List<String> originalGrades=new ArrayList<String>();
	//目标机型many to one
	private BaseAirPlanType targetatrid;
	//原始机型many to one
	private BaseAirPlanType originalatrid;
	private String typedesc;
	//删除标示符
	private String deleteflag;
	private TfQualBaseTgroup tfQualBaseTgroup;
	
	//对应训练类型many to one
	private TfQualBaseTrainingtype tfQualBaseTrainingtype;
	//模板信息
	private TfQualBaseTemplate tfQualBaseTemplate;
	// Constructors

	/** default constructor */
	public TfQualBaseType() {
	}

	/** minimal constructor */
	public TfQualBaseType(String typeid) {
		this.typeid = typeid;
	}

	/** full constructor */

	public TfQualBaseType(String typeid,
			TfQualBaseTypeGroup tfQualBaseTypeGroup,
			TfQualPilotTechgrade originalgrade, TfQualPilotTechgrade targetgrade,
			BaseAirPlanType targetatrid, BaseAirPlanType originalatrid,
			String typedesc, String deleteflag,TfQualBaseTgroup tfQualBaseTgroup,
			TfQualBaseTemplate tfQualBaseTemplate) {
		super();
		this.typeid = typeid;
		this.tfQualBaseTypeGroup = tfQualBaseTypeGroup;
		this.originalgrade = originalgrade;
		this.targetgrade = targetgrade;
		this.targetatrid = targetatrid;
		this.originalatrid = originalatrid;
		this.typedesc = typedesc;
		this.deleteflag = deleteflag;
		this.tfQualBaseTgroup = tfQualBaseTgroup;
		this.tfQualBaseTemplate = tfQualBaseTemplate;
	}


	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "TYPEID", unique = true, nullable = false, length = 36)
	public String getTypeid() {
		return this.typeid;
	}
	
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QTGROUPID")
	public TfQualBaseTypeGroup getTfQualBaseTypeGroup() {
		return this.tfQualBaseTypeGroup;
	}

	public void setTfQualBaseTypeGroup(TfQualBaseTypeGroup tfQualBaseTypeGroup) {
		this.tfQualBaseTypeGroup = tfQualBaseTypeGroup;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPEGROUP")
	public TfQualBaseTgroup getTfQualBaseTgroup() {
		return tfQualBaseTgroup;
	}
	public void setTfQualBaseTgroup(TfQualBaseTgroup tfQualBaseTgroup) {
		this.tfQualBaseTgroup = tfQualBaseTgroup;
	}
	
	
	@Column(name = "TYPEDESC", length = 50)
	public String getTypedesc() {
		return this.typedesc;
	}
	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ORIGINALGRADE")
	public TfQualPilotTechgrade getOriginalgrade() {
		return this.originalgrade;
	}

	public void setOriginalgrade(TfQualPilotTechgrade originalgrade) {
		this.originalgrade = originalgrade;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TARGETGRADE")
	public TfQualPilotTechgrade getTargetgrade() {
		return this.targetgrade;
	}

	public void setTargetgrade(TfQualPilotTechgrade targetgrade) {
		this.targetgrade = targetgrade;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TARGETATRID")
	public BaseAirPlanType getTargetatrid() {
		return this.targetatrid;
	}

	public void setTargetatrid(BaseAirPlanType targetatrid) {
		this.targetatrid = targetatrid;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ORIGINALATRID")
	public BaseAirPlanType getOriginalatrid() {
		return this.originalatrid;
	}

	public void setOriginalatrid(BaseAirPlanType originalatrid) {
		this.originalatrid = originalatrid;
	}

	@Column(name ="DELETEFLAG", length = 1)
	public String getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TRAINTYPE")
	public TfQualBaseTrainingtype getTfQualBaseTrainingtype() {
		return tfQualBaseTrainingtype;
	}

	public void setTfQualBaseTrainingtype(
			TfQualBaseTrainingtype tfQualBaseTrainingtype) {
		this.tfQualBaseTrainingtype = tfQualBaseTrainingtype;
	}
	@Transient
	public List<String> getOriginalAtrs() {
		return originalAtrs;
	}
	public void setOriginalAtrs(List<String> originalAtrs) {
		this.originalAtrs = originalAtrs;
	}
	
	@Transient
	public List<String> getOriginalGrades() {
		return originalGrades;
	}
	public void setOriginalGrades(List<String> originalGrades) {
		this.originalGrades = originalGrades;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID")
	public TfQualBaseTemplate getTfQualBaseTemplate() {
		return tfQualBaseTemplate;
	}

	public void setTfQualBaseTemplate(TfQualBaseTemplate tfQualBaseTemplate) {
		this.tfQualBaseTemplate = tfQualBaseTemplate;
	}
	
	
}