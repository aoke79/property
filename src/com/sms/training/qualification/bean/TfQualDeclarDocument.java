package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TF_QUAL_DECLAR_DOCUMENT",  uniqueConstraints = {})
public class TfQualDeclarDocument implements java.io.Serializable  {

		// Fields

		private String id;
		private String planType;
		private String creater;
		private Date createDate;
		private String orgGrade;
		private Date printDate;
		private String contectPerson;
		private String contectPhone;
		private String printOrg;
		private String pilots;
		private String upType;
		private String pilotGradeType;
		private String atrid;
		private String receiveOrg;
		private String issuer;
		private String documentId;
		private String title;
		private String copysendUnit;
		private String bureau;
		private Date techStandAppDate;
		private String declaredinfoid;
		private String sealId; 
		// Constructors

		/** default constructor */
		public TfQualDeclarDocument() {
		}

		/** full constructor */
		public TfQualDeclarDocument(String id, String planType, String creater,
				Date createDate, String orgGrade, Date printDate,
				String contectPerson, String contectPhone, String printOrg,
				String pilots, String upType, String pilotGradeType, String atrid,
				String receiveOrg, String issuer, String documentId, String title,
				String copysendUnit, String bureau, Date techStandAppDate, String declaredinfoid,
			    String sealId) {
			this.id = id;
			this.planType = planType;
			this.creater = creater;
			this.createDate = createDate;
			this.orgGrade = orgGrade;
			this.printDate = printDate;
			this.contectPerson = contectPerson;
			this.contectPhone = contectPhone;
			this.printOrg = printOrg;
			this.pilots = pilots;
			this.upType = upType;
			this.pilotGradeType = pilotGradeType;
			this.atrid = atrid;
			this.receiveOrg = receiveOrg;
			this.issuer = issuer;
			this.documentId = documentId;
			this.title = title;
			this.copysendUnit = copysendUnit;
			this.bureau = bureau;
			this.techStandAppDate = techStandAppDate;
			this.sealId=sealId;
			this.declaredinfoid=declaredinfoid;
		}

		// Property accessors
		@Id
		@GeneratedValue(generator="system-uuid")
		@GenericGenerator(name="system-uuid", strategy = "uuid")
		@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Column(name = "PLAN_TYPE", length = 50)
		public String getPlanType() {
			return this.planType;
		}

		public void setPlanType(String planType) {
			this.planType = planType;
		}

		@Column(name = "CREATER", length = 50)
		public String getCreater() {
			return this.creater;
		}

		public void setCreater(String creater) {
			this.creater = creater;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "CREATE_DATE", length = 7)
		public Date getCreateDate() {
			return this.createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		@Column(name = "ORG_GRADE", length = 50)
		public String getOrgGrade() {
			return this.orgGrade;
		}

		public void setOrgGrade(String orgGrade) {
			this.orgGrade = orgGrade;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "PRINT_DATE", length = 7)
		public Date getPrintDate() {
			return this.printDate;
		}

		public void setPrintDate(Date printDate) {
			this.printDate = printDate;
		}

		@Column(name = "CONTECT_PERSON", length = 50)
		public String getContectPerson() {
			return this.contectPerson;
		}

		public void setContectPerson(String contectPerson) {
			this.contectPerson = contectPerson;
		}

		@Column(name = "CONTECT_PHONE", length = 20)
		public String getContectPhone() {
			return this.contectPhone;
		}

		public void setContectPhone(String contectPhone) {
			this.contectPhone = contectPhone;
		}

		@Column(name = "PRINT_ORG", length = 50)
		public String getPrintOrg() {
			return this.printOrg;
		}

		public void setPrintOrg(String printOrg) {
			this.printOrg = printOrg;
		}

		@Column(name = "PILOTS", length = 1000)
		public String getPilots() {
			return this.pilots;
		}

		public void setPilots(String pilots) {
			this.pilots = pilots;
		}

		@Column(name = "UP_TYPE", length = 50)
		public String getUpType() {
			return this.upType;
		}

		public void setUpType(String upType) {
			this.upType = upType;
		}

		@Column(name = "PILOT_GRADE_TYPE", length = 50)
		public String getPilotGradeType() {
			return this.pilotGradeType;
		}

		public void setPilotGradeType(String pilotGradeType) {
			this.pilotGradeType = pilotGradeType;
		}

		@Column(name = "ATRID", length = 20)
		public String getAtrid() {
			return this.atrid;
		}

		public void setAtrid(String atrid) {
			this.atrid = atrid;
		}

		@Column(name = "RECEIVE_ORG", length = 50)
		public String getReceiveOrg() {
			return this.receiveOrg;
		}

		public void setReceiveOrg(String receiveOrg) {
			this.receiveOrg = receiveOrg;
		}

		@Column(name = "ISSUER", length = 20)
		public String getIssuer() {
			return this.issuer;
		}

		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}

