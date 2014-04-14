package com.sms.security.basicdata.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

	
	@Entity
	@Table(name = "BASE_AIRPLANTYPE")
	public class BaseAirplanType implements Serializable {

		private static final long serialVersionUID = 1L;
		
		
		/** 编号 */
		private String id;
		/** 机型*/
		private String atrid;
		/** 描述 */
		private String atrdesc;
		/** 机型类型 */
		private String atrkind;
		
		
		@Id
		@GeneratedValue(generator="type-uuid")
		@GenericGenerator(name="type-uuid",strategy="uuid")
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		@Column(name="ATRID")
		public String getAtrid() {
			return atrid;
		}
		public void setAtrid(String atrid) {
			this.atrid = atrid;
		}
		
		@Column(name="ATRDESC")
		public String getAtrdesc() {
			return atrdesc;
		}
		public void setAtrdesc(String atrdesc) {
			this.atrdesc = atrdesc;
		}
		
		@Column(name="ATRKIND")
		public String getAtrkind() {
			return atrkind;
		}
		public void setAtrkind(String atrkind) {
			this.atrkind = atrkind;
		}
	
	}