		@Column(name = "DOCUMENT_ID", length = 50)
		public String getDocumentId() {
			return this.documentId;
		}

		public void setDocumentId(String documentId) {
			this.documentId = documentId;
		}

		@Column(name = "TITLE", length = 50)
		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Column(name = "COPYSEND_UNIT", length = 50)
		public String getCopysendUnit() {
			return this.copysendUnit;
		}

		public void setCopysendUnit(String copysendUnit) {
			this.copysendUnit = copysendUnit;
		}

		@Column(name = "BUREAU", length = 50)
		public String getBureau() {
			return this.bureau;
		}

		public void setBureau(String bureau) {
			this.bureau = bureau;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "TECH_STAND_APP_DATE", length = 7)
		public Date getTechStandAppDate() {
			return this.techStandAppDate;
		}
		@Column(name = "DECLAREDINFOID", length = 36)
		public String getDeclaredinfoid() {
			return declaredinfoid;
		}

		public void setDeclaredinfoid(String declaredinfoid) {
			this.declaredinfoid = declaredinfoid;
		}
		public void setTechStandAppDate(Date techStandAppDate) {
			this.techStandAppDate = techStandAppDate;
		}
		@Column(name = "SEAL_ID", length = 36)
		public String getSealId() {
			return sealId;
		}

		public void setSealId(String sealId) {
			this.sealId = sealId;
		}
		public boolean equals(Object other) {
			if ((this == other))
				return true;
			if ((other == null))
				return false;
			if (!(other instanceof TfQualDeclarDocument))
				return false;
			TfQualDeclarDocument castOther = (TfQualDeclarDocument) other;

			return ((this.getId() == castOther.getId()) || (this.getId() != null
					&& castOther.getId() != null && this.getId().equals(
					castOther.getId())))
					&& ((this.getPlanType() == castOther.getPlanType()) || (this
							.getPlanType() != null
							&& castOther.getPlanType() != null && this
							.getPlanType().equals(castOther.getPlanType())))
					&& ((this.getCreater() == castOther.getCreater()) || (this
							.getCreater() != null && castOther.getCreater() != null && this
							.getCreater().equals(castOther.getCreater())))
					&& ((this.getCreateDate() == castOther.getCreateDate()) || (this
							.getCreateDate() != null
							&& castOther.getCreateDate() != null && this
							.getCreateDate().equals(castOther.getCreateDate())))
					&& ((this.getOrgGrade() == castOther.getOrgGrade()) || (this
							.getOrgGrade() != null
							&& castOther.getOrgGrade() != null && this
							.getOrgGrade().equals(castOther.getOrgGrade())))
					&& ((this.getPrintDate() == castOther.getPrintDate()) || (this
							.getPrintDate() != null
							&& castOther.getPrintDate() != null && this
							.getPrintDate().equals(castOther.getPrintDate())))
					&& ((this.getContectPerson() == castOther.getContectPerson()) || (this
							.getContectPerson() != null
							&& castOther.getContectPerson() != null && this
							.getContectPerson()
							.equals(castOther.getContectPerson())))
					&& ((this.getContectPhone() == castOther.getContectPhone()) || (this
							.getContectPhone() != null
							&& castOther.getContectPhone() != null && this
							.getContectPhone().equals(castOther.getContectPhone())))
					&& ((this.getPrintOrg() == castOther.getPrintOrg()) || (this
							.getPrintOrg() != null
							&& castOther.getPrintOrg() != null && this
							.getPrintOrg().equals(castOther.getPrintOrg())))
					&& ((this.getPilots() == castOther.getPilots()) || (this
							.getPilots() != null && castOther.getPilots() != null && this
							.getPilots().equals(castOther.getPilots())))
					&& ((this.getUpType() == castOther.getUpType()) || (this
							.getUpType() != null && castOther.getUpType() != null && this
							.getUpType().equals(castOther.getUpType())))
					&& ((this.getPilotGradeType() == castOther.getPilotGradeType()) || (this
							.getPilotGradeType() != null
							&& castOther.getPilotGradeType() != null && this
							.getPilotGradeType().equals(
									castOther.getPilotGradeType())))
					&& ((this.getAtrid() == castOther.getAtrid()) || (this
							.getAtrid() != null && castOther.getAtrid() != null && this
							.getAtrid().equals(castOther.getAtrid())))
					&& ((this.getReceiveOrg() == castOther.getReceiveOrg()) || (this
							.getReceiveOrg() != null
							&& castOther.getReceiveOrg() != null && this
							.getReceiveOrg().equals(castOther.getReceiveOrg())))
					&& ((this.getIssuer() == castOther.getIssuer()) || (this
							.getIssuer() != null && castOther.getIssuer() != null && this
							.getIssuer().equals(castOther.getIssuer())))
					&& ((this.getDocumentId() == castOther.getDocumentId()) || (this
							.getDocumentId() != null
							&& castOther.getDocumentId() != null && this
							.getDocumentId().equals(castOther.getDocumentId())))
					&& ((this.getTitle() == castOther.getTitle()) || (this
							.getTitle() != null && castOther.getTitle() != null && this
							.getTitle().equals(castOther.getTitle())))
					&& ((this.getCopysendUnit() == castOther.getCopysendUnit()) || (this
							.getCopysendUnit() != null
							&& castOther.getCopysendUnit() != null && this
							.getCopysendUnit().equals(castOther.getCopysendUnit())))
					&& ((this.getBureau() == castOther.getBureau()) || (this
							.getBureau() != null && castOther.getBureau() != null && this
							.getBureau().equals(castOther.getBureau())))
					&& ((this.getTechStandAppDate() == castOther
							.getTechStandAppDate()) || (this.getTechStandAppDate() != null
							&& castOther.getTechStandAppDate() != null && this
							.getTechStandAppDate().equals(
									castOther.getTechStandAppDate())));
		}

		public int hashCode() {
			int result = 17;

			result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
			result = 37 * result
					+ (getPlanType() == null ? 0 : this.getPlanType().hashCode());
			result = 37 * result
					+ (getCreater() == null ? 0 : this.getCreater().hashCode());
			result = 37
					* result
					+ (getCreateDate() == null ? 0 : this.getCreateDate()
							.hashCode());
			result = 37 * result
					+ (getOrgGrade() == null ? 0 : this.getOrgGrade().hashCode());
			result = 37 * result
					+ (getPrintDate() == null ? 0 : this.getPrintDate().hashCode());
			result = 37
					* result
					+ (getContectPerson() == null ? 0 : this.getContectPerson()
							.hashCode());
			result = 37
					* result
					+ (getContectPhone() == null ? 0 : this.getContectPhone()
							.hashCode());
			result = 37 * result
					+ (getPrintOrg() == null ? 0 : this.getPrintOrg().hashCode());
			result = 37 * result
					+ (getPilots() == null ? 0 : this.getPilots().hashCode());
			result = 37 * result
					+ (getUpType() == null ? 0 : this.getUpType().hashCode());
			result = 37
					* result
					+ (getPilotGradeType() == null ? 0 : this.getPilotGradeType()
							.hashCode());
			result = 37 * result
					+ (getAtrid() == null ? 0 : this.getAtrid().hashCode());
			result = 37
					* result
					+ (getReceiveOrg() == null ? 0 : this.getReceiveOrg()
							.hashCode());
			result = 37 * result
					+ (getIssuer() == null ? 0 : this.getIssuer().hashCode());
			result = 37
					* result
					+ (getDocumentId() == null ? 0 : this.getDocumentId()
							.hashCode());
			result = 37 * result
					+ (getTitle() == null ? 0 : this.getTitle().hashCode());
			result = 37
					* result
					+ (getCopysendUnit() == null ? 0 : this.getCopysendUnit()
							.hashCode());
			result = 37 * result
					+ (getBureau() == null ? 0 : this.getBureau().hashCode());
			result = 37
					* result
					+ (getTechStandAppDate() == null ? 0 : this
							.getTechStandAppDate().hashCode());
			return result;
		}

}
 
